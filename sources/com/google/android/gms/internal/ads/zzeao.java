package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefj;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeao<P> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Class<P> zzhue;
    private ConcurrentMap<String, List<zzean<P>>> zzhup = new ConcurrentHashMap();
    private zzean<P> zzhuq;

    public final zzean<P> zzayx() {
        return this.zzhuq;
    }

    private zzeao(Class<P> cls) {
        this.zzhue = cls;
    }

    public static <P> zzeao<P> zza(Class<P> cls) {
        return new zzeao<>(cls);
    }

    public final void zza(zzean<P> zzean) {
        if (zzean == null) {
            throw new IllegalArgumentException("the primary entry must be non-null");
        } else if (zzean.zzayu() == zzefc.ENABLED) {
            List list = (List) this.zzhup.get(new String(zzean.zzayw(), UTF_8));
            if (list == null) {
                list = Collections.emptyList();
            }
            if (!list.isEmpty()) {
                this.zzhuq = zzean;
                return;
            }
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        } else {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
    }

    public final zzean<P> zza(P p, zzefj.zzb zzb) throws GeneralSecurityException {
        byte[] bArr;
        if (zzb.zzayu() == zzefc.ENABLED) {
            int i = zzeaa.zzhub[zzb.zzayv().ordinal()];
            if (i == 1 || i == 2) {
                bArr = ByteBuffer.allocate(5).put((byte) 0).putInt(zzb.zzbcm()).array();
            } else if (i == 3) {
                bArr = ByteBuffer.allocate(5).put((byte) 1).putInt(zzb.zzbcm()).array();
            } else if (i == 4) {
                bArr = zzdzx.zzhua;
            } else {
                throw new GeneralSecurityException("unknown output prefix type");
            }
            zzean zzean = new zzean(p, bArr, zzb.zzayu(), zzb.zzayv(), zzb.zzbcm());
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzean);
            String str = new String(zzean.zzayw(), UTF_8);
            List list = (List) this.zzhup.put(str, Collections.unmodifiableList(arrayList));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list);
                arrayList2.add(zzean);
                this.zzhup.put(str, Collections.unmodifiableList(arrayList2));
            }
            return zzean;
        }
        throw new GeneralSecurityException("only ENABLED key is allowed");
    }

    public final Class<P> zzayk() {
        return this.zzhue;
    }
}
