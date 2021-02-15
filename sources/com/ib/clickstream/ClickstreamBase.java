package com.ib.clickstream;

import com.ib.clickstream.ClickstreamConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0002`\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\u0002`\u000eH&¢\u0006\u0002\u0010\u000fJ1\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0002`\n2\u0006\u0010\u0011\u001a\u00020\u0012H&¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0017H&J\u001c\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u0019j\u0002`\u001aH&J\b\u0010\u001b\u001a\u00020\u0015H&¨\u0006\u001d"}, d2 = {"Lcom/ib/clickstream/ClickstreamBase;", "", "()V", "didFailToSend", "", "event", "Lorg/json/JSONObject;", "impressions", "", "Lcom/ib/clickstream/Impression;", "Lcom/ib/clickstream/Impressions;", "errors", "Ljava/util/ArrayList;", "Lcom/ib/clickstream/ClickstreamConstants$ClickstreamError;", "Lcom/ib/clickstream/Errors;", "(Lorg/json/JSONObject;[Lcom/ib/clickstream/Impression;Ljava/util/ArrayList;)V", "didSend", "batchCount", "", "(Lorg/json/JSONObject;[Lcom/ib/clickstream/Impression;I)V", "getSiteName", "", "getSiteParamsFilterList", "", "getSiteSpecificData", "Ljava/util/HashMap;", "Lcom/ib/clickstream/SiteSpecificParameters;", "getVertical", "Companion", "clickstream_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClickstreamBase.kt */
public abstract class ClickstreamBase {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static int MAX_PAY_LOAD_SIZE_BYTES = 3500;
    public static final String SHARED_PREF = "clickstream.pref";
    public static final String SHARED_PREF_PARTY_KEY = "shared.preference.party.keys";

    public abstract void didFailToSend(JSONObject jSONObject, Impression[] impressionArr, ArrayList<ClickstreamConstants.ClickstreamError> arrayList);

    public abstract void didSend(JSONObject jSONObject, Impression[] impressionArr, int i);

    public abstract String getSiteName();

    public abstract List<String> getSiteParamsFilterList();

    public abstract HashMap<String, String> getSiteSpecificData();

    public abstract String getVertical();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nXT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ib/clickstream/ClickstreamBase$Companion;", "", "()V", "MAX_PAY_LOAD_SIZE_BYTES", "", "getMAX_PAY_LOAD_SIZE_BYTES$clickstream_release", "()I", "setMAX_PAY_LOAD_SIZE_BYTES$clickstream_release", "(I)V", "SHARED_PREF", "", "SHARED_PREF_PARTY_KEY", "clickstream_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ClickstreamBase.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getMAX_PAY_LOAD_SIZE_BYTES$clickstream_release() {
            return ClickstreamBase.MAX_PAY_LOAD_SIZE_BYTES;
        }

        public final void setMAX_PAY_LOAD_SIZE_BYTES$clickstream_release(int i) {
            ClickstreamBase.MAX_PAY_LOAD_SIZE_BYTES = i;
        }
    }
}
