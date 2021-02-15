package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefb;
import com.google.android.gms.internal.ads.zzelj;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzeag<KeyProtoT extends zzelj> {
    private final Class<KeyProtoT> zzhuf;
    private final Map<Class<?>, zzeai<?, KeyProtoT>> zzhug;
    private final Class<?> zzhuh;

    @SafeVarargs
    protected zzeag(Class<KeyProtoT> cls, zzeai<?, KeyProtoT>... zzeaiArr) {
        this.zzhuf = cls;
        HashMap hashMap = new HashMap();
        int length = zzeaiArr.length;
        int i = 0;
        while (i < length) {
            zzeai<?, KeyProtoT> zzeai = zzeaiArr[i];
            if (hashMap.containsKey(zzeai.zzayk())) {
                String valueOf = String.valueOf(zzeai.zzayk().getCanonicalName());
                throw new IllegalArgumentException(valueOf.length() != 0 ? "KeyTypeManager constructed with duplicate factories for primitive ".concat(valueOf) : new String("KeyTypeManager constructed with duplicate factories for primitive "));
            } else {
                hashMap.put(zzeai.zzayk(), zzeai);
                i++;
            }
        }
        if (zzeaiArr.length > 0) {
            this.zzhuh = zzeaiArr[0].zzayk();
        } else {
            this.zzhuh = Void.class;
        }
        this.zzhug = Collections.unmodifiableMap(hashMap);
    }

    public abstract String getKeyType();

    public abstract zzefb.zza zzayo();

    public abstract void zze(KeyProtoT keyprotot) throws GeneralSecurityException;

    public abstract KeyProtoT zzr(zzeip zzeip) throws zzekj;

    public final Class<KeyProtoT> zzayn() {
        return this.zzhuf;
    }

    public final <P> P zza(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        zzeai zzeai = this.zzhug.get(cls);
        if (zzeai != null) {
            return zzeai.zzag(keyprotot);
        }
        String canonicalName = cls.getCanonicalName();
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 41);
        sb.append("Requested primitive class ");
        sb.append(canonicalName);
        sb.append(" not supported.");
        throw new IllegalArgumentException(sb.toString());
    }

    public final Set<Class<?>> zzayp() {
        return this.zzhug.keySet();
    }

    /* access modifiers changed from: package-private */
    public final Class<?> zzayq() {
        return this.zzhuh;
    }

    public zzeaf<?, KeyProtoT> zzayr() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }
}
