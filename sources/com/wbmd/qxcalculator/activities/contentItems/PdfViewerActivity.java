package com.wbmd.qxcalculator.activities.contentItems;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.ReaderView;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.download.PdfDownloader;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import java.io.File;

public class PdfViewerActivity extends ContentItemActivity {
    public static final String KEY_BUNDLE_URL = "PdfViewerActivity.KEY_BUNDLE_URL";
    public static final String KEY_FILE_NAME = "PdfViewerActivity.KEY_FILE_NAME";
    /* access modifiers changed from: private */
    public ProgressBar downloadProgressBar;
    private View errorView;
    private String fileName;
    private FrameLayout frameLayout;
    private ReaderView mReader;
    private PdfDownloader pdfDownloader;
    private View pdfDownloadingView;
    /* access modifiers changed from: private */
    public File pdfFile;
    /* access modifiers changed from: private */
    public boolean progressBarDeterminateSet;
    private PdfType type;
    private String url;
    private ViewMode viewMode;

    private enum PdfType {
        LOCAL,
        REMOTE
    }

    private enum ViewMode {
        PDF_LOADING,
        PDF_READING,
        ERROR
    }

    /* access modifiers changed from: protected */
    public boolean requiresContentItem() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_pdf_viewer;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.contentItem != null) {
            String str = this.contentItem.fileSource.source;
            if (!str.toLowerCase().startsWith("http")) {
                str = FileHelper.getInstance().getResourceFolderPathForContentItemIdentifier(this.contentItem.identifier) + str;
            }
            setUrl(str);
        } else {
            setUrl(getIntent().getStringExtra(KEY_BUNDLE_URL));
            setTitle(getString(R.string.app_full_name));
        }
        if (bundle != null) {
            this.fileName = bundle.getString(KEY_FILE_NAME);
        }
        Log.d("API", "loaded filename from savedInstance " + this.fileName);
        if (this.url == null) {
            finish();
            return;
        }
        this.frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        ReaderView readerView = new ReaderView(this);
        this.mReader = readerView;
        readerView.getPlugPDFDisplay().setBackgroundColor(-1);
        this.frameLayout.addView(this.mReader);
        this.pdfDownloadingView = findViewById(R.id.pdf_download_view);
        this.downloadProgressBar = (ProgressBar) findViewById(R.id.downloading_progress_bar);
        this.errorView = findViewById(R.id.error_view);
        ((TextView) findViewById(R.id.error_title_text_view)).setText(R.string.error_downloading_pdf_text);
        ((TextView) findViewById(R.id.error_body_text_view)).setText(R.string.error_downloading_pdf_body);
        setViewMode(ViewMode.PDF_LOADING);
        loadPdf();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(KEY_FILE_NAME, this.fileName);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onResume() {
        super.onResume();
        AnalyticsHandler.getInstance().trackPageView(this, "PDF Viewer Page");
        if (this.contentItem == null && this.trackerPageName != null) {
            AnalyticsHandler.getInstance().trackPageView(this, this.trackerPageName);
        }
    }

    private void cancelDownload() {
        PdfDownloader pdfDownloader2 = this.pdfDownloader;
        if (pdfDownloader2 != null) {
            pdfDownloader2.downloaderListener = null;
            this.pdfDownloader.cancel();
            this.pdfDownloader = null;
        }
    }

    private void setUrl(String str) {
        this.url = str;
        if (str != null) {
            if (str.toLowerCase().contains("http")) {
                this.type = PdfType.REMOTE;
            } else {
                this.type = PdfType.LOCAL;
            }
        }
    }

    private void loadPdf() {
        File file;
        if (this.type == PdfType.LOCAL) {
            openPDF(this.url, "");
            return;
        }
        if (this.fileName != null) {
            file = new File(this.fileName);
        } else if (this.contentItem != null) {
            FileHelper instance = FileHelper.getInstance();
            file = instance.getTempFileWithName(this.contentItem.identifier + ".pdf");
            this.fileName = file.getAbsolutePath();
        } else {
            file = FileHelper.getInstance().createRandomFileForTempPdf();
            this.fileName = file.getAbsolutePath();
        }
        Log.d("API", "fileName is " + this.fileName);
        if (file.exists()) {
            Log.d("API", "file exists");
            openPDF(file.getAbsolutePath(), "");
            return;
        }
        PdfDownloader pdfDownloader2 = new PdfDownloader(this.url, file, (Context) this);
        this.pdfDownloader = pdfDownloader2;
        pdfDownloader2.downloaderListener = new PdfDownloader.PdfDownloaderListener() {
            public void onProgressChanged(long j, long j2) {
                Log.d("API", "bytes read " + j + "; contentLength " + j2);
                if (j2 > 0) {
                    if (!PdfViewerActivity.this.progressBarDeterminateSet) {
                        PdfViewerActivity.this.downloadProgressBar.setIndeterminate(false);
                        boolean unused = PdfViewerActivity.this.progressBarDeterminateSet = true;
                    }
                    PdfViewerActivity.this.downloadProgressBar.setProgress((int) ((((double) j) / ((double) j2)) * 100.0d));
                } else if (!PdfViewerActivity.this.progressBarDeterminateSet) {
                    PdfViewerActivity.this.downloadProgressBar.setIndeterminate(true);
                    boolean unused2 = PdfViewerActivity.this.progressBarDeterminateSet = true;
                }
            }

            public void onFinished(boolean z, File file) {
                if (!z || file == null) {
                    PdfViewerActivity.this.setViewMode(ViewMode.ERROR);
                    return;
                }
                Log.d("API", "downloaded to file: " + file.getAbsolutePath());
                File unused = PdfViewerActivity.this.pdfFile = file;
                PdfViewerActivity.this.openPDF(file.getAbsolutePath(), "");
            }
        };
        this.pdfDownloader.startDownload();
    }

    /* access modifiers changed from: private */
    public void openPDF(String str, String str2) {
        setViewMode(ViewMode.PDF_READING);
        this.mReader.openFile(str, str2);
        this.mReader.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.VERTICAL);
        this.mReader.setBackgroundColor(getResources().getColor(R.color.pdf_reader_background));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.contentItem != null) {
            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(R.menu.menu_content_item, menu);
        menu.findItem(R.id.action_more_info).setVisible(false);
        menu.findItem(R.id.action_fave).setVisible(false);
        return true;
    }

    /* access modifiers changed from: protected */
    public void shareButtonPressed() {
        if (this.pdfFile == null) {
            Toast.makeText(this, R.string.pdf_not_downloaded, 0).show();
            return;
        }
        Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".com.qxmd.calculate.provider", this.pdfFile);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        startActivity(Intent.createChooser(intent, getString(R.string.share_item)));
    }

    /* access modifiers changed from: private */
    public void setViewMode(ViewMode viewMode2) {
        if (this.viewMode != viewMode2) {
            this.viewMode = viewMode2;
            if (viewMode2 == ViewMode.PDF_LOADING) {
                this.mReader.setVisibility(8);
                this.pdfDownloadingView.setVisibility(0);
                this.errorView.setVisibility(8);
            } else if (viewMode2 == ViewMode.PDF_READING) {
                this.mReader.setVisibility(0);
                this.pdfDownloadingView.setVisibility(8);
                this.errorView.setVisibility(8);
            } else if (viewMode2 == ViewMode.ERROR) {
                this.mReader.setVisibility(8);
                this.pdfDownloadingView.setVisibility(8);
                this.errorView.setVisibility(0);
            }
        }
    }

    public void onDestroy() {
        unloadPdfReader();
        cancelDownload();
        super.onDestroy();
    }

    private void unloadPdfReader() {
        ReaderView readerView = this.mReader;
        if (readerView != null && readerView.getDocument() != null) {
            this.mReader.clear();
            this.frameLayout.removeView(this.mReader);
            this.mReader = null;
        }
    }
}
