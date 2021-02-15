package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdom {
    /* access modifiers changed from: private */
    public boolean zzbny;
    /* access modifiers changed from: private */
    public zzvn zzbpf;
    /* access modifiers changed from: private */
    public zzadu zzdly;
    /* access modifiers changed from: private */
    public zzajc zzdsi;
    /* access modifiers changed from: private */
    public int zzgtu = 1;
    /* access modifiers changed from: private */
    public boolean zzgvi = false;
    /* access modifiers changed from: private */
    public zzxq zzhey;
    /* access modifiers changed from: private */
    public zzaak zzhez;
    /* access modifiers changed from: private */
    public zzvg zzhfa;
    /* access modifiers changed from: private */
    public String zzhfb;
    /* access modifiers changed from: private */
    public ArrayList<String> zzhfc;
    /* access modifiers changed from: private */
    public ArrayList<String> zzhfd;
    /* access modifiers changed from: private */
    public zzvs zzhfe;
    /* access modifiers changed from: private */
    public PublisherAdViewOptions zzhff;
    /* access modifiers changed from: private */
    public zzxk zzhfg;
    /* access modifiers changed from: private */
    public zzdod zzhfj = new zzdod();

    public final zzdom zzh(zzvg zzvg) {
        this.zzhfa = zzvg;
        return this;
    }

    public final zzvg zzatk() {
        return this.zzhfa;
    }

    public final zzdom zze(zzvn zzvn) {
        this.zzbpf = zzvn;
        return this;
    }

    public final zzdom zzbo(boolean z) {
        this.zzgvi = z;
        return this;
    }

    public final zzvn zzkg() {
        return this.zzbpf;
    }

    public final zzdom zzc(zzxq zzxq) {
        this.zzhey = zzxq;
        return this;
    }

    public final zzdom zzgt(String str) {
        this.zzhfb = str;
        return this;
    }

    public final String zzatl() {
        return this.zzhfb;
    }

    public final zzdom zzc(zzaak zzaak) {
        this.zzhez = zzaak;
        return this;
    }

    public final zzdod zzatm() {
        return this.zzhfj;
    }

    public final zzdom zzbp(boolean z) {
        this.zzbny = z;
        return this;
    }

    public final zzdom zzec(int i) {
        this.zzgtu = i;
        return this;
    }

    public final zzdom zzc(ArrayList<String> arrayList) {
        this.zzhfc = arrayList;
        return this;
    }

    public final zzdom zzd(ArrayList<String> arrayList) {
        this.zzhfd = arrayList;
        return this;
    }

    public final zzdom zzb(zzadu zzadu) {
        this.zzdly = zzadu;
        return this;
    }

    public final zzdom zzb(zzvs zzvs) {
        this.zzhfe = zzvs;
        return this;
    }

    public final zzdom zzb(zzajc zzajc) {
        this.zzdsi = zzajc;
        this.zzhez = new zzaak(false, true, false);
        return this;
    }

    public final zzdom zzb(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzhff = publisherAdViewOptions;
        if (publisherAdViewOptions != null) {
            this.zzbny = publisherAdViewOptions.getManualImpressionsEnabled();
            this.zzhfg = publisherAdViewOptions.zzjv();
        }
        return this;
    }

    public final zzdom zzc(zzdok zzdok) {
        this.zzhfj.zza(zzdok.zzhfh);
        this.zzhfa = zzdok.zzhfa;
        this.zzbpf = zzdok.zzbpf;
        this.zzhey = zzdok.zzhey;
        this.zzhfb = zzdok.zzhfb;
        this.zzhez = zzdok.zzhez;
        this.zzhfc = zzdok.zzhfc;
        this.zzhfd = zzdok.zzhfd;
        this.zzdly = zzdok.zzdly;
        this.zzhfe = zzdok.zzhfe;
        zzdom zzb = zzb(zzdok.zzhff);
        zzb.zzgvi = zzdok.zzgvi;
        return zzb;
    }

    public final zzdok zzatn() {
        Preconditions.checkNotNull(this.zzhfb, "ad unit must not be null");
        Preconditions.checkNotNull(this.zzbpf, "ad size must not be null");
        Preconditions.checkNotNull(this.zzhfa, "ad request must not be null");
        return new zzdok(this);
    }

    public final boolean zzato() {
        return this.zzgvi;
    }
}
