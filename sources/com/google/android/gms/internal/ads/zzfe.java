package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzfe {
    static zzeac zzaaa;

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean zzb(com.google.android.gms.internal.ads.zzex r9) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            com.google.android.gms.internal.ads.zzeac r0 = zzaaa
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            com.google.android.gms.internal.ads.zzaaq<java.lang.String> r0 = com.google.android.gms.internal.ads.zzabb.zzcrd
            com.google.android.gms.internal.ads.zzaax r2 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r2.zzd(r0)
            java.lang.String r0 = (java.lang.String) r0
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x001c
            int r4 = r0.length()
            if (r4 != 0) goto L_0x0037
        L_0x001c:
            if (r9 != 0) goto L_0x0020
        L_0x001e:
            r0 = r2
            goto L_0x0034
        L_0x0020:
            java.lang.String r0 = "TkuK+8ZKbIcxeUe4msY7eeifKf/tICuqqRvwzwQUhsKM0HemvJhBrPQYp0qpvgcE"
            java.lang.String r4 = "eNJuSXkridnHpFkTgNBQFH0ivDH801goaJfT5bONEac="
            java.lang.reflect.Method r9 = r9.zza((java.lang.String) r0, (java.lang.String) r4)
            if (r9 != 0) goto L_0x002b
            goto L_0x001e
        L_0x002b:
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.Object r9 = r9.invoke(r2, r0)
            java.lang.String r9 = (java.lang.String) r9
            r0 = r9
        L_0x0034:
            if (r0 != 0) goto L_0x0037
            return r3
        L_0x0037:
            byte[] r9 = com.google.android.gms.internal.ads.zzcv.zzb(r0, r1)     // Catch:{ IllegalArgumentException -> 0x011e }
            com.google.android.gms.internal.ads.zzeah r9 = com.google.android.gms.internal.ads.zzeal.zzl(r9)     // Catch:{  }
            com.google.android.gms.internal.ads.zzefw r0 = com.google.android.gms.internal.ads.zzece.zzhvl     // Catch:{  }
            java.util.List r0 = r0.zzbdf()     // Catch:{  }
            java.util.Iterator r0 = r0.iterator()     // Catch:{  }
        L_0x0049:
            boolean r4 = r0.hasNext()     // Catch:{  }
            if (r4 == 0) goto L_0x0115
            java.lang.Object r4 = r0.next()     // Catch:{  }
            com.google.android.gms.internal.ads.zzefi r4 = (com.google.android.gms.internal.ads.zzefi) r4     // Catch:{  }
            java.lang.String r5 = r4.zzbbt()     // Catch:{  }
            boolean r5 = r5.isEmpty()     // Catch:{  }
            if (r5 != 0) goto L_0x010d
            java.lang.String r5 = r4.zzbcb()     // Catch:{  }
            boolean r5 = r5.isEmpty()     // Catch:{  }
            if (r5 != 0) goto L_0x0105
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            boolean r5 = r5.isEmpty()     // Catch:{  }
            if (r5 != 0) goto L_0x00fd
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkAead"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkMac"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkHybridDecrypt"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkHybridEncrypt"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkPublicKeySign"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkPublicKeyVerify"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkStreamingAead"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            java.lang.String r6 = "TinkDeterministicAead"
            boolean r5 = r5.equals(r6)     // Catch:{  }
            if (r5 == 0) goto L_0x00d5
            goto L_0x0049
        L_0x00d5:
            java.lang.String r5 = r4.zzbce()     // Catch:{  }
            com.google.android.gms.internal.ads.zzdzy r5 = com.google.android.gms.internal.ads.zzear.zzhn(r5)     // Catch:{  }
            com.google.android.gms.internal.ads.zzeaq r6 = r5.zzayj()     // Catch:{  }
            com.google.android.gms.internal.ads.zzear.zza(r6)     // Catch:{  }
            java.lang.String r6 = r4.zzbbt()     // Catch:{  }
            java.lang.String r7 = r4.zzbcb()     // Catch:{  }
            int r8 = r4.zzbcc()     // Catch:{  }
            com.google.android.gms.internal.ads.zzeab r5 = r5.zzb(r6, r7, r8)     // Catch:{  }
            boolean r4 = r4.zzbcd()     // Catch:{  }
            com.google.android.gms.internal.ads.zzear.zza(r5, (boolean) r4)     // Catch:{  }
            goto L_0x0049
        L_0x00fd:
            java.security.GeneralSecurityException r9 = new java.security.GeneralSecurityException     // Catch:{  }
            java.lang.String r0 = "Missing catalogue_name."
            r9.<init>(r0)     // Catch:{  }
            throw r9     // Catch:{  }
        L_0x0105:
            java.security.GeneralSecurityException r9 = new java.security.GeneralSecurityException     // Catch:{  }
            java.lang.String r0 = "Missing primitive_name."
            r9.<init>(r0)     // Catch:{  }
            throw r9     // Catch:{  }
        L_0x010d:
            java.security.GeneralSecurityException r9 = new java.security.GeneralSecurityException     // Catch:{  }
            java.lang.String r0 = "Missing type_url."
            r9.<init>(r0)     // Catch:{  }
            throw r9     // Catch:{  }
        L_0x0115:
            com.google.android.gms.internal.ads.zzeac r9 = com.google.android.gms.internal.ads.zzeci.zza(r9, r2)     // Catch:{  }
            zzaaa = r9     // Catch:{  }
            if (r9 == 0) goto L_0x011e
            return r1
        L_0x011e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfe.zzb(com.google.android.gms.internal.ads.zzex):boolean");
    }
}
