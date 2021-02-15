package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;
import com.webmd.medscape.live.explorelivevents.ui.views.QuickFiltersView2;

public abstract class FragmentExploreBinding extends ViewDataBinding {
    public final AppBarLayout actionBar;
    public final Button btShowAll;
    public final View divider;
    public final RelativeLayout lytAnim;
    public final RelativeLayout lytEventsHappening;
    public final QuickFiltersView2 lytFilters;
    public final RelativeLayout lytIntro;
    @Bindable
    protected StyleManager mStyleManager;
    @Bindable
    protected ExploreEventsViewModel mViewModel;
    public final NotFoundErrorViewBinding notFound;
    public final ProgressBar pbLoader;
    public final RecyclerView rvEvents;
    public final NestedScrollView scrollView;
    public final Toolbar toolbar;
    public final TextView tvDescription;
    public final TextView tvEventsHappening;
    public final ConstraintLayout verticalEventsView;

    public abstract void setStyleManager(StyleManager styleManager);

    public abstract void setViewModel(ExploreEventsViewModel exploreEventsViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentExploreBinding(Object obj, View view, int i, AppBarLayout appBarLayout, Button button, View view2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, QuickFiltersView2 quickFiltersView2, RelativeLayout relativeLayout3, NotFoundErrorViewBinding notFoundErrorViewBinding, ProgressBar progressBar, RecyclerView recyclerView, NestedScrollView nestedScrollView, Toolbar toolbar2, TextView textView, TextView textView2, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.actionBar = appBarLayout;
        this.btShowAll = button;
        this.divider = view2;
        this.lytAnim = relativeLayout;
        this.lytEventsHappening = relativeLayout2;
        this.lytFilters = quickFiltersView2;
        this.lytIntro = relativeLayout3;
        this.notFound = notFoundErrorViewBinding;
        setContainedBinding(notFoundErrorViewBinding);
        this.pbLoader = progressBar;
        this.rvEvents = recyclerView;
        this.scrollView = nestedScrollView;
        this.toolbar = toolbar2;
        this.tvDescription = textView;
        this.tvEventsHappening = textView2;
        this.verticalEventsView = constraintLayout;
    }

    public ExploreEventsViewModel getViewModel() {
        return this.mViewModel;
    }

    public StyleManager getStyleManager() {
        return this.mStyleManager;
    }

    public static FragmentExploreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentExploreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentExploreBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_explore, viewGroup, z, obj);
    }

    public static FragmentExploreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentExploreBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentExploreBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_explore, (ViewGroup) null, false, obj);
    }

    public static FragmentExploreBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentExploreBinding bind(View view, Object obj) {
        return (FragmentExploreBinding) bind(obj, view, R.layout.fragment_explore);
    }
}
