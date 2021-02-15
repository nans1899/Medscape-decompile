package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaqo extends zzaqn implements zzahq<zzbgj> {
    private float density;
    private int maxHeight = -1;
    private int maxWidth = -1;
    private int rotation;
    private final WindowManager zzbrj;
    private final zzbgj zzdgy;
    private final zzaam zzdob;
    private int zzdoc = -1;
    private int zzdod = -1;
    private int zzdoe = -1;
    private int zzdof = -1;
    private final Context zzvr;
    private DisplayMetrics zzxd;

    public zzaqo(zzbgj zzbgj, Context context, zzaam zzaam) {
        super(zzbgj);
        this.zzdgy = zzbgj;
        this.zzvr = context;
        this.zzdob = zzaam;
        this.zzbrj = (WindowManager) context.getSystemService("window");
    }

    public final void zzj(int i, int i2) {
        int i3 = 0;
        if (this.zzvr instanceof Activity) {
            i3 = zzp.zzkr().zzf((Activity) this.zzvr)[0];
        }
        if (this.zzdgy.zzabu() == null || !this.zzdgy.zzabu().zzadb()) {
            int width = this.zzdgy.getWidth();
            int height = this.zzdgy.getHeight();
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcnc)).booleanValue()) {
                if (width == 0 && this.zzdgy.zzabu() != null) {
                    width = this.zzdgy.zzabu().widthPixels;
                }
                if (height == 0 && this.zzdgy.zzabu() != null) {
                    height = this.zzdgy.zzabu().heightPixels;
                }
            }
            this.zzdoe = zzwm.zzpt().zzb(this.zzvr, width);
            this.zzdof = zzwm.zzpt().zzb(this.zzvr, height);
        }
        zzb(i, i2 - i3, this.zzdoe, this.zzdof);
        this.zzdgy.zzabw().zzi(i, i2);
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        this.zzxd = new DisplayMetrics();
        Display defaultDisplay = this.zzbrj.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzxd);
        this.density = this.zzxd.density;
        this.rotation = defaultDisplay.getRotation();
        zzwm.zzpt();
        DisplayMetrics displayMetrics = this.zzxd;
        this.zzdoc = zzbbg.zzb(displayMetrics, displayMetrics.widthPixels);
        zzwm.zzpt();
        DisplayMetrics displayMetrics2 = this.zzxd;
        this.zzdod = zzbbg.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzzu = this.zzdgy.zzzu();
        if (zzzu == null || zzzu.getWindow() == null) {
            this.maxWidth = this.zzdoc;
            this.maxHeight = this.zzdod;
        } else {
            zzp.zzkr();
            int[] zzd = zzayu.zzd(zzzu);
            zzwm.zzpt();
            this.maxWidth = zzbbg.zzb(this.zzxd, zzd[0]);
            zzwm.zzpt();
            this.maxHeight = zzbbg.zzb(this.zzxd, zzd[1]);
        }
        if (this.zzdgy.zzabu().zzadb()) {
            this.zzdoe = this.zzdoc;
            this.zzdof = this.zzdod;
        } else {
            this.zzdgy.measure(0, 0);
        }
        zza(this.zzdoc, this.zzdod, this.maxWidth, this.maxHeight, this.density, this.rotation);
        this.zzdgy.zza("onDeviceFeaturesReceived", new zzaqj(new zzaql().zzae(this.zzdob.zzrb()).zzad(this.zzdob.zzrc()).zzaf(this.zzdob.zzre()).zzag(this.zzdob.zzrd()).zzah(true)).zzdp());
        int[] iArr = new int[2];
        this.zzdgy.getLocationOnScreen(iArr);
        zzj(zzwm.zzpt().zzb(this.zzvr, iArr[0]), zzwm.zzpt().zzb(this.zzvr, iArr[1]));
        if (zzayp.isLoggable(2)) {
            zzayp.zzfd("Dispatching Ready Event.");
        }
        zzdy(this.zzdgy.zzzx().zzbre);
    }
}
