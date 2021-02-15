package com.medscape.android.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.gms.common.Scopes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.activity.cme.views.CMELandingActivity;
import com.medscape.android.activity.events.LiveEventExploreActivity;
import com.medscape.android.activity.help.HelpActivity;
import com.medscape.android.activity.login.LoginManager;
import com.medscape.android.activity.rss.views.NewsLandingActivity;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.consult.activity.ConsultTermsActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.landingfeed.view.BaseLandingFragment;
import com.medscape.android.myinvites.Badger;
import com.medscape.android.myinvites.MyInvitationsManager;
import com.medscape.android.task.GetOpenMyInvitationsCount;
import com.medscape.android.util.BottomNavHelper;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\f\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u0006H\u0002J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0002J\u0006\u0010$\u001a\u00020\u0012J\u0016\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'J\b\u0010)\u001a\u00020\u001eH\u0016J\u0012\u0010*\u001a\u00020\u001e2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u001eH\u0014J\u0012\u0010.\u001a\u00020\u001e2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\b\u00101\u001a\u00020\u001eH\u0016J\b\u00102\u001a\u00020\u001eH\u0016J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u000205H\u0016J\u0012\u00106\u001a\u00020\u001e2\b\u00107\u001a\u0004\u0018\u000108H\u0014J\b\u00109\u001a\u00020\u001eH\u0014J\b\u0010:\u001a\u00020\u001eH\u0014J\b\u0010;\u001a\u00020\u001eH\u0014J\u0006\u0010<\u001a\u00020\u001eJ\u0010\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020?H\u0002J\u0010\u0010B\u001a\u00020\u001e2\u0006\u0010C\u001a\u00020?H\u0002J\b\u0010D\u001a\u00020\u001eH\u0002J\b\u0010E\u001a\u00020\u001eH\u0002J\b\u0010F\u001a\u00020\u001eH\u0002J\b\u0010G\u001a\u00020\u001eH\u0002J\u000e\u0010H\u001a\u00020\u001e2\u0006\u0010I\u001a\u00020\u0006J\u0010\u0010J\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lcom/medscape/android/base/BottomNavBaseActivity;", "Lcom/medscape/android/base/NavigableBaseActivity;", "Lcom/medscape/android/task/GetOpenMyInvitationsCount$GetOpenMyInvitationsCountListener;", "Lcom/google/android/material/navigation/NavigationView$OnNavigationItemSelectedListener;", "()V", "avoidNavItemSelection", "", "baseFragment", "Lcom/medscape/android/landingfeed/view/BaseLandingFragment;", "getBaseFragment", "()Lcom/medscape/android/landingfeed/view/BaseLandingFragment;", "setBaseFragment", "(Lcom/medscape/android/landingfeed/view/BaseLandingFragment;)V", "ignoreFirstTimeClose", "isConsultVisible", "mCapabilitiesReceiver", "Landroid/content/BroadcastReceiver;", "navigationDrawer", "Landroidx/drawerlayout/widget/DrawerLayout;", "navigationView", "Lcom/google/android/material/navigation/NavigationView;", "profileIcon", "Landroid/widget/ImageView;", "getProfileIcon", "()Landroid/widget/ImageView;", "setProfileIcon", "(Landroid/widget/ImageView;)V", "textCount", "Landroid/widget/TextView;", "checkForInvitations", "", "closeNavigationDrawer", "animation", "fillContent", "actualContent", "Landroid/view/View;", "getNavigationDrawer", "markModule", "moduleId", "", "linkId", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onGetOpenMyInvitationsCountComplete", "json", "Lorg/json/JSONObject;", "onGetOpenMyInvitationsCountError", "onGetOpenMyInvitationsCountNoResults", "onNavigationItemSelected", "item", "Landroid/view/MenuItem;", "onNewIntent", "intent", "Landroid/content/Intent;", "onPause", "onResume", "onStop", "setConsultItemVisibility", "setContentView", "layoutResourceId", "", "setDrawerInvitationCount", "count", "setInvitationsCountIcon", "invites", "setOpenInvitationsView", "setSelectedNavItem", "setUpBottomNavigation", "setupDrawerNavigation", "toggleBottomAndDrawer", "showing", "toggleNavigationDrawer", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BottomNavBaseActivity.kt */
public abstract class BottomNavBaseActivity extends NavigableBaseActivity implements GetOpenMyInvitationsCount.GetOpenMyInvitationsCountListener, NavigationView.OnNavigationItemSelectedListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public boolean avoidNavItemSelection;
    private BaseLandingFragment baseFragment;
    /* access modifiers changed from: private */
    public boolean ignoreFirstTimeClose;
    private boolean isConsultVisible;
    private final BroadcastReceiver mCapabilitiesReceiver = new BottomNavBaseActivity$mCapabilitiesReceiver$1(this);
    /* access modifiers changed from: private */
    public DrawerLayout navigationDrawer;
    private NavigationView navigationView;
    private ImageView profileIcon;
    private TextView textCount;

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

    public void onGetOpenMyInvitationsCountError() {
    }

    public static final /* synthetic */ DrawerLayout access$getNavigationDrawer$p(BottomNavBaseActivity bottomNavBaseActivity) {
        DrawerLayout drawerLayout = bottomNavBaseActivity.navigationDrawer;
        if (drawerLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
        }
        return drawerLayout;
    }

    public final BaseLandingFragment getBaseFragment() {
        return this.baseFragment;
    }

    public final void setBaseFragment(BaseLandingFragment baseLandingFragment) {
        this.baseFragment = baseLandingFragment;
    }

    public final ImageView getProfileIcon() {
        return this.profileIcon;
    }

    public final void setProfileIcon(ImageView imageView) {
        this.profileIcon = imageView;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ignoreFirstTimeClose = true;
        checkForInvitations();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mCapabilitiesReceiver, new IntentFilter(Constants.HOMESCREEN_BROADCAST_UPDATE));
    }

    public void setContentView(int i) {
        super.setContentView((int) R.layout.activity_bottom_nav_base);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
        Intrinsics.checkNotNullExpressionValue(bottomNavigationView, "bottom_nav_base");
        bottomNavigationView.getMenu().clear();
        ((BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base)).inflateMenu(R.menu.bottom_navigation_menu);
        setupDrawerNavigation();
        View inflate = View.inflate(this, i, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "View.inflate(this, layoutResourceId, null)");
        fillContent(inflate);
    }

    private final void fillContent(View view) {
        setConsultItemVisibility();
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        ((LinearLayout) _$_findCachedViewById(R.id.bottom_nav_content)).addView(view);
        setUpBottomNavigation();
    }

    private final void setUpBottomNavigation() {
        ((BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base)).setOnNavigationItemSelectedListener(new BottomNavBaseActivity$setUpBottomNavigation$1(this));
    }

    private final void setupDrawerNavigation() {
        View findViewById = findViewById(R.id.navigation_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.navigation_view)");
        NavigationView navigationView2 = (NavigationView) findViewById;
        this.navigationView = navigationView2;
        if (navigationView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationView");
        }
        navigationView2.setNavigationItemSelectedListener(this);
        Context context = this;
        CapabilitiesManager instance = CapabilitiesManager.getInstance(context);
        this.isConsultVisible = instance != null && instance.isConsultFeatureAvailable();
        NavigationView navigationView3 = this.navigationView;
        if (navigationView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationView");
        }
        MenuItem findItem = navigationView3.getMenu().findItem(R.id.nav_drawer_profile);
        Intrinsics.checkNotNullExpressionValue(findItem, "navigationView.menu.find…(R.id.nav_drawer_profile)");
        findItem.setVisible(this.isConsultVisible);
        View findViewById2 = findViewById(R.id.navigation_drawer);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.navigation_drawer)");
        this.navigationDrawer = (DrawerLayout) findViewById2;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && !supportActionBar.isShowing()) {
            TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{16843499});
            Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "theme.obtainStyledAttrib…id.R.attr.actionBarSize))");
            int dimension = (int) obtainStyledAttributes.getDimension(0, 0.0f);
            obtainStyledAttributes.recycle();
            NavigationView navigationView4 = this.navigationView;
            if (navigationView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigationView");
            }
            ViewGroup.LayoutParams layoutParams = navigationView4.getLayoutParams();
            if (layoutParams != null) {
                DrawerLayout.LayoutParams layoutParams2 = (DrawerLayout.LayoutParams) layoutParams;
                layoutParams2.topMargin = dimension;
                NavigationView navigationView5 = this.navigationView;
                if (navigationView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("navigationView");
                }
                navigationView5.setLayoutParams(layoutParams2);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type androidx.drawerlayout.widget.DrawerLayout.LayoutParams");
            }
        }
        NavigationView navigationView6 = this.navigationView;
        if (navigationView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationView");
        }
        if (navigationView6.getHeaderCount() > 0) {
            NavigationView navigationView7 = this.navigationView;
            if (navigationView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigationView");
            }
            View headerView = navigationView7.getHeaderView(0);
            TextView textView = (TextView) headerView.findViewById(R.id.text_name);
            Intrinsics.checkNotNullExpressionValue(textView, "textName");
            textView.setText(Settings.singleton(context).getSetting(Constants.USER_DISPLAYNAME, getString(R.string.nav_drawer_header_doctor)));
            TextView textView2 = (TextView) headerView.findViewById(R.id.text_email);
            Intrinsics.checkNotNullExpressionValue(textView2, "textEmail");
            textView2.setText(Settings.singleton(context).getSetting(Constants.USER_EMAIL, ""));
            ((RequestBuilder) Glide.with((FragmentActivity) this).load(Integer.valueOf(R.drawable.anonymous_person)).circleCrop()).into((ImageView) headerView.findViewById(R.id.image_profile));
        }
        NavigationView navigationView8 = this.navigationView;
        if (navigationView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationView");
        }
        MenuItem findItem2 = navigationView8.getMenu().findItem(R.id.nav_drawer_debug);
        Intrinsics.checkNotNullExpressionValue(findItem2, "navigationView.menu.find…em(R.id.nav_drawer_debug)");
        findItem2.setVisible(false);
        NavigationView navigationView9 = this.navigationView;
        if (navigationView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationView");
        }
        MenuItem findItem3 = navigationView9.getMenu().findItem(R.id.nav_drawer_invitations);
        findItem3.setActionView(R.layout.drawer_item_badge);
        Intrinsics.checkNotNullExpressionValue(findItem3, "menuItem");
        View actionView = findItem3.getActionView();
        if (actionView != null) {
            View findViewById3 = ((ConstraintLayout) actionView).findViewById(R.id.text_count);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(id)");
            this.textCount = (TextView) findViewById3;
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
    }

    public final void markModule(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "moduleId");
        Intrinsics.checkNotNullParameter(str2, "linkId");
        OmnitureManager.get().markModule(str, str2, (Map<String, Object>) null);
    }

    public final void setConsultItemVisibility() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
        Intrinsics.checkNotNullExpressionValue(bottomNavigationView, "bottom_nav_base");
        MenuItem findItem = bottomNavigationView.getMenu().findItem(R.id.bottomnav_consult);
        Intrinsics.checkNotNullExpressionValue(findItem, "bottom_nav_base.menu.fin…m(R.id.bottomnav_consult)");
        CapabilitiesManager instance = CapabilitiesManager.getInstance(this);
        Intrinsics.checkNotNullExpressionValue(instance, "CapabilitiesManager.getInstance(this)");
        boolean isConsultFeatureAvailable = instance.isConsultFeatureAvailable();
        findItem.setVisible(isConsultFeatureAvailable);
        BottomNavHelper.removeShiftMode((BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base));
        NavigationView navigationView2 = this.navigationView;
        if (navigationView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationView");
        }
        MenuItem findItem2 = navigationView2.getMenu().findItem(R.id.nav_drawer_profile);
        Intrinsics.checkNotNullExpressionValue(findItem2, "navigationView.menu.find…(R.id.nav_drawer_profile)");
        findItem2.setVisible(isConsultFeatureAvailable);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setSelectedNavItem();
        Context context = this;
        CMEHelper.INSTANCE.unRegisterSaveReceiver(context);
        Integer openInvitations = MyInvitationsManager.Companion.get(context).getOpenInvitations();
        if (openInvitations != null) {
            setInvitationsCountIcon(openInvitations.intValue());
        }
    }

    private final void setSelectedNavItem() {
        if (this instanceof HomeScreenActivity) {
            BottomNavigationView bottomNavigationView = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView, "bottom_nav_base");
            bottomNavigationView.setSelectedItemId(R.id.bottomnav_home);
        } else if (this instanceof NewsLandingActivity) {
            BottomNavigationView bottomNavigationView2 = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView2, "bottom_nav_base");
            bottomNavigationView2.setSelectedItemId(R.id.bottomnav_news);
        } else if (this instanceof CMELandingActivity) {
            BottomNavigationView bottomNavigationView3 = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView3, "bottom_nav_base");
            bottomNavigationView3.setSelectedItemId(R.id.bottomnav_edu);
        } else if (this instanceof LiveEventExploreActivity) {
            BottomNavigationView bottomNavigationView4 = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView4, "bottom_nav_base");
            bottomNavigationView4.setSelectedItemId(R.id.bottomnav_events);
        } else if ((this instanceof ConsultTimelineActivity) || (this instanceof ConsultTermsActivity)) {
            BottomNavigationView bottomNavigationView5 = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView5, "bottom_nav_base");
            bottomNavigationView5.setSelectedItemId(R.id.bottomnav_consult);
        }
    }

    public void onBackPressed() {
        DrawerLayout drawerLayout = this.navigationDrawer;
        if (drawerLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
        }
        if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            DrawerLayout drawerLayout2 = this.navigationDrawer;
            if (drawerLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
            }
            drawerLayout2.closeDrawer((int) GravityCompat.START);
        } else if (!(this instanceof HomeScreenActivity)) {
            MedscapeMenu.onItemSelected(this, 7);
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mCapabilitiesReceiver);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.avoidNavItemSelection = true;
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!(this instanceof LiveEventExploreActivity)) {
            setIntent(intent);
            this.avoidNavItemSelection = true;
        }
        checkForInvitations();
    }

    private final void setOpenInvitationsView() {
        ImageView imageView = this.profileIcon;
        if (imageView != null) {
            Context context = this;
            Integer openInvitations = MyInvitationsManager.Companion.get(context).getOpenInvitations();
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.invitations_budge_icon);
            if (drawable != null) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                if (openInvitations != null) {
                    Badger.setBadgeCount(context, layerDrawable, openInvitations.intValue());
                }
                imageView.setImageDrawable(layerDrawable);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        }
    }

    private final void checkForInvitations() {
        MyInvitationsManager.Companion.get(this).fetchAndListenOpenInvitations(this);
    }

    public void onGetOpenMyInvitationsCountComplete(JSONObject jSONObject) {
        setInvitationsCountIcon(MyInvitationsManager.Companion.getOpenMyInvitationsCount(jSONObject));
    }

    public void onGetOpenMyInvitationsCountNoResults() {
        setInvitationsCountIcon(0);
    }

    private final void setInvitationsCountIcon(int i) {
        MyInvitationsManager.Companion.get(this).updateOpenInvitations(i);
        setOpenInvitationsView();
        setDrawerInvitationCount(i);
    }

    private final void setDrawerInvitationCount(int i) {
        TextView textView = this.textCount;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textCount");
        }
        textView.setText(String.valueOf(i));
        TextView textView2 = this.textCount;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textCount");
        }
        ViewGroup.LayoutParams layoutParams = textView2.getLayoutParams();
        TextView textView3 = this.textCount;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textCount");
        }
        layoutParams.height = textView3.getWidth();
        TextView textView4 = this.textCount;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textCount");
        }
        textView4.setLayoutParams(layoutParams);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        switch (menuItem.getItemId()) {
            case R.id.nav_drawer_debug:
                MedscapeMenu.onItemSelected(this, 22);
                return true;
            case R.id.nav_drawer_invitations:
                MedscapeMenu.onItemSelected(this, 19);
                return true;
            case R.id.nav_drawer_profile:
                StringBuilder sb = new StringBuilder();
                sb.append("profile/");
                Context context = this;
                sb.append(LoginManager.getStoredGUID(context));
                OmnitureManager.get().trackPageView(context, "consult", "consult", sb.toString(), (String) null, (String) null, (Map<String, Object>) null);
                CapabilitiesManager.getInstance(context).startConsultProfileEluaCheck(context, this, (ProgressBar) _$_findCachedViewById(R.id.help_loading));
                return true;
            case R.id.nav_drawer_saved:
                MedscapeMenu.onItemSelected(this, 11);
                return true;
            case R.id.nav_drawer_settings:
                Context context2 = this;
                OmnitureManager.get().trackPageView(context2, "other", Scopes.PROFILE, "view", (String) null, (String) null, (Map<String, Object>) null);
                startActivity(new Intent(context2, HelpActivity.class));
                return true;
            default:
                return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        closeNavigationDrawer(false);
    }

    public final DrawerLayout getNavigationDrawer() {
        DrawerLayout drawerLayout = this.navigationDrawer;
        if (drawerLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
        }
        return drawerLayout;
    }

    public static /* synthetic */ void toggleNavigationDrawer$default(BottomNavBaseActivity bottomNavBaseActivity, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = true;
            }
            bottomNavBaseActivity.toggleNavigationDrawer(z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toggleNavigationDrawer");
    }

    public final void toggleNavigationDrawer(boolean z) {
        if (this.navigationDrawer != null) {
            DrawerLayout drawerLayout = this.navigationDrawer;
            if (drawerLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
            }
            if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
                DrawerLayout drawerLayout2 = this.navigationDrawer;
                if (drawerLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
                }
                drawerLayout2.closeDrawer((int) GravityCompat.START, z);
                return;
            }
            DrawerLayout drawerLayout3 = this.navigationDrawer;
            if (drawerLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
            }
            drawerLayout3.openDrawer((int) GravityCompat.START, z);
        }
    }

    static /* synthetic */ void closeNavigationDrawer$default(BottomNavBaseActivity bottomNavBaseActivity, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = true;
            }
            bottomNavBaseActivity.closeNavigationDrawer(z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: closeNavigationDrawer");
    }

    private final void closeNavigationDrawer(boolean z) {
        if (this.navigationDrawer != null) {
            DrawerLayout drawerLayout = this.navigationDrawer;
            if (drawerLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
            }
            if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
                DrawerLayout drawerLayout2 = this.navigationDrawer;
                if (drawerLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
                }
                drawerLayout2.closeDrawer((int) GravityCompat.START, z);
            }
        }
    }

    public final void toggleBottomAndDrawer(boolean z) {
        if (z) {
            DrawerLayout drawerLayout = this.navigationDrawer;
            if (drawerLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
            }
            drawerLayout.setDrawerLockMode(0);
            BottomNavigationView bottomNavigationView = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView, "bottom_nav_base");
            bottomNavigationView.setVisibility(0);
            return;
        }
        DrawerLayout drawerLayout2 = this.navigationDrawer;
        if (drawerLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationDrawer");
        }
        drawerLayout2.setDrawerLockMode(1);
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) _$_findCachedViewById(R.id.bottom_nav_base);
        Intrinsics.checkNotNullExpressionValue(bottomNavigationView2, "bottom_nav_base");
        bottomNavigationView2.setVisibility(8);
    }
}
