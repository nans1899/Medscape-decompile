package androidx.navigation.dynamicfeatures;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0012\u001a\u00020\u0002H\u0016J0\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0017H\u0016J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u0002H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006%"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicIncludeGraphNavigator;", "Landroidx/navigation/Navigator;", "Landroidx/navigation/dynamicfeatures/DynamicIncludeGraphNavigator$DynamicIncludeNavGraph;", "context", "Landroid/content/Context;", "navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "navInflater", "Landroidx/navigation/NavInflater;", "installManager", "Landroidx/navigation/dynamicfeatures/DynamicInstallManager;", "(Landroid/content/Context;Landroidx/navigation/NavigatorProvider;Landroidx/navigation/NavInflater;Landroidx/navigation/dynamicfeatures/DynamicInstallManager;)V", "createdDestinations", "", "packageName", "", "getPackageName", "()Ljava/lang/String;", "createDestination", "navigate", "Landroidx/navigation/NavDestination;", "destination", "args", "Landroid/os/Bundle;", "navOptions", "Landroidx/navigation/NavOptions;", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "onRestoreState", "", "savedState", "onSaveState", "popBackStack", "", "replaceWithIncludedNav", "Landroidx/navigation/NavGraph;", "DynamicIncludeNavGraph", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
@Navigator.Name("include-dynamic")
/* compiled from: DynamicIncludeGraphNavigator.kt */
public final class DynamicIncludeGraphNavigator extends Navigator<DynamicIncludeNavGraph> {
    private final Context context;
    private final List<DynamicIncludeNavGraph> createdDestinations = new ArrayList();
    private final DynamicInstallManager installManager;
    private final NavInflater navInflater;
    private final NavigatorProvider navigatorProvider;
    private final String packageName;

    public boolean popBackStack() {
        return true;
    }

    public DynamicIncludeGraphNavigator(Context context2, NavigatorProvider navigatorProvider2, NavInflater navInflater2, DynamicInstallManager dynamicInstallManager) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(navigatorProvider2, "navigatorProvider");
        Intrinsics.checkParameterIsNotNull(navInflater2, "navInflater");
        Intrinsics.checkParameterIsNotNull(dynamicInstallManager, "installManager");
        this.context = context2;
        this.navigatorProvider = navigatorProvider2;
        this.navInflater = navInflater2;
        this.installManager = dynamicInstallManager;
        String packageName2 = context2.getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName2, "context.packageName");
        this.packageName = packageName2;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public DynamicIncludeNavGraph createDestination() {
        DynamicIncludeNavGraph dynamicIncludeNavGraph = new DynamicIncludeNavGraph(this);
        this.createdDestinations.add(dynamicIncludeNavGraph);
        return dynamicIncludeNavGraph;
    }

    public NavDestination navigate(DynamicIncludeNavGraph dynamicIncludeNavGraph, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        Intrinsics.checkParameterIsNotNull(dynamicIncludeNavGraph, "destination");
        DynamicExtras dynamicExtras = (DynamicExtras) (!(extras instanceof DynamicExtras) ? null : extras);
        String moduleName = dynamicIncludeNavGraph.getModuleName();
        if (moduleName != null && this.installManager.needsInstall(moduleName)) {
            return this.installManager.performInstall(dynamicIncludeNavGraph, bundle, dynamicExtras, moduleName);
        }
        NavGraph replaceWithIncludedNav = replaceWithIncludedNav(dynamicIncludeNavGraph);
        NavigatorProvider navigatorProvider2 = this.navigatorProvider;
        String navigatorName = replaceWithIncludedNav.getNavigatorName();
        Intrinsics.checkExpressionValueIsNotNull(navigatorName, "includedNav.navigatorName");
        Navigator navigator = navigatorProvider2.getNavigator(navigatorName);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(name)");
        return navigator.navigate(replaceWithIncludedNav, bundle, navOptions, extras);
    }

    private final NavGraph replaceWithIncludedNav(DynamicIncludeNavGraph dynamicIncludeNavGraph) {
        int identifier = this.context.getResources().getIdentifier(dynamicIncludeNavGraph.getGraphResourceName(), NotificationCompat.CATEGORY_NAVIGATION, dynamicIncludeNavGraph.getGraphPackage());
        if (identifier != 0) {
            NavGraph inflate = this.navInflater.inflate(identifier);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "navInflater.inflate(graphId)");
            if (inflate.getId() == 0 || inflate.getId() == dynamicIncludeNavGraph.getId()) {
                inflate.setId(dynamicIncludeNavGraph.getId());
                NavGraph parent = dynamicIncludeNavGraph.getParent();
                if (parent != null) {
                    Intrinsics.checkExpressionValueIsNotNull(parent, "destination.parent\n     … NavGraph.\"\n            )");
                    parent.addDestination(inflate);
                    this.createdDestinations.remove(dynamicIncludeNavGraph);
                    return inflate;
                }
                throw new IllegalStateException("The include-dynamic destination with id " + dynamicIncludeNavGraph.getDisplayName() + ' ' + "does not have a parent. Make sure it is attached to a NavGraph.");
            }
            throw new IllegalStateException(("The included <navigation>'s id " + inflate.getDisplayName() + " is different from " + "the destination id " + dynamicIncludeNavGraph.getDisplayName() + ". Either remove the " + "<navigation> id or make them match.").toString());
        }
        throw new Resources.NotFoundException(dynamicIncludeNavGraph.getGraphPackage() + ":navigation/" + dynamicIncludeNavGraph.getGraphResourceName());
    }

    public Bundle onSaveState() {
        return Bundle.EMPTY;
    }

    public void onRestoreState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "savedState");
        super.onRestoreState(bundle);
        while (!this.createdDestinations.isEmpty()) {
            Iterator it = new ArrayList(this.createdDestinations).iterator();
            Intrinsics.checkExpressionValueIsNotNull(it, "ArrayList(createdDestinations).iterator()");
            this.createdDestinations.clear();
            while (it.hasNext()) {
                DynamicIncludeNavGraph dynamicIncludeNavGraph = (DynamicIncludeNavGraph) it.next();
                String moduleName = dynamicIncludeNavGraph.getModuleName();
                if (moduleName == null || !this.installManager.needsInstall(moduleName)) {
                    Intrinsics.checkExpressionValueIsNotNull(dynamicIncludeNavGraph, "dynamicNavGraph");
                    replaceWithIncludedNav(dynamicIncludeNavGraph);
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\n¨\u0006\u0019"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicIncludeGraphNavigator$DynamicIncludeNavGraph;", "Landroidx/navigation/NavDestination;", "navGraphNavigator", "Landroidx/navigation/Navigator;", "(Landroidx/navigation/Navigator;)V", "graphPackage", "", "getGraphPackage", "()Ljava/lang/String;", "setGraphPackage", "(Ljava/lang/String;)V", "graphResourceName", "getGraphResourceName", "setGraphResourceName", "moduleName", "getModuleName", "setModuleName", "getPackageOrDefault", "context", "Landroid/content/Context;", "getPackageOrDefault$navigation_dynamic_features_runtime_release", "onInflate", "", "attrs", "Landroid/util/AttributeSet;", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DynamicIncludeGraphNavigator.kt */
    public static final class DynamicIncludeNavGraph extends NavDestination {
        private String graphPackage;
        private String graphResourceName;
        private String moduleName;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DynamicIncludeNavGraph(Navigator<? extends NavDestination> navigator) {
            super(navigator);
            Intrinsics.checkParameterIsNotNull(navigator, "navGraphNavigator");
        }

        public final String getGraphResourceName() {
            return this.graphResourceName;
        }

        public final void setGraphResourceName(String str) {
            this.graphResourceName = str;
        }

        public final String getGraphPackage() {
            return this.graphPackage;
        }

        public final void setGraphPackage(String str) {
            this.graphPackage = str;
        }

        public final String getModuleName() {
            return this.moduleName;
        }

        public final void setModuleName(String str) {
            this.moduleName = str;
        }

        public void onInflate(Context context, AttributeSet attributeSet) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
            super.onInflate(context, attributeSet);
            int[] iArr = R.styleable.DynamicIncludeGraphNavigator;
            Intrinsics.checkExpressionValueIsNotNull(iArr, "R.styleable.DynamicIncludeGraphNavigator");
            boolean z = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
            String string = obtainStyledAttributes.getString(R.styleable.DynamicIncludeGraphNavigator_moduleName);
            this.moduleName = string;
            CharSequence charSequence = string;
            if (!(charSequence == null || charSequence.length() == 0)) {
                String string2 = obtainStyledAttributes.getString(R.styleable.DynamicIncludeGraphNavigator_graphPackage);
                if (string2 != null) {
                    if (!(string2.length() > 0)) {
                        throw new IllegalArgumentException(("`graphPackage` cannot be empty for <include-dynamic>. You can omit the `graphPackage` attribute entirely to use the " + "default of " + context.getPackageName() + '.' + this.moduleName + '.').toString());
                    }
                }
                this.graphPackage = getPackageOrDefault$navigation_dynamic_features_runtime_release(context, string2);
                String string3 = obtainStyledAttributes.getString(R.styleable.DynamicIncludeGraphNavigator_graphResName);
                this.graphResourceName = string3;
                CharSequence charSequence2 = string3;
                if (charSequence2 == null || charSequence2.length() == 0) {
                    z = true;
                }
                if (!z) {
                    obtainStyledAttributes.recycle();
                    return;
                }
                throw new IllegalArgumentException("`graphResName` must be set for <include-dynamic>".toString());
            }
            throw new IllegalArgumentException("`moduleName` must be set for <include-dynamic>".toString());
        }

        public final String getPackageOrDefault$navigation_dynamic_features_runtime_release(Context context, String str) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            if (str != null) {
                String packageName = context.getPackageName();
                Intrinsics.checkExpressionValueIsNotNull(packageName, "context.packageName");
                String replace$default = StringsKt.replace$default(str, NavInflater.APPLICATION_ID_PLACEHOLDER, packageName, false, 4, (Object) null);
                if (replace$default != null) {
                    return replace$default;
                }
            }
            return context.getPackageName() + '.' + this.moduleName;
        }
    }
}
