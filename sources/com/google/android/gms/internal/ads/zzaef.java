package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaef extends NativeAd.Image {
    private final int height;
    private final Uri uri;
    private final int width;
    private final double zzddl;
    private final zzaee zzddq;
    private final Drawable zzddr;

    public zzaef(zzaee zzaee) {
        Drawable drawable;
        int i;
        this.zzddq = zzaee;
        Uri uri2 = null;
        try {
            IObjectWrapper zzsg = zzaee.zzsg();
            if (zzsg != null) {
                drawable = (Drawable) ObjectWrapper.unwrap(zzsg);
                this.zzddr = drawable;
                uri2 = this.zzddq.getUri();
                this.uri = uri2;
                double d = 1.0d;
                d = this.zzddq.getScale();
                this.zzddl = d;
                int i2 = -1;
                i = this.zzddq.getWidth();
                this.width = i;
                i2 = this.zzddq.getHeight();
                this.height = i2;
            }
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
        drawable = null;
        this.zzddr = drawable;
        try {
            uri2 = this.zzddq.getUri();
        } catch (RemoteException e2) {
            zzbbq.zzc("", e2);
        }
        this.uri = uri2;
        double d2 = 1.0d;
        try {
            d2 = this.zzddq.getScale();
        } catch (RemoteException e3) {
            zzbbq.zzc("", e3);
        }
        this.zzddl = d2;
        int i22 = -1;
        try {
            i = this.zzddq.getWidth();
        } catch (RemoteException e4) {
            zzbbq.zzc("", e4);
            i = -1;
        }
        this.width = i;
        try {
            i22 = this.zzddq.getHeight();
        } catch (RemoteException e5) {
            zzbbq.zzc("", e5);
        }
        this.height = i22;
    }

    public final Drawable getDrawable() {
        return this.zzddr;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final double getScale() {
        return this.zzddl;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }
}
