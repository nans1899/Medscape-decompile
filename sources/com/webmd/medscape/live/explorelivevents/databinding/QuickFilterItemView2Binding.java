package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;

public abstract class QuickFilterItemView2Binding extends ViewDataBinding {
    @Bindable
    protected String mFilterName;
    @Bindable
    protected StyleManager mStyleManager;
    public final TextView text1;

    public abstract void setFilterName(String str);

    public abstract void setStyleManager(StyleManager styleManager);

    protected QuickFilterItemView2Binding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.text1 = textView;
    }

    public String getFilterName() {
        return this.mFilterName;
    }

    public StyleManager getStyleManager() {
        return this.mStyleManager;
    }

    public static QuickFilterItemView2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QuickFilterItemView2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (QuickFilterItemView2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.quick_filter_item_view2, viewGroup, z, obj);
    }

    public static QuickFilterItemView2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QuickFilterItemView2Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (QuickFilterItemView2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.quick_filter_item_view2, (ViewGroup) null, false, obj);
    }

    public static QuickFilterItemView2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QuickFilterItemView2Binding bind(View view, Object obj) {
        return (QuickFilterItemView2Binding) bind(obj, view, R.layout.quick_filter_item_view2);
    }
}
