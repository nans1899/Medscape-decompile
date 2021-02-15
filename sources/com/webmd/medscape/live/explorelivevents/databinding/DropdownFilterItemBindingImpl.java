package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.data.DropdownFilterButton;

public class DropdownFilterItemBindingImpl extends DropdownFilterItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public DropdownFilterItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private DropdownFilterItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[0]);
        this.mDirtyFlags = -1;
        this.tbFilter.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.filter != i) {
            return false;
        }
        setFilter((DropdownFilterButton) obj);
        return true;
    }

    public void setFilter(DropdownFilterButton dropdownFilterButton) {
        this.mFilter = dropdownFilterButton;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.filter);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        DropdownFilterButton dropdownFilterButton = this.mFilter;
        String str = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (!(i == 0 || dropdownFilterButton == null)) {
            str = dropdownFilterButton.getText();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.tbFilter, str);
        }
    }
}
