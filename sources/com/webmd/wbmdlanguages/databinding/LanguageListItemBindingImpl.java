package com.webmd.wbmdlanguages.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.webmd.wbmdlanguages.BR;
import com.webmd.wbmdlanguages.models.LanguageModel;

public class LanguageListItemBindingImpl extends LanguageListItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public LanguageListItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private LanguageListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        this.logo.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        this.rowTitle.setTag((Object) null);
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
        if (BR.language_model != i) {
            return false;
        }
        setLanguageModel((LanguageModel) obj);
        return true;
    }

    public void setLanguageModel(LanguageModel languageModel) {
        this.mLanguageModel = languageModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.language_model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str2 = null;
        LanguageModel languageModel = this.mLanguageModel;
        int i = 0;
        int i2 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i2 != 0) {
            if (languageModel != null) {
                z = languageModel.isSelected();
                str = languageModel.getLanguage();
            } else {
                str = null;
                z = false;
            }
            if (i2 != 0) {
                j |= z ? 8 : 4;
            }
            if (!z) {
                i = 8;
            }
            str2 = str;
        }
        if ((j & 3) != 0) {
            this.logo.setVisibility(i);
            TextViewBindingAdapter.setText(this.rowTitle, str2);
        }
    }
}
