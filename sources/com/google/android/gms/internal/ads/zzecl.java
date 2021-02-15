package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzede;
import com.google.android.gms.internal.ads.zzedi;
import com.google.android.gms.internal.ads.zzedu;
import com.google.android.gms.internal.ads.zzeet;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzecl implements zzegs {
    private final String zzhwf;
    private final int zzhwg;
    private zzedu zzhwh;
    private zzede zzhwi;
    private int zzhwj;

    zzecl(zzefe zzefe) throws GeneralSecurityException {
        String zzbbt = zzefe.zzbbt();
        this.zzhwf = zzbbt;
        if (zzbbt.equals(zzeax.zzhvf)) {
            try {
                zzedx zzl = zzedx.zzl(zzefe.zzbbu(), zzejm.zzbft());
                this.zzhwh = (zzedu) zzear.zzb(zzefe);
                this.zzhwg = zzl.getKeySize();
            } catch (zzekj e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (this.zzhwf.equals(zzeax.zzhve)) {
            try {
                zzedh zzf = zzedh.zzf(zzefe.zzbbu(), zzejm.zzbft());
                this.zzhwi = (zzede) zzear.zzb(zzefe);
                this.zzhwj = zzf.zzazp().getKeySize();
                this.zzhwg = this.zzhwj + zzf.zzazq().getKeySize();
            } catch (zzekj e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else {
            String valueOf = String.valueOf(this.zzhwf);
            throw new GeneralSecurityException(valueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(valueOf) : new String("unsupported AEAD DEM key type: "));
        }
    }

    public final int zzazc() {
        return this.zzhwg;
    }

    public final zzdzv zzn(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzhwg) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        } else if (this.zzhwf.equals(zzeax.zzhvf)) {
            return (zzdzv) zzear.zza(this.zzhwf, (zzelj) (zzedu) ((zzejz) ((zzedu.zza) zzedu.zzbah().zza(this.zzhwh)).zzw(zzeip.zzi(bArr, 0, this.zzhwg)).zzbgt()), zzdzv.class);
        } else if (this.zzhwf.equals(zzeax.zzhve)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zzhwj);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zzhwj, this.zzhwg);
            zzede.zza zzb = zzede.zzazn().zzet(this.zzhwi.getVersion()).zzb((zzedi) ((zzejz) ((zzedi.zza) zzedi.zzazt().zza(this.zzhwi.zzazl())).zzu(zzeip.zzu(copyOfRange)).zzbgt()));
            return (zzdzv) zzear.zza(this.zzhwf, (zzelj) (zzede) ((zzejz) zzb.zzb((zzeet) ((zzejz) ((zzeet.zza) zzeet.zzbbl().zza(this.zzhwi.zzazm())).zzad(zzeip.zzu(copyOfRange2)).zzbgt())).zzbgt()), zzdzv.class);
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }
}
