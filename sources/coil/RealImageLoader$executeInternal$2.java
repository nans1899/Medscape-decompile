package coil;

import androidx.lifecycle.Lifecycle;
import coil.memory.RequestDelegate;
import coil.memory.RequestService;
import coil.memory.TargetDelegate;
import coil.request.ErrorResult;
import coil.request.Request;
import coil.request.SuccessResult;
import coil.transition.Transition;
import coil.util.Logger;
import com.webmd.wbmdcmepulse.models.CPEvent;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lcoil/request/SuccessResult;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader$executeInternal$2", f = "RealImageLoader.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {274}, m = "invokeSuspend", n = {"$this$outerJob", "eventListener", "lifecycle", "mainDispatcher", "targetDelegate", "deferred", "requestDelegate"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* compiled from: RealImageLoader.kt */
final class RealImageLoader$executeInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SuccessResult>, Object> {
    final /* synthetic */ Request $request;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ RealImageLoader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RealImageLoader$executeInternal$2(RealImageLoader realImageLoader, Request request, Continuation continuation) {
        super(2, continuation);
        this.this$0 = realImageLoader;
        this.$request = request;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        RealImageLoader$executeInternal$2 realImageLoader$executeInternal$2 = new RealImageLoader$executeInternal$2(this.this$0, this.$request, continuation);
        realImageLoader$executeInternal$2.p$ = (CoroutineScope) obj;
        return realImageLoader$executeInternal$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RealImageLoader$executeInternal$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (!this.this$0.isShutdown) {
                final EventListener create = this.this$0.eventListenerFactory.create(this.$request);
                RequestService.LifecycleInfo lifecycleInfo = this.this$0.requestService.lifecycleInfo(this.$request);
                Lifecycle component1 = lifecycleInfo.component1();
                CoroutineDispatcher component2 = lifecycleInfo.component2();
                TargetDelegate createTargetDelegate = this.this$0.delegateService.createTargetDelegate(this.$request, create);
                Lifecycle lifecycle = component1;
                Deferred async = BuildersKt.async(coroutineScope, component2, CoroutineStart.LAZY, new RealImageLoader$executeInternal$2$deferred$1(this, create, lifecycle, createTargetDelegate, (Continuation) null));
                final TargetDelegate targetDelegate = createTargetDelegate;
                CoroutineDispatcher coroutineDispatcher = component2;
                final RequestDelegate createRequestDelegate = this.this$0.delegateService.createRequestDelegate(this.$request, createTargetDelegate, component1, coroutineDispatcher, async);
                async.invokeOnCompletion(new Function1<Throwable, Unit>(this) {
                    final /* synthetic */ RealImageLoader$executeInternal$2 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Throwable) obj);
                        return Unit.INSTANCE;
                    }

                    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
                    @DebugMetadata(c = "coil.RealImageLoader$executeInternal$2$2$1", f = "RealImageLoader.kt", i = {0, 0}, l = {266}, m = "invokeSuspend", n = {"$this$launch", "result"}, s = {"L$0", "L$1"})
                    /* renamed from: coil.RealImageLoader$executeInternal$2$2$1  reason: invalid class name */
                    /* compiled from: RealImageLoader.kt */
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        Object L$0;
                        Object L$1;
                        int label;
                        private CoroutineScope p$;
                        final /* synthetic */ AnonymousClass2 this$0;

                        {
                            this.this$0 = r1;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            Intrinsics.checkParameterIsNotNull(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
                            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, th, continuation);
                            r0.p$ = (CoroutineScope) obj;
                            return r0;
                        }

                        public final Object invoke(Object obj, Object obj2) {
                            return ((AnonymousClass1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                CoroutineScope coroutineScope = this.p$;
                                createRequestDelegate.onComplete();
                                Throwable th = th;
                                if (th == null) {
                                    return Unit.INSTANCE;
                                }
                                if (th instanceof CancellationException) {
                                    Logger logger$coil_base_release = this.this$0.this$0.this$0.getLogger$coil_base_release();
                                    if (logger$coil_base_release != null && logger$coil_base_release.getLevel() <= 4) {
                                        logger$coil_base_release.log("RealImageLoader", 4, "🏗  Cancelled - " + this.this$0.this$0.$request.getData(), (Throwable) null);
                                    }
                                    create.onCancel(this.this$0.this$0.$request);
                                    Request.Listener listener = this.this$0.this$0.$request.getListener();
                                    if (listener != null) {
                                        listener.onCancel(this.this$0.this$0.$request);
                                    }
                                    return Unit.INSTANCE;
                                }
                                Logger logger$coil_base_release2 = this.this$0.this$0.this$0.getLogger$coil_base_release();
                                if (logger$coil_base_release2 != null && logger$coil_base_release2.getLevel() <= 4) {
                                    logger$coil_base_release2.log("RealImageLoader", 4, "🚨 Failed - " + this.this$0.this$0.$request.getData() + " - " + th, (Throwable) null);
                                }
                                ErrorResult errorResult = this.this$0.this$0.this$0.requestService.errorResult(this.this$0.this$0.$request, th, true);
                                TargetDelegate targetDelegate = targetDelegate;
                                Transition transition = this.this$0.this$0.$request.getTransition();
                                if (transition == null) {
                                    transition = this.this$0.this$0.this$0.getDefaults().getTransition();
                                }
                                this.L$0 = coroutineScope;
                                this.L$1 = errorResult;
                                this.label = 1;
                                if (targetDelegate.error(errorResult, transition, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else if (i == 1) {
                                ErrorResult errorResult2 = (ErrorResult) this.L$1;
                                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                                ResultKt.throwOnFailure(obj);
                            } else {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            create.onError(this.this$0.this$0.$request, th);
                            Request.Listener listener2 = this.this$0.this$0.$request.getListener();
                            if (listener2 != null) {
                                listener2.onError(this.this$0.this$0.$request, th);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    public final void invoke(final Throwable th) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(this.this$0.this$0.loaderScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass1(this, (Continuation) null), 2, (Object) null);
                    }
                });
                this.L$0 = coroutineScope;
                this.L$1 = create;
                this.L$2 = lifecycle;
                this.L$3 = coroutineDispatcher;
                this.L$4 = targetDelegate;
                this.L$5 = async;
                this.L$6 = createRequestDelegate;
                this.label = 1;
                Object await = async.await(this);
                return await == coroutine_suspended ? coroutine_suspended : await;
            }
            throw new IllegalStateException("The image loader is shutdown.".toString());
        } else if (i == 1) {
            RequestDelegate requestDelegate = (RequestDelegate) this.L$6;
            Deferred deferred = (Deferred) this.L$5;
            TargetDelegate targetDelegate2 = (TargetDelegate) this.L$4;
            CoroutineDispatcher coroutineDispatcher2 = (CoroutineDispatcher) this.L$3;
            Lifecycle lifecycle2 = (Lifecycle) this.L$2;
            EventListener eventListener = (EventListener) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            return obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
