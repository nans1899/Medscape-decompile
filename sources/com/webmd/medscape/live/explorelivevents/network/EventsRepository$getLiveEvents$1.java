package com.webmd.medscape.live.explorelivevents.network;

import android.util.Log;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.LiveEventsResponse;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import com.webmd.wbmdcmepulse.models.CPEvent;
import java.util.List;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import retrofit2.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.webmd.medscape.live.explorelivevents.network.EventsRepository$getLiveEvents$1", f = "EventsRepository.kt", i = {0, 0, 1, 1, 1}, l = {55, 56}, m = "invokeSuspend", n = {"$this$launch", "cookie", "$this$launch", "cookie", "response"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* compiled from: EventsRepository.kt */
final class EventsRepository$getLiveEvents$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ EventsApi $apiService;
    final /* synthetic */ String $endDate;
    final /* synthetic */ Function1 $failureCallback;
    final /* synthetic */ List $locations;
    final /* synthetic */ List $specialties;
    final /* synthetic */ String $startDate;
    final /* synthetic */ Function1 $successCallback;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ EventsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventsRepository$getLiveEvents$1(EventsRepository eventsRepository, EventsApi eventsApi, List list, List list2, String str, String str2, Function1 function1, Function1 function12, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eventsRepository;
        this.$apiService = eventsApi;
        this.$specialties = list;
        this.$locations = list2;
        this.$startDate = str;
        this.$endDate = str2;
        this.$successCallback = function1;
        this.$failureCallback = function12;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        EventsRepository$getLiveEvents$1 eventsRepository$getLiveEvents$1 = new EventsRepository$getLiveEvents$1(this.this$0, this.$apiService, this.$specialties, this.$locations, this.$startDate, this.$endDate, this.$successCallback, this.$failureCallback, continuation);
        eventsRepository$getLiveEvents$1.p$ = (CoroutineScope) obj;
        return eventsRepository$getLiveEvents$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((EventsRepository$getLiveEvents$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(T t) {
        CoroutineScope coroutineScope;
        String str;
        final Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        String str2;
        T coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(t);
            coroutineScope = this.p$;
            String string = this.this$0.sharedPreferencesManager.getString("KEY_PREFS_COOKIE_STRING", "");
            objectRef = new Ref.ObjectRef();
            EventsApi eventsApi = this.$apiService;
            if (string != null) {
                str2 = string;
            } else {
                str2 = "";
            }
            List list = this.$specialties;
            List list2 = this.$locations;
            String str3 = this.$startDate;
            String str4 = this.$endDate;
            this.L$0 = coroutineScope;
            this.L$1 = string;
            this.L$2 = objectRef;
            this.L$3 = objectRef;
            this.label = 1;
            T liveEvents = eventsApi.getLiveEvents(str2, list, list2, str3, str4, this);
            if (liveEvents == coroutine_suspended) {
                return coroutine_suspended;
            }
            str = string;
            t = liveEvents;
            objectRef2 = objectRef;
        } else if (i == 1) {
            objectRef2 = (Ref.ObjectRef) this.L$3;
            objectRef = (Ref.ObjectRef) this.L$2;
            str = (String) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(t);
        } else if (i == 2) {
            Ref.ObjectRef objectRef3 = (Ref.ObjectRef) this.L$2;
            String str5 = (String) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(t);
            } catch (Exception e) {
                this.this$0.showError(e, this.$failureCallback);
            }
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef2.element = (Response) t;
        this.L$0 = coroutineScope;
        this.L$1 = str;
        this.L$2 = objectRef;
        this.label = 2;
        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this, (Continuation) null), this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "com.webmd.medscape.live.explorelivevents.network.EventsRepository$getLiveEvents$1$1", f = "EventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.webmd.medscape.live.explorelivevents.network.EventsRepository$getLiveEvents$1$1  reason: invalid class name */
    /* compiled from: EventsRepository.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;
        final /* synthetic */ EventsRepository$getLiveEvents$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, objectRef, continuation);
            r0.p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (((Response) objectRef.element).isSuccessful()) {
                    LiveEventsResponse liveEventsResponse = (LiveEventsResponse) ((Response) objectRef.element).body();
                    if (liveEventsResponse == null) {
                        return null;
                    }
                    this.this$0.$successCallback.invoke(liveEventsResponse.getLiveEvents());
                    return Unit.INSTANCE;
                }
                Log.e("Error", String.valueOf(((Response) objectRef.element).errorBody()));
                int code = ((Response) objectRef.element).code();
                if (code == 404) {
                    this.this$0.$failureCallback.invoke(new Error(true, "No events found!", ExtensionsKt.getErrorImageRes(((Response) objectRef.element).code(), this.this$0.this$0.context)));
                    EventsRepository eventsRepository = this.this$0.this$0;
                    eventsRepository.onError(((Response) objectRef.element).code() + ' ' + ((Response) objectRef.element).message());
                    return Unit.INSTANCE;
                } else if (code != 414) {
                    this.this$0.$failureCallback.invoke(new Error(true, ((Response) objectRef.element).message(), ExtensionsKt.getErrorImageRes(((Response) objectRef.element).code(), this.this$0.this$0.context)));
                    EventsRepository eventsRepository2 = this.this$0.this$0;
                    eventsRepository2.onError(((Response) objectRef.element).code() + ' ' + ((Response) objectRef.element).message());
                    return Unit.INSTANCE;
                } else {
                    this.this$0.$failureCallback.invoke(new Error(true, "Too many selections. Please select fewer items, and try again.", ExtensionsKt.getErrorImageRes(((Response) objectRef.element).code(), this.this$0.this$0.context)));
                    EventsRepository eventsRepository3 = this.this$0.this$0;
                    eventsRepository3.onError(((Response) objectRef.element).code() + ' ' + ((Response) objectRef.element).message());
                    return Unit.INSTANCE;
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
