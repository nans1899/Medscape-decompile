package com.medscape.android.consult.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.medscape.android.consult.interfaces.IFileCreatedListener;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.FileOutputStream;

public class CreateFileForBitMapTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = CreateFileForBitMapTask.class.getSimpleName();
    private Bitmap mBitmap;
    private Context mContext;
    private IFileCreatedListener mFileCreatedListener;

    public CreateFileForBitMapTask(Context context, Bitmap bitmap, IFileCreatedListener iFileCreatedListener) {
        this.mContext = context;
        this.mFileCreatedListener = iFileCreatedListener;
        this.mBitmap = bitmap;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Void... voidArr) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return "bitmap.png";
        }
        try {
            FileOutputStream openFileOutput = this.mContext.openFileOutput("bitmap.png", 0);
            this.mBitmap.compress(Bitmap.CompressFormat.PNG, 100, openFileOutput);
            openFileOutput.close();
            return "bitmap.png";
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to create file for bitmap");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        IFileCreatedListener iFileCreatedListener = this.mFileCreatedListener;
        if (iFileCreatedListener != null) {
            iFileCreatedListener.onFileCreated(str);
        }
    }
}
