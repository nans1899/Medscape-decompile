package com.medscape.android.homescreen.home_nav_tray.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.activity.calc.CalculatorLandingActivity;
import com.medscape.android.activity.decisionpoint.DecisionPointHubActivity;
import com.medscape.android.activity.directory.DirectorySearchActivity;
import com.medscape.android.activity.formulary.IndexedDrugFormularyListActivity;
import com.medscape.android.activity.interactions.DrugInteractionActivity;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.analytics.FirebaseEventsConstants;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.drugs.IndexedDrugListActivity;
import com.medscape.android.homescreen.edit_navigation.MenuEditActivity;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.pillid.PillIdentifierActivity;
import com.medscape.android.reference.ClinicalReferenceFolderActivity;
import com.medscape.android.util.constants.AppboyConstants;
import com.medscape.android.webmdrx.activity.IndexedRxDrugListActivity;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.omniture.OmnitureTracker;
import com.wbmd.omniture.PageView;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/helpers/NavigationProvider;", "", "()V", "navigateTo", "", "context", "Landroid/app/Activity;", "navItem", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NavigationProvider.kt */
public final class NavigationProvider {
    public final void navigateTo(Activity activity, NavItem navItem) {
        Activity activity2 = activity;
        Intrinsics.checkNotNullParameter(navItem, "navItem");
        switch (navItem.getId()) {
            case 1:
                Context context = activity2;
                OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, AdParameterKeys.DRUG_ID, (Map<String, Object>) null);
                Intent intent = new Intent(context, IndexedDrugListActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent);
                    return;
                }
                return;
            case 2:
                Context context2 = activity2;
                OmnitureManager.get().trackModule(context2, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "dic", (Map<String, Object>) null);
                Intent intent2 = new Intent(context2, DrugInteractionActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent2);
                    return;
                }
                return;
            case 3:
                Context context3 = activity2;
                OmnitureManager.get().trackModule(context3, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "cnd", (Map<String, Object>) null);
                Intent putExtra = new Intent(context3, ClinicalReferenceFolderActivity.class).putExtra("folderId", -1);
                Intrinsics.checkNotNullExpressionValue(putExtra, "Intent(context, Clinical….putExtra(\"folderId\", -1)");
                if (activity2 != null) {
                    activity2.startActivity(putExtra);
                    return;
                }
                return;
            case 4:
                Context context4 = activity2;
                OmnitureManager.get().trackModule(context4, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "prcd", (Map<String, Object>) null);
                Intent putExtra2 = new Intent(context4, ClinicalReferenceFolderActivity.class).putExtra("folderId", navItem.getFolderId());
                Intrinsics.checkNotNullExpressionValue(putExtra2, "Intent(context, Clinical…derId\", navItem.folderId)");
                if (activity2 != null) {
                    activity2.startActivity(putExtra2);
                    return;
                }
                return;
            case 5:
                Context context5 = activity2;
                OmnitureManager.get().trackModule(context5, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, Constants.OMNITURE_MLINK_CALC, (Map<String, Object>) null);
                Intent intent3 = new Intent(context5, CalculatorLandingActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent3);
                    return;
                }
                return;
            case 6:
                Context context6 = activity2;
                OmnitureManager.get().trackModule(context6, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "pill", (Map<String, Object>) null);
                Intent intent4 = new Intent(context6, PillIdentifierActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent4);
                    return;
                }
                return;
            case 7:
                Context context7 = activity2;
                OmnitureManager.get().trackModule(context7, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "cases", (Map<String, Object>) null);
                if (activity2 != null) {
                    new PlatformRouteDispatcher(activity2).routeEvent(FirebaseEventsConstants.CASES_VIEWED);
                }
                if (activity2 != null) {
                    WebviewUtil.Companion.launchShareableWebView(context7, "https://reference.medscape.com/features/cases", "Cases, Quizzes and Trends", "", "case", Constants.OMNITURE_CHANNEL_REFERENCE, "", 2);
                    return;
                }
                return;
            case 8:
                Context context8 = activity2;
                OmnitureManager.get().trackModule(context8, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, ContentParser.GUIDE, (Map<String, Object>) null);
                if (activity2 != null) {
                    new PlatformRouteDispatcher(activity2).routeEvent(AppboyConstants.APPBOY_EVENT_GUIDLINES_VIEWED);
                }
                if (activity2 != null) {
                    WebviewUtil.Companion.launchShareableWebView(context8, "https://reference.medscape.com/features/guidelines", "Latest Clinical Guidelines", "", ContentParser.GUIDE, Constants.OMNITURE_CHANNEL_REFERENCE, "", 2);
                    return;
                }
                return;
            case 9:
                Intent intent5 = new Intent(activity2, MenuEditActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent5);
                    return;
                }
                return;
            case 10:
                Context context9 = activity2;
                OmnitureManager.get().trackModule(context9, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "frmlry", (Map<String, Object>) null);
                Intent intent6 = new Intent(context9, IndexedDrugFormularyListActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent6);
                    return;
                }
                return;
            case 11:
                Context context10 = activity2;
                OmnitureManager.get().trackModule(context10, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "wrx", (Map<String, Object>) null);
                Intent intent7 = new Intent(context10, IndexedRxDrugListActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent7);
                    return;
                }
                return;
            case 12:
                Context context11 = activity2;
                OmnitureManager.get().trackModule(context11, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "drcty", (Map<String, Object>) null);
                Intent intent8 = new Intent(context11, DirectorySearchActivity.class);
                if (activity2 != null) {
                    activity2.startActivity(intent8);
                    return;
                }
                return;
            case 13:
                if (activity2 != null) {
                    OmnitureTracker.sendPageView$default(new OmnitureTracker(), new PageView.PageViewBuilder().page("decision-point/hub").channel(Constants.OMNITURE_CHANNEL_REFERENCE).mlink("dp").mmodule(Constants.OMNITURE_MMODULE_REFMENU).build(), (String) null, 2, (Object) null);
                    if (AppboyEventsHandler.isLastEventCallWithTwentyFourHours(FirebaseEventsConstants.DECISION_POINT_VIEWED)) {
                        new PlatformRouteDispatcher(activity2, false, true).routeEvent(FirebaseEventsConstants.DECISION_POINT_VIEWED, AppboyEventsHandler.addUserDataToViewedEvents());
                    } else {
                        new PlatformRouteDispatcher(activity2).routeEvent(FirebaseEventsConstants.DECISION_POINT_VIEWED);
                    }
                    activity2.startActivity(new Intent(activity2, DecisionPointHubActivity.class));
                    return;
                }
                return;
            default:
                return;
        }
    }
}
