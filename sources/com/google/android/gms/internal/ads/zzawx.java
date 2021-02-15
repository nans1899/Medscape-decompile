package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzawx implements zzaxf {
    static final zzaxf zzdya = new zzawx();

    private zzawx() {
    }

    public final Object zza(zzbiq zzbiq) {
        String currentScreenName = zzbiq.getCurrentScreenName();
        if (currentScreenName != null) {
            return currentScreenName;
        }
        String currentScreenClass = zzbiq.getCurrentScreenClass();
        return currentScreenClass != null ? currentScreenClass : "";
    }
}
