package com.medscape.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.medscape.android.R;

public abstract class HomeNativeAdLayoutBinding extends ViewDataBinding {
    public final View adRootLayout;
    @Bindable
    protected PublisherAdView mDfpad;
    @Bindable
    protected NativeCustomTemplateAd mNativead;

    public abstract void setDfpad(PublisherAdView publisherAdView);

    public abstract void setNativead(NativeCustomTemplateAd nativeCustomTemplateAd);

    protected HomeNativeAdLayoutBinding(Object obj, View view, int i, View view2) {
        super(obj, view, i);
        this.adRootLayout = view2;
    }

    public NativeCustomTemplateAd getNativead() {
        return this.mNativead;
    }

    public PublisherAdView getDfpad() {
        return this.mDfpad;
    }

    public static HomeNativeAdLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomeNativeAdLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (HomeNativeAdLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.home_native_ad_layout, viewGroup, z, obj);
    }

    public static HomeNativeAdLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomeNativeAdLayoutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (HomeNativeAdLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.home_native_ad_layout, (ViewGroup) null, false, obj);
    }

    public static HomeNativeAdLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomeNativeAdLayoutBinding bind(View view, Object obj) {
        return (HomeNativeAdLayoutBinding) bind(obj, view, R.layout.home_native_ad_layout);
    }
}
