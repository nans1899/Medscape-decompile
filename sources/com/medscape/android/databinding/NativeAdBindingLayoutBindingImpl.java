package com.medscape.android.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.Spanned;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.medscape.android.ads.NativeADViewModel;
import com.medscape.android.ads.NativeADViewModelKt;
import com.medscape.android.generated.callback.OnClickListener;

public class NativeAdBindingLayoutBindingImpl extends NativeAdBindingLayoutBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback1;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public NativeAdBindingLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private NativeAdBindingLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[5], objArr[6], objArr[1], objArr[0], objArr[2], objArr[4]);
        this.mDirtyFlags = -1;
        this.additionalLink1Text.setTag((Object) null);
        this.additionalLink2Text.setTag((Object) null);
        this.jobCode.setTag((Object) null);
        this.label.setTag((Object) null);
        this.nativeAdLayout.setTag((Object) null);
        this.title.setTag((Object) null);
        this.viewDivider.setTag((Object) null);
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
        if (16 != i) {
            return false;
        }
        setNativeViewModel((NativeADViewModel) obj);
        return true;
    }

    public void setNativeViewModel(NativeADViewModel nativeADViewModel) {
        this.mNativeViewModel = nativeADViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        int i2;
        String str;
        String str2;
        int i3;
        int i4;
        int i5;
        int i6;
        String str3;
        Spanned spanned;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String str4;
        String str5;
        boolean z5;
        boolean z6;
        boolean z7;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        NativeADViewModel nativeADViewModel = this.mNativeViewModel;
        Spanned spanned2 = null;
        int i7 = 0;
        int i8 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i8 != 0) {
            if (nativeADViewModel != null) {
                spanned2 = nativeADViewModel.getAdditionalLink1Url();
                z7 = nativeADViewModel.getAdditionalLink1TxtViewVisibility();
                z6 = nativeADViewModel.getAdditionalLink2TxtViewVisibility();
                z5 = nativeADViewModel.getTitleVisibility();
                str5 = nativeADViewModel.getTitle();
                str2 = nativeADViewModel.getLabel();
                str4 = nativeADViewModel.getJobCode();
                z4 = nativeADViewModel.getJobCodeVisibility();
                z3 = nativeADViewModel.getLabelVisibility();
                z2 = nativeADViewModel.getRootLayoutVisibility();
                z = nativeADViewModel.getLinkDividerVisibility();
                spanned = nativeADViewModel.getAdditionalLink2Url();
            } else {
                spanned = null;
                str5 = null;
                str2 = null;
                str4 = null;
                z7 = false;
                z6 = false;
                z5 = false;
                z4 = false;
                z3 = false;
                z2 = false;
                z = false;
            }
            if (i8 != 0) {
                j |= z7 ? PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID : 16384;
            }
            if ((j & 3) != 0) {
                j |= z6 ? PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : 1024;
            }
            if ((j & 3) != 0) {
                j |= z5 ? 128 : 64;
            }
            if ((j & 3) != 0) {
                j |= z4 ? PlaybackStateCompat.ACTION_PLAY_FROM_URI : PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            if ((j & 3) != 0) {
                j |= z3 ? 512 : 256;
            }
            if ((j & 3) != 0) {
                j |= z2 ? 32 : 16;
            }
            if ((j & 3) != 0) {
                j |= z ? 8 : 4;
            }
            int i9 = z7 ? 0 : 8;
            i6 = z6 ? 0 : 8;
            int i10 = z5 ? 0 : 8;
            int i11 = z4 ? 0 : 8;
            int i12 = z3 ? 0 : 8;
            int i13 = z2 ? 0 : 8;
            if (!z) {
                i7 = 8;
            }
            i = i7;
            i2 = i10;
            i5 = i11;
            i4 = i12;
            i7 = i9;
            str3 = str4;
            str = str5;
            i3 = i13;
        } else {
            spanned = null;
            str3 = null;
            str2 = null;
            str = null;
            i6 = 0;
            i5 = 0;
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        if ((3 & j) != 0) {
            this.additionalLink1Text.setVisibility(i7);
            NativeADViewModelKt.setClickableText(this.additionalLink1Text, spanned2);
            this.additionalLink2Text.setVisibility(i6);
            NativeADViewModelKt.setClickableText(this.additionalLink2Text, spanned);
            this.jobCode.setVisibility(i5);
            TextViewBindingAdapter.setText(this.jobCode, str3);
            this.label.setVisibility(i4);
            TextViewBindingAdapter.setText(this.label, str2);
            this.nativeAdLayout.setVisibility(i3);
            this.title.setVisibility(i2);
            TextViewBindingAdapter.setText(this.title, str);
            this.viewDivider.setVisibility(i);
        }
        if ((j & 2) != 0) {
            this.nativeAdLayout.setOnClickListener(this.mCallback1);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        NativeADViewModel nativeADViewModel = this.mNativeViewModel;
        if (nativeADViewModel != null) {
            nativeADViewModel.performClick();
        }
    }
}
