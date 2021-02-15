package com.wbmd.qxcalculator.activities.homescreen;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.fragments.common.QxMDFragment;
import com.wbmd.qxcalculator.fragments.homescreen.SearchFragment;

public class SearchActivity extends QxMDActivity implements SearchView.OnQueryTextListener {
    public static final String EXTRA_FRAGMENT_TYPE = "EXTRA_FRAGMENT_TYPE";
    private static final String KEY_SEARCH_QUERY = "KEY_SEARCH_QUERY";
    /* access modifiers changed from: private */
    public SearchView mSearchView;
    public String searchString;

    public int getStatusBarColor() {
        return getResources().getColor(R.color.status_bar_color_default);
    }

    public int getActionBarColor() {
        return getResources().getColor(R.color.action_bar_color_default);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_search_calc;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("API", "SearchActivity onCreate");
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (bundle != null) {
            this.searchString = bundle.getString(KEY_SEARCH_QUERY);
        } else if (getIntent() == null) {
            finish();
        } else {
            addFragmentToContainer(SearchFragment.newInstance(), R.id.fragment_container);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(KEY_SEARCH_QUERY, this.searchString);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_searchable, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(getResources().getColor(R.color.toolbar_tint), PorterDuff.Mode.SRC_IN);
        MenuItem findItem = menu.findItem(R.id.action_search);
        findItem.getIcon().setColorFilter(porterDuffColorFilter);
        SearchView searchView = (SearchView) findItem.getActionView();
        searchView.setOnQueryTextListener(this);
        findItem.expandActionView();
        findItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                SearchActivity.this.finish();
                return true;
            }
        });
        searchView.setQueryHint(getString(R.string.search_hint));
        ((ImageView) searchView.findViewById(R.id.search_close_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SearchActivity.this.mSearchView.setQuery("", true);
            }
        });
        this.mSearchView = searchView;
        String str = this.searchString;
        if (str == null || str.isEmpty()) {
            return true;
        }
        this.mSearchView.setQuery(this.searchString, false);
        this.mSearchView.clearFocus();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.close_enter_modal, R.anim.close_exit_modal);
    }

    public boolean onQueryTextSubmit(String str) {
        this.mSearchView.clearFocus();
        Fragment defaultFragment = getDefaultFragment();
        if (defaultFragment == null || !(defaultFragment instanceof SearchFragment) || SearchFragment.callback == null) {
            return true;
        }
        SearchFragment.callback.onClickSearchButton();
        return true;
    }

    public boolean onQueryTextChange(String str) {
        this.searchString = str;
        Fragment defaultFragment = getDefaultFragment();
        if (defaultFragment == null || !(defaultFragment instanceof QxMDFragment)) {
            return false;
        }
        ((QxMDFragment) defaultFragment).toolbarSearchEnterred(str);
        return false;
    }
}
