package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.SearchFilter;

public class SpecialtyItemBindingImpl extends SpecialtyItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public SpecialtyItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private SpecialtyItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
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
        } else if (BR.searchFilter != i) {
            return false;
        } else {
            setSearchFilter((SearchFilter) obj);
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

    public void setSearchFilter(SearchFilter searchFilter) {
        this.mSearchFilter = searchFilter;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.searchFilter);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r13 = this;
            monitor-enter(r13)
            long r0 = r13.mDirtyFlags     // Catch:{ all -> 0x0054 }
            r2 = 0
            r13.mDirtyFlags = r2     // Catch:{ all -> 0x0054 }
            monitor-exit(r13)     // Catch:{ all -> 0x0054 }
            com.webmd.medscape.live.explorelivevents.common.StyleManager r4 = r13.mStyleManager
            com.webmd.medscape.live.explorelivevents.data.SearchFilter r5 = r13.mSearchFilter
            r6 = 5
            long r6 = r6 & r0
            r8 = 0
            r9 = 0
            int r10 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r10 == 0) goto L_0x002c
            if (r4 == 0) goto L_0x001c
            com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer r4 = r4.getColors()
            goto L_0x001d
        L_0x001c:
            r4 = r8
        L_0x001d:
            if (r4 == 0) goto L_0x002c
            int r9 = r4.getFilterItemTextColor()
            int r6 = r4.getFilterItemBackgroundColor()
            int r4 = r4.getFilterCheckBoxSelectedColor()
            goto L_0x002e
        L_0x002c:
            r4 = 0
            r6 = 0
        L_0x002e:
            r11 = 6
            long r0 = r0 & r11
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L_0x003b
            if (r5 == 0) goto L_0x003b
            java.lang.String r8 = r5.getSpecialtyDisplay()
        L_0x003b:
            if (r10 == 0) goto L_0x004c
            android.widget.CheckBox r0 = r13.cbIsChecked
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.setTint(r0, r4)
            android.widget.LinearLayout r0 = r13.mboundView0
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintColor(r0, r6)
            android.widget.TextView r0 = r13.tvFilterName
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r0, r9)
        L_0x004c:
            if (r7 == 0) goto L_0x0053
            android.widget.TextView r0 = r13.tvFilterName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x0053:
            return
        L_0x0054:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x0054 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.SpecialtyItemBindingImpl.executeBindings():void");
    }
}
