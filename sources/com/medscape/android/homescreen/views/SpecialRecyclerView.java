package com.medscape.android.homescreen.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/medscape/android/homescreen/views/SpecialRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "recylerviewReleased", "Lkotlin/Function0;", "", "getRecylerviewReleased", "()Lkotlin/jvm/functions/Function0;", "setRecylerviewReleased", "(Lkotlin/jvm/functions/Function0;)V", "specialClick", "getSpecialClick", "setSpecialClick", "onInterceptTouchEvent", "", "event", "Landroid/view/MotionEvent;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SpecialRecyclerView.kt */
public final class SpecialRecyclerView extends RecyclerView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static float fabButtonTouchY = -1.0f;
    /* access modifiers changed from: private */
    public static boolean isFabButtonSwipeReleased;
    private HashMap _$_findViewCache;
    private Function0<Unit> recylerviewReleased;
    private Function0<Unit> specialClick;

    public SpecialRecyclerView(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
    }

    public SpecialRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SpecialRecyclerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SpecialRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final Function0<Unit> getSpecialClick() {
        return this.specialClick;
    }

    public final void setSpecialClick(Function0<Unit> function0) {
        this.specialClick = function0;
    }

    public final Function0<Unit> getRecylerviewReleased() {
        return this.recylerviewReleased;
    }

    public final void setRecylerviewReleased(Function0<Unit> function0) {
        this.recylerviewReleased = function0;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Function0<Unit> function0;
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        if (fabButtonTouchY != -1.0f) {
            isFabButtonSwipeReleased = false;
            if (motionEvent.getAction() == 0 && Math.abs(motionEvent.getRawY() - fabButtonTouchY) > ((float) 30) && (function0 = this.recylerviewReleased) != null) {
                Unit invoke = function0.invoke();
            }
            if (motionEvent.getAction() == 1) {
                if (Math.abs(motionEvent.getRawY() - fabButtonTouchY) < ((float) 30)) {
                    Function0<Unit> function02 = this.specialClick;
                    if (function02 != null) {
                        Unit invoke2 = function02.invoke();
                    }
                    Function0<Unit> function03 = this.recylerviewReleased;
                    if (function03 != null) {
                        Unit invoke3 = function03.invoke();
                    }
                    requestDisallowInterceptTouchEvent(true);
                    return true;
                }
                Function0<Unit> function04 = this.recylerviewReleased;
                if (function04 != null) {
                    Unit invoke4 = function04.invoke();
                }
            }
            if (motionEvent.getAction() == 2) {
                isFabButtonSwipeReleased = true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/medscape/android/homescreen/views/SpecialRecyclerView$Companion;", "", "()V", "fabButtonTouchY", "", "getFabButtonTouchY", "()F", "setFabButtonTouchY", "(F)V", "isFabButtonSwipeReleased", "", "()Z", "setFabButtonSwipeReleased", "(Z)V", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SpecialRecyclerView.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final float getFabButtonTouchY() {
            return SpecialRecyclerView.fabButtonTouchY;
        }

        public final void setFabButtonTouchY(float f) {
            SpecialRecyclerView.fabButtonTouchY = f;
        }

        public final boolean isFabButtonSwipeReleased() {
            return SpecialRecyclerView.isFabButtonSwipeReleased;
        }

        public final void setFabButtonSwipeReleased(boolean z) {
            SpecialRecyclerView.isFabButtonSwipeReleased = z;
        }
    }
}
