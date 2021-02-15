package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;

public class LocationItemBindingImpl extends LocationItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public LocationItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private LocationItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[2]);
        this.mDirtyFlags = -1;
        this.cbIsChecked.setTag((Object) null);
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        this.tvFilterName.setTag((Object) null);
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
        } else if (BR.location != i) {
            return false;
        } else {
            setLocation((String) obj);
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

    public void setLocation(String str) {
        this.mLocation = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.location);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r12 = this;
            monitor-enter(r12)
            long r0 = r12.mDirtyFlags     // Catch:{ all -> 0x004d }
            r2 = 0
            r12.mDirtyFlags = r2     // Catch:{ all -> 0x004d }
            monitor-exit(r12)     // Catch:{ all -> 0x004d }
            r4 = 0
            com.webmd.medscape.live.explorelivevents.common.StyleManager r5 = r12.mStyleManager
            java.lang.String r6 = r12.mLocation
            r7 = 5
            long r7 = r7 & r0
            r9 = 0
            int r10 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r10 == 0) goto L_0x002d
            if (r5 == 0) goto L_0x001b
            com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer r4 = r5.getColors()
        L_0x001b:
            if (r4 == 0) goto L_0x002d
            int r9 = r4.getFilterItemTextColor()
            int r5 = r4.getFilterItemBackgroundColor()
            int r4 = r4.getFilterCheckBoxSelectedColor()
            r11 = r9
            r9 = r4
            r4 = r11
            goto L_0x002f
        L_0x002d:
            r4 = 0
            r5 = 0
        L_0x002f:
            r7 = 6
            long r0 = r0 & r7
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r10 == 0) goto L_0x0045
            android.widget.CheckBox r0 = r12.cbIsChecked
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.setTint(r0, r9)
            android.widget.LinearLayout r0 = r12.mboundView0
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintColor(r0, r5)
            android.widget.TextView r0 = r12.tvFilterName
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r0, r4)
        L_0x0045:
            if (r7 == 0) goto L_0x004c
            android.widget.TextView r0 = r12.tvFilterName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x004c:
            return
        L_0x004d:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x004d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.LocationItemBindingImpl.executeBindings():void");
    }
}
