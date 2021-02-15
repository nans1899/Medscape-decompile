package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.OnFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.databinding.QuickFilterItemView2Binding;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.QuickFilterViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/adapters/QuickFiltersView2RecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/QuickFilterViewHolder;", "context", "Landroid/content/Context;", "listener", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "(Landroid/content/Context;Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;)V", "items", "", "", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setStyle", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuickFiltersView2RecyclerViewAdapter.kt */
public final class QuickFiltersView2RecyclerViewAdapter extends RecyclerView.Adapter<QuickFilterViewHolder> {
    private final List<String> items;
    /* access modifiers changed from: private */
    public OnFilterSelectedListener listener;
    private StyleManager styleManager;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ QuickFiltersView2RecyclerViewAdapter(Context context, OnFilterSelectedListener onFilterSelectedListener, StyleManager styleManager2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, onFilterSelectedListener, (i & 4) != 0 ? new StyleManager(false, 1, (DefaultConstructorMarker) null) : styleManager2);
    }

    public QuickFiltersView2RecyclerViewAdapter(Context context, OnFilterSelectedListener onFilterSelectedListener, StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleManager2, "styleManager");
        this.listener = onFilterSelectedListener;
        this.styleManager = styleManager2;
        this.items = CollectionsKt.listOf(context.getString(R.string.specialty_filter_title), context.getString(R.string.date_range_filter_title), context.getString(R.string.location_filter_title));
    }

    public QuickFilterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        QuickFilterItemView2Binding inflate = QuickFilterItemView2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "QuickFilterItemView2Bind…tInflater, parent, false)");
        return new QuickFilterViewHolder(inflate);
    }

    public final void setStyle(StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(styleManager2, "styleManager");
        this.styleManager = styleManager2;
    }

    public void onBindViewHolder(QuickFilterViewHolder quickFilterViewHolder, int i) {
        Intrinsics.checkNotNullParameter(quickFilterViewHolder, "holder");
        String str = this.items.get(i);
        Intrinsics.checkNotNullExpressionValue(str, "items[position]");
        quickFilterViewHolder.getBinding().setFilterName(str);
        quickFilterViewHolder.getBinding().setStyleManager(this.styleManager);
        quickFilterViewHolder.getBinding().getRoot().setOnClickListener(new QuickFiltersView2RecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1(this, quickFilterViewHolder, i));
    }

    public int getItemCount() {
        return this.items.size();
    }
}
