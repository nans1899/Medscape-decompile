package com.medscape.android.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.medscape.android.R;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.interfaces.ICallbackEvent;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AndroidPdfViewerActivity extends NavigableBaseActivity {
    /* access modifiers changed from: private */
    public MedscapeException mException;
    /* access modifiers changed from: private */
    public String mPdfUrl;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public View mRootView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_android_pdf_viewer);
        String stringExtra = getIntent().getStringExtra("pdf_url");
        this.mPdfUrl = stringExtra;
        if (StringUtil.isNotEmpty(stringExtra) && this.mPdfUrl.startsWith("http://")) {
            this.mPdfUrl = this.mPdfUrl.replace("http://", "https://");
        }
        this.mRootView = findViewById(R.id.root_view);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        setUpViews();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        MedscapeException medscapeException = this.mException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
            this.mException = null;
        }
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void setUpViews() {
        this.mProgressBar.setVisibility(0);
        new DownloadFileFromUrl(new ICallbackEvent<InputStream, Exception>() {
            public void onError(Exception exc) {
                AndroidPdfViewerActivity.this.showPDFLoadException();
            }

            public void onCompleted(InputStream inputStream) {
                if (inputStream != null) {
                    ((PDFView) AndroidPdfViewerActivity.this.findViewById(R.id.pdf_view)).fromStream(inputStream).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).defaultPage(0).onError(new OnErrorListener() {
                        public void onError(Throwable th) {
                            AndroidPdfViewerActivity.this.showPDFLoadException();
                        }
                    }).onLoad(new OnLoadCompleteListener() {
                        public void loadComplete(int i) {
                            AndroidPdfViewerActivity.this.mProgressBar.setVisibility(8);
                        }
                    }).load();
                } else {
                    AndroidPdfViewerActivity.this.showPDFLoadException();
                }
            }
        }).execute(new String[]{this.mPdfUrl});
    }

    class DownloadFileFromUrl extends AsyncTask<String, String, String> {
        private ICallbackEvent<InputStream, Exception> mCallback;
        private InputStream mStream;

        DownloadFileFromUrl(ICallbackEvent<InputStream, Exception> iCallbackEvent) {
            this.mCallback = iCallbackEvent;
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            try {
                this.mStream = new URL(AndroidPdfViewerActivity.this.mPdfUrl).openStream();
                return null;
            } catch (IOException e) {
                this.mCallback.onError(e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            this.mCallback.onCompleted(this.mStream);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public void showPDFLoadException() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (AndroidPdfViewerActivity.this.mProgressBar != null) {
                    AndroidPdfViewerActivity.this.mProgressBar.setVisibility(8);
                }
                MedscapeException unused = AndroidPdfViewerActivity.this.mException = new MedscapeException("Could not download PDF");
                AndroidPdfViewerActivity.this.mException.showSnackBar(AndroidPdfViewerActivity.this.mRootView, -2, AndroidPdfViewerActivity.this.getResources().getString(R.string.retry), new View.OnClickListener() {
                    public void onClick(View view) {
                        AndroidPdfViewerActivity.this.setUpViews();
                    }
                });
            }
        });
    }
}
