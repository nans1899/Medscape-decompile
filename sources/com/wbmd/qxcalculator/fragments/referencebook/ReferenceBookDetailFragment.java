package com.wbmd.qxcalculator.fragments.referencebook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.ReaderView;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity;
import com.wbmd.qxcalculator.activities.contentItems.ReferenceBookDetailActivity;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem;
import com.wbmd.qxcalculator.model.download.PdfDownloader;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import java.io.File;
import java.util.List;

public class ReferenceBookDetailFragment extends Fragment implements View.OnTouchListener {
    private static final String KEY_ARG_CONTENT_ITEM = "ReferenceBookDetailFragment.KEY_ARG_CONTENT_ITEM";
    private static final String KEY_ARG_SECTION_ITEM = "ReferenceBookDetailFragment.KEY_ARG_SECTION_ITEM";
    private static final String KEY_BUNDLE_SECTION_ITEM = "ReferenceBookDetailFragment.KEY_BUNDLE_SECTION_ITEM";
    private static final String PDF_PREFIX = "ref_book_section_item_";
    private ContentItem contentItem;
    /* access modifiers changed from: private */
    public boolean didJustTap;
    /* access modifiers changed from: private */
    public ProgressBar downloadProgressBar;
    private View errorView;
    private FrameLayout frameLayout;
    private ReferenceBookDetailListener mListener;
    private ReaderView mReader;
    private View nextButton;
    private PdfDownloader pdfDownloader;
    private View pdfDownloadingView;
    private View prevButton;
    /* access modifiers changed from: private */
    public boolean progressBarDeterminateSet;
    /* access modifiers changed from: private */
    public ReferenceBookSectionItem sectionItem;
    private Handler touchUpHandler;
    private TouchUpRunnable touchUpRunnable;
    private SectionItemType type;
    private ViewMode viewMode;
    private WebView webView;

    public interface ReferenceBookDetailListener {
        void didDisplayItem(ReferenceBookSectionItem referenceBookSectionItem);
    }

    private enum SectionItemType {
        HTML_LOCAL,
        HTML_REMOTE,
        PDF_LOCAL,
        PDF_REMOTE
    }

    private enum ViewMode {
        HTML,
        PDF_LOADING,
        PDF_READING,
        ERROR
    }

    private class TouchUpRunnable implements Runnable {
        private TouchUpRunnable() {
        }

        public void run() {
            Log.d("API", "reset tap");
            boolean unused = ReferenceBookDetailFragment.this.didJustTap = false;
        }
    }

    public static ReferenceBookDetailFragment newInstance(ContentItem contentItem2, ReferenceBookSectionItem referenceBookSectionItem) {
        ReferenceBookDetailFragment referenceBookDetailFragment = new ReferenceBookDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ARG_CONTENT_ITEM, contentItem2);
        bundle.putParcelable(KEY_ARG_SECTION_ITEM, referenceBookSectionItem);
        referenceBookDetailFragment.setArguments(bundle);
        return referenceBookDetailFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (ReferenceBookDetailListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement ReferenceBookDetailListener");
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.contentItem = (ContentItem) getArguments().getParcelable(KEY_ARG_CONTENT_ITEM);
            setSectionItem((ReferenceBookSectionItem) getArguments().getParcelable(KEY_ARG_SECTION_ITEM));
        }
        if (bundle != null) {
            setSectionItem((ReferenceBookSectionItem) bundle.getParcelable(KEY_BUNDLE_SECTION_ITEM));
        }
        this.touchUpHandler = new Handler();
        this.touchUpRunnable = new TouchUpRunnable();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_reference_book_detail, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.button_prev);
        this.prevButton = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ReferenceBookDetailFragment referenceBookDetailFragment = ReferenceBookDetailFragment.this;
                referenceBookDetailFragment.setSectionItem(referenceBookDetailFragment.getPreviousSectionItem(referenceBookDetailFragment.sectionItem));
                ReferenceBookDetailFragment referenceBookDetailFragment2 = ReferenceBookDetailFragment.this;
                referenceBookDetailFragment2.loadSectionItem(referenceBookDetailFragment2.sectionItem, (Bundle) null);
                ReferenceBookDetailFragment.this.updateButtonStates();
            }
        });
        View findViewById2 = inflate.findViewById(R.id.button_next);
        this.nextButton = findViewById2;
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ReferenceBookDetailFragment referenceBookDetailFragment = ReferenceBookDetailFragment.this;
                referenceBookDetailFragment.setSectionItem(referenceBookDetailFragment.getNextSectionItem(referenceBookDetailFragment.sectionItem));
                ReferenceBookDetailFragment referenceBookDetailFragment2 = ReferenceBookDetailFragment.this;
                referenceBookDetailFragment2.loadSectionItem(referenceBookDetailFragment2.sectionItem, (Bundle) null);
                ReferenceBookDetailFragment.this.updateButtonStates();
            }
        });
        this.frameLayout = (FrameLayout) inflate.findViewById(R.id.frame_layout);
        WebView webView2 = (WebView) inflate.findViewById(R.id.webview);
        this.webView = webView2;
        webView2.clearCache(false);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (ReferenceBookDetailFragment.this.getActivity() != null) {
                    if (i < 15) {
                        ((ReferenceBookDetailActivity) ReferenceBookDetailFragment.this.getActivity()).setActionBarProgressBarProgress(15);
                    } else {
                        ((ReferenceBookDetailActivity) ReferenceBookDetailFragment.this.getActivity()).setActionBarProgressBarProgress(i);
                    }
                    if (i == 100) {
                        ((ReferenceBookDetailActivity) ReferenceBookDetailFragment.this.getActivity()).showActionBarProgressBar(false);
                    } else {
                        ((ReferenceBookDetailActivity) ReferenceBookDetailFragment.this.getActivity()).showActionBarProgressBar(true);
                    }
                }
            }
        });
        this.webView.setOnTouchListener(this);
        this.webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                Log.d("WEBVIEW", "onPageSTarted " + str);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                Log.d("WEBVIEW", "onPageFinished " + str);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Log.d("WEBVIEW", "shouldOverrideUrl " + str + "; didJustTap " + ReferenceBookDetailFragment.this.didJustTap);
                if (!ReferenceBookDetailFragment.this.didJustTap) {
                    return false;
                }
                boolean unused = ReferenceBookDetailFragment.this.didJustTap = false;
                ReferenceBookDetailFragment.this.launchExternalHtmlViewer(str);
                return true;
            }
        });
        this.pdfDownloadingView = inflate.findViewById(R.id.pdf_download_view);
        this.downloadProgressBar = (ProgressBar) inflate.findViewById(R.id.downloading_progress_bar);
        this.errorView = inflate.findViewById(R.id.error_view);
        ((TextView) inflate.findViewById(R.id.error_title_text_view)).setText(R.string.error_downloading_pdf_text);
        ((TextView) inflate.findViewById(R.id.error_body_text_view)).setText(R.string.error_downloading_pdf_body);
        loadSectionItem(this.sectionItem, bundle);
        return inflate;
    }

    /* access modifiers changed from: private */
    public void launchExternalHtmlViewer(String str) {
        Log.d("API", "launch ext html viewer " + str);
        Intent intent = new Intent(getActivity(), FileSourceHtmlActivity.class);
        intent.putExtra(FileSourceHtmlActivity.KEY_INTENT_URL, str);
        startActivity(intent);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        Log.d("API", "touch up");
        this.didJustTap = true;
        this.touchUpHandler.removeCallbacks(this.touchUpRunnable);
        this.touchUpHandler.postDelayed(this.touchUpRunnable, 500);
        return false;
    }

    /* access modifiers changed from: private */
    public void setSectionItem(ReferenceBookSectionItem referenceBookSectionItem) {
        this.sectionItem = referenceBookSectionItem;
        if (referenceBookSectionItem.source.toLowerCase().contains("http")) {
            if (referenceBookSectionItem.source.toLowerCase().contains(".pdf")) {
                this.type = SectionItemType.PDF_REMOTE;
            } else {
                this.type = SectionItemType.HTML_REMOTE;
            }
        } else if (referenceBookSectionItem.source.toLowerCase().contains(".pdf")) {
            this.type = SectionItemType.PDF_LOCAL;
        } else {
            this.type = SectionItemType.HTML_LOCAL;
        }
    }

    public void onDestroy() {
        unloadPdfReader();
        cancelDownload();
        super.onDestroy();
    }

    private void cancelDownload() {
        PdfDownloader pdfDownloader2 = this.pdfDownloader;
        if (pdfDownloader2 != null) {
            pdfDownloader2.downloaderListener = null;
            this.pdfDownloader.cancel();
            this.pdfDownloader = null;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.type == SectionItemType.HTML_LOCAL || this.type == SectionItemType.HTML_REMOTE) {
            this.webView.saveState(bundle);
        }
        bundle.putParcelable(KEY_BUNDLE_SECTION_ITEM, this.sectionItem);
    }

    public void onResume() {
        super.onResume();
        this.webView.onResume();
        updateButtonStates();
    }

    public void onPause() {
        super.onPause();
        this.webView.onPause();
    }

    private void loadPdf(ReferenceBookSectionItem referenceBookSectionItem) {
        if (this.type == SectionItemType.PDF_LOCAL) {
            openPDF(FileHelper.getInstance().getResourceFolderPathForContentItemIdentifier(this.contentItem.referenceBook.identifier) + referenceBookSectionItem.source, "");
            return;
        }
        FileHelper instance = FileHelper.getInstance();
        File tempFileWithName = instance.getTempFileWithName(PDF_PREFIX + referenceBookSectionItem.identifier + ".pdf");
        if (tempFileWithName.exists()) {
            openPDF(tempFileWithName.getAbsolutePath(), "");
            return;
        }
        String str = referenceBookSectionItem.source;
        PdfDownloader pdfDownloader2 = new PdfDownloader(str, PDF_PREFIX + referenceBookSectionItem.identifier, (Context) getActivity());
        this.pdfDownloader = pdfDownloader2;
        pdfDownloader2.downloaderListener = new PdfDownloader.PdfDownloaderListener() {
            public void onProgressChanged(long j, long j2) {
                Log.d("API", "bytes read " + j + "; contentLength " + j2);
                if (j2 > 0) {
                    if (!ReferenceBookDetailFragment.this.progressBarDeterminateSet) {
                        ReferenceBookDetailFragment.this.downloadProgressBar.setIndeterminate(false);
                        boolean unused = ReferenceBookDetailFragment.this.progressBarDeterminateSet = true;
                    }
                    ReferenceBookDetailFragment.this.downloadProgressBar.setProgress((int) ((((double) j) / ((double) j2)) * 100.0d));
                } else if (!ReferenceBookDetailFragment.this.progressBarDeterminateSet) {
                    ReferenceBookDetailFragment.this.downloadProgressBar.setIndeterminate(true);
                    boolean unused2 = ReferenceBookDetailFragment.this.progressBarDeterminateSet = true;
                }
            }

            public void onFinished(boolean z, File file) {
                if (!z || file == null) {
                    ReferenceBookDetailFragment.this.setViewMode(ViewMode.ERROR);
                    return;
                }
                Log.d("API", "downloaded to file: " + file.getAbsolutePath());
                ReferenceBookDetailFragment.this.openPDF(file.getAbsolutePath(), "");
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

    private void loadHtml(ReferenceBookSectionItem referenceBookSectionItem) {
        if (this.type == SectionItemType.HTML_LOCAL) {
            WebView webView2 = this.webView;
            webView2.loadUrl("file://" + FileHelper.getInstance().getResourceFolderPathForContentItem(this.contentItem) + referenceBookSectionItem.source);
            return;
        }
        this.webView.loadUrl(referenceBookSectionItem.source);
    }

    /* access modifiers changed from: private */
    public void loadSectionItem(ReferenceBookSectionItem referenceBookSectionItem, Bundle bundle) {
        this.mListener.didDisplayItem(referenceBookSectionItem);
        cancelDownload();
        if (!this.contentItem.trackerId.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(Html.fromHtml(referenceBookSectionItem.title).toString());
            ReferenceBookSectionItem referenceBookSectionItem2 = referenceBookSectionItem;
            while (referenceBookSectionItem2.parentItem != null) {
                referenceBookSectionItem2 = referenceBookSectionItem2.parentItem;
                sb.insert(0, Html.fromHtml(referenceBookSectionItem2.title).toString() + " > ");
            }
            AnalyticsHandler instance = AnalyticsHandler.getInstance();
            FragmentActivity activity = getActivity();
            instance.trackPageView(activity, this.contentItem.name + " Page View - " + sb.toString());
        }
        if (this.contentItem.promotionToUse != null) {
            EventsManager instance2 = EventsManager.getInstance();
            ContentItem contentItem2 = this.contentItem;
            instance2.trackReferenceBookSectionViewed(contentItem2, referenceBookSectionItem, contentItem2.promotionToUse);
        }
        this.downloadProgressBar.setProgress(0);
        this.downloadProgressBar.setIndeterminate(true);
        this.progressBarDeterminateSet = false;
        if (this.type == SectionItemType.HTML_LOCAL || this.type == SectionItemType.HTML_REMOTE) {
            setViewMode(ViewMode.HTML);
            if (bundle == null) {
                loadHtml(referenceBookSectionItem);
                return;
            }
            Log.d("API", "restore webview state");
            this.webView.restoreState(bundle);
            return;
        }
        setViewMode(ViewMode.PDF_LOADING);
        loadPdf(referenceBookSectionItem);
    }

    /* access modifiers changed from: private */
    public void setViewMode(ViewMode viewMode2) {
        Log.d("API", "this viewMode " + this.viewMode + "; new viewMode " + viewMode2);
        ViewMode viewMode3 = this.viewMode;
        if (viewMode3 != viewMode2) {
            this.viewMode = viewMode2;
            if (viewMode2 == ViewMode.HTML) {
                if (this.mReader != null) {
                    unloadPdfReader();
                }
                this.webView.setVisibility(0);
                this.pdfDownloadingView.setVisibility(8);
                this.errorView.setVisibility(8);
            } else if (viewMode2 == ViewMode.PDF_LOADING) {
                if (viewMode3 == ViewMode.HTML) {
                    this.webView.loadUrl("about:blank");
                }
                this.webView.setVisibility(8);
                ReaderView readerView = this.mReader;
                if (readerView != null) {
                    readerView.setVisibility(8);
                }
                this.pdfDownloadingView.setVisibility(0);
                this.errorView.setVisibility(8);
            } else if (viewMode2 == ViewMode.PDF_READING) {
                if (viewMode3 == ViewMode.HTML) {
                    this.webView.loadUrl("about:blank");
                }
                if (this.mReader == null) {
                    ReaderView readerView2 = new ReaderView(getActivity());
                    this.mReader = readerView2;
                    this.frameLayout.addView(readerView2);
                }
                this.webView.setVisibility(8);
                this.mReader.setVisibility(0);
                this.pdfDownloadingView.setVisibility(8);
                this.errorView.setVisibility(8);
            } else if (viewMode2 == ViewMode.ERROR) {
                if (viewMode3 == ViewMode.HTML) {
                    this.webView.loadUrl("about:blank");
                }
                this.webView.setVisibility(8);
                ReaderView readerView3 = this.mReader;
                if (readerView3 != null) {
                    readerView3.setVisibility(8);
                }
                this.pdfDownloadingView.setVisibility(8);
                this.errorView.setVisibility(0);
            }
        }
    }

    private void unloadPdfReader() {
        FrameLayout frameLayout2;
        ReaderView readerView = this.mReader;
        if (readerView != null) {
            if (!(readerView.getParent() == null || (frameLayout2 = this.frameLayout) == null)) {
                frameLayout2.removeView(this.mReader);
            }
            if (this.mReader.getDocument() != null) {
                this.mReader.clear();
                this.mReader = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public ReferenceBookSectionItem getPreviousSectionItem(ReferenceBookSectionItem referenceBookSectionItem) {
        List<ReferenceBookSectionItem> list = this.contentItem.referenceBook.flatSectionItems;
        int indexOf = list.indexOf(referenceBookSectionItem);
        if (indexOf == 0) {
            return null;
        }
        return list.get(indexOf - 1);
    }

    /* access modifiers changed from: private */
    public ReferenceBookSectionItem getNextSectionItem(ReferenceBookSectionItem referenceBookSectionItem) {
        List<ReferenceBookSectionItem> list = this.contentItem.referenceBook.flatSectionItems;
        int indexOf = list.indexOf(referenceBookSectionItem);
        if (indexOf < list.size() - 1) {
            return list.get(indexOf + 1);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void updateButtonStates() {
        boolean z = true;
        int i = 0;
        boolean z2 = getPreviousSectionItem(this.sectionItem) != null;
        if (getNextSectionItem(this.sectionItem) == null) {
            z = false;
        }
        this.prevButton.setVisibility(z2 ? 0 : 8);
        View view = this.nextButton;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
    }
}
