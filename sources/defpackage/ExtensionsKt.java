package defpackage;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a*\u0010\u0000\u001a\u00020\u0001*\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u001a*\u0010\u0007\u001a\u00020\u0001*\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¨\u0006\b"}, d2 = {"addIfValueInMapIsNullOrEmpty", "", "", "", "", "key", "value", "addIfValueIsNotNullOrEmpty", "wbmd.omniture_release"}, k = 2, mv = {1, 4, 0})
/* renamed from: ExtensionsKt  reason: default package */
/* compiled from: Extensions.kt */
public final class ExtensionsKt {
    public static final void addIfValueIsNotNullOrEmpty(Map<String, Object> map, String str, String str2) {
        Intrinsics.checkNotNullParameter(map, "$this$addIfValueIsNotNullOrEmpty");
        Intrinsics.checkNotNullParameter(str, "key");
        CharSequence charSequence = str2;
        if (!(charSequence == null || charSequence.length() == 0) && !StringsKt.equals(str2, "null", true)) {
            map.put(str, str2);
        }
    }

    public static final void addIfValueInMapIsNullOrEmpty(Map<String, Object> map, String str, String str2) {
        Intrinsics.checkNotNullParameter(map, "$this$addIfValueInMapIsNullOrEmpty");
        Intrinsics.checkNotNullParameter(str, "key");
        if (map.get(str) != null) {
            if (map.get(str) instanceof String) {
                if (!(String.valueOf(map.get(str)).length() == 0)) {
                    return;
                }
            } else {
                return;
            }
        }
        addIfValueIsNotNullOrEmpty(map, str, str2);
    }
}
