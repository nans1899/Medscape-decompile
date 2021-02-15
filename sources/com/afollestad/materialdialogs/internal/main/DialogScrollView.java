package com.afollestad.materialdialogs.internal.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.afollestad.materialdialogs.utils.MDUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0011H\u0014J(\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0014R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/afollestad/materialdialogs/internal/main/DialogScrollView;", "Landroid/widget/ScrollView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "isScrollable", "", "()Z", "rootView", "Lcom/afollestad/materialdialogs/internal/main/DialogLayout;", "getRootView", "()Lcom/afollestad/materialdialogs/internal/main/DialogLayout;", "setRootView", "(Lcom/afollestad/materialdialogs/internal/main/DialogLayout;)V", "invalidateDividers", "", "invalidateOverScroll", "onAttachedToWindow", "onScrollChanged", "left", "", "top", "oldl", "oldt", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: DialogScrollView.kt */
public final class DialogScrollView extends ScrollView {
    private DialogLayout rootView;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DialogScrollView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public DialogScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final DialogLayout getRootView() {
        return this.rootView;
    }

    public final void setRootView(DialogLayout dialogLayout) {
        this.rootView = dialogLayout;
    }

    private final boolean isScrollable() {
        View childAt = getChildAt(0);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(0)");
        return childAt.getMeasuredHeight() > getHeight();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MDUtil.INSTANCE.waitForWidth(this, DialogScrollView$onAttachedToWindow$1.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        invalidateDividers();
    }

    public final void invalidateDividers() {
        boolean z = false;
        if (getChildCount() == 0 || getMeasuredHeight() == 0 || !isScrollable()) {
            DialogLayout dialogLayout = this.rootView;
            if (dialogLayout != null) {
                dialogLayout.invalidateDividers(false, false);
                return;
            }
            return;
        }
        View childAt = getChildAt(getChildCount() - 1);
        Intrinsics.checkExpressionValueIsNotNull(childAt, "view");
        int bottom = childAt.getBottom() - (getMeasuredHeight() + getScrollY());
        DialogLayout dialogLayout2 = this.rootView;
        if (dialogLayout2 != null) {
            boolean z2 = getScrollY() > 0;
            if (bottom > 0) {
                z = true;
            }
            dialogLayout2.invalidateDividers(z2, z);
        }
    }

    /* access modifiers changed from: private */
    public final void invalidateOverScroll() {
        setOverScrollMode((getChildCount() == 0 || getMeasuredHeight() == 0 || !isScrollable()) ? 2 : 1);
    }
}
