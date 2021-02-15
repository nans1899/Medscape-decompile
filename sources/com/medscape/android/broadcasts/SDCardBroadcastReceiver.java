package com.medscape.android.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.updater.UpdateManager;
import java.io.File;
import java.util.List;

public class SDCardBroadcastReceiver extends BroadcastReceiver {
    private Context context;

    public SDCardBroadcastReceiver() {
        System.err.println("constructor");
    }

    public void onReceive(Context context2, Intent intent) {
        this.context = context2;
        validateData();
    }

    private void validateData() {
        List<CRData> wordConditionMatching;
        String str = FileHelper.getDataDirectory(this.context) + "/Medscape/Medscape.sql";
        boolean z = false;
        SharedPreferences.Editor edit = this.context.getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).edit();
        if (!new File(str).exists() || (wordConditionMatching = SearchHelper.wordConditionMatching("cpr", 25, 0, this.context)) == null || wordConditionMatching.size() == 0) {
            z = true;
        }
        if (z) {
            edit.putFloat("version", -1.0f);
            edit.commit();
            Settings.singleton(this.context).saveSetting(Constants.PREF_SESSION_END, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
    }
}
