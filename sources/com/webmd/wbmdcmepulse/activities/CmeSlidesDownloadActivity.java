package com.webmd.wbmdcmepulse.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.FileDownloader;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import java.io.IOException;

public class CmeSlidesDownloadActivity extends CmeBaseActivity {
    /* access modifiers changed from: private */
    public static final String TAG = CmeSlidesDownloadActivity.class.getSimpleName();
    private final String[] STORAGE = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    private final int WRITE_EXTERNAL_STORAGE = 17;
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public Context mContext;
    private DownloadFilesTask mDownloadFilesTask;
    /* access modifiers changed from: private */
    public CustomFontTextView mDownloadStatusTextView;
    private String mDownloadUrl;
    /* access modifiers changed from: private */
    public FileDownloader mFileDownloader;
    /* access modifiers changed from: private */
    public View mNoNetworkView;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_slides_download);
        setUpDefaultActionBar("Downloading Slides", true);
        this.mActivity = this;
        this.mContext = this;
        this.mFileDownloader = new FileDownloader();
        this.mDownloadUrl = getIntent().getStringExtra(Constants.BUNDLE_KEY_DOWNLOAD_URL);
        View findViewById = findViewById(R.id.noNetworkView);
        this.mNoNetworkView = findViewById;
        ((Button) findViewById.findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Utilities.isNetworkAvailable(CmeSlidesDownloadActivity.this)) {
                    CmeSlidesDownloadActivity.this.mDownloadStatusTextView.setVisibility(0);
                    CmeSlidesDownloadActivity.this.mProgressBar.setVisibility(0);
                    CmeSlidesDownloadActivity.this.mNoNetworkView.setVisibility(8);
                    if (CmeSlidesDownloadActivity.this.verifyStoragePermissions()) {
                        CmeSlidesDownloadActivity.this.downloadSlides();
                    }
                }
            }
        });
        this.mDownloadStatusTextView = (CustomFontTextView) findViewById(R.id.download_status_text_view);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        if (Extensions.isStringNullOrEmpty(this.mDownloadUrl)) {
            this.mDownloadStatusTextView.setText("No slides available for this activity");
            this.mProgressBar.setVisibility(8);
        }
        if (!Utilities.isNetworkAvailable(this)) {
            this.mDownloadStatusTextView.setVisibility(8);
            this.mProgressBar.setVisibility(8);
            this.mNoNetworkView.setVisibility(0);
        } else if (verifyStoragePermissions()) {
            downloadSlides();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 17) {
            if (iArr.length > 0 && iArr[0] == 0) {
                downloadSlides();
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, this.STORAGE[0])) {
                this.mDownloadStatusTextView.setText("Cannot download slides, file storage permission has been denied for this application");
                this.mProgressBar.setVisibility(8);
                final View findViewById = findViewById(R.id.retry_button);
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        findViewById.setVisibility(8);
                        CmeSlidesDownloadActivity.this.mProgressBar.setVisibility(0);
                        if (CmeSlidesDownloadActivity.this.verifyStoragePermissions()) {
                            CmeSlidesDownloadActivity.this.downloadSlides();
                        }
                    }
                });
            } else {
                this.mDownloadStatusTextView.setText("You must grant the application permission to access file storage");
                this.mProgressBar.setVisibility(8);
                final View findViewById2 = findViewById(R.id.retry_button);
                findViewById2.setVisibility(0);
                findViewById2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        findViewById2.setVisibility(8);
                        CmeSlidesDownloadActivity.this.mProgressBar.setVisibility(0);
                        if (CmeSlidesDownloadActivity.this.verifyStoragePermissions()) {
                            CmeSlidesDownloadActivity.this.downloadSlides();
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean verifyStoragePermissions() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, this.STORAGE, 17);
        return false;
    }

    /* access modifiers changed from: private */
    public void downloadSlides() {
        DownloadFilesTask downloadFilesTask = new DownloadFilesTask(new ICallbackEvent() {
            public void onError(final Object obj) {
                new Thread() {
                    public void run() {
                        CmeSlidesDownloadActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CmeSlidesDownloadActivity.this.mDownloadStatusTextView.setText(obj.toString());
                                CmeSlidesDownloadActivity.this.mProgressBar.setVisibility(8);
                            }
                        });
                    }
                }.start();
            }

            public void onCompleted(final Object obj) {
                new Thread() {
                    public void run() {
                        CmeSlidesDownloadActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CmeSlidesDownloadActivity.this.mDownloadStatusTextView.setText(obj.toString());
                                CmeSlidesDownloadActivity.this.mProgressBar.setVisibility(8);
                            }
                        });
                    }
                }.start();
            }
        });
        this.mDownloadFilesTask = downloadFilesTask;
        downloadFilesTask.execute(new String[]{this.mDownloadUrl});
    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {
        private String fileName;
        private ICallbackEvent mCallBackEvent;

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Integer... numArr) {
        }

        public DownloadFilesTask(ICallbackEvent iCallbackEvent) {
            this.mCallBackEvent = iCallbackEvent;
        }

        /* access modifiers changed from: protected */
        public Long doInBackground(String... strArr) {
            String str = strArr[0];
            String replace = str.replace("http:", "");
            this.fileName = replace;
            String replace2 = replace.replace("/", "");
            this.fileName = replace2;
            String replace3 = replace2.replace(".", "_");
            this.fileName = replace3;
            this.fileName = replace3.replace("_pptx", ".pptx");
            try {
                if (!CmeSlidesDownloadActivity.this.mFileDownloader.fileExists(this.fileName)) {
                    CmeSlidesDownloadActivity.this.mFileDownloader.downloadFile(str, this.fileName);
                } else {
                    this.mCallBackEvent.onCompleted("File Already Exists");
                }
            } catch (IOException e) {
                Trace.e(CmeSlidesDownloadActivity.TAG, e.getMessage());
                this.mCallBackEvent.onError("Could not download file.");
            }
            return 1L;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Long l) {
            try {
                if (!CmeSlidesDownloadActivity.this.mActivity.isFinishing()) {
                    Uri fromFile = Uri.fromFile(CmeSlidesDownloadActivity.this.mFileDownloader.getDownloadedFile(this.fileName));
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setDataAndType(fromFile, "application/vnd.ms-powerpoint");
                    intent.addFlags(268435456);
                    if (CmeSlidesDownloadActivity.this.mContext.getPackageManager().queryIntentActivities(intent, 131072).size() > 0) {
                        CmeSlidesDownloadActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(CmeSlidesDownloadActivity.this.mContext, "Please download a program that will support this file type.", 0).show();
                    }
                    this.mCallBackEvent.onCompleted("Download Complete");
                }
            } catch (Exception e) {
                Trace.e(CmeSlidesDownloadActivity.TAG, e.getMessage());
            }
        }
    }
}
