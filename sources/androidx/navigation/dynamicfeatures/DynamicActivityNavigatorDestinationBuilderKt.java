package androidx.navigation.dynamicfeatures;

import androidx.navigation.Navigator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH\b¨\u0006\t"}, d2 = {"activity", "", "Landroidx/navigation/dynamicfeatures/DynamicNavGraphBuilder;", "id", "", "builder", "Lkotlin/Function1;", "Landroidx/navigation/dynamicfeatures/DynamicActivityNavigatorDestinationBuilder;", "Lkotlin/ExtensionFunctionType;", "navigation-dynamic-features-runtime_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: DynamicActivityNavigatorDestinationBuilder.kt */
public final class DynamicActivityNavigatorDestinationBuilderKt {
    public static final void activity(DynamicNavGraphBuilder dynamicNavGraphBuilder, int i, Function1<? super DynamicActivityNavigatorDestinationBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraphBuilder, "$this$activity");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        Navigator navigator = dynamicNavGraphBuilder.getProvider().getNavigator(DynamicActivityNavigator.class);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
        DynamicActivityNavigatorDestinationBuilder dynamicActivityNavigatorDestinationBuilder = new DynamicActivityNavigatorDestinationBuilder((DynamicActivityNavigator) navigator, i);
        function1.invoke(dynamicActivityNavigatorDestinationBuilder);
        dynamicNavGraphBuilder.destination(dynamicActivityNavigatorDestinationBuilder);
    }
}
