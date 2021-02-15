package bo.app;

import com.appboy.models.IPutIntoJson;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.HashMap;
import java.util.Map;

public enum w implements IPutIntoJson<String> {
    LOCATION_RECORDED("lr"),
    CUSTOM_EVENT("ce"),
    PURCHASE("p"),
    PUSH_STORY_PAGE_CLICK("cic"),
    PUSH_NOTIFICATION_TRACKING("pc"),
    PUSH_NOTIFICATION_ACTION_TRACKING("ca"),
    INTERNAL("i"),
    INTERNAL_ERROR("ie"),
    NEWS_FEED_CARD_IMPRESSION("ci"),
    NEWS_FEED_CARD_CLICK("cc"),
    GEOFENCE("g"),
    CONTENT_CARDS_CLICK("ccc"),
    CONTENT_CARDS_IMPRESSION("cci"),
    CONTENT_CARDS_CONTROL_IMPRESSION("ccic"),
    CONTENT_CARDS_DISMISS("ccd"),
    INCREMENT("inc"),
    ADD_TO_CUSTOM_ATTRIBUTE_ARRAY("add"),
    REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY("rem"),
    SET_CUSTOM_ATTRIBUTE_ARRAY("set"),
    INAPP_MESSAGE_IMPRESSION("si"),
    INAPP_MESSAGE_CONTROL_IMPRESSION("iec"),
    INAPP_MESSAGE_CLICK(AdParameterKeys.CONDITION),
    INAPP_MESSAGE_BUTTON_CLICK("sbc"),
    INAPP_MESSAGE_DISPLAY_FAILURE("sfe"),
    USER_ALIAS("uae"),
    SESSION_START("ss"),
    SESSION_END("se"),
    TEST_TYPE("tt"),
    PUSH_DELIVERY("pd"),
    LOCATION_CUSTOM_ATTRIBUTE_ADD("lcaa"),
    LOCATION_CUSTOM_ATTRIBUTE_REMOVE("lcar");
    
    private static final Map<String, w> G = null;
    private final String F;

    static {
        int i;
        HashMap hashMap = new HashMap();
        for (w wVar : values()) {
            hashMap.put(wVar.F, wVar);
        }
        G = new HashMap(hashMap);
    }

    private w(String str) {
        this.F = str;
    }

    public static w a(String str) {
        if (G.containsKey(str)) {
            return G.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }

    public static boolean a(w wVar) {
        return b(wVar) || wVar.equals(PUSH_NOTIFICATION_ACTION_TRACKING) || wVar.equals(PUSH_STORY_PAGE_CLICK);
    }

    public static boolean b(w wVar) {
        return wVar.equals(PUSH_NOTIFICATION_TRACKING);
    }

    /* renamed from: a */
    public String forJsonPut() {
        return this.F;
    }
}
