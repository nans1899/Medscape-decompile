package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzatb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzatb> CREATOR = new zzatd();
    private ParcelFileDescriptor zzdtv;
    private Parcelable zzdtw = null;
    private boolean zzdtx = true;

    public zzatb(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzdtv = parcelFileDescriptor;
    }

    /* JADX INFO: finally extract failed */
    public final <T extends SafeParcelable> T zza(Parcelable.Creator<T> creator) {
        if (this.zzdtx) {
            if (this.zzdtv == null) {
                zzayp.zzfc("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzdtv));
            try {
                int readInt = dataInputStream.readInt();
                byte[] bArr = new byte[readInt];
                dataInputStream.readFully(bArr, 0, readInt);
                IOUtils.closeQuietly((Closeable) dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, readInt);
                    obtain.setDataPosition(0);
                    this.zzdtw = (Parcelable) creator.createFromParcel(obtain);
                    obtain.recycle();
                    this.zzdtx = false;
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (IOException e) {
                zzayp.zzc("Could not read from parcel file descriptor", e);
                IOUtils.closeQuietly((Closeable) dataInputStream);
                return null;
            } catch (Throwable th2) {
                IOUtils.closeQuietly((Closeable) dataInputStream);
                throw th2;
            }
        }
        return (SafeParcelable) this.zzdtw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzvf();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdtv, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX INFO: finally extract failed */
    private final ParcelFileDescriptor zzvf() {
        if (this.zzdtv == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzdtw.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzdtv = zzh(marshall);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }
        return this.zzdtv;
    }

    private static <T> ParcelFileDescriptor zzh(byte[] bArr) {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
            try {
                zzbbz.zzeep.execute(new zzata(autoCloseOutputStream, bArr));
                return createPipe[0];
            } catch (IOException e) {
                e = e;
                zzayp.zzc("Error transporting the ad response", e);
                zzp.zzkv().zza(e, "LargeParcelTeleporter.pipeData.2");
                IOUtils.closeQuietly((Closeable) autoCloseOutputStream);
                return null;
            }
        } catch (IOException e2) {
            e = e2;
            autoCloseOutputStream = null;
            zzayp.zzc("Error transporting the ad response", e);
            zzp.zzkv().zza(e, "LargeParcelTeleporter.pipeData.2");
            IOUtils.closeQuietly((Closeable) autoCloseOutputStream);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final /* synthetic */ void zza(java.io.OutputStream r3, byte[] r4) {
        /*
            r0 = 0
            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0019 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0019 }
            int r0 = r4.length     // Catch:{ IOException -> 0x0014, all -> 0x0011 }
            r1.writeInt(r0)     // Catch:{ IOException -> 0x0014, all -> 0x0011 }
            r1.write(r4)     // Catch:{ IOException -> 0x0014, all -> 0x0011 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r1)
            return
        L_0x0011:
            r4 = move-exception
            r0 = r1
            goto L_0x0032
        L_0x0014:
            r4 = move-exception
            r0 = r1
            goto L_0x001a
        L_0x0017:
            r4 = move-exception
            goto L_0x0032
        L_0x0019:
            r4 = move-exception
        L_0x001a:
            java.lang.String r1 = "Error transporting the ad response"
            com.google.android.gms.internal.ads.zzayp.zzc(r1, r4)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.internal.ads.zzayb r1 = com.google.android.gms.ads.internal.zzp.zzkv()     // Catch:{ all -> 0x0017 }
            java.lang.String r2 = "LargeParcelTeleporter.pipeData.1"
            r1.zza(r4, r2)     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x002e
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)
            return
        L_0x002e:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r0)
            return
        L_0x0032:
            if (r0 != 0) goto L_0x0038
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)
            goto L_0x003b
        L_0x0038:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r0)
        L_0x003b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatb.zza(java.io.OutputStream, byte[]):void");
    }
}
