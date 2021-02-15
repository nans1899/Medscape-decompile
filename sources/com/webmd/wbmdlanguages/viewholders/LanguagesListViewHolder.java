package com.webmd.wbmdlanguages.viewholders;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.wbmdlanguages.models.LanguageModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\f"}, d2 = {"Lcom/webmd/wbmdlanguages/viewholders/LanguagesListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "(Landroidx/databinding/ViewDataBinding;)V", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "bind", "", "data", "Lcom/webmd/wbmdlanguages/models/LanguageModel;", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LanguagesListViewHolder.kt */
public final class LanguagesListViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LanguagesListViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        Intrinsics.checkNotNullParameter(viewDataBinding, "binding");
        this.binding = viewDataBinding;
    }

    public final ViewDataBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(ViewDataBinding viewDataBinding) {
        Intrinsics.checkNotNullParameter(viewDataBinding, "<set-?>");
        this.binding = viewDataBinding;
    }

    public final void bind(LanguageModel languageModel) {
        Intrinsics.checkNotNullParameter(languageModel, "data");
        this.binding.setVariable(BR.language_model, languageModel);
    }
}
