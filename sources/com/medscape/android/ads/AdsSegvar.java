package com.medscape.android.ads;

import android.content.Context;
import android.util.Log;
import com.google.ads.AdRequest;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.activity.rss.NewsManager;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.homescreen.user.UserProfileProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdsSegvar {
    private static String ADSEGVAR_DB_NAME = "AdSegvars.sqlite";
    private static AdsSegvar instance;
    private HashMap<String, String> globalMap = new HashMap<>();
    private HashMap<String, String> pageOverProfileSpecificDataMap = new HashMap<>();
    private HashMap<String, String> profileSpecificDataMap = new HashMap<>();

    protected AdsSegvar() {
    }

    public static AdsSegvar getInstance() {
        if (instance == null) {
            instance = new AdsSegvar();
        }
        return instance;
    }

    public HashMap<String, String> getGlobalMap(Context context) {
        if (this.globalMap.isEmpty()) {
            setGlobalMap(context);
        }
        return this.globalMap;
    }

    public String getAdsegvarDbPath() {
        return FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0073, code lost:
        if (r6 != 2) goto L_0x0079;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setGlobalMap(android.content.Context r6) {
        /*
            r5 = this;
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r5.globalMap
            r0.clear()
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            long r0 = r0.getTime()
            java.lang.Long.toString(r0)
            java.util.Random r0 = new java.util.Random
            r0.<init>()
            r1 = 899999(0xdbb9f, float:1.261167E-39)
            int r0 = r0.nextInt(r1)
            r1 = 100000(0x186a0, float:1.4013E-40)
            int r0 = r0 + r1
            java.lang.Integer.toString(r0)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r5.globalMap
            java.lang.String r1 = "auth"
            java.lang.String r2 = "1"
            r0.put(r1, r2)
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            java.lang.String r1 = "module_ad"
            java.lang.String r6 = r0.getEnvironmentWithDefault(r6, r1)
            int r0 = r6.hashCode()
            r1 = 68759055(0x4192e0f, float:1.8006213E-36)
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x0062
            r1 = 206969445(0xc561a65, float:1.6493903E-31)
            if (r0 == r1) goto L_0x0058
            r1 = 608412604(0x2443a3bc, float:4.2422573E-17)
            if (r0 == r1) goto L_0x004e
            goto L_0x006c
        L_0x004e:
            java.lang.String r0 = "environment_qa"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x006c
            r6 = 1
            goto L_0x006d
        L_0x0058:
            java.lang.String r0 = "environment_production"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x006c
            r6 = 0
            goto L_0x006d
        L_0x0062:
            java.lang.String r0 = "environment_staging"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x006c
            r6 = 2
            goto L_0x006d
        L_0x006c:
            r6 = -1
        L_0x006d:
            java.lang.String r0 = "0"
            if (r6 == 0) goto L_0x0079
            if (r6 == r4) goto L_0x0076
            if (r6 == r3) goto L_0x007a
            goto L_0x0079
        L_0x0076:
            java.lang.String r2 = "2"
            goto L_0x007a
        L_0x0079:
            r2 = r0
        L_0x007a:
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r5.globalMap
            java.lang.String r0 = "env"
            r6.put(r0, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r5.globalMap
            java.lang.String r0 = com.medscape.android.util.OldConstants.MAPP
            java.lang.String r1 = "mapp"
            r6.put(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.AdsSegvar.setGlobalMap(android.content.Context):void");
    }

    public HashMap<String, String> createProfileSpecificDataMap() {
        String profileSegvar;
        this.profileSpecificDataMap.clear();
        if (!(UserProfileProvider.INSTANCE.getUserProfile() == null || (profileSegvar = UserProfileProvider.INSTANCE.getUserProfile().getProfileSegvar()) == null)) {
            String[] split = profileSegvar.split("&");
            for (String str : split) {
                if (str.contains("=")) {
                    String[] split2 = str.split("=");
                    String str2 = "";
                    String str3 = (split2.length <= 0 || split2[0] == null) ? str2 : split2[0];
                    if (split2.length > 1 && split2[1] != null) {
                        str2 = split2[1];
                    }
                    this.profileSpecificDataMap.put(str3, str2);
                }
            }
        }
        return this.profileSpecificDataMap;
    }

    public String[] getScreenSpecificDataFromHtml() {
        if (NewsManager.jsonString != null) {
            String[] strArr = new String[0];
            try {
                JSONObject jSONObject = new JSONObject(NewsManager.jsonString.trim());
                JSONObject jSONObject2 = jSONObject.getJSONObject("pageSegVars");
                JSONObject jSONObject3 = jSONObject.getJSONObject("userSegVars");
                JSONArray jSONArray = jSONObject.getJSONArray("exclusionCategories");
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(makePairs(jSONObject2));
                arrayList.add("excl_cat=" + jSONArray.join(",").replace("\"", ""));
                makePairsByUpdatingExistingProfileSpecificPairs(jSONObject3);
                return (String[]) arrayList.toArray(strArr);
            } catch (Exception unused) {
                Log.e(AdRequest.LOGTAG, "Error parsing JSON segvars");
            }
        }
        return new String[0];
    }

    public String[] getScreenSpecificDataFromJSON(String str) {
        String[] strArr = new String[0];
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str.trim());
                JSONObject jSONObject2 = jSONObject.getJSONObject("pageSegVars");
                JSONObject jSONObject3 = jSONObject.getJSONObject("userSegVars");
                JSONArray jSONArray = jSONObject.getJSONArray("exclusionCategories");
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(makePairs(jSONObject2));
                arrayList.add("excl_cat=" + jSONArray.join(",").replace("\"", ""));
                makePairsByUpdatingExistingProfileSpecificPairs(jSONObject3);
                return (String[]) arrayList.toArray(strArr);
            } catch (Exception unused) {
                Log.e(AdRequest.LOGTAG, "Error parsing JSON segvars");
            }
        }
        return strArr;
    }

    private ArrayList makePairs(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            arrayList.add(next + "=" + jSONObject.getString(next));
        }
        return arrayList;
    }

    private ArrayList makePairsByUpdatingExistingProfileSpecificPairs(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (this.profileSpecificDataMap.containsKey(next)) {
                arrayList.add(next + "=" + jSONObject.getString(next));
                this.pageOverProfileSpecificDataMap.put(next, jSONObject.getString(next));
            }
        }
        return arrayList;
    }

    public HashMap<String, String> getProileSpecificDataMap() {
        return this.profileSpecificDataMap;
    }

    public HashMap<String, String> generateGetProfileSpecificDataMap() {
        if (this.profileSpecificDataMap.isEmpty()) {
            createProfileSpecificDataMap();
        }
        return this.profileSpecificDataMap;
    }

    public HashMap<String, String> getPageOverProileSpecificDataMap() {
        return this.pageOverProfileSpecificDataMap;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008c, code lost:
        if (r2 == null) goto L_0x0091;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, java.lang.String> queryDatabase(android.content.Context r6, int r7, int r8) {
        /*
            r5 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            r2.<init>()     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            java.lang.String r3 = r5.getAdsegvarDbPath()     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            r2.append(r3)     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            java.lang.String r3 = ADSEGVAR_DB_NAME     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            r2.append(r3)     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            boolean r2 = com.medscape.android.db.DatabaseHelper.checkDataBase(r2)     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            if (r2 == 0) goto L_0x0029
            com.medscape.android.db.DatabaseHelper r2 = new com.medscape.android.db.DatabaseHelper     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            java.lang.String r3 = ADSEGVAR_DB_NAME     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            r2.<init>(r6, r3)     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            goto L_0x002e
        L_0x0029:
            com.medscape.android.db.DatabaseHelper r2 = new com.medscape.android.db.DatabaseHelper     // Catch:{ Exception -> 0x0082, all -> 0x007f }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0082, all -> 0x007f }
        L_0x002e:
            android.database.sqlite.SQLiteDatabase r6 = r2.getDatabase()     // Catch:{ Exception -> 0x007d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007d }
            r3.<init>()     // Catch:{ Exception -> 0x007d }
            java.lang.String r4 = "SELECT TagValue,TagType FROM tblDFPSegvars WHERE AssetID = "
            r3.append(r4)     // Catch:{ Exception -> 0x007d }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x007d }
            r3.append(r7)     // Catch:{ Exception -> 0x007d }
            java.lang.String r7 = " AND AssetType = "
            r3.append(r7)     // Catch:{ Exception -> 0x007d }
            r3.append(r8)     // Catch:{ Exception -> 0x007d }
            java.lang.String r7 = r3.toString()     // Catch:{ Exception -> 0x007d }
            android.database.Cursor r1 = r6.rawQuery(r7, r1)     // Catch:{ Exception -> 0x007d }
            r1.moveToFirst()     // Catch:{ Exception -> 0x007d }
        L_0x0056:
            boolean r6 = r1.isAfterLast()     // Catch:{ Exception -> 0x007d }
            if (r6 != 0) goto L_0x0077
            java.lang.String r6 = "TagType"
            int r6 = r1.getColumnIndex(r6)     // Catch:{ Exception -> 0x007d }
            java.lang.String r6 = r1.getString(r6)     // Catch:{ Exception -> 0x007d }
            java.lang.String r7 = "TagValue"
            int r7 = r1.getColumnIndex(r7)     // Catch:{ Exception -> 0x007d }
            java.lang.String r7 = r1.getString(r7)     // Catch:{ Exception -> 0x007d }
            r0.put(r6, r7)     // Catch:{ Exception -> 0x007d }
            r1.moveToNext()     // Catch:{ Exception -> 0x007d }
            goto L_0x0056
        L_0x0077:
            if (r1 == 0) goto L_0x008e
            r1.close()
            goto L_0x008e
        L_0x007d:
            r6 = move-exception
            goto L_0x0084
        L_0x007f:
            r6 = move-exception
            r2 = r1
            goto L_0x0093
        L_0x0082:
            r6 = move-exception
            r2 = r1
        L_0x0084:
            r6.printStackTrace()     // Catch:{ all -> 0x0092 }
            if (r1 == 0) goto L_0x008c
            r1.close()
        L_0x008c:
            if (r2 == 0) goto L_0x0091
        L_0x008e:
            r2.close()
        L_0x0091:
            return r0
        L_0x0092:
            r6 = move-exception
        L_0x0093:
            if (r1 == 0) goto L_0x0098
            r1.close()
        L_0x0098:
            if (r2 == 0) goto L_0x009d
            r2.close()
        L_0x009d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.AdsSegvar.queryDatabase(android.content.Context, int, int):java.util.HashMap");
    }
}
