package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.data.UiState;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;

public class FragmentEventsBindingImpl extends FragmentEventsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(8);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"not_found_error_view"}, new int[]{4}, new int[]{R.layout.not_found_error_view});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.actionBar, 5);
        sViewsWithIds.put(R.id.toolbar, 6);
        sViewsWithIds.put(R.id.filters, 7);
    }

    public FragmentEventsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private FragmentEventsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, objArr[5], objArr[1], objArr[7], objArr[4], objArr[3], objArr[2], objArr[6]);
        this.mDirtyFlags = -1;
        this.constraintView.setTag((Object) null);
        CoordinatorLayout coordinatorLayout = objArr[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag((Object) null);
        this.pbLoader.setTag((Object) null);
        this.rvItems.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.lytError.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.lytError.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBinding r0 = r6.lytError
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.FragmentEventsBindingImpl.hasPendingBindings():boolean");
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
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.lytError.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelUiState((MutableLiveData) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeLytError((NotFoundErrorViewBinding) obj, i2);
    }

    private boolean onChangeViewModelUiState(MutableLiveData<UiState> mutableLiveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLytError(NotFoundErrorViewBinding notFoundErrorViewBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        boolean z;
        boolean z2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ExploreEventsViewModel exploreEventsViewModel = this.mViewModel;
        int i2 = 0;
        int i3 = ((j & 13) > 0 ? 1 : ((j & 13) == 0 ? 0 : -1));
        if (i3 != 0) {
            UiState uiState = null;
            MutableLiveData<UiState> uiState2 = exploreEventsViewModel != null ? exploreEventsViewModel.getUiState() : null;
            updateLiveDataRegistration(0, uiState2);
            if (uiState2 != null) {
                uiState = uiState2.getValue();
            }
            if (uiState != null) {
                z2 = uiState.getLoading();
                z = uiState.getHasData();
            } else {
                z2 = false;
                z = false;
            }
            if (i3 != 0) {
                j |= z2 ? 128 : 64;
            }
            if ((j & 13) != 0) {
                j |= z ? 32 : 16;
            }
            int i4 = z2 ? 0 : 8;
            if (!z) {
                i2 = 8;
            }
            int i5 = i2;
            i2 = i4;
            i = i5;
        } else {
            i = 0;
        }
        if ((j & 13) != 0) {
            this.pbLoader.setVisibility(i2);
            this.rvItems.setVisibility(i);
        }
        executeBindingsOn(this.lytError);
    }
}
