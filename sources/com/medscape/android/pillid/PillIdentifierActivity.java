package com.medscape.android.pillid;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.medscape.android.R;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.util.constants.AppboyConstants;

public class PillIdentifierActivity extends NavigableBaseActivity implements OnFilterSelectedListener {
    PillIdentifierMainFragment pillIdentifierMainFragment;

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Deprecated
    public void showUpButton() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_pill_identifier);
        this.pillIdentifierMainFragment = PillIdentifierMainFragment.newInstance();
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add((int) R.id.container, (Fragment) this.pillIdentifierMainFragment).addToBackStack((String) null).commit();
        }
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                if (PillIdentifierActivity.this.getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    PillIdentifierActivity pillIdentifierActivity = PillIdentifierActivity.this;
                    pillIdentifierActivity.setTitle(Html.fromHtml("<font color=#ffffff>" + PillIdentifierActivity.this.getResources().getString(R.string.pill_identifier_filter_main_title) + "</font>"));
                }
            }
        });
        setTitle(Html.fromHtml("<font color=#ffffff>Pill ID</font>"));
        AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_PILLID_VIEWED, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            handleUpAction();
            return true;
        } else if (itemId != R.id.action_clear) {
            return true;
        } else {
            this.pillIdentifierMainFragment.onClearClick(MenuItemCompat.getActionView(menuItem));
            return true;
        }
    }

    public void handleUpAction() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().executePendingTransactions();
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
            if (findFragmentById != null && !findFragmentById.isRemoving()) {
                if (findFragmentById instanceof PillIdentifierMainFragment) {
                    ((PillIdentifierMainFragment) findFragmentById).sendOmnitureMetric();
                } else if (findFragmentById instanceof PillSearchResultsPreviewFragment) {
                    ((PillSearchResultsPreviewFragment) findFragmentById).sendOmniturePing();
                }
            }
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
    }

    public void onClearFilterClick(View view) {
        this.pillIdentifierMainFragment.onClearFilterClick(view);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
    }

    public void onFilterSelected(FilterType filterType, String str, String str2) {
        this.pillIdentifierMainFragment.updateSelectedFilter(filterType, str, str2);
        getSupportFragmentManager().popBackStack();
    }

    public void onBackPressed() {
        handleUpAction();
    }
}
