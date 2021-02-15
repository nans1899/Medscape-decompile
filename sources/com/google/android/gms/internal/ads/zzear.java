package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzefj;
import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzear {
    private static final Logger logger = Logger.getLogger(zzear.class.getName());
    private static final ConcurrentMap<String, zza> zzhut = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzb> zzhuu = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> zzhuv = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzdzy<?>> zzhuw = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<?>, zzeaq<?>> zzhux = new ConcurrentHashMap();

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    interface zza {
        Set<Class<?>> zzayp();

        zzeab<?> zzayy();

        Class<?> zzayz();

        Class<?> zzaza();

        <P> zzeab<P> zzb(Class<P> cls) throws GeneralSecurityException;
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    interface zzb {
    }

    private static <KeyProtoT extends zzelj> zza zza(zzeag<KeyProtoT> zzeag) {
        return new zzeat(zzeag);
    }

    private static <KeyProtoT extends zzelj> zzb zzb(zzeag<KeyProtoT> zzeag) {
        return new zzeav(zzeag);
    }

    private static synchronized zza zzhm(String str) throws GeneralSecurityException {
        zza zza2;
        synchronized (zzear.class) {
            if (!zzhut.containsKey(str)) {
                String valueOf = String.valueOf(str);
                throw new GeneralSecurityException(valueOf.length() != 0 ? "No key manager found for key type ".concat(valueOf) : new String("No key manager found for key type "));
            }
            zza2 = (zza) zzhut.get(str);
        }
        return zza2;
    }

    @Deprecated
    public static zzdzy<?> zzhn(String str) throws GeneralSecurityException {
        if (str != null) {
            zzdzy<?> zzdzy = (zzdzy) zzhuw.get(str.toLowerCase());
            if (zzdzy != null) {
                return zzdzy;
            }
            String format = String.format("no catalogue found for %s. ", new Object[]{str});
            if (str.toLowerCase().startsWith("tinkaead")) {
                format = String.valueOf(format).concat("Maybe call AeadConfig.register().");
            }
            if (str.toLowerCase().startsWith("tinkdeterministicaead")) {
                format = String.valueOf(format).concat("Maybe call DeterministicAeadConfig.register().");
            } else if (str.toLowerCase().startsWith("tinkstreamingaead")) {
                format = String.valueOf(format).concat("Maybe call StreamingAeadConfig.register().");
            } else if (str.toLowerCase().startsWith("tinkhybriddecrypt") || str.toLowerCase().startsWith("tinkhybridencrypt")) {
                format = String.valueOf(format).concat("Maybe call HybridConfig.register().");
            } else if (str.toLowerCase().startsWith("tinkmac")) {
                format = String.valueOf(format).concat("Maybe call MacConfig.register().");
            } else if (str.toLowerCase().startsWith("tinkpublickeysign") || str.toLowerCase().startsWith("tinkpublickeyverify")) {
                format = String.valueOf(format).concat("Maybe call SignatureConfig.register().");
            } else if (str.toLowerCase().startsWith("tink")) {
                format = String.valueOf(format).concat("Maybe call TinkConfig.register().");
            }
            throw new GeneralSecurityException(format);
        }
        throw new IllegalArgumentException("catalogueName must be non-null.");
    }

    private static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0092, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void zza(java.lang.String r8, java.lang.Class<?> r9, boolean r10) throws java.security.GeneralSecurityException {
        /*
            java.lang.Class<com.google.android.gms.internal.ads.zzear> r0 = com.google.android.gms.internal.ads.zzear.class
            monitor-enter(r0)
            java.util.concurrent.ConcurrentMap<java.lang.String, com.google.android.gms.internal.ads.zzear$zza> r1 = zzhut     // Catch:{ all -> 0x0093 }
            boolean r1 = r1.containsKey(r8)     // Catch:{ all -> 0x0093 }
            if (r1 != 0) goto L_0x000d
            monitor-exit(r0)
            return
        L_0x000d:
            java.util.concurrent.ConcurrentMap<java.lang.String, com.google.android.gms.internal.ads.zzear$zza> r1 = zzhut     // Catch:{ all -> 0x0093 }
            java.lang.Object r1 = r1.get(r8)     // Catch:{ all -> 0x0093 }
            com.google.android.gms.internal.ads.zzear$zza r1 = (com.google.android.gms.internal.ads.zzear.zza) r1     // Catch:{ all -> 0x0093 }
            java.lang.Class r2 = r1.zzayz()     // Catch:{ all -> 0x0093 }
            boolean r2 = r2.equals(r9)     // Catch:{ all -> 0x0093 }
            if (r2 != 0) goto L_0x0065
            java.util.logging.Logger r10 = logger     // Catch:{ all -> 0x0093 }
            java.util.logging.Level r2 = java.util.logging.Level.WARNING     // Catch:{ all -> 0x0093 }
            java.lang.String r3 = "com.google.crypto.tink.Registry"
            java.lang.String r4 = "ensureKeyManagerInsertable"
            java.lang.String r5 = "Attempted overwrite of a registered key manager for key type "
            java.lang.String r6 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0093 }
            int r7 = r6.length()     // Catch:{ all -> 0x0093 }
            if (r7 == 0) goto L_0x0038
            java.lang.String r5 = r5.concat(r6)     // Catch:{ all -> 0x0093 }
            goto L_0x003e
        L_0x0038:
            java.lang.String r6 = new java.lang.String     // Catch:{ all -> 0x0093 }
            r6.<init>(r5)     // Catch:{ all -> 0x0093 }
            r5 = r6
        L_0x003e:
            r10.logp(r2, r3, r4, r5)     // Catch:{ all -> 0x0093 }
            java.security.GeneralSecurityException r10 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "typeUrl (%s) is already registered with %s, cannot be re-registered with %s"
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0093 }
            r4 = 0
            r3[r4] = r8     // Catch:{ all -> 0x0093 }
            r8 = 1
            java.lang.Class r1 = r1.zzayz()     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x0093 }
            r3[r8] = r1     // Catch:{ all -> 0x0093 }
            r8 = 2
            java.lang.String r9 = r9.getName()     // Catch:{ all -> 0x0093 }
            r3[r8] = r9     // Catch:{ all -> 0x0093 }
            java.lang.String r8 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x0093 }
            r10.<init>(r8)     // Catch:{ all -> 0x0093 }
            throw r10     // Catch:{ all -> 0x0093 }
        L_0x0065:
            if (r10 == 0) goto L_0x0091
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Boolean> r9 = zzhuv     // Catch:{ all -> 0x0093 }
            java.lang.Object r9 = r9.get(r8)     // Catch:{ all -> 0x0093 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x0093 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0093 }
            if (r9 != 0) goto L_0x0091
            java.security.GeneralSecurityException r9 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x0093 }
            java.lang.String r10 = "New keys are already disallowed for key type "
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0093 }
            int r1 = r8.length()     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x0088
            java.lang.String r8 = r10.concat(r8)     // Catch:{ all -> 0x0093 }
            goto L_0x008d
        L_0x0088:
            java.lang.String r8 = new java.lang.String     // Catch:{ all -> 0x0093 }
            r8.<init>(r10)     // Catch:{ all -> 0x0093 }
        L_0x008d:
            r9.<init>(r8)     // Catch:{ all -> 0x0093 }
            throw r9     // Catch:{ all -> 0x0093 }
        L_0x0091:
            monitor-exit(r0)
            return
        L_0x0093:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzear.zza(java.lang.String, java.lang.Class, boolean):void");
    }

    public static synchronized <P> void zza(zzeab<P> zzeab, boolean z) throws GeneralSecurityException {
        synchronized (zzear.class) {
            if (zzeab != null) {
                String keyType = zzeab.getKeyType();
                zza(keyType, zzeab.getClass(), z);
                if (!zzhut.containsKey(keyType)) {
                    zzhut.put(keyType, new zzeau(zzeab));
                }
                zzhuv.put(keyType, Boolean.valueOf(z));
            } else {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
        }
    }

    public static synchronized <KeyProtoT extends zzelj> void zza(zzeag<KeyProtoT> zzeag, boolean z) throws GeneralSecurityException {
        synchronized (zzear.class) {
            String keyType = zzeag.getKeyType();
            zza(keyType, zzeag.getClass(), true);
            if (!zzhut.containsKey(keyType)) {
                zzhut.put(keyType, zza(zzeag));
                zzhuu.put(keyType, zzb(zzeag));
            }
            zzhuv.put(keyType, true);
        }
    }

    public static synchronized <KeyProtoT extends zzelj, PublicKeyProtoT extends zzelj> void zza(zzeas<KeyProtoT, PublicKeyProtoT> zzeas, zzeag<PublicKeyProtoT> zzeag, boolean z) throws GeneralSecurityException {
        Class<?> zzaza;
        synchronized (zzear.class) {
            String keyType = zzeas.getKeyType();
            String keyType2 = zzeag.getKeyType();
            zza(keyType, zzeas.getClass(), true);
            zza(keyType2, zzeag.getClass(), false);
            if (!keyType.equals(keyType2)) {
                if (zzhut.containsKey(keyType) && (zzaza = ((zza) zzhut.get(keyType)).zzaza()) != null) {
                    if (!zzaza.equals(zzeag.getClass())) {
                        Logger logger2 = logger;
                        Level level = Level.WARNING;
                        StringBuilder sb = new StringBuilder(String.valueOf(keyType).length() + 96 + String.valueOf(keyType2).length());
                        sb.append("Attempted overwrite of a registered key manager for key type ");
                        sb.append(keyType);
                        sb.append(" with inconsistent public key type ");
                        sb.append(keyType2);
                        logger2.logp(level, "com.google.crypto.tink.Registry", "registerAsymmetricKeyManagers", sb.toString());
                        throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", new Object[]{zzeas.getClass().getName(), zzaza.getName(), zzeag.getClass().getName()}));
                    }
                }
                if (!zzhut.containsKey(keyType) || ((zza) zzhut.get(keyType)).zzaza() == null) {
                    zzhut.put(keyType, new zzeaw(zzeas, zzeag));
                    zzhuu.put(keyType, zzb(zzeas));
                }
                zzhuv.put(keyType, true);
                if (!zzhut.containsKey(keyType2)) {
                    zzhut.put(keyType2, zza(zzeag));
                }
                zzhuv.put(keyType2, false);
            } else {
                throw new GeneralSecurityException("Private and public key type must be different.");
            }
        }
    }

    public static synchronized <P> void zza(zzeaq<P> zzeaq) throws GeneralSecurityException {
        synchronized (zzear.class) {
            if (zzeaq != null) {
                Class<P> zzayk = zzeaq.zzayk();
                if (zzhux.containsKey(zzayk)) {
                    zzeaq zzeaq2 = (zzeaq) zzhux.get(zzayk);
                    if (!zzeaq.getClass().equals(zzeaq2.getClass())) {
                        Logger logger2 = logger;
                        Level level = Level.WARNING;
                        String valueOf = String.valueOf(zzayk.toString());
                        logger2.logp(level, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", valueOf.length() != 0 ? "Attempted overwrite of a registered SetWrapper for type ".concat(valueOf) : new String("Attempted overwrite of a registered SetWrapper for type "));
                        throw new GeneralSecurityException(String.format("SetWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", new Object[]{zzayk.getName(), zzeaq2.getClass().getName(), zzeaq.getClass().getName()}));
                    }
                }
                zzhux.put(zzayk, zzeaq);
            } else {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
        }
    }

    private static zzeab<?> zzho(String str) throws GeneralSecurityException {
        return zzhm(str).zzayy();
    }

    private static <P> zzeab<P> zza(String str, Class<P> cls) throws GeneralSecurityException {
        zza zzhm = zzhm(str);
        if (cls == null) {
            return zzhm.zzayy();
        }
        if (zzhm.zzayp().contains(cls)) {
            return zzhm.zzb(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzhm.zzayz());
        Set<Class<?>> zzayp = zzhm.zzayp();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class next : zzayp) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(next.getCanonicalName());
            z = false;
        }
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder(String.valueOf(name).length() + 77 + String.valueOf(valueOf).length() + String.valueOf(sb2).length());
        sb3.append("Primitive type ");
        sb3.append(name);
        sb3.append(" not supported by key manager of type ");
        sb3.append(valueOf);
        sb3.append(", supported primitives: ");
        sb3.append(sb2);
        throw new GeneralSecurityException(sb3.toString());
    }

    public static synchronized zzefb zza(zzefe zzefe) throws GeneralSecurityException {
        zzefb zzo;
        synchronized (zzear.class) {
            zzeab<?> zzho = zzho(zzefe.zzbbt());
            if (((Boolean) zzhuv.get(zzefe.zzbbt())).booleanValue()) {
                zzo = zzho.zzo(zzefe.zzbbu());
            } else {
                String valueOf = String.valueOf(zzefe.zzbbt());
                throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
            }
        }
        return zzo;
    }

    public static synchronized zzelj zzb(zzefe zzefe) throws GeneralSecurityException {
        zzelj zzn;
        synchronized (zzear.class) {
            zzeab<?> zzho = zzho(zzefe.zzbbt());
            if (((Boolean) zzhuv.get(zzefe.zzbbt())).booleanValue()) {
                zzn = zzho.zzn(zzefe.zzbbu());
            } else {
                String valueOf = String.valueOf(zzefe.zzbbt());
                throw new GeneralSecurityException(valueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(valueOf) : new String("newKey-operation not permitted for key type "));
            }
        }
        return zzn;
    }

    public static <P> P zza(String str, zzelj zzelj, Class<P> cls) throws GeneralSecurityException {
        return zza(str, (Class) checkNotNull(cls)).zza(zzelj);
    }

    private static <P> P zza(String str, zzeip zzeip, Class<P> cls) throws GeneralSecurityException {
        return zza(str, cls).zzm(zzeip);
    }

    public static <P> P zza(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return zza(str, zzeip.zzu(bArr), (Class) checkNotNull(cls));
    }

    public static <P> zzeao<P> zza(zzeah zzeah, zzeab<P> zzeab, Class<P> cls) throws GeneralSecurityException {
        Class cls2 = (Class) checkNotNull(cls);
        zzeay.zzc(zzeah.zzays());
        zzeao<P> zza2 = zzeao.zza(cls2);
        for (zzefj.zzb next : zzeah.zzays().zzbch()) {
            if (next.zzayu() == zzefc.ENABLED) {
                zzean<P> zza3 = zza2.zza(zza(next.zzbcl().zzbbt(), next.zzbcl().zzbbu(), cls2), next);
                if (next.zzbcm() == zzeah.zzays().zzbcg()) {
                    zza2.zza(zza3);
                }
            }
        }
        return zza2;
    }

    public static <P> P zza(zzeao<P> zzeao) throws GeneralSecurityException {
        zzeaq zzeaq = (zzeaq) zzhux.get(zzeao.zzayk());
        if (zzeaq != null) {
            return zzeaq.zza(zzeao);
        }
        String valueOf = String.valueOf(zzeao.zzayk().getName());
        throw new GeneralSecurityException(valueOf.length() != 0 ? "No wrapper found for ".concat(valueOf) : new String("No wrapper found for "));
    }

    private zzear() {
    }
}
