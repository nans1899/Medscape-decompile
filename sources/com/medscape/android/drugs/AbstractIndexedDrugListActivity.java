package com.medscape.android.drugs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
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
import com.medscape.android.activity.formulary.IndexedDrugFormularyListActivity;
import com.medscape.android.activity.search.RecentlyViewedItemClickListener;
import com.medscape.android.activity.search.RecentlyViewedItemsAdapter;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.search.SearchActivity;
import com.medscape.android.activity.search.SearchMode;
import com.medscape.android.activity.search.SearchModeProvider;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.drugs.model.DrugsContract;
import com.medscape.android.util.ViewHelper;
import com.medscape.android.util.constants.AppboyConstants;
import com.medscape.android.view.FastScroller;
import com.medscape.android.webmdrx.activity.IndexedRxDrugListActivity;
import com.tonicartos.superslim.LayoutManager;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractIndexedDrugListActivity extends AbstractBreadcrumbNavigableActivity implements SearchModeProvider, LoaderManager.LoaderCallbacks<Cursor> {
    private static final String DISABLED = "disabled";
    public static final int INDEX_CONTENT_ID = 0;
    public static final int INDEX_GENERIC_ID = 3;
    public static final int INDEX_ID = 1;
    public static final int INDEX_NAME = 2;
    public static final int INDEX_SOURCE = 5;
    public static final int INDEX_TYPE = 4;
    public static final int LOADER_DRUGS = 1;
    public static final int LOADER_RECENTLY_VIEWED_ITEMS = 2;
    public static final int MAX_RECENTLY_VIEWED_ITEMS = 5;
    public static final int MAX_SEARCH_RESULTS = 25;
    /* access modifiers changed from: private */
    public static String QUERY;
    private static final String TAG = AbstractIndexedDrugListActivity.class.getSimpleName();
    public static boolean mIsRxDrugActivity;
    /* access modifiers changed from: private */
    public AutoCompleteTextView autoCompleteTextView;
    /* access modifiers changed from: private */
    public SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    /* access modifiers changed from: private */
    public IndexedListCursorAdapter drugsCursorAdapter;
    /* access modifiers changed from: private */
    public boolean fakeSubmitQueryToOpenRecentlyViewed;
    /* access modifiers changed from: private */
    public boolean isSearchMenuCollapsed;
    IndexedItemsDataAdapter mDrugsDataAdapter;
    protected RecyclerView mDrugsListView;
    private boolean popupShowing;
    private RecentlyViewedItemClickListener recentlyViewedItemClickListener;
    private MenuItem searchItem;
    protected SearchView searchView;

    protected enum ClickSource {
        BROWSE,
        SEARCH
    }

    /* access modifiers changed from: protected */
    public abstract int getHintHeaderText();

    /* access modifiers changed from: protected */
    public abstract boolean includeDrugClasses();

    /* access modifiers changed from: protected */
    public abstract void onDrugClick(String str, String str2, String str3, String str4, int i, ClickSource clickSource, int i2, String str5);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.indexed_drugs_main);
        QUERY = "SELECT ContentID, UniqueID AS _id, DrugName, GenericID, 'D' as TYPE, 1 as Source FROM tblDrugs";
        ViewHelper.findById((Activity) this, 16908301).setVisibility(0);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mDrugsListView = recyclerView;
        recyclerView.setLayoutManager(new LayoutManager((Context) this));
        IndexedListCursorAdapter indexedListCursorAdapter = new IndexedListCursorAdapter(this);
        this.drugsCursorAdapter = indexedListCursorAdapter;
        IndexedItemsDataAdapter indexedItemsDataAdapter = new IndexedItemsDataAdapter(indexedListCursorAdapter, new DataViewHolder.DataListClickListener() {
            public void onDataListItemClicked(int i) {
                AbstractIndexedDrugListActivity abstractIndexedDrugListActivity = AbstractIndexedDrugListActivity.this;
                abstractIndexedDrugListActivity.handleDrugListItemClick(abstractIndexedDrugListActivity.drugsCursorAdapter, i, ClickSource.BROWSE);
            }
        });
        this.mDrugsDataAdapter = indexedItemsDataAdapter;
        this.mDrugsListView.setAdapter(indexedItemsDataAdapter);
        ((FastScroller) findViewById(R.id.fastscroller)).setRecyclerView(this.mDrugsListView);
        setupHintHeader();
        initDatabase(this);
        getSupportLoaderManager().initLoader(1, (Bundle) null, this);
        this.recentlyViewedItemClickListener = new RecentlyViewedItemClickListener(this);
        sendOmniturePing();
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
        editText.setHint(getResources().getString(R.string.search_drugs_view));
        ((ImageView) this.searchView.findViewById(R.id.search_close_btn)).setImageResource(R.drawable.ic_close_blue_24dp);
        final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.search_icon);
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            declaredField.set(editText, Integer.valueOf(R.drawable.new_search_cursor));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.searchView.setQueryHint(getResources().getString(R.string.search_drugs_view));
        initAutoCompleteSearchView();
        this.searchView.setQuery("", true);
        editText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    AbstractIndexedDrugListActivity.this.searchView.setIconified(false);
                    if (TextUtils.isEmpty(AbstractIndexedDrugListActivity.this.searchView.getQuery())) {
                        AbstractIndexedDrugListActivity.this.getSupportLoaderManager().restartLoader(2, (Bundle) null, AbstractIndexedDrugListActivity.this);
                    } else {
                        AbstractIndexedDrugListActivity.this.searchView.setQuery(AbstractIndexedDrugListActivity.this.searchView.getQuery(), false);
                    }
                    HashMap hashMap = new HashMap();
                    AbstractIndexedDrugListActivity abstractIndexedDrugListActivity = AbstractIndexedDrugListActivity.this;
                    if (abstractIndexedDrugListActivity instanceof IndexedDrugListActivity) {
                        OmnitureManager.get().markModule("drugssearch", "drgs", (Map<String, Object>) null);
                        AbstractIndexedDrugListActivity.this.mPvid = OmnitureManager.get().trackPageView(AbstractIndexedDrugListActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "drugs/search/recently-viewed", (String) null, (String) null, (String) null, hashMap);
                    } else if (abstractIndexedDrugListActivity instanceof IndexedRxDrugListActivity) {
                        abstractIndexedDrugListActivity.mPvid = OmnitureManager.get().trackPageView(AbstractIndexedDrugListActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "pricing/search/recently-viewed", (String) null, (String) null, (String) null, hashMap);
                    } else {
                        abstractIndexedDrugListActivity.mPvid = OmnitureManager.get().trackPageView(AbstractIndexedDrugListActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "search", "click", (String) null, (String) null, hashMap);
                    }
                }
                return true;
            }
        });
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                if (AbstractIndexedDrugListActivity.this.fakeSubmitQueryToOpenRecentlyViewed) {
                    boolean unused = AbstractIndexedDrugListActivity.this.fakeSubmitQueryToOpenRecentlyViewed = false;
                    return true;
                }
                if (AbstractIndexedDrugListActivity.this instanceof IndexedDrugListActivity) {
                    OmnitureManager.get().trackModule(AbstractIndexedDrugListActivity.this, Constants.OMNITURE_CHANNEL_REFERENCE, "search-btn", "drgs", (Map<String, Object>) null);
                }
                AbstractIndexedDrugListActivity.this.startSearchActivity(str, SearchMode.SEARCH_DRUGS);
                return true;
            }

            public boolean onQueryTextChange(String str) {
                if (!AbstractIndexedDrugListActivity.this.isSearchMenuCollapsed) {
                    if (TextUtils.isEmpty(str)) {
                        AbstractIndexedDrugListActivity.this.searchView.setSuggestionsAdapter((CursorAdapter) null);
                        imageView2.setVisibility(0);
                        AbstractIndexedDrugListActivity.this.hideSuggestionDropDown();
                        AbstractIndexedDrugListActivity.this.getSupportLoaderManager().restartLoader(2, (Bundle) null, AbstractIndexedDrugListActivity.this);
                    } else {
                        imageView2.setVisibility(8);
                        AbstractIndexedDrugListActivity.this.setupSuggestionsAdapter();
                    }
                }
                boolean unused = AbstractIndexedDrugListActivity.this.isSearchMenuCollapsed = false;
                return true;
            }
        });
        this.searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            public boolean onSuggestionSelect(int i) {
                return false;
            }

            public boolean onSuggestionClick(int i) {
                AbstractIndexedDrugListActivity abstractIndexedDrugListActivity = AbstractIndexedDrugListActivity.this;
                abstractIndexedDrugListActivity.handleSuggestionClick(i, abstractIndexedDrugListActivity.searchView.getSuggestionsAdapter() instanceof RecentlyViewedItemsAdapter);
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AbstractIndexedDrugListActivity.this.finish();
            }
        });
        getSupportActionBar().setCustomView(inflate);
    }

    /* access modifiers changed from: protected */
    public void sendOmniturePing() {
        if (this instanceof IndexedDrugListActivity) {
            this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drugs/browse", "view", (String) null, (String) null, (Map<String, Object>) null);
        } else if (this instanceof IndexedDrugFormularyListActivity) {
            this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drugs/formulary/browse", "view", (String) null, (String) null, (Map<String, Object>) null);
            AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_FORMULARY_VIEWED, this);
        } else if (this instanceof IndexedRxDrugListActivity) {
            this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drugs/webmdrx/browse", "view", (String) null, (String) null, (Map<String, Object>) null);
        }
    }

    private void setupHintHeader() {
        ((TextView) ViewHelper.findById((Activity) this, (int) R.id.hintHeader)).setText(getHintHeaderText());
    }

    /* access modifiers changed from: private */
    public void handleDrugListItemClick(CursorAdapter cursorAdapter, int i, ClickSource clickSource) {
        Cursor cursor = (Cursor) cursorAdapter.getItem(i);
        String string = cursor.getString(1);
        if (!DISABLED.equals(string)) {
            onDrugClick(string, cursor.getString(0), cursor.getString(2), cursor.getString(4), cursor.getInt(5), clickSource, i, cursor.getString(3));
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        reshowSuggestionsPopup();
        this.popupShowing = false;
        mIsRxDrugActivity = this instanceof IndexedRxDrugListActivity;
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(this.searchView.getQuery())) {
            setupSuggestionsAdapter();
        }
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
        closeDatabase();
    }

    /* access modifiers changed from: private */
    public void startSearchActivity(String str, SearchMode searchMode) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(Constants.EXTRA_QUERY, str);
        intent.putExtra(Constants.EXTRA_MODE, searchMode.getId());
        if (this instanceof IndexedDrugFormularyListActivity) {
            intent.putExtra("isFormulary", true);
        } else if (this instanceof IndexedRxDrugListActivity) {
            intent.putExtra("isRx", true);
        }
        startActivity(intent);
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

    /* access modifiers changed from: private */
    public void handleSuggestionClick(int i, boolean z) {
        if (z) {
            this.recentlyViewedItemClickListener.launchAppropriateScreen((Cursor) this.searchView.getSuggestionsAdapter().getItem(i));
        } else {
            handleDrugListItemClick(this.searchView.getSuggestionsAdapter(), i, ClickSource.SEARCH);
        }
        this.popupShowing = true;
    }

    /* access modifiers changed from: private */
    public void setupSuggestionsAdapter() {
        if (this.searchView.getSuggestionsAdapter() == null || (this.searchView.getSuggestionsAdapter() instanceof RecentlyViewedItemsAdapter)) {
            this.searchView.setSuggestionsAdapter((CursorAdapter) null);
            if (this instanceof IndexedDrugListActivity) {
                OmnitureManager.get().markModule("search-type", "drgs", (Map<String, Object>) null);
                OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "browse-search/view", (String) null, (String) null, (String) null, (Map<String, Object>) null);
            }
            applySearchResults((Cursor) null);
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
                AbstractIndexedDrugListActivity abstractIndexedDrugListActivity = AbstractIndexedDrugListActivity.this;
                Cursor access$900 = abstractIndexedDrugListActivity.buildSearchDrugsCursor(abstractIndexedDrugListActivity.db, charSequence);
                this.lastResults = access$900;
                return access$900;
            }
        });
        this.searchView.setSuggestionsAdapter(searchResultsCursorAdapter);
        this.fakeSubmitQueryToOpenRecentlyViewed = true;
        SearchView searchView2 = this.searchView;
        searchView2.setQuery(searchView2.getQuery(), true);
        showSuggestionDropDown();
    }

    private void showSuggestionDropDown() {
        if (this.autoCompleteTextView != null && !isFinishing()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    try {
                        AbstractIndexedDrugListActivity.this.autoCompleteTextView.showDropDown();
                    } catch (Throwable unused) {
                    }
                }
            }, 100);
        }
    }

    /* access modifiers changed from: private */
    public void hideSuggestionDropDown() {
        if (this.autoCompleteTextView != null && !isFinishing()) {
            this.autoCompleteTextView.dismissDropDown();
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            boolean z = this instanceof IndexedRxDrugListActivity;
            mIsRxDrugActivity = z;
            if (z) {
                return new RxDrugsCursorLoader(this, this.db);
            }
            return new DrugsCursorLoader(this, this.db);
        } else if (i == 2) {
            return new CursorLoader(this, RecentlyViewedSuggestionHelper.URI_RECENTLY_VIEWED, (String[]) null, (String) null, new String[]{null}, (String) null);
        } else {
            throw new IllegalArgumentException("unknown loader id: " + i);
        }
    }

    public void onLoadFinished(Loader<Cursor> loader, final Cursor cursor) {
        int id = loader.getId();
        if (id == 1) {
            new Thread(new Runnable() {
                public void run() {
                    AbstractIndexedDrugListActivity.this.updateDrugList(cursor);
                    AbstractIndexedDrugListActivity.this.mDrugsListView.post(new Runnable() {
                        public void run() {
                            AbstractIndexedDrugListActivity.this.mDrugsDataAdapter.setData(AbstractIndexedDrugListActivity.this.drugsCursorAdapter);
                            AbstractIndexedDrugListActivity.this.mDrugsDataAdapter.notifyDataSetChanged();
                            ViewHelper.findById((Activity) AbstractIndexedDrugListActivity.this, 16908301).setVisibility(8);
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

    private void applySearchResults(Cursor cursor) {
        if (this.searchView.getSuggestionsAdapter() == null) {
            setupFilterAdapter(cursor);
            return;
        }
        throw new IllegalStateException("we should not load content while filtering");
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        int id = loader.getId();
        if (id == 1) {
            updateDrugList();
        } else if (id != 2) {
            throw new IllegalArgumentException("unknown loader id: " + loader.getId());
        }
    }

    private void applySearchViewRecentSuggestions(Cursor cursor) {
        this.searchView.setSuggestionsAdapter(new RecentlyViewedItemsAdapter(this, RecentlyViewedSuggestionHelper.filterRecentlyViewedBySearchMode(this, cursor, this instanceof IndexedRxDrugListActivity ? SearchMode.SEARCH_PRICING : SearchMode.SEARCH_DRUGS, 5)));
        showSuggestionDropDown();
    }

    /* access modifiers changed from: private */
    public void updateDrugList(Cursor cursor) {
        this.drugsCursorAdapter.swapCursor(getCursorWithAlphabetIndexPopulated(cursor));
    }

    private void updateDrugList() {
        this.drugsCursorAdapter.swapCursor((Cursor) null);
    }

    /* access modifiers changed from: private */
    public Cursor buildSearchDrugsCursor(SQLiteDatabase sQLiteDatabase, CharSequence charSequence) {
        if (sQLiteDatabase == null) {
            Log.e("TAG", "Database is not initialized!!!");
            return null;
        }
        String buildDrugSearchSql = SearchHelper.buildDrugSearchSql(charSequence, includeDrugClasses(), 25, this instanceof IndexedRxDrugListActivity);
        if (buildDrugSearchSql == null) {
            return null;
        }
        return sQLiteDatabase.rawQuery(buildDrugSearchSql, (String[]) null);
    }

    /* access modifiers changed from: private */
    public static Cursor buildDrugsCursor(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase == null) {
            Log.e("TAG", "Database is not initialized!!!");
            return null;
        }
        try {
            return sQLiteDatabase.rawQuery(str, (String[]) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private void initDatabase(Context context) {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            this.dbHelper = databaseHelper;
            this.db = databaseHelper.getDatabase();
        } catch (Exception e) {
            Log.e(TAG, "failed to open db", e);
        }
    }

    private void closeDatabase() {
        this.dbHelper.close();
    }

    public SearchMode getCurrentSearchMode() {
        return this instanceof IndexedRxDrugListActivity ? SearchMode.SEARCH_PRICING : SearchMode.SEARCH_DRUGS;
    }

    private static class DrugsCursorLoader extends AbstractCursorLoader {
        private final SQLiteDatabase db;

        public DrugsCursorLoader(Context context, SQLiteDatabase sQLiteDatabase) {
            super(context);
            this.db = sQLiteDatabase;
        }

        /* access modifiers changed from: protected */
        public Cursor buildCursor() {
            String unused = AbstractIndexedDrugListActivity.QUERY = AbstractIndexedDrugListActivity.QUERY + " WHERE ContentID != 0";
            String unused2 = AbstractIndexedDrugListActivity.QUERY = AbstractIndexedDrugListActivity.QUERY + " ORDER BY DrugName + 0, DrugName COLLATE NOCASE";
            return AbstractIndexedDrugListActivity.buildDrugsCursor(this.db, AbstractIndexedDrugListActivity.QUERY);
        }
    }

    private static class RxDrugsCursorLoader extends AbstractCursorLoader {
        private final SQLiteDatabase db;

        public RxDrugsCursorLoader(Context context, SQLiteDatabase sQLiteDatabase) {
            super(context);
            this.db = sQLiteDatabase;
        }

        /* access modifiers changed from: protected */
        public Cursor buildCursor() {
            String unused = AbstractIndexedDrugListActivity.QUERY = AbstractIndexedDrugListActivity.QUERY + " WHERE ((ContentID != 0) AND (GenericID != 0) AND (Availability = 1 OR Availability = 4))";
            String unused2 = AbstractIndexedDrugListActivity.QUERY = AbstractIndexedDrugListActivity.QUERY + " ORDER BY DrugName + 0, DrugName COLLATE NOCASE";
            return AbstractIndexedDrugListActivity.buildDrugsCursor(this.db, AbstractIndexedDrugListActivity.QUERY);
        }
    }

    private static class SearchResultsCursorAdapter extends CursorAdapter {
        private final LayoutInflater layoutInflater;

        public SearchResultsCursorAdapter(Context context, Cursor cursor) {
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
            textView.setCompoundDrawablesWithIntrinsicBounds(SearchHelper.TYPE_DRUG_CLASS.equals(cursor.getString(4)) ? R.drawable.search_icon_class_blue : R.drawable.drugs3x, 0, 0, 0);
            ViewTreeObserver viewTreeObserver = viewHolder.textView.getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(viewHolder.onPreDrawListener);
            }
        }
    }

    public static class ViewHolder {
        public final ViewTreeObserver.OnPreDrawListener onPreDrawListener;
        public final TextView textView;

        private ViewHolder(TextView textView2, ViewTreeObserver.OnPreDrawListener onPreDrawListener2) {
            this.textView = textView2;
            this.onPreDrawListener = onPreDrawListener2;
        }
    }

    private Cursor getCursorWithAlphabetIndexPopulated(Cursor cursor) {
        if (cursor != null) {
            try {
                MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames());
                cursor.moveToFirst();
                Character ch = null;
                while (!cursor.isAfterLast()) {
                    String string = cursor.getString(cursor.getColumnIndex(DrugsContract.Drug.DRUG_NAME));
                    if (ch == null) {
                        ch = Character.valueOf(string.charAt(0));
                        insertRowWithIndex(cursor, matrixCursor, ch.toString());
                    } else if (Character.isLetter(ch.charValue()) && Character.toLowerCase(ch.charValue()) != Character.toLowerCase(string.charAt(0))) {
                        ch = Character.valueOf(string.charAt(0));
                        insertRowWithIndex(cursor, matrixCursor, ch.toString());
                    }
                    insertRow(cursor, matrixCursor);
                    cursor.moveToNext();
                }
                return matrixCursor;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public final void insertRow(Cursor cursor, MatrixCursor matrixCursor) {
        if (cursor == null || cursor.isClosed()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AbstractIndexedDrugListActivity.this.getSupportLoaderManager().restartLoader(1, (Bundle) null, AbstractIndexedDrugListActivity.this);
                }
            });
            return;
        }
        String[] columnNames = cursor.getColumnNames();
        String[] strArr = new String[columnNames.length];
        int length = columnNames.length - 1;
        for (int i = 0; i < length; i++) {
            strArr[i] = cursor.getString(i);
        }
        matrixCursor.addRow(strArr);
    }

    public static final void insertRowWithIndex(Cursor cursor, MatrixCursor matrixCursor, String str) {
        String[] columnNames = cursor.getColumnNames();
        String[] strArr = new String[columnNames.length];
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            if (columnNames[i].equals(DrugsContract.Drug.DRUG_NAME)) {
                strArr[i] = str;
            } else if (columnNames[i].equals("_id")) {
                strArr[i] = DISABLED;
            } else {
                strArr[i] = cursor.getString(i);
            }
        }
        matrixCursor.addRow(strArr);
    }
}
