package com.medscape.android.activity.calc.ads;

import android.content.Context;
import com.google.android.gms.ads.AdSize;
import com.medscape.android.util.Util;
import com.wbmd.ads.IAdConversions;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\nH\u0016J\u0015\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/medscape/android/activity/calc/ads/AdConversions;", "Lcom/wbmd/ads/IAdConversions;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getAdConversionSizes", "Ljava/util/HashMap;", "Lcom/google/android/gms/ads/AdSize;", "Lkotlin/collections/HashMap;", "getBlockerAdSize", "", "()[Lcom/google/android/gms/ads/AdSize;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdConversions.kt */
public final class AdConversions implements IAdConversions {
    private final Context context;

    public AdConversions(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public AdSize[] getBlockerAdSize() {
        return new AdSize[]{new AdSize(1, 3)};
    }

    public HashMap<AdSize, AdSize> getAdConversionSizes() {
        return MapsKt.hashMapOf(new Pair(new AdSize(3, 1), new AdSize(Util.getDisplayWidthInDp(this.context), 400)));
    }
}
