package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.OnFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.databinding.ButtonItemBinding;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.ButtonViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0018H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR4\u0010\u000e\u001a&\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\t0\t \u0010*\u0012\u0012\u000e\b\u0001\u0012\n \u0010*\u0004\u0018\u00010\t0\t0\u000f0\u000fX\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/adapters/FilterButtonRecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/ButtonViewHolder;", "context", "Landroid/content/Context;", "listener", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;", "(Landroid/content/Context;Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;)V", "allUpcomingFilter", "", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "listOfButtons", "", "kotlin.jvm.PlatformType", "[Ljava/lang/String;", "nextWeekFilter", "thisWeekFilter", "thisWeekendFilter", "todayFilter", "tomorrowFilter", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FilterButtonRecyclerViewAdapter.kt */
public final class FilterButtonRecyclerViewAdapter extends RecyclerView.Adapter<ButtonViewHolder> {
    /* access modifiers changed from: private */
    public final String allUpcomingFilter;
    private Context context;
    private String[] listOfButtons;
    /* access modifiers changed from: private */
    public OnFilterSelectedListener listener;
    /* access modifiers changed from: private */
    public final String nextWeekFilter;
    /* access modifiers changed from: private */
    public final String thisWeekFilter;
    /* access modifiers changed from: private */
    public final String thisWeekendFilter;
    /* access modifiers changed from: private */
    public final String todayFilter;
    /* access modifiers changed from: private */
    public final String tomorrowFilter;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public FilterButtonRecyclerViewAdapter(Context context2, OnFilterSelectedListener onFilterSelectedListener) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.listener = onFilterSelectedListener;
        String[] stringArray = context2.getResources().getStringArray(R.array.quick_filters);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ay(R.array.quick_filters)");
        this.listOfButtons = stringArray;
        String string = this.context.getString(R.string.today_filter);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.today_filter)");
        this.todayFilter = string;
        String string2 = this.context.getString(R.string.tomorrow_filter);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.tomorrow_filter)");
        this.tomorrowFilter = string2;
        String string3 = this.context.getString(R.string.this_week_filter);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.this_week_filter)");
        this.thisWeekFilter = string3;
        String string4 = this.context.getString(R.string.this_weekend_filter);
        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.this_weekend_filter)");
        this.thisWeekendFilter = string4;
        String string5 = this.context.getString(R.string.next_week_filter);
        Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.next_week_filter)");
        this.nextWeekFilter = string5;
        String string6 = this.context.getString(R.string.all_upcoming_filter);
        Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.all_upcoming_filter)");
        this.allUpcomingFilter = string6;
    }

    public ButtonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ButtonItemBinding inflate = ButtonItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "ButtonItemBinding.inflat…tInflater, parent, false)");
        return new ButtonViewHolder(inflate);
    }

    public int getItemCount() {
        return this.listOfButtons.length;
    }

    public void onBindViewHolder(ButtonViewHolder buttonViewHolder, int i) {
        Intrinsics.checkNotNullParameter(buttonViewHolder, "holder");
        String str = this.listOfButtons[i];
        buttonViewHolder.getBinding().setButton(str);
        buttonViewHolder.getBinding().tvButton.setOnClickListener(new FilterButtonRecyclerViewAdapter$onBindViewHolder$1(this, str));
    }
}
