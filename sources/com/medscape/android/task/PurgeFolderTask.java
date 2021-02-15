package com.medscape.android.task;

import android.os.AsyncTask;
import com.medscape.android.helper.FileHelper;
import java.io.File;
import java.io.IOException;

public class PurgeFolderTask extends AsyncTask<File, Double, Boolean> {
    private File mDestination;
    private File mFileToPurge;
    private PurgeListener mListener;

    public interface PurgeListener {
        void onPurgeComplete();
    }

    public PurgeFolderTask(File file) {
        this(file, (PurgeListener) null);
    }

    public PurgeFolderTask(File file, PurgeListener purgeListener) {
        this.mDestination = file;
        this.mListener = purgeListener;
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(File... fileArr) {
        boolean z = false;
        try {
            FileHelper.copyFiles(fileArr[0], this.mDestination);
            File file = fileArr[0];
            this.mFileToPurge = file;
            z = true;
            FileHelper.deleteFile(file.getAbsolutePath());
        } catch (IOException unused) {
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        PurgeListener purgeListener;
        if (bool.booleanValue() && (purgeListener = this.mListener) != null) {
            purgeListener.onPurgeComplete();
        }
    }
}
