package com.wbmd.ads.utils;

import kotlin.Metadata;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/wbmd/ads/utils/PvidGenerator;", "", "()V", "generatePVID", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PvidGenerator.kt */
public final class PvidGenerator {
    public static final PvidGenerator INSTANCE = new PvidGenerator();

    private PvidGenerator() {
    }

    public final String generatePVID() {
        long currentTimeMillis = System.currentTimeMillis() / ((long) 1000);
        int random = RangesKt.random(new IntRange(0, 99999999), (Random) Random.Default);
        StringBuilder sb = new StringBuilder();
        sb.append(currentTimeMillis);
        sb.append(random);
        return sb.toString();
    }
}
