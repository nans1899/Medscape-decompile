package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvl {
    public static final zzvl zzchs = new zzvl();

    protected zzvl() {
    }

    public static zzvg zza(Context context, zzyw zzyw) {
        List list;
        Context context2;
        zzuy zzuy;
        String str;
        zzyw zzyw2 = zzyw;
        Date birthday = zzyw.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzyw.getContentUrl();
        int gender = zzyw.getGender();
        Set<String> keywords = zzyw.getKeywords();
        if (!keywords.isEmpty()) {
            list = Collections.unmodifiableList(new ArrayList(keywords));
            context2 = context;
        } else {
            context2 = context;
            list = null;
        }
        boolean isTestDevice = zzyw2.isTestDevice(context2);
        Location location = zzyw.getLocation();
        Bundle networkExtrasBundle = zzyw2.getNetworkExtrasBundle(AdMobAdapter.class);
        if (zzyw.zzqu() != null) {
            zzuy = new zzuy(zzyw.zzqu().getAdString(), zzwm.zzqb().containsKey(zzyw.zzqu().getQueryInfo()) ? zzwm.zzqb().get(zzyw.zzqu().getQueryInfo()) : "");
        } else {
            zzuy = null;
        }
        boolean manualImpressionsEnabled = zzyw.getManualImpressionsEnabled();
        String publisherProvidedId = zzyw.getPublisherProvidedId();
        SearchAdRequest zzqp = zzyw.zzqp();
        zzaag zzaag = zzqp != null ? new zzaag(zzqp) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzwm.zzpt();
            str = zzbbg.zza(Thread.currentThread().getStackTrace(), packageName);
        } else {
            str = null;
        }
        boolean isDesignedForFamilies = zzyw.isDesignedForFamilies();
        RequestConfiguration requestConfiguration = zzzd.zzqw().getRequestConfiguration();
        return new zzvg(8, time, networkExtrasBundle, gender, list, isTestDevice, Math.max(zzyw.zzqs(), requestConfiguration.getTagForChildDirectedTreatment()), manualImpressionsEnabled, publisherProvidedId, zzaag, location, contentUrl, zzyw.zzqr(), zzyw.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzyw.zzqt())), zzyw.zzqo(), str, isDesignedForFamilies, zzuy, Math.max(zzyw.zzqv(), requestConfiguration.getTagForUnderAgeOfConsent()), (String) Collections.max(Arrays.asList(new String[]{zzyw.getMaxAdContentRating(), requestConfiguration.getMaxAdContentRating()}), zzvk.zzchr), zzyw.zzqn());
    }

    public static zzaum zza(Context context, zzyw zzyw, String str) {
        return new zzaum(zza(context, zzyw), str);
    }

    static final /* synthetic */ int zzd(String str, String str2) {
        return RequestConfiguration.zzadq.indexOf(str) - RequestConfiguration.zzadq.indexOf(str2);
    }
}
