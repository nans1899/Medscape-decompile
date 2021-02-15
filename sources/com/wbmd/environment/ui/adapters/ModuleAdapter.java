package com.wbmd.environment.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wbmd.environment.data.Module;
import com.wbmd.environment.databinding.RowEnvironmentBinding;
import com.wbmd.environment.interfaces.OnModuleListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B%\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/wbmd/environment/ui/adapters/ModuleAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/wbmd/environment/ui/adapters/ModuleAdapter$ModuleHolder;", "items", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Module;", "Lkotlin/collections/ArrayList;", "listener", "Lcom/wbmd/environment/interfaces/OnModuleListener;", "(Ljava/util/ArrayList;Lcom/wbmd/environment/interfaces/OnModuleListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ModuleHolder", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ModuleAdapter.kt */
public final class ModuleAdapter extends RecyclerView.Adapter<ModuleHolder> {
    private ArrayList<Module> items;
    /* access modifiers changed from: private */
    public OnModuleListener listener;

    public ModuleAdapter(ArrayList<Module> arrayList, OnModuleListener onModuleListener) {
        Intrinsics.checkNotNullParameter(arrayList, FirebaseAnalytics.Param.ITEMS);
        Intrinsics.checkNotNullParameter(onModuleListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.items = arrayList;
        this.listener = onModuleListener;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/wbmd/environment/ui/adapters/ModuleAdapter$ModuleHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/wbmd/environment/databinding/RowEnvironmentBinding;", "(Lcom/wbmd/environment/ui/adapters/ModuleAdapter;Lcom/wbmd/environment/databinding/RowEnvironmentBinding;)V", "getBinding", "()Lcom/wbmd/environment/databinding/RowEnvironmentBinding;", "setBinding", "(Lcom/wbmd/environment/databinding/RowEnvironmentBinding;)V", "bindData", "", "module", "Lcom/wbmd/environment/data/Module;", "wbmdenvironment_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ModuleAdapter.kt */
    public final class ModuleHolder extends RecyclerView.ViewHolder {
        private RowEnvironmentBinding binding;
        final /* synthetic */ ModuleAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ModuleHolder(ModuleAdapter moduleAdapter, RowEnvironmentBinding rowEnvironmentBinding) {
            super(rowEnvironmentBinding.getRoot());
            Intrinsics.checkNotNullParameter(rowEnvironmentBinding, "binding");
            this.this$0 = moduleAdapter;
            this.binding = rowEnvironmentBinding;
        }

        public final RowEnvironmentBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(RowEnvironmentBinding rowEnvironmentBinding) {
            Intrinsics.checkNotNullParameter(rowEnvironmentBinding, "<set-?>");
            this.binding = rowEnvironmentBinding;
        }

        public final void bindData(Module module) {
            Intrinsics.checkNotNullParameter(module, "module");
            this.binding.setModule(module);
            this.binding.setListener(this.this$0.listener);
        }
    }

    public ModuleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        RowEnvironmentBinding inflate = RowEnvironmentBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "RowEnvironmentBinding.in…tInflater, parent, false)");
        return new ModuleHolder(this, inflate);
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void onBindViewHolder(ModuleHolder moduleHolder, int i) {
        Intrinsics.checkNotNullParameter(moduleHolder, "holder");
        Module module = this.items.get(i);
        Intrinsics.checkNotNullExpressionValue(module, "items[position]");
        moduleHolder.bindData(module);
    }
}
