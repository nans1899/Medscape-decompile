package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.internal.measurement.zzca;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzhz;
import com.google.android.gms.internal.measurement.zzlq;
import com.google.android.gms.internal.measurement.zzmj;
import com.google.android.gms.internal.measurement.zzng;
import com.google.android.gms.internal.measurement.zznt;
import com.google.android.gms.internal.measurement.zzny;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlinx.coroutines.DebugKt;
import org.joda.time.DateTimeConstants;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public class zzki implements zzgq {
    private static volatile zzki zza;
    private final zzkv zzaa;
    private zzfp zzb;
    private zzeu zzc;
    private zzac zzd;
    private zzfb zze;
    private zzke zzf;
    private zzo zzg;
    private final zzks zzh;
    private zzie zzi;
    private zzjo zzj;
    /* access modifiers changed from: private */
    public final zzfv zzk;
    private boolean zzl;
    private boolean zzm;
    private long zzn;
    private List<Runnable> zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private FileLock zzu;
    private FileChannel zzv;
    private List<Long> zzw;
    private List<Long> zzx;
    private long zzy;
    private final Map<String, zzad> zzz;

    /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
    private class zza implements zzae {
        zzcd.zzg zza;
        List<Long> zzb;
        List<zzcd.zzc> zzc;
        private long zzd;

        private zza() {
        }

        public final void zza(zzcd.zzg zzg) {
            Preconditions.checkNotNull(zzg);
            this.zza = zzg;
        }

        public final boolean zza(long j, zzcd.zzc zzc2) {
            Preconditions.checkNotNull(zzc2);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (this.zzc.size() > 0 && zza(this.zzc.get(0)) != zza(zzc2)) {
                return false;
            }
            long zzbo = this.zzd + ((long) zzc2.zzbo());
            if (zzbo >= ((long) Math.max(0, zzat.zzh.zza(null).intValue()))) {
                return false;
            }
            this.zzd = zzbo;
            this.zzc.add(zzc2);
            this.zzb.add(Long.valueOf(j));
            if (this.zzc.size() >= Math.max(1, zzat.zzi.zza(null).intValue())) {
                return false;
            }
            return true;
        }

        private static long zza(zzcd.zzc zzc2) {
            return ((zzc2.zze() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzki zzki, zzkl zzkl) {
            this();
        }
    }

    public static zzki zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzki.class) {
                if (zza == null) {
                    zza = new zzki(new zzkq(context));
                }
            }
        }
        return zza;
    }

    private zzki(zzkq zzkq) {
        this(zzkq, (zzfv) null);
    }

    private zzki(zzkq zzkq, zzfv zzfv) {
        this.zzl = false;
        this.zzaa = new zzkp(this);
        Preconditions.checkNotNull(zzkq);
        this.zzk = zzfv.zza(zzkq.zza, (zzae) null, (Long) null);
        this.zzy = -1;
        zzks zzks = new zzks(this);
        zzks.zzak();
        this.zzh = zzks;
        zzeu zzeu = new zzeu(this);
        zzeu.zzak();
        this.zzc = zzeu;
        zzfp zzfp = new zzfp(this);
        zzfp.zzak();
        this.zzb = zzfp;
        this.zzz = new HashMap();
        this.zzk.zzp().zza((Runnable) new zzkl(this, zzkq));
    }

    /* access modifiers changed from: private */
    public final void zza(zzkq zzkq) {
        this.zzk.zzp().zzc();
        zzac zzac = new zzac(this);
        zzac.zzak();
        this.zzd = zzac;
        this.zzk.zza().zza((zzaa) this.zzb);
        zzjo zzjo = new zzjo(this);
        zzjo.zzak();
        this.zzj = zzjo;
        zzo zzo2 = new zzo(this);
        zzo2.zzak();
        this.zzg = zzo2;
        zzie zzie = new zzie(this);
        zzie.zzak();
        this.zzi = zzie;
        zzke zzke = new zzke(this);
        zzke.zzak();
        this.zzf = zzke;
        this.zze = new zzfb(this);
        if (this.zzp != this.zzq) {
            this.zzk.zzq().zze().zza("Not all upload components initialized", Integer.valueOf(this.zzp), Integer.valueOf(this.zzq));
        }
        this.zzl = true;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        this.zzk.zzp().zzc();
        zze().zzu();
        if (this.zzk.zzb().zzc.zza() == 0) {
            this.zzk.zzb().zzc.zza(this.zzk.zzl().currentTimeMillis());
        }
        zzab();
    }

    public final zzx zzt() {
        return this.zzk.zzt();
    }

    public final zzy zzb() {
        return this.zzk.zza();
    }

    public final zzer zzq() {
        return this.zzk.zzq();
    }

    public final zzfo zzp() {
        return this.zzk.zzp();
    }

    public final zzfp zzc() {
        zzb((zzkj) this.zzb);
        return this.zzb;
    }

    public final zzeu zzd() {
        zzb((zzkj) this.zzc);
        return this.zzc;
    }

    public final zzac zze() {
        zzb((zzkj) this.zzd);
        return this.zzd;
    }

    private final zzfb zzv() {
        zzfb zzfb = this.zze;
        if (zzfb != null) {
            return zzfb;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzke zzw() {
        zzb((zzkj) this.zzf);
        return this.zzf;
    }

    public final zzo zzf() {
        zzb((zzkj) this.zzg);
        return this.zzg;
    }

    public final zzie zzg() {
        zzb((zzkj) this.zzi);
        return this.zzi;
    }

    public final zzks zzh() {
        zzb((zzkj) this.zzh);
        return this.zzh;
    }

    public final zzjo zzi() {
        return this.zzj;
    }

    public final zzep zzj() {
        return this.zzk.zzi();
    }

    public final Context zzm() {
        return this.zzk.zzm();
    }

    public final Clock zzl() {
        return this.zzk.zzl();
    }

    public final zzkw zzk() {
        return this.zzk.zzh();
    }

    private final void zzx() {
        this.zzk.zzp().zzc();
    }

    /* access modifiers changed from: package-private */
    public final void zzn() {
        if (!this.zzl) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zzb(zzkj zzkj) {
        if (zzkj == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzkj.zzai()) {
            String valueOf = String.valueOf(zzkj.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzad zzad) {
        if (zzmj.zzb() && this.zzk.zza().zza(zzat.zzci)) {
            zzx();
            zzn();
            this.zzz.put(str, zzad);
            zzac zze2 = zze();
            if (zzmj.zzb() && zze2.zzs().zza(zzat.zzci)) {
                Preconditions.checkNotNull(str);
                Preconditions.checkNotNull(zzad);
                zze2.zzc();
                zze2.zzaj();
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("consent_state", zzad.zza());
                try {
                    if (zze2.c_().insertWithOnConflict("consent_settings", (String) null, contentValues, 5) == -1) {
                        zze2.zzq().zze().zza("Failed to insert/update consent setting (got -1). appId", zzer.zza(str));
                    }
                } catch (SQLiteException e) {
                    zze2.zzq().zze().zza("Error storing consent setting. appId, error", zzer.zza(str), e);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final zzad zza(String str) {
        zzad zzad = zzad.zza;
        if (zzmj.zzb() && this.zzk.zza().zza(zzat.zzci)) {
            zzx();
            zzn();
            zzad = this.zzz.get(str);
            if (zzad == null) {
                zzad = zze().zzj(str);
                if (zzad == null) {
                    zzad = zzad.zza;
                }
                zza(str, zzad);
            }
        }
        return zzad;
    }

    private final long zzy() {
        long currentTimeMillis = this.zzk.zzl().currentTimeMillis();
        zzfd zzb2 = this.zzk.zzb();
        zzb2.zzaa();
        zzb2.zzc();
        long zza2 = zzb2.zzg.zza();
        if (zza2 == 0) {
            zza2 = 1 + ((long) zzb2.zzo().zzg().nextInt(DateTimeConstants.MILLIS_PER_DAY));
            zzb2.zzg.zza(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzar r36, java.lang.String r37) {
        /*
            r35 = this;
            r0 = r35
            r1 = r36
            r3 = r37
            com.google.android.gms.measurement.internal.zzac r2 = r35.zze()
            com.google.android.gms.measurement.internal.zzf r2 = r2.zzb(r3)
            if (r2 == 0) goto L_0x0112
            java.lang.String r4 = r2.zzl()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x001c
            goto L_0x0112
        L_0x001c:
            java.lang.Boolean r4 = r0.zzb((com.google.android.gms.measurement.internal.zzf) r2)
            if (r4 != 0) goto L_0x0040
            java.lang.String r4 = r1.zza
            java.lang.String r5 = "_ui"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x005a
            com.google.android.gms.measurement.internal.zzfv r4 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzq()
            com.google.android.gms.measurement.internal.zzet r4 = r4.zzh()
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r37)
            java.lang.String r6 = "Could not find package. appId"
            r4.zza(r6, r5)
            goto L_0x005a
        L_0x0040:
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L_0x005a
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zze()
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r37)
            java.lang.String r3 = "App version does not match; dropping event. appId"
            r1.zza(r3, r2)
            return
        L_0x005a:
            com.google.android.gms.measurement.internal.zzn r15 = new com.google.android.gms.measurement.internal.zzn
            java.lang.String r4 = r2.zze()
            java.lang.String r5 = r2.zzl()
            long r6 = r2.zzm()
            java.lang.String r8 = r2.zzn()
            long r9 = r2.zzo()
            long r11 = r2.zzp()
            boolean r14 = r2.zzr()
            r16 = 0
            java.lang.String r17 = r2.zzi()
            long r18 = r2.zzae()
            r20 = 0
            r22 = 0
            boolean r23 = r2.zzaf()
            boolean r24 = r2.zzag()
            r25 = 0
            java.lang.String r26 = r2.zzf()
            java.lang.Boolean r27 = r2.zzah()
            long r28 = r2.zzq()
            java.util.List r30 = r2.zzai()
            boolean r31 = com.google.android.gms.internal.measurement.zznt.zzb()
            if (r31 == 0) goto L_0x00bf
            com.google.android.gms.measurement.internal.zzfv r13 = r0.zzk
            com.google.android.gms.measurement.internal.zzy r13 = r13.zza()
            java.lang.String r1 = r2.zzc()
            r32 = r14
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzat.zzbi
            boolean r1 = r13.zze(r1, r14)
            if (r1 == 0) goto L_0x00c1
            java.lang.String r1 = r2.zzg()
            goto L_0x00c2
        L_0x00bf:
            r32 = r14
        L_0x00c1:
            r1 = 0
        L_0x00c2:
            boolean r2 = com.google.android.gms.internal.measurement.zzmj.zzb()
            if (r2 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzfv r2 = r0.zzk
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r13 = com.google.android.gms.measurement.internal.zzat.zzci
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r13)
            if (r2 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzad r2 = r0.zza((java.lang.String) r3)
            java.lang.String r2 = r2.zza()
            goto L_0x00e1
        L_0x00df:
            java.lang.String r2 = ""
        L_0x00e1:
            r33 = r2
            r2 = r15
            r3 = r37
            r13 = 0
            r14 = r32
            r34 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r19 = r20
            r21 = r22
            r22 = r23
            r23 = r24
            r24 = r25
            r25 = r26
            r26 = r27
            r27 = r28
            r29 = r30
            r30 = r1
            r31 = r33
            r2.<init>((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (long) r6, (java.lang.String) r8, (long) r9, (long) r11, (java.lang.String) r13, (boolean) r14, (boolean) r15, (java.lang.String) r16, (long) r17, (long) r19, (int) r21, (boolean) r22, (boolean) r23, (boolean) r24, (java.lang.String) r25, (java.lang.Boolean) r26, (long) r27, (java.util.List<java.lang.String>) r29, (java.lang.String) r30, (java.lang.String) r31)
            r1 = r36
            r2 = r34
            r0.zzb((com.google.android.gms.measurement.internal.zzar) r1, (com.google.android.gms.measurement.internal.zzn) r2)
            return
        L_0x0112:
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzv()
            java.lang.String r2 = "No app data available; dropping event"
            r1.zza(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zza(com.google.android.gms.measurement.internal.zzar, java.lang.String):void");
    }

    private final void zzb(zzar zzar, zzn zzn2) {
        if (zzny.zzb() && this.zzk.zza().zza(zzat.zzbz)) {
            zzev zza2 = zzev.zza(zzar);
            this.zzk.zzh().zza(zza2.zzb, zze().zzi(zzn2.zza));
            this.zzk.zzh().zza(zza2, this.zzk.zza().zza(zzn2.zza));
            zzar = zza2.zza();
        }
        if (this.zzk.zza().zza(zzat.zzbd) && Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzar.zza) && "referrer API v2".equals(zzar.zzb.zzd("_cis"))) {
            String zzd2 = zzar.zzb.zzd("gclid");
            if (!TextUtils.isEmpty(zzd2)) {
                zza(new zzkr("_lgclid", zzar.zzd, zzd2, DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzn2);
            }
        }
        zza(zzar, zzn2);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzar zzar, zzn zzn2) {
        List<zzw> list;
        List<zzw> list2;
        List<zzw> list3;
        zzar zzar2 = zzar;
        zzn zzn3 = zzn2;
        Preconditions.checkNotNull(zzn2);
        Preconditions.checkNotEmpty(zzn3.zza);
        zzx();
        zzn();
        String str = zzn3.zza;
        long j = zzar2.zzd;
        zzh();
        if (zzks.zza(zzar, zzn2)) {
            if (!zzn3.zzh) {
                zzc(zzn3);
                return;
            }
            if (zzn3.zzu != null) {
                if (zzn3.zzu.contains(zzar2.zza)) {
                    Bundle zzb2 = zzar2.zzb.zzb();
                    zzb2.putLong("ga_safelisted", 1);
                    zzar2 = new zzar(zzar2.zza, new zzam(zzb2), zzar2.zzc, zzar2.zzd);
                } else {
                    this.zzk.zzq().zzv().zza("Dropping non-safelisted event. appId, event name, origin", str, zzar2.zza, zzar2.zzc);
                    return;
                }
            }
            zze().zze();
            try {
                zzac zze2 = zze();
                Preconditions.checkNotEmpty(str);
                zze2.zzc();
                zze2.zzaj();
                int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if (i < 0) {
                    zze2.zzq().zzh().zza("Invalid time querying timed out conditional properties", zzer.zza(str), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zze2.zza("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzw next : list) {
                    if (next != null) {
                        this.zzk.zzq().zzw().zza("User property timed out", next.zza, this.zzk.zzi().zzc(next.zzc.zza), next.zzc.zza());
                        if (next.zzg != null) {
                            zzc(new zzar(next.zzg, j), zzn3);
                        }
                        zze().zze(str, next.zzc.zza);
                    }
                }
                zzac zze3 = zze();
                Preconditions.checkNotEmpty(str);
                zze3.zzc();
                zze3.zzaj();
                if (i < 0) {
                    zze3.zzq().zzh().zza("Invalid time querying expired conditional properties", zzer.zza(str), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zze3.zza("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzw next2 : list2) {
                    if (next2 != null) {
                        this.zzk.zzq().zzw().zza("User property expired", next2.zza, this.zzk.zzi().zzc(next2.zzc.zza), next2.zzc.zza());
                        zze().zzb(str, next2.zzc.zza);
                        if (next2.zzk != null) {
                            arrayList.add(next2.zzk);
                        }
                        zze().zze(str, next2.zzc.zza);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList2.get(i2);
                    i2++;
                    zzc(new zzar((zzar) obj, j), zzn3);
                }
                zzac zze4 = zze();
                String str2 = zzar2.zza;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zze4.zzc();
                zze4.zzaj();
                if (i < 0) {
                    zze4.zzq().zzh().zza("Invalid time querying triggered conditional properties", zzer.zza(str), zze4.zzn().zza(str2), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zze4.zza("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzw next3 : list3) {
                    if (next3 != null) {
                        zzkr zzkr = next3.zzc;
                        zzkt zzkt = r4;
                        zzkt zzkt2 = new zzkt(next3.zza, next3.zzb, zzkr.zza, j, zzkr.zza());
                        if (zze().zza(zzkt)) {
                            this.zzk.zzq().zzw().zza("User property triggered", next3.zza, this.zzk.zzi().zzc(zzkt.zzc), zzkt.zze);
                        } else {
                            this.zzk.zzq().zze().zza("Too many active user properties, ignoring", zzer.zza(next3.zza), this.zzk.zzi().zzc(zzkt.zzc), zzkt.zze);
                        }
                        if (next3.zzi != null) {
                            arrayList3.add(next3.zzi);
                        }
                        next3.zzc = new zzkr(zzkt);
                        next3.zze = true;
                        zze().zza(next3);
                    }
                }
                zzc(zzar2, zzn3);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj2 = arrayList4.get(i3);
                    i3++;
                    zzc(new zzar((zzar) obj2, j), zzn3);
                }
                zze().b_();
            } finally {
                zze().zzg();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x02f3 A[Catch:{ SQLiteException -> 0x0282, all -> 0x09bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0302  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0352 A[Catch:{ SQLiteException -> 0x0282, all -> 0x09bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x037f  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x0970 A[Catch:{ SQLiteException -> 0x0282, all -> 0x09bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x016a A[Catch:{ SQLiteException -> 0x0282, all -> 0x09bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0172 A[Catch:{ SQLiteException -> 0x0282, all -> 0x09bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02ba A[Catch:{ SQLiteException -> 0x0282, all -> 0x09bf }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzc(com.google.android.gms.measurement.internal.zzar r27, com.google.android.gms.measurement.internal.zzn r28) {
        /*
            r26 = this;
            r1 = r26
            r2 = r27
            r3 = r28
            java.lang.String r4 = "_sno"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r28)
            java.lang.String r5 = r3.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            long r5 = java.lang.System.nanoTime()
            r26.zzx()
            r26.zzn()
            java.lang.String r15 = r3.zza
            r26.zzh()
            boolean r7 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.measurement.internal.zzar) r27, (com.google.android.gms.measurement.internal.zzn) r28)
            if (r7 != 0) goto L_0x0026
            return
        L_0x0026:
            boolean r7 = r3.zzh
            if (r7 != 0) goto L_0x002e
            r1.zzc(r3)
            return
        L_0x002e:
            com.google.android.gms.measurement.internal.zzfp r7 = r26.zzc()
            java.lang.String r8 = r2.zza
            boolean r7 = r7.zzb(r15, r8)
            java.lang.String r14 = "_err"
            r13 = 0
            if (r7 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()
            com.google.android.gms.measurement.internal.zzet r3 = r3.zzh()
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r15)
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzk
            com.google.android.gms.measurement.internal.zzep r5 = r5.zzi()
            java.lang.String r6 = r2.zza
            java.lang.String r5 = r5.zza((java.lang.String) r6)
            java.lang.String r6 = "Dropping blacklisted event. appId"
            r3.zza(r6, r4, r5)
            com.google.android.gms.measurement.internal.zzfp r3 = r26.zzc()
            boolean r3 = r3.zzg(r15)
            if (r3 != 0) goto L_0x0073
            com.google.android.gms.measurement.internal.zzfp r3 = r26.zzc()
            boolean r3 = r3.zzh(r15)
            if (r3 == 0) goto L_0x0071
            goto L_0x0073
        L_0x0071:
            r3 = 0
            goto L_0x0074
        L_0x0073:
            r3 = 1
        L_0x0074:
            if (r3 != 0) goto L_0x0094
            java.lang.String r4 = r2.zza
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L_0x0094
            com.google.android.gms.measurement.internal.zzfv r4 = r1.zzk
            com.google.android.gms.measurement.internal.zzkw r7 = r4.zzh()
            com.google.android.gms.measurement.internal.zzkv r8 = r1.zzaa
            r10 = 11
            java.lang.String r12 = r2.zza
            r2 = 0
            java.lang.String r11 = "_ev"
            r9 = r15
            r4 = r13
            r13 = r2
            r7.zza((com.google.android.gms.measurement.internal.zzkv) r8, (java.lang.String) r9, (int) r10, (java.lang.String) r11, (java.lang.String) r12, (int) r13)
            goto L_0x0095
        L_0x0094:
            r4 = r13
        L_0x0095:
            if (r3 == 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()
            com.google.android.gms.measurement.internal.zzf r2 = r2.zzb(r15)
            if (r2 == 0) goto L_0x00de
            long r5 = r2.zzu()
            long r7 = r2.zzt()
            long r5 = java.lang.Math.max(r5, r7)
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk
            com.google.android.gms.common.util.Clock r3 = r3.zzl()
            long r7 = r3.currentTimeMillis()
            long r7 = r7 - r5
            long r5 = java.lang.Math.abs(r7)
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r3 = com.google.android.gms.measurement.internal.zzat.zzy
            java.lang.Object r3 = r3.zza(r4)
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()
            com.google.android.gms.measurement.internal.zzet r3 = r3.zzv()
            java.lang.String r4 = "Fetching config for blacklisted app"
            r3.zza(r4)
            r1.zza((com.google.android.gms.measurement.internal.zzf) r2)
        L_0x00de:
            return
        L_0x00df:
            boolean r7 = com.google.android.gms.internal.measurement.zzmi.zzb()
            if (r7 == 0) goto L_0x010e
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk
            com.google.android.gms.measurement.internal.zzy r7 = r7.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzat.zzbv
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r8)
            if (r7 == 0) goto L_0x010e
            com.google.android.gms.measurement.internal.zzev r2 = com.google.android.gms.measurement.internal.zzev.zza(r27)
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk
            com.google.android.gms.measurement.internal.zzkw r7 = r7.zzh()
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk
            com.google.android.gms.measurement.internal.zzy r8 = r8.zza()
            int r8 = r8.zza((java.lang.String) r15)
            r7.zza((com.google.android.gms.measurement.internal.zzev) r2, (int) r8)
            com.google.android.gms.measurement.internal.zzar r2 = r2.zza()
        L_0x010e:
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk
            com.google.android.gms.measurement.internal.zzer r7 = r7.zzq()
            r8 = 2
            boolean r7 = r7.zza((int) r8)
            if (r7 == 0) goto L_0x0134
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk
            com.google.android.gms.measurement.internal.zzer r7 = r7.zzq()
            com.google.android.gms.measurement.internal.zzet r7 = r7.zzw()
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk
            com.google.android.gms.measurement.internal.zzep r9 = r9.zzi()
            java.lang.String r9 = r9.zza((com.google.android.gms.measurement.internal.zzar) r2)
            java.lang.String r10 = "Logging event"
            r7.zza(r10, r9)
        L_0x0134:
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()
            r7.zze()
            r1.zzc(r3)     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = "ecommerce_purchase"
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x09bf }
            java.lang.String r9 = "refund"
            if (r7 != 0) goto L_0x015f
            java.lang.String r7 = "purchase"
            java.lang.String r10 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r7 = r7.equals(r10)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x015f
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r7 = r9.equals(r7)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x015d
            goto L_0x015f
        L_0x015d:
            r7 = 0
            goto L_0x0160
        L_0x015f:
            r7 = 1
        L_0x0160:
            java.lang.String r10 = "_iap"
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r10 = r10.equals(r11)     // Catch:{ all -> 0x09bf }
            if (r10 != 0) goto L_0x016f
            if (r7 == 0) goto L_0x016d
            goto L_0x016f
        L_0x016d:
            r10 = 0
            goto L_0x0170
        L_0x016f:
            r10 = 1
        L_0x0170:
            if (r10 == 0) goto L_0x0302
            com.google.android.gms.measurement.internal.zzam r10 = r2.zzb     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = "currency"
            java.lang.String r10 = r10.zzd(r11)     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = "value"
            if (r7 == 0) goto L_0x01d9
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x09bf }
            java.lang.Double r7 = r7.zzc(r11)     // Catch:{ all -> 0x09bf }
            double r17 = r7.doubleValue()     // Catch:{ all -> 0x09bf }
            r19 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r17 = r17 * r19
            r21 = 0
            int r7 = (r17 > r21 ? 1 : (r17 == r21 ? 0 : -1))
            if (r7 != 0) goto L_0x01a2
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x09bf }
            java.lang.Long r7 = r7.zzb(r11)     // Catch:{ all -> 0x09bf }
            long r12 = r7.longValue()     // Catch:{ all -> 0x09bf }
            double r11 = (double) r12     // Catch:{ all -> 0x09bf }
            double r17 = r11 * r19
        L_0x01a2:
            r11 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r7 = (r17 > r11 ? 1 : (r17 == r11 ? 0 : -1))
            if (r7 > 0) goto L_0x01bc
            r11 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r7 = (r17 > r11 ? 1 : (r17 == r11 ? 0 : -1))
            if (r7 < 0) goto L_0x01bc
            long r11 = java.lang.Math.round(r17)     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r7 = r9.equals(r7)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x01e3
            long r11 = -r11
            goto L_0x01e3
        L_0x01bc:
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r7 = r7.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = "Data lost. Currency value is too big. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r15)     // Catch:{ all -> 0x09bf }
            java.lang.Double r10 = java.lang.Double.valueOf(r17)     // Catch:{ all -> 0x09bf }
            r7.zza(r8, r9, r10)     // Catch:{ all -> 0x09bf }
            r23 = r5
            r5 = 0
            r11 = 0
            goto L_0x02f1
        L_0x01d9:
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x09bf }
            java.lang.Long r7 = r7.zzb(r11)     // Catch:{ all -> 0x09bf }
            long r11 = r7.longValue()     // Catch:{ all -> 0x09bf }
        L_0x01e3:
            boolean r7 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x02ed
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = r10.toUpperCase(r7)     // Catch:{ all -> 0x09bf }
            java.lang.String r9 = "[A-Z]{3}"
            boolean r9 = r7.matches(r9)     // Catch:{ all -> 0x09bf }
            if (r9 == 0) goto L_0x02ed
            java.lang.String r9 = "_ltv_"
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x09bf }
            int r10 = r7.length()     // Catch:{ all -> 0x09bf }
            if (r10 == 0) goto L_0x0208
            java.lang.String r7 = r9.concat(r7)     // Catch:{ all -> 0x09bf }
            goto L_0x020d
        L_0x0208:
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x09bf }
            r7.<init>(r9)     // Catch:{ all -> 0x09bf }
        L_0x020d:
            r10 = r7
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkt r7 = r7.zzc(r15, r10)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0249
            java.lang.Object r9 = r7.zze     // Catch:{ all -> 0x09bf }
            boolean r9 = r9 instanceof java.lang.Long     // Catch:{ all -> 0x09bf }
            if (r9 != 0) goto L_0x021f
            goto L_0x0249
        L_0x021f:
            java.lang.Object r7 = r7.zze     // Catch:{ all -> 0x09bf }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x09bf }
            long r7 = r7.longValue()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkt r17 = new com.google.android.gms.measurement.internal.zzkt     // Catch:{ all -> 0x09bf }
            java.lang.String r9 = r2.zzc     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r13 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.common.util.Clock r13 = r13.zzl()     // Catch:{ all -> 0x09bf }
            long r18 = r13.currentTimeMillis()     // Catch:{ all -> 0x09bf }
            long r7 = r7 + r11
            java.lang.Long r13 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x09bf }
            r7 = r17
            r8 = r15
            r23 = r5
            r5 = 0
            r6 = 1
            r11 = r18
            r7.<init>(r8, r9, r10, r11, r13)     // Catch:{ all -> 0x09bf }
        L_0x0246:
            r6 = r17
            goto L_0x02b0
        L_0x0249:
            r23 = r5
            r5 = 0
            r6 = 1
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r9 = r9.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r13 = com.google.android.gms.measurement.internal.zzat.zzad     // Catch:{ all -> 0x09bf }
            int r9 = r9.zzb(r15, r13)     // Catch:{ all -> 0x09bf }
            int r9 = r9 - r6
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)     // Catch:{ all -> 0x09bf }
            r7.zzc()     // Catch:{ all -> 0x09bf }
            r7.zzaj()     // Catch:{ all -> 0x09bf }
            android.database.sqlite.SQLiteDatabase r13 = r7.c_()     // Catch:{ SQLiteException -> 0x0282 }
            java.lang.String r8 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            r6 = 3
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0282 }
            r6[r5] = r15     // Catch:{ SQLiteException -> 0x0282 }
            r16 = 1
            r6[r16] = r15     // Catch:{ SQLiteException -> 0x0282 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x0282 }
            r16 = 2
            r6[r16] = r9     // Catch:{ SQLiteException -> 0x0282 }
            r13.execSQL(r8, r6)     // Catch:{ SQLiteException -> 0x0282 }
            goto L_0x0295
        L_0x0282:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.measurement.internal.zzer r7 = r7.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = "Error pruning currencies. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r15)     // Catch:{ all -> 0x09bf }
            r7.zza(r8, r9, r6)     // Catch:{ all -> 0x09bf }
        L_0x0295:
            com.google.android.gms.measurement.internal.zzkt r17 = new com.google.android.gms.measurement.internal.zzkt     // Catch:{ all -> 0x09bf }
            java.lang.String r9 = r2.zzc     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.common.util.Clock r6 = r6.zzl()     // Catch:{ all -> 0x09bf }
            long r18 = r6.currentTimeMillis()     // Catch:{ all -> 0x09bf }
            java.lang.Long r13 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x09bf }
            r7 = r17
            r8 = r15
            r11 = r18
            r7.<init>(r8, r9, r10, r11, r13)     // Catch:{ all -> 0x09bf }
            goto L_0x0246
        L_0x02b0:
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzkt) r6)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x02f0
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r7 = r7.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r15)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r10 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzep r10 = r10.zzi()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r6.zzc     // Catch:{ all -> 0x09bf }
            java.lang.String r10 = r10.zzc(r11)     // Catch:{ all -> 0x09bf }
            java.lang.Object r6 = r6.zze     // Catch:{ all -> 0x09bf }
            r7.zza(r8, r9, r10, r6)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r7 = r6.zzh()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkv r8 = r1.zzaa     // Catch:{ all -> 0x09bf }
            r10 = 9
            r11 = 0
            r12 = 0
            r13 = 0
            r9 = r15
            r7.zza((com.google.android.gms.measurement.internal.zzkv) r8, (java.lang.String) r9, (int) r10, (java.lang.String) r11, (java.lang.String) r12, (int) r13)     // Catch:{ all -> 0x09bf }
            goto L_0x02f0
        L_0x02ed:
            r23 = r5
            r5 = 0
        L_0x02f0:
            r11 = 1
        L_0x02f1:
            if (r11 != 0) goto L_0x0305
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()     // Catch:{ all -> 0x09bf }
            r2.b_()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()
            r2.zzg()
            return
        L_0x0302:
            r23 = r5
            r5 = 0
        L_0x0305:
            java.lang.String r6 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r6 = com.google.android.gms.measurement.internal.zzkw.zza((java.lang.String) r6)     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r18 = r14.equals(r7)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            r7.zzh()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x09bf }
            long r7 = com.google.android.gms.measurement.internal.zzkw.zza((com.google.android.gms.measurement.internal.zzam) r7)     // Catch:{ all -> 0x09bf }
            r19 = 1
            long r11 = r7 + r19
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            long r8 = r26.zzy()     // Catch:{ all -> 0x09bf }
            r13 = 1
            r16 = 0
            r17 = 0
            r10 = r15
            r14 = r6
            r27 = r15
            r15 = r16
            r16 = r18
            com.google.android.gms.measurement.internal.zzaf r7 = r7.zza(r8, r10, r11, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x09bf }
            long r8 = r7.zzb     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r10 = com.google.android.gms.measurement.internal.zzat.zzj     // Catch:{ all -> 0x09bf }
            r14 = 0
            java.lang.Object r10 = r10.zza(r14)     // Catch:{ all -> 0x09bf }
            java.lang.Integer r10 = (java.lang.Integer) r10     // Catch:{ all -> 0x09bf }
            int r10 = r10.intValue()     // Catch:{ all -> 0x09bf }
            long r10 = (long) r10     // Catch:{ all -> 0x09bf }
            long r8 = r8 - r10
            r10 = 1000(0x3e8, double:4.94E-321)
            r12 = 0
            int r15 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r15 <= 0) goto L_0x037f
            long r8 = r8 % r10
            int r2 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x0370
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r27)     // Catch:{ all -> 0x09bf }
            long r5 = r7.zzb     // Catch:{ all -> 0x09bf }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x09bf }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x09bf }
        L_0x0370:
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()     // Catch:{ all -> 0x09bf }
            r2.b_()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()
            r2.zzg()
            return
        L_0x037f:
            if (r6 == 0) goto L_0x03d6
            long r8 = r7.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r15 = com.google.android.gms.measurement.internal.zzat.zzl     // Catch:{ all -> 0x09bf }
            java.lang.Object r15 = r15.zza(r14)     // Catch:{ all -> 0x09bf }
            java.lang.Integer r15 = (java.lang.Integer) r15     // Catch:{ all -> 0x09bf }
            int r15 = r15.intValue()     // Catch:{ all -> 0x09bf }
            long r14 = (long) r15     // Catch:{ all -> 0x09bf }
            long r8 = r8 - r14
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 <= 0) goto L_0x03d6
            long r8 = r8 % r10
            int r3 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r3 != 0) goto L_0x03b3
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r27)     // Catch:{ all -> 0x09bf }
            long r6 = r7.zza     // Catch:{ all -> 0x09bf }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x09bf }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x09bf }
        L_0x03b3:
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r7 = r3.zzh()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkv r8 = r1.zzaa     // Catch:{ all -> 0x09bf }
            r10 = 16
            java.lang.String r11 = "_ev"
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x09bf }
            r13 = 0
            r9 = r27
            r7.zza((com.google.android.gms.measurement.internal.zzkv) r8, (java.lang.String) r9, (int) r10, (java.lang.String) r11, (java.lang.String) r12, (int) r13)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()     // Catch:{ all -> 0x09bf }
            r2.b_()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()
            r2.zzg()
            return
        L_0x03d6:
            if (r18 == 0) goto L_0x0425
            long r8 = r7.zzd     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r10 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r10 = r10.zza()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r14 = com.google.android.gms.measurement.internal.zzat.zzk     // Catch:{ all -> 0x09bf }
            int r10 = r10.zzb(r11, r14)     // Catch:{ all -> 0x09bf }
            r11 = 1000000(0xf4240, float:1.401298E-39)
            int r10 = java.lang.Math.min(r11, r10)     // Catch:{ all -> 0x09bf }
            int r10 = java.lang.Math.max(r5, r10)     // Catch:{ all -> 0x09bf }
            long r10 = (long) r10     // Catch:{ all -> 0x09bf }
            long r8 = r8 - r10
            int r10 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r10 <= 0) goto L_0x0425
            int r2 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x0416
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r27)     // Catch:{ all -> 0x09bf }
            long r5 = r7.zzd     // Catch:{ all -> 0x09bf }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x09bf }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x09bf }
        L_0x0416:
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()     // Catch:{ all -> 0x09bf }
            r2.b_()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()
            r2.zzg()
            return
        L_0x0425:
            com.google.android.gms.measurement.internal.zzam r7 = r2.zzb     // Catch:{ all -> 0x09bf }
            android.os.Bundle r14 = r7.zzb()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r7 = r7.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = "_o"
            java.lang.String r9 = r2.zzc     // Catch:{ all -> 0x09bf }
            r7.zza((android.os.Bundle) r14, (java.lang.String) r8, (java.lang.Object) r9)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r7 = r7.zzh()     // Catch:{ all -> 0x09bf }
            r15 = r27
            boolean r7 = r7.zze(r15)     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = "_r"
            if (r7 == 0) goto L_0x0464
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r7 = r7.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = "_dbg"
            java.lang.Long r9 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x09bf }
            r7.zza((android.os.Bundle) r14, (java.lang.String) r8, (java.lang.Object) r9)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r7 = r7.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.Long r8 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x09bf }
            r7.zza((android.os.Bundle) r14, (java.lang.String) r11, (java.lang.Object) r8)     // Catch:{ all -> 0x09bf }
        L_0x0464:
            java.lang.String r7 = "_s"
            java.lang.String r8 = r2.zza     // Catch:{ all -> 0x09bf }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x048b
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkt r7 = r7.zzc(r8, r4)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x048b
            java.lang.Object r8 = r7.zze     // Catch:{ all -> 0x09bf }
            boolean r8 = r8 instanceof java.lang.Long     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x048b
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r8 = r8.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.Object r7 = r7.zze     // Catch:{ all -> 0x09bf }
            r8.zza((android.os.Bundle) r14, (java.lang.String) r4, (java.lang.Object) r7)     // Catch:{ all -> 0x09bf }
        L_0x048b:
            com.google.android.gms.measurement.internal.zzac r4 = r26.zze()     // Catch:{ all -> 0x09bf }
            long r7 = r4.zzc(r15)     // Catch:{ all -> 0x09bf }
            int r4 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r4 <= 0) goto L_0x04ae
            com.google.android.gms.measurement.internal.zzfv r4 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r4 = r4.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.String r9 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r15)     // Catch:{ all -> 0x09bf }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x09bf }
            r4.zza(r9, r10, r7)     // Catch:{ all -> 0x09bf }
        L_0x04ae:
            com.google.android.gms.measurement.internal.zzak r4 = new com.google.android.gms.measurement.internal.zzak     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            java.lang.String r9 = r2.zzc     // Catch:{ all -> 0x09bf }
            java.lang.String r10 = r2.zza     // Catch:{ all -> 0x09bf }
            long r12 = r2.zzd     // Catch:{ all -> 0x09bf }
            r18 = 0
            r7 = r4
            r2 = r10
            r10 = r15
            r5 = r11
            r11 = r2
            r16 = r14
            r2 = r15
            r25 = 0
            r14 = r18
            r7.<init>((com.google.android.gms.measurement.internal.zzfv) r8, (java.lang.String) r9, (java.lang.String) r10, (java.lang.String) r11, (long) r12, (long) r14, (android.os.Bundle) r16)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r4.zzb     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzan r7 = r7.zza((java.lang.String) r2, (java.lang.String) r8)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x054e
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            long r7 = r7.zzh(r2)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r9 = r9.zza()     // Catch:{ all -> 0x09bf }
            int r9 = r9.zzb(r2)     // Catch:{ all -> 0x09bf }
            long r9 = (long) r9     // Catch:{ all -> 0x09bf }
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x0534
            if (r6 == 0) goto L_0x0534
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r5 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r2)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzi()     // Catch:{ all -> 0x09bf }
            java.lang.String r4 = r4.zzb     // Catch:{ all -> 0x09bf }
            java.lang.String r4 = r7.zza((java.lang.String) r4)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r7 = r7.zza()     // Catch:{ all -> 0x09bf }
            int r7 = r7.zzb(r2)     // Catch:{ all -> 0x09bf }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x09bf }
            r3.zza(r5, r6, r4, r7)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkw r7 = r3.zzh()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkv r8 = r1.zzaa     // Catch:{ all -> 0x09bf }
            r10 = 8
            r11 = 0
            r12 = 0
            r13 = 0
            r9 = r2
            r7.zza((com.google.android.gms.measurement.internal.zzkv) r8, (java.lang.String) r9, (int) r10, (java.lang.String) r11, (java.lang.String) r12, (int) r13)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()
            r2.zzg()
            return
        L_0x0534:
            com.google.android.gms.measurement.internal.zzan r6 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x09bf }
            java.lang.String r9 = r4.zzb     // Catch:{ all -> 0x09bf }
            r10 = 0
            r12 = 0
            long r14 = r4.zzc     // Catch:{ all -> 0x09bf }
            r16 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r7 = r6
            r8 = r2
            r7.<init>(r8, r9, r10, r12, r14, r16, r18, r19, r20, r21)     // Catch:{ all -> 0x09bf }
            goto L_0x055c
        L_0x054e:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x09bf }
            long r8 = r7.zzf     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzak r4 = r4.zza(r2, r8)     // Catch:{ all -> 0x09bf }
            long r8 = r4.zzc     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzan r6 = r7.zza(r8)     // Catch:{ all -> 0x09bf }
        L_0x055c:
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()     // Catch:{ all -> 0x09bf }
            r2.zza((com.google.android.gms.measurement.internal.zzan) r6)     // Catch:{ all -> 0x09bf }
            r26.zzx()     // Catch:{ all -> 0x09bf }
            r26.zzn()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r28)     // Catch:{ all -> 0x09bf }
            java.lang.String r2 = r4.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x09bf }
            java.lang.String r2 = r4.zza     // Catch:{ all -> 0x09bf }
            java.lang.String r6 = r3.zza     // Catch:{ all -> 0x09bf }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r2 = com.google.android.gms.internal.measurement.zzcd.zzg.zzbh()     // Catch:{ all -> 0x09bf }
            r6 = 1
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r2 = r2.zza((int) r6)     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = "android"
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r2 = r2.zza((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x059b
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x09bf }
            r2.zzf((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
        L_0x059b:
            java.lang.String r7 = r3.zzd     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x05a8
            java.lang.String r7 = r3.zzd     // Catch:{ all -> 0x09bf }
            r2.zze((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
        L_0x05a8:
            java.lang.String r7 = r3.zzc     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x05b5
            java.lang.String r7 = r3.zzc     // Catch:{ all -> 0x09bf }
            r2.zzg((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
        L_0x05b5:
            long r7 = r3.zzj     // Catch:{ all -> 0x09bf }
            r9 = -2147483648(0xffffffff80000000, double:NaN)
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x05c4
            long r7 = r3.zzj     // Catch:{ all -> 0x09bf }
            int r8 = (int) r7     // Catch:{ all -> 0x09bf }
            r2.zzh((int) r8)     // Catch:{ all -> 0x09bf }
        L_0x05c4:
            long r7 = r3.zze     // Catch:{ all -> 0x09bf }
            r2.zzf((long) r7)     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = r3.zzb     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x05d6
            java.lang.String r7 = r3.zzb     // Catch:{ all -> 0x09bf }
            r2.zzk((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
        L_0x05d6:
            boolean r7 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0601
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r7 = r7.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x09bf }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r8)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0601
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzad r7 = r1.zza((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r3.zzw     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzad r8 = com.google.android.gms.measurement.internal.zzad.zza((java.lang.String) r8)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzad r7 = r7.zzb((com.google.android.gms.measurement.internal.zzad) r8)     // Catch:{ all -> 0x09bf }
            java.lang.String r7 = r7.zza()     // Catch:{ all -> 0x09bf }
            r2.zzq(r7)     // Catch:{ all -> 0x09bf }
        L_0x0601:
            boolean r7 = com.google.android.gms.internal.measurement.zznt.zzb()     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0650
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r7 = r7.zza()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzat.zzbi     // Catch:{ all -> 0x09bf }
            boolean r7 = r7.zze(r8, r9)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0650
            java.lang.String r7 = r2.zzo()     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x062e
            java.lang.String r7 = r3.zzv     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x062e
            java.lang.String r7 = r3.zzv     // Catch:{ all -> 0x09bf }
            r2.zzp(r7)     // Catch:{ all -> 0x09bf }
        L_0x062e:
            java.lang.String r7 = r2.zzo()     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0667
            java.lang.String r7 = r2.zzs()     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0667
            java.lang.String r7 = r3.zzr     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x0667
            java.lang.String r7 = r3.zzr     // Catch:{ all -> 0x09bf }
            r2.zzo(r7)     // Catch:{ all -> 0x09bf }
            goto L_0x0667
        L_0x0650:
            java.lang.String r7 = r2.zzo()     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0667
            java.lang.String r7 = r3.zzr     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x0667
            java.lang.String r7 = r3.zzr     // Catch:{ all -> 0x09bf }
            r2.zzo(r7)     // Catch:{ all -> 0x09bf }
        L_0x0667:
            long r7 = r3.zzf     // Catch:{ all -> 0x09bf }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x0674
            long r7 = r3.zzf     // Catch:{ all -> 0x09bf }
            r2.zzh((long) r7)     // Catch:{ all -> 0x09bf }
        L_0x0674:
            long r7 = r3.zzt     // Catch:{ all -> 0x09bf }
            r2.zzk((long) r7)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzks r7 = r26.zzh()     // Catch:{ all -> 0x09bf }
            java.util.List r7 = r7.zze()     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x0686
            r2.zzd((java.lang.Iterable<? extends java.lang.Integer>) r7)     // Catch:{ all -> 0x09bf }
        L_0x0686:
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzad r7 = r1.zza((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r3.zzw     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzad r8 = com.google.android.gms.measurement.internal.zzad.zza((java.lang.String) r8)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzad r7 = r7.zzb((com.google.android.gms.measurement.internal.zzad) r8)     // Catch:{ all -> 0x09bf }
            boolean r8 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x06b0
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r8 = r8.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x09bf }
            boolean r8 = r8.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r11)     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x06b0
            boolean r8 = r7.zzc()     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x0758
        L_0x06b0:
            com.google.android.gms.measurement.internal.zzjo r8 = r1.zzj     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x09bf }
            android.util.Pair r8 = r8.zza(r11, r7)     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x06e0
            java.lang.Object r11 = r8.first     // Catch:{ all -> 0x09bf }
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ all -> 0x09bf }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x09bf }
            if (r11 != 0) goto L_0x06e0
            boolean r11 = r3.zzo     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x0758
            java.lang.Object r11 = r8.first     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x09bf }
            r2.zzh((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            java.lang.Object r11 = r8.second     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x0758
            java.lang.Object r8 = r8.second     // Catch:{ all -> 0x09bf }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x09bf }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x09bf }
            r2.zza((boolean) r8)     // Catch:{ all -> 0x09bf }
            goto L_0x0758
        L_0x06e0:
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzal r8 = r8.zzw()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            android.content.Context r11 = r11.zzm()     // Catch:{ all -> 0x09bf }
            boolean r8 = r8.zza(r11)     // Catch:{ all -> 0x09bf }
            if (r8 != 0) goto L_0x0758
            boolean r8 = r3.zzp     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x0758
            boolean r8 = com.google.android.gms.internal.measurement.zzox.zzb()     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x070c
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r8 = r8.zza()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzcf     // Catch:{ all -> 0x09bf }
            boolean r8 = r8.zze(r11, r12)     // Catch:{ all -> 0x09bf }
            if (r8 != 0) goto L_0x0758
        L_0x070c:
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            android.content.Context r8 = r8.zzm()     // Catch:{ all -> 0x09bf }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = "android_id"
            java.lang.String r8 = android.provider.Settings.Secure.getString(r8, r11)     // Catch:{ all -> 0x09bf }
            if (r8 != 0) goto L_0x0738
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = "null secure ID. appId"
            java.lang.String r12 = r2.zzj()     // Catch:{ all -> 0x09bf }
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r12)     // Catch:{ all -> 0x09bf }
            r8.zza(r11, r12)     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = "null"
            goto L_0x0755
        L_0x0738:
            boolean r11 = r8.isEmpty()     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x0755
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r11 = r11.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r11 = r11.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.String r12 = "empty secure ID. appId"
            java.lang.String r13 = r2.zzj()     // Catch:{ all -> 0x09bf }
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r13)     // Catch:{ all -> 0x09bf }
            r11.zza(r12, r13)     // Catch:{ all -> 0x09bf }
        L_0x0755:
            r2.zzm(r8)     // Catch:{ all -> 0x09bf }
        L_0x0758:
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzal r8 = r8.zzw()     // Catch:{ all -> 0x09bf }
            r8.zzaa()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = android.os.Build.MODEL     // Catch:{ all -> 0x09bf }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r8 = r2.zzc((java.lang.String) r8)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzal r11 = r11.zzw()     // Catch:{ all -> 0x09bf }
            r11.zzaa()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x09bf }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r8 = r8.zzb((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzal r11 = r11.zzw()     // Catch:{ all -> 0x09bf }
            long r11 = r11.zze()     // Catch:{ all -> 0x09bf }
            int r12 = (int) r11     // Catch:{ all -> 0x09bf }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r8 = r8.zzf((int) r12)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzal r11 = r11.zzw()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r11.zzf()     // Catch:{ all -> 0x09bf }
            r8.zzd((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r8 = r8.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzat.zzbx     // Catch:{ all -> 0x09bf }
            boolean r8 = r8.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r11)     // Catch:{ all -> 0x09bf }
            if (r8 != 0) goto L_0x07a5
            long r11 = r3.zzl     // Catch:{ all -> 0x09bf }
            r2.zzj((long) r11)     // Catch:{ all -> 0x09bf }
        L_0x07a5:
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            boolean r8 = r8.zzaa()     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x07d3
            boolean r8 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x07c5
            com.google.android.gms.measurement.internal.zzfv r8 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r8 = r8.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x09bf }
            boolean r8 = r8.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r11)     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x07c5
            r2.zzj()     // Catch:{ all -> 0x09bf }
            goto L_0x07c8
        L_0x07c5:
            r2.zzj()     // Catch:{ all -> 0x09bf }
        L_0x07c8:
            boolean r8 = android.text.TextUtils.isEmpty(r25)     // Catch:{ all -> 0x09bf }
            if (r8 != 0) goto L_0x07d3
            r8 = r25
            r2.zzn(r8)     // Catch:{ all -> 0x09bf }
        L_0x07d3:
            com.google.android.gms.measurement.internal.zzac r8 = r26.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzf r8 = r8.zzb(r11)     // Catch:{ all -> 0x09bf }
            if (r8 != 0) goto L_0x0880
            com.google.android.gms.measurement.internal.zzf r8 = new com.google.android.gms.measurement.internal.zzf     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            java.lang.String r12 = r3.zza     // Catch:{ all -> 0x09bf }
            r8.<init>(r11, r12)     // Catch:{ all -> 0x09bf }
            boolean r11 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x0804
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r11 = r11.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x09bf }
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r12)     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x0804
            java.lang.String r11 = r1.zza((com.google.android.gms.measurement.internal.zzad) r7)     // Catch:{ all -> 0x09bf }
            r8.zza((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            goto L_0x080b
        L_0x0804:
            java.lang.String r11 = r26.zzz()     // Catch:{ all -> 0x09bf }
            r8.zza((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
        L_0x080b:
            java.lang.String r11 = r3.zzk     // Catch:{ all -> 0x09bf }
            r8.zzf((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r3.zzb     // Catch:{ all -> 0x09bf }
            r8.zzb((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            boolean r11 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x082f
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r11 = r11.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x09bf }
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r12)     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x082f
            boolean r11 = r7.zzc()     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x083a
        L_0x082f:
            com.google.android.gms.measurement.internal.zzjo r11 = r1.zzj     // Catch:{ all -> 0x09bf }
            java.lang.String r12 = r3.zza     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r11.zza(r12)     // Catch:{ all -> 0x09bf }
            r8.zze((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
        L_0x083a:
            r8.zzg((long) r9)     // Catch:{ all -> 0x09bf }
            r8.zza((long) r9)     // Catch:{ all -> 0x09bf }
            r8.zzb((long) r9)     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r3.zzc     // Catch:{ all -> 0x09bf }
            r8.zzg((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            long r11 = r3.zzj     // Catch:{ all -> 0x09bf }
            r8.zzc((long) r11)     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r3.zzd     // Catch:{ all -> 0x09bf }
            r8.zzh((java.lang.String) r11)     // Catch:{ all -> 0x09bf }
            long r11 = r3.zze     // Catch:{ all -> 0x09bf }
            r8.zzd((long) r11)     // Catch:{ all -> 0x09bf }
            long r11 = r3.zzf     // Catch:{ all -> 0x09bf }
            r8.zze((long) r11)     // Catch:{ all -> 0x09bf }
            boolean r11 = r3.zzh     // Catch:{ all -> 0x09bf }
            r8.zza((boolean) r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r11 = r11.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzbx     // Catch:{ all -> 0x09bf }
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r12)     // Catch:{ all -> 0x09bf }
            if (r11 != 0) goto L_0x0874
            long r11 = r3.zzl     // Catch:{ all -> 0x09bf }
            r8.zzp(r11)     // Catch:{ all -> 0x09bf }
        L_0x0874:
            long r11 = r3.zzt     // Catch:{ all -> 0x09bf }
            r8.zzf((long) r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r11 = r26.zze()     // Catch:{ all -> 0x09bf }
            r11.zza((com.google.android.gms.measurement.internal.zzf) r8)     // Catch:{ all -> 0x09bf }
        L_0x0880:
            boolean r11 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x089a
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r11 = r11.zza()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x09bf }
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r12)     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x089a
            boolean r7 = r7.zze()     // Catch:{ all -> 0x09bf }
            if (r7 == 0) goto L_0x08ab
        L_0x089a:
            java.lang.String r7 = r8.zzd()     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x08ab
            java.lang.String r7 = r8.zzd()     // Catch:{ all -> 0x09bf }
            r2.zzi((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
        L_0x08ab:
            java.lang.String r7 = r8.zzi()     // Catch:{ all -> 0x09bf }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x09bf }
            if (r7 != 0) goto L_0x08bc
            java.lang.String r7 = r8.zzi()     // Catch:{ all -> 0x09bf }
            r2.zzl((java.lang.String) r7)     // Catch:{ all -> 0x09bf }
        L_0x08bc:
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r3 = r3.zza     // Catch:{ all -> 0x09bf }
            java.util.List r3 = r7.zza((java.lang.String) r3)     // Catch:{ all -> 0x09bf }
            r11 = 0
        L_0x08c7:
            int r7 = r3.size()     // Catch:{ all -> 0x09bf }
            if (r11 >= r7) goto L_0x08fe
            com.google.android.gms.internal.measurement.zzcd$zzk$zza r7 = com.google.android.gms.internal.measurement.zzcd.zzk.zzj()     // Catch:{ all -> 0x09bf }
            java.lang.Object r8 = r3.get(r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkt r8 = (com.google.android.gms.measurement.internal.zzkt) r8     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r8.zzc     // Catch:{ all -> 0x09bf }
            com.google.android.gms.internal.measurement.zzcd$zzk$zza r7 = r7.zza((java.lang.String) r8)     // Catch:{ all -> 0x09bf }
            java.lang.Object r8 = r3.get(r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkt r8 = (com.google.android.gms.measurement.internal.zzkt) r8     // Catch:{ all -> 0x09bf }
            long r12 = r8.zzd     // Catch:{ all -> 0x09bf }
            com.google.android.gms.internal.measurement.zzcd$zzk$zza r7 = r7.zza((long) r12)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzks r8 = r26.zzh()     // Catch:{ all -> 0x09bf }
            java.lang.Object r12 = r3.get(r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzkt r12 = (com.google.android.gms.measurement.internal.zzkt) r12     // Catch:{ all -> 0x09bf }
            java.lang.Object r12 = r12.zze     // Catch:{ all -> 0x09bf }
            r8.zza((com.google.android.gms.internal.measurement.zzcd.zzk.zza) r7, (java.lang.Object) r12)     // Catch:{ all -> 0x09bf }
            r2.zza((com.google.android.gms.internal.measurement.zzcd.zzk.zza) r7)     // Catch:{ all -> 0x09bf }
            int r11 = r11 + 1
            goto L_0x08c7
        L_0x08fe:
            com.google.android.gms.measurement.internal.zzac r3 = r26.zze()     // Catch:{ IOException -> 0x0973 }
            com.google.android.gms.internal.measurement.zzjh r7 = r2.zzz()     // Catch:{ IOException -> 0x0973 }
            com.google.android.gms.internal.measurement.zzhz r7 = (com.google.android.gms.internal.measurement.zzhz) r7     // Catch:{ IOException -> 0x0973 }
            com.google.android.gms.internal.measurement.zzcd$zzg r7 = (com.google.android.gms.internal.measurement.zzcd.zzg) r7     // Catch:{ IOException -> 0x0973 }
            long r2 = r3.zza((com.google.android.gms.internal.measurement.zzcd.zzg) r7)     // Catch:{ IOException -> 0x0973 }
            com.google.android.gms.measurement.internal.zzac r7 = r26.zze()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzam r8 = r4.zze     // Catch:{ all -> 0x09bf }
            if (r8 == 0) goto L_0x0969
            com.google.android.gms.measurement.internal.zzam r8 = r4.zze     // Catch:{ all -> 0x09bf }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x09bf }
        L_0x091c:
            boolean r11 = r8.hasNext()     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x0930
            java.lang.Object r11 = r8.next()     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x09bf }
            boolean r11 = r5.equals(r11)     // Catch:{ all -> 0x09bf }
            if (r11 == 0) goto L_0x091c
        L_0x092e:
            r11 = 1
            goto L_0x096a
        L_0x0930:
            com.google.android.gms.measurement.internal.zzfp r5 = r26.zzc()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r4.zza     // Catch:{ all -> 0x09bf }
            java.lang.String r11 = r4.zzb     // Catch:{ all -> 0x09bf }
            boolean r5 = r5.zzc(r8, r11)     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r11 = r26.zze()     // Catch:{ all -> 0x09bf }
            long r12 = r26.zzy()     // Catch:{ all -> 0x09bf }
            java.lang.String r14 = r4.zza     // Catch:{ all -> 0x09bf }
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            com.google.android.gms.measurement.internal.zzaf r8 = r11.zza(r12, r14, r15, r16, r17, r18, r19)     // Catch:{ all -> 0x09bf }
            if (r5 == 0) goto L_0x0969
            long r11 = r8.zze     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzy r5 = r5.zza()     // Catch:{ all -> 0x09bf }
            java.lang.String r8 = r4.zza     // Catch:{ all -> 0x09bf }
            int r5 = r5.zzc(r8)     // Catch:{ all -> 0x09bf }
            long r13 = (long) r5     // Catch:{ all -> 0x09bf }
            int r5 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r5 >= 0) goto L_0x0969
            goto L_0x092e
        L_0x0969:
            r11 = 0
        L_0x096a:
            boolean r2 = r7.zza((com.google.android.gms.measurement.internal.zzak) r4, (long) r2, (boolean) r11)     // Catch:{ all -> 0x09bf }
            if (r2 == 0) goto L_0x098c
            r1.zzn = r9     // Catch:{ all -> 0x09bf }
            goto L_0x098c
        L_0x0973:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.measurement.internal.zzfv r4 = r1.zzk     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzq()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzet r4 = r4.zze()     // Catch:{ all -> 0x09bf }
            java.lang.String r5 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r2 = r2.zzj()     // Catch:{ all -> 0x09bf }
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r2)     // Catch:{ all -> 0x09bf }
            r4.zza(r5, r2, r3)     // Catch:{ all -> 0x09bf }
        L_0x098c:
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()     // Catch:{ all -> 0x09bf }
            r2.b_()     // Catch:{ all -> 0x09bf }
            com.google.android.gms.measurement.internal.zzac r2 = r26.zze()
            r2.zzg()
            r26.zzab()
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzw()
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r23
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "Background event processing time, ms"
            r2.zza(r4, r3)
            return
        L_0x09bf:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzac r3 = r26.zze()
            r3.zzg()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zzc(com.google.android.gms.measurement.internal.zzar, com.google.android.gms.measurement.internal.zzn):void");
    }

    private final String zza(zzad zzad) {
        if (!zzmj.zzb() || !this.zzk.zza().zza(zzat.zzci) || zzad.zze()) {
            return zzz();
        }
        return null;
    }

    @Deprecated
    private final String zzz() {
        byte[] bArr = new byte[16];
        this.zzk.zzh().zzg().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:136|137) */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        r1.zzk.zzq().zze().zza("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.measurement.internal.zzer.zza(r5), r0);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:136:0x0373 */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x022e A[Catch:{ all -> 0x03af }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x02c3 A[Catch:{ all -> 0x03af }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02d4 A[Catch:{ all -> 0x03af }] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x02f8 A[Catch:{ MalformedURLException -> 0x0373 }] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x02fa A[Catch:{ MalformedURLException -> 0x0373 }] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0302 A[Catch:{ MalformedURLException -> 0x0373 }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0312 A[Catch:{ MalformedURLException -> 0x0373 }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x032a A[Catch:{ MalformedURLException -> 0x0373 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0227 A[Catch:{ all -> 0x03af }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0228 A[Catch:{ all -> 0x03af }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzo() {
        /*
            r17 = this;
            r1 = r17
            r17.zzx()
            r17.zzn()
            r0 = 1
            r1.zzt = r0
            r2 = 0
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzio r3 = r3.zzv()     // Catch:{ all -> 0x03af }
            java.lang.Boolean r3 = r3.zzaf()     // Catch:{ all -> 0x03af }
            if (r3 != 0) goto L_0x002d
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzq()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()     // Catch:{ all -> 0x03af }
            java.lang.String r3 = "Upload data called on the client side before use of service was decided"
            r0.zza(r3)     // Catch:{ all -> 0x03af }
            r1.zzt = r2
            r17.zzac()
            return
        L_0x002d:
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x03af }
            if (r3 == 0) goto L_0x0048
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzq()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()     // Catch:{ all -> 0x03af }
            java.lang.String r3 = "Upload called in the client side when service should be used"
            r0.zza(r3)     // Catch:{ all -> 0x03af }
            r1.zzt = r2
            r17.zzac()
            return
        L_0x0048:
            long r3 = r1.zzn     // Catch:{ all -> 0x03af }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0059
            r17.zzab()     // Catch:{ all -> 0x03af }
            r1.zzt = r2
            r17.zzac()
            return
        L_0x0059:
            r17.zzx()     // Catch:{ all -> 0x03af }
            java.util.List<java.lang.Long> r3 = r1.zzw     // Catch:{ all -> 0x03af }
            if (r3 == 0) goto L_0x0062
            r3 = 1
            goto L_0x0063
        L_0x0062:
            r3 = 0
        L_0x0063:
            if (r3 == 0) goto L_0x007a
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzq()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzw()     // Catch:{ all -> 0x03af }
            java.lang.String r3 = "Uploading requested multiple times"
            r0.zza(r3)     // Catch:{ all -> 0x03af }
            r1.zzt = r2
            r17.zzac()
            return
        L_0x007a:
            com.google.android.gms.measurement.internal.zzeu r3 = r17.zzd()     // Catch:{ all -> 0x03af }
            boolean r3 = r3.zze()     // Catch:{ all -> 0x03af }
            if (r3 != 0) goto L_0x009c
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzq()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzw()     // Catch:{ all -> 0x03af }
            java.lang.String r3 = "Network not connected, ignoring upload request"
            r0.zza(r3)     // Catch:{ all -> 0x03af }
            r17.zzab()     // Catch:{ all -> 0x03af }
            r1.zzt = r2
            r17.zzac()
            return
        L_0x009c:
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.common.util.Clock r3 = r3.zzl()     // Catch:{ all -> 0x03af }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r7 = r7.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r8 = com.google.android.gms.measurement.internal.zzat.zzap     // Catch:{ all -> 0x03af }
            r9 = 0
            int r7 = r7.zzb(r9, r8)     // Catch:{ all -> 0x03af }
            long r10 = com.google.android.gms.measurement.internal.zzy.zzj()     // Catch:{ all -> 0x03af }
            long r10 = r3 - r10
            r8 = 0
        L_0x00ba:
            if (r8 >= r7) goto L_0x00c5
            boolean r12 = r1.zza((java.lang.String) r9, (long) r10)     // Catch:{ all -> 0x03af }
            if (r12 == 0) goto L_0x00c5
            int r8 = r8 + 1
            goto L_0x00ba
        L_0x00c5:
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzfd r7 = r7.zzb()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzfh r7 = r7.zzc     // Catch:{ all -> 0x03af }
            long r7 = r7.zza()     // Catch:{ all -> 0x03af }
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x00ee
            com.google.android.gms.measurement.internal.zzfv r5 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzv()     // Catch:{ all -> 0x03af }
            java.lang.String r6 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r7 = r3 - r7
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x03af }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x03af }
            r5.zza(r6, r7)     // Catch:{ all -> 0x03af }
        L_0x00ee:
            com.google.android.gms.measurement.internal.zzac r5 = r17.zze()     // Catch:{ all -> 0x03af }
            java.lang.String r5 = r5.d_()     // Catch:{ all -> 0x03af }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x03af }
            r7 = -1
            if (r6 != 0) goto L_0x0387
            long r10 = r1.zzy     // Catch:{ all -> 0x03af }
            int r6 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x010e
            com.google.android.gms.measurement.internal.zzac r6 = r17.zze()     // Catch:{ all -> 0x03af }
            long r6 = r6.zzz()     // Catch:{ all -> 0x03af }
            r1.zzy = r6     // Catch:{ all -> 0x03af }
        L_0x010e:
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r7 = com.google.android.gms.measurement.internal.zzat.zzf     // Catch:{ all -> 0x03af }
            int r6 = r6.zzb(r5, r7)     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r7 = r7.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r8 = com.google.android.gms.measurement.internal.zzat.zzg     // Catch:{ all -> 0x03af }
            int r7 = r7.zzb(r5, r8)     // Catch:{ all -> 0x03af }
            int r7 = java.lang.Math.max(r2, r7)     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzac r8 = r17.zze()     // Catch:{ all -> 0x03af }
            java.util.List r6 = r8.zza((java.lang.String) r5, (int) r6, (int) r7)     // Catch:{ all -> 0x03af }
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x03af }
            if (r7 != 0) goto L_0x03a9
            boolean r7 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x03af }
            if (r7 == 0) goto L_0x0156
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r7 = r7.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x03af }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r8)     // Catch:{ all -> 0x03af }
            if (r7 == 0) goto L_0x0156
            com.google.android.gms.measurement.internal.zzad r7 = r1.zza((java.lang.String) r5)     // Catch:{ all -> 0x03af }
            boolean r7 = r7.zzc()     // Catch:{ all -> 0x03af }
            if (r7 == 0) goto L_0x01a9
        L_0x0156:
            java.util.Iterator r7 = r6.iterator()     // Catch:{ all -> 0x03af }
        L_0x015a:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x03af }
            if (r8 == 0) goto L_0x0179
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x03af }
            android.util.Pair r8 = (android.util.Pair) r8     // Catch:{ all -> 0x03af }
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzg r8 = (com.google.android.gms.internal.measurement.zzcd.zzg) r8     // Catch:{ all -> 0x03af }
            java.lang.String r10 = r8.zzad()     // Catch:{ all -> 0x03af }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x03af }
            if (r10 != 0) goto L_0x015a
            java.lang.String r7 = r8.zzad()     // Catch:{ all -> 0x03af }
            goto L_0x017a
        L_0x0179:
            r7 = r9
        L_0x017a:
            if (r7 == 0) goto L_0x01a9
            r8 = 0
        L_0x017d:
            int r10 = r6.size()     // Catch:{ all -> 0x03af }
            if (r8 >= r10) goto L_0x01a9
            java.lang.Object r10 = r6.get(r8)     // Catch:{ all -> 0x03af }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x03af }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzg r10 = (com.google.android.gms.internal.measurement.zzcd.zzg) r10     // Catch:{ all -> 0x03af }
            java.lang.String r11 = r10.zzad()     // Catch:{ all -> 0x03af }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x03af }
            if (r11 != 0) goto L_0x01a6
            java.lang.String r10 = r10.zzad()     // Catch:{ all -> 0x03af }
            boolean r10 = r10.equals(r7)     // Catch:{ all -> 0x03af }
            if (r10 != 0) goto L_0x01a6
            java.util.List r6 = r6.subList(r2, r8)     // Catch:{ all -> 0x03af }
            goto L_0x01a9
        L_0x01a6:
            int r8 = r8 + 1
            goto L_0x017d
        L_0x01a9:
            com.google.android.gms.internal.measurement.zzcd$zzf$zza r7 = com.google.android.gms.internal.measurement.zzcd.zzf.zzb()     // Catch:{ all -> 0x03af }
            int r8 = r6.size()     // Catch:{ all -> 0x03af }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x03af }
            int r11 = r6.size()     // Catch:{ all -> 0x03af }
            r10.<init>(r11)     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r11 = r11.zza()     // Catch:{ all -> 0x03af }
            boolean r11 = r11.zzh(r5)     // Catch:{ all -> 0x03af }
            if (r11 == 0) goto L_0x01e6
            boolean r11 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x03af }
            if (r11 == 0) goto L_0x01e4
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r11 = r11.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x03af }
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r12)     // Catch:{ all -> 0x03af }
            if (r11 == 0) goto L_0x01e4
            com.google.android.gms.measurement.internal.zzad r11 = r1.zza((java.lang.String) r5)     // Catch:{ all -> 0x03af }
            boolean r11 = r11.zzc()     // Catch:{ all -> 0x03af }
            if (r11 == 0) goto L_0x01e6
        L_0x01e4:
            r11 = 1
            goto L_0x01e7
        L_0x01e6:
            r11 = 0
        L_0x01e7:
            boolean r12 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x03af }
            if (r12 == 0) goto L_0x0208
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r12 = r12.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r13 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x03af }
            boolean r12 = r12.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r13)     // Catch:{ all -> 0x03af }
            if (r12 == 0) goto L_0x0208
            com.google.android.gms.measurement.internal.zzad r12 = r1.zza((java.lang.String) r5)     // Catch:{ all -> 0x03af }
            boolean r12 = r12.zzc()     // Catch:{ all -> 0x03af }
            if (r12 == 0) goto L_0x0206
            goto L_0x0208
        L_0x0206:
            r12 = 0
            goto L_0x0209
        L_0x0208:
            r12 = 1
        L_0x0209:
            boolean r13 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x03af }
            if (r13 == 0) goto L_0x022a
            com.google.android.gms.measurement.internal.zzfv r13 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r13 = r13.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x03af }
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r14)     // Catch:{ all -> 0x03af }
            if (r13 == 0) goto L_0x022a
            com.google.android.gms.measurement.internal.zzad r13 = r1.zza((java.lang.String) r5)     // Catch:{ all -> 0x03af }
            boolean r13 = r13.zze()     // Catch:{ all -> 0x03af }
            if (r13 == 0) goto L_0x0228
            goto L_0x022a
        L_0x0228:
            r13 = 0
            goto L_0x022b
        L_0x022a:
            r13 = 1
        L_0x022b:
            r14 = 0
        L_0x022c:
            if (r14 >= r8) goto L_0x02b4
            java.lang.Object r15 = r6.get(r14)     // Catch:{ all -> 0x03af }
            android.util.Pair r15 = (android.util.Pair) r15     // Catch:{ all -> 0x03af }
            java.lang.Object r15 = r15.first     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzg r15 = (com.google.android.gms.internal.measurement.zzcd.zzg) r15     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzhz$zza r15 = r15.zzbn()     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzhz$zza r15 = (com.google.android.gms.internal.measurement.zzhz.zza) r15     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r15 = (com.google.android.gms.internal.measurement.zzcd.zzg.zza) r15     // Catch:{ all -> 0x03af }
            java.lang.Object r16 = r6.get(r14)     // Catch:{ all -> 0x03af }
            r0 = r16
            android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x03af }
            java.lang.Object r0 = r0.second     // Catch:{ all -> 0x03af }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x03af }
            r10.add(r0)     // Catch:{ all -> 0x03af }
            r16 = r10
            r9 = 32053(0x7d35, double:1.58363E-319)
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r9 = r15.zzg((long) r9)     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r9 = r9.zza((long) r3)     // Catch:{ all -> 0x03af }
            r9.zzb((boolean) r2)     // Catch:{ all -> 0x03af }
            if (r11 != 0) goto L_0x0263
            r15.zzr()     // Catch:{ all -> 0x03af }
        L_0x0263:
            boolean r9 = com.google.android.gms.internal.measurement.zzmj.zzb()     // Catch:{ all -> 0x03af }
            if (r9 == 0) goto L_0x0284
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r9 = r9.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzat.zzci     // Catch:{ all -> 0x03af }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r10)     // Catch:{ all -> 0x03af }
            if (r9 == 0) goto L_0x0284
            if (r12 != 0) goto L_0x027f
            r15.zzk()     // Catch:{ all -> 0x03af }
            r15.zzl()     // Catch:{ all -> 0x03af }
        L_0x027f:
            if (r13 != 0) goto L_0x0284
            r15.zzm()     // Catch:{ all -> 0x03af }
        L_0x0284:
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzy r9 = r9.zza()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzat.zzaw     // Catch:{ all -> 0x03af }
            boolean r9 = r9.zze(r5, r10)     // Catch:{ all -> 0x03af }
            if (r9 == 0) goto L_0x02a9
            com.google.android.gms.internal.measurement.zzjh r9 = r15.zzz()     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzg r9 = (com.google.android.gms.internal.measurement.zzcd.zzg) r9     // Catch:{ all -> 0x03af }
            byte[] r9 = r9.zzbk()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzks r10 = r17.zzh()     // Catch:{ all -> 0x03af }
            long r9 = r10.zza((byte[]) r9)     // Catch:{ all -> 0x03af }
            r15.zzl((long) r9)     // Catch:{ all -> 0x03af }
        L_0x02a9:
            r7.zza((com.google.android.gms.internal.measurement.zzcd.zzg.zza) r15)     // Catch:{ all -> 0x03af }
            int r14 = r14 + 1
            r10 = r16
            r0 = 1
            r9 = 0
            goto L_0x022c
        L_0x02b4:
            r16 = r10
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzer r6 = r6.zzq()     // Catch:{ all -> 0x03af }
            r9 = 2
            boolean r6 = r6.zza((int) r9)     // Catch:{ all -> 0x03af }
            if (r6 == 0) goto L_0x02d4
            com.google.android.gms.measurement.internal.zzks r6 = r17.zzh()     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzjh r9 = r7.zzz()     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzf r9 = (com.google.android.gms.internal.measurement.zzcd.zzf) r9     // Catch:{ all -> 0x03af }
            java.lang.String r6 = r6.zza((com.google.android.gms.internal.measurement.zzcd.zzf) r9)     // Catch:{ all -> 0x03af }
            goto L_0x02d5
        L_0x02d4:
            r6 = 0
        L_0x02d5:
            r17.zzh()     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzjh r9 = r7.zzz()     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x03af }
            com.google.android.gms.internal.measurement.zzcd$zzf r9 = (com.google.android.gms.internal.measurement.zzcd.zzf) r9     // Catch:{ all -> 0x03af }
            byte[] r14 = r9.zzbk()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.String> r9 = com.google.android.gms.measurement.internal.zzat.zzp     // Catch:{ all -> 0x03af }
            r0 = 0
            java.lang.Object r0 = r9.zza(r0)     // Catch:{ all -> 0x03af }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x03af }
            java.net.URL r13 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0373 }
            r13.<init>(r0)     // Catch:{ MalformedURLException -> 0x0373 }
            boolean r9 = r16.isEmpty()     // Catch:{ MalformedURLException -> 0x0373 }
            if (r9 != 0) goto L_0x02fa
            r9 = 1
            goto L_0x02fb
        L_0x02fa:
            r9 = 0
        L_0x02fb:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r9)     // Catch:{ MalformedURLException -> 0x0373 }
            java.util.List<java.lang.Long> r9 = r1.zzw     // Catch:{ MalformedURLException -> 0x0373 }
            if (r9 == 0) goto L_0x0312
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzer r9 = r9.zzq()     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzet r9 = r9.zze()     // Catch:{ MalformedURLException -> 0x0373 }
            java.lang.String r10 = "Set uploading progress before finishing the previous upload"
            r9.zza(r10)     // Catch:{ MalformedURLException -> 0x0373 }
            goto L_0x031b
        L_0x0312:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x0373 }
            r10 = r16
            r9.<init>(r10)     // Catch:{ MalformedURLException -> 0x0373 }
            r1.zzw = r9     // Catch:{ MalformedURLException -> 0x0373 }
        L_0x031b:
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzfd r9 = r9.zzb()     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzfh r9 = r9.zzd     // Catch:{ MalformedURLException -> 0x0373 }
            r9.zza(r3)     // Catch:{ MalformedURLException -> 0x0373 }
            java.lang.String r3 = "?"
            if (r8 <= 0) goto L_0x0332
            com.google.android.gms.internal.measurement.zzcd$zzg r3 = r7.zza((int) r2)     // Catch:{ MalformedURLException -> 0x0373 }
            java.lang.String r3 = r3.zzx()     // Catch:{ MalformedURLException -> 0x0373 }
        L_0x0332:
            com.google.android.gms.measurement.internal.zzfv r4 = r1.zzk     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzq()     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzet r4 = r4.zzw()     // Catch:{ MalformedURLException -> 0x0373 }
            java.lang.String r7 = "Uploading data. app, uncompressed size, data"
            int r8 = r14.length     // Catch:{ MalformedURLException -> 0x0373 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ MalformedURLException -> 0x0373 }
            r4.zza(r7, r3, r8, r6)     // Catch:{ MalformedURLException -> 0x0373 }
            r3 = 1
            r1.zzs = r3     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzeu r11 = r17.zzd()     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzkk r3 = new com.google.android.gms.measurement.internal.zzkk     // Catch:{ MalformedURLException -> 0x0373 }
            r3.<init>(r1, r5)     // Catch:{ MalformedURLException -> 0x0373 }
            r11.zzc()     // Catch:{ MalformedURLException -> 0x0373 }
            r11.zzaj()     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzfo r4 = r11.zzp()     // Catch:{ MalformedURLException -> 0x0373 }
            com.google.android.gms.measurement.internal.zzey r6 = new com.google.android.gms.measurement.internal.zzey     // Catch:{ MalformedURLException -> 0x0373 }
            r15 = 0
            r10 = r6
            r12 = r5
            r16 = r3
            r10.<init>(r11, r12, r13, r14, r15, r16)     // Catch:{ MalformedURLException -> 0x0373 }
            r4.zzc((java.lang.Runnable) r6)     // Catch:{ MalformedURLException -> 0x0373 }
            goto L_0x03a9
        L_0x0373:
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zze()     // Catch:{ all -> 0x03af }
            java.lang.String r4 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r5)     // Catch:{ all -> 0x03af }
            r3.zza(r4, r5, r0)     // Catch:{ all -> 0x03af }
            goto L_0x03a9
        L_0x0387:
            r1.zzy = r7     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzac r0 = r17.zze()     // Catch:{ all -> 0x03af }
            long r5 = com.google.android.gms.measurement.internal.zzy.zzj()     // Catch:{ all -> 0x03af }
            long r3 = r3 - r5
            java.lang.String r0 = r0.zza((long) r3)     // Catch:{ all -> 0x03af }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x03af }
            if (r3 != 0) goto L_0x03a9
            com.google.android.gms.measurement.internal.zzac r3 = r17.zze()     // Catch:{ all -> 0x03af }
            com.google.android.gms.measurement.internal.zzf r0 = r3.zzb(r0)     // Catch:{ all -> 0x03af }
            if (r0 == 0) goto L_0x03a9
            r1.zza((com.google.android.gms.measurement.internal.zzf) r0)     // Catch:{ all -> 0x03af }
        L_0x03a9:
            r1.zzt = r2
            r17.zzac()
            return
        L_0x03af:
            r0 = move-exception
            r1.zzt = r2
            r17.zzac()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zzo():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0256, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0257, code lost:
        r5 = r1;
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a7, code lost:
        r25 = "";
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:617:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0256 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0275 A[SYNTHETIC, Splitter:B:133:0x0275] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x027c A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x028a A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0465 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0467 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x046a A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x046b A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x048a A[SYNTHETIC, Splitter:B:221:0x048a] */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x052c A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x058e A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0592 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x05f8 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x062a A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x0649 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x073d A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:353:0x08cb A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:359:0x08e3 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x08fd A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:493:0x0c86 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:494:0x0c99 A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:496:0x0c9c A[Catch:{ IOException -> 0x022e, all -> 0x1030 }] */
    /* JADX WARNING: Removed duplicated region for block: B:497:0x0cc3 A[SYNTHETIC, Splitter:B:497:0x0cc3] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0118 A[Catch:{ SQLiteException -> 0x024c }] */
    /* JADX WARNING: Removed duplicated region for block: B:608:0x1013  */
    /* JADX WARNING: Removed duplicated region for block: B:616:0x102a A[SYNTHETIC, Splitter:B:616:0x102a] */
    /* JADX WARNING: Removed duplicated region for block: B:634:0x04f1 A[EDGE_INSN: B:634:0x04f1->B:230:0x04f1 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x012f A[SYNTHETIC, Splitter:B:63:0x012f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r43, long r44) {
        /*
            r42 = this;
            r1 = r42
            java.lang.String r2 = "_npa"
            java.lang.String r3 = "_ai"
            java.lang.String r4 = ""
            com.google.android.gms.measurement.internal.zzac r5 = r42.zze()
            r5.zze()
            com.google.android.gms.measurement.internal.zzki$zza r5 = new com.google.android.gms.measurement.internal.zzki$zza     // Catch:{ all -> 0x1030 }
            r6 = 0
            r5.<init>(r1, r6)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzac r7 = r42.zze()     // Catch:{ all -> 0x1030 }
            long r8 = r1.zzy     // Catch:{ all -> 0x1030 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x1030 }
            r7.zzc()     // Catch:{ all -> 0x1030 }
            r7.zzaj()     // Catch:{ all -> 0x1030 }
            r11 = -1
            r13 = 2
            r14 = 0
            r15 = 1
            android.database.sqlite.SQLiteDatabase r10 = r7.c_()     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            boolean r16 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            if (r16 == 0) goto L_0x0095
            int r16 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r16 == 0) goto L_0x0046
            java.lang.String[] r6 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            java.lang.String r17 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            r6[r14] = r17     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            java.lang.String r17 = java.lang.String.valueOf(r44)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            r6[r15] = r17     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            goto L_0x004e
        L_0x0046:
            java.lang.String[] r6 = new java.lang.String[r15]     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            java.lang.String r17 = java.lang.String.valueOf(r44)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            r6[r14] = r17     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
        L_0x004e:
            if (r16 == 0) goto L_0x0055
            java.lang.String r16 = "rowid <= ? and "
            r44 = r16
            goto L_0x0057
        L_0x0055:
            r44 = r4
        L_0x0057:
            int r13 = r44.length()     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            int r13 = r13 + 148
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            r11.<init>(r13)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            java.lang.String r12 = "select app_id, metadata_fingerprint from raw_events where "
            r11.append(r12)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            r12 = r44
            r11.append(r12)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            java.lang.String r12 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r11.append(r12)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            java.lang.String r11 = r11.toString()     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            android.database.Cursor r6 = r10.rawQuery(r11, r6)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            boolean r11 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x0091 }
            if (r11 != 0) goto L_0x0085
            if (r6 == 0) goto L_0x00e4
            r6.close()     // Catch:{ all -> 0x1030 }
            goto L_0x00e4
        L_0x0085:
            java.lang.String r11 = r6.getString(r14)     // Catch:{ SQLiteException -> 0x0091 }
            java.lang.String r12 = r6.getString(r15)     // Catch:{ SQLiteException -> 0x024c }
            r6.close()     // Catch:{ SQLiteException -> 0x024c }
            goto L_0x00f0
        L_0x0091:
            r0 = move-exception
            r25 = r4
            goto L_0x00aa
        L_0x0095:
            int r6 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r6 == 0) goto L_0x00ae
            r11 = 2
            java.lang.String[] r12 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            r11 = 0
            r12[r14] = r11     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            java.lang.String r11 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            r12[r15] = r11     // Catch:{ SQLiteException -> 0x00a6, all -> 0x0256 }
            goto L_0x00b3
        L_0x00a6:
            r0 = move-exception
            r25 = r4
            r6 = 0
        L_0x00aa:
            r11 = 0
        L_0x00ab:
            r4 = r0
            goto L_0x0262
        L_0x00ae:
            r11 = 0
            java.lang.String[] r12 = new java.lang.String[]{r11}     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
        L_0x00b3:
            if (r6 == 0) goto L_0x00b8
            java.lang.String r6 = " and rowid <= ?"
            goto L_0x00b9
        L_0x00b8:
            r6 = r4
        L_0x00b9:
            int r11 = r6.length()     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            int r11 = r11 + 84
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            r13.<init>(r11)     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            java.lang.String r11 = "select metadata_fingerprint from raw_events where app_id = ?"
            r13.append(r11)     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            r13.append(r6)     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            java.lang.String r6 = " order by rowid limit 1;"
            r13.append(r6)     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            java.lang.String r6 = r13.toString()     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            android.database.Cursor r6 = r10.rawQuery(r6, r12)     // Catch:{ SQLiteException -> 0x025c, all -> 0x0256 }
            boolean r11 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x0251 }
            if (r11 != 0) goto L_0x00e8
            if (r6 == 0) goto L_0x00e4
            r6.close()     // Catch:{ all -> 0x1030 }
        L_0x00e4:
            r25 = r4
            goto L_0x0278
        L_0x00e8:
            java.lang.String r12 = r6.getString(r14)     // Catch:{ SQLiteException -> 0x0251 }
            r6.close()     // Catch:{ SQLiteException -> 0x0251 }
            r11 = 0
        L_0x00f0:
            java.lang.String r17 = "raw_events_metadata"
            java.lang.String r13 = "metadata"
            java.lang.String[] r18 = new java.lang.String[]{r13}     // Catch:{ SQLiteException -> 0x024c }
            java.lang.String r19 = "app_id = ? and metadata_fingerprint = ?"
            r13 = 2
            java.lang.String[] r15 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x024c }
            r15[r14] = r11     // Catch:{ SQLiteException -> 0x024c }
            r13 = 1
            r15[r13] = r12     // Catch:{ SQLiteException -> 0x024c }
            r21 = 0
            r22 = 0
            java.lang.String r23 = "rowid"
            java.lang.String r24 = "2"
            r16 = r10
            r20 = r15
            android.database.Cursor r6 = r16.query(r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ SQLiteException -> 0x024c }
            boolean r13 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x024c }
            if (r13 != 0) goto L_0x012f
            com.google.android.gms.measurement.internal.zzer r8 = r7.zzq()     // Catch:{ SQLiteException -> 0x024c }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zze()     // Catch:{ SQLiteException -> 0x024c }
            java.lang.String r9 = "Raw event metadata record is missing. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x024c }
            r8.zza(r9, r10)     // Catch:{ SQLiteException -> 0x024c }
            if (r6 == 0) goto L_0x00e4
            r6.close()     // Catch:{ all -> 0x1030 }
            goto L_0x00e4
        L_0x012f:
            byte[] r13 = r6.getBlob(r14)     // Catch:{ SQLiteException -> 0x024c }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r15 = com.google.android.gms.internal.measurement.zzcd.zzg.zzbh()     // Catch:{ IOException -> 0x022e }
            com.google.android.gms.internal.measurement.zzjk r13 = com.google.android.gms.measurement.internal.zzks.zza(r15, (byte[]) r13)     // Catch:{ IOException -> 0x022e }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r13 = (com.google.android.gms.internal.measurement.zzcd.zzg.zza) r13     // Catch:{ IOException -> 0x022e }
            com.google.android.gms.internal.measurement.zzjh r13 = r13.zzz()     // Catch:{ IOException -> 0x022e }
            com.google.android.gms.internal.measurement.zzhz r13 = (com.google.android.gms.internal.measurement.zzhz) r13     // Catch:{ IOException -> 0x022e }
            com.google.android.gms.internal.measurement.zzcd$zzg r13 = (com.google.android.gms.internal.measurement.zzcd.zzg) r13     // Catch:{ IOException -> 0x022e }
            boolean r15 = r6.moveToNext()     // Catch:{ SQLiteException -> 0x024c }
            if (r15 == 0) goto L_0x015f
            com.google.android.gms.measurement.internal.zzer r15 = r7.zzq()     // Catch:{ SQLiteException -> 0x024c }
            com.google.android.gms.measurement.internal.zzet r15 = r15.zzh()     // Catch:{ SQLiteException -> 0x024c }
            java.lang.String r14 = "Get multiple raw event metadata records, expected one. appId"
            r25 = r4
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x0249 }
            r15.zza(r14, r4)     // Catch:{ SQLiteException -> 0x0249 }
            goto L_0x0161
        L_0x015f:
            r25 = r4
        L_0x0161:
            r6.close()     // Catch:{ SQLiteException -> 0x0249 }
            r5.zza(r13)     // Catch:{ SQLiteException -> 0x0249 }
            r13 = -1
            int r4 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x0184
            java.lang.String r4 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r13 = 3
            java.lang.String[] r14 = new java.lang.String[r13]     // Catch:{ SQLiteException -> 0x0249 }
            r13 = 0
            r14[r13] = r11     // Catch:{ SQLiteException -> 0x0249 }
            r13 = 1
            r14[r13] = r12     // Catch:{ SQLiteException -> 0x0249 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x0249 }
            r9 = 2
            r14[r9] = r8     // Catch:{ SQLiteException -> 0x0249 }
            r19 = r4
            r20 = r14
            goto L_0x0193
        L_0x0184:
            java.lang.String r4 = "app_id = ? and metadata_fingerprint = ?"
            r8 = 2
            java.lang.String[] r9 = new java.lang.String[r8]     // Catch:{ SQLiteException -> 0x0249 }
            r8 = 0
            r9[r8] = r11     // Catch:{ SQLiteException -> 0x0249 }
            r8 = 1
            r9[r8] = r12     // Catch:{ SQLiteException -> 0x0249 }
            r19 = r4
            r20 = r9
        L_0x0193:
            java.lang.String r17 = "raw_events"
            java.lang.String r4 = "rowid"
            java.lang.String r8 = "name"
            java.lang.String r9 = "timestamp"
            java.lang.String r12 = "data"
            java.lang.String[] r18 = new java.lang.String[]{r4, r8, r9, r12}     // Catch:{ SQLiteException -> 0x0249 }
            r21 = 0
            r22 = 0
            java.lang.String r23 = "rowid"
            r24 = 0
            r16 = r10
            android.database.Cursor r4 = r16.query(r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ SQLiteException -> 0x0249 }
            boolean r6 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            if (r6 != 0) goto L_0x01cd
            com.google.android.gms.measurement.internal.zzer r6 = r7.zzq()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            com.google.android.gms.measurement.internal.zzet r6 = r6.zzh()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            java.lang.String r8 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            r6.zza(r8, r9)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            if (r4 == 0) goto L_0x0278
            r4.close()     // Catch:{ all -> 0x1030 }
            goto L_0x0278
        L_0x01cd:
            r6 = 0
            long r8 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            r6 = 3
            byte[] r10 = r4.getBlob(r6)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r6 = com.google.android.gms.internal.measurement.zzcd.zzc.zzj()     // Catch:{ IOException -> 0x0207 }
            com.google.android.gms.internal.measurement.zzjk r6 = com.google.android.gms.measurement.internal.zzks.zza(r6, (byte[]) r10)     // Catch:{ IOException -> 0x0207 }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r6 = (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r6     // Catch:{ IOException -> 0x0207 }
            r10 = 1
            java.lang.String r12 = r4.getString(r10)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r10 = r6.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            r12 = 2
            long r13 = r4.getLong(r12)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            r10.zza((long) r13)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzjh r6 = r6.zzz()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzhz r6 = (com.google.android.gms.internal.measurement.zzhz) r6     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzcd$zzc r6 = (com.google.android.gms.internal.measurement.zzcd.zzc) r6     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            boolean r6 = r5.zza(r8, r6)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            if (r6 != 0) goto L_0x021a
            if (r4 == 0) goto L_0x0278
            r4.close()     // Catch:{ all -> 0x1030 }
            goto L_0x0278
        L_0x0207:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.measurement.internal.zzer r8 = r7.zzq()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zze()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            java.lang.String r9 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            r8.zza(r9, r10, r6)     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
        L_0x021a:
            boolean r6 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x022a, all -> 0x0226 }
            if (r6 != 0) goto L_0x01cd
            if (r4 == 0) goto L_0x0278
            r4.close()     // Catch:{ all -> 0x1030 }
            goto L_0x0278
        L_0x0226:
            r0 = move-exception
            r5 = r1
            r6 = r4
            goto L_0x0259
        L_0x022a:
            r0 = move-exception
            r6 = r4
            goto L_0x00ab
        L_0x022e:
            r0 = move-exception
            r25 = r4
            r4 = r0
            com.google.android.gms.measurement.internal.zzer r8 = r7.zzq()     // Catch:{ SQLiteException -> 0x0249 }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zze()     // Catch:{ SQLiteException -> 0x0249 }
            java.lang.String r9 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x0249 }
            r8.zza(r9, r10, r4)     // Catch:{ SQLiteException -> 0x0249 }
            if (r6 == 0) goto L_0x0278
            r6.close()     // Catch:{ all -> 0x1030 }
            goto L_0x0278
        L_0x0249:
            r0 = move-exception
            goto L_0x00ab
        L_0x024c:
            r0 = move-exception
            r25 = r4
            goto L_0x00ab
        L_0x0251:
            r0 = move-exception
            r25 = r4
            r4 = r0
            goto L_0x0261
        L_0x0256:
            r0 = move-exception
            r5 = r1
            r6 = 0
        L_0x0259:
            r1 = r0
            goto L_0x1028
        L_0x025c:
            r0 = move-exception
            r25 = r4
            r4 = r0
            r6 = 0
        L_0x0261:
            r11 = 0
        L_0x0262:
            com.google.android.gms.measurement.internal.zzer r7 = r7.zzq()     // Catch:{ all -> 0x1024 }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zze()     // Catch:{ all -> 0x1024 }
            java.lang.String r8 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r11)     // Catch:{ all -> 0x1024 }
            r7.zza(r8, r9, r4)     // Catch:{ all -> 0x1024 }
            if (r6 == 0) goto L_0x0278
            r6.close()     // Catch:{ all -> 0x1030 }
        L_0x0278:
            java.util.List<com.google.android.gms.internal.measurement.zzcd$zzc> r4 = r5.zzc     // Catch:{ all -> 0x1030 }
            if (r4 == 0) goto L_0x0287
            java.util.List<com.google.android.gms.internal.measurement.zzcd$zzc> r4 = r5.zzc     // Catch:{ all -> 0x1030 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x1030 }
            if (r4 == 0) goto L_0x0285
            goto L_0x0287
        L_0x0285:
            r4 = 0
            goto L_0x0288
        L_0x0287:
            r4 = 1
        L_0x0288:
            if (r4 != 0) goto L_0x1013
            com.google.android.gms.internal.measurement.zzcd$zzg r4 = r5.zza     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r4 = r4.zzbn()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r4 = (com.google.android.gms.internal.measurement.zzhz.zza) r4     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r4 = (com.google.android.gms.internal.measurement.zzcd.zzg.zza) r4     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r4 = r4.zzc()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zza()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r7 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzat.zzat     // Catch:{ all -> 0x1030 }
            boolean r6 = r6.zze(r7, r8)     // Catch:{ all -> 0x1030 }
            r7 = -1
            r8 = -1
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x02b3:
            java.util.List<com.google.android.gms.internal.measurement.zzcd$zzc> r9 = r5.zzc     // Catch:{ all -> 0x1030 }
            int r9 = r9.size()     // Catch:{ all -> 0x1030 }
            r18 = r13
            java.lang.String r13 = "_fr"
            r19 = r2
            java.lang.String r2 = "_et"
            r20 = r14
            java.lang.String r14 = "_e"
            r21 = r6
            r22 = r7
            if (r12 >= r9) goto L_0x095a
            java.util.List<com.google.android.gms.internal.measurement.zzcd$zzc> r9 = r5.zzc     // Catch:{ all -> 0x1030 }
            java.lang.Object r9 = r9.get(r12)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r9 = (com.google.android.gms.internal.measurement.zzcd.zzc) r9     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r9 = r9.zzbn()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r9 = (com.google.android.gms.internal.measurement.zzhz.zza) r9     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r9 = (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfp r6 = r42.zzc()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r7 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x1030 }
            r26 = r12
            java.lang.String r12 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r6 = r6.zzb(r7, r12)     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = "_err"
            if (r6 == 0) goto L_0x0374
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r12 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r12.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r12)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r13 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzep r13 = r13.zzi()     // Catch:{ all -> 0x1030 }
            java.lang.String r14 = r9.zzd()     // Catch:{ all -> 0x1030 }
            java.lang.String r13 = r13.zza((java.lang.String) r14)     // Catch:{ all -> 0x1030 }
            r2.zza(r6, r12, r13)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfp r2 = r42.zzc()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r6 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.zzg(r6)     // Catch:{ all -> 0x1030 }
            if (r2 != 0) goto L_0x033d
            com.google.android.gms.measurement.internal.zzfp r2 = r42.zzc()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r6 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.zzh(r6)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x033b
            goto L_0x033d
        L_0x033b:
            r2 = 0
            goto L_0x033e
        L_0x033d:
            r2 = 1
        L_0x033e:
            if (r2 != 0) goto L_0x0367
            java.lang.String r2 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r2 = r7.equals(r2)     // Catch:{ all -> 0x1030 }
            if (r2 != 0) goto L_0x0367
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzkw r27 = r2.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzkv r2 = r1.zzaa     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r6 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r29 = r6.zzx()     // Catch:{ all -> 0x1030 }
            r30 = 11
            java.lang.String r31 = "_ev"
            java.lang.String r32 = r9.zzd()     // Catch:{ all -> 0x1030 }
            r33 = 0
            r28 = r2
            r27.zza((com.google.android.gms.measurement.internal.zzkv) r28, (java.lang.String) r29, (int) r30, (java.lang.String) r31, (java.lang.String) r32, (int) r33)     // Catch:{ all -> 0x1030 }
        L_0x0367:
            r27 = r15
            r13 = r18
            r14 = r20
            r6 = r26
            r7 = 3
            r16 = r3
            goto L_0x094c
        L_0x0374:
            boolean r6 = com.google.android.gms.internal.measurement.zzlq.zzb()     // Catch:{ all -> 0x1030 }
            if (r6 == 0) goto L_0x0404
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zza()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r12 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r12.zzx()     // Catch:{ all -> 0x1030 }
            r27 = r15
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzat.zzcl     // Catch:{ all -> 0x1030 }
            boolean r6 = r6.zze(r12, r15)     // Catch:{ all -> 0x1030 }
            if (r6 == 0) goto L_0x0406
            java.lang.String r6 = r9.zzd()     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = com.google.android.gms.measurement.internal.zzgs.zza(r3)     // Catch:{ all -> 0x1030 }
            boolean r6 = r6.equals(r12)     // Catch:{ all -> 0x1030 }
            if (r6 == 0) goto L_0x0406
            r9.zza((java.lang.String) r3)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r6 = r6.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r6 = r6.zzw()     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = "Renaming ad_impression to _ai"
            r6.zza(r12)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r6 = r6.zzq()     // Catch:{ all -> 0x1030 }
            r12 = 5
            boolean r6 = r6.zza((int) r12)     // Catch:{ all -> 0x1030 }
            if (r6 == 0) goto L_0x0406
            r6 = 0
        L_0x03be:
            int r12 = r9.zzb()     // Catch:{ all -> 0x1030 }
            if (r6 >= r12) goto L_0x0406
            java.lang.String r12 = "ad_platform"
            com.google.android.gms.internal.measurement.zzcd$zze r15 = r9.zza((int) r6)     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = r15.zzb()     // Catch:{ all -> 0x1030 }
            boolean r12 = r12.equals(r15)     // Catch:{ all -> 0x1030 }
            if (r12 == 0) goto L_0x0401
            com.google.android.gms.internal.measurement.zzcd$zze r12 = r9.zza((int) r6)     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r12.zzd()     // Catch:{ all -> 0x1030 }
            boolean r12 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x1030 }
            if (r12 != 0) goto L_0x0401
            java.lang.String r12 = "admob"
            com.google.android.gms.internal.measurement.zzcd$zze r15 = r9.zza((int) r6)     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = r15.zzd()     // Catch:{ all -> 0x1030 }
            boolean r12 = r12.equalsIgnoreCase(r15)     // Catch:{ all -> 0x1030 }
            if (r12 == 0) goto L_0x0401
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r12 = r12.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r12 = r12.zzj()     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = "AdMob ad impression logged from app. Potentially duplicative."
            r12.zza(r15)     // Catch:{ all -> 0x1030 }
        L_0x0401:
            int r6 = r6 + 1
            goto L_0x03be
        L_0x0404:
            r27 = r15
        L_0x0406:
            com.google.android.gms.measurement.internal.zzfp r6 = r42.zzc()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r12 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r12.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r6 = r6.zzc(r12, r15)     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = "_c"
            if (r6 != 0) goto L_0x0477
            r42.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = r9.zzd()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)     // Catch:{ all -> 0x1030 }
            r16 = r3
            int r3 = r15.hashCode()     // Catch:{ all -> 0x1030 }
            r29 = r10
            r10 = 94660(0x171c4, float:1.32647E-40)
            if (r3 == r10) goto L_0x0452
            r10 = 95025(0x17331, float:1.33158E-40)
            if (r3 == r10) goto L_0x0448
            r10 = 95027(0x17333, float:1.33161E-40)
            if (r3 == r10) goto L_0x043e
            goto L_0x045c
        L_0x043e:
            java.lang.String r3 = "_ui"
            boolean r3 = r15.equals(r3)     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x045c
            r3 = 1
            goto L_0x045d
        L_0x0448:
            java.lang.String r3 = "_ug"
            boolean r3 = r15.equals(r3)     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x045c
            r3 = 2
            goto L_0x045d
        L_0x0452:
            java.lang.String r3 = "_in"
            boolean r3 = r15.equals(r3)     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x045c
            r3 = 0
            goto L_0x045d
        L_0x045c:
            r3 = -1
        L_0x045d:
            if (r3 == 0) goto L_0x0467
            r10 = 1
            if (r3 == r10) goto L_0x0467
            r10 = 2
            if (r3 == r10) goto L_0x0467
            r3 = 0
            goto L_0x0468
        L_0x0467:
            r3 = 1
        L_0x0468:
            if (r3 == 0) goto L_0x046b
            goto L_0x047b
        L_0x046b:
            r30 = r2
            r31 = r4
            r32 = r11
            r10 = r13
            r11 = r14
        L_0x0473:
            r13 = r18
            goto L_0x0664
        L_0x0477:
            r16 = r3
            r29 = r10
        L_0x047b:
            r30 = r2
            r3 = 0
            r10 = 0
            r15 = 0
        L_0x0480:
            int r2 = r9.zzb()     // Catch:{ all -> 0x1030 }
            r31 = r4
            java.lang.String r4 = "_r"
            if (r15 >= r2) goto L_0x04f1
            com.google.android.gms.internal.measurement.zzcd$zze r2 = r9.zza((int) r15)     // Catch:{ all -> 0x1030 }
            java.lang.String r2 = r2.zzb()     // Catch:{ all -> 0x1030 }
            boolean r2 = r12.equals(r2)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x04b9
            com.google.android.gms.internal.measurement.zzcd$zze r2 = r9.zza((int) r15)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = r2.zzbn()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = (com.google.android.gms.internal.measurement.zzhz.zza) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = (com.google.android.gms.internal.measurement.zzcd.zze.zza) r2     // Catch:{ all -> 0x1030 }
            r3 = 1
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = r2.zza((long) r3)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r2 = r2.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r2 = (com.google.android.gms.internal.measurement.zzcd.zze) r2     // Catch:{ all -> 0x1030 }
            r9.zza((int) r15, (com.google.android.gms.internal.measurement.zzcd.zze) r2)     // Catch:{ all -> 0x1030 }
            r32 = r11
            r3 = 1
            goto L_0x04ea
        L_0x04b9:
            com.google.android.gms.internal.measurement.zzcd$zze r2 = r9.zza((int) r15)     // Catch:{ all -> 0x1030 }
            java.lang.String r2 = r2.zzb()     // Catch:{ all -> 0x1030 }
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x04e8
            com.google.android.gms.internal.measurement.zzcd$zze r2 = r9.zza((int) r15)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = r2.zzbn()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = (com.google.android.gms.internal.measurement.zzhz.zza) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = (com.google.android.gms.internal.measurement.zzcd.zze.zza) r2     // Catch:{ all -> 0x1030 }
            r32 = r11
            r10 = 1
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = r2.zza((long) r10)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r2 = r2.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r2 = (com.google.android.gms.internal.measurement.zzcd.zze) r2     // Catch:{ all -> 0x1030 }
            r9.zza((int) r15, (com.google.android.gms.internal.measurement.zzcd.zze) r2)     // Catch:{ all -> 0x1030 }
            r10 = 1
            goto L_0x04ea
        L_0x04e8:
            r32 = r11
        L_0x04ea:
            int r15 = r15 + 1
            r4 = r31
            r11 = r32
            goto L_0x0480
        L_0x04f1:
            r32 = r11
            if (r3 != 0) goto L_0x0528
            if (r6 == 0) goto L_0x0528
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzw()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzep r11 = r11.zzi()     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = r9.zzd()     // Catch:{ all -> 0x1030 }
            java.lang.String r11 = r11.zza((java.lang.String) r15)     // Catch:{ all -> 0x1030 }
            r2.zza(r3, r11)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = com.google.android.gms.internal.measurement.zzcd.zze.zzm()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = r2.zza((java.lang.String) r12)     // Catch:{ all -> 0x1030 }
            r3 = r13
            r11 = r14
            r13 = 1
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = r2.zza((long) r13)     // Catch:{ all -> 0x1030 }
            r9.zza((com.google.android.gms.internal.measurement.zzcd.zze.zza) r2)     // Catch:{ all -> 0x1030 }
            goto L_0x052a
        L_0x0528:
            r3 = r13
            r11 = r14
        L_0x052a:
            if (r10 != 0) goto L_0x055a
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzw()     // Catch:{ all -> 0x1030 }
            java.lang.String r10 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzfv r13 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzep r13 = r13.zzi()     // Catch:{ all -> 0x1030 }
            java.lang.String r14 = r9.zzd()     // Catch:{ all -> 0x1030 }
            java.lang.String r13 = r13.zza((java.lang.String) r14)     // Catch:{ all -> 0x1030 }
            r2.zza(r10, r13)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = com.google.android.gms.internal.measurement.zzcd.zze.zzm()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = r2.zza((java.lang.String) r4)     // Catch:{ all -> 0x1030 }
            r13 = 1
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = r2.zza((long) r13)     // Catch:{ all -> 0x1030 }
            r9.zza((com.google.android.gms.internal.measurement.zzcd.zze.zza) r2)     // Catch:{ all -> 0x1030 }
        L_0x055a:
            com.google.android.gms.measurement.internal.zzac r33 = r42.zze()     // Catch:{ all -> 0x1030 }
            long r34 = r42.zzy()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r2 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r36 = r2.zzx()     // Catch:{ all -> 0x1030 }
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 1
            com.google.android.gms.measurement.internal.zzaf r2 = r33.zza(r34, r36, r37, r38, r39, r40, r41)     // Catch:{ all -> 0x1030 }
            long r13 = r2.zze     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r10 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r10 = r10.zzx()     // Catch:{ all -> 0x1030 }
            int r2 = r2.zzc(r10)     // Catch:{ all -> 0x1030 }
            r10 = r3
            long r2 = (long) r2     // Catch:{ all -> 0x1030 }
            int r15 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r15 <= 0) goto L_0x0592
            zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (java.lang.String) r4)     // Catch:{ all -> 0x1030 }
            goto L_0x0594
        L_0x0592:
            r18 = 1
        L_0x0594:
            java.lang.String r2 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r2 = com.google.android.gms.measurement.internal.zzkw.zza((java.lang.String) r2)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x0473
            if (r6 == 0) goto L_0x0473
            com.google.android.gms.measurement.internal.zzac r33 = r42.zze()     // Catch:{ all -> 0x1030 }
            long r34 = r42.zzy()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r2 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r36 = r2.zzx()     // Catch:{ all -> 0x1030 }
            r37 = 0
            r38 = 0
            r39 = 1
            r40 = 0
            r41 = 0
            com.google.android.gms.measurement.internal.zzaf r2 = r33.zza(r34, r36, r37, r38, r39, r40, r41)     // Catch:{ all -> 0x1030 }
            long r2 = r2.zzc     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r4 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r4 = r4.zza()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r13 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r13 = r13.zzx()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r14 = com.google.android.gms.measurement.internal.zzat.zzm     // Catch:{ all -> 0x1030 }
            int r4 = r4.zzb(r13, r14)     // Catch:{ all -> 0x1030 }
            long r13 = (long) r4     // Catch:{ all -> 0x1030 }
            int r4 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r4 <= 0) goto L_0x0473
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r4 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r4)     // Catch:{ all -> 0x1030 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x1030 }
            r2 = -1
            r3 = 0
            r4 = 0
            r13 = 0
        L_0x05f2:
            int r14 = r9.zzb()     // Catch:{ all -> 0x1030 }
            if (r13 >= r14) goto L_0x061f
            com.google.android.gms.internal.measurement.zzcd$zze r14 = r9.zza((int) r13)     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = r14.zzb()     // Catch:{ all -> 0x1030 }
            boolean r15 = r12.equals(r15)     // Catch:{ all -> 0x1030 }
            if (r15 == 0) goto L_0x0611
            com.google.android.gms.internal.measurement.zzhz$zza r2 = r14.zzbn()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = (com.google.android.gms.internal.measurement.zzhz.zza) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r2 = (com.google.android.gms.internal.measurement.zzcd.zze.zza) r2     // Catch:{ all -> 0x1030 }
            r3 = r2
            r2 = r13
            goto L_0x061c
        L_0x0611:
            java.lang.String r14 = r14.zzb()     // Catch:{ all -> 0x1030 }
            boolean r14 = r7.equals(r14)     // Catch:{ all -> 0x1030 }
            if (r14 == 0) goto L_0x061c
            r4 = 1
        L_0x061c:
            int r13 = r13 + 1
            goto L_0x05f2
        L_0x061f:
            if (r4 == 0) goto L_0x0628
            if (r3 == 0) goto L_0x0628
            r9.zzb((int) r2)     // Catch:{ all -> 0x1030 }
            goto L_0x0473
        L_0x0628:
            if (r3 == 0) goto L_0x0649
            java.lang.Object r3 = r3.clone()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r3 = (com.google.android.gms.internal.measurement.zzhz.zza) r3     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r3 = (com.google.android.gms.internal.measurement.zzcd.zze.zza) r3     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze$zza r3 = r3.zza((java.lang.String) r7)     // Catch:{ all -> 0x1030 }
            r13 = 10
            com.google.android.gms.internal.measurement.zzcd$zze$zza r3 = r3.zza((long) r13)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r3 = r3.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r3 = (com.google.android.gms.internal.measurement.zzhz) r3     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r3 = (com.google.android.gms.internal.measurement.zzcd.zze) r3     // Catch:{ all -> 0x1030 }
            r9.zza((int) r2, (com.google.android.gms.internal.measurement.zzcd.zze) r3)     // Catch:{ all -> 0x1030 }
            goto L_0x0473
        L_0x0649:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r4 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r4)     // Catch:{ all -> 0x1030 }
            r2.zza(r3, r4)     // Catch:{ all -> 0x1030 }
            goto L_0x0473
        L_0x0664:
            if (r6 == 0) goto L_0x0727
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x1030 }
            java.util.List r3 = r9.zza()     // Catch:{ all -> 0x1030 }
            r2.<init>(r3)     // Catch:{ all -> 0x1030 }
            r3 = 0
            r4 = -1
            r6 = -1
        L_0x0672:
            int r7 = r2.size()     // Catch:{ all -> 0x1030 }
            java.lang.String r14 = "currency"
            java.lang.String r15 = "value"
            if (r3 >= r7) goto L_0x06a2
            java.lang.Object r7 = r2.get(r3)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r7 = (com.google.android.gms.internal.measurement.zzcd.zze) r7     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = r7.zzb()     // Catch:{ all -> 0x1030 }
            boolean r7 = r15.equals(r7)     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x068e
            r4 = r3
            goto L_0x069f
        L_0x068e:
            java.lang.Object r7 = r2.get(r3)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r7 = (com.google.android.gms.internal.measurement.zzcd.zze) r7     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = r7.zzb()     // Catch:{ all -> 0x1030 }
            boolean r7 = r14.equals(r7)     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x069f
            r6 = r3
        L_0x069f:
            int r3 = r3 + 1
            goto L_0x0672
        L_0x06a2:
            r3 = -1
            if (r4 == r3) goto L_0x0728
            java.lang.Object r3 = r2.get(r4)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r3 = (com.google.android.gms.internal.measurement.zzcd.zze) r3     // Catch:{ all -> 0x1030 }
            boolean r3 = r3.zze()     // Catch:{ all -> 0x1030 }
            if (r3 != 0) goto L_0x06d8
            java.lang.Object r3 = r2.get(r4)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r3 = (com.google.android.gms.internal.measurement.zzcd.zze) r3     // Catch:{ all -> 0x1030 }
            boolean r3 = r3.zzi()     // Catch:{ all -> 0x1030 }
            if (r3 != 0) goto L_0x06d8
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzj()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "Value must be specified with a numeric type."
            r2.zza(r3)     // Catch:{ all -> 0x1030 }
            r9.zzb((int) r4)     // Catch:{ all -> 0x1030 }
            zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (java.lang.String) r12)     // Catch:{ all -> 0x1030 }
            r2 = 18
            zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (int) r2, (java.lang.String) r15)     // Catch:{ all -> 0x1030 }
            goto L_0x0727
        L_0x06d8:
            r3 = -1
            if (r6 != r3) goto L_0x06de
            r2 = 1
            r7 = 3
            goto L_0x070a
        L_0x06de:
            java.lang.Object r2 = r2.get(r6)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r2 = (com.google.android.gms.internal.measurement.zzcd.zze) r2     // Catch:{ all -> 0x1030 }
            java.lang.String r2 = r2.zzd()     // Catch:{ all -> 0x1030 }
            int r6 = r2.length()     // Catch:{ all -> 0x1030 }
            r7 = 3
            if (r6 == r7) goto L_0x06f1
        L_0x06ef:
            r2 = 1
            goto L_0x070a
        L_0x06f1:
            r6 = 0
        L_0x06f2:
            int r15 = r2.length()     // Catch:{ all -> 0x1030 }
            if (r6 >= r15) goto L_0x0709
            int r15 = r2.codePointAt(r6)     // Catch:{ all -> 0x1030 }
            boolean r17 = java.lang.Character.isLetter(r15)     // Catch:{ all -> 0x1030 }
            if (r17 != 0) goto L_0x0703
            goto L_0x06ef
        L_0x0703:
            int r15 = java.lang.Character.charCount(r15)     // Catch:{ all -> 0x1030 }
            int r6 = r6 + r15
            goto L_0x06f2
        L_0x0709:
            r2 = 0
        L_0x070a:
            if (r2 == 0) goto L_0x0729
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzj()     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r2.zza(r6)     // Catch:{ all -> 0x1030 }
            r9.zzb((int) r4)     // Catch:{ all -> 0x1030 }
            zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (java.lang.String) r12)     // Catch:{ all -> 0x1030 }
            r2 = 19
            zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (int) r2, (java.lang.String) r14)     // Catch:{ all -> 0x1030 }
            goto L_0x0729
        L_0x0727:
            r3 = -1
        L_0x0728:
            r7 = 3
        L_0x0729:
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r4 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r4 = r4.zzx()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzat.zzas     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.zze(r4, r6)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x08cb
            java.lang.String r2 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r2 = r11.equals(r2)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x079a
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r2 = r9.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r2 = (com.google.android.gms.internal.measurement.zzcd.zzc) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r2 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r2, (java.lang.String) r10)     // Catch:{ all -> 0x1030 }
            if (r2 != 0) goto L_0x0795
            if (r32 == 0) goto L_0x078b
            long r14 = r32.zzf()     // Catch:{ all -> 0x1030 }
            long r17 = r9.zzf()     // Catch:{ all -> 0x1030 }
            long r14 = r14 - r17
            long r14 = java.lang.Math.abs(r14)     // Catch:{ all -> 0x1030 }
            r17 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r2 > 0) goto L_0x078b
            java.lang.Object r2 = r32.clone()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = (com.google.android.gms.internal.measurement.zzhz.zza) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2     // Catch:{ all -> 0x1030 }
            boolean r4 = r1.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2)     // Catch:{ all -> 0x1030 }
            if (r4 == 0) goto L_0x078b
            r4 = r31
            r4.zza((int) r8, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2)     // Catch:{ all -> 0x1030 }
            r10 = r22
            r6 = r30
        L_0x0785:
            r29 = 0
            r32 = 0
            goto L_0x08d1
        L_0x078b:
            r4 = r31
            r29 = r9
            r10 = r20
        L_0x0791:
            r6 = r30
            goto L_0x08d1
        L_0x0795:
            r4 = r31
            r10 = r22
            goto L_0x0791
        L_0x079a:
            r4 = r31
            java.lang.String r2 = "_vs"
            java.lang.String r6 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x07f1
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r2 = r9.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r2 = (com.google.android.gms.internal.measurement.zzcd.zzc) r2     // Catch:{ all -> 0x1030 }
            r6 = r30
            com.google.android.gms.internal.measurement.zzcd$zze r2 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r2, (java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            if (r2 != 0) goto L_0x07ed
            if (r29 == 0) goto L_0x07e5
            long r14 = r29.zzf()     // Catch:{ all -> 0x1030 }
            long r17 = r9.zzf()     // Catch:{ all -> 0x1030 }
            long r14 = r14 - r17
            long r14 = java.lang.Math.abs(r14)     // Catch:{ all -> 0x1030 }
            r17 = 1000(0x3e8, double:4.94E-321)
            int r2 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r2 > 0) goto L_0x07e5
            java.lang.Object r2 = r29.clone()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = (com.google.android.gms.internal.measurement.zzhz.zza) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2     // Catch:{ all -> 0x1030 }
            boolean r10 = r1.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9)     // Catch:{ all -> 0x1030 }
            if (r10 == 0) goto L_0x07e5
            r10 = r22
            r4.zza((int) r10, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2)     // Catch:{ all -> 0x1030 }
            goto L_0x0785
        L_0x07e5:
            r10 = r22
            r32 = r9
            r8 = r20
            goto L_0x08d1
        L_0x07ed:
            r10 = r22
            goto L_0x08d1
        L_0x07f1:
            r10 = r22
            r6 = r30
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r12 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r12.zzx()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzat.zzbl     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.zze(r12, r14)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x08d1
            java.lang.String r2 = "_ab"
            java.lang.String r12 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.equals(r12)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x08d1
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r2 = r9.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r2 = (com.google.android.gms.internal.measurement.zzcd.zzc) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r2 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r2, (java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            if (r2 != 0) goto L_0x08d1
            if (r29 == 0) goto L_0x08d1
            long r14 = r29.zzf()     // Catch:{ all -> 0x1030 }
            long r17 = r9.zzf()     // Catch:{ all -> 0x1030 }
            long r14 = r14 - r17
            long r14 = java.lang.Math.abs(r14)     // Catch:{ all -> 0x1030 }
            r17 = 4000(0xfa0, double:1.9763E-320)
            int r2 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r2 > 0) goto L_0x08d1
            java.lang.Object r2 = r29.clone()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz$zza r2 = (com.google.android.gms.internal.measurement.zzhz.zza) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2     // Catch:{ all -> 0x1030 }
            r1.zzb((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9)     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r2.zzd()     // Catch:{ all -> 0x1030 }
            boolean r12 = r11.equals(r12)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r12)     // Catch:{ all -> 0x1030 }
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r12 = r2.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r12 = (com.google.android.gms.internal.measurement.zzhz) r12     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r12 = (com.google.android.gms.internal.measurement.zzcd.zzc) r12     // Catch:{ all -> 0x1030 }
            java.lang.String r14 = "_sn"
            com.google.android.gms.internal.measurement.zzcd$zze r12 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r12, (java.lang.String) r14)     // Catch:{ all -> 0x1030 }
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r14 = r2.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r14 = (com.google.android.gms.internal.measurement.zzhz) r14     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r14 = (com.google.android.gms.internal.measurement.zzcd.zzc) r14     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = "_sc"
            com.google.android.gms.internal.measurement.zzcd$zze r14 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r14, (java.lang.String) r15)     // Catch:{ all -> 0x1030 }
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r15 = r2.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r15 = (com.google.android.gms.internal.measurement.zzhz) r15     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r15 = (com.google.android.gms.internal.measurement.zzcd.zzc) r15     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "_si"
            com.google.android.gms.internal.measurement.zzcd$zze r3 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r15, (java.lang.String) r3)     // Catch:{ all -> 0x1030 }
            if (r12 == 0) goto L_0x088c
            java.lang.String r12 = r12.zzd()     // Catch:{ all -> 0x1030 }
            goto L_0x088e
        L_0x088c:
            r12 = r25
        L_0x088e:
            boolean r15 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x1030 }
            if (r15 != 0) goto L_0x089c
            r42.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r15 = "_sn"
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (java.lang.String) r15, (java.lang.Object) r12)     // Catch:{ all -> 0x1030 }
        L_0x089c:
            if (r14 == 0) goto L_0x08a3
            java.lang.String r12 = r14.zzd()     // Catch:{ all -> 0x1030 }
            goto L_0x08a5
        L_0x08a3:
            r12 = r25
        L_0x08a5:
            boolean r14 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x1030 }
            if (r14 != 0) goto L_0x08b3
            r42.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r14 = "_sc"
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (java.lang.String) r14, (java.lang.Object) r12)     // Catch:{ all -> 0x1030 }
        L_0x08b3:
            if (r3 == 0) goto L_0x08c5
            r42.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = "_si"
            long r14 = r3.zzf()     // Catch:{ all -> 0x1030 }
            java.lang.Long r3 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9, (java.lang.String) r12, (java.lang.Object) r3)     // Catch:{ all -> 0x1030 }
        L_0x08c5:
            r4.zza((int) r10, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r2)     // Catch:{ all -> 0x1030 }
            r29 = 0
            goto L_0x08d1
        L_0x08cb:
            r10 = r22
            r6 = r30
            r4 = r31
        L_0x08d1:
            if (r21 != 0) goto L_0x0932
            java.lang.String r2 = r9.zzd()     // Catch:{ all -> 0x1030 }
            boolean r2 = r11.equals(r2)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x0932
            int r2 = r9.zzb()     // Catch:{ all -> 0x1030 }
            if (r2 != 0) goto L_0x08fd
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r6 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            r2.zza(r3, r6)     // Catch:{ all -> 0x1030 }
            goto L_0x0932
        L_0x08fd:
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r2 = r9.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r2 = (com.google.android.gms.internal.measurement.zzcd.zzc) r2     // Catch:{ all -> 0x1030 }
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzks.zzb(r2, r6)     // Catch:{ all -> 0x1030 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ all -> 0x1030 }
            if (r2 != 0) goto L_0x092a
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r6 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            r2.zza(r3, r6)     // Catch:{ all -> 0x1030 }
            goto L_0x0932
        L_0x092a:
            long r2 = r2.longValue()     // Catch:{ all -> 0x1030 }
            long r2 = r27 + r2
            r27 = r2
        L_0x0932:
            java.util.List<com.google.android.gms.internal.measurement.zzcd$zzc> r2 = r5.zzc     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r3 = r9.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r3 = (com.google.android.gms.internal.measurement.zzhz) r3     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r3 = (com.google.android.gms.internal.measurement.zzcd.zzc) r3     // Catch:{ all -> 0x1030 }
            r6 = r26
            r2.set(r6, r3)     // Catch:{ all -> 0x1030 }
            int r14 = r20 + 1
            r4.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r9)     // Catch:{ all -> 0x1030 }
            r22 = r10
            r10 = r29
            r11 = r32
        L_0x094c:
            int r12 = r6 + 1
            r3 = r16
            r2 = r19
            r6 = r21
            r7 = r22
            r15 = r27
            goto L_0x02b3
        L_0x095a:
            r6 = r2
            r10 = r13
            r11 = r14
            r27 = r15
            if (r21 == 0) goto L_0x09b6
            r14 = r20
            r15 = r27
            r2 = 0
        L_0x0966:
            if (r2 >= r14) goto L_0x09b4
            com.google.android.gms.internal.measurement.zzcd$zzc r3 = r4.zzb((int) r2)     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = r3.zzc()     // Catch:{ all -> 0x1030 }
            boolean r7 = r11.equals(r7)     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x0987
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r7 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r3, (java.lang.String) r10)     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x0987
            r4.zzc((int) r2)     // Catch:{ all -> 0x1030 }
            int r14 = r14 + -1
            int r2 = r2 + -1
            goto L_0x09b1
        L_0x0987:
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r3 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc) r3, (java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x09b1
            boolean r7 = r3.zze()     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x099f
            long r7 = r3.zzf()     // Catch:{ all -> 0x1030 }
            java.lang.Long r3 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x1030 }
            goto L_0x09a0
        L_0x099f:
            r3 = 0
        L_0x09a0:
            if (r3 == 0) goto L_0x09b1
            long r7 = r3.longValue()     // Catch:{ all -> 0x1030 }
            r12 = 0
            int r9 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r9 <= 0) goto L_0x09b1
            long r7 = r3.longValue()     // Catch:{ all -> 0x1030 }
            long r15 = r15 + r7
        L_0x09b1:
            r3 = 1
            int r2 = r2 + r3
            goto L_0x0966
        L_0x09b4:
            r2 = r15
            goto L_0x09b8
        L_0x09b6:
            r2 = r27
        L_0x09b8:
            r6 = 0
            r1.zza((com.google.android.gms.internal.measurement.zzcd.zzg.zza) r4, (long) r2, (boolean) r6)     // Catch:{ all -> 0x1030 }
            java.util.List r6 = r4.zza()     // Catch:{ all -> 0x1030 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x1030 }
        L_0x09c4:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x09de
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r7 = (com.google.android.gms.internal.measurement.zzcd.zzc) r7     // Catch:{ all -> 0x1030 }
            java.lang.String r8 = "_s"
            java.lang.String r7 = r7.zzc()     // Catch:{ all -> 0x1030 }
            boolean r7 = r8.equals(r7)     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x09c4
            r6 = 1
            goto L_0x09df
        L_0x09de:
            r6 = 0
        L_0x09df:
            java.lang.String r7 = "_se"
            if (r6 == 0) goto L_0x09ee
            com.google.android.gms.measurement.internal.zzac r6 = r42.zze()     // Catch:{ all -> 0x1030 }
            java.lang.String r8 = r4.zzj()     // Catch:{ all -> 0x1030 }
            r6.zzb((java.lang.String) r8, (java.lang.String) r7)     // Catch:{ all -> 0x1030 }
        L_0x09ee:
            java.lang.String r6 = "_sid"
            int r6 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzg.zza) r4, (java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            if (r6 < 0) goto L_0x09f8
            r6 = 1
            goto L_0x09f9
        L_0x09f8:
            r6 = 0
        L_0x09f9:
            if (r6 == 0) goto L_0x0a00
            r6 = 1
            r1.zza((com.google.android.gms.internal.measurement.zzcd.zzg.zza) r4, (long) r2, (boolean) r6)     // Catch:{ all -> 0x1030 }
            goto L_0x0a22
        L_0x0a00:
            int r2 = com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzg.zza) r4, (java.lang.String) r7)     // Catch:{ all -> 0x1030 }
            if (r2 < 0) goto L_0x0a22
            r4.zze((int) r2)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = "Session engagement user property is in the bundle without session ID. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r6 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            r2.zza(r3, r6)     // Catch:{ all -> 0x1030 }
        L_0x0a22:
            com.google.android.gms.measurement.internal.zzks r2 = r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r3 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zzw()     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = "Checking account type status for ad personalization signals"
            r3.zza(r6)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfp r3 = r2.zzj()     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1030 }
            boolean r3 = r3.zze(r6)     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x0ab3
            com.google.android.gms.measurement.internal.zzac r3 = r2.zzi()     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = r4.zzj()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzf r3 = r3.zzb(r6)     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x0ab3
            boolean r3 = r3.zzaf()     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x0ab3
            com.google.android.gms.measurement.internal.zzal r3 = r2.zzk()     // Catch:{ all -> 0x1030 }
            boolean r3 = r3.zzi()     // Catch:{ all -> 0x1030 }
            if (r3 == 0) goto L_0x0ab3
            com.google.android.gms.measurement.internal.zzer r3 = r2.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zzv()     // Catch:{ all -> 0x1030 }
            java.lang.String r6 = "Turning off ad personalization due to account type"
            r3.zza(r6)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzk$zza r3 = com.google.android.gms.internal.measurement.zzcd.zzk.zzj()     // Catch:{ all -> 0x1030 }
            r6 = r19
            com.google.android.gms.internal.measurement.zzcd$zzk$zza r3 = r3.zza((java.lang.String) r6)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzal r2 = r2.zzk()     // Catch:{ all -> 0x1030 }
            long r7 = r2.zzg()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzk$zza r2 = r3.zza((long) r7)     // Catch:{ all -> 0x1030 }
            r7 = 1
            com.google.android.gms.internal.measurement.zzcd$zzk$zza r2 = r2.zzb((long) r7)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r2 = r2.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r2 = (com.google.android.gms.internal.measurement.zzhz) r2     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzk r2 = (com.google.android.gms.internal.measurement.zzcd.zzk) r2     // Catch:{ all -> 0x1030 }
            r3 = 0
        L_0x0a91:
            int r7 = r4.zze()     // Catch:{ all -> 0x1030 }
            if (r3 >= r7) goto L_0x0aad
            com.google.android.gms.internal.measurement.zzcd$zzk r7 = r4.zzd((int) r3)     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = r7.zzc()     // Catch:{ all -> 0x1030 }
            boolean r7 = r6.equals(r7)     // Catch:{ all -> 0x1030 }
            if (r7 == 0) goto L_0x0aaa
            r4.zza((int) r3, (com.google.android.gms.internal.measurement.zzcd.zzk) r2)     // Catch:{ all -> 0x1030 }
            r3 = 1
            goto L_0x0aae
        L_0x0aaa:
            int r3 = r3 + 1
            goto L_0x0a91
        L_0x0aad:
            r3 = 0
        L_0x0aae:
            if (r3 != 0) goto L_0x0ab3
            r4.zza((com.google.android.gms.internal.measurement.zzcd.zzk) r2)     // Catch:{ all -> 0x1030 }
        L_0x0ab3:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r2 = r4.zzb((long) r2)     // Catch:{ all -> 0x1030 }
            r6 = -9223372036854775808
            r2.zzc((long) r6)     // Catch:{ all -> 0x1030 }
            r2 = 0
        L_0x0ac2:
            int r3 = r4.zzb()     // Catch:{ all -> 0x1030 }
            if (r2 >= r3) goto L_0x0af5
            com.google.android.gms.internal.measurement.zzcd$zzc r3 = r4.zzb((int) r2)     // Catch:{ all -> 0x1030 }
            long r6 = r3.zze()     // Catch:{ all -> 0x1030 }
            long r8 = r4.zzf()     // Catch:{ all -> 0x1030 }
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x0adf
            long r6 = r3.zze()     // Catch:{ all -> 0x1030 }
            r4.zzb((long) r6)     // Catch:{ all -> 0x1030 }
        L_0x0adf:
            long r6 = r3.zze()     // Catch:{ all -> 0x1030 }
            long r8 = r4.zzg()     // Catch:{ all -> 0x1030 }
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x0af2
            long r6 = r3.zze()     // Catch:{ all -> 0x1030 }
            r4.zzc((long) r6)     // Catch:{ all -> 0x1030 }
        L_0x0af2:
            int r2 = r2 + 1
            goto L_0x0ac2
        L_0x0af5:
            boolean r2 = com.google.android.gms.internal.measurement.zzox.zzb()     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x0b10
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = r4.zzj()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzat.zzcf     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.zze(r3, r6)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x0b10
            r4.zzq()     // Catch:{ all -> 0x1030 }
        L_0x0b10:
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r2 = r4.zzp()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzo r6 = r42.zzf()     // Catch:{ all -> 0x1030 }
            java.lang.String r7 = r4.zzj()     // Catch:{ all -> 0x1030 }
            java.util.List r8 = r4.zza()     // Catch:{ all -> 0x1030 }
            java.util.List r9 = r4.zzd()     // Catch:{ all -> 0x1030 }
            long r10 = r4.zzf()     // Catch:{ all -> 0x1030 }
            java.lang.Long r10 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x1030 }
            long r11 = r4.zzg()     // Catch:{ all -> 0x1030 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x1030 }
            java.util.List r3 = r6.zza(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x1030 }
            r2.zzc((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzcd.zza>) r3)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzfv r2 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r3 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r3 = r3.zzx()     // Catch:{ all -> 0x1030 }
            boolean r2 = r2.zzi(r3)     // Catch:{ all -> 0x1030 }
            if (r2 == 0) goto L_0x0e83
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x100f }
            r2.<init>()     // Catch:{ all -> 0x100f }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x100f }
            r3.<init>()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzfv r6 = r1.zzk     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzkw r6 = r6.zzh()     // Catch:{ all -> 0x100f }
            java.security.SecureRandom r6 = r6.zzg()     // Catch:{ all -> 0x100f }
            r7 = 0
        L_0x0b62:
            int r8 = r4.zzb()     // Catch:{ all -> 0x100f }
            if (r7 >= r8) goto L_0x0e4a
            com.google.android.gms.internal.measurement.zzcd$zzc r8 = r4.zzb((int) r7)     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzhz$zza r8 = r8.zzbn()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzhz$zza r8 = (com.google.android.gms.internal.measurement.zzhz.zza) r8     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzc$zza r8 = (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8     // Catch:{ all -> 0x100f }
            java.lang.String r9 = r8.zzd()     // Catch:{ all -> 0x100f }
            java.lang.String r10 = "_ep"
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x100f }
            java.lang.String r10 = "_sr"
            if (r9 == 0) goto L_0x0bf6
            r42.zzh()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r9 = r8.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r9 = (com.google.android.gms.internal.measurement.zzcd.zzc) r9     // Catch:{ all -> 0x1030 }
            java.lang.String r11 = "_en"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzks.zzb(r9, r11)     // Catch:{ all -> 0x1030 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x1030 }
            java.lang.Object r11 = r2.get(r9)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzan r11 = (com.google.android.gms.measurement.internal.zzan) r11     // Catch:{ all -> 0x1030 }
            if (r11 != 0) goto L_0x0bae
            com.google.android.gms.measurement.internal.zzac r11 = r42.zze()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r12 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r12.zzx()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzan r11 = r11.zza((java.lang.String) r12, (java.lang.String) r9)     // Catch:{ all -> 0x1030 }
            r2.put(r9, r11)     // Catch:{ all -> 0x1030 }
        L_0x0bae:
            java.lang.Long r9 = r11.zzi     // Catch:{ all -> 0x1030 }
            if (r9 != 0) goto L_0x0beb
            java.lang.Long r9 = r11.zzj     // Catch:{ all -> 0x1030 }
            long r12 = r9.longValue()     // Catch:{ all -> 0x1030 }
            r14 = 1
            int r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r9 <= 0) goto L_0x0bc6
            r42.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.Long r9 = r11.zzj     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8, (java.lang.String) r10, (java.lang.Object) r9)     // Catch:{ all -> 0x1030 }
        L_0x0bc6:
            java.lang.Boolean r9 = r11.zzk     // Catch:{ all -> 0x1030 }
            if (r9 == 0) goto L_0x0be0
            java.lang.Boolean r9 = r11.zzk     // Catch:{ all -> 0x1030 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x1030 }
            if (r9 == 0) goto L_0x0be0
            r42.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r9 = "_efs"
            r10 = 1
            java.lang.Long r12 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8, (java.lang.String) r9, (java.lang.Object) r12)     // Catch:{ all -> 0x1030 }
        L_0x0be0:
            com.google.android.gms.internal.measurement.zzjh r9 = r8.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r9 = (com.google.android.gms.internal.measurement.zzcd.zzc) r9     // Catch:{ all -> 0x1030 }
            r3.add(r9)     // Catch:{ all -> 0x1030 }
        L_0x0beb:
            r4.zza((int) r7, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8)     // Catch:{ all -> 0x1030 }
        L_0x0bee:
            r9 = r2
            r43 = r5
            r15 = r6
            r5 = 1
            goto L_0x0e40
        L_0x0bf6:
            com.google.android.gms.measurement.internal.zzfp r9 = r42.zzc()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzg r11 = r5.zza     // Catch:{ all -> 0x100f }
            java.lang.String r11 = r11.zzx()     // Catch:{ all -> 0x100f }
            long r11 = r9.zzf(r11)     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzfv r9 = r1.zzk     // Catch:{ all -> 0x100f }
            r9.zzh()     // Catch:{ all -> 0x100f }
            long r13 = r8.zzf()     // Catch:{ all -> 0x100f }
            long r13 = com.google.android.gms.measurement.internal.zzkw.zza((long) r13, (long) r11)     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzjh r9 = r8.zzz()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzc r9 = (com.google.android.gms.internal.measurement.zzcd.zzc) r9     // Catch:{ all -> 0x100f }
            java.lang.String r15 = "_dbg"
            r19 = r11
            r16 = 1
            java.lang.Long r11 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x100f }
            boolean r12 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x100f }
            if (r12 != 0) goto L_0x0c83
            if (r11 != 0) goto L_0x0c2c
            goto L_0x0c83
        L_0x0c2c:
            java.util.List r9 = r9.zza()     // Catch:{ all -> 0x1030 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x1030 }
        L_0x0c34:
            boolean r12 = r9.hasNext()     // Catch:{ all -> 0x1030 }
            if (r12 == 0) goto L_0x0c83
            java.lang.Object r12 = r9.next()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zze r12 = (com.google.android.gms.internal.measurement.zzcd.zze) r12     // Catch:{ all -> 0x1030 }
            r43 = r9
            java.lang.String r9 = r12.zzb()     // Catch:{ all -> 0x1030 }
            boolean r9 = r15.equals(r9)     // Catch:{ all -> 0x1030 }
            if (r9 == 0) goto L_0x0c80
            boolean r9 = r11 instanceof java.lang.Long     // Catch:{ all -> 0x1030 }
            if (r9 == 0) goto L_0x0c5e
            long r15 = r12.zzf()     // Catch:{ all -> 0x1030 }
            java.lang.Long r9 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x1030 }
            boolean r9 = r11.equals(r9)     // Catch:{ all -> 0x1030 }
            if (r9 != 0) goto L_0x0c7e
        L_0x0c5e:
            boolean r9 = r11 instanceof java.lang.String     // Catch:{ all -> 0x1030 }
            if (r9 == 0) goto L_0x0c6c
            java.lang.String r9 = r12.zzd()     // Catch:{ all -> 0x1030 }
            boolean r9 = r11.equals(r9)     // Catch:{ all -> 0x1030 }
            if (r9 != 0) goto L_0x0c7e
        L_0x0c6c:
            boolean r9 = r11 instanceof java.lang.Double     // Catch:{ all -> 0x1030 }
            if (r9 == 0) goto L_0x0c83
            double r15 = r12.zzj()     // Catch:{ all -> 0x1030 }
            java.lang.Double r9 = java.lang.Double.valueOf(r15)     // Catch:{ all -> 0x1030 }
            boolean r9 = r11.equals(r9)     // Catch:{ all -> 0x1030 }
            if (r9 == 0) goto L_0x0c83
        L_0x0c7e:
            r9 = 1
            goto L_0x0c84
        L_0x0c80:
            r9 = r43
            goto L_0x0c34
        L_0x0c83:
            r9 = 0
        L_0x0c84:
            if (r9 != 0) goto L_0x0c99
            com.google.android.gms.measurement.internal.zzfp r9 = r42.zzc()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzg r11 = r5.zza     // Catch:{ all -> 0x1030 }
            java.lang.String r11 = r11.zzx()     // Catch:{ all -> 0x1030 }
            java.lang.String r12 = r8.zzd()     // Catch:{ all -> 0x1030 }
            int r9 = r9.zzd(r11, r12)     // Catch:{ all -> 0x1030 }
            goto L_0x0c9a
        L_0x0c99:
            r9 = 1
        L_0x0c9a:
            if (r9 > 0) goto L_0x0cc3
            com.google.android.gms.measurement.internal.zzfv r10 = r1.zzk     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzer r10 = r10.zzq()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.measurement.internal.zzet r10 = r10.zzh()     // Catch:{ all -> 0x1030 }
            java.lang.String r11 = "Sample rate must be positive. event, rate"
            java.lang.String r12 = r8.zzd()     // Catch:{ all -> 0x1030 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x1030 }
            r10.zza(r11, r12, r9)     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzjh r9 = r8.zzz()     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x1030 }
            com.google.android.gms.internal.measurement.zzcd$zzc r9 = (com.google.android.gms.internal.measurement.zzcd.zzc) r9     // Catch:{ all -> 0x1030 }
            r3.add(r9)     // Catch:{ all -> 0x1030 }
            r4.zza((int) r7, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8)     // Catch:{ all -> 0x1030 }
            goto L_0x0bee
        L_0x0cc3:
            java.lang.String r11 = r8.zzd()     // Catch:{ all -> 0x100f }
            java.lang.Object r11 = r2.get(r11)     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzan r11 = (com.google.android.gms.measurement.internal.zzan) r11     // Catch:{ all -> 0x100f }
            if (r11 != 0) goto L_0x0d21
            com.google.android.gms.measurement.internal.zzac r11 = r42.zze()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzg r12 = r5.zza     // Catch:{ all -> 0x100f }
            java.lang.String r12 = r12.zzx()     // Catch:{ all -> 0x100f }
            java.lang.String r15 = r8.zzd()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzan r11 = r11.zza((java.lang.String) r12, (java.lang.String) r15)     // Catch:{ all -> 0x100f }
            if (r11 != 0) goto L_0x0d21
            com.google.android.gms.measurement.internal.zzfv r11 = r1.zzk     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzer r11 = r11.zzq()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzet r11 = r11.zzh()     // Catch:{ all -> 0x100f }
            java.lang.String r12 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzcd$zzg r15 = r5.zza     // Catch:{ all -> 0x100f }
            java.lang.String r15 = r15.zzx()     // Catch:{ all -> 0x100f }
            java.lang.String r1 = r8.zzd()     // Catch:{ all -> 0x100f }
            r11.zza(r12, r15, r1)     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzan r11 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzg r1 = r5.zza     // Catch:{ all -> 0x100f }
            java.lang.String r26 = r1.zzx()     // Catch:{ all -> 0x100f }
            java.lang.String r27 = r8.zzd()     // Catch:{ all -> 0x100f }
            r28 = 1
            r30 = 1
            r32 = 1
            long r34 = r8.zzf()     // Catch:{ all -> 0x100f }
            r36 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r25 = r11
            r25.<init>(r26, r27, r28, r30, r32, r34, r36, r38, r39, r40, r41)     // Catch:{ all -> 0x100f }
        L_0x0d21:
            r42.zzh()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzjh r1 = r8.zzz()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzhz r1 = (com.google.android.gms.internal.measurement.zzhz) r1     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzc r1 = (com.google.android.gms.internal.measurement.zzcd.zzc) r1     // Catch:{ all -> 0x100f }
            java.lang.String r12 = "_eid"
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzks.zzb(r1, r12)     // Catch:{ all -> 0x100f }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x100f }
            if (r1 == 0) goto L_0x0d38
            r12 = 1
            goto L_0x0d39
        L_0x0d38:
            r12 = 0
        L_0x0d39:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)     // Catch:{ all -> 0x100f }
            r15 = 1
            if (r9 != r15) goto L_0x0d6e
            com.google.android.gms.internal.measurement.zzjh r1 = r8.zzz()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzhz r1 = (com.google.android.gms.internal.measurement.zzhz) r1     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzc r1 = (com.google.android.gms.internal.measurement.zzcd.zzc) r1     // Catch:{ all -> 0x100f }
            r3.add(r1)     // Catch:{ all -> 0x100f }
            boolean r1 = r12.booleanValue()     // Catch:{ all -> 0x100f }
            if (r1 == 0) goto L_0x0d69
            java.lang.Long r1 = r11.zzi     // Catch:{ all -> 0x100f }
            if (r1 != 0) goto L_0x0d5d
            java.lang.Long r1 = r11.zzj     // Catch:{ all -> 0x100f }
            if (r1 != 0) goto L_0x0d5d
            java.lang.Boolean r1 = r11.zzk     // Catch:{ all -> 0x100f }
            if (r1 == 0) goto L_0x0d69
        L_0x0d5d:
            r1 = 0
            com.google.android.gms.measurement.internal.zzan r9 = r11.zza(r1, r1, r1)     // Catch:{ all -> 0x100f }
            java.lang.String r1 = r8.zzd()     // Catch:{ all -> 0x100f }
            r2.put(r1, r9)     // Catch:{ all -> 0x100f }
        L_0x0d69:
            r4.zza((int) r7, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8)     // Catch:{ all -> 0x100f }
            goto L_0x0bee
        L_0x0d6e:
            int r15 = r6.nextInt(r9)     // Catch:{ all -> 0x100f }
            if (r15 != 0) goto L_0x0db0
            r42.zzh()     // Catch:{ all -> 0x100f }
            r43 = r5
            r15 = r6
            long r5 = (long) r9     // Catch:{ all -> 0x100f }
            java.lang.Long r1 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8, (java.lang.String) r10, (java.lang.Object) r1)     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzjh r1 = r8.zzz()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzhz r1 = (com.google.android.gms.internal.measurement.zzhz) r1     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzc r1 = (com.google.android.gms.internal.measurement.zzcd.zzc) r1     // Catch:{ all -> 0x100f }
            r3.add(r1)     // Catch:{ all -> 0x100f }
            boolean r1 = r12.booleanValue()     // Catch:{ all -> 0x100f }
            if (r1 == 0) goto L_0x0d9c
            java.lang.Long r1 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x100f }
            r5 = 0
            com.google.android.gms.measurement.internal.zzan r11 = r11.zza(r5, r1, r5)     // Catch:{ all -> 0x100f }
        L_0x0d9c:
            java.lang.String r1 = r8.zzd()     // Catch:{ all -> 0x100f }
            long r5 = r8.zzf()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzan r5 = r11.zza(r5, r13)     // Catch:{ all -> 0x100f }
            r2.put(r1, r5)     // Catch:{ all -> 0x100f }
            r9 = r2
            r5 = 1
            goto L_0x0e3d
        L_0x0db0:
            r43 = r5
            r15 = r6
            java.lang.Long r5 = r11.zzh     // Catch:{ all -> 0x100f }
            if (r5 == 0) goto L_0x0dc2
            java.lang.Long r5 = r11.zzh     // Catch:{ all -> 0x100f }
            long r5 = r5.longValue()     // Catch:{ all -> 0x100f }
            r17 = r1
            r16 = r2
            goto L_0x0dd7
        L_0x0dc2:
            r5 = r42
            com.google.android.gms.measurement.internal.zzfv r6 = r5.zzk     // Catch:{ all -> 0x100f }
            r6.zzh()     // Catch:{ all -> 0x100f }
            long r5 = r8.zzg()     // Catch:{ all -> 0x100f }
            r17 = r1
            r16 = r2
            r1 = r19
            long r5 = com.google.android.gms.measurement.internal.zzkw.zza((long) r5, (long) r1)     // Catch:{ all -> 0x100f }
        L_0x0dd7:
            int r1 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r1 == 0) goto L_0x0e25
            r42.zzh()     // Catch:{ all -> 0x100f }
            java.lang.String r1 = "_efs"
            r5 = 1
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8, (java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x100f }
            r42.zzh()     // Catch:{ all -> 0x100f }
            long r1 = (long) r9     // Catch:{ all -> 0x100f }
            java.lang.Long r9 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzks.zza((com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8, (java.lang.String) r10, (java.lang.Object) r9)     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzjh r9 = r8.zzz()     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzhz r9 = (com.google.android.gms.internal.measurement.zzhz) r9     // Catch:{ all -> 0x100f }
            com.google.android.gms.internal.measurement.zzcd$zzc r9 = (com.google.android.gms.internal.measurement.zzcd.zzc) r9     // Catch:{ all -> 0x100f }
            r3.add(r9)     // Catch:{ all -> 0x100f }
            boolean r9 = r12.booleanValue()     // Catch:{ all -> 0x100f }
            if (r9 == 0) goto L_0x0e13
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x100f }
            r2 = 1
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x100f }
            r2 = 0
            com.google.android.gms.measurement.internal.zzan r11 = r11.zza(r2, r1, r9)     // Catch:{ all -> 0x100f }
        L_0x0e13:
            java.lang.String r1 = r8.zzd()     // Catch:{ all -> 0x100f }
            long r9 = r8.zzf()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzan r2 = r11.zza(r9, r13)     // Catch:{ all -> 0x100f }
            r9 = r16
            r9.put(r1, r2)     // Catch:{ all -> 0x100f }
            goto L_0x0e3d
        L_0x0e25:
            r9 = r16
            r5 = 1
            boolean r1 = r12.booleanValue()     // Catch:{ all -> 0x100f }
            if (r1 == 0) goto L_0x0e3d
            java.lang.String r1 = r8.zzd()     // Catch:{ all -> 0x100f }
            r2 = r17
            r10 = 0
            com.google.android.gms.measurement.internal.zzan r2 = r11.zza(r2, r10, r10)     // Catch:{ all -> 0x100f }
            r9.put(r1, r2)     // Catch:{ all -> 0x100f }
        L_0x0e3d:
            r4.zza((int) r7, (com.google.android.gms.internal.measurement.zzcd.zzc.zza) r8)     // Catch:{ all -> 0x100f }
        L_0x0e40:
            int r7 = r7 + 1
            r1 = r42
            r5 = r43
            r2 = r9
            r6 = r15
            goto L_0x0b62
        L_0x0e4a:
            r9 = r2
            r43 = r5
            int r1 = r3.size()     // Catch:{ all -> 0x100f }
            int r2 = r4.zzb()     // Catch:{ all -> 0x100f }
            if (r1 >= r2) goto L_0x0e5e
            com.google.android.gms.internal.measurement.zzcd$zzg$zza r1 = r4.zzc()     // Catch:{ all -> 0x100f }
            r1.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzcd.zzc>) r3)     // Catch:{ all -> 0x100f }
        L_0x0e5e:
            java.util.Set r1 = r9.entrySet()     // Catch:{ all -> 0x100f }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x100f }
        L_0x0e66:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x100f }
            if (r2 == 0) goto L_0x0e80
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x100f }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzac r3 = r42.zze()     // Catch:{ all -> 0x100f }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzan r2 = (com.google.android.gms.measurement.internal.zzan) r2     // Catch:{ all -> 0x100f }
            r3.zza((com.google.android.gms.measurement.internal.zzan) r2)     // Catch:{ all -> 0x100f }
            goto L_0x0e66
        L_0x0e80:
            r1 = r43
            goto L_0x0e84
        L_0x0e83:
            r1 = r5
        L_0x0e84:
            com.google.android.gms.internal.measurement.zzcd$zzg r2 = r1.zza     // Catch:{ all -> 0x100f }
            java.lang.String r2 = r2.zzx()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzac r3 = r42.zze()     // Catch:{ all -> 0x100f }
            com.google.android.gms.measurement.internal.zzf r3 = r3.zzb(r2)     // Catch:{ all -> 0x100f }
            if (r3 != 0) goto L_0x0eb0
            r5 = r42
            com.google.android.gms.measurement.internal.zzfv r3 = r5.zzk     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zze()     // Catch:{ all -> 0x102e }
            java.lang.String r6 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r7 = r1.zza     // Catch:{ all -> 0x102e }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x102e }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r7)     // Catch:{ all -> 0x102e }
            r3.zza(r6, r7)     // Catch:{ all -> 0x102e }
            goto L_0x0f0d
        L_0x0eb0:
            r5 = r42
            int r6 = r4.zzb()     // Catch:{ all -> 0x102e }
            if (r6 <= 0) goto L_0x0f0d
            long r6 = r3.zzk()     // Catch:{ all -> 0x102e }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0ec6
            r4.zze((long) r6)     // Catch:{ all -> 0x102e }
            goto L_0x0ec9
        L_0x0ec6:
            r4.zzi()     // Catch:{ all -> 0x102e }
        L_0x0ec9:
            long r8 = r3.zzj()     // Catch:{ all -> 0x102e }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0ed4
            goto L_0x0ed5
        L_0x0ed4:
            r6 = r8
        L_0x0ed5:
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0edd
            r4.zzd((long) r6)     // Catch:{ all -> 0x102e }
            goto L_0x0ee0
        L_0x0edd:
            r4.zzh()     // Catch:{ all -> 0x102e }
        L_0x0ee0:
            r3.zzv()     // Catch:{ all -> 0x102e }
            long r6 = r3.zzs()     // Catch:{ all -> 0x102e }
            int r7 = (int) r6     // Catch:{ all -> 0x102e }
            r4.zzg((int) r7)     // Catch:{ all -> 0x102e }
            long r6 = r4.zzf()     // Catch:{ all -> 0x102e }
            r3.zza((long) r6)     // Catch:{ all -> 0x102e }
            long r6 = r4.zzg()     // Catch:{ all -> 0x102e }
            r3.zzb((long) r6)     // Catch:{ all -> 0x102e }
            java.lang.String r6 = r3.zzad()     // Catch:{ all -> 0x102e }
            if (r6 == 0) goto L_0x0f03
            r4.zzj((java.lang.String) r6)     // Catch:{ all -> 0x102e }
            goto L_0x0f06
        L_0x0f03:
            r4.zzn()     // Catch:{ all -> 0x102e }
        L_0x0f06:
            com.google.android.gms.measurement.internal.zzac r6 = r42.zze()     // Catch:{ all -> 0x102e }
            r6.zza((com.google.android.gms.measurement.internal.zzf) r3)     // Catch:{ all -> 0x102e }
        L_0x0f0d:
            int r3 = r4.zzb()     // Catch:{ all -> 0x102e }
            if (r3 <= 0) goto L_0x0f6e
            com.google.android.gms.measurement.internal.zzfp r3 = r42.zzc()     // Catch:{ all -> 0x102e }
            com.google.android.gms.internal.measurement.zzcd$zzg r6 = r1.zza     // Catch:{ all -> 0x102e }
            java.lang.String r6 = r6.zzx()     // Catch:{ all -> 0x102e }
            com.google.android.gms.internal.measurement.zzca$zzb r3 = r3.zza((java.lang.String) r6)     // Catch:{ all -> 0x102e }
            if (r3 == 0) goto L_0x0f32
            boolean r6 = r3.zza()     // Catch:{ all -> 0x102e }
            if (r6 != 0) goto L_0x0f2a
            goto L_0x0f32
        L_0x0f2a:
            long r6 = r3.zzb()     // Catch:{ all -> 0x102e }
            r4.zzi((long) r6)     // Catch:{ all -> 0x102e }
            goto L_0x0f5d
        L_0x0f32:
            com.google.android.gms.internal.measurement.zzcd$zzg r3 = r1.zza     // Catch:{ all -> 0x102e }
            java.lang.String r3 = r3.zzam()     // Catch:{ all -> 0x102e }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x102e }
            if (r3 == 0) goto L_0x0f44
            r6 = -1
            r4.zzi((long) r6)     // Catch:{ all -> 0x102e }
            goto L_0x0f5d
        L_0x0f44:
            com.google.android.gms.measurement.internal.zzfv r3 = r5.zzk     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zzh()     // Catch:{ all -> 0x102e }
            java.lang.String r6 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzcd$zzg r7 = r1.zza     // Catch:{ all -> 0x102e }
            java.lang.String r7 = r7.zzx()     // Catch:{ all -> 0x102e }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r7)     // Catch:{ all -> 0x102e }
            r3.zza(r6, r7)     // Catch:{ all -> 0x102e }
        L_0x0f5d:
            com.google.android.gms.measurement.internal.zzac r3 = r42.zze()     // Catch:{ all -> 0x102e }
            com.google.android.gms.internal.measurement.zzjh r4 = r4.zzz()     // Catch:{ all -> 0x102e }
            com.google.android.gms.internal.measurement.zzhz r4 = (com.google.android.gms.internal.measurement.zzhz) r4     // Catch:{ all -> 0x102e }
            com.google.android.gms.internal.measurement.zzcd$zzg r4 = (com.google.android.gms.internal.measurement.zzcd.zzg) r4     // Catch:{ all -> 0x102e }
            r13 = r18
            r3.zza((com.google.android.gms.internal.measurement.zzcd.zzg) r4, (boolean) r13)     // Catch:{ all -> 0x102e }
        L_0x0f6e:
            com.google.android.gms.measurement.internal.zzac r3 = r42.zze()     // Catch:{ all -> 0x102e }
            java.util.List<java.lang.Long> r1 = r1.zzb     // Catch:{ all -> 0x102e }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)     // Catch:{ all -> 0x102e }
            r3.zzc()     // Catch:{ all -> 0x102e }
            r3.zzaj()     // Catch:{ all -> 0x102e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x102e }
            java.lang.String r6 = "rowid in ("
            r4.<init>(r6)     // Catch:{ all -> 0x102e }
            r6 = 0
        L_0x0f85:
            int r7 = r1.size()     // Catch:{ all -> 0x102e }
            if (r6 >= r7) goto L_0x0fa2
            if (r6 == 0) goto L_0x0f92
            java.lang.String r7 = ","
            r4.append(r7)     // Catch:{ all -> 0x102e }
        L_0x0f92:
            java.lang.Object r7 = r1.get(r6)     // Catch:{ all -> 0x102e }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x102e }
            long r7 = r7.longValue()     // Catch:{ all -> 0x102e }
            r4.append(r7)     // Catch:{ all -> 0x102e }
            int r6 = r6 + 1
            goto L_0x0f85
        L_0x0fa2:
            java.lang.String r6 = ")"
            r4.append(r6)     // Catch:{ all -> 0x102e }
            android.database.sqlite.SQLiteDatabase r6 = r3.c_()     // Catch:{ all -> 0x102e }
            java.lang.String r7 = "raw_events"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x102e }
            r8 = 0
            int r4 = r6.delete(r7, r4, r8)     // Catch:{ all -> 0x102e }
            int r6 = r1.size()     // Catch:{ all -> 0x102e }
            if (r4 == r6) goto L_0x0fd5
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zze()     // Catch:{ all -> 0x102e }
            java.lang.String r6 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x102e }
            int r1 = r1.size()     // Catch:{ all -> 0x102e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x102e }
            r3.zza(r6, r4, r1)     // Catch:{ all -> 0x102e }
        L_0x0fd5:
            com.google.android.gms.measurement.internal.zzac r1 = r42.zze()     // Catch:{ all -> 0x102e }
            android.database.sqlite.SQLiteDatabase r3 = r1.c_()     // Catch:{ all -> 0x102e }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0fec }
            r7 = 0
            r6[r7] = r2     // Catch:{ SQLiteException -> 0x0fec }
            r7 = 1
            r6[r7] = r2     // Catch:{ SQLiteException -> 0x0fec }
            r3.execSQL(r4, r6)     // Catch:{ SQLiteException -> 0x0fec }
            goto L_0x0fff
        L_0x0fec:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzet r1 = r1.zze()     // Catch:{ all -> 0x102e }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r2)     // Catch:{ all -> 0x102e }
            r1.zza(r4, r2, r3)     // Catch:{ all -> 0x102e }
        L_0x0fff:
            com.google.android.gms.measurement.internal.zzac r1 = r42.zze()     // Catch:{ all -> 0x102e }
            r1.b_()     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzac r1 = r42.zze()
            r1.zzg()
            r1 = 1
            return r1
        L_0x100f:
            r0 = move-exception
            r5 = r42
            goto L_0x1032
        L_0x1013:
            r5 = r1
            com.google.android.gms.measurement.internal.zzac r1 = r42.zze()     // Catch:{ all -> 0x102e }
            r1.b_()     // Catch:{ all -> 0x102e }
            com.google.android.gms.measurement.internal.zzac r1 = r42.zze()
            r1.zzg()
            r1 = 0
            return r1
        L_0x1024:
            r0 = move-exception
            r5 = r1
            goto L_0x0259
        L_0x1028:
            if (r6 == 0) goto L_0x102d
            r6.close()     // Catch:{ all -> 0x102e }
        L_0x102d:
            throw r1     // Catch:{ all -> 0x102e }
        L_0x102e:
            r0 = move-exception
            goto L_0x1032
        L_0x1030:
            r0 = move-exception
            r5 = r1
        L_0x1032:
            r1 = r0
            com.google.android.gms.measurement.internal.zzac r2 = r42.zze()
            r2.zzg()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zza(java.lang.String, long):boolean");
    }

    private final void zza(zzcd.zzg.zza zza2, long j, boolean z) {
        zzkt zzkt;
        String str = z ? "_se" : "_lte";
        zzkt zzc2 = zze().zzc(zza2.zzj(), str);
        if (zzc2 == null || zzc2.zze == null) {
            zzkt = new zzkt(zza2.zzj(), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str, this.zzk.zzl().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzkt = new zzkt(zza2.zzj(), DebugKt.DEBUG_PROPERTY_VALUE_AUTO, str, this.zzk.zzl().currentTimeMillis(), Long.valueOf(((Long) zzc2.zze).longValue() + j));
        }
        zzcd.zzk zzk2 = (zzcd.zzk) ((zzhz) zzcd.zzk.zzj().zza(str).zza(this.zzk.zzl().currentTimeMillis()).zzb(((Long) zzkt.zze).longValue()).zzz());
        boolean z2 = false;
        int zza3 = zzks.zza(zza2, str);
        if (zza3 >= 0) {
            zza2.zza(zza3, zzk2);
            z2 = true;
        }
        if (!z2) {
            zza2.zza(zzk2);
        }
        if (j > 0) {
            zze().zza(zzkt);
            this.zzk.zzq().zzw().zza("Updated engagement user property. scope, value", z ? "session-scoped" : "lifetime", zzkt.zze);
        }
    }

    private final boolean zza(zzcd.zzc.zza zza2, zzcd.zzc.zza zza3) {
        String str;
        Preconditions.checkArgument("_e".equals(zza2.zzd()));
        zzh();
        zzcd.zze zza4 = zzks.zza((zzcd.zzc) ((zzhz) zza2.zzz()), "_sc");
        String str2 = null;
        if (zza4 == null) {
            str = null;
        } else {
            str = zza4.zzd();
        }
        zzh();
        zzcd.zze zza5 = zzks.zza((zzcd.zzc) ((zzhz) zza3.zzz()), "_pc");
        if (zza5 != null) {
            str2 = zza5.zzd();
        }
        if (str2 == null || !str2.equals(str)) {
            return false;
        }
        zzb(zza2, zza3);
        return true;
    }

    private final void zzb(zzcd.zzc.zza zza2, zzcd.zzc.zza zza3) {
        Preconditions.checkArgument("_e".equals(zza2.zzd()));
        zzh();
        zzcd.zze zza4 = zzks.zza((zzcd.zzc) ((zzhz) zza2.zzz()), "_et");
        if (zza4.zze() && zza4.zzf() > 0) {
            long zzf2 = zza4.zzf();
            zzh();
            zzcd.zze zza5 = zzks.zza((zzcd.zzc) ((zzhz) zza3.zzz()), "_et");
            if (zza5 != null && zza5.zzf() > 0) {
                zzf2 += zza5.zzf();
            }
            zzh();
            zzks.zza(zza3, "_et", (Object) Long.valueOf(zzf2));
            zzh();
            zzks.zza(zza2, "_fr", (Object) 1L);
        }
    }

    private static void zza(zzcd.zzc.zza zza2, String str) {
        List<zzcd.zze> zza3 = zza2.zza();
        for (int i = 0; i < zza3.size(); i++) {
            if (str.equals(zza3.get(i).zzb())) {
                zza2.zzb(i);
                return;
            }
        }
    }

    private static void zza(zzcd.zzc.zza zza2, int i, String str) {
        List<zzcd.zze> zza3 = zza2.zza();
        int i2 = 0;
        while (i2 < zza3.size()) {
            if (!"_err".equals(zza3.get(i2).zzb())) {
                i2++;
            } else {
                return;
            }
        }
        zza2.zza((zzcd.zze) ((zzhz) zzcd.zze.zzm().zza("_err").zza(Long.valueOf((long) i).longValue()).zzz())).zza((zzcd.zze) ((zzhz) zzcd.zze.zzm().zza("_ev").zzb(str).zzz()));
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzac zze2;
        zzx();
        zzn();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzs = false;
                zzac();
                throw th2;
            }
        }
        List<Long> list = this.zzw;
        this.zzw = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzk.zzb().zzc.zza(this.zzk.zzl().currentTimeMillis());
                this.zzk.zzb().zzd.zza(0);
                zzab();
                this.zzk.zzq().zzw().zza("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zze().zze();
                try {
                    for (Long next : list) {
                        try {
                            zze2 = zze();
                            long longValue = next.longValue();
                            zze2.zzc();
                            zze2.zzaj();
                            if (zze2.c_().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zze2.zzq().zze().zza("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzx == null || !this.zzx.contains(next)) {
                                throw e2;
                            }
                        }
                    }
                    zze().b_();
                    zze().zzg();
                    this.zzx = null;
                    if (!zzd().zze() || !zzaa()) {
                        this.zzy = -1;
                        zzab();
                    } else {
                        zzo();
                    }
                    this.zzn = 0;
                } catch (Throwable th3) {
                    zze().zzg();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzk.zzq().zze().zza("Database error while trying to delete uploaded bundles", e3);
                this.zzn = this.zzk.zzl().elapsedRealtime();
                this.zzk.zzq().zzw().zza("Disable upload, time", Long.valueOf(this.zzn));
            }
        } else {
            this.zzk.zzq().zzw().zza("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzk.zzb().zzd.zza(this.zzk.zzl().currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    z = false;
                }
            }
            if (z) {
                this.zzk.zzb().zze.zza(this.zzk.zzl().currentTimeMillis());
            }
            zze().zza(list);
            zzab();
        }
        this.zzs = false;
        zzac();
    }

    private final boolean zzaa() {
        zzx();
        zzn();
        return zze().zzx() || !TextUtils.isEmpty(zze().d_());
    }

    private final void zza(zzf zzf2) {
        ArrayMap arrayMap;
        zzx();
        if (!zznt.zzb() || !this.zzk.zza().zze(zzf2.zzc(), zzat.zzbi)) {
            if (TextUtils.isEmpty(zzf2.zze()) && TextUtils.isEmpty(zzf2.zzf())) {
                zza(zzf2.zzc(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
                return;
            }
        } else if (TextUtils.isEmpty(zzf2.zze()) && TextUtils.isEmpty(zzf2.zzg()) && TextUtils.isEmpty(zzf2.zzf())) {
            zza(zzf2.zzc(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
            return;
        }
        String zza2 = this.zzk.zza().zza(zzf2);
        try {
            URL url = new URL(zza2);
            this.zzk.zzq().zzw().zza("Fetching remote configuration", zzf2.zzc());
            zzca.zzb zza3 = zzc().zza(zzf2.zzc());
            String zzb2 = zzc().zzb(zzf2.zzc());
            if (zza3 == null || TextUtils.isEmpty(zzb2)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put(HttpHeaders.IF_MODIFIED_SINCE, zzb2);
                arrayMap = arrayMap2;
            }
            this.zzr = true;
            zzeu zzd2 = zzd();
            String zzc2 = zzf2.zzc();
            zzkn zzkn = new zzkn(this);
            zzd2.zzc();
            zzd2.zzaj();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkn);
            zzd2.zzp().zzc((Runnable) new zzey(zzd2, zzc2, url, (byte[]) null, arrayMap, zzkn));
        } catch (MalformedURLException unused) {
            this.zzk.zzq().zze().zza("Failed to parse config URL. Not fetching. appId", zzer.zza(zzf2.zzc()), zza2);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x013a A[Catch:{ all -> 0x018d, all -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x014a A[Catch:{ all -> 0x018d, all -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0172 A[Catch:{ all -> 0x018d, all -> 0x0196 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0176 A[Catch:{ all -> 0x018d, all -> 0x0196 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.zzx()
            r6.zzn()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x000e
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x0196 }
        L_0x000e:
            com.google.android.gms.measurement.internal.zzfv r1 = r6.zzk     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()     // Catch:{ all -> 0x0196 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x0196 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0196 }
            r1.zza(r2, r3)     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzac r1 = r6.zze()     // Catch:{ all -> 0x0196 }
            r1.zze()     // Catch:{ all -> 0x0196 }
            com.google.android.gms.measurement.internal.zzac r1 = r6.zze()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzf r1 = r1.zzb(r7)     // Catch:{ all -> 0x018d }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            r4 = 1
            if (r8 == r2) goto L_0x003e
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x003e
            if (r8 != r3) goto L_0x0042
        L_0x003e:
            if (r9 != 0) goto L_0x0042
            r2 = 1
            goto L_0x0043
        L_0x0042:
            r2 = 0
        L_0x0043:
            if (r1 != 0) goto L_0x005a
            com.google.android.gms.measurement.internal.zzfv r8 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzq()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zzh()     // Catch:{ all -> 0x018d }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r7)     // Catch:{ all -> 0x018d }
            r8.zza(r9, r7)     // Catch:{ all -> 0x018d }
            goto L_0x0179
        L_0x005a:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00ca
            if (r8 != r5) goto L_0x0061
            goto L_0x00ca
        L_0x0061:
            com.google.android.gms.measurement.internal.zzfv r10 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r10 = r10.zzl()     // Catch:{ all -> 0x018d }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r1.zzi((long) r10)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzac r10 = r6.zze()     // Catch:{ all -> 0x018d }
            r10.zza((com.google.android.gms.measurement.internal.zzf) r1)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfv r10 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzer r10 = r10.zzq()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzet r10 = r10.zzw()     // Catch:{ all -> 0x018d }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x018d }
            r10.zza(r11, r1, r9)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfp r9 = r6.zzc()     // Catch:{ all -> 0x018d }
            r9.zzc(r7)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfv r7 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfd r7 = r7.zzb()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfh r7 = r7.zzd     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfv r9 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r9 = r9.zzl()     // Catch:{ all -> 0x018d }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r7.zza(r9)     // Catch:{ all -> 0x018d }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00ae
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00ad
            goto L_0x00ae
        L_0x00ad:
            r4 = 0
        L_0x00ae:
            if (r4 == 0) goto L_0x00c5
            com.google.android.gms.measurement.internal.zzfv r7 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfd r7 = r7.zzb()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfh r7 = r7.zze     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzfv r8 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r8 = r8.zzl()     // Catch:{ all -> 0x018d }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r7.zza(r8)     // Catch:{ all -> 0x018d }
        L_0x00c5:
            r6.zzab()     // Catch:{ all -> 0x018d }
            goto L_0x0179
        L_0x00ca:
            r9 = 0
            if (r11 == 0) goto L_0x00d6
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x018d }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x018d }
            goto L_0x00d7
        L_0x00d6:
            r11 = r9
        L_0x00d7:
            if (r11 == 0) goto L_0x00e6
            int r2 = r11.size()     // Catch:{ all -> 0x018d }
            if (r2 <= 0) goto L_0x00e6
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x018d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x018d }
            goto L_0x00e7
        L_0x00e6:
            r11 = r9
        L_0x00e7:
            if (r8 == r5) goto L_0x0103
            if (r8 != r3) goto L_0x00ec
            goto L_0x0103
        L_0x00ec:
            com.google.android.gms.measurement.internal.zzfp r9 = r6.zzc()     // Catch:{ all -> 0x018d }
            boolean r9 = r9.zza(r7, r10, r11)     // Catch:{ all -> 0x018d }
            if (r9 != 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x0196 }
            r7.zzg()     // Catch:{ all -> 0x0196 }
            r6.zzr = r0
            r6.zzac()
            return
        L_0x0103:
            com.google.android.gms.measurement.internal.zzfp r11 = r6.zzc()     // Catch:{ all -> 0x018d }
            com.google.android.gms.internal.measurement.zzca$zzb r11 = r11.zza((java.lang.String) r7)     // Catch:{ all -> 0x018d }
            if (r11 != 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzfp r11 = r6.zzc()     // Catch:{ all -> 0x018d }
            boolean r9 = r11.zza(r7, r9, r9)     // Catch:{ all -> 0x018d }
            if (r9 != 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x0196 }
            r7.zzg()     // Catch:{ all -> 0x0196 }
            r6.zzr = r0
            r6.zzac()
            return
        L_0x0124:
            com.google.android.gms.measurement.internal.zzfv r9 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.common.util.Clock r9 = r9.zzl()     // Catch:{ all -> 0x018d }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x018d }
            r1.zzh((long) r2)     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzac r9 = r6.zze()     // Catch:{ all -> 0x018d }
            r9.zza((com.google.android.gms.measurement.internal.zzf) r1)     // Catch:{ all -> 0x018d }
            if (r8 != r5) goto L_0x014a
            com.google.android.gms.measurement.internal.zzfv r8 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzq()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zzj()     // Catch:{ all -> 0x018d }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zza(r9, r7)     // Catch:{ all -> 0x018d }
            goto L_0x0162
        L_0x014a:
            com.google.android.gms.measurement.internal.zzfv r7 = r6.zzk     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzer r7 = r7.zzq()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zzw()     // Catch:{ all -> 0x018d }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x018d }
            int r10 = r10.length     // Catch:{ all -> 0x018d }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x018d }
            r7.zza(r9, r8, r10)     // Catch:{ all -> 0x018d }
        L_0x0162:
            com.google.android.gms.measurement.internal.zzeu r7 = r6.zzd()     // Catch:{ all -> 0x018d }
            boolean r7 = r7.zze()     // Catch:{ all -> 0x018d }
            if (r7 == 0) goto L_0x0176
            boolean r7 = r6.zzaa()     // Catch:{ all -> 0x018d }
            if (r7 == 0) goto L_0x0176
            r6.zzo()     // Catch:{ all -> 0x018d }
            goto L_0x0179
        L_0x0176:
            r6.zzab()     // Catch:{ all -> 0x018d }
        L_0x0179:
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x018d }
            r7.b_()     // Catch:{ all -> 0x018d }
            com.google.android.gms.measurement.internal.zzac r7 = r6.zze()     // Catch:{ all -> 0x0196 }
            r7.zzg()     // Catch:{ all -> 0x0196 }
            r6.zzr = r0
            r6.zzac()
            return
        L_0x018d:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzac r8 = r6.zze()     // Catch:{ all -> 0x0196 }
            r8.zzg()     // Catch:{ all -> 0x0196 }
            throw r7     // Catch:{ all -> 0x0196 }
        L_0x0196:
            r7 = move-exception
            r6.zzr = r0
            r6.zzac()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzab() {
        /*
            r21 = this;
            r0 = r21
            r21.zzx()
            r21.zzn()
            long r1 = r0.zzn
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x004d
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.common.util.Clock r1 = r1.zzl()
            long r1 = r1.elapsedRealtime()
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r7 = r0.zzn
            long r1 = r1 - r7
            long r1 = java.lang.Math.abs(r1)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004b
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()
            java.lang.Long r2 = java.lang.Long.valueOf(r5)
            java.lang.String r3 = "Upload has been suspended. Will update scheduling later in approximately ms"
            r1.zza(r3, r2)
            com.google.android.gms.measurement.internal.zzfb r1 = r21.zzv()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzke r1 = r21.zzw()
            r1.zze()
            return
        L_0x004b:
            r0.zzn = r3
        L_0x004d:
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            boolean r1 = r1.zzaf()
            if (r1 == 0) goto L_0x0255
            boolean r1 = r21.zzaa()
            if (r1 != 0) goto L_0x005d
            goto L_0x0255
        L_0x005d:
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.common.util.Clock r1 = r1.zzl()
            long r1 = r1.currentTimeMillis()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzat.zzz
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r7 = r5.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.measurement.internal.zzac r5 = r21.zze()
            boolean r5 = r5.zzy()
            if (r5 != 0) goto L_0x008f
            com.google.android.gms.measurement.internal.zzac r5 = r21.zze()
            boolean r5 = r5.e_()
            if (r5 == 0) goto L_0x008d
            goto L_0x008f
        L_0x008d:
            r5 = 0
            goto L_0x0090
        L_0x008f:
            r5 = 1
        L_0x0090:
            if (r5 == 0) goto L_0x00cc
            com.google.android.gms.measurement.internal.zzfv r10 = r0.zzk
            com.google.android.gms.measurement.internal.zzy r10 = r10.zza()
            java.lang.String r10 = r10.zzu()
            boolean r11 = android.text.TextUtils.isEmpty(r10)
            if (r11 != 0) goto L_0x00bb
            java.lang.String r11 = ".none."
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x00bb
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzat.zzu
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00dc
        L_0x00bb:
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzat.zzt
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00dc
        L_0x00cc:
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzat.zzs
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
        L_0x00dc:
            com.google.android.gms.measurement.internal.zzfv r12 = r0.zzk
            com.google.android.gms.measurement.internal.zzfd r12 = r12.zzb()
            com.google.android.gms.measurement.internal.zzfh r12 = r12.zzc
            long r12 = r12.zza()
            com.google.android.gms.measurement.internal.zzfv r14 = r0.zzk
            com.google.android.gms.measurement.internal.zzfd r14 = r14.zzb()
            com.google.android.gms.measurement.internal.zzfh r14 = r14.zzd
            long r14 = r14.zza()
            com.google.android.gms.measurement.internal.zzac r16 = r21.zze()
            r17 = r10
            long r9 = r16.zzv()
            com.google.android.gms.measurement.internal.zzac r11 = r21.zze()
            r19 = r7
            long r6 = r11.zzw()
            long r6 = java.lang.Math.max(r9, r6)
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x0113
        L_0x0110:
            r10 = r3
            goto L_0x0188
        L_0x0113:
            long r6 = r6 - r1
            long r6 = java.lang.Math.abs(r6)
            long r6 = r1 - r6
            long r12 = r12 - r1
            long r8 = java.lang.Math.abs(r12)
            long r8 = r1 - r8
            long r14 = r14 - r1
            long r10 = java.lang.Math.abs(r14)
            long r1 = r1 - r10
            long r8 = java.lang.Math.max(r8, r1)
            long r10 = r6 + r19
            if (r5 == 0) goto L_0x0139
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0139
            long r10 = java.lang.Math.min(r6, r8)
            long r10 = r10 + r17
        L_0x0139:
            com.google.android.gms.measurement.internal.zzks r5 = r21.zzh()
            r12 = r17
            boolean r5 = r5.zza((long) r8, (long) r12)
            if (r5 != 0) goto L_0x0147
            long r10 = r8 + r12
        L_0x0147:
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0188
            int r5 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x0188
            r5 = 0
        L_0x0150:
            r6 = 20
            com.google.android.gms.measurement.internal.zzeg<java.lang.Integer> r7 = com.google.android.gms.measurement.internal.zzat.zzab
            r8 = 0
            java.lang.Object r7 = r7.zza(r8)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r9 = 0
            int r7 = java.lang.Math.max(r9, r7)
            int r6 = java.lang.Math.min(r6, r7)
            if (r5 >= r6) goto L_0x0110
            r6 = 1
            long r6 = r6 << r5
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r12 = com.google.android.gms.measurement.internal.zzat.zzaa
            java.lang.Object r12 = r12.zza(r8)
            java.lang.Long r12 = (java.lang.Long) r12
            long r12 = r12.longValue()
            long r12 = java.lang.Math.max(r3, r12)
            long r12 = r12 * r6
            long r10 = r10 + r12
            int r6 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x0185
            goto L_0x0188
        L_0x0185:
            int r5 = r5 + 1
            goto L_0x0150
        L_0x0188:
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x01aa
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()
            java.lang.String r2 = "Next upload time is 0"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzfb r1 = r21.zzv()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzke r1 = r21.zzw()
            r1.zze()
            return
        L_0x01aa:
            com.google.android.gms.measurement.internal.zzeu r1 = r21.zzd()
            boolean r1 = r1.zze()
            if (r1 != 0) goto L_0x01d2
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()
            java.lang.String r2 = "No network"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzfb r1 = r21.zzv()
            r1.zza()
            com.google.android.gms.measurement.internal.zzke r1 = r21.zzw()
            r1.zze()
            return
        L_0x01d2:
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzfd r1 = r1.zzb()
            com.google.android.gms.measurement.internal.zzfh r1 = r1.zze
            long r1 = r1.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzat.zzq
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zzks r7 = r21.zzh()
            boolean r7 = r7.zza((long) r1, (long) r5)
            if (r7 != 0) goto L_0x01fe
            long r1 = r1 + r5
            long r10 = java.lang.Math.max(r10, r1)
        L_0x01fe:
            com.google.android.gms.measurement.internal.zzfb r1 = r21.zzv()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.common.util.Clock r1 = r1.zzl()
            long r1 = r1.currentTimeMillis()
            long r10 = r10 - r1
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x023a
            com.google.android.gms.measurement.internal.zzeg<java.lang.Long> r1 = com.google.android.gms.measurement.internal.zzat.zzv
            r2 = 0
            java.lang.Object r1 = r1.zza(r2)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r10 = java.lang.Math.max(r3, r1)
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzfd r1 = r1.zzb()
            com.google.android.gms.measurement.internal.zzfh r1 = r1.zzc
            com.google.android.gms.measurement.internal.zzfv r2 = r0.zzk
            com.google.android.gms.common.util.Clock r2 = r2.zzl()
            long r2 = r2.currentTimeMillis()
            r1.zza(r2)
        L_0x023a:
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()
            java.lang.Long r2 = java.lang.Long.valueOf(r10)
            java.lang.String r3 = "Upload scheduled in approximately ms"
            r1.zza(r3, r2)
            com.google.android.gms.measurement.internal.zzke r1 = r21.zzw()
            r1.zza(r10)
            return
        L_0x0255:
            com.google.android.gms.measurement.internal.zzfv r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzfb r1 = r21.zzv()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzke r1 = r21.zzw()
            r1.zze()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zzab():void");
    }

    /* access modifiers changed from: package-private */
    public final void zza(Runnable runnable) {
        zzx();
        if (this.zzo == null) {
            this.zzo = new ArrayList();
        }
        this.zzo.add(runnable);
    }

    private final void zzac() {
        zzx();
        if (this.zzr || this.zzs || this.zzt) {
            this.zzk.zzq().zzw().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzr), Boolean.valueOf(this.zzs), Boolean.valueOf(this.zzt));
            return;
        }
        this.zzk.zzq().zzw().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzo;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            this.zzo.clear();
        }
    }

    private final Boolean zzb(zzf zzf2) {
        try {
            if (zzf2.zzm() != -2147483648L) {
                if (zzf2.zzm() == ((long) Wrappers.packageManager(this.zzk.zzm()).getPackageInfo(zzf2.zzc(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzk.zzm()).getPackageInfo(zzf2.zzc(), 0).versionName;
                if (zzf2.zzl() != null && zzf2.zzl().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzr() {
        zzx();
        zzn();
        if (!this.zzm) {
            this.zzm = true;
            if (zzad()) {
                int zza2 = zza(this.zzv);
                int zzae = this.zzk.zzx().zzae();
                zzx();
                if (zza2 > zzae) {
                    this.zzk.zzq().zze().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzae));
                } else if (zza2 >= zzae) {
                } else {
                    if (zza(zzae, this.zzv)) {
                        this.zzk.zzq().zzw().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzae));
                    } else {
                        this.zzk.zzq().zze().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzae));
                    }
                }
            }
        }
    }

    private final boolean zzad() {
        FileLock fileLock;
        zzx();
        if (!this.zzk.zza().zza(zzat.zzbh) || (fileLock = this.zzu) == null || !fileLock.isValid()) {
            try {
                FileChannel channel = new RandomAccessFile(new File(this.zzk.zzm().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                this.zzv = channel;
                FileLock tryLock = channel.tryLock();
                this.zzu = tryLock;
                if (tryLock != null) {
                    this.zzk.zzq().zzw().zza("Storage concurrent access okay");
                    return true;
                }
                this.zzk.zzq().zze().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                this.zzk.zzq().zze().zza("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                this.zzk.zzq().zze().zza("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                this.zzk.zzq().zzh().zza("Storage lock already acquired", e3);
                return false;
            }
        } else {
            this.zzk.zzq().zzw().zza("Storage concurrent access okay");
            return true;
        }
    }

    private final int zza(FileChannel fileChannel) {
        zzx();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzk.zzq().zze().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzk.zzq().zzh().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            this.zzk.zzq().zze().zza("Failed to read from channel", e);
            return 0;
        }
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzx();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzk.zzq().zze().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            if (this.zzk.zza().zza(zzat.zzbr) && Build.VERSION.SDK_INT <= 19) {
                fileChannel.position(0);
            }
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzk.zzq().zze().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzk.zzq().zze().zza("Failed to write to channel", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzn zzn2) {
        if (this.zzw != null) {
            ArrayList arrayList = new ArrayList();
            this.zzx = arrayList;
            arrayList.addAll(this.zzw);
        }
        zzac zze2 = zze();
        String str = zzn2.zza;
        Preconditions.checkNotEmpty(str);
        zze2.zzc();
        zze2.zzaj();
        try {
            SQLiteDatabase c_ = zze2.c_();
            String[] strArr = {str};
            int delete = c_.delete("apps", "app_id=?", strArr) + 0 + c_.delete(OmnitureConstants.CHANNEL, "app_id=?", strArr) + c_.delete("user_attributes", "app_id=?", strArr) + c_.delete("conditional_properties", "app_id=?", strArr) + c_.delete("raw_events", "app_id=?", strArr) + c_.delete("raw_events_metadata", "app_id=?", strArr) + c_.delete("queue", "app_id=?", strArr) + c_.delete("audience_filter_values", "app_id=?", strArr) + c_.delete("main_event_params", "app_id=?", strArr) + c_.delete("default_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zze2.zzq().zzw().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zze2.zzq().zze().zza("Error resetting analytics data. appId, error", zzer.zza(str), e);
        }
        if (zzn2.zzh) {
            zzb(zzn2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzkr zzkr, zzn zzn2) {
        zzx();
        zzn();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
                return;
            }
            int zzb2 = this.zzk.zzh().zzb(zzkr.zza);
            if (zzb2 != 0) {
                this.zzk.zzh();
                this.zzk.zzh().zza(this.zzaa, zzn2.zza, zzb2, "_ev", zzkw.zza(zzkr.zza, 24, true), zzkr.zza != null ? zzkr.zza.length() : 0);
                return;
            }
            int zzb3 = this.zzk.zzh().zzb(zzkr.zza, zzkr.zza());
            if (zzb3 != 0) {
                this.zzk.zzh();
                String zza2 = zzkw.zza(zzkr.zza, 24, true);
                Object zza3 = zzkr.zza();
                this.zzk.zzh().zza(this.zzaa, zzn2.zza, zzb3, "_ev", zza2, (zza3 == null || (!(zza3 instanceof String) && !(zza3 instanceof CharSequence))) ? 0 : String.valueOf(zza3).length());
                return;
            }
            Object zzc2 = this.zzk.zzh().zzc(zzkr.zza, zzkr.zza());
            if (zzc2 != null) {
                if ("_sid".equals(zzkr.zza)) {
                    long j = zzkr.zzb;
                    String str = zzkr.zze;
                    long j2 = 0;
                    zzkt zzc3 = zze().zzc(zzn2.zza, "_sno");
                    if (zzc3 == null || !(zzc3.zze instanceof Long)) {
                        if (zzc3 != null) {
                            this.zzk.zzq().zzh().zza("Retrieved last session number from database does not contain a valid (long) value", zzc3.zze);
                        }
                        zzan zza4 = zze().zza(zzn2.zza, "_s");
                        if (zza4 != null) {
                            j2 = zza4.zzc;
                            this.zzk.zzq().zzw().zza("Backfill the session number. Last used session number", Long.valueOf(j2));
                        }
                    } else {
                        j2 = ((Long) zzc3.zze).longValue();
                    }
                    zza(new zzkr("_sno", j, Long.valueOf(j2 + 1), str), zzn2);
                }
                zzkt zzkt = new zzkt(zzn2.zza, zzkr.zze, zzkr.zza, zzkr.zzb, zzc2);
                this.zzk.zzq().zzw().zza("Setting user property", this.zzk.zzi().zzc(zzkt.zzc), zzc2);
                zze().zze();
                try {
                    zzc(zzn2);
                    boolean zza5 = zze().zza(zzkt);
                    zze().b_();
                    if (!zza5) {
                        this.zzk.zzq().zze().zza("Too many unique user properties are set. Ignoring user property", this.zzk.zzi().zzc(zzkt.zzc), zzkt.zze);
                        this.zzk.zzh().zza(this.zzaa, zzn2.zza, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zze().zzg();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzkr zzkr, zzn zzn2) {
        zzx();
        zzn();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
            } else if (!"_npa".equals(zzkr.zza) || zzn2.zzs == null) {
                this.zzk.zzq().zzv().zza("Removing user property", this.zzk.zzi().zzc(zzkr.zza));
                zze().zze();
                try {
                    zzc(zzn2);
                    zze().zzb(zzn2.zza, zzkr.zza);
                    zze().b_();
                    this.zzk.zzq().zzv().zza("User property removed", this.zzk.zzi().zzc(zzkr.zza));
                } finally {
                    zze().zzg();
                }
            } else {
                this.zzk.zzq().zzv().zza("Falling back to manifest metadata value for ad personalization");
                zza(new zzkr("_npa", this.zzk.zzl().currentTimeMillis(), Long.valueOf(zzn2.zzs.booleanValue() ? 1 : 0), DebugKt.DEBUG_PROPERTY_VALUE_AUTO), zzn2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzkj zzkj) {
        this.zzp++;
    }

    /* access modifiers changed from: package-private */
    public final void zzs() {
        this.zzq++;
    }

    /* access modifiers changed from: package-private */
    public final zzfv zzu() {
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0483 A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011b A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01d2 A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0206 A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0208 A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x020c A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x022f A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0235 A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0242 A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0255 A[Catch:{ SQLiteException -> 0x01bd, all -> 0x04af }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.measurement.internal.zzn r22) {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            java.lang.String r3 = "_sysu"
            java.lang.String r4 = "_sys"
            java.lang.String r5 = "_pfo"
            java.lang.String r6 = "_uwa"
            java.lang.String r0 = "app_id=?"
            r21.zzx()
            r21.zzn()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r22)
            java.lang.String r7 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            boolean r7 = r21.zze(r22)
            if (r7 != 0) goto L_0x0023
            return
        L_0x0023:
            com.google.android.gms.measurement.internal.zzac r7 = r21.zze()
            java.lang.String r8 = r2.zza
            com.google.android.gms.measurement.internal.zzf r7 = r7.zzb(r8)
            r8 = 0
            if (r7 == 0) goto L_0x0056
            java.lang.String r10 = r7.zze()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 == 0) goto L_0x0056
            java.lang.String r10 = r2.zzb
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x0056
            r7.zzh((long) r8)
            com.google.android.gms.measurement.internal.zzac r10 = r21.zze()
            r10.zza((com.google.android.gms.measurement.internal.zzf) r7)
            com.google.android.gms.measurement.internal.zzfp r7 = r21.zzc()
            java.lang.String r10 = r2.zza
            r7.zzd(r10)
        L_0x0056:
            boolean r7 = r2.zzh
            if (r7 != 0) goto L_0x005e
            r21.zzc(r22)
            return
        L_0x005e:
            long r10 = r2.zzm
            int r7 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk
            com.google.android.gms.common.util.Clock r7 = r7.zzl()
            long r10 = r7.currentTimeMillis()
        L_0x006e:
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk
            com.google.android.gms.measurement.internal.zzal r7 = r7.zzw()
            r7.zzh()
            int r7 = r2.zzn
            r15 = 1
            if (r7 == 0) goto L_0x0098
            if (r7 == r15) goto L_0x0098
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzk
            com.google.android.gms.measurement.internal.zzer r12 = r12.zzq()
            com.google.android.gms.measurement.internal.zzet r12 = r12.zzh()
            java.lang.String r13 = r2.zza
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r13)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.String r14 = "Incorrect app type, assuming installed app. appId, appType"
            r12.zza(r14, r13, r7)
            r7 = 0
        L_0x0098:
            com.google.android.gms.measurement.internal.zzac r12 = r21.zze()
            r12.zze()
            com.google.android.gms.measurement.internal.zzac r12 = r21.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x04af }
            java.lang.String r14 = "_npa"
            com.google.android.gms.measurement.internal.zzkt r14 = r12.zzc(r13, r14)     // Catch:{ all -> 0x04af }
            if (r14 == 0) goto L_0x00bc
            java.lang.String r12 = "auto"
            java.lang.String r13 = r14.zzb     // Catch:{ all -> 0x04af }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x04af }
            if (r12 == 0) goto L_0x00b8
            goto L_0x00bc
        L_0x00b8:
            r18 = r3
            r3 = 1
            goto L_0x010f
        L_0x00bc:
            java.lang.Boolean r12 = r2.zzs     // Catch:{ all -> 0x04af }
            if (r12 == 0) goto L_0x00f9
            com.google.android.gms.measurement.internal.zzkr r13 = new com.google.android.gms.measurement.internal.zzkr     // Catch:{ all -> 0x04af }
            java.lang.String r18 = "_npa"
            java.lang.Boolean r12 = r2.zzs     // Catch:{ all -> 0x04af }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x04af }
            if (r12 == 0) goto L_0x00cf
            r19 = 1
            goto L_0x00d1
        L_0x00cf:
            r19 = r8
        L_0x00d1:
            java.lang.Long r19 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x04af }
            java.lang.String r20 = "auto"
            r8 = 1
            r12 = r13
            r8 = r13
            r13 = r18
            r18 = r3
            r9 = r14
            r3 = 1
            r14 = r10
            r16 = r19
            r17 = r20
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x04af }
            if (r9 == 0) goto L_0x00f5
            java.lang.Object r9 = r9.zze     // Catch:{ all -> 0x04af }
            java.lang.Long r12 = r8.zzc     // Catch:{ all -> 0x04af }
            boolean r9 = r9.equals(r12)     // Catch:{ all -> 0x04af }
            if (r9 != 0) goto L_0x010f
        L_0x00f5:
            r1.zza((com.google.android.gms.measurement.internal.zzkr) r8, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
            goto L_0x010f
        L_0x00f9:
            r18 = r3
            r9 = r14
            r3 = 1
            if (r9 == 0) goto L_0x010f
            com.google.android.gms.measurement.internal.zzkr r8 = new com.google.android.gms.measurement.internal.zzkr     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "_npa"
            r16 = 0
            java.lang.String r17 = "auto"
            r12 = r8
            r14 = r10
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x04af }
            r1.zzb((com.google.android.gms.measurement.internal.zzkr) r8, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
        L_0x010f:
            com.google.android.gms.measurement.internal.zzac r8 = r21.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzf r8 = r8.zzb(r9)     // Catch:{ all -> 0x04af }
            if (r8 == 0) goto L_0x01d0
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzk     // Catch:{ all -> 0x04af }
            r12.zzh()     // Catch:{ all -> 0x04af }
            java.lang.String r12 = r2.zzb     // Catch:{ all -> 0x04af }
            java.lang.String r13 = r8.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r14 = r2.zzr     // Catch:{ all -> 0x04af }
            java.lang.String r15 = r8.zzf()     // Catch:{ all -> 0x04af }
            boolean r12 = com.google.android.gms.measurement.internal.zzkw.zza((java.lang.String) r12, (java.lang.String) r13, (java.lang.String) r14, (java.lang.String) r15)     // Catch:{ all -> 0x04af }
            if (r12 == 0) goto L_0x01d0
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzer r12 = r12.zzq()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzet r12 = r12.zzh()     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r14 = r8.zzc()     // Catch:{ all -> 0x04af }
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r14)     // Catch:{ all -> 0x04af }
            r12.zza(r13, r14)     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzac r12 = r21.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r8 = r8.zzc()     // Catch:{ all -> 0x04af }
            r12.zzaj()     // Catch:{ all -> 0x04af }
            r12.zzc()     // Catch:{ all -> 0x04af }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)     // Catch:{ all -> 0x04af }
            android.database.sqlite.SQLiteDatabase r13 = r12.c_()     // Catch:{ SQLiteException -> 0x01bd }
            java.lang.String[] r14 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x01bd }
            r15 = 0
            r14[r15] = r8     // Catch:{ SQLiteException -> 0x01bd }
            java.lang.String r9 = "events"
            int r9 = r13.delete(r9, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "user_attributes"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "conditional_properties"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "apps"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "raw_events"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "raw_events_metadata"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "event_filters"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "property_filters"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "audience_filter_values"
            int r15 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r15
            java.lang.String r15 = "consent_settings"
            int r0 = r13.delete(r15, r0, r14)     // Catch:{ SQLiteException -> 0x01bd }
            int r9 = r9 + r0
            if (r9 <= 0) goto L_0x01cf
            com.google.android.gms.measurement.internal.zzer r0 = r12.zzq()     // Catch:{ SQLiteException -> 0x01bd }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzw()     // Catch:{ SQLiteException -> 0x01bd }
            java.lang.String r13 = "Deleted application data. app, records"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ SQLiteException -> 0x01bd }
            r0.zza(r13, r8, r9)     // Catch:{ SQLiteException -> 0x01bd }
            goto L_0x01cf
        L_0x01bd:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzer r9 = r12.zzq()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzet r9 = r9.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r12 = "Error deleting application data. appId, error"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r8)     // Catch:{ all -> 0x04af }
            r9.zza(r12, r8, r0)     // Catch:{ all -> 0x04af }
        L_0x01cf:
            r8 = 0
        L_0x01d0:
            if (r8 == 0) goto L_0x022f
            long r12 = r8.zzm()     // Catch:{ all -> 0x04af }
            r14 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x01ea
            long r12 = r8.zzm()     // Catch:{ all -> 0x04af }
            r9 = r4
            long r3 = r2.zzj     // Catch:{ all -> 0x04af }
            int r0 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r0 == 0) goto L_0x01eb
            r0 = 1
            goto L_0x01ec
        L_0x01ea:
            r9 = r4
        L_0x01eb:
            r0 = 0
        L_0x01ec:
            long r3 = r8.zzm()     // Catch:{ all -> 0x04af }
            int r12 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0208
            java.lang.String r3 = r8.zzl()     // Catch:{ all -> 0x04af }
            if (r3 == 0) goto L_0x0208
            java.lang.String r3 = r8.zzl()     // Catch:{ all -> 0x04af }
            java.lang.String r4 = r2.zzc     // Catch:{ all -> 0x04af }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x04af }
            if (r3 != 0) goto L_0x0208
            r14 = 1
            goto L_0x0209
        L_0x0208:
            r14 = 0
        L_0x0209:
            r0 = r0 | r14
            if (r0 == 0) goto L_0x0230
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x04af }
            r0.<init>()     // Catch:{ all -> 0x04af }
            java.lang.String r3 = "_pv"
            java.lang.String r4 = r8.zzl()     // Catch:{ all -> 0x04af }
            r0.putString(r3, r4)     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzar r3 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "_au"
            com.google.android.gms.measurement.internal.zzam r14 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x04af }
            r14.<init>(r0)     // Catch:{ all -> 0x04af }
            java.lang.String r15 = "auto"
            r12 = r3
            r16 = r10
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x04af }
            r1.zza((com.google.android.gms.measurement.internal.zzar) r3, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
            goto L_0x0230
        L_0x022f:
            r9 = r4
        L_0x0230:
            r21.zzc(r22)     // Catch:{ all -> 0x04af }
            if (r7 != 0) goto L_0x0242
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x04af }
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzan r0 = r0.zza((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x04af }
            goto L_0x0253
        L_0x0242:
            r3 = 1
            if (r7 != r3) goto L_0x0252
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x04af }
            java.lang.String r4 = "_v"
            com.google.android.gms.measurement.internal.zzan r0 = r0.zza((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x04af }
            goto L_0x0253
        L_0x0252:
            r0 = 0
        L_0x0253:
            if (r0 != 0) goto L_0x0483
            r3 = 3600000(0x36ee80, double:1.7786363E-317)
            long r12 = r10 / r3
            r14 = 1
            long r12 = r12 + r14
            long r12 = r12 * r3
            java.lang.String r0 = "_dac"
            java.lang.String r3 = "_r"
            java.lang.String r4 = "_c"
            java.lang.String r8 = "_et"
            if (r7 != 0) goto L_0x03e1
            com.google.android.gms.measurement.internal.zzkr r7 = new com.google.android.gms.measurement.internal.zzkr     // Catch:{ all -> 0x04af }
            java.lang.String r14 = "_fot"
            java.lang.Long r16 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x04af }
            java.lang.String r17 = "auto"
            r12 = r7
            r13 = r14
            r14 = r10
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x04af }
            r1.zza((com.google.android.gms.measurement.internal.zzkr) r7, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
            r21.zzx()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzfv r7 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzfi r7 = r7.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x04af }
            r7.zza(r12)     // Catch:{ all -> 0x04af }
            r21.zzx()     // Catch:{ all -> 0x04af }
            r21.zzn()     // Catch:{ all -> 0x04af }
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ all -> 0x04af }
            r7.<init>()     // Catch:{ all -> 0x04af }
            r12 = 1
            r7.putLong(r4, r12)     // Catch:{ all -> 0x04af }
            r7.putLong(r3, r12)     // Catch:{ all -> 0x04af }
            r3 = 0
            r7.putLong(r6, r3)     // Catch:{ all -> 0x04af }
            r7.putLong(r5, r3)     // Catch:{ all -> 0x04af }
            r7.putLong(r9, r3)     // Catch:{ all -> 0x04af }
            r14 = r18
            r7.putLong(r14, r3)     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zza()     // Catch:{ all -> 0x04af }
            java.lang.String r4 = r2.zza     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzas     // Catch:{ all -> 0x04af }
            boolean r3 = r3.zze(r4, r12)     // Catch:{ all -> 0x04af }
            if (r3 == 0) goto L_0x02c3
            r3 = 1
            r7.putLong(r8, r3)     // Catch:{ all -> 0x04af }
            goto L_0x02c5
        L_0x02c3:
            r3 = 1
        L_0x02c5:
            boolean r12 = r2.zzq     // Catch:{ all -> 0x04af }
            if (r12 == 0) goto L_0x02cc
            r7.putLong(r0, r3)     // Catch:{ all -> 0x04af }
        L_0x02cc:
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x04af }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x04af }
            r0.zzc()     // Catch:{ all -> 0x04af }
            r0.zzaj()     // Catch:{ all -> 0x04af }
            java.lang.String r4 = "first_open_count"
            long r3 = r0.zzh(r3, r4)     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x04af }
            android.content.Context r0 = r0.zzm()     // Catch:{ all -> 0x04af }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x04af }
            if (r0 != 0) goto L_0x0306
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzq()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r6 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x04af }
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r9)     // Catch:{ all -> 0x04af }
            r0.zza(r6, r9)     // Catch:{ all -> 0x04af }
        L_0x0302:
            r12 = 0
            goto L_0x03c5
        L_0x0306:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ NameNotFoundException -> 0x0318 }
            android.content.Context r0 = r0.zzm()     // Catch:{ NameNotFoundException -> 0x0318 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0318 }
            java.lang.String r12 = r2.zza     // Catch:{ NameNotFoundException -> 0x0318 }
            r13 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r12, r13)     // Catch:{ NameNotFoundException -> 0x0318 }
            goto L_0x032f
        L_0x0318:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzer r12 = r12.zzq()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzet r12 = r12.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r15 = r2.zza     // Catch:{ all -> 0x04af }
            java.lang.Object r15 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r15)     // Catch:{ all -> 0x04af }
            r12.zza(r13, r15, r0)     // Catch:{ all -> 0x04af }
            r0 = 0
        L_0x032f:
            if (r0 == 0) goto L_0x0381
            long r12 = r0.firstInstallTime     // Catch:{ all -> 0x04af }
            r15 = 0
            int r17 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r17 == 0) goto L_0x0381
            long r12 = r0.firstInstallTime     // Catch:{ all -> 0x04af }
            r18 = r14
            long r14 = r0.lastUpdateTime     // Catch:{ all -> 0x04af }
            int r0 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x0364
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzy r0 = r0.zza()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzat.zzbm     // Catch:{ all -> 0x04af }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r12)     // Catch:{ all -> 0x04af }
            if (r0 == 0) goto L_0x035d
            r12 = 0
            int r0 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r0 != 0) goto L_0x0362
            r12 = 1
            r7.putLong(r6, r12)     // Catch:{ all -> 0x04af }
            goto L_0x0362
        L_0x035d:
            r12 = 1
            r7.putLong(r6, r12)     // Catch:{ all -> 0x04af }
        L_0x0362:
            r14 = 0
            goto L_0x0365
        L_0x0364:
            r14 = 1
        L_0x0365:
            com.google.android.gms.measurement.internal.zzkr r0 = new com.google.android.gms.measurement.internal.zzkr     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "_fi"
            if (r14 == 0) goto L_0x036e
            r14 = 1
            goto L_0x0370
        L_0x036e:
            r14 = 0
        L_0x0370:
            java.lang.Long r16 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x04af }
            java.lang.String r17 = "auto"
            r12 = r0
            r6 = r18
            r14 = r10
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x04af }
            r1.zza((com.google.android.gms.measurement.internal.zzkr) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
            goto L_0x0382
        L_0x0381:
            r6 = r14
        L_0x0382:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ NameNotFoundException -> 0x0394 }
            android.content.Context r0 = r0.zzm()     // Catch:{ NameNotFoundException -> 0x0394 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0394 }
            java.lang.String r12 = r2.zza     // Catch:{ NameNotFoundException -> 0x0394 }
            r13 = 0
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r12, r13)     // Catch:{ NameNotFoundException -> 0x0394 }
            goto L_0x03ab
        L_0x0394:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfv r12 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzer r12 = r12.zzq()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzet r12 = r12.zze()     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r14 = r2.zza     // Catch:{ all -> 0x04af }
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r14)     // Catch:{ all -> 0x04af }
            r12.zza(r13, r14, r0)     // Catch:{ all -> 0x04af }
            r0 = 0
        L_0x03ab:
            if (r0 == 0) goto L_0x0302
            int r12 = r0.flags     // Catch:{ all -> 0x04af }
            r13 = 1
            r12 = r12 & r13
            if (r12 == 0) goto L_0x03b8
            r12 = 1
            r7.putLong(r9, r12)     // Catch:{ all -> 0x04af }
        L_0x03b8:
            int r0 = r0.flags     // Catch:{ all -> 0x04af }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0302
            r12 = 1
            r7.putLong(r6, r12)     // Catch:{ all -> 0x04af }
            goto L_0x0302
        L_0x03c5:
            int r0 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r0 < 0) goto L_0x03cc
            r7.putLong(r5, r3)     // Catch:{ all -> 0x04af }
        L_0x03cc:
            com.google.android.gms.measurement.internal.zzar r0 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "_f"
            com.google.android.gms.measurement.internal.zzam r14 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x04af }
            r14.<init>(r7)     // Catch:{ all -> 0x04af }
            java.lang.String r15 = "auto"
            r12 = r0
            r16 = r10
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x04af }
            r1.zzb((com.google.android.gms.measurement.internal.zzar) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
            goto L_0x043d
        L_0x03e1:
            r5 = 1
            if (r7 != r5) goto L_0x043d
            com.google.android.gms.measurement.internal.zzkr r5 = new com.google.android.gms.measurement.internal.zzkr     // Catch:{ all -> 0x04af }
            java.lang.String r6 = "_fvt"
            java.lang.Long r16 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x04af }
            java.lang.String r17 = "auto"
            r12 = r5
            r13 = r6
            r14 = r10
            r12.<init>(r13, r14, r16, r17)     // Catch:{ all -> 0x04af }
            r1.zza((com.google.android.gms.measurement.internal.zzkr) r5, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
            r21.zzx()     // Catch:{ all -> 0x04af }
            r21.zzn()     // Catch:{ all -> 0x04af }
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x04af }
            r5.<init>()     // Catch:{ all -> 0x04af }
            r6 = 1
            r5.putLong(r4, r6)     // Catch:{ all -> 0x04af }
            r5.putLong(r3, r6)     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zza()     // Catch:{ all -> 0x04af }
            java.lang.String r4 = r2.zza     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzat.zzas     // Catch:{ all -> 0x04af }
            boolean r3 = r3.zze(r4, r6)     // Catch:{ all -> 0x04af }
            if (r3 == 0) goto L_0x0420
            r3 = 1
            r5.putLong(r8, r3)     // Catch:{ all -> 0x04af }
            goto L_0x0422
        L_0x0420:
            r3 = 1
        L_0x0422:
            boolean r6 = r2.zzq     // Catch:{ all -> 0x04af }
            if (r6 == 0) goto L_0x0429
            r5.putLong(r0, r3)     // Catch:{ all -> 0x04af }
        L_0x0429:
            com.google.android.gms.measurement.internal.zzar r0 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "_v"
            com.google.android.gms.measurement.internal.zzam r14 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x04af }
            r14.<init>(r5)     // Catch:{ all -> 0x04af }
            java.lang.String r15 = "auto"
            r12 = r0
            r16 = r10
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x04af }
            r1.zzb((com.google.android.gms.measurement.internal.zzar) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
        L_0x043d:
            com.google.android.gms.measurement.internal.zzfv r0 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzy r0 = r0.zza()     // Catch:{ all -> 0x04af }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzat.zzat     // Catch:{ all -> 0x04af }
            boolean r0 = r0.zze(r3, r4)     // Catch:{ all -> 0x04af }
            if (r0 != 0) goto L_0x04a0
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x04af }
            r0.<init>()     // Catch:{ all -> 0x04af }
            r3 = 1
            r0.putLong(r8, r3)     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzfv r3 = r1.zzk     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zza()     // Catch:{ all -> 0x04af }
            java.lang.String r4 = r2.zza     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzat.zzas     // Catch:{ all -> 0x04af }
            boolean r3 = r3.zze(r4, r5)     // Catch:{ all -> 0x04af }
            if (r3 == 0) goto L_0x046e
            java.lang.String r3 = "_fr"
            r4 = 1
            r0.putLong(r3, r4)     // Catch:{ all -> 0x04af }
        L_0x046e:
            com.google.android.gms.measurement.internal.zzar r3 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "_e"
            com.google.android.gms.measurement.internal.zzam r14 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x04af }
            r14.<init>(r0)     // Catch:{ all -> 0x04af }
            java.lang.String r15 = "auto"
            r12 = r3
            r16 = r10
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x04af }
            r1.zzb((com.google.android.gms.measurement.internal.zzar) r3, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
            goto L_0x04a0
        L_0x0483:
            boolean r0 = r2.zzi     // Catch:{ all -> 0x04af }
            if (r0 == 0) goto L_0x04a0
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x04af }
            r0.<init>()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzar r3 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ all -> 0x04af }
            java.lang.String r13 = "_cd"
            com.google.android.gms.measurement.internal.zzam r14 = new com.google.android.gms.measurement.internal.zzam     // Catch:{ all -> 0x04af }
            r14.<init>(r0)     // Catch:{ all -> 0x04af }
            java.lang.String r15 = "auto"
            r12 = r3
            r16 = r10
            r12.<init>(r13, r14, r15, r16)     // Catch:{ all -> 0x04af }
            r1.zzb((com.google.android.gms.measurement.internal.zzar) r3, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04af }
        L_0x04a0:
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()     // Catch:{ all -> 0x04af }
            r0.b_()     // Catch:{ all -> 0x04af }
            com.google.android.gms.measurement.internal.zzac r0 = r21.zze()
            r0.zzg()
            return
        L_0x04af:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzac r2 = r21.zze()
            r2.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zzb(com.google.android.gms.measurement.internal.zzn):void");
    }

    private final zzn zzb(String str) {
        String str2 = str;
        zzf zzb2 = zze().zzb(str2);
        if (zzb2 == null || TextUtils.isEmpty(zzb2.zzl())) {
            this.zzk.zzq().zzv().zza("No app data available; dropping", str2);
            return null;
        }
        Boolean zzb3 = zzb(zzb2);
        if (zzb3 == null || zzb3.booleanValue()) {
            return new zzn(str, zzb2.zze(), zzb2.zzl(), zzb2.zzm(), zzb2.zzn(), zzb2.zzo(), zzb2.zzp(), (String) null, zzb2.zzr(), false, zzb2.zzi(), zzb2.zzae(), 0, 0, zzb2.zzaf(), zzb2.zzag(), false, zzb2.zzf(), zzb2.zzah(), zzb2.zzq(), zzb2.zzai(), (!zznt.zzb() || !this.zzk.zza().zze(str2, zzat.zzbi)) ? null : zzb2.zzg(), (!zzmj.zzb() || !this.zzk.zza().zza(zzat.zzci)) ? "" : zza(str).zza());
        }
        this.zzk.zzq().zze().zza("App version does not match; dropping. appId", zzer.zza(str));
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzw zzw2) {
        zzn zzb2 = zzb(zzw2.zza);
        if (zzb2 != null) {
            zza(zzw2, zzb2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzw zzw2, zzn zzn2) {
        Preconditions.checkNotNull(zzw2);
        Preconditions.checkNotEmpty(zzw2.zza);
        Preconditions.checkNotNull(zzw2.zzb);
        Preconditions.checkNotNull(zzw2.zzc);
        Preconditions.checkNotEmpty(zzw2.zzc.zza);
        zzx();
        zzn();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
                return;
            }
            zzw zzw3 = new zzw(zzw2);
            boolean z = false;
            zzw3.zze = false;
            zze().zze();
            try {
                zzw zzd2 = zze().zzd(zzw3.zza, zzw3.zzc.zza);
                if (zzd2 != null && !zzd2.zzb.equals(zzw3.zzb)) {
                    this.zzk.zzq().zzh().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzk.zzi().zzc(zzw3.zzc.zza), zzw3.zzb, zzd2.zzb);
                }
                if (zzd2 != null && zzd2.zze) {
                    zzw3.zzb = zzd2.zzb;
                    zzw3.zzd = zzd2.zzd;
                    zzw3.zzh = zzd2.zzh;
                    zzw3.zzf = zzd2.zzf;
                    zzw3.zzi = zzd2.zzi;
                    zzw3.zze = zzd2.zze;
                    zzw3.zzc = new zzkr(zzw3.zzc.zza, zzd2.zzc.zzb, zzw3.zzc.zza(), zzd2.zzc.zze);
                } else if (TextUtils.isEmpty(zzw3.zzf)) {
                    zzw3.zzc = new zzkr(zzw3.zzc.zza, zzw3.zzd, zzw3.zzc.zza(), zzw3.zzc.zze);
                    zzw3.zze = true;
                    z = true;
                }
                if (zzw3.zze) {
                    zzkr zzkr = zzw3.zzc;
                    zzkt zzkt = new zzkt(zzw3.zza, zzw3.zzb, zzkr.zza, zzkr.zzb, zzkr.zza());
                    if (zze().zza(zzkt)) {
                        this.zzk.zzq().zzv().zza("User property updated immediately", zzw3.zza, this.zzk.zzi().zzc(zzkt.zzc), zzkt.zze);
                    } else {
                        this.zzk.zzq().zze().zza("(2)Too many active user properties, ignoring", zzer.zza(zzw3.zza), this.zzk.zzi().zzc(zzkt.zzc), zzkt.zze);
                    }
                    if (z && zzw3.zzi != null) {
                        zzc(new zzar(zzw3.zzi, zzw3.zzd), zzn2);
                    }
                }
                if (zze().zza(zzw3)) {
                    this.zzk.zzq().zzv().zza("Conditional property added", zzw3.zza, this.zzk.zzi().zzc(zzw3.zzc.zza), zzw3.zzc.zza());
                } else {
                    this.zzk.zzq().zze().zza("Too many conditional properties, ignoring", zzer.zza(zzw3.zza), this.zzk.zzi().zzc(zzw3.zzc.zza), zzw3.zzc.zza());
                }
                zze().b_();
            } finally {
                zze().zzg();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzw zzw2) {
        zzn zzb2 = zzb(zzw2.zza);
        if (zzb2 != null) {
            zzb(zzw2, zzb2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzw zzw2, zzn zzn2) {
        Preconditions.checkNotNull(zzw2);
        Preconditions.checkNotEmpty(zzw2.zza);
        Preconditions.checkNotNull(zzw2.zzc);
        Preconditions.checkNotEmpty(zzw2.zzc.zza);
        zzx();
        zzn();
        if (zze(zzn2)) {
            if (!zzn2.zzh) {
                zzc(zzn2);
                return;
            }
            zze().zze();
            try {
                zzc(zzn2);
                zzw zzd2 = zze().zzd(zzw2.zza, zzw2.zzc.zza);
                if (zzd2 != null) {
                    this.zzk.zzq().zzv().zza("Removing conditional user property", zzw2.zza, this.zzk.zzi().zzc(zzw2.zzc.zza));
                    zze().zze(zzw2.zza, zzw2.zzc.zza);
                    if (zzd2.zze) {
                        zze().zzb(zzw2.zza, zzw2.zzc.zza);
                    }
                    if (zzw2.zzk != null) {
                        Bundle bundle = null;
                        if (zzw2.zzk.zzb != null) {
                            bundle = zzw2.zzk.zzb.zzb();
                        }
                        zzc(this.zzk.zzh().zza(zzw2.zza, zzw2.zzk.zza, bundle, zzd2.zzb, zzw2.zzk.zzd, true, false, zzlq.zzb() && this.zzk.zza().zza(zzat.zzcl)), zzn2);
                    }
                } else {
                    this.zzk.zzq().zzh().zza("Conditional user property doesn't exist", zzer.zza(zzw2.zza), this.zzk.zzi().zzc(zzw2.zzc.zza));
                }
                zze().b_();
            } finally {
                zze().zzg();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01d9  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.measurement.internal.zzf zza(com.google.android.gms.measurement.internal.zzn r9, com.google.android.gms.measurement.internal.zzf r10, java.lang.String r11) {
        /*
            r8 = this;
            com.google.android.gms.measurement.internal.zzad r0 = com.google.android.gms.measurement.internal.zzad.zza
            boolean r1 = com.google.android.gms.internal.measurement.zzmj.zzb()
            if (r1 == 0) goto L_0x0026
            com.google.android.gms.measurement.internal.zzfv r1 = r8.zzk
            com.google.android.gms.measurement.internal.zzy r1 = r1.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzat.zzci
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r2)
            if (r1 == 0) goto L_0x0026
            java.lang.String r0 = r9.zza
            com.google.android.gms.measurement.internal.zzad r0 = r8.zza((java.lang.String) r0)
            java.lang.String r1 = r9.zzw
            com.google.android.gms.measurement.internal.zzad r1 = com.google.android.gms.measurement.internal.zzad.zza((java.lang.String) r1)
            com.google.android.gms.measurement.internal.zzad r0 = r0.zzb((com.google.android.gms.measurement.internal.zzad) r1)
        L_0x0026:
            r1 = 1
            if (r10 != 0) goto L_0x0069
            com.google.android.gms.measurement.internal.zzf r10 = new com.google.android.gms.measurement.internal.zzf
            com.google.android.gms.measurement.internal.zzfv r2 = r8.zzk
            java.lang.String r3 = r9.zza
            r10.<init>(r2, r3)
            boolean r2 = com.google.android.gms.internal.measurement.zzmj.zzb()
            if (r2 == 0) goto L_0x005d
            com.google.android.gms.measurement.internal.zzfv r2 = r8.zzk
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzat.zzci
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r3)
            if (r2 == 0) goto L_0x005d
            boolean r2 = r0.zze()
            if (r2 == 0) goto L_0x0053
            java.lang.String r2 = r8.zza((com.google.android.gms.measurement.internal.zzad) r0)
            r10.zza((java.lang.String) r2)
        L_0x0053:
            boolean r0 = r0.zzc()
            if (r0 == 0) goto L_0x0067
            r10.zze((java.lang.String) r11)
            goto L_0x0067
        L_0x005d:
            java.lang.String r0 = r8.zzz()
            r10.zza((java.lang.String) r0)
            r10.zze((java.lang.String) r11)
        L_0x0067:
            r11 = 1
            goto L_0x00bb
        L_0x0069:
            boolean r2 = com.google.android.gms.internal.measurement.zzmj.zzb()
            if (r2 == 0) goto L_0x0083
            com.google.android.gms.measurement.internal.zzfv r2 = r8.zzk
            com.google.android.gms.measurement.internal.zzy r2 = r2.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzat.zzci
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r3)
            if (r2 == 0) goto L_0x0083
            boolean r2 = r0.zzc()
            if (r2 == 0) goto L_0x00ba
        L_0x0083:
            java.lang.String r2 = r10.zzh()
            boolean r2 = r11.equals(r2)
            if (r2 != 0) goto L_0x00ba
            r10.zze((java.lang.String) r11)
            boolean r11 = com.google.android.gms.internal.measurement.zzmj.zzb()
            if (r11 == 0) goto L_0x00b2
            com.google.android.gms.measurement.internal.zzfv r11 = r8.zzk
            com.google.android.gms.measurement.internal.zzy r11 = r11.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzat.zzci
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r2)
            if (r11 == 0) goto L_0x00b2
            boolean r11 = r0.zze()
            if (r11 == 0) goto L_0x0067
            java.lang.String r11 = r8.zza((com.google.android.gms.measurement.internal.zzad) r0)
            r10.zza((java.lang.String) r11)
            goto L_0x0067
        L_0x00b2:
            java.lang.String r11 = r8.zzz()
            r10.zza((java.lang.String) r11)
            goto L_0x0067
        L_0x00ba:
            r11 = 0
        L_0x00bb:
            java.lang.String r0 = r9.zzb
            java.lang.String r2 = r10.zze()
            boolean r0 = android.text.TextUtils.equals(r0, r2)
            if (r0 != 0) goto L_0x00cd
            java.lang.String r11 = r9.zzb
            r10.zzb((java.lang.String) r11)
            r11 = 1
        L_0x00cd:
            java.lang.String r0 = r9.zzr
            java.lang.String r2 = r10.zzf()
            boolean r0 = android.text.TextUtils.equals(r0, r2)
            if (r0 != 0) goto L_0x00df
            java.lang.String r11 = r9.zzr
            r10.zzc((java.lang.String) r11)
            r11 = 1
        L_0x00df:
            boolean r0 = com.google.android.gms.internal.measurement.zznt.zzb()
            if (r0 == 0) goto L_0x0109
            com.google.android.gms.measurement.internal.zzfv r0 = r8.zzk
            com.google.android.gms.measurement.internal.zzy r0 = r0.zza()
            java.lang.String r2 = r10.zzc()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzat.zzbi
            boolean r0 = r0.zze(r2, r3)
            if (r0 == 0) goto L_0x0109
            java.lang.String r0 = r9.zzv
            java.lang.String r2 = r10.zzg()
            boolean r0 = android.text.TextUtils.equals(r0, r2)
            if (r0 != 0) goto L_0x0109
            java.lang.String r11 = r9.zzv
            r10.zzd((java.lang.String) r11)
            r11 = 1
        L_0x0109:
            java.lang.String r0 = r9.zzk
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0123
            java.lang.String r0 = r9.zzk
            java.lang.String r2 = r10.zzi()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0123
            java.lang.String r11 = r9.zzk
            r10.zzf((java.lang.String) r11)
            r11 = 1
        L_0x0123:
            long r2 = r9.zze
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x013b
            long r2 = r9.zze
            long r6 = r10.zzo()
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x013b
            long r2 = r9.zze
            r10.zzd((long) r2)
            r11 = 1
        L_0x013b:
            java.lang.String r0 = r9.zzc
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0155
            java.lang.String r0 = r9.zzc
            java.lang.String r2 = r10.zzl()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0155
            java.lang.String r11 = r9.zzc
            r10.zzg((java.lang.String) r11)
            r11 = 1
        L_0x0155:
            long r2 = r9.zzj
            long r6 = r10.zzm()
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0165
            long r2 = r9.zzj
            r10.zzc((long) r2)
            r11 = 1
        L_0x0165:
            java.lang.String r0 = r9.zzd
            if (r0 == 0) goto L_0x017b
            java.lang.String r0 = r9.zzd
            java.lang.String r2 = r10.zzn()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x017b
            java.lang.String r11 = r9.zzd
            r10.zzh((java.lang.String) r11)
            r11 = 1
        L_0x017b:
            long r2 = r9.zzf
            long r6 = r10.zzp()
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x018b
            long r2 = r9.zzf
            r10.zze((long) r2)
            r11 = 1
        L_0x018b:
            boolean r0 = r9.zzh
            boolean r2 = r10.zzr()
            if (r0 == r2) goto L_0x0199
            boolean r11 = r9.zzh
            r10.zza((boolean) r11)
            r11 = 1
        L_0x0199:
            java.lang.String r0 = r9.zzg
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x01b3
            java.lang.String r0 = r9.zzg
            java.lang.String r2 = r10.zzac()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01b3
            java.lang.String r11 = r9.zzg
            r10.zzi((java.lang.String) r11)
            r11 = 1
        L_0x01b3:
            com.google.android.gms.measurement.internal.zzfv r0 = r8.zzk
            com.google.android.gms.measurement.internal.zzy r0 = r0.zza()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzat.zzbx
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r2)
            if (r0 != 0) goto L_0x01d1
            long r2 = r9.zzl
            long r6 = r10.zzae()
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x01d1
            long r2 = r9.zzl
            r10.zzp(r2)
            r11 = 1
        L_0x01d1:
            boolean r0 = r9.zzo
            boolean r2 = r10.zzaf()
            if (r0 == r2) goto L_0x01df
            boolean r11 = r9.zzo
            r10.zzb((boolean) r11)
            r11 = 1
        L_0x01df:
            boolean r0 = r9.zzp
            boolean r2 = r10.zzag()
            if (r0 == r2) goto L_0x01ed
            boolean r11 = r9.zzp
            r10.zzc((boolean) r11)
            r11 = 1
        L_0x01ed:
            java.lang.Boolean r0 = r9.zzs
            java.lang.Boolean r2 = r10.zzah()
            if (r0 == r2) goto L_0x01fb
            java.lang.Boolean r11 = r9.zzs
            r10.zza((java.lang.Boolean) r11)
            r11 = 1
        L_0x01fb:
            long r2 = r9.zzt
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0211
            long r2 = r9.zzt
            long r4 = r10.zzq()
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0211
            long r2 = r9.zzt
            r10.zzf((long) r2)
            goto L_0x0212
        L_0x0211:
            r1 = r11
        L_0x0212:
            if (r1 == 0) goto L_0x021b
            com.google.android.gms.measurement.internal.zzac r9 = r8.zze()
            r9.zza((com.google.android.gms.measurement.internal.zzf) r10)
        L_0x021b:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.zza(com.google.android.gms.measurement.internal.zzn, com.google.android.gms.measurement.internal.zzf, java.lang.String):com.google.android.gms.measurement.internal.zzf");
    }

    /* access modifiers changed from: package-private */
    public final zzf zzc(zzn zzn2) {
        String str;
        zzx();
        zzn();
        Preconditions.checkNotNull(zzn2);
        Preconditions.checkNotEmpty(zzn2.zza);
        zzf zzb2 = zze().zzb(zzn2.zza);
        zzad zzad = zzad.zza;
        if (zzmj.zzb() && this.zzk.zza().zza(zzat.zzci)) {
            zzad = zza(zzn2.zza).zzb(zzad.zza(zzn2.zzw));
        }
        if (!zzmj.zzb() || !this.zzk.zza().zza(zzat.zzci) || zzad.zzc()) {
            str = this.zzj.zza(zzn2.zza);
        } else {
            str = "";
        }
        if (!zzng.zzb() || !this.zzk.zza().zza(zzat.zzbn)) {
            return zza(zzn2, zzb2, str);
        }
        if (zzb2 == null) {
            zzb2 = new zzf(this.zzk, zzn2.zza);
            if (!zzmj.zzb() || !this.zzk.zza().zza(zzat.zzci)) {
                zzb2.zza(zzz());
                zzb2.zze(str);
            } else {
                if (zzad.zze()) {
                    zzb2.zza(zza(zzad));
                }
                if (zzad.zzc()) {
                    zzb2.zze(str);
                }
            }
        } else if ((!zzmj.zzb() || !this.zzk.zza().zza(zzat.zzci) || zzad.zzc()) && !str.equals(zzb2.zzh())) {
            zzb2.zze(str);
            if (!zzmj.zzb() || !this.zzk.zza().zza(zzat.zzci)) {
                zzb2.zza(zzz());
            } else {
                zzb2.zza(zza(zzad));
            }
        }
        zzb2.zzb(zzn2.zzb);
        zzb2.zzc(zzn2.zzr);
        if (zznt.zzb() && this.zzk.zza().zze(zzb2.zzc(), zzat.zzbi)) {
            zzb2.zzd(zzn2.zzv);
        }
        if (!TextUtils.isEmpty(zzn2.zzk)) {
            zzb2.zzf(zzn2.zzk);
        }
        if (zzn2.zze != 0) {
            zzb2.zzd(zzn2.zze);
        }
        if (!TextUtils.isEmpty(zzn2.zzc)) {
            zzb2.zzg(zzn2.zzc);
        }
        zzb2.zzc(zzn2.zzj);
        if (zzn2.zzd != null) {
            zzb2.zzh(zzn2.zzd);
        }
        zzb2.zze(zzn2.zzf);
        zzb2.zza(zzn2.zzh);
        if (!TextUtils.isEmpty(zzn2.zzg)) {
            zzb2.zzi(zzn2.zzg);
        }
        if (!this.zzk.zza().zza(zzat.zzbx)) {
            zzb2.zzp(zzn2.zzl);
        }
        zzb2.zzb(zzn2.zzo);
        zzb2.zzc(zzn2.zzp);
        zzb2.zza(zzn2.zzs);
        zzb2.zzf(zzn2.zzt);
        if (zzb2.zza()) {
            zze().zza(zzb2);
        }
        return zzb2;
    }

    /* access modifiers changed from: package-private */
    public final String zzd(zzn zzn2) {
        try {
            return (String) this.zzk.zzp().zza(new zzkm(this, zzn2)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzk.zzq().zze().zza("Failed to get app instance id. appId", zzer.zza(zzn2.zza), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzab();
    }

    private final boolean zze(zzn zzn2) {
        return (!zznt.zzb() || !this.zzk.zza().zze(zzn2.zza, zzat.zzbi)) ? !TextUtils.isEmpty(zzn2.zzb) || !TextUtils.isEmpty(zzn2.zzr) : !TextUtils.isEmpty(zzn2.zzb) || !TextUtils.isEmpty(zzn2.zzv) || !TextUtils.isEmpty(zzn2.zzr);
    }
}
