package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ConditionVariable;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaax implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final Object lock = new Object();
    private Bundle metaData = new Bundle();
    private volatile boolean zzckl = false;
    private final ConditionVariable zzclm = new ConditionVariable();
    /* access modifiers changed from: private */
    public SharedPreferences zzcln = null;
    private Context zzclo;
    private JSONObject zzclp = new JSONObject();
    private volatile boolean zzzh = false;

    public final void initialize(Context context) {
        if (!this.zzzh) {
            synchronized (this.lock) {
                if (!this.zzzh) {
                    if (!this.zzckl) {
                        this.zzckl = true;
                    }
                    Context applicationContext = context.getApplicationContext() == null ? context : context.getApplicationContext();
                    this.zzclo = applicationContext;
                    try {
                        this.metaData = Wrappers.packageManager(applicationContext).getApplicationInfo(this.zzclo.getPackageName(), 128).metaData;
                    } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
                    }
                    try {
                        Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                        if (remoteContext == null && context != null) {
                            Context applicationContext2 = context.getApplicationContext();
                            if (applicationContext2 != null) {
                                context = applicationContext2;
                            }
                            remoteContext = context;
                        }
                        if (remoteContext != null) {
                            zzwm.zzpv();
                            SharedPreferences sharedPreferences = remoteContext.getSharedPreferences("google_ads_flags", 0);
                            this.zzcln = sharedPreferences;
                            if (sharedPreferences != null) {
                                sharedPreferences.registerOnSharedPreferenceChangeListener(this);
                            }
                            zzadj.zza(new zzabc(this));
                            zzri();
                            this.zzzh = true;
                            this.zzckl = false;
                            this.zzclm.open();
                        }
                    } finally {
                        this.zzckl = false;
                        this.zzclm.open();
                    }
                }
            }
        }
    }

    public final <T> T zzd(zzaaq<T> zzaaq) {
        if (!this.zzclm.block(CoroutineLiveDataKt.DEFAULT_TIMEOUT)) {
            synchronized (this.lock) {
                if (!this.zzckl) {
                    throw new IllegalStateException("Flags.initialize() was not called!");
                }
            }
        }
        if (!this.zzzh || this.zzcln == null) {
            synchronized (this.lock) {
                if (this.zzzh) {
                    if (this.zzcln == null) {
                    }
                }
                T zzrf = zzaaq.zzrf();
                return zzrf;
            }
        }
        if (zzaaq.getSource() == 2) {
            Bundle bundle = this.metaData;
            if (bundle == null) {
                return zzaaq.zzrf();
            }
            return zzaaq.zza(bundle);
        } else if (zzaaq.getSource() != 1 || !this.zzclp.has(zzaaq.getKey())) {
            return zzbay.zza(new zzaba(this, zzaaq));
        } else {
            return zzaaq.zzb(this.zzclp);
        }
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("flag_configuration".equals(str)) {
            zzri();
        }
    }

    private final void zzri() {
        if (this.zzcln != null) {
            try {
                this.zzclp = new JSONObject((String) zzbay.zza(new zzaaz(this)));
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzrj() {
        return this.zzcln.getString("flag_configuration", "{}");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zze(zzaaq zzaaq) {
        return zzaaq.zza(this.zzcln);
    }
}
