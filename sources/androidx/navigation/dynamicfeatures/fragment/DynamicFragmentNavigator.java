package androidx.navigation.dynamicfeatures.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.dynamicfeatures.DynamicExtras;
import androidx.navigation.dynamicfeatures.DynamicInstallManager;
import androidx.navigation.fragment.FragmentNavigator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0017B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J0\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/navigation/dynamicfeatures/fragment/DynamicFragmentNavigator;", "Landroidx/navigation/fragment/FragmentNavigator;", "context", "Landroid/content/Context;", "manager", "Landroidx/fragment/app/FragmentManager;", "containerId", "", "installManager", "Landroidx/navigation/dynamicfeatures/DynamicInstallManager;", "(Landroid/content/Context;Landroidx/fragment/app/FragmentManager;ILandroidx/navigation/dynamicfeatures/DynamicInstallManager;)V", "createDestination", "Landroidx/navigation/dynamicfeatures/fragment/DynamicFragmentNavigator$Destination;", "navigate", "Landroidx/navigation/NavDestination;", "destination", "Landroidx/navigation/fragment/FragmentNavigator$Destination;", "args", "Landroid/os/Bundle;", "navOptions", "Landroidx/navigation/NavOptions;", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "Destination", "navigation-dynamic-features-fragment_release"}, k = 1, mv = {1, 1, 16})
@Navigator.Name("fragment")
/* compiled from: DynamicFragmentNavigator.kt */
public final class DynamicFragmentNavigator extends FragmentNavigator {
    private final DynamicInstallManager installManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DynamicFragmentNavigator(Context context, FragmentManager fragmentManager, int i, DynamicInstallManager dynamicInstallManager) {
        super(context, fragmentManager, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "manager");
        Intrinsics.checkParameterIsNotNull(dynamicInstallManager, "installManager");
        this.installManager = dynamicInstallManager;
    }

    public Destination createDestination() {
        return new Destination((Navigator<? extends FragmentNavigator.Destination>) this);
    }

    public NavDestination navigate(FragmentNavigator.Destination destination, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        String moduleName;
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        DynamicExtras dynamicExtras = (DynamicExtras) (!(extras instanceof DynamicExtras) ? null : extras);
        if ((destination instanceof Destination) && (moduleName = ((Destination) destination).getModuleName()) != null && this.installManager.needsInstall(moduleName)) {
            return this.installManager.performInstall(destination, bundle, dynamicExtras, moduleName);
        }
        if (dynamicExtras != null) {
            extras = dynamicExtras.getDestinationExtras();
        }
        return super.navigate(destination, bundle, navOptions, extras);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Landroidx/navigation/dynamicfeatures/fragment/DynamicFragmentNavigator$Destination;", "Landroidx/navigation/fragment/FragmentNavigator$Destination;", "navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "(Landroidx/navigation/NavigatorProvider;)V", "fragmentNavigator", "Landroidx/navigation/Navigator;", "(Landroidx/navigation/Navigator;)V", "moduleName", "", "getModuleName", "()Ljava/lang/String;", "setModuleName", "(Ljava/lang/String;)V", "onInflate", "", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "navigation-dynamic-features-fragment_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DynamicFragmentNavigator.kt */
    public static final class Destination extends FragmentNavigator.Destination {
        private String moduleName;

        public final String getModuleName() {
            return this.moduleName;
        }

        public final void setModuleName(String str) {
            this.moduleName = str;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Destination(NavigatorProvider navigatorProvider) {
            super(navigatorProvider);
            Intrinsics.checkParameterIsNotNull(navigatorProvider, "navigatorProvider");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Destination(Navigator<? extends FragmentNavigator.Destination> navigator) {
            super(navigator);
            Intrinsics.checkParameterIsNotNull(navigator, "fragmentNavigator");
        }

        public void onInflate(Context context, AttributeSet attributeSet) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
            super.onInflate(context, attributeSet);
            int[] iArr = R.styleable.DynamicFragmentNavigator;
            Intrinsics.checkExpressionValueIsNotNull(iArr, "R.styleable.DynamicFragmentNavigator");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
            this.moduleName = obtainStyledAttributes.getString(R.styleable.DynamicFragmentNavigator_moduleName);
            obtainStyledAttributes.recycle();
        }
    }
}
