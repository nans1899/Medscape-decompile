package com.wbmd.environment.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.wbmd.environment.BR;
import com.wbmd.environment.data.Module;
import com.wbmd.environment.generated.callback.OnClickListener;
import com.wbmd.environment.interfaces.OnModuleListener;
import com.wbmd.environment.ui.adapters.ModuleAdapterKt;

public class RowEnvironmentBindingImpl extends RowEnvironmentBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback1;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public RowEnvironmentBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private RowEnvironmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.textEnvironment.setTag((Object) null);
        this.textModule.setTag((Object) null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.module == i) {
            setModule((Module) obj);
        } else if (BR.listener != i) {
            return false;
        } else {
            setListener((OnModuleListener) obj);
        }
        return true;
    }

    public void setModule(Module module) {
        this.mModule = module;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.module);
        super.requestRebind();
    }

    public void setListener(OnModuleListener onModuleListener) {
        this.mListener = onModuleListener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.listener);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Module module = this.mModule;
        OnModuleListener onModuleListener = this.mListener;
        String str = null;
        int i = ((5 & j) > 0 ? 1 : ((5 & j) == 0 ? 0 : -1));
        if (!(i == 0 || module == null)) {
            str = module.getName();
        }
        if ((j & 4) != 0) {
            this.mboundView0.setOnClickListener(this.mCallback1);
        }
        if (i != 0) {
            ModuleAdapterKt.setActiveEnvironment(this.textEnvironment, module);
            TextViewBindingAdapter.setText(this.textModule, str);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        Module module = this.mModule;
        OnModuleListener onModuleListener = this.mListener;
        if (onModuleListener != null) {
            onModuleListener.onChanged(module);
        }
    }
}
