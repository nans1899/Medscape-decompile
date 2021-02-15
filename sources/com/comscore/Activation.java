package com.comscore;

import com.comscore.Configuration;
import com.comscore.util.CrossPublisherIdUtil;
import java.util.List;

public class Activation {
    private static final String a = "http://segment-data-us-east.zqtk.net/%s?url=%s&c2=%s&c12=%s&ns_ap_bi=%s&ns_ap_sv=%s&ns_ap_an=%s&ns_ap_ver=%s&ns_ap_pn=android";
    private static final String b = "\\{\\s*\".*\"\\s*:\\s*[^\\}]*\\s*\\}";

    public interface ActivationListener {
        void onReceivedCategories(List<String> list);
    }

    static class a implements CrossPublisherIdUtil.Listener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ ActivationListener e;

        /* renamed from: com.comscore.Activation$a$a  reason: collision with other inner class name */
        class C0001a implements Runnable {
            final /* synthetic */ String a;

            C0001a(String str) {
                this.a = str;
            }

            public void run() {
                a aVar = a.this;
                a.this.e.onReceivedCategories(Activation.b(aVar.a, aVar.b, aVar.c, aVar.d, this.a));
            }
        }

        a(String str, String str2, String str3, String str4, ActivationListener activationListener) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = activationListener;
        }

        public void onCrossPublisherIdRequested(String str, boolean z) {
            new Thread(new C0001a(str)).start();
        }
    }

    static class b implements Configuration.a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ ActivationListener e;

        b(String str, String str2, String str3, String str4, ActivationListener activationListener) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = activationListener;
        }

        public void onConfigurationChanged(int i) {
            if (i == 20307) {
                Activation.a(this.a, this.b, this.c, this.d, this.e);
                Analytics.getConfiguration().b(this);
            }
        }
    }

    private Activation() {
    }

    static void a(String str, String str2, String str3, String str4, ActivationListener activationListener) {
        if (Analytics.getConfiguration().d()) {
            CrossPublisherIdUtil.requestCrossPublisherId(new a(str, str2, str3, str4, activationListener));
        } else {
            Analytics.getConfiguration().a((Configuration.a) new b(str, str2, str3, str4, activationListener));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0122, code lost:
        return new java.util.ArrayList();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x011d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> b(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "UTF-8"
            boolean r1 = com.comscore.util.setup.Setup.isSetUpFinished()
            if (r1 == 0) goto L_0x0135
            if (r10 == 0) goto L_0x0135
            if (r11 != 0) goto L_0x000e
            goto L_0x0135
        L_0x000e:
            com.comscore.util.jni.JniComScoreHelper r1 = com.comscore.util.setup.Setup.getJniComScoreHelper()
            java.lang.String r1 = r1.getApplicationId()
            java.lang.String r2 = com.comscore.Analytics.getVersion()
            com.comscore.util.jni.JniComScoreHelper r3 = com.comscore.util.setup.Setup.getJniComScoreHelper()
            java.lang.String r3 = r3.getApplicationName()
            com.comscore.util.jni.JniComScoreHelper r4 = com.comscore.util.setup.Setup.getJniComScoreHelper()
            java.lang.String r4 = r4.getApplicationVersion()
            if (r1 == 0) goto L_0x012f
            if (r2 == 0) goto L_0x012f
            if (r3 == 0) goto L_0x012f
            if (r4 == 0) goto L_0x012f
            boolean r5 = r10.isEmpty()
            if (r5 != 0) goto L_0x012f
            boolean r5 = r11.isEmpty()
            if (r5 != 0) goto L_0x012f
            boolean r5 = r1.isEmpty()
            if (r5 != 0) goto L_0x012f
            boolean r5 = r2.isEmpty()
            if (r5 != 0) goto L_0x012f
            boolean r5 = r3.isEmpty()
            if (r5 != 0) goto L_0x012f
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x0058
            goto L_0x012f
        L_0x0058:
            java.lang.String r5 = "http://segment-data-us-east.zqtk.net/%s?url=%s&c2=%s&c12=%s&ns_ap_bi=%s&ns_ap_sv=%s&ns_ap_an=%s&ns_ap_ver=%s&ns_ap_pn=android"
            r6 = 8
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            java.lang.String r8 = java.net.URLEncoder.encode(r8, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r7 = 0
            r6[r7] = r8     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            java.lang.String r8 = java.net.URLEncoder.encode(r9, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r9 = 1
            r6[r9] = r8     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r8 = 2
            java.lang.String r9 = java.net.URLEncoder.encode(r10, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r6[r8] = r9     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r8 = 3
            java.lang.String r9 = java.net.URLEncoder.encode(r11, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r6[r8] = r9     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r8 = 4
            java.lang.String r9 = java.net.URLEncoder.encode(r1, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r6[r8] = r9     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r8 = 5
            java.lang.String r9 = java.net.URLEncoder.encode(r2, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r6[r8] = r9     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r8 = 6
            java.lang.String r9 = java.net.URLEncoder.encode(r3, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r6[r8] = r9     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r8 = 7
            java.lang.String r9 = java.net.URLEncoder.encode(r4, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r6[r8] = r9     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            java.lang.String r8 = java.lang.String.format(r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            if (r12 == 0) goto L_0x00ba
            boolean r9 = r12.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            if (r9 != 0) goto L_0x00ba
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r9.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r9.append(r8)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            java.lang.String r8 = "&ns_ak="
            r9.append(r8)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            java.lang.String r8 = java.net.URLEncoder.encode(r12, r0)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            r9.append(r8)     // Catch:{ UnsupportedEncodingException -> 0x0129 }
            java.lang.String r8 = r9.toString()     // Catch:{ UnsupportedEncodingException -> 0x0129 }
        L_0x00ba:
            java.net.URL r9 = new java.net.URL     // Catch:{ IOException -> 0x0123 }
            r9.<init>(r8)     // Catch:{ IOException -> 0x0123 }
            java.io.InputStream r8 = r9.openStream()     // Catch:{ IOException -> 0x0123 }
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0123 }
            r9.<init>()     // Catch:{ IOException -> 0x0123 }
            r10 = 1024(0x400, float:1.435E-42)
            byte[] r10 = new byte[r10]     // Catch:{ IOException -> 0x0123 }
        L_0x00cc:
            int r11 = r8.read(r10)     // Catch:{ IOException -> 0x0123 }
            r12 = -1
            if (r11 == r12) goto L_0x00d7
            r9.write(r10, r7, r11)     // Catch:{ IOException -> 0x0123 }
            goto L_0x00cc
        L_0x00d7:
            java.lang.String r8 = r9.toString(r0)     // Catch:{ IOException -> 0x0123 }
            java.lang.String r9 = "\\{\\s*\".*\"\\s*:\\s*[^\\}]*\\s*\\}"
            java.util.regex.Pattern r9 = java.util.regex.Pattern.compile(r9)     // Catch:{ IOException -> 0x0123 }
            java.util.regex.Matcher r8 = r9.matcher(r8)     // Catch:{ IOException -> 0x0123 }
            boolean r9 = r8.find()     // Catch:{ IOException -> 0x0123 }
            if (r9 != 0) goto L_0x00f1
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ IOException -> 0x0123 }
            r8.<init>()     // Catch:{ IOException -> 0x0123 }
            return r8
        L_0x00f1:
            java.lang.String r8 = r8.group(r7)     // Catch:{ IOException -> 0x0123 }
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ JSONException -> 0x011d }
            r9.<init>(r8)     // Catch:{ JSONException -> 0x011d }
            java.lang.String r8 = "data"
            org.json.JSONArray r8 = r9.getJSONArray(r8)     // Catch:{ JSONException -> 0x0101 }
            goto L_0x0107
        L_0x0101:
            java.lang.String r8 = "errors"
            org.json.JSONArray r8 = r9.getJSONArray(r8)     // Catch:{ JSONException -> 0x011d }
        L_0x0107:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ JSONException -> 0x011d }
            r9.<init>()     // Catch:{ JSONException -> 0x011d }
            int r10 = r8.length()     // Catch:{ JSONException -> 0x011d }
        L_0x0110:
            if (r7 >= r10) goto L_0x011c
            java.lang.String r11 = r8.optString(r7)     // Catch:{ JSONException -> 0x011d }
            r9.add(r11)     // Catch:{ JSONException -> 0x011d }
            int r7 = r7 + 1
            goto L_0x0110
        L_0x011c:
            return r9
        L_0x011d:
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ IOException -> 0x0123 }
            r8.<init>()     // Catch:{ IOException -> 0x0123 }
            return r8
        L_0x0123:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            return r8
        L_0x0129:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            return r8
        L_0x012f:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            return r8
        L_0x0135:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.Activation.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }
}
