package com.webmd.webmdrx.activities.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.google.android.material.tabs.TabLayout;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.RxBaseActivity;
import com.webmd.webmdrx.fragments.ShareSavingsFragment;
import com.webmd.webmdrx.util.Util;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0005H\u0016J\b\u0010$\u001a\u00020\u001cH\u0003J\u0006\u0010%\u001a\u00020\u001cR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006&"}, d2 = {"Lcom/webmd/webmdrx/activities/share/ShareSavingsActivity;", "Lcom/webmd/webmdrx/activities/RxBaseActivity;", "Lcom/webmd/webmdrx/fragments/ShareSavingsFragment$ScreenClickablity;", "()V", "isScreenNotClickable", "", "isScreenNotClickable$wbmdrx_release", "()Z", "setScreenNotClickable$wbmdrx_release", "(Z)V", "mDrugContentID", "", "mShareOptionsTab", "Lcom/google/android/material/tabs/TabLayout;", "getMShareOptionsTab$wbmdrx_release", "()Lcom/google/android/material/tabs/TabLayout;", "setMShareOptionsTab$wbmdrx_release", "(Lcom/google/android/material/tabs/TabLayout;)V", "mShareSavingsFragment", "Lcom/webmd/webmdrx/fragments/ShareSavingsFragment;", "getMShareSavingsFragment$wbmdrx_release", "()Lcom/webmd/webmdrx/fragments/ShareSavingsFragment;", "setMShareSavingsFragment$wbmdrx_release", "(Lcom/webmd/webmdrx/fragments/ShareSavingsFragment;)V", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setScreenClickability", "isClickable", "setUpActionBar", "setUpTabs", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareSavingsActivity.kt */
public final class ShareSavingsActivity extends RxBaseActivity implements ShareSavingsFragment.ScreenClickablity {
    private HashMap _$_findViewCache;
    private boolean isScreenNotClickable;
    /* access modifiers changed from: private */
    public String mDrugContentID = "";
    private TabLayout mShareOptionsTab;
    private ShareSavingsFragment mShareSavingsFragment;

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

    public final TabLayout getMShareOptionsTab$wbmdrx_release() {
        return this.mShareOptionsTab;
    }

    public final void setMShareOptionsTab$wbmdrx_release(TabLayout tabLayout) {
        this.mShareOptionsTab = tabLayout;
    }

    public final boolean isScreenNotClickable$wbmdrx_release() {
        return this.isScreenNotClickable;
    }

    public final void setScreenNotClickable$wbmdrx_release(boolean z) {
        this.isScreenNotClickable = z;
    }

    public final ShareSavingsFragment getMShareSavingsFragment$wbmdrx_release() {
        return this.mShareSavingsFragment;
    }

    public final void setMShareSavingsFragment$wbmdrx_release(ShareSavingsFragment shareSavingsFragment) {
        this.mShareSavingsFragment = shareSavingsFragment;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_savings);
        setUpActionBar();
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        intent.getExtras();
        setUpTabs();
        this.isScreenNotClickable = false;
        Util.updateRxGroupDate(this);
    }

    private final void setUpActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.a_pricing_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Context context = this;
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, R.drawable.abc_ic_ab_back_material);
        if (drawable != null) {
            drawable.setColorFilter(ContextCompat.getColor(context, R.color.toolbar_icon_gray), PorterDuff.Mode.SRC_ATOP);
            ActionBar supportActionBar2 = getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setHomeAsUpIndicator(drawable);
            }
        }
    }

    public final void setUpTabs() {
        ShareSavingsFragment shareSavingsFragment;
        View findViewById = findViewById(R.id.share_tabs);
        if (findViewById != null) {
            TabLayout tabLayout = (TabLayout) findViewById;
            this.mShareOptionsTab = tabLayout;
            if (tabLayout != null) {
                tabLayout.addTab(tabLayout.newTab().setTag(0).setText((CharSequence) getString(R.string.share_tab_email)));
                tabLayout.addTab(tabLayout.newTab().setTag(1).setText((CharSequence) getString(R.string.share_tab_text)));
            }
            TabLayout tabLayout2 = this.mShareOptionsTab;
            if (tabLayout2 != null) {
                tabLayout2.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new ShareSavingsActivity$setUpTabs$2(this));
            }
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String str = this.mDrugContentID;
                Intrinsics.checkNotNullExpressionValue(extras, "it");
                shareSavingsFragment = ShareSavingsFragment.Companion.newInstance(true, this, str, extras);
            } else {
                shareSavingsFragment = null;
            }
            this.mShareSavingsFragment = shareSavingsFragment;
            if (shareSavingsFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.share_tab_container, shareSavingsFragment).commit();
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.google.android.material.tabs.TabLayout");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        if (this.isScreenNotClickable) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setScreenClickability(boolean z) {
        this.isScreenNotClickable = !z;
    }
}
