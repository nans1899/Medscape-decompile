package com.medscape.android.activity.formulary;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.helper.ZipUtils;
import com.medscape.android.homescreen.interfaces.IUpdateDownloadListener;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.Util;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UnknownFormatConversionException;
import org.json.JSONException;
import org.json.JSONObject;

public class FormularyDownloadService extends AsyncTask<String, Void, Integer> {
    private static final int CONNECTION_ERROR = 2;
    private static final int DUPLICATE = 11;
    private static final int FAIL = 0;
    public static final String FORMULARY_DATABASE_FILE_NAME = "formulary_new.zip";
    private static final int FORMULARY_RETRY_DOWNLOAD_TIME = 86400000;
    private static final int SUCCESS = 1;
    private static final String TAG = "FormularyDownloadServic";
    public static boolean isDownloadStarted;
    private IUpdateDownloadListener downloadListener;
    private Context mContext;

    public FormularyDownloadService(Context context, IUpdateDownloadListener iUpdateDownloadListener) {
        this.mContext = context;
        this.downloadListener = iUpdateDownloadListener;
    }

    /* access modifiers changed from: protected */
    public Integer doInBackground(String... strArr) {
        return Integer.valueOf(downloadZip(this.mContext, strArr[1], strArr[0]));
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Integer num) {
        PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, OldConstants.FORMULARY_UPDATE_REQUEST_CODE, new Intent(this.mContext, FormularyUpdateReceiver.class), 0);
        super.onPostExecute(num);
        int intValue = num.intValue();
        if (intValue == 0 || intValue == 2) {
            ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, System.currentTimeMillis() + Constants.DAY_IN_MILLIS, broadcast);
        } else if (intValue == 11) {
            Log.d(TAG, "Duplicate request: ignore it");
            return;
        }
        onDownloadComplete();
    }

    public static int downloadZip(Context context, String str, String str2) {
        if (context instanceof FormularyMyPlanPage) {
            isDownloadStarted = false;
        }
        if (isDownloadStarted) {
            return 11;
        }
        isDownloadStarted = true;
        try {
            String allPlanId = getAllPlanId(context);
            String setting = Settings.singleton(context).getSetting(Constants.PREF_FORMULARY_LAST_SERVER_UPDATE_TIME, "");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URI(((((str2 + "response=application/zip") + "&userId=" + Settings.singleton(context).getSetting(Constants.REGISTERED_ID, "")) + "&forcedUpdate=" + str) + "&userPlans=" + URLEncoder.encode(allPlanId, "UTF8")) + "&lastClientUpdate=" + setting).toURL().openConnection();
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            InputStream inputStream = httpURLConnection.getInputStream();
            int contentLength = httpURLConnection.getContentLength();
            String contentType = httpURLConnection.getContentType();
            if (contentType != null) {
                if (!contentType.contains("application/zip")) {
                    throw new UnknownFormatConversionException("content type error");
                }
            }
            File file = new File(FileHelper.getDataDirectory(context) + "/Medscape/temp");
            if (!file.canWrite()) {
                file.mkdir();
            }
            FileHelper.saveToFile(inputStream, file, "formulary_new.zip");
            ZipUtils.unzipWOPassword(new FileInputStream(file.getPath() + "/" + "formulary_new.zip"), file.getPath(), UpdateManager.PASSWORD, (OnUpdateListener) null, contentLength);
            parseStatusCode(context);
            isDownloadStarted = false;
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            isDownloadStarted = false;
            throw th;
        }
        isDownloadStarted = false;
        return 2;
    }

    private static void parseStatusCode(Context context) throws Exception {
        File file = new File(FileHelper.getDataDirectory(context) + "/Medscape/formulary.sqlite");
        File file2 = new File(FileHelper.getDataDirectory(context) + "/Medscape/temp/status");
        File file3 = new File(FileHelper.getDataDirectory(context) + "/Medscape/temp/" + "formulary_new.zip");
        if (file2.exists()) {
            try {
                JSONObject jSONObject = new JSONObject(FileHelper.readFileAsString(file2));
                JSONObject jSONObject2 = jSONObject.getJSONObject("status").getJSONObject("statusCode");
                jSONObject2.getString("name");
                String string = jSONObject2.getString("code");
                Log.d(TAG, "code = " + string);
                String string2 = jSONObject.getString("timestamp");
                if (string.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) && file3.exists()) {
                    file.delete();
                    if (moveFormularyDatabase(file3)) {
                        Log.d(TAG, "successfully updated = ");
                        Settings.singleton(context).saveSetting(Constants.PREF_FORMULARY_LAST_SERVER_UPDATE_TIME, string2.toString());
                        file2.delete();
                        file3.delete();
                        Date date = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmmssZ");
                        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, System.currentTimeMillis() + OldConstants.FORMULARY_UPDATE_TIME, PendingIntent.getBroadcast(context.getApplicationContext(), OldConstants.FORMULARY_UPDATE_REQUEST_CODE, new Intent(context, FormularyUpdateReceiver.class), 0));
                        Settings.singleton(context).saveSetting(Constants.PREF_FORMULARY_LAST_UPDATE_TIME, simpleDateFormat.format(date) + "T" + simpleDateFormat2.format(date));
                    }
                } else if (string.equalsIgnoreCase(UserProfile.PSYCHOLOGIST_ID)) {
                    Log.d(TAG, "Already updated ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    private static boolean moveFormularyDatabase(File file) {
        boolean z = false;
        try {
            File file2 = new File(FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/");
            if (!file2.canWrite()) {
                file2.mkdir();
            }
            if (!file.exists()) {
                return false;
            }
            if (!file.renameTo(new File(file2, file.getName()))) {
                Log.d(TAG, "Failed to move");
                return false;
            }
            try {
                Log.d(TAG, "successfully move");
                ZipUtils.unzipWOPassword(new FileInputStream(file2.getPath() + "/" + "formulary_new.zip"), file2.getPath(), UpdateManager.PASSWORD, (OnUpdateListener) null, 0);
                return true;
            } catch (Exception e) {
                e = e;
                z = true;
                e.printStackTrace();
                return z;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r4 == null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
        if (r4 != null) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getAllPlanId(android.content.Context r4) {
        /*
            com.medscape.android.activity.formulary.FormularyDatabaseHelper r4 = com.medscape.android.activity.formulary.FormularyDatabaseHelper.getInstance(r4)
            android.database.sqlite.SQLiteDatabase r4 = r4.getDatabase()
            java.lang.String r0 = ""
            if (r4 == 0) goto L_0x005e
            java.lang.String r1 = "select planId, stateId from tblUserPlan"
            r2 = 0
            android.database.Cursor r1 = r4.rawQuery(r1, r2)     // Catch:{ Exception -> 0x004e }
            if (r1 == 0) goto L_0x005e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004e }
            r2.<init>()     // Catch:{ Exception -> 0x004e }
        L_0x001a:
            boolean r3 = r1.moveToNext()     // Catch:{ Exception -> 0x004e }
            if (r3 == 0) goto L_0x0041
            r3 = 0
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x004e }
            r2.append(r3)     // Catch:{ Exception -> 0x004e }
            java.lang.String r3 = "|"
            r2.append(r3)     // Catch:{ Exception -> 0x004e }
            r3 = 1
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x004e }
            r2.append(r3)     // Catch:{ Exception -> 0x004e }
            boolean r3 = r1.isLast()     // Catch:{ Exception -> 0x004e }
            if (r3 != 0) goto L_0x001a
            java.lang.String r3 = ","
            r2.append(r3)     // Catch:{ Exception -> 0x004e }
            goto L_0x001a
        L_0x0041:
            r1.close()     // Catch:{ Exception -> 0x004e }
            r2.trimToSize()     // Catch:{ Exception -> 0x004e }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x004e }
            goto L_0x005e
        L_0x004c:
            r0 = move-exception
            goto L_0x0058
        L_0x004e:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x004c }
            if (r4 == 0) goto L_0x0061
        L_0x0054:
            r4.close()
            goto L_0x0061
        L_0x0058:
            if (r4 == 0) goto L_0x005d
            r4.close()
        L_0x005d:
            throw r0
        L_0x005e:
            if (r4 == 0) goto L_0x0061
            goto L_0x0054
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyDownloadService.getAllPlanId(android.content.Context):java.lang.String");
    }

    private void onDownloadComplete() {
        Log.d(TAG, "onDownloadComplete()");
        isDownloadStarted = false;
        Log.d(TAG, "isDownloadStarted FINISH onDownloadComplete()");
        IUpdateDownloadListener iUpdateDownloadListener = this.downloadListener;
        if (iUpdateDownloadListener != null) {
            iUpdateDownloadListener.onUpdateDownloadComplete();
        }
    }
}
