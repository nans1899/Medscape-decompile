package com.medscape.android.activity.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.medscape.android.R;
import com.medscape.android.activity.saved.viewmodel.QxSaveViewModel;
import com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity;
import com.wbmd.qxcalculator.util.Log;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/activity/calc/MedscapeFileSourceHtmlActivity;", "Lcom/wbmd/qxcalculator/activities/contentItems/FileSourceHtmlActivity;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "qxSaveViewModel", "Lcom/medscape/android/activity/saved/viewmodel/QxSaveViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onResume", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeFileSourceHtmlActivity.kt */
public class MedscapeFileSourceHtmlActivity extends FileSourceHtmlActivity {
    private final String TAG = "FileSourceActivity";
    private HashMap _$_findViewCache;
    private QxSaveViewModel qxSaveViewModel;

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

    public final String getTAG() {
        return this.TAG;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.qxSaveViewModel = (QxSaveViewModel) ViewModelProviders.of((FragmentActivity) this).get(new QxSaveViewModel().getClass());
    }

    public void onResume() {
        super.onResume();
        QxSaveViewModel qxSaveViewModel2 = this.qxSaveViewModel;
        if (qxSaveViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        qxSaveViewModel2.saveToRecentlyViewed$medscape_release(this, this.contentItem);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = null;
        MenuItem findItem = menu != null ? menu.findItem(R.id.action_fave) : null;
        if (menu != null) {
            menuItem = menu.findItem(R.id.action_share);
        }
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
        if (findItem == null) {
            return true;
        }
        QxSaveViewModel qxSaveViewModel2 = this.qxSaveViewModel;
        if (qxSaveViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        qxSaveViewModel2.changeSaveIcon$medscape_release(this, this.contentItem, findItem);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        int itemId = menuItem.getItemId();
        Log.d("Calc", "Id : " + itemId);
        if (itemId != R.id.action_fave) {
            return super.onOptionsItemSelected(menuItem);
        }
        QxSaveViewModel qxSaveViewModel2 = this.qxSaveViewModel;
        if (qxSaveViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        Activity activity = this;
        qxSaveViewModel2.onOptionsItemSelected$medscape_release(activity, this.contentItem);
        QxSaveViewModel qxSaveViewModel3 = this.qxSaveViewModel;
        if (qxSaveViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        qxSaveViewModel3.changeSaveIcon$medscape_release(activity, this.contentItem, menuItem);
        return true;
    }
}
