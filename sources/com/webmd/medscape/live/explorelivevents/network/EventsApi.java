package com.webmd.medscape.live.explorelivevents.network;

import com.webmd.medscape.live.explorelivevents.data.FiltersResponse;
import com.webmd.medscape.live.explorelivevents.data.LiveEventsResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\bJa\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0012\b\u0001\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\f2\u0012\b\u0001\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\f2\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/network/EventsApi;", "", "getFilters", "Lretrofit2/Response;", "Lcom/webmd/medscape/live/explorelivevents/data/FiltersResponse;", "Cookie", "", "startDate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLiveEvents", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEventsResponse;", "specialty", "", "location", "endDate", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EventsApi.kt */
public interface EventsApi {
    @GET("events/search/filters")
    Object getFilters(@Header("Cookie") String str, @Query("startDate") String str2, Continuation<? super Response<FiltersResponse>> continuation);

    @GET("events/search")
    Object getLiveEvents(@Header("Cookie") String str, @Query(encoded = true, value = "specialty") List<String> list, @Query(encoded = true, value = "location") List<String> list2, @Query("startDate") String str2, @Query("endDate") String str3, Continuation<? super Response<LiveEventsResponse>> continuation);
}
