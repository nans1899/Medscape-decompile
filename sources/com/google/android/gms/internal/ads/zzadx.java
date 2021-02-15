package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzadx extends NativeAd.AdChoicesInfo {
    private String text;
    private final List<NativeAd.Image> zzddd = new ArrayList();
    private final zzadw zzddo;

    public zzadx(zzadw zzadw) {
        zzaee zzaee;
        IBinder iBinder;
        this.zzddo = zzadw;
        try {
            this.text = zzadw.getText();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            this.text = "";
        }
        try {
            for (zzaee next : zzadw.zzsb()) {
                if (!(next instanceof IBinder) || (iBinder = (IBinder) next) == null) {
                    zzaee = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    zzaee = queryLocalInterface instanceof zzaee ? (zzaee) queryLocalInterface : new zzaeg(iBinder);
                }
                if (zzaee != null) {
                    this.zzddd.add(new zzaef(zzaee));
                }
            }
        } catch (RemoteException e2) {
            zzbbq.zzc("", e2);
        }
    }

    public final CharSequence getText() {
        return this.text;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzddd;
    }
}
