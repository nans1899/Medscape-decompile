package com.medscape.android.update;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.formulary.FormularyDownloadService;
import com.medscape.android.homescreen.interfaces.IUpdateDownloadListener;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.task.PurgeFolderTask;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.SingleDataUpdateManager;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.updater.UpdateUrls;
import com.medscape.android.updater.model.Update;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.Util;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;
import java.io.File;
import java.util.List;
import java.util.Map;

public class BackgroundDataUpdateService extends Service implements OnUpdateListener {
    final int PROGRESS_INTERVAL = 200;
    NotificationCompat.Builder mBuilder;
    int mCurrentFile;
    int mCurrentProgress = 0;
    int mCurrentProgressMax;
    boolean mDownloadComplete;
    String mPListDescriptionText;
    private ServiceHandler mServiceHandler;
    private Looper mServiceLooper;
    int mTotalFileSize = 0;
    List<Update> mUpdateList;
    String mUpdateMsg;
    String mUpdateTitle;
    UpdateManager mUpdater;
    NotificationManager notificationManager;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onUpdateNotAvailable(int i) {
    }

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            BackgroundDataUpdateService.this.mUpdater.isVersionCheck = false;
            BackgroundDataUpdateService.this.mUpdater.getUpdatePList2(1, UpdateUrls.getUrlForEnvironment(BackgroundDataUpdateService.this.mUpdater.getEnvironment(), UpdateUrls.UPDATEPLIST_URL));
        }
    }

    public void onCreate() {
        HandlerThread handlerThread = new HandlerThread("ServiceStartArguments", 10);
        handlerThread.start();
        this.mServiceLooper = handlerThread.getLooper();
        this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
        UpdateManager createFrom = UpdateManager.createFrom(MedscapeApplication.get().getUpdateManager());
        this.mUpdater = createFrom;
        createFrom.setOnUpdateListener(this);
        createNotificationManage();
        NotificationCompat.Builder builder = new NotificationCompat.Builder((Context) this, getString(R.string.default_notification_channel_id));
        this.mBuilder = builder;
        builder.setOnlyAlertOnce(true).setContentTitle("Data Update").setContentText("Download started").setTicker("Download started").setSmallIcon((int) R.drawable.ic_micon_inverse).setContentIntent(PendingIntent.getActivity(this, 1000, new Intent(this, BackgroundUpdateProgressActivity.class), 134217728));
        startForeground(1000, this.mBuilder.build());
        this.mBuilder.setContentText("Download in progress");
        MedscapeApplication.get().setBackgroundUpdate(true);
    }

    private void createNotificationManage() {
        this.notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.default_notification_channel_id), "Notifications", 3);
            notificationChannel.setDescription("Notifications");
            this.notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Message obtainMessage = this.mServiceHandler.obtainMessage();
        obtainMessage.arg1 = i2;
        this.mServiceHandler.sendMessage(obtainMessage);
        return 1;
    }

    public void onDestroy() {
        stopForeground(true);
        PendingIntent activity = PendingIntent.getActivity(this, 1000, new Intent(this, BackgroundUpdateProgressActivity.class).putExtra(Constants.PREF_UPDATE_TITLE, this.mUpdateTitle).putExtra(Constants.PREF_UPDATE_MESSAGE, this.mUpdateMsg), 134217728);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder((Context) this, getString(R.string.default_notification_channel_id)).setContentTitle("Data Update").setContentText("Update Completed").setSmallIcon((int) R.drawable.ic_micon_inverse).setAutoCancel(true).setTicker("Update Completed").setContentIntent(activity);
        if (this.mDownloadComplete) {
            this.notificationManager.notify(1000, contentIntent.build());
            SharedPreferenceProvider.get().save(Constants.PREF_UPDATE_TIME, Long.valueOf(System.currentTimeMillis()));
            SharedPreferenceProvider.get().save(Constants.PREF_UPDATE_TITLE, this.mUpdateTitle);
            SharedPreferenceProvider.get().save(Constants.PREF_UPDATE_MESSAGE, this.mUpdateMsg);
        } else {
            this.notificationManager.notify(1000, new NotificationCompat.Builder((Context) this, getString(R.string.default_notification_channel_id)).setContentTitle("Data Update").setContentText("Update Failed").setSmallIcon((int) R.drawable.ic_micon_inverse).setAutoCancel(true).setTicker("Update Failed").setContentIntent(activity).build());
        }
        MedscapeApplication.get().setBackgroundUpdate(false);
        super.onDestroy();
    }

    public void onUpdateAvailable(int i, List<Update> list, int i2) {
        if (Util.isSDCardAvailable()) {
            this.mUpdateList = list;
            boolean z = false;
            Update update = list.get(0);
            if (update.getUrl() != null && !update.getUrl().equals("")) {
                this.mTotalFileSize = list.size();
                NotificationCompat.Builder builder = this.mBuilder;
                StringBuilder sb = new StringBuilder();
                sb.append("Download in progress (");
                this.mCurrentFile = 1;
                sb.append(1);
                sb.append("/");
                sb.append(this.mTotalFileSize);
                sb.append(")");
                builder.setContentText(sb.toString());
                startForeground(1000, this.mBuilder.build());
                Settings singleton = Settings.singleton(this);
                singleton.saveSetting(Constants.PREF_DRUG_INSTALLTION_TOTAL_FILES, "" + this.mUpdateList.size());
                this.mUpdater.downloadAndInstallUpdate(1, this.mUpdateList.get(0), Constants.DIRECTORY_TEMP, false);
            } else if (!update.getType().equalsIgnoreCase("Upgrade App")) {
                this.mUpdateTitle = update.getmUpdateTitle();
                this.mUpdateMsg = update.getmUpdateMsg();
                startForeground(1000, this.mBuilder.setContentIntent(PendingIntent.getActivity(this, 1000, new Intent(this, BackgroundUpdateProgressActivity.class).putExtra(Constants.PREF_UPDATE_TITLE, this.mUpdateTitle).putExtra(Constants.PREF_UPDATE_MESSAGE, this.mUpdateMsg), 134217728)).build());
                Settings.singleton(this).saveSetting(Constants.PREF_DRUG_INSTALLTION_PLIST_TEXT, update.getType());
                if (this.mUpdateList.size() > 1) {
                    Update update2 = this.mUpdateList.get(1);
                    if (update2.getUrl() == null || update2.getUrl().equals("")) {
                        String type = update2.getType();
                        UpdateManager updateManager = this.mUpdater;
                        if (Double.parseDouble(Settings.singleton(this).getSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO)) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            z = true;
                        }
                        updateManager.getDrugPList(type, z);
                    }
                }
            }
        }
    }

    public void onUpdateComplete(int i, Update update) {
        if (this.mUpdateList.size() > 0) {
            this.mUpdateList.remove(0);
        }
        if (this.mUpdateList.size() > 0) {
            String type = this.mUpdateList.get(0).getType();
            if (type.equals("purgeClinical")) {
                NotificationCompat.Builder builder = this.mBuilder;
                StringBuilder sb = new StringBuilder();
                sb.append("Download in progress (");
                int min = Math.min(this.mCurrentFile + 1, this.mTotalFileSize);
                this.mCurrentFile = min;
                sb.append(min);
                sb.append("/");
                sb.append(this.mTotalFileSize);
                sb.append(")");
                startForeground(1000, builder.setContentText(sb.toString()).build());
                this.mUpdater.markAllUpdatesCompleted(2);
                if (this.mUpdateList.size() > 0) {
                    onUpdateComplete(0, this.mUpdateList.get(0));
                    return;
                }
                this.mDownloadComplete = true;
                dataUpdateFinish();
            } else if (type.equals("purgeSystem")) {
                NotificationCompat.Builder builder2 = this.mBuilder;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Download in progress (");
                int min2 = Math.min(this.mCurrentFile + 1, this.mTotalFileSize);
                this.mCurrentFile = min2;
                sb2.append(min2);
                sb2.append("/");
                sb2.append(this.mTotalFileSize);
                sb2.append(")");
                startForeground(1000, builder2.setContentText(sb2.toString()).build());
                new PurgeFolderTask(new File(Constants.DIRECTORY_MAIN), new PurgeFolderTask.PurgeListener() {
                    public void onPurgeComplete() {
                        BackgroundDataUpdateService.this.mUpdater.markAllUpdatesCompleted(1);
                        if (BackgroundDataUpdateService.this.mUpdateList.size() > 0) {
                            BackgroundDataUpdateService backgroundDataUpdateService = BackgroundDataUpdateService.this;
                            backgroundDataUpdateService.onUpdateComplete(0, backgroundDataUpdateService.mUpdateList.get(0));
                            return;
                        }
                        BackgroundDataUpdateService.this.mDownloadComplete = true;
                        BackgroundDataUpdateService.this.dataUpdateFinish();
                    }
                }).execute(new File[]{new File(Constants.DIRECTORY_TEMP)});
            } else if (!type.equals("conditionalDownloadClinical")) {
                NotificationCompat.Builder builder3 = this.mBuilder;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Download in progress (");
                int min3 = Math.min(this.mCurrentFile + 1, this.mTotalFileSize);
                this.mCurrentFile = min3;
                sb3.append(min3);
                sb3.append("/");
                sb3.append(this.mTotalFileSize);
                sb3.append(")");
                startForeground(1000, builder3.setContentText(sb3.toString()).build());
                this.mUpdater.downloadAndInstallUpdate(1, this.mUpdateList.get(0), Constants.DIRECTORY_TEMP, false);
            } else {
                NotificationCompat.Builder builder4 = this.mBuilder;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Download in progress (");
                int min4 = Math.min(this.mCurrentFile + 1, this.mTotalFileSize);
                this.mCurrentFile = min4;
                sb4.append(min4);
                sb4.append("/");
                sb4.append(this.mTotalFileSize);
                sb4.append(")");
                startForeground(1000, builder4.setContentText(sb4.toString()).build());
                this.mUpdater.downloadAndInstallUpdate(2, this.mUpdateList.get(0), Constants.DIRECTORY_MAIN_CR, false);
            }
        } else {
            this.mDownloadComplete = true;
            dataUpdateFinish();
            float f = getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", -1.0f);
            OmnitureManager omnitureManager = OmnitureManager.get();
            omnitureManager.trackModule(this, "other", "data-finish", "" + f, (Map<String, Object>) null);
        }
    }

    public void onProgressUpdate(int i) {
        int i2 = this.mCurrentProgress + 1;
        this.mCurrentProgress = i2;
        if (i2 % 200 == 0) {
            this.mBuilder.setProgress(this.mCurrentProgressMax, i, false);
            startForeground(1000, this.mBuilder.build());
        }
    }

    public void setMaxProgress(int i) {
        this.mCurrentProgressMax = i;
        this.mBuilder.setProgress(i, 0, false);
        startForeground(1000, this.mBuilder.build());
    }

    public void setProgressMessage(String str) {
        NotificationCompat.Builder builder = this.mBuilder;
        builder.setContentTitle("Data Update (" + str + ")");
        startForeground(1000, this.mBuilder.build());
    }

    public void onNetworkError(int i) {
        stopSelf();
    }

    /* access modifiers changed from: private */
    public void dataUpdateFinish() {
        startSingleUpdates();
        stopSelf();
    }

    private void startSingleUpdates() {
        Settings.singleton(this).saveSetting(Constants.PREF_UPDATE_COMPLETE, "NO");
        if (!isSingleUpdateStarted()) {
            startFormularyUpdates();
        }
    }

    public void startFormularyUpdates() {
        Settings.singleton(this).saveSetting(Constants.PREF_UPDATE_COMPLETE, "NO");
        if (!isFormularyUpdateStarted()) {
            finishAllBackgroundUpdates();
        }
    }

    private boolean isFormularyUpdateStarted() {
        if (!Util.isOnline(this) || !Settings.singleton(this).getSetting(Constants.PREF_FORMULARY_VISITED, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            return false;
        }
        new FormularyDownloadService(this, (IUpdateDownloadListener) null).execute(new String[]{OldConstants.getFormularyURL(new EnvironmentManager().getEnvironmentWithDefault(this, EnvironmentConstants.MODULE_SERVICE)), ""});
        return true;
    }

    private boolean isSingleUpdateStarted() {
        if (Util.isOnline(this)) {
            return new SingleDataUpdateManager(this, (IUpdateDownloadListener) null).checkForSingleUpdate(0);
        }
        return false;
    }

    public void finishAllBackgroundUpdates() {
        Settings.singleton(this).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
    }
}
