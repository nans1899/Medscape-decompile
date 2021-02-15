package androidx.navigation.dynamicfeatures;

import androidx.navigation.NavDestinationDsl;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.dynamicfeatures.DynamicGraphNavigator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicNavGraphBuilder;", "Landroidx/navigation/NavGraphBuilder;", "provider", "Landroidx/navigation/NavigatorProvider;", "id", "", "startDestination", "(Landroidx/navigation/NavigatorProvider;II)V", "moduleName", "", "getModuleName", "()Ljava/lang/String;", "setModuleName", "(Ljava/lang/String;)V", "progressDestination", "getProgressDestination", "()I", "setProgressDestination", "(I)V", "build", "Landroidx/navigation/NavGraph;", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
@NavDestinationDsl
/* compiled from: DynamicNavGraphBuilder.kt */
public final class DynamicNavGraphBuilder extends NavGraphBuilder {
    private String moduleName;
    private int progressDestination;
    private int startDestination;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DynamicNavGraphBuilder(NavigatorProvider navigatorProvider, int i, int i2) {
        super(navigatorProvider, i, i2);
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "provider");
        this.startDestination = i2;
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

    public NavGraph build() {
        NavGraph build = super.build();
        if (build instanceof DynamicGraphNavigator.DynamicNavGraph) {
            DynamicGraphNavigator.DynamicNavGraph dynamicNavGraph = (DynamicGraphNavigator.DynamicNavGraph) build;
            dynamicNavGraph.setModuleName(this.moduleName);
            dynamicNavGraph.setProgressDestination(this.progressDestination);
            if (this.progressDestination == 0) {
                Navigator navigator = getProvider().getNavigator(DynamicGraphNavigator.class);
                Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
                ((DynamicGraphNavigator) navigator).getDestinationsWithoutDefaultProgressDestination$navigation_dynamic_features_runtime_release().add(build);
            }
        }
        return build;
    }
}
