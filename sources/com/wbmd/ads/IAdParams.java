package com.wbmd.ads;

import com.google.android.gms.ads.AdSize;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003j\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u0001`\u0005H&J\u0015\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u0004H&J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0004H&J\u0015\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, d2 = {"Lcom/wbmd/ads/IAdParams;", "", "getADParams", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getAdSizes", "", "Lcom/google/android/gms/ads/AdSize;", "()[Lcom/google/android/gms/ads/AdSize;", "getAdUnitID", "getCurrentPageName", "getNativeTemplates", "()[Ljava/lang/String;", "getPosValue", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: IAdParams.kt */
public interface IAdParams {
    HashMap<String, String> getADParams();

    AdSize[] getAdSizes();

    String getAdUnitID();

    String getCurrentPageName();

    String[] getNativeTemplates();

    int getPosValue();
}
