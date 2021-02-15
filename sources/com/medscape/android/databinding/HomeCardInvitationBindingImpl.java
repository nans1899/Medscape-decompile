package com.medscape.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.medscape.android.R;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.myinvites.MyInvitationsAdapterKt;

public class HomeCardInvitationBindingImpl extends HomeCardInvitationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_type, 2);
        sViewsWithIds.put(R.id.image_badge, 3);
        sViewsWithIds.put(R.id.text_description, 4);
        sViewsWithIds.put(R.id.text_information, 5);
        sViewsWithIds.put(R.id.text_view_button, 6);
    }

    public HomeCardInvitationBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private HomeCardInvitationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[0], objArr[4], objArr[5], objArr[1], objArr[2], objArr[6]);
        this.mDirtyFlags = -1;
        this.rootContainer.setTag((Object) null);
        this.textTitle.setTag((Object) null);
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
        if (10 != i) {
            return false;
        }
        setItem((FeedDataItem) obj);
        return true;
    }

    public void setItem(FeedDataItem feedDataItem) {
        this.mItem = feedDataItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = null;
        FeedDataItem feedDataItem = this.mItem;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (!(i == 0 || feedDataItem == null)) {
            str = feedDataItem.getTitle();
        }
        if (i != 0) {
            MyInvitationsAdapterKt.setTextToHtml(this.textTitle, str);
        }
    }
}
