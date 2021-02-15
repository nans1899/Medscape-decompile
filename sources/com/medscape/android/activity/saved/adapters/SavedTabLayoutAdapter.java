package com.medscape.android.activity.saved.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.medscape.android.R;
import com.medscape.android.activity.saved.model.TabLayoutElement;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002'(B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u001c\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\"\u001a\u00020\u001eH\u0016J\u001c\u0010#\u001a\u00060\u0002R\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001eH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006)"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$ViewHolder;", "context", "Landroid/content/Context;", "tabItems", "", "Lcom/medscape/android/activity/saved/model/TabLayoutElement;", "listener", "Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;", "(Landroid/content/Context;Ljava/util/List;Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "inflater", "Landroid/view/LayoutInflater;", "getInflater", "()Landroid/view/LayoutInflater;", "setInflater", "(Landroid/view/LayoutInflater;)V", "getListener", "()Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;", "setListener", "(Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;)V", "getTabItems", "()Ljava/util/List;", "setTabItems", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "OnTabClicked", "ViewHolder", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SavedTabLayoutAdapter.kt */
public final class SavedTabLayoutAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private OnTabClicked listener;
    private List<TabLayoutElement> tabItems;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;", "", "onTabClicked", "", "position", "", "selectedElement", "Lcom/medscape/android/activity/saved/model/TabLayoutElement;", "fromTab", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SavedTabLayoutAdapter.kt */
    public interface OnTabClicked {
        void onTabClicked(int i, TabLayoutElement tabLayoutElement, boolean z);
    }

    public SavedTabLayoutAdapter(Context context2, List<TabLayoutElement> list, OnTabClicked onTabClicked) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(list, "tabItems");
        Intrinsics.checkNotNullParameter(onTabClicked, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.context = context2;
        this.tabItems = list;
        this.listener = onTabClicked;
        LayoutInflater from = LayoutInflater.from(context2);
        Intrinsics.checkNotNullExpressionValue(from, "LayoutInflater.from(context)");
        this.inflater = from;
    }

    public final Context getContext() {
        return this.context;
    }

    public final OnTabClicked getListener() {
        return this.listener;
    }

    public final List<TabLayoutElement> getTabItems() {
        return this.tabItems;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final void setListener(OnTabClicked onTabClicked) {
        Intrinsics.checkNotNullParameter(onTabClicked, "<set-?>");
        this.listener = onTabClicked;
    }

    public final void setTabItems(List<TabLayoutElement> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.tabItems = list;
    }

    public final LayoutInflater getInflater() {
        return this.inflater;
    }

    public final void setInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkNotNullParameter(layoutInflater, "<set-?>");
        this.inflater = layoutInflater;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = this.inflater.inflate(R.layout.tab_layout_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "view");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        TabLayoutElement tabLayoutElement = this.tabItems.get(i);
        viewHolder.getTabName().setText(tabLayoutElement.getName());
        if (tabLayoutElement.isSelected()) {
            viewHolder.getSelection().setVisibility(0);
        } else {
            viewHolder.getSelection().setVisibility(8);
        }
        viewHolder.itemView.setOnClickListener(new SavedTabLayoutAdapter$onBindViewHolder$1(this, i, tabLayoutElement));
    }

    public int getItemCount() {
        return this.tabItems.size();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter;Landroid/view/View;)V", "selection", "getSelection", "()Landroid/view/View;", "tabName", "Landroid/widget/TextView;", "getTabName", "()Landroid/widget/TextView;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SavedTabLayoutAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final View selection;
        private final TextView tabName;
        final /* synthetic */ SavedTabLayoutAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(SavedTabLayoutAdapter savedTabLayoutAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            this.this$0 = savedTabLayoutAdapter;
            View findViewById = view.findViewById(R.id.tab_name);
            if (findViewById != null) {
                this.tabName = (TextView) findViewById;
                View findViewById2 = view.findViewById(R.id.view_selected);
                if (findViewById2 != null) {
                    this.selection = findViewById2;
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }

        public final TextView getTabName() {
            return this.tabName;
        }

        public final View getSelection() {
            return this.selection;
        }
    }
}
