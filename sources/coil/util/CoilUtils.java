package coil.util;

import android.content.Context;
import android.view.View;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lcoil/util/CoilUtils;", "", "()V", "clear", "", "view", "Landroid/view/View;", "createDefaultCache", "Lokhttp3/Cache;", "context", "Landroid/content/Context;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CoilUtils.kt */
public final class CoilUtils {
    public static final CoilUtils INSTANCE = new CoilUtils();

    private CoilUtils() {
    }

    @JvmStatic
    public static final Cache createDefaultCache(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        File defaultCacheDirectory = Utils.INSTANCE.getDefaultCacheDirectory(context);
        return new Cache(defaultCacheDirectory, Utils.INSTANCE.calculateDiskCacheSize(defaultCacheDirectory));
    }

    @JvmStatic
    public static final void clear(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Extensions.getRequestManager(view).clearCurrentRequest();
    }
}
