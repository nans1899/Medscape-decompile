package androidx.navigation.dynamicfeatures;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020%R4\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00052\u000e\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f8A@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0013@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicInstallMonitor;", "", "()V", "<set-?>", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "getException", "()Ljava/lang/Exception;", "setException$navigation_dynamic_features_runtime_release", "(Ljava/lang/Exception;)V", "installRequired", "", "isInstallRequired", "()Z", "setInstallRequired$navigation_dynamic_features_runtime_release", "(Z)V", "isUsed", "isUsed$navigation_dynamic_features_runtime_release", "", "sessionId", "getSessionId", "()I", "setSessionId$navigation_dynamic_features_runtime_release", "(I)V", "splitInstallManager", "Lcom/google/android/play/core/splitinstall/SplitInstallManager;", "getSplitInstallManager$navigation_dynamic_features_runtime_release", "()Lcom/google/android/play/core/splitinstall/SplitInstallManager;", "setSplitInstallManager$navigation_dynamic_features_runtime_release", "(Lcom/google/android/play/core/splitinstall/SplitInstallManager;)V", "status", "Landroidx/lifecycle/LiveData;", "Lcom/google/android/play/core/splitinstall/SplitInstallSessionState;", "getStatus", "()Landroidx/lifecycle/LiveData;", "cancelInstall", "", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DynamicInstallMonitor.kt */
public final class DynamicInstallMonitor {
    private Exception exception;
    private boolean isInstallRequired;
    private boolean isUsed;
    private int sessionId;
    private SplitInstallManager splitInstallManager;
    private final LiveData<SplitInstallSessionState> status = new MutableLiveData();

    public final Exception getException() {
        return this.exception;
    }

    public final void setException$navigation_dynamic_features_runtime_release(Exception exc) {
        this.exception = exc;
    }

    public final LiveData<SplitInstallSessionState> getStatus() {
        return this.status;
    }

    public final boolean isInstallRequired() {
        return this.isInstallRequired;
    }

    public final void setInstallRequired$navigation_dynamic_features_runtime_release(boolean z) {
        this.isInstallRequired = z;
        if (z) {
            this.isUsed = true;
        }
    }

    public final int getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId$navigation_dynamic_features_runtime_release(int i) {
        this.sessionId = i;
    }

    public final SplitInstallManager getSplitInstallManager$navigation_dynamic_features_runtime_release() {
        return this.splitInstallManager;
    }

    public final void setSplitInstallManager$navigation_dynamic_features_runtime_release(SplitInstallManager splitInstallManager2) {
        this.splitInstallManager = splitInstallManager2;
    }

    public final boolean isUsed$navigation_dynamic_features_runtime_release() {
        return this.isUsed;
    }

    public final void cancelInstall() {
        int i;
        SplitInstallManager splitInstallManager2 = this.splitInstallManager;
        if (splitInstallManager2 != null && (i = this.sessionId) != 0) {
            splitInstallManager2.cancelInstall(i);
        }
    }
}
