package com.wbmd.decisionpoint.ui.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.databinding.ActivityHubBinding;
import com.wbmd.decisionpoint.domain.interfaces.HubListener;
import com.wbmd.decisionpoint.ui.fragments.HubFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/wbmd/decisionpoint/ui/activities/HubActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;", "()V", "binding", "Lcom/wbmd/decisionpoint/databinding/ActivityHubBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onExploreClick", "url", "", "title", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HubActivity.kt */
public class HubActivity extends AppCompatActivity implements HubListener {
    private HashMap _$_findViewCache;
    private ActivityHubBinding binding;

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
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_hub);
        Intrinsics.checkNotNullExpressionValue(contentView, "DataBindingUtil.setConte…s, R.layout.activity_hub)");
        this.binding = (ActivityHubBinding) contentView;
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, (Fragment) HubFragment.Companion.newInstance()).commit();
    }

    public void onExploreClick(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "title");
        startActivity(WebViewActivity.Companion.newIntent(this, str, str2));
    }
}
