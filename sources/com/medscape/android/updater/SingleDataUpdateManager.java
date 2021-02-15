package com.medscape.android.updater;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.helper.ZipUtils;
import com.medscape.android.homescreen.interfaces.IUpdateDownloadListener;
import com.medscape.android.updater.model.Update;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SingleDataUpdateManager {
    private static final String ADSEGVARS = "adsegvars";
    private static final String CALC = "calc";
    private static final String DRUG = "drug";
    private static final String REF = "ref";
    private static final String TAG = "SingleDataUpdateManager";
    private IUpdateDownloadListener downloadListener;
    private Context mContext;
    /* access modifiers changed from: private */
    public List<Update> updateList;

    public SingleDataUpdateManager(Context context, IUpdateDownloadListener iUpdateDownloadListener) {
        this.mContext = context;
        this.downloadListener = iUpdateDownloadListener;
    }

    public boolean checkForSingleUpdate(int i) {
        this.updateList = new ArrayList();
        float f = this.mContext.getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", 0.0f);
        float parseFloat = Float.parseFloat(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_DEPENDENT_VERISON_DRUG, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        if (parseFloat > Float.parseFloat(Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_DATA_DEPENDENT_CLIENT_VERISON_DRUG, AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_SINGLE_DRUG_UPDATE_CLIENT_VERISON, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (parseFloat >= f) {
            Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_DRUG, AppEventsConstants.EVENT_PARAM_VALUE_NO).toString();
            Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_DRUG_UPDATE_CLIENT_VERISON, AppEventsConstants.EVENT_PARAM_VALUE_NO).toString();
            if (Float.parseFloat(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_DRUG, AppEventsConstants.EVENT_PARAM_VALUE_NO)) > Float.parseFloat(Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_DRUG_UPDATE_CLIENT_VERISON, "-1"))) {
                Update update = new Update();
                update.setUrl(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_URL_DRUG, ""));
                update.setType(DRUG);
                update.setPasswordProtected(true);
                this.updateList.add(update);
            }
        }
        if (Float.parseFloat(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_DEPENDENT_VERSION_AD_SEGVARS, AppEventsConstants.EVENT_PARAM_VALUE_NO).toString()) > Float.parseFloat(Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_AD_SEGVARS_UPDATE_CLIENT_VERSION, "-1").toString())) {
            Update update2 = new Update();
            update2.setUrl(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_URL_AD_SEGVARS, ""));
            update2.setType(ADSEGVARS);
            this.updateList.add(update2);
        }
        if (Float.parseFloat(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_DEPENDENT_VERISON_CALC, AppEventsConstants.EVENT_PARAM_VALUE_NO)) >= f) {
            Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_CALC, AppEventsConstants.EVENT_PARAM_VALUE_NO).toString();
            Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_CALC_UPDATE_CLIENT_VERISON, "-1").toString();
            if (Float.parseFloat(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_CALC, AppEventsConstants.EVENT_PARAM_VALUE_NO)) > Float.parseFloat(Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_CALC_UPDATE_CLIENT_VERISON, "-1"))) {
                Update update3 = new Update();
                update3.setUrl(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_URL_CALC, ""));
                update3.setType("calc");
                update3.setPasswordProtected(true);
                this.updateList.add(update3);
            }
        }
        if (Float.parseFloat(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_DEPENDENT_VERISON_REF, AppEventsConstants.EVENT_PARAM_VALUE_NO)) >= Float.parseFloat(Settings.singleton(this.mContext).getSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_REF, AppEventsConstants.EVENT_PARAM_VALUE_NO).toString();
            Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_REF_UPDATE_CLIENT_VERISON, "-1").toString();
            if (Float.parseFloat(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_REF, AppEventsConstants.EVENT_PARAM_VALUE_NO)) > Float.parseFloat(Settings.singleton(this.mContext).getSetting(Constants.PREF_SINGLE_REF_UPDATE_CLIENT_VERISON, "-1"))) {
                Update update4 = new Update();
                update4.setUrl(Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_URL_REF, ""));
                update4.setType(REF);
                update4.setPasswordProtected(true);
                this.updateList.add(update4);
            }
        }
        List<Update> list = this.updateList;
        if (list == null || list.size() <= 0 || !Util.isOnline(this.mContext)) {
            return false;
        }
        return performSingleUpdate(this.updateList.get(0)).booleanValue();
    }

    public void onUpdateNotAvailable() {
        Settings.singleton(this.mContext).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
    }

    public Boolean performSingleUpdate(Update update) {
        if (update == null || update.getUrl() == null || update.getUrl().equals("")) {
            return false;
        }
        new DownloadUpdateTask().execute(new Update[]{update});
        return true;
    }

    public class DownloadUpdateTask extends AsyncTask<Update, Integer, Update> {
        int contentLength = 0;
        int error = -1;
        int failureCount = 0;
        private String type;
        Update update;

        public DownloadUpdateTask() {
        }

        /* access modifiers changed from: protected */
        public Update doInBackground(Update... updateArr) {
            Update update2 = updateArr[0];
            this.update = update2;
            this.failureCount = 0;
            this.type = update2.getType();
            String trim = updateArr[0].getUrl().trim();
            LogUtil.e(SingleDataUpdateManager.TAG, "doInBackground() url= %s", trim);
            String substring = trim.substring(trim.lastIndexOf("/"));
            Update connect = connect(this.update, substring);
            while (connect == null && this.failureCount < 1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connect = connect(this.update, substring);
            }
            return connect;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00d0, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x00d1, code lost:
            r11.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00d5, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d6, code lost:
            r11.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00da, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00db, code lost:
            r11.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00df, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00e0, code lost:
            r11.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e3, code lost:
            return null;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x00d5 A[ExcHandler: UnknownHostException (r11v3 'e' java.net.UnknownHostException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x00da A[ExcHandler: SocketTimeoutException (r11v2 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00df A[ExcHandler: SocketException (r11v1 'e' java.net.SocketException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.medscape.android.updater.model.Update connect(com.medscape.android.updater.model.Update r11, java.lang.String r12) {
            /*
                r10 = this;
                r0 = 0
                int r1 = r10.failureCount     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r2 = 1
                int r1 = r1 + r2
                r10.failureCount = r1     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.net.URL r1 = new java.net.URL     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r3 = r11.getUrl()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r1.<init>(r3)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.net.URLConnection r1 = r1.openConnection()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                int r3 = com.medscape.android.util.Util.TIMEOUT     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r1.setReadTimeout(r3)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.io.InputStream r3 = r1.getInputStream()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                int r4 = r1.getContentLength()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r10.contentLength = r4     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                int r4 = r1.getResponseCode()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r5 = 200(0xc8, float:2.8E-43)
                r6 = 0
                if (r4 != r5) goto L_0x00ac
                java.io.File r1 = new java.io.File     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r2.<init>()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                com.medscape.android.MedscapeApplication r4 = com.medscape.android.MedscapeApplication.get()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r4 = com.medscape.android.helper.FileHelper.getDataDirectory(r4)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r2.append(r4)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r4 = "/Medscape/"
                r2.append(r4)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r2 = r2.toString()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r1.<init>(r2)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                boolean r2 = r1.canWrite()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                if (r2 != 0) goto L_0x0055
                r1.mkdirs()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
            L_0x0055:
                java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r4 = 4096(0x1000, float:5.74E-42)
                r2.<init>(r3, r4)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.io.File r5 = new java.io.File     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r7.<init>()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r1 = r1.getPath()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r7.append(r1)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r1 = "/"
                r7.append(r1)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r7.append(r12)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r1 = r7.toString()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r5.<init>(r1)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                boolean r1 = r5.exists()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                if (r1 != 0) goto L_0x0082
                r5.createNewFile()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
            L_0x0082:
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r1.<init>(r5)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                byte[] r7 = new byte[r4]     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r8 = 0
            L_0x008a:
                r9 = -1
                if (r8 == r9) goto L_0x0095
                r1.write(r7, r6, r8)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                int r8 = r2.read(r7, r6, r4)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                goto L_0x008a
            L_0x0095:
                r1.close()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                boolean r1 = r11.isPasswordProtected()     // Catch:{ Exception -> 0x00a3, SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5 }
                r10.saveToFile(r12, r1)     // Catch:{ Exception -> 0x00a3, SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5 }
                r5.delete()     // Catch:{ Exception -> 0x00a3, SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5 }
                goto L_0x00cc
            L_0x00a3:
                r11 = move-exception
                r11.printStackTrace()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r11 = 8
                r10.error = r11     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                return r0
            L_0x00ac:
                java.lang.String r12 = "SingleDataUpdateManager"
                java.lang.String r4 = "FAILED network() result code = %s"
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r5.<init>()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                int r1 = r1.getResponseCode()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r5.append(r1)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r1 = ""
                r5.append(r1)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                java.lang.String r1 = r5.toString()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                r2[r6] = r1     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                com.medscape.android.util.LogUtil.e(r12, r4, r2)     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
            L_0x00cc:
                r3.close()     // Catch:{ SocketException -> 0x00df, SocketTimeoutException -> 0x00da, UnknownHostException -> 0x00d5, Exception -> 0x00d0 }
                return r11
            L_0x00d0:
                r11 = move-exception
                r11.printStackTrace()
                goto L_0x00e3
            L_0x00d5:
                r11 = move-exception
                r11.printStackTrace()
                goto L_0x00e3
            L_0x00da:
                r11 = move-exception
                r11.printStackTrace()
                goto L_0x00e3
            L_0x00df:
                r11 = move-exception
                r11.printStackTrace()
            L_0x00e3:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.updater.SingleDataUpdateManager.DownloadUpdateTask.connect(com.medscape.android.updater.model.Update, java.lang.String):com.medscape.android.updater.model.Update");
        }

        private void saveToFile(String str, boolean z) throws Exception {
            try {
                File file = new File(FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/");
                if (!file.canWrite()) {
                    file.mkdir();
                }
                FileInputStream fileInputStream = new FileInputStream(new File(file.getPath() + "/" + str));
                if (!z) {
                    ZipUtils.unzipWOPassword(fileInputStream, file.getPath(), UpdateManager.PASSWORD, (OnUpdateListener) null, this.contentLength);
                } else {
                    ZipUtils.unzip11(fileInputStream, file.getPath(), UpdateManager.PASSWORD, (OnUpdateListener) null, this.contentLength);
                }
            } catch (Exception e) {
                throw e;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Update update2) {
            if (update2 == null) {
                SingleDataUpdateManager.this.onError();
                return;
            }
            if (SingleDataUpdateManager.this.updateList != null) {
                if (SingleDataUpdateManager.this.updateList.size() > 0) {
                    SingleDataUpdateManager.this.updateList.remove(0);
                }
                if (SingleDataUpdateManager.this.updateList.size() > 0) {
                    SingleDataUpdateManager singleDataUpdateManager = SingleDataUpdateManager.this;
                    singleDataUpdateManager.performSingleUpdate((Update) singleDataUpdateManager.updateList.get(0));
                }
            }
            SingleDataUpdateManager.this.markAllUpdatesCompleted(this.type);
        }
    }

    /* access modifiers changed from: private */
    public void onError() {
        IUpdateDownloadListener iUpdateDownloadListener = this.downloadListener;
        if (iUpdateDownloadListener != null) {
            iUpdateDownloadListener.onUpdateDownloadComplete();
        }
    }

    public void markAllUpdatesCompleted(String str) {
        LogUtil.d(TAG, "markAllUpdatesCompleted() type =%s", str);
        if (str.equalsIgnoreCase(DRUG)) {
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_SINGLE_DRUG_UPDATE_CLIENT_VERISON, Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_DRUG, AppEventsConstants.EVENT_PARAM_VALUE_NO));
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_SINGLE_DATA_DEPENDENT_CLIENT_VERISON_DRUG, Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_DEPENDENT_VERISON_DRUG, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } else if (str.equalsIgnoreCase("calc")) {
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_SINGLE_CALC_UPDATE_CLIENT_VERISON, Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_CALC, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } else if (str.equalsIgnoreCase(REF)) {
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_SINGLE_REF_UPDATE_CLIENT_VERISON, Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_UPDATE_VERSION_REF, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } else if (str.equalsIgnoreCase(ADSEGVARS)) {
            Settings.singleton(this.mContext).saveSetting(Constants.PREF_SINGLE_AD_SEGVARS_UPDATE_CLIENT_VERSION, Settings.singleton(this.mContext).getSetting(UpdateManager.SINGLE_DATA_DEPENDENT_VERSION_AD_SEGVARS, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        }
        List<Update> list = this.updateList;
        if (list != null && list.size() == 0) {
            onSuccess();
        }
    }

    private void onSuccess() {
        Log.d(TAG, "onSuccess()");
        IUpdateDownloadListener iUpdateDownloadListener = this.downloadListener;
        if (iUpdateDownloadListener != null) {
            iUpdateDownloadListener.onSingleDataUpdateSucess();
        }
    }
}
