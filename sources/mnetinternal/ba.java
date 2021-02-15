package mnetinternal;

import android.content.Context;
import okhttp3.Cache;

final class ba {
    static Cache a(Context context) {
        return new Cache(context.getCacheDir(), 10485760);
    }
}
