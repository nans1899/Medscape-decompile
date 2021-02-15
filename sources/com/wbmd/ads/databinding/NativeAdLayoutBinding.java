package com.wbmd.ads.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.ads.R;
import com.wbmd.ads.model.BaseNativeADViewModel;

public abstract class NativeAdLayoutBinding extends ViewDataBinding {
    public final TextView label;
    @Bindable
    protected BaseNativeADViewModel mNativeViewModel;
    public final LinearLayout nativeAdLayout;
    public final TextView title;

    public abstract void setNativeViewModel(BaseNativeADViewModel baseNativeADViewModel);

    protected NativeAdLayoutBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, TextView textView2) {
        super(obj, view, i);
        this.label = textView;
        this.nativeAdLayout = linearLayout;
        this.title = textView2;
    }

    public BaseNativeADViewModel getNativeViewModel() {
        return this.mNativeViewModel;
    }

    public static NativeAdLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NativeAdLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (NativeAdLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.native_ad_layout, viewGroup, z, obj);
    }

    public static NativeAdLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NativeAdLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (NativeAdLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.native_ad_layout, (ViewGroup) null, false, obj);
    }

    public static NativeAdLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NativeAdLayoutBinding bind(View view, Object obj) {
        return (NativeAdLayoutBinding) bind(obj, view, R.layout.native_ad_layout);
    }
}
