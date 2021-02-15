package com.appboy.enums;

import bo.app.ed;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.appevents.UserDataStore;
import com.facebook.applinks.AppLinkData;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public enum CardKey {
    ID("id", "id"),
    VIEWED("viewed", "v"),
    CREATED("created", "ca"),
    EXPIRES_AT("expires_at", "ea"),
    EXTRAS(AppLinkData.ARGUMENTS_EXTRAS_KEY, "e"),
    OPEN_URI_IN_WEBVIEW("use_webview", "uw"),
    TYPE("type", "tp"),
    CATEGORIES("categories", (int) null),
    UPDATED("updated", (int) null),
    DISMISSED((String) null, Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE),
    REMOVED((String) null, "r"),
    PINNED((String) null, "p"),
    DISMISSIBLE((String) null, UserDataStore.DATE_OF_BIRTH),
    READ((String) null, "read"),
    CLICKED((String) null, "cl"),
    BANNER_IMAGE_IMAGE(MessengerShareContentUtility.MEDIA_IMAGE, "i"),
    BANNER_IMAGE_URL("url", "u"),
    BANNER_IMAGE_DOMAIN("domain", (int) null),
    BANNER_IMAGE_ASPECT_RATIO("aspect_ratio", "ar"),
    CAPTIONED_IMAGE_IMAGE(MessengerShareContentUtility.MEDIA_IMAGE, "i"),
    CAPTIONED_IMAGE_TITLE("title", "tt"),
    CAPTIONED_IMAGE_DESCRIPTION("description", "ds"),
    CAPTIONED_IMAGE_URL("url", "u"),
    CAPTIONED_IMAGE_DOMAIN("domain", "dm"),
    CAPTIONED_IMAGE_ASPECT_RATIO("aspect_ratio", "ar"),
    TEXT_ANNOUNCEMENT_TITLE("title", "tt"),
    TEXT_ANNOUNCEMENT_DESCRIPTION("description", "ds"),
    TEXT_ANNOUNCEMENT_URL("url", "u"),
    TEXT_ANNOUNCEMENT_DOMAIN("domain", "dm"),
    SHORT_NEWS_IMAGE(MessengerShareContentUtility.MEDIA_IMAGE, "i"),
    SHORT_NEWS_TITLE("title", "tt"),
    SHORT_NEWS_DESCRIPTION("description", "ds"),
    SHORT_NEWS_URL("url", "u"),
    SHORT_NEWS_DOMAIN("domain", "dm");
    
    /* access modifiers changed from: private */
    public static final String a = null;
    /* access modifiers changed from: private */
    public static final Map<String, CardType> b = null;
    private String c;
    private String d;

    static {
        a = AppboyLogger.getAppboyLogTag(CardKey.class);
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put("banner_image", CardType.BANNER);
        b.put("captioned_image", CardType.CAPTIONED_IMAGE);
        b.put("text_announcement", CardType.TEXT_ANNOUNCEMENT);
        b.put("short_news", CardType.SHORT_NEWS);
        b.put("control", CardType.CONTROL);
    }

    private CardKey(String str, String str2) {
        this.c = str;
        this.d = str2;
    }

    public String getContentCardsKey() {
        return this.d;
    }

    public String getFeedKey() {
        return this.c;
    }

    public static class Provider {
        private final boolean a;

        public Provider(boolean z) {
            this.a = z;
        }

        public String getKey(CardKey cardKey) {
            return this.a ? cardKey.getContentCardsKey() : cardKey.getFeedKey();
        }

        public CardType getCardTypeFromJson(JSONObject jSONObject) {
            String optString = jSONObject.optString(getKey(CardKey.TYPE), (String) null);
            if (!StringUtils.isNullOrEmpty(optString) && this.a && optString.equals("short_news") && StringUtils.isNullOrEmpty(ed.a(jSONObject, getKey(CardKey.SHORT_NEWS_IMAGE)))) {
                AppboyLogger.v(CardKey.a, "Short News card doesn't contain image url, parsing type as Text Announcement");
                optString = "text_announcement";
            }
            if (CardKey.b.containsKey(optString)) {
                return (CardType) CardKey.b.get(optString);
            }
            return CardType.DEFAULT;
        }
    }
}
