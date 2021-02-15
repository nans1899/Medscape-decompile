package com.appboy.push;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.appboy.Appboy;
import com.appboy.AppboyAdmReceiver;
import com.appboy.AppboyFcmReceiver;
import com.appboy.AppboyInternal;
import com.appboy.Constants;
import com.appboy.IAppboyNotificationFactory;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.enums.Channel;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.support.UriUtils;
import com.facebook.internal.ServerProtocol;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class AppboyNotificationUtils {
    public static final String APPBOY_NOTIFICATION_DELETED_SUFFIX = ".intent.APPBOY_PUSH_DELETED";
    public static final String APPBOY_NOTIFICATION_OPENED_SUFFIX = ".intent.APPBOY_NOTIFICATION_OPENED";
    public static final String APPBOY_NOTIFICATION_RECEIVED_SUFFIX = ".intent.APPBOY_PUSH_RECEIVED";
    private static final String SOURCE_KEY = "source";
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyNotificationUtils.class);

    public static boolean isValidNotificationPriority(int i) {
        return i >= -2 && i <= 2;
    }

    public static boolean isValidNotificationVisibility(int i) {
        return i == -1 || i == 0 || i == 1;
    }

    public static void handleNotificationOpened(Context context, Intent intent) {
        try {
            logNotificationOpened(context, intent);
            sendNotificationOpenedBroadcast(context, intent);
            if (new AppboyConfigurationProvider(context).getHandlePushDeepLinksAutomatically()) {
                routeUserWithNotificationOpenedIntent(context, intent);
            }
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Exception occurred attempting to handle notification opened intent.", e);
        }
    }

    public static void handleNotificationDeleted(Context context, Intent intent) {
        try {
            AppboyLogger.d(TAG, "Sending notification deleted broadcast");
            sendPushActionIntent(context, APPBOY_NOTIFICATION_DELETED_SUFFIX, intent.getExtras());
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Exception occurred attempting to handle notification delete intent.", e);
        }
    }

    public static void routeUserWithNotificationOpenedIntent(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra(Constants.APPBOY_PUSH_EXTRAS_KEY);
        if (bundleExtra == null) {
            bundleExtra = new Bundle();
        }
        bundleExtra.putString("cid", intent.getStringExtra("cid"));
        bundleExtra.putString("source", Constants.APPBOY);
        String stringExtra = intent.getStringExtra("uri");
        if (!StringUtils.isNullOrBlank(stringExtra)) {
            String str = TAG;
            AppboyLogger.d(str, "Found a deep link " + stringExtra);
            boolean equalsIgnoreCase = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(intent.getStringExtra(Constants.APPBOY_PUSH_OPEN_URI_IN_WEBVIEW_KEY));
            String str2 = TAG;
            AppboyLogger.d(str2, "Use webview set to: " + equalsIgnoreCase);
            bundleExtra.putString("uri", stringExtra);
            bundleExtra.putBoolean(Constants.APPBOY_PUSH_OPEN_URI_IN_WEBVIEW_KEY, equalsIgnoreCase);
            AppboyNavigator.getAppboyNavigator().gotoUri(context, ActionFactory.createUriActionFromUrlString(stringExtra, bundleExtra, equalsIgnoreCase, Channel.PUSH));
            return;
        }
        AppboyLogger.d(TAG, "Push notification had no deep link. Opening main activity.");
        context.startActivity(UriUtils.getMainActivityIntent(context, bundleExtra));
    }

    public static Bundle getAppboyExtrasWithoutPreprocessing(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (bundle.containsKey(Constants.APPBOY_PUSH_STORY_IS_NEWLY_RECEIVED) && !bundle.getBoolean(Constants.APPBOY_PUSH_STORY_IS_NEWLY_RECEIVED)) {
            return bundle.getBundle(Constants.APPBOY_PUSH_EXTRAS_KEY);
        }
        if (!Constants.IS_AMAZON.booleanValue()) {
            return parseJSONStringDictionaryIntoBundle(bundle.getString(Constants.APPBOY_PUSH_EXTRAS_KEY, "{}"));
        }
        return new Bundle(bundle);
    }

    @Deprecated
    public static String bundleOptString(Bundle bundle, String str, String str2) {
        return bundle.getString(str, str2);
    }

    public static Bundle parseJSONStringDictionaryIntoBundle(String str) {
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
            return bundle;
        } catch (JSONException e) {
            AppboyLogger.e(TAG, "Unable parse JSON into a bundle.", e);
            return null;
        }
    }

    public static boolean isAppboyPushMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(extras.getString(Constants.APPBOY_PUSH_APPBOY_KEY));
    }

    public static boolean isNotificationMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        return extras != null && extras.containsKey(Constants.APPBOY_PUSH_TITLE_KEY) && extras.containsKey(Constants.APPBOY_PUSH_CONTENT_KEY);
    }

    public static void sendPushMessageReceivedBroadcast(Context context, Bundle bundle) {
        AppboyLogger.d(TAG, "Sending push message received broadcast");
        sendPushActionIntent(context, APPBOY_NOTIFICATION_RECEIVED_SUFFIX, bundle);
    }

    public static boolean requestGeofenceRefreshIfAppropriate(Context context, Bundle bundle) {
        if (!bundle.containsKey(Constants.APPBOY_PUSH_SYNC_GEOFENCES_KEY)) {
            AppboyLogger.d(TAG, "Geofence sync key not included in push payload. Not syncing geofences.");
            return false;
        } else if (Boolean.parseBoolean(bundle.getString(Constants.APPBOY_PUSH_SYNC_GEOFENCES_KEY))) {
            AppboyInternal.requestGeofenceRefresh(context, true);
            return true;
        } else {
            AppboyLogger.d(TAG, "Geofence sync key was false. Not syncing geofences.");
            return false;
        }
    }

    public static void setNotificationDurationAlarm(Context context, Class<?> cls, int i, int i2) {
        Intent intent = new Intent(context, cls);
        intent.setAction(Constants.APPBOY_CANCEL_NOTIFICATION_ACTION);
        intent.putExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID, i);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (i2 >= 1000) {
            String str = TAG;
            AppboyLogger.d(str, "Setting Notification duration alarm for " + i2 + " ms");
            alarmManager.set(3, SystemClock.elapsedRealtime() + ((long) i2), broadcast);
        }
    }

    public static int getNotificationId(Bundle bundle) {
        if (bundle == null) {
            AppboyLogger.d(TAG, "Message without extras bundle received. Using default notification id: ");
            return -1;
        } else if (bundle.containsKey(Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID)) {
            try {
                int parseInt = Integer.parseInt(bundle.getString(Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID));
                String str = TAG;
                AppboyLogger.d(str, "Using notification id provided in the message's extras bundle: " + parseInt);
                return parseInt;
            } catch (NumberFormatException e) {
                AppboyLogger.e(TAG, "Unable to parse notification id provided in the message's extras bundle. Using default notification id instead: -1", e);
                return -1;
            }
        } else {
            int hashCode = (bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY, "") + bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY, "")).hashCode();
            String str2 = TAG;
            AppboyLogger.d(str2, "Message without notification id provided in the extras bundle received. Using a hash of the message: " + hashCode);
            return hashCode;
        }
    }

    public static int getNotificationPriority(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("p")) {
            return 0;
        }
        try {
            int parseInt = Integer.parseInt(bundle.getString("p"));
            if (isValidNotificationPriority(parseInt)) {
                return parseInt;
            }
            String str = TAG;
            AppboyLogger.e(str, "Received invalid notification priority " + parseInt);
            return 0;
        } catch (NumberFormatException e) {
            AppboyLogger.e(TAG, "Unable to parse custom priority. Returning default priority of 0", e);
            return 0;
        }
    }

    public static boolean wakeScreenIfHasPermission(Context context, Bundle bundle) {
        if (!PermissionUtils.hasPermission(context, "android.permission.WAKE_LOCK")) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel validNotificationChannel = getValidNotificationChannel((NotificationManager) context.getSystemService("notification"), bundle);
            if (validNotificationChannel == null) {
                AppboyLogger.d(TAG, "Not waking screen on Android O+ device, could not find notification channel.");
                return false;
            }
            int notificationChannelImportance = getNotificationChannelImportance(validNotificationChannel);
            if (notificationChannelImportance == 1) {
                String str = TAG;
                AppboyLogger.d(str, "Not acquiring wake-lock for Android O+ notification with importance: " + notificationChannelImportance);
                return false;
            }
        } else if (getNotificationPriority(bundle) == -2) {
            return false;
        }
        PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435482, TAG);
        newWakeLock.acquire();
        newWakeLock.release();
        return true;
    }

    public static IAppboyNotificationFactory getActiveNotificationFactory() {
        IAppboyNotificationFactory customAppboyNotificationFactory = Appboy.getCustomAppboyNotificationFactory();
        return customAppboyNotificationFactory == null ? AppboyNotificationFactory.getInstance() : customAppboyNotificationFactory;
    }

    public static void prefetchBitmapsIfNewlyReceivedStoryPush(Context context, Bundle bundle) {
        if (bundle.containsKey(Constants.APPBOY_PUSH_STORY_KEY) && bundle.getBoolean(Constants.APPBOY_PUSH_STORY_IS_NEWLY_RECEIVED, false)) {
            String actionFieldAtIndex = AppboyNotificationActionUtils.getActionFieldAtIndex(0, bundle, Constants.APPBOY_PUSH_STORY_IMAGE_KEY_TEMPLATE);
            int i = 0;
            while (!StringUtils.isNullOrBlank(actionFieldAtIndex)) {
                String str = TAG;
                AppboyLogger.v(str, "Pre-fetching bitmap at URL: " + actionFieldAtIndex);
                Appboy.getInstance(context).getAppboyImageLoader().getBitmapFromUrl(context, actionFieldAtIndex, AppboyViewBounds.NOTIFICATION_ONE_IMAGE_STORY);
                i++;
                actionFieldAtIndex = AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_IMAGE_KEY_TEMPLATE);
            }
            bundle.putBoolean(Constants.APPBOY_PUSH_STORY_IS_NEWLY_RECEIVED, false);
        }
    }

    public static void setTitleIfPresent(NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle != null) {
            AppboyLogger.d(TAG, "Setting title for notification");
            builder.setContentTitle(bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY));
        }
    }

    public static void setContentIfPresent(NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle != null) {
            AppboyLogger.d(TAG, "Setting content for notification");
            builder.setContentText(bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY));
        }
    }

    public static void setTickerIfPresent(NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle != null) {
            AppboyLogger.d(TAG, "Setting ticker for notification");
            builder.setTicker(bundle.getString(Constants.APPBOY_PUSH_TITLE_KEY));
        }
    }

    public static void setContentIntentIfPresent(Context context, NotificationCompat.Builder builder, Bundle bundle) {
        try {
            builder.setContentIntent(getPushActionPendingIntent(context, Constants.APPBOY_PUSH_CLICKED_ACTION, bundle));
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Error setting content intent.", e);
        }
    }

    public static void setDeleteIntent(Context context, NotificationCompat.Builder builder, Bundle bundle) {
        AppboyLogger.d(TAG, "Setting delete intent.");
        try {
            builder.setDeleteIntent(getPushActionPendingIntent(context, Constants.APPBOY_PUSH_DELETED_ACTION, bundle));
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Error setting delete intent.", e);
        }
    }

    public static int setSmallIcon(AppboyConfigurationProvider appboyConfigurationProvider, NotificationCompat.Builder builder) {
        int smallNotificationIconResourceId = appboyConfigurationProvider.getSmallNotificationIconResourceId();
        if (smallNotificationIconResourceId == 0) {
            AppboyLogger.d(TAG, "Small notification icon resource was not found. Will use the app icon when displaying notifications.");
            smallNotificationIconResourceId = appboyConfigurationProvider.getApplicationIconResourceId();
        } else {
            AppboyLogger.d(TAG, "Setting small icon for notification via resource id");
        }
        builder.setSmallIcon(smallNotificationIconResourceId);
        return smallNotificationIconResourceId;
    }

    public static void setSetShowWhen(NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle.containsKey(Constants.APPBOY_PUSH_STORY_KEY)) {
            AppboyLogger.d(TAG, "Set show when not supported in story push.");
            builder.setShowWhen(false);
        }
    }

    public static boolean setLargeIconIfPresentAndSupported(Context context, AppboyConfigurationProvider appboyConfigurationProvider, NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle.containsKey(Constants.APPBOY_PUSH_STORY_KEY)) {
            AppboyLogger.d(TAG, "Large icon not supported in story push.");
            return false;
        }
        if (bundle != null) {
            try {
                if (bundle.containsKey(Constants.APPBOY_PUSH_LARGE_ICON_KEY)) {
                    AppboyLogger.d(TAG, "Setting large icon for notification");
                    builder.setLargeIcon(AppboyImageUtils.getBitmap(context, Uri.parse(bundle.getString(Constants.APPBOY_PUSH_LARGE_ICON_KEY)), AppboyViewBounds.NOTIFICATION_LARGE_ICON));
                    return true;
                }
            } catch (Exception e) {
                AppboyLogger.e(TAG, "Error setting large notification icon", e);
            }
        }
        AppboyLogger.d(TAG, "Large icon bitmap url not present in extras. Attempting to use resource id instead.");
        int largeNotificationIconResourceId = appboyConfigurationProvider.getLargeNotificationIconResourceId();
        if (largeNotificationIconResourceId != 0) {
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeNotificationIconResourceId));
            return true;
        }
        AppboyLogger.d(TAG, "Large icon resource id not present for notification");
        AppboyLogger.d(TAG, "Large icon not set for notification");
        return false;
    }

    public static void setSoundIfPresentAndSupported(NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle == null || !bundle.containsKey(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_KEY)) {
            AppboyLogger.d(TAG, "Sound key not present in notification extras. Not setting sound for notification.");
            return;
        }
        String string = bundle.getString(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_KEY);
        if (string == null) {
            return;
        }
        if (string.equals(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE)) {
            AppboyLogger.d(TAG, "Setting default sound for notification.");
            builder.setDefaults(1);
            return;
        }
        AppboyLogger.d(TAG, "Setting sound for notification via uri.");
        builder.setSound(Uri.parse(string));
    }

    public static void setSummaryTextIfPresentAndSupported(NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle == null || !bundle.containsKey(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY)) {
            AppboyLogger.d(TAG, "Summary text not present in notification extras. Not setting summary text for notification.");
            return;
        }
        String string = bundle.getString(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
        if (string != null) {
            AppboyLogger.d(TAG, "Setting summary text for notification");
            builder.setSubText(string);
        }
    }

    public static void setPriorityIfPresentAndSupported(NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle != null) {
            AppboyLogger.d(TAG, "Setting priority for notification");
            builder.setPriority(getNotificationPriority(bundle));
        }
    }

    public static void setStyleIfSupported(Context context, NotificationCompat.Builder builder, Bundle bundle, Bundle bundle2) {
        if (bundle != null) {
            AppboyLogger.d(TAG, "Setting style for notification");
            builder.setStyle(AppboyNotificationStyleFactory.getBigNotificationStyle(context, bundle, bundle2, builder));
        }
    }

    public static void setAccentColorIfPresentAndSupported(AppboyConfigurationProvider appboyConfigurationProvider, NotificationCompat.Builder builder, Bundle bundle) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (bundle == null || !bundle.containsKey("ac")) {
            AppboyLogger.d(TAG, "Using default accent color for notification");
            builder.setColor(appboyConfigurationProvider.getDefaultNotificationAccentColor());
            return;
        }
        AppboyLogger.d(TAG, "Using accent color for notification from extras bundle");
        builder.setColor((int) Long.parseLong(bundle.getString("ac")));
    }

    public static void setCategoryIfPresentAndSupported(NotificationCompat.Builder builder, Bundle bundle) {
        if (Build.VERSION.SDK_INT < 21) {
            AppboyLogger.d(TAG, "Notification category not supported on this android version. Not setting category for notification.");
        } else if (bundle == null || !bundle.containsKey(Constants.APPBOY_PUSH_CATEGORY_KEY)) {
            AppboyLogger.d(TAG, "Category not present in notification extras. Not setting category for notification.");
        } else {
            AppboyLogger.d(TAG, "Setting category for notification");
            builder.setCategory(bundle.getString(Constants.APPBOY_PUSH_CATEGORY_KEY));
        }
    }

    public static void setVisibilityIfPresentAndSupported(NotificationCompat.Builder builder, Bundle bundle) {
        if (Build.VERSION.SDK_INT < 21) {
            AppboyLogger.d(TAG, "Notification visibility not supported on this android version. Not setting visibility for notification.");
        } else if (bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_VISIBILITY_KEY)) {
            try {
                int parseInt = Integer.parseInt(bundle.getString(Constants.APPBOY_PUSH_VISIBILITY_KEY));
                if (isValidNotificationVisibility(parseInt)) {
                    AppboyLogger.d(TAG, "Setting visibility for notification");
                    builder.setVisibility(parseInt);
                    return;
                }
                String str = TAG;
                AppboyLogger.e(str, "Received invalid notification visibility " + parseInt);
            } catch (Exception e) {
                AppboyLogger.e(TAG, "Failed to parse visibility from notificationExtras", e);
            }
        }
    }

    public static void setPublicVersionIfPresentAndSupported(Context context, AppboyConfigurationProvider appboyConfigurationProvider, NotificationCompat.Builder builder, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 21 && bundle != null && bundle.containsKey(Constants.APPBOY_PUSH_PUBLIC_NOTIFICATION_KEY)) {
            Bundle parseJSONStringDictionaryIntoBundle = parseJSONStringDictionaryIntoBundle(bundle.getString(Constants.APPBOY_PUSH_PUBLIC_NOTIFICATION_KEY));
            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(context);
            setContentIfPresent(builder2, parseJSONStringDictionaryIntoBundle);
            setTitleIfPresent(builder2, parseJSONStringDictionaryIntoBundle);
            setSummaryTextIfPresentAndSupported(builder2, parseJSONStringDictionaryIntoBundle);
            setSmallIcon(appboyConfigurationProvider, builder2);
            setAccentColorIfPresentAndSupported(appboyConfigurationProvider, builder2, parseJSONStringDictionaryIntoBundle);
            builder.setPublicVersion(builder2.build());
        }
    }

    public static void logBaiduNotificationClick(Context context, String str) {
        if (str == null) {
            AppboyLogger.w(TAG, "customContentString was null. Doing nothing.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("source", (String) null);
            String optString2 = jSONObject.optString("cid", (String) null);
            if (optString != null && Constants.APPBOY.equals(optString) && optString2 != null) {
                Appboy.getInstance(context).logPushNotificationOpened(optString2);
            }
        } catch (Exception e) {
            String str2 = TAG;
            AppboyLogger.e(str2, "Caught an exception processing customContentString: " + str, e);
        }
    }

    public static void handleCancelNotificationAction(Context context, Intent intent) {
        try {
            if (intent.hasExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID)) {
                int intExtra = intent.getIntExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID, -1);
                String str = TAG;
                AppboyLogger.d(str, "Cancelling notification action with id: " + intExtra);
                ((NotificationManager) context.getSystemService("notification")).cancel(Constants.APPBOY_PUSH_NOTIFICATION_TAG, intExtra);
            }
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Exception occurred handling cancel notification intent.", e);
        }
    }

    public static void cancelNotification(Context context, int i) {
        try {
            String str = TAG;
            AppboyLogger.d(str, "Cancelling notification action with id: " + i);
            Intent intent = new Intent(Constants.APPBOY_CANCEL_NOTIFICATION_ACTION).setClass(context, getNotificationReceiverClass());
            intent.putExtra(Constants.APPBOY_PUSH_NOTIFICATION_ID, i);
            IntentUtils.addComponentAndSendBroadcast(context, intent);
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Exception occurred attempting to cancel notification.", e);
        }
    }

    public static Class<?> getNotificationReceiverClass() {
        if (Constants.IS_AMAZON.booleanValue()) {
            return AppboyAdmReceiver.class;
        }
        return AppboyFcmReceiver.class;
    }

    @Deprecated
    public static boolean isUninstallTrackingPush(Bundle bundle) {
        AppboyLogger.w(TAG, "Uninstall tracking no longer sends a silent push notification to devices. This method should not be used. Returning false.");
        return false;
    }

    public static void setNotificationChannelIfSupported(Context context, AppboyConfigurationProvider appboyConfigurationProvider, NotificationCompat.Builder builder, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            NotificationChannel validNotificationChannel = getValidNotificationChannel(notificationManager, bundle);
            if (validNotificationChannel != null) {
                String str = TAG;
                AppboyLogger.d(str, "Using notification channel with id: " + validNotificationChannel.getId());
                builder.setChannelId(validNotificationChannel.getId());
            } else if (validNotificationChannel == null || validNotificationChannel.getId().equals(Constants.APPBOY_PUSH_DEFAULT_NOTIFICATION_CHANNEL_ID)) {
                NotificationChannel notificationChannel = new NotificationChannel(Constants.APPBOY_PUSH_DEFAULT_NOTIFICATION_CHANNEL_ID, appboyConfigurationProvider.getDefaultNotificationChannelName(), 3);
                notificationChannel.setDescription(appboyConfigurationProvider.getDefaultNotificationChannelDescription());
                notificationManager.createNotificationChannel(notificationChannel);
                builder.setChannelId(Constants.APPBOY_PUSH_DEFAULT_NOTIFICATION_CHANNEL_ID);
                AppboyLogger.d(TAG, "Using default notification channel with id: com_appboy_default_notification_channel");
            }
        }
    }

    public static void setNotificationBadgeNumberIfPresent(NotificationCompat.Builder builder, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            String string = bundle.getString(Constants.APPBOY_PUSH_NOTIFICATION_BADGE_COUNT_KEY, (String) null);
            if (!StringUtils.isNullOrBlank(string)) {
                try {
                    builder.setNumber(Integer.parseInt(string));
                } catch (NumberFormatException e) {
                    AppboyLogger.e(TAG, "Caught exception while setting number on notification.", e);
                }
            }
        }
    }

    public static void logPushDeliveryEvent(Context context, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("cid");
            if (!StringUtils.isNullOrBlank(string)) {
                Appboy.getInstance(context).logPushDeliveryEvent(string);
                return;
            }
            String str = TAG;
            AppboyLogger.d(str, "Could not log push delivery event due to null or blank campaign id in push extras bundle: " + bundle);
            return;
        }
        AppboyLogger.d(TAG, "Could not log push delivery event due to null push extras bundle.");
    }

    public static void handlePushStoryPageClicked(Context context, Intent intent) {
        try {
            Appboy.getInstance(context).logPushStoryPageClicked(intent.getStringExtra(Constants.APPBOY_CAMPAIGN_ID), intent.getStringExtra(Constants.APPBOY_STORY_PAGE_ID));
            if (!StringUtils.isNullOrBlank(intent.getStringExtra(Constants.APPBOY_ACTION_URI_KEY))) {
                intent.putExtra("uri", intent.getStringExtra(Constants.APPBOY_ACTION_URI_KEY));
                String stringExtra = intent.getStringExtra(Constants.APPBOY_ACTION_USE_WEBVIEW_KEY);
                if (!StringUtils.isNullOrBlank(stringExtra)) {
                    intent.putExtra(Constants.APPBOY_PUSH_OPEN_URI_IN_WEBVIEW_KEY, stringExtra);
                }
            } else {
                intent.removeExtra("uri");
            }
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            sendNotificationOpenedBroadcast(context, intent);
            if (new AppboyConfigurationProvider(context).getHandlePushDeepLinksAutomatically()) {
                routeUserWithNotificationOpenedIntent(context, intent);
            }
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Caught exception while handling story click.", e);
        }
    }

    public static void handleContentCardsSerializedCardIfPresent(Context context, Bundle bundle) {
        if (!Constants.IS_AMAZON.booleanValue() && bundle.containsKey(Constants.APPBOY_PUSH_CONTENT_CARD_SYNC_DATA_KEY)) {
            String string = bundle.getString(Constants.APPBOY_PUSH_CONTENT_CARD_SYNC_DATA_KEY, (String) null);
            String string2 = bundle.getString(Constants.APPBOY_PUSH_CONTENT_CARD_SYNC_USER_ID_KEY, (String) null);
            String str = TAG;
            AppboyLogger.d(str, "Push contains associated Content Cards card. User id: " + string2 + " Card data: " + string);
            AppboyInternal.addSerializedContentCardToStorage(context, string, string2);
        }
    }

    static String getOptionalStringResource(Resources resources, int i, String str) {
        try {
            return resources.getString(i);
        } catch (Resources.NotFoundException unused) {
            return str;
        }
    }

    static void sendNotificationOpenedBroadcast(Context context, Intent intent) {
        AppboyLogger.d(TAG, "Sending notification opened broadcast");
        sendPushActionIntent(context, APPBOY_NOTIFICATION_OPENED_SUFFIX, intent.getExtras());
    }

    private static void logNotificationOpened(Context context, Intent intent) {
        Appboy.getInstance(context).logPushNotificationOpened(intent);
    }

    static NotificationChannel getValidNotificationChannel(NotificationManager notificationManager, Bundle bundle) {
        if (bundle == null) {
            AppboyLogger.d(TAG, "Notification extras bundle was null. Could not find a valid notification channel");
            return null;
        }
        String string = bundle.getString(Constants.APPBOY_PUSH_NOTIFICATION_CHANNEL_ID_KEY, (String) null);
        if (!StringUtils.isNullOrBlank(string)) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(string);
            if (notificationChannel != null) {
                String str = TAG;
                AppboyLogger.d(str, "Found notification channel in extras with id: " + string);
                return notificationChannel;
            }
            String str2 = TAG;
            AppboyLogger.d(str2, "Notification channel from extras is invalid, no channel found with id: " + string);
        }
        NotificationChannel notificationChannel2 = notificationManager.getNotificationChannel(Constants.APPBOY_PUSH_DEFAULT_NOTIFICATION_CHANNEL_ID);
        if (notificationChannel2 != null) {
            return notificationChannel2;
        }
        AppboyLogger.d(TAG, "Appboy default notification channel does not exist on device.");
        return null;
    }

    private static int getNotificationChannelImportance(NotificationChannel notificationChannel) {
        return notificationChannel.getImportance();
    }

    private static PendingIntent getPushActionPendingIntent(Context context, String str, Bundle bundle) {
        Intent intent = new Intent(str).setClass(context, getNotificationReceiverClass());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return PendingIntent.getBroadcast(context, IntentUtils.getRequestCode(), intent, 1073741824);
    }

    private static void sendPushActionIntent(Context context, String str, Bundle bundle) {
        Intent intent = new Intent(context.getPackageName() + str);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        IntentUtils.addComponentAndSendBroadcast(context, intent);
    }
}
