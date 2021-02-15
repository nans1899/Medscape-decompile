package com.google.android.gms.internal.ads;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzadl extends zzadv {
    private static final int zzdcy = Color.rgb(12, 174, 206);
    private static final int zzdcz;
    private static final int zzdda;
    private static final int zzddb = zzdcy;
    private final int backgroundColor;
    private final int textColor;
    private final int textSize;
    private final String zzddc;
    private final List<zzadq> zzddd = new ArrayList();
    private final List<zzaee> zzdde = new ArrayList();
    private final int zzddf;
    private final int zzddg;
    private final boolean zzddh;

    public zzadl(String str, List<zzadq> list, Integer num, Integer num2, Integer num3, int i, int i2, boolean z) {
        this.zzddc = str;
        if (list != null) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzadq zzadq = list.get(i3);
                this.zzddd.add(zzadq);
                this.zzdde.add(zzadq);
            }
        }
        this.backgroundColor = num != null ? num.intValue() : zzdda;
        this.textColor = num2 != null ? num2.intValue() : zzddb;
        this.textSize = num3 != null ? num3.intValue() : 12;
        this.zzddf = i;
        this.zzddg = i2;
        this.zzddh = z;
    }

    public final String getText() {
        return this.zzddc;
    }

    public final List<zzaee> zzsb() {
        return this.zzdde;
    }

    public final List<zzadq> zzsc() {
        return this.zzddd;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final int zzsd() {
        return this.zzddf;
    }

    public final int zzse() {
        return this.zzddg;
    }

    static {
        int rgb = Color.rgb(204, 204, 204);
        zzdcz = rgb;
        zzdda = rgb;
    }
}
