package coil.util;

import kotlin.Lazy;
import kotlin.Metadata;
import okhttp3.Call;
import okhttp3.Request;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lokhttp3/Call;", "kotlin.jvm.PlatformType", "it", "Lokhttp3/Request;", "newCall"}, k = 3, mv = {1, 1, 16})
/* renamed from: coil.util.-Extensions$lazyCallFactory$1  reason: invalid class name */
/* compiled from: Extensions.kt */
final class Extensions$lazyCallFactory$1 implements Call.Factory {
    final /* synthetic */ Lazy $lazy;

    Extensions$lazyCallFactory$1(Lazy lazy) {
        this.$lazy = lazy;
    }

    public final Call newCall(Request request) {
        return ((Call.Factory) this.$lazy.getValue()).newCall(request);
    }
}
