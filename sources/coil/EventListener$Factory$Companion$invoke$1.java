package coil;

import coil.EventListener;
import coil.request.Request;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"coil/EventListener$Factory$Companion$invoke$1", "Lcoil/EventListener$Factory;", "create", "Lcoil/EventListener;", "request", "Lcoil/request/Request;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EventListener.kt */
public final class EventListener$Factory$Companion$invoke$1 implements EventListener.Factory {
    final /* synthetic */ EventListener $listener;

    EventListener$Factory$Companion$invoke$1(EventListener eventListener) {
        this.$listener = eventListener;
    }

    public EventListener create(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        return this.$listener;
    }
}
