package net.media.android.bidder.base.common;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import mnetinternal.cb;
import mnetinternal.ci;
import mnetinternal.ck;
import mnetinternal.x;
import mnetinternal.z;
import net.media.android.bidder.base.ConsentStatus;
import net.media.android.bidder.base.SubjectToGDPR;
import net.media.android.bidder.base.configs.c;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;

public final class b {
    private static b a;
    private boolean b;
    /* access modifiers changed from: private */
    public SubjectToGDPR c = SubjectToGDPR.fromValue(cb.a().b("__mn__subject_to_gdpr", SubjectToGDPR.UNKNOWN.value()));
    private ConsentStatus d = ConsentStatus.fromValue(cb.a().b("__mn__consent_status", ConsentStatus.UNKNOWN.value()));
    private String e = cb.a().a("__mn__consent_string");

    private b() {
    }

    public static void a(boolean z) {
        a().b = z;
        x.a().a(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, new z() {
            public void a(Object obj) {
                if (c.a().e("eu_dnt") && c.a().e("is_eu") && b.a().c != SubjectToGDPR.ENABLED) {
                    Logger.debug("##DataPrivacy##", "subject to gdpr set to disabled, but config shows enabled, enabling gdpr");
                    b.a().a(SubjectToGDPR.ENABLED, ConsentStatus.UNKNOWN, "");
                }
            }
        });
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    private boolean j() {
        if (c.a().e("dnt") || this.b || ck.a().g()) {
            return true;
        }
        if (!b() || h()) {
            return false;
        }
        return true;
    }

    public boolean b() {
        if ((this.c != SubjectToGDPR.UNKNOWN || !c.a().e("unknown_p")) && this.c != SubjectToGDPR.DISABLED) {
            return true;
        }
        return false;
    }

    public SubjectToGDPR c() {
        return this.c;
    }

    public boolean d() {
        return b() && !h();
    }

    public boolean e() {
        return j();
    }

    public boolean f() {
        return this.b;
    }

    public String g() {
        return this.e;
    }

    public boolean h() {
        return this.d == ConsentStatus.GIVEN;
    }

    public ConsentStatus i() {
        return this.d;
    }

    public void a(SubjectToGDPR subjectToGDPR, ConsentStatus consentStatus, String str) {
        Logger.debug("##DataPrivacy##", "manual consent, " + subjectToGDPR + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + consentStatus + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        this.c = subjectToGDPR;
        int i = AnonymousClass2.a[subjectToGDPR.ordinal()];
        if (i == 1) {
            this.d = consentStatus;
            this.e = str;
        } else if (i == 2 || i == 3) {
            this.d = ConsentStatus.UNKNOWN;
            this.e = "";
        }
        cb.a().a("__mn__subject_to_gdpr", this.c.value());
        cb.a().a("__mn__consent_status", this.d.value());
        cb.a().a("__mn__consent_string", this.e);
        ck.a().b();
        ci.a().a(0);
    }

    /* renamed from: net.media.android.bidder.base.common.b$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                net.media.android.bidder.base.SubjectToGDPR[] r0 = net.media.android.bidder.base.SubjectToGDPR.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                net.media.android.bidder.base.SubjectToGDPR r1 = net.media.android.bidder.base.SubjectToGDPR.ENABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                net.media.android.bidder.base.SubjectToGDPR r1 = net.media.android.bidder.base.SubjectToGDPR.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                net.media.android.bidder.base.SubjectToGDPR r1 = net.media.android.bidder.base.SubjectToGDPR.DISABLED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.common.b.AnonymousClass2.<clinit>():void");
        }
    }
}
