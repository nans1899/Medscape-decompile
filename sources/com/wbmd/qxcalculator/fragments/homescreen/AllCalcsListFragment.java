package com.wbmd.qxcalculator.fragments.homescreen;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.qxmd.qxrecyclerview.DividerItemDecoration;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.fragments.common.RefreshingFragment;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.rowItems.FeaturedContentRowItem;
import com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import java.util.List;

public class AllCalcsListFragment extends RefreshingFragment implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener, RowItemBuilder.OnRowItemsChangedListener {
    private QxRecyclerViewAdapter adapter;
    private QxRecyclerView listView;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return "Tab_All_Calculators";
    }

    public QxRecyclerViewAdapter getAdapter() {
        return this.adapter;
    }

    public void appRefreshComplete(boolean z) {
        if (z) {
            rebuildContentItemList((List<String>) null);
        }
        if (z) {
            QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
            if (qxRecyclerViewAdapter != null) {
                qxRecyclerViewAdapter.reset();
            }
            if (getView() != null) {
                this.listView.scrollToPosition(0);
                rebuildContentItemList((List<String>) null);
                QxRecyclerView qxRecyclerView = this.listView;
                if (qxRecyclerView != null) {
                    qxRecyclerView.scrollToPosition(0);
                    return;
                }
                return;
            }
            return;
        }
        QxRecyclerViewAdapter qxRecyclerViewAdapter2 = this.adapter;
        if (qxRecyclerViewAdapter2 != null && !qxRecyclerViewAdapter2.getHasBeenInitialized() && getView() != null) {
            rebuildContentItemList((List<String>) null);
        }
    }

    public void listDataChanged() {
        QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
        if (qxRecyclerViewAdapter != null) {
            qxRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public void rebuildCalculatorList() {
        QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
        if (qxRecyclerViewAdapter != null) {
            qxRecyclerViewAdapter.reset();
        }
        if (getView() != null) {
            this.listView.scrollToPosition(0);
            rebuildContentItemList((List<String>) null);
        }
    }

    public IntentFilter getBroadcastIntentFilter() {
        return super.getBroadcastIntentFilter();
    }

    public static AllCalcsListFragment newInstance() {
        return new AllCalcsListFragment();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("API", "AllCalcListFragment onAttach");
    }

    public void onDetach() {
        super.onDetach();
        Log.d("API", "AllCalcListFragment onDetach");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("API", "allCalcListFragment ONCREATE!!!!!");
        if (this.adapter == null) {
            Log.d("API", "allCalcListFragment ONCREATE adapter null");
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setHasStableIds(false);
            this.adapter.setOnClickListener(this);
        }
        RowItemBuilder.getInstance().setAllCalcRowItemsChangedListener(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("API", "allCalcListFragment onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_all_calcs_list, viewGroup, false);
        QxRecyclerView qxRecyclerView = (QxRecyclerView) inflate.findViewById(R.id.recycler_view);
        this.listView = qxRecyclerView;
        qxRecyclerView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.listView.setLayoutManager(linearLayoutManager);
        this.listView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("API", "allCalcListFragment onSaveInstance");
    }

    public void onResume() {
        super.onResume();
        Log.d("API", "AllCalcListFragment onresume");
        if (!this.adapter.getHasBeenInitialized() && !this.contentRefreshListener.isRefreshing()) {
            rebuildContentItemList((List<String>) null);
        }
    }

    public void onPause() {
        super.onPause();
        Log.d("API", "AllCalcListFragment onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d("API", "AllCalcListFragment onSTop");
    }

    public void onDestroyView() {
        Log.d("API", "allCalcListFragment onDestroyView");
        super.onDestroyView();
    }

    public void onDestroy() {
        Log.d("API", "allCalcListFragment onDestroy");
        super.onDestroy();
        RowItemBuilder.getInstance().setAllCalcRowItemsChangedListener((RowItemBuilder.OnRowItemsChangedListener) null);
    }

    private void rebuildContentItemList(List<String> list) {
        Log.d("API", "AllCalcListFragment rebuildContentItemList");
        this.adapter.reset();
        this.adapter.addSectionWithTitle("Test", RowItemBuilder.getInstance().getAllCalcRowItems());
        this.adapter.notifyDataSetChanged();
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        DBContentItem dBContentItem;
        Log.d("API", "rowItemclicked");
        if (qxRecyclerViewRowItem instanceof FeaturedContentRowItem) {
            FeaturedContentRowItem featuredContentRowItem = (FeaturedContentRowItem) qxRecyclerViewRowItem;
            dBContentItem = featuredContentRowItem.dbContentItem;
            if (featuredContentRowItem.featuredContentAd.promotionToUse != null) {
                EventsManager.getInstance().trackFeaturedContentClick(featuredContentRowItem.featuredContentAd, featuredContentRowItem.featuredContentAd.promotionToUse);
            }
        } else {
            dBContentItem = ((LeafItemRowItem) qxRecyclerViewRowItem).contentItem;
        }
        ContentItemLaunchManager.getInstance().launchContentItem(dBContentItem, getActivity());
    }
}
