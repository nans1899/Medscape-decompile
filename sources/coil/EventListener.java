package coil;

import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.decode.Options;
import coil.fetch.Fetcher;
import coil.request.Request;
import coil.size.Size;
import coil.transition.Transition;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 '2\u00020\u0001:\u0002'(J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0017J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0017J$\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\b\u001a\u00020\tH\u0017J$\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\b\u001a\u00020\tH\u0017J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0017J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0011H\u0017J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0017J\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0017J\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u0017J\u0018\u0010&\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u0017\u0002\u0007\n\u0005\bF0\u0001¨\u0006)"}, d2 = {"Lcoil/EventListener;", "Lcoil/request/Request$Listener;", "decodeEnd", "", "request", "Lcoil/request/Request;", "decoder", "Lcoil/decode/Decoder;", "options", "Lcoil/decode/Options;", "decodeStart", "fetchEnd", "fetcher", "Lcoil/fetch/Fetcher;", "fetchStart", "mapEnd", "mappedData", "", "mapStart", "data", "onCancel", "onDispatch", "onError", "throwable", "", "onStart", "onSuccess", "source", "Lcoil/decode/DataSource;", "resolveSizeEnd", "size", "Lcoil/size/Size;", "resolveSizeStart", "transformEnd", "transformStart", "transitionEnd", "transition", "Lcoil/transition/Transition;", "transitionStart", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EventListener.kt */
public interface EventListener extends Request.Listener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final EventListener NONE = new EventListener$Companion$NONE$1();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: EventListener.kt */
    public static final class DefaultImpls {
        public static void decodeEnd(EventListener eventListener, Request request, Decoder decoder, Options options) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(decoder, "decoder");
            Intrinsics.checkParameterIsNotNull(options, "options");
        }

        public static void decodeStart(EventListener eventListener, Request request, Decoder decoder, Options options) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(decoder, "decoder");
            Intrinsics.checkParameterIsNotNull(options, "options");
        }

        public static void fetchEnd(EventListener eventListener, Request request, Fetcher<?> fetcher, Options options) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(fetcher, "fetcher");
            Intrinsics.checkParameterIsNotNull(options, "options");
        }

        public static void fetchStart(EventListener eventListener, Request request, Fetcher<?> fetcher, Options options) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(fetcher, "fetcher");
            Intrinsics.checkParameterIsNotNull(options, "options");
        }

        public static void mapEnd(EventListener eventListener, Request request, Object obj) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(obj, "mappedData");
        }

        public static void mapStart(EventListener eventListener, Request request, Object obj) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(obj, "data");
        }

        public static void onCancel(EventListener eventListener, Request request) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        }

        public static void onDispatch(EventListener eventListener, Request request) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        }

        public static void onError(EventListener eventListener, Request request, Throwable th) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(th, "throwable");
        }

        public static void onStart(EventListener eventListener, Request request) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        }

        public static void onSuccess(EventListener eventListener, Request request, DataSource dataSource) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(dataSource, "source");
        }

        public static void resolveSizeEnd(EventListener eventListener, Request request, Size size) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(size, "size");
        }

        public static void resolveSizeStart(EventListener eventListener, Request request) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        }

        public static void transformEnd(EventListener eventListener, Request request) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        }

        public static void transformStart(EventListener eventListener, Request request) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        }

        public static void transitionEnd(EventListener eventListener, Request request, Transition transition) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(transition, "transition");
        }

        public static void transitionStart(EventListener eventListener, Request request, Transition transition) {
            Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(transition, "transition");
        }
    }

    void decodeEnd(Request request, Decoder decoder, Options options);

    void decodeStart(Request request, Decoder decoder, Options options);

    void fetchEnd(Request request, Fetcher<?> fetcher, Options options);

    void fetchStart(Request request, Fetcher<?> fetcher, Options options);

    void mapEnd(Request request, Object obj);

    void mapStart(Request request, Object obj);

    void onCancel(Request request);

    void onDispatch(Request request);

    void onError(Request request, Throwable th);

    void onStart(Request request);

    void onSuccess(Request request, DataSource dataSource);

    void resolveSizeEnd(Request request, Size size);

    void resolveSizeStart(Request request);

    void transformEnd(Request request);

    void transformStart(Request request);

    void transitionEnd(Request request, Transition transition);

    void transitionStart(Request request, Transition transition);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0002\u0007\n\u0005\bF0\u0001¨\u0006\u0005"}, d2 = {"Lcoil/EventListener$Companion;", "", "()V", "NONE", "Lcoil/EventListener;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: EventListener.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u0002\u0007\n\u0005\bF0\u0001¨\u0006\u0007"}, d2 = {"Lcoil/EventListener$Factory;", "", "create", "Lcoil/EventListener;", "request", "Lcoil/request/Request;", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: EventListener.kt */
    public interface Factory {
        public static final Companion Companion;
        public static final Factory NONE;

        /* renamed from: coil.EventListener$Factory$-CC  reason: invalid class name */
        /* compiled from: EventListener.kt */
        public final /* synthetic */ class CC {
            @JvmStatic
            public static Factory create(EventListener eventListener) {
                return Factory.Companion.create(eventListener);
            }
        }

        EventListener create(Request request);

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\bR\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0002\u0007\n\u0005\bF0\u0001¨\u0006\t"}, d2 = {"Lcoil/EventListener$Factory$Companion;", "", "()V", "NONE", "Lcoil/EventListener$Factory;", "invoke", "listener", "Lcoil/EventListener;", "create", "coil-base_release"}, k = 1, mv = {1, 1, 16})
        /* compiled from: EventListener.kt */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = null;

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final Factory create(EventListener eventListener) {
                Intrinsics.checkParameterIsNotNull(eventListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                return new EventListener$Factory$Companion$invoke$1(eventListener);
            }
        }

        static {
            Companion companion = new Companion((DefaultConstructorMarker) null);
            Companion = companion;
            NONE = companion.create(EventListener.NONE);
        }
    }
}
