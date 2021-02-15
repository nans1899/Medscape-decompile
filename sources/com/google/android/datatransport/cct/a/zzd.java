package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zza;

final class zzd extends zza {
    private final Integer zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;

    static final class zza extends zza.C0002zza {
        private Integer zza;
        private String zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private String zzg;
        private String zzh;

        zza() {
        }

        public zza.C0002zza zza(Integer num) {
            this.zza = num;
            return this;
        }

        public zza.C0002zza zzb(String str) {
            this.zzh = str;
            return this;
        }

        public zza.C0002zza zzc(String str) {
            this.zzc = str;
            return this;
        }

        public zza.C0002zza zzd(String str) {
            this.zzg = str;
            return this;
        }

        public zza.C0002zza zze(String str) {
            this.zzb = str;
            return this;
        }

        public zza.C0002zza zzf(String str) {
            this.zzf = str;
            return this;
        }

        public zza.C0002zza zzg(String str) {
            this.zze = str;
            return this;
        }

        public zza.C0002zza zza(String str) {
            this.zzd = str;
            return this;
        }

        public zza zza() {
            return new zzd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, (zzc) null);
        }
    }

    /* synthetic */ zzd(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, zzc zzc2) {
        this.zza = num;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
        this.zzh = str7;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        Integer num = this.zza;
        if (num != null ? num.equals(((zzd) obj).zza) : ((zzd) obj).zza == null) {
            String str = this.zzb;
            if (str != null ? str.equals(((zzd) obj).zzb) : ((zzd) obj).zzb == null) {
                String str2 = this.zzc;
                if (str2 != null ? str2.equals(((zzd) obj).zzc) : ((zzd) obj).zzc == null) {
                    String str3 = this.zzd;
                    if (str3 != null ? str3.equals(((zzd) obj).zzd) : ((zzd) obj).zzd == null) {
                        String str4 = this.zze;
                        if (str4 != null ? str4.equals(((zzd) obj).zze) : ((zzd) obj).zze == null) {
                            String str5 = this.zzf;
                            if (str5 != null ? str5.equals(((zzd) obj).zzf) : ((zzd) obj).zzf == null) {
                                String str6 = this.zzg;
                                if (str6 != null ? str6.equals(((zzd) obj).zzg) : ((zzd) obj).zzg == null) {
                                    String str7 = this.zzh;
                                    if (str7 == null) {
                                        if (((zzd) obj).zzh == null) {
                                            return true;
                                        }
                                    } else if (str7.equals(((zzd) obj).zzh)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.zza;
        int i = 0;
        int hashCode = ((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003;
        String str = this.zzb;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zzc;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zzd;
        int hashCode4 = (hashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.zze;
        int hashCode5 = (hashCode4 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.zzf;
        int hashCode6 = (hashCode5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.zzg;
        int hashCode7 = (hashCode6 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.zzh;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode7 ^ i;
    }

    public String toString() {
        return "AndroidClientInfo{sdkVersion=" + this.zza + ", model=" + this.zzb + ", hardware=" + this.zzc + ", device=" + this.zzd + ", product=" + this.zze + ", osBuild=" + this.zzf + ", manufacturer=" + this.zzg + ", fingerprint=" + this.zzh + "}";
    }

    public String zzb() {
        return this.zzd;
    }

    public String zzc() {
        return this.zzh;
    }

    public String zzd() {
        return this.zzc;
    }

    public String zze() {
        return this.zzg;
    }

    public String zzf() {
        return this.zzb;
    }

    public String zzg() {
        return this.zzf;
    }

    public String zzh() {
        return this.zze;
    }

    public Integer zzi() {
        return this.zza;
    }
}
