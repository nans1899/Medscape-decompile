package io.branch.referral;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchUtil {
    static boolean isCustomDebugEnabled_ = false;

    public static boolean isTestModeEnabled(Context context) {
        boolean parseBoolean;
        if (isCustomDebugEnabled_) {
            return true;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("io.branch.sdk.TestMode")) {
                Resources resources = context.getResources();
                parseBoolean = Boolean.parseBoolean(resources.getString(resources.getIdentifier("io.branch.sdk.TestMode", "string", context.getPackageName())));
            } else {
                parseBoolean = applicationInfo.metaData.getBoolean("io.branch.sdk.TestMode", false);
            }
            return parseBoolean;
        } catch (Exception unused) {
            return false;
        }
    }

    static JSONObject formatLinkParam(JSONObject jSONObject) {
        return addSource(jSONObject);
    }

    static JSONObject addSource(JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("source", "android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static class JsonReader {
        private final JSONObject jsonObject;

        public JsonReader(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2 = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            this.jsonObject = jSONObject2;
        }

        public JSONObject getJsonObject() {
            return this.jsonObject;
        }

        public int readOutInt(String str) {
            int optInt = this.jsonObject.optInt(str);
            this.jsonObject.remove(str);
            return optInt;
        }

        public Integer readOutInt(String str, Integer num) {
            if (!this.jsonObject.has(str)) {
                return num;
            }
            Integer valueOf = Integer.valueOf(this.jsonObject.optInt(str));
            this.jsonObject.remove(str);
            return valueOf;
        }

        public String readOutString(String str) {
            String optString = this.jsonObject.optString(str);
            this.jsonObject.remove(str);
            return optString;
        }

        public String readOutString(String str, String str2) {
            String optString = this.jsonObject.optString(str, str2);
            this.jsonObject.remove(str);
            return optString;
        }

        public long readOutLong(String str) {
            long optLong = this.jsonObject.optLong(str);
            this.jsonObject.remove(str);
            return optLong;
        }

        public double readOutDouble(String str) {
            double optDouble = this.jsonObject.optDouble(str);
            this.jsonObject.remove(str);
            return optDouble;
        }

        public Double readOutDouble(String str, Double d) {
            if (!this.jsonObject.has(str)) {
                return d;
            }
            Double valueOf = Double.valueOf(this.jsonObject.optDouble(str));
            this.jsonObject.remove(str);
            return valueOf;
        }

        public boolean readOutBoolean(String str) {
            boolean optBoolean = this.jsonObject.optBoolean(str);
            this.jsonObject.remove(str);
            return optBoolean;
        }

        public JSONArray readOutJsonArray(String str) {
            JSONArray optJSONArray = this.jsonObject.optJSONArray(str);
            this.jsonObject.remove(str);
            return optJSONArray;
        }

        public Object readOut(String str) {
            Object opt = this.jsonObject.opt(str);
            this.jsonObject.remove(str);
            return opt;
        }

        public boolean has(String str) {
            return this.jsonObject.has(str);
        }

        public Iterator<String> keys() {
            return this.jsonObject.keys();
        }
    }

    public static Drawable getDrawable(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(i, context.getTheme());
        }
        return context.getResources().getDrawable(i);
    }

    public static int dpToPx(Context context, int i) {
        return Math.round(((float) i) * (context.getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    public static boolean isLowOnMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005f, code lost:
        if (r0 != null) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004f A[SYNTHETIC, Splitter:B:21:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054 A[Catch:{ IOException -> 0x0057 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005c A[SYNTHETIC, Splitter:B:31:0x005c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject getDeepLinkSchemes(android.content.Context r5) {
        /*
            boolean r0 = isLowOnMemory(r5)
            r1 = 0
            if (r0 != 0) goto L_0x0062
            java.util.jar.JarFile r0 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x0058, all -> 0x0049 }
            android.content.pm.PackageManager r2 = r5.getPackageManager()     // Catch:{ Exception -> 0x0058, all -> 0x0049 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ Exception -> 0x0058, all -> 0x0049 }
            r3 = 0
            android.content.pm.ApplicationInfo r5 = r2.getApplicationInfo(r5, r3)     // Catch:{ Exception -> 0x0058, all -> 0x0049 }
            java.lang.String r5 = r5.publicSourceDir     // Catch:{ Exception -> 0x0058, all -> 0x0049 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0058, all -> 0x0049 }
            java.lang.String r5 = "AndroidManifest.xml"
            java.util.zip.ZipEntry r5 = r0.getEntry(r5)     // Catch:{ Exception -> 0x0047, all -> 0x0042 }
            java.io.InputStream r5 = r0.getInputStream(r5)     // Catch:{ Exception -> 0x0047, all -> 0x0042 }
            int r2 = r5.available()     // Catch:{ Exception -> 0x005a, all -> 0x0040 }
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x005a, all -> 0x0040 }
            r5.read(r2)     // Catch:{ Exception -> 0x005a, all -> 0x0040 }
            io.branch.referral.ApkParser r3 = new io.branch.referral.ApkParser     // Catch:{ Exception -> 0x005a, all -> 0x0040 }
            r3.<init>()     // Catch:{ Exception -> 0x005a, all -> 0x0040 }
            org.json.JSONObject r1 = r3.decompressXMLForValidator(r2)     // Catch:{ Exception -> 0x005a, all -> 0x0040 }
            if (r5 == 0) goto L_0x003c
            r5.close()     // Catch:{ IOException -> 0x0062 }
        L_0x003c:
            r0.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0062
        L_0x0040:
            r1 = move-exception
            goto L_0x004d
        L_0x0042:
            r5 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
            goto L_0x004d
        L_0x0047:
            r5 = r1
            goto L_0x005a
        L_0x0049:
            r5 = move-exception
            r0 = r1
            r1 = r5
            r5 = r0
        L_0x004d:
            if (r5 == 0) goto L_0x0052
            r5.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0052:
            if (r0 == 0) goto L_0x0057
            r0.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            throw r1
        L_0x0058:
            r5 = r1
            r0 = r5
        L_0x005a:
            if (r5 == 0) goto L_0x005f
            r5.close()     // Catch:{ IOException -> 0x0062 }
        L_0x005f:
            if (r0 == 0) goto L_0x0062
            goto L_0x003c
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.BranchUtil.getDeepLinkSchemes(android.content.Context):org.json.JSONObject");
    }
}
