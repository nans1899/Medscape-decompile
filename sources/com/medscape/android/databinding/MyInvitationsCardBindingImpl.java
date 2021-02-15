package com.medscape.android.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.medscape.android.R;
import com.medscape.android.myinvites.specific.Invitation;

public class MyInvitationsCardBindingImpl extends MyInvitationsCardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.root_container, 7);
    }

    public MyInvitationsCardBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private MyInvitationsCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[7], objArr[6], objArr[4], objArr[2], objArr[5], objArr[3]);
        this.mDirtyFlags = -1;
        this.lineFeatured.setTag((Object) null);
        FrameLayout frameLayout = objArr[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag((Object) null);
        this.textCta.setTag((Object) null);
        this.textDescription.setTag((Object) null);
        this.textFeatured.setTag((Object) null);
        this.textInfo.setTag((Object) null);
        this.textTitle.setTag((Object) null);
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
        if (9 != i) {
            return false;
        }
        setInvitation((Invitation) obj);
        return true;
    }

    public void setInvitation(Invitation invitation) {
        this.mInvitation = invitation;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r28 = this;
            r1 = r28
            monitor-enter(r28)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0132 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0132 }
            monitor-exit(r28)     // Catch:{ all -> 0x0132 }
            com.medscape.android.myinvites.specific.Invitation r0 = r1.mInvitation
            r6 = 0
            r7 = 3
            long r9 = r2 & r7
            r12 = 4
            r15 = 0
            r16 = 0
            int r17 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r17 == 0) goto L_0x00c3
            if (r0 == 0) goto L_0x0031
            java.lang.String r15 = r0.getDescription()
            boolean r6 = r0.isFeatured()
            java.lang.String r9 = r0.getTitle()
            java.lang.String r10 = r0.getCta()
            java.lang.String r0 = r0.getInfo()
            goto L_0x0035
        L_0x0031:
            r0 = r15
            r9 = r0
            r10 = r9
            r6 = 0
        L_0x0035:
            if (r17 == 0) goto L_0x0048
            if (r6 == 0) goto L_0x0040
            r17 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r17
            r17 = 8192(0x2000, double:4.0474E-320)
            goto L_0x0046
        L_0x0040:
            r17 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r17
            r17 = 4096(0x1000, double:2.0237E-320)
        L_0x0046:
            long r2 = r2 | r17
        L_0x0048:
            if (r15 == 0) goto L_0x004f
            boolean r17 = r15.isEmpty()
            goto L_0x0051
        L_0x004f:
            r17 = 0
        L_0x0051:
            long r18 = r2 & r7
            int r20 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x0060
            if (r17 == 0) goto L_0x005c
            r18 = 512(0x200, double:2.53E-321)
            goto L_0x005e
        L_0x005c:
            r18 = 256(0x100, double:1.265E-321)
        L_0x005e:
            long r2 = r2 | r18
        L_0x0060:
            android.widget.TextView r11 = r1.textTitle
            android.content.res.Resources r11 = r11.getResources()
            if (r6 == 0) goto L_0x006c
            r14 = 2131165478(0x7f070126, float:1.7945174E38)
            goto L_0x006f
        L_0x006c:
            r14 = 2131165477(0x7f070125, float:1.7945172E38)
        L_0x006f:
            float r11 = r11.getDimension(r14)
            if (r6 == 0) goto L_0x0077
            r6 = 0
            goto L_0x0079
        L_0x0077:
            r6 = 8
        L_0x0079:
            if (r10 != 0) goto L_0x007d
            r14 = 1
            goto L_0x007e
        L_0x007d:
            r14 = 0
        L_0x007e:
            long r20 = r2 & r7
            int r22 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x008c
            if (r14 == 0) goto L_0x008b
            r20 = 8
            long r2 = r2 | r20
            goto L_0x008c
        L_0x008b:
            long r2 = r2 | r12
        L_0x008c:
            if (r9 == 0) goto L_0x0093
            boolean r20 = r9.isEmpty()
            goto L_0x0095
        L_0x0093:
            r20 = 0
        L_0x0095:
            long r21 = r2 & r7
            int r23 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x00a4
            if (r20 == 0) goto L_0x00a0
            r21 = 128(0x80, double:6.32E-322)
            goto L_0x00a2
        L_0x00a0:
            r21 = 64
        L_0x00a2:
            long r2 = r2 | r21
        L_0x00a4:
            if (r17 == 0) goto L_0x00a9
            r17 = 8
            goto L_0x00ab
        L_0x00a9:
            r17 = 0
        L_0x00ab:
            if (r20 == 0) goto L_0x00b0
            r20 = 8
            goto L_0x00b2
        L_0x00b0:
            r20 = 0
        L_0x00b2:
            r24 = r17
            r25 = r20
            r26 = r9
            r9 = r0
            r0 = r6
            r6 = r11
            r11 = r26
            r27 = r15
            r15 = r10
            r10 = r27
            goto L_0x00cc
        L_0x00c3:
            r9 = r15
            r10 = r9
            r11 = r10
            r0 = 0
            r14 = 0
            r24 = 0
            r25 = 0
        L_0x00cc:
            long r12 = r12 & r2
            int r17 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r17 == 0) goto L_0x00d8
            if (r15 == 0) goto L_0x00d8
            boolean r12 = r15.isEmpty()
            goto L_0x00d9
        L_0x00d8:
            r12 = 0
        L_0x00d9:
            long r20 = r2 & r7
            int r13 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00f5
            if (r14 == 0) goto L_0x00e4
            r18 = 1
            goto L_0x00e6
        L_0x00e4:
            r18 = r12
        L_0x00e6:
            if (r13 == 0) goto L_0x00f0
            if (r18 == 0) goto L_0x00ed
            r12 = 32
            goto L_0x00ef
        L_0x00ed:
            r12 = 16
        L_0x00ef:
            long r2 = r2 | r12
        L_0x00f0:
            if (r18 == 0) goto L_0x00f5
            r14 = 8
            goto L_0x00f6
        L_0x00f5:
            r14 = 0
        L_0x00f6:
            long r2 = r2 & r7
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0131
            android.view.View r2 = r1.lineFeatured
            r2.setVisibility(r0)
            android.widget.TextView r2 = r1.textCta
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r15)
            android.widget.TextView r2 = r1.textCta
            r2.setVisibility(r14)
            android.widget.TextView r2 = r1.textDescription
            com.medscape.android.myinvites.MyInvitationsAdapterKt.setTextToHtml(r2, r10)
            android.widget.TextView r2 = r1.textDescription
            r3 = r24
            r2.setVisibility(r3)
            android.widget.TextView r2 = r1.textFeatured
            r2.setVisibility(r0)
            android.widget.TextView r0 = r1.textInfo
            com.medscape.android.myinvites.MyInvitationsAdapterKt.setTextToHtml(r0, r9)
            android.widget.TextView r0 = r1.textTitle
            androidx.databinding.adapters.ViewBindingAdapter.setPaddingTop(r0, r6)
            android.widget.TextView r0 = r1.textTitle
            com.medscape.android.myinvites.MyInvitationsAdapterKt.setTextToHtml(r0, r11)
            android.widget.TextView r0 = r1.textTitle
            r2 = r25
            r0.setVisibility(r2)
        L_0x0131:
            return
        L_0x0132:
            r0 = move-exception
            monitor-exit(r28)     // Catch:{ all -> 0x0132 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.databinding.MyInvitationsCardBindingImpl.executeBindings():void");
    }
}
