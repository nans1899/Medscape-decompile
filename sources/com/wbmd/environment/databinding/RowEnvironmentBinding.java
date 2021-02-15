package com.wbmd.environment.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.environment.R;
import com.wbmd.environment.data.Module;
import com.wbmd.environment.interfaces.OnModuleListener;

public abstract class RowEnvironmentBinding extends ViewDataBinding {
    @Bindable
    protected OnModuleListener mListener;
    @Bindable
    protected Module mModule;
    public final TextView textEnvironment;
    public final TextView textModule;

    public abstract void setListener(OnModuleListener onModuleListener);

    public abstract void setModule(Module module);

    protected RowEnvironmentBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.textEnvironment = textView;
        this.textModule = textView2;
    }

    public OnModuleListener getListener() {
        return this.mListener;
    }

    public Module getModule() {
        return this.mModule;
    }

    public static RowEnvironmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowEnvironmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RowEnvironmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_environment, viewGroup, z, obj);
    }

    public static RowEnvironmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowEnvironmentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RowEnvironmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_environment, (ViewGroup) null, false, obj);
    }

    public static RowEnvironmentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowEnvironmentBinding bind(View view, Object obj) {
        return (RowEnvironmentBinding) bind(obj, view, R.layout.row_environment);
    }
}
