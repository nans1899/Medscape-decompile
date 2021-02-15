package com.medscape.android.activity.events;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.navigation.ActivityKt;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import com.facebook.internal.NativeProtocol;
import com.medscape.android.Constants;
import com.medscape.android.EnvironmentConfig;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.base.BottomNavBaseActivity;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.omniture.Action;
import com.wbmd.omniture.OmnitureTracker;
import com.wbmd.omniture.PageView;
import com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer;
import com.webmd.medscape.live.explorelivevents.common.ExploreIconsContainer;
import com.webmd.medscape.live.explorelivevents.common.IOmnitureListener;
import com.webmd.medscape.live.explorelivevents.common.OmnitureInfo;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.ui.fragments.ExploreFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0003J\b\u0010\f\u001a\u00020\u000bH\u0016J\u0012\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0015J\b\u0010\u0010\u001a\u00020\u000bH\u0002J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/medscape/android/activity/events/LiveEventExploreActivity;", "Lcom/medscape/android/base/BottomNavBaseActivity;", "Lcom/webmd/medscape/live/explorelivevents/common/IOmnitureListener;", "()V", "navController", "Landroidx/navigation/NavController;", "generateHeaderView", "Landroid/view/View;", "getStyle", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "initNavController", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "passCookieInformation", "sendOmnitureActionCall", "params", "Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo;", "sendOmniturePageView", "setupActionBar", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventExploreActivity.kt */
public final class LiveEventExploreActivity extends BottomNavBaseActivity implements IOmnitureListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public NavController navController;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public static final /* synthetic */ NavController access$getNavController$p(LiveEventExploreActivity liveEventExploreActivity) {
        NavController navController2 = liveEventExploreActivity.navController;
        if (navController2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navController");
        }
        return navController2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        passCookieInformation();
        setContentView(R.layout.activity_live_events_explore);
        initNavController();
    }

    private final void passCookieInformation() {
        Context context = this;
        SharedPreferencesManager.Companion.getInstance(context).saveString("KEY_PREFS_COOKIE_STRING", Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, ""));
    }

    public void onBackPressed() {
        if (getNavigationDrawer().isDrawerOpen((int) GravityCompat.START)) {
            getNavigationDrawer().closeDrawer((int) GravityCompat.START);
        } else if (this.navController != null) {
            NavController navController2 = this.navController;
            if (navController2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navController");
            }
            NavDestination currentDestination = navController2.getCurrentDestination();
            if (currentDestination == null || currentDestination.getId() != R.id.navigation_explore) {
                NavController navController3 = this.navController;
                if (navController3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("navController");
                }
                navController3.navigateUp();
                return;
            }
            finish();
        } else {
            finish();
        }
    }

    private final void initNavController() {
        this.navController = ActivityKt.findNavController(this, R.id.nav_host_fragment);
        if (this.navController != null) {
            String baseUrl = new EnvironmentManager().getBaseUrl(this, EnvironmentConfig.MODULE_LIVE_EVENTS);
            if (baseUrl == null) {
                baseUrl = "https://www.medscapelive.com/api/";
            }
            NavController navController2 = this.navController;
            if (navController2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navController");
            }
            navController2.setGraph((int) R.navigation.nav_graph_explore, ExploreFragment.Companion.getArgs(baseUrl, getStyle()));
            NavController navController3 = this.navController;
            if (navController3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navController");
            }
            navController3.addOnDestinationChangedListener(new LiveEventExploreActivity$initNavController$2(this));
        }
    }

    private final StyleManager getStyle() {
        StyleManager styleManager = new StyleManager(false, 1, (DefaultConstructorMarker) null);
        ExploreColorsContainer colors = styleManager.getColors();
        colors.setNavHeaderBackgroundColor(R.color.medscape_blue);
        colors.setNavTitleColor(R.color.white);
        colors.setStatusBarColor(R.color.medscapedarkblue);
        colors.setEventDateTextColor(R.color.medscape_blue);
        colors.setEventAccreditationTextColor(R.color.medscape_blue);
        colors.setShowAllButtonTextColor(R.color.medscape_blue);
        colors.setQuickFiltersHeadingTextColor(R.color.live_events_quick_filter_heading_text);
        colors.setQuickFiltersItemTextColor(R.color.live_events_quick_filter_item_text);
        colors.setQuickFiltersDividerColor(R.color.live_events_quick_filter_divider);
        colors.setBigTitleTextColor(R.color.live_events_quick_filter_item_text);
        colors.setBigHeadingTextColor(R.color.white);
        colors.setEventTitleTextColor(R.color.title_color);
        colors.setEventIconTintColor(R.color.medscape_blue);
        colors.setEventLocationTextColor(R.color.live_events_quick_filter_heading_text);
        colors.setFilterCheckBoxSelectedColor(R.color.medscape_blue);
        colors.setFilterItemTextColor(R.color.black);
        ExploreIconsContainer icons = styleManager.getIcons();
        icons.setNavIcon(R.drawable.ic_back_white);
        icons.setTickIcon(R.drawable.ic_check_white_24dp);
        styleManager.setCustomView(generateHeaderView());
        return styleManager;
    }

    private final View generateHeaderView() {
        View inflate = getLayoutInflater().inflate(R.layout.header_action_bar, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.header_title);
        Intrinsics.checkNotNullExpressionValue(textView, "title");
        textView.setText(getString(R.string.explore_header_title));
        setProfileIcon((ImageView) inflate.findViewById(R.id.profile_icon));
        ImageView profileIcon = getProfileIcon();
        if (profileIcon != null) {
            profileIcon.setOnClickListener(new LiveEventExploreActivity$generateHeaderView$1(this));
        }
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    public void sendOmniturePageView(OmnitureInfo omnitureInfo) {
        Intrinsics.checkNotNullParameter(omnitureInfo, NativeProtocol.WEB_DIALOG_PARAMS);
        OmnitureTracker.sendPageView$default(new OmnitureTracker(), new PageView.PageViewBuilder().page(omnitureInfo.getPageNames()).channel(omnitureInfo.getChannel()).userSeg(omnitureInfo.getUserSeg()).exitUrl(omnitureInfo.getExitUrl()).site("app-msp").build(), (String) null, 2, (Object) null);
    }

    public void sendOmnitureActionCall(OmnitureInfo omnitureInfo) {
        Intrinsics.checkNotNullParameter(omnitureInfo, NativeProtocol.WEB_DIALOG_PARAMS);
        Action.ActionBuilder actionBuilder = new Action.ActionBuilder();
        actionBuilder.mmodule(omnitureInfo.getMModule());
        actionBuilder.mlink(omnitureInfo.getMLink());
        actionBuilder.channel(omnitureInfo.getChannel());
        actionBuilder.userSeg(omnitureInfo.getUserSeg());
        actionBuilder.exitUrl(omnitureInfo.getExitUrl());
        new OmnitureTracker().sendAction(actionBuilder.build());
    }
}
