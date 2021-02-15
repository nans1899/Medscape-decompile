package com.google.android.gms.internal.ads;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeop {
    public static final zzeop zziui = new zzeop(1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static final zzeop zziuj = new zzeop(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, -1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static final zzeop zziuk = new zzeop(-1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, -1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static final zzeop zziul = new zzeop(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, -1.0d, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double w;
    private final double zziue;
    private final double zziuf;
    private final double zziug;
    private final double zziuh;

    private zzeop(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        this.zziue = d6;
        this.zziuf = d7;
        this.w = d8;
        this.a = d2;
        this.b = d3;
        this.c = d4;
        this.d = d5;
        this.zziug = d9;
        this.zziuh = d10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzeop zzeop = (zzeop) obj;
        return Double.compare(zzeop.a, this.a) == 0 && Double.compare(zzeop.b, this.b) == 0 && Double.compare(zzeop.c, this.c) == 0 && Double.compare(zzeop.d, this.d) == 0 && Double.compare(zzeop.zziug, this.zziug) == 0 && Double.compare(zzeop.zziuh, this.zziuh) == 0 && Double.compare(zzeop.zziue, this.zziue) == 0 && Double.compare(zzeop.zziuf, this.zziuf) == 0 && Double.compare(zzeop.w, this.w) == 0;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zziue);
        long doubleToLongBits2 = Double.doubleToLongBits(this.zziuf);
        long doubleToLongBits3 = Double.doubleToLongBits(this.w);
        long doubleToLongBits4 = Double.doubleToLongBits(this.a);
        long doubleToLongBits5 = Double.doubleToLongBits(this.b);
        long doubleToLongBits6 = Double.doubleToLongBits(this.c);
        long doubleToLongBits7 = Double.doubleToLongBits(this.d);
        long doubleToLongBits8 = Double.doubleToLongBits(this.zziug);
        long doubleToLongBits9 = Double.doubleToLongBits(this.zziuh);
        return (((((((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31) + ((int) (doubleToLongBits7 ^ (doubleToLongBits7 >>> 32)))) * 31) + ((int) (doubleToLongBits8 ^ (doubleToLongBits8 >>> 32)))) * 31) + ((int) (doubleToLongBits9 ^ (doubleToLongBits9 >>> 32)));
    }

    public final String toString() {
        if (equals(zziui)) {
            return "Rotate 0°";
        }
        if (equals(zziuj)) {
            return "Rotate 90°";
        }
        if (equals(zziuk)) {
            return "Rotate 180°";
        }
        if (equals(zziul)) {
            return "Rotate 270°";
        }
        double d2 = this.zziue;
        double d3 = this.zziuf;
        double d4 = this.w;
        double d5 = this.a;
        double d6 = this.b;
        double d7 = this.c;
        double d8 = this.d;
        double d9 = this.zziug;
        double d10 = this.zziuh;
        double d11 = d8;
        StringBuilder sb = new StringBuilder(260);
        sb.append("Matrix{u=");
        sb.append(d2);
        sb.append(", v=");
        sb.append(d3);
        sb.append(", w=");
        sb.append(d4);
        sb.append(", a=");
        sb.append(d5);
        sb.append(", b=");
        sb.append(d6);
        sb.append(", c=");
        sb.append(d7);
        sb.append(", d=");
        sb.append(d11);
        sb.append(", tx=");
        sb.append(d9);
        sb.append(", ty=");
        sb.append(d10);
        sb.append("}");
        return sb.toString();
    }

    public static zzeop zzs(ByteBuffer byteBuffer) {
        double zzi = zzbq.zzi(byteBuffer);
        double zzi2 = zzbq.zzi(byteBuffer);
        double zzj = zzbq.zzj(byteBuffer);
        return new zzeop(zzi, zzi2, zzbq.zzi(byteBuffer), zzbq.zzi(byteBuffer), zzj, zzbq.zzj(byteBuffer), zzbq.zzj(byteBuffer), zzbq.zzi(byteBuffer), zzbq.zzi(byteBuffer));
    }
}
