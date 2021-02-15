package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UniversalResourceAnalyser {
    private static final String SKIP_LIST_KEY = "uri_skip_list";
    private static final String SKIP_URL_FORMATS_KEY = "skip_url_format_key";
    private static final String UPDATE_URL_PATH = "https://cdn.branch.io/sdk/uriskiplist_v#.json";
    private static final String VERSION_KEY = "version";
    private static UniversalResourceAnalyser instance;
    /* access modifiers changed from: private */
    public static JSONObject skipURLFormats;
    private final JSONObject DEFAULT_SKIP_URL_LIST;
    private final ArrayList<String> acceptURLFormats;

    public static UniversalResourceAnalyser getInstance(Context context) {
        if (instance == null) {
            instance = new UniversalResourceAnalyser(context);
        }
        return instance;
    }

    private UniversalResourceAnalyser(Context context) {
        JSONObject jSONObject = new JSONObject();
        this.DEFAULT_SKIP_URL_LIST = jSONObject;
        try {
            jSONObject.putOpt("version", 0);
            JSONArray jSONArray = new JSONArray();
            this.DEFAULT_SKIP_URL_LIST.putOpt(SKIP_LIST_KEY, jSONArray);
            jSONArray.put("^fb\\d+:");
            jSONArray.put("^li\\d+:");
            jSONArray.put("^pdk\\d+:");
            jSONArray.put("^twitterkit-.*:");
            jSONArray.put("^com\\.googleusercontent\\.apps\\.\\d+-.*:\\/oauth");
            jSONArray.put("^(?i)(?!(http|https):).*(:|:.*\\b)(password|o?auth|o?auth.?token|access|access.?token)\\b");
            jSONArray.put("^(?i)((http|https):\\/\\/).*[\\/|?|#].*\\b(password|o?auth|o?auth.?token|access|access.?token)\\b");
        } catch (JSONException unused) {
        }
        skipURLFormats = retrieveSkipURLFormats(context);
        this.acceptURLFormats = new ArrayList<>();
    }

    private JSONObject retrieveSkipURLFormats(Context context) {
        PrefHelper instance2 = PrefHelper.getInstance(context);
        JSONObject jSONObject = new JSONObject();
        String string = instance2.getString(SKIP_URL_FORMATS_KEY);
        if (TextUtils.isEmpty(string) || "bnc_no_value".equals(string)) {
            return this.DEFAULT_SKIP_URL_LIST;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            return jSONObject;
        }
    }

    /* access modifiers changed from: package-private */
    public void addToSkipURLFormats(String str) {
        JSONArray optJSONArray = skipURLFormats.optJSONArray(SKIP_LIST_KEY);
        if (optJSONArray == null) {
            try {
                optJSONArray = new JSONArray();
                skipURLFormats.put(SKIP_LIST_KEY, optJSONArray);
            } catch (Exception unused) {
                return;
            }
        }
        optJSONArray.put(str);
    }

    /* access modifiers changed from: package-private */
    public void addToAcceptURLFormats(String str) {
        this.acceptURLFormats.add(str);
    }

    /* access modifiers changed from: package-private */
    public void addToAcceptURLFormats(List<String> list) {
        this.acceptURLFormats.addAll(list);
    }

    /* access modifiers changed from: package-private */
    public void checkAndUpdateSkipURLFormats(Context context) {
        try {
            new UrlSkipListUpdateTask(context).executeTask(new Void[0]);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public String getStrippedURL(String str) {
        String str2;
        try {
            JSONArray optJSONArray = skipURLFormats.optJSONArray(SKIP_LIST_KEY);
            if (optJSONArray != null) {
                int i = 0;
                while (true) {
                    if (i >= optJSONArray.length()) {
                        break;
                    }
                    try {
                        str2 = optJSONArray.getString(i);
                        if (Pattern.compile(str2).matcher(str).find()) {
                            break;
                        }
                        i++;
                    } catch (JSONException unused) {
                    }
                }
            }
            str2 = null;
            if (str2 == null) {
                if (this.acceptURLFormats.size() <= 0) {
                    return str;
                }
                Iterator<String> it = this.acceptURLFormats.iterator();
                while (it.hasNext()) {
                    if (str.matches(it.next())) {
                        return str;
                    }
                }
            }
            return str2;
        } catch (Exception unused2) {
            return str;
        }
    }

    private static class UrlSkipListUpdateTask extends BranchAsyncTask<Void, Void, JSONObject> {
        private final int TIME_OUT;
        private final PrefHelper prefHelper;

        private UrlSkipListUpdateTask(Context context) {
            this.TIME_OUT = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.prefHelper = PrefHelper.getInstance(context);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0062  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.json.JSONObject doInBackground(java.lang.Void... r7) {
            /*
                r6 = this;
                org.json.JSONObject r7 = new org.json.JSONObject
                r7.<init>()
                r0 = 0
                java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x005f }
                java.lang.String r2 = "https://cdn.branch.io/sdk/uriskiplist_v#.json"
                java.lang.String r3 = "#"
                org.json.JSONObject r4 = io.branch.referral.UniversalResourceAnalyser.skipURLFormats     // Catch:{ all -> 0x005f }
                java.lang.String r5 = "version"
                int r4 = r4.optInt(r5)     // Catch:{ all -> 0x005f }
                int r4 = r4 + 1
                java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x005f }
                java.lang.String r2 = r2.replace(r3, r4)     // Catch:{ all -> 0x005f }
                r1.<init>(r2)     // Catch:{ all -> 0x005f }
                java.net.URLConnection r1 = r1.openConnection()     // Catch:{ all -> 0x005f }
                javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ all -> 0x005f }
                r0 = 1500(0x5dc, float:2.102E-42)
                r1.setConnectTimeout(r0)     // Catch:{ all -> 0x005d }
                r1.setReadTimeout(r0)     // Catch:{ all -> 0x005d }
                int r0 = r1.getResponseCode()     // Catch:{ all -> 0x005d }
                r2 = 200(0xc8, float:2.8E-43)
                if (r0 != r2) goto L_0x0057
                java.io.InputStream r0 = r1.getInputStream()     // Catch:{ all -> 0x005d }
                if (r0 == 0) goto L_0x0057
                java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x005d }
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x005d }
                java.io.InputStream r3 = r1.getInputStream()     // Catch:{ all -> 0x005d }
                r2.<init>(r3)     // Catch:{ all -> 0x005d }
                r0.<init>(r2)     // Catch:{ all -> 0x005d }
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ all -> 0x005d }
                java.lang.String r0 = r0.readLine()     // Catch:{ all -> 0x005d }
                r2.<init>(r0)     // Catch:{ all -> 0x005d }
                r7 = r2
            L_0x0057:
                if (r1 == 0) goto L_0x0065
                r1.disconnect()
                goto L_0x0065
            L_0x005d:
                r0 = r1
                goto L_0x0060
            L_0x005f:
            L_0x0060:
                if (r0 == 0) goto L_0x0065
                r0.disconnect()
            L_0x0065:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.UniversalResourceAnalyser.UrlSkipListUpdateTask.doInBackground(java.lang.Void[]):org.json.JSONObject");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject.optInt("version") > UniversalResourceAnalyser.skipURLFormats.optInt("version")) {
                JSONObject unused = UniversalResourceAnalyser.skipURLFormats = jSONObject;
                this.prefHelper.setString(UniversalResourceAnalyser.SKIP_URL_FORMATS_KEY, UniversalResourceAnalyser.skipURLFormats.toString());
            }
        }
    }
}
