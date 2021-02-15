package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import java.util.ArrayList;
import java.util.List;

final class ad implements Runnable {
    final /* synthetic */ SplitInstallRequest a;
    final /* synthetic */ af b;

    ad(af afVar, SplitInstallRequest splitInstallRequest) {
        this.b = afVar;
        this.a = splitInstallRequest;
    }

    public final void run() {
        ac a2 = this.b.b;
        List<String> moduleNames = this.a.getModuleNames();
        List a3 = af.b(this.a.getLanguages());
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt("status", 5);
        bundle.putInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE, 0);
        if (!moduleNames.isEmpty()) {
            bundle.putStringArrayList("module_names", new ArrayList(moduleNames));
        }
        if (!a3.isEmpty()) {
            bundle.putStringArrayList("languages", new ArrayList(a3));
        }
        bundle.putLong("total_bytes_to_download", 0);
        bundle.putLong("bytes_downloaded", 0);
        a2.a(SplitInstallSessionState.a(bundle));
    }
}
