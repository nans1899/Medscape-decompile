package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;

public class QuickFilterItemView2BindingImpl extends QuickFilterItemView2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public QuickFilterItemView2BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private QuickFilterItemView2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[0]);
        this.mDirtyFlags = -1;
        this.text1.setTag((Object) null);
        setRootTag(view);
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
        if (BR.styleManager == i) {
            setStyleManager((StyleManager) obj);
        } else if (BR.filterName != i) {
            return false;
        } else {
            setFilterName((String) obj);
        }
        return true;
    }

    public void setStyleManager(StyleManager styleManager) {
        this.mStyleManager = styleManager;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.styleManager);
        super.requestRebind();
    }

    public void setFilterName(String str) {
        this.mFilterName = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.filterName);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r11 = this;
            monitor-enter(r11)
            long r0 = r11.mDirtyFlags     // Catch:{ all -> 0x0040 }
            r2 = 0
            r11.mDirtyFlags = r2     // Catch:{ all -> 0x0040 }
            monitor-exit(r11)     // Catch:{ all -> 0x0040 }
            r4 = 0
            com.webmd.medscape.live.explorelivevents.common.StyleManager r5 = r11.mStyleManager
            java.lang.String r6 = r11.mFilterName
            r7 = 5
            long r7 = r7 & r0
            r9 = 0
            int r10 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r10 == 0) goto L_0x0026
            if (r5 == 0) goto L_0x001b
            com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer r4 = r5.getColors()
        L_0x001b:
            if (r4 == 0) goto L_0x0026
            int r9 = r4.getQuickFiltersViewBackgroundColor()
            int r4 = r4.getQuickFiltersItemTextColor()
            goto L_0x0027
        L_0x0026:
            r4 = 0
        L_0x0027:
            r7 = 6
            long r0 = r0 & r7
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r10 == 0) goto L_0x0038
            android.widget.TextView r0 = r11.text1
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintColor(r0, r9)
            android.widget.TextView r0 = r11.text1
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r0, r4)
        L_0x0038:
            if (r5 == 0) goto L_0x003f
            android.widget.TextView r0 = r11.text1
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0040 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.QuickFilterItemView2BindingImpl.executeBindings():void");
    }
}
