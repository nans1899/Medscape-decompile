package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpc {
    public static ParcelFileDescriptor zzf(InputStream inputStream) throws IOException {
        ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor = createPipe[0];
        zzbbz.zzeep.execute(new zzdpb(inputStream, createPipe[1]));
        return parcelFileDescriptor;
    }
}
