package com.medscape.android.activity.calc.fragments;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.medscape.android.R;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.model.db.DBCategory;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.rowItems.FeaturedContentRowItem;
import com.wbmd.qxcalculator.model.rowItems.GroupRowItem;
import com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem;
import com.wbmd.qxcalculator.util.FirebaseEventsConstants;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import com.wbmd.qxcalculator.util.SharedPreferenceHelper;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\t\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rJ.\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/activity/calc/fragments/GroupedListFragment;", "Lcom/wbmd/qxcalculator/fragments/homescreen/GroupedListFragment;", "()V", "getCategory", "", "rowItem", "Lcom/qxmd/qxrecyclerview/QxRecyclerViewRowItem;", "item", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "newInstance", "callback", "Lcom/wbmd/qxcalculator/LaunchQxCallback;", "isShowFeaturedContentRowItem", "", "onRecyclerViewRowItemClicked", "adapter", "Lcom/qxmd/qxrecyclerview/QxRecyclerViewAdapter;", "view", "Landroid/view/View;", "position", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: GroupedListFragment.kt */
public final class GroupedListFragment extends com.wbmd.qxcalculator.fragments.homescreen.GroupedListFragment {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final GroupedListFragment newInstance(LaunchQxCallback launchQxCallback, boolean z) {
        com.wbmd.qxcalculator.fragments.homescreen.GroupedListFragment.calcCallback = launchQxCallback;
        RowItemBuilder.getInstance().setShowFeaturedContentRowItem(z);
        return new GroupedListFragment();
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
            Intrinsics.checkNotNullExpressionValue(dBContentItem, "contentItem");
            getCategory(qxRecyclerViewRowItem, dBContentItem);
        } else {
            return;
        }
        SharedPreferenceHelper instance = SharedPreferenceHelper.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "SharedPreferenceHelper.getInstance()");
        if (!instance.isNumCategoriesFirEventSent().booleanValue()) {
            String str = FirebaseEventsConstants.EXPANDED_CATEGORY;
            SharedPreferenceHelper instance2 = SharedPreferenceHelper.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance2, "SharedPreferenceHelper.getInstance()");
            sendFirebaseEventForCategoryClicked(str, instance2.getNumberOfCategoriesOpened());
        }
        SharedPreferenceHelper instance3 = SharedPreferenceHelper.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance3, "SharedPreferenceHelper.getInstance()");
        instance3.setNumberOfCategoriesOpened(0);
        Intent intent = new Intent();
        intent.putExtra(CalculatorActivity.KEY_EXTRA_CALC_FROM_SECTION, getString(R.string.feed));
        ContentItemLaunchManager.getInstance().launchContentItem(dBContentItem, getActivity(), com.wbmd.qxcalculator.fragments.homescreen.GroupedListFragment.calcCallback, intent);
    }

    private final void getCategory(QxRecyclerViewRowItem qxRecyclerViewRowItem, DBContentItem dBContentItem) {
        QxRecyclerViewRowItem qxRecyclerViewRowItem2 = qxRecyclerViewRowItem.parentItem;
        if (qxRecyclerViewRowItem2 instanceof GroupRowItem) {
            DBCategory dBCategory = ((GroupRowItem) qxRecyclerViewRowItem2).category;
            Intrinsics.checkNotNullExpressionValue(dBCategory, "parent.category");
            String name = dBCategory.getName();
            Intrinsics.checkNotNullExpressionValue(name, "parent.category.name");
            dBContentItem.setParentCalcName(name);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(qxRecyclerViewRowItem2, "parent");
        getCategory(qxRecyclerViewRowItem2, dBContentItem);
    }
}
