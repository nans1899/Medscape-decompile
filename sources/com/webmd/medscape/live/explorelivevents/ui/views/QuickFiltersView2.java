package com.webmd.medscape.live.explorelivevents.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.webmd.medscape.live.explorelivevents.common.OnFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.databinding.QuickFiltersView2Binding;
import com.webmd.medscape.live.explorelivevents.ui.adapters.QuickFiltersView2RecyclerViewAdapter;
import com.webmd.medscape.live.explorelivevents.ui.decorators.MiddleDividerItemDecoration;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.ZonedDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0002J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/views/QuickFiltersView2;", "Landroid/widget/RelativeLayout;", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "divider", "Lcom/webmd/medscape/live/explorelivevents/ui/decorators/MiddleDividerItemDecoration;", "listener", "mBinding", "Lcom/webmd/medscape/live/explorelivevents/databinding/QuickFiltersView2Binding;", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "onDateRangeFilterSelected", "", "onLocationFilterSelected", "onSpecialtyFilterSelected", "setOnFilterSelectedListener", "setStyle", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuickFiltersView2.kt */
public final class QuickFiltersView2 extends RelativeLayout implements OnFilterSelectedListener {
    private HashMap _$_findViewCache;
    private final MiddleDividerItemDecoration divider;
    private OnFilterSelectedListener listener;
    private QuickFiltersView2Binding mBinding;
    private StyleManager styleManager;

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
    public QuickFiltersView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        this.divider = new MiddleDividerItemDecoration(context, MiddleDividerItemDecoration.Companion.getVERTICAL());
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            QuickFiltersView2Binding inflate = QuickFiltersView2Binding.inflate((LayoutInflater) systemService, this, true);
            Intrinsics.checkNotNullExpressionValue(inflate, "QuickFiltersView2Binding…flate(inflater,this,true)");
            this.mBinding = inflate;
            this.listener = this;
            RecyclerView recyclerView = inflate.rvFilters;
            recyclerView.setAdapter(new QuickFiltersView2RecyclerViewAdapter(context, this.listener, (StyleManager) null, 4, (DefaultConstructorMarker) null));
            recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            recyclerView.addItemDecoration(this.divider);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public void onClick(String str, Pair<ZonedDateTime, ZonedDateTime> pair) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(pair, "datesSelected");
        OnFilterSelectedListener.DefaultImpls.onClick(this, str, pair);
    }

    public void onSpecificFilterSelected() {
        OnFilterSelectedListener.DefaultImpls.onSpecificFilterSelected(this);
    }

    public final void setStyle(StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(styleManager2, "styleManager");
        this.styleManager = styleManager2;
        this.mBinding.setStyleManager(styleManager2);
        RecyclerView recyclerView = this.mBinding.rvFilters;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "mBinding.rvFilters");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((QuickFiltersView2RecyclerViewAdapter) adapter).setStyle(styleManager2);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.adapters.QuickFiltersView2RecyclerViewAdapter");
    }

    public final void setOnFilterSelectedListener(OnFilterSelectedListener onFilterSelectedListener) {
        Intrinsics.checkNotNullParameter(onFilterSelectedListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = onFilterSelectedListener;
    }

    public void onSpecialtyFilterSelected() {
        OnFilterSelectedListener onFilterSelectedListener = this.listener;
        if (onFilterSelectedListener != null) {
            onFilterSelectedListener.onSpecialtyFilterSelected();
        }
    }

    public void onDateRangeFilterSelected() {
        OnFilterSelectedListener onFilterSelectedListener = this.listener;
        if (onFilterSelectedListener != null) {
            onFilterSelectedListener.onDateRangeFilterSelected();
        }
    }

    public void onLocationFilterSelected() {
        OnFilterSelectedListener onFilterSelectedListener = this.listener;
        if (onFilterSelectedListener != null) {
            onFilterSelectedListener.onLocationFilterSelected();
        }
    }
}
