package bo.app;

import com.appboy.enums.inappmessage.MessageType;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageBase;
import com.appboy.models.InAppMessageControl;
import com.appboy.models.InAppMessageFull;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class ec {
    private static final String a = AppboyLogger.getAppboyLogTag(ec.class);

    public static IInAppMessage a(String str, bq bqVar) {
        try {
            if (!StringUtils.isNullOrBlank(str)) {
                return a(new JSONObject(str), bqVar);
            }
            AppboyLogger.i(a, "In-app message string was null or blank. Not de-serializing message.");
            return null;
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Encountered JSONException processing in-app message string: " + str, e);
            return null;
        } catch (Exception e2) {
            String str3 = a;
            AppboyLogger.e(str3, "Failed to deserialize the in-app message string." + str, e2);
            return null;
        }
    }

    public static IInAppMessage a(JSONObject jSONObject, bq bqVar) {
        if (jSONObject == null) {
            try {
                AppboyLogger.d(a, "In-app message Json was null. Not deserializing message.");
                return null;
            } catch (JSONException e) {
                String str = a;
                AppboyLogger.w(str, "Encountered JSONException processing in-app message: " + ed.a(jSONObject), e);
                return null;
            } catch (Exception e2) {
                String str2 = a;
                AppboyLogger.e(str2, "Failed to deserialize the in-app message: " + ed.a(jSONObject), e2);
                return null;
            }
        } else if (a(jSONObject)) {
            AppboyLogger.d(a, "Deserializing control in-app message.");
            return new InAppMessageControl(jSONObject, bqVar);
        } else {
            MessageType messageType = (MessageType) ed.a(jSONObject, "type", MessageType.class, null);
            if (messageType == null) {
                String str3 = a;
                AppboyLogger.i(str3, "In-app message type was null. Not deserializing message: " + ed.a(jSONObject));
                return null;
            }
            int i = AnonymousClass1.a[messageType.ordinal()];
            if (i == 1) {
                return new InAppMessageFull(jSONObject, bqVar);
            }
            if (i == 2) {
                return new InAppMessageModal(jSONObject, bqVar);
            }
            if (i == 3) {
                return new InAppMessageSlideup(jSONObject, bqVar);
            }
            if (i == 4) {
                return new InAppMessageHtmlFull(jSONObject, bqVar);
            }
            String str4 = a;
            AppboyLogger.e(str4, "Unknown in-app message type. Not deserializing message: " + ed.a(jSONObject));
            return null;
        }
    }

    /* renamed from: bo.app.ec$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.appboy.enums.inappmessage.MessageType[] r0 = com.appboy.enums.inappmessage.MessageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.appboy.enums.inappmessage.MessageType r1 = com.appboy.enums.inappmessage.MessageType.FULL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appboy.enums.inappmessage.MessageType r1 = com.appboy.enums.inappmessage.MessageType.MODAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appboy.enums.inappmessage.MessageType r1 = com.appboy.enums.inappmessage.MessageType.SLIDEUP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.appboy.enums.inappmessage.MessageType r1 = com.appboy.enums.inappmessage.MessageType.HTML_FULL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.ec.AnonymousClass1.<clinit>():void");
        }
    }

    static boolean a(JSONObject jSONObject) {
        return jSONObject.optBoolean(InAppMessageBase.IS_CONTROL, false);
    }
}
