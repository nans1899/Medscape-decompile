package coil.request;

import coil.decode.DataSource;
import coil.request.Request;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"coil/request/RequestBuilder$listener$5", "Lcoil/request/Request$Listener;", "onCancel", "", "request", "Lcoil/request/Request;", "onError", "throwable", "", "onStart", "onSuccess", "source", "Lcoil/decode/DataSource;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestBuilder.kt */
public final class RequestBuilder$listener$5 implements Request.Listener {
    final /* synthetic */ Function1 $onCancel;
    final /* synthetic */ Function2 $onError;
    final /* synthetic */ Function1 $onStart;
    final /* synthetic */ Function2 $onSuccess;

    public RequestBuilder$listener$5(Function1 function1, Function1 function12, Function2 function2, Function2 function22) {
        this.$onStart = function1;
        this.$onCancel = function12;
        this.$onError = function2;
        this.$onSuccess = function22;
    }

    public void onStart(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        this.$onStart.invoke(request);
    }

    public void onCancel(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        this.$onCancel.invoke(request);
    }

    public void onError(Request request, Throwable th) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        this.$onError.invoke(request, th);
    }

    public void onSuccess(Request request, DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(dataSource, "source");
        this.$onSuccess.invoke(request, dataSource);
    }
}
