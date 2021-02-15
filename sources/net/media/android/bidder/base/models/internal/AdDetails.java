package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class AdDetails {
    @c(a = "ads_details")
    protected List<BidResponse> mBidResponses;
    @c(a = "ext")
    protected Map<String, Object> mExtensions;

    protected AdDetails() {
    }

    public List<BidResponse> getBidResponses() {
        return this.mBidResponses;
    }

    public Map<String, Object> getExtensions() {
        return this.mExtensions;
    }

    public final class TypeAdapter extends v<AdDetails> {
        public static final g<AdDetails> TYPE_TOKEN = g.b(AdDetails.class);
        private final e mGson;
        private final v<BidResponse> mTypeAdapter0;
        private final v<List<BidResponse>> mTypeAdapter1;
        private final v<Object> mTypeAdapter2;
        private final v<Map<String, Object>> mTypeAdapter3 = new p.f(i.A, this.mTypeAdapter2, new p.e());

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            g<Object> b = g.b(Object.class);
            v<BidResponse> a = eVar.a(BidResponse.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter0 = a;
            this.mTypeAdapter1 = new p.d(a, new p.c());
            this.mTypeAdapter2 = eVar.a(b);
        }

        public void write(j jVar, AdDetails adDetails) {
            if (adDetails == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("ads_details");
            if (adDetails.mBidResponses != null) {
                this.mTypeAdapter1.write(jVar, adDetails.mBidResponses);
            } else {
                jVar.f();
            }
            jVar.a("ext");
            if (adDetails.mExtensions != null) {
                this.mTypeAdapter3.write(jVar, adDetails.mExtensions);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public AdDetails read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                AdDetails adDetails = new AdDetails();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    int hashCode = g.hashCode();
                    if (hashCode != 100897) {
                        if (hashCode == 1535009203 && g.equals("ads_details")) {
                            c = 0;
                        }
                    } else if (g.equals("ext")) {
                        c = 1;
                    }
                    if (c == 0) {
                        adDetails.mBidResponses = this.mTypeAdapter1.read(hVar);
                    } else if (c != 1) {
                        hVar.n();
                    } else {
                        adDetails.mExtensions = this.mTypeAdapter3.read(hVar);
                    }
                }
                hVar.d();
                return adDetails;
            }
        }
    }

    public String[] getAuctionProcessLogs() {
        Map<String, Object> map = this.mExtensions;
        if (map == null || !map.containsKey("ap_logs")) {
            return null;
        }
        ArrayList arrayList = (ArrayList) this.mExtensions.get("ap_logs");
        if (arrayList == null) {
            return new String[0];
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
