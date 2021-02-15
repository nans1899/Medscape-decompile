package com.medscape.android.homescreen.viewmodel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenViewModel.kt */
final class HomeScreenViewModel$handleNetworkError$thread$1 implements Runnable {
    final /* synthetic */ HomeScreenViewModel this$0;

    HomeScreenViewModel$handleNetworkError$thread$1(HomeScreenViewModel homeScreenViewModel) {
        this.this$0 = homeScreenViewModel;
    }

    public final void run() {
        boolean z = false;
        try {
            InetAddress byName = InetAddress.getByName("bi.medscape.com");
            Intrinsics.checkNotNullExpressionValue(byName, "InetAddress.getByName(\"bi.medscape.com\")");
            if (Intrinsics.areEqual((Object) byName.getHostAddress(), (Object) "127.0.0.1")) {
                this.this$0.getDialogListener().showDialog(12, "");
                z = true;
            }
        } catch (UnknownHostException e) {
            try {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (!z) {
            this.this$0.getDialogListener().showDialog(11, "");
        }
    }
}
