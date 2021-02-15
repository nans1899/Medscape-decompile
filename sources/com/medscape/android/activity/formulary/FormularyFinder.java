package com.medscape.android.activity.formulary;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FormularyFinder {
    private static final String BRAND_PARAM = "&response=application/json";
    private static String BRAND_URL = "https://api.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?";
    private static final String CONTENT_PARAM = "contentId=";
    private static Callbacks sDummyCallbacks = new Callbacks() {
        public void onFormularyDownloaded(boolean z) {
        }
    };
    public String TAG = "FormularyFinder";
    /* access modifiers changed from: private */
    public boolean hasFormularies = false;
    private List<BrandModel> mBrandModel;
    Callbacks mCallBack = sDummyCallbacks;
    private Context mContext;
    /* access modifiers changed from: private */
    public boolean mIsDownloaded = false;
    private String mJsonString;

    public interface Callbacks {
        void onFormularyDownloaded(boolean z);
    }

    public FormularyFinder(Context context) {
        this.mContext = context;
    }

    public boolean hasFormulary() {
        return this.hasFormularies;
    }

    public void checkForFormularies(int i) {
        setBrandURL();
        if (FormularyDatabaseHelper.getInstance(this.mContext).isValidDatabse(this.mContext)) {
            Context context = this.mContext;
            List<BrandModel> brandListFromContentId = BrandModel.getBrandListFromContentId(context, "" + i);
            this.mBrandModel = brandListFromContentId;
            if (brandListFromContentId.size() > 0) {
                this.hasFormularies = true;
            } else {
                this.hasFormularies = false;
            }
            Callbacks callbacks = this.mCallBack;
            if (callbacks != null) {
                callbacks.onFormularyDownloaded(this.hasFormularies);
            }
            this.mIsDownloaded = true;
        } else if (Util.isOnline(this.mContext)) {
            this.mIsDownloaded = false;
            GetBrandListTask getBrandListTask = new GetBrandListTask();
            getBrandListTask.execute(new String[]{String.format(BRAND_URL + CONTENT_PARAM + "%s" + BRAND_PARAM, new Object[]{Integer.valueOf(i)})});
        } else {
            this.hasFormularies = false;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setBrandURL() {
        /*
            r7 = this;
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            android.content.Context r1 = r7.mContext
            java.lang.String r2 = "module_service"
            java.lang.String r0 = r0.getEnvironmentWithDefault(r1, r2)
            int r1 = r0.hashCode()
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r1) {
                case 206969445: goto L_0x004c;
                case 446124970: goto L_0x0042;
                case 568937877: goto L_0x0038;
                case 568961724: goto L_0x002e;
                case 568961725: goto L_0x0024;
                case 568961726: goto L_0x001a;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x0056
        L_0x001a:
            java.lang.String r1 = "environment_qa02"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0056
            r0 = 4
            goto L_0x0057
        L_0x0024:
            java.lang.String r1 = "environment_qa01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0056
            r0 = 2
            goto L_0x0057
        L_0x002e:
            java.lang.String r1 = "environment_qa00"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0056
            r0 = 1
            goto L_0x0057
        L_0x0038:
            java.lang.String r1 = "environment_perf"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0056
            r0 = 3
            goto L_0x0057
        L_0x0042:
            java.lang.String r1 = "environment_dev01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0056
            r0 = 5
            goto L_0x0057
        L_0x004c:
            java.lang.String r1 = "environment_production"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0056
            r0 = 0
            goto L_0x0057
        L_0x0056:
            r0 = -1
        L_0x0057:
            if (r0 == 0) goto L_0x007d
            if (r0 == r6) goto L_0x0078
            if (r0 == r5) goto L_0x0073
            if (r0 == r4) goto L_0x006e
            if (r0 == r3) goto L_0x0069
            if (r0 == r2) goto L_0x0064
            goto L_0x0081
        L_0x0064:
            java.lang.String r0 = "http://api.dev01.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?"
            BRAND_URL = r0
            goto L_0x0081
        L_0x0069:
            java.lang.String r0 = "http://api.qa02.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?"
            BRAND_URL = r0
            goto L_0x0081
        L_0x006e:
            java.lang.String r0 = "http://api.perf.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?"
            BRAND_URL = r0
            goto L_0x0081
        L_0x0073:
            java.lang.String r0 = "https://api.qa01.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?"
            BRAND_URL = r0
            goto L_0x0081
        L_0x0078:
            java.lang.String r0 = "http://api.qa00.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?"
            BRAND_URL = r0
            goto L_0x0081
        L_0x007d:
            java.lang.String r0 = "https://api.medscape.com/ws/services/formularyService/getInternalDrugsHavingFormularies?"
            BRAND_URL = r0
        L_0x0081:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyFinder.setBrandURL():void");
    }

    public class GetBrandListTask extends AsyncTask<String, Double, String> {
        public GetBrandListTask() {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0085  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.String... r8) {
            /*
                r7 = this;
                java.lang.String r0 = "doInBackground URL =%s"
                r1 = 1
                r2 = 0
                r3 = 0
                com.medscape.android.activity.formulary.FormularyFinder r4 = com.medscape.android.activity.formulary.FormularyFinder.this     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.lang.String r4 = r4.TAG     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r6 = r8[r2]     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r5[r2] = r6     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                com.medscape.android.util.LogUtil.e(r4, r0, r5)     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.net.URL r4 = new java.net.URL     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r8 = r8[r2]     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r4.<init>(r8)     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.net.URLConnection r8 = r4.openConnection()     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                int r4 = com.medscape.android.util.Util.TIMEOUT     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r8.setReadTimeout(r4)     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.io.InputStream r4 = r8.getInputStream()     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                int r8 = r8.getContentLength()     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                com.medscape.android.activity.formulary.FormularyFinder r5 = com.medscape.android.activity.formulary.FormularyFinder.this     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.lang.String r5 = r5.TAG     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r6[r2] = r8     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                com.medscape.android.util.LogUtil.e(r5, r0, r6)     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r8.<init>()     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
            L_0x0040:
                int r0 = r4.read()     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r5 = -1
                if (r0 == r5) goto L_0x004b
                r8.write(r0)     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                goto L_0x0040
            L_0x004b:
                java.lang.String r0 = new java.lang.String     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                byte[] r5 = r8.toByteArray()     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                r0.<init>(r5)     // Catch:{ SocketException -> 0x00aa, SocketTimeoutException -> 0x00a5, UnknownHostException -> 0x00a0, FileNotFoundException -> 0x009b, Exception -> 0x007a }
                com.medscape.android.activity.formulary.FormularyFinder r3 = com.medscape.android.activity.formulary.FormularyFinder.this     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                java.lang.String r3 = r3.TAG     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                java.lang.String r5 = "jsonString jsonString =%s"
                java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                r6[r2] = r0     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                com.medscape.android.util.LogUtil.e(r3, r5, r6)     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                r8.flush()     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                r8.close()     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                r4.close()     // Catch:{ SocketException -> 0x0077, SocketTimeoutException -> 0x0074, UnknownHostException -> 0x0071, FileNotFoundException -> 0x006e, Exception -> 0x006b }
                goto L_0x00af
            L_0x006b:
                r8 = move-exception
                r3 = r0
                goto L_0x007b
            L_0x006e:
                r8 = move-exception
                r3 = r0
                goto L_0x009c
            L_0x0071:
                r8 = move-exception
                r3 = r0
                goto L_0x00a1
            L_0x0074:
                r8 = move-exception
                r3 = r0
                goto L_0x00a6
            L_0x0077:
                r8 = move-exception
                r3 = r0
                goto L_0x00ab
            L_0x007a:
                r8 = move-exception
            L_0x007b:
                java.lang.String r0 = r8.getMessage()
                boolean r0 = com.medscape.android.util.StringUtil.isNotEmpty(r0)
                if (r0 == 0) goto L_0x00ae
                java.lang.Class r0 = r7.getClass()
                java.lang.String r0 = r0.getName()
                java.lang.Object[] r1 = new java.lang.Object[r1]
                java.lang.String r8 = r8.getMessage()
                r1[r2] = r8
                java.lang.String r8 = "message = %s"
                com.medscape.android.util.LogUtil.e(r0, r8, r1)
                goto L_0x00ae
            L_0x009b:
                r8 = move-exception
            L_0x009c:
                r8.printStackTrace()
                goto L_0x00ae
            L_0x00a0:
                r8 = move-exception
            L_0x00a1:
                r8.printStackTrace()
                goto L_0x00ae
            L_0x00a5:
                r8 = move-exception
            L_0x00a6:
                r8.printStackTrace()
                goto L_0x00ae
            L_0x00aa:
                r8 = move-exception
            L_0x00ab:
                r8.printStackTrace()
            L_0x00ae:
                r0 = r3
            L_0x00af:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyFinder.GetBrandListTask.doInBackground(java.lang.String[]):java.lang.String");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            if (StringUtil.isNotEmpty(str)) {
                int indexOf = str.indexOf("[");
                int indexOf2 = str.indexOf("]") + 1;
                if (StringUtil.isSubstringOpSafe(str, indexOf, indexOf2)) {
                    FormularyFinder.this.setmJsonString(str.substring(indexOf, indexOf2).trim());
                    if (!FormularyFinder.this.mIsDownloaded) {
                        FormularyFinder.this.onDownloadComplete();
                    }
                }
            } else {
                boolean unused = FormularyFinder.this.hasFormularies = false;
            }
            boolean unused2 = FormularyFinder.this.mIsDownloaded = true;
        }
    }

    public void setmJsonString(String str) {
        this.mJsonString = str;
    }

    private String getJsonString() {
        return this.mJsonString;
    }

    /* access modifiers changed from: private */
    public void onDownloadComplete() {
        List<BrandModel> brandList = getBrandList(getJsonString());
        this.mBrandModel = brandList;
        if (brandList.size() > 0) {
            this.hasFormularies = true;
        } else {
            this.hasFormularies = false;
        }
        Callbacks callbacks = this.mCallBack;
        if (callbacks != null) {
            callbacks.onFormularyDownloaded(this.hasFormularies);
        }
    }

    public List<BrandModel> getBrandList(String str) {
        ArrayList arrayList = new ArrayList();
        LogUtil.e(this.TAG, "getBrandList() = %s ", str);
        if (str == null) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            LogUtil.e(this.TAG, "Brand size = %s", Integer.valueOf(jSONArray.length()));
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LogUtil.e(this.TAG, "Brand name = %s", jSONObject.getString("drugName"));
                arrayList.add(new BrandModel(jSONObject.getString("drugName"), jSONObject.getString("drugId")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void setCallBack(Callbacks callbacks) {
        this.mCallBack = callbacks;
    }

    public boolean isDownloadComplete() {
        return this.mIsDownloaded;
    }

    public List<BrandModel> getBrandModelList() {
        return this.mBrandModel;
    }
}
