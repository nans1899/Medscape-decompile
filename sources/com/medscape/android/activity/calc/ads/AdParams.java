package com.medscape.android.activity.calc.ads;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.parser.model.UserProfile;
import com.wbmd.ads.IAdParams;
import com.wbmd.omniture.OmnitureState;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005\u0012,\b\u0002\u0010\t\u001a&\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\nj\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u0001`\u000b¢\u0006\u0002\u0010\fJ,\u0010\u000f\u001a&\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\nj\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u0001`\u000bH\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\bH\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0016J\u0015\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0003H\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\rR2\u0010\t\u001a&\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\nj\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u0001`\u000bX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/medscape/android/activity/calc/ads/AdParams;", "Lcom/wbmd/ads/IAdParams;", "posValue", "", "adSizes", "", "Lcom/google/android/gms/ads/AdSize;", "nativeTemplates", "", "additionalAdData", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "(I[Lcom/google/android/gms/ads/AdSize;[Ljava/lang/String;Ljava/util/HashMap;)V", "[Lcom/google/android/gms/ads/AdSize;", "[Ljava/lang/String;", "getADParams", "getAdSizes", "()[Lcom/google/android/gms/ads/AdSize;", "getAdUnitID", "getCurrentPageName", "getNativeTemplates", "()[Ljava/lang/String;", "getPosValue", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdParams.kt */
public final class AdParams implements IAdParams {
    private final AdSize[] adSizes;
    private final HashMap<String, String> additionalAdData;
    private final String[] nativeTemplates;
    private final int posValue;

    public String getAdUnitID() {
        return DFPReferenceAdListener.AD_UNIT_ID;
    }

    public AdParams(int i, AdSize[] adSizeArr, String[] strArr, HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(adSizeArr, "adSizes");
        this.posValue = i;
        this.adSizes = adSizeArr;
        this.nativeTemplates = strArr;
        this.additionalAdData = hashMap;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AdParams(int i, AdSize[] adSizeArr, String[] strArr, HashMap hashMap, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, adSizeArr, (i2 & 4) != 0 ? null : strArr, (i2 & 8) != 0 ? null : hashMap);
    }

    public int getPosValue() {
        return this.posValue;
    }

    public AdSize[] getAdSizes() {
        return this.adSizes;
    }

    public String[] getNativeTemplates() {
        return this.nativeTemplates;
    }

    public HashMap<String, String> getADParams() {
        HashMap<String, String> hashMap;
        new HashMap();
        new HashMap();
        AdsSegvar instance = AdsSegvar.getInstance();
        MedscapeApplication medscapeApplication = MedscapeApplication.get();
        Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
        HashMap<String, String> globalMap = instance.getGlobalMap(medscapeApplication.getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(globalMap, "AdsSegvar.getInstance().…get().applicationContext)");
        AdsSegvar instance2 = AdsSegvar.getInstance();
        if (UserProfileProvider.INSTANCE.getUserProfile() != null) {
            UserProfile userProfile = UserProfileProvider.INSTANCE.getUserProfile();
            Intrinsics.checkNotNull(userProfile);
            if (userProfile.getTidMap().get(NativeAppInstallAd.ASSET_HEADLINE) != null) {
                UserProfile userProfile2 = UserProfileProvider.INSTANCE.getUserProfile();
                Intrinsics.checkNotNull(userProfile2);
                globalMap.put("tar", userProfile2.getTidMap().get(NativeAppInstallAd.ASSET_HEADLINE));
            }
            hashMap = instance2.createProfileSpecificDataMap();
            Intrinsics.checkNotNullExpressionValue(hashMap, "adsSegvar.createProfileSpecificDataMap()");
        } else {
            Intrinsics.checkNotNullExpressionValue(instance2, "adsSegvar");
            hashMap = instance2.getProileSpecificDataMap();
            Intrinsics.checkNotNullExpressionValue(hashMap, "adsSegvar.proileSpecificDataMap");
        }
        globalMap.putAll(hashMap);
        Intrinsics.checkNotNullExpressionValue(instance2, "adsSegvar");
        globalMap.putAll(instance2.getPageOverProileSpecificDataMap());
        globalMap.put(AdsConstants.CN, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        Map map = this.additionalAdData;
        if (!(map == null || map.isEmpty())) {
            globalMap.putAll(this.additionalAdData);
        }
        return globalMap;
    }

    public String getCurrentPageName() {
        return OmnitureState.Companion.getInstance().getReferringPage();
    }
}
