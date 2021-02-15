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
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.updater.model.Update;
import com.medscape.android.util.Util;
import java.util.List;

public class BackgroundClinicalUpdateService extends Service implements OnUpdateListener {
    final int PROGRESS_INTERVAL = 200;
    NotificationCompat.Builder mBuilder;
    int mCurrentFile;
    int mCurrentProgress = 0;
    int mCurrentProgressMax;
    boolean mDownloadComplete = false;
    private ServiceHandler mServiceHandler;
    private Looper mServiceLooper;
    int mTotalFileSize = 0;
    List<Update> mUpdateList;
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
            BackgroundClinicalUpdateService.this.mUpdater.isVersionCheck = false;
            BackgroundClinicalUpdateService.this.mUpdater.getReferencePList();
        }
    }

    public void onCreate() {
        HandlerThread handlerThread = new HandlerThread("ClinicalDownloadThread", 10);
        handlerThread.start();
        UpdateManager updateManager = new UpdateManager(this);
        this.mUpdater = updateManager;
        updateManager.setOnUpdateListener(this);
        this.mServiceLooper = handlerThread.getLooper();
        this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
        MedscapeApplication.get().listenForUpdates(this);
        createNotificationManage();
        NotificationCompat.Builder builder = new NotificationCompat.Builder((Context) this, getString(R.string.default_notification_channel_id));
        this.mBuilder = builder;
        builder.setOnlyAlertOnce(true).setContentTitle("Clinical Reference Download").setContentText("Download started").setTicker("Download started").setSmallIcon((int) R.drawable.ic_micon_inverse).setContentIntent(PendingIntent.getActivity(this, 1001, new Intent(), 134217728));
        startForeground(1001, this.mBuilder.build());
        this.mBuilder.setContentText("Download in progress").setTicker("");
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
        return 2;
    }

    public void onDestroy() {
        stopForeground(true);
        PendingIntent activity = PendingIntent.getActivity(this, 1001, new Intent(), 134217728);
        if (this.mDownloadComplete) {
            this.notificationManager.notify(1001, new NotificationCompat.Builder((Context) this, getString(R.string.default_notification_channel_id)).setContentTitle("Clinical Reference Download").setContentText("Download Completed").setSmallIcon((int) R.drawable.ic_micon_inverse).setAutoCancel(true).setTicker("Download Completed").setContentIntent(activity).build());
        } else {
            this.notificationManager.notify(1001, new NotificationCompat.Builder((Context) this, getString(R.string.default_notification_channel_id)).setContentTitle("Clinical Reference Download").setContentText("Download Failed").setSmallIcon((int) R.drawable.ic_micon_inverse).setAutoCancel(true).setTicker("Download Failed").setContentIntent(activity).build());
        }
        super.onDestroy();
        MedscapeApplication.get().setBackgroundUpdate(false);
    }

    public void onUpdateAvailable(int i, List<Update> list, int i2) {
        if (Util.isSDCardAvailable()) {
            this.mUpdateList = list;
            if (list != null && !list.isEmpty() && this.mUpdateList.get(0) != null) {
                Update update = this.mUpdateList.get(0);
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
                    startForeground(1001, this.mBuilder.build());
                    Settings singleton = Settings.singleton(this);
                    singleton.saveSetting(Constants.PREF_DRUG_INSTALLTION_TOTAL_FILES, "" + this.mUpdateList.size());
                    this.mUpdater.downloadAndInstallUpdate(2, this.mUpdateList.get(0), Constants.DIRECTORY_MAIN_CR, false);
                }
            }
        }
    }

    public void onUpdateComplete(int i, Update update) {
        List<Update> list = this.mUpdateList;
        if (list != null) {
            if (list.size() > 0) {
                this.mUpdateList.remove(0);
            }
            if (this.mUpdateList.size() <= 0) {
                return;
            }
            if (!this.mUpdateList.get(0).getType().equals("purgeClinical")) {
                NotificationCompat.Builder builder = this.mBuilder;
                StringBuilder sb = new StringBuilder();
                sb.append("Download in progress (");
                int min = Math.min(this.mCurrentFile + 1, this.mTotalFileSize);
                this.mCurrentFile = min;
                sb.append(min);
                sb.append("/");
                sb.append(this.mTotalFileSize);
                sb.append(")");
                startForeground(1001, builder.setContentText(sb.toString()).build());
                this.mUpdater.downloadAndInstallUpdate(2, this.mUpdateList.get(0), Constants.DIRECTORY_MAIN_CR, false);
                return;
            }
            NotificationCompat.Builder builder2 = this.mBuilder;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Download in progress (");
            int min2 = Math.min(this.mCurrentFile + 1, this.mTotalFileSize);
            this.mCurrentFile = min2;
            sb2.append(min2);
            sb2.append("/");
            sb2.append(this.mTotalFileSize);
            sb2.append(")");
            startForeground(1001, builder2.setContentText(sb2.toString()).build());
            this.mUpdater.markAllUpdatesCompleted(2);
            this.mDownloadComplete = true;
            stopSelf();
        }
    }

    public void onProgressUpdate(int i) {
        int i2 = this.mCurrentProgress + 1;
        this.mCurrentProgress = i2;
        if (i2 % 200 == 0) {
            this.mBuilder.setProgress(this.mCurrentProgressMax, i, false);
            startForeground(1001, this.mBuilder.build());
        }
    }

    public void setMaxProgress(int i) {
        this.mCurrentProgressMax = i;
        this.mBuilder.setProgress(i, 0, false);
        startForeground(1001, this.mBuilder.build());
    }

    public void setProgressMessage(String str) {
        NotificationCompat.Builder builder = this.mBuilder;
        builder.setContentTitle("Data Update (" + str + ")");
        startForeground(1001, this.mBuilder.build());
    }

    public void onNetworkError(int i) {
        stopSelf();
    }

    public void finishAllBackgroundUpdates() {
        Settings.singleton(this).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
    }
}
