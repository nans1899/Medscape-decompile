package com.wbmd.environment.ui.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.environment.R;
import com.wbmd.environment.data.Module;
import com.wbmd.environment.ui.adapters.ModuleAdapter;
import com.wbmd.environment.ui.fragments.EnvironmentDialogFragment;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0007H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/wbmd/environment/ui/activities/EnvironmentActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/wbmd/environment/ui/adapters/ModuleAdapter;", "items", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Module;", "Lkotlin/collections/ArrayList;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showEnvironmentPickerDialog", "module", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentActivity.kt */
public final class EnvironmentActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public ModuleAdapter adapter;
    private ArrayList<Module> items = new EnvironmentManager().getModules();

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
        setContentView(R.layout.activity_environment);
        ArrayList<Module> arrayList = this.items;
        if (arrayList != null) {
            this.adapter = new ModuleAdapter(arrayList, new EnvironmentActivity$onCreate$$inlined$let$lambda$1(this));
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recycler_view);
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recycler_view");
            recyclerView.setAdapter(this.adapter);
        }
    }

    /* access modifiers changed from: private */
    public final void showEnvironmentPickerDialog(Module module) {
        EnvironmentDialogFragment newInstance = EnvironmentDialogFragment.Companion.newInstance(module.getEnvironments(), module.getId());
        newInstance.setListener(new EnvironmentActivity$showEnvironmentPickerDialog$1(this));
        newInstance.show(getSupportFragmentManager(), EnvironmentDialogFragment.TAG);
    }
}
