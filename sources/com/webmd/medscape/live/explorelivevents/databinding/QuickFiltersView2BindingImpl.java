package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;

public class QuickFiltersView2BindingImpl extends QuickFiltersView2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv_filters, 3);
    }

    public QuickFiltersView2BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private QuickFiltersView2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[3], objArr[1]);
        this.mDirtyFlags = -1;
        this.divider.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        this.tvHeading.setTag((Object) null);
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
        if (BR.styleManager != i) {
            return false;
        }
        setStyleManager((StyleManager) obj);
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

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r9 = this;
            monitor-enter(r9)
            long r0 = r9.mDirtyFlags     // Catch:{ all -> 0x0040 }
            r2 = 0
            r9.mDirtyFlags = r2     // Catch:{ all -> 0x0040 }
            monitor-exit(r9)     // Catch:{ all -> 0x0040 }
            r4 = 0
            com.webmd.medscape.live.explorelivevents.common.StyleManager r5 = r9.mStyleManager
            r6 = 3
            long r0 = r0 & r6
            r6 = 0
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L_0x002c
            if (r5 == 0) goto L_0x0019
            com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer r4 = r5.getColors()
        L_0x0019:
            if (r4 == 0) goto L_0x002c
            int r6 = r4.getQuickFiltersViewBackgroundColor()
            int r0 = r4.getQuickFiltersHeadingTextColor()
            int r1 = r4.getQuickFiltersDividerColor()
            r8 = r1
            r1 = r0
            r0 = r6
            r6 = r8
            goto L_0x002e
        L_0x002c:
            r0 = 0
            r1 = 0
        L_0x002e:
            if (r7 == 0) goto L_0x003f
            android.view.View r2 = r9.divider
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintColor(r2, r6)
            android.widget.TextView r2 = r9.tvHeading
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintColor(r2, r0)
            android.widget.TextView r0 = r9.tvHeading
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r0, r1)
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0040 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.QuickFiltersView2BindingImpl.executeBindings():void");
    }
}
