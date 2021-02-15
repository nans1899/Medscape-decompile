package com.medscape.android.activity;

import android.view.Menu;
import com.medscape.android.R;
import com.medscape.android.base.NavigableBaseActivity;

public abstract class AbstractBreadcrumbNavigableActivity extends NavigableBaseActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
    }
}
