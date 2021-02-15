package com.wbmd.ads.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.ads.R;
import com.wbmd.ads.model.BaseNativeADViewModel;

public abstract class AdItemLayoutBinding extends ViewDataBinding {
    public final TextView adLabel;
    public final LinearLayout adLayout;
    public final FrameLayout adRootView;
    @Bindable
    protected BaseNativeADViewModel mAdmodel;
    public final NativeAdLayoutBinding nativeAdLayout;

    public abstract void setAdmodel(BaseNativeADViewModel baseNativeADViewModel);

    protected AdItemLayoutBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, FrameLayout frameLayout, NativeAdLayoutBinding nativeAdLayoutBinding) {
        super(obj, view, i);
        this.adLabel = textView;
        this.adLayout = linearLayout;
        this.adRootView = frameLayout;
        this.nativeAdLayout = nativeAdLayoutBinding;
        setContainedBinding(nativeAdLayoutBinding);
    }

    public BaseNativeADViewModel getAdmodel() {
        return this.mAdmodel;
    }

    public static AdItemLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AdItemLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (AdItemLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ad_item_layout, viewGroup, z, obj);
    }

    public static AdItemLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AdItemLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (AdItemLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.ad_item_layout, (ViewGroup) null, false, obj);
    }

    public static AdItemLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AdItemLayoutBinding bind(View view, Object obj) {
        return (AdItemLayoutBinding) bind(obj, view, R.layout.ad_item_layout);
    }
}
