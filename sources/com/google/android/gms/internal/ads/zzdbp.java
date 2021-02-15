package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzp;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbp implements zzdgu<Bundle> {
    private final zzvn zzbpf;
    private final List<Parcelable> zzgva;
    private final Context zzvr;

    public zzdbp(Context context, zzvn zzvn, List<Parcelable> list) {
        this.zzvr = context;
        this.zzbpf = zzvn;
        this.zzgva = list;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        if (zzacy.zzdbw.get().booleanValue()) {
            Bundle bundle2 = new Bundle();
            zzp.zzkr();
            bundle2.putString("activity", zzayu.zzau(this.zzvr));
            Bundle bundle3 = new Bundle();
            bundle3.putInt(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH, this.zzbpf.width);
            bundle3.putInt(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, this.zzbpf.height);
            bundle2.putBundle("size", bundle3);
            if (this.zzgva.size() > 0) {
                List<Parcelable> list = this.zzgva;
                bundle2.putParcelableArray("parents", (Parcelable[]) list.toArray(new Parcelable[list.size()]));
            }
            bundle.putBundle("view_hierarchy", bundle2);
        }
    }
}
