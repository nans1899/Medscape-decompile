package com.ib.foreceup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.ib.foreceup.model.RemoteUpdate;
import com.ib.foreceup.model.RemoteUpdateMessaging;
import com.ib.foreceup.model.UserConditions;
import com.ib.foreceup.util.Constants;
import com.ib.foreceup.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

public class ForceUpdateManager {
    private static final String FORCE_UPDATE_KEY = "android_forceup_config";
    private static final String KEY_CAN_PROCEED_PREVIOUSLY = "can_proceed_previously";
    public static String appPackageName = "";
    private AppCompatActivity activity;
    private int appVersionNumber;
    private long cacheExpiration = 3600;
    private Context context;
    private ThreadPoolExecutor executor;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private int maxShowDialogDelay = 10000;
    private int numberOfCores = Runtime.getRuntime().availableProcessors();
    private SharedPreferences sharedPreferences;
    private UpdateListener updateListener;
    private boolean useForceupReceiver;
    private ArrayList<UserConditions.UserSentCriteria> userCriteria;

    public interface UpdateListener {
        void onError(Throwable th);

        void onNext();
    }

    public ForceUpdateManager(AppCompatActivity appCompatActivity, int i, String str, UpdateListener updateListener2) {
        this.activity = appCompatActivity;
        appPackageName = str;
        this.updateListener = updateListener2;
        this.sharedPreferences = Util.getSharedPreferences(appCompatActivity);
        int i2 = this.numberOfCores;
        this.executor = new ThreadPoolExecutor(i2 * 2, i2 * 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque());
        try {
            this.appVersionNumber = RemoteUpdate.versionStringToNumber(appCompatActivity.getPackageManager().getPackageInfo(str, 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initRemoteConfig(i);
    }

    public ForceUpdateManager() {
    }

    private void initRemoteConfig(int i) {
        this.mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        this.mFirebaseRemoteConfig.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(this.cacheExpiration).build());
        this.mFirebaseRemoteConfig.setDefaults(i);
    }

    public void checkRemoteConfig() {
        this.mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener((Executor) this.executor, new OnCompleteListener<Boolean>() {
            public void onComplete(Task<Boolean> task) {
                if (task.isSuccessful()) {
                    ForceUpdateManager.this.fetchRemoteConfig();
                } else if (ForceUpdateManager.this.isCanProceedPreviously()) {
                    ForceUpdateManager.this.onNext();
                } else {
                    ForceUpdateManager.this.onError(new Exception("Failed to fetch RemoteConfig"));
                }
            }
        });
    }

    public void checkRemoteConfigWithReceiver(Context context2, ArrayList<UserConditions.UserSentCriteria> arrayList) {
        this.context = context2;
        this.useForceupReceiver = true;
        this.maxShowDialogDelay = 10000;
        this.userCriteria = arrayList;
        checkRemoteConfig();
    }

    /* access modifiers changed from: private */
    public void fetchRemoteConfig() {
        try {
            List<RemoteUpdate> parseRemoteConfigList = RemoteConfigParser.parseRemoteConfigList(this.mFirebaseRemoteConfig.getString(FORCE_UPDATE_KEY));
            boolean z = false;
            Collections.sort(parseRemoteConfigList);
            Iterator<RemoteUpdate> it = parseRemoteConfigList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RemoteUpdate next = it.next();
                if (shouldDisplayUpdateDialog(next)) {
                    z = true;
                    handleRemoteUpdate(next);
                    break;
                }
            }
            if (!z) {
                onNext();
            }
        } catch (JSONException e) {
            if (isCanProceedPreviously()) {
                onNext();
            } else {
                onError(e);
            }
        }
    }

    private boolean shouldDisplayUpdateDialog(RemoteUpdate remoteUpdate) {
        if (RemoteUpdate.TYPE_SILENT.equalsIgnoreCase(remoteUpdate.getRemoteUpdateType())) {
            return false;
        }
        if (remoteUpdate.getTargetVersionMinNumber() == 0 && remoteUpdate.getTargetVersionMaxNumber() == 0) {
            return false;
        }
        if (remoteUpdate.getTargetVersionMinNumber() <= 0 || remoteUpdate.getTargetVersionMaxNumber() <= 0) {
            if (remoteUpdate.getTargetVersionMinNumber() > 0) {
                if (remoteUpdate.getTargetVersionMinNumber() <= this.appVersionNumber) {
                    return true;
                }
                return false;
            } else if (remoteUpdate.getTargetVersionMaxNumber() <= 0 || remoteUpdate.getTargetVersionMaxNumber() < this.appVersionNumber) {
                return false;
            } else {
                return true;
            }
        } else if (remoteUpdate.getTargetVersionMinNumber() > this.appVersionNumber || remoteUpdate.getTargetVersionMaxNumber() < this.appVersionNumber) {
            return false;
        } else {
            return true;
        }
    }

    private void handleRemoteUpdate(RemoteUpdate remoteUpdate) {
        if (RemoteUpdate.TYPE_SILENT.equalsIgnoreCase(remoteUpdate.getRemoteUpdateType())) {
            onNext();
        } else if (!RemoteUpdate.TYPE_OPTIONAL.equalsIgnoreCase(remoteUpdate.getRemoteUpdateType()) || !shouldSkipVersion(remoteUpdate.getTargetVersionMax())) {
            boolean equalsIgnoreCase = RemoteUpdate.TYPE_MANDATORY.equalsIgnoreCase(remoteUpdate.getRemoteUpdateType());
            RemoteUpdateMessaging remoteUpdateMessaging = remoteUpdate.getRemoteUpdateMessaging();
            if (isNullOrEmpty(remoteUpdateMessaging.getTitle())) {
                remoteUpdateMessaging.setTitle(this.mFirebaseRemoteConfig.getString("update_available_title"));
            }
            if (isNullOrEmpty(remoteUpdateMessaging.getMessage())) {
                remoteUpdateMessaging.setMessage(this.mFirebaseRemoteConfig.getString("update_text"));
            }
            if (isNullOrEmpty(remoteUpdateMessaging.getUpdateButtonText())) {
                remoteUpdateMessaging.setUpdateButtonText(this.mFirebaseRemoteConfig.getString("update_button_text"));
            }
            if (remoteUpdate.getTypeOrder() >= 1 && isNullOrEmpty(remoteUpdateMessaging.getDimissButtonText())) {
                remoteUpdateMessaging.setDimissButtonText(this.mFirebaseRemoteConfig.getString("dismiss_button_text"));
            }
            if (remoteUpdate.getTypeOrder() >= 2 && isNullOrEmpty(remoteUpdateMessaging.getSkipButtonText())) {
                remoteUpdateMessaging.setSkipButtonText(this.mFirebaseRemoteConfig.getString("skip_button_text"));
            }
            setCanProceedPreviously(!equalsIgnoreCase);
            remoteUpdateMessaging.setNextTargetVersionMax(remoteUpdate.getTargetVersionMax());
            remoteUpdateMessaging.setForceGoToPlayStore(equalsIgnoreCase);
            HashMap<String, ArrayList<UserConditions>> additionalCriteria = remoteUpdate.getAdditionalCriteria();
            if (additionalCriteria != null) {
                ArrayList<UserConditions.UserSentCriteria> arrayList = this.userCriteria;
                if (arrayList != null && !arrayList.isEmpty() && shouldSendUpdateBroadcast(additionalCriteria, this.userCriteria)) {
                    if (!this.useForceupReceiver) {
                        Util.showDialog(this.activity, remoteUpdateMessaging, this.updateListener);
                    } else {
                        sendForceupBroadcast(remoteUpdateMessaging);
                    }
                }
            } else if (!this.useForceupReceiver) {
                Util.showDialog(this.activity, remoteUpdateMessaging, this.updateListener);
            } else {
                sendForceupBroadcast(remoteUpdateMessaging);
            }
        } else {
            onNext();
        }
    }

    public boolean shouldSendUpdateBroadcast(HashMap<String, ArrayList<UserConditions>> hashMap, ArrayList<UserConditions.UserSentCriteria> arrayList) {
        boolean z = false;
        for (String next : hashMap.keySet()) {
            Iterator<UserConditions.UserSentCriteria> it = arrayList.iterator();
            while (it.hasNext()) {
                UserConditions.UserSentCriteria next2 = it.next();
                if (next.equals(next2.getKey())) {
                    HashMap<String, String> criteria = next2.getCriteria();
                    Iterator it2 = hashMap.get(next2.getKey()).iterator();
                    while (it2.hasNext()) {
                        UserConditions userConditions = (UserConditions) it2.next();
                        for (String next3 : criteria.keySet()) {
                            if (userConditions.getKay().equals(next3)) {
                                ArrayList<String> value = userConditions.getValue();
                                z = userConditions.isInverse() ? !value.contains(criteria.get(next3)) : value.contains(criteria.get(next3));
                            }
                        }
                    }
                }
            }
        }
        return z;
    }

    private void sendForceupBroadcast(RemoteUpdateMessaging remoteUpdateMessaging) {
        if (Util.isForceupDone(this.activity)) {
            return;
        }
        if (Util.isForceupReceiverRegistered) {
            Intent intent = new Intent(Constants.SHOW_DIALOG_FORCE_UP_ACTION);
            intent.putExtra(Constants.REMOTE_UPDATE_MESSAGING, remoteUpdateMessaging);
            if (this.context == null) {
                this.context = this.activity;
            }
            LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
            return;
        }
        int i = this.maxShowDialogDelay - 300;
        this.maxShowDialogDelay = i;
        if (i > 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendForceupBroadcast(remoteUpdateMessaging);
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /* access modifiers changed from: private */
    public void onNext() {
        Util.saveForceupDonePref(this.activity, true);
        UpdateListener updateListener2 = this.updateListener;
        if (updateListener2 != null) {
            updateListener2.onNext();
        }
    }

    /* access modifiers changed from: private */
    public void onError(Throwable th) {
        Util.saveForceupDonePref(this.activity, true);
        UpdateListener updateListener2 = this.updateListener;
        if (updateListener2 != null) {
            updateListener2.onError(th);
        }
    }

    /* access modifiers changed from: private */
    public boolean isCanProceedPreviously() {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        return sharedPreferences2 == null || sharedPreferences2.getBoolean(KEY_CAN_PROCEED_PREVIOUSLY, true);
    }

    private void setCanProceedPreviously(boolean z) {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        if (sharedPreferences2 != null) {
            sharedPreferences2.edit().putBoolean(KEY_CAN_PROCEED_PREVIOUSLY, z).apply();
        }
    }

    private boolean shouldSkipVersion(String str) {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        if (sharedPreferences2 == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("skip_");
        sb.append(str);
        return sharedPreferences2.getBoolean(sb.toString(), false);
    }
}
