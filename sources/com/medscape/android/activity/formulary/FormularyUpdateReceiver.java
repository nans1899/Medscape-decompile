package com.medscape.android.activity.formulary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.homescreen.interfaces.IUpdateDownloadListener;

public class FormularyUpdateReceiver extends BroadcastReceiver {
    public static final String FORMULARY_DATABASE_FILE_NAME = "formulary_new.zip";
    public static String FORMULARY_SERVICE_URL = "http://api.qa02.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?";
    private static final String TAG = "FormularyUpdateReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReveiver called");
        Toast.makeText(context, "Notification Received", 0).show();
        new FormularyDownloadService(context, (IUpdateDownloadListener) null).execute(new String[]{FORMULARY_SERVICE_URL, AppEventsConstants.EVENT_PARAM_VALUE_YES});
    }
}
