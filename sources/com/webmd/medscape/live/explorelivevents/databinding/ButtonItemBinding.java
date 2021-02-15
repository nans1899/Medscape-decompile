package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.R;

public abstract class ButtonItemBinding extends ViewDataBinding {
    @Bindable
    protected String mButton;
    public final TextView tvButton;

    public abstract void setButton(String str);

    protected ButtonItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvButton = textView;
    }

    public String getButton() {
        return this.mButton;
    }

    public static ButtonItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ButtonItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ButtonItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.button_item, viewGroup, z, obj);
    }

    public static ButtonItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ButtonItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ButtonItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.button_item, (ViewGroup) null, false, obj);
    }

    public static ButtonItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ButtonItemBinding bind(View view, Object obj) {
        return (ButtonItemBinding) bind(obj, view, R.layout.button_item);
    }
}
