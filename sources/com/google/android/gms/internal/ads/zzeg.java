package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeg {
    private static final String[] zzyb = {"/aclk", "/pcs/click", "/dbm/clk"};
    private String zzxx = "googleads.g.doubleclick.net";
    private String zzxy = "/pagead/ads";
    private String zzxz = "ad.doubleclick.net";
    private String[] zzya = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzdw zzyc;

    public zzeg(zzdw zzdw) {
        this.zzyc = zzdw;
    }

    private final boolean zza(Uri uri) {
        if (uri != null) {
            try {
                return uri.getHost().equals(this.zzxz);
            } catch (NullPointerException unused) {
                return false;
            }
        } else {
            throw null;
        }
    }

    public final boolean zzb(Uri uri) {
        if (uri != null) {
            try {
                String host = uri.getHost();
                for (String endsWith : this.zzya) {
                    if (host.endsWith(endsWith)) {
                        return true;
                    }
                }
            } catch (NullPointerException unused) {
            }
            return false;
        }
        throw null;
    }

    public final zzdw zzca() {
        return this.zzyc;
    }

    public final Uri zza(Uri uri, Context context) throws zzef {
        return zza(uri, this.zzyc.zzb(context));
    }

    public final void zza(MotionEvent motionEvent) {
        this.zzyc.zza(motionEvent);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzef {
        try {
            return zza(uri, this.zzyc.zza(context, uri.getQueryParameter("ai"), view, activity));
        } catch (UnsupportedOperationException unused) {
            throw new zzef("Provided Uri is not in a valid state");
        }
    }

    public final boolean zzc(Uri uri) {
        if (zzb(uri)) {
            for (String endsWith : zzyb) {
                if (uri.getPath().endsWith(endsWith)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final Uri zza(Uri uri, String str) throws zzef {
        try {
            boolean zza = zza(uri);
            if (zza) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzef("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzef("Query parameter already exists: ms");
            }
            if (zza) {
                String uri2 = uri.toString();
                int indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    int i = indexOf + 1;
                    return Uri.parse(uri2.substring(0, i) + "dc_ms" + "=" + str + ";" + uri2.substring(i));
                }
                String encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                return Uri.parse(uri2.substring(0, encodedPath.length() + indexOf2) + ";" + "dc_ms" + "=" + str + ";" + uri2.substring(indexOf2 + encodedPath.length()));
            }
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter("ms", str).build();
            }
            int i2 = indexOf3 + 1;
            return Uri.parse(uri3.substring(0, i2) + "ms" + "=" + str + "&" + uri3.substring(i2));
        } catch (UnsupportedOperationException unused) {
            throw new zzef("Provided Uri is not in a valid state");
        }
    }
}
