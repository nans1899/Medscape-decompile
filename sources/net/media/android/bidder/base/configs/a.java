package net.media.android.bidder.base.configs;

import android.content.Context;
import com.google.common.net.HttpHeaders;
import com.mnet.gson.f;
import com.mnet.gson.n;
import java.util.concurrent.TimeUnit;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.af;
import mnetinternal.ai;
import mnetinternal.ak;
import mnetinternal.an;
import mnetinternal.cb;
import mnetinternal.x;
import mnetinternal.y;
import mnetinternal.z;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import net.media.android.bidder.base.models.internal.Config;

public final class a {
    /* access modifiers changed from: private */
    public static a a = new a();
    /* access modifiers changed from: private */
    public n b;

    private a() {
    }

    public static void a(final Context context) {
        Logger.debug("##ConfigController##", "configure controller init");
        aa.a((Runnable) new ac() {
            public void a() {
                a.a.b(context);
                x.a().a(AdTrackerEvent.EVENT_APP_IN_FOREGROUND, new z() {
                    public void a(Object obj) {
                        a.a.a(0);
                    }
                });
            }
        });
    }

    public static a a() {
        return a;
    }

    /* access modifiers changed from: private */
    public void b(Context context) {
        try {
            cb.a().a("__mn__config_expired", false);
            n nVar = (n) new f().a().b().a(cb.a().a("__mn__rw_cfg"), n.class);
            this.b = nVar;
            if (nVar == null) {
                Logger.debug("##ConfigController##", "stored config is null");
                e();
                return;
            }
            f();
            e();
        } catch (Exception e) {
            Logger.notify("##ConfigController##", e.getMessage(), e);
            e();
        }
    }

    /* access modifiers changed from: private */
    public void a(final Config config) {
        aa.a((Runnable) new ac() {
            public void a() {
                Logger.debug("##ConfigController##", "saving configs");
                try {
                    if (a.this.a(config.getAllConfigs()) >= a.this.a(a.this.b)) {
                        cb.a().a("__mn__config_expired", false);
                        Logger.debug("##ConfigController##", "newer config available, enabling requests now");
                    }
                    n unused = a.this.b = config.getAllConfigs();
                    a.this.f();
                    cb.a().a("__mn__rw_cfg", a.this.b.toString());
                } catch (Exception e) {
                    Logger.error("##ConfigController##", e.getMessage(), e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final int i) {
        Logger.debug("##ConfigController##", "checking for update");
        af.a(new ai.a(an.c() + "/" + MNet.getCustomerId() + "/" + MNet.getContext().getPackageName()).b(HttpHeaders.CACHE_CONTROL, "no-cache").a(), new ak<Config>() {
            public Class<Config> a() {
                return Config.class;
            }

            public void a(Config config) {
                if (config == null) {
                    Logger.debug("##ConfigController##", "null configs");
                    return;
                }
                Logger.debug("##ConfigController##", "got configs");
                a.this.a(config);
            }

            public void a(Throwable th) {
                Logger.debug("##ConfigController##", th.getMessage());
                if (i <= 5) {
                    aa.a(new ac() {
                        public void a() {
                            a.this.a(i + 1);
                        }
                    }, (long) (i * 1000), TimeUnit.MILLISECONDS);
                }
            }
        });
    }

    private void e() {
        int a2 = c.a().a("mnet_config_update_interval");
        Logger.debug("##ConfigController##", "scheduling config sync for interval " + a2);
        aa.a(new ac() {
            public void a() {
                a.this.a(0);
            }
        }, 0, (long) a2, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    public n a(String str) {
        n nVar = this.b;
        if (nVar == null || nVar.b(str) == null) {
            return null;
        }
        return (n) this.b.b(str);
    }

    /* access modifiers changed from: private */
    public void f() {
        Logger.debug("##ConfigController##", "fire config fetch complete");
        x.a().a((y) new y() {
            public String a() {
                return AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE;
            }

            public Object b() {
                return null;
            }
        });
    }

    public String b() {
        return String.valueOf(a(this.b));
    }

    /* access modifiers changed from: private */
    public int a(n nVar) {
        if (nVar != null && nVar.a("version")) {
            try {
                return nVar.b("version").g();
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public void c() {
        a(0);
    }
}
