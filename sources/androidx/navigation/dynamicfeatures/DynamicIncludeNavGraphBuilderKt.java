package androidx.navigation.dynamicfeatures;

import androidx.navigation.Navigator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\b\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u000bH\b¨\u0006\f"}, d2 = {"includeDynamic", "", "Landroidx/navigation/dynamicfeatures/DynamicNavGraphBuilder;", "id", "", "moduleName", "", "graphResourceName", "builder", "Lkotlin/Function1;", "Landroidx/navigation/dynamicfeatures/DynamicIncludeNavGraphBuilder;", "Lkotlin/ExtensionFunctionType;", "navigation-dynamic-features-runtime_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: DynamicIncludeNavGraphBuilder.kt */
public final class DynamicIncludeNavGraphBuilderKt {
    public static final void includeDynamic(DynamicNavGraphBuilder dynamicNavGraphBuilder, int i, String str, String str2, Function1<? super DynamicIncludeNavGraphBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraphBuilder, "$this$includeDynamic");
        Intrinsics.checkParameterIsNotNull(str, "moduleName");
        Intrinsics.checkParameterIsNotNull(str2, "graphResourceName");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        Navigator navigator = dynamicNavGraphBuilder.getProvider().getNavigator(DynamicIncludeGraphNavigator.class);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
        DynamicIncludeNavGraphBuilder dynamicIncludeNavGraphBuilder = new DynamicIncludeNavGraphBuilder((DynamicIncludeGraphNavigator) navigator, i, str, str2);
        function1.invoke(dynamicIncludeNavGraphBuilder);
        dynamicNavGraphBuilder.destination(dynamicIncludeNavGraphBuilder);
    }

    public static final void includeDynamic(DynamicNavGraphBuilder dynamicNavGraphBuilder, int i, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraphBuilder, "$this$includeDynamic");
        Intrinsics.checkParameterIsNotNull(str, "moduleName");
        Intrinsics.checkParameterIsNotNull(str2, "graphResourceName");
        Navigator navigator = dynamicNavGraphBuilder.getProvider().getNavigator(DynamicIncludeGraphNavigator.class);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
        dynamicNavGraphBuilder.destination(new DynamicIncludeNavGraphBuilder((DynamicIncludeGraphNavigator) navigator, i, str, str2));
    }
}
