package coil;

import coil.EventListener;
import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.decode.Options;
import coil.fetch.Fetcher;
import coil.request.Request;
import coil.size.Size;
import coil.transition.Transition;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"coil/EventListener$Companion$NONE$1", "Lcoil/EventListener;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EventListener.kt */
public final class EventListener$Companion$NONE$1 implements EventListener {
    EventListener$Companion$NONE$1() {
    }

    public void decodeEnd(Request request, Decoder decoder, Options options) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(options, "options");
        EventListener.DefaultImpls.decodeEnd(this, request, decoder, options);
    }

    public void decodeStart(Request request, Decoder decoder, Options options) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(options, "options");
        EventListener.DefaultImpls.decodeStart(this, request, decoder, options);
    }

    public void fetchEnd(Request request, Fetcher<?> fetcher, Options options) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(fetcher, "fetcher");
        Intrinsics.checkParameterIsNotNull(options, "options");
        EventListener.DefaultImpls.fetchEnd(this, request, fetcher, options);
    }

    public void fetchStart(Request request, Fetcher<?> fetcher, Options options) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(fetcher, "fetcher");
        Intrinsics.checkParameterIsNotNull(options, "options");
        EventListener.DefaultImpls.fetchStart(this, request, fetcher, options);
    }

    public void mapEnd(Request request, Object obj) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(obj, "mappedData");
        EventListener.DefaultImpls.mapEnd(this, request, obj);
    }

    public void mapStart(Request request, Object obj) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(obj, "data");
        EventListener.DefaultImpls.mapStart(this, request, obj);
    }

    public void onCancel(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        EventListener.DefaultImpls.onCancel(this, request);
    }

    public void onDispatch(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        EventListener.DefaultImpls.onDispatch(this, request);
    }

    public void onError(Request request, Throwable th) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        EventListener.DefaultImpls.onError(this, request, th);
    }

    public void onStart(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        EventListener.DefaultImpls.onStart(this, request);
    }

    public void onSuccess(Request request, DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(dataSource, "source");
        EventListener.DefaultImpls.onSuccess(this, request, dataSource);
    }

    public void resolveSizeEnd(Request request, Size size) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(size, "size");
        EventListener.DefaultImpls.resolveSizeEnd(this, request, size);
    }

    public void resolveSizeStart(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        EventListener.DefaultImpls.resolveSizeStart(this, request);
    }

    public void transformEnd(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        EventListener.DefaultImpls.transformEnd(this, request);
    }

    public void transformStart(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        EventListener.DefaultImpls.transformStart(this, request);
    }

    public void transitionEnd(Request request, Transition transition) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(transition, "transition");
        EventListener.DefaultImpls.transitionEnd(this, request, transition);
    }

    public void transitionStart(Request request, Transition transition) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(transition, "transition");
        EventListener.DefaultImpls.transitionStart(this, request, transition);
    }
}
