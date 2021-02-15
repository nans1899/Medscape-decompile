package com.wbmd.wbmdcommons.utils;

import android.animation.Animator;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/wbmd/wbmdcommons/utils/AnimUtil;", "", "()V", "fadeIn", "", "view", "Landroid/view/View;", "duration", "", "fadeOut", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AnimUtil.kt */
public final class AnimUtil {
    public static final AnimUtil INSTANCE = new AnimUtil();

    private AnimUtil() {
    }

    public final void fadeIn(View view, long j) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setVisibility(0);
        view.setAlpha(0.0f);
        view.animate().alpha(1.0f).setDuration(j).setListener((Animator.AnimatorListener) null);
    }

    public final void fadeOut(View view, long j) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getVisibility() == 0) {
            view.animate().alpha(0.0f).setDuration(j).setListener(new AnimUtil$fadeOut$1(view));
        }
    }
}
