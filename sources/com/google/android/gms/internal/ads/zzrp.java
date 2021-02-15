package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;
import org.apache.commons.io.IOUtils;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzrp {
    private final int zzbtz;
    private final int zzbua;
    private final int zzbub;
    private final zzrq zzbuc = new zzrt();

    public zzrp(int i) {
        this.zzbua = i;
        this.zzbtz = 6;
        this.zzbub = 0;
    }

    public final String zza(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            sb.append(((String) obj).toLowerCase(Locale.US));
            sb.append(10);
        }
        return zzbt(sb.toString());
    }

    private final String zzbt(String str) {
        String[] split = str.split(IOUtils.LINE_SEPARATOR_UNIX);
        if (split.length == 0) {
            return "";
        }
        zzrr zzrr = new zzrr();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzbua, new zzrs(this));
        for (String zze : split) {
            String[] zze2 = zzru.zze(zze, false);
            if (zze2.length != 0) {
                zzrv.zza(zze2, this.zzbua, this.zzbtz, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzrr.write(this.zzbuc.zzbu(((zzry) it.next()).zzbuj));
            } catch (IOException e) {
                zzayp.zzc("Error while writing hash to byteStream", e);
            }
        }
        return zzrr.toString();
    }
}
