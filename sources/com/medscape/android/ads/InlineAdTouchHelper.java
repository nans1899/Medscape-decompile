package com.medscape.android.ads;

import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ\u001c\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/medscape/android/ads/InlineAdTouchHelper;", "", "()V", "lastADTouchX", "", "lastADTouchY", "xADTouchDistance", "yADTouchDistance", "applyTouchListener", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dispatchADBottomTouchEvent", "", "touchWithinTopHalf", "event", "Landroid/view/MotionEvent;", "view", "Landroid/view/View;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: InlineAdTouchHelper.kt */
public final class InlineAdTouchHelper {
    /* access modifiers changed from: private */
    public float lastADTouchX;
    /* access modifiers changed from: private */
    public float lastADTouchY;
    /* access modifiers changed from: private */
    public float xADTouchDistance;
    /* access modifiers changed from: private */
    public float yADTouchDistance;

    public static /* synthetic */ void applyTouchListener$default(InlineAdTouchHelper inlineAdTouchHelper, RecyclerView recyclerView, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        inlineAdTouchHelper.applyTouchListener(recyclerView, z);
    }

    public final void applyTouchListener(RecyclerView recyclerView, boolean z) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        float f = (float) 0;
        this.xADTouchDistance = f;
        this.yADTouchDistance = f;
        this.lastADTouchX = f;
        this.lastADTouchY = f;
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.addOnItemTouchListener(new InlineAdTouchHelper$applyTouchListener$1(this, z));
    }

    /* access modifiers changed from: private */
    public final boolean touchWithinTopHalf(MotionEvent motionEvent, View view) {
        if (motionEvent == null || view == null || view.getWidth() == 0 || view.getHeight() == 0) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int height = iArr[1] + (view.getHeight() / 2);
        if (motionEvent.getRawX() > ((float) ((iArr[0] + view.getWidth()) - 1)) || motionEvent.getRawX() < ((float) iArr[0]) || motionEvent.getRawY() > ((float) height) || motionEvent.getRawY() < ((float) iArr[1])) {
            return false;
        }
        return true;
    }
}
