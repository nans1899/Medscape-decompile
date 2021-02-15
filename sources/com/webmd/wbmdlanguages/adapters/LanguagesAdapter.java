package com.webmd.wbmdlanguages.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.webmd.wbmdlanguages.R;
import com.webmd.wbmdlanguages.models.LanguageModel;
import com.webmd.wbmdlanguages.viewholders.LanguagesListViewHolder;
import com.webmd.wbmdlanguages.viewmodels.LanguageSupportViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u001dH\u0016J\u0018\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001dH\u0016J\u0014\u0010&\u001a\u00020\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lcom/webmd/wbmdlanguages/adapters/LanguagesAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/webmd/wbmdlanguages/viewholders/LanguagesListViewHolder;", "context", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "getContext", "()Landroidx/fragment/app/FragmentActivity;", "setContext", "inflater", "Landroid/view/LayoutInflater;", "getInflater", "()Landroid/view/LayoutInflater;", "setInflater", "(Landroid/view/LayoutInflater;)V", "list", "", "Lcom/webmd/wbmdlanguages/models/LanguageModel;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "viewModel", "Lcom/webmd/wbmdlanguages/viewmodels/LanguageSupportViewModel;", "getViewModel", "()Lcom/webmd/wbmdlanguages/viewmodels/LanguageSupportViewModel;", "setViewModel", "(Lcom/webmd/wbmdlanguages/viewmodels/LanguageSupportViewModel;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "items", "wbmdlanguages_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LanguagesAdapter.kt */
public final class LanguagesAdapter extends RecyclerView.Adapter<LanguagesListViewHolder> {
    private FragmentActivity context;
    private LayoutInflater inflater;
    private List<LanguageModel> list = new ArrayList();
    private LanguageSupportViewModel viewModel;

    public final FragmentActivity getContext() {
        return this.context;
    }

    public final void setContext(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<set-?>");
        this.context = fragmentActivity;
    }

    public LanguagesAdapter(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        this.context = fragmentActivity;
        LayoutInflater from = LayoutInflater.from(fragmentActivity);
        Intrinsics.checkNotNullExpressionValue(from, "LayoutInflater.from(context)");
        this.inflater = from;
        FragmentActivity fragmentActivity2 = this.context;
        LanguageSupportViewModel languageSupportViewModel = fragmentActivity2 != null ? (LanguageSupportViewModel) ViewModelProviders.of(fragmentActivity2).get(LanguageSupportViewModel.class) : null;
        Intrinsics.checkNotNullExpressionValue(languageSupportViewModel, "context?.let { ViewModel…tViewModel::class.java) }");
        this.viewModel = languageSupportViewModel;
    }

    public final LayoutInflater getInflater() {
        return this.inflater;
    }

    public final void setInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkNotNullParameter(layoutInflater, "<set-?>");
        this.inflater = layoutInflater;
    }

    public final List<LanguageModel> getList() {
        return this.list;
    }

    public final void setList(List<LanguageModel> list2) {
        Intrinsics.checkNotNullParameter(list2, "<set-?>");
        this.list = list2;
    }

    public final LanguageSupportViewModel getViewModel() {
        return this.viewModel;
    }

    public final void setViewModel(LanguageSupportViewModel languageSupportViewModel) {
        Intrinsics.checkNotNullParameter(languageSupportViewModel, "<set-?>");
        this.viewModel = languageSupportViewModel;
    }

    public LanguagesListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.language_list_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "binding");
        return new LanguagesListViewHolder(inflate);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void onBindViewHolder(LanguagesListViewHolder languagesListViewHolder, int i) {
        Intrinsics.checkNotNullParameter(languagesListViewHolder, "holder");
        languagesListViewHolder.bind(this.list.get(i));
        languagesListViewHolder.itemView.setOnClickListener(new LanguagesAdapter$onBindViewHolder$1(this, i));
    }

    public final void setData(List<LanguageModel> list2) {
        Intrinsics.checkNotNullParameter(list2, FirebaseAnalytics.Param.ITEMS);
        this.list.clear();
        this.list.addAll(list2);
        notifyDataSetChanged();
    }
}
