package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;

public abstract class QuickFiltersView2Binding extends ViewDataBinding {
    public final View divider;
    @Bindable
    protected StyleManager mStyleManager;
    public final RecyclerView rvFilters;
    public final TextView tvHeading;

    public abstract void setStyleManager(StyleManager styleManager);

    protected QuickFiltersView2Binding(Object obj, View view, int i, View view2, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i);
        this.divider = view2;
        this.rvFilters = recyclerView;
        this.tvHeading = textView;
    }

    public StyleManager getStyleManager() {
        return this.mStyleManager;
    }

    public static QuickFiltersView2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QuickFiltersView2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (QuickFiltersView2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.quick_filters_view2, viewGroup, z, obj);
    }

    public static QuickFiltersView2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QuickFiltersView2Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (QuickFiltersView2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.quick_filters_view2, (ViewGroup) null, false, obj);
    }

    public static QuickFiltersView2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QuickFiltersView2Binding bind(View view, Object obj) {
        return (QuickFiltersView2Binding) bind(obj, view, R.layout.quick_filters_view2);
    }
}
