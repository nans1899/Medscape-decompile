package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.configuration.AppboyConfig;
import com.appboy.support.AppboyLogger;
import java.util.EnumSet;
import java.util.Set;
import org.json.JSONArray;

public class m {
    private static final String a = AppboyLogger.getAppboyLogTag(m.class);
    private final SharedPreferences b;

    public m(Context context) {
        this.b = context.getSharedPreferences("com.appboy.override.configuration.cache", 0);
    }

    public void a(AppboyConfig appboyConfig) {
        String str = a;
        AppboyLogger.d(str, "Setting Braze Override configuration with config: " + appboyConfig);
        SharedPreferences.Editor edit = this.b.edit();
        a(edit, "com_appboy_api_key", appboyConfig.getApiKey());
        a(edit, "com_appboy_server_target", appboyConfig.getServerTarget());
        a(edit, "com_appboy_sdk_flavor", (Enum) appboyConfig.getSdkFlavor());
        a(edit, "com_appboy_newsfeed_unread_visual_indicator_on", appboyConfig.getIsNewsFeedVisualIndicatorOn());
        a(edit, "com_appboy_custom_endpoint", appboyConfig.getCustomEndpoint());
        a(edit, "com_appboy_push_small_notification_icon", appboyConfig.getSmallNotificationIcon());
        a(edit, "com_appboy_push_large_notification_icon", appboyConfig.getLargeNotificationIcon());
        a(edit, "com_appboy_session_timeout", appboyConfig.getSessionTimeout());
        a(edit, "com_appboy_default_notification_accent_color", appboyConfig.getDefaultNotificationAccentColor());
        a(edit, "com_appboy_trigger_action_minimum_time_interval_seconds", appboyConfig.getTriggerActionMinimumTimeIntervalSeconds());
        a(edit, "com_appboy_push_adm_messaging_registration_enabled", appboyConfig.getAdmMessagingRegistrationEnabled());
        a(edit, "com_appboy_handle_push_deep_links_automatically", appboyConfig.getHandlePushDeepLinksAutomatically());
        a(edit, "com_appboy_notifications_enabled_tracking_on", appboyConfig.getNotificationsEnabledTrackingOn());
        a(edit, "com_appboy_disable_location_collection", appboyConfig.getDisableLocationCollection());
        a(edit, "com_appboy_data_flush_interval_bad_network", appboyConfig.getBadNetworkDataFlushInterval());
        a(edit, "com_appboy_data_flush_interval_good_network", appboyConfig.getGoodNetworkDataFlushInterval());
        a(edit, "com_appboy_data_flush_interval_great_network", appboyConfig.getGreatNetworkDataFlushInterval());
        a(edit, "com_appboy_default_notification_channel_name", appboyConfig.getDefaultNotificationChannelName());
        a(edit, "com_appboy_default_notification_channel_description", appboyConfig.getDefaultNotificationChannelDescription());
        a(edit, "com_appboy_push_deep_link_back_stack_activity_enabled", appboyConfig.getPushDeepLinkBackStackActivityEnabled());
        a(edit, "com_appboy_push_deep_link_back_stack_activity_class_name", appboyConfig.getPushDeepLinkBackStackActivityClassName());
        a(edit, "com_appboy_session_start_based_timeout_enabled", appboyConfig.getIsSessionStartBasedTimeoutEnabled());
        a(edit, "com_appboy_firebase_cloud_messaging_registration_enabled", appboyConfig.getIsFirebaseCloudMessagingRegistrationEnabled());
        a(edit, "com_appboy_firebase_cloud_messaging_sender_id", appboyConfig.getFirebaseCloudMessagingSenderIdKey());
        a(edit, "com_appboy_content_cards_unread_visual_indicator_enabled", appboyConfig.getContentCardsUnreadVisualIndicatorEnabled());
        a(edit, "com_appboy_device_object_whitelisting_enabled", appboyConfig.getDeviceObjectWhitelistEnabled());
        a(edit, "com_appboy_device_in_app_message_accessibility_exclusive_mode_enabled", appboyConfig.getIsInAppMessageAccessibilityExclusiveModeEnabled());
        if (appboyConfig.getLocaleToApiMapping() != null) {
            a(edit, "com_appboy_locale_api_key_map", new JSONArray(appboyConfig.getLocaleToApiMapping()).toString());
        }
        EnumSet<v> deviceObjectWhitelist = appboyConfig.getDeviceObjectWhitelist();
        if (deviceObjectWhitelist != null) {
            a(edit, "com_appboy_device_object_whitelist", dv.a(deviceObjectWhitelist));
        }
        edit.apply();
    }

    public void a() {
        AppboyLogger.d(a, "Clearing Braze Override configuration cache");
        SharedPreferences.Editor edit = this.b.edit();
        edit.clear();
        edit.apply();
    }

    public String a(String str, String str2) {
        return this.b.getString(str, str2);
    }

    public int a(String str, int i) {
        return this.b.getInt(str, i);
    }

    public boolean a(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public Set<String> a(String str, Set<String> set) {
        return this.b.getStringSet(str, set);
    }

    public boolean a(String str) {
        return this.b.contains(str);
    }

    /* access modifiers changed from: package-private */
    public void a(SharedPreferences.Editor editor, String str, Enum enumR) {
        if (enumR != null) {
            a(editor, str, enumR.toString());
        }
    }

    private static void a(SharedPreferences.Editor editor, String str, Integer num) {
        if (num != null) {
            editor.putInt(str, num.intValue());
        }
    }

    private static void a(SharedPreferences.Editor editor, String str, String str2) {
        if (str2 != null) {
            editor.putString(str, str2);
        }
    }

    private static void a(SharedPreferences.Editor editor, String str, Boolean bool) {
        if (bool != null) {
            editor.putBoolean(str, bool.booleanValue());
        }
    }

    private static void a(SharedPreferences.Editor editor, String str, Set<String> set) {
        if (set != null) {
            editor.putStringSet(str, set);
        }
    }
}
