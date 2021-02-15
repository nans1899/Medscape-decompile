package coil.request;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "T", "Lcoil/request/RequestBuilder;", "it", "Lcoil/request/Request;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: RequestBuilder.kt */
public final class RequestBuilder$listener$1 extends Lambda implements Function1<Request, Unit> {
    public static final RequestBuilder$listener$1 INSTANCE = new RequestBuilder$listener$1();

    public RequestBuilder$listener$1() {
        super(1);
    }

    public final void invoke(Request request) {
        Intrinsics.checkParameterIsNotNull(request, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Request) obj);
        return Unit.INSTANCE;
    }
}
