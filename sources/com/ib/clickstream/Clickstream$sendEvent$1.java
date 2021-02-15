package com.ib.clickstream;

import androidx.core.app.NotificationCompat;
import com.appboy.Constants;
import com.ib.clickstream.ClickstreamConstants;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: Clickstream.kt */
final class Clickstream$sendEvent$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ClickstreamConstants.EventType $eventType;
    final /* synthetic */ Impression[] $impressions;
    final /* synthetic */ JSONObject $parameterJson;
    final /* synthetic */ Clickstream this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Clickstream$sendEvent$1(Clickstream clickstream, JSONObject jSONObject, ClickstreamConstants.EventType eventType, Impression[] impressionArr) {
        super(0);
        this.this$0 = clickstream;
        this.$parameterJson = jSONObject;
        this.$eventType = eventType;
        this.$impressions = impressionArr;
    }

    public final void invoke() {
        JSONObject eventJson = this.this$0.getDataGenerator$clickstream_release().getEventJson(this.$parameterJson, this.this$0.isNewPartyGenerated, this.$eventType.name());
        ArrayList<JSONObject> generateEvents = this.this$0.generateEvents(eventJson, this.$impressions);
        this.this$0.isNewPartyGenerated = false;
        if (!generateEvents.isEmpty()) {
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = generateEvents.size();
            ArrayList arrayList = new ArrayList();
            Iterator<JSONObject> it = generateEvents.iterator();
            while (it.hasNext()) {
                JSONObject next = it.next();
                ClickstreamRetrofitClient client$clickstream_release = this.this$0.getClient$clickstream_release();
                Intrinsics.checkNotNullExpressionValue(next, "event");
                final ArrayList arrayList2 = arrayList;
                final Ref.IntRef intRef2 = intRef;
                final JSONObject jSONObject = eventJson;
                final ArrayList<JSONObject> arrayList3 = generateEvents;
                client$clickstream_release.sendMetrics(next, this.this$0.party_id, new Callback<ResponseBody>(this) {
                    final /* synthetic */ Clickstream$sendEvent$1 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public void onFailure(Call<ResponseBody> call, Throwable th) {
                        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
                        Intrinsics.checkNotNullParameter(th, Constants.APPBOY_PUSH_TITLE_KEY);
                        ClickstreamConstants.ClickstreamError clickstreamError = ClickstreamConstants.ClickstreamError.generic;
                        ArrayList arrayList = arrayList2;
                        if (arrayList != null) {
                            arrayList.add(clickstreamError);
                        }
                        Ref.IntRef intRef = intRef2;
                        intRef.element--;
                        if (intRef2.element == 0) {
                            this.this$0.this$0.didFailToSend(jSONObject, this.this$0.$impressions, arrayList2);
                        }
                    }

                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ClickstreamConstants.ClickstreamError clickstreamError;
                        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
                        Intrinsics.checkNotNullParameter(response, "response");
                        if (!response.isSuccessful()) {
                            if (response.code() == 413) {
                                clickstreamError = ClickstreamConstants.ClickstreamError.payloadSize;
                            } else {
                                clickstreamError = ClickstreamConstants.ClickstreamError.generic;
                            }
                            ArrayList arrayList = arrayList2;
                            if (arrayList != null) {
                                arrayList.add(clickstreamError);
                            }
                        }
                        Ref.IntRef intRef = intRef2;
                        intRef.element--;
                        if (intRef2.element == 0) {
                            ArrayList arrayList2 = arrayList2;
                            if (arrayList2 == null || arrayList2.size() <= 0) {
                                this.this$0.this$0.didSend(jSONObject, this.this$0.$impressions, arrayList3.size());
                            } else {
                                this.this$0.this$0.didFailToSend(jSONObject, this.this$0.$impressions, arrayList2);
                            }
                        }
                    }
                });
            }
        }
    }
}
