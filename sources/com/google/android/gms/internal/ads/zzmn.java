package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmn implements zzjx, zzmx, zznj, zzow<zzmu> {
    /* access modifiers changed from: private */
    public final Handler handler;
    private final Uri uri;
    private final Handler zzaes;
    private boolean zzafu;
    /* access modifiers changed from: private */
    public boolean zzagi;
    private long zzaih;
    private final zzom zzaos;
    private final int zzbdj;
    /* access modifiers changed from: private */
    public final zzmy zzbdk;
    private final zznc zzbdl;
    private final zzok zzbdm;
    /* access modifiers changed from: private */
    public final String zzbdn;
    /* access modifiers changed from: private */
    public final long zzbdo;
    private final zzot zzbdp = new zzot("Loader:ExtractorMediaPeriod");
    private final zzmt zzbdq;
    private final zzpb zzbdr;
    private final Runnable zzbds;
    /* access modifiers changed from: private */
    public final Runnable zzbdt;
    /* access modifiers changed from: private */
    public final SparseArray<zznh> zzbdu;
    /* access modifiers changed from: private */
    public zzna zzbdv;
    private zzke zzbdw;
    private boolean zzbdx;
    private boolean zzbdy;
    private boolean zzbdz;
    private int zzbea;
    private zznp zzbeb;
    private boolean[] zzbec;
    private boolean[] zzbed;
    private boolean zzbee;
    private long zzbef;
    private long zzbeg;
    private int zzbeh;
    private boolean zzbei;
    private long zzcp;

    public zzmn(Uri uri2, zzom zzom, zzjv[] zzjvArr, int i, Handler handler2, zzmy zzmy, zznc zznc, zzok zzok, String str, int i2) {
        this.uri = uri2;
        this.zzaos = zzom;
        this.zzbdj = i;
        this.zzaes = handler2;
        this.zzbdk = zzmy;
        this.zzbdl = zznc;
        this.zzbdm = zzok;
        this.zzbdn = str;
        this.zzbdo = (long) i2;
        this.zzbdq = new zzmt(zzjvArr, this);
        this.zzbdr = new zzpb();
        this.zzbds = new zzmq(this);
        this.zzbdt = new zzmp(this);
        this.handler = new Handler();
        this.zzbeg = -9223372036854775807L;
        this.zzbdu = new SparseArray<>();
        this.zzcp = -1;
    }

    public final void zzee(long j) {
    }

    public final void release() {
        this.zzbdp.zza((Runnable) new zzms(this, this.zzbdq));
        this.handler.removeCallbacksAndMessages((Object) null);
        this.zzagi = true;
    }

    public final void zza(zzna zzna, long j) {
        this.zzbdv = zzna;
        this.zzbdr.open();
        startLoading();
    }

    public final void zzhn() throws IOException {
        this.zzbdp.zzbj(Integer.MIN_VALUE);
    }

    public final zznp zzho() {
        return this.zzbeb;
    }

    public final long zza(zzob[] zzobArr, boolean[] zArr, zznm[] zznmArr, boolean[] zArr2, long j) {
        zzoz.checkState(this.zzafu);
        for (int i = 0; i < zzobArr.length; i++) {
            if (zznmArr[i] != null && (zzobArr[i] == null || !zArr[i])) {
                int zza = zznmArr[i].track;
                zzoz.checkState(this.zzbec[zza]);
                this.zzbea--;
                this.zzbec[zza] = false;
                this.zzbdu.valueAt(zza).disable();
                zznmArr[i] = null;
            }
        }
        boolean z = false;
        for (int i2 = 0; i2 < zzobArr.length; i2++) {
            if (zznmArr[i2] == null && zzobArr[i2] != null) {
                zzob zzob = zzobArr[i2];
                zzoz.checkState(zzob.length() == 1);
                zzoz.checkState(zzob.zzbf(0) == 0);
                int zza2 = this.zzbeb.zza(zzob.zzil());
                zzoz.checkState(!this.zzbec[zza2]);
                this.zzbea++;
                this.zzbec[zza2] = true;
                zznmArr[i2] = new zzmw(this, zza2);
                zArr2[i2] = true;
                z = true;
            }
        }
        if (!this.zzbdy) {
            int size = this.zzbdu.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (!this.zzbec[i3]) {
                    this.zzbdu.valueAt(i3).disable();
                }
            }
        }
        if (this.zzbea == 0) {
            this.zzbdz = false;
            if (this.zzbdp.isLoading()) {
                this.zzbdp.zzis();
            }
        } else if (!this.zzbdy ? j != 0 : z) {
            j = zzeg(j);
            for (int i4 = 0; i4 < zznmArr.length; i4++) {
                if (zznmArr[i4] != null) {
                    zArr2[i4] = true;
                }
            }
        }
        this.zzbdy = true;
        return j;
    }

    public final boolean zzef(long j) {
        if (this.zzbei) {
            return false;
        }
        if (this.zzafu && this.zzbea == 0) {
            return false;
        }
        boolean open = this.zzbdr.open();
        if (this.zzbdp.isLoading()) {
            return open;
        }
        startLoading();
        return true;
    }

    public final long zzhp() {
        if (this.zzbea == 0) {
            return Long.MIN_VALUE;
        }
        return zzhr();
    }

    public final long zzhq() {
        if (!this.zzbdz) {
            return -9223372036854775807L;
        }
        this.zzbdz = false;
        return this.zzbef;
    }

    public final long zzhr() {
        long j;
        if (this.zzbei) {
            return Long.MIN_VALUE;
        }
        if (zzhw()) {
            return this.zzbeg;
        }
        if (this.zzbee) {
            j = Long.MAX_VALUE;
            int size = this.zzbdu.size();
            for (int i = 0; i < size; i++) {
                if (this.zzbed[i]) {
                    j = Math.min(j, this.zzbdu.valueAt(i).zzhv());
                }
            }
        } else {
            j = zzhv();
        }
        return j == Long.MIN_VALUE ? this.zzbef : j;
    }

    public final long zzeg(long j) {
        if (!this.zzbdw.isSeekable()) {
            j = 0;
        }
        this.zzbef = j;
        int size = this.zzbdu.size();
        boolean z = !zzhw();
        int i = 0;
        while (z && i < size) {
            if (this.zzbec[i]) {
                z = this.zzbdu.valueAt(i).zze(j, false);
            }
            i++;
        }
        if (!z) {
            this.zzbeg = j;
            this.zzbei = false;
            if (this.zzbdp.isLoading()) {
                this.zzbdp.zzis();
            } else {
                for (int i2 = 0; i2 < size; i2++) {
                    this.zzbdu.valueAt(i2).zzk(this.zzbec[i2]);
                }
            }
        }
        this.zzbdz = false;
        return j;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzbb(int i) {
        if (!this.zzbei) {
            return !zzhw() && this.zzbdu.valueAt(i).zzie();
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void zzhs() throws IOException {
        this.zzbdp.zzbj(Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    public final int zza(int i, zzhq zzhq, zzjk zzjk, boolean z) {
        if (this.zzbdz || zzhw()) {
            return -3;
        }
        return this.zzbdu.valueAt(i).zza(zzhq, zzjk, z, this.zzbei, this.zzbef);
    }

    /* access modifiers changed from: package-private */
    public final void zzd(int i, long j) {
        zznh valueAt = this.zzbdu.valueAt(i);
        if (!this.zzbei || j <= valueAt.zzhv()) {
            valueAt.zze(j, true);
        } else {
            valueAt.zzih();
        }
    }

    public final zzkg zzc(int i, int i2) {
        zznh zznh = this.zzbdu.get(i);
        if (zznh != null) {
            return zznh;
        }
        zznh zznh2 = new zznh(this.zzbdm);
        zznh2.zza(this);
        this.zzbdu.put(i, zznh2);
        return zznh2;
    }

    public final void zzgr() {
        this.zzbdx = true;
        this.handler.post(this.zzbds);
    }

    public final void zza(zzke zzke) {
        this.zzbdw = zzke;
        this.handler.post(this.zzbds);
    }

    public final void zzf(zzho zzho) {
        this.handler.post(this.zzbds);
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, boolean], vars: [r4v0 ?, r4v3 ?, r4v5 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    public final void zzht() {
        /*
            r8 = this;
            boolean r0 = r8.zzagi
            if (r0 != 0) goto L_0x009d
            boolean r0 = r8.zzafu
            if (r0 != 0) goto L_0x009d
            com.google.android.gms.internal.ads.zzke r0 = r8.zzbdw
            if (r0 == 0) goto L_0x009d
            boolean r0 = r8.zzbdx
            if (r0 != 0) goto L_0x0012
            goto L_0x009d
        L_0x0012:
            android.util.SparseArray<com.google.android.gms.internal.ads.zznh> r0 = r8.zzbdu
            int r0 = r0.size()
            r1 = 0
            r2 = 0
        L_0x001a:
            if (r2 >= r0) goto L_0x002e
            android.util.SparseArray<com.google.android.gms.internal.ads.zznh> r3 = r8.zzbdu
            java.lang.Object r3 = r3.valueAt(r2)
            com.google.android.gms.internal.ads.zznh r3 = (com.google.android.gms.internal.ads.zznh) r3
            com.google.android.gms.internal.ads.zzho r3 = r3.zzif()
            if (r3 != 0) goto L_0x002b
            return
        L_0x002b:
            int r2 = r2 + 1
            goto L_0x001a
        L_0x002e:
            com.google.android.gms.internal.ads.zzpb r2 = r8.zzbdr
            r2.zziu()
            com.google.android.gms.internal.ads.zznq[] r2 = new com.google.android.gms.internal.ads.zznq[r0]
            boolean[] r3 = new boolean[r0]
            r8.zzbed = r3
            boolean[] r3 = new boolean[r0]
            r8.zzbec = r3
            com.google.android.gms.internal.ads.zzke r3 = r8.zzbdw
            long r3 = r3.getDurationUs()
            r8.zzaih = r3
            r3 = 0
        L_0x0046:
            r4 = 1
            if (r3 >= r0) goto L_0x007c
            android.util.SparseArray<com.google.android.gms.internal.ads.zznh> r5 = r8.zzbdu
            java.lang.Object r5 = r5.valueAt(r3)
            com.google.android.gms.internal.ads.zznh r5 = (com.google.android.gms.internal.ads.zznh) r5
            com.google.android.gms.internal.ads.zzho r5 = r5.zzif()
            com.google.android.gms.internal.ads.zznq r6 = new com.google.android.gms.internal.ads.zznq
            com.google.android.gms.internal.ads.zzho[] r7 = new com.google.android.gms.internal.ads.zzho[r4]
            r7[r1] = r5
            r6.<init>(r7)
            r2[r3] = r6
            java.lang.String r5 = r5.zzahc
            boolean r6 = com.google.android.gms.internal.ads.zzpg.zzbg(r5)
            if (r6 != 0) goto L_0x0070
            boolean r5 = com.google.android.gms.internal.ads.zzpg.zzbf(r5)
            if (r5 == 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r4 = 0
        L_0x0070:
            boolean[] r5 = r8.zzbed
            r5[r3] = r4
            boolean r5 = r8.zzbee
            r4 = r4 | r5
            r8.zzbee = r4
            int r3 = r3 + 1
            goto L_0x0046
        L_0x007c:
            com.google.android.gms.internal.ads.zznp r0 = new com.google.android.gms.internal.ads.zznp
            r0.<init>(r2)
            r8.zzbeb = r0
            r8.zzafu = r4
            com.google.android.gms.internal.ads.zznc r0 = r8.zzbdl
            com.google.android.gms.internal.ads.zznn r1 = new com.google.android.gms.internal.ads.zznn
            long r2 = r8.zzaih
            com.google.android.gms.internal.ads.zzke r4 = r8.zzbdw
            boolean r4 = r4.isSeekable()
            r1.<init>(r2, r4)
            r2 = 0
            r0.zzb(r1, r2)
            com.google.android.gms.internal.ads.zzna r0 = r8.zzbdv
            r0.zza(r8)
        L_0x009d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzmn.zzht():void");
    }

    private final void zza(zzmu zzmu) {
        if (this.zzcp == -1) {
            this.zzcp = zzmu.zzcp;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        r0 = r9.zzbdw;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void startLoading() {
        /*
            r9 = this;
            com.google.android.gms.internal.ads.zzmu r6 = new com.google.android.gms.internal.ads.zzmu
            android.net.Uri r2 = r9.uri
            com.google.android.gms.internal.ads.zzom r3 = r9.zzaos
            com.google.android.gms.internal.ads.zzmt r4 = r9.zzbdq
            com.google.android.gms.internal.ads.zzpb r5 = r9.zzbdr
            r0 = r6
            r1 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            boolean r0 = r9.zzafu
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 == 0) goto L_0x0040
            boolean r0 = r9.zzhw()
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            long r3 = r9.zzaih
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0031
            long r7 = r9.zzbeg
            int r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x0031
            r0 = 1
            r9.zzbei = r0
            r9.zzbeg = r1
            return
        L_0x0031:
            com.google.android.gms.internal.ads.zzke r0 = r9.zzbdw
            long r3 = r9.zzbeg
            long r3 = r0.zzdz(r3)
            long r7 = r9.zzbeg
            r6.zze(r3, r7)
            r9.zzbeg = r1
        L_0x0040:
            int r0 = r9.zzhu()
            r9.zzbeh = r0
            int r0 = r9.zzbdj
            r3 = -1
            if (r0 != r3) goto L_0x0067
            boolean r0 = r9.zzafu
            if (r0 == 0) goto L_0x0066
            long r3 = r9.zzcp
            r7 = -1
            int r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x0066
            com.google.android.gms.internal.ads.zzke r0 = r9.zzbdw
            if (r0 == 0) goto L_0x0064
            long r3 = r0.getDurationUs()
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            r0 = 6
            goto L_0x0067
        L_0x0066:
            r0 = 3
        L_0x0067:
            com.google.android.gms.internal.ads.zzot r1 = r9.zzbdp
            r1.zza(r6, r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzmn.startLoading():void");
    }

    private final int zzhu() {
        int size = this.zzbdu.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.zzbdu.valueAt(i2).zzid();
        }
        return i;
    }

    private final long zzhv() {
        int size = this.zzbdu.size();
        long j = Long.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            j = Math.max(j, this.zzbdu.valueAt(i).zzhv());
        }
        return j;
    }

    private final boolean zzhw() {
        return this.zzbeg != -9223372036854775807L;
    }

    public final /* synthetic */ int zza(zzoy zzoy, long j, long j2, IOException iOException) {
        zzke zzke;
        zzmu zzmu = (zzmu) zzoy;
        zza(zzmu);
        Handler handler2 = this.zzaes;
        if (!(handler2 == null || this.zzbdk == null)) {
            handler2.post(new zzmr(this, iOException));
        }
        if (iOException instanceof zzns) {
            return 3;
        }
        boolean z = zzhu() > this.zzbeh;
        if (this.zzcp == -1 && ((zzke = this.zzbdw) == null || zzke.getDurationUs() == -9223372036854775807L)) {
            this.zzbef = 0;
            this.zzbdz = this.zzafu;
            int size = this.zzbdu.size();
            for (int i = 0; i < size; i++) {
                this.zzbdu.valueAt(i).zzk(!this.zzafu || this.zzbec[i]);
            }
            zzmu.zze(0, 0);
        }
        this.zzbeh = zzhu();
        return z ? 1 : 0;
    }

    public final /* synthetic */ void zza(zzoy zzoy, long j, long j2, boolean z) {
        zza((zzmu) zzoy);
        if (!z && this.zzbea > 0) {
            int size = this.zzbdu.size();
            for (int i = 0; i < size; i++) {
                this.zzbdu.valueAt(i).zzk(this.zzbec[i]);
            }
            this.zzbdv.zza(this);
        }
    }

    public final /* synthetic */ void zza(zzoy zzoy, long j, long j2) {
        zza((zzmu) zzoy);
        this.zzbei = true;
        if (this.zzaih == -9223372036854775807L) {
            long zzhv = zzhv();
            this.zzaih = zzhv == Long.MIN_VALUE ? 0 : zzhv + 10000;
            this.zzbdl.zzb(new zznn(this.zzaih, this.zzbdw.isSeekable()), (Object) null);
        }
        this.zzbdv.zza(this);
    }
}
