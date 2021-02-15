package com.medscape.android.BI;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.CookieSyncManager;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.Util;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIManager {
    public static final String BI_URL = "https://bi.medscape.com/pi/global/medscapemobileapp-1x1.gif?";
    private static final String TAG = "BIManager";
    private static final BIManager singletonBIManager = new BIManager();
    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    private String FullScreenAD_ELPST;
    /* access modifiers changed from: private */
    public String cookieString;
    private int numOfAttempt;
    private String section;
    private String url = "";

    public static BIManager getInstance() {
        return singletonBIManager;
    }

    public boolean startBI(Context context, String str, String str2, String str3, String str4) {
        this.section = str4;
        return startBI(context, str, str2, str3);
    }

    public void setFullScreenAD_ELPST(String str) {
        this.FullScreenAD_ELPST = str;
    }

    public boolean startBI(Context context, String str, String str2, String str3) {
        String buildUrl = buildUrl(getBIObject(context, str, str2, str3, this.section));
        this.url = buildUrl;
        LogUtil.d(TAG, "url = %s", buildUrl);
        if (!Util.isOnline(context)) {
            return false;
        }
        CookieSyncManager.createInstance(context);
        this.cookieString = Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, "");
        AsyncTaskHelper.execute(threadPoolExecutor, new BITask(), this.url);
        this.numOfAttempt++;
        return false;
    }

    /* access modifiers changed from: private */
    public void onCompleteBI(Boolean bool) {
        if (!bool.booleanValue() && this.numOfAttempt < 3) {
            AsyncTaskHelper.execute(threadPoolExecutor, new BITask(), this.url);
            this.numOfAttempt++;
        }
    }

    private BIParameters getBIObject(Context context, String str, String str2, String str3, String str4) {
        try {
            BIParameters bIParameters = new BIParameters();
            bIParameters.setAct(str);
            bIParameters.setDts("" + System.currentTimeMillis());
            if (!Util.isTestDriveTimeSet(context) || Util.isTestDriveTimeFinished(context)) {
                bIParameters.setGuid(Settings.singleton(context).getSetting(Constants.REGISTERED_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO));
            } else {
                bIParameters.setGuid("-999");
            }
            bIParameters.setMdl(Util.getPhoneModel());
            bIParameters.setOs(com.wbmd.wbmdcommons.utils.Util.getPhoneOSVersion());
            bIParameters.setPag(str2);
            bIParameters.setVer(Util.getApplicationVersion(context));
            if (str3 != null && !str3.equals("")) {
                bIParameters.setData(str3);
            }
            if (str4 != null && !str4.equals("")) {
                bIParameters.setSection(str4);
            }
            if (this.FullScreenAD_ELPST != null && !this.FullScreenAD_ELPST.equals("")) {
                bIParameters.setElpst(this.FullScreenAD_ELPST);
            }
            return bIParameters;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String buildUrl(BIParameters bIParameters) {
        if (bIParameters == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(BI_URL);
        stringBuffer.append("APPTYPE=android&");
        stringBuffer.append(BIParameters.PAG);
        stringBuffer.append("=");
        stringBuffer.append(bIParameters.getPag());
        stringBuffer.append("&");
        stringBuffer.append(BIParameters.ACT);
        stringBuffer.append("=");
        stringBuffer.append(bIParameters.getAct());
        stringBuffer.append("&");
        stringBuffer.append(BIParameters.DTS);
        stringBuffer.append("=");
        stringBuffer.append(bIParameters.getDts());
        stringBuffer.append("&");
        stringBuffer.append(BIParameters.MDL);
        stringBuffer.append("=");
        stringBuffer.append(Uri.encode(bIParameters.getMdl()));
        stringBuffer.append("&");
        stringBuffer.append(BIParameters.GUID);
        stringBuffer.append("=");
        stringBuffer.append(bIParameters.getGuid());
        stringBuffer.append("&");
        stringBuffer.append(BIParameters.OS);
        stringBuffer.append("=");
        stringBuffer.append(bIParameters.getOs());
        stringBuffer.append("&");
        stringBuffer.append(BIParameters.VER);
        stringBuffer.append("=");
        stringBuffer.append(bIParameters.getVer());
        if (bIParameters.getData() != null && !bIParameters.getData().equals("")) {
            stringBuffer.append("&");
            stringBuffer.append(BIParameters.DATA);
            stringBuffer.append("=");
            stringBuffer.append(URLEncoder.encode(bIParameters.getData()));
        }
        if (bIParameters.getSection() != null && !bIParameters.getSection().equals("")) {
            stringBuffer.append("&");
            stringBuffer.append(BIParameters.SECTION);
            stringBuffer.append("=");
            stringBuffer.append(bIParameters.getSection());
        }
        if (bIParameters.getElpst() != null && !bIParameters.getElpst().equals("")) {
            stringBuffer.append("&");
            stringBuffer.append(BIParameters.ELPST);
            stringBuffer.append("=");
            stringBuffer.append(bIParameters.getElpst());
        }
        return stringBuffer.toString();
    }

    private class BITask extends AsyncTask<String, String, Boolean> {
        private BITask() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(String... strArr) {
            try {
                if (!BIConnection.sendInfo(strArr[0], BIManager.this.cookieString)) {
                    BIConnection.sendInfo(strArr[0], BIManager.this.cookieString);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            super.onCancelled();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            BIManager.this.onCompleteBI(bool);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate(strArr);
        }
    }
}
