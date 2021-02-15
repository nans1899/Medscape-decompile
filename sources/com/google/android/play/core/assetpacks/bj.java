package com.google.android.play.core.assetpacks;

import java.util.Map;

final class bj extends AssetPackStates {
    private final long a;
    private final Map<String, AssetPackState> b;

    bj(long j, Map<String, AssetPackState> map) {
        this.a = j;
        this.b = map;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackStates) {
            AssetPackStates assetPackStates = (AssetPackStates) obj;
            return this.a == assetPackStates.totalBytes() && this.b.equals(assetPackStates.packStates());
        }
    }

    public final int hashCode() {
        long j = this.a;
        return this.b.hashCode() ^ ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003);
    }

    public final Map<String, AssetPackState> packStates() {
        return this.b;
    }

    public final String toString() {
        long j = this.a;
        String valueOf = String.valueOf(this.b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 61);
        sb.append("AssetPackStates{totalBytes=");
        sb.append(j);
        sb.append(", packStates=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    public final long totalBytes() {
        return this.a;
    }
}
