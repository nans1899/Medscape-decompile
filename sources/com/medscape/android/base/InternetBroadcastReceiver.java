package com.medscape.android.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;

public class InternetBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        AccountProvider.signIn(context, new ICallbackEvent<Object, Exception>() {
            public void onCompleted(Object obj) {
            }

            public void onError(Exception exc) {
            }
        });
    }
}
