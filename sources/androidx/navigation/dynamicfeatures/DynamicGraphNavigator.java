package androidx.navigation.dynamicfeatures;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001:\u0001'B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u0014\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0010\u0010\u0013\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0002J0\u0010\u0018\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J!\u0010!\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010\u001cH\u0000¢\u0006\u0002\b#J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u001cH\u0016J\n\u0010&\u001a\u0004\u0018\u00010\u001cH\u0016R.\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator;", "Landroidx/navigation/NavGraphNavigator;", "navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "installManager", "Landroidx/navigation/dynamicfeatures/DynamicInstallManager;", "(Landroidx/navigation/NavigatorProvider;Landroidx/navigation/dynamicfeatures/DynamicInstallManager;)V", "<set-?>", "Lkotlin/Function0;", "Landroidx/navigation/NavDestination;", "defaultProgressDestinationSupplier", "getDefaultProgressDestinationSupplier$navigation_dynamic_features_runtime_release", "()Lkotlin/jvm/functions/Function0;", "destinationsWithoutDefaultProgressDestination", "", "Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator$DynamicNavGraph;", "getDestinationsWithoutDefaultProgressDestination$navigation_dynamic_features_runtime_release", "()Ljava/util/List;", "createDestination", "installDefaultProgressDestination", "", "progressDestinationSupplier", "", "dynamicNavGraph", "navigate", "destination", "Landroidx/navigation/NavGraph;", "args", "Landroid/os/Bundle;", "navOptions", "Landroidx/navigation/NavOptions;", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "navigateToProgressDestination", "progressArgs", "navigateToProgressDestination$navigation_dynamic_features_runtime_release", "onRestoreState", "savedState", "onSaveState", "DynamicNavGraph", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
@Navigator.Name("navigation")
/* compiled from: DynamicGraphNavigator.kt */
public final class DynamicGraphNavigator extends NavGraphNavigator {
    private Function0<? extends NavDestination> defaultProgressDestinationSupplier;
    private final List<DynamicNavGraph> destinationsWithoutDefaultProgressDestination = new ArrayList();
    private final DynamicInstallManager installManager;
    private final NavigatorProvider navigatorProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DynamicGraphNavigator(NavigatorProvider navigatorProvider2, DynamicInstallManager dynamicInstallManager) {
        super(navigatorProvider2);
        Intrinsics.checkParameterIsNotNull(navigatorProvider2, "navigatorProvider");
        Intrinsics.checkParameterIsNotNull(dynamicInstallManager, "installManager");
        this.navigatorProvider = navigatorProvider2;
        this.installManager = dynamicInstallManager;
    }

    public final Function0<NavDestination> getDefaultProgressDestinationSupplier$navigation_dynamic_features_runtime_release() {
        return this.defaultProgressDestinationSupplier;
    }

    public final List<DynamicNavGraph> getDestinationsWithoutDefaultProgressDestination$navigation_dynamic_features_runtime_release() {
        return this.destinationsWithoutDefaultProgressDestination;
    }

    public NavDestination navigate(NavGraph navGraph, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        String moduleName;
        Intrinsics.checkParameterIsNotNull(navGraph, "destination");
        DynamicExtras dynamicExtras = extras instanceof DynamicExtras ? (DynamicExtras) extras : null;
        if ((navGraph instanceof DynamicNavGraph) && (moduleName = ((DynamicNavGraph) navGraph).getModuleName()) != null && this.installManager.needsInstall(moduleName)) {
            return this.installManager.performInstall(navGraph, bundle, dynamicExtras, moduleName);
        }
        if (dynamicExtras != null) {
            extras = dynamicExtras.getDestinationExtras();
        }
        return super.navigate(navGraph, bundle, navOptions, extras);
    }

    public DynamicNavGraph createDestination() {
        return new DynamicNavGraph(this, this.navigatorProvider);
    }

    public final void installDefaultProgressDestination(Function0<? extends NavDestination> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "progressDestinationSupplier");
        this.defaultProgressDestinationSupplier = function0;
    }

    public final NavDestination navigateToProgressDestination$navigation_dynamic_features_runtime_release(DynamicNavGraph dynamicNavGraph, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(dynamicNavGraph, "dynamicNavGraph");
        int progressDestination = dynamicNavGraph.getProgressDestination();
        if (progressDestination == 0) {
            progressDestination = installDefaultProgressDestination(dynamicNavGraph);
        }
        NavDestination findNode = dynamicNavGraph.findNode(progressDestination);
        if (findNode != null) {
            Intrinsics.checkExpressionValueIsNotNull(findNode, "dynamicNavGraph.findNode…dule of this navigator.\")");
            Navigator navigator = this.navigatorProvider.getNavigator(findNode.getNavigatorName());
            Intrinsics.checkExpressionValueIsNotNull(navigator, "navigatorProvider.getNav…n.navigatorName\n        )");
            return navigator.navigate(findNode, bundle, (NavOptions) null, (Navigator.Extras) null);
        }
        throw new IllegalStateException("The progress destination id must be set and accessible to the module of this navigator.");
    }

    private final int installDefaultProgressDestination(DynamicNavGraph dynamicNavGraph) {
        Function0<? extends NavDestination> function0 = this.defaultProgressDestinationSupplier;
        if (function0 != null) {
            NavDestination navDestination = (NavDestination) function0.invoke();
            dynamicNavGraph.addDestination(navDestination);
            dynamicNavGraph.setProgressDestination(navDestination.getId());
            return navDestination.getId();
        }
        throw new IllegalStateException("You must set a default progress destination using DynamicNavGraphNavigator.installDefaultProgressDestination or pass in an DynamicInstallMonitor in the DynamicExtras.\nAlternatively, when using NavHostFragment make sure to swap it with DynamicNavHostFragment. This will take care of setting the default progress destination for you.".toString());
    }

    public Bundle onSaveState() {
        return Bundle.EMPTY;
    }

    public void onRestoreState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "savedState");
        super.onRestoreState(bundle);
        Iterator<DynamicNavGraph> it = this.destinationsWithoutDefaultProgressDestination.iterator();
        while (it.hasNext()) {
            installDefaultProgressDestination(it.next());
            it.remove();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00058\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator$DynamicNavGraph;", "Landroidx/navigation/NavGraph;", "navGraphNavigator", "Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator;", "navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "(Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator;Landroidx/navigation/NavigatorProvider;)V", "moduleName", "", "getModuleName", "()Ljava/lang/String;", "setModuleName", "(Ljava/lang/String;)V", "getNavGraphNavigator$navigation_dynamic_features_runtime_release", "()Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator;", "getNavigatorProvider$navigation_dynamic_features_runtime_release", "()Landroidx/navigation/NavigatorProvider;", "progressDestination", "", "getProgressDestination", "()I", "setProgressDestination", "(I)V", "onInflate", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "Companion", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DynamicGraphNavigator.kt */
    public static final class DynamicNavGraph extends NavGraph {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private String moduleName;
        private final DynamicGraphNavigator navGraphNavigator;
        private final NavigatorProvider navigatorProvider;
        private int progressDestination;

        public final DynamicGraphNavigator getNavGraphNavigator$navigation_dynamic_features_runtime_release() {
            return this.navGraphNavigator;
        }

        public final NavigatorProvider getNavigatorProvider$navigation_dynamic_features_runtime_release() {
            return this.navigatorProvider;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DynamicNavGraph(DynamicGraphNavigator dynamicGraphNavigator, NavigatorProvider navigatorProvider2) {
            super(dynamicGraphNavigator);
            Intrinsics.checkParameterIsNotNull(dynamicGraphNavigator, "navGraphNavigator");
            Intrinsics.checkParameterIsNotNull(navigatorProvider2, "navigatorProvider");
            this.navGraphNavigator = dynamicGraphNavigator;
            this.navigatorProvider = navigatorProvider2;
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator$DynamicNavGraph$Companion;", "", "()V", "getOrThrow", "Landroidx/navigation/dynamicfeatures/DynamicGraphNavigator$DynamicNavGraph;", "destination", "Landroidx/navigation/NavDestination;", "getOrThrow$navigation_dynamic_features_runtime_release", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
        /* compiled from: DynamicGraphNavigator.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final DynamicNavGraph getOrThrow$navigation_dynamic_features_runtime_release(NavDestination navDestination) {
                Intrinsics.checkParameterIsNotNull(navDestination, "destination");
                NavGraph parent = navDestination.getParent();
                if (!(parent instanceof DynamicNavGraph)) {
                    parent = null;
                }
                DynamicNavGraph dynamicNavGraph = (DynamicNavGraph) parent;
                if (dynamicNavGraph != null) {
                    return dynamicNavGraph;
                }
                throw new IllegalStateException("Dynamic destinations must be part of a DynamicNavGraph.\nYou can use DynamicNavHostFragment, which will take care of setting up the NavController for Dynamic destinations.\nIf you're not using Fragments, you must set up the NavigatorProvider manually.");
            }
        }

        public final String getModuleName() {
            return this.moduleName;
        }

        public final void setModuleName(String str) {
            this.moduleName = str;
        }

        public final int getProgressDestination() {
            return this.progressDestination;
        }

        public final void setProgressDestination(int i) {
            this.progressDestination = i;
        }

        public void onInflate(Context context, AttributeSet attributeSet) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
            super.onInflate(context, attributeSet);
            int[] iArr = R.styleable.DynamicGraphNavigator;
            Intrinsics.checkExpressionValueIsNotNull(iArr, "R.styleable.DynamicGraphNavigator");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
            this.moduleName = obtainStyledAttributes.getString(R.styleable.DynamicGraphNavigator_moduleName);
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.DynamicGraphNavigator_progressDestination, 0);
            this.progressDestination = resourceId;
            if (resourceId == 0) {
                this.navGraphNavigator.getDestinationsWithoutDefaultProgressDestination$navigation_dynamic_features_runtime_release().add(this);
            }
            obtainStyledAttributes.recycle();
        }
    }
}
