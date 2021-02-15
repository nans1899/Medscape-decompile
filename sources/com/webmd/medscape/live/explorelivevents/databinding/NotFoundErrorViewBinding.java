package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;

public abstract class NotFoundErrorViewBinding extends ViewDataBinding {
    public final ImageView idLogo;
    public final ImageView ivShutterstock;
    @Bindable
    protected ExploreEventsViewModel mViewModel;
    public final RelativeLayout notFoundLyt;

    public abstract void setViewModel(ExploreEventsViewModel exploreEventsViewModel);

    protected NotFoundErrorViewBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.idLogo = imageView;
        this.ivShutterstock = imageView2;
        this.notFoundLyt = relativeLayout;
    }

    public ExploreEventsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static NotFoundErrorViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotFoundErrorViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (NotFoundErrorViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.not_found_error_view, viewGroup, z, obj);
    }

    public static NotFoundErrorViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotFoundErrorViewBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (NotFoundErrorViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.not_found_error_view, (ViewGroup) null, false, obj);
    }

    public static NotFoundErrorViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NotFoundErrorViewBinding bind(View view, Object obj) {
        return (NotFoundErrorViewBinding) bind(obj, view, R.layout.not_found_error_view);
    }
}
