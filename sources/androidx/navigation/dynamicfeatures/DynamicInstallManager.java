package androidx.navigation.dynamicfeatures;

import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.dynamicfeatures.DynamicGraphNavigator;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.SplitInstallHelper;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J.\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0007J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicInstallManager;", "", "context", "Landroid/content/Context;", "splitInstallManager", "Lcom/google/android/play/core/splitinstall/SplitInstallManager;", "(Landroid/content/Context;Lcom/google/android/play/core/splitinstall/SplitInstallManager;)V", "needsInstall", "", "module", "", "performInstall", "Landroidx/navigation/NavDestination;", "destination", "args", "Landroid/os/Bundle;", "extras", "Landroidx/navigation/dynamicfeatures/DynamicExtras;", "moduleName", "requestInstall", "", "installMonitor", "Landroidx/navigation/dynamicfeatures/DynamicInstallMonitor;", "Companion", "SplitInstallListenerWrapper", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DynamicInstallManager.kt */
public class DynamicInstallManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final SplitInstallManager splitInstallManager;

    public DynamicInstallManager(Context context2, SplitInstallManager splitInstallManager2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(splitInstallManager2, "splitInstallManager");
        this.context = context2;
        this.splitInstallManager = splitInstallManager2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0000¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicInstallManager$Companion;", "", "()V", "terminateLiveData", "", "status", "Landroidx/lifecycle/MutableLiveData;", "Lcom/google/android/play/core/splitinstall/SplitInstallSessionState;", "terminateLiveData$navigation_dynamic_features_runtime_release", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DynamicInstallManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void terminateLiveData$navigation_dynamic_features_runtime_release(MutableLiveData<SplitInstallSessionState> mutableLiveData) {
            Intrinsics.checkParameterIsNotNull(mutableLiveData, "status");
            if (!(!mutableLiveData.hasActiveObservers())) {
                throw new IllegalStateException("This DynamicInstallMonitor will not emit any more status updates. You should remove all Observers after null has been emitted.".toString());
            }
        }
    }

    public final NavDestination performInstall(NavDestination navDestination, Bundle bundle, DynamicExtras dynamicExtras, String str) {
        Intrinsics.checkParameterIsNotNull(navDestination, "destination");
        Intrinsics.checkParameterIsNotNull(str, "moduleName");
        if ((dynamicExtras != null ? dynamicExtras.getInstallMonitor() : null) != null) {
            requestInstall(str, dynamicExtras.getInstallMonitor());
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt(Constants.DESTINATION_ID, navDestination.getId());
        bundle2.putBundle(Constants.DESTINATION_ARGS, bundle);
        DynamicGraphNavigator.DynamicNavGraph orThrow$navigation_dynamic_features_runtime_release = DynamicGraphNavigator.DynamicNavGraph.Companion.getOrThrow$navigation_dynamic_features_runtime_release(navDestination);
        NavigatorProvider navigatorProvider$navigation_dynamic_features_runtime_release = orThrow$navigation_dynamic_features_runtime_release.getNavigatorProvider$navigation_dynamic_features_runtime_release();
        String navigatorName = orThrow$navigation_dynamic_features_runtime_release.getNavigatorName();
        Intrinsics.checkExpressionValueIsNotNull(navigatorName, "dynamicNavGraph.navigatorName");
        Navigator navigator = navigatorProvider$navigation_dynamic_features_runtime_release.getNavigator(navigatorName);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(name)");
        if (navigator instanceof DynamicGraphNavigator) {
            return ((DynamicGraphNavigator) navigator).navigateToProgressDestination$navigation_dynamic_features_runtime_release(orThrow$navigation_dynamic_features_runtime_release, bundle2);
        }
        throw new IllegalStateException("You must use a DynamicNavGraph to perform a module installation.");
    }

    public final boolean needsInstall(String str) {
        Intrinsics.checkParameterIsNotNull(str, "module");
        return !this.splitInstallManager.getInstalledModules().contains(str);
    }

    private final void requestInstall(String str, DynamicInstallMonitor dynamicInstallMonitor) {
        if (!dynamicInstallMonitor.isUsed$navigation_dynamic_features_runtime_release()) {
            LiveData<SplitInstallSessionState> status = dynamicInstallMonitor.getStatus();
            if (status != null) {
                MutableLiveData mutableLiveData = (MutableLiveData) status;
                dynamicInstallMonitor.setInstallRequired$navigation_dynamic_features_runtime_release(true);
                this.splitInstallManager.startInstall(SplitInstallRequest.newBuilder().addModule(str).build()).addOnSuccessListener(new DynamicInstallManager$requestInstall$2(this, dynamicInstallMonitor, mutableLiveData, str)).addOnFailureListener(new DynamicInstallManager$requestInstall$3(str, dynamicInstallMonitor, mutableLiveData));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<com.google.android.play.core.splitinstall.SplitInstallSessionState>");
        }
        throw new IllegalStateException("You must pass in a fresh DynamicInstallMonitor in DynamicExtras every time you call navigate().".toString());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicInstallManager$SplitInstallListenerWrapper;", "Lcom/google/android/play/core/splitinstall/SplitInstallStateUpdatedListener;", "context", "Landroid/content/Context;", "status", "Landroidx/lifecycle/MutableLiveData;", "Lcom/google/android/play/core/splitinstall/SplitInstallSessionState;", "installMonitor", "Landroidx/navigation/dynamicfeatures/DynamicInstallMonitor;", "(Landroid/content/Context;Landroidx/lifecycle/MutableLiveData;Landroidx/navigation/dynamicfeatures/DynamicInstallMonitor;)V", "onStateUpdate", "", "splitInstallSessionState", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DynamicInstallManager.kt */
    private static final class SplitInstallListenerWrapper implements SplitInstallStateUpdatedListener {
        private final Context context;
        private final DynamicInstallMonitor installMonitor;
        private final MutableLiveData<SplitInstallSessionState> status;

        public SplitInstallListenerWrapper(Context context2, MutableLiveData<SplitInstallSessionState> mutableLiveData, DynamicInstallMonitor dynamicInstallMonitor) {
            Intrinsics.checkParameterIsNotNull(context2, "context");
            Intrinsics.checkParameterIsNotNull(mutableLiveData, "status");
            Intrinsics.checkParameterIsNotNull(dynamicInstallMonitor, "installMonitor");
            this.context = context2;
            this.status = mutableLiveData;
            this.installMonitor = dynamicInstallMonitor;
        }

        public void onStateUpdate(SplitInstallSessionState splitInstallSessionState) {
            Intrinsics.checkParameterIsNotNull(splitInstallSessionState, "splitInstallSessionState");
            if (splitInstallSessionState.sessionId() == this.installMonitor.getSessionId()) {
                if (splitInstallSessionState.status() == 5) {
                    SplitCompat.install(this.context);
                    SplitInstallHelper.updateAppInfo(this.context);
                }
                this.status.setValue(splitInstallSessionState);
                if (splitInstallSessionState.hasTerminalStatus()) {
                    SplitInstallManager splitInstallManager$navigation_dynamic_features_runtime_release = this.installMonitor.getSplitInstallManager$navigation_dynamic_features_runtime_release();
                    if (splitInstallManager$navigation_dynamic_features_runtime_release == null) {
                        Intrinsics.throwNpe();
                    }
                    splitInstallManager$navigation_dynamic_features_runtime_release.unregisterListener(this);
                    DynamicInstallManager.Companion.terminateLiveData$navigation_dynamic_features_runtime_release(this.status);
                }
            }
        }
    }
}
