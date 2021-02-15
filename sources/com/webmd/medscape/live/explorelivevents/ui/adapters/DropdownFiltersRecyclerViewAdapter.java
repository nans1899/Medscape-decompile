package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.OnDropdownFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.OnFilterResetListener;
import com.webmd.medscape.live.explorelivevents.data.DropdownFilterButton;
import com.webmd.medscape.live.explorelivevents.data.FilterState;
import com.webmd.medscape.live.explorelivevents.databinding.DropdownFilterItemBinding;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.DropdownFilterViewHolder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0002H\u0002J\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dJ \u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0018\u0010 \u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001dH\u0016J\u0018\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J \u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0002H\u0002J\u0014\u0010*\u001a\u00020\u00162\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070+J\u0016\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020/R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/adapters/DropdownFiltersRecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/DropdownFilterViewHolder;", "context", "Landroid/content/Context;", "filters", "", "Lcom/webmd/medscape/live/explorelivevents/data/DropdownFilterButton;", "listener", "Lcom/webmd/medscape/live/explorelivevents/common/OnDropdownFilterSelectedListener;", "resetListener", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterResetListener;", "(Landroid/content/Context;Ljava/util/List;Lcom/webmd/medscape/live/explorelivevents/common/OnDropdownFilterSelectedListener;Lcom/webmd/medscape/live/explorelivevents/common/OnFilterResetListener;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getFilters", "()Ljava/util/List;", "setFilters", "(Ljava/util/List;)V", "disableFilter", "", "filterButton", "Landroid/widget/Button;", "filter", "holder", "disableFilterAtPosition", "position", "", "enableFilter", "getItemCount", "onBindViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "resetFilter", "filterType", "setFilterState", "state", "Lcom/webmd/medscape/live/explorelivevents/data/FilterState;", "setFiltersData", "", "updateFilters", "type", "text", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DropdownFiltersRecyclerViewAdapter.kt */
public final class DropdownFiltersRecyclerViewAdapter extends RecyclerView.Adapter<DropdownFilterViewHolder> {
    private Context context;
    private List<DropdownFilterButton> filters;
    private OnDropdownFilterSelectedListener listener;
    private OnFilterResetListener resetListener;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FilterState.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[FilterState.ON.ordinal()] = 1;
            $EnumSwitchMapping$0[FilterState.OFF.ordinal()] = 2;
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DropdownFiltersRecyclerViewAdapter(Context context2, List list, OnDropdownFilterSelectedListener onDropdownFilterSelectedListener, OnFilterResetListener onFilterResetListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? new ArrayList() : list, onDropdownFilterSelectedListener, onFilterResetListener);
    }

    public final List<DropdownFilterButton> getFilters() {
        return this.filters;
    }

    public final void setFilters(List<DropdownFilterButton> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.filters = list;
    }

    public DropdownFiltersRecyclerViewAdapter(Context context2, List<DropdownFilterButton> list, OnDropdownFilterSelectedListener onDropdownFilterSelectedListener, OnFilterResetListener onFilterResetListener) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(list, ShareConstants.WEB_DIALOG_PARAM_FILTERS);
        Intrinsics.checkNotNullParameter(onDropdownFilterSelectedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(onFilterResetListener, "resetListener");
        this.context = context2;
        this.filters = list;
        this.listener = onDropdownFilterSelectedListener;
        this.resetListener = onFilterResetListener;
    }

    public DropdownFilterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        DropdownFilterItemBinding inflate = DropdownFilterItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "DropdownFilterItemBindin…tInflater, parent, false)");
        return new DropdownFilterViewHolder(inflate);
    }

    public int getItemCount() {
        return this.filters.size();
    }

    public void onBindViewHolder(DropdownFilterViewHolder dropdownFilterViewHolder, int i) {
        Intrinsics.checkNotNullParameter(dropdownFilterViewHolder, "holder");
        DropdownFilterButton dropdownFilterButton = this.filters.get(i);
        dropdownFilterViewHolder.getBinding().setFilter(dropdownFilterButton);
        Button button = dropdownFilterViewHolder.getBinding().tbFilter;
        Intrinsics.checkNotNullExpressionValue(button, "holder.binding.tbFilter");
        if (dropdownFilterButton.isEnabled()) {
            setFilterState(FilterState.ON, button, dropdownFilterViewHolder);
        }
        if (dropdownFilterButton.getCanceled()) {
            setFilterState(FilterState.OFF, button, dropdownFilterViewHolder);
        }
        button.setOnClickListener(new DropdownFiltersRecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1(dropdownFilterButton, button, this, dropdownFilterViewHolder, dropdownFilterButton));
    }

    private final void setFilterState(FilterState filterState, Button button, DropdownFilterViewHolder dropdownFilterViewHolder) {
        int i = WhenMappings.$EnumSwitchMapping$0[filterState.ordinal()];
        if (i == 1) {
            button.setBackgroundResource(R.drawable.dropdown_filter_selected);
            button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_selected, 0);
            View root = dropdownFilterViewHolder.getBinding().getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "holder.binding.root");
            button.setTextColor(ContextCompat.getColor(root.getContext(), R.color.white));
        } else if (i == 2) {
            button.setBackgroundResource(R.drawable.dropdown_filter_neutral);
            button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_neutral, 0);
            View root2 = dropdownFilterViewHolder.getBinding().getRoot();
            Intrinsics.checkNotNullExpressionValue(root2, "holder.binding.root");
            button.setTextColor(ContextCompat.getColor(root2.getContext(), R.color.bandicoot));
        }
    }

    /* access modifiers changed from: private */
    public final void enableFilter(Button button, DropdownFilterViewHolder dropdownFilterViewHolder, DropdownFilterButton dropdownFilterButton) {
        setFilterState(FilterState.ON, button, dropdownFilterViewHolder);
        dropdownFilterButton.setEnabled(true);
        int type = dropdownFilterButton.getType();
        if (type == 0) {
            this.listener.onDateSelected();
        } else if (type == 1) {
            this.listener.onLocationSelected();
        } else if (type == 2) {
            this.listener.onSpecialtySelected();
        }
    }

    private final void disableFilter(Context context2, Button button, DropdownFilterButton dropdownFilterButton, DropdownFilterViewHolder dropdownFilterViewHolder) {
        setFilterState(FilterState.OFF, button, dropdownFilterViewHolder);
        button.setText(dropdownFilterButton.getText());
        dropdownFilterButton.setEnabled(false);
        this.resetListener.onFilterReset(dropdownFilterButton.getType());
        resetFilter(dropdownFilterButton.getType(), context2);
    }

    public final void updateFilters(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        for (DropdownFilterButton next : this.filters) {
            if (next.getType() == i) {
                next.setText(str);
            }
        }
        notifyDataSetChanged();
    }

    private final void resetFilter(int i, Context context2) {
        SharedPreferencesManager.Companion.getInstance(context2);
        for (DropdownFilterButton next : this.filters) {
            if (next.getType() == i) {
                if (i == 0) {
                    String string = this.context.getString(R.string.default_filter_date);
                    Intrinsics.checkNotNullExpressionValue(string, "this.context.getString(R…ring.default_filter_date)");
                    next.setText(string);
                } else if (i == 1) {
                    String string2 = this.context.getString(R.string.default_filter_location);
                    Intrinsics.checkNotNullExpressionValue(string2, "this.context.getString(R….default_filter_location)");
                    next.setText(string2);
                } else if (i == 2) {
                    String string3 = this.context.getString(R.string.default_filter_specialty);
                    Intrinsics.checkNotNullExpressionValue(string3, "this.context.getString(R…default_filter_specialty)");
                    next.setText(string3);
                }
            }
        }
        notifyDataSetChanged();
    }

    public final void setFiltersData(List<DropdownFilterButton> list) {
        Intrinsics.checkNotNullParameter(list, ShareConstants.WEB_DIALOG_PARAM_FILTERS);
        this.filters.clear();
        this.filters.addAll(list);
        notifyDataSetChanged();
    }

    public final void disableFilterAtPosition(int i) {
        this.filters.get(i).setEnabled(false);
        notifyItemChanged(i);
    }
}
