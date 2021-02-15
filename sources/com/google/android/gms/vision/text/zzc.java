package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzab;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
final class zzc {
    static Rect zza(Text text) {
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        for (Point point : text.getCornerPoints()) {
            i3 = Math.min(i3, point.x);
            i = Math.max(i, point.x);
            i4 = Math.min(i4, point.y);
            i2 = Math.max(i2, point.y);
        }
        return new Rect(i3, i4, i, i2);
    }

    static Point[] zza(zzab zzab) {
        Point[] pointArr = new Point[4];
        double sin = Math.sin(Math.toRadians((double) zzab.zzen));
        double cos = Math.cos(Math.toRadians((double) zzab.zzen));
        pointArr[0] = new Point(zzab.left, zzab.top);
        pointArr[1] = new Point((int) (((double) zzab.left) + (((double) zzab.width) * cos)), (int) (((double) zzab.top) + (((double) zzab.width) * sin)));
        pointArr[2] = new Point((int) (((double) pointArr[1].x) - (((double) zzab.height) * sin)), (int) (((double) pointArr[1].y) + (((double) zzab.height) * cos)));
        pointArr[3] = new Point(pointArr[0].x + (pointArr[2].x - pointArr[1].x), pointArr[0].y + (pointArr[2].y - pointArr[1].y));
        return pointArr;
    }
}
