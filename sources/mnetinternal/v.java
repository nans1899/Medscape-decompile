package mnetinternal;

import android.text.TextUtils;
import mnetinternal.ai;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.macro.b;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.BidResponse;

final class v implements u {
    v() {
    }

    public boolean a(BidResponse bidResponse) {
        if (0 == bidResponse.getExpiry()) {
            return true;
        }
        if (bidResponse.isAdx()) {
            return false;
        }
        if ((bidResponse.getExpiry() - 200) - System.currentTimeMillis() > 0) {
            return true;
        }
        b(bidResponse);
        return false;
    }

    private void b(final BidResponse bidResponse) {
        aa.a((Runnable) new ac() {
            public void a() {
                if (bidResponse.getExpiryLogs() != null) {
                    for (String a2 : bidResponse.getExpiryLogs()) {
                        String a3 = b.a(a2, bidResponse);
                        if (da.a(a3)) {
                            af.a(new ai.a(a3).a(), new ak<String>() {
                                public Class<String> a() {
                                    return String.class;
                                }

                                public void a(String str) {
                                    Logger.debug("##MNetBidValidator##", "expiry logs fired");
                                }

                                public void a(Throwable th) {
                                    Logger.warning("##MNetBidValidator##", "expiry logs error: ", th);
                                }
                            });
                        }
                    }
                }
                if (!TextUtils.isEmpty(bidResponse.getPredictionId())) {
                    net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("predicted_bid_expired").addProperty("prediction_id", bidResponse.getPredictionId()));
                }
            }
        });
    }
}
