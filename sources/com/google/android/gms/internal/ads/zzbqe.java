package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.zzp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqe extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private View zzaat;
    private final Context zzvr;

    public static zzbqe zza(Context context, View view, zzdnv zzdnv) {
        zzbqe zzbqe = new zzbqe(context);
        zzbqe.zzaat = view;
        zzbqe.addView(view);
        zzp.zzlo();
        zzbco.zza((View) zzbqe, (ViewTreeObserver.OnScrollChangedListener) zzbqe);
        zzp.zzlo();
        zzbco.zza((View) zzbqe, (ViewTreeObserver.OnGlobalLayoutListener) zzbqe);
        JSONObject jSONObject = zzdnv.zzhej;
        if (jSONObject != null) {
            RelativeLayout relativeLayout = new RelativeLayout(zzbqe.zzvr);
            JSONObject optJSONObject = jSONObject.optJSONObject("header");
            if (optJSONObject != null) {
                zzbqe.zza(optJSONObject, relativeLayout, 10);
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("footer");
            if (optJSONObject2 != null) {
                zzbqe.zza(optJSONObject2, relativeLayout, 12);
            }
            zzbqe.addView(relativeLayout);
        }
        return zzbqe;
    }

    private zzbqe(Context context) {
        super(context);
        this.zzvr = context;
    }

    public final void onScrollChanged() {
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        this.zzaat.setY((float) (-iArr[1]));
    }

    public final void onGlobalLayout() {
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        this.zzaat.setY((float) (-iArr[1]));
    }

    private final void zza(JSONObject jSONObject, RelativeLayout relativeLayout, int i) {
        TextView textView = new TextView(this.zzvr);
        textView.setTextColor(-1);
        textView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setGravity(17);
        textView.setText(jSONObject.optString("text", ""));
        textView.setTextSize((float) jSONObject.optDouble("text_size", 11.0d));
        int zzb = zzb(jSONObject.optDouble("padding", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        textView.setPadding(0, zzb, 0, zzb);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, zzb(jSONObject.optDouble(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, 15.0d)));
        layoutParams.addRule(i);
        relativeLayout.addView(textView, layoutParams);
    }

    private final int zzb(double d) {
        zzwm.zzpt();
        return zzbbg.zzc(this.zzvr, (int) d);
    }
}
