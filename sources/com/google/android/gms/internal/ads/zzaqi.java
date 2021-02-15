package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaqi extends zzaqn {
    private final Map<String, String> zzczo;
    /* access modifiers changed from: private */
    public final Context zzvr;

    public zzaqi(zzbgj zzbgj, Map<String, String> map) {
        super(zzbgj, "storePicture");
        this.zzczo = map;
        this.zzvr = zzbgj.zzzu();
    }

    public final void execute() {
        if (this.zzvr == null) {
            zzdx("Activity context is not available");
            return;
        }
        zzp.zzkr();
        if (!zzayu.zzat(this.zzvr).zzrd()) {
            zzdx("Feature is not supported by the device.");
            return;
        }
        String str = this.zzczo.get("iurl");
        if (TextUtils.isEmpty(str)) {
            zzdx("Image url cannot be empty.");
        } else if (!URLUtil.isValidUrl(str)) {
            String valueOf = String.valueOf(str);
            zzdx(valueOf.length() != 0 ? "Invalid image url: ".concat(valueOf) : new String("Invalid image url: "));
        } else {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            zzp.zzkr();
            if (!zzayu.zzeo(lastPathSegment)) {
                String valueOf2 = String.valueOf(lastPathSegment);
                zzdx(valueOf2.length() != 0 ? "Image type not recognized: ".concat(valueOf2) : new String("Image type not recognized: "));
                return;
            }
            Resources resources = zzp.zzkv().getResources();
            zzp.zzkr();
            AlertDialog.Builder zzas = zzayu.zzas(this.zzvr);
            zzas.setTitle(resources != null ? resources.getString(R.string.s1) : "Save image");
            zzas.setMessage(resources != null ? resources.getString(R.string.s2) : "Allow Ad to store image in Picture gallery?");
            zzas.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzaqh(this, str, lastPathSegment));
            zzas.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzaqk(this));
            zzas.create().show();
        }
    }
}
