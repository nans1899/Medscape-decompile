package com.medscape.android.consult.fragments;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.Scopes;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.activity.ConsultProfileActivity;
import com.medscape.android.consult.adapters.ConsultProfileListAdapter;
import com.medscape.android.consult.interfaces.ICommunityUserReceivedListener;
import com.medscape.android.consult.interfaces.IFeedReceivedListener;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.IProfileDataChangedListener;
import com.medscape.android.consult.interfaces.IProfileTabChangedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.util.MedscapeException;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsultProfileFragment extends Fragment implements ICommunityUserReceivedListener, ILoadMoreListener, IProfileTabChangedListener, IFeedReceivedListener, IProfileDataChangedListener {
    static final String TAG = ConsultProfileFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public ConsultProfileListAdapter mAdapter;
    private boolean mCanLoad = false;
    /* access modifiers changed from: private */
    public ConsultUser mConsultUser;
    private View mContentWrapper;
    /* access modifiers changed from: private */
    public int mCurrentTab = 0;
    /* access modifiers changed from: private */
    public ConsultFeed mFollowerFeed;
    /* access modifiers changed from: private */
    public ConsultFeed mFollowingFeed;
    private MedscapeException mMedscapeException;
    /* access modifiers changed from: private */
    public ConsultFeed mPostsFeed;
    RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public ConsultFeed mResponsesFeed;
    private View mRootView;
    /* access modifiers changed from: private */
    public boolean mShowExceptionIfFailedtoLoad = false;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_consult_profile, viewGroup, false);
        this.mRootView = inflate;
        this.mContentWrapper = inflate.findViewById(R.id.content_wrapper);
        init();
        return this.mRootView;
    }

    private void init() {
        setUpRecyclerView();
        if (this.mConsultUser != null) {
            OmnitureManager.get().markModule("consult-profile", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
            ((BaseActivity) getActivity()).setCurrentPvid(OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", Scopes.PROFILE, this.mConsultUser.getUserId(), (String) null, (Map<String, Object>) null));
        }
    }

    public static ConsultProfileFragment newInstance(Bundle bundle) {
        ConsultProfileFragment consultProfileFragment = new ConsultProfileFragment();
        if (bundle != null) {
            ConsultUser consultUser = (ConsultUser) bundle.getParcelable(Constants.CONSULT_USER_BUNDLE_KEY);
            if (consultUser == null) {
                return null;
            }
            consultProfileFragment.setConsultUser(consultUser);
        }
        return consultProfileFragment;
    }

    public void onResume() {
        super.onResume();
        this.mPostsFeed = null;
        this.mResponsesFeed = null;
        this.mFollowerFeed = null;
        this.mFollowingFeed = null;
        setUpConsultUser();
    }

    /* access modifiers changed from: private */
    public void setUpConsultUser() {
        addUserToAdapter();
        if (this.mConsultUser != null) {
            ConsultDataManager.getInstance(getActivity(), getActivity()).getFullProfileForUser(getActivity(), this.mConsultUser.getUserId(), this);
        }
    }

    public void setConsultUser(ConsultUser consultUser) {
        this.mConsultUser = consultUser;
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) this.mRootView.findViewById(R.id.profile_recycler);
        this.mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (ConsultProfileFragment.this.shouldLoadNextSetOfData(linearLayoutManager, i2)) {
                    ConsultProfileFragment.this.loadNextSetOfData();
                }
                super.onScrolled(recyclerView, i, i2);
            }
        });
        if (this.mAdapter == null) {
            ConsultProfileListAdapter consultProfileListAdapter = new ConsultProfileListAdapter(getActivity(), this, this, this, ConsultUtils.isMedstudent(this.mConsultUser));
            this.mAdapter = consultProfileListAdapter;
            consultProfileListAdapter.disableTabs();
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000f, code lost:
        r1 = getCountToDisplay();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldLoadNextSetOfData(androidx.recyclerview.widget.LinearLayoutManager r1, int r2) {
        /*
            r0 = this;
            r1.getChildCount()
            r1.getItemCount()
            r1.findFirstVisibleItemPosition()
            boolean r1 = r0.mCanLoad
            if (r1 == 0) goto L_0x001b
            if (r2 <= 0) goto L_0x001b
            int r1 = r0.getCountToDisplay()
            int r2 = r0.getLoadedFeedItemsCount()
            if (r2 >= r1) goto L_0x001b
            r1 = 1
            goto L_0x001c
        L_0x001b:
            r1 = 0
        L_0x001c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.fragments.ConsultProfileFragment.shouldLoadNextSetOfData(androidx.recyclerview.widget.LinearLayoutManager, int):boolean");
    }

    /* access modifiers changed from: private */
    public void loadNextSetOfData() {
        this.mCanLoad = false;
        this.mAdapter.refreshTabs(false);
        int i = this.mCurrentTab;
        if (i == 0) {
            getPostsForUser(this.mConsultUser, this.mPostsFeed);
        } else if (i == 1) {
            getResponsesForUser(this.mConsultUser, this.mResponsesFeed);
        } else if (i == 2) {
            getUsersFollowedByUser(this.mConsultUser, this.mFollowingFeed);
        } else if (i == 3) {
            getUsersFollowingUser(this.mConsultUser, this.mFollowerFeed);
        }
    }

    private int getCountToDisplay() {
        ConsultUser consultUser = this.mConsultUser;
        if (consultUser != null) {
            int i = this.mCurrentTab;
            if (i == 0) {
                return consultUser.getPostCount();
            }
            if (i == 1) {
                return consultUser.getResponsesCount();
            }
            if (i == 2) {
                return consultUser.getFollowingCount();
            }
            if (i == 3) {
                return consultUser.getFollowerCount();
            }
        }
        return 0;
    }

    private int getLoadedFeedItemsCount() {
        ConsultFeed consultFeed;
        ConsultFeed consultFeed2;
        ConsultFeed consultFeed3;
        ConsultFeed consultFeed4;
        if (this.mCurrentTab == 0 && (consultFeed4 = this.mPostsFeed) != null && consultFeed4.getFeedItems() != null) {
            return this.mPostsFeed.getFeedItems().size();
        }
        if (this.mCurrentTab == 1 && (consultFeed3 = this.mResponsesFeed) != null && consultFeed3.getFeedItems() != null) {
            return getRepliesCount(this.mResponsesFeed);
        }
        if (this.mCurrentTab == 2 && (consultFeed2 = this.mFollowingFeed) != null && consultFeed2.getFeedItems() != null) {
            return this.mFollowingFeed.getFeedItems().size();
        }
        if (this.mCurrentTab != 3 || (consultFeed = this.mFollowerFeed) == null || consultFeed.getFeedItems() == null) {
            return 0;
        }
        return this.mFollowerFeed.getFeedItems().size();
    }

    private int getRepliesCount(ConsultFeed consultFeed) {
        List<ConsultFeedItem> feedItems;
        int i = 0;
        if (!(consultFeed == null || consultFeed.getFeedItems() == null || (feedItems = consultFeed.getFeedItems()) == null || feedItems.size() <= 0)) {
            for (ConsultFeedItem next : feedItems) {
                if (next instanceof ConsultPost) {
                    ConsultPost consultPost = (ConsultPost) next;
                    if (consultPost.getReplies() != null) {
                        i += consultPost.getReplies().size();
                    }
                }
            }
        }
        return i;
    }

    public void addUserToAdapter() {
        if (this.mAdapter != null && this.mConsultUser != null && getActivity() != null && !getActivity().isFinishing()) {
            Trace.i(TAG, "Trying to update the profile list on the ui thread");
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(ConsultProfileFragment.this.mConsultUser);
                    Trace.i(ConsultProfileFragment.TAG, "Updating the profile list on the ui thread");
                    ConsultProfileFragment.this.mAdapter.setItems(arrayList);
                }
            });
        }
    }

    public void onCurrentUserReceived(ConsultUser consultUser) {
        if (consultUser != null) {
            Trace.e(TAG, "Current user received");
            this.mConsultUser = consultUser;
            if (this.mAdapter != null) {
                Trace.i(TAG, "Setting flag to say we have the full profile");
                this.mAdapter.setHaveFullProfile(true);
            }
            setTitle();
            addUserToAdapter();
            getListItems();
        }
    }

    private void setTitle() {
        ActionBar supportActionBar = ((ConsultProfileActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null && this.mConsultUser != null) {
            supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + this.mConsultUser.getDisplayName() + "</font>"));
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
            supportActionBar.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
        }
    }

    private void getPostsForUser(final ConsultUser consultUser, final ConsultFeed consultFeed) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ConsultDataManager.getInstance(ConsultProfileFragment.this.getActivity(), ConsultProfileFragment.this.getActivity()).getPostsForUser(consultUser, consultFeed, ConsultProfileFragment.this);
                }
            });
        }
    }

    private void getResponsesForUser(final ConsultUser consultUser, final ConsultFeed consultFeed) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ConsultDataManager.getInstance(ConsultProfileFragment.this.getActivity(), ConsultProfileFragment.this.getActivity()).getResponsesForUser(consultUser, consultFeed, ConsultProfileFragment.this);
                }
            });
        }
    }

    private void getUsersFollowedByUser(final ConsultUser consultUser, final ConsultFeed consultFeed) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ConsultDataManager.getInstance(ConsultProfileFragment.this.getActivity(), ConsultProfileFragment.this.getActivity()).getUsersFollowedByUser(consultUser, consultFeed, ConsultProfileFragment.this);
                }
            });
        }
    }

    private void getUsersFollowingUser(final ConsultUser consultUser, final ConsultFeed consultFeed) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ConsultDataManager.getInstance(ConsultProfileFragment.this.getActivity(), ConsultProfileFragment.this.getActivity()).getUsersFollowingUser(consultUser, consultFeed, ConsultProfileFragment.this);
                }
            });
        }
    }

    public void onFailedToReceiveCurrentUser(MedscapeException medscapeException) {
        try {
            this.mMedscapeException = medscapeException;
            medscapeException.showSnackBar(this.mContentWrapper, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultProfileFragment.this.setUpConsultUser();
                }
            });
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show error for getting user info");
        }
        ConsultProfileListAdapter consultProfileListAdapter = this.mAdapter;
        if (consultProfileListAdapter != null) {
            consultProfileListAdapter.setFailedToReceiveFullProfile(true);
            addUserToAdapter();
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void updateProfileBitmapForCurrentUser(String str) {
        ConsultProfileListAdapter consultProfileListAdapter = this.mAdapter;
        if (consultProfileListAdapter != null) {
            consultProfileListAdapter.updateProfileBitMap(str);
        }
    }

    public void onMoreRequested() {
        getListItems();
    }

    /* access modifiers changed from: private */
    public void getListItems() {
        ConsultProfileListAdapter consultProfileListAdapter = this.mAdapter;
        if (consultProfileListAdapter != null) {
            consultProfileListAdapter.updateListWithProfileItems((List<ConsultFeedItem>) null, Constants.CONSULT_LIST_LOADING, false);
        }
        int i = this.mCurrentTab;
        if (i == 0) {
            getPostsForUser(this.mConsultUser, this.mPostsFeed);
        } else if (i == 1) {
            getResponsesForUser(this.mConsultUser, this.mResponsesFeed);
        } else if (i == 2) {
            getUsersFollowedByUser(this.mConsultUser, this.mFollowingFeed);
        } else if (i == 3) {
            getUsersFollowingUser(this.mConsultUser, this.mFollowerFeed);
        }
    }

    public void onTabChanged(int i) {
        if (this.mConsultUser != null) {
            this.mShowExceptionIfFailedtoLoad = true;
            MedscapeException medscapeException = this.mMedscapeException;
            if (medscapeException != null) {
                medscapeException.dismissSnackBar();
            }
            this.mCurrentTab = i;
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.mConsultUser);
            this.mAdapter.setItems(arrayList);
            if (i == 0) {
                getPostsForUser(this.mConsultUser, (ConsultFeed) null);
            } else if (i == 1) {
                getResponsesForUser(this.mConsultUser, (ConsultFeed) null);
            } else if (i == 2) {
                getUsersFollowedByUser(this.mConsultUser, (ConsultFeed) null);
            } else if (i == 3) {
                getUsersFollowingUser(this.mConsultUser, (ConsultFeed) null);
            }
        }
    }

    public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
        this.mShowExceptionIfFailedtoLoad = false;
        ConsultProfileListAdapter consultProfileListAdapter = this.mAdapter;
        if (consultProfileListAdapter != null) {
            consultProfileListAdapter.updateTotalItemsForFeedInUserObject(consultFeed.getTotalItems());
        }
        setCurrentFeed(consultFeed);
        this.mCanLoad = true;
        Trace.i(TAG, "Received feed for user");
        if (consultFeed == null) {
            return;
        }
        if (consultFeed.getFeedItems() == null || consultFeed.getFeedItems().size() != 0) {
            ConsultProfileListAdapter consultProfileListAdapter2 = this.mAdapter;
            if (consultProfileListAdapter2 != null) {
                consultProfileListAdapter2.updateListWithProfileItems(consultFeed.getFeedItems(), (String) null, true);
                return;
            }
            return;
        }
        ConsultProfileListAdapter consultProfileListAdapter3 = this.mAdapter;
        if (consultProfileListAdapter3 != null) {
            consultProfileListAdapter3.updateListWithProfileItems(new ArrayList(), (String) null, true);
        }
    }

    public void onFailedToReceiveFeed(MedscapeException medscapeException, int i, String str) {
        Trace.w(TAG, "Failed to receive feed for user");
        try {
            this.mMedscapeException = medscapeException;
            if (this.mShowExceptionIfFailedtoLoad) {
                medscapeException.showSnackBar(this.mContentWrapper, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean unused = ConsultProfileFragment.this.mShowExceptionIfFailedtoLoad = true;
                        if (ConsultProfileFragment.this.mCurrentTab == 0) {
                            ConsultFeed unused2 = ConsultProfileFragment.this.mPostsFeed = null;
                        } else if (ConsultProfileFragment.this.mCurrentTab == 1) {
                            ConsultFeed unused3 = ConsultProfileFragment.this.mResponsesFeed = null;
                        } else if (ConsultProfileFragment.this.mCurrentTab == 2) {
                            ConsultFeed unused4 = ConsultProfileFragment.this.mFollowingFeed = null;
                        } else if (ConsultProfileFragment.this.mCurrentTab == 3) {
                            ConsultFeed unused5 = ConsultProfileFragment.this.mFollowerFeed = null;
                        }
                        ConsultProfileFragment.this.getListItems();
                    }
                });
            } else {
                medscapeException.showAlert(getActivity(), "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }, (String) null, (DialogInterface.OnClickListener) null);
            }
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show error when getting posts for user");
        }
        this.mShowExceptionIfFailedtoLoad = false;
        ConsultProfileListAdapter consultProfileListAdapter = this.mAdapter;
        if (consultProfileListAdapter != null) {
            consultProfileListAdapter.updateListWithProfileItems((List<ConsultFeedItem>) null, Constants.CONSULT_LIST_ERROR, true);
        }
    }

    private void setCurrentFeed(ConsultFeed consultFeed) {
        int i = this.mCurrentTab;
        if (i == 0) {
            this.mPostsFeed = consultFeed;
        } else if (i == 1) {
            this.mResponsesFeed = consultFeed;
        } else if (i == 2) {
            this.mFollowingFeed = consultFeed;
        } else if (i == 3) {
            this.mFollowerFeed = consultFeed;
        }
    }

    public void onAffiliationChangedListener() {
        this.mRecyclerView.smoothScrollToPosition(0);
    }
}
