package coil.request;

import coil.decode.DataSource;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "T", "Lcoil/request/RequestBuilder;", "<anonymous parameter 0>", "Lcoil/request/Request;", "<anonymous parameter 1>", "Lcoil/decode/DataSource;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: RequestBuilder.kt */
public final class RequestBuilder$listener$4 extends Lambda implements Function2<Request, DataSource, Unit> {
    public static final RequestBuilder$listener$4 INSTANCE = new RequestBuilder$listener$4();

    public RequestBuilder$listener$4() {
        super(2);
    }

    public final void invoke(Request request, DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(request, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(dataSource, "<anonymous parameter 1>");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Request) obj, (DataSource) obj2);
        return Unit.INSTANCE;
    }
}