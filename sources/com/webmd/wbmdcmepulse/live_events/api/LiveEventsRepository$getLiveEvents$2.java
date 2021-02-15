package com.webmd.wbmdcmepulse.live_events.api;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.appboy.Constants;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.live_events.util.LiveEventsLoadFinish;
import com.webmd.wbmdcmepulse.live_events.util.LiveEventsUtil;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00040\u0001J.\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00040\b2\u0006\u0010\t\u001a\u00020\nH\u0016JD\u0010\u000b\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00040\b2\u001c\u0010\f\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00040\rH\u0016¨\u0006\u000e"}, d2 = {"com/webmd/wbmdcmepulse/live_events/api/LiveEventsRepository$getLiveEvents$2", "Lretrofit2/Callback;", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsRepository.kt */
public final class LiveEventsRepository$getLiveEvents$2 implements Callback<ArrayList<LiveEventItem>> {
    final /* synthetic */ LiveEventsLoadFinish $listener;
    final /* synthetic */ ArrayList $liveEvents;
    final /* synthetic */ LiveEventsRepository this$0;

    LiveEventsRepository$getLiveEvents$2(LiveEventsRepository liveEventsRepository, LiveEventsLoadFinish liveEventsLoadFinish, ArrayList arrayList) {
        this.this$0 = liveEventsRepository;
        this.$listener = liveEventsLoadFinish;
        this.$liveEvents = arrayList;
    }

    public void onFailure(Call<ArrayList<LiveEventItem>> call, Throwable th) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(th, Constants.APPBOY_PUSH_TITLE_KEY);
        this.$listener.onLiveEventsLoaded(this.$liveEvents);
    }

    public void onResponse(Call<ArrayList<LiveEventItem>> call, Response<ArrayList<LiveEventItem>> response) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.isSuccessful()) {
            ArrayList body = response.body();
            if (body != null) {
                Iterator<LiveEventItem> it = new LiveEventsUtil().sortLiveEventsByDate(body, this.this$0.getDateFormat()).iterator();
                while (it.hasNext()) {
                    LiveEventItem next = it.next();
                    CharSequence eventTitle = next.getEventTitle();
                    boolean z = false;
                    if (!(eventTitle == null || eventTitle.length() == 0)) {
                        CharSequence eventDate = next.getEventDate();
                        if (eventDate == null || eventDate.length() == 0) {
                            z = true;
                        }
                        if (!z) {
                            String eventDate2 = next.getEventDate();
                            String str = null;
                            next.setEventDate(eventDate2 != null ? StringsKt.replace$default(eventDate2, ",", "", false, 4, (Object) null) : null);
                            LiveEventsUtil liveEventsUtil = new LiveEventsUtil();
                            String eventDate3 = next.getEventDate();
                            if (eventDate3 != null) {
                                str = StringsKt.replace$default(eventDate3, ",", "", false, 4, (Object) null);
                            }
                            next.setEventDate(liveEventsUtil.getCorrectFormatString(str));
                            this.$liveEvents.add(next);
                        }
                    }
                }
            }
        } else if (response.errorBody() != null) {
            Log.d(this.this$0.getMTAG(), String.valueOf(response.errorBody()));
        }
        this.$listener.onLiveEventsLoaded(this.$liveEvents);
    }
}
