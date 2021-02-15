package com.webmd.medscape.live.explorelivevents.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.BR;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;

public class EventItemBindingImpl extends EventItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public EventItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private EventItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1], objArr[4], objArr[3], objArr[5]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.tvAccreditation.setTag((Object) null);
        this.tvEventDate.setTag((Object) null);
        this.tvEventDescription.setTag((Object) null);
        this.tvEventTitle.setTag((Object) null);
        this.tvLocation.setTag((Object) null);
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
        if (BR.liveEvent == i) {
            setLiveEvent((LiveEvent) obj);
        } else if (BR.styleManager != i) {
            return false;
        } else {
            setStyleManager((StyleManager) obj);
        }
        return true;
    }

    public void setLiveEvent(LiveEvent liveEvent) {
        this.mLiveEvent = liveEvent;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.liveEvent);
        super.requestRebind();
    }

    public void setStyleManager(StyleManager styleManager) {
        this.mStyleManager = styleManager;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.styleManager);
        super.requestRebind();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer} */
    /* JADX WARNING: type inference failed for: r15v0 */
    /* JADX WARNING: type inference failed for: r15v1, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r15v8 */
    /* JADX WARNING: type inference failed for: r15v9 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r25 = this;
            r1 = r25
            monitor-enter(r25)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x010d }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x010d }
            monitor-exit(r25)     // Catch:{ all -> 0x010d }
            com.webmd.medscape.live.explorelivevents.data.LiveEvent r0 = r1.mLiveEvent
            com.webmd.medscape.live.explorelivevents.common.StyleManager r6 = r1.mStyleManager
            r7 = 7
            long r9 = r2 & r7
            r11 = 5
            r13 = 6
            r15 = 0
            r16 = 0
            int r17 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r17 == 0) goto L_0x00ae
            long r9 = r2 & r11
            int r17 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r17 == 0) goto L_0x0052
            if (r0 == 0) goto L_0x003a
            java.lang.String r9 = r0.getTitle()
            boolean r10 = r0.isAccredited()
            java.lang.String r18 = r0.getDate()
            java.lang.String r19 = r0.getLocation()
            java.lang.String r20 = r0.getHeader()
            goto L_0x0042
        L_0x003a:
            r9 = r15
            r18 = r9
            r19 = r18
            r20 = r19
            r10 = 0
        L_0x0042:
            if (r17 == 0) goto L_0x004d
            if (r10 == 0) goto L_0x0049
            r21 = 16
            goto L_0x004b
        L_0x0049:
            r21 = 8
        L_0x004b:
            long r2 = r2 | r21
        L_0x004d:
            if (r10 == 0) goto L_0x0050
            goto L_0x0059
        L_0x0050:
            r10 = 4
            goto L_0x005a
        L_0x0052:
            r9 = r15
            r18 = r9
            r19 = r18
            r20 = r19
        L_0x0059:
            r10 = 0
        L_0x005a:
            if (r6 == 0) goto L_0x0060
            com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer r15 = r6.getColors()
        L_0x0060:
            long r21 = r2 & r13
            int r6 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x007d
            if (r15 == 0) goto L_0x007d
            int r6 = r15.getEventTitleTextColor()
            int r17 = r15.getEventLocationTextColor()
            int r21 = r15.getEventItemBackgroundColor()
            int r22 = r15.getEventDateTextColor()
            int r23 = r15.getEventAccreditationTextColor()
            goto L_0x0086
        L_0x007d:
            r6 = 0
            r17 = 0
            r21 = 0
            r22 = 0
            r23 = 0
        L_0x0086:
            if (r15 == 0) goto L_0x009a
            int r16 = r15.getEventIconTintColor()
            r12 = r6
            r24 = r16
            r15 = r18
            r6 = r20
            r7 = r21
            r11 = r22
            r8 = r23
            goto L_0x00a7
        L_0x009a:
            r12 = r6
            r15 = r18
            r6 = r20
            r7 = r21
            r11 = r22
            r8 = r23
            r24 = 0
        L_0x00a7:
            r20 = r0
            r0 = r17
            r21 = r19
            goto L_0x00bc
        L_0x00ae:
            r20 = r0
            r6 = r15
            r9 = r6
            r21 = r9
            r0 = 0
            r7 = 0
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r24 = 0
        L_0x00bc:
            long r13 = r13 & r2
            int r22 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x00da
            androidx.constraintlayout.widget.ConstraintLayout r13 = r1.mboundView0
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintColor(r13, r7)
            android.widget.TextView r7 = r1.tvAccreditation
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r7, r8)
            android.widget.TextView r7 = r1.tvEventDate
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r7, r11)
            android.widget.TextView r7 = r1.tvEventTitle
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r7, r12)
            android.widget.TextView r7 = r1.tvLocation
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.paintTextColor(r7, r0)
        L_0x00da:
            r7 = 5
            long r7 = r7 & r2
            int r0 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00fc
            android.widget.TextView r0 = r1.tvAccreditation
            r0.setVisibility(r10)
            android.widget.TextView r0 = r1.tvEventDate
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r15)
            android.widget.TextView r0 = r1.tvEventDescription
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
            android.widget.TextView r0 = r1.tvEventTitle
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r9)
            android.widget.TextView r0 = r1.tvLocation
            r15 = r21
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r15)
        L_0x00fc:
            r6 = 7
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x010c
            android.widget.TextView r0 = r1.tvLocation
            r2 = r20
            r3 = r24
            com.webmd.medscape.live.explorelivevents.common.BindingAdaptersKt.setIconLeft(r0, r2, r3)
        L_0x010c:
            return
        L_0x010d:
            r0 = move-exception
            monitor-exit(r25)     // Catch:{ all -> 0x010d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.EventItemBindingImpl.executeBindings():void");
    }
}
