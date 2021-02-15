package com.medscape.android.update;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.view.CustomFontTextView;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/medscape/android/update/BackgroundUpdateProgressActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BackgroundUpdateProgressActivity.kt */
public final class BackgroundUpdateProgressActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_update_details);
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setTitle((CharSequence) "Practice Update");
        }
        String stringExtra = getIntent().getStringExtra(Constants.PREF_UPDATE_TITLE);
        String stringExtra2 = getIntent().getStringExtra(Constants.PREF_UPDATE_MESSAGE);
        CustomFontTextView customFontTextView = (CustomFontTextView) _$_findCachedViewById(R.id.title_view);
        Intrinsics.checkNotNullExpressionValue(customFontTextView, "title_view");
        customFontTextView.setText(stringExtra);
        CustomFontTextView customFontTextView2 = (CustomFontTextView) _$_findCachedViewById(R.id.description_view);
        Intrinsics.checkNotNullExpressionValue(customFontTextView2, "description_view");
        customFontTextView2.setText(stringExtra2);
    }

    public void onBackPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
