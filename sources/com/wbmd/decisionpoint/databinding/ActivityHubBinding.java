package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;

public abstract class ActivityHubBinding extends ViewDataBinding {
    public final FrameLayout frameContainer;

    protected ActivityHubBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.frameContainer = frameLayout;
    }

    public static ActivityHubBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHubBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityHubBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_hub, viewGroup, z, obj);
    }

    public static ActivityHubBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHubBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityHubBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_hub, (ViewGroup) null, false, obj);
    }

    public static ActivityHubBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHubBinding bind(View view, Object obj) {
        return (ActivityHubBinding) bind(obj, view, R.layout.activity_hub);
    }
}
