package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdok {
    public final zzvn zzbpf;
    public final zzadu zzdly;
    public final zzajc zzdsi;
    public final int zzgtu;
    public final boolean zzgvi;
    public final zzxq zzhey;
    public final zzaak zzhez;
    public final zzvg zzhfa;
    public final String zzhfb;
    public final ArrayList<String> zzhfc;
    public final ArrayList<String> zzhfd;
    public final zzvs zzhfe;
    public final PublisherAdViewOptions zzhff;
    public final zzxk zzhfg;
    public final zzdob zzhfh;

    private zzdok(zzdom zzdom) {
        zzaak zzaak;
        zzadu zzadu;
        this.zzbpf = zzdom.zzbpf;
        this.zzhfb = zzdom.zzhfb;
        this.zzhey = zzdom.zzhey;
        this.zzhfa = new zzvg(zzdom.zzhfa.versionCode, zzdom.zzhfa.zzchb, zzdom.zzhfa.extras, zzdom.zzhfa.zzchc, zzdom.zzhfa.zzchd, zzdom.zzhfa.zzche, zzdom.zzhfa.zzadm, zzdom.zzhfa.zzbny || zzdom.zzbny, zzdom.zzhfa.zzchf, zzdom.zzhfa.zzchg, zzdom.zzhfa.zznb, zzdom.zzhfa.zzchh, zzdom.zzhfa.zzchi, zzdom.zzhfa.zzchj, zzdom.zzhfa.zzchk, zzdom.zzhfa.zzchl, zzdom.zzhfa.zzchm, zzdom.zzhfa.zzchn, zzdom.zzhfa.zzcho, zzdom.zzhfa.zzadn, zzdom.zzhfa.zzado, zzdom.zzhfa.zzchp);
        if (zzdom.zzhez != null) {
            zzaak = zzdom.zzhez;
        } else {
            zzaak = zzdom.zzdly != null ? zzdom.zzdly.zzddn : null;
        }
        this.zzhez = zzaak;
        this.zzhfc = zzdom.zzhfc;
        this.zzhfd = zzdom.zzhfd;
        if (zzdom.zzhfc == null) {
            zzadu = null;
        } else if (zzdom.zzdly == null) {
            zzadu = new zzadu(new NativeAdOptions.Builder().build());
        } else {
            zzadu = zzdom.zzdly;
        }
        this.zzdly = zzadu;
        this.zzhfe = zzdom.zzhfe;
        this.zzgtu = zzdom.zzgtu;
        this.zzhff = zzdom.zzhff;
        this.zzhfg = zzdom.zzhfg;
        this.zzdsi = zzdom.zzdsi;
        this.zzhfh = new zzdob(zzdom.zzhfj);
        this.zzgvi = zzdom.zzgvi;
    }

    public final zzafy zzati() {
        PublisherAdViewOptions publisherAdViewOptions = this.zzhff;
        if (publisherAdViewOptions == null) {
            return null;
        }
        return publisherAdViewOptions.zzjw();
    }
}
