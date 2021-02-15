package com.appboy.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.enums.AppboyViewBounds;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;
import java.util.HashMap;
import java.util.Map;

public class AppboyNotificationStyleFactory {
    public static final int BIG_PICTURE_STYLE_IMAGE_HEIGHT = 192;
    private static final String CENTER = "center";
    private static final String END = "end";
    private static final Map<String, Integer> GRAVITY_MAP;
    private static final String START = "start";
    private static final Integer[] STORY_FULL_VIEW_XML_IDS = {Integer.valueOf(R.id.com_appboy_story_text_view), Integer.valueOf(R.id.com_appboy_story_text_view_container), Integer.valueOf(R.id.com_appboy_story_text_view_small), Integer.valueOf(R.id.com_appboy_story_text_view_small_container), Integer.valueOf(R.id.com_appboy_story_image_view), Integer.valueOf(R.id.com_appboy_story_relative_layout)};
    private static final String STORY_SET_GRAVITY = "setGravity";
    private static final String STORY_SET_VISIBILITY = "setVisibility";
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyNotificationStyleFactory.class);

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(START, Integer.valueOf(GravityCompat.START));
        hashMap.put(CENTER, 17);
        hashMap.put(END, Integer.valueOf(GravityCompat.END));
        GRAVITY_MAP = hashMap;
    }

    public static NotificationCompat.Style getBigNotificationStyle(Context context, Bundle bundle, Bundle bundle2, NotificationCompat.Builder builder) {
        NotificationCompat.Style style;
        if (bundle.containsKey(Constants.APPBOY_PUSH_STORY_KEY)) {
            AppboyLogger.d(TAG, "Rendering push notification with DecoratedCustomViewStyle (Story)");
            style = getStoryStyle(context, bundle, builder);
        } else if (bundle2 == null || !bundle2.containsKey(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY)) {
            style = null;
        } else {
            AppboyLogger.d(TAG, "Rendering push notification with BigPictureStyle");
            style = getBigPictureNotificationStyle(context, bundle, bundle2);
        }
        if (style != null) {
            return style;
        }
        AppboyLogger.d(TAG, "Rendering push notification with BigTextStyle");
        return getBigTextNotificationStyle(bundle);
    }

    public static NotificationCompat.BigTextStyle getBigTextNotificationStyle(Bundle bundle) {
        String str = null;
        if (bundle == null) {
            return null;
        }
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText(bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY));
        String string = bundle.containsKey(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY) ? bundle.getString(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY) : null;
        if (bundle.containsKey(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY)) {
            str = bundle.getString(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY);
        }
        if (string != null) {
            bigTextStyle.setSummaryText(string);
        }
        if (str != null) {
            bigTextStyle.setBigContentTitle(str);
        }
        return bigTextStyle;
    }

    public static NotificationCompat.DecoratedCustomViewStyle getStoryStyle(Context context, Bundle bundle, NotificationCompat.Builder builder) {
        NotificationCompat.DecoratedCustomViewStyle decoratedCustomViewStyle = new NotificationCompat.DecoratedCustomViewStyle();
        int pushStoryPageCount = getPushStoryPageCount(bundle);
        int pushStoryPageIndex = getPushStoryPageIndex(bundle);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.com_appboy_notification_story_one_image);
        if (!populatePushStoryPage(remoteViews, context, bundle, pushStoryPageIndex)) {
            AppboyLogger.w(TAG, "Push story page was not populated correctly. Not using DecoratedCustomViewStyle.");
            return null;
        }
        remoteViews.setOnClickPendingIntent(R.id.com_appboy_story_button_previous, createStoryTraversedPendingIntent(context, bundle, ((pushStoryPageIndex - 1) + pushStoryPageCount) % pushStoryPageCount));
        remoteViews.setOnClickPendingIntent(R.id.com_appboy_story_button_next, createStoryTraversedPendingIntent(context, bundle, (pushStoryPageIndex + 1) % pushStoryPageCount));
        builder.setCustomBigContentView(remoteViews);
        builder.setOnlyAlertOnce(true);
        return decoratedCustomViewStyle;
    }

    public static NotificationCompat.BigPictureStyle getBigPictureNotificationStyle(Context context, Bundle bundle, Bundle bundle2) {
        Bitmap bitmap;
        if (bundle2 != null && bundle2.containsKey(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY)) {
            String string = bundle2.getString(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY);
            if (StringUtils.isNullOrBlank(string) || (bitmap = AppboyImageUtils.getBitmap(context, Uri.parse(string), AppboyViewBounds.NOTIFICATION_EXPANDED_IMAGE)) == null) {
                return null;
            }
            try {
                if (bitmap.getWidth() > bitmap.getHeight()) {
                    DisplayMetrics defaultScreenDisplayMetrics = AppboyImageUtils.getDefaultScreenDisplayMetrics(context);
                    int pixelsFromDensityAndDp = AppboyImageUtils.getPixelsFromDensityAndDp(defaultScreenDisplayMetrics.densityDpi, 192);
                    int i = pixelsFromDensityAndDp * 2;
                    if (i > defaultScreenDisplayMetrics.widthPixels) {
                        i = defaultScreenDisplayMetrics.widthPixels;
                    }
                    try {
                        bitmap = Bitmap.createScaledBitmap(bitmap, i, pixelsFromDensityAndDp, true);
                    } catch (Exception e) {
                        AppboyLogger.e(TAG, "Failed to scale image bitmap, using original.", e);
                    }
                }
                if (bitmap == null) {
                    AppboyLogger.i(TAG, "Bitmap download failed for push notification. No image will be included with the notification.");
                    return null;
                }
                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
                bigPictureStyle.bigPicture(bitmap);
                setBigPictureSummaryAndTitle(bigPictureStyle, bundle);
                return bigPictureStyle;
            } catch (Exception e2) {
                AppboyLogger.e(TAG, "Failed to create Big Picture Style.", e2);
            }
        }
        return null;
    }

    private static PendingIntent createStoryPageClickedPendingIntent(Context context, String str, String str2, String str3, String str4) {
        Intent intent = new Intent(Constants.APPBOY_STORY_CLICKED_ACTION).setClass(context, AppboyNotificationRoutingActivity.class);
        intent.putExtra(Constants.APPBOY_ACTION_URI_KEY, str);
        intent.putExtra(Constants.APPBOY_ACTION_USE_WEBVIEW_KEY, str2);
        intent.putExtra(Constants.APPBOY_STORY_PAGE_ID, str3);
        intent.putExtra(Constants.APPBOY_CAMPAIGN_ID, str4);
        return PendingIntent.getActivity(context, IntentUtils.getRequestCode(), intent, 1073741824);
    }

    private static PendingIntent createStoryTraversedPendingIntent(Context context, Bundle bundle, int i) {
        Intent intent = new Intent(Constants.APPBOY_STORY_TRAVERSE_CLICKED_ACTION).setClass(context, AppboyNotificationUtils.getNotificationReceiverClass());
        if (bundle != null) {
            bundle.putInt(Constants.APPBOY_STORY_INDEX_KEY, i);
            intent.putExtras(bundle);
        }
        return PendingIntent.getBroadcast(context, IntentUtils.getRequestCode(), intent, 1073741824);
    }

    static int getPushStoryPageCount(Bundle bundle) {
        int i = 0;
        while (pushStoryPageExistsForIndex(bundle, i)) {
            i++;
        }
        return i;
    }

    static boolean pushStoryPageExistsForIndex(Bundle bundle, int i) {
        return AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_IMAGE_KEY_TEMPLATE, (String) null) != null;
    }

    static int getPushStoryPageIndex(Bundle bundle) {
        if (!bundle.containsKey(Constants.APPBOY_STORY_INDEX_KEY)) {
            return 0;
        }
        return bundle.getInt(Constants.APPBOY_STORY_INDEX_KEY);
    }

    private static boolean populatePushStoryPage(RemoteViews remoteViews, Context context, Bundle bundle, int i) {
        String string = bundle.getString("cid");
        String actionFieldAtIndex = AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_TITLE_KEY_TEMPLATE);
        if (!StringUtils.isNullOrBlank(actionFieldAtIndex)) {
            remoteViews.setTextViewText(STORY_FULL_VIEW_XML_IDS[0].intValue(), actionFieldAtIndex);
            remoteViews.setInt(STORY_FULL_VIEW_XML_IDS[1].intValue(), STORY_SET_GRAVITY, GRAVITY_MAP.get(AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_TITLE_JUSTIFICATION_KEY_TEMPLATE, CENTER)).intValue());
        } else {
            remoteViews.setInt(STORY_FULL_VIEW_XML_IDS[1].intValue(), STORY_SET_VISIBILITY, 8);
        }
        String actionFieldAtIndex2 = AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_SUBTITLE_KEY_TEMPLATE);
        if (!StringUtils.isNullOrBlank(actionFieldAtIndex2)) {
            remoteViews.setTextViewText(STORY_FULL_VIEW_XML_IDS[2].intValue(), actionFieldAtIndex2);
            remoteViews.setInt(STORY_FULL_VIEW_XML_IDS[3].intValue(), STORY_SET_GRAVITY, GRAVITY_MAP.get(AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_SUBTITLE_JUSTIFICATION_KEY_TEMPLATE, CENTER)).intValue());
        } else {
            remoteViews.setInt(STORY_FULL_VIEW_XML_IDS[3].intValue(), STORY_SET_VISIBILITY, 8);
        }
        Bitmap bitmapFromUrl = Appboy.getInstance(context).getAppboyImageLoader().getBitmapFromUrl(context, AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_IMAGE_KEY_TEMPLATE), AppboyViewBounds.NOTIFICATION_ONE_IMAGE_STORY);
        if (bitmapFromUrl == null) {
            return false;
        }
        remoteViews.setImageViewBitmap(STORY_FULL_VIEW_XML_IDS[4].intValue(), bitmapFromUrl);
        remoteViews.setOnClickPendingIntent(STORY_FULL_VIEW_XML_IDS[5].intValue(), createStoryPageClickedPendingIntent(context, AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_DEEP_LINK_KEY_TEMPLATE), AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_USE_WEBVIEW_KEY_TEMPLATE), AppboyNotificationActionUtils.getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_STORY_ID_KEY_TEMPLATE, ""), string));
        return true;
    }

    static void setBigPictureSummaryAndTitle(NotificationCompat.BigPictureStyle bigPictureStyle, Bundle bundle) {
        String str = null;
        String string = bundle.containsKey(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY) ? bundle.getString(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY) : null;
        if (bundle.containsKey(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY)) {
            str = bundle.getString(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY);
        }
        if (string != null) {
            bigPictureStyle.setSummaryText(string);
        }
        if (str != null) {
            bigPictureStyle.setBigContentTitle(str);
        }
        if (bundle.getString(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY) == null && string == null) {
            bigPictureStyle.setSummaryText(bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY));
        }
    }
}
