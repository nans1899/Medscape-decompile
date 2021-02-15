package com.medscape.android.consult.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.MatrixCursor;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.adapters.ConsultTagsListAdapter;
import com.medscape.android.consult.adapters.IndexedTagsCursorAdapter;
import com.medscape.android.consult.interfaces.ITagCountListener;
import com.medscape.android.consult.interfaces.ITagsForSearchReceivedListener;
import com.medscape.android.consult.interfaces.ITagsReceivedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.tasks.FilterTagsTask;
import com.medscape.android.view.FastScroller;
import com.tonicartos.superslim.LayoutManager;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsultTagSelectionActivity extends BaseActivity implements ITagCountListener, SearchView.OnQueryTextListener, ITagsReceivedListener {
    private static final String TAG = ConsultTagSelectionActivity.class.getSimpleName();
    private ConsultTagsListAdapter mAdapter;
    private FastScroller mFastScroller;
    private LayoutManager mLayoutManger;
    private View mNoResultsView;
    private View mProgressDialog;
    private String mQuery;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private List<String> mTagList = null;
    private IndexedTagsCursorAdapter mTagsCursorAdapter;
    private Toolbar mToolbar;

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_tag_selection);
        this.mProgressDialog = findViewById(R.id.progressBar);
        this.mNoResultsView = findViewById(R.id.no_results_msg);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mTagsCursorAdapter = new IndexedTagsCursorAdapter(this);
        setUpRecyclerView();
        setSupportActionBar(this.mToolbar);
        setActionBarTitle(setTagListFromIntent());
        setActionBar();
        FastScroller fastScroller = (FastScroller) findViewById(R.id.fastscroller);
        this.mFastScroller = fastScroller;
        fastScroller.setRecyclerView(this.mRecyclerView);
    }

    public void setActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
        }
    }

    private void setActionBarTitle(int i) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            return;
        }
        if (i == 0) {
            supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.consult_selected_tag_label) + "</font>"));
            return;
        }
        supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.consult_selected_tag_label) + " (" + i + ")</font>"));
    }

    private int setTagListFromIntent() {
        Intent intent;
        ArrayList<String> stringArrayListExtra;
        if (this.mAdapter == null || (intent = getIntent()) == null || (stringArrayListExtra = intent.getStringArrayListExtra(Constants.CONSULT_SELECTED_TAGS)) == null || stringArrayListExtra.size() <= 0) {
            return 0;
        }
        int size = stringArrayListExtra.size();
        this.mAdapter.setSelectedTags(stringArrayListExtra);
        return size;
    }

    private void setUpRecyclerView() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LayoutManager layoutManager = new LayoutManager((Context) this);
        this.mLayoutManger = layoutManager;
        this.mRecyclerView.setLayoutManager(layoutManager);
        if (this.mAdapter == null) {
            this.mAdapter = new ConsultTagsListAdapter(this.mTagsCursorAdapter, this);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consult_tag_select, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        this.mSearchView = searchView;
        EditText editText = (EditText) searchView.findViewById(R.id.search_src_text);
        editText.setTextColor(getResources().getColor(R.color.white));
        editText.setHintTextColor(getResources().getColor(R.color.white));
        editText.setHint(getResources().getString(R.string.consult_search_tags_hint));
        ((ImageView) this.mSearchView.findViewById(R.id.search_close_btn)).setImageResource(R.drawable.ic_close_white_24dp);
        setActionBar();
        this.mSearchView.setOnQueryTextListener(this);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ArrayList<String> selectedTags;
        if (menuItem == null) {
            return true;
        }
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        } else if (menuItem.getItemId() != R.id.done) {
            return true;
        } else {
            Intent intent = new Intent();
            ConsultTagsListAdapter consultTagsListAdapter = this.mAdapter;
            if (!(consultTagsListAdapter == null || (selectedTags = consultTagsListAdapter.getSelectedTags()) == null)) {
                intent.putStringArrayListExtra(Constants.CONSULT_SELECTED_TAGS, selectedTags);
            }
            setResult(-1, intent);
            finish();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateTagsIfNecessary();
        this.mPvid = OmnitureManager.get().trackPageView(this, "consult", "consult", ContentParser.TAGS, (String) null, (String) null, (Map<String, Object>) null);
    }

    private void updateTagsIfNecessary() {
        List<String> list = this.mTagList;
        if (list == null) {
            ConsultDataManager.getInstance(this, this).loadTagList(this);
        } else {
            setContentForAdapter(list);
        }
    }

    private void setContentForAdapter(List<String> list) {
        this.mProgressDialog.setVisibility(8);
        if (this.mAdapter == null) {
            this.mAdapter = new ConsultTagsListAdapter(this.mTagsCursorAdapter, this);
        }
        if (list == null || list.isEmpty()) {
            this.mNoResultsView.setVisibility(0);
            this.mRecyclerView.setVisibility(8);
            this.mFastScroller.setVisibility(4);
            return;
        }
        this.mNoResultsView.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id", ContentParser.ITEM, "description"});
        Character ch = null;
        int i = 0;
        for (String next : list) {
            if (ch == null) {
                ch = Character.valueOf(next.charAt(0));
                matrixCursor.addRow(new Object[]{Integer.valueOf(i), "", ch.toString()});
            } else if (Character.toLowerCase(ch.charValue()) != Character.toLowerCase(next.charAt(0))) {
                ch = Character.valueOf(next.charAt(0));
                matrixCursor.addRow(new Object[]{Integer.valueOf(i), "", ch.toString()});
            }
            matrixCursor.addRow(new Object[]{Integer.valueOf(i), "", next});
            i++;
        }
        this.mTagsCursorAdapter.swapCursor(matrixCursor);
        this.mAdapter.setData(this.mTagsCursorAdapter);
        this.mAdapter.notifyDataSetChanged();
        setFastScrollerVisibility(this.mTagsCursorAdapter.getCount());
    }

    private void setFastScrollerVisibility(int i) {
        this.mRecyclerView.postDelayed(new Runnable(i) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ConsultTagSelectionActivity.this.lambda$setFastScrollerVisibility$0$ConsultTagSelectionActivity(this.f$1);
            }
        }, 200);
    }

    public /* synthetic */ void lambda$setFastScrollerVisibility$0$ConsultTagSelectionActivity(int i) {
        LayoutManager layoutManager = this.mLayoutManger;
        if (layoutManager != null && layoutManager.findFirstCompletelyVisibleItemPosition() == 1 && this.mLayoutManger.findLastCompletelyVisibleItemPosition() == i - 1) {
            this.mFastScroller.setVisibility(4);
        } else {
            this.mFastScroller.setVisibility(0);
        }
    }

    public void onTagCountChanged(int i) {
        setActionBarTitle(i);
        setActionBar();
    }

    public void showMaxTagCountMessage() {
        new AlertDialog.Builder(this).setMessage((CharSequence) getString(R.string.consult_tag_limit_msg)).setCancelable(true).setPositiveButton((CharSequence) getString(R.string.alert_dialog_confirmation_ok_button_text), (DialogInterface.OnClickListener) $$Lambda$ConsultTagSelectionActivity$LPBWL0XhrjE5AXtzPCVaOSl2U.INSTANCE).show();
    }

    public boolean onQueryTextChange(String str) {
        this.mQuery = str;
        new FilterTagsTask(this.mTagList, new ITagsForSearchReceivedListener() {
            public final void onTagsReceived(List list, String str) {
                ConsultTagSelectionActivity.this.lambda$onQueryTextChange$2$ConsultTagSelectionActivity(list, str);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
        return true;
    }

    public /* synthetic */ void lambda$onQueryTextChange$2$ConsultTagSelectionActivity(List list, String str) {
        String str2;
        if (str != null && (str2 = this.mQuery) != null && str.equalsIgnoreCase(str2)) {
            setContentForAdapter(list);
        }
    }

    public void onTagsReceived(List<String> list) {
        this.mTagList = list;
        setContentForAdapter(list);
    }
}
