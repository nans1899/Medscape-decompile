package com.google.firebase.inappmessaging.display.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Application;
import android.graphics.Point;
import android.view.View;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class FiamAnimator {

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    public interface AnimationCompleteListener {
        void onComplete();
    }

    @Inject
    FiamAnimator() {
    }

    public void slideIntoView(final Application application, final View view, Position position) {
        view.setAlpha(0.0f);
        Point access$000 = Position.getPoint(position, view);
        view.animate().translationX((float) access$000.x).translationY((float) access$000.y).setDuration(1).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view.animate().translationX(0.0f).translationY(0.0f).alpha(1.0f).setDuration((long) application.getResources().getInteger(17694722)).setListener((Animator.AnimatorListener) null);
            }
        });
    }

    public void slideOutOfView(Application application, View view, Position position, final AnimationCompleteListener animationCompleteListener) {
        Point access$000 = Position.getPoint(position, view);
        view.animate().translationX((float) access$000.x).translationY((float) access$000.y).setDuration((long) application.getResources().getInteger(17694722)).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                animationCompleteListener.onComplete();
            }
        });
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    public enum Position {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM;

        /* access modifiers changed from: private */
        public static Point getPoint(Position position, View view) {
            view.measure(-2, -2);
            int i = AnonymousClass3.$SwitchMap$com$google$firebase$inappmessaging$display$internal$FiamAnimator$Position[position.ordinal()];
            if (i == 1) {
                return new Point(view.getMeasuredWidth() * -1, 0);
            }
            if (i == 2) {
                return new Point(view.getMeasuredWidth() * 1, 0);
            }
            if (i == 3) {
                return new Point(0, view.getMeasuredHeight() * -1);
            }
            if (i != 4) {
                return new Point(0, view.getMeasuredHeight() * -1);
            }
            return new Point(0, view.getMeasuredHeight() * 1);
        }
    }

    /* renamed from: com.google.firebase.inappmessaging.display.internal.FiamAnimator$3  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$inappmessaging$display$internal$FiamAnimator$Position;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.firebase.inappmessaging.display.internal.FiamAnimator$Position[] r0 = com.google.firebase.inappmessaging.display.internal.FiamAnimator.Position.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$inappmessaging$display$internal$FiamAnimator$Position = r0
                com.google.firebase.inappmessaging.display.internal.FiamAnimator$Position r1 = com.google.firebase.inappmessaging.display.internal.FiamAnimator.Position.LEFT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$display$internal$FiamAnimator$Position     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.inappmessaging.display.internal.FiamAnimator$Position r1 = com.google.firebase.inappmessaging.display.internal.FiamAnimator.Position.RIGHT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$display$internal$FiamAnimator$Position     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.inappmessaging.display.internal.FiamAnimator$Position r1 = com.google.firebase.inappmessaging.display.internal.FiamAnimator.Position.TOP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$display$internal$FiamAnimator$Position     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.inappmessaging.display.internal.FiamAnimator$Position r1 = com.google.firebase.inappmessaging.display.internal.FiamAnimator.Position.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.display.internal.FiamAnimator.AnonymousClass3.<clinit>():void");
        }
    }
}
