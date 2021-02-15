package com.medscape.android.consult.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.ads.AdRequestHelper;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.adapters.ConsultSearchListAdapter;
import com.medscape.android.consult.interfaces.IFeedReceivedListener;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.IPostReceivedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel;
import com.medscape.android.drugs.details.util.AdHelper;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultSearchFragment extends Fragment implements ILoadMoreListener, IFeedReceivedListener, OnAdListener {
    static final String TAG = ConsultSearchFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public ConsultSearchListAdapter mAdapter;
    private int mConsultSearchType = Constants.CONSULT_SEARCH_POSTS;
    private int mCurrentTab = 0;
    private View mNoResultsView;
    private ConsultFeed mPostsFeed;
    /* access modifiers changed from: private */
    public View mProgressDialog;
    private View mQueryHintView;
    RecyclerView mRecyclerView;
    private View mRootView;
    private String mSearchQuery = "";
    private ConsultFeed mTagsFeed;
    private ConsultFeed mUsersFeed;
    private ConsultSponsoredAdsViewModel sponsoredAdsViewModel;

    public void isAdExpandedByUser(boolean z) {
    }

    public void onAdAvailable() {
    }

    public void onAdNotAvilable() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getActivity() != null) {
            this.sponsoredAdsViewModel = (ConsultSponsoredAdsViewModel) ViewModelProviders.of(getActivity()).get(ConsultSponsoredAdsViewModel.class);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.consult_search, viewGroup, false);
        this.mRootView = inflate;
        this.mProgressDialog = inflate.findViewById(R.id.progressBar);
        this.mNoResultsView = this.mRootView.findViewById(R.id.no_results_msg);
        this.mQueryHintView = this.mRootView.findViewById(R.id.query_hint);
        setUpRecyclerView();
        setObserver();
        return this.mRootView;
    }

    private void setObserver() {
        ConsultSponsoredAdsViewModel consultSponsoredAdsViewModel = this.sponsoredAdsViewModel;
        if (consultSponsoredAdsViewModel != null) {
            consultSponsoredAdsViewModel.getPostID().observe(this, new Observer<String>() {
                public void onChanged(String str) {
                    ConsultSearchFragment.this.requestSponsoredPost(str);
                }
            });
        }
    }

    private void requestAd() {
        if (this.mCurrentTab == 0) {
            new AdRequestHelper().makeSponsoredPostADCall(getActivity(), new AdHelper().getConsultScreenMap(getActivity()), this);
            ConsultSearchListAdapter consultSearchListAdapter = this.mAdapter;
            if (consultSearchListAdapter != null) {
                consultSearchListAdapter.resetSponsoredPost();
            }
        }
    }

    public void requestSponsoredPost(String str) {
        ConsultDataManager.getInstance(getActivity(), getActivity()).getSponsoredAdPost(str, new IPostReceivedListener() {
            public void onPostRequestFailed(MedscapeException medscapeException) {
            }

            public void onPostReceived(ConsultPost consultPost) {
                if (ConsultSearchFragment.this.mAdapter != null && ConsultSearchFragment.this.mAdapter.addSponsoredPost(consultPost)) {
                    ConsultSearchFragment.this.mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public static ConsultSearchFragment newInstance() {
        return new ConsultSearchFragment();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) this.mRootView.findViewById(R.id.search_recycler);
        this.mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (this.mAdapter == null) {
            this.mAdapter = new ConsultSearchListAdapter(getActivity(), this);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    public void clearSearch() {
        this.mPostsFeed = null;
        this.mUsersFeed = null;
        this.mTagsFeed = null;
        this.mSearchQuery = "";
        this.mNoResultsView.setVisibility(8);
        this.mProgressDialog.setVisibility(8);
        this.mRecyclerView.setVisibility(8);
        this.mQueryHintView.setVisibility(0);
    }

    public void loadFeedForSearchRequest(int i, String str) {
        ConsultSearchListAdapter consultSearchListAdapter = new ConsultSearchListAdapter(getActivity(), this);
        this.mAdapter = consultSearchListAdapter;
        this.mRecyclerView.setAdapter(consultSearchListAdapter);
        refreshCurrentTabForListAdapter(i);
        requestAd();
        setBackgroundForFeedType(i);
        this.mRecyclerView.setVisibility(8);
        this.mNoResultsView.setVisibility(8);
        setCurrentFeed((ConsultFeed) null, i);
        this.mSearchQuery = str;
        this.mConsultSearchType = i;
        if (StringUtil.isNotEmpty(str)) {
            this.mQueryHintView.setVisibility(8);
            View view = this.mProgressDialog;
            if (view != null) {
                view.setVisibility(0);
            }
            Util.hideKeyboard(getActivity());
            fetchResultsForQuery();
        }
    }

    private void searchForPostsWithText(String str) {
        ConsultDataManager.getInstance(getActivity(), getActivity()).searchContentForText(str, getCurrentFeed(), this, this.mConsultSearchType);
    }

    private void searchForTagsWithText(String str) {
        ConsultDataManager.getInstance(getActivity(), getActivity()).getTagSearchResultsForText(str, this, this.mConsultSearchType);
    }

    private void searchForUsersWithText(String str) {
        ConsultDataManager.getInstance(getActivity(), getActivity()).searchUsersForText(str, getCurrentFeed(), this, this.mConsultSearchType);
    }

    private void refreshCurrentTabForListAdapter(int i) {
        ConsultSearchListAdapter consultSearchListAdapter = this.mAdapter;
        if (consultSearchListAdapter == null) {
            return;
        }
        if (i == 3024) {
            this.mCurrentTab = 0;
            consultSearchListAdapter.refreshCurrentTab(0);
        } else if (i == 3025) {
            this.mCurrentTab = 1;
            consultSearchListAdapter.refreshCurrentTab(1);
        } else if (i == 3026) {
            this.mCurrentTab = 2;
            consultSearchListAdapter.refreshCurrentTab(2);
        }
    }

    private void setBackgroundForFeedType(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return;
        }
        if (i == 3025) {
            recyclerView.setBackgroundColor(getResources().getColor(17170443));
        } else {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.consult_timeline_background));
        }
    }

    private void setCurrentFeed(ConsultFeed consultFeed, int i) {
        if (i == 3024) {
            this.mPostsFeed = consultFeed;
        } else if (i == 3025) {
            this.mTagsFeed = consultFeed;
        } else if (i == 3026) {
            this.mUsersFeed = consultFeed;
        }
    }

    private ConsultFeed getCurrentFeed() {
        int i = this.mCurrentTab;
        if (i == 0) {
            return this.mPostsFeed;
        }
        if (i == 1) {
            return this.mTagsFeed;
        }
        if (i == 2) {
            return this.mUsersFeed;
        }
        return null;
    }

    public void onMoreRequested() {
        if (Util.isOnline(getContext())) {
            ConsultSearchListAdapter consultSearchListAdapter = this.mAdapter;
            if (consultSearchListAdapter != null) {
                consultSearchListAdapter.updateListWithSearchItems((List<ConsultFeedItem>) null, Constants.CONSULT_LIST_LOADING);
                this.mAdapter.saveSponsoredPost();
            }
            fetchResultsForQuery();
            return;
        }
        final MedscapeException medscapeException = new MedscapeException(getContext().getString(R.string.consult_error_message_title), getContext().getString(R.string.internet_required));
        medscapeException.showSnackBar(this.mRecyclerView, -1, "OK", new View.OnClickListener() {
            public void onClick(View view) {
                medscapeException.dismissSnackBar();
            }
        });
    }

    private void fetchResultsForQuery() {
        int i = this.mConsultSearchType;
        if (i == 3024) {
            searchForPostsWithText(this.mSearchQuery);
        } else if (i == 3025) {
            searchForTagsWithText(this.mSearchQuery);
        } else if (i == 3026) {
            searchForUsersWithText(this.mSearchQuery);
        }
    }

    private String getFilterFromType() {
        int i = this.mConsultSearchType;
        if (i == 3024) {
            return "posts";
        }
        if (i == 3025) {
            return ContentParser.TAGS;
        }
        return i == 3026 ? "users" : "posts";
    }

    public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
        String str2 = TAG;
        Trace.i(str2, "Received feed for " + str + " for feed type " + i);
        if (i == this.mConsultSearchType && StringUtil.isNotEmpty(this.mSearchQuery) && this.mSearchQuery.equalsIgnoreCase(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("wapp.filter", "app_" + getFilterFromType());
            hashMap.put("wapp.query", "app-msp-" + str);
            hashMap.put("wapp.results", "app-msp-" + consultFeed.getTotalItems());
            OmnitureManager.get().markModule("consult-srch-btn", getFilterFromType(), hashMap);
            ((BaseActivity) getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "searchresults", (String) null, (String) null, (Map<String, Object>) null));
            View view = this.mProgressDialog;
            if (view != null) {
                view.setVisibility(8);
            }
            setCurrentFeed(consultFeed, i);
            Trace.i(TAG, "Received feed for search");
            if (consultFeed != null) {
                this.mRecyclerView.setVisibility(0);
                this.mNoResultsView.setVisibility(8);
                if (consultFeed.getFeedItems() == null || consultFeed.getFeedItems().size() <= 0) {
                    this.mRecyclerView.setVisibility(8);
                    this.mNoResultsView.setVisibility(0);
                    return;
                }
                ConsultSearchListAdapter consultSearchListAdapter = this.mAdapter;
                if (consultSearchListAdapter != null) {
                    consultSearchListAdapter.setListItemCount(consultFeed.getTotalItems());
                    this.mAdapter.updateListWithSearchItems(consultFeed.getFeedItems(), (String) null);
                }
            }
        }
    }

    public void onFailedToReceiveFeed(final MedscapeException medscapeException, int i, String str) {
        String str2 = TAG;
        Trace.i(str2, "Failed to receive feed for " + str + " for feed type " + i);
        if (i == this.mConsultSearchType && StringUtil.isNotEmpty(this.mSearchQuery) && this.mSearchQuery.equalsIgnoreCase(str)) {
            if (medscapeException != null) {
                try {
                    if (Util.isOnline(getContext())) {
                        medscapeException.showAlert(getActivity(), "OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    } else {
                        this.mRecyclerView.setVisibility(8);
                        this.mNoResultsView.setVisibility(0);
                        medscapeException.setMessage(getResources().getString(R.string.internet_required));
                        medscapeException.showSnackBar(this.mRecyclerView, -1, "OK", new View.OnClickListener() {
                            public void onClick(View view) {
                                medscapeException.dismissSnackBar();
                            }
                        });
                    }
                } catch (Exception unused) {
                    Trace.w(TAG, "Failed to show failure alert");
                }
            }
            if (getActivity() != null && !getActivity().isFinishing()) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (ConsultSearchFragment.this.mProgressDialog != null) {
                            ConsultSearchFragment.this.mProgressDialog.setVisibility(8);
                        }
                    }
                });
            }
            if (getCurrentFeed() == null) {
                this.mRecyclerView.setVisibility(8);
                this.mQueryHintView.setVisibility(0);
                return;
            }
            ConsultSearchListAdapter consultSearchListAdapter = this.mAdapter;
            if (consultSearchListAdapter != null) {
                consultSearchListAdapter.updateListWithSearchItems((List<ConsultFeedItem>) null, Constants.CONSULT_LIST_ERROR);
            }
        }
    }

    public String getCurrentPvid() {
        if (getActivity() == null || !isAdded() || !(getActivity() instanceof BaseActivity)) {
            return null;
        }
        return ((BaseActivity) getActivity()).getCurrentPvid();
    }
}
