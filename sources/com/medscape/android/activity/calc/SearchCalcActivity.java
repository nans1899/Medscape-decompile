package com.medscape.android.activity.calc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.helper.CalcOmnitureHelper;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.QxSearchEventsCallback;
import com.wbmd.qxcalculator.activities.homescreen.SearchActivity;
import com.wbmd.qxcalculator.fragments.homescreen.SearchFragment;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0012\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001c\u0010\u0019\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u001c\u001a\u00020\fH\u0016J\b\u0010\u001d\u001a\u00020\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/medscape/android/activity/calc/SearchCalcActivity;", "Lcom/wbmd/qxcalculator/activities/homescreen/SearchActivity;", "Lcom/wbmd/qxcalculator/QxSearchEventsCallback;", "Lcom/wbmd/qxcalculator/LaunchQxCallback;", "()V", "isSearchEmpty", "", "mPvid", "", "mSearchView", "Landroidx/appcompat/widget/SearchView;", "onClickSearchButton", "", "onClickSearchResult", "contentItem", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "position", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onPrepareOptionsMenu", "onQxItemClicked", "dbContentItem", "bundle", "onSearchResultsLoaded", "setupActionbar", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SearchCalcActivity.kt */
public final class SearchCalcActivity extends SearchActivity implements QxSearchEventsCallback, LaunchQxCallback {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public boolean isSearchEmpty = true;
    private String mPvid = "";
    /* access modifiers changed from: private */
    public SearchView mSearchView;

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

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SearchFragment.newInstance(this, this);
        setupActionbar();
    }

    private final void setupActionbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayShowCustomEnabled(true);
        }
        Integer num = null;
        View inflate = LayoutInflater.from(this).inflate(R.layout.new_search_actionbar, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.back_arrow);
        SearchView searchView = (SearchView) inflate.findViewById(R.id.search_view);
        this.mSearchView = searchView;
        if (searchView != null) {
            searchView.setIconifiedByDefault(false);
        }
        SearchView searchView2 = this.mSearchView;
        if (searchView2 != null) {
            searchView2.setIconified(true);
        }
        SearchView searchView3 = this.mSearchView;
        EditText editText = searchView3 != null ? (EditText) searchView3.findViewById(R.id.search_src_text) : null;
        if (editText != null) {
            editText.setTextColor(getResources().getColor(R.color.title_color));
            editText.setHintTextColor(getResources().getColor(R.color.material_grey));
            editText.setHint(getResources().getString(R.string.search_calculators_view));
            editText.setOnTouchListener(new SearchCalcActivity$setupActionbar$1(this));
            SearchView searchView4 = this.mSearchView;
            ImageView imageView2 = searchView4 != null ? (ImageView) searchView4.findViewById(R.id.search_close_btn) : null;
            if (imageView2 != null) {
                imageView2.setImageResource(R.drawable.ic_close_blue_24dp);
                imageView2.setOnClickListener(new SearchCalcActivity$setupActionbar$2(this));
                if (this.searchString != null) {
                    String str = this.searchString;
                    Intrinsics.checkNotNullExpressionValue(str, "searchString");
                    if (str.length() > 0) {
                        SearchView searchView5 = this.mSearchView;
                        if (searchView5 != null) {
                            searchView5.setQuery(this.searchString, false);
                        }
                        SearchView searchView6 = this.mSearchView;
                        if (searchView6 != null) {
                            searchView6.clearFocus();
                        }
                    }
                }
                ImageView imageView3 = (ImageView) inflate.findViewById(R.id.search_icon);
                try {
                    Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
                    Intrinsics.checkNotNullExpressionValue(declaredField, "mCursorDrawableRes");
                    declaredField.setAccessible(true);
                    declaredField.set(editText, Integer.valueOf(R.drawable.new_search_cursor));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SearchView searchView7 = this.mSearchView;
                if (searchView7 != null) {
                    searchView7.setQueryHint(getResources().getString(R.string.search_calculators_view));
                }
                SearchView searchView8 = this.mSearchView;
                if (searchView8 != null) {
                    num = Integer.valueOf(searchView8.getImeOptions());
                }
                SearchView searchView9 = this.mSearchView;
                if (searchView9 != null) {
                    Intrinsics.checkNotNull(num);
                    searchView9.setImeOptions(num.intValue() | 268435456);
                }
                SearchView searchView10 = this.mSearchView;
                if (searchView10 != null) {
                    searchView10.setOnQueryTextListener(new SearchCalcActivity$setupActionbar$3(this, imageView3));
                }
                imageView.setOnClickListener(new SearchCalcActivity$setupActionbar$4(this));
                ActionBar supportActionBar3 = getSupportActionBar();
                if (supportActionBar3 != null) {
                    supportActionBar3.setCustomView(inflate);
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.EditText");
    }

    public void onClickSearchResult(DBContentItem dBContentItem, int i) {
        Intrinsics.checkNotNullParameter(dBContentItem, "contentItem");
        String calcPageNameForOmniture = Util.getCalcPageNameForOmniture(this, dBContentItem);
        Intrinsics.checkNotNullExpressionValue(calcPageNameForOmniture, "Util.getCalcPageNameForOmniture(this, contentItem)");
        this.mPvid = CalcOmnitureHelper.INSTANCE.sendOmnitureCall(this, "srch-results", "" + i, calcPageNameForOmniture);
    }

    public void onClickSearchButton() {
        OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "srch-btn", "ref", (Map<String, Object>) null);
        CalcOmnitureHelper.INSTANCE.sendOmnitureCall(this, "search-btn", "ref", "calc/search/results");
    }

    public void onSearchResultsLoaded() {
        CalcOmnitureHelper.INSTANCE.sendOmnitureCall(this, "search-type", "ref", "calc/browse-search/view");
    }

    public void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle) {
        if (bundle != null) {
            bundle.putString("pvid", this.mPvid);
        }
        Util.openQxItem(this, dBContentItem, bundle);
    }
}
