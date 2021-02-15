package com.medscape.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.ads.NativeADViewModel;

public abstract class ClrefTocFragmentBinding extends ViewDataBinding {
    public final AdItemLayoutBinding adContainerLayout;
    public final RecyclerView articleSections;
    @Bindable
    protected NativeADViewModel mActivityAdModel;
    public final ProgressBar progress;
    public final View tocHeader;

    public abstract void setActivityAdModel(NativeADViewModel nativeADViewModel);

    protected ClrefTocFragmentBinding(Object obj, View view, int i, AdItemLayoutBinding adItemLayoutBinding, RecyclerView recyclerView, ProgressBar progressBar, View view2) {
        super(obj, view, i);
        this.adContainerLayout = adItemLayoutBinding;
        setContainedBinding(adItemLayoutBinding);
        this.articleSections = recyclerView;
        this.progress = progressBar;
        this.tocHeader = view2;
    }

    public NativeADViewModel getActivityAdModel() {
        return this.mActivityAdModel;
    }

    public static ClrefTocFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ClrefTocFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ClrefTocFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.clref_toc_fragment, viewGroup, z, obj);
    }

    public static ClrefTocFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ClrefTocFragmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ClrefTocFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.clref_toc_fragment, (ViewGroup) null, false, obj);
    }

    public static ClrefTocFragmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ClrefTocFragmentBinding bind(View view, Object obj) {
        return (ClrefTocFragmentBinding) bind(obj, view, R.layout.clref_toc_fragment);
    }
}
