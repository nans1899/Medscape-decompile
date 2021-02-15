package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.common.internal.Preconditions;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.ArrayList;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcb implements zzdgu<Bundle> {
    private final String zzacy;
    private final zzvn zzbpf;
    private final float zzbsa;
    private final boolean zzchv;
    private final int zzdoc;
    private final int zzdod;
    private final String zzgvg;
    private final String zzgvh;
    private final boolean zzgvi;

    public zzdcb(zzvn zzvn, String str, boolean z, String str2, float f, int i, int i2, String str3, boolean z2) {
        Preconditions.checkNotNull(zzvn, "the adSize must not be null");
        this.zzbpf = zzvn;
        this.zzacy = str;
        this.zzchv = z;
        this.zzgvg = str2;
        this.zzbsa = f;
        this.zzdoc = i;
        this.zzdod = i2;
        this.zzgvh = str3;
        this.zzgvi = z2;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        zzdot.zza(bundle, "smart_w", MessengerShareContentUtility.WEBVIEW_RATIO_FULL, this.zzbpf.width == -1);
        zzdot.zza(bundle, "smart_h", DebugKt.DEBUG_PROPERTY_VALUE_AUTO, this.zzbpf.height == -2);
        zzdot.zza(bundle, "ene", (Boolean) true, this.zzbpf.zzchw);
        zzdot.zza(bundle, "rafmt", "102", this.zzbpf.zzchz);
        zzdot.zza(bundle, "rafmt", "103", this.zzbpf.zzcia);
        zzdot.zza(bundle, "inline_adaptive_slot", (Boolean) true, this.zzgvi);
        zzdot.zza(bundle, "format", this.zzacy);
        zzdot.zza(bundle, "fluid", GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, this.zzchv);
        String str = this.zzgvg;
        zzdot.zza(bundle, "sz", str, !TextUtils.isEmpty(str));
        bundle.putFloat("u_sd", this.zzbsa);
        bundle.putInt("sw", this.zzdoc);
        bundle.putInt("sh", this.zzdod);
        String str2 = this.zzgvh;
        zzdot.zza(bundle, AdParameterKeys.CONDITION, str2, true ^ TextUtils.isEmpty(str2));
        ArrayList arrayList = new ArrayList();
        if (this.zzbpf.zzchu == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, this.zzbpf.height);
            bundle2.putInt(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH, this.zzbpf.width);
            bundle2.putBoolean("is_fluid_height", this.zzbpf.zzchv);
            arrayList.add(bundle2);
        } else {
            for (zzvn zzvn : this.zzbpf.zzchu) {
                Bundle bundle3 = new Bundle();
                bundle3.putBoolean("is_fluid_height", zzvn.zzchv);
                bundle3.putInt(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, zzvn.height);
                bundle3.putInt(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH, zzvn.width);
                arrayList.add(bundle3);
            }
        }
        bundle.putParcelableArrayList("valid_ad_sizes", arrayList);
    }
}
