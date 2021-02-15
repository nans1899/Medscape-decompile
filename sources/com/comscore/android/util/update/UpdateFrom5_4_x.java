package com.comscore.android.util.update;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateFrom5_4_x {
    public static final String OLD_DEFAULT_PREFS_NAME = "cSPrefs";
    private SharedPreferences a;
    private HashMap<String, String> b;
    /* access modifiers changed from: private */
    public File c;
    /* access modifiers changed from: private */
    public File d;
    private Context e;

    class a implements Runnable {
        a() {
        }

        public void run() {
            try {
                Thread.sleep(400);
            } catch (InterruptedException unused) {
            }
            if (UpdateFrom5_4_x.this.c.exists()) {
                UpdateFrom5_4_x.this.c.delete();
            }
            if (UpdateFrom5_4_x.this.d.exists()) {
                UpdateFrom5_4_x.this.d.delete();
            }
        }
    }

    class b implements FilenameFilter {
        b() {
        }

        public boolean accept(File file, String str) {
            return str.startsWith("cs_cache_");
        }
    }

    public UpdateFrom5_4_x(Context context) {
        this.e = context;
        File file = new File(this.e.getFilesDir().getParent() + "/shared_prefs/" + OLD_DEFAULT_PREFS_NAME + ".xml");
        this.c = file;
        if (file.exists()) {
            this.d = new File(this.e.getFilesDir().getParent() + "/shared_prefs/" + OLD_DEFAULT_PREFS_NAME + ".back");
            this.b = new HashMap<>();
            SharedPreferences sharedPreferences = this.e.getSharedPreferences(OLD_DEFAULT_PREFS_NAME, 0);
            this.a = sharedPreferences;
            for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                if (next.getValue() instanceof String) {
                    this.b.put(next.getKey(), (String) next.getValue());
                }
            }
        }
    }

    private ArrayList<String> a(Context context) {
        String[] strArr;
        File filesDir = context.getFilesDir();
        if (filesDir == null || !filesDir.isDirectory()) {
            strArr = null;
        } else {
            strArr = filesDir.list(new b());
            if (strArr != null) {
                Arrays.sort(strArr);
            } else {
                strArr = new String[0];
            }
        }
        return new ArrayList<>(Arrays.asList(strArr));
    }

    private void a() {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().commit();
            new Thread(new a()).start();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027 A[SYNTHETIC, Splitter:B:15:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r4, java.lang.String r5, java.util.ArrayList<java.lang.String> r6) {
        /*
            r3 = this;
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            java.io.FileInputStream r4 = r4.openFileInput(r5)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
        L_0x000f:
            java.lang.String r4 = r1.readLine()     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            if (r4 == 0) goto L_0x002f
            int r5 = r4.length()     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            if (r5 == 0) goto L_0x000f
            r6.add(r4)     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            goto L_0x000f
        L_0x001f:
            r4 = move-exception
            r0 = r1
            goto L_0x0025
        L_0x0022:
            r0 = r1
            goto L_0x002c
        L_0x0024:
            r4 = move-exception
        L_0x0025:
            if (r0 == 0) goto L_0x002a
            r0.close()     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            throw r4
        L_0x002b:
        L_0x002c:
            if (r0 == 0) goto L_0x0032
            r1 = r0
        L_0x002f:
            r1.close()     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.android.util.update.UpdateFrom5_4_x.a(android.content.Context, java.lang.String, java.util.ArrayList):void");
    }

    public void libraryPostUpdate(Map<String, String> map, ArrayList<String> arrayList) {
        if (this.a != null) {
            a();
            Iterator<String> it = a(this.e).iterator();
            while (it.hasNext()) {
                String next = it.next();
                a(this.e, next, arrayList);
                this.e.deleteFile(next);
            }
        }
    }

    public void libraryUpdate(Map<String, String> map) {
        String str;
        if (this.a != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("ns_ap_fg", "foreground_transitions_count");
            hashMap.put("installTime", "current_install_timestamp");
            String str2 = this.b.get("lastActivityTime");
            if (!(str2 == null || str2.length() == 0)) {
                map.put("last_application_accumulation_timestamp", str2);
                map.put("last_session_accumulation_timestamp", str2);
            }
            hashMap.put("lastApplicationAccumulationTimestamp", "last_application_accumulation_timestamp");
            hashMap.put("lastSessionAccumulationTimestamp", "last_session_accumulation_timestamp");
            hashMap.put("lastApplicationSessionTimestamp", "last_application_session_timestamp");
            hashMap.put("lastUserSessionTimestamp", "last_user_session_timestamp");
            hashMap.put("lastActiveUserSessionTimestamp", "last_active_user_session_timestamp");
            hashMap.put("foregroundTransitionsCount", "foreground_transitions_count");
            hashMap.put("totalForegroundTime", "total_foreground_time");
            hashMap.put("totalBackgroundTime", "total_background_time");
            hashMap.put("totalInactiveTime", "total_inactive_time");
            hashMap.put("accumulatedForegroundTime", "accumulated_foreground_time");
            hashMap.put("accumulatedBackgroundTime", "accumulated_background_time");
            hashMap.put("accumulatedInactiveTime", "accumulated_inactive_time");
            hashMap.put("accumulatedApplicationSessionTime", "accumulated_application_session_time");
            hashMap.put("accumulatedActiveUserSessionTime", "accumulated_active_user_session_time");
            hashMap.put("accumulatedUserSessionTime", "accumulated_user_session_time");
            hashMap.put("activeUserSessionCount", "active_user_session_count");
            hashMap.put("userInteractionCount", "user_interaction_count");
            hashMap.put("userSessionCount", "user_session_count");
            hashMap.put("applicationSessionCountKey", "application_session_count");
            hashMap.put("genesis", "genesis");
            hashMap.put("previousGenesis", "previous_genesis");
            hashMap.put("installId", "current_install_timestamp");
            hashMap.put("firstInstallId", "first_install_timestamp");
            hashMap.put("currentVersion", "previous_app_version");
            hashMap.put("runs", "runs");
            hashMap.put("coldStartCount", "cold_start_count");
            hashMap.put("lastMeasurementProcessedTimestamp", "last_transmission_time");
            hashMap.put("lastUserInteractionTimestamp", "last_user_interaction_timestamp");
            hashMap.put("crossPublisherId", "crosspublisher_id_RSA");
            hashMap.put("md5RawCrossPublisherId", "crosspublisher_id_MD5");
            for (Map.Entry entry : hashMap.entrySet()) {
                String str3 = (String) entry.getValue();
                String str4 = this.b.get((String) entry.getKey());
                if (!(str4 == null || str4.length() == 0)) {
                    map.put(str3, str4);
                }
            }
            String str5 = map.get("updated_from_versions");
            if (str5 == null || str5.length() == 0) {
                str = "5.4.x";
            } else {
                str = "5.4.x," + str5;
            }
            map.put("updated_from_versions", str);
        }
    }
}
