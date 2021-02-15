package com.webmd.medscape.live.explorelivevents.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt;
import com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer;
import com.webmd.medscape.live.explorelivevents.common.ExploreFontsContainer;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.UiState;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;

public class FragmentExploreBindingImpl extends FragmentExploreBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;
    private final ConstraintLayout mboundView1;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(17);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"not_found_error_view"}, new int[]{7}, new int[]{R.layout.not_found_error_view});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.actionBar, 8);
        sViewsWithIds.put(R.id.toolbar, 9);
        sViewsWithIds.put(R.id.scroll_view, 10);
        sViewsWithIds.put(R.id.lyt_intro, 11);
        sViewsWithIds.put(R.id.lyt_anim, 12);
        sViewsWithIds.put(R.id.lyt_filters, 13);
        sViewsWithIds.put(R.id.divider, 14);
        sViewsWithIds.put(R.id.lyt_events_happening, 15);
        sViewsWithIds.put(R.id.vertical_events_view, 16);
    }

    public FragmentExploreBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private FragmentExploreBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[8], objArr[6], objArr[14], objArr[12], objArr[15], objArr[13], objArr[11], objArr[7], objArr[5], objArr[4], objArr[10], objArr[9], objArr[2], objArr[3], objArr[16]);
        this.mDirtyFlags = -1;
        this.btShowAll.setTag((Object) null);
        CoordinatorLayout coordinatorLayout = objArr[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.pbLoader.setTag((Object) null);
        this.rvEvents.setTag((Object) null);
        this.tvDescription.setTag((Object) null);
        this.tvEventsHappening.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
        }
        this.notFound.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.notFound.hasPendingBindings() == false) goto L_0x0016;
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
            com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBinding r0 = r6.notFound
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
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.FragmentExploreBindingImpl.hasPendingBindings():boolean");
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.styleManager == i) {
            setStyleManager((StyleManager) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((ExploreEventsViewModel) obj);
        }
        return true;
    }

    public void setStyleManager(StyleManager styleManager) {
        this.mStyleManager = styleManager;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.styleManager);
        super.requestRebind();
    }

    public void setViewModel(ExploreEventsViewModel exploreEventsViewModel) {
        this.mViewModel = exploreEventsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.notFound.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelUiState((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeViewModelErrorObserver((MutableLiveData) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeNotFound((NotFoundErrorViewBinding) obj, i2);
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

    private boolean onChangeViewModelErrorObserver(MutableLiveData<Error> mutableLiveData, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeNotFound(NotFoundErrorViewBinding notFoundErrorViewBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        ExploreColorsContainer exploreColorsContainer;
        ExploreFontsContainer exploreFontsContainer;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        StyleManager styleManager = this.mStyleManager;
        ExploreEventsViewModel exploreEventsViewModel = this.mViewModel;
        int i10 = 0;
        if ((j & 40) != 0) {
            if (styleManager != null) {
                exploreColorsContainer = styleManager.getColors();
                exploreFontsContainer = styleManager.getFonts();
            } else {
                exploreFontsContainer = null;
                exploreColorsContainer = null;
            }
            if (exploreColorsContainer != null) {
                i2 = exploreColorsContainer.getBigTitleTextColor();
                i = exploreColorsContainer.getShowAllButtonTextColor();
                i3 = exploreColorsContainer.getBigHeadingTextColor();
            } else {
                i3 = 0;
                i2 = 0;
                i = 0;
            }
            i4 = exploreFontsContainer != null ? exploreFontsContainer.getBoldFont() : 0;
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        if ((51 & j) != 0) {
            int i11 = ((j & 49) > 0 ? 1 : ((j & 49) == 0 ? 0 : -1));
            if (i11 != 0) {
                MutableLiveData<UiState> uiState = exploreEventsViewModel != null ? exploreEventsViewModel.getUiState() : null;
                updateLiveDataRegistration(0, uiState);
                UiState value = uiState != null ? uiState.getValue() : null;
                if (value != null) {
                    z3 = value.getHasData();
                    z2 = value.getLoading();
                    z4 = value.getShowMoreItems();
                } else {
                    z4 = false;
                    z3 = false;
                    z2 = false;
                }
                if (i11 != 0) {
                    j |= z3 ? PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : 1024;
                }
                if ((j & 49) != 0) {
                    j |= z2 ? 512 : 256;
                }
                if ((j & 49) != 0) {
                    j |= z4 ? 128 : 64;
                }
                i5 = z3 ? 0 : 8;
                i8 = z2 ? 0 : 8;
                i9 = z4 ? 0 : 8;
            } else {
                i9 = 0;
                i5 = 0;
                i8 = 0;
            }
            int i12 = ((j & 50) > 0 ? 1 : ((j & 50) == 0 ? 0 : -1));
            if (i12 != 0) {
                MutableLiveData<Error> errorObserver = exploreEventsViewModel != null ? exploreEventsViewModel.getErrorObserver() : null;
                updateLiveDataRegistration(1, errorObserver);
                Error value2 = errorObserver != null ? errorObserver.getValue() : null;
                if (value2 != null) {
                    z = value2.getShowError();
                } else {
                    z = false;
                }
                if (i12 != 0) {
                    j |= z ? PlaybackStateCompat.ACTION_PLAY_FROM_URI : PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                }
                if (z) {
                    i10 = 8;
                }
                i6 = i10;
                i10 = i9;
                i7 = i8;
            } else {
                i10 = i9;
                i7 = i8;
                i6 = 0;
            }
        } else {
            i7 = 0;
            i6 = 0;
            i5 = 0;
        }
        if ((j & 49) != 0) {
            this.btShowAll.setVisibility(i10);
            this.pbLoader.setVisibility(i7);
            this.rvEvents.setVisibility(i5);
        }
        if ((j & 40) != 0) {
            BindingAdaptersKt.paintTextColor(this.btShowAll, i);
            BindingAdaptersKt.setFont(this.tvDescription, i4);
            BindingAdaptersKt.paintTextColor(this.tvDescription, i3);
            BindingAdaptersKt.paintTextColor(this.tvEventsHappening, i2);
        }
        if ((j & 50) != 0) {
            this.tvEventsHappening.setVisibility(i6);
        }
        executeBindingsOn(this.notFound);
    }
}
