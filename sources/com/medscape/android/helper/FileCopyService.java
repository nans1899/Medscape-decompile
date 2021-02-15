package com.medscape.android.helper;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import com.medscape.android.Constants;
import com.medscape.android.provider.SharedPreferenceProvider;
import java.io.File;
import java.io.IOException;

public class FileCopyService extends IntentService {
    public FileCopyService(String str) {
        super(str);
    }

    public FileCopyService() {
        super((String) null);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        boolean z = true;
        boolean booleanExtra = intent != null ? intent.getBooleanExtra(Constants.EXTRA_COPY_BUNDLE, true) : true;
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            z = false;
        }
        if (z) {
            File file = new File(Environment.getExternalStorageDirectory() + "/Medscape/");
            if (!file.exists()) {
                return;
            }
            if (booleanExtra) {
                try {
                    FileHelper.copyFiles(file, new File(getFilesDir() + "/Medscape/"));
                    SharedPreferenceProvider.get().save(Constants.COPY_STATUS, (int) Constants.COPY_COMPLETED);
                    FileHelper.deleteDir(file);
                } catch (IOException unused) {
                    SharedPreferenceProvider.get().save(Constants.COPY_STATUS, (int) Constants.COPY_FAILED);
                }
            } else {
                FileHelper.deleteDir(file);
            }
        }
    }
}
