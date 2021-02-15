package com.google.android.gms.internal.ads;

import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public interface zzcgc extends View.OnClickListener, View.OnTouchListener {
    void zza(String str, View view, boolean z);

    View zzahx();

    FrameLayout zzana();

    zzqq zzanb();

    Map<String, WeakReference<View>> zzanc();

    Map<String, WeakReference<View>> zzand();

    Map<String, WeakReference<View>> zzane();

    String zzanf();

    IObjectWrapper zzang();

    View zzgf(String str);

    JSONObject zzsi();
}
