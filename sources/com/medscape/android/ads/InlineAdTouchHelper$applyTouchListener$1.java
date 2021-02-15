package com.medscape.android.ads;

import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u0018\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"com/medscape/android/ads/InlineAdTouchHelper$applyTouchListener$1", "Landroidx/recyclerview/widget/RecyclerView$OnItemTouchListener;", "onInterceptTouchEvent", "", "rv", "Landroidx/recyclerview/widget/RecyclerView;", "e", "Landroid/view/MotionEvent;", "onRequestDisallowInterceptTouchEvent", "", "disallowIntercept", "onTouchEvent", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: InlineAdTouchHelper.kt */
public final class InlineAdTouchHelper$applyTouchListener$1 implements RecyclerView.OnItemTouchListener {
    final /* synthetic */ boolean $dispatchADBottomTouchEvent;
    final /* synthetic */ InlineAdTouchHelper this$0;

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(recyclerView, "rv");
        Intrinsics.checkNotNullParameter(motionEvent, "e");
    }

    InlineAdTouchHelper$applyTouchListener$1(InlineAdTouchHelper inlineAdTouchHelper, boolean z) {
        this.this$0 = inlineAdTouchHelper;
        this.$dispatchADBottomTouchEvent = z;
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(recyclerView, "rv");
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        try {
            View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            View findViewById = findChildViewUnder != null ? findChildViewUnder.findViewById(R.id.dfp_adLayout) : null;
            if (findViewById == null || findViewById.getVisibility() != 0) {
                return false;
            }
            if (!this.$dispatchADBottomTouchEvent || this.this$0.touchWithinTopHalf(motionEvent, findViewById)) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.this$0.yADTouchDistance = 0.0f;
                    this.this$0.xADTouchDistance = this.this$0.yADTouchDistance;
                    this.this$0.lastADTouchX = motionEvent.getX();
                    this.this$0.lastADTouchY = motionEvent.getY();
                    return false;
                } else if (action != 2) {
                    return false;
                } else {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    InlineAdTouchHelper inlineAdTouchHelper = this.this$0;
                    inlineAdTouchHelper.xADTouchDistance = inlineAdTouchHelper.xADTouchDistance + Math.abs(x - this.this$0.lastADTouchX);
                    InlineAdTouchHelper inlineAdTouchHelper2 = this.this$0;
                    inlineAdTouchHelper2.yADTouchDistance = inlineAdTouchHelper2.yADTouchDistance + Math.abs(y - this.this$0.lastADTouchY);
                    this.this$0.lastADTouchX = x;
                    this.this$0.lastADTouchY = y;
                    if (this.this$0.xADTouchDistance <= this.this$0.yADTouchDistance) {
                        return false;
                    }
                    findViewById.getParent().requestDisallowInterceptTouchEvent(true);
                    findViewById.getFocusable();
                    return false;
                }
            } else {
                findViewById.getParent().requestDisallowInterceptTouchEvent(true);
                findViewById.getFocusable();
                return false;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
