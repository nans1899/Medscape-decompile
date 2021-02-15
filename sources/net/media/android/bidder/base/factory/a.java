package net.media.android.bidder.base.factory;

import mnetinternal.al;
import mnetinternal.bc;
import net.media.android.bidder.base.MNet;

public final class a {
    private static a a;
    private al b = new bc(MNet.getContext());

    private a() {
    }

    public static al a() {
        if (a == null) {
            a = new a();
        }
        return a.b;
    }
}
