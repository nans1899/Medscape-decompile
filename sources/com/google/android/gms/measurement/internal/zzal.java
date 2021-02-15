package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.util.Clock;
import com.medscape.android.Constants;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzal extends zzgr {
    private long zza;
    private String zzb;
    private Boolean zzc;
    private AccountManager zzd;
    private Boolean zze;
    private long zzf;

    zzal(zzfv zzfv) {
        super(zzfv);
    }

    /* access modifiers changed from: protected */
    public final boolean zzd() {
        Calendar instance = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert((long) (instance.get(15) + instance.get(16)), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String lowerCase = locale.getLanguage().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
        StringBuilder sb = new StringBuilder(String.valueOf(lowerCase).length() + 1 + String.valueOf(lowerCase2).length());
        sb.append(lowerCase);
        sb.append("-");
        sb.append(lowerCase2);
        this.zzb = sb.toString();
        return false;
    }

    public final long zze() {
        zzaa();
        return this.zza;
    }

    public final String zzf() {
        zzaa();
        return this.zzb;
    }

    public final boolean zza(Context context) {
        if (this.zzc == null) {
            this.zzc = false;
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    packageManager.getPackageInfo("com.google.android.gms", 128);
                    this.zzc = true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return this.zzc.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final long zzg() {
        zzc();
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final void zzh() {
        zzc();
        this.zze = null;
        this.zzf = 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzi() {
        zzc();
        long currentTimeMillis = zzl().currentTimeMillis();
        if (currentTimeMillis - this.zzf > Constants.DAY_IN_MILLIS) {
            this.zze = null;
        }
        Boolean bool = this.zze;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (ContextCompat.checkSelfPermission(zzm(), "android.permission.GET_ACCOUNTS") != 0) {
            zzq().zzi().zza("Permission error checking for dasher/unicorn accounts");
            this.zzf = currentTimeMillis;
            this.zze = false;
            return false;
        }
        if (this.zzd == null) {
            this.zzd = AccountManager.get(zzm());
        }
        try {
            Account[] result = this.zzd.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_HOSTED"}, (AccountManagerCallback) null, (Handler) null).getResult();
            if (result == null || result.length <= 0) {
                Account[] result2 = this.zzd.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_uca"}, (AccountManagerCallback) null, (Handler) null).getResult();
                if (result2 != null && result2.length > 0) {
                    this.zze = true;
                    this.zzf = currentTimeMillis;
                    return true;
                }
                this.zzf = currentTimeMillis;
                this.zze = false;
                return false;
            }
            this.zze = true;
            this.zzf = currentTimeMillis;
            return true;
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            zzq().zzf().zza("Exception checking account types", e);
        }
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzep zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkw zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzfo zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzer zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzfd zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }
}
