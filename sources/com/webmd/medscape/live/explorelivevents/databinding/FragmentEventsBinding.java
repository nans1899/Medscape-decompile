package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;
import com.webmd.medscape.live.explorelivevents.ui.views.DropDownFiltersView;

public abstract class FragmentEventsBinding extends ViewDataBinding {
    public final AppBarLayout actionBar;
    public final ConstraintLayout constraintView;
    public final DropDownFiltersView filters;
    public final NotFoundErrorViewBinding lytError;
    @Bindable
    protected ExploreEventsViewModel mViewModel;
    public final ProgressBar pbLoader;
    public final RecyclerView rvItems;
    public final Toolbar toolbar;

    public abstract void setViewModel(ExploreEventsViewModel exploreEventsViewModel);

    protected FragmentEventsBinding(Object obj, View view, int i, AppBarLayout appBarLayout, ConstraintLayout constraintLayout, DropDownFiltersView dropDownFiltersView, NotFoundErrorViewBinding notFoundErrorViewBinding, ProgressBar progressBar, RecyclerView recyclerView, Toolbar toolbar2) {
        super(obj, view, i);
        this.actionBar = appBarLayout;
        this.constraintView = constraintLayout;
        this.filters = dropDownFiltersView;
        this.lytError = notFoundErrorViewBinding;
        setContainedBinding(notFoundErrorViewBinding);
        this.pbLoader = progressBar;
        this.rvItems = recyclerView;
        this.toolbar = toolbar2;
    }

    public ExploreEventsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentEventsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEventsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentEventsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_events, viewGroup, z, obj);
    }

    public static FragmentEventsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEventsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentEventsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_events, (ViewGroup) null, false, obj);
    }

    public static FragmentEventsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEventsBinding bind(View view, Object obj) {
        return (FragmentEventsBinding) bind(obj, view, R.layout.fragment_events);
    }
}
