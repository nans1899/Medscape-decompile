package com.wbmd.decisionpoint.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.wbmd.decisionpoint.BR;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoint;
import com.wbmd.decisionpoint.domain.interfaces.HubListener;
import com.wbmd.decisionpoint.generated.callback.OnClickListener;
import com.wbmd.decisionpoint.ui.adapters.holders.DecisionPointViewHolderKt;

public class RowDecisionPointBindingImpl extends RowDecisionPointBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback1;
    private final View.OnClickListener mCallback2;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public RowDecisionPointBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private RowDecisionPointBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[5], objArr[1], objArr[2], objArr[4], objArr[3]);
        this.mDirtyFlags = -1;
        this.buttonExplore.setTag((Object) null);
        this.cardView.setTag((Object) null);
        this.imageType.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.textMessage.setTag((Object) null);
        this.textTitle.setTag((Object) null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
        this.mCallback2 = new OnClickListener(this, 2);
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
        if (BR.item == i) {
            setItem((DecisionPoint) obj);
        } else if (BR.listener != i) {
            return false;
        } else {
            setListener((HubListener) obj);
        }
        return true;
    }

    public void setItem(DecisionPoint decisionPoint) {
        this.mItem = decisionPoint;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setListener(HubListener hubListener) {
        this.mListener = hubListener;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.listener);
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
        DecisionPoint decisionPoint = this.mItem;
        HubListener hubListener = this.mListener;
        String str3 = null;
        int i = ((5 & j) > 0 ? 1 : ((5 & j) == 0 ? 0 : -1));
        if (i == 0 || decisionPoint == null) {
            str2 = null;
            str = null;
        } else {
            str3 = decisionPoint.getImageUrl();
            str = decisionPoint.getDetail();
            str2 = decisionPoint.getTitle();
        }
        if ((j & 4) != 0) {
            this.buttonExplore.setOnClickListener(this.mCallback2);
            this.cardView.setOnClickListener(this.mCallback1);
        }
        if (i != 0) {
            DecisionPointViewHolderKt.setDecisionPointImage(this.imageType, str3);
            TextViewBindingAdapter.setText(this.textMessage, str);
            TextViewBindingAdapter.setText(this.textTitle, str2);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        if (i == 1) {
            DecisionPoint decisionPoint = this.mItem;
            HubListener hubListener = this.mListener;
            if (hubListener != null) {
                if (decisionPoint != null) {
                    hubListener.onExploreClick(decisionPoint.getUrl(), this.cardView.getResources().getString(R.string.decision_point_link, new Object[]{decisionPoint.getTitle()}));
                }
            }
        } else if (i == 2) {
            DecisionPoint decisionPoint2 = this.mItem;
            HubListener hubListener2 = this.mListener;
            if (hubListener2 != null) {
                if (decisionPoint2 != null) {
                    hubListener2.onExploreClick(decisionPoint2.getUrl(), this.buttonExplore.getResources().getString(R.string.decision_point_link, new Object[]{decisionPoint2.getTitle()}));
                }
            }
        }
    }
}
