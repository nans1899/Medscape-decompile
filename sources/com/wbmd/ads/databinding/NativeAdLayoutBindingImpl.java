package com.wbmd.ads.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.wbmd.ads.BR;
import com.wbmd.ads.generated.callback.OnClickListener;
import com.wbmd.ads.model.BaseNativeADViewModel;

public class NativeAdLayoutBindingImpl extends NativeAdLayoutBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback1;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public NativeAdLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private NativeAdLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[0], objArr[2]);
        this.mDirtyFlags = -1;
        this.label.setTag((Object) null);
        this.nativeAdLayout.setTag((Object) null);
        this.title.setTag((Object) null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
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
        if (BR.nativeViewModel != i) {
            return false;
        }
        setNativeViewModel((BaseNativeADViewModel) obj);
        return true;
    }

    public void setNativeViewModel(BaseNativeADViewModel baseNativeADViewModel) {
        this.mNativeViewModel = baseNativeADViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.nativeViewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r18 = this;
            r1 = r18
            monitor-enter(r18)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x00a1 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00a1 }
            monitor-exit(r18)     // Catch:{ all -> 0x00a1 }
            com.wbmd.ads.model.BaseNativeADViewModel r0 = r1.mNativeViewModel
            r6 = 3
            long r8 = r2 & r6
            r10 = 0
            r11 = 8
            r12 = 0
            int r13 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0058
            if (r0 == 0) goto L_0x002b
            boolean r8 = r0.getLabelVisibility()
            boolean r9 = r0.getTitleVisibility()
            java.lang.String r10 = r0.getTitle()
            java.lang.String r0 = r0.getLabel()
            goto L_0x002e
        L_0x002b:
            r0 = r10
            r8 = 0
            r9 = 0
        L_0x002e:
            if (r13 == 0) goto L_0x003e
            if (r8 == 0) goto L_0x0038
            r13 = 8
            long r2 = r2 | r13
            r13 = 128(0x80, double:6.32E-322)
            goto L_0x003d
        L_0x0038:
            r13 = 4
            long r2 = r2 | r13
            r13 = 64
        L_0x003d:
            long r2 = r2 | r13
        L_0x003e:
            long r13 = r2 & r6
            int r15 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x004c
            if (r9 == 0) goto L_0x0049
            r13 = 512(0x200, double:2.53E-321)
            goto L_0x004b
        L_0x0049:
            r13 = 256(0x100, double:1.265E-321)
        L_0x004b:
            long r2 = r2 | r13
        L_0x004c:
            if (r8 == 0) goto L_0x0050
            r13 = 0
            goto L_0x0052
        L_0x0050:
            r13 = 8
        L_0x0052:
            if (r9 == 0) goto L_0x0055
            goto L_0x005c
        L_0x0055:
            r14 = 8
            goto L_0x005d
        L_0x0058:
            r0 = r10
            r8 = 0
            r9 = 0
            r13 = 0
        L_0x005c:
            r14 = 0
        L_0x005d:
            long r15 = r2 & r6
            int r17 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r17 == 0) goto L_0x0074
            if (r8 == 0) goto L_0x0066
            r9 = 1
        L_0x0066:
            if (r17 == 0) goto L_0x0070
            if (r9 == 0) goto L_0x006d
            r15 = 32
            goto L_0x006f
        L_0x006d:
            r15 = 16
        L_0x006f:
            long r2 = r2 | r15
        L_0x0070:
            if (r9 == 0) goto L_0x0073
            r11 = 0
        L_0x0073:
            r12 = r11
        L_0x0074:
            long r6 = r6 & r2
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x0092
            android.widget.TextView r6 = r1.label
            r6.setVisibility(r13)
            android.widget.TextView r6 = r1.label
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
            android.widget.LinearLayout r0 = r1.nativeAdLayout
            r0.setVisibility(r12)
            android.widget.TextView r0 = r1.title
            r0.setVisibility(r14)
            android.widget.TextView r0 = r1.title
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x0092:
            r6 = 2
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00a0
            android.widget.LinearLayout r0 = r1.nativeAdLayout
            android.view.View$OnClickListener r2 = r1.mCallback1
            r0.setOnClickListener(r2)
        L_0x00a0:
            return
        L_0x00a1:
            r0 = move-exception
            monitor-exit(r18)     // Catch:{ all -> 0x00a1 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.ads.databinding.NativeAdLayoutBindingImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int i, View view) {
        BaseNativeADViewModel baseNativeADViewModel = this.mNativeViewModel;
        if (baseNativeADViewModel != null) {
            baseNativeADViewModel.performClick();
        }
    }
}
