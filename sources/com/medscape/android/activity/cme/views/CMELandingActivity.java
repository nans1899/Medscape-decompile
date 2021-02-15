package com.medscape.android.activity.cme.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import com.medscape.android.R;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.base.BottomNavBaseActivity;
import com.medscape.android.landingfeed.view.BaseLandingFragment;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0014J\b\u0010\u000f\u001a\u00020\u0004H\u0014¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/activity/cme/views/CMELandingActivity;", "Lcom/medscape/android/base/BottomNavBaseActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "setupActionBar", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CMELandingActivity.kt */
public final class CMELandingActivity extends BottomNavBaseActivity {
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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rss);
        setBaseFragment(CMELandingFragment.Companion.newInstance());
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        BaseLandingFragment baseFragment = getBaseFragment();
        if (baseFragment != null) {
            beginTransaction.replace(R.id.rss_container, (CMELandingFragment) baseFragment);
            beginTransaction.commit();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.cme.views.CMELandingFragment");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        BaseLandingFragment baseFragment = getBaseFragment();
        if (baseFragment != null) {
            baseFragment.oneTimeLoadFeed(this);
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        if (getSupportActionBar() != null) {
            ActionBar supportActionBar = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            ActionBar supportActionBar2 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar2);
            Intrinsics.checkNotNullExpressionValue(supportActionBar2, "supportActionBar!!");
            supportActionBar2.setTitle((CharSequence) "");
            ActionBar supportActionBar3 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar3);
            Context context = this;
            supportActionBar3.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.medscape_blue)));
            ActionBar supportActionBar4 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar4);
            supportActionBar4.setDisplayHomeAsUpEnabled(false);
            View inflate = LayoutInflater.from(context).inflate(R.layout.header_action_bar, (ConstraintLayout) _$_findCachedViewById(R.id.root));
            TextView textView = (TextView) inflate.findViewById(R.id.header_title);
            setProfileIcon((ImageView) inflate.findViewById(R.id.profile_icon));
            Intrinsics.checkNotNullExpressionValue(textView, "title");
            textView.setText(getString(R.string.cme_header_title));
            ImageView profileIcon = getProfileIcon();
            if (profileIcon != null) {
                profileIcon.setOnClickListener(new CMELandingActivity$setupActionBar$1(this));
            }
            ActionBar supportActionBar5 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar5);
            Intrinsics.checkNotNullExpressionValue(supportActionBar5, "supportActionBar!!");
            supportActionBar5.setCustomView(inflate);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, Category.K_MENU_CATEGORY);
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.cme_list, menu);
        MenuItem findItem = menu.findItem(R.id.action_search);
        if (findItem == null) {
            return true;
        }
        findItem.setOnMenuItemClickListener(new CMELandingActivity$onCreateOptionsMenu$1(this));
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (getNavigationDrawer().isDrawerOpen((int) GravityCompat.START)) {
            BottomNavBaseActivity.toggleNavigationDrawer$default(this, false, 1, (Object) null);
            return true;
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_cme_tracker) {
            CMEHelper.launchCMETracker$default(this, false, 2, (Object) null);
            return true;
        } else if (itemId != R.id.action_search) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            return false;
        }
    }
}
