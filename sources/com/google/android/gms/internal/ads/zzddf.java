package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddf implements zzdgx<zzddc> {
    private final zzdzb zzgcz;
    private final Context zzvr;

    public zzddf(zzdzb zzdzb, Context context) {
        this.zzgcz = zzdzb;
        this.zzvr = context;
    }

    public final zzdzc<zzddc> zzarj() {
        return this.zzgcz.zze(new zzdde(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzddc zzarn() throws Exception {
        AudioManager audioManager = (AudioManager) this.zzvr.getSystemService("audio");
        return new zzddc(audioManager.getMode(), audioManager.isMusicActive(), audioManager.isSpeakerphoneOn(), audioManager.getStreamVolume(3), audioManager.getRingerMode(), audioManager.getStreamVolume(2), zzp.zzkw().zzqg(), zzp.zzkw().zzqh());
    }
}
