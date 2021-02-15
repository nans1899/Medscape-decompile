package com.medscape.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.medscape.android.R;
import com.medscape.android.consult.models.BodyUpdates;

public class ItemConsultPostUpdateBindingImpl extends ItemConsultPostUpdateBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.post_update_layout, 4);
        sViewsWithIds.put(R.id.update_text, 5);
    }

    public ItemConsultPostUpdateBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private ItemConsultPostUpdateBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1], objArr[3], objArr[4], objArr[5]);
        this.mDirtyFlags = -1;
        this.consultEdit.setTag((Object) null);
        this.displayTime.setTag((Object) null);
        FrameLayout frameLayout = objArr[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag((Object) null);
        this.postBody.setTag((Object) null);
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
        if (3 != i) {
            return false;
        }
        setBodyupdate((BodyUpdates) obj);
        return true;
    }

    public void setBodyupdate(BodyUpdates bodyUpdates) {
        this.mBodyupdate = bodyUpdates;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(3);
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
        BodyUpdates bodyUpdates = this.mBodyupdate;
        String str2 = null;
        int i = 0;
        int i2 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i2 != 0) {
            if (bodyUpdates != null) {
                str2 = bodyUpdates.getDisplayDate();
                z = bodyUpdates.isCurrentUser();
                str = bodyUpdates.getBody();
            } else {
                str = null;
                z = false;
            }
            if (i2 != 0) {
                j |= z ? 8 : 4;
            }
            if (!z) {
                i = 4;
            }
        } else {
            str = null;
        }
        if ((j & 3) != 0) {
            this.consultEdit.setVisibility(i);
            TextViewBindingAdapter.setText(this.displayTime, str2);
            TextViewBindingAdapter.setText(this.postBody, str);
        }
    }
}
