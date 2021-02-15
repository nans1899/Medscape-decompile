package mnetinternal;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.configs.a;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public final class bb extends ai {
    private static final MediaType a = MediaType.parse("application/json; charset=utf-8");
    private Request.Builder b;
    private HttpUrl.Builder c;
    private int d = 1;
    private String e;
    private String f;
    private int g = -1;

    public bb(String str) {
        this.e = cb.a().b("__mn__ssl_handshake_failed") ? str.replaceFirst("https://", "http://") : str;
        this.b = new Request.Builder();
        this.c = HttpUrl.parse(this.e).newBuilder();
        a(this.b);
    }

    public void a(String str, String str2) {
        this.c.addEncodedQueryParameter(str, Uri.encode(str2));
    }

    public void b(String str, String str2) {
        this.b.addHeader(str, str2);
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(int i) {
        this.g = i;
    }

    public int a() {
        return this.g;
    }

    public String b() {
        return this.e;
    }

    public void b(int i) {
        this.d = i;
    }

    public Request c() {
        int i = this.d;
        if (i == 1) {
            this.b.get();
        } else if (i == 2) {
            this.b.put(b(this.f));
        } else if (i == 3) {
            this.b.post(b(this.f));
        }
        this.b.url(this.c.build());
        return this.b.build();
    }

    private RequestBody b(String str) {
        if (str == null) {
            try {
                return RequestBody.create(a, "{}".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return null;
            }
        } else {
            try {
                return RequestBody.create(a, str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    private void a(Request.Builder builder) {
        try {
            builder.addHeader("MN-SV", Constants.VERSION_CODE_STRING);
            builder.addHeader("MN-OV", Constants.SDK_INT_STRING);
            builder.addHeader("MN-DV", Constants.DEVICE_MODEL_STRING);
            builder.addHeader("MN-PLT", "android");
            builder.addHeader("MN-BID", MNet.getContext().getPackageName());
            builder.addHeader("MN-AV", "" + ck.a().c());
            builder.addHeader("MN-CV", a.a().b());
            builder.addHeader("MN-CID", MNet.getCustomerId());
            builder.addHeader("User-Agent", ck.a().f());
        } catch (Exception unused) {
        }
    }
}
