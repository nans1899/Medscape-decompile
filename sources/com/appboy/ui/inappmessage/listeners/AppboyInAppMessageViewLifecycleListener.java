package com.appboy.ui.inappmessage.listeners;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import com.appboy.enums.Channel;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.models.MessageButton;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.BundleUtils;
import com.appboy.support.WebContentUtils;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.NewsfeedAction;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.ui.inappmessage.InAppMessageCloser;

public class AppboyInAppMessageViewLifecycleListener implements IInAppMessageViewLifecycleListener {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageViewLifecycleListener.class);

    public void beforeOpened(View view, IInAppMessage iInAppMessage) {
        AppboyLogger.d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeOpened called.");
        iInAppMessage.logImpression();
    }

    public void afterOpened(View view, IInAppMessage iInAppMessage) {
        AppboyLogger.d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterOpened called.");
    }

    public void beforeClosed(View view, IInAppMessage iInAppMessage) {
        AppboyLogger.d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.beforeClosed called.");
    }

    public void afterClosed(IInAppMessage iInAppMessage) {
        AppboyLogger.d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.afterClosed called.");
        getInAppMessageManager().resetAfterInAppMessageClose();
        if (iInAppMessage instanceof IInAppMessageHtml) {
            startClearHtmlInAppMessageAssetsThread();
        }
        iInAppMessage.onAfterClosed();
    }

    public void onClicked(InAppMessageCloser inAppMessageCloser, View view, IInAppMessage iInAppMessage) {
        AppboyLogger.d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onClicked called.");
        iInAppMessage.logClick();
        if (!getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageClicked(iInAppMessage, inAppMessageCloser)) {
            performInAppMessageClicked(iInAppMessage, inAppMessageCloser);
        }
    }

    public void onButtonClicked(InAppMessageCloser inAppMessageCloser, MessageButton messageButton, IInAppMessageImmersive iInAppMessageImmersive) {
        AppboyLogger.d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onButtonClicked called.");
        iInAppMessageImmersive.logButtonClick(messageButton);
        if (!getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageButtonClicked(messageButton, inAppMessageCloser)) {
            performInAppMessageButtonClicked(messageButton, iInAppMessageImmersive, inAppMessageCloser);
        }
    }

    public void onDismissed(View view, IInAppMessage iInAppMessage) {
        AppboyLogger.d(TAG, "InAppMessageViewWrapper.IInAppMessageViewLifecycleListener.onDismissed called.");
        getInAppMessageManager().getInAppMessageManagerListener().onInAppMessageDismissed(iInAppMessage);
    }

    private void performInAppMessageButtonClicked(MessageButton messageButton, IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser) {
        performClickAction(messageButton.getClickAction(), iInAppMessage, inAppMessageCloser, messageButton.getUri(), messageButton.getOpenUriInWebview());
    }

    private void performInAppMessageClicked(IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser) {
        performClickAction(iInAppMessage.getClickAction(), iInAppMessage, inAppMessageCloser, iInAppMessage.getUri(), iInAppMessage.getOpenUriInWebView());
    }

    private void performClickAction(ClickAction clickAction, IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser, Uri uri, boolean z) {
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.w(TAG, "Can't perform click action because the cached activity is null.");
            return;
        }
        int i = AnonymousClass2.$SwitchMap$com$appboy$enums$inappmessage$ClickAction[clickAction.ordinal()];
        if (i == 1) {
            inAppMessageCloser.close(false);
            AppboyNavigator.getAppboyNavigator().gotoNewsFeed(getInAppMessageManager().getActivity(), new NewsfeedAction(BundleUtils.mapToBundle(iInAppMessage.getExtras()), Channel.INAPP_MESSAGE));
        } else if (i == 2) {
            inAppMessageCloser.close(false);
            AppboyNavigator.getAppboyNavigator().gotoUri(getInAppMessageManager().getActivity(), ActionFactory.createUriActionFromUri(uri, BundleUtils.mapToBundle(iInAppMessage.getExtras()), z, Channel.INAPP_MESSAGE));
        } else if (i != 3) {
            inAppMessageCloser.close(false);
        } else {
            inAppMessageCloser.close(iInAppMessage.getAnimateOut());
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.listeners.AppboyInAppMessageViewLifecycleListener$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$appboy$enums$inappmessage$ClickAction;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.appboy.enums.inappmessage.ClickAction[] r0 = com.appboy.enums.inappmessage.ClickAction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$appboy$enums$inappmessage$ClickAction = r0
                com.appboy.enums.inappmessage.ClickAction r1 = com.appboy.enums.inappmessage.ClickAction.NEWS_FEED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$appboy$enums$inappmessage$ClickAction     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appboy.enums.inappmessage.ClickAction r1 = com.appboy.enums.inappmessage.ClickAction.URI     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$appboy$enums$inappmessage$ClickAction     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appboy.enums.inappmessage.ClickAction r1 = com.appboy.enums.inappmessage.ClickAction.NONE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appboy.ui.inappmessage.listeners.AppboyInAppMessageViewLifecycleListener.AnonymousClass2.<clinit>():void");
        }
    }

    private AppboyInAppMessageManager getInAppMessageManager() {
        return AppboyInAppMessageManager.getInstance();
    }

    private void startClearHtmlInAppMessageAssetsThread() {
        new Thread(new Runnable() {
            public void run() {
                Activity activity = AppboyInAppMessageManager.getInstance().getActivity();
                if (activity != null) {
                    AppboyFileUtils.deleteFileOrDirectory(WebContentUtils.getHtmlInAppMessageAssetCacheDirectory(activity));
                }
            }
        }).start();
    }
}
