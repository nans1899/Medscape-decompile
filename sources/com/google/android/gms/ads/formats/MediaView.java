package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.internal.ads.zzadp;
import com.google.android.gms.internal.ads.zzadr;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class MediaView extends FrameLayout {
    private MediaContent zzbng;
    private boolean zzbnh;
    private zzadp zzbni;
    private ImageView.ScaleType zzbnj;
    private boolean zzbnk;
    private zzadr zzbnl;

    public MediaView(Context context) {
        super(context);
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setMediaContent(MediaContent mediaContent) {
        this.zzbnh = true;
        this.zzbng = mediaContent;
        zzadp zzadp = this.zzbni;
        if (zzadp != null) {
            zzadp.setMediaContent(mediaContent);
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void zza(zzadp zzadp) {
        this.zzbni = zzadp;
        if (this.zzbnh) {
            zzadp.setMediaContent(this.zzbng);
        }
    }

    public void setImageScaleType(ImageView.ScaleType scaleType) {
        this.zzbnk = true;
        this.zzbnj = scaleType;
        zzadr zzadr = this.zzbnl;
        if (zzadr != null) {
            zzadr.setImageScaleType(scaleType);
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void zza(zzadr zzadr) {
        this.zzbnl = zzadr;
        if (this.zzbnk) {
            zzadr.setImageScaleType(this.zzbnj);
        }
    }
}
