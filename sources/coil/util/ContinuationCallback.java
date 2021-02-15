package coil.util;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012#\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0002j\u0002`\bB\u001b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0002J\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\rH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcoil/util/ContinuationCallback;", "Lokhttp3/Callback;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "call", "Lokhttp3/Call;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "Lokhttp3/Response;", "(Lokhttp3/Call;Lkotlinx/coroutines/CancellableContinuation;)V", "invoke", "onFailure", "e", "Ljava/io/IOException;", "onResponse", "response", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ContinuationCallback.kt */
public final class ContinuationCallback implements Callback, Function1<Throwable, Unit> {
    private final Call call;
    private final CancellableContinuation<Response> continuation;

    public ContinuationCallback(Call call2, CancellableContinuation<? super Response> cancellableContinuation) {
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
        this.call = call2;
        this.continuation = cancellableContinuation;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public void onResponse(Call call2, Response response) {
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(response, "response");
        Result.Companion companion = Result.Companion;
        this.continuation.resumeWith(Result.m6constructorimpl(response));
    }

    public void onFailure(Call call2, IOException iOException) {
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(iOException, "e");
        if (!call2.isCanceled()) {
            Result.Companion companion = Result.Companion;
            this.continuation.resumeWith(Result.m6constructorimpl(ResultKt.createFailure(iOException)));
        }
    }

    public void invoke(Throwable th) {
        try {
            this.call.cancel();
        } catch (Throwable unused) {
        }
    }
}
