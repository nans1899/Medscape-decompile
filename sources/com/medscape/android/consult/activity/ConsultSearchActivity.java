package com.medscape.android.consult.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.tabs.TabLayout;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.fragments.ConsultSearchFragment;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import java.util.Map;

public class ConsultSearchActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public String mQuery;
    /* access modifiers changed from: private */
    public SearchView mSearchView;
    /* access modifiers changed from: private */
    public TabLayout mTabLayout;

    /* access modifiers changed from: private */
    public int getTypeForPosition(int i) {
        return i == 1 ? Constants.CONSULT_SEARCH_TAGS : i == 2 ? Constants.CONSULT_SEARCH_USERS : Constants.CONSULT_SEARCH_POSTS;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_consult_search);
        this.mPvid = OmnitureManager.get().trackPageView(this, "consult", "consult", "search", (String) null, (String) null, (Map<String, Object>) null);
        Intent intent = getIntent();
        if (intent != null && "android.intent.action.SEARCH".equals(intent.getAction()) && ((ConsultSearchFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_SEARCH)) == null) {
            getSupportFragmentManager().beginTransaction().replace((int) R.id.content_frame, (Fragment) ConsultSearchFragment.newInstance(), Constants.FRAGMENT_TAG_CONSULT_SEARCH).commit();
        }
        this.mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        setUpTabLayout();
        addTabChangedListener();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        View actionView;
        getMenuInflater().inflate(R.menu.consult_search_menu, menu);
        MenuItem findItem = menu.findItem(R.id.action_search);
        if (findItem == null || (actionView = findItem.getActionView()) == null || !(actionView instanceof SearchView)) {
            return true;
        }
        SearchView searchView = (SearchView) actionView;
        this.mSearchView = searchView;
        searchView.setQueryHint(getResources().getString(R.string.consult_search_posts_hint));
        this.mSearchView.setMaxWidth(Integer.MAX_VALUE);
        this.mSearchView.onActionViewExpanded();
        setQueryTextListener();
        setCloseButtonListener();
        return true;
    }

    private void setCloseButtonListener() {
        View findViewById;
        SearchView searchView = this.mSearchView;
        if (searchView != null && (findViewById = searchView.findViewById(R.id.search_close_btn)) != null && (findViewById instanceof ImageView)) {
            ((ImageView) findViewById).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultSearchActivity.this.clearSearch();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void clearSearch() {
        Fragment findFragmentByTag;
        this.mQuery = "";
        View findViewById = findViewById(R.id.search_src_text);
        if (findViewById != null && (findViewById instanceof EditText)) {
            EditText editText = (EditText) findViewById;
            if (!StringUtil.isNullOrEmpty(editText.getText().toString())) {
                editText.setText("");
            }
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (!(supportFragmentManager == null || (findFragmentByTag = supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_SEARCH)) == null || !(findFragmentByTag instanceof ConsultSearchFragment))) {
            ((ConsultSearchFragment) findFragmentByTag).clearSearch();
        }
        this.mSearchView.requestFocus();
        Util.showKeyboard(this);
    }

    private void setUpTabLayout() {
        if (this.mTabLayout.getTabCount() == 0) {
            TabLayout tabLayout = this.mTabLayout;
            tabLayout.addTab(tabLayout.newTab());
        }
        TabLayout.Tab tabAt = this.mTabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.setText((CharSequence) getResources().getString(R.string.consult_tab_posts));
        }
        if (this.mTabLayout.getTabCount() == 1) {
            TabLayout tabLayout2 = this.mTabLayout;
            tabLayout2.addTab(tabLayout2.newTab());
        }
        TabLayout.Tab tabAt2 = this.mTabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setText((CharSequence) getResources().getString(R.string.consult_tab_tags));
        }
        if (this.mTabLayout.getTabCount() == 2) {
            TabLayout tabLayout3 = this.mTabLayout;
            tabLayout3.addTab(tabLayout3.newTab());
        }
        TabLayout.Tab tabAt3 = this.mTabLayout.getTabAt(2);
        if (tabAt3 != null) {
            tabAt3.setText((CharSequence) getResources().getString(R.string.consult_tab_users));
        }
    }

    private void setQueryTextListener() {
        this.mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                String unused = ConsultSearchActivity.this.mQuery = str;
                int selectedTabPosition = ConsultSearchActivity.this.mTabLayout.getSelectedTabPosition();
                ConsultSearchActivity consultSearchActivity = ConsultSearchActivity.this;
                consultSearchActivity.searchWithTypeAndQuery(consultSearchActivity.getTypeForPosition(selectedTabPosition));
                ConsultSearchActivity.this.mSearchView.clearFocus();
                return false;
            }

            public boolean onQueryTextChange(String str) {
                if (!StringUtil.isNullOrEmpty(str)) {
                    return false;
                }
                ConsultSearchActivity.this.clearSearch();
                return false;
            }
        });
    }

    private void addTabChangedListener() {
        TabLayout tabLayout = this.mTabLayout;
        if (tabLayout != null) {
            tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
                public void onTabReselected(TabLayout.Tab tab) {
                }

                public void onTabUnselected(TabLayout.Tab tab) {
                }

                public void onTabSelected(TabLayout.Tab tab) {
                    ConsultSearchActivity.this.mPvid = OmnitureManager.get().trackPageView(ConsultSearchActivity.this, "consult", "consult", "search", (String) null, (String) null, (Map<String, Object>) null);
                    int position = tab.getPosition();
                    ConsultSearchActivity.this.setQueryHintForPosition(position);
                    ConsultSearchActivity consultSearchActivity = ConsultSearchActivity.this;
                    consultSearchActivity.searchWithTypeAndQuery(consultSearchActivity.getTypeForPosition(position));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setQueryHintForPosition(int i) {
        SearchView searchView = this.mSearchView;
        if (searchView == null) {
            return;
        }
        if (i == 0) {
            searchView.setQueryHint(getResources().getString(R.string.consult_search_posts_hint));
        } else if (i == 1) {
            searchView.setQueryHint(getResources().getString(R.string.consult_search_tags_hint));
        } else if (i == 2) {
            searchView.setQueryHint(getResources().getString(R.string.consult_search_users_hint));
        }
    }

    /* access modifiers changed from: private */
    public void searchWithTypeAndQuery(int i) {
        ConsultSearchFragment consultSearchFragment;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(Constants.FRAGMENT_TAG_CONSULT_SEARCH);
            if (findFragmentByTag == null || !(findFragmentByTag instanceof ConsultSearchFragment)) {
                consultSearchFragment = ConsultSearchFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace((int) R.id.content_frame, (Fragment) consultSearchFragment, Constants.FRAGMENT_TAG_CONSULT_SEARCH).commit();
            } else {
                consultSearchFragment = (ConsultSearchFragment) findFragmentByTag;
            }
            consultSearchFragment.loadFeedForSearchRequest(i, this.mQuery);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public void onBackPressed() {
        finish();
    }
}
