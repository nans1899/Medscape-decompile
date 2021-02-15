package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.i;

final /* synthetic */ class h implements OnCompleteListener {
    private final t a;
    private final i b;

    h(t tVar, i iVar) {
        this.a = tVar;
        this.b = iVar;
    }

    public final void onComplete(Task task) {
        t tVar = this.a;
        i iVar = this.b;
        if (task.isSuccessful()) {
            tVar.a((SplitInstallManager) task.getResult()).addOnCompleteListener(new j(iVar));
        } else {
            iVar.a(task.getException());
        }
    }
}
