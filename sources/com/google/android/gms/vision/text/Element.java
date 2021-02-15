package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzao;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public class Element implements Text {
    private zzao zzee;

    Element(zzao zzao) {
        this.zzee = zzao;
    }

    public String getLanguage() {
        return this.zzee.zzej;
    }

    public String getValue() {
        return this.zzee.zzes;
    }

    public Rect getBoundingBox() {
        return zzc.zza((Text) this);
    }

    public Point[] getCornerPoints() {
        return zzc.zza(this.zzee.zzep);
    }

    public List<? extends Text> getComponents() {
        return new ArrayList();
    }
}
