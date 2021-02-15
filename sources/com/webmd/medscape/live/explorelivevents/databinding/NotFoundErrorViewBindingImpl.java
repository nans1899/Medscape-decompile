package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;

public class NotFoundErrorViewBindingImpl extends NotFoundErrorViewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final TextView mboundView2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.id_logo, 3);
    }

    public NotFoundErrorViewBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private NotFoundErrorViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[3], objArr[1], objArr[0]);
        this.mDirtyFlags = -1;
        this.ivShutterstock.setTag((Object) null);
        TextView textView = objArr[2];
        this.mboundView2 = textView;
        textView.setTag((Object) null);
        this.notFoundLyt.setTag((Object) null);
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
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((ExploreEventsViewModel) obj);
        return true;
    }

    public void setViewModel(ExploreEventsViewModel exploreEventsViewModel) {
        this.mViewModel = exploreEventsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModelErrorObserver((MutableLiveData) obj, i2);
    }

    private boolean onChangeViewModelErrorObserver(MutableLiveData<Error> mutableLiveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r4v2 */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r9v4, types: [java.lang.String] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r14 = this;
            monitor-enter(r14)
            long r0 = r14.mDirtyFlags     // Catch:{ all -> 0x0065 }
            r2 = 0
            r14.mDirtyFlags = r2     // Catch:{ all -> 0x0065 }
            monitor-exit(r14)     // Catch:{ all -> 0x0065 }
            com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel r4 = r14.mViewModel
            r5 = 7
            long r7 = r0 & r5
            r9 = 0
            r10 = 0
            int r11 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r11 == 0) goto L_0x004f
            if (r4 == 0) goto L_0x001b
            androidx.lifecycle.MutableLiveData r4 = r4.getErrorObserver()
            goto L_0x001c
        L_0x001b:
            r4 = r9
        L_0x001c:
            r14.updateLiveDataRegistration(r10, r4)
            if (r4 == 0) goto L_0x0028
            java.lang.Object r4 = r4.getValue()
            com.webmd.medscape.live.explorelivevents.data.Error r4 = (com.webmd.medscape.live.explorelivevents.data.Error) r4
            goto L_0x0029
        L_0x0028:
            r4 = r9
        L_0x0029:
            if (r4 == 0) goto L_0x0038
            boolean r7 = r4.getShowError()
            java.lang.String r9 = r4.getMessage()
            android.graphics.drawable.Drawable r4 = r4.getErrorImage()
            goto L_0x003a
        L_0x0038:
            r4 = r9
            r7 = 0
        L_0x003a:
            if (r11 == 0) goto L_0x0044
            if (r7 == 0) goto L_0x0041
            r11 = 16
            goto L_0x0043
        L_0x0041:
            r11 = 8
        L_0x0043:
            long r0 = r0 | r11
        L_0x0044:
            if (r7 == 0) goto L_0x0047
            goto L_0x004b
        L_0x0047:
            r7 = 8
            r10 = 8
        L_0x004b:
            r13 = r9
            r9 = r4
            r4 = r13
            goto L_0x0050
        L_0x004f:
            r4 = r9
        L_0x0050:
            long r0 = r0 & r5
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0064
            android.widget.ImageView r0 = r14.ivShutterstock
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.setErrorImage((android.widget.ImageView) r0, (android.graphics.drawable.Drawable) r9)
            android.widget.TextView r0 = r14.mboundView2
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
            android.widget.RelativeLayout r0 = r14.notFoundLyt
            r0.setVisibility(r10)
        L_0x0064:
            return
        L_0x0065:
            r0 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x0065 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBindingImpl.executeBindings():void");
    }
}
