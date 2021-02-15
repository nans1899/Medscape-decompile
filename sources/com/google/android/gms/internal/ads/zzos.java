package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzos extends IOException {
    private final int type;
    private final zzon zzbio;

    public zzos(String str, zzon zzon, int i) {
        super(str);
        this.zzbio = zzon;
        this.type = 1;
    }

    public zzos(IOException iOException, zzon zzon, int i) {
        super(iOException);
        this.zzbio = zzon;
        this.type = i;
    }

    public zzos(String str, IOException iOException, zzon zzon, int i) {
        super(str, iOException);
        this.zzbio = zzon;
        this.type = 1;
    }
}
