package androidx.navigation.dynamicfeatures.fragment.ui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.dynamicfeatures.Constants;
import androidx.navigation.dynamicfeatures.DynamicExtras;
import androidx.navigation.dynamicfeatures.DynamicInstallMonitor;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\b&\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0017\u001a\u00020\u0018H\u0005J\"\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0018H$J\u0012\u0010\u001f\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010!\u001a\u00020\u00182\b\b\u0001\u0010\"\u001a\u00020\u0004H$J\b\u0010#\u001a\u00020\u0018H\u0014J\"\u0010$\u001a\u00020\u00182\b\b\u0001\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H$J\b\u0010)\u001a\u00020\u0018H\u0016J\u0010\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u0007H\u0016R\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Landroidx/navigation/dynamicfeatures/fragment/ui/AbstractProgressFragment;", "Landroidx/fragment/app/Fragment;", "()V", "contentLayoutId", "", "(I)V", "destinationArgs", "Landroid/os/Bundle;", "getDestinationArgs", "()Landroid/os/Bundle;", "destinationArgs$delegate", "Lkotlin/Lazy;", "destinationId", "getDestinationId", "()I", "destinationId$delegate", "installViewModel", "Landroidx/navigation/dynamicfeatures/fragment/ui/InstallViewModel;", "getInstallViewModel", "()Landroidx/navigation/dynamicfeatures/fragment/ui/InstallViewModel;", "installViewModel$delegate", "navigated", "", "navigate", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCancelled", "onCreate", "savedInstanceState", "onFailed", "errorCode", "onInstalled", "onProgress", "status", "bytesDownloaded", "", "bytesTotal", "onResume", "onSaveInstanceState", "outState", "Companion", "StateObserver", "navigation-dynamic-features-fragment_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AbstractProgressFragment.kt */
public abstract class AbstractProgressFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int INSTALL_REQUEST_CODE = 1;
    private static final String TAG = "AbstractProgress";
    private final Lazy destinationArgs$delegate;
    private final Lazy destinationId$delegate;
    private final Lazy installViewModel$delegate;
    private boolean navigated;

    private final Bundle getDestinationArgs() {
        return (Bundle) this.destinationArgs$delegate.getValue();
    }

    private final int getDestinationId() {
        return ((Number) this.destinationId$delegate.getValue()).intValue();
    }

    private final InstallViewModel getInstallViewModel() {
        return (InstallViewModel) this.installViewModel$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public abstract void onCancelled();

    /* access modifiers changed from: protected */
    public abstract void onFailed(int i);

    /* access modifiers changed from: protected */
    public void onInstalled() {
    }

    /* access modifiers changed from: protected */
    public abstract void onProgress(int i, long j, long j2);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/navigation/dynamicfeatures/fragment/ui/AbstractProgressFragment$Companion;", "", "()V", "INSTALL_REQUEST_CODE", "", "TAG", "", "navigation-dynamic-features-fragment_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AbstractProgressFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AbstractProgressFragment() {
        this.installViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(InstallViewModel.class), new AbstractProgressFragment$$special$$inlined$viewModels$2(new AbstractProgressFragment$$special$$inlined$viewModels$1(this)), AbstractProgressFragment$installViewModel$2.INSTANCE);
        this.destinationId$delegate = LazyKt.lazy(new AbstractProgressFragment$destinationId$2(this));
        this.destinationArgs$delegate = LazyKt.lazy(new AbstractProgressFragment$destinationArgs$2(this));
    }

    public AbstractProgressFragment(int i) {
        super(i);
        this.installViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(InstallViewModel.class), new AbstractProgressFragment$$special$$inlined$viewModels$4(new AbstractProgressFragment$$special$$inlined$viewModels$3(this)), AbstractProgressFragment$installViewModel$2.INSTANCE);
        this.destinationId$delegate = LazyKt.lazy(new AbstractProgressFragment$destinationId$2(this));
        this.destinationArgs$delegate = LazyKt.lazy(new AbstractProgressFragment$destinationArgs$2(this));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.navigated = bundle.getBoolean(Constants.KEY_NAVIGATED, false);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.navigated) {
            FragmentKt.findNavController(this).popBackStack();
            return;
        }
        DynamicInstallMonitor installMonitor = getInstallViewModel().getInstallMonitor();
        if (installMonitor == null) {
            Log.i(TAG, "onResume: monitor is null, navigating");
            navigate();
            installMonitor = getInstallViewModel().getInstallMonitor();
        }
        if (installMonitor != null) {
            Log.i(TAG, "onResume: monitor is now not null, observing");
            installMonitor.getStatus().observe(this, new StateObserver(this, installMonitor));
        }
    }

    /* access modifiers changed from: protected */
    public final void navigate() {
        Log.i(TAG, "navigate: ");
        DynamicInstallMonitor dynamicInstallMonitor = new DynamicInstallMonitor();
        FragmentKt.findNavController(this).navigate(getDestinationId(), getDestinationArgs(), (NavOptions) null, (Navigator.Extras) new DynamicExtras(dynamicInstallMonitor, (Navigator.Extras) null, 2, (DefaultConstructorMarker) null));
        if (!dynamicInstallMonitor.isInstallRequired()) {
            Log.i(TAG, "navigate: install not required");
            this.navigated = true;
            return;
        }
        Log.i(TAG, "navigate: setting install monitor");
        getInstallViewModel().setInstallMonitor(dynamicInstallMonitor);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(Constants.KEY_NAVIGATED, this.navigated);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/navigation/dynamicfeatures/fragment/ui/AbstractProgressFragment$StateObserver;", "Landroidx/lifecycle/Observer;", "Lcom/google/android/play/core/splitinstall/SplitInstallSessionState;", "monitor", "Landroidx/navigation/dynamicfeatures/DynamicInstallMonitor;", "(Landroidx/navigation/dynamicfeatures/fragment/ui/AbstractProgressFragment;Landroidx/navigation/dynamicfeatures/DynamicInstallMonitor;)V", "onChanged", "", "sessionState", "navigation-dynamic-features-fragment_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AbstractProgressFragment.kt */
    private final class StateObserver implements Observer<SplitInstallSessionState> {
        private final DynamicInstallMonitor monitor;
        final /* synthetic */ AbstractProgressFragment this$0;

        public StateObserver(AbstractProgressFragment abstractProgressFragment, DynamicInstallMonitor dynamicInstallMonitor) {
            Intrinsics.checkParameterIsNotNull(dynamicInstallMonitor, "monitor");
            this.this$0 = abstractProgressFragment;
            this.monitor = dynamicInstallMonitor;
        }

        public void onChanged(SplitInstallSessionState splitInstallSessionState) {
            if (splitInstallSessionState != null) {
                if (splitInstallSessionState.hasTerminalStatus()) {
                    this.monitor.getStatus().removeObserver(this);
                }
                switch (splitInstallSessionState.status()) {
                    case 0:
                        this.this$0.onFailed(-100);
                        return;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 9:
                        this.this$0.onProgress(splitInstallSessionState.status(), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload());
                        return;
                    case 5:
                        this.this$0.onInstalled();
                        this.this$0.navigate();
                        return;
                    case 6:
                        this.this$0.onFailed(splitInstallSessionState.errorCode());
                        return;
                    case 7:
                        this.this$0.onCancelled();
                        return;
                    case 8:
                        try {
                            AbstractProgressFragment abstractProgressFragment = this.this$0;
                            PendingIntent resolutionIntent = splitInstallSessionState.resolutionIntent();
                            Intrinsics.checkExpressionValueIsNotNull(resolutionIntent, "sessionState.resolutionIntent()");
                            abstractProgressFragment.startIntentSenderForResult(resolutionIntent.getIntentSender(), 1, (Intent) null, 0, 0, 0, (Bundle) null);
                            return;
                        } catch (IntentSender.SendIntentException unused) {
                            this.this$0.onFailed(-100);
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == 0) {
            onCancelled();
        }
    }
}
