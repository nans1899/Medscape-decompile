package coil.util;

import com.wbmd.wbmddrugscommons.constants.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\b\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"log", "", "Lcoil/util/Logger;", "tag", "", "priority", "", "lazyMessage", "Lkotlin/Function0;", "throwable", "", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* renamed from: coil.util.-Logs  reason: invalid class name */
/* compiled from: Logs.kt */
public final class Logs {
    public static final void log(Logger logger, String str, int i, Function0<String> function0) {
        Intrinsics.checkParameterIsNotNull(logger, "$this$log");
        Intrinsics.checkParameterIsNotNull(str, Constants.WBMDDrugKeyTag);
        Intrinsics.checkParameterIsNotNull(function0, "lazyMessage");
        if (logger.getLevel() <= i) {
            logger.log(str, i, function0.invoke(), (Throwable) null);
        }
    }

    public static final void log(Logger logger, String str, Throwable th) {
        Intrinsics.checkParameterIsNotNull(logger, "$this$log");
        Intrinsics.checkParameterIsNotNull(str, Constants.WBMDDrugKeyTag);
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        if (logger.getLevel() <= 6) {
            logger.log(str, 6, (String) null, th);
        }
    }
}
