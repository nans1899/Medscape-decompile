package mnetinternal;

import com.mnet.gson.k;
import java.io.InputStream;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.models.internal.LoggerResponse;

public final class aw extends ap<LoggerResponse> {
    /* renamed from: a */
    public LoggerResponse b(InputStream inputStream) {
        return (LoggerResponse) a.b().a((k) d(inputStream), LoggerResponse.class);
    }
}
