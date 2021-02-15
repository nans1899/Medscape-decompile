package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.google.android.gms.internal.ads.zzbbg;
import com.google.android.gms.internal.ads.zzwm;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzo extends FrameLayout implements View.OnClickListener {
    private final ImageButton zzdpu;
    private final zzw zzdpv;

    public zzo(Context context, zzr zzr, zzw zzw) {
        super(context);
        this.zzdpv = zzw;
        setOnClickListener(this);
        ImageButton imageButton = new ImageButton(context);
        this.zzdpu = imageButton;
        imageButton.setImageResource(17301527);
        this.zzdpu.setBackgroundColor(0);
        this.zzdpu.setOnClickListener(this);
        ImageButton imageButton2 = this.zzdpu;
        zzwm.zzpt();
        int zzc = zzbbg.zzc(context, zzr.paddingLeft);
        zzwm.zzpt();
        int zzc2 = zzbbg.zzc(context, 0);
        zzwm.zzpt();
        int zzc3 = zzbbg.zzc(context, zzr.paddingRight);
        zzwm.zzpt();
        imageButton2.setPadding(zzc, zzc2, zzc3, zzbbg.zzc(context, zzr.paddingBottom));
        this.zzdpu.setContentDescription("Interstitial close button");
        ImageButton imageButton3 = this.zzdpu;
        zzwm.zzpt();
        int zzc4 = zzbbg.zzc(context, zzr.size + zzr.paddingLeft + zzr.paddingRight);
        zzwm.zzpt();
        addView(imageButton3, new FrameLayout.LayoutParams(zzc4, zzbbg.zzc(context, zzr.size + zzr.paddingBottom), 17));
    }

    public final void onClick(View view) {
        zzw zzw = this.zzdpv;
        if (zzw != null) {
            zzw.zzus();
        }
    }

    public final void zzal(boolean z) {
        if (z) {
            this.zzdpu.setVisibility(8);
        } else {
            this.zzdpu.setVisibility(0);
        }
    }
}
