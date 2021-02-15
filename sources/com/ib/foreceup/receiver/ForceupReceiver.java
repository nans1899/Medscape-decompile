package com.ib.foreceup.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.ib.foreceup.ForceUpdateManager;
import com.ib.foreceup.model.RemoteUpdateMessaging;
import com.ib.foreceup.util.Constants;
import com.ib.foreceup.util.Util;

public class ForceupReceiver extends BroadcastReceiver {
    private AppCompatActivity activity;
    RemoteUpdateMessaging remoteUpdateMessaging;

    public ForceupReceiver(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    public void onReceive(Context context, Intent intent) {
        Util.unregisterForceupReceiver(this.activity, this);
        Util.saveForceupDonePref(this.activity, true);
        RemoteUpdateMessaging remoteUpdateMessaging2 = (RemoteUpdateMessaging) intent.getParcelableExtra(Constants.REMOTE_UPDATE_MESSAGING);
        this.remoteUpdateMessaging = remoteUpdateMessaging2;
        Util.showDialog(this.activity, remoteUpdateMessaging2, (ForceUpdateManager.UpdateListener) null);
    }
}
