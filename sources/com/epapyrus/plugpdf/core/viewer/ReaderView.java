package com.epapyrus.plugpdf.core.viewer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import com.epapyrus.plugpdf.core.BaseReaderControl;
import com.epapyrus.plugpdf.core.LicenseInfo;
import com.epapyrus.plugpdf.core.OutlineItem;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.PlugPDF;
import com.epapyrus.plugpdf.core.PlugPDFException;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.Register;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.SearchInfo;
import com.epapyrus.plugpdf.core.annotation.AnnotEventListener;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.acroform.BaseField;
import com.epapyrus.plugpdf.core.annotation.acroform.ButtonField;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldCreator;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldEventListener;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldProperty;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldRule;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolSelect;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.DocumentState;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class ReaderView extends ViewGroup implements BaseReaderControl {
    private static boolean mEnableUseRecentPage = false;
    private boolean isBilateralMode = false;
    private AnnotEventListener mAnnotEventListener;
    private AnnotToolEventListener mAnnotToolEventListener;
    /* access modifiers changed from: private */
    public Context mCtx = null;
    /* access modifiers changed from: private */
    public BasePlugPDFDisplay mDisplay = null;
    private BasePlugPDFDisplay.DisplayEventListener mDisplayListener = new BasePlugPDFDisplay.DisplayEventListener() {
        public void onPageSelect(int i) {
            if (ReaderView.this.mReaderViewListener == null) {
                return;
            }
            if (PropertyManager.getPageDisplayModeAfterThumbnail() == null) {
                ReaderView.this.mReaderViewListener.onChangeDisplayMode(ReaderView.this.mLastPageDisplayMode, i);
            } else {
                ReaderView.this.mReaderViewListener.onChangeDisplayMode(PropertyManager.getPageDisplayModeAfterThumbnail(), i);
            }
        }

        public void allTaskCompleted() {
            ReaderView.this.loadFinish(DocumentState.OPEN.ALL_TASK_COMPLETED);
        }
    };
    /* access modifiers changed from: private */
    public PDFDocument mDoc = null;
    private FieldEventListener mFieldEventListener;
    private BasePlugPDFDisplay.FitType mFitType = BasePlugPDFDisplay.FitType.WIDTH;
    /* access modifiers changed from: private */
    public BasePlugPDFDisplay.PageDisplayMode mLastPageDisplayMode;
    private int mLastSearchPageIdx;
    private View.OnLongClickListener mOnLongClickListener;
    private AsyncTask<Void, Void, byte[]> mOpenUrlTask;
    private PageViewListener mPageViewListener;
    /* access modifiers changed from: private */
    public ReaderListener mReaderViewListener;
    /* access modifiers changed from: private */
    public AlertDialog.Builder mSearchFinishAlertBuilder = null;
    private ProgressDialog mSearchProgressDialog = null;
    private AsyncTask<Integer, Integer, SearchInfo> mSearchTask;
    /* access modifiers changed from: private */
    public String mSearchText;
    private AnnotToolSelect.TextSelectListener mTextSelectListener;

    public ReaderView(Context context) {
        super(context);
        init(context);
    }

    public ReaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ReaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        clear();
    }

    private void init(Context context) {
        this.mCtx = context;
        this.mLastSearchPageIdx = -1;
        loadDisplay(new EmptyDisplay(this.mCtx));
    }

    public void setReaderListener(ReaderListener readerListener) {
        this.mReaderViewListener = readerListener;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            basePlugPDFDisplay.setReaderListener(readerListener);
        }
    }

    public void setPageViewListener(PageViewListener pageViewListener) {
        this.mPageViewListener = pageViewListener;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            Adapter adapter = basePlugPDFDisplay.getAdapter();
            if (adapter instanceof PageAdapter) {
                ((PageAdapter) adapter).setListener(this.mPageViewListener);
            } else if (adapter instanceof ThumbnailPageAdapter) {
                ((ThumbnailPageAdapter) adapter).setListener(this.mPageViewListener);
            }
        }
    }

    public PDFDocument getDocument() {
        return this.mDoc;
    }

    public BasePlugPDFDisplay getPlugPDFDisplay() {
        return this.mDisplay;
    }

    public void setPlugPDFDisplay(BasePlugPDFDisplay basePlugPDFDisplay) {
        loadDisplay(basePlugPDFDisplay);
    }

    public void refreshLayout() {
        this.mDisplay.refreshLayout();
    }

    public void openFile(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        if (PlugPDF.getLicenseInfo() != null) {
            try {
                this.mDoc = new PDFDocument(str, str2);
                if (this.mDisplay instanceof EmptyDisplay) {
                    setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
                } else {
                    setPageDisplayMode(this.mDisplay.getMode());
                }
                loadFinish(DocumentState.OPEN.SUCCESS);
                if (mEnableUseRecentPage) {
                    useRecentPage(str);
                }
            } catch (PlugPDFException.WrongPassword unused) {
                loadFinish(DocumentState.OPEN.WRONG_PASSWD);
            } catch (Exception e) {
                Log.e("PlugPDF", "[ERROR] " + e.toString());
                loadFinish(DocumentState.OPEN.FAIL);
            }
        } else {
            throw new RuntimeException("Pleas call PlugPDF.init() first.");
        }
    }

    public void openData(byte[] bArr, int i, String str) {
        if (str == null) {
            str = "";
        }
        if (PlugPDF.getLicenseInfo() != null) {
            try {
                this.mDoc = new PDFDocument(bArr, i, str);
                if (this.mDisplay instanceof EmptyDisplay) {
                    setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
                } else {
                    setPageDisplayMode(this.mDisplay.getMode());
                }
                loadFinish(DocumentState.OPEN.SUCCESS);
            } catch (PlugPDFException.WrongPassword unused) {
                loadFinish(DocumentState.OPEN.WRONG_PASSWD);
            } catch (Exception e) {
                Log.e("PlugPDF", "[ERROR] " + e.toString());
                loadFinish(DocumentState.OPEN.FAIL);
            }
        } else {
            throw new RuntimeException("Pleas call PlugPDF.init() first.");
        }
    }

    public void openJetStreamUrl(String str, int i, String str2, String str3) {
        if (str3 == null) {
            str3 = "";
        }
        if (PlugPDF.getLicenseInfo() != null) {
            try {
                this.mDoc = new PDFDocument(str, i, str2, str3);
                if (this.mDisplay instanceof EmptyDisplay) {
                    setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
                } else {
                    setPageDisplayMode(this.mDisplay.getMode());
                }
                loadFinish(DocumentState.OPEN.SUCCESS);
            } catch (PlugPDFException.WrongPassword unused) {
                loadFinish(DocumentState.OPEN.WRONG_PASSWD);
            } catch (Exception e) {
                Log.e("PlugPDF", "[ERROR] " + e.toString());
                loadFinish(DocumentState.OPEN.FAIL);
            }
        } else {
            throw new RuntimeException("Pleas call PlugPDF.init() first.");
        }
    }

    public void openJetStreamUrl(String str, int i, String str2, String str3, String str4) {
        if (str3 == null) {
            str3 = "";
        }
        String str5 = str3;
        if (PlugPDF.getLicenseInfo() != null) {
            try {
                this.mDoc = new PDFDocument(str, i, str2, str5, str4);
                setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
                loadFinish(DocumentState.OPEN.SUCCESS);
            } catch (PlugPDFException.WrongPassword unused) {
                loadFinish(DocumentState.OPEN.WRONG_PASSWD);
            } catch (Exception e) {
                Log.e("PlugPDF", "[ERROR] " + e.toString());
                loadFinish(DocumentState.OPEN.FAIL);
            }
        } else {
            throw new RuntimeException("Pleas call PlugPDF.init() first.");
        }
    }

    public void openUrl(final String str, final String str2) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(0);
        progressDialog.setTitle(getContext().getString(ResourceManager.getStringId(this.mCtx, "text_notice")));
        progressDialog.setMessage(getContext().getString(ResourceManager.getStringId(this.mCtx, "text_url_downloading")));
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                ReaderView.this.stopDownload();
            }
        });
        AnonymousClass3 r2 = new AsyncTask<Void, Void, byte[]>() {
            private HttpURLConnection connection = null;
            private boolean useFileSystem = false;

            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c3, code lost:
                if (r10 != null) goto L_0x00c5;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c5, code lost:
                r10.disconnect();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ee, code lost:
                if (r10 == null) goto L_0x00f1;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f1, code lost:
                return r0;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public byte[] doInBackground(java.lang.Void... r10) {
                /*
                    r9 = this;
                    java.lang.String r10 = "PlugPDF"
                    r0 = 0
                    java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x00d3 }
                    java.lang.String r2 = r6     // Catch:{ Exception -> 0x00d3 }
                    r1.<init>(r2)     // Catch:{ Exception -> 0x00d3 }
                    java.net.URLConnection r2 = r1.openConnection()     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x00d3 }
                    r9.connection = r2     // Catch:{ Exception -> 0x00d3 }
                    r3 = 1
                    r2.setDoInput(r3)     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r2 = r9.connection     // Catch:{ Exception -> 0x00d3 }
                    r4 = 10000(0x2710, float:1.4013E-41)
                    r2.setConnectTimeout(r4)     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r2 = r9.connection     // Catch:{ Exception -> 0x00d3 }
                    r2.connect()     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r2 = r9.connection     // Catch:{ Exception -> 0x00d3 }
                    int r2 = r2.getResponseCode()     // Catch:{ Exception -> 0x00d3 }
                    r4 = 200(0xc8, float:2.8E-43)
                    if (r2 != r4) goto L_0x00c9
                    java.net.HttpURLConnection r2 = r9.connection     // Catch:{ Exception -> 0x00d3 }
                    java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r4 = r9.connection     // Catch:{ Exception -> 0x00d3 }
                    int r4 = r4.getContentLength()     // Catch:{ Exception -> 0x00d3 }
                    r5 = 5242880(0x500000, float:7.34684E-39)
                    if (r4 <= r5) goto L_0x003d
                    goto L_0x003e
                L_0x003d:
                    r3 = 0
                L_0x003e:
                    r9.useFileSystem = r3     // Catch:{ Exception -> 0x00d3 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d3 }
                    r3.<init>()     // Catch:{ Exception -> 0x00d3 }
                    java.lang.String r5 = "[DEBUG] download pdf file URL. (File length: "
                    r3.append(r5)     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r5 = r9.connection     // Catch:{ Exception -> 0x00d3 }
                    int r5 = r5.getContentLength()     // Catch:{ Exception -> 0x00d3 }
                    r3.append(r5)     // Catch:{ Exception -> 0x00d3 }
                    java.lang.String r5 = ")"
                    r3.append(r5)     // Catch:{ Exception -> 0x00d3 }
                    java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00d3 }
                    android.util.Log.d(r10, r3)     // Catch:{ Exception -> 0x00d3 }
                    java.lang.String r1 = r1.getFile()     // Catch:{ Exception -> 0x00d3 }
                    java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x00d3 }
                    com.epapyrus.plugpdf.core.viewer.ReaderView r5 = com.epapyrus.plugpdf.core.viewer.ReaderView.this     // Catch:{ Exception -> 0x00d3 }
                    android.content.Context r5 = r5.mCtx     // Catch:{ Exception -> 0x00d3 }
                    java.io.File r5 = r5.getCacheDir()     // Catch:{ Exception -> 0x00d3 }
                    r3.<init>(r5, r1)     // Catch:{ Exception -> 0x00d3 }
                    boolean r1 = r9.useFileSystem     // Catch:{ Exception -> 0x00d3 }
                    if (r1 == 0) goto L_0x00b5
                    java.io.File r1 = r3.getParentFile()     // Catch:{ Exception -> 0x00d3 }
                    boolean r1 = r1.exists()     // Catch:{ Exception -> 0x00d3 }
                    if (r1 == 0) goto L_0x00ae
                    java.io.File r1 = r3.getParentFile()     // Catch:{ Exception -> 0x00d3 }
                    boolean r1 = r1.isDirectory()     // Catch:{ Exception -> 0x00d3 }
                    if (r1 != 0) goto L_0x008b
                    goto L_0x00ae
                L_0x008b:
                    boolean r1 = r3.exists()     // Catch:{ Exception -> 0x00d3 }
                    if (r1 == 0) goto L_0x00b5
                    long r5 = r3.length()     // Catch:{ Exception -> 0x00d3 }
                    long r7 = (long) r4     // Catch:{ Exception -> 0x00d3 }
                    int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                    if (r1 != 0) goto L_0x00aa
                    java.lang.String r1 = r3.getAbsolutePath()     // Catch:{ Exception -> 0x00d3 }
                    byte[] r10 = r1.getBytes()     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r0 = r9.connection
                    if (r0 == 0) goto L_0x00a9
                    r0.disconnect()
                L_0x00a9:
                    return r10
                L_0x00aa:
                    r3.delete()     // Catch:{ Exception -> 0x00d3 }
                    goto L_0x00b5
                L_0x00ae:
                    java.io.File r1 = r3.getParentFile()     // Catch:{ Exception -> 0x00d3 }
                    r1.mkdirs()     // Catch:{ Exception -> 0x00d3 }
                L_0x00b5:
                    com.epapyrus.plugpdf.core.viewer.ReaderView r1 = com.epapyrus.plugpdf.core.viewer.ReaderView.this     // Catch:{ Exception -> 0x00d3 }
                    java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ Exception -> 0x00d3 }
                    boolean r4 = r9.useFileSystem     // Catch:{ Exception -> 0x00d3 }
                    byte[] r0 = r1.readFromStream(r2, r3, r4)     // Catch:{ Exception -> 0x00d3 }
                    java.net.HttpURLConnection r10 = r9.connection
                    if (r10 == 0) goto L_0x00f1
                L_0x00c5:
                    r10.disconnect()
                    goto L_0x00f1
                L_0x00c9:
                    java.net.HttpURLConnection r10 = r9.connection
                    if (r10 == 0) goto L_0x00d0
                    r10.disconnect()
                L_0x00d0:
                    return r0
                L_0x00d1:
                    r10 = move-exception
                    goto L_0x00f2
                L_0x00d3:
                    r1 = move-exception
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d1 }
                    r2.<init>()     // Catch:{ all -> 0x00d1 }
                    java.lang.String r3 = "[ERROR] URL open failed because, "
                    r2.append(r3)     // Catch:{ all -> 0x00d1 }
                    java.lang.String r1 = r1.getLocalizedMessage()     // Catch:{ all -> 0x00d1 }
                    r2.append(r1)     // Catch:{ all -> 0x00d1 }
                    java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x00d1 }
                    android.util.Log.e(r10, r1)     // Catch:{ all -> 0x00d1 }
                    java.net.HttpURLConnection r10 = r9.connection
                    if (r10 == 0) goto L_0x00f1
                    goto L_0x00c5
                L_0x00f1:
                    return r0
                L_0x00f2:
                    java.net.HttpURLConnection r0 = r9.connection
                    if (r0 == 0) goto L_0x00f9
                    r0.disconnect()
                L_0x00f9:
                    throw r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.ReaderView.AnonymousClass3.doInBackground(java.lang.Void[]):byte[]");
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(byte[] bArr) {
                String str;
                progressDialog.cancel();
                if (bArr == null) {
                    int i = -1;
                    try {
                        i = this.connection.getResponseCode();
                        str = this.connection.getResponseMessage();
                    } catch (IOException e) {
                        Log.e("PlugPDF", "[ERROR] ReaderView.openUrl ", e);
                        str = null;
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReaderView.this.getContext());
                    builder.setTitle("HTTP ERROR " + i + IOUtils.LINE_SEPARATOR_WINDOWS + str);
                    builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
                    builder.show();
                    ReaderView.this.loadFinish(DocumentState.OPEN.FAIL);
                    return;
                }
                try {
                    if (this.useFileSystem) {
                        PDFDocument unused = ReaderView.this.mDoc = new PDFDocument(new String(bArr), str2);
                    } else {
                        PDFDocument unused2 = ReaderView.this.mDoc = new PDFDocument(bArr, bArr.length, str2);
                    }
                    if (ReaderView.this.mDisplay instanceof EmptyDisplay) {
                        ReaderView.this.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
                    } else {
                        ReaderView.this.setPageDisplayMode(ReaderView.this.mDisplay.getMode());
                    }
                    ReaderView.this.loadFinish(DocumentState.OPEN.SUCCESS);
                } catch (PlugPDFException.WrongPassword unused3) {
                    ReaderView.this.loadFinish(DocumentState.OPEN.WRONG_PASSWD);
                } catch (Exception e2) {
                    Log.e("PlugPDF", "[ERROR] " + e2.toString());
                    ReaderView.this.loadFinish(DocumentState.OPEN.FAIL);
                }
            }

            /* access modifiers changed from: protected */
            public void onCancelled() {
                super.onCancelled();
                progressDialog.cancel();
            }

            /* access modifiers changed from: protected */
            public void onPreExecute() {
                super.onPreExecute();
                progressDialog.show();
            }
        };
        this.mOpenUrlTask = r2;
        r2.execute(new Void[0]);
    }

    public void openStream(FileInputStream fileInputStream, int i, String str) {
        if (str == null) {
            str = "";
        }
        if (PlugPDF.getLicenseInfo() != null) {
            try {
                this.mDoc = new PDFDocument(fileInputStream, i, str);
                if (this.mDisplay instanceof EmptyDisplay) {
                    setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
                } else {
                    setPageDisplayMode(this.mDisplay.getMode());
                }
                loadFinish(DocumentState.OPEN.SUCCESS);
            } catch (PlugPDFException.WrongPassword unused) {
                loadFinish(DocumentState.OPEN.WRONG_PASSWD);
            } catch (Exception e) {
                Log.e("PlugPDF", "[ERROR] " + e.toString());
                loadFinish(DocumentState.OPEN.FAIL);
            }
        } else {
            throw new RuntimeException("Pleas call PlugPDF.init() first.");
        }
    }

    /* access modifiers changed from: private */
    public byte[] readFromStream(InputStream inputStream, String str, boolean z) {
        OutputStream outputStream;
        byte[] byteArray;
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[1024];
        if (z) {
            try {
                outputStream = new FileOutputStream(new File(str));
            } catch (Exception e) {
                e.printStackTrace(System.out);
                return null;
            }
        } else {
            outputStream = new ByteArrayOutputStream(2048);
        }
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            outputStream.write(bArr, 0, read);
        }
        if (z) {
            byteArray = str.getBytes();
        } else {
            byteArray = ((ByteArrayOutputStream) outputStream).toByteArray();
        }
        return byteArray;
    }

    public String save() {
        String saveFile = this.mDoc.saveFile();
        refreshLayout();
        return saveFile;
    }

    public String saveWithEncrypt(String str, String str2, int i) {
        if (this.mDoc.setEncrypt(str, str2, i)) {
            String saveFile = this.mDoc.saveFile();
            refreshLayout();
            return saveFile;
        }
        throw new RuntimeException("Error Encrypt.");
    }

    public String saveAsFile(String str) {
        String saveAsFile = this.mDoc.saveAsFile(str);
        refreshLayout();
        return saveAsFile;
    }

    public String saveAsFileWithEncrypt(String str, String str2, String str3, int i) {
        if (this.mDoc.setEncrypt(str2, str3, i)) {
            String saveAsFile = this.mDoc.saveAsFile(str);
            refreshLayout();
            return saveAsFile;
        }
        throw new RuntimeException("Error Encrypt.");
    }

    public void flattenFromFields(boolean z) {
        for (int i = 0; i < getPageCount(); i++) {
            FieldProperty[] loadFieldList = this.mDoc.loadFieldList(i);
            if (loadFieldList != null) {
                for (FieldProperty fieldProperty : loadFieldList) {
                    if (fieldProperty != null) {
                        FieldCreator.create(this.mCtx, this.mDoc, fieldProperty).beforeFlatten();
                    }
                }
            }
        }
        this.mDoc.flattenFormFields(z);
    }

    public void flattenAnnots() {
        this.mDoc.flattenAnnots();
    }

    /* access modifiers changed from: private */
    public void loadFinish(DocumentState.OPEN open) {
        if (open == DocumentState.OPEN.SUCCESS) {
            showTrialExpiration();
            showUpdateMessage();
        }
        ReaderListener readerListener = this.mReaderViewListener;
        if (readerListener != null) {
            readerListener.onLoadFinish(open);
        }
    }

    private void showTrialExpiration() {
        LicenseInfo licenseInfo = PlugPDF.getLicenseInfo();
        if (licenseInfo == null) {
            throw new RuntimeException("Pleas call PlugPDF.init() first.");
        } else if (licenseInfo.getProductVersion() == LicenseInfo.ProductVersion.VER_KR_TRIAL || licenseInfo.getProductVersion() == LicenseInfo.ProductVersion.VER_US_TRIAL) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Trial Expiration : " + licenseInfo.getYear() + "/" + licenseInfo.getMonth() + "/" + licenseInfo.getDay());
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
            builder.show();
        }
    }

    private String downloadHtml(String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StringBuilder sb = new StringBuilder();
        final String str2 = str;
        final StringBuilder sb2 = sb;
        final CountDownLatch countDownLatch2 = countDownLatch;
        new HandlerThread("UIHandler") {
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb2.append(readLine);
                    }
                } catch (Exception unused) {
                    Log.e("PlugPDF", "Update Check failed: this device cannot connect to PlugPDF server.");
                } catch (Throwable th) {
                    countDownLatch2.countDown();
                    throw th;
                }
                countDownLatch2.countDown();
            }
        }.start();
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void showUpdateMessage() {
        if (PlugPDF.isUpdateCheckEnabled() && PlugPDFUtility.IsNetConnected(this.mCtx)) {
            String[] split = PlugPDF.getVersionName().split("\\.");
            String str = "" + split[0] + "." + split[1] + "." + split[2];
            String downloadHtml = downloadHtml("https://plugpdf.com/devrefs/android/index.html");
            if (downloadHtml.length() != 0 && !downloadHtml.contains(str)) {
                new AlertDialog.Builder(getContext()).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).setMessage("Please update the PlugPDF SDK to the latest version. \n https://plugpdf.com/sdkfile/PlugPDF_Android.zip").create().show();
            }
        }
    }

    public int getPageIdx() {
        return this.mDisplay.getPageIdx();
    }

    public void goToPage(int i) {
        this.mDisplay.goToPage(i);
    }

    public BasePlugPDFDisplay.PageDisplayMode getPageDisplayMode() {
        return this.mDisplay.getMode();
    }

    public int getPageCount() {
        return this.mDoc.getPageCount();
    }

    private void loadDisplay(BasePlugPDFDisplay basePlugPDFDisplay) {
        Adapter adapter;
        BasePlugPDFDisplay basePlugPDFDisplay2 = this.mDisplay;
        if (basePlugPDFDisplay2 != null) {
            basePlugPDFDisplay2.clear();
            try {
                removeAllViews();
            } catch (IndexOutOfBoundsException e) {
                Log.w("PlugPDF", e);
            }
        }
        this.mDisplay = basePlugPDFDisplay;
        basePlugPDFDisplay.setDocument(this.mDoc);
        this.mDisplay.setDisplayListener(this.mDisplayListener);
        this.mDisplay.setReaderListener(this.mReaderViewListener);
        this.mDisplay.setAdapter(new PageAdapter(getContext(), this.mDoc));
        if (this.mDisplay.getMode() == BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL) {
            adapter = new ThumbnailPageAdapter(getContext(), this.mDoc);
            ((ThumbnailPageAdapter) adapter).setListener(this.mPageViewListener);
        } else {
            adapter = new PageAdapter(getContext(), this.mDoc);
            PageAdapter pageAdapter = (PageAdapter) adapter;
            pageAdapter.setListener(this.mPageViewListener);
            pageAdapter.setFieldEvenetListener(this.mFieldEventListener);
        }
        this.mDisplay.setAdapter(adapter);
        this.mDisplay.setAnnotEventListener(this.mAnnotEventListener);
        this.mDisplay.setAnnotToolEventListenr(this.mAnnotToolEventListener);
        this.mDisplay.setTextSelectListener(this.mTextSelectListener);
        this.mDisplay.setOnLongClickListener(this.mOnLongClickListener);
        this.mDisplay.setFitType(this.mFitType);
        addView(this.mDisplay);
    }

    public void restoreSavedState(Object obj) {
        this.mDoc = (PDFDocument) obj;
        loadFinish(DocumentState.OPEN.SUCCESS);
    }

    public Object getState() {
        return this.mDoc;
    }

    public void stopSearch() {
        AsyncTask<Integer, Integer, SearchInfo> asyncTask = this.mSearchTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.mSearchTask = null;
        }
    }

    public void stopDownload() {
        AsyncTask<Void, Void, byte[]> asyncTask = this.mOpenUrlTask;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.mOpenUrlTask = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            basePlugPDFDisplay.measure(0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        PDFDocument pDFDocument = this.mDoc;
        if (pDFDocument != null) {
            if (pDFDocument.wasReleased()) {
                Log.e("PlugPDF", "", new RuntimeException("this document was released"));
                return;
            }
            BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
            if (basePlugPDFDisplay != null) {
                basePlugPDFDisplay.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
                invalidate();
            }
        }
    }

    private boolean hasArabicCharacters(String str) {
        for (char c : str.toCharArray()) {
            if (c >= 1536 && c <= 1791) {
                return true;
            }
            if (c >= 1872 && c <= 1919) {
                return true;
            }
            if (c >= 64336 && c <= 64575) {
                return true;
            }
            if (c >= 65136 && c <= 65276) {
                return true;
            }
        }
        return false;
    }

    private String reverseString(String str) {
        return new StringBuffer(str).reverse().toString();
    }

    public void search(String str, final int i) {
        if (!str.isEmpty() && this.mDoc != null) {
            if (hasArabicCharacters(str)) {
                str = reverseString(str);
            }
            this.mSearchText = str;
            stopSearch();
            if (i == 0) {
                int pageIdx = getPageIdx();
                int i2 = this.mLastSearchPageIdx;
                if (i2 == -1 || i2 != pageIdx) {
                    this.mLastSearchPageIdx = pageIdx;
                } else {
                    return;
                }
            }
            final ProgressDialog progressDialog = this.mSearchProgressDialog;
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setProgressStyle(1);
                progressDialog.setTitle(getContext().getString(ResourceManager.getStringId(this.mCtx, "text_search_progress")));
            }
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    ReaderView.this.stopSearch();
                }
            });
            progressDialog.setMax(this.mDoc.getPageCount());
            AnonymousClass6 r1 = new AsyncTask<Integer, Integer, SearchInfo>() {
                /* access modifiers changed from: protected */
                /* JADX WARNING: Removed duplicated region for block: B:17:0x006b  */
                /* JADX WARNING: Removed duplicated region for block: B:24:0x00ad A[RETURN] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public com.epapyrus.plugpdf.core.SearchInfo doInBackground(java.lang.Integer... r7) {
                    /*
                        r6 = this;
                        int r0 = r6
                        r1 = 0
                        if (r0 != 0) goto L_0x003a
                        com.epapyrus.plugpdf.core.viewer.ReaderView r7 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        int r7 = r7.getPageIdx()
                        com.epapyrus.plugpdf.core.viewer.ReaderView r0 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        com.epapyrus.plugpdf.core.PDFDocument r0 = r0.mDoc
                        com.epapyrus.plugpdf.core.viewer.ReaderView r2 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        java.lang.String r2 = r2.mSearchText
                        boolean r3 = com.epapyrus.plugpdf.core.PropertyManager.isIncludeUnAccentSearchResults()
                        android.graphics.RectF[] r0 = r0.getSearchPage(r7, r2, r3)
                        if (r0 == 0) goto L_0x0039
                        int r2 = r0.length
                        if (r2 <= 0) goto L_0x0039
                        com.epapyrus.plugpdf.core.SearchInfo r1 = new com.epapyrus.plugpdf.core.SearchInfo
                        com.epapyrus.plugpdf.core.viewer.ReaderView r2 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        java.lang.String r2 = r2.mSearchText
                        com.epapyrus.plugpdf.core.viewer.ReaderView r3 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        com.epapyrus.plugpdf.core.PDFDocument r3 = r3.mDoc
                        java.lang.String r3 = r3.getFilePath()
                        r1.<init>(r2, r7, r0, r3)
                    L_0x0039:
                        return r1
                    L_0x003a:
                        com.epapyrus.plugpdf.core.SearchInfo r0 = com.epapyrus.plugpdf.core.Register.getSearchInfo()
                        r2 = 0
                        if (r0 != 0) goto L_0x0048
                        com.epapyrus.plugpdf.core.viewer.ReaderView r0 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        int r0 = r0.getPageIdx()
                        goto L_0x0057
                    L_0x0048:
                        com.epapyrus.plugpdf.core.SearchInfo r0 = com.epapyrus.plugpdf.core.Register.getSearchInfo()
                        int r0 = r0.getPageIdx()
                        r3 = r7[r2]
                        int r3 = r3.intValue()
                    L_0x0056:
                        int r0 = r0 + r3
                    L_0x0057:
                        if (r0 < 0) goto L_0x00ad
                        com.epapyrus.plugpdf.core.viewer.ReaderView r3 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        com.epapyrus.plugpdf.core.PDFDocument r3 = r3.mDoc
                        int r3 = r3.getPageCount()
                        if (r0 >= r3) goto L_0x00ad
                        boolean r3 = r6.isCancelled()
                        if (r3 != 0) goto L_0x00ad
                        r3 = 1
                        java.lang.Integer[] r3 = new java.lang.Integer[r3]
                        java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
                        r3[r2] = r4
                        r6.publishProgress(r3)
                        com.epapyrus.plugpdf.core.viewer.ReaderView r3 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        com.epapyrus.plugpdf.core.PDFDocument r3 = r3.mDoc
                        com.epapyrus.plugpdf.core.viewer.ReaderView r4 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        java.lang.String r4 = r4.mSearchText
                        boolean r5 = com.epapyrus.plugpdf.core.PropertyManager.isIncludeUnAccentSearchResults()
                        android.graphics.RectF[] r3 = r3.getSearchPage(r0, r4, r5)
                        if (r3 == 0) goto L_0x00a6
                        int r4 = r3.length
                        if (r4 <= 0) goto L_0x00a6
                        com.epapyrus.plugpdf.core.SearchInfo r7 = new com.epapyrus.plugpdf.core.SearchInfo
                        com.epapyrus.plugpdf.core.viewer.ReaderView r1 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        java.lang.String r1 = r1.mSearchText
                        com.epapyrus.plugpdf.core.viewer.ReaderView r2 = com.epapyrus.plugpdf.core.viewer.ReaderView.this
                        com.epapyrus.plugpdf.core.PDFDocument r2 = r2.mDoc
                        java.lang.String r2 = r2.getFilePath()
                        r7.<init>(r1, r0, r3, r2)
                        return r7
                    L_0x00a6:
                        r3 = r7[r2]
                        int r3 = r3.intValue()
                        goto L_0x0056
                    L_0x00ad:
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.ReaderView.AnonymousClass6.doInBackground(java.lang.Integer[]):com.epapyrus.plugpdf.core.SearchInfo");
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(SearchInfo searchInfo) {
                    progressDialog.cancel();
                    if (searchInfo != null) {
                        if (ReaderView.this.mReaderViewListener != null) {
                            ReaderView.this.mReaderViewListener.onSearchFinish(true);
                        }
                        if (ReaderView.this.getPageDisplayMode() == BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL) {
                            ReaderView.this.setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
                        }
                        if (i != 0) {
                            ReaderView.this.getPlugPDFDisplay().goToPage(searchInfo.getPageIdx());
                        }
                        Register.setSearchInfo(searchInfo);
                        ReaderView.this.getPlugPDFDisplay().setupPageViews();
                        if (PropertyManager.isEnableResetZoomAfterSearching()) {
                            ReaderView.this.setFitType(BasePlugPDFDisplay.FitType.MINIMUM);
                            return;
                        }
                        return;
                    }
                    if (ReaderView.this.mReaderViewListener != null) {
                        ReaderView.this.mReaderViewListener.onSearchFinish(false);
                    }
                    if (i != 0) {
                        AlertDialog.Builder access$800 = ReaderView.this.mSearchFinishAlertBuilder;
                        if (access$800 == null) {
                            access$800 = new AlertDialog.Builder(ReaderView.this.getContext());
                            access$800.setTitle(ReaderView.this.getContext().getString(ResourceManager.getStringId(ReaderView.this.mCtx, "text_search_found_fail")));
                            access$800.setPositiveButton(ReaderView.this.getContext().getString(ResourceManager.getStringId(ReaderView.this.mCtx, "text_ok")), (DialogInterface.OnClickListener) null);
                        }
                        access$800.create().show();
                    }
                }

                /* access modifiers changed from: protected */
                public void onCancelled() {
                    super.onCancelled();
                    progressDialog.cancel();
                }

                /* access modifiers changed from: protected */
                public void onProgressUpdate(Integer... numArr) {
                    super.onProgressUpdate(numArr);
                    progressDialog.setProgress(numArr[0].intValue());
                }

                /* access modifiers changed from: protected */
                public void onPreExecute() {
                    super.onPreExecute();
                    progressDialog.show();
                }
            };
            this.mSearchTask = r1;
            r1.execute(new Integer[]{Integer.valueOf(i)});
        }
    }

    public void resetSearchInfo() {
        Register.setSearchInfo((SearchInfo) null);
        getPlugPDFDisplay().setupPageViews();
    }

    public void clear() {
        SparseArray<PageView> childViewList = this.mDisplay.getChildViewList();
        boolean z = false;
        if (childViewList != null) {
            int i = 0;
            while (true) {
                if (i < childViewList.size()) {
                    if (!childViewList.valueAt(i).isLoadedPage()) {
                        z = true;
                        break;
                    }
                    i++;
                }
            }
        }
        try {
            if (this.mDisplay != null) {
                stopSearch();
                this.mDisplay.clear();
            }
        } catch (Exception e) {
            Log.w("PlugPDF", "[WARNING] clear was failed because, " + e.getLocalizedMessage());
        }
        PDFDocument pDFDocument = this.mDoc;
        if (pDFDocument != null && !pDFDocument.wasReleased() && this.mDisplay.wasFinishedLoad() && !z) {
            Log.d("PlugPDF", "[DEBUG] Releasing PDFDocument");
            this.mDoc.release();
        }
    }

    public void setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode pageDisplayMode) {
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        int pageIdx = basePlugPDFDisplay != null ? basePlugPDFDisplay.getPageIdx() : 0;
        Log.d("PlugPDF", "[DEBUG] change Page Display Mode : " + pageDisplayMode);
        if (pageDisplayMode != BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL) {
            this.mLastPageDisplayMode = pageDisplayMode;
        }
        switch (AnonymousClass7.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode[pageDisplayMode.ordinal()]) {
            case 1:
                loadDisplay(new HorizontalDisplay(this.mCtx));
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = false;
                return;
            case 2:
                if (getResources().getConfiguration().orientation == 2) {
                    loadDisplay(new BilateralHorizontalDisplay(this.mCtx, PropertyManager.getUseBilateralCoverDisplay()));
                } else {
                    loadDisplay(new HorizontalDisplay(this.mCtx));
                }
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = true;
                return;
            case 3:
                loadDisplay(new VerticalDisplay(this.mCtx));
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = false;
                return;
            case 4:
                if (getResources().getConfiguration().orientation == 2) {
                    loadDisplay(new BilateralVerticalDisplay(this.mCtx, PropertyManager.getUseBilateralCoverDisplay()));
                } else {
                    loadDisplay(new VerticalDisplay(this.mCtx));
                }
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = true;
                return;
            case 5:
                loadDisplay(new ContinuosDisplay(this.mCtx));
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = false;
                return;
            case 6:
                loadDisplay(new ThumbnailDisplay(this.mCtx));
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = false;
                return;
            case 7:
                loadDisplay(new RealisticDisplay(this.mCtx));
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = false;
                return;
            case 8:
                if (getResources().getConfiguration().orientation == 2) {
                    loadDisplay(new BilateralRealisticDisplay(this.mCtx));
                } else {
                    loadDisplay(new RealisticDisplay(this.mCtx));
                }
                this.mDisplay.goToPage(pageIdx);
                this.isBilateralMode = true;
                return;
            default:
                return;
        }
    }

    /* renamed from: com.epapyrus.plugpdf.core.viewer.ReaderView$7  reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
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
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.BILATERAL_HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0028 }
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
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.CONTINUOS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.REALISTIC     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$PageDisplayMode r1 = com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode.BILATERAL_REALISTIC     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.ReaderView.AnonymousClass7.<clinit>():void");
        }
    }

    public List<OutlineItem> getOutlineItem() {
        return this.mDoc.getOutline();
    }

    public void changeGestureType(BaseGestureProcessor.GestureType gestureType) {
        this.mDisplay.changeGesture(gestureType);
    }

    public void setAnnotationTool(BaseAnnotTool.AnnotToolType annotToolType) {
        this.mDisplay.setAnnotationTool(annotToolType);
    }

    public void setTextSelectListener(AnnotToolSelect.TextSelectListener textSelectListener) {
        this.mTextSelectListener = textSelectListener;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            basePlugPDFDisplay.setTextSelectListener(textSelectListener);
        }
    }

    public void setAnnotEventListener(AnnotEventListener annotEventListener) {
        this.mAnnotEventListener = annotEventListener;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            basePlugPDFDisplay.setAnnotEventListener(annotEventListener);
        }
    }

    public void setAnnotToolListener(AnnotToolEventListener annotToolEventListener) {
        this.mAnnotToolEventListener = annotToolEventListener;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            basePlugPDFDisplay.setAnnotToolEventListenr(annotToolEventListener);
        }
    }

    public void setFieldEventListener(FieldEventListener fieldEventListener) {
        this.mFieldEventListener = fieldEventListener;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            Adapter adapter = basePlugPDFDisplay.getAdapter();
            if (adapter instanceof PageAdapter) {
                ((PageAdapter) adapter).setFieldEvenetListener(this.mFieldEventListener);
            }
        }
    }

    public void enableAnnotFeature(String str, boolean z) {
        if (str != null) {
            PDFDocument.enableAnnotFeature(str, z);
        }
    }

    public BaseField findField(int i, String str) {
        PageView pageView = this.mDisplay.getChildViewList().get(i);
        if (pageView != null) {
            for (BaseField next : pageView.getFieldList()) {
                if (next.getUID().equals(str)) {
                    return next;
                }
            }
        }
        FieldProperty loadField = this.mDoc.loadField(i, str);
        if (loadField == null) {
            return null;
        }
        BaseField create = FieldCreator.create(getContext(), this.mDoc, loadField);
        create.setScale(getPageRatio());
        return create;
    }

    public BaseField findFieldWithTitle(int i, String str, String str2) {
        PageView pageView = this.mDisplay.getChildViewList().get(i);
        if (pageView != null) {
            for (BaseField next : pageView.getFieldList()) {
                if (next.getUID().equals(str2) && next.getTitle().equals(str)) {
                    return next;
                }
            }
        }
        FieldProperty[] loadFieldList = this.mDoc.loadFieldList(i);
        for (FieldProperty create : loadFieldList) {
            BaseField create2 = FieldCreator.create(getContext(), this.mDoc, create);
            if (create2 != null && create2.getUID().equals(str2) && create2.getTitle().equals(str)) {
                create2.setScale(getPageRatio());
                return create2;
            }
        }
        return null;
    }

    public BaseField[] getAllField(int i) {
        PageView pageView = this.mDisplay.getChildViewList().get(i);
        if (pageView != null) {
            return (BaseField[]) pageView.getFieldList().toArray(new BaseField[pageView.getFieldList().size()]);
        }
        FieldProperty[] loadFieldList = this.mDoc.loadFieldList(i);
        if (loadFieldList == null) {
            return null;
        }
        BaseField[] baseFieldArr = new BaseField[loadFieldList.length];
        for (int i2 = 0; i2 < loadFieldList.length; i2++) {
            baseFieldArr[i2] = FieldCreator.create(getContext(), this.mDoc, loadFieldList[i2]);
            baseFieldArr[i2].setScale(getPageRatio());
        }
        return baseFieldArr;
    }

    public void setFieldValue(int i, String str, String str2) {
        BaseField findField = findField(i, str);
        if (findField != null) {
            findField.setValue(str2);
            this.mDoc.setFieldValue(i, findField.getObjID(), str2);
        }
    }

    public void setFieldValueWithTitle(int i, String str, String str2, String str3) {
        BaseField findFieldWithTitle = findFieldWithTitle(i, str, str2);
        if (findFieldWithTitle != null) {
            findFieldWithTitle.setValue(str3);
            this.mDoc.setFieldValue(i, findFieldWithTitle.getObjID(), str3);
        }
    }

    public void setFitType(BasePlugPDFDisplay.FitType fitType) {
        this.mFitType = fitType;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            basePlugPDFDisplay.setFitType(fitType);
        }
    }

    public void setFieldBitmap(int i, String str, Bitmap bitmap) {
        BaseField findField = findField(i, str);
        if (findField != null) {
            ((ButtonField) findField).setBitmap(bitmap);
        }
    }

    public void setFieldAlphaBitmap(int i, String str, Bitmap bitmap, boolean z) {
        BaseField findField = findField(i, str);
        if (findField != null) {
            ((ButtonField) findField).setAlphaBitmap(bitmap, z);
        }
    }

    public String getFieldValue(int i, String str) {
        BaseField findField = findField(i, str);
        if (findField == null) {
            return null;
        }
        return findField.getValue();
    }

    public String getFieldValueWithTitle(int i, String str, String str2) {
        BaseField findFieldWithTitle = findFieldWithTitle(i, str, str2);
        if (findFieldWithTitle == null) {
            return null;
        }
        return findFieldWithTitle.getValue();
    }

    public void setFieldState(int i, String str, BaseField.FieldState fieldState) {
        FieldRule.instance().registerFieldState(i, "", str, fieldState);
        applyFieldRule();
    }

    public void setFieldStateWithTitle(int i, String str, String str2, BaseField.FieldState fieldState) {
        FieldRule.instance().registerFieldState(i, str, str2, fieldState);
        applyFieldRule();
    }

    public void setGlobalFieldState(BaseField.FieldState fieldState) {
        FieldRule.instance().setGlobalFieldState(fieldState);
        applyFieldRule();
    }

    public void resetGlobalFieldState() {
        FieldRule.instance().resetGlobalFieldState();
    }

    public BaseField.FieldState getFieldState(int i, String str) {
        PageView pageView = this.mDisplay.getChildViewList().get(i);
        if (pageView != null) {
            for (BaseField next : pageView.getFieldList()) {
                if (next.getUID() != null && next.getUID().equals(str)) {
                    return next.getFieldState();
                }
            }
        }
        return FieldRule.instance().getFieldState(i, str);
    }

    private void applyFieldRule() {
        FieldRule instance = FieldRule.instance();
        SparseArray<PageView> childViewList = this.mDisplay.getChildViewList();
        for (int i = 0; i < childViewList.size(); i++) {
            PageView pageView = childViewList.get(childViewList.keyAt(i));
            if (pageView != null) {
                for (BaseField applyRule : pageView.getFieldList()) {
                    instance.applyRule(applyRule);
                }
            }
        }
    }

    public void changeScale(double d, int i, int i2) {
        this.mDisplay.changeScale(d, i, i2);
    }

    public void clearAllField(int i) {
        PageView pageView = this.mDisplay.getChildViewList().get(i);
        if (pageView != null) {
            for (BaseField clear : pageView.getFieldList()) {
                clear.clear();
            }
        }
        FieldProperty[] loadFieldList = this.mDoc.loadFieldList(i);
        for (FieldProperty fieldProperty : loadFieldList) {
            if (fieldProperty != null) {
                FieldCreator.create(getContext(), this.mDoc, fieldProperty).clear();
            }
        }
    }

    public boolean setEncrypt(String str, String str2, int i) {
        return this.mDoc.setEncrypt(str, str2, i);
    }

    public String getPageText(int i) {
        return this.mDoc.getPageText(i);
    }

    public boolean fieldCenterOn(BaseField baseField) {
        try {
            this.mDisplay.scrollToView(baseField.getPageIdx(), baseField);
            return true;
        } catch (Exception e) {
            Log.e("PlugPDF", "[ERROR] ReaderView.fieldCenterOn ", e);
            return false;
        }
    }

    public void drawPage(Bitmap bitmap, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mDoc.drawPage2(i, bitmap, i2, i3, i4, i5, i6, i7);
    }

    public PointF getPageSize(int i) {
        return this.mDoc.getPageSize(i);
    }

    public static void setEnableUseRecentPage(boolean z) {
        mEnableUseRecentPage = z;
    }

    protected static boolean isEnableUseRecentPage() {
        return mEnableUseRecentPage;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:14|15|16|17|64) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:25|26|27|28|66) */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0031 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0047 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x005b */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0075 A[SYNTHETIC, Splitter:B:49:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0081 A[SYNTHETIC, Splitter:B:56:0x0081] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0088 A[SYNTHETIC, Splitter:B:60:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void useRecentPage(java.lang.String r5) {
        /*
            r4 = this;
            boolean r0 = mEnableUseRecentPage
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            android.content.Context r2 = r4.getContext()     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            java.io.File r2 = r2.getFilesDir()     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            java.lang.String r3 = "pgInfo.ser"
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            if (r2 != 0) goto L_0x001c
            return
        L_0x001c:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0065 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0065 }
            java.lang.Object r0 = r1.readObject()     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            if (r0 != 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0031 }
        L_0x0031:
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return
        L_0x0035:
            com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay r3 = r4.mDisplay     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            if (r3 == 0) goto L_0x003e
            com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay r3 = r4.mDisplay     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r3.setRecentPageMap(r0)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
        L_0x003e:
            java.lang.Object r3 = r0.get(r5)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            if (r3 != 0) goto L_0x004b
            r1.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            r2.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            return
        L_0x004b:
            java.lang.Object r5 = r0.get(r5)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r4.goToPage(r5)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r1.close()     // Catch:{ IOException -> 0x005b }
        L_0x005b:
            r2.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x007d
        L_0x005f:
            r5 = move-exception
            r0 = r1
            goto L_0x007f
        L_0x0062:
            r5 = move-exception
            r0 = r1
            goto L_0x006c
        L_0x0065:
            r5 = move-exception
            goto L_0x006c
        L_0x0067:
            r5 = move-exception
            r2 = r0
            goto L_0x007f
        L_0x006a:
            r5 = move-exception
            r2 = r0
        L_0x006c:
            java.lang.String r1 = "PlugPDF"
            java.lang.String r3 = "[ERROR] ReaderView.useRecentPage "
            android.util.Log.e(r1, r3, r5)     // Catch:{ all -> 0x007e }
            if (r0 == 0) goto L_0x007a
            r0.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x007a
        L_0x0079:
        L_0x007a:
            if (r2 == 0) goto L_0x007d
            goto L_0x005b
        L_0x007d:
            return
        L_0x007e:
            r5 = move-exception
        L_0x007f:
            if (r0 == 0) goto L_0x0086
            r0.close()     // Catch:{ IOException -> 0x0085 }
            goto L_0x0086
        L_0x0085:
        L_0x0086:
            if (r2 == 0) goto L_0x008b
            r2.close()     // Catch:{ IOException -> 0x008b }
        L_0x008b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.ReaderView.useRecentPage(java.lang.String):void");
    }

    public void setOuterFocusInterception(boolean z) {
        this.mDisplay.setOuterFocusInterception(z);
    }

    public void updateOutline(List<OutlineItem> list) {
        this.mDoc.updateOutline((OutlineItem[]) list.toArray(new OutlineItem[list.size()]));
    }

    public synchronized void exportAnnotToXFDF(String str) {
        this.mDoc.exportAnnotToXFDF(str);
    }

    public synchronized void importAnnotFromXFDF(String str) {
        this.mDoc.importAnnotFromXFDF(str);
        this.mDisplay.refreshLayout();
    }

    public boolean canPrint() {
        return this.mDoc.canPrint();
    }

    public boolean canModifyContent() {
        return this.mDoc.canModifyContent();
    }

    public boolean canCopyContent() {
        return this.mDoc.canCopyContent();
    }

    public boolean canModifyAnnot() {
        return this.mDoc.canModifyAnnot();
    }

    public boolean canFillField() {
        return this.mDoc.canFillField();
    }

    public boolean canExtract() {
        return this.mDoc.canExtract();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.isBilateralMode) {
            int pageIdx = this.mDisplay.getPageIdx();
            if (configuration.orientation == 2) {
                int i = AnonymousClass7.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode[getPageDisplayMode().ordinal()];
                if (i == 1) {
                    loadDisplay(new BilateralHorizontalDisplay(this.mCtx, PropertyManager.getUseBilateralCoverDisplay()));
                    goToPage(pageIdx);
                } else if (i == 3) {
                    loadDisplay(new BilateralVerticalDisplay(this.mCtx, PropertyManager.getUseBilateralCoverDisplay()));
                    goToPage(pageIdx);
                } else if (i == 7) {
                    loadDisplay(new BilateralRealisticDisplay(this.mCtx));
                    goToPage(pageIdx);
                }
            } else {
                int i2 = AnonymousClass7.$SwitchMap$com$epapyrus$plugpdf$core$viewer$BasePlugPDFDisplay$PageDisplayMode[getPageDisplayMode().ordinal()];
                if (i2 == 2) {
                    loadDisplay(new HorizontalDisplay(this.mCtx));
                    goToPage(pageIdx);
                } else if (i2 == 4) {
                    loadDisplay(new VerticalDisplay(this.mCtx));
                    goToPage(pageIdx);
                } else if (i2 == 8) {
                    loadDisplay(new RealisticDisplay(this.mCtx));
                    goToPage(pageIdx);
                }
            }
        }
    }

    @Deprecated
    public String extractText(Rect rect) {
        return extractText(new RectF((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom));
    }

    public String extractText(RectF rectF) {
        if (canExtract()) {
            boolean isBilateral = isBilateral();
            RectF[] convertToDocAreaFrom = convertToDocAreaFrom(rectF, isBilateral);
            int pageIdx = getPageIdx();
            if (isBilateral) {
                int i = pageIdx - (pageIdx % 2);
                if (convertToDocAreaFrom[0] != null && convertToDocAreaFrom[1] != null) {
                    String extractText = this.mDoc.extractText(i, convertToDocAreaFrom[0]);
                    return extractText + this.mDoc.extractText(i + 1, convertToDocAreaFrom[1]);
                } else if (convertToDocAreaFrom[0] != null) {
                    return this.mDoc.extractText(i, convertToDocAreaFrom[0]);
                } else {
                    if (convertToDocAreaFrom[1] != null) {
                        return this.mDoc.extractText(i + 1, convertToDocAreaFrom[1]);
                    }
                }
            } else if (convertToDocAreaFrom[0] != null) {
                return this.mDoc.extractText(pageIdx, convertToDocAreaFrom[0]);
            }
            return "";
        }
        throw new SecurityException("You can't extract data in this PDFDocument. This PDFDocument has not permission.");
    }

    @Deprecated
    public Bitmap extractPatch(Rect rect, float f) {
        return extractPatch(new RectF((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom), f);
    }

    public Bitmap extractPatch(RectF rectF, float f) {
        if (canExtract()) {
            boolean isBilateral = isBilateral();
            RectF[] convertToDocAreaFrom = convertToDocAreaFrom(rectF, isBilateral);
            int pageIdx = getPageIdx();
            if (isBilateral) {
                int i = pageIdx - (pageIdx % 2);
                if (convertToDocAreaFrom[0] != null && convertToDocAreaFrom[1] != null) {
                    return combineBitmap(createPatchBitmap(convertToDocAreaFrom[0], i, f), createPatchBitmap(convertToDocAreaFrom[1], i + 1, f), false);
                }
                if (convertToDocAreaFrom[0] != null) {
                    return createPatchBitmap(convertToDocAreaFrom[0], i, f);
                }
                if (convertToDocAreaFrom[1] != null) {
                    return createPatchBitmap(convertToDocAreaFrom[1], i + 1, f);
                }
                return null;
            } else if (convertToDocAreaFrom[0] != null) {
                return createPatchBitmap(convertToDocAreaFrom[0], pageIdx, f);
            } else {
                return null;
            }
        } else {
            throw new SecurityException("You can't extract data in this PDFDocument. This PDFDocument has not permission.");
        }
    }

    public RectF[] convertToDocAreaFrom(RectF rectF) {
        return convertToDocAreaFrom(rectF, isBilateral());
    }

    private RectF[] convertToDocAreaFrom(RectF rectF, boolean z) {
        PointF calcCoordinateOfDocumentOnScreen = calcCoordinateOfDocumentOnScreen(new PointF(rectF.left, rectF.top));
        PointF calcCoordinateOfDocumentOnScreen2 = calcCoordinateOfDocumentOnScreen(new PointF(rectF.right, rectF.bottom));
        RectF[] rectFArr = new RectF[(z ? 2 : 1)];
        RectF rectF2 = new RectF(calcCoordinateOfDocumentOnScreen.x, calcCoordinateOfDocumentOnScreen.y, calcCoordinateOfDocumentOnScreen2.x, calcCoordinateOfDocumentOnScreen2.y);
        SparseArray<PageView> childViewList = this.mDisplay.getChildViewList();
        int pageIdx = getPageIdx();
        if (z) {
            int i = pageIdx - (pageIdx % 2);
            PageView pageView = childViewList.get(i);
            PageView pageView2 = childViewList.get(i + 1);
            float left = (float) pageView.getLeft();
            float pageRatio = getPageRatio();
            if (calcCoordinateOfDocumentOnScreen.x > (((float) pageView2.getLeft()) - left) / pageRatio) {
                rectF2.left -= (((float) pageView2.getLeft()) - left) / pageRatio;
                rectF2.right -= (((float) pageView2.getLeft()) - left) / pageRatio;
                rectFArr[1] = fitDocumentArea(rectF2, pageView2);
            } else if (calcCoordinateOfDocumentOnScreen2.x < (((float) pageView.getRight()) - left) / pageRatio) {
                rectFArr[0] = fitDocumentArea(rectF2, pageView);
            } else {
                RectF rectF3 = new RectF(fitDocumentArea(rectF2, pageView));
                rectF2.left -= (((float) pageView2.getLeft()) - left) / pageRatio;
                rectF2.right -= (((float) pageView2.getLeft()) - left) / pageRatio;
                RectF rectF4 = new RectF(fitDocumentArea(rectF2, pageView2));
                rectFArr[0] = rectF3;
                rectFArr[1] = rectF4;
            }
        } else {
            rectFArr[0] = fitDocumentArea(rectF2, childViewList.get(pageIdx));
        }
        return rectFArr;
    }

    public RectF convertToScreenAreaFrom(RectF[] rectFArr) {
        RectF rectF;
        RectF rectF2 = null;
        if (rectFArr == null) {
            return null;
        }
        SparseArray<PageView> childViewList = this.mDisplay.getChildViewList();
        int pageIdx = getPageIdx();
        RectF rectF3 = new RectF();
        if (isBilateral()) {
            int i = pageIdx - (pageIdx % 2);
            PageView pageView = childViewList.get(i);
            PageView pageView2 = childViewList.get(i + 1);
            if (rectFArr[0] == null && rectFArr[1] == null) {
                return rectF3;
            }
            float pageRatio = getPageRatio();
            if (rectFArr[0] != null) {
                rectF = new RectF();
                rectF.left = ((float) pageView.getLeft()) + (rectFArr[0].left * pageRatio);
                rectF.top = ((float) pageView.getTop()) + (rectFArr[0].top * pageRatio);
                rectF.right = rectF.left + (rectFArr[0].width() * pageRatio);
                rectF.bottom = rectF.top + (rectFArr[0].height() * pageRatio);
            } else {
                rectF = null;
            }
            if (rectFArr[1] != null) {
                rectF2 = new RectF();
                rectF2.left = ((float) pageView2.getLeft()) + (rectFArr[1].left * pageRatio);
                rectF2.top = ((float) pageView2.getTop()) + (rectFArr[1].top * pageRatio);
                rectF2.right = rectF2.left + (rectFArr[1].width() * pageRatio);
                rectF2.bottom = rectF2.top + (rectFArr[1].height() * pageRatio);
            }
            if (rectF == null) {
                return rectF2;
            }
            if (rectF2 == null) {
                return rectF;
            }
            rectF3.left = rectF.left;
            rectF3.top = rectF.top < rectF2.top ? rectF.top : rectF2.top;
            rectF3.right = rectF2.right;
            rectF3.bottom = rectF.bottom > rectF2.bottom ? rectF.bottom : rectF2.bottom;
        } else {
            PageView pageView3 = childViewList.get(pageIdx);
            RectF rectF4 = rectFArr[0];
            if (rectF4 == null) {
                return rectF3;
            }
            float pageRatio2 = getPageRatio();
            rectF3.left = ((float) pageView3.getLeft()) + (rectF4.left * pageRatio2);
            rectF3.top = ((float) pageView3.getTop()) + (rectF4.top * pageRatio2);
            rectF3.right = rectF3.left + (rectF4.width() * pageRatio2);
            rectF3.bottom = rectF3.top + (rectF4.height() * pageRatio2);
        }
        return rectF3;
    }

    @Deprecated
    public Bitmap extractPatch(Rect rect) {
        return extractPatch(new RectF((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom));
    }

    public Bitmap extractPatch(RectF rectF) {
        return extractPatch(rectF, 1.0f);
    }

    private Bitmap createPatchBitmap(RectF rectF, int i, float f) {
        RectF rectF2 = rectF;
        int i2 = i;
        PageView pageView = this.mDisplay.getChildViewList().get(i);
        int width = (int) (rectF.width() * f);
        int height = (int) (rectF.height() * f);
        if (width <= 0 || height <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, PlugPDF.bitmapConfig());
        this.mDoc.drawPage2(i, createBitmap, (int) ((((float) pageView.getWidth()) / getPageRatio()) * f), (int) ((((float) pageView.getHeight()) / getPageRatio()) * f), (int) (rectF2.left * f), (int) (rectF2.top * f), createBitmap.getWidth(), createBitmap.getHeight());
        return createBitmap;
    }

    private RectF fitDocumentArea(RectF rectF, PageView pageView) {
        float annotScale = pageView.getAnnotScale();
        float width = ((float) pageView.getWidth()) / annotScale;
        float height = ((float) pageView.getHeight()) / annotScale;
        float f = 0.0f;
        float f2 = rectF.left < 0.0f ? 0.0f : rectF.left;
        if (rectF.top >= 0.0f) {
            f = rectF.top;
        }
        if (rectF.right <= width) {
            width = rectF.right;
        }
        if (rectF.bottom <= height) {
            height = rectF.bottom;
        }
        return new RectF(f2, f, width, height);
    }

    private Bitmap combineBitmap(Bitmap bitmap, Bitmap bitmap2, boolean z) {
        Bitmap bitmap3;
        if (bitmap == null) {
            return bitmap2;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        new BitmapFactory.Options().inDither = true;
        if (z) {
            bitmap3 = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight() + bitmap2.getHeight(), true);
        } else {
            bitmap3 = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() + bitmap2.getWidth(), bitmap.getHeight(), true);
        }
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setFlags(1);
        Canvas canvas = new Canvas(bitmap3);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (z) {
            canvas.drawBitmap(bitmap2, 0.0f, (float) bitmap.getHeight(), paint);
        } else {
            canvas.drawBitmap(bitmap2, (float) bitmap.getWidth(), 0.0f, paint);
        }
        bitmap.recycle();
        bitmap2.recycle();
        return bitmap3;
    }

    @Deprecated
    public PointF calcCoordinateOfDocumentOnScreen(Point point) {
        return calcCoordinateOfDocumentOnScreen(new PointF((float) point.x, (float) point.y));
    }

    public PointF calcCoordinateOfDocumentOnScreen(PointF pointF) {
        PageView pageView;
        int[] iArr = new int[2];
        getRootView().findViewById(16908290).getLocationInWindow(iArr);
        int i = iArr[1];
        int[] iArr2 = new int[2];
        getLocationInWindow(iArr2);
        iArr2[1] = iArr2[1] - i;
        if (isBilateral()) {
            int pageIdx = getPageIdx();
            pageView = this.mDisplay.getChildViewList().get(pageIdx - (pageIdx % 2));
        } else {
            pageView = this.mDisplay.getPageView();
        }
        float annotScale = pageView.getAnnotScale();
        return new PointF(((pointF.x - ((float) iArr2[0])) - ((float) pageView.getLeft())) / annotScale, ((pointF.y - ((float) iArr2[1])) - ((float) pageView.getTop())) / annotScale);
    }

    @Deprecated
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay != null) {
            basePlugPDFDisplay.setOnLongClickListener(onLongClickListener);
        }
    }

    public boolean isBilateral() {
        return this.isBilateralMode && getResources().getConfiguration().orientation == 2;
    }

    public void insertImageWatermark(int i, RectF rectF, Bitmap bitmap, double d, double d2) {
        insertImageWatermark(new int[]{i}, rectF, bitmap, d, d2);
    }

    public void insertImageWatermark(int[] iArr, RectF rectF, Bitmap bitmap, double d, double d2) {
        this.mDoc.insertImageWatermark(iArr, rectF, bitmap, d, d2);
        SparseArray<PageView> childViewList = this.mDisplay.getChildViewList();
        for (int i = 0; i < childViewList.size(); i++) {
            PageView valueAt = childViewList.valueAt(i);
            valueAt.cancelAdjustPatch();
            valueAt.drawEntire();
        }
    }

    public void insertTextWatermark(int i, PointF pointF, String str, int i2, double d, double d2) {
        insertTextWatermark(new int[]{i}, pointF, str, i2, d, d2);
    }

    public void insertTextWatermark(int i, PointF pointF, String str, String str2, int i2, double d, double d2) {
        insertTextWatermark(new int[]{i}, pointF, str, str2, i2, d, d2);
    }

    public void insertTextWatermark(int[] iArr, PointF pointF, String str, int i, double d, double d2) {
        insertTextWatermark(iArr, pointF, str, "DroidSans", i, d, d2);
    }

    public void insertTextWatermark(int[] iArr, PointF pointF, String str, String str2, int i, double d, double d2) {
        this.mDoc.insertTextWatermark(iArr, pointF, str, str2, i, d, d2);
        SparseArray<PageView> childViewList = this.mDisplay.getChildViewList();
        for (int i2 = 0; i2 < childViewList.size(); i2++) {
            PageView valueAt = childViewList.valueAt(i2);
            valueAt.cancelAdjustPatch();
            valueAt.drawEntire();
        }
    }

    public void insertVideo(int i, String str, RectF rectF, String str2, RectF rectF2, String str3) {
        this.mDoc.insertVideo(i, str, rectF, str2, rectF2, str3);
        SparseArray<PageView> childViewList = this.mDisplay.getChildViewList();
        for (int i2 = 0; i2 < childViewList.size(); i2++) {
            childViewList.valueAt(i2).prepareAnnot();
        }
    }

    public JSONArray getFieldListAsJSONArray() throws JSONException {
        return getDocument().getFieldListAsJSONArray();
    }

    public float getPageRatio() {
        BasePlugPDFDisplay basePlugPDFDisplay = this.mDisplay;
        if (basePlugPDFDisplay == null || basePlugPDFDisplay.getPageView() == null) {
            return 1.0f;
        }
        return this.mDisplay.getPageView().getAnnotScale();
    }

    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        this.mDisplay.setBackgroundColor(i);
    }

    public BaseAnnotTool getAnnotToolType() {
        return this.mDisplay.mAnnotTool;
    }

    public void setSearchFinishAlertBuilder(AlertDialog.Builder builder) {
        this.mSearchFinishAlertBuilder = builder;
    }

    public void setSearchProgressDialog(ProgressDialog progressDialog) {
        this.mSearchProgressDialog = progressDialog;
    }
}
