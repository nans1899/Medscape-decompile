package com.google.android.play.core.missingsplits;

import android.content.Context;
import java.util.concurrent.atomic.AtomicReference;

public class MissingSplitsManagerFactory {
    private static final AtomicReference<Boolean> a = new AtomicReference<>((Object) null);

    public static MissingSplitsManager create(Context context) {
        return new b(context, Runtime.getRuntime(), new a(context, context.getPackageManager()), a);
    }
}
