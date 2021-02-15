package com.google.android.gms.internal.ads;

import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.nio.ByteBuffer;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zziy {
    private static final int[] zzamf = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] zzamg = {-1, Constants.CMEACTIVITY_REQUEST_CODE, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] zzamh = {64, 112, 128, 192, 224, 256, 384, 448, 512, DimensionsKt.XXXHDPI, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    public static int zzo(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        return ((((byteBuffer.get(position + 5) & 252) >> 2) | ((byteBuffer.get(position + 4) & 1) << 6)) + 1) << 5;
    }
}
