package com.appboy.ui.actions;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.TaskStackBuilder;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.enums.Channel;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.ui.AppboyWebViewActivity;
import com.appboy.ui.support.UriUtils;
import java.util.Iterator;
import java.util.List;

public class UriAction implements IAction {
    private static final String TAG = AppboyLogger.getAppboyLogTag(UriAction.class);
    private final Channel mChannel;
    private final Bundle mExtras;
    private Uri mUri;
    private boolean mUseWebView;

    UriAction(Uri uri, Bundle bundle, boolean z, Channel channel) {
        this.mUri = uri;
        this.mExtras = bundle;
        this.mUseWebView = z;
        this.mChannel = channel;
    }

    public Channel getChannel() {
        return this.mChannel;
    }

    public void execute(Context context) {
        if (AppboyFileUtils.isLocalUri(this.mUri)) {
            String str = TAG;
            AppboyLogger.d(str, "Not executing local Uri: " + this.mUri);
            return;
        }
        String str2 = TAG;
        AppboyLogger.d(str2, "Executing Uri action from channel " + this.mChannel + ": " + this.mUri + ". UseWebView: " + this.mUseWebView + ". Extras: " + this.mExtras);
        if (!this.mUseWebView || !AppboyFileUtils.REMOTE_SCHEMES.contains(this.mUri.getScheme())) {
            if (this.mChannel.equals(Channel.PUSH)) {
                openUriWithActionViewFromPush(context, this.mUri, this.mExtras);
            } else {
                openUriWithActionView(context, this.mUri, this.mExtras);
            }
        } else if (this.mChannel.equals(Channel.PUSH)) {
            openUriWithWebViewActivityFromPush(context, this.mUri, this.mExtras);
        } else {
            openUriWithWebViewActivity(context, this.mUri, this.mExtras);
        }
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
    }

    public void setUseWebView(boolean z) {
        this.mUseWebView = z;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean getUseWebView() {
        return this.mUseWebView;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    static void openUriWithWebViewActivity(Context context, Uri uri, Bundle bundle) {
        Intent webViewActivityIntent = getWebViewActivityIntent(context, uri, bundle);
        webViewActivityIntent.setFlags(872415232);
        try {
            context.startActivity(webViewActivityIntent);
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Appboy AppboyWebViewActivity not opened successfully.", e);
        }
    }

    private static void openUriWithWebViewActivityFromPush(Context context, Uri uri, Bundle bundle) {
        TaskStackBuilder configuredTaskBackStackBuilder = getConfiguredTaskBackStackBuilder(context, bundle);
        configuredTaskBackStackBuilder.addNextIntent(getWebViewActivityIntent(context, uri, bundle));
        try {
            configuredTaskBackStackBuilder.startActivities(bundle);
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Appboy AppboyWebViewActivity not opened successfully.", e);
        }
    }

    private static void openUriWithActionView(Context context, Uri uri, Bundle bundle) {
        Intent actionViewIntent = getActionViewIntent(context, uri, bundle);
        actionViewIntent.setFlags(872415232);
        if (actionViewIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(actionViewIntent);
            return;
        }
        String str = TAG;
        AppboyLogger.w(str, "Could not find appropriate activity to open for deep link " + uri + ".");
    }

    private static void openUriWithActionViewFromPush(Context context, Uri uri, Bundle bundle) {
        TaskStackBuilder configuredTaskBackStackBuilder = getConfiguredTaskBackStackBuilder(context, bundle);
        configuredTaskBackStackBuilder.addNextIntent(getActionViewIntent(context, uri, bundle));
        try {
            configuredTaskBackStackBuilder.startActivities(bundle);
        } catch (ActivityNotFoundException e) {
            String str = TAG;
            AppboyLogger.w(str, "Could not find appropriate activity to open for deep link " + uri, e);
        }
    }

    private static Intent getWebViewActivityIntent(Context context, Uri uri, Bundle bundle) {
        Intent intent = new Intent(context, AppboyWebViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra("url", uri.toString());
        return intent;
    }

    private static Intent getActionViewIntent(Context context, Uri uri, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() > 1) {
            Iterator<ResolveInfo> it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (next.activityInfo.packageName.equals(context.getPackageName())) {
                    String str = TAG;
                    AppboyLogger.d(str, "Setting deep link activity to " + next.activityInfo.packageName + ".");
                    intent.setPackage(next.activityInfo.packageName);
                    break;
                }
            }
        }
        return intent;
    }

    private static TaskStackBuilder getConfiguredTaskBackStackBuilder(Context context, Bundle bundle) {
        AppboyConfigurationProvider appboyConfigurationProvider = new AppboyConfigurationProvider(context);
        TaskStackBuilder create = TaskStackBuilder.create(context);
        if (appboyConfigurationProvider.getIsPushDeepLinkBackStackActivityEnabled()) {
            String pushDeepLinkBackStackActivityClassName = appboyConfigurationProvider.getPushDeepLinkBackStackActivityClassName();
            if (StringUtils.isNullOrBlank(pushDeepLinkBackStackActivityClassName)) {
                AppboyLogger.i(TAG, "Adding main activity intent to back stack while opening uri from push");
                create.addNextIntent(UriUtils.getMainActivityIntent(context, bundle));
            } else if (UriUtils.isActivityRegisteredInManifest(context, pushDeepLinkBackStackActivityClassName)) {
                String str = TAG;
                AppboyLogger.i(str, "Adding custom back stack activity while opening uri from push: " + pushDeepLinkBackStackActivityClassName);
                create.addNextIntent(new Intent().setClassName(context, pushDeepLinkBackStackActivityClassName));
            } else {
                String str2 = TAG;
                AppboyLogger.i(str2, "Not adding unregistered activity to the back stack while opening uri from push: " + pushDeepLinkBackStackActivityClassName);
            }
        } else {
            AppboyLogger.i(TAG, "Not adding back stack activity while opening uri from push due to disabled configuration setting.");
        }
        return create;
    }
}
