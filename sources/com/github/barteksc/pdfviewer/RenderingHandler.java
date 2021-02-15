package com.github.barteksc.pdfviewer;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseBooleanArray;
import com.github.barteksc.pdfviewer.exception.PageRenderingException;
import com.github.barteksc.pdfviewer.model.PagePart;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

class RenderingHandler extends Handler {
    static final int MSG_RENDER_TASK = 1;
    private static final String TAG = RenderingHandler.class.getName();
    private final SparseBooleanArray openedPages = new SparseBooleanArray();
    private PdfDocument pdfDocument;
    /* access modifiers changed from: private */
    public PDFView pdfView;
    private PdfiumCore pdfiumCore;
    private RectF renderBounds = new RectF();
    private Matrix renderMatrix = new Matrix();
    private Rect roundedRenderBounds = new Rect();
    private boolean running = false;

    RenderingHandler(Looper looper, PDFView pDFView, PdfiumCore pdfiumCore2, PdfDocument pdfDocument2) {
        super(looper);
        this.pdfView = pDFView;
        this.pdfiumCore = pdfiumCore2;
        this.pdfDocument = pdfDocument2;
    }

    /* access modifiers changed from: package-private */
    public void addRenderingTask(int i, int i2, float f, float f2, RectF rectF, boolean z, int i3, boolean z2, boolean z3) {
        sendMessage(obtainMessage(1, new RenderingTask(f, f2, rectF, i, i2, z, i3, z2, z3)));
    }

    public void handleMessage(Message message) {
        try {
            final PagePart proceed = proceed((RenderingTask) message.obj);
            if (proceed == null) {
                return;
            }
            if (this.running) {
                this.pdfView.post(new Runnable() {
                    public void run() {
                        RenderingHandler.this.pdfView.onBitmapRendered(proceed);
                    }
                });
            } else {
                proceed.getRenderedBitmap().recycle();
            }
        } catch (PageRenderingException e) {
            this.pdfView.post(new Runnable() {
                public void run() {
                    RenderingHandler.this.pdfView.onPageError(e);
                }
            });
        }
    }

    private PagePart proceed(RenderingTask renderingTask) throws PageRenderingException {
        if (this.openedPages.indexOfKey(renderingTask.page) < 0) {
            try {
                this.pdfiumCore.openPage(this.pdfDocument, renderingTask.page);
                this.openedPages.put(renderingTask.page, true);
            } catch (Exception e) {
                this.openedPages.put(renderingTask.page, false);
                throw new PageRenderingException(renderingTask.page, e);
            }
        }
        int round = Math.round(renderingTask.width);
        int round2 = Math.round(renderingTask.height);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(round, round2, renderingTask.bestQuality ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            calculateBounds(round, round2, renderingTask.bounds);
            if (this.openedPages.get(renderingTask.page)) {
                this.pdfiumCore.renderPageBitmap(this.pdfDocument, createBitmap, renderingTask.page, this.roundedRenderBounds.left, this.roundedRenderBounds.top, this.roundedRenderBounds.width(), this.roundedRenderBounds.height(), renderingTask.annotationRendering);
            } else {
                createBitmap.eraseColor(this.pdfView.getInvalidPageColor());
            }
            return new PagePart(renderingTask.userPage, renderingTask.page, createBitmap, renderingTask.width, renderingTask.height, renderingTask.bounds, renderingTask.thumbnail, renderingTask.cacheOrder);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void calculateBounds(int i, int i2, RectF rectF) {
        this.renderMatrix.reset();
        float f = (float) i;
        float f2 = (float) i2;
        this.renderMatrix.postTranslate((-rectF.left) * f, (-rectF.top) * f2);
        this.renderMatrix.postScale(1.0f / rectF.width(), 1.0f / rectF.height());
        this.renderBounds.set(0.0f, 0.0f, f, f2);
        this.renderMatrix.mapRect(this.renderBounds);
        this.renderBounds.round(this.roundedRenderBounds);
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        this.running = false;
    }

    /* access modifiers changed from: package-private */
    public void start() {
        this.running = true;
    }

    private class RenderingTask {
        boolean annotationRendering;
        boolean bestQuality;
        RectF bounds;
        int cacheOrder;
        float height;
        int page;
        boolean thumbnail;
        int userPage;
        float width;

        RenderingTask(float f, float f2, RectF rectF, int i, int i2, boolean z, int i3, boolean z2, boolean z3) {
            this.page = i2;
            this.width = f;
            this.height = f2;
            this.bounds = rectF;
            this.userPage = i;
            this.thumbnail = z;
            this.cacheOrder = i3;
            this.bestQuality = z2;
            this.annotationRendering = z3;
        }
    }
}
