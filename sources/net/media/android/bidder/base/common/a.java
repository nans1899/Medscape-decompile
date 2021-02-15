package net.media.android.bidder.base.common;

import com.facebook.share.internal.ShareConstants;
import java.util.List;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.af;
import mnetinternal.ai;
import mnetinternal.ak;
import mnetinternal.an;
import net.media.android.bidder.base.configs.c;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.internal.BidRequest;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.FireLogsInfo;

public final class a {
    /* access modifiers changed from: private */
    public boolean a;
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public BidResponse f;
    /* access modifiers changed from: private */
    public List<BidResponse> g;
    /* access modifiers changed from: private */
    public AdRequest h;
    private int i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public String k;

    static /* synthetic */ int j(a aVar) {
        int i2 = aVar.i;
        aVar.i = i2 + 1;
        return i2;
    }

    private a(AdRequest adRequest, BidResponse bidResponse, List<BidResponse> list) {
        this.i = 0;
        this.j = c.a().a("MAX_HTTP_RETRIES");
        this.k = an.g();
        this.f = bidResponse;
        this.g = list;
        this.h = adRequest;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        this.e = i2;
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        this.d = z;
    }

    public void a() {
        aa.a((Runnable) new ac() {
            public void a() {
                String json = BidRequest.createForFireLogs(a.this.h, a.this.f, a.this.g, new FireLogsInfo(a.this.a, a.this.d, a.this.b, a.this.c), a.this.e).toJson();
                if (json == null || ((long) json.length()) >= c.a().b("url_length_max")) {
                    final ai a2 = new ai.a(a.this.k).a(json).a();
                    if (a.this.d) {
                        Logger.debug("##AuctionLogs##", "win log");
                    }
                    af.b(a2, new ak<String>() {
                        public Class<String> a() {
                            return String.class;
                        }

                        public void a(String str) {
                            Logger.debug("##AuctionLogs##", "auction logs fired " + a2.b());
                        }

                        public void a(Throwable th) {
                            Logger.debug("##AuctionLogs##", "error: " + th.getMessage());
                            if (a.j(a.this) < a.this.j) {
                                a.this.a();
                            }
                        }
                    });
                    return;
                }
                final ai a3 = new ai.a(a.this.k).a(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, json).a();
                af.a(a3, new ak<String>() {
                    public Class<String> a() {
                        return String.class;
                    }

                    public void a(String str) {
                        Logger.debug("##AuctionLogs##", "auction logs fired " + a3.b());
                    }

                    public void a(Throwable th) {
                        Logger.debug("##AuctionLogs##", "error: " + th.getMessage());
                        if (a.j(a.this) < a.this.j) {
                            a.this.a();
                        }
                    }
                });
            }
        });
    }

    /* renamed from: net.media.android.bidder.base.common.a$a  reason: collision with other inner class name */
    public static class C0043a {
        private a a;

        public C0043a(AdRequest adRequest, BidResponse bidResponse, List<BidResponse> list) {
            this.a = new a(adRequest, bidResponse, list);
        }

        public C0043a a() {
            this.a.a(true);
            return this;
        }

        public C0043a a(int i) {
            this.a.a(i);
            return this;
        }

        public a b() {
            if (this.a.d) {
                String unused = this.a.k = an.g();
            }
            return this.a;
        }
    }
}
