package com.epapyrus.plugpdf.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.epapyrus.plugpdf.core.PlugPDFException;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.acroform.FieldProperty;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PDFDocument {
    private static final int PERM_COPYCONTENT = 4;
    private static final int PERM_DOCUMENTASSEMBLY = 10;
    private static final int PERM_EXTRACT = 9;
    private static final int PERM_FILLFILED = 8;
    private static final int PERM_MODIFYANNOT = 5;
    private static final int PERM_MODIFYCONTENT = 3;
    private static final int PERM_PRINT = 2;
    private static String assetFontPath = null;
    private static Context mContext = null;
    private static int mMaxAnnotCount = 300;
    private static String substitueFont;
    private String alphanum = "0123456789!@#$%^&*ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private String mFilePath = null;
    private long mPDFHandle = 0;
    private int mPageCount = -1;
    private float mPageHeight;
    private int mPageIdx = -1;
    private float mPageWidth;
    private ArrayList mRecentPageIdxList = new ArrayList();
    private boolean releasedDoc;
    private boolean wasEdited;

    private static native void addScribbleContentOverInternal(long j, int i, PointF[][] pointFArr, float[][] fArr, float f, float f2, float f3, float f4, float f5, int i2);

    private static native int countAnnotsInternal(long j, int i);

    private static native int countPagesInternal(long j);

    public static native void createEmptyDocument(String str, int i, int i2);

    private static native long createPdfInternal();

    private static native long createSignatureFieldInternal(String str, String str2, String str3, String str4, int i, String str5, String str6, String str7, StringBuffer stringBuffer);

    private static native Bitmap drawAnnotAP(long j, int i, int i2, int i3, double d);

    private static native void drawCurruntPageInternal(long j, Bitmap bitmap, int i, int i2, int i3, int i4, int i5, int i6, String str, double d);

    private static native void drawPageInternal(long j, int i, Bitmap bitmap, int i2, int i3, int i4, int i5, int i6, int i7, String str, double d);

    private static native void enableAnnotFeatureInternal(String str, boolean z);

    private static native void exportAnnotToXFDFInternal(long j, String str);

    private static native String extractTextFromStartToEndInternal(long j, int i, RectF rectF, RectF rectF2);

    private static native String extractTextInternal(long j, int i, RectF rectF);

    private static native ArrayList<RectF> extractTextRectsInternal(long j, int i, RectF rectF, boolean z, boolean z2, boolean z3);

    private static native boolean flattenAnnotsInternal(long j, boolean z);

    private static native boolean flattenFormFieldsInternal(long j, boolean z);

    private static native int getEncrypt(long j);

    private native String getInfoItemInternal(long j, String str);

    private static native String getLastErrorMsgInternal(long j);

    private static native byte[] getPDFDataInternal(long j, int i, int i2);

    private static native float getPageHeightInternal(long j, int i);

    private static native String getPageText(long j, int i);

    private static native float getPageWidthInternal(long j, int i);

    private static native void gotoPageInternal(long j, int i);

    private native boolean hasOwnerPermission(long j);

    private static native void importAnnotFromXFDFInternal(long j, String str);

    private static native int injectImageInternal(String str, String str2, String str3, int i, String str4, double d, double d2, double d3);

    private static native int injectSignatureDataInternal(String str, byte[] bArr, long j);

    private static native int injectSignatureFileInternal(String str, String str2, long j);

    private static native void insertAlphaImageToWidgetInternal(long j, int i, int i2, int i3, int i4, int i5, byte[] bArr);

    private static native BaseAnnot insertCircleAnnotInternal(long j, int i, RectF rectF, float f, float f2, float f3, float f4, float f5, float f6, boolean z, float f7, int i2, String str);

    private static native BaseAnnot insertFileAnnotInternal(long j, int i, PointF pointF, String str, byte[] bArr, int i2, int i3, String str2);

    private static native BaseAnnot insertFreeTextInternal(long j, int i, PointF pointF, String str, String str2, double d, double d2, double d3, int i2, int i3, int i4, RectF rectF, String str3);

    private static native void insertImageToWidgetInternal(long j, int i, int i2, int i3, int i4, byte[] bArr);

    private static native void insertImageWatermarkInternal(long j, int[] iArr, RectF rectF, byte[] bArr, int i, int i2, double d, double d2);

    private static native BaseAnnot insertInkAnnotInternal(long j, int i, PointF[][] pointFArr, float f, float f2, float f3, float f4, float f5, String str);

    private static native BaseAnnot insertLineAnnotInternal(long j, int i, PointF pointF, PointF pointF2, float f, float f2, float f3, float f4, int i2, String str);

    private static native BaseAnnot insertLinkAnnotInternal(long j, int i, RectF rectF, int i2, String str, String str2);

    private static native BaseAnnot insertNoteAnnotInternal(long j, int i, PointF pointF, String str, byte[] bArr, int i2, int i3, String str2, String str3, String str4);

    private static native BaseAnnot insertSquareAnnotInternal(long j, int i, RectF rectF, float f, float f2, float f3, float f4, float f5, float f6, boolean z, float f7, int i2, String str);

    private static native BaseAnnot insertStampAnnotInternal(long j, int i, RectF rectF, float f, byte[] bArr, int i2, int i3, String str);

    private static native BaseAnnot insertTextMarkupAnnotInternal(long j, int i, RectF[] rectFArr, float f, float f2, float f3, String str, String str2);

    private static native void insertTextWatermarkInternal(long j, int[] iArr, PointF pointF, String str, String str2, int i, double d, double d2);

    private static native void insertVideoInternal(long j, int i, String str, RectF rectF, String str2, RectF rectF2, String str3, String str4);

    private static native BaseAnnot[] loadAnnotListInternal(long j, int i, int i2, int i3);

    private static native FieldProperty loadFieldInternal(long j, int i, String str);

    private static native FieldProperty[] loadFieldListInternal(long j, int i);

    private static native void loadRichMediaFileInternal(long j, int i, int i2, int i3, String str);

    private static native int mergePdfFileInternal(String[] strArr, String str, String str2);

    private static native long openData(byte[] bArr, String str);

    private static native long openFile(String str, String str2);

    private static native long openJetStream(String str, int i, String str2, String str3, String str4, String str5);

    private static native long openStream(InputStream inputStream, int i, String str);

    private static native int outlineCountInternal(long j);

    private static native OutlineItem[] outlineInternal(long j, int i);

    private static native float pageHeightInternal(long j);

    private static native float pageWidthInternal(long j);

    private static native void pdfInit();

    private static native int readDigestTokenInternal(String str, String str2, String str3, int i, String str4, byte[] bArr);

    private static native void releaseInternal(long j);

    private static native void removeAnnotApInternal(long j, int i, int i2, int i3);

    private static native void removeAnnotInternal(long j, int i, int i2);

    private static native boolean saveAsFileInternal(long j, String str);

    private static native boolean saveAsIncrementallyInternal(long j, String str);

    private static native RectF[] searchPageInternal(long j, int i, String str, boolean z);

    public static native void setCMapPath(String str);

    private static native boolean setEncrypt(long j, String str, String str2, int i);

    private static native void setFieldValueInternal(long j, int i, int i2, String str, boolean z);

    public static native void setFontMapInternal(String str);

    private static native void setFontPathInternal(String str);

    private native void setInfoItemInternal(long j, String str, String str2);

    private static native FieldProperty[] signatureWidgetsPropertiesInternal(String str, String str2);

    private static native void transplantPageInternal(long j, long j2, int i);

    private static native BaseAnnot updateCircleAnnotInternal(long j, int i, RectF rectF, float f, float f2, float f3, float f4, float f5, float f6, boolean z, float f7, int i2, int i3);

    private static native boolean updateFileAnnotInternal(long j, int i, int i2, String str);

    private static native BaseAnnot updateFreeTextInternal(long j, int i, PointF pointF, String str, String str2, double d, double d2, double d3, int i2, int i3, int i4, RectF rectF, int i5);

    private static native boolean updateInkAnnotColorInternal(long j, int i, int i2, float f, float f2, float f3);

    private static native boolean updateInkAnnotInternal(long j, int i, PointF[][] pointFArr, float f, float f2, float f3, float f4, float f5, int i2);

    private static native boolean updateInkAnnotLineWidthInternal(long j, int i, int i2, float f);

    private static native boolean updateInkAnnotOpacityInternal(long j, int i, int i2, float f);

    private static native BaseAnnot updateLineAnnotInternal(long j, int i, PointF pointF, PointF pointF2, float f, float f2, float f3, float f4, int i2, int i3);

    private static native boolean updateNoteAnnotInternal(long j, int i, int i2, String str, String str2, String str3, PointF pointF);

    private static native int updateOutlineInternal(long j, OutlineItem[] outlineItemArr);

    private static native BaseAnnot updateSquareAnnotInternal(long j, int i, RectF rectF, float f, float f2, float f3, float f4, float f5, float f6, boolean z, float f7, int i2, int i3);

    private static native BaseAnnot updateStampAnnotInternal(long j, int i, RectF rectF, float f, int i2);

    public static native LicenseInfo validateLicense(String str, String str2, String str3);

    private static native void writeSoundFileInternal(long j, int i, int i2, int i3, String str);

    public static void pdfInit(Context context) {
        mContext = context;
        pdfInit();
    }

    public void release() {
        releaseInternal(getPDFHandle());
        this.releasedDoc = true;
    }

    public boolean wasReleased() {
        return this.releasedDoc;
    }

    public boolean wasEdited() {
        return this.wasEdited;
    }

    public static void setSubstitueFont(String str) {
        substitueFont = str;
    }

    public static String getSubstitueFont() {
        return substitueFont;
    }

    public void insertRecentPageIdx(int i) {
        this.mRecentPageIdxList.add(Integer.valueOf(i));
    }

    public ArrayList getRecentPageIdxList() {
        return this.mRecentPageIdxList;
    }

    private long getPDFHandle() {
        if (!wasReleased()) {
            long j = this.mPDFHandle;
            if (j != 0) {
                return j;
            }
            throw new RuntimeException("did not yet try to open the document.");
        }
        throw new RuntimeException("this document was released.");
    }

    private String getRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb.append(this.alphanum.charAt(new Random().nextInt(this.alphanum.length())));
        }
        return sb.toString();
    }

    public String getLastErrorMsg() {
        return getLastErrorMsgInternal(getPDFHandle());
    }

    public PDFDocument(String str, String str2) throws Exception {
        File file = new File(str);
        if (!file.exists()) {
            throw new FileNotFoundException("PDF file Not Found. filename: " + str);
        } else if (file.canRead()) {
            long openFile = openFile(str, str2);
            this.mPDFHandle = openFile;
            if (openFile != 0) {
                String lastErrorMsg = getLastErrorMsg();
                if (!lastErrorMsg.equals("")) {
                    releaseInternal(this.mPDFHandle);
                    if (lastErrorMsg.equals("Wrong password")) {
                        throw new PlugPDFException.WrongPassword("input data wrong password. fileapth: " + str);
                    }
                    throw new Exception("Failed to open file (" + str + "). Because, " + lastErrorMsg.replace("(.*) at line .*", "$1"));
                }
                this.mPageCount = countPagesSynchronized();
                this.mFilePath = str;
                return;
            }
            throw new Exception("Failed to open. Must check license.");
        } else {
            throw new IOException("PDF file can't access. filename: " + str);
        }
    }

    public PDFDocument(byte[] bArr, int i, String str) throws Exception {
        long openData = openData(bArr, str);
        this.mPDFHandle = openData;
        if (openData != 0) {
            String lastErrorMsg = getLastErrorMsg();
            if (!lastErrorMsg.equals("")) {
                releaseInternal(this.mPDFHandle);
                if (lastErrorMsg.equals("Wrong password")) {
                    throw new PlugPDFException.WrongPassword("input data wrong password");
                }
                throw new Exception("Failed to open from data. Because, " + lastErrorMsg.replace("(.*) at line .*", "$1"));
            }
            this.mPageCount = countPagesSynchronized();
            this.mFilePath = null;
            return;
        }
        throw new Exception("Failed to open. Must check license.");
    }

    public PDFDocument(String str, int i, String str2, String str3) throws Exception {
        long openJetStream = openJetStream(str, i, str2, str3, mContext.getCacheDir().getAbsolutePath(), "/jss/jetStream.service");
        this.mPDFHandle = openJetStream;
        if (openJetStream != 0) {
            String lastErrorMsg = getLastErrorMsg();
            if (!lastErrorMsg.equals("")) {
                releaseInternal(this.mPDFHandle);
                if (lastErrorMsg.equals("Wrong password")) {
                    throw new PlugPDFException.WrongPassword("input data wrong password filepath: " + str + ":" + i + "/jss/jetStream.service/" + str2);
                } else if (lastErrorMsg.equals("Connect failed")) {
                    throw new PlugPDFException.JetStreamConnectionError("connection error");
                } else {
                    throw new Exception("Failed to open. Because, " + lastErrorMsg.replace("(.*) at line .*", "$1"));
                }
            } else {
                this.mPageCount = countPagesSynchronized();
                this.mFilePath = null;
            }
        } else {
            throw new Exception("Failed to open. Must check license.");
        }
    }

    public PDFDocument(String str, int i, String str2, String str3, String str4) throws Exception {
        long openJetStream = openJetStream(str, i, str2, str3, mContext.getCacheDir().getAbsolutePath(), str4);
        this.mPDFHandle = openJetStream;
        if (openJetStream != 0) {
            String lastErrorMsg = getLastErrorMsg();
            if (!lastErrorMsg.equals("")) {
                releaseInternal(this.mPDFHandle);
                if (lastErrorMsg.equals("Wrong password")) {
                    throw new PlugPDFException.WrongPassword("input data wrong password filepath: " + str + ":" + i + str4 + "/" + str2);
                } else if (lastErrorMsg.equals("Connect failed")) {
                    throw new PlugPDFException.JetStreamConnectionError("connection error");
                } else {
                    throw new Exception("Failed to open. Because, " + lastErrorMsg.replace("(.*) at line .*", "$1"));
                }
            } else {
                this.mPageCount = countPagesSynchronized();
                this.mFilePath = null;
            }
        } else {
            throw new Exception("Failed to open. Must check license.");
        }
    }

    public PDFDocument(FileInputStream fileInputStream, int i, String str) throws Exception {
        FreelyInputStream freelyInputStream = new FreelyInputStream(fileInputStream, i);
        freelyInputStream.mark(0);
        long openStream = openStream(freelyInputStream, i, str);
        this.mPDFHandle = openStream;
        if (openStream != 0) {
            String lastErrorMsg = getLastErrorMsg();
            if (!lastErrorMsg.equals("")) {
                releaseInternal(this.mPDFHandle);
                if (lastErrorMsg.equals("Wrong password")) {
                    throw new PlugPDFException.WrongPassword("input data wrong password.");
                } else if (lastErrorMsg.equals("Connect failed")) {
                    throw new PlugPDFException.JetStreamConnectionError("connection error.");
                } else {
                    throw new Exception("Failed to open. Because," + lastErrorMsg.replace("(.*) at line .*", "$1"));
                }
            } else {
                this.mPageCount = countPagesSynchronized();
            }
        } else {
            throw new Exception("Failed to open. Must check license.");
        }
    }

    public PDFDocument() throws Exception {
        long createPdfInternal = createPdfInternal();
        this.mPDFHandle = createPdfInternal;
        if (createPdfInternal != 0) {
            this.mPageCount = 0;
            this.mFilePath = null;
            return;
        }
        throw new Exception("Failed to create PDF object. Must check license.");
    }

    public int getPageCount() {
        if (this.mPageCount < 0) {
            this.mPageCount = countPagesSynchronized();
        }
        return this.mPageCount;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public synchronized PointF getPageSize(int i) {
        if (i > this.mPageCount - 1) {
            i = this.mPageCount - 1;
        } else if (i < 0) {
            i = 0;
        }
        return new PointF(getPageWidthInternal(getPDFHandle(), i), getPageHeightInternal(getPDFHandle(), i));
    }

    public float getCurrentPageWidth() {
        return this.mPageWidth;
    }

    public float getCurrentPageHeight() {
        return this.mPageHeight;
    }

    public synchronized void drawPage(int i, Bitmap bitmap, int i2, int i3, int i4, int i5, int i6, int i7) {
        synchronized (this) {
            gotoPage(i);
            drawCurruntPageInternal(getPDFHandle(), bitmap, i2, i3, i4, i5, i6, i7, (String) null, (double) PropertyManager.getPageBackgroundOpacity());
            if (PropertyManager.getEnableNightMode()) {
                negativeImage(bitmap);
            }
        }
    }

    public synchronized void drawPage(int i, Bitmap bitmap, int i2, int i3, int i4, int i5, int i6, int i7, double d) {
        synchronized (this) {
            gotoPage(i);
            double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d2 = d;
            }
            drawCurruntPageInternal(getPDFHandle(), bitmap, i2, i3, i4, i5, i6, i7, (String) null, d2 > 255.0d ? 255.0d : d2);
            if (PropertyManager.getEnableNightMode()) {
                negativeImage(bitmap);
            }
        }
    }

    public synchronized void drawPage2(int i, Bitmap bitmap, int i2, int i3, int i4, int i5, int i6, int i7) {
        synchronized (this) {
            drawPageInternal(getPDFHandle(), i, bitmap, i2, i3, i4, i5, i6, i7, (String) null, 255.0d);
            if (PropertyManager.getEnableNightMode()) {
                negativeImage(bitmap);
            }
        }
    }

    public synchronized void savePDFAsPng(int i, PointF pointF, String str, int i2) {
        int i3 = i;
        PointF pointF2 = pointF;
        synchronized (this) {
            if (pointF2.equals(0.0f, 0.0f)) {
                Log.w("PlugPDF", "PageSize is zero");
                return;
            }
            if (i3 >= 0) {
                if (i3 <= getPageCount()) {
                    if (str == null) {
                        Log.w("PlugPDF", "Wrong path");
                        return;
                    }
                    int i4 = i2 < 0 ? 0 : i2;
                    if (i4 > 255) {
                        i4 = 255;
                    }
                    drawPageInternal(getPDFHandle(), i, (Bitmap) null, (int) pointF2.x, (int) pointF2.y, 0, 0, (int) pointF2.x, (int) pointF2.y, str, (double) i4);
                    return;
                }
            }
            Log.w("PlugPDF", "Wrong Page index" + i);
        }
    }

    public int getAnnotCount(int i) {
        return countAnnotsInternal(getPDFHandle(), i);
    }

    public synchronized BaseAnnot[] loadAnnotList(int i) {
        BaseAnnot[] baseAnnotArr;
        int annotCount = getAnnotCount(i);
        BaseAnnot[] baseAnnotArr2 = new BaseAnnot[annotCount];
        if (annotCount < 250) {
            baseAnnotArr = loadAnnotListInternal(getPDFHandle(), i, 0, annotCount);
        } else {
            BaseAnnot[] loadAnnotListInternal = loadAnnotListInternal(getPDFHandle(), i, 0, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            int i2 = annotCount / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            int i3 = 1;
            while (i3 < i2) {
                long pDFHandle = getPDFHandle();
                int i4 = i3 * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
                i3++;
                BaseAnnot[] loadAnnotListInternal2 = loadAnnotListInternal(pDFHandle, i, i4, i3 * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                for (int i5 = 0; i5 < 250; i5++) {
                    loadAnnotListInternal[i4 + i5] = loadAnnotListInternal2[i5];
                }
            }
            long pDFHandle2 = getPDFHandle();
            int i6 = i2 * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            BaseAnnot[] loadAnnotListInternal3 = loadAnnotListInternal(pDFHandle2, i, i6, annotCount);
            for (int i7 = 0; i7 < annotCount - i6; i7++) {
                loadAnnotListInternal[i6 + i7] = loadAnnotListInternal3[i7];
            }
            baseAnnotArr = loadAnnotListInternal;
        }
        return baseAnnotArr;
    }

    public synchronized void removeAnnot(int i, int i2) {
        removeAnnotInternal(getPDFHandle(), i, i2);
        this.wasEdited = true;
    }

    public synchronized BaseAnnot insertLineAnnot(int i, PointF pointF, PointF pointF2, int i2, int i3, int i4, int i5, float f) {
        synchronized (this) {
            if (!canModifyAnnot()) {
                throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
            } else if (getAnnotCount(i) > mMaxAnnotCount) {
                Log.w("PlugPDF", "You cannot insert annotation anymore in this page. (page=" + i + ")");
                return null;
            } else {
                int i6 = i;
                this.wasEdited = true;
                BaseAnnot insertLineAnnotInternal = insertLineAnnotInternal(getPDFHandle(), i, pointF, pointF2, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, (int) f, getRandomString());
                return insertLineAnnotInternal;
            }
        }
    }

    public synchronized BaseAnnot updateLineAnnot(int i, PointF pointF, PointF pointF2, int i2, int i3, int i4, int i5, float f, int i6) {
        BaseAnnot updateLineAnnotInternal;
        synchronized (this) {
            if (canModifyAnnot()) {
                this.wasEdited = true;
                updateLineAnnotInternal = updateLineAnnotInternal(getPDFHandle(), i, pointF, pointF2, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, (int) f, i6);
            } else {
                throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
            }
        }
        return updateLineAnnotInternal;
    }

    public synchronized BaseAnnot insertInkAnnot(int i, PointF[][] pointFArr, int i2, int i3, int i4, int i5, float f) {
        return insertInkAnnot(i, pointFArr, i2, i3, i4, i5, f, getRandomString());
    }

    public synchronized BaseAnnot insertInkAnnot(int i, PointF[][] pointFArr, int i2, int i3, int i4, int i5, float f, String str) {
        synchronized (this) {
            if (!canModifyAnnot()) {
                throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
            } else if (getAnnotCount(i) > mMaxAnnotCount) {
                StringBuilder sb = new StringBuilder();
                sb.append("You cannot insert annotation anymore in this page. (page=");
                int i6 = i;
                sb.append(i);
                sb.append(")");
                Log.w("PlugPDF", sb.toString());
                return null;
            } else {
                int i7 = i;
                this.wasEdited = true;
                BaseAnnot insertInkAnnotInternal = insertInkAnnotInternal(getPDFHandle(), i, pointFArr, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, f, str);
                return insertInkAnnotInternal;
            }
        }
    }

    public synchronized boolean updateInkAnnot(int i, PointF[][] pointFArr, int i2, int i3, int i4, int i5, float f, int i6) {
        boolean updateInkAnnotInternal;
        synchronized (this) {
            if (canModifyAnnot()) {
                this.wasEdited = true;
                updateInkAnnotInternal = updateInkAnnotInternal(getPDFHandle(), i, pointFArr, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, f, i6);
            } else {
                throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
            }
        }
        return updateInkAnnotInternal;
    }

    public synchronized boolean updateInkAnnotLineWidth(int i, int i2, float f) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        }
        return updateInkAnnotLineWidthInternal(getPDFHandle(), i, i2, f);
    }

    public synchronized boolean updateInkAnnotOpacity(int i, int i2, float f) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        }
        return updateInkAnnotOpacityInternal(getPDFHandle(), i, i2, f / 255.0f);
    }

    public synchronized boolean updateInkAnnotColor(int i, int i2, int i3, int i4, int i5) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        }
        return updateInkAnnotColorInternal(getPDFHandle(), i, i2, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f);
    }

    public synchronized BaseAnnot insertNoteAnnot(int i, PointF pointF, String str, String str2) {
        return insertNoteAnnot(i, pointF, str, str2, getRandomString());
    }

    public synchronized BaseAnnot insertFileAnnot(int i, PointF pointF, String str) {
        return insertFileAnnot(i, pointF, str, getRandomString());
    }

    public BaseAnnot insertFreeText(int i, PointF pointF, String str, String str2, double d, double d2, double d3, int i2, int i3, int i4, RectF rectF) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
            return insertFreeTextInternal(getPDFHandle(), i, pointF, str, str2, d, d2, d3, i2, i3, i4, rectF, getRandomString());
        }
        throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
    }

    public BaseAnnot updateFreeText(int i, PointF pointF, String str, String str2, double d, double d2, double d3, int i2, int i3, int i4, RectF rectF, int i5) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
            return updateFreeTextInternal(getPDFHandle(), i, pointF, str, str2, d, d2, d3, i2, i3, i4, rectF, i5);
        }
        throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
    }

    public synchronized BaseAnnot insertNoteAnnot(int i, PointF pointF, String str, String str2, String str3) {
        synchronized (this) {
            if (!canModifyAnnot()) {
                throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
            } else if (getAnnotCount(i) > mMaxAnnotCount) {
                Log.w("PlugPDF", "You cannot insert annotation anymore in this page. (page=" + i + ")");
                return null;
            } else {
                int i2 = i;
                this.wasEdited = true;
                String format = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance().getTime());
                Bitmap enableCustomNoteIcon = PropertyManager.getEnableCustomNoteIcon();
                if (enableCustomNoteIcon == null) {
                    BaseAnnot insertNoteAnnotInternal = insertNoteAnnotInternal(getPDFHandle(), i, pointF, str, (byte[]) null, 0, 0, str2, format, str3);
                    return insertNoteAnnotInternal;
                }
                int i3 = i;
                PointF pointF2 = pointF;
                String str4 = str;
                BaseAnnot insertNoteAnnotInternal2 = insertNoteAnnotInternal(getPDFHandle(), i3, pointF2, str4, bitmap2ByteArray(enableCustomNoteIcon), enableCustomNoteIcon.getWidth(), enableCustomNoteIcon.getHeight(), str2, format, str3);
                return insertNoteAnnotInternal2;
            }
        }
    }

    public synchronized BaseAnnot insertFileAnnot(int i, PointF pointF, String str, String str2) {
        if (!canModifyAnnot()) {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        } else if (getAnnotCount(i) > mMaxAnnotCount) {
            Log.w("PlugPDF", "You cannot insert annotation anymore in this page. (page=" + i + ")");
            return null;
        } else {
            this.wasEdited = true;
            return insertFileAnnotInternal(getPDFHandle(), i, pointF, str, (byte[]) null, 0, 0, str2);
        }
    }

    public synchronized BaseAnnot insertTextMarkupAnnot(int i, RectF[] rectFArr, float f, float f2, float f3, String str) {
        return insertTextMarkupAnnot(i, rectFArr, f, f2, f3, str, getRandomString());
    }

    public synchronized BaseAnnot insertTextMarkupAnnot(int i, RectF[] rectFArr, float f, float f2, float f3, String str, String str2) {
        BaseAnnot insertTextMarkupAnnotInternal;
        RectF[] rectFArr2 = rectFArr;
        String str3 = str;
        synchronized (this) {
            if (BaseAnnotTool.AnnotToolType.HIGHLIGHT.name().equalsIgnoreCase(str3)) {
                str3 = "Highlight";
            } else if (BaseAnnotTool.AnnotToolType.UNDERLINE.name().equalsIgnoreCase(str3)) {
                str3 = "Underline";
            } else if (BaseAnnotTool.AnnotToolType.SQUIGGLY.name().equalsIgnoreCase(str3)) {
                str3 = "Squiggly";
            } else if (BaseAnnotTool.AnnotToolType.STRIKEOUT.name().equalsIgnoreCase(str3)) {
                str3 = "StrikeOut";
            }
            String str4 = str3;
            if (canModifyAnnot()) {
                this.wasEdited = true;
                int length = rectFArr2.length / 200;
                for (int i2 = 0; i2 < length + 1; i2++) {
                    if (getAnnotCount(i) + Math.min(rectFArr2.length - (200 * i2), 200) > mMaxAnnotCount) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("You cannot insert annotation anymore in this page. (page=");
                        int i3 = i;
                        sb.append(i);
                        sb.append(")");
                        Log.w("PlugPDF", sb.toString());
                    } else {
                        int i4 = i;
                    }
                }
                int i5 = i;
                insertTextMarkupAnnotInternal = insertTextMarkupAnnotInternal(getPDFHandle(), i, rectFArr, f / 255.0f, f2 / 255.0f, f3 / 255.0f, str4, str2);
            } else {
                throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
            }
        }
        return insertTextMarkupAnnotInternal;
    }

    public synchronized BaseAnnot insertLinkAnnot(int i, RectF rectF, int i2, String str) {
        return insertLinkAnnot(i, rectF, i2, str, getRandomString());
    }

    public synchronized BaseAnnot insertLinkAnnot(int i, RectF rectF, int i2, String str, String str2) {
        if (!canModifyAnnot()) {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        } else if (getAnnotCount(i) > mMaxAnnotCount) {
            Log.w("PlugPDF", "You cannot insert annotation anymore in this page. (page=" + i + ")");
            return null;
        } else {
            this.wasEdited = true;
            return insertLinkAnnotInternal(getPDFHandle(), i, rectF, i2, str, str2);
        }
    }

    public BaseAnnot insertSquareAnnot(int i, RectF rectF, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8, int i9) {
        return insertSquareAnnot(i, rectF, i2, i3, i4, i5, i6, i7, z, i8, i9, getRandomString());
    }

    public BaseAnnot insertSquareAnnot(int i, RectF rectF, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8, int i9, String str) {
        if (!canModifyAnnot()) {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        } else if (getAnnotCount(i) > mMaxAnnotCount) {
            Log.w("PlugPDF", "You cannot insert annotation anymore in this page. (page=" + i + ")");
            return null;
        } else {
            int i10 = i;
            this.wasEdited = true;
            return insertSquareAnnotInternal(getPDFHandle(), i, rectF, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, ((float) i6) / 255.0f, ((float) i7) / 255.0f, z, ((float) i8) / 255.0f, i9, str);
        }
    }

    public BaseAnnot updateSquareAnnot(int i, RectF rectF, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8, int i9, int i10) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
            return updateSquareAnnotInternal(getPDFHandle(), i, rectF, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, ((float) i6) / 255.0f, ((float) i7) / 255.0f, z, ((float) i8) / 255.0f, i9, i10);
        }
        throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
    }

    public BaseAnnot insertCircleAnnot(int i, RectF rectF, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8, int i9) {
        return insertCircleAnnot(i, rectF, i2, i3, i4, i5, i6, i7, z, i8, i9, getRandomString());
    }

    public BaseAnnot insertCircleAnnot(int i, RectF rectF, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8, int i9, String str) {
        if (!canModifyAnnot()) {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        } else if (getAnnotCount(i) > mMaxAnnotCount) {
            Log.w("PlugPDF", "You cannot insert annotation anymore in this page. (page=" + i + ")");
            return null;
        } else {
            int i10 = i;
            this.wasEdited = true;
            return insertCircleAnnotInternal(getPDFHandle(), i, rectF, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, ((float) i6) / 255.0f, ((float) i7) / 255.0f, z, ((float) i8) / 255.0f, i9, str);
        }
    }

    public BaseAnnot updateCircleAnnot(int i, RectF rectF, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8, int i9, int i10) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
            return updateCircleAnnotInternal(getPDFHandle(), i, rectF, ((float) i2) / 255.0f, ((float) i3) / 255.0f, ((float) i4) / 255.0f, ((float) i5) / 255.0f, ((float) i6) / 255.0f, ((float) i7) / 255.0f, z, ((float) i8) / 255.0f, i9, i10);
        }
        throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
    }

    public BaseAnnot insertStampAnnot(int i, RectF rectF, int i2, Bitmap bitmap) {
        return insertStampAnnot(i, rectF, i2, bitmap, getRandomString());
    }

    public BaseAnnot insertStampAnnot(int i, RectF rectF, int i2, Bitmap bitmap, String str) {
        if (!canModifyAnnot()) {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        } else if (getAnnotCount(i) > mMaxAnnotCount) {
            StringBuilder sb = new StringBuilder();
            sb.append("You cannot insert annotation anymore in this page. (page=");
            int i3 = i;
            sb.append(i);
            sb.append(")");
            Log.w("PlugPDF", sb.toString());
            return null;
        } else {
            int i4 = i;
            this.wasEdited = true;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Rect rect = new Rect(0, 0, width, height);
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawBitmap(bitmap, rect, rect, (Paint) null);
            byte[] bArr = new byte[(width * height * 4)];
            createBitmap.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
            createBitmap.recycle();
            return insertStampAnnotInternal(getPDFHandle(), i, rectF, ((float) i2) / 255.0f, bArr, width, height, str);
        }
    }

    public BaseAnnot updateStampAnnot(int i, RectF rectF, int i2, int i3) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
            return updateStampAnnotInternal(getPDFHandle(), i, rectF, ((float) i2) / 255.0f, i3);
        }
        throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
    }

    @Deprecated
    public void insertImageToPage(int i, RectF rectF, Bitmap bitmap) {
        insertImageWatermark(new int[]{i}, rectF, bitmap, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d);
    }

    public void insertImageWatermark(int[] iArr, RectF rectF, Bitmap bitmap, double d, double d2) {
        if (canModifyContent()) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Rect rect = new Rect(0, 0, width, height);
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Bitmap bitmap2 = bitmap;
            new Canvas(createBitmap).drawBitmap(bitmap, rect, rect, (Paint) null);
            byte[] bArr = new byte[(width * height * 4)];
            createBitmap.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
            createBitmap.recycle();
            insertImageWatermarkInternal(getPDFHandle(), iArr, rectF, bArr, width, height, d, d2);
            this.wasEdited = true;
            return;
        }
        throw new SecurityException("You can't modify contents in this PDFDocument. This PDFDocument has not permission.");
    }

    public void insertTextWatermark(int[] iArr, PointF pointF, String str, int i, double d, double d2) {
        if (canModifyContent()) {
            insertTextWatermarkInternal(getPDFHandle(), iArr, pointF, str, "DroidSans", i, d, d2);
            this.wasEdited = true;
            return;
        }
        throw new SecurityException("You can't modify contents in this PDFDocument. This PDFDocument has not permission.");
    }

    public void insertTextWatermark(int[] iArr, PointF pointF, String str, String str2, int i, double d, double d2) {
        if (canModifyContent()) {
            insertTextWatermarkInternal(getPDFHandle(), iArr, pointF, str, str2, i, d, d2);
            this.wasEdited = true;
            return;
        }
        throw new SecurityException("You can't modify contents in this PDFDocument. This PDFDocument has not permission.");
    }

    public synchronized void insertImageToWidget(int i, int i2, int i3, int i4, byte[] bArr) {
        insertImageToWidgetInternal(getPDFHandle(), i, i2, i3, i4, bArr);
        this.wasEdited = true;
    }

    public synchronized void insertAlphaImageToWidget(int i, int i2, int i3, int i4, int i5, byte[] bArr) {
        insertAlphaImageToWidgetInternal(getPDFHandle(), i, i2, i3, i4, i5, bArr);
        this.wasEdited = true;
    }

    public void insertVideo(int i, String str, RectF rectF, String str2, RectF rectF2, String str3) {
        insertVideoInternal(getPDFHandle(), i, str, rectF, str2, rectF2, str3, getRandomString());
        this.wasEdited = true;
    }

    public void insertVideo(int i, String str, RectF rectF, String str2, RectF rectF2, String str3, String str4) {
        insertVideoInternal(getPDFHandle(), i, str, rectF, str2, rectF2, str3, str4);
        this.wasEdited = true;
    }

    public synchronized boolean updateNoteAnnot(int i, int i2, String str, String str2, PointF pointF) {
        String format;
        if (canModifyAnnot()) {
            format = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance().getTime());
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        }
        return updateNoteAnnotInternal(getPDFHandle(), i, i2, str, str2, format, pointF);
    }

    public synchronized boolean updateFileAnnot(int i, int i2, String str) {
        if (canModifyAnnot()) {
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't modify annotation in this PDFDocument. This PDFDocument has not permission.");
        }
        return updateFileAnnotInternal(getPDFHandle(), i, i2, str);
    }

    public static synchronized void enableAnnotFeature(String str, boolean z) {
        synchronized (PDFDocument.class) {
            enableAnnotFeatureInternal(str, z);
        }
    }

    public synchronized FieldProperty loadField(int i, String str) {
        return loadFieldInternal(getPDFHandle(), i, str);
    }

    public synchronized FieldProperty[] loadFieldList(int i) {
        return loadFieldListInternal(getPDFHandle(), i);
    }

    public synchronized JSONArray getFieldListAsJSONArray() throws JSONException {
        JSONArray jSONArray;
        jSONArray = new JSONArray();
        for (int i = 0; i < getPageCount(); i++) {
            JSONArray jSONArray2 = new JSONArray();
            FieldProperty[] loadFieldListInternal = loadFieldListInternal(getPDFHandle(), i);
            HashMap hashMap = new HashMap();
            if (loadFieldListInternal != null) {
                for (int i2 = 0; i2 < loadFieldListInternal.length; i2++) {
                    if (loadFieldListInternal[i2].getType() == 2) {
                        if (!hashMap.containsKey(loadFieldListInternal[i2].getTitle())) {
                            hashMap.put(loadFieldListInternal[i2].getTitle(), AppEventsConstants.EVENT_PARAM_VALUE_NO);
                        }
                        if (loadFieldListInternal[i2].getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED).equals("On")) {
                            hashMap.put(loadFieldListInternal[i2].getTitle(), loadFieldListInternal[i2].getValue("AP_N"));
                        }
                    } else {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("title", loadFieldListInternal[i2].getTitle());
                        if (loadFieldListInternal[i2].getType() == 1) {
                            if (loadFieldListInternal[i2].getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED).equals("On")) {
                                jSONObject.put("value", 1);
                            } else if (loadFieldListInternal[i2].getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED).equals("Off")) {
                                jSONObject.put("value", 0);
                            } else {
                                jSONObject.put("value", loadFieldListInternal[i2].getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED));
                            }
                        } else if (loadFieldListInternal[i2].getType() == 4) {
                            Bitmap drawAnnotAP = drawAnnotAP(i, loadFieldListInternal[i2].getObjID(), 0, 1.0d);
                            if (Bitmap.createBitmap(drawAnnotAP.getWidth(), drawAnnotAP.getHeight(), drawAnnotAP.getConfig()).sameAs(drawAnnotAP)) {
                                jSONObject.put("value", "unsigned");
                            } else {
                                jSONObject.put("value", "signed");
                            }
                        } else {
                            jSONObject.put("value", loadFieldListInternal[i2].getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED));
                        }
                        jSONArray2.put(jSONObject);
                    }
                }
                for (String str : hashMap.keySet()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("title", str);
                    jSONObject2.put("value", hashMap.get(str));
                    jSONArray2.put(jSONObject2);
                }
            }
            jSONArray.put(jSONArray2);
        }
        return jSONArray;
    }

    public synchronized void setFieldValue(int i, int i2, String str, boolean z) {
        if (canFillField()) {
            setFieldValueInternal(getPDFHandle(), i, i2, str, z);
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't fill field in this PDFDocument. This PDFDocument has not permission.");
        }
    }

    public synchronized void setFieldValue(int i, int i2, String str) {
        if (canFillField()) {
            setFieldValue(i, i2, str, false);
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't fill field in this PDFDocument. This PDFDocument has not permission.");
        }
    }

    public synchronized RectF[] getSearchPage(int i, String str, boolean z) {
        if (z) {
            String removeAccentStr = removeAccentStr(str);
            if (removeAccentStr != null && !removeAccentStr.isEmpty()) {
                return searchPageInternal(getPDFHandle(), i, removeAccentStr, z);
            }
        }
        return searchPageInternal(getPDFHandle(), i, str, z);
    }

    public static String removeAccentStr(String str) {
        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(Normalizer.normalize(str, Normalizer.Form.NFD)).replaceAll("");
    }

    public static int removeAccent(int i) {
        return new BigInteger(Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(Normalizer.normalize(new String(Character.toChars(i)), Normalizer.Form.NFD)).replaceAll("").getBytes(Charset.forName("UTF-8"))).intValue();
    }

    public synchronized boolean hasOutline() {
        return outlineCountInternal(getPDFHandle()) > 0;
    }

    public synchronized List<OutlineItem> getOutline() {
        int outlineCountInternal = outlineCountInternal(getPDFHandle());
        if (outlineCountInternal <= 0) {
            return null;
        }
        OutlineItem[] outlineItemArr = new OutlineItem[outlineCountInternal];
        int i = outlineCountInternal / 500;
        for (int i2 = 0; i2 < i + 1; i2++) {
            int i3 = 500 * i2;
            OutlineItem[] outlineInternal = outlineInternal(getPDFHandle(), i3);
            if (outlineInternal != null) {
                System.arraycopy(outlineInternal, 0, outlineItemArr, i3, outlineInternal.length);
            }
        }
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, outlineItemArr);
        return arrayList;
    }

    public synchronized void updateOutline(OutlineItem[] outlineItemArr) {
        updateOutlineInternal(getPDFHandle(), outlineItemArr);
        this.wasEdited = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009d, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String saveFile() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.wasEdited()     // Catch:{ all -> 0x009e }
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = r4.mFilePath     // Catch:{ all -> 0x009e }
            monitor-exit(r4)
            return r0
        L_0x000b:
            boolean r0 = r4.canModifyContent()     // Catch:{ all -> 0x009e }
            if (r0 != 0) goto L_0x002c
            boolean r0 = r4.canModifyAnnot()     // Catch:{ all -> 0x009e }
            if (r0 != 0) goto L_0x002c
            boolean r0 = r4.canFillField()     // Catch:{ all -> 0x009e }
            if (r0 != 0) goto L_0x002c
            boolean r0 = r4.canAssembly()     // Catch:{ all -> 0x009e }
            if (r0 == 0) goto L_0x0024
            goto L_0x002c
        L_0x0024:
            java.lang.SecurityException r0 = new java.lang.SecurityException     // Catch:{ all -> 0x009e }
            java.lang.String r1 = "You can't save this PDFDocument. This PDFDocument has not permission."
            r0.<init>(r1)     // Catch:{ all -> 0x009e }
            throw r0     // Catch:{ all -> 0x009e }
        L_0x002c:
            java.lang.String r0 = r4.mFilePath     // Catch:{ all -> 0x009e }
            if (r0 != 0) goto L_0x006a
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat     // Catch:{ all -> 0x009e }
            java.lang.String r1 = "yyyyMMddHHmmss"
            r0.<init>(r1)     // Catch:{ all -> 0x009e }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x009e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x009e }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r2.<init>()     // Catch:{ all -> 0x009e }
            r2.append(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = java.io.File.separator     // Catch:{ all -> 0x009e }
            r2.append(r1)     // Catch:{ all -> 0x009e }
            java.util.Calendar r1 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x009e }
            java.util.Date r1 = r1.getTime()     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r0.format(r1)     // Catch:{ all -> 0x009e }
            r2.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = ".pdf"
            r2.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r4.saveAsFile(r0)     // Catch:{ all -> 0x009e }
            goto L_0x009c
        L_0x006a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r1.<init>()     // Catch:{ all -> 0x009e }
            r1.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r2 = ".tmp"
            r1.append(r2)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x009e }
            r4.saveAsFile(r1)     // Catch:{ all -> 0x009e }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x009e }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r2.<init>()     // Catch:{ all -> 0x009e }
            r2.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r3 = ".tmp"
            r2.append(r3)     // Catch:{ all -> 0x009e }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x009e }
            r1.<init>(r2)     // Catch:{ all -> 0x009e }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x009e }
            r2.<init>(r0)     // Catch:{ all -> 0x009e }
            r1.renameTo(r2)     // Catch:{ all -> 0x009e }
        L_0x009c:
            monitor-exit(r4)
            return r0
        L_0x009e:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.PDFDocument.saveFile():java.lang.String");
    }

    public synchronized String saveAsFile(String str) {
        String str2;
        if (wasEdited() || !str.equals(this.mFilePath)) {
            if (str.equals(this.mFilePath)) {
                str2 = str + ".tmp.pdf";
            } else {
                str2 = str;
            }
            Log.d("PlugPDF", "[DEBUG] Saving PDF file as " + str2);
            if (!saveAsFileInternal(getPDFHandle(), str2)) {
                return null;
            }
            if (str2.equals(str)) {
                new File(str2).renameTo(new File(str));
            }
            this.mFilePath = str;
            return str;
        }
        return this.mFilePath;
    }

    public synchronized byte[] getPDFData(boolean z) {
        if (wasEdited() || z) {
            Log.d("PlugPDF", "[DEBUG] Get PDF Data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (true) {
                try {
                    byte[] pDFDataInternal = getPDFDataInternal(getPDFHandle(), i, 65535);
                    if (pDFDataInternal == null) {
                        break;
                    }
                    int length = pDFDataInternal.length;
                    byteArrayOutputStream.write(pDFDataInternal, 0, length);
                    i += length;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        Log.d("PlugPDF", "[WARNING] PDF ISN'T EDIDTED.");
        return null;
    }

    public synchronized void flattenFormFields(boolean z) {
        flattenFormFieldsInternal(getPDFHandle(), z);
    }

    public synchronized void flattenAnnots() {
        flattenAnnotsInternal(getPDFHandle(), false);
        this.wasEdited = true;
    }

    public static int mergePdfFile(List<String> list, String str, String str2) {
        return mergePdfFileInternal((String[]) list.toArray(new String[list.size()]), str, str2);
    }

    public static int injectImage(String str, String str2, String str3, int i, String str4, double d, double d2, double d3) {
        return injectImageInternal(str, str2, str3, i, str4, d, d2, d3);
    }

    public static long createSignatureField(String str, String str2, String str3, String str4, int i, String str5, String str6, String str7, StringBuffer stringBuffer) {
        return createSignatureFieldInternal(str, str2, str3, str4, i, str5, str6, str7, stringBuffer);
    }

    public static int readDigestToken(String str, String str2, String str3, int i, String str4, byte[] bArr) {
        return readDigestTokenInternal(str, str2, str3, i, str4, bArr);
    }

    public static int injectSignatureFile(String str, String str2, long j) {
        return injectSignatureFileInternal(str, str2, j);
    }

    public static int injectSignatureData(String str, byte[] bArr, long j) {
        return injectSignatureDataInternal(str, bArr, j);
    }

    public void transplantPage(PDFDocument pDFDocument, int i) {
        if (!pDFDocument.canExtract()) {
            throw new SecurityException("You can't assemble the src PDFDocument. The src PDFDocument has not permission.");
        } else if (canAssembly()) {
            transplantPageInternal(getPDFHandle(), pDFDocument.getPDFHandle(), i);
            this.wasEdited = true;
        } else {
            throw new SecurityException("You can't assemble this PDFDocument. This PDFDocument has not permission.");
        }
    }

    public void addScribbleContentOver(int i, PointF[][] pointFArr, float[][] fArr, float f, float f2, float f3, float f4, float f5, int i2) {
        addScribbleContentOverInternal(getPDFHandle(), i, pointFArr, fArr, f, f2, f3, f4, f5, i2);
    }

    public ArrayList<RectF> extractTextRects(int i, RectF rectF, boolean z, boolean z2, boolean z3) {
        if (canExtract()) {
            return extractTextRectsInternal(getPDFHandle(), i, rectF, z, z2, z3);
        }
        throw new SecurityException("You can't extract data in this PDFDocument. This PDFDocument has not permission.");
    }

    public String extractText(int i, RectF rectF) {
        if (canExtract()) {
            return extractTextInternal(getPDFHandle(), i, rectF);
        }
        throw new SecurityException("You can't extract data in this PDFDocument. This PDFDocument has not permission.");
    }

    public String extractText(int i, RectF rectF, RectF rectF2) {
        if (canExtract()) {
            return extractTextFromStartToEndInternal(getPDFHandle(), i, rectF, rectF2);
        }
        throw new SecurityException("You can't extract data in this PDFDocument. This PDFDocument has not permission.");
    }

    private void negativeImage(Bitmap bitmap) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setColorFilter(colorMatrixColorFilter);
        new Canvas(copy).drawBitmap(copy, 0.0f, 0.0f, paint);
        int[] iArr = new int[(copy.getHeight() * copy.getWidth())];
        copy.getPixels(iArr, 0, copy.getWidth(), 0, 0, copy.getWidth(), copy.getHeight());
        bitmap.setPixels(iArr, 0, copy.getWidth(), 0, 0, copy.getWidth(), copy.getHeight());
    }

    private byte[] bitmap2ByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private synchronized int countPagesSynchronized() {
        return countPagesInternal(getPDFHandle());
    }

    private void gotoPage(int i) {
        int i2 = this.mPageCount;
        if (i > i2 - 1) {
            i = i2 - 1;
        } else if (i < 0) {
            i = 0;
        }
        if (this.mPageIdx != i) {
            gotoPageInternal(getPDFHandle(), i);
            this.mPageIdx = i;
            this.mPageWidth = pageWidthInternal(getPDFHandle());
            this.mPageHeight = pageHeightInternal(getPDFHandle());
        }
    }

    public synchronized boolean setEncrypt(String str, String str2, int i) {
        this.wasEdited = true;
        return setEncrypt(getPDFHandle(), str, str2, i);
    }

    public synchronized int getEncrypt() {
        return getEncrypt(getPDFHandle());
    }

    public boolean hasOwnerPermission() {
        return hasOwnerPermission(getPDFHandle());
    }

    public boolean canPrint() {
        return hasOwnerPermission() || (getEncrypt() & ((int) Math.pow(2.0d, 29.0d))) > 0;
    }

    public boolean canModifyContent() {
        return hasOwnerPermission() || (getEncrypt() & ((int) Math.pow(2.0d, 28.0d))) > 0;
    }

    public boolean canCopyContent() {
        return hasOwnerPermission() || (getEncrypt() & ((int) Math.pow(2.0d, 27.0d))) > 0;
    }

    public boolean canModifyAnnot() {
        return hasOwnerPermission() || (getEncrypt() & ((int) Math.pow(2.0d, 26.0d))) > 0;
    }

    public boolean canFillField() {
        return hasOwnerPermission() || (getEncrypt() & ((int) Math.pow(2.0d, 23.0d))) > 0;
    }

    public boolean canExtract() {
        return hasOwnerPermission() || (getEncrypt() & ((int) Math.pow(2.0d, 22.0d))) > 0;
    }

    public boolean canAssembly() {
        return hasOwnerPermission() || (getEncrypt() & ((int) Math.pow(2.0d, 21.0d))) > 0;
    }

    @Deprecated
    public static int getUserAccessPermissionsWithPrint(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return getUserAccessPermissions(z, z2, z3, z4, z5, z6, z7);
    }

    public static int getUserAccessPermissions(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        char c;
        char[] cArr = new char[32];
        int i = 0;
        while (true) {
            c = '1';
            if (i >= 32) {
                break;
            }
            if (i == 0 || i == 1) {
                c = '0';
            }
            cArr[i] = c;
            i++;
        }
        cArr[2] = z ? '1' : '0';
        cArr[3] = z2 ? '1' : '0';
        cArr[4] = z3 ? '1' : '0';
        cArr[5] = z4 ? '1' : '0';
        cArr[8] = z5 ? '1' : '0';
        cArr[9] = z6 ? '1' : '0';
        if (!z7) {
            c = '0';
        }
        cArr[10] = c;
        return Integer.parseInt(new String(cArr), 2);
    }

    public synchronized String getPageText(int i) {
        return getPageText(getPDFHandle(), i);
    }

    public synchronized Bitmap drawAnnotAP(int i, int i2, int i3, double d) {
        return drawAnnotAP(getPDFHandle(), i, i2, i3, d);
    }

    public synchronized void removeAnnotAp(int i, int i2, int i3) {
        removeAnnotApInternal(getPDFHandle(), i, i2, i3);
        this.wasEdited = true;
    }

    public synchronized void exportAnnotToXFDF(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        exportAnnotToXFDFInternal(getPDFHandle(), str);
    }

    public synchronized void importAnnotFromXFDF(String str) {
        this.wasEdited = true;
        importAnnotFromXFDFInternal(getPDFHandle(), str);
    }

    public static FieldProperty[] signatureWidgetsProperties(String str, String str2) {
        return signatureWidgetsPropertiesInternal(str, str2);
    }

    public synchronized void writeSoundFile(int i, int i2, int i3, String str) {
        writeSoundFileInternal(getPDFHandle(), i, i2, i3, str);
    }

    public synchronized void loadRichMediaFile(int i, int i2, int i3, String str) {
        loadRichMediaFileInternal(getPDFHandle(), i, i2, i3, str);
    }

    public static void setFontPath(String str) {
        assetFontPath = str;
        setFontPathInternal(str);
    }

    public static String getFontPath() {
        return assetFontPath;
    }

    public static void setFontMap(String str) {
        setSubstitueFont(str.substring(str.indexOf("=") + 1));
        setFontMapInternal(str);
    }

    public static void setMaxAnnotCount(int i) {
        mMaxAnnotCount = i;
    }

    public void setInfoItem(String str, String str2) {
        setInfoItemInternal(getPDFHandle(), str, str2);
    }

    public String getInfoItem(String str) {
        return getInfoItemInternal(getPDFHandle(), str);
    }
}
