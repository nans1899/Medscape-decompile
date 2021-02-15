package com.google.android.gms.vision.face;

import android.graphics.PointF;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class Contour {
    public static final int FACE = 1;
    public static final int LEFT_CHEEK = 14;
    public static final int LEFT_EYE = 6;
    public static final int LEFT_EYEBROW_BOTTOM = 3;
    public static final int LEFT_EYEBROW_TOP = 2;
    public static final int LOWER_LIP_BOTTOM = 11;
    public static final int LOWER_LIP_TOP = 10;
    public static final int NOSE_BOTTOM = 13;
    public static final int NOSE_BRIDGE = 12;
    public static final int RIGHT_CHEEK = 15;
    public static final int RIGHT_EYE = 7;
    public static final int RIGHT_EYEBROW_BOTTOM = 5;
    public static final int RIGHT_EYEBROW_TOP = 4;
    public static final int UPPER_LIP_BOTTOM = 9;
    public static final int UPPER_LIP_TOP = 8;
    private final int type;
    private final PointF[] zzcq;

    public final PointF[] getPositions() {
        return this.zzcq;
    }

    public final int getType() {
        return this.type;
    }

    public Contour(PointF[] pointFArr, int i) {
        this.zzcq = pointFArr;
        this.type = i;
    }
}
