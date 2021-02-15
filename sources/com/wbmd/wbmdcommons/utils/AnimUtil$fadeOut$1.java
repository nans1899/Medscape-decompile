package com.wbmd.wbmdcommons.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wbmd/wbmdcommons/utils/AnimUtil$fadeOut$1", "Landroid/animation/AnimatorListenerAdapter;", "onAnimationEnd", "", "animation", "Landroid/animation/Animator;", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AnimUtil.kt */
public final class AnimUtil$fadeOut$1 extends AnimatorListenerAdapter {
    final /* synthetic */ View $view;

    AnimUtil$fadeOut$1(View view) {
        this.$view = view;
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        this.$view.setVisibility(8);
    }
}
