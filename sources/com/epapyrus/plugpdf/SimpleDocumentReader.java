package com.epapyrus.plugpdf;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.epapyrus.plugpdf.core.CoordConverter;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.annotation.AnnotEventListener;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.DocumentState;
import com.epapyrus.plugpdf.core.viewer.PageViewListener;
import com.epapyrus.plugpdf.core.viewer.ReaderListener;
import com.epapyrus.plugpdf.core.viewer.ReaderView;
import java.io.FileInputStream;

public class SimpleDocumentReader implements ReaderListener {
    private Activity mAct;
    private SimpleReaderControlView mControlView;
    /* access modifiers changed from: private */
    public byte[] mFileData = null;
    /* access modifiers changed from: private */
    public String mFilePath = null;
    private SimpleDocumentReaderListener mListener;
    /* access modifiers changed from: private */
    public ReaderView mReaderView;

    public void onChangeZoom(double d) {
    }

    public void onDoubleTapUp(MotionEvent motionEvent) {
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public SimpleDocumentReader(Activity activity) {
        this.mAct = activity;
        ReaderView readerView = new ReaderView(this.mAct);
        this.mReaderView = readerView;
        readerView.setReaderListener(this);
        SimpleReaderControlView simpleReaderControlView = (SimpleReaderControlView) SimpleReaderControlView.inflate(this.mAct, R.layout.simple_reader_control, (ViewGroup) null);
        this.mControlView = simpleReaderControlView;
        simpleReaderControlView.createUILayout(this.mReaderView);
    }

    public void setListener(SimpleDocumentReaderListener simpleDocumentReaderListener) {
        this.mListener = simpleDocumentReaderListener;
    }

    public void setPageViewListener(PageViewListener pageViewListener) {
        this.mReaderView.setPageViewListener(pageViewListener);
    }

    public void setAnnotEventLisener(AnnotEventListener annotEventListener) {
        this.mReaderView.setAnnotEventListener(annotEventListener);
    }

    public void setAnnotToolEventListener(AnnotToolEventListener annotToolEventListener) {
        this.mReaderView.setAnnotToolListener(annotToolEventListener);
    }

    public ReaderView getReaderView() {
        return this.mReaderView;
    }

    public void openFile(String str, String str2) {
        this.mFilePath = str;
        this.mReaderView.openFile(str, str2);
    }

    public void openStream(FileInputStream fileInputStream, int i, String str) {
        this.mReaderView.openStream(fileInputStream, i, str);
    }

    public void openData(byte[] bArr, int i, String str) {
        this.mFileData = bArr;
        this.mReaderView.openData(bArr, i, str);
    }

    public void openJetStreamUrl(String str, int i, String str2, String str3) {
        this.mReaderView.openJetStreamUrl(str, i, str2, str3);
    }

    public void openJetStreamUrl(String str, int i, String str2, String str3, String str4) {
        this.mReaderView.openJetStreamUrl(str, i, str2, str3, str4);
    }

    public void openUrl(String str, String str2) {
        this.mReaderView.openUrl(str, str2);
    }

    public void restoreReaderState(Object obj) {
        this.mReaderView.restoreSavedState(obj);
    }

    public Object getReaderState() {
        return this.mReaderView.getState();
    }

    public void saveControlState(Bundle bundle) {
        this.mControlView.saveState(bundle);
    }

    public void restoreControlState(Bundle bundle) {
        this.mControlView.restoreState(bundle);
    }

    public void setTitle(String str) {
        this.mControlView.setTitle(str);
    }

    public PDFDocument getDocument() {
        return this.mReaderView.getDocument();
    }

    public void goToPage(int i) {
        this.mReaderView.goToPage(i);
    }

    public int getPageIdx() {
        return this.mReaderView.getPageIdx();
    }

    public void search(String str, int i) {
        this.mReaderView.search(str, i);
    }

    public void stopSearch() {
        this.mReaderView.stopSearch();
    }

    public void refreshLayout() {
        ReaderView readerView = this.mReaderView;
        if (readerView != null) {
            readerView.refreshLayout();
        }
        SimpleReaderControlView simpleReaderControlView = this.mControlView;
        if (simpleReaderControlView != null) {
            simpleReaderControlView.refreshLayout();
        }
    }

    public void enableAnnotationMenu(boolean z) {
        this.mControlView.showEditButton(z);
    }

    public void enableAnnotationFeature(String str, boolean z) {
        this.mControlView.enableAnnotFeature(str, z);
        this.mReaderView.enableAnnotFeature(str, z);
    }

    /* renamed from: com.epapyrus.plugpdf.SimpleDocumentReader$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode[] r0 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode = r0
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.VERTICAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.CONTINUOS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.BILATERAL_VERTICAL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.BILATERAL_HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.BILATERAL_REALISTIC     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.REALISTIC     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.SimpleDocumentReader.AnonymousClass2.<clinit>():void");
        }
    }

    public void setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode pageDisplayMode) {
        switch (AnonymousClass2.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode[pageDisplayMode.ordinal()]) {
            case 1:
                this.mControlView.setHorizontalMode();
                return;
            case 2:
                this.mControlView.setVerticalMode();
                return;
            case 3:
                this.mControlView.setContinuosMode();
                return;
            case 4:
                this.mControlView.setBilateralVerticalMode();
                return;
            case 5:
                this.mControlView.setBilateralHorizontalMode();
                return;
            case 6:
                this.mControlView.setBilateralRealisticMode();
                return;
            case 7:
                this.mControlView.setThumbnailMode();
                return;
            case 8:
                this.mControlView.setRealisticMode();
                return;
            default:
                return;
        }
    }

    public void setDoubleTapZoomLevel(double d) {
        PropertyManager.setDoubleTapZoomLevel(d);
    }

    public void flattenFormFields(boolean z) {
        this.mReaderView.flattenFromFields(z);
    }

    public void flattenAnnots() {
        this.mReaderView.flattenAnnots();
    }

    public void save() {
        this.mReaderView.save();
    }

    public String saveAsFile(String str) {
        return this.mReaderView.saveAsFile(str);
    }

    public void onLoadFinish(DocumentState.OPEN open) {
        if (open == DocumentState.OPEN.SUCCESS) {
            this.mControlView.init(this.mAct);
            RelativeLayout relativeLayout = new RelativeLayout(this.mAct);
            relativeLayout.addView(this.mReaderView);
            relativeLayout.addView(this.mControlView);
            this.mAct.setContentView(relativeLayout);
            CoordConverter.initCoordConverter(this.mAct, this.mReaderView);
            goToPage(0);
        } else if (open == DocumentState.OPEN.WRONG_PASSWD) {
            new PasswordDialog(this.mAct) {
                public void onInputtedPassword(String str) {
                    if (SimpleDocumentReader.this.mFilePath != null) {
                        SimpleDocumentReader.this.mReaderView.openFile(SimpleDocumentReader.this.mFilePath, str);
                    } else if (SimpleDocumentReader.this.mFileData != null) {
                        SimpleDocumentReader.this.mReaderView.openData(SimpleDocumentReader.this.mFileData, SimpleDocumentReader.this.mFileData.length, str);
                    }
                }
            }.show();
        }
        SimpleDocumentReaderListener simpleDocumentReaderListener = this.mListener;
        if (simpleDocumentReaderListener != null) {
            simpleDocumentReaderListener.onLoadFinish(open);
        }
    }

    public void onSearchFinish(boolean z) {
        if (z && this.mReaderView.getPageDisplayMode() == BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL) {
            onChangeDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL, this.mReaderView.getPageIdx());
        }
    }

    public void onGoToPage(int i, int i2) {
        this.mControlView.updatePageNumber(i, i2);
    }

    public void onSingleTapUp(MotionEvent motionEvent) {
        this.mControlView.toggleControlTabBar();
    }

    public void onScroll(int i, int i2) {
        this.mControlView.hideTopMenu();
    }

    public void onChangeDisplayMode(BasePlugPDFDisplay.PageDisplayMode pageDisplayMode, int i) {
        if (pageDisplayMode == BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL) {
            this.mControlView.setHorizontalMode();
        } else if (pageDisplayMode == BasePlugPDFDisplay.PageDisplayMode.VERTICAL) {
            this.mControlView.setVerticalMode();
        } else if (pageDisplayMode == BasePlugPDFDisplay.PageDisplayMode.CONTINUOS) {
            this.mControlView.setContinuosMode();
        } else if (pageDisplayMode == BasePlugPDFDisplay.PageDisplayMode.BILATERAL_VERTICAL) {
            this.mControlView.setBilateralVerticalMode();
        } else if (pageDisplayMode == BasePlugPDFDisplay.PageDisplayMode.BILATERAL_HORIZONTAL) {
            this.mControlView.setBilateralHorizontalMode();
        } else if (pageDisplayMode == BasePlugPDFDisplay.PageDisplayMode.BILATERAL_REALISTIC) {
            this.mControlView.setBilateralRealisticMode();
        } else if (pageDisplayMode == BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL) {
            this.mControlView.setThumbnailMode();
        }
    }

    public boolean setEncrypt(String str, String str2, int i) {
        return this.mReaderView.setEncrypt(str, str2, i);
    }

    public String getPageText(int i) {
        return this.mReaderView.getPageText(i);
    }

    public void clear() {
        this.mReaderView.clear();
        this.mFileData = null;
        this.mFilePath = null;
    }
}
