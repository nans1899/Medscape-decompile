package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import com.appboy.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzbfy;
import com.google.android.gms.internal.ads.zzbhi;
import com.google.android.gms.internal.ads.zzbhl;
import com.google.android.gms.internal.ads.zzbhp;
import com.google.android.gms.internal.ads.zzbhq;
import com.google.android.gms.internal.ads.zzbhs;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.net.URISyntaxException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzahu<T extends zzbfy & zzbhi & zzbhl & zzbhp & zzbhq & zzbhs> implements zzahq<T> {
    private final zza zzdfm;
    private final zzaqd zzdfn;

    public zzahu(zza zza, zzaqd zzaqd) {
        this.zzdfm = zza;
        this.zzdfn = zzaqd;
    }

    private static boolean zzd(Map<String, String> map) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close"));
    }

    private static int zze(Map<String, String> map) {
        String str = map.get("o");
        if (str == null) {
            return -1;
        }
        if ("p".equalsIgnoreCase(str)) {
            zzp.zzkt();
            return 7;
        } else if ("l".equalsIgnoreCase(str)) {
            zzp.zzkt();
            return 6;
        } else if ("c".equalsIgnoreCase(str)) {
            return zzp.zzkt().zzxw();
        } else {
            return -1;
        }
    }

    private final void zzab(boolean z) {
        zzaqd zzaqd = this.zzdfn;
        if (zzaqd != null) {
            zzaqd.zzac(z);
        }
    }

    static Uri zza(Context context, zzeg zzeg, Uri uri, View view, Activity activity) {
        if (zzeg == null) {
            return uri;
        }
        try {
            if (zzeg.zzc(uri)) {
                return zzeg.zza(uri, context, view, activity);
            }
            return uri;
        } catch (zzef unused) {
            return uri;
        } catch (Exception e) {
            zzp.zzkv().zza(e, "OpenGmsgHandler.maybeAddClickSignalsToUri");
            return uri;
        }
    }

    static Uri zzf(Uri uri) {
        try {
            if (uri.getQueryParameter("aclk_ms") != null) {
                return uri.buildUpon().appendQueryParameter("aclk_upms", String.valueOf(SystemClock.uptimeMillis())).build();
            }
        } catch (UnsupportedOperationException e) {
            String valueOf = String.valueOf(uri.toString());
            zzayp.zzc(valueOf.length() != 0 ? "Error adding click uptime parameter to url: ".concat(valueOf) : new String("Error adding click uptime parameter to url: "), e);
        }
        return uri;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbfy zzbfy = (zzbfy) obj;
        zzbhi zzbhi = (zzbhi) zzbfy;
        String zzc = zzaxh.zzc((String) map.get("u"), zzbhi.getContext(), true);
        String str = (String) map.get(Constants.APPBOY_PUSH_CONTENT_KEY);
        if (str == null) {
            zzayp.zzfe("Action missing from an open GMSG.");
            return;
        }
        zza zza = this.zzdfm;
        if (zza != null && !zza.zzjz()) {
            this.zzdfm.zzbo(zzc);
        } else if ("expand".equalsIgnoreCase(str)) {
            if (((zzbhl) zzbfy).zzacb()) {
                zzayp.zzfe("Cannot expand WebView that is already expanded.");
                return;
            }
            zzab(false);
            ((zzbhp) zzbfy).zzb(zzd(map), zze(map));
        } else if ("webapp".equalsIgnoreCase(str)) {
            zzab(false);
            if (zzc != null) {
                ((zzbhp) zzbfy).zza(zzd(map), zze(map), zzc);
            } else {
                ((zzbhp) zzbfy).zza(zzd(map), zze(map), (String) map.get("html"), (String) map.get("baseurl"));
            }
        } else if (AdParameterKeys.SECTION_ID.equalsIgnoreCase(str) && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase((String) map.get("system_browser"))) {
            zzab(true);
            if (TextUtils.isEmpty(zzc)) {
                zzayp.zzfe("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzbhp) zzbfy).zza(new zzb(new zzaht(zzbhi.getContext(), ((zzbhq) zzbfy).zzabz(), ((zzbhs) zzbfy).getView()).zzc(map)));
            } catch (ActivityNotFoundException e) {
                zzayp.zzfe(e.getMessage());
            }
        } else if ("open_app".equalsIgnoreCase(str)) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyl)).booleanValue()) {
                zzab(true);
                String str2 = (String) map.get("p");
                if (str2 == null) {
                    zzayp.zzfe("Package name missing from open app action.");
                    return;
                }
                PackageManager packageManager = zzbhi.getContext().getPackageManager();
                if (packageManager == null) {
                    zzayp.zzfe("Cannot get package manager from open app action.");
                    return;
                }
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str2);
                if (launchIntentForPackage != null) {
                    ((zzbhp) zzbfy).zza(new zzb(launchIntentForPackage));
                }
            }
        } else {
            zzab(true);
            String str3 = (String) map.get("intent_url");
            Intent intent = null;
            if (!TextUtils.isEmpty(str3)) {
                try {
                    intent = Intent.parseUri(str3, 0);
                } catch (URISyntaxException e2) {
                    String valueOf = String.valueOf(str3);
                    zzayp.zzc(valueOf.length() != 0 ? "Error parsing the url: ".concat(valueOf) : new String("Error parsing the url: "), e2);
                }
            }
            if (!(intent == null || intent.getData() == null)) {
                Uri data = intent.getData();
                if (!Uri.EMPTY.equals(data)) {
                    Uri zzf = zzf(zza(zzbhi.getContext(), ((zzbhq) zzbfy).zzabz(), data, ((zzbhs) zzbfy).getView(), zzbhi.zzzu()));
                    if (!TextUtils.isEmpty(intent.getType())) {
                        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcym)).booleanValue()) {
                            intent.setDataAndType(zzf, intent.getType());
                        }
                    }
                    intent.setData(zzf);
                }
            }
            if (intent != null) {
                ((zzbhp) zzbfy).zza(new zzb(intent));
                return;
            }
            if (!TextUtils.isEmpty(zzc)) {
                zzc = zzf(zza(zzbhi.getContext(), ((zzbhq) zzbfy).zzabz(), Uri.parse(zzc), ((zzbhs) zzbfy).getView(), zzbhi.zzzu())).toString();
            }
            ((zzbhp) zzbfy).zza(new zzb((String) map.get("i"), zzc, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
        }
    }
}
