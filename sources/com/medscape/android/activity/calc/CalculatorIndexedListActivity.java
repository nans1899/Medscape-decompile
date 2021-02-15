package com.medscape.android.activity.calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.search.RecentlyViewedItemClickListener;
import com.medscape.android.activity.search.RecentlyViewedItemsAdapter;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.search.SearchActivity;
import com.medscape.android.activity.search.SearchMode;
import com.medscape.android.activity.search.SearchModeProvider;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.drugs.AbstractCursorLoader;
import com.medscape.android.drugs.IndexedItemsDataAdapter;
import com.medscape.android.drugs.IndexedListCursorAdapter;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.ViewHelper;
import com.medscape.android.view.FastScroller;
import com.tonicartos.superslim.LayoutManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CalculatorIndexedListActivity extends AbstractBreadcrumbNavigableActivity implements LoaderManager.LoaderCallbacks<Cursor>, SearchModeProvider {
    private static final String DISABLED = "disabled";
    public static final int INDEX_CALC_ID = 1;
    public static final int INDEX_CALC_TITLE = 0;
    public static final int INDEX_CALC_TYPE = 2;
    public static final int INDEX_SEARCH_CALC_ID = 0;
    public static final int INDEX_SEARCH_CALC_TITLE = 2;
    public static final int INDEX_SEARCH_CALC_TYPE = 3;
    public static final int LOADER_CALCS = 1;
    public static final int LOADER_RECENTLY_VIEWED_ITEMS = 2;
    public static final int MAX_RECENTLY_VIEWED_ITEMS = 5;
    public static final int MAX_SEARCH_RESULTS = 25;
    public static String QUERY;
    private static final String TAG = CalculatorIndexedListActivity.class.getSimpleName();
    public AutoCompleteTextView autoCompleteTextView;
    /* access modifiers changed from: private */
    public ArrayList<String> calcTitles;
    public SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    /* access modifiers changed from: private */
    public boolean fakeSubmitQueryToOpenRecentlyViewed;
    /* access modifiers changed from: private */
    public boolean isSearchMenuCollapsed = false;
    /* access modifiers changed from: private */
    public RecyclerView mCalcsListView;
    IndexedListCursorAdapter mCursorAdapter;
    IndexedItemsDataAdapter mItemsAdapter;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private boolean popupShowing;
    private RecentlyViewedItemClickListener recentlyViewedItemClickListener;
    public MenuItem searchItem;
    public SearchView searchView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.indexed_calc_main);
        setQuery();
        this.mCalcsListView = (RecyclerView) findViewById(R.id.recycler_view);
        ProgressBar progressBar = (ProgressBar) ViewHelper.findById((Activity) this, 16908301);
        this.mProgressBar = progressBar;
        progressBar.setVisibility(0);
        this.mCalcsListView.setLayoutManager(new LayoutManager((Context) this));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
            setTitle(Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.calculators) + "</font>"));
        }
        IndexedListCursorAdapter indexedListCursorAdapter = new IndexedListCursorAdapter(this);
        this.mCursorAdapter = indexedListCursorAdapter;
        IndexedItemsDataAdapter indexedItemsDataAdapter = new IndexedItemsDataAdapter(indexedListCursorAdapter, new DataViewHolder.DataListClickListener() {
            public void onDataListItemClicked(int i) {
                if (CalculatorIndexedListActivity.this.mCursorAdapter != null && CalculatorIndexedListActivity.this.mCursorAdapter.getCount() > i) {
                    Cursor item = CalculatorIndexedListActivity.this.mCursorAdapter.getItem(i);
                    String string = item.getString(0);
                    CalcArticle calcArticle = new CalcArticle();
                    calcArticle.setCalcId(item.getString(1));
                    calcArticle.setTitle(string);
                    calcArticle.setType(Integer.parseInt(item.getString(2)));
                    if (CalculatorIndexedListActivity.this.calcTitles != null && CalculatorIndexedListActivity.this.calcTitles.size() > 0 && CalculatorIndexedListActivity.this.calcTitles.contains(string)) {
                        i = CalculatorIndexedListActivity.this.calcTitles.indexOf(string) + 1;
                    }
                    CalculatorIndexedListActivity.this.openSelectedCalculator(i, calcArticle);
                }
            }
        });
        this.mItemsAdapter = indexedItemsDataAdapter;
        this.mCalcsListView.setAdapter(indexedItemsDataAdapter);
        ((FastScroller) findViewById(R.id.fastscroller)).setRecyclerView(this.mCalcsListView);
        initDatabase(this);
        getSupportLoaderManager().initLoader(1, (Bundle) null, this);
        this.recentlyViewedItemClickListener = new RecentlyViewedItemClickListener(this);
        sendOmniturePing();
    }

    public void initDatabase(Context context) {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            this.dbHelper = databaseHelper;
            this.db = databaseHelper.getDatabase();
        } catch (Exception e) {
            Log.e(TAG, "failed to open db", e);
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            return new CalcsCursorLoader(this, this.db);
        }
        if (i == 2) {
            return new CursorLoader(this, RecentlyViewedSuggestionHelper.URI_RECENTLY_VIEWED, (String[]) null, (String) null, new String[]{null}, (String) null);
        }
        throw new IllegalArgumentException("unknown loader id: " + i);
    }

    public void onLoadFinished(Loader<Cursor> loader, final Cursor cursor) {
        int id = loader.getId();
        if (id == 1) {
            new Thread(new Runnable() {
                public void run() {
                    CalculatorIndexedListActivity.this.updateCalcList(cursor);
                    CalculatorIndexedListActivity.this.mCalcsListView.post(new Runnable() {
                        public void run() {
                            CalculatorIndexedListActivity.this.mItemsAdapter.setData(CalculatorIndexedListActivity.this.mCursorAdapter);
                            CalculatorIndexedListActivity.this.mItemsAdapter.notifyDataSetChanged();
                            CalculatorIndexedListActivity.this.mProgressBar.setVisibility(8);
                        }
                    });
                }
            }).start();
        } else if (id == 2) {
            applySearchViewRecentSuggestions(cursor);
        } else {
            throw new IllegalArgumentException("unknown loader id: " + loader.getId());
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        int id = loader.getId();
        if (id == 1) {
            this.mCursorAdapter.swapCursor((Cursor) null);
        } else if (id != 2) {
            throw new IllegalArgumentException("unknown loader id: " + loader.getId());
        }
    }

    public SearchMode getCurrentSearchMode() {
        return SearchMode.SEARCH_CALCULATORS;
    }

    private static class CalcsCursorLoader extends AbstractCursorLoader {
        private final SQLiteDatabase db;

        public CalcsCursorLoader(Context context, SQLiteDatabase sQLiteDatabase) {
            super(context);
            this.db = sQLiteDatabase;
        }

        /* access modifiers changed from: protected */
        public Cursor buildCursor() {
            SQLiteDatabase sQLiteDatabase = this.db;
            if (sQLiteDatabase == null) {
                Log.e("TAG", "Database is not initialized!!!");
                return null;
            }
            try {
                return sQLiteDatabase.rawQuery(CalculatorIndexedListActivity.QUERY, (String[]) null);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateCalcList(Cursor cursor) {
        this.mCursorAdapter.swapCursor(getCursorWithAlphabetIndexPopulated(cursor));
    }

    public Cursor getCursorWithAlphabetIndexPopulated(Cursor cursor) {
        Character ch = null;
        if (cursor == null) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames());
        this.calcTitles = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String string = cursor.getString(cursor.getColumnIndex("Title"));
            this.calcTitles.add(string);
            if (StringUtil.isNotEmpty(string)) {
                if (ch == null) {
                    ch = Character.valueOf(string.charAt(0));
                    insertRowWithIndex(cursor, matrixCursor, ch.toString());
                } else if (Character.isLetter(ch.charValue()) && Character.toLowerCase(ch.charValue()) != Character.toLowerCase(string.charAt(0))) {
                    ch = Character.valueOf(string.charAt(0));
                    insertRowWithIndex(cursor, matrixCursor, ch.toString());
                }
            }
            insertRow(cursor, matrixCursor);
            cursor.moveToNext();
        }
        return matrixCursor;
    }

    private void insertRow(Cursor cursor, MatrixCursor matrixCursor) {
        if (cursor == null || cursor.isClosed()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    CalculatorIndexedListActivity.this.getSupportLoaderManager().restartLoader(1, (Bundle) null, CalculatorIndexedListActivity.this);
                }
            });
            return;
        }
        String[] columnNames = cursor.getColumnNames();
        String[] strArr = new String[columnNames.length];
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            strArr[i] = cursor.getString(i);
        }
        matrixCursor.addRow(strArr);
    }

    private static void insertRowWithIndex(Cursor cursor, MatrixCursor matrixCursor, String str) {
        String[] columnNames = cursor.getColumnNames();
        String[] strArr = new String[columnNames.length];
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            String str2 = columnNames[i];
            char c = 65535;
            int hashCode = str2.hashCode();
            if (hashCode != 94650) {
                if (hashCode == 80818744 && str2.equals("Title")) {
                    c = 0;
                }
            } else if (str2.equals("_id")) {
                c = 1;
            }
            if (c == 0) {
                strArr[i] = str;
            } else if (c != 1) {
                strArr[i] = cursor.getString(i);
            } else {
                strArr[i] = DISABLED;
            }
        }
        matrixCursor.addRow(strArr);
    }

    /* access modifiers changed from: private */
    public void openSelectedCalculator(int i, CalcArticle calcArticle) {
        OmnitureManager omnitureManager = OmnitureManager.get();
        omnitureManager.markModule(true, "brwslst", "" + i, (Map<String, Object>) null);
        Intent intent = new Intent(this, CalcArticleActivity.class);
        intent.putExtra("article", calcArticle);
        startActivity(intent);
    }

    private void sendOmniturePing() {
        this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "calculators/browse", "view", (String) null, (String) null, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        View inflate = LayoutInflater.from(this).inflate(R.layout.new_search_actionbar, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.back_arrow);
        SearchView searchView2 = (SearchView) inflate.findViewById(R.id.search_view);
        this.searchView = searchView2;
        searchView2.setIconifiedByDefault(false);
        this.searchView.setIconified(true);
        EditText editText = (EditText) this.searchView.findViewById(R.id.search_src_text);
        editText.setTextColor(getResources().getColor(R.color.title_color));
        editText.setHintTextColor(getResources().getColor(R.color.material_grey));
        editText.setHint(getResources().getString(R.string.search_calculators_view));
        ((ImageView) this.searchView.findViewById(R.id.search_close_btn)).setImageResource(R.drawable.ic_close_blue_24dp);
        final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.search_icon);
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            declaredField.set(editText, Integer.valueOf(R.drawable.new_search_cursor));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.searchView.setQueryHint(getResources().getString(R.string.search_calculators_view));
        initAutoCompleteSearchView();
        this.searchView.setQuery("", true);
        editText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    CalculatorIndexedListActivity.this.searchView.setIconified(false);
                    if (TextUtils.isEmpty(CalculatorIndexedListActivity.this.searchView.getQuery())) {
                        CalculatorIndexedListActivity.this.getSupportLoaderManager().restartLoader(2, (Bundle) null, CalculatorIndexedListActivity.this);
                    } else {
                        CalculatorIndexedListActivity.this.searchView.setQuery(CalculatorIndexedListActivity.this.searchView.getQuery(), false);
                    }
                    HashMap hashMap = new HashMap();
                    OmnitureManager.get().markModule("calcssearch", "ref", (Map<String, Object>) null);
                    CalculatorIndexedListActivity.this.mPvid = OmnitureManager.get().trackPageView(CalculatorIndexedListActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "calculators/search/recently-viewed", (String) null, (String) null, (String) null, hashMap);
                }
                return true;
            }
        });
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                if (CalculatorIndexedListActivity.this.fakeSubmitQueryToOpenRecentlyViewed) {
                    boolean unused = CalculatorIndexedListActivity.this.fakeSubmitQueryToOpenRecentlyViewed = false;
                    return true;
                }
                OmnitureManager.get().trackModule(CalculatorIndexedListActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "search-btn", "ref", (Map<String, Object>) null);
                Intent intent = new Intent(CalculatorIndexedListActivity.this, SearchActivity.class);
                intent.putExtra(Constants.EXTRA_QUERY, str);
                intent.putExtra(Constants.EXTRA_MODE, SearchMode.SEARCH_CALCULATORS.getId());
                CalculatorIndexedListActivity.this.startActivity(intent);
                return true;
            }

            public boolean onQueryTextChange(String str) {
                if (!CalculatorIndexedListActivity.this.isSearchMenuCollapsed) {
                    if (TextUtils.isEmpty(str)) {
                        CalculatorIndexedListActivity.this.searchView.setSuggestionsAdapter((CursorAdapter) null);
                        imageView2.setVisibility(0);
                        CalculatorIndexedListActivity.this.hideSuggestionDropDown();
                        CalculatorIndexedListActivity.this.getSupportLoaderManager().restartLoader(2, (Bundle) null, CalculatorIndexedListActivity.this);
                    } else {
                        imageView2.setVisibility(8);
                        CalculatorIndexedListActivity.this.setupSuggestionsAdapter();
                    }
                }
                boolean unused = CalculatorIndexedListActivity.this.isSearchMenuCollapsed = false;
                return true;
            }
        });
        this.searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            public boolean onSuggestionSelect(int i) {
                return false;
            }

            public boolean onSuggestionClick(int i) {
                CalculatorIndexedListActivity calculatorIndexedListActivity = CalculatorIndexedListActivity.this;
                calculatorIndexedListActivity.handleSuggestionClick(i, calculatorIndexedListActivity.searchView.getSuggestionsAdapter() instanceof RecentlyViewedItemsAdapter);
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CalculatorIndexedListActivity.this.finish();
            }
        });
        getSupportActionBar().setCustomView(inflate);
    }

    private void initAutoCompleteSearchView() {
        try {
            Field declaredField = SearchView.class.getDeclaredField("mSearchSrcTextView");
            declaredField.setAccessible(true);
            AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) declaredField.get(this.searchView);
            this.autoCompleteTextView = autoCompleteTextView2;
            autoCompleteTextView2.setThreshold(0);
        } catch (NoSuchFieldException e) {
            Log.w(TAG, "initAutoCompleteSearchView: auto complete text view field is missing", e);
        } catch (IllegalAccessException e2) {
            Log.w(TAG, "initAutoCompleteSearchView: no access to auto complete text view field", e2);
        }
    }

    private void showSuggestionDropDown() {
        if (this.autoCompleteTextView != null && !isFinishing()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    try {
                        CalculatorIndexedListActivity.this.autoCompleteTextView.showDropDown();
                    } catch (Throwable unused) {
                    }
                }
            }, 100);
        }
    }

    /* access modifiers changed from: private */
    public void hideSuggestionDropDown() {
        AutoCompleteTextView autoCompleteTextView2 = this.autoCompleteTextView;
        if (autoCompleteTextView2 != null) {
            autoCompleteTextView2.dismissDropDown();
        }
    }

    private void setupFilterAdapter(Cursor cursor) {
        SearchResultsCursorAdapter searchResultsCursorAdapter = new SearchResultsCursorAdapter(this, cursor);
        searchResultsCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            private Cursor lastResults;

            public Cursor runQuery(CharSequence charSequence) {
                if (TextUtils.isEmpty(charSequence)) {
                    return this.lastResults;
                }
                CalculatorIndexedListActivity calculatorIndexedListActivity = CalculatorIndexedListActivity.this;
                Cursor buildSearchDrugsCursor = calculatorIndexedListActivity.buildSearchDrugsCursor(calculatorIndexedListActivity.db, charSequence);
                this.lastResults = buildSearchDrugsCursor;
                return buildSearchDrugsCursor;
            }
        });
        this.searchView.setSuggestionsAdapter(searchResultsCursorAdapter);
        this.fakeSubmitQueryToOpenRecentlyViewed = true;
        SearchView searchView2 = this.searchView;
        searchView2.setQuery(searchView2.getQuery(), true);
        showSuggestionDropDown();
    }

    /* access modifiers changed from: private */
    public void setupSuggestionsAdapter() {
        if (this.searchView.getSuggestionsAdapter() == null || (this.searchView.getSuggestionsAdapter() instanceof RecentlyViewedItemsAdapter)) {
            this.searchView.setSuggestionsAdapter((CursorAdapter) null);
            OmnitureManager.get().markModule("search-type", "ref", (Map<String, Object>) null);
            OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "browse-search/view", (String) null, (String) null, (String) null, (Map<String, Object>) null);
            applySearchResults((Cursor) null);
        }
    }

    private void applySearchResults(Cursor cursor) {
        if (this.searchView.getSuggestionsAdapter() == null) {
            setupFilterAdapter(cursor);
            return;
        }
        throw new IllegalStateException("we should not load content while filtering");
    }

    private static class SearchResultsCursorAdapter extends CursorAdapter {
        private final LayoutInflater layoutInflater;

        SearchResultsCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
            this.layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.layoutInflater.inflate(R.layout.search_suggestion_result_item, viewGroup, false);
            final TextView textView = (TextView) inflate.findViewById(R.id.textview);
            inflate.setTag(new ViewHolder(textView, new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    textView.setSingleLine(false);
                    ViewTreeObserver viewTreeObserver = textView.getViewTreeObserver();
                    if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
                        return true;
                    }
                    viewTreeObserver.removeOnPreDrawListener(this);
                    return true;
                }
            }));
            return inflate;
        }

        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            TextView textView = viewHolder.textView;
            textView.setText(cursor.getString(2));
            textView.setCompoundDrawablesWithIntrinsicBounds(SearchHelper.TYPE_CALCULATOR_FOLDER.equals(cursor.getString(3)) ? R.drawable.search_icon_class_blue : R.drawable.calculator, 0, 0, 0);
            ViewTreeObserver viewTreeObserver = viewHolder.textView.getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(viewHolder.onPreDrawListener);
            }
        }
    }

    public static class ViewHolder {
        final ViewTreeObserver.OnPreDrawListener onPreDrawListener;
        public final TextView textView;

        private ViewHolder(TextView textView2, ViewTreeObserver.OnPreDrawListener onPreDrawListener2) {
            this.textView = textView2;
            this.onPreDrawListener = onPreDrawListener2;
        }
    }

    public Cursor buildSearchDrugsCursor(SQLiteDatabase sQLiteDatabase, CharSequence charSequence) {
        if (sQLiteDatabase == null || charSequence == null || StringUtil.isNullOrEmpty(charSequence.toString())) {
            Log.e("TAG", "Database is not initialized!!!");
            return null;
        }
        String buildCalcsSearchSql = SearchHelper.buildCalcsSearchSql(charSequence, true, 25);
        if (buildCalcsSearchSql == null) {
            return null;
        }
        return removeDuplicates(sQLiteDatabase.rawQuery(buildCalcsSearchSql, (String[]) null));
    }

    /* access modifiers changed from: private */
    public void handleSuggestionClick(int i, boolean z) {
        Cursor cursor = (Cursor) this.searchView.getSuggestionsAdapter().getItem(i);
        if (z) {
            this.recentlyViewedItemClickListener.launchAppropriateScreen(cursor);
        } else if (cursor.getString(3).equals(SearchHelper.TYPE_CALCULATOR)) {
            CalcArticle calcArticle = new CalcArticle();
            calcArticle.setCalcId(cursor.getString(0));
            calcArticle.setTitle(cursor.getString(2));
            openSelectedCalculator(i + 1, calcArticle);
        } else if (cursor.getString(3).equals(SearchHelper.TYPE_CALCULATOR_FOLDER)) {
            try {
                startActivity(new Intent(this, CalcTitleActivity.class).putExtra("folderId", cursor.getString(0)).putExtra("folderName", cursor.getString(2)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.popupShowing = true;
    }

    private void applySearchViewRecentSuggestions(Cursor cursor) {
        this.searchView.setSuggestionsAdapter(new RecentlyViewedItemsAdapter(this, RecentlyViewedSuggestionHelper.filterRecentlyViewedBySearchMode(this, cursor, SearchMode.SEARCH_CALCULATORS, 5)));
        showSuggestionDropDown();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        reshowSuggestionsPopup();
        this.popupShowing = false;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        AutoCompleteTextView autoCompleteTextView2 = this.autoCompleteTextView;
        if (autoCompleteTextView2 != null) {
            this.popupShowing = autoCompleteTextView2.isPopupShowing() | this.popupShowing;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.dbHelper.close();
    }

    private void reshowSuggestionsPopup() {
        SearchView searchView2 = this.searchView;
        if (searchView2 != null && this.popupShowing && this.autoCompleteTextView != null && searchView2.getSuggestionsAdapter().getCount() > 0) {
            if (TextUtils.isEmpty(this.searchView.getQuery())) {
                getSupportLoaderManager().restartLoader(2, (Bundle) null, this);
            }
            this.autoCompleteTextView.showDropDown();
        }
    }

    public void setQuery() {
        QUERY = "SELECT DISTINCT Title, CalcID, Type, UniqueID AS _id FROM tblCalcTitles ORDER BY Title COLLATE NOCASE";
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.isSearchMenuCollapsed = false;
        if (!TextUtils.isEmpty(this.searchView.getQuery())) {
            setupSuggestionsAdapter();
        }
    }

    private Cursor removeDuplicates(Cursor cursor) {
        MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames());
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        while (cursor.moveToNext()) {
            try {
                String string = cursor.getString(3);
                String string2 = cursor.getString(2);
                String string3 = cursor.getString(0);
                if ((string.equals(SearchHelper.TYPE_CALCULATOR) && hashSet2.add(string2)) || (string.equals(SearchHelper.TYPE_CALCULATOR_FOLDER) && hashSet.add(string2))) {
                    String[] strArr = new String[cursor.getColumnCount()];
                    strArr[0] = string3;
                    strArr[2] = string2;
                    strArr[3] = string;
                    matrixCursor.addRow(strArr);
                }
            } catch (Exception unused) {
                return cursor;
            } finally {
                cursor.close();
            }
        }
        return matrixCursor;
    }
}
