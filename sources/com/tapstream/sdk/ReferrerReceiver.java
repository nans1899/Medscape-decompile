package com.tapstream.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        if (stringExtra != null) {
            try {
                String decode = URLDecoder.decode(stringExtra, "utf-8");
                if (decode.length() > 0) {
                    SharedPreferences.Editor edit = context.getApplicationContext().getSharedPreferences(AndroidPlatform.UUID_KEY, 0).edit();
                    edit.putString("referrer", decode);
                    edit.apply();
                }
            } catch (UnsupportedEncodingException unused) {
            }
        }
    }
}
