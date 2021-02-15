package com.theartofdev.edmodo.cropper;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

final class CropWindowMoveHandler {
    private static final Matrix MATRIX = new Matrix();
    private final float mMaxCropHeight;
    private final float mMaxCropWidth;
    private final float mMinCropHeight;
    private final float mMinCropWidth;
    private final PointF mTouchOffset = new PointF();
    private final Type mType;

    public enum Type {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        CENTER
    }

    private static float calculateAspectRatio(float f, float f2, float f3, float f4) {
        return (f3 - f) / (f4 - f2);
    }

    public CropWindowMoveHandler(Type type, CropWindowHandler cropWindowHandler, float f, float f2) {
        this.mType = type;
        this.mMinCropWidth = cropWindowHandler.getMinCropWidth();
        this.mMinCropHeight = cropWindowHandler.getMinCropHeight();
        this.mMaxCropWidth = cropWindowHandler.getMaxCropWidth();
        this.mMaxCropHeight = cropWindowHandler.getMaxCropHeight();
        calculateTouchOffset(cropWindowHandler.getRect(), f, f2);
    }

    public void move(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3, boolean z, float f4) {
        float f5 = f + this.mTouchOffset.x;
        float f6 = f2 + this.mTouchOffset.y;
        if (this.mType == Type.CENTER) {
            moveCenter(rectF, f5, f6, rectF2, i, i2, f3);
        } else if (z) {
            moveSizeWithFixedAspectRatio(rectF, f5, f6, rectF2, i, i2, f3, f4);
        } else {
            moveSizeWithFreeAspectRatio(rectF, f5, f6, rectF2, i, i2, f3);
        }
    }

    /* renamed from: com.theartofdev.edmodo.cropper.CropWindowMoveHandler$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type[] r0 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type = r0
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.TOP_LEFT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.TOP_RIGHT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.BOTTOM_LEFT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.BOTTOM_RIGHT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.LEFT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.TOP     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type     // Catch:{ NoSuchFieldError -> 0x006c }
                com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type.CENTER     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.CropWindowMoveHandler.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        r3 = r3 - r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0045, code lost:
        r2.mTouchOffset.x = r1;
        r2.mTouchOffset.y = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r1 = r3 - r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void calculateTouchOffset(android.graphics.RectF r3, float r4, float r5) {
        /*
            r2 = this;
            int[] r0 = com.theartofdev.edmodo.cropper.CropWindowMoveHandler.AnonymousClass1.$SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type
            com.theartofdev.edmodo.cropper.CropWindowMoveHandler$Type r1 = r2.mType
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            switch(r0) {
                case 1: goto L_0x003e;
                case 2: goto L_0x0037;
                case 3: goto L_0x0030;
                case 4: goto L_0x0029;
                case 5: goto L_0x0024;
                case 6: goto L_0x0021;
                case 7: goto L_0x001e;
                case 8: goto L_0x001b;
                case 9: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            r3 = 0
            goto L_0x0045
        L_0x0010:
            float r0 = r3.centerX()
            float r1 = r0 - r4
            float r3 = r3.centerY()
            goto L_0x0044
        L_0x001b:
            float r3 = r3.bottom
            goto L_0x0044
        L_0x001e:
            float r3 = r3.right
            goto L_0x0026
        L_0x0021:
            float r3 = r3.top
            goto L_0x0044
        L_0x0024:
            float r3 = r3.left
        L_0x0026:
            float r3 = r3 - r4
            r1 = r3
            goto L_0x000e
        L_0x0029:
            float r0 = r3.right
            float r1 = r0 - r4
            float r3 = r3.bottom
            goto L_0x0044
        L_0x0030:
            float r0 = r3.left
            float r1 = r0 - r4
            float r3 = r3.bottom
            goto L_0x0044
        L_0x0037:
            float r0 = r3.right
            float r1 = r0 - r4
            float r3 = r3.top
            goto L_0x0044
        L_0x003e:
            float r0 = r3.left
            float r1 = r0 - r4
            float r3 = r3.top
        L_0x0044:
            float r3 = r3 - r5
        L_0x0045:
            android.graphics.PointF r4 = r2.mTouchOffset
            r4.x = r1
            android.graphics.PointF r4 = r2.mTouchOffset
            r4.y = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.CropWindowMoveHandler.calculateTouchOffset(android.graphics.RectF, float, float):void");
    }

    private void moveCenter(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3) {
        float centerX = f - rectF.centerX();
        float centerY = f2 - rectF.centerY();
        if (rectF.left + centerX < 0.0f || rectF.right + centerX > ((float) i) || rectF.left + centerX < rectF2.left || rectF.right + centerX > rectF2.right) {
            centerX /= 1.05f;
            this.mTouchOffset.x -= centerX / 2.0f;
        }
        if (rectF.top + centerY < 0.0f || rectF.bottom + centerY > ((float) i2) || rectF.top + centerY < rectF2.top || rectF.bottom + centerY > rectF2.bottom) {
            centerY /= 1.05f;
            this.mTouchOffset.y -= centerY / 2.0f;
        }
        rectF.offset(centerX, centerY);
        snapEdgesToBounds(rectF, rectF2, f3);
    }

    private void moveSizeWithFreeAspectRatio(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3) {
        switch (AnonymousClass1.$SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                RectF rectF3 = rectF;
                RectF rectF4 = rectF2;
                float f4 = f3;
                adjustTop(rectF3, f2, rectF4, f4, 0.0f, false, false);
                adjustLeft(rectF3, f, rectF4, f4, 0.0f, false, false);
                return;
            case 2:
                RectF rectF5 = rectF;
                RectF rectF6 = rectF2;
                adjustTop(rectF5, f2, rectF6, f3, 0.0f, false, false);
                adjustRight(rectF5, f, rectF6, i, f3, 0.0f, false, false);
                return;
            case 3:
                RectF rectF7 = rectF;
                RectF rectF8 = rectF2;
                adjustBottom(rectF7, f2, rectF8, i2, f3, 0.0f, false, false);
                adjustLeft(rectF7, f, rectF8, f3, 0.0f, false, false);
                return;
            case 4:
                RectF rectF9 = rectF;
                RectF rectF10 = rectF2;
                float f5 = f3;
                adjustBottom(rectF9, f2, rectF10, i2, f5, 0.0f, false, false);
                adjustRight(rectF9, f, rectF10, i, f5, 0.0f, false, false);
                return;
            case 5:
                adjustLeft(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 6:
                adjustTop(rectF, f2, rectF2, f3, 0.0f, false, false);
                return;
            case 7:
                adjustRight(rectF, f, rectF2, i, f3, 0.0f, false, false);
                return;
            case 8:
                adjustBottom(rectF, f2, rectF2, i2, f3, 0.0f, false, false);
                return;
            default:
                return;
        }
    }

    private void moveSizeWithFixedAspectRatio(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3, float f4) {
        RectF rectF3 = rectF;
        float f5 = f;
        float f6 = f2;
        RectF rectF4 = rectF2;
        float f7 = f4;
        switch (AnonymousClass1.$SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                if (calculateAspectRatio(f, f6, rectF3.right, rectF3.bottom) < f7) {
                    adjustTop(rectF, f2, rectF2, f3, f4, true, false);
                    adjustLeftByAspectRatio(rectF, f7);
                    return;
                }
                adjustLeft(rectF, f, rectF2, f3, f4, true, false);
                adjustTopByAspectRatio(rectF, f7);
                return;
            case 2:
                if (calculateAspectRatio(rectF3.left, f6, f, rectF3.bottom) < f7) {
                    adjustTop(rectF, f2, rectF2, f3, f4, false, true);
                    adjustRightByAspectRatio(rectF, f7);
                    return;
                }
                adjustRight(rectF, f, rectF2, i, f3, f4, true, false);
                adjustTopByAspectRatio(rectF, f7);
                return;
            case 3:
                if (calculateAspectRatio(f, rectF3.top, rectF3.right, f6) < f7) {
                    adjustBottom(rectF, f2, rectF2, i2, f3, f4, true, false);
                    adjustLeftByAspectRatio(rectF, f7);
                    return;
                }
                adjustLeft(rectF, f, rectF2, f3, f4, false, true);
                adjustBottomByAspectRatio(rectF, f7);
                return;
            case 4:
                if (calculateAspectRatio(rectF3.left, rectF3.top, f, f6) < f7) {
                    adjustBottom(rectF, f2, rectF2, i2, f3, f4, false, true);
                    adjustRightByAspectRatio(rectF, f7);
                    return;
                }
                adjustRight(rectF, f, rectF2, i, f3, f4, false, true);
                adjustBottomByAspectRatio(rectF, f7);
                return;
            case 5:
                adjustLeft(rectF, f, rectF2, f3, f4, true, true);
                adjustTopBottomByAspectRatio(rectF, rectF4, f7);
                return;
            case 6:
                adjustTop(rectF, f2, rectF2, f3, f4, true, true);
                adjustLeftRightByAspectRatio(rectF, rectF4, f7);
                return;
            case 7:
                adjustRight(rectF, f, rectF2, i, f3, f4, true, true);
                adjustTopBottomByAspectRatio(rectF, rectF4, f7);
                return;
            case 8:
                adjustBottom(rectF, f2, rectF2, i2, f3, f4, true, true);
                adjustLeftRightByAspectRatio(rectF, rectF4, f7);
                return;
            default:
                return;
        }
    }

    private void snapEdgesToBounds(RectF rectF, RectF rectF2, float f) {
        if (rectF.left < rectF2.left + f) {
            rectF.offset(rectF2.left - rectF.left, 0.0f);
        }
        if (rectF.top < rectF2.top + f) {
            rectF.offset(0.0f, rectF2.top - rectF.top);
        }
        if (rectF.right > rectF2.right - f) {
            rectF.offset(rectF2.right - rectF.right, 0.0f);
        }
        if (rectF.bottom > rectF2.bottom - f) {
            rectF.offset(0.0f, rectF2.bottom - rectF.bottom);
        }
    }

    private void adjustLeft(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.mTouchOffset.x -= f / 1.1f;
        }
        if (f < rectF2.left) {
            this.mTouchOffset.x -= (f - rectF2.left) / 2.0f;
        }
        if (f - rectF2.left < f2) {
            f = rectF2.left;
        }
        if (rectF.right - f < this.mMinCropWidth) {
            f = rectF.right - this.mMinCropWidth;
        }
        if (rectF.right - f > this.mMaxCropWidth) {
            f = rectF.right - this.mMaxCropWidth;
        }
        if (f - rectF2.left < f2) {
            f = rectF2.left;
        }
        if (f3 > 0.0f) {
            float f4 = (rectF.right - f) / f3;
            if (f4 < this.mMinCropHeight) {
                f = Math.max(rectF2.left, rectF.right - (this.mMinCropHeight * f3));
                f4 = (rectF.right - f) / f3;
            }
            if (f4 > this.mMaxCropHeight) {
                f = Math.max(rectF2.left, rectF.right - (this.mMaxCropHeight * f3));
                f4 = (rectF.right - f) / f3;
            }
            if (!z || !z2) {
                if (z && rectF.bottom - f4 < rectF2.top) {
                    f = Math.max(rectF2.left, rectF.right - ((rectF.bottom - rectF2.top) * f3));
                    f4 = (rectF.right - f) / f3;
                }
                if (z2 && rectF.top + f4 > rectF2.bottom) {
                    f = Math.max(f, Math.max(rectF2.left, rectF.right - ((rectF2.bottom - rectF.top) * f3)));
                }
            } else {
                f = Math.max(f, Math.max(rectF2.left, rectF.right - (rectF2.height() * f3)));
            }
        }
        rectF.left = f;
    }

    private void adjustRight(RectF rectF, float f, RectF rectF2, int i, float f2, float f3, boolean z, boolean z2) {
        float f4 = (float) i;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.mTouchOffset.x -= (f - f4) / 1.1f;
        }
        if (f > rectF2.right) {
            this.mTouchOffset.x -= (f - rectF2.right) / 2.0f;
        }
        if (rectF2.right - f < f2) {
            f = rectF2.right;
        }
        if (f - rectF.left < this.mMinCropWidth) {
            f = rectF.left + this.mMinCropWidth;
        }
        if (f - rectF.left > this.mMaxCropWidth) {
            f = rectF.left + this.mMaxCropWidth;
        }
        if (rectF2.right - f < f2) {
            f = rectF2.right;
        }
        if (f3 > 0.0f) {
            float f5 = (f - rectF.left) / f3;
            if (f5 < this.mMinCropHeight) {
                f = Math.min(rectF2.right, rectF.left + (this.mMinCropHeight * f3));
                f5 = (f - rectF.left) / f3;
            }
            if (f5 > this.mMaxCropHeight) {
                f = Math.min(rectF2.right, rectF.left + (this.mMaxCropHeight * f3));
                f5 = (f - rectF.left) / f3;
            }
            if (!z || !z2) {
                if (z && rectF.bottom - f5 < rectF2.top) {
                    f = Math.min(rectF2.right, rectF.left + ((rectF.bottom - rectF2.top) * f3));
                    f5 = (f - rectF.left) / f3;
                }
                if (z2 && rectF.top + f5 > rectF2.bottom) {
                    f = Math.min(f, Math.min(rectF2.right, rectF.left + ((rectF2.bottom - rectF.top) * f3)));
                }
            } else {
                f = Math.min(f, Math.min(rectF2.right, rectF.left + (rectF2.height() * f3)));
            }
        }
        rectF.right = f;
    }

    private void adjustTop(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.mTouchOffset.y -= f / 1.1f;
        }
        if (f < rectF2.top) {
            this.mTouchOffset.y -= (f - rectF2.top) / 2.0f;
        }
        if (f - rectF2.top < f2) {
            f = rectF2.top;
        }
        if (rectF.bottom - f < this.mMinCropHeight) {
            f = rectF.bottom - this.mMinCropHeight;
        }
        if (rectF.bottom - f > this.mMaxCropHeight) {
            f = rectF.bottom - this.mMaxCropHeight;
        }
        if (f - rectF2.top < f2) {
            f = rectF2.top;
        }
        if (f3 > 0.0f) {
            float f4 = (rectF.bottom - f) * f3;
            if (f4 < this.mMinCropWidth) {
                f = Math.max(rectF2.top, rectF.bottom - (this.mMinCropWidth / f3));
                f4 = (rectF.bottom - f) * f3;
            }
            if (f4 > this.mMaxCropWidth) {
                f = Math.max(rectF2.top, rectF.bottom - (this.mMaxCropWidth / f3));
                f4 = (rectF.bottom - f) * f3;
            }
            if (!z || !z2) {
                if (z && rectF.right - f4 < rectF2.left) {
                    f = Math.max(rectF2.top, rectF.bottom - ((rectF.right - rectF2.left) / f3));
                    f4 = (rectF.bottom - f) * f3;
                }
                if (z2 && rectF.left + f4 > rectF2.right) {
                    f = Math.max(f, Math.max(rectF2.top, rectF.bottom - ((rectF2.right - rectF.left) / f3)));
                }
            } else {
                f = Math.max(f, Math.max(rectF2.top, rectF.bottom - (rectF2.width() / f3)));
            }
        }
        rectF.top = f;
    }

    private void adjustBottom(RectF rectF, float f, RectF rectF2, int i, float f2, float f3, boolean z, boolean z2) {
        float f4 = (float) i;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.mTouchOffset.y -= (f - f4) / 1.1f;
        }
        if (f > rectF2.bottom) {
            this.mTouchOffset.y -= (f - rectF2.bottom) / 2.0f;
        }
        if (rectF2.bottom - f < f2) {
            f = rectF2.bottom;
        }
        if (f - rectF.top < this.mMinCropHeight) {
            f = rectF.top + this.mMinCropHeight;
        }
        if (f - rectF.top > this.mMaxCropHeight) {
            f = rectF.top + this.mMaxCropHeight;
        }
        if (rectF2.bottom - f < f2) {
            f = rectF2.bottom;
        }
        if (f3 > 0.0f) {
            float f5 = (f - rectF.top) * f3;
            if (f5 < this.mMinCropWidth) {
                f = Math.min(rectF2.bottom, rectF.top + (this.mMinCropWidth / f3));
                f5 = (f - rectF.top) * f3;
            }
            if (f5 > this.mMaxCropWidth) {
                f = Math.min(rectF2.bottom, rectF.top + (this.mMaxCropWidth / f3));
                f5 = (f - rectF.top) * f3;
            }
            if (!z || !z2) {
                if (z && rectF.right - f5 < rectF2.left) {
                    f = Math.min(rectF2.bottom, rectF.top + ((rectF.right - rectF2.left) / f3));
                    f5 = (f - rectF.top) * f3;
                }
                if (z2 && rectF.left + f5 > rectF2.right) {
                    f = Math.min(f, Math.min(rectF2.bottom, rectF.top + ((rectF2.right - rectF.left) / f3)));
                }
            } else {
                f = Math.min(f, Math.min(rectF2.bottom, rectF.top + (rectF2.width() / f3)));
            }
        }
        rectF.bottom = f;
    }

    private void adjustLeftByAspectRatio(RectF rectF, float f) {
        rectF.left = rectF.right - (rectF.height() * f);
    }

    private void adjustTopByAspectRatio(RectF rectF, float f) {
        rectF.top = rectF.bottom - (rectF.width() / f);
    }

    private void adjustRightByAspectRatio(RectF rectF, float f) {
        rectF.right = rectF.left + (rectF.height() * f);
    }

    private void adjustBottomByAspectRatio(RectF rectF, float f) {
        rectF.bottom = rectF.top + (rectF.width() / f);
    }

    private void adjustLeftRightByAspectRatio(RectF rectF, RectF rectF2, float f) {
        rectF.inset((rectF.width() - (rectF.height() * f)) / 2.0f, 0.0f);
        if (rectF.left < rectF2.left) {
            rectF.offset(rectF2.left - rectF.left, 0.0f);
        }
        if (rectF.right > rectF2.right) {
            rectF.offset(rectF2.right - rectF.right, 0.0f);
        }
    }

    private void adjustTopBottomByAspectRatio(RectF rectF, RectF rectF2, float f) {
        rectF.inset(0.0f, (rectF.height() - (rectF.width() / f)) / 2.0f);
        if (rectF.top < rectF2.top) {
            rectF.offset(0.0f, rectF2.top - rectF.top);
        }
        if (rectF.bottom > rectF2.bottom) {
            rectF.offset(0.0f, rectF2.bottom - rectF.bottom);
        }
    }
}
