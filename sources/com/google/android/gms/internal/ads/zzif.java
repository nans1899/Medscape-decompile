package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzif {
    private static final zzif zzaiz = new zzif(new int[]{2}, 2);
    private final int[] zzaja;
    private final int zzajb = 2;

    private zzif(int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        this.zzaja = copyOf;
        Arrays.sort(copyOf);
    }

    public final boolean zzw(int i) {
        return Arrays.binarySearch(this.zzaja, i) >= 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzif)) {
            return false;
        }
        zzif zzif = (zzif) obj;
        return Arrays.equals(this.zzaja, zzif.zzaja) && this.zzajb == zzif.zzajb;
    }

    public final int hashCode() {
        return this.zzajb + (Arrays.hashCode(this.zzaja) * 31);
    }

    public final String toString() {
        int i = this.zzajb;
        String arrays = Arrays.toString(this.zzaja);
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 67);
        sb.append("AudioCapabilities[maxChannelCount=");
        sb.append(i);
        sb.append(", supportedEncodings=");
        sb.append(arrays);
        sb.append("]");
        return sb.toString();
    }
}
