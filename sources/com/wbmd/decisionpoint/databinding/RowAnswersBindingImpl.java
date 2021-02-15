package com.wbmd.decisionpoint.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;

public class RowAnswersBindingImpl extends RowAnswersBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.image_line_top, 1);
        sViewsWithIds.put(R.id.text_steps, 2);
        sViewsWithIds.put(R.id.text_count_1, 3);
        sViewsWithIds.put(R.id.text_title_1, 4);
        sViewsWithIds.put(R.id.text_message_1, 5);
        sViewsWithIds.put(R.id.line_1_2, 6);
        sViewsWithIds.put(R.id.text_count_2, 7);
        sViewsWithIds.put(R.id.text_title_2, 8);
        sViewsWithIds.put(R.id.text_message_2, 9);
        sViewsWithIds.put(R.id.line_2_3, 10);
        sViewsWithIds.put(R.id.text_count_3, 11);
        sViewsWithIds.put(R.id.text_title_3, 12);
        sViewsWithIds.put(R.id.text_message_3, 13);
        sViewsWithIds.put(R.id.image_line_bottom, 14);
    }

    public RowAnswersBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }

    private RowAnswersBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[14], objArr[1], objArr[6], objArr[10], objArr[3], objArr[7], objArr[11], objArr[5], objArr[9], objArr[13], objArr[2], objArr[4], objArr[8], objArr[12]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
