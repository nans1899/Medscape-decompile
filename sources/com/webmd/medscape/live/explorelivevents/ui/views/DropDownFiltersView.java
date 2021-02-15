package com.webmd.medscape.live.explorelivevents.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.OnDropdownFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.OnFilterResetListener;
import com.webmd.medscape.live.explorelivevents.data.DropdownFilterButton;
import com.webmd.medscape.live.explorelivevents.ui.adapters.DropdownFiltersRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nJ\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0016J\u000e\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u0003J\u0014\u0010\u001a\u001a\u00020\u00122\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\u000e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0002J\u0016\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010 \u001a\u00020!R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/views/DropDownFiltersView;", "Landroid/widget/LinearLayout;", "Lcom/webmd/medscape/live/explorelivevents/common/OnDropdownFilterSelectedListener;", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterResetListener;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "layoutRes", "", "listener", "mAdapter", "Lcom/webmd/medscape/live/explorelivevents/ui/adapters/DropdownFiltersRecyclerViewAdapter;", "mFilters", "Landroidx/recyclerview/widget/RecyclerView;", "resetListener", "disableFilterAtPosition", "", "position", "onDateSelected", "onFilterReset", "type", "onLocationSelected", "onSpecialtySelected", "setDateFilterResetListener", "setFilters", "filters", "", "Lcom/webmd/medscape/live/explorelivevents/data/DropdownFilterButton;", "setOnDropDownFilterListener", "updateFilters", "text", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DropDownFiltersView.kt */
public final class DropDownFiltersView extends LinearLayout implements OnDropdownFilterSelectedListener, OnFilterResetListener {
    private HashMap _$_findViewCache;
    private final int layoutRes = R.layout.dropdown_filters_view;
    private OnDropdownFilterSelectedListener listener;
    private DropdownFiltersRecyclerViewAdapter mAdapter;
    private final RecyclerView mFilters;
    private OnFilterResetListener resetListener;

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

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DropDownFiltersView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        OnDropdownFilterSelectedListener onDropdownFilterSelectedListener = this;
        OnFilterResetListener onFilterResetListener = this;
        this.mAdapter = new DropdownFiltersRecyclerViewAdapter(context, new ArrayList(), onDropdownFilterSelectedListener, onFilterResetListener);
        View.inflate(context, this.layoutRes, this);
        View findViewById = findViewById(R.id.rv_dropdown_filters);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.rv_dropdown_filters)");
        RecyclerView recyclerView = (RecyclerView) findViewById;
        this.mFilters = recyclerView;
        recyclerView.setAdapter(this.mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        this.listener = onDropdownFilterSelectedListener;
        this.resetListener = onFilterResetListener;
    }

    public final void setFilters(List<DropdownFilterButton> list) {
        Intrinsics.checkNotNullParameter(list, ShareConstants.WEB_DIALOG_PARAM_FILTERS);
        this.mAdapter.setFiltersData(list);
    }

    public final void disableFilterAtPosition(int i) {
        this.mAdapter.disableFilterAtPosition(i);
    }

    public final void updateFilters(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        this.mAdapter.updateFilters(i, str);
    }

    public final void setOnDropDownFilterListener(OnDropdownFilterSelectedListener onDropdownFilterSelectedListener) {
        Intrinsics.checkNotNullParameter(onDropdownFilterSelectedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = onDropdownFilterSelectedListener;
    }

    public final void setDateFilterResetListener(OnFilterResetListener onFilterResetListener) {
        Intrinsics.checkNotNullParameter(onFilterResetListener, "resetListener");
        this.resetListener = onFilterResetListener;
    }

    public void onDateSelected() {
        this.listener.onDateSelected();
    }

    public void onLocationSelected() {
        this.listener.onLocationSelected();
    }

    public void onSpecialtySelected() {
        this.listener.onSpecialtySelected();
    }

    public void onFilterReset(int i) {
        this.resetListener.onFilterReset(i);
    }
}
