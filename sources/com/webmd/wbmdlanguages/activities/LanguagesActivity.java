package com.webmd.wbmdlanguages.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.webmd.wbmdlanguages.R;
import com.webmd.wbmdlanguages.fragments.LanguagesFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/webmd/wbmdlanguages/activities/LanguagesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "languagesFragment", "Lcom/webmd/wbmdlanguages/fragments/LanguagesFragment;", "getLanguagesFragment", "()Lcom/webmd/wbmdlanguages/fragments/LanguagesFragment;", "setLanguagesFragment", "(Lcom/webmd/wbmdlanguages/fragments/LanguagesFragment;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LanguagesActivity.kt */
public final class LanguagesActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    public LanguagesFragment languagesFragment;

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

    public final LanguagesFragment getLanguagesFragment() {
        LanguagesFragment languagesFragment2 = this.languagesFragment;
        if (languagesFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languagesFragment");
        }
        return languagesFragment2;
    }

    public final void setLanguagesFragment(LanguagesFragment languagesFragment2) {
        Intrinsics.checkNotNullParameter(languagesFragment2, "<set-?>");
        this.languagesFragment = languagesFragment2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_languages);
        this.languagesFragment = LanguagesFragment.Companion.newInstance();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        int i = R.id.rss_container;
        LanguagesFragment languagesFragment2 = this.languagesFragment;
        if (languagesFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languagesFragment");
        }
        beginTransaction.replace(i, languagesFragment2);
        beginTransaction.commit();
    }
}
