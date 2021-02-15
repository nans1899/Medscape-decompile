package com.wbmd.qxcalculator.fragments.homescreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItemWrapper;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.fragments.common.RefreshingFragment;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.managers.QxFirebaseEventManager;
import com.wbmd.qxcalculator.model.db.DBCategory;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.rowItems.CategoryRowItem;
import com.wbmd.qxcalculator.model.rowItems.FeaturedContentRowItem;
import com.wbmd.qxcalculator.model.rowItems.GroupRowItem;
import com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.InvisibleHeaderRowItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.FirebaseEventsConstants;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import com.wbmd.qxcalculator.util.SharedPreferenceHelper;
import com.wbmd.qxcalculator.views.ArrowView;
import java.util.ArrayList;
import java.util.List;

public class GroupedListFragment extends RefreshingFragment implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener, QxRecyclerViewAdapter.OnRecyclerViewRowItemExpandCollapseListener, RowItemBuilder.OnRowItemsChangedListener {
    private static final String KEY_EXPANDED_IDS = "KEY_EXPANDED_IDS";
    protected static LaunchQxCallback calcCallback;
    /* access modifiers changed from: private */
    public QxRecyclerViewAdapter adapter;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d("BROADCAST", "receive " + intent.getAction());
            if (intent.getAction().equals(DataManager.KEY_DID_LOGOUT) && GroupedListFragment.this.adapter != null) {
                GroupedListFragment.this.adapter.reset();
                GroupedListFragment.this.adapter.notifyDataSetChanged();
            }
        }
    };
    private QxRecyclerView listView;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return "Tab_Specialty";
    }

    public QxRecyclerViewAdapter getAdapter() {
        return this.adapter;
    }

    public void appRefreshComplete(boolean z) {
        if (z) {
            QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
            if (qxRecyclerViewAdapter != null) {
                qxRecyclerViewAdapter.reset();
            }
            if (getView() != null) {
                this.listView.scrollToPosition(0);
                rebuildContentItemList((List<String>) null);
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
            rebuildContentItemList((List<String>) null);
            QxRecyclerView qxRecyclerView = this.listView;
            if (qxRecyclerView != null) {
                qxRecyclerView.scrollToPosition(0);
            }
        }
    }

    public IntentFilter getBroadcastIntentFilter() {
        return super.getBroadcastIntentFilter();
    }

    public static GroupedListFragment newInstance() {
        return new GroupedListFragment();
    }

    public static GroupedListFragment newInstance(LaunchQxCallback launchQxCallback, boolean z) {
        calcCallback = launchQxCallback;
        RowItemBuilder.getInstance().setShowFeaturedContentRowItem(z);
        return new GroupedListFragment();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("API", "GroupedListFragment onAttach " + activity);
    }

    public void onDetach() {
        super.onDetach();
        Log.d("API", "GroupedListFragment onDetach");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("API", "groupedListFragment ONCREATE!!!!!");
        if (this.adapter == null) {
            Log.d("API", "groupedListFragment ONCREATE adapter null");
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setHasStableIds(false);
            this.adapter.setOnClickListener(this);
            this.adapter.setOnExpandCollapseListener(this);
        }
        if (bundle != null) {
            Log.d("API", "groupedListFragment onCreateView - savedInstance not Null");
            ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_EXPANDED_IDS);
            Log.d("API", "groupedListFragment onCreateView - expandedIds " + stringArrayList);
            rebuildContentItemList(stringArrayList);
        }
        RowItemBuilder.getInstance().setGroupedCalcRowItemsChangedListener(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DataManager.KEY_DID_LOGOUT);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.broadcastReceiver, intentFilter);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("API", "groupedListFragment onCreateView");
        QxRecyclerView qxRecyclerView = (QxRecyclerView) layoutInflater.inflate(R.layout.fragment_grouped_list, viewGroup, false);
        this.listView = qxRecyclerView;
        qxRecyclerView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.listView.setLayoutManager(linearLayoutManager);
        return this.listView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("API", "groupedListFragment onSaveInstance");
        List<QxRecyclerViewRowItem> rowItems = this.adapter.getRowItems();
        Log.d("API", "groupedListFragment onSaveInstance rowItems count " + rowItems.size());
        ArrayList<String> expandedIdsInGroupedList = getExpandedIdsInGroupedList(rowItems);
        Log.d("API", "groupedListFragment onSaveInstance a - expanded Ids " + expandedIdsInGroupedList);
        bundle.putStringArrayList(KEY_EXPANDED_IDS, expandedIdsInGroupedList);
    }

    public void onResume() {
        super.onResume();
        AnalyticsHandler.getInstance().trackPageView(getActivity(), "Grouped_Page");
        Log.d("API", "GroupedListFragment onresume");
        if (!this.adapter.getHasBeenInitialized() && !this.contentRefreshListener.isRefreshing()) {
            rebuildContentItemList((List<String>) null);
        }
    }

    public void onPause() {
        super.onPause();
        Log.d("API", "GroupedListFragment onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d("API", "GroupedListFragment onSTop");
    }

    public void onDestroyView() {
        Log.d("API", "groupedListFragment onDestroyView");
        super.onDestroyView();
    }

    public void onDestroy() {
        Log.d("API", "groupedListFragment onDestroy");
        super.onDestroy();
        RowItemBuilder.getInstance().setGroupedCalcRowItemsChangedListener((RowItemBuilder.OnRowItemsChangedListener) null);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver);
    }

    private void rebuildContentItemList(List<String> list) {
        int positionForRowItem;
        Log.d("API", "GroupedListFragment rebuildContentItemList");
        this.adapter.reset();
        List groupedCalcRowItems = RowItemBuilder.getInstance().getGroupedCalcRowItems();
        this.adapter.addSectionWithHeaderItem(new InvisibleHeaderRowItem(), groupedCalcRowItems);
        if (list == null) {
            list = getExpandedIdsInGroupedList(groupedCalcRowItems);
        }
        if (list == null || list.isEmpty()) {
            QxRecyclerViewRowItem rowItemUsersGroup = RowItemBuilder.getInstance().getRowItemUsersGroup();
            if (rowItemUsersGroup != null && (positionForRowItem = this.adapter.getPositionForRowItem(rowItemUsersGroup)) >= 0) {
                this.adapter.expandRowItemAtPosition(positionForRowItem);
            }
        } else {
            this.adapter.expandChildrenContainedInExpandedIds((List<QxRecyclerViewRowItemWrapper>) null, list);
        }
        this.adapter.notifyDataSetChanged();
    }

    public ArrayList<String> getExpandedIdsInGroupedList(List<QxRecyclerViewRowItem> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (list != null) {
            for (QxRecyclerViewRowItem next : list) {
                if (next.isExpanded) {
                    arrayList.add(next.getId());
                }
            }
        }
        return arrayList;
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
        } else if (qxRecyclerViewRowItem instanceof LeafItemRowItem) {
            dBContentItem = ((LeafItemRowItem) qxRecyclerViewRowItem).contentItem;
        } else {
            return;
        }
        if (!SharedPreferenceHelper.getInstance().isNumCategoriesFirEventSent().booleanValue()) {
            sendFirebaseEventForCategoryClicked(FirebaseEventsConstants.EXPANDED_CATEGORY, SharedPreferenceHelper.getInstance().getNumberOfCategoriesOpened());
        }
        SharedPreferenceHelper.getInstance().setNumberOfCategoriesOpened(0);
        Intent intent = new Intent();
        intent.putExtra(CalculatorActivity.KEY_EXTRA_CALC_FROM_SECTION, getString(R.string.feed));
        ContentItemLaunchManager.getInstance().launchContentItem(dBContentItem, getActivity(), calcCallback, intent);
    }

    public void onRecyclerViewRowItemExpanded(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(true, true);
        }
        if (qxRecyclerViewRowItem instanceof CategoryRowItem) {
            DBCategory dBCategory = ((CategoryRowItem) qxRecyclerViewRowItem).category;
            if (!SharedPreferenceHelper.getInstance().isNumCategoriesFirEventSent().booleanValue() && (qxRecyclerViewRowItem instanceof GroupRowItem)) {
                SharedPreferenceHelper.getInstance().setNumberOfCategoriesOpened(SharedPreferenceHelper.getInstance().getNumberOfCategoriesOpened() + 1);
            }
            ArrayList<String> arrayList = new ArrayList<>();
            if (qxRecyclerViewRowItem.children != null) {
                for (QxRecyclerViewRowItem next : qxRecyclerViewRowItem.children) {
                    if (next instanceof LeafItemRowItem) {
                        DBContentItem dBContentItem = ((LeafItemRowItem) next).contentItem;
                        if (dBContentItem.getTrackerId() != null && !dBContentItem.getTrackerId().isEmpty() && !arrayList.contains(dBContentItem.getTrackerId())) {
                            arrayList.add(dBContentItem.getTrackerId());
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                for (String str : arrayList) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(qxRecyclerViewRowItem.getTitle());
                    QxRecyclerViewRowItem qxRecyclerViewRowItem2 = qxRecyclerViewRowItem;
                    while (qxRecyclerViewRowItem2.parentItem != null) {
                        qxRecyclerViewRowItem2 = qxRecyclerViewRowItem2.parentItem;
                        sb.insert(0, qxRecyclerViewRowItem2.getTitle() + " > ");
                    }
                }
            }
        }
    }

    public void onRecyclerViewRowItemCollapsed(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(false, true);
        }
    }

    /* access modifiers changed from: protected */
    public void sendFirebaseEventForCategoryClicked(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseEventsConstants.NUM_CATEGORIES_OPENED_KEY, i);
        QxFirebaseEventManager.getInstance(getActivity()).sendEventName(str, bundle);
        SharedPreferenceHelper.getInstance().setNumCategoriesFirEventSent(true);
    }
}
