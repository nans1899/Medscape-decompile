package androidx.navigation.dynamicfeatures.fragment;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavInflater;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.dynamicfeatures.DynamicActivityNavigator;
import androidx.navigation.dynamicfeatures.DynamicGraphNavigator;
import androidx.navigation.dynamicfeatures.DynamicIncludeGraphNavigator;
import androidx.navigation.dynamicfeatures.DynamicInstallManager;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\t"}, d2 = {"Landroidx/navigation/dynamicfeatures/fragment/DynamicNavHostFragment;", "Landroidx/navigation/fragment/NavHostFragment;", "()V", "createSplitInstallManager", "Lcom/google/android/play/core/splitinstall/SplitInstallManager;", "onCreateNavController", "", "navController", "Landroidx/navigation/NavController;", "navigation-dynamic-features-fragment_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DynamicNavHostFragment.kt */
public class DynamicNavHostFragment extends NavHostFragment {
    /* access modifiers changed from: protected */
    public void onCreateNavController(NavController navController) {
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        super.onCreateNavController(navController);
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        DynamicInstallManager dynamicInstallManager = new DynamicInstallManager(requireContext, createSplitInstallManager());
        NavigatorProvider navigatorProvider = navController.getNavigatorProvider();
        Intrinsics.checkExpressionValueIsNotNull(navigatorProvider, "navController.navigatorProvider");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        navigatorProvider.addNavigator(new DynamicActivityNavigator(requireActivity, dynamicInstallManager));
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        DynamicFragmentNavigator dynamicFragmentNavigator = new DynamicFragmentNavigator(requireContext2, childFragmentManager, getId(), dynamicInstallManager);
        navigatorProvider.addNavigator(dynamicFragmentNavigator);
        DynamicGraphNavigator dynamicGraphNavigator = new DynamicGraphNavigator(navigatorProvider, dynamicInstallManager);
        dynamicGraphNavigator.installDefaultProgressDestination((Function0<? extends NavDestination>) new DynamicNavHostFragment$onCreateNavController$1(dynamicFragmentNavigator));
        navigatorProvider.addNavigator(dynamicGraphNavigator);
        Context requireContext3 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
        NavInflater navInflater = navController.getNavInflater();
        Intrinsics.checkExpressionValueIsNotNull(navInflater, "navController.navInflater");
        navigatorProvider.addNavigator(new DynamicIncludeGraphNavigator(requireContext3, navigatorProvider, navInflater, dynamicInstallManager));
    }

    /* access modifiers changed from: protected */
    public SplitInstallManager createSplitInstallManager() {
        SplitInstallManager create = SplitInstallManagerFactory.create(requireContext());
        Intrinsics.checkExpressionValueIsNotNull(create, "SplitInstallManagerFacto….create(requireContext())");
        return create;
    }
}
