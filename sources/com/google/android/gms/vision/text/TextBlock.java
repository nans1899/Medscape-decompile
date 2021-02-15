package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzab;
import com.google.android.gms.internal.vision.zzah;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzah[] zzeh;
    private List<Line> zzei;
    private String zzej;
    private Rect zzek;

    TextBlock(SparseArray<zzah> sparseArray) {
        this.zzeh = new zzah[sparseArray.size()];
        int i = 0;
        while (true) {
            zzah[] zzahArr = this.zzeh;
            if (i < zzahArr.length) {
                zzahArr[i] = sparseArray.valueAt(i);
                i++;
            } else {
                return;
            }
        }
    }

    public String getLanguage() {
        String str = this.zzej;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzah zzah : this.zzeh) {
            hashMap.put(zzah.zzej, Integer.valueOf((hashMap.containsKey(zzah.zzej) ? ((Integer) hashMap.get(zzah.zzej)).intValue() : 0) + 1));
        }
        String str2 = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        this.zzej = str2;
        if (str2 == null || str2.isEmpty()) {
            this.zzej = "und";
        }
        return this.zzej;
    }

    public String getValue() {
        zzah[] zzahArr = this.zzeh;
        if (zzahArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzahArr[0].zzes);
        for (int i = 1; i < this.zzeh.length; i++) {
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            sb.append(this.zzeh[i].zzes);
        }
        return sb.toString();
    }

    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzah[] zzahArr;
        TextBlock textBlock2 = this;
        if (textBlock2.cornerPoints == null) {
            char c = 0;
            if (textBlock2.zzeh.length == 0) {
                textBlock2.cornerPoints = new Point[0];
            } else {
                int i = Integer.MIN_VALUE;
                int i2 = Integer.MIN_VALUE;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MAX_VALUE;
                int i5 = 0;
                while (true) {
                    zzahArr = textBlock2.zzeh;
                    if (i5 >= zzahArr.length) {
                        break;
                    }
                    zzab zzab = zzahArr[i5].zzep;
                    zzab zzab2 = textBlock2.zzeh[c].zzep;
                    double sin = Math.sin(Math.toRadians((double) zzab2.zzen));
                    double cos = Math.cos(Math.toRadians((double) zzab2.zzen));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzab.left, zzab.top);
                    pointArr[c].offset(-zzab2.left, -zzab2.top);
                    int i6 = i2;
                    int i7 = (int) ((((double) pointArr[c].x) * cos) + (((double) pointArr[c].y) * sin));
                    int i8 = (int) ((((double) (-pointArr[0].x)) * sin) + (((double) pointArr[0].y) * cos));
                    pointArr[0].x = i7;
                    pointArr[0].y = i8;
                    pointArr[1] = new Point(zzab.width + i7, i8);
                    pointArr[2] = new Point(zzab.width + i7, zzab.height + i8);
                    pointArr[3] = new Point(i7, i8 + zzab.height);
                    i2 = i6;
                    for (int i9 = 0; i9 < 4; i9++) {
                        Point point = pointArr[i9];
                        i3 = Math.min(i3, point.x);
                        i = Math.max(i, point.x);
                        i4 = Math.min(i4, point.y);
                        i2 = Math.max(i2, point.y);
                    }
                    i5++;
                    c = 0;
                    textBlock2 = this;
                }
                int i10 = i2;
                zzab zzab3 = zzahArr[0].zzep;
                int i11 = zzab3.left;
                int i12 = zzab3.top;
                double sin2 = Math.sin(Math.toRadians((double) zzab3.zzen));
                double cos2 = Math.cos(Math.toRadians((double) zzab3.zzen));
                int i13 = i10;
                Point[] pointArr2 = {new Point(i3, i4), new Point(i, i4), new Point(i, i13), new Point(i3, i13)};
                for (int i14 = 0; i14 < 4; i14++) {
                    pointArr2[i14].x = (int) ((((double) pointArr2[i14].x) * cos2) - (((double) pointArr2[i14].y) * sin2));
                    pointArr2[i14].y = (int) ((((double) pointArr2[i14].x) * sin2) + (((double) pointArr2[i14].y) * cos2));
                    pointArr2[i14].offset(i11, i12);
                }
                textBlock = this;
                textBlock.cornerPoints = pointArr2;
                return textBlock.cornerPoints;
            }
        }
        textBlock = textBlock2;
        return textBlock.cornerPoints;
    }

    public List<? extends Text> getComponents() {
        if (this.zzeh.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzei == null) {
            this.zzei = new ArrayList(this.zzeh.length);
            for (zzah line : this.zzeh) {
                this.zzei.add(new Line(line));
            }
        }
        return this.zzei;
    }

    public Rect getBoundingBox() {
        if (this.zzek == null) {
            this.zzek = zzc.zza((Text) this);
        }
        return this.zzek;
    }
}
