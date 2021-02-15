package androidx.navigation.dynamicfeatures.fragment;

import androidx.navigation.dynamicfeatures.fragment.DynamicFragmentNavigator;
import androidx.navigation.dynamicfeatures.fragment.ui.DefaultProgressFragment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/navigation/dynamicfeatures/fragment/DynamicFragmentNavigator$Destination;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: DynamicNavHostFragment.kt */
final class DynamicNavHostFragment$onCreateNavController$1 extends Lambda implements Function0<DynamicFragmentNavigator.Destination> {
    final /* synthetic */ DynamicFragmentNavigator $fragmentNavigator;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DynamicNavHostFragment$onCreateNavController$1(DynamicFragmentNavigator dynamicFragmentNavigator) {
        super(0);
        this.$fragmentNavigator = dynamicFragmentNavigator;
    }

    public final DynamicFragmentNavigator.Destination invoke() {
        DynamicFragmentNavigator.Destination createDestination = this.$fragmentNavigator.createDestination();
        createDestination.setClassName(DefaultProgressFragment.class.getName());
        createDestination.setId(R.id.dfn_progress_fragment);
        return createDestination;
    }
}
