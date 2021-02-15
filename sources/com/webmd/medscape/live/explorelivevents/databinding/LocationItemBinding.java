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

public abstract class LocationItemBinding extends ViewDataBinding {
    public final CheckBox cbIsChecked;
    @Bindable
    protected String mLocation;
    @Bindable
    protected StyleManager mStyleManager;
    public final TextView tvFilterName;

    public abstract void setLocation(String str);

    public abstract void setStyleManager(StyleManager styleManager);

    protected LocationItemBinding(Object obj, View view, int i, CheckBox checkBox, TextView textView) {
        super(obj, view, i);
        this.cbIsChecked = checkBox;
        this.tvFilterName = textView;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public StyleManager getStyleManager() {
        return this.mStyleManager;
    }

    public static LocationItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LocationItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.location_item, viewGroup, z, obj);
    }

    public static LocationItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LocationItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.location_item, (ViewGroup) null, false, obj);
    }

    public static LocationItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LocationItemBinding bind(View view, Object obj) {
        return (LocationItemBinding) bind(obj, view, R.layout.location_item);
    }
}
