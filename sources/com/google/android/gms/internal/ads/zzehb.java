package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzehe;
import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzehb<T_WRAPPER extends zzehe<T_ENGINE>, T_ENGINE> {
    private static final Logger logger = Logger.getLogger(zzehb.class.getName());
    private static final List<Provider> zzicw;
    public static final zzehb<zzehd, Cipher> zzicx = new zzehb<>(new zzehd());
    public static final zzehb<zzehh, Mac> zzicy = new zzehb<>(new zzehh());
    private static final zzehb<zzehj, Signature> zzicz = new zzehb<>(new zzehj());
    private static final zzehb<zzehk, MessageDigest> zzida = new zzehb<>(new zzehk());
    public static final zzehb<zzehg, KeyAgreement> zzidb = new zzehb<>(new zzehg());
    public static final zzehb<zzehi, KeyPairGenerator> zzidc = new zzehb<>(new zzehi());
    public static final zzehb<zzehf, KeyFactory> zzidd = new zzehb<>(new zzehf());
    private T_WRAPPER zzide;
    private List<Provider> zzidf = zzicw;
    private boolean zzidg = true;

    private zzehb(T_WRAPPER t_wrapper) {
        this.zzide = t_wrapper;
    }

    public final T_ENGINE zzhs(String str) throws GeneralSecurityException {
        Exception exc = null;
        for (Provider zza : this.zzidf) {
            try {
                return this.zzide.zza(str, zza);
            } catch (Exception e) {
                if (exc == null) {
                    exc = e;
                }
            }
        }
        if (this.zzidg) {
            return this.zzide.zza(str, (Provider) null);
        }
        throw new GeneralSecurityException("No good Provider found.", exc);
    }

    static {
        if (zzehu.zzbdt()) {
            String[] strArr = {ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                String str = strArr[i];
                Provider provider = Security.getProvider(str);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    logger.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{str}));
                }
            }
            zzicw = arrayList;
        } else {
            zzicw = new ArrayList();
        }
    }
}
