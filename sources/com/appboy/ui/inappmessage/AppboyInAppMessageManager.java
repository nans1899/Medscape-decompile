package com.appboy.ui.inappmessage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import com.appboy.Appboy;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.enums.inappmessage.InAppMessageFailureType;
import com.appboy.enums.inappmessage.Orientation;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageFull;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.inappmessage.factories.AppboyFullViewFactory;
import com.appboy.ui.inappmessage.factories.AppboyHtmlFullViewFactory;
import com.appboy.ui.inappmessage.factories.AppboyInAppMessageAnimationFactory;
import com.appboy.ui.inappmessage.factories.AppboyModalViewFactory;
import com.appboy.ui.inappmessage.factories.AppboySlideupViewFactory;
import com.appboy.ui.inappmessage.listeners.AppboyDefaultHtmlInAppMessageActionListener;
import com.appboy.ui.inappmessage.listeners.AppboyDefaultInAppMessageManagerListener;
import com.appboy.ui.inappmessage.listeners.AppboyInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.AppboyInAppMessageWebViewClientListener;
import com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageViewLifecycleListener;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.support.ViewUtils;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AppboyInAppMessageManager {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageManager.class);
    private static volatile AppboyInAppMessageManager sInstance = null;
    private Activity mActivity;
    private AppboyConfigurationProvider mAppboyConfigurationProvider;
    private Context mApplicationContext;
    private IInAppMessage mCarryoverInAppMessage;
    private IInAppMessageManagerListener mCustomControlInAppMessageManagerListener;
    private IHtmlInAppMessageActionListener mCustomHtmlInAppMessageActionListener;
    private IInAppMessageAnimationFactory mCustomInAppMessageAnimationFactory;
    private IInAppMessageManagerListener mCustomInAppMessageManagerListener;
    private IInAppMessageViewFactory mCustomInAppMessageViewFactory;
    private IHtmlInAppMessageActionListener mDefaultHtmlInAppMessageActionListener = new AppboyDefaultHtmlInAppMessageActionListener();
    private IInAppMessageManagerListener mDefaultInAppMessageManagerListener = new AppboyDefaultInAppMessageManagerListener();
    private AtomicBoolean mDisplayingInAppMessage = new AtomicBoolean(false);
    private IInAppMessageAnimationFactory mInAppMessageAnimationFactory = new AppboyInAppMessageAnimationFactory();
    private IEventSubscriber<InAppMessageEvent> mInAppMessageEventSubscriber;
    private IInAppMessageViewFactory mInAppMessageFullViewFactory = new AppboyFullViewFactory();
    private IInAppMessageViewFactory mInAppMessageHtmlFullViewFactory = new AppboyHtmlFullViewFactory(this.mInAppMessageWebViewClientListener);
    private IInAppMessageViewFactory mInAppMessageModalViewFactory = new AppboyModalViewFactory();
    private IInAppMessageViewFactory mInAppMessageSlideupViewFactory = new AppboySlideupViewFactory();
    private final Stack<IInAppMessage> mInAppMessageStack = new Stack<>();
    private final IInAppMessageViewLifecycleListener mInAppMessageViewLifecycleListener = new AppboyInAppMessageViewLifecycleListener();
    private IInAppMessageViewWrapper mInAppMessageViewWrapper;
    private final IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener = new AppboyInAppMessageWebViewClientListener();
    private Integer mOriginalOrientation;
    private IInAppMessage mUnRegisteredInAppMessage;

    public static AppboyInAppMessageManager getInstance() {
        if (sInstance == null) {
            synchronized (AppboyInAppMessageManager.class) {
                if (sInstance == null) {
                    sInstance = new AppboyInAppMessageManager();
                }
            }
        }
        return sInstance;
    }

    public void ensureSubscribedToInAppMessageEvents(Context context) {
        if (this.mInAppMessageEventSubscriber == null) {
            AppboyLogger.d(TAG, "Subscribing in-app message event subscriber");
            this.mInAppMessageEventSubscriber = createInAppMessageEventSubscriber();
            Appboy.getInstance(context).subscribeToNewInAppMessages(this.mInAppMessageEventSubscriber);
        }
    }

    public void registerInAppMessageManager(Activity activity) {
        AppboyLogger.d(TAG, "registerInAppMessageManager called");
        this.mActivity = activity;
        if (activity != null && this.mApplicationContext == null) {
            this.mApplicationContext = activity.getApplicationContext();
        }
        if (this.mAppboyConfigurationProvider == null) {
            this.mAppboyConfigurationProvider = new AppboyConfigurationProvider(this.mApplicationContext);
        }
        if (this.mCarryoverInAppMessage != null) {
            AppboyLogger.d(TAG, "Requesting display of carryover in-app message.");
            this.mCarryoverInAppMessage.setAnimateIn(false);
            displayInAppMessage(this.mCarryoverInAppMessage, true);
            this.mCarryoverInAppMessage = null;
        } else if (this.mUnRegisteredInAppMessage != null) {
            AppboyLogger.d(TAG, "Adding previously unregistered in-app message.");
            addInAppMessage(this.mUnRegisteredInAppMessage);
            this.mUnRegisteredInAppMessage = null;
        }
        ensureSubscribedToInAppMessageEvents(this.mApplicationContext);
    }

    public void unregisterInAppMessageManager(Activity activity) {
        AppboyLogger.d(TAG, "unregisterInAppMessageManager called");
        IInAppMessageViewWrapper iInAppMessageViewWrapper = this.mInAppMessageViewWrapper;
        if (iInAppMessageViewWrapper != null) {
            ViewUtils.removeViewFromParent(iInAppMessageViewWrapper.getInAppMessageView());
            if (this.mInAppMessageViewWrapper.getIsAnimatingClose()) {
                this.mInAppMessageViewLifecycleListener.afterClosed(this.mInAppMessageViewWrapper.getInAppMessage());
                this.mCarryoverInAppMessage = null;
            } else {
                this.mCarryoverInAppMessage = this.mInAppMessageViewWrapper.getInAppMessage();
            }
            this.mInAppMessageViewWrapper = null;
        } else {
            this.mCarryoverInAppMessage = null;
        }
        this.mActivity = null;
        this.mDisplayingInAppMessage.set(false);
    }

    public void setCustomInAppMessageManagerListener(IInAppMessageManagerListener iInAppMessageManagerListener) {
        AppboyLogger.d(TAG, "Custom InAppMessageManagerListener set");
        this.mCustomInAppMessageManagerListener = iInAppMessageManagerListener;
    }

    public void setCustomControlInAppMessageManagerListener(IInAppMessageManagerListener iInAppMessageManagerListener) {
        AppboyLogger.d(TAG, "Custom ControlInAppMessageManagerListener set. This listener will only be used for control in-app messages.");
        this.mCustomControlInAppMessageManagerListener = iInAppMessageManagerListener;
    }

    public void setCustomHtmlInAppMessageActionListener(IHtmlInAppMessageActionListener iHtmlInAppMessageActionListener) {
        AppboyLogger.d(TAG, "Custom htmlInAppMessageActionListener set");
        this.mCustomHtmlInAppMessageActionListener = iHtmlInAppMessageActionListener;
    }

    public void setCustomInAppMessageAnimationFactory(IInAppMessageAnimationFactory iInAppMessageAnimationFactory) {
        AppboyLogger.d(TAG, "Custom InAppMessageAnimationFactory set");
        this.mCustomInAppMessageAnimationFactory = iInAppMessageAnimationFactory;
    }

    public void setCustomInAppMessageViewFactory(IInAppMessageViewFactory iInAppMessageViewFactory) {
        AppboyLogger.d(TAG, "Custom InAppMessageViewFactory set");
        this.mCustomInAppMessageViewFactory = iInAppMessageViewFactory;
    }

    public void addInAppMessage(IInAppMessage iInAppMessage) {
        this.mInAppMessageStack.push(iInAppMessage);
        requestDisplayInAppMessage();
    }

    public boolean requestDisplayInAppMessage() {
        InAppMessageOperation inAppMessageOperation;
        try {
            if (this.mActivity == null) {
                if (!this.mInAppMessageStack.empty()) {
                    AppboyLogger.w(TAG, "No activity is currently registered to receive in-app messages. Saving in-app message as unregistered in-app message. It will automatically be displayed when the next activity registers to receive in-app messages.");
                    this.mUnRegisteredInAppMessage = this.mInAppMessageStack.pop();
                } else {
                    AppboyLogger.d(TAG, "No activity is currently registered to receive in-app messages and the in-app message stack is empty. Doing nothing.");
                }
                return false;
            } else if (this.mDisplayingInAppMessage.get()) {
                AppboyLogger.d(TAG, "A in-app message is currently being displayed. Ignoring request to display in-app message.");
                return false;
            } else if (this.mInAppMessageStack.isEmpty()) {
                AppboyLogger.d(TAG, "The in-app message stack is empty. No in-app message will be displayed.");
                return false;
            } else {
                final IInAppMessage pop = this.mInAppMessageStack.pop();
                if (!pop.isControl()) {
                    inAppMessageOperation = getInAppMessageManagerListener().beforeInAppMessageDisplayed(pop);
                } else {
                    AppboyLogger.d(TAG, "Using the control in-app message manager listener.");
                    inAppMessageOperation = getControlInAppMessageManagerListener().beforeInAppMessageDisplayed(pop);
                }
                int i = AnonymousClass3.$SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation[inAppMessageOperation.ordinal()];
                if (i == 1) {
                    AppboyLogger.d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_NOW. The in-app message will be displayed.");
                    new Handler(this.mApplicationContext.getMainLooper()).post(new Runnable() {
                        public void run() {
                            new AppboyAsyncInAppMessageDisplayer().execute(new IInAppMessage[]{pop});
                        }
                    });
                    return true;
                } else if (i == 2) {
                    AppboyLogger.d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISPLAY_LATER. The in-app message will be pushed back onto the stack.");
                    this.mInAppMessageStack.push(pop);
                    return false;
                } else if (i != 3) {
                    AppboyLogger.e(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned null instead of a InAppMessageOperation. Ignoring the in-app message. Please check the IInAppMessageStackBehaviour implementation.");
                    return false;
                } else {
                    AppboyLogger.d(TAG, "The IInAppMessageManagerListener method beforeInAppMessageDisplayed returned DISCARD. The in-app message will not be displayed and will not be put back on the stack.");
                    return false;
                }
            }
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Error running requestDisplayInAppMessage", e);
            return false;
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.AppboyInAppMessageManager$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.appboy.ui.inappmessage.InAppMessageOperation[] r0 = com.appboy.ui.inappmessage.InAppMessageOperation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation = r0
                com.appboy.ui.inappmessage.InAppMessageOperation r1 = com.appboy.ui.inappmessage.InAppMessageOperation.DISPLAY_NOW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appboy.ui.inappmessage.InAppMessageOperation r1 = com.appboy.ui.inappmessage.InAppMessageOperation.DISPLAY_LATER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$appboy$ui$inappmessage$InAppMessageOperation     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appboy.ui.inappmessage.InAppMessageOperation r1 = com.appboy.ui.inappmessage.InAppMessageOperation.DISCARD     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appboy.ui.inappmessage.AppboyInAppMessageManager.AnonymousClass3.<clinit>():void");
        }
    }

    public void hideCurrentlyDisplayingInAppMessage(boolean z) {
        IInAppMessageViewWrapper iInAppMessageViewWrapper = this.mInAppMessageViewWrapper;
        if (iInAppMessageViewWrapper != null) {
            if (z) {
                this.mInAppMessageViewLifecycleListener.onDismissed(iInAppMessageViewWrapper.getInAppMessageView(), iInAppMessageViewWrapper.getInAppMessage());
            }
            iInAppMessageViewWrapper.close();
        }
    }

    public IInAppMessageManagerListener getInAppMessageManagerListener() {
        IInAppMessageManagerListener iInAppMessageManagerListener = this.mCustomInAppMessageManagerListener;
        return iInAppMessageManagerListener != null ? iInAppMessageManagerListener : this.mDefaultInAppMessageManagerListener;
    }

    public IInAppMessageManagerListener getControlInAppMessageManagerListener() {
        IInAppMessageManagerListener iInAppMessageManagerListener = this.mCustomControlInAppMessageManagerListener;
        return iInAppMessageManagerListener != null ? iInAppMessageManagerListener : this.mDefaultInAppMessageManagerListener;
    }

    public IHtmlInAppMessageActionListener getHtmlInAppMessageActionListener() {
        IHtmlInAppMessageActionListener iHtmlInAppMessageActionListener = this.mCustomHtmlInAppMessageActionListener;
        return iHtmlInAppMessageActionListener != null ? iHtmlInAppMessageActionListener : this.mDefaultHtmlInAppMessageActionListener;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public void resetAfterInAppMessageClose() {
        AppboyLogger.v(TAG, "Resetting after in-app message close.");
        this.mInAppMessageViewWrapper = null;
        this.mDisplayingInAppMessage.set(false);
        if (this.mActivity != null && this.mOriginalOrientation != null) {
            String str = TAG;
            AppboyLogger.d(str, "Setting requested orientation to original orientation " + this.mOriginalOrientation);
            ViewUtils.setActivityRequestedOrientation(this.mActivity, this.mOriginalOrientation.intValue());
            this.mOriginalOrientation = null;
        }
    }

    private IInAppMessageAnimationFactory getInAppMessageAnimationFactory() {
        IInAppMessageAnimationFactory iInAppMessageAnimationFactory = this.mCustomInAppMessageAnimationFactory;
        return iInAppMessageAnimationFactory != null ? iInAppMessageAnimationFactory : this.mInAppMessageAnimationFactory;
    }

    private IInAppMessageViewFactory getInAppMessageViewFactory(IInAppMessage iInAppMessage) {
        IInAppMessageViewFactory iInAppMessageViewFactory = this.mCustomInAppMessageViewFactory;
        if (iInAppMessageViewFactory != null) {
            return iInAppMessageViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageSlideup) {
            return this.mInAppMessageSlideupViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageModal) {
            return this.mInAppMessageModalViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageFull) {
            return this.mInAppMessageFullViewFactory;
        }
        if (iInAppMessage instanceof InAppMessageHtmlFull) {
            return this.mInAppMessageHtmlFullViewFactory;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean displayInAppMessage(IInAppMessage iInAppMessage, boolean z) {
        if (!this.mDisplayingInAppMessage.compareAndSet(false, true)) {
            AppboyLogger.d(TAG, "A in-app message is currently being displayed. Adding in-app message back on the stack.");
            this.mInAppMessageStack.push(iInAppMessage);
            return false;
        }
        try {
            if (this.mActivity != null) {
                if (!z) {
                    long expirationTimestamp = iInAppMessage.getExpirationTimestamp();
                    if (expirationTimestamp > 0) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis > expirationTimestamp) {
                            throw new Exception("In-app message is expired. Doing nothing. Expiration: $" + expirationTimestamp + ". Current time: " + currentTimeMillis);
                        }
                    } else {
                        AppboyLogger.d(TAG, "Expiration timestamp not defined. Continuing.");
                    }
                } else {
                    AppboyLogger.d(TAG, "Not checking expiration status for carry-over in-app message.");
                }
                if (!verifyOrientationStatus(iInAppMessage)) {
                    throw new Exception("Current orientation did not match specified orientation for in-app message. Doing nothing.");
                } else if (iInAppMessage.isControl()) {
                    AppboyLogger.d(TAG, "Not displaying control in-app message. Logging impression and ending display execution.");
                    iInAppMessage.logImpression();
                    resetAfterInAppMessageClose();
                    return true;
                } else {
                    IInAppMessageViewFactory inAppMessageViewFactory = getInAppMessageViewFactory(iInAppMessage);
                    if (inAppMessageViewFactory != null) {
                        View createInAppMessageView = inAppMessageViewFactory.createInAppMessageView(this.mActivity, iInAppMessage);
                        if (createInAppMessageView == null) {
                            iInAppMessage.logDisplayFailure(InAppMessageFailureType.DISPLAY_VIEW_GENERATION);
                            throw new Exception("The in-app message view returned from the IInAppMessageViewFactory was null. The in-app message will not be displayed and will not be put back on the stack.");
                        } else if (createInAppMessageView.getParent() == null) {
                            Animation openingAnimation = getInAppMessageAnimationFactory().getOpeningAnimation(iInAppMessage);
                            Animation closingAnimation = getInAppMessageAnimationFactory().getClosingAnimation(iInAppMessage);
                            if (createInAppMessageView instanceof IInAppMessageImmersiveView) {
                                AppboyLogger.d(TAG, "Creating view wrapper for immersive in-app message.");
                                IInAppMessageImmersiveView iInAppMessageImmersiveView = (IInAppMessageImmersiveView) createInAppMessageView;
                                this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(createInAppMessageView, iInAppMessage, this.mInAppMessageViewLifecycleListener, this.mAppboyConfigurationProvider, openingAnimation, closingAnimation, iInAppMessageImmersiveView.getMessageClickableView(), iInAppMessageImmersiveView.getMessageButtonViews(), iInAppMessageImmersiveView.getMessageCloseButtonView());
                            } else if (createInAppMessageView instanceof IInAppMessageView) {
                                AppboyLogger.d(TAG, "Creating view wrapper for base in-app message.");
                                this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(createInAppMessageView, iInAppMessage, this.mInAppMessageViewLifecycleListener, this.mAppboyConfigurationProvider, openingAnimation, closingAnimation, ((IInAppMessageView) createInAppMessageView).getMessageClickableView());
                            } else {
                                AppboyLogger.d(TAG, "Creating view wrapper for in-app message.");
                                this.mInAppMessageViewWrapper = new InAppMessageViewWrapper(createInAppMessageView, iInAppMessage, this.mInAppMessageViewLifecycleListener, this.mAppboyConfigurationProvider, openingAnimation, closingAnimation, createInAppMessageView);
                            }
                            this.mInAppMessageViewWrapper.open(this.mActivity);
                            return true;
                        } else {
                            iInAppMessage.logDisplayFailure(InAppMessageFailureType.DISPLAY_VIEW_GENERATION);
                            throw new Exception("The in-app message view returned from the IInAppMessageViewFactory already has a parent. This is a sign that the view is being reused. The IInAppMessageViewFactory method createInAppMessageViewmust return a new view without a parent. The in-app message will not be displayed and will not be put back on the stack.");
                        }
                    } else {
                        iInAppMessage.logDisplayFailure(InAppMessageFailureType.DISPLAY_VIEW_GENERATION);
                        throw new Exception("ViewFactory from getInAppMessageViewFactory was null.");
                    }
                }
            } else {
                this.mCarryoverInAppMessage = iInAppMessage;
                throw new Exception("No activity is currently registered to receive in-app messages. Registering in-app message as carry-over in-app message. It will automatically be displayed when the next activity registers to receive in-app messages.");
            }
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Could not display in-app message", e);
            resetAfterInAppMessageClose();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean verifyOrientationStatus(IInAppMessage iInAppMessage) {
        if (ViewUtils.isRunningOnTablet(this.mActivity)) {
            AppboyLogger.d(TAG, "Running on tablet. In-app message can be displayed in any orientation.");
            return true;
        }
        Orientation orientation = iInAppMessage.getOrientation();
        if (orientation == null) {
            AppboyLogger.d(TAG, "No orientation specified. In-app message can be displayed in any orientation.");
            return true;
        } else if (orientation == Orientation.ANY) {
            AppboyLogger.d(TAG, "Any orientation specified. In-app message can be displayed in any orientation.");
            return true;
        } else if (!currentOrientationIsValid(this.mActivity.getResources().getConfiguration().orientation, orientation)) {
            return false;
        } else {
            if (this.mOriginalOrientation == null) {
                AppboyLogger.d(TAG, "Requesting orientation lock.");
                this.mOriginalOrientation = Integer.valueOf(this.mActivity.getRequestedOrientation());
                ViewUtils.setActivityRequestedOrientation(this.mActivity, 14);
            }
            return true;
        }
    }

    private boolean currentOrientationIsValid(int i, Orientation orientation) {
        if (i == 2 && orientation == Orientation.LANDSCAPE) {
            AppboyLogger.d(TAG, "Current and preferred orientation are landscape.");
            return true;
        } else if (i == 1 && orientation == Orientation.PORTRAIT) {
            AppboyLogger.d(TAG, "Current and preferred orientation are portrait.");
            return true;
        } else {
            String str = TAG;
            AppboyLogger.d(str, "Current orientation " + i + " and preferred orientation " + orientation + " don't match");
            return false;
        }
    }

    private IEventSubscriber<InAppMessageEvent> createInAppMessageEventSubscriber() {
        return new IEventSubscriber<InAppMessageEvent>() {
            public void trigger(InAppMessageEvent inAppMessageEvent) {
                if (!AppboyInAppMessageManager.this.getInAppMessageManagerListener().onInAppMessageReceived(inAppMessageEvent.getInAppMessage())) {
                    AppboyInAppMessageManager.this.addInAppMessage(inAppMessageEvent.getInAppMessage());
                }
            }
        };
    }
}
