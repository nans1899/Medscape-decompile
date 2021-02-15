package com.appboy.ui.inappmessage;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener;
import com.appboy.ui.inappmessage.listeners.TouchAwareSwipeDismissTouchListener;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageHtmlBaseView;
import com.appboy.ui.support.ViewUtils;
import java.util.List;

public class InAppMessageViewWrapper implements IInAppMessageViewWrapper {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(InAppMessageViewWrapper.class);
    private final AppboyConfigurationProvider mAppboyConfigurationProvider;
    /* access modifiers changed from: private */
    public List<View> mButtons;
    private View mClickableInAppMessageView;
    private View mCloseButton;
    private final Animation mClosingAnimation;
    private FrameLayout mContentFrameLayout;
    /* access modifiers changed from: private */
    public Runnable mDismissRunnable;
    /* access modifiers changed from: private */
    public final IInAppMessage mInAppMessage;
    /* access modifiers changed from: private */
    public final View mInAppMessageView;
    /* access modifiers changed from: private */
    public final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener;
    private boolean mIsAnimatingClose;
    private final Animation mOpeningAnimation;

    public InAppMessageViewWrapper(View view, IInAppMessage iInAppMessage, IInAppMessageViewLifecycleListener iInAppMessageViewLifecycleListener, AppboyConfigurationProvider appboyConfigurationProvider, Animation animation, Animation animation2, View view2) {
        this.mInAppMessageView = view;
        this.mInAppMessage = iInAppMessage;
        this.mInAppMessageViewLifecycleListener = iInAppMessageViewLifecycleListener;
        this.mAppboyConfigurationProvider = appboyConfigurationProvider;
        this.mIsAnimatingClose = false;
        if (view2 != null) {
            this.mClickableInAppMessageView = view2;
        } else {
            this.mClickableInAppMessageView = view;
        }
        if (this.mInAppMessage instanceof InAppMessageSlideup) {
            TouchAwareSwipeDismissTouchListener touchAwareSwipeDismissTouchListener = new TouchAwareSwipeDismissTouchListener(view, (Object) null, createDismissCallbacks());
            touchAwareSwipeDismissTouchListener.setTouchListener(createTouchAwareListener());
            this.mClickableInAppMessageView.setOnTouchListener(touchAwareSwipeDismissTouchListener);
        }
        this.mOpeningAnimation = animation;
        this.mClosingAnimation = animation2;
        this.mClickableInAppMessageView.setOnClickListener(createClickListener());
    }

    public InAppMessageViewWrapper(View view, IInAppMessage iInAppMessage, IInAppMessageViewLifecycleListener iInAppMessageViewLifecycleListener, AppboyConfigurationProvider appboyConfigurationProvider, Animation animation, Animation animation2, View view2, List<View> list, View view3) {
        this(view, iInAppMessage, iInAppMessageViewLifecycleListener, appboyConfigurationProvider, animation, animation2, view2);
        if (view3 != null) {
            this.mCloseButton = view3;
            view3.setOnClickListener(createCloseInAppMessageClickListener());
        }
        if (list != null) {
            this.mButtons = list;
            for (View onClickListener : list) {
                onClickListener.setOnClickListener(createButtonClickListener());
            }
        }
    }

    public void open(Activity activity) {
        final FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView().findViewById(16908290);
        int height = frameLayout.getHeight();
        final int displayHeight = ViewUtils.getDisplayHeight(activity);
        if (this.mAppboyConfigurationProvider.getIsInAppMessageAccessibilityExclusiveModeEnabled()) {
            this.mContentFrameLayout = frameLayout;
            setAllFrameLayoutChildrenAsNonAccessibilityImportant(frameLayout);
        }
        if (height == 0) {
            ViewTreeObserver viewTreeObserver = frameLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        String access$000 = InAppMessageViewWrapper.TAG;
                        AppboyLogger.d(access$000, "Detected root view height of " + frameLayout.getHeight() + ", display height of " + displayHeight + " in onGlobalLayout");
                        frameLayout.removeView(InAppMessageViewWrapper.this.mInAppMessageView);
                        InAppMessageViewWrapper.this.open(frameLayout, displayHeight);
                        frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
                return;
            }
            return;
        }
        String str = TAG;
        AppboyLogger.d(str, "Detected root view height of " + height + ", display height of " + displayHeight);
        open(frameLayout, displayHeight);
    }

    /* access modifiers changed from: private */
    public void open(FrameLayout frameLayout, int i) {
        this.mInAppMessageViewLifecycleListener.beforeOpened(this.mInAppMessageView, this.mInAppMessage);
        AppboyLogger.d(TAG, "Adding In-app message view to root FrameLayout.");
        frameLayout.addView(this.mInAppMessageView, getLayoutParams(frameLayout, i));
        if (this.mInAppMessage.getAnimateIn()) {
            AppboyLogger.d(TAG, "In-app message view will animate into the visible area.");
            setAndStartAnimation(true);
            return;
        }
        AppboyLogger.d(TAG, "In-app message view will be placed instantly into the visible area.");
        if (this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
            addDismissRunnable();
        }
        ViewUtils.setFocusableInTouchModeAndRequestFocus(this.mInAppMessageView);
        announceForAccessibilityIfNecessary();
        this.mInAppMessageViewLifecycleListener.afterOpened(this.mInAppMessageView, this.mInAppMessage);
    }

    /* access modifiers changed from: private */
    public void announceForAccessibilityIfNecessary() {
        View view = this.mInAppMessageView;
        if (view instanceof IInAppMessageImmersiveView) {
            view.announceForAccessibility(this.mInAppMessage.getMessage());
        } else if (view instanceof AppboyInAppMessageHtmlBaseView) {
            view.announceForAccessibility("In-app message displayed.");
        }
    }

    private FrameLayout.LayoutParams getLayoutParams(FrameLayout frameLayout, int i) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        IInAppMessage iInAppMessage = this.mInAppMessage;
        if (iInAppMessage instanceof InAppMessageSlideup) {
            layoutParams.gravity = ((InAppMessageSlideup) iInAppMessage).getSlideFrom() == SlideFrom.TOP ? 48 : 80;
        }
        if (i > 0 && i == frameLayout.getHeight()) {
            int topVisibleCoordinate = ViewUtils.getTopVisibleCoordinate(frameLayout);
            String str = TAG;
            AppboyLogger.d(str, "Detected status bar height of " + topVisibleCoordinate + ".");
            layoutParams.setMargins(0, topVisibleCoordinate, 0, 0);
        }
        return layoutParams;
    }

    public void close() {
        if (this.mAppboyConfigurationProvider.getIsInAppMessageAccessibilityExclusiveModeEnabled()) {
            setAllFrameLayoutChildrenAsAccessibilityAuto(this.mContentFrameLayout);
        }
        this.mInAppMessageView.removeCallbacks(this.mDismissRunnable);
        this.mInAppMessageViewLifecycleListener.beforeClosed(this.mInAppMessageView, this.mInAppMessage);
        if (this.mInAppMessage.getAnimateOut()) {
            this.mIsAnimatingClose = true;
            setAndStartAnimation(false);
            return;
        }
        closeInAppMessageView();
    }

    public View getInAppMessageView() {
        return this.mInAppMessageView;
    }

    public IInAppMessage getInAppMessage() {
        return this.mInAppMessage;
    }

    public boolean getIsAnimatingClose() {
        return this.mIsAnimatingClose;
    }

    private View.OnClickListener createClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                if (InAppMessageViewWrapper.this.mInAppMessage instanceof IInAppMessageImmersive) {
                    IInAppMessageImmersive iInAppMessageImmersive = (IInAppMessageImmersive) InAppMessageViewWrapper.this.mInAppMessage;
                    if (iInAppMessageImmersive.getMessageButtons() == null || iInAppMessageImmersive.getMessageButtons().size() == 0) {
                        InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
                        return;
                    }
                    return;
                }
                InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            }
        };
    }

    private View.OnClickListener createButtonClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                IInAppMessageImmersive iInAppMessageImmersive = (IInAppMessageImmersive) InAppMessageViewWrapper.this.mInAppMessage;
                for (int i = 0; i < InAppMessageViewWrapper.this.mButtons.size(); i++) {
                    if (view.getId() == ((View) InAppMessageViewWrapper.this.mButtons.get(i)).getId()) {
                        InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.onButtonClicked(new InAppMessageCloser(InAppMessageViewWrapper.this), iInAppMessageImmersive.getMessageButtons().get(i), iInAppMessageImmersive);
                        return;
                    }
                }
            }
        };
    }

    private View.OnClickListener createCloseInAppMessageClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
            }
        };
    }

    /* access modifiers changed from: private */
    public void addDismissRunnable() {
        if (this.mDismissRunnable == null) {
            AnonymousClass5 r0 = new Runnable() {
                public void run() {
                    AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
                }
            };
            this.mDismissRunnable = r0;
            this.mInAppMessageView.postDelayed(r0, (long) this.mInAppMessage.getDurationInMilliseconds());
        }
    }

    private SwipeDismissTouchListener.DismissCallbacks createDismissCallbacks() {
        return new SwipeDismissTouchListener.DismissCallbacks() {
            public boolean canDismiss(Object obj) {
                return true;
            }

            public void onDismiss(View view, Object obj) {
                InAppMessageViewWrapper.this.mInAppMessage.setAnimateOut(false);
                AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
            }
        };
    }

    private TouchAwareSwipeDismissTouchListener.ITouchListener createTouchAwareListener() {
        return new TouchAwareSwipeDismissTouchListener.ITouchListener() {
            public void onTouchStartedOrContinued() {
                InAppMessageViewWrapper.this.mInAppMessageView.removeCallbacks(InAppMessageViewWrapper.this.mDismissRunnable);
            }

            public void onTouchEnded() {
                if (InAppMessageViewWrapper.this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
                    InAppMessageViewWrapper.this.addDismissRunnable();
                }
            }
        };
    }

    private void setAndStartAnimation(boolean z) {
        Animation animation;
        if (z) {
            animation = this.mOpeningAnimation;
        } else {
            animation = this.mClosingAnimation;
        }
        animation.setAnimationListener(createAnimationListener(z));
        this.mInAppMessageView.clearAnimation();
        this.mInAppMessageView.setAnimation(animation);
        animation.startNow();
        this.mInAppMessageView.invalidate();
    }

    private Animation.AnimationListener createAnimationListener(boolean z) {
        return z ? new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (InAppMessageViewWrapper.this.mInAppMessage.getDismissType() == DismissType.AUTO_DISMISS) {
                    InAppMessageViewWrapper.this.addDismissRunnable();
                }
                AppboyLogger.d(InAppMessageViewWrapper.TAG, "In-app message animated into view.");
                ViewUtils.setFocusableInTouchModeAndRequestFocus(InAppMessageViewWrapper.this.mInAppMessageView);
                InAppMessageViewWrapper.this.announceForAccessibilityIfNecessary();
                InAppMessageViewWrapper.this.mInAppMessageViewLifecycleListener.afterOpened(InAppMessageViewWrapper.this.mInAppMessageView, InAppMessageViewWrapper.this.mInAppMessage);
            }
        } : new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                InAppMessageViewWrapper.this.mInAppMessageView.clearAnimation();
                InAppMessageViewWrapper.this.mInAppMessageView.setVisibility(8);
                InAppMessageViewWrapper.this.closeInAppMessageView();
            }
        };
    }

    /* access modifiers changed from: private */
    public void closeInAppMessageView() {
        AppboyLogger.d(TAG, "Closing in-app message view");
        ViewUtils.removeViewFromParent(this.mInAppMessageView);
        View view = this.mInAppMessageView;
        if (view instanceof AppboyInAppMessageHtmlBaseView) {
            AppboyInAppMessageHtmlBaseView appboyInAppMessageHtmlBaseView = (AppboyInAppMessageHtmlBaseView) view;
            if (appboyInAppMessageHtmlBaseView.getMessageWebView() != null) {
                AppboyLogger.d(TAG, "Called destroy on the AppboyInAppMessageHtmlBaseView WebView");
                appboyInAppMessageHtmlBaseView.getMessageWebView().destroy();
            }
        }
        this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessage);
    }

    private static void setAllFrameLayoutChildrenAsNonAccessibilityImportant(FrameLayout frameLayout) {
        if (frameLayout == null) {
            AppboyLogger.w(TAG, "In-app message FrameLayout was null. Not preparing in-app message accessibility for exclusive mode.");
            return;
        }
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            View childAt = frameLayout.getChildAt(i);
            if (childAt != null) {
                ViewCompat.setImportantForAccessibility(childAt, 4);
            }
        }
    }

    private static void setAllFrameLayoutChildrenAsAccessibilityAuto(FrameLayout frameLayout) {
        if (frameLayout == null) {
            AppboyLogger.w(TAG, "In-app message FrameLayout was null. Not preparing in-app message accessibility for exclusive mode.");
            return;
        }
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            View childAt = frameLayout.getChildAt(i);
            if (childAt != null) {
                ViewCompat.setImportantForAccessibility(childAt, 0);
            }
        }
    }
}
