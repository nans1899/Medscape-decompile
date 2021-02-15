package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzad {
    public static final zzad zza = new zzad((Boolean) null, (Boolean) null);
    private final Boolean zzb;
    private final Boolean zzc;

    public zzad(Boolean bool, Boolean bool2) {
        this.zzb = bool;
        this.zzc = bool2;
    }

    public static boolean zza(int i, int i2) {
        return i <= i2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzad = (zzad) obj;
        if (zza(this.zzb) == zza(zzad.zzb) && zza(this.zzc) == zza(zzad.zzc)) {
            return true;
        }
        return false;
    }

    private static int zza(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : 2;
    }

    public final int hashCode() {
        return ((zza(this.zzb) + 527) * 31) + zza(this.zzc);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ConsentSettings: ");
        sb.append("adStorage=");
        Boolean bool = this.zzb;
        String str2 = "granted";
        if (bool == null) {
            sb.append("uninitialized");
        } else {
            if (bool.booleanValue()) {
                str = str2;
            } else {
                str = "denied";
            }
            sb.append(str);
        }
        sb.append(", analyticsStorage=");
        Boolean bool2 = this.zzc;
        if (bool2 == null) {
            sb.append("uninitialized");
        } else {
            if (!bool2.booleanValue()) {
                str2 = "denied";
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    public static String zza(Bundle bundle) {
        String string = bundle.getString("ad_storage");
        if (string != null && zzb(string) == null) {
            return string;
        }
        String string2 = bundle.getString("analytics_storage");
        if (string2 == null || zzb(string2) != null) {
            return null;
        }
        return string2;
    }

    public static zzad zzb(Bundle bundle) {
        if (bundle == null) {
            return zza;
        }
        return new zzad(zzb(bundle.getString("ad_storage")), zzb(bundle.getString("analytics_storage")));
    }

    public static zzad zza(String str) {
        Boolean bool;
        Boolean bool2 = null;
        if (str != null) {
            Boolean zza2 = str.length() >= 3 ? zza(str.charAt(2)) : null;
            if (str.length() >= 4) {
                bool2 = zza(str.charAt(3));
            }
            bool = bool2;
            bool2 = zza2;
        } else {
            bool = null;
        }
        return new zzad(bool2, bool);
    }

    private static Boolean zzb(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("granted")) {
            return Boolean.TRUE;
        }
        if (str.equals("denied")) {
            return Boolean.FALSE;
        }
        return null;
    }

    private static Boolean zza(char c) {
        if (c == '0') {
            return Boolean.FALSE;
        }
        if (c != '1') {
            return null;
        }
        return Boolean.TRUE;
    }

    private static char zzb(Boolean bool) {
        if (bool == null) {
            return '-';
        }
        return bool.booleanValue() ? '1' : '0';
    }

    public final String zza() {
        return "G1" + zzb(this.zzb) + zzb(this.zzc);
    }

    public final Boolean zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        Boolean bool = this.zzb;
        return bool == null || bool.booleanValue();
    }

    public final Boolean zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        Boolean bool = this.zzc;
        return bool == null || bool.booleanValue();
    }

    public final boolean zza(zzad zzad) {
        if (this.zzb != Boolean.FALSE || zzad.zzb == Boolean.FALSE) {
            return this.zzc == Boolean.FALSE && zzad.zzc != Boolean.FALSE;
        }
        return true;
    }

    private static Boolean zza(Boolean bool, Boolean bool2) {
        if (bool == null) {
            return bool2;
        }
        if (bool2 == null) {
            return bool;
        }
        return Boolean.valueOf(bool.booleanValue() && bool2.booleanValue());
    }

    public final zzad zzb(zzad zzad) {
        return new zzad(zza(this.zzb, zzad.zzb), zza(this.zzc, zzad.zzc));
    }

    public final zzad zzc(zzad zzad) {
        Boolean bool = this.zzb;
        if (bool == null) {
            bool = zzad.zzb;
        }
        Boolean bool2 = this.zzc;
        if (bool2 == null) {
            bool2 = zzad.zzc;
        }
        return new zzad(bool, bool2);
    }
}
