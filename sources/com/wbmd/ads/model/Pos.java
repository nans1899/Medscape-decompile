package com.wbmd.ads.model;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/wbmd/ads/model/Pos;", "", "(Ljava/lang/String;I)V", "value", "", "StickyAdhesive", "InstreamFeed", "BottomInstream", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Pos.kt */
public enum Pos {
    ;

    public abstract int value();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"Lcom/wbmd/ads/model/Pos$StickyAdhesive;", "Lcom/wbmd/ads/model/Pos;", "value", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Pos.kt */
    static final class StickyAdhesive extends Pos {
        public int value() {
            return 1006;
        }

        StickyAdhesive(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"Lcom/wbmd/ads/model/Pos$InstreamFeed;", "Lcom/wbmd/ads/model/Pos;", "value", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Pos.kt */
    static final class InstreamFeed extends Pos {
        public int value() {
            return 2022;
        }

        InstreamFeed(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"Lcom/wbmd/ads/model/Pos$BottomInstream;", "Lcom/wbmd/ads/model/Pos;", "value", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Pos.kt */
    static final class BottomInstream extends Pos {
        public int value() {
            return 2420;
        }

        BottomInstream(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }
}
