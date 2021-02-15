package com.github.barteksc.pdfviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import com.github.barteksc.pdfviewer.exception.PageRenderingException;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.github.barteksc.pdfviewer.source.AssetSource;
import com.github.barteksc.pdfviewer.source.ByteArraySource;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.github.barteksc.pdfviewer.source.FileSource;
import com.github.barteksc.pdfviewer.source.InputStreamSource;
import com.github.barteksc.pdfviewer.source.UriSource;
import com.github.barteksc.pdfviewer.util.ArrayUtils;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.MathUtils;
import com.github.barteksc.pdfviewer.util.Util;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFView extends RelativeLayout {
    public static final float DEFAULT_MAX_SCALE = 3.0f;
    public static final float DEFAULT_MID_SCALE = 1.75f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    private static final String TAG = PDFView.class.getSimpleName();
    private AnimationManager animationManager;
    private boolean annotationRendering = false;
    private PaintFlagsDrawFilter antialiasFilter = new PaintFlagsDrawFilter(0, 3);
    private boolean bestQuality = false;
    CacheManager cacheManager;
    private int currentFilteredPage;
    private int currentPage;
    private float currentXOffset = 0.0f;
    private float currentYOffset = 0.0f;
    private Paint debugPaint;
    private DecodingAsyncTask decodingAsyncTask;
    private int defaultPage = 0;
    private int documentPageCount;
    /* access modifiers changed from: private */
    public DragPinchManager dragPinchManager;
    private boolean enableAntialiasing = true;
    private int[] filteredUserPageIndexes;
    private int[] filteredUserPages;
    private int invalidPageColor = -1;
    private boolean isScrollHandleInit = false;
    private float maxZoom = 3.0f;
    private float midZoom = 1.75f;
    private float minZoom = 1.0f;
    private OnDrawListener onDrawAllListener;
    private OnDrawListener onDrawListener;
    private List<Integer> onDrawPagesNums = new ArrayList(10);
    private OnErrorListener onErrorListener;
    private OnLoadCompleteListener onLoadCompleteListener;
    private OnPageChangeListener onPageChangeListener;
    private OnPageErrorListener onPageErrorListener;
    private OnPageScrollListener onPageScrollListener;
    private OnRenderListener onRenderListener;
    private OnTapListener onTapListener;
    private float optimalPageHeight;
    private float optimalPageWidth;
    private int[] originalUserPages;
    private int pageHeight;
    private int pageWidth;
    private PagesLoader pagesLoader;
    private Paint paint;
    private PdfDocument pdfDocument;
    private PdfiumCore pdfiumCore;
    private boolean recycled = true;
    private boolean renderDuringScale = false;
    RenderingHandler renderingHandler;
    private final HandlerThread renderingHandlerThread = new HandlerThread("PDF renderer");
    private ScrollDir scrollDir = ScrollDir.NONE;
    private ScrollHandle scrollHandle;
    private int spacingPx = 0;
    private State state = State.DEFAULT;
    /* access modifiers changed from: private */
    public boolean swipeVertical = true;
    private float zoom = 1.0f;

    enum ScrollDir {
        NONE,
        START,
        END
    }

    private enum State {
        DEFAULT,
        LOADED,
        SHOWN,
        ERROR
    }

    /* access modifiers changed from: package-private */
    public ScrollHandle getScrollHandle() {
        return this.scrollHandle;
    }

    public PDFView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.cacheManager = new CacheManager();
            AnimationManager animationManager2 = new AnimationManager(this);
            this.animationManager = animationManager2;
            this.dragPinchManager = new DragPinchManager(this, animationManager2);
            this.paint = new Paint();
            Paint paint2 = new Paint();
            this.debugPaint = paint2;
            paint2.setStyle(Paint.Style.STROKE);
            this.pdfiumCore = new PdfiumCore(context);
            setWillNotDraw(false);
        }
    }

    /* access modifiers changed from: private */
    public void load(DocumentSource documentSource, String str, OnLoadCompleteListener onLoadCompleteListener2, OnErrorListener onErrorListener2) {
        load(documentSource, str, onLoadCompleteListener2, onErrorListener2, (int[]) null);
    }

    /* access modifiers changed from: private */
    public void load(DocumentSource documentSource, String str, OnLoadCompleteListener onLoadCompleteListener2, OnErrorListener onErrorListener2, int[] iArr) {
        if (this.recycled) {
            if (iArr != null) {
                this.originalUserPages = iArr;
                this.filteredUserPages = ArrayUtils.deleteDuplicatedPages(iArr);
                this.filteredUserPageIndexes = ArrayUtils.calculateIndexesInDuplicateArray(this.originalUserPages);
            }
            this.onLoadCompleteListener = onLoadCompleteListener2;
            this.onErrorListener = onErrorListener2;
            int[] iArr2 = this.originalUserPages;
            int i = iArr2 != null ? iArr2[0] : 0;
            this.recycled = false;
            DecodingAsyncTask decodingAsyncTask2 = new DecodingAsyncTask(documentSource, str, this, this.pdfiumCore, i);
            this.decodingAsyncTask = decodingAsyncTask2;
            decodingAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new IllegalStateException("Don't call load on a PDF View without recycling it first.");
    }

    public void jumpTo(int i, boolean z) {
        float f = -calculatePageOffset(i);
        if (this.swipeVertical) {
            if (z) {
                this.animationManager.startYAnimation(this.currentYOffset, f);
            } else {
                moveTo(this.currentXOffset, f);
            }
        } else if (z) {
            this.animationManager.startXAnimation(this.currentXOffset, f);
        } else {
            moveTo(f, this.currentYOffset);
        }
        showPage(i);
    }

    public void jumpTo(int i) {
        jumpTo(i, false);
    }

    /* access modifiers changed from: package-private */
    public void showPage(int i) {
        if (!this.recycled) {
            int determineValidPageNumberFrom = determineValidPageNumberFrom(i);
            this.currentPage = determineValidPageNumberFrom;
            this.currentFilteredPage = determineValidPageNumberFrom;
            int[] iArr = this.filteredUserPageIndexes;
            if (iArr != null && determineValidPageNumberFrom >= 0 && determineValidPageNumberFrom < iArr.length) {
                this.currentFilteredPage = iArr[determineValidPageNumberFrom];
            }
            loadPages();
            if (this.scrollHandle != null && !documentFitsView()) {
                this.scrollHandle.setPageNum(this.currentPage + 1);
            }
            OnPageChangeListener onPageChangeListener2 = this.onPageChangeListener;
            if (onPageChangeListener2 != null) {
                onPageChangeListener2.onPageChanged(this.currentPage, getPageCount());
            }
        }
    }

    public float getPositionOffset() {
        int i;
        float f;
        float f2;
        if (this.swipeVertical) {
            f2 = -this.currentYOffset;
            f = calculateDocLength();
            i = getHeight();
        } else {
            f2 = -this.currentXOffset;
            f = calculateDocLength();
            i = getWidth();
        }
        return MathUtils.limit(f2 / (f - ((float) i)), 0.0f, 1.0f);
    }

    public void setPositionOffset(float f, boolean z) {
        if (this.swipeVertical) {
            moveTo(this.currentXOffset, ((-calculateDocLength()) + ((float) getHeight())) * f, z);
        } else {
            moveTo(((-calculateDocLength()) + ((float) getWidth())) * f, this.currentYOffset, z);
        }
        loadPageByOffset();
    }

    public void setPositionOffset(float f) {
        setPositionOffset(f, true);
    }

    private float calculatePageOffset(int i) {
        if (this.swipeVertical) {
            return toCurrentScale((((float) i) * this.optimalPageHeight) + ((float) (i * this.spacingPx)));
        }
        return toCurrentScale((((float) i) * this.optimalPageWidth) + ((float) (i * this.spacingPx)));
    }

    /* access modifiers changed from: package-private */
    public float calculateDocLength() {
        int pageCount = getPageCount();
        if (this.swipeVertical) {
            return toCurrentScale((((float) pageCount) * this.optimalPageHeight) + ((float) ((pageCount - 1) * this.spacingPx)));
        }
        return toCurrentScale((((float) pageCount) * this.optimalPageWidth) + ((float) ((pageCount - 1) * this.spacingPx)));
    }

    public void stopFling() {
        this.animationManager.stopFling();
    }

    public int getPageCount() {
        int[] iArr = this.originalUserPages;
        if (iArr != null) {
            return iArr.length;
        }
        return this.documentPageCount;
    }

    public void enableSwipe(boolean z) {
        this.dragPinchManager.setSwipeEnabled(z);
    }

    public void enableDoubletap(boolean z) {
        this.dragPinchManager.enableDoubletap(z);
    }

    /* access modifiers changed from: private */
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener2) {
        this.onPageChangeListener = onPageChangeListener2;
    }

    /* access modifiers changed from: package-private */
    public OnPageChangeListener getOnPageChangeListener() {
        return this.onPageChangeListener;
    }

    /* access modifiers changed from: private */
    public void setOnPageScrollListener(OnPageScrollListener onPageScrollListener2) {
        this.onPageScrollListener = onPageScrollListener2;
    }

    /* access modifiers changed from: package-private */
    public OnPageScrollListener getOnPageScrollListener() {
        return this.onPageScrollListener;
    }

    /* access modifiers changed from: private */
    public void setOnRenderListener(OnRenderListener onRenderListener2) {
        this.onRenderListener = onRenderListener2;
    }

    /* access modifiers changed from: package-private */
    public OnRenderListener getOnRenderListener() {
        return this.onRenderListener;
    }

    /* access modifiers changed from: private */
    public void setOnTapListener(OnTapListener onTapListener2) {
        this.onTapListener = onTapListener2;
    }

    /* access modifiers changed from: package-private */
    public OnTapListener getOnTapListener() {
        return this.onTapListener;
    }

    /* access modifiers changed from: private */
    public void setOnDrawListener(OnDrawListener onDrawListener2) {
        this.onDrawListener = onDrawListener2;
    }

    /* access modifiers changed from: private */
    public void setOnDrawAllListener(OnDrawListener onDrawListener2) {
        this.onDrawAllListener = onDrawListener2;
    }

    /* access modifiers changed from: private */
    public void setOnPageErrorListener(OnPageErrorListener onPageErrorListener2) {
        this.onPageErrorListener = onPageErrorListener2;
    }

    /* access modifiers changed from: package-private */
    public void onPageError(PageRenderingException pageRenderingException) {
        OnPageErrorListener onPageErrorListener2 = this.onPageErrorListener;
        if (onPageErrorListener2 != null) {
            onPageErrorListener2.onPageError(pageRenderingException.getPage(), pageRenderingException.getCause());
            return;
        }
        String str = TAG;
        Log.e(str, "Cannot open page " + pageRenderingException.getPage(), pageRenderingException.getCause());
    }

    public void recycle() {
        PdfDocument pdfDocument2;
        this.animationManager.stopAll();
        RenderingHandler renderingHandler2 = this.renderingHandler;
        if (renderingHandler2 != null) {
            renderingHandler2.stop();
            this.renderingHandler.removeMessages(1);
        }
        DecodingAsyncTask decodingAsyncTask2 = this.decodingAsyncTask;
        if (decodingAsyncTask2 != null) {
            decodingAsyncTask2.cancel(true);
        }
        this.cacheManager.recycle();
        ScrollHandle scrollHandle2 = this.scrollHandle;
        if (scrollHandle2 != null && this.isScrollHandleInit) {
            scrollHandle2.destroyLayout();
        }
        PdfiumCore pdfiumCore2 = this.pdfiumCore;
        if (!(pdfiumCore2 == null || (pdfDocument2 = this.pdfDocument) == null)) {
            pdfiumCore2.closeDocument(pdfDocument2);
        }
        this.renderingHandler = null;
        this.originalUserPages = null;
        this.filteredUserPages = null;
        this.filteredUserPageIndexes = null;
        this.pdfDocument = null;
        this.scrollHandle = null;
        this.isScrollHandleInit = false;
        this.currentYOffset = 0.0f;
        this.currentXOffset = 0.0f;
        this.zoom = 1.0f;
        this.recycled = true;
        this.state = State.DEFAULT;
    }

    public boolean isRecycled() {
        return this.recycled;
    }

    public void computeScroll() {
        super.computeScroll();
        if (!isInEditMode()) {
            this.animationManager.computeFling();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        recycle();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!isInEditMode() && this.state == State.SHOWN) {
            this.animationManager.stopAll();
            calculateOptimalWidthAndHeight();
            if (this.swipeVertical) {
                moveTo(this.currentXOffset, -calculatePageOffset(this.currentPage));
            } else {
                moveTo(-calculatePageOffset(this.currentPage), this.currentYOffset);
            }
            loadPageByOffset();
        }
    }

    public boolean canScrollHorizontally(int i) {
        if (this.swipeVertical) {
            if (i < 0 && this.currentXOffset < 0.0f) {
                return true;
            }
            if (i <= 0 || this.currentXOffset + toCurrentScale(this.optimalPageWidth) <= ((float) getWidth())) {
                return false;
            }
            return true;
        } else if (i < 0 && this.currentXOffset < 0.0f) {
            return true;
        } else {
            if (i <= 0 || this.currentXOffset + calculateDocLength() <= ((float) getWidth())) {
                return false;
            }
            return true;
        }
    }

    public boolean canScrollVertically(int i) {
        if (this.swipeVertical) {
            if (i < 0 && this.currentYOffset < 0.0f) {
                return true;
            }
            if (i <= 0 || this.currentYOffset + calculateDocLength() <= ((float) getHeight())) {
                return false;
            }
            return true;
        } else if (i < 0 && this.currentYOffset < 0.0f) {
            return true;
        } else {
            if (i <= 0 || this.currentYOffset + toCurrentScale(this.optimalPageHeight) <= ((float) getHeight())) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            if (this.enableAntialiasing) {
                canvas.setDrawFilter(this.antialiasFilter);
            }
            Drawable background = getBackground();
            if (background == null) {
                canvas.drawColor(-1);
            } else {
                background.draw(canvas);
            }
            if (!this.recycled && this.state == State.SHOWN) {
                float f = this.currentXOffset;
                float f2 = this.currentYOffset;
                canvas.translate(f, f2);
                for (PagePart drawPart : this.cacheManager.getThumbnails()) {
                    drawPart(canvas, drawPart);
                }
                for (PagePart next : this.cacheManager.getPageParts()) {
                    drawPart(canvas, next);
                    if (this.onDrawAllListener != null && !this.onDrawPagesNums.contains(Integer.valueOf(next.getUserPage()))) {
                        this.onDrawPagesNums.add(Integer.valueOf(next.getUserPage()));
                    }
                }
                for (Integer intValue : this.onDrawPagesNums) {
                    drawWithListener(canvas, intValue.intValue(), this.onDrawAllListener);
                }
                this.onDrawPagesNums.clear();
                drawWithListener(canvas, this.currentPage, this.onDrawListener);
                canvas.translate(-f, -f2);
            }
        }
    }

    private void drawWithListener(Canvas canvas, int i, OnDrawListener onDrawListener2) {
        float f;
        if (onDrawListener2 != null) {
            float f2 = 0.0f;
            if (this.swipeVertical) {
                f = calculatePageOffset(i);
            } else {
                f2 = calculatePageOffset(i);
                f = 0.0f;
            }
            canvas.translate(f2, f);
            onDrawListener2.onLayerDrawn(canvas, toCurrentScale(this.optimalPageWidth), toCurrentScale(this.optimalPageHeight), i);
            canvas.translate(-f2, -f);
        }
    }

    private void drawPart(Canvas canvas, PagePart pagePart) {
        float f;
        float f2;
        RectF pageRelativeBounds = pagePart.getPageRelativeBounds();
        Bitmap renderedBitmap = pagePart.getRenderedBitmap();
        if (!renderedBitmap.isRecycled()) {
            if (this.swipeVertical) {
                f = calculatePageOffset(pagePart.getUserPage());
                f2 = 0.0f;
            } else {
                f2 = calculatePageOffset(pagePart.getUserPage());
                f = 0.0f;
            }
            canvas.translate(f2, f);
            Rect rect = new Rect(0, 0, renderedBitmap.getWidth(), renderedBitmap.getHeight());
            float currentScale = toCurrentScale(pageRelativeBounds.left * this.optimalPageWidth);
            float currentScale2 = toCurrentScale(pageRelativeBounds.top * this.optimalPageHeight);
            RectF rectF = new RectF((float) ((int) currentScale), (float) ((int) currentScale2), (float) ((int) (currentScale + toCurrentScale(pageRelativeBounds.width() * this.optimalPageWidth))), (float) ((int) (currentScale2 + toCurrentScale(pageRelativeBounds.height() * this.optimalPageHeight))));
            float f3 = this.currentXOffset + f2;
            float f4 = this.currentYOffset + f;
            if (rectF.left + f3 >= ((float) getWidth()) || f3 + rectF.right <= 0.0f || rectF.top + f4 >= ((float) getHeight()) || f4 + rectF.bottom <= 0.0f) {
                canvas.translate(-f2, -f);
                return;
            }
            canvas.drawBitmap(renderedBitmap, rect, rectF, this.paint);
            if (Constants.DEBUG_MODE) {
                this.debugPaint.setColor(pagePart.getUserPage() % 2 == 0 ? -65536 : -16776961);
                canvas.drawRect(rectF, this.debugPaint);
            }
            canvas.translate(-f2, -f);
        }
    }

    public void loadPages() {
        RenderingHandler renderingHandler2;
        if (this.optimalPageWidth != 0.0f && this.optimalPageHeight != 0.0f && (renderingHandler2 = this.renderingHandler) != null) {
            renderingHandler2.removeMessages(1);
            this.cacheManager.makeANewSet();
            this.pagesLoader.loadPages();
            redraw();
        }
    }

    /* access modifiers changed from: package-private */
    public void loadComplete(PdfDocument pdfDocument2, int i, int i2) {
        this.state = State.LOADED;
        this.documentPageCount = this.pdfiumCore.getPageCount(pdfDocument2);
        this.pdfDocument = pdfDocument2;
        this.pageWidth = i;
        this.pageHeight = i2;
        calculateOptimalWidthAndHeight();
        this.pagesLoader = new PagesLoader(this);
        if (!this.renderingHandlerThread.isAlive()) {
            this.renderingHandlerThread.start();
        }
        RenderingHandler renderingHandler2 = new RenderingHandler(this.renderingHandlerThread.getLooper(), this, this.pdfiumCore, pdfDocument2);
        this.renderingHandler = renderingHandler2;
        renderingHandler2.start();
        ScrollHandle scrollHandle2 = this.scrollHandle;
        if (scrollHandle2 != null) {
            scrollHandle2.setupLayout(this);
            this.isScrollHandleInit = true;
        }
        OnLoadCompleteListener onLoadCompleteListener2 = this.onLoadCompleteListener;
        if (onLoadCompleteListener2 != null) {
            onLoadCompleteListener2.loadComplete(this.documentPageCount);
        }
        jumpTo(this.defaultPage, false);
    }

    /* access modifiers changed from: package-private */
    public void loadError(Throwable th) {
        this.state = State.ERROR;
        recycle();
        invalidate();
        OnErrorListener onErrorListener2 = this.onErrorListener;
        if (onErrorListener2 != null) {
            onErrorListener2.onError(th);
        } else {
            Log.e("PDFView", "load pdf error", th);
        }
    }

    /* access modifiers changed from: package-private */
    public void redraw() {
        invalidate();
    }

    public void onBitmapRendered(PagePart pagePart) {
        if (this.state == State.LOADED) {
            this.state = State.SHOWN;
            OnRenderListener onRenderListener2 = this.onRenderListener;
            if (onRenderListener2 != null) {
                onRenderListener2.onInitiallyRendered(getPageCount(), this.optimalPageWidth, this.optimalPageHeight);
            }
        }
        if (pagePart.isThumbnail()) {
            this.cacheManager.cacheThumbnail(pagePart);
        } else {
            this.cacheManager.cachePart(pagePart);
        }
        redraw();
    }

    private int determineValidPageNumberFrom(int i) {
        if (i <= 0) {
            return 0;
        }
        int[] iArr = this.originalUserPages;
        if (iArr == null) {
            int i2 = this.documentPageCount;
            if (i >= i2) {
                return i2 - 1;
            }
        } else if (i >= iArr.length) {
            return iArr.length - 1;
        }
        return i;
    }

    private float calculateCenterOffsetForPage(int i) {
        float f;
        float width;
        float f2;
        if (this.swipeVertical) {
            f = -((((float) i) * this.optimalPageHeight) + ((float) (i * this.spacingPx)));
            width = (float) (getHeight() / 2);
            f2 = this.optimalPageHeight;
        } else {
            f = -((((float) i) * this.optimalPageWidth) + ((float) (i * this.spacingPx)));
            width = (float) (getWidth() / 2);
            f2 = this.optimalPageWidth;
        }
        return f + (width - (f2 / 2.0f));
    }

    private void calculateOptimalWidthAndHeight() {
        if (this.state != State.DEFAULT && getWidth() != 0) {
            float width = (float) getWidth();
            float height = (float) getHeight();
            float f = ((float) this.pageWidth) / ((float) this.pageHeight);
            float floor = (float) Math.floor((double) (width / f));
            if (floor > height) {
                width = (float) Math.floor((double) (f * height));
            } else {
                height = floor;
            }
            this.optimalPageWidth = width;
            this.optimalPageHeight = height;
        }
    }

    public void moveTo(float f, float f2) {
        moveTo(f, f2, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveTo(float r6, float r7, boolean r8) {
        /*
            r5 = this;
            boolean r0 = r5.swipeVertical
            r1 = 1073741824(0x40000000, float:2.0)
            r2 = 0
            if (r0 == 0) goto L_0x0080
            float r0 = r5.optimalPageWidth
            float r0 = r5.toCurrentScale(r0)
            int r3 = r5.getWidth()
            float r3 = (float) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0020
            int r6 = r5.getWidth()
            int r6 = r6 / 2
            float r6 = (float) r6
            float r0 = r0 / r1
        L_0x001e:
            float r6 = r6 - r0
            goto L_0x0037
        L_0x0020:
            int r3 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r3 <= 0) goto L_0x0026
            r6 = 0
            goto L_0x0037
        L_0x0026:
            float r3 = r6 + r0
            int r4 = r5.getWidth()
            float r4 = (float) r4
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x0037
            int r6 = r5.getWidth()
            float r6 = (float) r6
            goto L_0x001e
        L_0x0037:
            float r0 = r5.calculateDocLength()
            int r3 = r5.getHeight()
            float r3 = (float) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x004c
            int r7 = r5.getHeight()
            float r7 = (float) r7
            float r7 = r7 - r0
            float r7 = r7 / r1
            goto L_0x0064
        L_0x004c:
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0052
            r7 = 0
            goto L_0x0064
        L_0x0052:
            float r1 = r7 + r0
            int r2 = r5.getHeight()
            float r2 = (float) r2
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x0064
            float r7 = -r0
            int r0 = r5.getHeight()
            float r0 = (float) r0
            float r7 = r7 + r0
        L_0x0064:
            float r0 = r5.currentYOffset
            int r1 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x0070
            com.github.barteksc.pdfviewer.PDFView$ScrollDir r0 = com.github.barteksc.pdfviewer.PDFView.ScrollDir.END
            r5.scrollDir = r0
            goto L_0x00f5
        L_0x0070:
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x007a
            com.github.barteksc.pdfviewer.PDFView$ScrollDir r0 = com.github.barteksc.pdfviewer.PDFView.ScrollDir.START
            r5.scrollDir = r0
            goto L_0x00f5
        L_0x007a:
            com.github.barteksc.pdfviewer.PDFView$ScrollDir r0 = com.github.barteksc.pdfviewer.PDFView.ScrollDir.NONE
            r5.scrollDir = r0
            goto L_0x00f5
        L_0x0080:
            float r0 = r5.optimalPageHeight
            float r0 = r5.toCurrentScale(r0)
            int r3 = r5.getHeight()
            float r3 = (float) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0099
            int r7 = r5.getHeight()
            int r7 = r7 / 2
            float r7 = (float) r7
            float r0 = r0 / r1
        L_0x0097:
            float r7 = r7 - r0
            goto L_0x00b0
        L_0x0099:
            int r3 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r3 <= 0) goto L_0x009f
            r7 = 0
            goto L_0x00b0
        L_0x009f:
            float r3 = r7 + r0
            int r4 = r5.getHeight()
            float r4 = (float) r4
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x00b0
            int r7 = r5.getHeight()
            float r7 = (float) r7
            goto L_0x0097
        L_0x00b0:
            float r0 = r5.calculateDocLength()
            int r3 = r5.getWidth()
            float r3 = (float) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x00c5
            int r6 = r5.getWidth()
            float r6 = (float) r6
            float r6 = r6 - r0
            float r6 = r6 / r1
            goto L_0x00dd
        L_0x00c5:
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x00cb
            r6 = 0
            goto L_0x00dd
        L_0x00cb:
            float r1 = r6 + r0
            int r2 = r5.getWidth()
            float r2 = (float) r2
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x00dd
            float r6 = -r0
            int r0 = r5.getWidth()
            float r0 = (float) r0
            float r6 = r6 + r0
        L_0x00dd:
            float r0 = r5.currentXOffset
            int r1 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x00e8
            com.github.barteksc.pdfviewer.PDFView$ScrollDir r0 = com.github.barteksc.pdfviewer.PDFView.ScrollDir.END
            r5.scrollDir = r0
            goto L_0x00f5
        L_0x00e8:
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x00f1
            com.github.barteksc.pdfviewer.PDFView$ScrollDir r0 = com.github.barteksc.pdfviewer.PDFView.ScrollDir.START
            r5.scrollDir = r0
            goto L_0x00f5
        L_0x00f1:
            com.github.barteksc.pdfviewer.PDFView$ScrollDir r0 = com.github.barteksc.pdfviewer.PDFView.ScrollDir.NONE
            r5.scrollDir = r0
        L_0x00f5:
            r5.currentXOffset = r6
            r5.currentYOffset = r7
            float r6 = r5.getPositionOffset()
            if (r8 == 0) goto L_0x010e
            com.github.barteksc.pdfviewer.scroll.ScrollHandle r7 = r5.scrollHandle
            if (r7 == 0) goto L_0x010e
            boolean r7 = r5.documentFitsView()
            if (r7 != 0) goto L_0x010e
            com.github.barteksc.pdfviewer.scroll.ScrollHandle r7 = r5.scrollHandle
            r7.setScroll(r6)
        L_0x010e:
            com.github.barteksc.pdfviewer.listener.OnPageScrollListener r7 = r5.onPageScrollListener
            if (r7 == 0) goto L_0x0119
            int r8 = r5.getCurrentPage()
            r7.onPageScrolled(r8, r6)
        L_0x0119:
            r5.redraw()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.PDFView.moveTo(float, float, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public ScrollDir getScrollDir() {
        return this.scrollDir;
    }

    /* access modifiers changed from: package-private */
    public void loadPageByOffset() {
        float f;
        float f2;
        int i;
        if (getPageCount() != 0) {
            int i2 = this.spacingPx;
            float pageCount = (float) (i2 - (i2 / getPageCount()));
            if (this.swipeVertical) {
                f2 = this.currentYOffset;
                f = this.optimalPageHeight + pageCount;
                i = getHeight();
            } else {
                f2 = this.currentXOffset;
                f = this.optimalPageWidth + pageCount;
                i = getWidth();
            }
            int floor = (int) Math.floor((double) ((Math.abs(f2) + (((float) i) / 2.0f)) / toCurrentScale(f)));
            if (floor < 0 || floor > getPageCount() - 1 || floor == getCurrentPage()) {
                loadPages();
            } else {
                showPage(floor);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int[] getFilteredUserPages() {
        return this.filteredUserPages;
    }

    /* access modifiers changed from: package-private */
    public int[] getOriginalUserPages() {
        return this.originalUserPages;
    }

    /* access modifiers changed from: package-private */
    public int[] getFilteredUserPageIndexes() {
        return this.filteredUserPageIndexes;
    }

    /* access modifiers changed from: package-private */
    public int getDocumentPageCount() {
        return this.documentPageCount;
    }

    public void moveRelativeTo(float f, float f2) {
        moveTo(this.currentXOffset + f, this.currentYOffset + f2);
    }

    public void zoomTo(float f) {
        this.zoom = f;
    }

    public void zoomCenteredTo(float f, PointF pointF) {
        float f2 = f / this.zoom;
        zoomTo(f);
        moveTo((this.currentXOffset * f2) + (pointF.x - (pointF.x * f2)), (this.currentYOffset * f2) + (pointF.y - (pointF.y * f2)));
    }

    public void zoomCenteredRelativeTo(float f, PointF pointF) {
        zoomCenteredTo(this.zoom * f, pointF);
    }

    public boolean documentFitsView() {
        int pageCount = getPageCount();
        int i = (pageCount - 1) * this.spacingPx;
        if (this.swipeVertical) {
            if ((((float) pageCount) * this.optimalPageHeight) + ((float) i) < ((float) getHeight())) {
                return true;
            }
            return false;
        } else if ((((float) pageCount) * this.optimalPageWidth) + ((float) i) < ((float) getWidth())) {
            return true;
        } else {
            return false;
        }
    }

    public void fitToWidth(int i) {
        if (this.state != State.SHOWN) {
            Log.e(TAG, "Cannot fit, document not rendered yet");
            return;
        }
        fitToWidth();
        jumpTo(i);
    }

    public void fitToWidth() {
        if (this.state != State.SHOWN) {
            Log.e(TAG, "Cannot fit, document not rendered yet");
            return;
        }
        zoomTo(((float) getWidth()) / this.optimalPageWidth);
        setPositionOffset(0.0f);
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public float getCurrentXOffset() {
        return this.currentXOffset;
    }

    public float getCurrentYOffset() {
        return this.currentYOffset;
    }

    public float toRealScale(float f) {
        return f / this.zoom;
    }

    public float toCurrentScale(float f) {
        return f * this.zoom;
    }

    public float getZoom() {
        return this.zoom;
    }

    public boolean isZooming() {
        return this.zoom != this.minZoom;
    }

    public float getOptimalPageWidth() {
        return this.optimalPageWidth;
    }

    public float getOptimalPageHeight() {
        return this.optimalPageHeight;
    }

    /* access modifiers changed from: private */
    public void setDefaultPage(int i) {
        this.defaultPage = i;
    }

    public void resetZoom() {
        zoomTo(this.minZoom);
    }

    public void resetZoomWithAnimation() {
        zoomWithAnimation(this.minZoom);
    }

    public void zoomWithAnimation(float f, float f2, float f3) {
        this.animationManager.startZoomAnimation(f, f2, this.zoom, f3);
    }

    public void zoomWithAnimation(float f) {
        this.animationManager.startZoomAnimation((float) (getWidth() / 2), (float) (getHeight() / 2), this.zoom, f);
    }

    /* access modifiers changed from: private */
    public void setScrollHandle(ScrollHandle scrollHandle2) {
        this.scrollHandle = scrollHandle2;
    }

    public int getPageAtPositionOffset(float f) {
        int floor = (int) Math.floor((double) (((float) getPageCount()) * f));
        return floor == getPageCount() ? floor - 1 : floor;
    }

    public float getMinZoom() {
        return this.minZoom;
    }

    public void setMinZoom(float f) {
        this.minZoom = f;
    }

    public float getMidZoom() {
        return this.midZoom;
    }

    public void setMidZoom(float f) {
        this.midZoom = f;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public void setMaxZoom(float f) {
        this.maxZoom = f;
    }

    public void useBestQuality(boolean z) {
        this.bestQuality = z;
    }

    public boolean isBestQuality() {
        return this.bestQuality;
    }

    public boolean isSwipeVertical() {
        return this.swipeVertical;
    }

    public void setSwipeVertical(boolean z) {
        this.swipeVertical = z;
    }

    public void enableAnnotationRendering(boolean z) {
        this.annotationRendering = z;
    }

    public boolean isAnnotationRendering() {
        return this.annotationRendering;
    }

    public void enableRenderDuringScale(boolean z) {
        this.renderDuringScale = z;
    }

    public boolean isAntialiasing() {
        return this.enableAntialiasing;
    }

    public void enableAntialiasing(boolean z) {
        this.enableAntialiasing = z;
    }

    /* access modifiers changed from: package-private */
    public int getSpacingPx() {
        return this.spacingPx;
    }

    /* access modifiers changed from: private */
    public void setSpacing(int i) {
        this.spacingPx = Util.getDP(getContext(), i);
    }

    /* access modifiers changed from: private */
    public void setInvalidPageColor(int i) {
        this.invalidPageColor = i;
    }

    public int getInvalidPageColor() {
        return this.invalidPageColor;
    }

    public boolean doRenderDuringScale() {
        return this.renderDuringScale;
    }

    public PdfDocument.Meta getDocumentMeta() {
        PdfDocument pdfDocument2 = this.pdfDocument;
        if (pdfDocument2 == null) {
            return null;
        }
        return this.pdfiumCore.getDocumentMeta(pdfDocument2);
    }

    public List<PdfDocument.Bookmark> getTableOfContents() {
        PdfDocument pdfDocument2 = this.pdfDocument;
        if (pdfDocument2 == null) {
            return new ArrayList();
        }
        return this.pdfiumCore.getTableOfContents(pdfDocument2);
    }

    public Configurator fromAsset(String str) {
        return new Configurator(new AssetSource(str));
    }

    public Configurator fromFile(File file) {
        return new Configurator(new FileSource(file));
    }

    public Configurator fromUri(Uri uri) {
        return new Configurator(new UriSource(uri));
    }

    public Configurator fromBytes(byte[] bArr) {
        return new Configurator(new ByteArraySource(bArr));
    }

    public Configurator fromStream(InputStream inputStream) {
        return new Configurator(new InputStreamSource(inputStream));
    }

    public Configurator fromSource(DocumentSource documentSource) {
        return new Configurator(documentSource);
    }

    public class Configurator {
        private boolean annotationRendering;
        private boolean antialiasing;
        private int defaultPage;
        /* access modifiers changed from: private */
        public final DocumentSource documentSource;
        private boolean enableDoubletap;
        private boolean enableSwipe;
        private int invalidPageColor;
        private OnDrawListener onDrawAllListener;
        private OnDrawListener onDrawListener;
        /* access modifiers changed from: private */
        public OnErrorListener onErrorListener;
        /* access modifiers changed from: private */
        public OnLoadCompleteListener onLoadCompleteListener;
        private OnPageChangeListener onPageChangeListener;
        private OnPageErrorListener onPageErrorListener;
        private OnPageScrollListener onPageScrollListener;
        private OnRenderListener onRenderListener;
        private OnTapListener onTapListener;
        /* access modifiers changed from: private */
        public int[] pageNumbers;
        /* access modifiers changed from: private */
        public String password;
        private ScrollHandle scrollHandle;
        private int spacing;
        private boolean swipeHorizontal;

        private Configurator(DocumentSource documentSource2) {
            this.pageNumbers = null;
            this.enableSwipe = true;
            this.enableDoubletap = true;
            this.defaultPage = 0;
            this.swipeHorizontal = false;
            this.annotationRendering = false;
            this.password = null;
            this.scrollHandle = null;
            this.antialiasing = true;
            this.spacing = 0;
            this.invalidPageColor = -1;
            this.documentSource = documentSource2;
        }

        public Configurator pages(int... iArr) {
            this.pageNumbers = iArr;
            return this;
        }

        public Configurator enableSwipe(boolean z) {
            this.enableSwipe = z;
            return this;
        }

        public Configurator enableDoubletap(boolean z) {
            this.enableDoubletap = z;
            return this;
        }

        public Configurator enableAnnotationRendering(boolean z) {
            this.annotationRendering = z;
            return this;
        }

        public Configurator onDraw(OnDrawListener onDrawListener2) {
            this.onDrawListener = onDrawListener2;
            return this;
        }

        public Configurator onDrawAll(OnDrawListener onDrawListener2) {
            this.onDrawAllListener = onDrawListener2;
            return this;
        }

        public Configurator onLoad(OnLoadCompleteListener onLoadCompleteListener2) {
            this.onLoadCompleteListener = onLoadCompleteListener2;
            return this;
        }

        public Configurator onPageScroll(OnPageScrollListener onPageScrollListener2) {
            this.onPageScrollListener = onPageScrollListener2;
            return this;
        }

        public Configurator onError(OnErrorListener onErrorListener2) {
            this.onErrorListener = onErrorListener2;
            return this;
        }

        public Configurator onPageError(OnPageErrorListener onPageErrorListener2) {
            this.onPageErrorListener = onPageErrorListener2;
            return this;
        }

        public Configurator onPageChange(OnPageChangeListener onPageChangeListener2) {
            this.onPageChangeListener = onPageChangeListener2;
            return this;
        }

        public Configurator onRender(OnRenderListener onRenderListener2) {
            this.onRenderListener = onRenderListener2;
            return this;
        }

        public Configurator onTap(OnTapListener onTapListener2) {
            this.onTapListener = onTapListener2;
            return this;
        }

        public Configurator defaultPage(int i) {
            this.defaultPage = i;
            return this;
        }

        public Configurator swipeHorizontal(boolean z) {
            this.swipeHorizontal = z;
            return this;
        }

        public Configurator password(String str) {
            this.password = str;
            return this;
        }

        public Configurator scrollHandle(ScrollHandle scrollHandle2) {
            this.scrollHandle = scrollHandle2;
            return this;
        }

        public Configurator enableAntialiasing(boolean z) {
            this.antialiasing = z;
            return this;
        }

        public Configurator spacing(int i) {
            this.spacing = i;
            return this;
        }

        public Configurator invalidPageColor(int i) {
            this.invalidPageColor = i;
            return this;
        }

        public void load() {
            PDFView.this.recycle();
            PDFView.this.setOnDrawListener(this.onDrawListener);
            PDFView.this.setOnDrawAllListener(this.onDrawAllListener);
            PDFView.this.setOnPageChangeListener(this.onPageChangeListener);
            PDFView.this.setOnPageScrollListener(this.onPageScrollListener);
            PDFView.this.setOnRenderListener(this.onRenderListener);
            PDFView.this.setOnTapListener(this.onTapListener);
            PDFView.this.setOnPageErrorListener(this.onPageErrorListener);
            PDFView.this.enableSwipe(this.enableSwipe);
            PDFView.this.enableDoubletap(this.enableDoubletap);
            PDFView.this.setDefaultPage(this.defaultPage);
            PDFView.this.setSwipeVertical(!this.swipeHorizontal);
            PDFView.this.enableAnnotationRendering(this.annotationRendering);
            PDFView.this.setScrollHandle(this.scrollHandle);
            PDFView.this.enableAntialiasing(this.antialiasing);
            PDFView.this.setSpacing(this.spacing);
            PDFView.this.setInvalidPageColor(this.invalidPageColor);
            PDFView.this.dragPinchManager.setSwipeVertical(PDFView.this.swipeVertical);
            PDFView.this.post(new Runnable() {
                public void run() {
                    if (Configurator.this.pageNumbers != null) {
                        PDFView.this.load(Configurator.this.documentSource, Configurator.this.password, Configurator.this.onLoadCompleteListener, Configurator.this.onErrorListener, Configurator.this.pageNumbers);
                    } else {
                        PDFView.this.load(Configurator.this.documentSource, Configurator.this.password, Configurator.this.onLoadCompleteListener, Configurator.this.onErrorListener);
                    }
                }
            });
        }
    }
}
