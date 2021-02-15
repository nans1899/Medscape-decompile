package com.webmd.wbmdcmepulse.live_events.api;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.logging.HttpLoggingInterceptor;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "log"}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventsApi.kt */
final class LiveEventsApi$Companion$create$logger$1 implements HttpLoggingInterceptor.Logger {
    public static final LiveEventsApi$Companion$create$logger$1 INSTANCE = new LiveEventsApi$Companion$create$logger$1();

    LiveEventsApi$Companion$create$logger$1() {
    }

    public final void log(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        Log.d("API", str);
    }
}
