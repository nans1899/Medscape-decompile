package bo.app;

import com.appboy.enums.CardKey;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.models.cards.Card;
import com.appboy.models.cards.ControlCard;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class by {
    private static final String a = AppboyLogger.getAppboyLogTag(by.class);

    public static List<Card> a(JSONArray jSONArray, CardKey.Provider provider, bl blVar, dm dmVar, c cVar) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Card a2 = a(jSONArray.optString(i), provider, blVar, dmVar, cVar);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            } catch (Exception e) {
                String str = a;
                AppboyLogger.e(str, "Unable to create Card JSON in array. Ignoring. Was on element index: " + i + " of json array: " + jSONArray.toString(), e);
            }
        }
        return arrayList;
    }

    private static Card a(String str, CardKey.Provider provider, bl blVar, dm dmVar, c cVar) {
        return a(new JSONObject(str), provider, blVar, dmVar, cVar);
    }

    static Card a(JSONObject jSONObject, CardKey.Provider provider, bl blVar, dm dmVar, c cVar) {
        int i = AnonymousClass1.a[provider.getCardTypeFromJson(jSONObject).ordinal()];
        if (i == 1) {
            return new BannerImageCard(jSONObject, provider, blVar, dmVar, cVar);
        }
        if (i == 2) {
            return new CaptionedImageCard(jSONObject, provider, blVar, dmVar, cVar);
        }
        if (i == 3) {
            return new ShortNewsCard(jSONObject, provider, blVar, dmVar, cVar);
        }
        if (i == 4) {
            return new TextAnnouncementCard(jSONObject, provider, blVar, dmVar, cVar);
        }
        if (i == 5) {
            return new ControlCard(jSONObject, provider, blVar, dmVar, cVar);
        }
        throw new JSONException("Failed to construct java object from JSON [" + jSONObject.toString() + "]");
    }

    /* renamed from: bo.app.by$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.appboy.enums.CardType[] r0 = com.appboy.enums.CardType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.appboy.enums.CardType r1 = com.appboy.enums.CardType.BANNER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appboy.enums.CardType r1 = com.appboy.enums.CardType.CAPTIONED_IMAGE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appboy.enums.CardType r1 = com.appboy.enums.CardType.SHORT_NEWS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.appboy.enums.CardType r1 = com.appboy.enums.CardType.TEXT_ANNOUNCEMENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.appboy.enums.CardType r1 = com.appboy.enums.CardType.CONTROL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.by.AnonymousClass1.<clinit>():void");
        }
    }
}
