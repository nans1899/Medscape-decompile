package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzadq extends zzaed {
    private final int height;
    private final Uri uri;
    private final int width;
    private final Drawable zzddk;
    private final double zzddl;

    public zzadq(Drawable drawable, Uri uri2, double d, int i, int i2) {
        this.zzddk = drawable;
        this.uri = uri2;
        this.zzddl = d;
        this.width = i;
        this.height = i2;
    }

    public final IObjectWrapper zzsg() throws RemoteException {
        return ObjectWrapper.wrap(this.zzddk);
    }

    public final Uri getUri() throws RemoteException {
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
