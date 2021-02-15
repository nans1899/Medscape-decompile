package com.medscape.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.medscape.android.R;
import com.medscape.android.ads.NativeADViewModel;

public abstract class NativeAdBindingLayoutBinding extends ViewDataBinding {
    public final TextView additionalLink1Text;
    public final TextView additionalLink2Text;
    public final TextView jobCode;
    public final TextView label;
    @Bindable
    protected NativeADViewModel mNativeViewModel;
    public final LinearLayout nativeAdLayout;
    public final TextView title;
    public final View viewDivider;

    public abstract void setNativeViewModel(NativeADViewModel nativeADViewModel);

    protected NativeAdBindingLayoutBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, TextView textView4, LinearLayout linearLayout, TextView textView5, View view2) {
        super(obj, view, i);
        this.additionalLink1Text = textView;
        this.additionalLink2Text = textView2;
        this.jobCode = textView3;
        this.label = textView4;
        this.nativeAdLayout = linearLayout;
        this.title = textView5;
        this.viewDivider = view2;
    }

    public NativeADViewModel getNativeViewModel() {
        return this.mNativeViewModel;
    }

    public static NativeAdBindingLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NativeAdBindingLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (NativeAdBindingLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.native_ad_binding_layout, viewGroup, z, obj);
    }

    public static NativeAdBindingLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NativeAdBindingLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (NativeAdBindingLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.native_ad_binding_layout, (ViewGroup) null, false, obj);
    }

    public static NativeAdBindingLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NativeAdBindingLayoutBinding bind(View view, Object obj) {
        return (NativeAdBindingLayoutBinding) bind(obj, view, R.layout.native_ad_binding_layout);
    }
}
