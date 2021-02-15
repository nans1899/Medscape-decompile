package mnetinternal;

import com.facebook.appevents.AppEventsConstants;
import net.media.android.bidder.base.configs.AdUnitConfig;

public abstract class ai {
    public abstract int a();

    /* access modifiers changed from: protected */
    public abstract void a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(String str);

    /* access modifiers changed from: protected */
    public abstract void a(String str, String str2);

    public abstract String b();

    /* access modifiers changed from: protected */
    public abstract void b(int i);

    /* access modifiers changed from: protected */
    public abstract void b(String str, String str2);

    protected ai() {
    }

    public static class a {
        private ai a;

        public a(String str) {
            this.a = new bb(str);
        }

        public a a(String str, String str2) {
            this.a.a(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.a.b(str, str2);
            return this;
        }

        public a a(String str) {
            if (AdUnitConfig.getInstance().getPublisherConfig().shouldEncode()) {
                String a2 = ct.a(str.getBytes());
                b("MN-E", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                this.a.a(a2);
            } else {
                this.a.a(str);
            }
            return this;
        }

        public a a(int i) {
            this.a.a(i);
            return this;
        }

        public ai a() {
            return this.a;
        }
    }
}
