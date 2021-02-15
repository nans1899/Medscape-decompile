package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.viewmodels.HubViewModel;

public abstract class FragmentHubBinding extends ViewDataBinding {
    public final AppBarLayout appBarLayout;
    @Bindable
    protected HubViewModel mViewmodel;
    public final ProgressBar progressBar;
    public final RecyclerView recyclerView;
    public final Toolbar toolbar;

    public abstract void setViewmodel(HubViewModel hubViewModel);

    protected FragmentHubBinding(Object obj, View view, int i, AppBarLayout appBarLayout2, ProgressBar progressBar2, RecyclerView recyclerView2, Toolbar toolbar2) {
        super(obj, view, i);
        this.appBarLayout = appBarLayout2;
        this.progressBar = progressBar2;
        this.recyclerView = recyclerView2;
        this.toolbar = toolbar2;
    }

    public HubViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public static FragmentHubBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHubBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentHubBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_hub, viewGroup, z, obj);
    }

    public static FragmentHubBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHubBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentHubBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_hub, (ViewGroup) null, false, obj);
    }

    public static FragmentHubBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHubBinding bind(View view, Object obj) {
        return (FragmentHubBinding) bind(obj, view, R.layout.fragment_hub);
    }
}
