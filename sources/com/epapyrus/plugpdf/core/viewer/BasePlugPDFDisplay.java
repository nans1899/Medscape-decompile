package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.annotation.AnnotEventListener;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolCircle;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolEraser;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolFile;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolFreeText;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolInk;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolLine;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolLink;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolNav;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolNote;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolSelect;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolSquare;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolStamp;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotToolTransform;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.gesture.EditGestureProcessor;
import com.epapyrus.plugpdf.core.gesture.GestureProcessorListener;
import com.epapyrus.plugpdf.core.gesture.ViewGestureProcessor;
import java.util.Map;

public abstract class BasePlugPDFDisplay extends AdapterView<Adapter> implements GestureProcessorListener {
    protected Adapter mAdapter;
    protected AnnotEventListener mAnnotEventListener = null;
    protected BaseAnnotTool mAnnotTool;
    protected AnnotToolEventListener mAnnotToolEventListener = null;
    protected Context mCtx;
    protected int mCurPageIdx;
    protected DisplayEventListener mDisplayListener;
    protected PDFDocument mDoc = null;
    protected FitType mFitType = FitType.WIDTH;
    protected BaseGestureProcessor mGesture = null;
    protected boolean mImmediatelyClear;
    protected ReaderListener mListener;
    protected boolean mLoadFinish;
    private PageDisplayMode mMode;
    protected View.OnLongClickListener mOnLongClickListener;
    protected boolean mOuterFocusInterception = false;
    protected Map<String, Integer> mRecentPageMap;
    protected boolean mScrollDisabled;
    private AnnotToolSelect.TextSelectListener mTextSelectListener;

    protected enum Direction {
        DIAGONALLY,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public interface DisplayEventListener {
        void allTaskCompleted();

        void onPageSelect(int i);
    }

    public enum FitType {
        WIDTH,
        HEIGHT,
        MINIMUM
    }

    public enum PageDisplayMode {
        HORIZONTAL,
        VERTICAL,
        REALISTIC,
        CONTINUOS,
        BILATERAL_VERTICAL,
        BILATERAL_HORIZONTAL,
        BILATERAL_REALISTIC,
        THUMBNAIL
    }

    public abstract double calculatePageViewScaleInReaderView();

    public abstract void changeScale(double d, int i, int i2);

    public abstract SparseArray<PageView> getChildViewList();

    public abstract PageView getPageView();

    public abstract PageView getPageView(int i);

    /* access modifiers changed from: protected */
    public abstract double getPageViewScale(int i);

    public View getSelectedView() {
        return null;
    }

    public abstract void goToPage(int i);

    public void onAnnotLongPress(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType) {
    }

    public void onAnnotSingleTapUp(MotionEvent motionEvent, BaseGestureProcessor.GestureType gestureType) {
    }

    public void onAnnotTouchEvent(MotionEvent motionEvent) {
    }

    public void onDoubleTapUp(MotionEvent motionEvent) {
    }

    public void onDown(MotionEvent motionEvent) {
    }

    public void onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
    }

    public void onScale(ScaleGestureDetector scaleGestureDetector) {
    }

    public void onScaleBegin() {
    }

    public void onScaleEnd() {
    }

    public void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
    }

    public void onSingleTapConfirmed(MotionEvent motionEvent) {
    }

    public abstract void refreshLayout();

    public abstract void scrollToView(int i, View view);

    public abstract void setScrollPosX(int i);

    public abstract void setScrollPosY(int i);

    public void setSelection(int i) {
    }

    public abstract void setupPageViews();

    public BasePlugPDFDisplay(Context context, PageDisplayMode pageDisplayMode) {
        super(context);
        setBackgroundColor(-12303292);
        this.mCurPageIdx = -1;
        this.mMode = pageDisplayMode;
        this.mCtx = context;
        changeAnnotationTool(new AnnotToolNav(context, this));
        setGesture(new ViewGestureProcessor(this.mCtx, this));
    }

    public void setDisplayListener(DisplayEventListener displayEventListener) {
        this.mDisplayListener = displayEventListener;
    }

    public void setAnnotEventListener(AnnotEventListener annotEventListener) {
        this.mAnnotEventListener = annotEventListener;
    }

    public void setAnnotToolEventListenr(AnnotToolEventListener annotToolEventListener) {
        this.mAnnotToolEventListener = annotToolEventListener;
    }

    public void setAnnotationTool(BaseAnnotTool.AnnotToolType annotToolType) {
        if (this.mGesture.getType() == BaseGestureProcessor.GestureType.EDIT) {
            if ((annotToolType != BaseAnnotTool.AnnotToolType.TEXT_SELECT || this.mDoc.canExtract()) && (annotToolType == BaseAnnotTool.AnnotToolType.NONE || this.mDoc.canModifyAnnot())) {
                switch (AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()]) {
                    case 1:
                        changeAnnotationTool(new AnnotToolInk(this.mCtx));
                        return;
                    case 2:
                        changeAnnotationTool(new AnnotToolEraser(this.mCtx));
                        return;
                    case 3:
                        changeAnnotationTool(new AnnotToolNote(this.mCtx));
                        return;
                    case 4:
                        changeAnnotationTool(new AnnotToolSelect(this.mCtx, BaseAnnotTool.AnnotToolType.HIGHLIGHT));
                        return;
                    case 5:
                        changeAnnotationTool(new AnnotToolSelect(this.mCtx, BaseAnnotTool.AnnotToolType.UNDERLINE));
                        return;
                    case 6:
                        changeAnnotationTool(new AnnotToolSelect(this.mCtx, BaseAnnotTool.AnnotToolType.STRIKEOUT));
                        return;
                    case 7:
                        changeAnnotationTool(new AnnotToolLink(this.mCtx));
                        return;
                    case 8:
                        changeAnnotationTool(new AnnotToolSquare(this.mCtx));
                        return;
                    case 9:
                        changeAnnotationTool(new AnnotToolCircle(this.mCtx));
                        return;
                    case 10:
                        AnnotToolSelect annotToolSelect = new AnnotToolSelect(this.mCtx, BaseAnnotTool.AnnotToolType.TEXT_SELECT);
                        annotToolSelect.setListener(this.mTextSelectListener);
                        changeAnnotationTool(annotToolSelect);
                        return;
                    case 11:
                        changeAnnotationTool(new AnnotToolFile(this.mCtx));
                        return;
                    case 12:
                        changeAnnotationTool(new AnnotToolLine(this.mCtx));
                        return;
                    case 13:
                        changeAnnotationTool(new AnnotToolTransform(this.mCtx, this));
                        return;
                    case 14:
                        changeAnnotationTool(new AnnotToolFreeText(this.mCtx));
                        return;
                    case 15:
                        changeAnnotationTool(new AnnotToolStamp(this.mCtx));
                        return;
                    default:
                        changeAnnotationTool(new AnnotToolNav(this.mCtx, this));
                        return;
                }
            } else {
                throw new SecurityException("You can't use the " + annotToolType.toString() + " Tool in this Document. This Document has not permission.");
            }
        }
    }

    public void setTextSelectListener(AnnotToolSelect.TextSelectListener textSelectListener) {
        this.mTextSelectListener = textSelectListener;
        BaseAnnotTool baseAnnotTool = this.mAnnotTool;
        if (baseAnnotTool instanceof AnnotToolSelect) {
            ((AnnotToolSelect) baseAnnotTool).setListener(textSelectListener);
        }
    }

    private void changeAnnotationTool(BaseAnnotTool baseAnnotTool) {
        BaseAnnotTool baseAnnotTool2 = this.mAnnotTool;
        if (baseAnnotTool2 != null) {
            baseAnnotTool2.exit();
        }
        this.mAnnotTool = baseAnnotTool;
        baseAnnotTool.enter();
    }

    public Adapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setDocument(PDFDocument pDFDocument) {
        this.mDoc = pDFDocument;
    }

    public void setReaderListener(ReaderListener readerListener) {
        this.mListener = readerListener;
    }

    /* renamed from: com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType;
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$gesture$BaseGestureProcessor$GestureType;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0059 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0093 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x009f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00ab */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00b7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00c3 */
        static {
            /*
                com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor$GestureType[] r0 = com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor.GestureType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$gesture$BaseGestureProcessor$GestureType = r0
                r1 = 1
                com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor$GestureType r2 = com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor.GestureType.VIEW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$epapyrus$plugpdf$core$gesture$BaseGestureProcessor$GestureType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor$GestureType r3 = com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor.GestureType.EDIT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType[] r2 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType = r2
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r3 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.INK     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r2 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.ERASER     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.NOTE     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x004e }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.HIGHLIGHT     // Catch:{ NoSuchFieldError -> 0x004e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0059 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.UNDERLINE     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.STRIKEOUT     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x006f }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.LINK     // Catch:{ NoSuchFieldError -> 0x006f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x007b }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.SQUARE     // Catch:{ NoSuchFieldError -> 0x007b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0087 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.TEXT_SELECT     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x009f }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.FILE_ATTACTH     // Catch:{ NoSuchFieldError -> 0x009f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009f }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009f }
            L_0x009f:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x00ab }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.LINE     // Catch:{ NoSuchFieldError -> 0x00ab }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ab }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ab }
            L_0x00ab:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x00b7 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.TRANSFORM     // Catch:{ NoSuchFieldError -> 0x00b7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b7 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b7 }
            L_0x00b7:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x00c3 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.FREE_TEXT     // Catch:{ NoSuchFieldError -> 0x00c3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c3 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c3 }
            L_0x00c3:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x00cf }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.STAMP     // Catch:{ NoSuchFieldError -> 0x00cf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cf }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cf }
            L_0x00cf:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.AnonymousClass1.<clinit>():void");
        }
    }

    public void changeGesture(BaseGestureProcessor.GestureType gestureType) {
        int i = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$gesture$BaseGestureProcessor$GestureType[gestureType.ordinal()];
        if (i == 1) {
            setGesture(new ViewGestureProcessor(this.mCtx, this));
            changeAnnotationTool(new AnnotToolNav(this.mCtx, this));
        } else if (i == 2) {
            setGesture(new EditGestureProcessor(this.mCtx, this));
            changeAnnotationTool(new AnnotToolNav(this.mCtx, this));
        }
    }

    private void setGesture(BaseGestureProcessor baseGestureProcessor) {
        this.mGesture = baseGestureProcessor;
        baseGestureProcessor.setGestureListener(this);
    }

    public BaseGestureProcessor getGesture() {
        return this.mGesture;
    }

    public PageDisplayMode getMode() {
        return this.mMode;
    }

    public void setOuterFocusInterception(boolean z) {
        this.mOuterFocusInterception = z;
    }

    public int pageCount() {
        return this.mDoc.getPageCount();
    }

    public int getPageIdx() {
        return this.mCurPageIdx;
    }

    public void clear() {
        this.mImmediatelyClear = true;
        BaseAnnotTool baseAnnotTool = this.mAnnotTool;
        if (baseAnnotTool != null) {
            baseAnnotTool.exit();
        }
    }

    /* access modifiers changed from: protected */
    public void setRecentPageMap(Map<String, Integer> map) {
        this.mRecentPageMap = map;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        SparseArray<PageView> childViewList = getChildViewList();
        if (childViewList != null) {
            for (int i5 = 0; i5 < childViewList.size(); i5++) {
                childViewList.valueAt(i5).setParentSize(getWidth(), getHeight());
            }
        }
    }

    public void setFitType(FitType fitType) {
        this.mFitType = fitType;
    }

    public boolean wasFinishedLoad() {
        return this.mLoadFinish;
    }

    public void setScrollDisabled(boolean z) {
        this.mScrollDisabled = z;
    }

    public void onLongPress(MotionEvent motionEvent) {
        ReaderListener readerListener = this.mListener;
        if (readerListener != null) {
            readerListener.onLongPress(motionEvent);
        }
    }
}
