package com.webmd.medscape.live.explorelivevents.network;

import android.content.Context;
import android.util.Log;
import com.webmd.medscape.live.explorelivevents.data.FiltersResponse;
import com.webmd.wbmdcmepulse.models.CPEvent;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.webmd.medscape.live.explorelivevents.network.EventsRepository$getFilters$1", f = "EventsRepository.kt", i = {0, 0}, l = {136}, m = "invokeSuspend", n = {"$this$launch", "cookie"}, s = {"L$0", "L$1"})
/* compiled from: EventsRepository.kt */
final class EventsRepository$getFilters$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ EventsApi $apiService;
    final /* synthetic */ Context $context;
    final /* synthetic */ String $startDate;
    Object L$0;
    Object L$1;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ EventsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventsRepository$getFilters$1(EventsRepository eventsRepository, EventsApi eventsApi, String str, Context context, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eventsRepository;
        this.$apiService = eventsApi;
        this.$startDate = str;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        EventsRepository$getFilters$1 eventsRepository$getFilters$1 = new EventsRepository$getFilters$1(this.this$0, this.$apiService, this.$startDate, this.$context, continuation);
        eventsRepository$getFilters$1.p$ = (CoroutineScope) obj;
        return eventsRepository$getFilters$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((EventsRepository$getFilters$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            String str = "";
            String string = this.this$0.sharedPreferencesManager.getString("KEY_PREFS_COOKIE_STRING", str);
            EventsApi eventsApi = this.$apiService;
            if (string != null) {
                str = string;
            }
            String str2 = this.$startDate;
            this.L$0 = coroutineScope;
            this.L$1 = string;
            this.label = 1;
            obj = eventsApi.getFilters(str, str2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            String str3 = (String) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Response response = (Response) obj;
        if (response.isSuccessful()) {
            FiltersResponse filtersResponse = (FiltersResponse) response.body();
            if (filtersResponse != null) {
                Collection allLocations = filtersResponse.getAllLocations();
                if (!(allLocations == null || allLocations.isEmpty())) {
                    Collection searchFilters = filtersResponse.getSearchFilters();
                    if (searchFilters != null && !searchFilters.isEmpty()) {
                        z = false;
                    }
                    if (!z) {
                        this.this$0.cacheFilters(filtersResponse);
                    }
                }
                this.this$0.cacheFromLocalStorage(this.$context);
            }
        } else {
            Log.e(EventsRepository.TAG, response.message());
            this.this$0.cacheFromLocalStorage(this.$context);
        }
        return Unit.INSTANCE;
    }
}
