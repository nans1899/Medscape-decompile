package com.medscape.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.medscape.android.R;
import com.medscape.android.ads.NativeADViewModel;

public abstract class AdItemLayoutBinding extends ViewDataBinding {
    public final TextView adLabel;
    public final LinearLayout adLayout;
    public final FrameLayout adRootView;
    @Bindable
    protected NativeADViewModel mAdmodel;
    public final NativeAdBindingLayoutBinding nativeAdLayout;

    public abstract void setAdmodel(NativeADViewModel nativeADViewModel);

    protected AdItemLayoutBinding(Object obj, View view, int i, TextView textView, LinearLayout linearLayout, FrameLayout frameLayout, NativeAdBindingLayoutBinding nativeAdBindingLayoutBinding) {
        super(obj, view, i);
        this.adLabel = textView;
        this.adLayout = linearLayout;
        this.adRootView = frameLayout;
        this.nativeAdLayout = nativeAdBindingLayoutBinding;
        setContainedBinding(nativeAdBindingLayoutBinding);
    }

    public NativeADViewModel getAdmodel() {
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
