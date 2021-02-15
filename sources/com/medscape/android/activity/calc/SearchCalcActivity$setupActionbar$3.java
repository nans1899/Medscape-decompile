package com.medscape.android.activity.calc;

import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.actions.SearchIntents;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.wbmd.qxcalculator.fragments.common.QxMDFragment;
import com.wbmd.qxcalculator.fragments.homescreen.SearchFragment;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"com/medscape/android/activity/calc/SearchCalcActivity$setupActionbar$3", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "onQueryTextChange", "", "newText", "", "onQueryTextSubmit", "query", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SearchCalcActivity.kt */
public final class SearchCalcActivity$setupActionbar$3 implements SearchView.OnQueryTextListener {
    final /* synthetic */ ImageView $searchIcon;
    final /* synthetic */ SearchCalcActivity this$0;

    SearchCalcActivity$setupActionbar$3(SearchCalcActivity searchCalcActivity, ImageView imageView) {
        this.this$0 = searchCalcActivity;
        this.$searchIcon = imageView;
    }

    public boolean onQueryTextSubmit(String str) {
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
        SearchView access$getMSearchView$p = this.this$0.mSearchView;
        if (access$getMSearchView$p != null) {
            access$getMSearchView$p.clearFocus();
        }
        Fragment defaultFragment = this.this$0.getDefaultFragment();
        if (defaultFragment == null || !(defaultFragment instanceof SearchFragment) || SearchFragment.callback == null) {
            return true;
        }
        SearchFragment.callback.onClickSearchButton();
        return true;
    }

    public boolean onQueryTextChange(String str) {
        Intrinsics.checkNotNullParameter(str, "newText");
        if (str.length() == 0) {
            ImageView imageView = this.$searchIcon;
            Intrinsics.checkNotNullExpressionValue(imageView, "searchIcon");
            imageView.setVisibility(0);
            this.this$0.isSearchEmpty = true;
        } else {
            ImageView imageView2 = this.$searchIcon;
            Intrinsics.checkNotNullExpressionValue(imageView2, "searchIcon");
            imageView2.setVisibility(8);
        }
        this.this$0.searchString = str;
        if (str.length() == 1 && this.this$0.isSearchEmpty) {
            OmnitureManager.get().trackModule(this.this$0, Constants.OMNITURE_CHANNEL_REFERENCE, "search-type", "ref", (Map<String, Object>) null);
            this.this$0.isSearchEmpty = false;
        }
        Fragment defaultFragment = this.this$0.getDefaultFragment();
        if (defaultFragment != null && (defaultFragment instanceof QxMDFragment)) {
            ((QxMDFragment) defaultFragment).toolbarSearchEnterred(str);
        }
        return false;
    }
}
