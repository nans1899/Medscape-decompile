package com.wbmd.decisionpoint.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.wbmd.decisionpoint.BR;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.viewmodels.HubViewModel;

public class FragmentHubBindingImpl extends FragmentHubBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.appBarLayout, 3);
        sViewsWithIds.put(R.id.toolbar, 4);
    }

    public FragmentHubBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private FragmentHubBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[3], objArr[2], objArr[1], objArr[4]);
        this.mDirtyFlags = -1;
        CoordinatorLayout coordinatorLayout = objArr[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag((Object) null);
        this.progressBar.setTag((Object) null);
        this.recyclerView.setTag((Object) null);
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
        if (BR.viewmodel != i) {
            return false;
        }
        setViewmodel((HubViewModel) obj);
        return true;
    }

    public void setViewmodel(HubViewModel hubViewModel) {
        this.mViewmodel = hubViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewmodelLoadingData((MutableLiveData) obj, i2);
    }

    private boolean onChangeViewmodelLoadingData(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: com.wbmd.decisionpoint.ui.adapters.HubAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: java.lang.Boolean} */
    /* JADX WARNING: type inference failed for: r11v0 */
    /* JADX WARNING: type inference failed for: r11v3 */
    /* JADX WARNING: type inference failed for: r11v6 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r15 = this;
            monitor-enter(r15)
            long r0 = r15.mDirtyFlags     // Catch:{ all -> 0x0069 }
            r2 = 0
            r15.mDirtyFlags = r2     // Catch:{ all -> 0x0069 }
            monitor-exit(r15)     // Catch:{ all -> 0x0069 }
            com.wbmd.decisionpoint.viewmodels.HubViewModel r4 = r15.mViewmodel
            r5 = 7
            long r7 = r0 & r5
            r9 = 6
            r11 = 0
            r12 = 0
            int r13 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r13 == 0) goto L_0x0053
            long r7 = r0 & r9
            int r14 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r14 == 0) goto L_0x0023
            if (r4 == 0) goto L_0x0023
            com.wbmd.decisionpoint.ui.adapters.HubAdapter r7 = r4.getHubAdapter()
            goto L_0x0024
        L_0x0023:
            r7 = r11
        L_0x0024:
            if (r4 == 0) goto L_0x002b
            androidx.lifecycle.MutableLiveData r4 = r4.getLoadingData()
            goto L_0x002c
        L_0x002b:
            r4 = r11
        L_0x002c:
            r15.updateLiveDataRegistration(r12, r4)
            if (r4 == 0) goto L_0x0038
            java.lang.Object r4 = r4.getValue()
            r11 = r4
            java.lang.Boolean r11 = (java.lang.Boolean) r11
        L_0x0038:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            r8 = 1
            if (r4 != r8) goto L_0x0040
            goto L_0x0041
        L_0x0040:
            r8 = 0
        L_0x0041:
            if (r13 == 0) goto L_0x004b
            if (r8 == 0) goto L_0x0048
            r13 = 16
            goto L_0x004a
        L_0x0048:
            r13 = 8
        L_0x004a:
            long r0 = r0 | r13
        L_0x004b:
            if (r8 == 0) goto L_0x004e
            goto L_0x0052
        L_0x004e:
            r4 = 8
            r12 = 8
        L_0x0052:
            r11 = r7
        L_0x0053:
            long r4 = r0 & r5
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x005e
            android.widget.ProgressBar r4 = r15.progressBar
            r4.setVisibility(r12)
        L_0x005e:
            long r0 = r0 & r9
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0068
            androidx.recyclerview.widget.RecyclerView r0 = r15.recyclerView
            com.wbmd.decisionpoint.ui.adapters.HubAdapterKt.setHubAdapter(r0, r11)
        L_0x0068:
            return
        L_0x0069:
            r0 = move-exception
            monitor-exit(r15)     // Catch:{ all -> 0x0069 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.decisionpoint.databinding.FragmentHubBindingImpl.executeBindings():void");
    }
}
