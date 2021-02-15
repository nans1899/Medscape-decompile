package mnetinternal;

import com.facebook.internal.AnalyticsEvents;
import com.mnet.gson.n;
import java.io.InputStream;

public final class au extends ap<aj> {
    /* renamed from: a */
    public aj b(InputStream inputStream) {
        n d = d(inputStream);
        if (!d.a("success") || !d.b("success").h()) {
            return new aj(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED, false);
        }
        return new aj("Success", true);
    }
}
