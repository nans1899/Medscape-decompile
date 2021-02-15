package androidx.navigation.dynamicfeatures;

import androidx.navigation.NavGraph;
import androidx.navigation.NavigatorProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0003\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\b\u001a:\u0010\u0000\u001a\u00020\t*\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\b¨\u0006\u000b"}, d2 = {"navigation", "Landroidx/navigation/NavGraph;", "Landroidx/navigation/NavigatorProvider;", "id", "", "startDestination", "builder", "Lkotlin/Function1;", "Landroidx/navigation/dynamicfeatures/DynamicNavGraphBuilder;", "", "Lkotlin/ExtensionFunctionType;", "navigation-dynamic-features-runtime_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: DynamicNavGraphBuilder.kt */
public final class DynamicNavGraphBuilderKt {
    public static /* synthetic */ NavGraph navigation$default(NavigatorProvider navigatorProvider, int i, int i2, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        DynamicNavGraphBuilder dynamicNavGraphBuilder = new DynamicNavGraphBuilder(navigatorProvider, i, i2);
        function1.invoke(dynamicNavGraphBuilder);
        return dynamicNavGraphBuilder.build();
    }

    public static final NavGraph navigation(NavigatorProvider navigatorProvider, int i, int i2, Function1<? super DynamicNavGraphBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        DynamicNavGraphBuilder dynamicNavGraphBuilder = new DynamicNavGraphBuilder(navigatorProvider, i, i2);
        function1.invoke(dynamicNavGraphBuilder);
        return dynamicNavGraphBuilder.build();
    }

    public static final void navigation(DynamicNavGraphBuilder dynamicNavGraphBuilder, int i, int i2, Function1<? super DynamicNavGraphBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraphBuilder, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        DynamicNavGraphBuilder dynamicNavGraphBuilder2 = new DynamicNavGraphBuilder(dynamicNavGraphBuilder.getProvider(), i, i2);
        function1.invoke(dynamicNavGraphBuilder2);
        dynamicNavGraphBuilder.destination(dynamicNavGraphBuilder2);
    }
}
