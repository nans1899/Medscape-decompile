package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.medscape.android.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzduf {
    private final Context zzvr;
    private final zzdtc zzvw;

    public zzduf(Context context, zzdtc zzdtc) {
        this.zzvr = context;
        this.zzvw = zzdtc;
    }

    private final void zzj(byte[] bArr) {
        if (this.zzvw != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("os.arch:");
            sb.append(zzdwc.OS_ARCH.value());
            sb.append(";");
            try {
                String[] strArr = (String[]) Build.class.getField("SUPPORTED_ABIS").get((Object) null);
                if (strArr != null) {
                    sb.append("supported_abis:");
                    sb.append(Arrays.toString(strArr));
                    sb.append(";");
                }
            } catch (IllegalAccessException | NoSuchFieldException unused) {
            }
            sb.append("CPU_ABI:");
            sb.append(Build.CPU_ABI);
            sb.append(";");
            sb.append("CPU_ABI2:");
            sb.append(Build.CPU_ABI2);
            sb.append(";");
            if (bArr != null) {
                sb.append("ELF:");
                sb.append(Arrays.toString(bArr));
                sb.append(";");
            }
            this.zzvw.zzg((int) Constants.REQUEST_CODE_PICTURE_CHOOSE_SECOND, sb.toString());
        }
    }

    private final String zzawj() {
        HashSet hashSet = new HashSet(Arrays.asList(new String[]{"i686", "armv71"}));
        String value = zzdwc.OS_ARCH.value();
        if (!TextUtils.isEmpty(value) && hashSet.contains(value)) {
            return value;
        }
        try {
            String[] strArr = (String[]) Build.class.getField("SUPPORTED_ABIS").get((Object) null);
            if (strArr != null && strArr.length > 0) {
                return strArr[0];
            }
        } catch (NoSuchFieldException e) {
            zzdtc zzdtc = this.zzvw;
            if (zzdtc != null) {
                zzdtc.zza(2024, 0, (Exception) e);
            }
        } catch (IllegalAccessException e2) {
            zzdtc zzdtc2 = this.zzvw;
            if (zzdtc2 != null) {
                zzdtc2.zza(2024, 0, (Exception) e2);
            }
        }
        if (Build.CPU_ABI != null) {
            return Build.CPU_ABI;
        }
        return Build.CPU_ABI2;
    }

    private final zzgo zzawk() {
        FileInputStream fileInputStream;
        File file = new File(new File(this.zzvr.getApplicationInfo().dataDir), "lib");
        if (!file.exists()) {
            return zzgo.UNSUPPORTED;
        }
        File[] listFiles = file.listFiles(new zzdxk(Pattern.compile(".*\\.so$", 2)));
        if (listFiles == null || listFiles.length == 0) {
            return zzgo.UNSUPPORTED;
        }
        try {
            fileInputStream = new FileInputStream(listFiles[0]);
            byte[] bArr = new byte[20];
            if (fileInputStream.read(bArr) == 20) {
                byte[] bArr2 = {0, 0};
                if (bArr[5] == 2) {
                    zzj(bArr);
                    zzgo zzgo = zzgo.UNSUPPORTED;
                    fileInputStream.close();
                    return zzgo;
                }
                bArr2[0] = bArr[19];
                bArr2[1] = bArr[18];
                short s = ByteBuffer.wrap(bArr2).getShort();
                if (s == 3) {
                    zzgo zzgo2 = zzgo.X86;
                    fileInputStream.close();
                    return zzgo2;
                } else if (s == 40) {
                    zzgo zzgo3 = zzgo.ARM7;
                    fileInputStream.close();
                    return zzgo3;
                } else if (s == 62) {
                    zzgo zzgo4 = zzgo.X86_64;
                    fileInputStream.close();
                    return zzgo4;
                } else if (s != 183) {
                    zzgo zzgo5 = zzgo.UNSUPPORTED;
                    fileInputStream.close();
                    return zzgo5;
                } else {
                    zzgo zzgo6 = zzgo.ARM64;
                    fileInputStream.close();
                    return zzgo6;
                }
            } else {
                fileInputStream.close();
                return zzgo.UNSUPPORTED;
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            zzehy.zza(th, th);
        }
        throw th;
    }

    public final zzgo zzawl() {
        zzgo zzawk = zzawk();
        if (zzawk != zzgo.UNSUPPORTED) {
            return zzawk;
        }
        String zzawj = zzawj();
        if (!TextUtils.isEmpty(zzawj)) {
            if (zzawj.equalsIgnoreCase("i686") || zzawj.equalsIgnoreCase("x86")) {
                return zzgo.X86;
            }
            if (zzawj.equalsIgnoreCase("x86_64")) {
                return zzgo.X86_64;
            }
            if (zzawj.equalsIgnoreCase("arm64-v8a")) {
                return zzgo.ARM64;
            }
            if (zzawj.equalsIgnoreCase("armeabi-v7a") || zzawj.equalsIgnoreCase("armv71")) {
                return zzgo.ARM7;
            }
        }
        zzj((byte[]) null);
        return zzgo.UNSUPPORTED;
    }
}
