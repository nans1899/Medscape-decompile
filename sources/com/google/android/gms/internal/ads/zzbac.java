package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbac {
    private final String[] zzece;
    private final double[] zzecf;
    private final double[] zzecg;
    private final int[] zzech;
    private int zzeci;

    private zzbac(zzbah zzbah) {
        int size = zzbah.zzecq.size();
        this.zzece = (String[]) zzbah.zzecp.toArray(new String[size]);
        this.zzecf = zzg(zzbah.zzecq);
        this.zzecg = zzg(zzbah.zzecr);
        this.zzech = new int[size];
        this.zzeci = 0;
    }

    private static double[] zzg(List<Double> list) {
        int size = list.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = list.get(i).doubleValue();
        }
        return dArr;
    }

    public final void zza(double d) {
        this.zzeci++;
        int i = 0;
        while (true) {
            double[] dArr = this.zzecg;
            if (i < dArr.length) {
                if (dArr[i] <= d && d < this.zzecf[i]) {
                    int[] iArr = this.zzech;
                    iArr[i] = iArr[i] + 1;
                }
                if (d >= this.zzecg[i]) {
                    i++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final List<zzbae> zzyh() {
        ArrayList arrayList = new ArrayList(this.zzece.length);
        int i = 0;
        while (true) {
            String[] strArr = this.zzece;
            if (i >= strArr.length) {
                return arrayList;
            }
            String str = strArr[i];
            double d = this.zzecg[i];
            double d2 = this.zzecf[i];
            int[] iArr = this.zzech;
            arrayList.add(new zzbae(str, d, d2, ((double) iArr[i]) / ((double) this.zzeci), iArr[i]));
            i++;
        }
    }
}
