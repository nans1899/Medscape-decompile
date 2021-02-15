package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.SearchFilter;

public abstract class SpecialtyItemBinding extends ViewDataBinding {
    public final CheckBox cbIsChecked;
    @Bindable
    protected SearchFilter mSearchFilter;
    @Bindable
    protected StyleManager mStyleManager;
    public final TextView tvFilterName;

    public abstract void setSearchFilter(SearchFilter searchFilter);

    public abstract void setStyleManager(StyleManager styleManager);

    protected SpecialtyItemBinding(Object obj, View view, int i, CheckBox checkBox, TextView textView) {
        super(obj, view, i);
        this.cbIsChecked = checkBox;
        this.tvFilterName = textView;
    }

    public SearchFilter getSearchFilter() {
        return this.mSearchFilter;
    }

    public StyleManager getStyleManager() {
        return this.mStyleManager;
    }

    public static SpecialtyItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SpecialtyItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SpecialtyItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.specialty_item, viewGroup, z, obj);
    }

    public static SpecialtyItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SpecialtyItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SpecialtyItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.specialty_item, (ViewGroup) null, false, obj);
    }

    public static SpecialtyItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SpecialtyItemBinding bind(View view, Object obj) {
        return (SpecialtyItemBinding) bind(obj, view, R.layout.specialty_item);
    }
}
