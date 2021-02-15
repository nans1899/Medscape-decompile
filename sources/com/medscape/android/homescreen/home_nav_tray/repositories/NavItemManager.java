package com.medscape.android.homescreen.home_nav_tray.repositories;

import android.content.Context;
import com.ib.foreceup.model.UserConditions;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigConditionModel;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigCriteriaModel;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigManager;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigModel;
import com.medscape.android.forceup.ForceUpManager;
import com.medscape.android.homescreen.home_nav_tray.helpers.MenuOrderValidator;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.util.NavConstants;
import com.medscape.android.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u0005J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ,\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#2\u0006\u0010&\u001a\u00020$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eR6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006'"}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/repositories/NavItemManager;", "", "()V", "homeMenuItems", "Ljava/util/LinkedHashMap;", "", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "Lkotlin/collections/LinkedHashMap;", "getHomeMenuItems", "()Ljava/util/LinkedHashMap;", "setHomeMenuItems", "(Ljava/util/LinkedHashMap;)V", "moreItem", "orderValidator", "Lcom/medscape/android/homescreen/home_nav_tray/helpers/MenuOrderValidator;", "getOrderValidator", "()Lcom/medscape/android/homescreen/home_nav_tray/helpers/MenuOrderValidator;", "setOrderValidator", "(Lcom/medscape/android/homescreen/home_nav_tray/helpers/MenuOrderValidator;)V", "createNewOrder", "", "customOrder", "getDefaultOrder", "getItemById", "id", "getItems", "getItemsForPage", "", "order", "context", "Landroid/content/Context;", "getOrderedItems", "shouldEnableFeature", "", "configList", "Ljava/util/HashMap;", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigModel;", "type", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NavItemManager.kt */
public final class NavItemManager {
    private LinkedHashMap<Integer, NavItem> homeMenuItems = MapsKt.linkedMapOf(TuplesKt.to(1, new NavItem(1, "Drugs", R.drawable.ic_nav_drug, -1, false, NavConstants.NAV_DRUG_TYPE)), TuplesKt.to(2, new NavItem(2, "Interaction Checker", R.drawable.ic_nav_interaction, -1, false, NavConstants.NAV_INTERACTION_TYPE)), TuplesKt.to(3, new NavItem(3, "Conditions", R.drawable.ic_nav_conditions, -1, false, NavConstants.NAV_CONDITION_TYPE)), TuplesKt.to(4, new NavItem(4, "Procedures", R.drawable.ic_nav_procedures, -1, false, NavConstants.NAV_PROCEDURE_TYPE)), TuplesKt.to(13, new NavItem(13, "Decision Point", R.drawable.ic_nav_decision_point, -1, false, NavConstants.NAV_DECISION_POINT_TYPE)), TuplesKt.to(5, new NavItem(5, "Calculators", R.drawable.ic_nav_calculators, -1, false, NavConstants.NAV_CALC_TYPE)), TuplesKt.to(6, new NavItem(6, "Pill Identifier", R.drawable.ic_nav_pill_identifier, -1, false, NavConstants.NAV_PILLID_TYPE)), TuplesKt.to(7, new NavItem(7, "Cases & Quizzes", R.drawable.ic_nav_case_and_quiz, -1, false, NavConstants.NAV_CASES_TYPE)), TuplesKt.to(8, new NavItem(8, "Latest Guidelines", R.drawable.ic_nav_guidelines, -1, false, NavConstants.NAV_GUIDE_TYPE)), TuplesKt.to(10, new NavItem(10, "Formulary", R.drawable.ic_nav_formulary, -1, false, NavConstants.NAV_FORM_TYPE)));
    private final NavItem moreItem = new NavItem(9, "More", R.drawable.ic_nav_more, 0, false, (String) null, 56, (DefaultConstructorMarker) null);
    private MenuOrderValidator orderValidator = new MenuOrderValidator();

    public NavItemManager() {
        if (Util.isUSuser(MedscapeApplication.get())) {
            this.homeMenuItems.put(11, new NavItem(11, "Pricing & Saving", R.drawable.ic_nav_pricing, -1, false, NavConstants.NAV_PRICING_TYPE));
        }
        this.homeMenuItems.put(12, new NavItem(12, "Directory", R.drawable.ic_nav_directory, -1, false, NavConstants.NAV_DIRECTORY_TYPE));
    }

    public final LinkedHashMap<Integer, NavItem> getHomeMenuItems() {
        return this.homeMenuItems;
    }

    public final void setHomeMenuItems(LinkedHashMap<Integer, NavItem> linkedHashMap) {
        Intrinsics.checkNotNullParameter(linkedHashMap, "<set-?>");
        this.homeMenuItems = linkedHashMap;
    }

    public final MenuOrderValidator getOrderValidator() {
        return this.orderValidator;
    }

    public final void setOrderValidator(MenuOrderValidator menuOrderValidator) {
        Intrinsics.checkNotNullParameter(menuOrderValidator, "<set-?>");
        this.orderValidator = menuOrderValidator;
    }

    public final List<NavItem> getItemsForPage(List<Integer> list, Context context) {
        Intrinsics.checkNotNullParameter(list, "order");
        List<NavItem> subList = getOrderedItems(list, context).subList(0, 7);
        subList.add(this.moreItem);
        return subList;
    }

    public final List<NavItem> getItems() {
        Collection<NavItem> values = this.homeMenuItems.values();
        Intrinsics.checkNotNullExpressionValue(values, "homeMenuItems.values");
        return CollectionsKt.toMutableList(values);
    }

    public final List<NavItem> getOrderedItems(List<Integer> list, Context context) {
        Intrinsics.checkNotNullParameter(list, "customOrder");
        List<NavItem> arrayList = new ArrayList<>();
        MenuOrderValidator menuOrderValidator = this.orderValidator;
        Set<Integer> keySet = this.homeMenuItems.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "homeMenuItems.keys");
        if (!menuOrderValidator.validate(list, keySet)) {
            list = createNewOrder(list);
        }
        for (Integer intValue : list) {
            NavItem itemById = getItemById(intValue.intValue());
            if (itemById != null && shouldEnableFeature(FeatureConfigManager.Companion.getReferenceConfig(), itemById.getType(), context)) {
                arrayList.add(itemById);
            }
        }
        return arrayList;
    }

    public final NavItem getItemById(int i) {
        return this.homeMenuItems.get(Integer.valueOf(i));
    }

    public final List<Integer> getDefaultOrder() {
        List<Integer> arrayList = new ArrayList<>();
        for (Map.Entry entry : this.homeMenuItems.entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            NavItem navItem = (NavItem) entry.getValue();
            arrayList.add(Integer.valueOf(intValue));
        }
        return arrayList;
    }

    public final List<Integer> createNewOrder(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "customOrder");
        List<Integer> defaultOrder = getDefaultOrder();
        List<Integer> mutableList = CollectionsKt.toMutableList(list);
        int i = 0;
        for (Number intValue : defaultOrder) {
            int intValue2 = intValue.intValue();
            if (!list.contains(Integer.valueOf(intValue2))) {
                mutableList.add(i, Integer.valueOf(intValue2));
            }
            i++;
        }
        return mutableList;
    }

    public final boolean shouldEnableFeature(HashMap<String, FeatureConfigModel> hashMap, String str, Context context) {
        boolean z;
        Intrinsics.checkNotNullParameter(hashMap, "configList");
        Intrinsics.checkNotNullParameter(str, "type");
        if (context == null) {
            return true;
        }
        ArrayList<UserConditions.UserSentCriteria> prepareUserForceupCriteria = new ForceUpManager().prepareUserForceupCriteria(context);
        FeatureConfigModel featureConfigModel = hashMap.get(str);
        if (featureConfigModel == null) {
            return true;
        }
        Intrinsics.checkNotNullExpressionValue(featureConfigModel, "configList[type] ?: return true");
        boolean z2 = true;
        for (FeatureConfigCriteriaModel featureConfigCriteriaModel : featureConfigModel.getCriteria()) {
            if (prepareUserForceupCriteria != null) {
                for (UserConditions.UserSentCriteria userSentCriteria : prepareUserForceupCriteria) {
                    if (Intrinsics.areEqual((Object) featureConfigCriteriaModel.getTarget(), (Object) userSentCriteria.getKey())) {
                        for (FeatureConfigConditionModel featureConfigConditionModel : featureConfigCriteriaModel.getConditions()) {
                            if (userSentCriteria.getCriteria().containsKey(featureConfigConditionModel.getKey())) {
                                if (!featureConfigConditionModel.getInverse()) {
                                    z = CollectionsKt.contains(featureConfigConditionModel.getValue(), userSentCriteria.getCriteria().get(featureConfigConditionModel.getKey()));
                                } else if (CollectionsKt.contains(featureConfigConditionModel.getValue(), userSentCriteria.getCriteria().get(featureConfigConditionModel.getKey()))) {
                                    z = false;
                                }
                                z2 = !z2 && z;
                            }
                            z = true;
                            if (!z2) {
                            }
                        }
                    }
                }
            }
        }
        return z2;
    }
}
