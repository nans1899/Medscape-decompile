package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzte extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzte> CREATOR = new zztd();
    public final String url;
    private final long zzbve;
    private final String zzbvf;
    private final String zzbvg;
    private final String zzbvh;
    private final Bundle zzbvi;
    public final boolean zzbvj;
    public long zzbvk;

    public static zzte zzbw(String str) {
        return zzd(Uri.parse(str));
    }

    public static zzte zzd(Uri uri) {
        long j;
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                int size = pathSegments.size();
                StringBuilder sb = new StringBuilder(62);
                sb.append("Expected 2 path parts for namespace and id, found :");
                sb.append(size);
                zzayp.zzfe(sb.toString());
                return null;
            }
            String str = pathSegments.get(0);
            String str2 = pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            if (queryParameter2 == null) {
                j = 0;
            } else {
                j = Long.parseLong(queryParameter2);
            }
            long j2 = j;
            Bundle bundle = new Bundle();
            zzp.zzkt();
            for (String next : uri.getQueryParameterNames()) {
                if (next.startsWith("tag.")) {
                    bundle.putString(next.substring(4), uri.getQueryParameter(next));
                }
            }
            return new zzte(queryParameter, j2, host, str, str2, bundle, equals, 0);
        } catch (NullPointerException | NumberFormatException e) {
            zzayp.zzd("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    zzte(String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z, long j2) {
        this.url = str;
        this.zzbve = j;
        this.zzbvf = str2 == null ? "" : str2;
        this.zzbvg = str3 == null ? "" : str3;
        this.zzbvh = str4 == null ? "" : str4;
        this.zzbvi = bundle == null ? new Bundle() : bundle;
        this.zzbvj = z;
        this.zzbvk = j2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzbve);
        SafeParcelWriter.writeString(parcel, 4, this.zzbvf, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbvg, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbvh, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzbvi, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbvj);
        SafeParcelWriter.writeLong(parcel, 9, this.zzbvk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
