package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.bl;
import com.google.android.play.core.internal.ci;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class o implements ci<Executor> {
    public static Executor b() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(k.a);
        bl.a(newSingleThreadExecutor, "Cannot return null from a non-@Nullable @Provides method");
        return newSingleThreadExecutor;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return b();
    }
}
