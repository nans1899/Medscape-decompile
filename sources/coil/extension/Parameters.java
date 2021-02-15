package coil.extension;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\n\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0002H\b¨\u0006\t"}, d2 = {"count", "", "Lcoil/request/Parameters;", "get", "", "key", "", "isNotEmpty", "", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: Parameters.kt */
public final class Parameters {
    public static final int count(coil.request.Parameters parameters) {
        Intrinsics.checkParameterIsNotNull(parameters, "$this$count");
        return parameters.size();
    }

    public static final boolean isNotEmpty(coil.request.Parameters parameters) {
        Intrinsics.checkParameterIsNotNull(parameters, "$this$isNotEmpty");
        return !parameters.isEmpty();
    }

    public static final Object get(coil.request.Parameters parameters, String str) {
        Intrinsics.checkParameterIsNotNull(parameters, "$this$get");
        Intrinsics.checkParameterIsNotNull(str, "key");
        return parameters.value(str);
    }
}
