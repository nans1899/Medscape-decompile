package com.ib.foreceup.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.ib.foreceup.ForceUpdateManager;
import com.ib.foreceup.model.RemoteUpdateMessaging;
import com.ib.foreceup.receiver.ForceupReceiver;
import com.ib.foreceup.ui.UpdateDialogFragment;

public class Util {
    public static boolean isForceupReceiverRegistered;

    public static SharedPreferences getSharedPreferences(Activity activity) {
        if (activity != null) {
            return activity.getSharedPreferences("force_update_preferences", 0);
        }
        return null;
    }

    public static void saveForceupDonePref(Activity activity, boolean z) {
        if (activity != null) {
            getSharedPreferences(activity).edit().putBoolean(Constants.PREF_FORCEUP_CHECK_DONE, z).apply();
        }
    }

    public static boolean isForceupDone(Activity activity) {
        if (activity != null) {
            return getSharedPreferences(activity).getBoolean(Constants.PREF_FORCEUP_CHECK_DONE, false);
        }
        return false;
    }

    public static void showDialog(AppCompatActivity appCompatActivity, RemoteUpdateMessaging remoteUpdateMessaging, ForceUpdateManager.UpdateListener updateListener) {
        if (appCompatActivity != null && remoteUpdateMessaging != null) {
            boolean isForceGoToPlayStore = remoteUpdateMessaging.isForceGoToPlayStore();
            UpdateDialogFragment newInstance = UpdateDialogFragment.newInstance(remoteUpdateMessaging.getTitle(), remoteUpdateMessaging.getMessage(), remoteUpdateMessaging.getUpdateButtonText(), remoteUpdateMessaging.getDimissButtonText(), remoteUpdateMessaging.getSkipButtonText(), !isForceGoToPlayStore, new DialogInterface.OnClickListener(isForceGoToPlayStore, appCompatActivity, remoteUpdateMessaging, updateListener) {
                public final /* synthetic */ boolean f$0;
                public final /* synthetic */ AppCompatActivity f$1;
                public final /* synthetic */ RemoteUpdateMessaging f$2;
                public final /* synthetic */ ForceUpdateManager.UpdateListener f$3;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    Util.lambda$showDialog$0(this.f$0, this.f$1, this.f$2, this.f$3, dialogInterface, i);
                }
            }, new DialogInterface.OnDismissListener(isForceGoToPlayStore, updateListener, appCompatActivity) {
                public final /* synthetic */ boolean f$0;
                public final /* synthetic */ ForceUpdateManager.UpdateListener f$1;
                public final /* synthetic */ AppCompatActivity f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onDismiss(DialogInterface dialogInterface) {
                    Util.lambda$showDialog$1(this.f$0, this.f$1, this.f$2, dialogInterface);
                }
            });
            try {
                if (!appCompatActivity.isDestroyed()) {
                    newInstance.show(appCompatActivity.getSupportFragmentManager(), "update_dialog");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void lambda$showDialog$0(boolean z, AppCompatActivity appCompatActivity, RemoteUpdateMessaging remoteUpdateMessaging, ForceUpdateManager.UpdateListener updateListener, DialogInterface dialogInterface, int i) {
        if (i == -3 && !z) {
            onSkip(appCompatActivity, remoteUpdateMessaging.getNextTargetVersionMax(), updateListener);
        } else if (i == -1 || z) {
            gotoPlayStore(appCompatActivity);
            appCompatActivity.finish();
        } else if (updateListener != null) {
            updateListener.onNext();
        }
    }

    static /* synthetic */ void lambda$showDialog$1(boolean z, ForceUpdateManager.UpdateListener updateListener, AppCompatActivity appCompatActivity, DialogInterface dialogInterface) {
        if (z) {
            gotoPlayStore(appCompatActivity);
            appCompatActivity.finish();
        } else if (updateListener != null) {
            updateListener.onNext();
        }
    }

    private static void gotoPlayStore(Activity activity) {
        if (activity != null) {
            try {
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + ForceUpdateManager.appPackageName)));
            } catch (ActivityNotFoundException unused) {
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + ForceUpdateManager.appPackageName)));
            }
        }
    }

    private static void onSkip(Activity activity, String str, ForceUpdateManager.UpdateListener updateListener) {
        if (activity != null) {
            SharedPreferences.Editor edit = getSharedPreferences(activity).edit();
            edit.putBoolean("skip_" + str, true).apply();
        }
        if (updateListener != null) {
            updateListener.onNext();
        }
    }

    public static BroadcastReceiver registerForceupReceiver(AppCompatActivity appCompatActivity) {
        if (appCompatActivity == null || isForceupReceiverRegistered || isForceupDone(appCompatActivity)) {
            return null;
        }
        ForceupReceiver forceupReceiver = new ForceupReceiver(appCompatActivity);
        LocalBroadcastManager.getInstance(appCompatActivity).registerReceiver(new ForceupReceiver(appCompatActivity), new IntentFilter(Constants.SHOW_DIALOG_FORCE_UP_ACTION));
        isForceupReceiverRegistered = true;
        return forceupReceiver;
    }

    public static void unregisterForceupReceiver(AppCompatActivity appCompatActivity, BroadcastReceiver broadcastReceiver) {
        if (appCompatActivity != null && broadcastReceiver != null) {
            try {
                if (isForceupReceiverRegistered) {
                    LocalBroadcastManager.getInstance(appCompatActivity).unregisterReceiver(broadcastReceiver);
                    isForceupReceiverRegistered = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
