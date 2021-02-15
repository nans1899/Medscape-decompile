package com.wbmd.ads;

import com.google.android.gms.ads.AdSize;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005H&J\u0015\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/wbmd/ads/IAdConversions;", "", "getAdConversionSizes", "Ljava/util/HashMap;", "Lcom/google/android/gms/ads/AdSize;", "Lkotlin/collections/HashMap;", "getBlockerAdSize", "", "()[Lcom/google/android/gms/ads/AdSize;", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: IAdConversions.kt */
public interface IAdConversions {
    HashMap<AdSize, AdSize> getAdConversionSizes();

    AdSize[] getBlockerAdSize();
}
