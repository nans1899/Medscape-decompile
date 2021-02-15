package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;
import mnetinternal.p;

public final class FireLogsInfo {
    @c(a = "aplog")
    protected boolean mIsAuctionProcessLogs;
    @c(a = "awlog")
    protected boolean mIsAuctionWinLogs;
    @c(a = "prlog")
    protected boolean mIsPrLogs;
    @c(a = "prflog")
    protected boolean mIsPrfLogs;

    public final class TypeAdapter extends v<FireLogsInfo> {
        public static final g<FireLogsInfo> TYPE_TOKEN = g.b(FireLogsInfo.class);
        private final e mGson;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
        }

        public void write(j jVar, FireLogsInfo fireLogsInfo) {
            if (fireLogsInfo == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("prflog");
            jVar.a(fireLogsInfo.mIsPrfLogs);
            jVar.a("prlog");
            jVar.a(fireLogsInfo.mIsPrLogs);
            jVar.a("awlog");
            jVar.a(fireLogsInfo.mIsAuctionWinLogs);
            jVar.a("aplog");
            jVar.a(fireLogsInfo.mIsAuctionProcessLogs);
            jVar.e();
        }

        public FireLogsInfo read(h hVar) {
            i f = hVar.f();
            if (i.NULL == f) {
                hVar.j();
                return null;
            } else if (i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                FireLogsInfo fireLogsInfo = new FireLogsInfo();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -980074976:
                            if (g.equals("prflog")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 93025461:
                            if (g.equals("aplog")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 93233998:
                            if (g.equals("awlog")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 106937858:
                            if (g.equals("prlog")) {
                                c = 1;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        fireLogsInfo.mIsPrfLogs = p.g.a(hVar, fireLogsInfo.mIsPrfLogs);
                    } else if (c == 1) {
                        fireLogsInfo.mIsPrLogs = p.g.a(hVar, fireLogsInfo.mIsPrLogs);
                    } else if (c == 2) {
                        fireLogsInfo.mIsAuctionWinLogs = p.g.a(hVar, fireLogsInfo.mIsAuctionWinLogs);
                    } else if (c != 3) {
                        hVar.n();
                    } else {
                        fireLogsInfo.mIsAuctionProcessLogs = p.g.a(hVar, fireLogsInfo.mIsAuctionProcessLogs);
                    }
                }
                hVar.d();
                return fireLogsInfo;
            }
        }
    }

    protected FireLogsInfo() {
    }

    public FireLogsInfo(boolean z, boolean z2, boolean z3, boolean z4) {
        this.mIsAuctionProcessLogs = z2;
        this.mIsAuctionWinLogs = z;
        this.mIsPrfLogs = z3;
        this.mIsPrLogs = z4;
    }

    public void setIsPrfLogs(boolean z) {
        this.mIsPrfLogs = z;
    }

    public void setIsPrLogs(boolean z) {
        this.mIsPrLogs = z;
    }

    public void setIsAuctionWinLogs(boolean z) {
        this.mIsAuctionWinLogs = z;
    }

    public void setisAuctionProcessLogs(boolean z) {
        this.mIsAuctionProcessLogs = z;
    }
}
