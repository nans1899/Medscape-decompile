package com.wbmd.decisionpoint.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.wbmd.decisionpoint.BR;
import com.wbmd.decisionpoint.domain.contributors.Contributor;
import com.wbmd.decisionpoint.ui.adapters.holders.ContributorItemViewHolderKt;

public class RowContributorItemBindingImpl extends RowContributorItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public RowContributorItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private RowContributorItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[2], objArr[3]);
        this.mDirtyFlags = -1;
        this.imageAvatar.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.textName.setTag((Object) null);
        this.textProfession.setTag((Object) null);
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
        if (BR.contributor != i) {
            return false;
        }
        setContributor((Contributor) obj);
        return true;
    }

    public void setContributor(Contributor contributor) {
        this.mContributor = contributor;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.contributor);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Contributor contributor = this.mContributor;
        String str3 = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || contributor == null) {
            str2 = null;
            str = null;
        } else {
            String expertTitle = contributor.getExpertTitle();
            String profileImage = contributor.getProfileImage();
            str = contributor.getExpertName();
            String str4 = expertTitle;
            str3 = profileImage;
            str2 = str4;
        }
        if (i != 0) {
            ContributorItemViewHolderKt.contributeImageUrl(this.imageAvatar, str3);
            TextViewBindingAdapter.setText(this.textName, str);
            TextViewBindingAdapter.setText(this.textProfession, str2);
        }
    }
}
