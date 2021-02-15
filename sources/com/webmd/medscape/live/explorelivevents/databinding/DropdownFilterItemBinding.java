package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.data.DropdownFilterButton;

public abstract class DropdownFilterItemBinding extends ViewDataBinding {
    @Bindable
    protected DropdownFilterButton mFilter;
    public final Button tbFilter;

    public abstract void setFilter(DropdownFilterButton dropdownFilterButton);

    protected DropdownFilterItemBinding(Object obj, View view, int i, Button button) {
        super(obj, view, i);
        this.tbFilter = button;
    }

    public DropdownFilterButton getFilter() {
        return this.mFilter;
    }

    public static DropdownFilterItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DropdownFilterItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DropdownFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dropdown_filter_item, viewGroup, z, obj);
    }

    public static DropdownFilterItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DropdownFilterItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DropdownFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dropdown_filter_item, (ViewGroup) null, false, obj);
    }

    public static DropdownFilterItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DropdownFilterItemBinding bind(View view, Object obj) {
        return (DropdownFilterItemBinding) bind(obj, view, R.layout.dropdown_filter_item);
    }
}
