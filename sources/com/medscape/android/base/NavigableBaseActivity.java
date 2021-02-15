package com.medscape.android.base;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;

public class NavigableBaseActivity extends BaseActivity {
    protected LinearLayout mContentContainer;
    protected Toolbar mToolbar;

    public void setContentView(int i) {
        super.setContentView((int) R.layout.left_nav_drawer);
        populateContentView(View.inflate(this, i, (ViewGroup) null));
    }

    public void setContentView(View view) {
        super.setContentView((int) R.layout.left_nav_drawer);
        populateContentView(view);
    }

    public void populateContentView(View view) {
        this.mContentContainer = (LinearLayout) findViewById(R.id.content_container);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.mContentContainer.addView(view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        setupActionBar();
    }

    public Toolbar getActionBarToolBar() {
        return this.mToolbar;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
            return true;
        } else if (itemId == R.id.action_save) {
            MedscapeMenu.onItemSelected(this, 11);
            return true;
        } else if (itemId != R.id.action_search) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            return true;
        }
    }
}
