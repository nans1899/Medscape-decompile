package com.webmd.medscape.live.explorelivevents.network;

import android.content.Context;
import android.util.Log;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.FiltersResponse;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import com.webmd.medscape.live.explorelivevents.util.GsonUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 )2\u00020\u0001:\u0001)B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018Jl\u0010\u0019\u001a\u00020\u00102\u0010\u0010\u001a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u001b2\u0010\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u001b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u00182\u0018\u0010\u001e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001b\u0012\u0004\u0012\u00020\u00100\u001f2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00100\u001fJ\u0010\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0018H\u0002J\u000e\u0010%\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eJ$\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00100\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/network/EventsRepository;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "sharedPreferencesManager", "Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;", "(Landroid/content/Context;Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "exceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "retrofit", "Lretrofit2/Retrofit;", "cacheFilters", "", "filtersResponse", "Lcom/webmd/medscape/live/explorelivevents/data/FiltersResponse;", "cacheFromLocalStorage", "createApiService", "Lcom/webmd/medscape/live/explorelivevents/network/EventsApi;", "getFilters", "startDate", "", "getLiveEvents", "specialties", "", "locations", "endDate", "successCallback", "Lkotlin/Function1;", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "failureCallback", "Lcom/webmd/medscape/live/explorelivevents/data/Error;", "onError", "errorMessage", "setRetrofit", "showError", "e", "Ljava/lang/Exception;", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EventsRepository.kt */
public final class EventsRepository implements CoroutineScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "EventsRepository";
    /* access modifiers changed from: private */
    public Context context;
    private final CoroutineExceptionHandler exceptionHandler = new EventsRepository$$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this);
    private Retrofit retrofit;
    /* access modifiers changed from: private */
    public SharedPreferencesManager sharedPreferencesManager;

    public EventsRepository(Context context2, SharedPreferencesManager sharedPreferencesManager2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(sharedPreferencesManager2, "sharedPreferencesManager");
        this.context = context2;
        this.sharedPreferencesManager = sharedPreferencesManager2;
    }

    public final void setRetrofit(Retrofit retrofit3) {
        Intrinsics.checkNotNullParameter(retrofit3, "retrofit");
        this.retrofit = retrofit3;
    }

    public final void getLiveEvents(List<String> list, List<String> list2, String str, String str2, Function1<? super List<LiveEvent>, Unit> function1, Function1<? super Error, Unit> function12) {
        Intrinsics.checkNotNullParameter(function1, "successCallback");
        Intrinsics.checkNotNullParameter(function12, "failureCallback");
        Retrofit retrofit3 = this.retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, this.exceptionHandler, (CoroutineStart) null, new EventsRepository$getLiveEvents$1(this, createApiService(retrofit3), list, list2, str, str2, function1, function12, (Continuation) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void showError(Exception exc, Function1<? super Error, Unit> function1) {
        String localizedMessage = exc.getLocalizedMessage();
        if (localizedMessage == null) {
            localizedMessage = "";
        }
        onError(localizedMessage);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getMain(), (CoroutineStart) null, new EventsRepository$showError$1(this, function1, (Continuation) null), 2, (Object) null);
    }

    private final EventsApi createApiService(Retrofit retrofit3) {
        Object create = retrofit3.create(EventsApi.class);
        Intrinsics.checkNotNullExpressionValue(create, "retrofit.create(EventsApi::class.java)");
        return (EventsApi) create;
    }

    public final void getFilters(Context context2, String str) {
        Intrinsics.checkNotNullParameter(context2, "context");
        boolean isNetworkAvailable = ExtensionsKt.isNetworkAvailable(context2);
        Retrofit retrofit3 = this.retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }
        EventsApi createApiService = createApiService(retrofit3);
        if (isNetworkAvailable) {
            try {
                Job unused = BuildersKt__Builders_commonKt.launch$default(this, this.exceptionHandler, (CoroutineStart) null, new EventsRepository$getFilters$1(this, createApiService, str, context2, (Continuation) null), 2, (Object) null);
            } catch (Exception e) {
                String localizedMessage = e.getLocalizedMessage();
                if (localizedMessage == null) {
                    localizedMessage = "";
                }
                onError(localizedMessage);
                cacheFromLocalStorage(context2);
            }
        } else {
            Log.i(TAG, "App is offline. Caching from local storage");
            cacheFromLocalStorage(context2);
        }
    }

    /* access modifiers changed from: private */
    public final void cacheFilters(FiltersResponse filtersResponse) {
        if (filtersResponse != null) {
            this.sharedPreferencesManager.saveString("filters_cache", GsonUtils.INSTANCE.serialize(FiltersResponse.class, filtersResponse));
        } else {
            this.sharedPreferencesManager.saveString("filters_cache", (String) null);
        }
    }

    /* access modifiers changed from: private */
    public final void cacheFromLocalStorage(Context context2) {
        try {
            cacheFilters((FiltersResponse) GsonUtils.INSTANCE.deserialize(FiltersResponse.class, ExtensionsKt.readJsonFromAssets(context2, "filters.json")));
        } catch (Exception e) {
            Log.e(TAG, e.getStackTrace().toString());
        }
    }

    /* access modifiers changed from: private */
    public final void onError(String str) {
        Log.e(TAG, str);
    }

    public CoroutineContext getCoroutineContext() {
        return Dispatchers.getIO();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/network/EventsRepository$Companion;", "", "()V", "TAG", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EventsRepository.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
