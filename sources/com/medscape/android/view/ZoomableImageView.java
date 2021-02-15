package com.medscape.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.wbmd.wbmdcommons.logging.Trace;

public class ZoomableImageView extends ImageView {
    public static final int DEFAULT_SCALE_FIT_INSIDE = 0;
    public static final int DEFAULT_SCALE_ORIGINAL = 1;
    static final int DRAG = 1;
    static final int NONE = 0;
    private static final String TAG = "ZoomableImageView";
    static final int ZOOM = 2;
    /* access modifiers changed from: private */
    public boolean allowDoubleTapZoom = true;
    Paint background;
    private int containerHeight;
    private int containerWidth;
    float curX;
    float curY;
    float currentScale;
    private int defaultScale = 1;
    float easing = 0.2f;
    private GestureDetector gestureDetector;
    /* access modifiers changed from: private */
    public Bitmap imgBitmap = null;
    /* access modifiers changed from: private */
    public int imgBitmapOriginalHeight;
    /* access modifiers changed from: private */
    public int imgBitmapOriginalWidth;
    boolean isAnimating = false;
    OnZoomableImageViewTapped mCallback;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public Runnable mUpdateImagePositionTask = new Runnable() {
        public void run() {
            if (Math.abs(ZoomableImageView.this.targetX - ZoomableImageView.this.curX) >= 5.0f || Math.abs(ZoomableImageView.this.targetY - ZoomableImageView.this.curY) >= 5.0f) {
                ZoomableImageView.this.isAnimating = true;
                float[] fArr = new float[9];
                ZoomableImageView.this.matrix.getValues(fArr);
                ZoomableImageView.this.currentScale = fArr[0];
                ZoomableImageView.this.curX = fArr[2];
                ZoomableImageView.this.curY = fArr[5];
                ZoomableImageView.this.matrix.postTranslate((ZoomableImageView.this.targetX - ZoomableImageView.this.curX) * 0.3f, (ZoomableImageView.this.targetY - ZoomableImageView.this.curY) * 0.3f);
                ZoomableImageView.this.mHandler.postDelayed(this, 25);
            } else {
                ZoomableImageView.this.isAnimating = false;
                ZoomableImageView.this.mHandler.removeCallbacks(ZoomableImageView.this.mUpdateImagePositionTask);
                float[] fArr2 = new float[9];
                ZoomableImageView.this.matrix.getValues(fArr2);
                ZoomableImageView.this.currentScale = fArr2[0];
                ZoomableImageView.this.curX = fArr2[2];
                ZoomableImageView.this.curY = fArr2[5];
                ZoomableImageView.this.matrix.postTranslate(ZoomableImageView.this.targetX - ZoomableImageView.this.curX, ZoomableImageView.this.targetY - ZoomableImageView.this.curY);
            }
            ZoomableImageView.this.invalidate();
        }
    };
    /* access modifiers changed from: private */
    public Runnable mUpdateImageScale = new Runnable() {
        public void run() {
            float f = ZoomableImageView.this.targetScale / ZoomableImageView.this.currentScale;
            float f2 = f - 1.0f;
            if (((double) Math.abs(f2)) > 0.05d) {
                ZoomableImageView.this.isAnimating = true;
                if (ZoomableImageView.this.targetScale > ZoomableImageView.this.currentScale) {
                    ZoomableImageView.this.scaleChange = (f2 * 0.2f) + 1.0f;
                    ZoomableImageView.this.currentScale *= ZoomableImageView.this.scaleChange;
                    if (ZoomableImageView.this.currentScale > ZoomableImageView.this.targetScale) {
                        ZoomableImageView.this.currentScale /= ZoomableImageView.this.scaleChange;
                        ZoomableImageView.this.scaleChange = 1.0f;
                    }
                } else {
                    ZoomableImageView.this.scaleChange = 1.0f - ((1.0f - f) * 0.5f);
                    ZoomableImageView.this.currentScale *= ZoomableImageView.this.scaleChange;
                    if (ZoomableImageView.this.currentScale < ZoomableImageView.this.targetScale) {
                        ZoomableImageView.this.currentScale /= ZoomableImageView.this.scaleChange;
                        ZoomableImageView.this.scaleChange = 1.0f;
                    }
                }
                if (ZoomableImageView.this.scaleChange != 1.0f) {
                    ZoomableImageView.this.matrix.postScale(ZoomableImageView.this.scaleChange, ZoomableImageView.this.scaleChange, ZoomableImageView.this.targetScaleX, ZoomableImageView.this.targetScaleY);
                    ZoomableImageView.this.mHandler.postDelayed(ZoomableImageView.this.mUpdateImageScale, 15);
                    ZoomableImageView.this.invalidate();
                    return;
                }
                ZoomableImageView.this.isAnimating = false;
                ZoomableImageView.this.scaleChange = 1.0f;
                ZoomableImageView.this.matrix.postScale(ZoomableImageView.this.targetScale / ZoomableImageView.this.currentScale, ZoomableImageView.this.targetScale / ZoomableImageView.this.currentScale, ZoomableImageView.this.targetScaleX, ZoomableImageView.this.targetScaleY);
                ZoomableImageView zoomableImageView = ZoomableImageView.this;
                zoomableImageView.currentScale = zoomableImageView.targetScale;
                ZoomableImageView.this.mHandler.removeCallbacks(ZoomableImageView.this.mUpdateImageScale);
                ZoomableImageView.this.invalidate();
                ZoomableImageView.this.checkImageConstraints();
                return;
            }
            ZoomableImageView.this.isAnimating = false;
            ZoomableImageView.this.scaleChange = 1.0f;
            ZoomableImageView.this.matrix.postScale(ZoomableImageView.this.targetScale / ZoomableImageView.this.currentScale, ZoomableImageView.this.targetScale / ZoomableImageView.this.currentScale, ZoomableImageView.this.targetScaleX, ZoomableImageView.this.targetScaleY);
            ZoomableImageView zoomableImageView2 = ZoomableImageView.this;
            zoomableImageView2.currentScale = zoomableImageView2.targetScale;
            ZoomableImageView.this.mHandler.removeCallbacks(ZoomableImageView.this.mUpdateImageScale);
            ZoomableImageView.this.invalidate();
            ZoomableImageView.this.checkImageConstraints();
        }
    };
    Matrix matrix = new Matrix();
    float maxScale = 8.0f;
    PointF mid = new PointF();
    float minScale;
    int mode = 0;
    float oldDist = 1.0f;
    Matrix savedMatrix = new Matrix();
    float scaleChange;
    float scaleDampingFactor = 0.5f;
    float screenDensity;
    PointF start = new PointF();
    float startingScale;
    float targetRatio;
    float targetScale;
    float targetScaleX;
    float targetScaleY;
    float targetX;
    float targetY;
    float transitionalRatio;
    float wpInnerRadius = 20.0f;
    float wpRadius = 25.0f;

    public interface OnZoomableImageViewTapped {
        void onTap(float f, float f2, boolean z);
    }

    public void setOnZoomableImageViewTappedCallback(OnZoomableImageViewTapped onZoomableImageViewTapped) {
        this.mCallback = onZoomableImageViewTapped;
    }

    public int getDefaultScale() {
        return this.defaultScale;
    }

    public void setDefaultScale(int i) {
        this.defaultScale = i;
    }

    public ZoomableImageView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.screenDensity = context.getResources().getDisplayMetrics().density;
        initPaints();
        this.gestureDetector = new GestureDetector(new MyGestureDetector());
    }

    public ZoomableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.screenDensity = context.getResources().getDisplayMetrics().density;
        initPaints();
        this.gestureDetector = new GestureDetector(new MyGestureDetector());
        this.defaultScale = 0;
    }

    private void initPaints() {
        this.background = new Paint();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.containerWidth = i;
        this.containerHeight = i2;
        if (this.imgBitmap != null) {
            rescaleImage();
            invalidate();
            if (this.startingScale == 0.0f) {
                this.startingScale = this.currentScale;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Bitmap bitmap = this.imgBitmap;
        if (bitmap != null && canvas != null) {
            canvas.drawBitmap(bitmap, this.matrix, this.background);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        if (r2 != 6) goto L_0x012a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            android.view.GestureDetector r0 = r9.gestureDetector
            boolean r0 = r0.onTouchEvent(r10)
            r1 = 1
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            boolean r0 = r9.isAnimating
            if (r0 == 0) goto L_0x000f
            return r1
        L_0x000f:
            r0 = 9
            float[] r0 = new float[r0]
            int r2 = r10.getAction()
            r2 = r2 & 255(0xff, float:3.57E-43)
            if (r2 == 0) goto L_0x0110
            r3 = 5
            r4 = 0
            r5 = 2
            if (r2 == r1) goto L_0x00f5
            r6 = 1092616192(0x41200000, float:10.0)
            if (r2 == r5) goto L_0x0048
            r7 = 3
            if (r2 == r7) goto L_0x00f5
            if (r2 == r3) goto L_0x002e
            r10 = 6
            if (r2 == r10) goto L_0x00f5
            goto L_0x012a
        L_0x002e:
            float r0 = r9.spacing(r10)
            r9.oldDist = r0
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x012a
            android.graphics.Matrix r0 = r9.savedMatrix
            android.graphics.Matrix r2 = r9.matrix
            r0.set(r2)
            android.graphics.PointF r0 = r9.mid
            r9.midPoint(r0, r10)
            r9.mode = r5
            goto L_0x012a
        L_0x0048:
            int r2 = r9.mode
            if (r2 != r1) goto L_0x0081
            boolean r2 = r9.isAnimating
            if (r2 != 0) goto L_0x0081
            android.graphics.Matrix r2 = r9.matrix
            android.graphics.Matrix r6 = r9.savedMatrix
            r2.set(r6)
            float r2 = r10.getX()
            android.graphics.PointF r6 = r9.start
            float r6 = r6.x
            float r2 = r2 - r6
            float r10 = r10.getY()
            android.graphics.PointF r6 = r9.start
            float r6 = r6.y
            float r10 = r10 - r6
            android.graphics.Matrix r6 = r9.matrix
            r6.postTranslate(r2, r10)
            android.graphics.Matrix r10 = r9.matrix
            r10.getValues(r0)
            r10 = r0[r5]
            r9.curX = r10
            r10 = r0[r3]
            r9.curY = r10
            r10 = r0[r4]
            r9.currentScale = r10
            goto L_0x012a
        L_0x0081:
            int r2 = r9.mode
            if (r2 != r5) goto L_0x012a
            boolean r2 = r9.isAnimating
            if (r2 != 0) goto L_0x012a
            float r10 = r9.spacing(r10)
            int r2 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x012a
            android.graphics.Matrix r2 = r9.matrix
            android.graphics.Matrix r6 = r9.savedMatrix
            r2.set(r6)
            float r2 = r9.oldDist
            float r10 = r10 / r2
            android.graphics.Matrix r2 = r9.matrix
            r2.getValues(r0)
            r2 = r0[r4]
            r9.currentScale = r2
            float r6 = r2 * r10
            float r7 = r9.minScale
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 > 0) goto L_0x00bd
            android.graphics.Matrix r10 = r9.matrix
            float r6 = r7 / r2
            float r7 = r7 / r2
            android.graphics.PointF r2 = r9.mid
            float r2 = r2.x
            android.graphics.PointF r8 = r9.mid
            float r8 = r8.y
            r10.postScale(r6, r7, r2, r8)
            goto L_0x00e3
        L_0x00bd:
            float r6 = r2 * r10
            float r7 = r9.maxScale
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 < 0) goto L_0x00d6
            android.graphics.Matrix r10 = r9.matrix
            float r6 = r7 / r2
            float r7 = r7 / r2
            android.graphics.PointF r2 = r9.mid
            float r2 = r2.x
            android.graphics.PointF r8 = r9.mid
            float r8 = r8.y
            r10.postScale(r6, r7, r2, r8)
            goto L_0x00e3
        L_0x00d6:
            android.graphics.Matrix r2 = r9.matrix
            android.graphics.PointF r6 = r9.mid
            float r6 = r6.x
            android.graphics.PointF r7 = r9.mid
            float r7 = r7.y
            r2.postScale(r10, r10, r6, r7)
        L_0x00e3:
            android.graphics.Matrix r10 = r9.matrix
            r10.getValues(r0)
            r10 = r0[r5]
            r9.curX = r10
            r10 = r0[r3]
            r9.curY = r10
            r10 = r0[r4]
            r9.currentScale = r10
            goto L_0x012a
        L_0x00f5:
            r9.mode = r4
            android.graphics.Matrix r10 = r9.matrix
            r10.getValues(r0)
            r10 = r0[r5]
            r9.curX = r10
            r10 = r0[r3]
            r9.curY = r10
            r10 = r0[r4]
            r9.currentScale = r10
            boolean r10 = r9.isAnimating
            if (r10 != 0) goto L_0x012a
            r9.checkImageConstraints()
            goto L_0x012a
        L_0x0110:
            boolean r0 = r9.isAnimating
            if (r0 != 0) goto L_0x012a
            android.graphics.Matrix r0 = r9.savedMatrix
            android.graphics.Matrix r2 = r9.matrix
            r0.set(r2)
            android.graphics.PointF r0 = r9.start
            float r2 = r10.getX()
            float r10 = r10.getY()
            r0.set(r2, r10)
            r9.mode = r1
        L_0x012a:
            r9.invalidate()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.view.ZoomableImageView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            this.imgBitmap = bitmap;
            this.containerWidth = getWidth();
            this.containerHeight = getHeight();
            rescaleImage();
            invalidate();
            return;
        }
        Log.d(TAG, "bitmap is null");
    }

    public Bitmap getPhotoBitmap() {
        return this.imgBitmap;
    }

    public void setImageBitmapOriginalWidth(int i) {
        this.imgBitmapOriginalWidth = i;
    }

    public void setImageBitmapOriginalHeight(int i) {
        this.imgBitmapOriginalHeight = i;
    }

    public boolean isAllowDoubleTapZoom() {
        return this.allowDoubleTapZoom;
    }

    public void setAllowDoubleTapZoom(boolean z) {
        this.allowDoubleTapZoom = z;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0091 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkImageConstraints() {
        /*
            r8 = this;
            android.graphics.Bitmap r0 = r8.imgBitmap
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 9
            float[] r0 = new float[r0]
            android.graphics.Matrix r1 = r8.matrix
            r1.getValues(r0)
            r1 = 0
            r2 = r0[r1]
            r8.currentScale = r2
            float r3 = r8.minScale
            r4 = 2
            int r5 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x002b
            float r3 = r3 / r2
            int r2 = r8.containerWidth
            int r2 = r2 / r4
            float r2 = (float) r2
            int r5 = r8.containerHeight
            int r5 = r5 / r4
            float r5 = (float) r5
            android.graphics.Matrix r6 = r8.matrix
            r6.postScale(r3, r3, r2, r5)
            r8.invalidate()
        L_0x002b:
            android.graphics.Matrix r2 = r8.matrix
            r2.getValues(r0)
            r2 = r0[r1]
            r8.currentScale = r2
            r2 = r0[r4]
            r8.curX = r2
            r2 = 5
            r0 = r0[r2]
            r8.curY = r0
            int r0 = r8.containerWidth
            android.graphics.Bitmap r2 = r8.imgBitmap
            int r2 = r2.getWidth()
            float r2 = (float) r2
            float r3 = r8.currentScale
            float r2 = r2 * r3
            int r2 = (int) r2
            int r0 = r0 - r2
            int r2 = r8.containerHeight
            android.graphics.Bitmap r3 = r8.imgBitmap
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r5 = r8.currentScale
            float r3 = r3 * r5
            int r3 = (int) r3
            int r2 = r2 - r3
            r3 = 0
            r5 = 1
            if (r0 >= 0) goto L_0x0072
            float r6 = r8.curX
            int r7 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0068
            r8.targetX = r3
            goto L_0x0076
        L_0x0068:
            float r0 = (float) r0
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 >= 0) goto L_0x0070
            r8.targetX = r0
            goto L_0x0076
        L_0x0070:
            r0 = 0
            goto L_0x0077
        L_0x0072:
            int r0 = r0 / r4
            float r0 = (float) r0
            r8.targetX = r0
        L_0x0076:
            r0 = 1
        L_0x0077:
            if (r2 >= 0) goto L_0x008a
            float r4 = r8.curY
            int r6 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0082
            r8.targetY = r3
            goto L_0x008e
        L_0x0082:
            float r2 = (float) r2
            int r3 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r3 >= 0) goto L_0x008f
            r8.targetY = r2
            goto L_0x008e
        L_0x008a:
            int r2 = r2 / r4
            float r1 = (float) r2
            r8.targetY = r1
        L_0x008e:
            r1 = 1
        L_0x008f:
            if (r0 != 0) goto L_0x0093
            if (r1 == 0) goto L_0x00b1
        L_0x0093:
            if (r1 != 0) goto L_0x0099
            float r1 = r8.curY
            r8.targetY = r1
        L_0x0099:
            if (r0 != 0) goto L_0x009f
            float r0 = r8.curX
            r8.targetX = r0
        L_0x009f:
            r8.isAnimating = r5
            android.os.Handler r0 = r8.mHandler
            java.lang.Runnable r1 = r8.mUpdateImagePositionTask
            r0.removeCallbacks(r1)
            android.os.Handler r0 = r8.mHandler
            java.lang.Runnable r1 = r8.mUpdateImagePositionTask
            r2 = 100
            r0.postDelayed(r1, r2)
        L_0x00b1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.view.ZoomableImageView.checkImageConstraints():void");
    }

    private float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private void midPoint(PointF pointF, MotionEvent motionEvent) {
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }

    private void rescaleImage() {
        int i;
        float f;
        int i2;
        float f2;
        float f3;
        int height = this.imgBitmap.getHeight();
        int width = this.imgBitmap.getWidth();
        this.matrix.reset();
        int i3 = 0;
        if (this.defaultScale == 0) {
            int i4 = this.containerWidth;
            if (width > i4) {
                f = ((float) i4) / ((float) width);
                int i5 = (this.containerHeight - ((int) (((float) height) * f))) / 2;
                this.matrix.setScale(f, f);
                this.matrix.postTranslate(0.0f, (float) i5);
                i2 = i5;
            } else {
                int i6 = this.containerHeight;
                if (height > i6) {
                    float f4 = ((float) i6) / ((float) height);
                    int i7 = (i4 - ((int) (((float) width) * f4))) / 2;
                    this.matrix.setScale(f4, f4);
                    this.matrix.postTranslate((float) i7, 0.0f);
                    i3 = i7;
                    f = f4;
                    i2 = 0;
                } else {
                    if (height > width) {
                        f3 = (float) i6;
                        f2 = (float) height;
                    } else {
                        f3 = (float) i4;
                        f2 = (float) width;
                    }
                    f = f3 / f2;
                    i3 = (this.containerWidth - ((int) (((float) width) * f))) / 2;
                    i2 = (this.containerHeight - ((int) (((float) height) * f))) / 2;
                    this.matrix.setScale(f, f);
                    this.matrix.postTranslate((float) i3, (float) i2);
                }
            }
            this.curX = (float) i3;
            this.curY = (float) i2;
            this.currentScale = f;
            this.minScale = f;
            return;
        }
        int i8 = this.containerWidth;
        if (width > i8) {
            int i9 = this.containerHeight;
            if (height > i9) {
                i = 0;
            } else {
                i = (i9 - height) / 2;
            }
            this.matrix.postTranslate(0.0f, (float) i);
        } else {
            int i10 = (i8 - width) / 2;
            int i11 = this.containerHeight;
            if (height <= i11) {
                i3 = (i11 - height) / 2;
            }
            this.matrix.postTranslate((float) i10, 0.0f);
            i = i3;
            i3 = i10;
        }
        this.curX = (float) i3;
        this.curY = (float) i;
        this.currentScale = 1.0f;
        this.minScale = 1.0f;
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        MyGestureDetector() {
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (!ZoomableImageView.this.allowDoubleTapZoom) {
                return false;
            }
            if (ZoomableImageView.this.isAnimating) {
                return true;
            }
            ZoomableImageView.this.scaleChange = 1.0f;
            ZoomableImageView.this.isAnimating = true;
            ZoomableImageView.this.targetScaleX = motionEvent.getX();
            ZoomableImageView.this.targetScaleY = motionEvent.getY();
            if (((double) Math.abs(ZoomableImageView.this.currentScale - ZoomableImageView.this.maxScale)) > 0.1d) {
                ZoomableImageView zoomableImageView = ZoomableImageView.this;
                zoomableImageView.targetScale = zoomableImageView.maxScale;
            } else {
                ZoomableImageView zoomableImageView2 = ZoomableImageView.this;
                zoomableImageView2.targetScale = zoomableImageView2.minScale;
            }
            ZoomableImageView zoomableImageView3 = ZoomableImageView.this;
            zoomableImageView3.targetRatio = zoomableImageView3.targetScale / ZoomableImageView.this.currentScale;
            ZoomableImageView.this.mHandler.removeCallbacks(ZoomableImageView.this.mUpdateImageScale);
            ZoomableImageView.this.mHandler.post(ZoomableImageView.this.mUpdateImageScale);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            try {
                int width = ZoomableImageView.this.imgBitmap.getWidth();
                int height = ZoomableImageView.this.imgBitmap.getHeight();
                if (ZoomableImageView.this.imgBitmapOriginalWidth > 0) {
                    width = ZoomableImageView.this.imgBitmapOriginalWidth;
                }
                if (ZoomableImageView.this.imgBitmapOriginalHeight > 0) {
                    height = ZoomableImageView.this.imgBitmapOriginalHeight;
                }
                float height2 = ((float) height) / (((float) ZoomableImageView.this.imgBitmap.getHeight()) * ZoomableImageView.this.currentScale);
                float x = (motionEvent.getX() - ZoomableImageView.this.curX) * (((float) width) / (((float) ZoomableImageView.this.imgBitmap.getWidth()) * ZoomableImageView.this.currentScale));
                float y = (motionEvent.getY() - ZoomableImageView.this.curY) * height2;
                if (ZoomableImageView.this.mCallback != null) {
                    boolean z = ZoomableImageView.this.currentScale > ZoomableImageView.this.startingScale;
                    if (z) {
                        ZoomableImageView.this.mCallback.onTap(x * 2.0f, y * 2.0f, z);
                    } else {
                        ZoomableImageView.this.mCallback.onTap(x, y, z);
                    }
                }
            } catch (Exception e) {
                Trace.e(ZoomableImageView.TAG, e.getMessage());
            }
            return false;
        }
    }
}
