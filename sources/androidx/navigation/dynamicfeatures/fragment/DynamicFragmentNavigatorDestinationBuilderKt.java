package androidx.navigation.dynamicfeatures.fragment;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.Navigator;
import androidx.navigation.dynamicfeatures.DynamicNavGraphBuilder;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import net.media.android.bidder.base.models.MNetUser;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a#\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\b\u001a<\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\nH\b\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\nH\b¨\u0006\r"}, d2 = {"fragment", "", "F", "Landroidx/fragment/app/Fragment;", "Landroidx/navigation/dynamicfeatures/DynamicNavGraphBuilder;", "id", "", "builder", "Lkotlin/Function1;", "Landroidx/navigation/dynamicfeatures/fragment/DynamicFragmentNavigatorDestinationBuilder;", "Lkotlin/ExtensionFunctionType;", "fragmentClassName", "", "navigation-dynamic-features-fragment_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: DynamicFragmentNavigatorDestinationBuilder.kt */
public final class DynamicFragmentNavigatorDestinationBuilderKt {
    public static final /* synthetic */ <F extends Fragment> void fragment(DynamicNavGraphBuilder dynamicNavGraphBuilder, int i) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraphBuilder, "$this$fragment");
        NavGraphBuilder navGraphBuilder = dynamicNavGraphBuilder;
        Navigator navigator = navGraphBuilder.getProvider().getNavigator(FragmentNavigator.class);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
        Intrinsics.reifiedOperationMarker(4, MNetUser.FEMALE);
        navGraphBuilder.destination(new FragmentNavigatorDestinationBuilder((FragmentNavigator) navigator, i, Reflection.getOrCreateKotlinClass(Fragment.class)));
    }

    public static final /* synthetic */ <F extends Fragment> void fragment(DynamicNavGraphBuilder dynamicNavGraphBuilder, int i, Function1<? super DynamicFragmentNavigatorDestinationBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraphBuilder, "$this$fragment");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        Intrinsics.reifiedOperationMarker(4, MNetUser.FEMALE);
        String name = Fragment.class.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "F::class.java.name");
        Navigator navigator = dynamicNavGraphBuilder.getProvider().getNavigator(DynamicFragmentNavigator.class);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
        DynamicFragmentNavigatorDestinationBuilder dynamicFragmentNavigatorDestinationBuilder = new DynamicFragmentNavigatorDestinationBuilder((DynamicFragmentNavigator) navigator, i, name);
        function1.invoke(dynamicFragmentNavigatorDestinationBuilder);
        dynamicNavGraphBuilder.destination(dynamicFragmentNavigatorDestinationBuilder);
    }

    public static final void fragment(DynamicNavGraphBuilder dynamicNavGraphBuilder, int i, String str, Function1<? super DynamicFragmentNavigatorDestinationBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraphBuilder, "$this$fragment");
        Intrinsics.checkParameterIsNotNull(str, "fragmentClassName");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        Navigator navigator = dynamicNavGraphBuilder.getProvider().getNavigator(DynamicFragmentNavigator.class);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
        DynamicFragmentNavigatorDestinationBuilder dynamicFragmentNavigatorDestinationBuilder = new DynamicFragmentNavigatorDestinationBuilder((DynamicFragmentNavigator) navigator, i, str);
        function1.invoke(dynamicFragmentNavigatorDestinationBuilder);
        dynamicNavGraphBuilder.destination(dynamicFragmentNavigatorDestinationBuilder);
    }
}
