package com.google.android.gms.internal.vision;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzbh {
    public static zzcy<zzbe> zzg(Context context) {
        String str = Build.TYPE;
        String str2 = Build.TAGS;
        boolean z = false;
        if ((str.equals("eng") || str.equals("userdebug")) && (str2.contains("dev-keys") || str2.contains("test-keys"))) {
            z = true;
        }
        if (!z) {
            return zzcy.zzcb();
        }
        if (zzas.zzu() && !context.isDeviceProtectedStorage()) {
            context = context.createDeviceProtectedStorageContext();
        }
        zzcy<File> zzh = zzh(context);
        if (zzh.isPresent()) {
            return zzcy.zzb(zza(zzh.get()));
        }
        return zzcy.zzcb();
    }

    private static zzcy<File> zzh(Context context) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
            return file.exists() ? zzcy.zzb(file) : zzcy.zzcb();
        } catch (RuntimeException e) {
            Log.e("HermeticFileOverrides", "no data dir", e);
            return zzcy.zzcb();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private static zzbe zza(File file) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            HashMap hashMap = new HashMap();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, 3);
                    if (split.length != 3) {
                        String valueOf = String.valueOf(readLine);
                        Log.e("HermeticFileOverrides", valueOf.length() != 0 ? "Invalid: ".concat(valueOf) : new String("Invalid: "));
                    } else {
                        String str = split[0];
                        String decode = Uri.decode(split[1]);
                        String decode2 = Uri.decode(split[2]);
                        if (!hashMap.containsKey(str)) {
                            hashMap.put(str, new HashMap());
                        }
                        ((Map) hashMap.get(str)).put(decode, decode2);
                    }
                } else {
                    String valueOf2 = String.valueOf(file);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 7);
                    sb.append("Parsed ");
                    sb.append(valueOf2);
                    Log.i("HermeticFileOverrides", sb.toString());
                    zzbe zzbe = new zzbe(hashMap);
                    bufferedReader.close();
                    return zzbe;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            zzfd.zza(th, th);
        }
        throw th;
    }
}
