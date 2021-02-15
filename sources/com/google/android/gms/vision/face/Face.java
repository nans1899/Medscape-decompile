package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public class Face {
    public static final float UNCOMPUTED_PROBABILITY = -1.0f;
    private float height;
    private int id;
    private float width;
    private PointF zzcg;
    private float zzch;
    private float zzci;
    private float zzcj;
    private List<Landmark> zzck;
    private final List<Contour> zzcl;
    private float zzcm;
    private float zzcn;
    private float zzco;
    private final float zzcp;

    private static float zza(float f) {
        if (f < 0.0f || f > 1.0f) {
            return -1.0f;
        }
        return f;
    }

    public PointF getPosition() {
        return new PointF(this.zzcg.x - (this.width / 2.0f), this.zzcg.y - (this.height / 2.0f));
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getEulerY() {
        return this.zzch;
    }

    public float getEulerZ() {
        return this.zzci;
    }

    public float getEulerX() {
        return this.zzcj;
    }

    public List<Landmark> getLandmarks() {
        return this.zzck;
    }

    public List<Contour> getContours() {
        return this.zzcl;
    }

    public float getIsLeftEyeOpenProbability() {
        return this.zzcm;
    }

    public float getIsRightEyeOpenProbability() {
        return this.zzcn;
    }

    public float getIsSmilingProbability() {
        return this.zzco;
    }

    public int getId() {
        return this.id;
    }

    public Face(int i, PointF pointF, float f, float f2, float f3, float f4, float f5, Landmark[] landmarkArr, Contour[] contourArr, float f6, float f7, float f8, float f9) {
        this.id = i;
        this.zzcg = pointF;
        this.width = f;
        this.height = f2;
        this.zzch = f3;
        this.zzci = f4;
        this.zzcj = f5;
        this.zzck = Arrays.asList(landmarkArr);
        this.zzcl = Arrays.asList(contourArr);
        this.zzcm = zza(f6);
        this.zzcn = zza(f7);
        this.zzco = zza(f8);
        this.zzcp = zza(f9);
    }
}
