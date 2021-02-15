package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.aa;
import com.google.android.play.core.internal.bt;
import com.google.android.play.core.internal.v;
import com.google.android.play.core.internal.y;
import java.util.Arrays;

final class b extends v {
    private final aa a = new aa("AssetPackExtractionService");
    private final Context b;
    private final AssetPackExtractionService c;
    private final bb d;

    b(Context context, AssetPackExtractionService assetPackExtractionService, bb bbVar) {
        this.b = context;
        this.c = assetPackExtractionService;
        this.d = bbVar;
    }

    public final void a(Bundle bundle, y yVar) throws RemoteException {
        String[] packagesForUid;
        this.a.a("updateServiceState AIDL call", new Object[0]);
        if (bt.a(this.b) && (packagesForUid = this.b.getPackageManager().getPackagesForUid(Binder.getCallingUid())) != null && Arrays.asList(packagesForUid).contains("com.android.vending")) {
            yVar.a(this.c.a(bundle), new Bundle());
            return;
        }
        yVar.a(new Bundle());
        this.c.a();
    }

    public final void a(y yVar) throws RemoteException {
        this.d.f();
        yVar.b(new Bundle());
    }
}
