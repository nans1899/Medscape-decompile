package mnetinternal;

import mnetinternal.ag;
import net.media.android.bidder.base.factory.a;

public final class af {
    private static af a = new af();
    private ag b = new ag.a().a(a.a()).a((ae) ah.a()).a();

    private af() {
    }

    private <T> void a(int i, ai aiVar, ak<T> akVar) {
        this.b.a(i, aiVar, akVar);
    }

    public static <T> void a(ai aiVar, ak<T> akVar) {
        a.a(1, aiVar, akVar);
    }

    public static <T> void b(ai aiVar, ak<T> akVar) {
        a.a(3, aiVar, akVar);
    }
}
