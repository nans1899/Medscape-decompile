package com.wbmd.qxcalculator.fragments.homescreen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.omniture.utils.OmnitureHelper;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.QxSearchEventsCallback;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.activities.homescreen.SearchActivity;
import com.wbmd.qxcalculator.fragments.common.QxMDFragment;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBTag;
import com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.InvisibleHeaderRowItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends QxMDFragment implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener {
    private static final String KEY_HAS_LOADED_TAGS = "SearchFragment.KEY_HAS_LOADED_TAGS";
    private static LaunchQxCallback calcCallback;
    public static QxSearchEventsCallback callback;
    private QxRecyclerViewAdapter adapter;
    /* access modifiers changed from: private */
    public List<QxRecyclerViewRowItem> allRowItems;
    private TextView emptyTextView;
    private View emptyView;
    private List<QxRecyclerViewRowItem> filteredRowItems;
    /* access modifiers changed from: private */
    public boolean hasLoadedTags;
    private QxRecyclerView listView;
    private View loadingView;

    protected enum ViewMode {
        LOADING,
        ERROR,
        NORMAL
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public static SearchFragment newInstance(QxSearchEventsCallback qxSearchEventsCallback) {
        callback = qxSearchEventsCallback;
        return new SearchFragment();
    }

    public static SearchFragment newInstance(QxSearchEventsCallback qxSearchEventsCallback, LaunchQxCallback launchQxCallback) {
        callback = qxSearchEventsCallback;
        calcCallback = launchQxCallback;
        return new SearchFragment();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.adapter == null) {
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setHasStableIds(false);
            this.adapter.setOnClickListener(this);
        }
        this.allRowItems = new ArrayList(RowItemBuilder.getInstance().getAllCalcRowItems());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("API", "allCalcListFragment onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_search_calc, viewGroup, false);
        QxRecyclerView qxRecyclerView = (QxRecyclerView) inflate.findViewById(R.id.recycler_view);
        this.listView = qxRecyclerView;
        qxRecyclerView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.listView.setLayoutManager(linearLayoutManager);
        this.emptyView = inflate.findViewById(R.id.empty_view);
        this.emptyTextView = (TextView) inflate.findViewById(R.id.empty_text_view);
        this.loadingView = inflate.findViewById(R.id.progress_layout);
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_HAS_LOADED_TAGS, this.hasLoadedTags);
    }

    public void onDestroy() {
        Log.d("API", "allCalcListFragment onDestroy");
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        AnalyticsHandler.getInstance().trackPageView(getActivity(), "Search_Page");
        Log.d("API", "AllCalcListFragment onresume");
        if (this.adapter.getHasBeenInitialized()) {
            showLoadingView(false);
            if (getActivity() == null || ((SearchActivity) getActivity()).searchString == null || ((SearchActivity) getActivity()).searchString.isEmpty()) {
                this.emptyView.setVisibility(8);
                this.listView.setVisibility(0);
            } else {
                List<QxRecyclerViewRowItem> list = this.filteredRowItems;
                if (list == null || list.isEmpty()) {
                    this.emptyView.setVisibility(0);
                    this.listView.setVisibility(8);
                } else {
                    this.emptyView.setVisibility(8);
                    this.listView.setVisibility(0);
                }
            }
        } else {
            rebuildContentItemList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string.search).toLowerCase());
        arrayList.add(getString(R.string.list));
        OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList);
    }

    public void onPause() {
        super.onPause();
        Log.d("API", "AllCalcListFragment onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d("API", "AllCalcListFragment onSTop");
    }

    private void rebuildContentItemList() {
        Log.d("API", "AllCalcListFragment rebuildContentItemList");
        this.adapter.reset();
        if (!this.hasLoadedTags) {
            showLoadingView(true);
            new Handler().post(new Runnable() {
                public void run() {
                    new LoadTagOperation().execute(new Void[0]);
                }
            });
        }
        List<QxRecyclerViewRowItem> list = this.allRowItems;
        this.emptyView.setVisibility(8);
        this.listView.setVisibility(0);
        this.adapter.addSectionWithHeaderItem(new InvisibleHeaderRowItem(), list);
        this.adapter.notifyDataSetChanged();
    }

    public void onDetach() {
        super.onDetach();
        Log.d("API", "AllCalcListFragment onDetach");
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        Log.d("API", "rowItemclicked");
        DBContentItem dBContentItem = ((LeafItemRowItem) qxRecyclerViewRowItem).contentItem;
        QxSearchEventsCallback qxSearchEventsCallback = callback;
        if (qxSearchEventsCallback != null) {
            qxSearchEventsCallback.onClickSearchResult(dBContentItem, i);
        }
        Intent intent = new Intent();
        intent.putExtra(CalculatorActivity.KEY_EXTRA_CALC_FROM_SECTION, getString(R.string.search_firebase_action));
        ContentItemLaunchManager.getInstance().launchContentItem(dBContentItem, getActivity(), calcCallback, intent);
    }

    public void toolbarSearchEnterred(String str) {
        updateListForSearchTerm(str);
    }

    private void updateListForSearchTerm(String str) {
        if (str == null || str.isEmpty()) {
            List<QxRecyclerViewRowItem> list = this.allRowItems;
            if (list != null && !list.isEmpty()) {
                this.filteredRowItems = null;
                this.adapter.reset();
                this.adapter.addSectionWithHeaderItem(new InvisibleHeaderRowItem(), this.allRowItems);
                this.emptyView.setVisibility(8);
                this.listView.setVisibility(0);
                this.adapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        this.filteredRowItems = new ArrayList(this.allRowItems.size());
        String lowerCase = str.toLowerCase(Locale.US);
        for (QxRecyclerViewRowItem next : this.allRowItems) {
            if (next.getTitle().toLowerCase(Locale.US).contains(lowerCase)) {
                this.filteredRowItems.add(next);
            } else {
                DBContentItem dBContentItem = ((LeafItemRowItem) next).contentItem;
                if (dBContentItem.getTags() != null && !dBContentItem.getTags().isEmpty()) {
                    Iterator<DBTag> it = dBContentItem.getTags().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().getName().toLowerCase().contains(lowerCase)) {
                                this.filteredRowItems.add(next);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        this.adapter.reset();
        if (!this.filteredRowItems.isEmpty()) {
            this.emptyView.setVisibility(8);
            this.listView.setVisibility(0);
            this.adapter.addSectionWithHeaderItem(new InvisibleHeaderRowItem(), this.filteredRowItems);
        } else {
            this.emptyView.setVisibility(0);
            this.listView.setVisibility(8);
            this.emptyTextView.setText(getString(R.string.no_search_results, str));
        }
        this.adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void showLoadingView(boolean z) {
        if (z) {
            setViewVisible(this.loadingView, true);
        } else {
            setViewVisible(this.loadingView, false);
        }
    }

    private void setViewVisible(View view, boolean z) {
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private class LoadTagOperation extends AsyncTask<Void, Void, Void> {
        private LoadTagOperation() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            for (QxRecyclerViewRowItem qxRecyclerViewRowItem : SearchFragment.this.allRowItems) {
                ((LeafItemRowItem) qxRecyclerViewRowItem).contentItem.getTags();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            if (SearchFragment.this.getActivity() != null) {
                SearchFragment.this.showLoadingView(false);
                boolean unused = SearchFragment.this.hasLoadedTags = true;
                if (SearchFragment.callback != null) {
                    SearchFragment.callback.onSearchResultsLoaded();
                }
            }
        }
    }
}
