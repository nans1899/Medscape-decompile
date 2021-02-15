package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbdr implements SensorEventListener {
    private final SensorManager zzehi;
    private final Object zzehj = new Object();
    private final Display zzehk;
    private final float[] zzehl = new float[9];
    private final float[] zzehm = new float[9];
    private float[] zzehn;
    private Handler zzeho;
    private zzbdt zzehp;

    zzbdr(Context context) {
        this.zzehi = (SensorManager) context.getSystemService("sensor");
        this.zzehk = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* access modifiers changed from: package-private */
    public final void start() {
        if (this.zzeho == null) {
            Sensor defaultSensor = this.zzehi.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzayp.zzfc("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            zzdvo zzdvo = new zzdvo(handlerThread.getLooper());
            this.zzeho = zzdvo;
            if (!this.zzehi.registerListener(this, defaultSensor, 0, zzdvo)) {
                zzayp.zzfc("SensorManager.registerListener failed.");
                stop();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void stop() {
        if (this.zzeho != null) {
            this.zzehi.unregisterListener(this);
            this.zzeho.post(new zzbdq(this));
            this.zzeho = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbdt zzbdt) {
        this.zzehp = zzbdt;
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr[0] != 0.0f || fArr[1] != 0.0f || fArr[2] != 0.0f) {
            synchronized (this.zzehj) {
                if (this.zzehn == null) {
                    this.zzehn = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.zzehl, fArr);
            int rotation = this.zzehk.getRotation();
            if (rotation == 1) {
                SensorManager.remapCoordinateSystem(this.zzehl, 2, 129, this.zzehm);
            } else if (rotation == 2) {
                SensorManager.remapCoordinateSystem(this.zzehl, 129, 130, this.zzehm);
            } else if (rotation != 3) {
                System.arraycopy(this.zzehl, 0, this.zzehm, 0, 9);
            } else {
                SensorManager.remapCoordinateSystem(this.zzehl, 130, 1, this.zzehm);
            }
            zzl(1, 3);
            zzl(2, 6);
            zzl(5, 7);
            synchronized (this.zzehj) {
                System.arraycopy(this.zzehm, 0, this.zzehn, 0, 9);
            }
            zzbdt zzbdt = this.zzehp;
            if (zzbdt != null) {
                zzbdt.zzux();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(float[] fArr) {
        synchronized (this.zzehj) {
            if (this.zzehn == null) {
                return false;
            }
            System.arraycopy(this.zzehn, 0, fArr, 0, this.zzehn.length);
            return true;
        }
    }

    private final void zzl(int i, int i2) {
        float[] fArr = this.zzehm;
        float f = fArr[i];
        fArr[i] = fArr[i2];
        fArr[i2] = f;
    }
}
