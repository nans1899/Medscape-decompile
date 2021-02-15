package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.query.AdInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzyw;
import com.google.android.gms.internal.ads.zzyz;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class AdRequest {
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_APP_ID_MISSING = 8;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_MEDIATION_NO_FILL = 9;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_G = "G";
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_MA = "MA";
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_PG = "PG";
    @Deprecated
    public static final String MAX_AD_CONTENT_RATING_T = "T";
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    @Deprecated
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_FALSE = 0;
    @Deprecated
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_TRUE = 1;
    @Deprecated
    public static final int TAG_FOR_UNDER_AGE_OF_CONSENT_UNSPECIFIED = -1;
    private final zzyw zzacu;

    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface MaxAdContentRating {
    }

    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public @interface TagForUnderAgeOfConsent {
    }

    private AdRequest(Builder builder) {
        this.zzacu = new zzyw(builder.zzacv);
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public final zzyz zzacv;

        public Builder() {
            zzyz zzyz = new zzyz();
            this.zzacv = zzyz;
            zzyz.zzck("B3EEABB8EE11C2BE770B684D95219ECB");
        }

        public final Builder addKeyword(String str) {
            this.zzacv.zzcj(str);
            return this;
        }

        public final Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zzacv.zza(networkExtras);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class<? extends MediationExtrasReceiver> cls, Bundle bundle) {
            this.zzacv.zza(cls, bundle);
            if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.zzacv.zzcl("B3EEABB8EE11C2BE770B684D95219ECB");
            }
            return this;
        }

        public final Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zzacv.zzb(cls, bundle);
            return this;
        }

        @Deprecated
        public final Builder addTestDevice(String str) {
            this.zzacv.zzck(str);
            return this;
        }

        public final AdRequest build() {
            return new AdRequest(this);
        }

        @Deprecated
        public final Builder setBirthday(Date date) {
            this.zzacv.zza(date);
            return this;
        }

        public final Builder setContentUrl(String str) {
            Preconditions.checkNotNull(str, "Content URL must be non-null.");
            Preconditions.checkNotEmpty(str, "Content URL must be non-empty.");
            Preconditions.checkArgument(str.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, Integer.valueOf(str.length()));
            this.zzacv.zzcm(str);
            return this;
        }

        public final Builder setNeighboringContentUrls(List<String> list) {
            if (list == null) {
                zzbbq.zzfe("neighboring content URLs list should not be null");
                return this;
            }
            this.zzacv.zzc(list);
            return this;
        }

        @Deprecated
        public final Builder setGender(int i) {
            this.zzacv.zzda(i);
            return this;
        }

        public final Builder setLocation(Location location) {
            this.zzacv.zza(location);
            return this;
        }

        public final Builder setRequestAgent(String str) {
            this.zzacv.zzco(str);
            return this;
        }

        @Deprecated
        public final Builder tagForChildDirectedTreatment(boolean z) {
            this.zzacv.zzz(z);
            return this;
        }

        @Deprecated
        public final Builder setIsDesignedForFamilies(boolean z) {
            this.zzacv.zzaa(z);
            return this;
        }

        public final Builder setAdInfo(AdInfo adInfo) {
            this.zzacv.zza(adInfo);
            return this;
        }

        @Deprecated
        public final Builder setTagForUnderAgeOfConsent(int i) {
            this.zzacv.zzdb(i);
            return this;
        }

        @Deprecated
        public final Builder setMaxAdContentRating(String str) {
            this.zzacv.zzcq(str);
            return this;
        }
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzacu.getBirthday();
    }

    public final String getContentUrl() {
        return this.zzacu.getContentUrl();
    }

    public final List<String> getNeighboringContentUrls() {
        return this.zzacu.zzqn();
    }

    @Deprecated
    public final int getGender() {
        return this.zzacu.getGender();
    }

    public final Set<String> getKeywords() {
        return this.zzacu.getKeywords();
    }

    public final Location getLocation() {
        return this.zzacu.getLocation();
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzacu.getNetworkExtras(cls);
    }

    public final <T extends MediationExtrasReceiver> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzacu.getNetworkExtrasBundle(cls);
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzacu.getCustomEventExtrasBundle(cls);
    }

    public final boolean isTestDevice(Context context) {
        return this.zzacu.isTestDevice(context);
    }

    public final zzyw zzdr() {
        return this.zzacu;
    }
}
