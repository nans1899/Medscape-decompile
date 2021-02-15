package com.medscape.android.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.actions.SearchIntents;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/medscape/android/notifications/VoiceCommandStartActivity;", "Lcom/medscape/android/base/BaseActivity;", "()V", "voiceQuery", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: VoiceCommandStartActivity.kt */
public final class VoiceCommandStartActivity extends BaseActivity {
    private HashMap _$_findViewCache;
    private String voiceQuery = "";

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

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.loadin_layout);
        if (getIntent() != null) {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            if (intent.getAction() != null) {
                Intent intent2 = getIntent();
                Intrinsics.checkNotNullExpressionValue(intent2, "intent");
                if (Intrinsics.areEqual((Object) intent2.getAction(), (Object) SearchIntents.ACTION_SEARCH)) {
                    if (getIntent().getStringExtra(SearchIntents.EXTRA_QUERY) != null) {
                        String stringExtra = getIntent().getStringExtra(SearchIntents.EXTRA_QUERY);
                        Intrinsics.checkNotNull(stringExtra);
                        this.voiceQuery = stringExtra;
                    }
                    Intent intent3 = new Intent(this, NotificationAuthenticationGateActivity.class);
                    intent3.putExtra("voice_query", this.voiceQuery);
                    startActivity(intent3);
                    finish();
                }
            }
        }
    }
}
