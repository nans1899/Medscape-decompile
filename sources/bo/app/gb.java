package bo.app;

import com.appboy.models.IInAppMessage;
import com.appboy.support.AppboyLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.medscape.android.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class gb {
    private static final String a = AppboyLogger.getAppboyLogTag(gb.class);

    public static IInAppMessage a(JSONObject jSONObject, bq bqVar) {
        if (jSONObject == null) {
            try {
                AppboyLogger.d(a, "Templated message Json was null. Not de-serializing templated message.");
                return null;
            } catch (JSONException e) {
                String str = a;
                AppboyLogger.w(str, "Encountered JSONException processing templated message: " + jSONObject, e);
                return null;
            } catch (Exception e2) {
                String str2 = a;
                AppboyLogger.w(str2, "Encountered general exception processing templated message: " + jSONObject, e2);
                return null;
            }
        } else {
            String string = jSONObject.getString("type");
            if (string.equals("inapp")) {
                return ec.a(jSONObject.getJSONObject("data"), bqVar);
            }
            String str3 = a;
            AppboyLogger.w(str3, "Received templated message Json with unknown type: " + string + ". Not parsing.");
            return null;
        }
    }

    public static List<ek> a(JSONArray jSONArray, bq bqVar) {
        if (jSONArray == null) {
            try {
                AppboyLogger.d(a, "Triggered actions Json array was null. Not de-serializing triggered actions.");
                return null;
            } catch (JSONException e) {
                String str = a;
                AppboyLogger.w(str, "Encountered JSONException processing triggered actions Json array: " + jSONArray, e);
                return null;
            } catch (Exception e2) {
                String str2 = a;
                AppboyLogger.w(str2, "Failed to deserialize triggered actions Json array: " + jSONArray, e2);
                return null;
            }
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                ek b = b(jSONArray.getJSONObject(i), bqVar);
                if (b != null) {
                    arrayList.add(b);
                }
            }
            return arrayList;
        }
    }

    public static ek b(JSONObject jSONObject, bq bqVar) {
        ek emVar;
        try {
            String string = jSONObject.getString("type");
            if (string.equals("inapp")) {
                emVar = new el(jSONObject, bqVar);
            } else if (string.equals("templated_iam")) {
                emVar = new em(jSONObject, bqVar);
            } else {
                String str = a;
                AppboyLogger.i(str, "Received unknown trigger type: " + string);
                return null;
            }
            return emVar;
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Encountered JSONException processing triggered action Json: " + jSONObject, e);
            return null;
        } catch (Exception e2) {
            String str3 = a;
            AppboyLogger.w(str3, "Failed to deserialize triggered action Json: " + jSONObject, e2);
            return null;
        }
    }

    public static List<er> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                AppboyLogger.w(a, "Received null or blank trigger condition Json. Not parsing.");
            } else {
                String string = optJSONObject.getString("type");
                if (string.equals(FirebaseAnalytics.Event.PURCHASE)) {
                    arrayList.add(new ev(optJSONObject));
                } else if (string.equals("custom_event")) {
                    arrayList.add(new eo(optJSONObject));
                } else if (string.equals("push_click")) {
                    arrayList.add(new ex(optJSONObject));
                } else if (string.equals(Constants.OMNITURE_MLINK_OPEN)) {
                    arrayList.add(new et());
                } else if (string.equals("iam_click")) {
                    arrayList.add(new es(optJSONObject));
                } else if (string.equals("test")) {
                    arrayList.add(new ey());
                } else if (string.equals("custom_event_property")) {
                    arrayList.add(new ep(optJSONObject));
                } else if (string.equals("purchase_property")) {
                    arrayList.add(new ew(optJSONObject));
                } else {
                    String str = a;
                    AppboyLogger.w(str, "Received triggered condition Json with unknown type: " + string + ". Not parsing.");
                }
            }
        }
        return arrayList;
    }
}
