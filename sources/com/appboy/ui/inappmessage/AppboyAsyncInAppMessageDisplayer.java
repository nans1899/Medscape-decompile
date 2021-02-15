package com.appboy.ui.inappmessage;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import com.appboy.Appboy;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.enums.inappmessage.InAppMessageFailureType;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageHtmlBase;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.WebContentUtils;
import java.io.File;

public class AppboyAsyncInAppMessageDisplayer extends AsyncTask<IInAppMessage, Integer, IInAppMessage> {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyAsyncInAppMessageDisplayer.class);

    /* access modifiers changed from: protected */
    public IInAppMessage doInBackground(IInAppMessage... iInAppMessageArr) {
        try {
            IInAppMessage iInAppMessage = iInAppMessageArr[0];
            if (iInAppMessage.isControl()) {
                AppboyLogger.d(TAG, "Skipping in-app message preparation for control in-app message.");
                return iInAppMessage;
            }
            AppboyLogger.d(TAG, "Starting asynchronous in-app message preparation.");
            if (iInAppMessage instanceof InAppMessageHtmlFull) {
                if (!prepareInAppMessageWithHtml(iInAppMessage)) {
                    iInAppMessage.logDisplayFailure(InAppMessageFailureType.ZIP_ASSET_DOWNLOAD);
                    return null;
                }
            } else if (!prepareInAppMessageWithBitmapDownload(iInAppMessage)) {
                iInAppMessage.logDisplayFailure(InAppMessageFailureType.IMAGE_DOWNLOAD);
                return null;
            }
            return iInAppMessage;
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Error running AsyncInAppMessageDisplayer", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(final IInAppMessage iInAppMessage) {
        if (iInAppMessage != null) {
            try {
                AppboyLogger.d(TAG, "Finished asynchronous in-app message preparation. Attempting to display in-app message.");
                new Handler(AppboyInAppMessageManager.getInstance().getApplicationContext().getMainLooper()).post(new Runnable() {
                    public void run() {
                        AppboyLogger.d(AppboyAsyncInAppMessageDisplayer.TAG, "Displaying in-app message.");
                        AppboyInAppMessageManager.getInstance().displayInAppMessage(iInAppMessage, false);
                    }
                });
            } catch (Exception e) {
                AppboyLogger.e(TAG, "Error running onPostExecute", e);
            }
        } else {
            AppboyLogger.e(TAG, "Cannot display the in-app message because the in-app message was null.");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean prepareInAppMessageWithHtml(IInAppMessage iInAppMessage) {
        InAppMessageHtmlBase inAppMessageHtmlBase = (InAppMessageHtmlBase) iInAppMessage;
        String localAssetsDirectoryUrl = inAppMessageHtmlBase.getLocalAssetsDirectoryUrl();
        if (!StringUtils.isNullOrBlank(localAssetsDirectoryUrl) && new File(localAssetsDirectoryUrl).exists()) {
            AppboyLogger.i(TAG, "Local assets for html in-app message are already populated. Not downloading assets.");
            return true;
        } else if (StringUtils.isNullOrBlank(inAppMessageHtmlBase.getAssetsZipRemoteUrl())) {
            AppboyLogger.i(TAG, "Html in-app message has no remote asset zip. Continuing with in-app message preparation.");
            return true;
        } else {
            String localHtmlUrlFromRemoteUrl = WebContentUtils.getLocalHtmlUrlFromRemoteUrl(WebContentUtils.getHtmlInAppMessageAssetCacheDirectory(AppboyInAppMessageManager.getInstance().getApplicationContext()), inAppMessageHtmlBase.getAssetsZipRemoteUrl());
            if (!StringUtils.isNullOrBlank(localHtmlUrlFromRemoteUrl)) {
                String str = TAG;
                AppboyLogger.d(str, "Local url for html in-app message assets is " + localHtmlUrlFromRemoteUrl);
                inAppMessageHtmlBase.setLocalAssetsDirectoryUrl(localHtmlUrlFromRemoteUrl);
                return true;
            }
            String str2 = TAG;
            AppboyLogger.w(str2, "Download of html content to local directory failed for remote url: " + inAppMessageHtmlBase.getAssetsZipRemoteUrl() + " . Returned local url is: " + localHtmlUrlFromRemoteUrl);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean prepareInAppMessageWithBitmapDownload(IInAppMessage iInAppMessage) {
        if (iInAppMessage.getBitmap() != null) {
            AppboyLogger.i(TAG, "In-app message already contains image bitmap. Not downloading image from URL.");
            iInAppMessage.setImageDownloadSuccessful(true);
            return true;
        }
        String localImageUrl = iInAppMessage.getLocalImageUrl();
        if (!StringUtils.isNullOrBlank(localImageUrl) && new File(localImageUrl).exists()) {
            AppboyLogger.i(TAG, "In-app message has local image url.");
            iInAppMessage.setBitmap(AppboyImageUtils.getBitmap(Uri.parse(localImageUrl)));
        }
        if (iInAppMessage.getBitmap() == null) {
            String remoteImageUrl = iInAppMessage.getRemoteImageUrl();
            if (!StringUtils.isNullOrBlank(remoteImageUrl)) {
                AppboyLogger.i(TAG, "In-app message has remote image url. Downloading.");
                AppboyViewBounds appboyViewBounds = AppboyViewBounds.NO_BOUNDS;
                if (iInAppMessage instanceof InAppMessageSlideup) {
                    appboyViewBounds = AppboyViewBounds.IN_APP_MESSAGE_SLIDEUP;
                } else if (iInAppMessage instanceof InAppMessageModal) {
                    appboyViewBounds = AppboyViewBounds.IN_APP_MESSAGE_MODAL;
                }
                Context applicationContext = AppboyInAppMessageManager.getInstance().getApplicationContext();
                iInAppMessage.setBitmap(Appboy.getInstance(applicationContext).getAppboyImageLoader().getBitmapFromUrl(applicationContext, remoteImageUrl, appboyViewBounds));
            } else {
                AppboyLogger.w(TAG, "In-app message has no remote image url. Not downloading image.");
                return true;
            }
        }
        if (iInAppMessage.getBitmap() == null) {
            return false;
        }
        iInAppMessage.setImageDownloadSuccessful(true);
        return true;
    }
}
