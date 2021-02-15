package com.google.firebase.inappmessaging.display.internal;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.google.firebase.inappmessaging.display.internal.SwipeDismissTouchListener;
import com.google.firebase.inappmessaging.display.internal.bindingwrappers.BindingWrapper;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class FiamWindowManager {
    static final int DEFAULT_TYPE = 1003;
    private BindingWrapper bindingWrapper;

    @Inject
    FiamWindowManager() {
    }

    public void show(BindingWrapper bindingWrapper2, Activity activity) {
        if (isFiamDisplayed()) {
            Logging.loge("Fiam already active. Cannot show new Fiam.");
            return;
        }
        InAppMessageLayoutConfig config = bindingWrapper2.getConfig();
        WindowManager.LayoutParams layoutParams = getLayoutParams(config, activity);
        WindowManager windowManager = getWindowManager(activity);
        windowManager.addView(bindingWrapper2.getRootView(), layoutParams);
        Rect insetDimensions = getInsetDimensions(activity);
        Logging.logdPair("Inset (top, bottom)", (float) insetDimensions.top, (float) insetDimensions.bottom);
        Logging.logdPair("Inset (left, right)", (float) insetDimensions.left, (float) insetDimensions.right);
        if (bindingWrapper2.canSwipeToDismiss()) {
            bindingWrapper2.getDialogView().setOnTouchListener(getSwipeListener(config, bindingWrapper2, windowManager, layoutParams));
        }
        this.bindingWrapper = bindingWrapper2;
    }

    public boolean isFiamDisplayed() {
        BindingWrapper bindingWrapper2 = this.bindingWrapper;
        if (bindingWrapper2 == null) {
            return false;
        }
        return bindingWrapper2.getRootView().isShown();
    }

    public void destroy(Activity activity) {
        if (isFiamDisplayed()) {
            getWindowManager(activity).removeViewImmediate(this.bindingWrapper.getRootView());
            this.bindingWrapper = null;
        }
    }

    private WindowManager.LayoutParams getLayoutParams(InAppMessageLayoutConfig inAppMessageLayoutConfig, Activity activity) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(inAppMessageLayoutConfig.windowWidth().intValue(), inAppMessageLayoutConfig.windowHeight().intValue(), 1003, inAppMessageLayoutConfig.windowFlag().intValue(), -3);
        Rect insetDimensions = getInsetDimensions(activity);
        if ((inAppMessageLayoutConfig.viewWindowGravity().intValue() & 48) == 48) {
            layoutParams.y = insetDimensions.top;
        }
        layoutParams.dimAmount = 0.3f;
        layoutParams.gravity = inAppMessageLayoutConfig.viewWindowGravity().intValue();
        layoutParams.windowAnimations = 0;
        return layoutParams;
    }

    private WindowManager getWindowManager(Activity activity) {
        return (WindowManager) activity.getSystemService("window");
    }

    private Point getDisplaySize(Activity activity) {
        Point point = new Point();
        Display defaultDisplay = getWindowManager(activity).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        return point;
    }

    private Rect getInsetDimensions(Activity activity) {
        Rect rect = new Rect();
        Rect visibleFrame = getVisibleFrame(activity);
        Point displaySize = getDisplaySize(activity);
        rect.top = visibleFrame.top;
        rect.left = visibleFrame.left;
        rect.right = displaySize.x - visibleFrame.right;
        rect.bottom = displaySize.y - visibleFrame.bottom;
        return rect;
    }

    private Rect getVisibleFrame(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect;
    }

    private SwipeDismissTouchListener getSwipeListener(InAppMessageLayoutConfig inAppMessageLayoutConfig, final BindingWrapper bindingWrapper2, WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        AnonymousClass1 r4 = new SwipeDismissTouchListener.DismissCallbacks() {
            public boolean canDismiss(Object obj) {
                return true;
            }

            public void onDismiss(View view, Object obj) {
                if (bindingWrapper2.getDismissListener() != null) {
                    bindingWrapper2.getDismissListener().onClick(view);
                }
            }
        };
        if (inAppMessageLayoutConfig.windowWidth().intValue() == -1) {
            return new SwipeDismissTouchListener(bindingWrapper2.getDialogView(), (Object) null, r4);
        }
        final WindowManager.LayoutParams layoutParams2 = layoutParams;
        final WindowManager windowManager2 = windowManager;
        final BindingWrapper bindingWrapper3 = bindingWrapper2;
        return new SwipeDismissTouchListener(bindingWrapper2.getDialogView(), (Object) null, r4) {
            /* access modifiers changed from: protected */
            public float getTranslationX() {
                return (float) layoutParams2.x;
            }

            /* access modifiers changed from: protected */
            public void setTranslationX(float f) {
                layoutParams2.x = (int) f;
                windowManager2.updateViewLayout(bindingWrapper3.getRootView(), layoutParams2);
            }
        };
    }
}
