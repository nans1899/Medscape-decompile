package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbv;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzs extends zzv {
    private zzbv.zzb zzg;
    private final /* synthetic */ zzo zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzs(zzo zzo, String str, int i, zzbv.zzb zzb) {
        super(str, i);
        this.zzh = zzo;
        this.zzg = zzb;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzg.zzb();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc() {
        return this.zzg.zzf();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010f, code lost:
        if (r6.booleanValue() == false) goto L_0x03a3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x03af  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x03b2  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x03ba A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x03bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(java.lang.Long r18, java.lang.Long r19, com.google.android.gms.internal.measurement.zzcd.zzc r20, long r21, com.google.android.gms.measurement.internal.zzan r23, boolean r24) {
        /*
            r17 = this;
            r0 = r17
            boolean r1 = com.google.android.gms.internal.measurement.zzmv.zzb()
            r2 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r4 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            if (r1 == 0) goto L_0x0024
            com.google.android.gms.measurement.internal.zzo r1 = r0.zzh
            com.google.android.gms.measurement.internal.zzy r1 = r1.zzs()
            java.lang.String r6 = r0.zza
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzat.zzbb
            boolean r1 = r1.zzd(r6, r7)
            if (r1 == 0) goto L_0x0024
            r1 = 1
            goto L_0x0025
        L_0x0024:
            r1 = 0
        L_0x0025:
            com.google.android.gms.internal.measurement.zzbv$zzb r6 = r0.zzg
            boolean r6 = r6.zzk()
            if (r6 == 0) goto L_0x0032
            r6 = r23
            long r6 = r6.zze
            goto L_0x0034
        L_0x0032:
            r6 = r21
        L_0x0034:
            com.google.android.gms.measurement.internal.zzo r8 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzq()
            r9 = 2
            boolean r8 = r8.zza((int) r9)
            r9 = 0
            if (r8 == 0) goto L_0x0096
            com.google.android.gms.measurement.internal.zzo r8 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzq()
            com.google.android.gms.measurement.internal.zzet r8 = r8.zzw()
            int r10 = r0.zzb
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            com.google.android.gms.internal.measurement.zzbv$zzb r11 = r0.zzg
            boolean r11 = r11.zza()
            if (r11 == 0) goto L_0x0065
            com.google.android.gms.internal.measurement.zzbv$zzb r11 = r0.zzg
            int r11 = r11.zzb()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            goto L_0x0066
        L_0x0065:
            r11 = r9
        L_0x0066:
            com.google.android.gms.measurement.internal.zzo r12 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r12 = r12.zzn()
            com.google.android.gms.internal.measurement.zzbv$zzb r13 = r0.zzg
            java.lang.String r13 = r13.zzc()
            java.lang.String r12 = r12.zza((java.lang.String) r13)
            java.lang.String r13 = "Evaluating filter. audience, filter, event"
            r8.zza(r13, r10, r11, r12)
            com.google.android.gms.measurement.internal.zzo r8 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzq()
            com.google.android.gms.measurement.internal.zzet r8 = r8.zzw()
            com.google.android.gms.measurement.internal.zzo r10 = r0.zzh
            com.google.android.gms.measurement.internal.zzks r10 = r10.f_()
            com.google.android.gms.internal.measurement.zzbv$zzb r11 = r0.zzg
            java.lang.String r10 = r10.zza((com.google.android.gms.internal.measurement.zzbv.zzb) r11)
            java.lang.String r11 = "Filter definition"
            r8.zza(r11, r10)
        L_0x0096:
            com.google.android.gms.internal.measurement.zzbv$zzb r8 = r0.zzg
            boolean r8 = r8.zza()
            if (r8 == 0) goto L_0x03fc
            com.google.android.gms.internal.measurement.zzbv$zzb r8 = r0.zzg
            int r8 = r8.zzb()
            r10 = 256(0x100, float:3.59E-43)
            if (r8 <= r10) goto L_0x00aa
            goto L_0x03fc
        L_0x00aa:
            com.google.android.gms.internal.measurement.zzbv$zzb r8 = r0.zzg
            boolean r8 = r8.zzh()
            com.google.android.gms.internal.measurement.zzbv$zzb r10 = r0.zzg
            boolean r10 = r10.zzi()
            com.google.android.gms.internal.measurement.zzbv$zzb r11 = r0.zzg
            boolean r11 = r11.zzk()
            if (r8 != 0) goto L_0x00c5
            if (r10 != 0) goto L_0x00c5
            if (r11 == 0) goto L_0x00c3
            goto L_0x00c5
        L_0x00c3:
            r8 = 0
            goto L_0x00c6
        L_0x00c5:
            r8 = 1
        L_0x00c6:
            if (r24 == 0) goto L_0x00f2
            if (r8 != 0) goto L_0x00f2
            com.google.android.gms.measurement.internal.zzo r1 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()
            int r3 = r0.zzb
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            com.google.android.gms.internal.measurement.zzbv$zzb r4 = r0.zzg
            boolean r4 = r4.zza()
            if (r4 == 0) goto L_0x00ec
            com.google.android.gms.internal.measurement.zzbv$zzb r4 = r0.zzg
            int r4 = r4.zzb()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r4)
        L_0x00ec:
            java.lang.String r4 = "Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID"
            r1.zza(r4, r3, r9)
            return r2
        L_0x00f2:
            com.google.android.gms.internal.measurement.zzbv$zzb r10 = r0.zzg
            java.lang.String r11 = r20.zzc()
            boolean r12 = r10.zzf()
            if (r12 == 0) goto L_0x0113
            com.google.android.gms.internal.measurement.zzbv$zzd r12 = r10.zzg()
            java.lang.Boolean r6 = zza((long) r6, (com.google.android.gms.internal.measurement.zzbv.zzd) r12)
            if (r6 != 0) goto L_0x010b
        L_0x0108:
            r5 = r9
            goto L_0x03a3
        L_0x010b:
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L_0x0113
            goto L_0x03a3
        L_0x0113:
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            java.util.List r7 = r10.zzd()
            java.util.Iterator r7 = r7.iterator()
        L_0x0120:
            boolean r12 = r7.hasNext()
            if (r12 == 0) goto L_0x0158
            java.lang.Object r12 = r7.next()
            com.google.android.gms.internal.measurement.zzbv$zzc r12 = (com.google.android.gms.internal.measurement.zzbv.zzc) r12
            java.lang.String r13 = r12.zzh()
            boolean r13 = r13.isEmpty()
            if (r13 == 0) goto L_0x0150
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            java.lang.String r7 = "null or empty param name in filter. event"
            r5.zza(r7, r6)
            goto L_0x0108
        L_0x0150:
            java.lang.String r12 = r12.zzh()
            r6.add(r12)
            goto L_0x0120
        L_0x0158:
            androidx.collection.ArrayMap r7 = new androidx.collection.ArrayMap
            r7.<init>()
            java.util.List r12 = r20.zza()
            java.util.Iterator r12 = r12.iterator()
        L_0x0165:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x01f2
            java.lang.Object r13 = r12.next()
            com.google.android.gms.internal.measurement.zzcd$zze r13 = (com.google.android.gms.internal.measurement.zzcd.zze) r13
            java.lang.String r14 = r13.zzb()
            boolean r14 = r6.contains(r14)
            if (r14 == 0) goto L_0x0165
            boolean r14 = r13.zze()
            if (r14 == 0) goto L_0x0199
            java.lang.String r14 = r13.zzb()
            boolean r15 = r13.zze()
            if (r15 == 0) goto L_0x0194
            long r15 = r13.zzf()
            java.lang.Long r13 = java.lang.Long.valueOf(r15)
            goto L_0x0195
        L_0x0194:
            r13 = r9
        L_0x0195:
            r7.put(r14, r13)
            goto L_0x0165
        L_0x0199:
            boolean r14 = r13.zzi()
            if (r14 == 0) goto L_0x01b7
            java.lang.String r14 = r13.zzb()
            boolean r15 = r13.zzi()
            if (r15 == 0) goto L_0x01b2
            double r15 = r13.zzj()
            java.lang.Double r13 = java.lang.Double.valueOf(r15)
            goto L_0x01b3
        L_0x01b2:
            r13 = r9
        L_0x01b3:
            r7.put(r14, r13)
            goto L_0x0165
        L_0x01b7:
            boolean r14 = r13.zzc()
            if (r14 == 0) goto L_0x01c9
            java.lang.String r14 = r13.zzb()
            java.lang.String r13 = r13.zzd()
            r7.put(r14, r13)
            goto L_0x0165
        L_0x01c9:
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            com.google.android.gms.measurement.internal.zzo r7 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzn()
            java.lang.String r10 = r13.zzb()
            java.lang.String r7 = r7.zzb(r10)
            java.lang.String r10 = "Unknown value for param. event, param"
            r5.zza(r10, r6, r7)
            goto L_0x0108
        L_0x01f2:
            java.util.List r6 = r10.zzd()
            java.util.Iterator r6 = r6.iterator()
        L_0x01fa:
            boolean r10 = r6.hasNext()
            if (r10 == 0) goto L_0x03a2
            java.lang.Object r10 = r6.next()
            com.google.android.gms.internal.measurement.zzbv$zzc r10 = (com.google.android.gms.internal.measurement.zzbv.zzc) r10
            boolean r12 = r10.zze()
            if (r12 == 0) goto L_0x0214
            boolean r12 = r10.zzf()
            if (r12 == 0) goto L_0x0214
            r12 = 1
            goto L_0x0215
        L_0x0214:
            r12 = 0
        L_0x0215:
            java.lang.String r13 = r10.zzh()
            boolean r14 = r13.isEmpty()
            if (r14 == 0) goto L_0x023a
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            java.lang.String r7 = "Event has empty param name. event"
            r5.zza(r7, r6)
            goto L_0x0108
        L_0x023a:
            java.lang.Object r14 = r7.get(r13)
            boolean r15 = r14 instanceof java.lang.Long
            if (r15 == 0) goto L_0x0287
            boolean r15 = r10.zzc()
            if (r15 != 0) goto L_0x026d
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            com.google.android.gms.measurement.internal.zzo r7 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzn()
            java.lang.String r7 = r7.zzb(r13)
            java.lang.String r10 = "No number filter for long param. event, param"
            r5.zza(r10, r6, r7)
            goto L_0x0108
        L_0x026d:
            java.lang.Long r14 = (java.lang.Long) r14
            long r13 = r14.longValue()
            com.google.android.gms.internal.measurement.zzbv$zzd r10 = r10.zzd()
            java.lang.Boolean r10 = zza((long) r13, (com.google.android.gms.internal.measurement.zzbv.zzd) r10)
            if (r10 != 0) goto L_0x027f
            goto L_0x0108
        L_0x027f:
            boolean r10 = r10.booleanValue()
            if (r10 != r12) goto L_0x01fa
            goto L_0x03a3
        L_0x0287:
            boolean r15 = r14 instanceof java.lang.Double
            if (r15 == 0) goto L_0x02d0
            boolean r15 = r10.zzc()
            if (r15 != 0) goto L_0x02b6
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            com.google.android.gms.measurement.internal.zzo r7 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzn()
            java.lang.String r7 = r7.zzb(r13)
            java.lang.String r10 = "No number filter for double param. event, param"
            r5.zza(r10, r6, r7)
            goto L_0x0108
        L_0x02b6:
            java.lang.Double r14 = (java.lang.Double) r14
            double r13 = r14.doubleValue()
            com.google.android.gms.internal.measurement.zzbv$zzd r10 = r10.zzd()
            java.lang.Boolean r10 = zza((double) r13, (com.google.android.gms.internal.measurement.zzbv.zzd) r10)
            if (r10 != 0) goto L_0x02c8
            goto L_0x0108
        L_0x02c8:
            boolean r10 = r10.booleanValue()
            if (r10 != r12) goto L_0x01fa
            goto L_0x03a3
        L_0x02d0:
            boolean r15 = r14 instanceof java.lang.String
            if (r15 == 0) goto L_0x0357
            boolean r15 = r10.zza()
            if (r15 == 0) goto L_0x02eb
            java.lang.String r14 = (java.lang.String) r14
            com.google.android.gms.internal.measurement.zzbv$zzf r10 = r10.zzb()
            com.google.android.gms.measurement.internal.zzo r13 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r13 = r13.zzq()
            java.lang.Boolean r10 = zza((java.lang.String) r14, (com.google.android.gms.internal.measurement.zzbv.zzf) r10, (com.google.android.gms.measurement.internal.zzer) r13)
            goto L_0x0301
        L_0x02eb:
            boolean r15 = r10.zzc()
            if (r15 == 0) goto L_0x0332
            java.lang.String r14 = (java.lang.String) r14
            boolean r15 = com.google.android.gms.measurement.internal.zzks.zza((java.lang.String) r14)
            if (r15 == 0) goto L_0x030d
            com.google.android.gms.internal.measurement.zzbv$zzd r10 = r10.zzd()
            java.lang.Boolean r10 = zza((java.lang.String) r14, (com.google.android.gms.internal.measurement.zzbv.zzd) r10)
        L_0x0301:
            if (r10 != 0) goto L_0x0305
            goto L_0x0108
        L_0x0305:
            boolean r10 = r10.booleanValue()
            if (r10 != r12) goto L_0x01fa
            goto L_0x03a3
        L_0x030d:
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            com.google.android.gms.measurement.internal.zzo r7 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzn()
            java.lang.String r7 = r7.zzb(r13)
            java.lang.String r10 = "Invalid param value for number filter. event, param"
            r5.zza(r10, r6, r7)
            goto L_0x0108
        L_0x0332:
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            com.google.android.gms.measurement.internal.zzo r7 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzn()
            java.lang.String r7 = r7.zzb(r13)
            java.lang.String r10 = "No filter for String param. event, param"
            r5.zza(r10, r6, r7)
            goto L_0x0108
        L_0x0357:
            if (r14 != 0) goto L_0x037d
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r6 = r6.zzq()
            com.google.android.gms.measurement.internal.zzet r6 = r6.zzw()
            com.google.android.gms.measurement.internal.zzo r7 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzn()
            java.lang.String r7 = r7.zza((java.lang.String) r11)
            com.google.android.gms.measurement.internal.zzo r9 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r9 = r9.zzn()
            java.lang.String r9 = r9.zzb(r13)
            java.lang.String r10 = "Missing param for filter. event, param"
            r6.zza(r10, r7, r9)
            goto L_0x03a3
        L_0x037d:
            com.google.android.gms.measurement.internal.zzo r5 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzh()
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r6 = r6.zzn()
            java.lang.String r6 = r6.zza((java.lang.String) r11)
            com.google.android.gms.measurement.internal.zzo r7 = r0.zzh
            com.google.android.gms.measurement.internal.zzep r7 = r7.zzn()
            java.lang.String r7 = r7.zzb(r13)
            java.lang.String r10 = "Unknown param type. event, param"
            r5.zza(r10, r6, r7)
            goto L_0x0108
        L_0x03a2:
            r5 = r3
        L_0x03a3:
            com.google.android.gms.measurement.internal.zzo r6 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r6 = r6.zzq()
            com.google.android.gms.measurement.internal.zzet r6 = r6.zzw()
            if (r5 != 0) goto L_0x03b2
            java.lang.String r7 = "null"
            goto L_0x03b3
        L_0x03b2:
            r7 = r5
        L_0x03b3:
            java.lang.String r9 = "Event filter result"
            r6.zza(r9, r7)
            if (r5 != 0) goto L_0x03bb
            return r4
        L_0x03bb:
            r0.zzc = r3
            boolean r4 = r5.booleanValue()
            if (r4 != 0) goto L_0x03c4
            return r2
        L_0x03c4:
            r0.zzd = r3
            if (r8 == 0) goto L_0x03fb
            boolean r3 = r20.zzd()
            if (r3 == 0) goto L_0x03fb
            long r3 = r20.zze()
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            com.google.android.gms.internal.measurement.zzbv$zzb r4 = r0.zzg
            boolean r4 = r4.zzi()
            if (r4 == 0) goto L_0x03ed
            if (r1 == 0) goto L_0x03ea
            com.google.android.gms.internal.measurement.zzbv$zzb r1 = r0.zzg
            boolean r1 = r1.zzf()
            if (r1 == 0) goto L_0x03ea
            r3 = r18
        L_0x03ea:
            r0.zzf = r3
            goto L_0x03fb
        L_0x03ed:
            if (r1 == 0) goto L_0x03f9
            com.google.android.gms.internal.measurement.zzbv$zzb r1 = r0.zzg
            boolean r1 = r1.zzf()
            if (r1 == 0) goto L_0x03f9
            r3 = r19
        L_0x03f9:
            r0.zze = r3
        L_0x03fb:
            return r2
        L_0x03fc:
            com.google.android.gms.measurement.internal.zzo r1 = r0.zzh
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzh()
            java.lang.String r2 = r0.zza
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r2)
            com.google.android.gms.internal.measurement.zzbv$zzb r3 = r0.zzg
            boolean r3 = r3.zza()
            if (r3 == 0) goto L_0x041e
            com.google.android.gms.internal.measurement.zzbv$zzb r3 = r0.zzg
            int r3 = r3.zzb()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)
        L_0x041e:
            java.lang.String r3 = java.lang.String.valueOf(r9)
            java.lang.String r5 = "Invalid event filter ID. appId, id"
            r1.zza(r5, r2, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzs.zza(java.lang.Long, java.lang.Long, com.google.android.gms.internal.measurement.zzcd$zzc, long, com.google.android.gms.measurement.internal.zzan, boolean):boolean");
    }
}
