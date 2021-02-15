package com.medscape.android.base;

import android.view.MenuItem;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;
import com.medscape.android.activity.cme.views.CMELandingActivity;
import com.medscape.android.activity.events.LiveEventExploreActivity;
import com.medscape.android.activity.rss.views.NewsLandingActivity;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.consult.activity.ConsultTermsActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.landingfeed.view.BaseLandingFragment;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "item", "Landroid/view/MenuItem;", "onNavigationItemSelected"}, k = 3, mv = {1, 4, 0})
/* compiled from: BottomNavBaseActivity.kt */
final class BottomNavBaseActivity$setUpBottomNavigation$1 implements BottomNavigationView.OnNavigationItemSelectedListener {
    final /* synthetic */ BottomNavBaseActivity this$0;

    BottomNavBaseActivity$setUpBottomNavigation$1(BottomNavBaseActivity bottomNavBaseActivity) {
        this.this$0 = bottomNavBaseActivity;
    }

    public final boolean onNavigationItemSelected(MenuItem menuItem) {
        RecyclerView feedView;
        RecyclerView feedView2;
        RecyclerView feedView3;
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (!this.this$0.avoidNavItemSelection) {
            switch (menuItem.getItemId()) {
                case R.id.bottomnav_consult:
                    BottomNavBaseActivity bottomNavBaseActivity = this.this$0;
                    if (!(bottomNavBaseActivity instanceof ConsultTimelineActivity) && !(bottomNavBaseActivity instanceof ConsultTermsActivity)) {
                        CapabilitiesManager.getInstance(bottomNavBaseActivity).startConsultActivity(this.this$0, true);
                        break;
                    } else {
                        BottomNavBaseActivity bottomNavBaseActivity2 = this.this$0;
                        if (bottomNavBaseActivity2 instanceof ConsultTimelineActivity) {
                            ((ConsultTimelineActivity) bottomNavBaseActivity2).scrollToTop();
                            break;
                        }
                    }
                    break;
                case R.id.bottomnav_edu:
                    BottomNavBaseActivity bottomNavBaseActivity3 = this.this$0;
                    if (bottomNavBaseActivity3 instanceof CMELandingActivity) {
                        BaseLandingFragment baseFragment = bottomNavBaseActivity3.getBaseFragment();
                        if (!(baseFragment == null || (feedView = baseFragment.getFeedView()) == null)) {
                            feedView.scrollToPosition(0);
                            break;
                        }
                    } else {
                        bottomNavBaseActivity3.markModule("educationnav", "tap");
                        MedscapeMenu.onItemSelected(this.this$0, 4);
                        break;
                    }
                case R.id.bottomnav_events:
                    BottomNavBaseActivity bottomNavBaseActivity4 = this.this$0;
                    if (!(bottomNavBaseActivity4 instanceof LiveEventExploreActivity)) {
                        bottomNavBaseActivity4.markModule("eventsnav", "tap");
                        MedscapeMenu.onItemSelected(this.this$0, 25);
                        break;
                    }
                    break;
                case R.id.bottomnav_home:
                    BottomNavBaseActivity bottomNavBaseActivity5 = this.this$0;
                    if (bottomNavBaseActivity5 instanceof HomeScreenActivity) {
                        if (!bottomNavBaseActivity5.ignoreFirstTimeClose) {
                            ((AppBarLayout) this.this$0._$_findCachedViewById(R.id.app_bar_layout)).setExpanded(true, false);
                        }
                        this.this$0.ignoreFirstTimeClose = false;
                        BaseLandingFragment baseFragment2 = this.this$0.getBaseFragment();
                        if (!(baseFragment2 == null || (feedView2 = baseFragment2.getFeedView()) == null)) {
                            feedView2.scrollToPosition(0);
                            break;
                        }
                    } else {
                        bottomNavBaseActivity5.markModule("homenav", "tap");
                        MedscapeMenu.onItemSelected(this.this$0, 7);
                        break;
                    }
                case R.id.bottomnav_news:
                    BottomNavBaseActivity bottomNavBaseActivity6 = this.this$0;
                    if (bottomNavBaseActivity6 instanceof NewsLandingActivity) {
                        BaseLandingFragment baseFragment3 = bottomNavBaseActivity6.getBaseFragment();
                        if (!(baseFragment3 == null || (feedView3 = baseFragment3.getFeedView()) == null)) {
                            feedView3.scrollToPosition(0);
                            break;
                        }
                    } else {
                        bottomNavBaseActivity6.markModule("newsnav", "tap");
                        MedscapeMenu.onItemSelected(this.this$0, 3);
                        break;
                    }
            }
        } else {
            this.this$0.avoidNavItemSelection = false;
        }
        return true;
    }
}
