package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzbbq;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class RequestConfiguration {
    public static final String MAX_AD_CONTENT_RATING_G = "G";
    public static final String MAX_AD_CONTENT_RATING_MA = "MA";
    public static final String MAX_AD_CONTENT_RATING_PG = "PG";
    public static final String MAX_AD_CONTENT_RATING_T = "T";
    public static final String MAX_AD_CONTENT_RATING_UNSPECIFIED = "";
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_FALSE = 0;
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_TRUE = 1;
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_UNSPECIFIED = -1;
    public static final List<String> zzadq = Arrays.asList(new String[]{"MA", "T", "PG", "G"});
    private final int zzadm;
    private final int zzadn;
    private final String zzado;
    private final List<String> zzadp;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface MaxAdContentRating {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface TagForChildDirectedTreatment {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface TagForUnderAgeOfConsent {
    }

    private RequestConfiguration(int i, int i2, String str, List<String> list) {
        this.zzadm = i;
        this.zzadn = i2;
        this.zzado = str;
        this.zzadp = list;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static class Builder {
        private int zzadm = -1;
        private int zzadn = -1;
        private String zzado = null;
        private final List<String> zzadp = new ArrayList();

        public RequestConfiguration build() {
            return new RequestConfiguration(this.zzadm, this.zzadn, this.zzado, this.zzadp);
        }

        public Builder setTagForChildDirectedTreatment(int i) {
            if (i == -1 || i == 0 || i == 1) {
                this.zzadm = i;
            } else {
                StringBuilder sb = new StringBuilder(68);
                sb.append("Invalid value passed to setTagForChildDirectedTreatment: ");
                sb.append(i);
                zzbbq.zzfe(sb.toString());
            }
            return this;
        }

        public Builder setTagForUnderAgeOfConsent(int i) {
            if (i == -1 || i == 0 || i == 1) {
                this.zzadn = i;
            } else {
                StringBuilder sb = new StringBuilder(63);
                sb.append("Invalid value passed to setTagForUnderAgeOfConsent: ");
                sb.append(i);
                zzbbq.zzfe(sb.toString());
            }
            return this;
        }

        public Builder setMaxAdContentRating(String str) {
            if (str == null || "".equals(str)) {
                this.zzado = null;
            } else if ("G".equals(str) || "PG".equals(str) || "T".equals(str) || "MA".equals(str)) {
                this.zzado = str;
            } else {
                String valueOf = String.valueOf(str);
                zzbbq.zzfe(valueOf.length() != 0 ? "Invalid value passed to setMaxAdContentRating: ".concat(valueOf) : new String("Invalid value passed to setMaxAdContentRating: "));
            }
            return this;
        }

        public Builder setTestDeviceIds(@Nullable List<String> list) {
            this.zzadp.clear();
            if (list != null) {
                this.zzadp.addAll(list);
            }
            return this;
        }
    }

    public int getTagForChildDirectedTreatment() {
        return this.zzadm;
    }

    public int getTagForUnderAgeOfConsent() {
        return this.zzadn;
    }

    public String getMaxAdContentRating() {
        String str = this.zzado;
        return str == null ? "" : str;
    }

    public List<String> getTestDeviceIds() {
        return new ArrayList(this.zzadp);
    }

    public Builder toBuilder() {
        return new Builder().setTagForChildDirectedTreatment(this.zzadm).setTagForUnderAgeOfConsent(this.zzadn).setMaxAdContentRating(this.zzado).setTestDeviceIds(this.zzadp);
    }
}
