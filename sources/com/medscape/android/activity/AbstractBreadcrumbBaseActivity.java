package com.medscape.android.activity;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import com.medscape.android.base.NavigableBaseActivity;

public abstract class AbstractBreadcrumbBaseActivity extends NavigableBaseActivity {
    /* access modifiers changed from: protected */
    public void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        this.mToolbar.setNavigationIcon((Drawable) null);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        return true;
    }
}
