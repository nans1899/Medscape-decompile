package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import com.google.android.play.core.internal.h;

public abstract class AssetPackState {
    static AssetPackState a(Bundle bundle, String str, cb cbVar, az azVar) {
        return a(str, azVar.a(bundle.getInt(h.a("status", str)), str), bundle.getInt(h.a(NativeProtocol.BRIDGE_ARG_ERROR_CODE, str)), bundle.getLong(h.a("bytes_downloaded", str)), bundle.getLong(h.a("total_bytes_to_download", str)), cbVar.b(str));
    }

    public static AssetPackState a(String str, int i, int i2, long j, long j2, double d) {
        return new bi(str, i, i2, j, j2, (int) Math.rint(100.0d * d));
    }

    public abstract long bytesDownloaded();

    public abstract int errorCode();

    public abstract String name();

    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();
}
