package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.i;

final /* synthetic */ class j implements OnCompleteListener {
    private final i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final void onComplete(Task task) {
        i iVar = this.a;
        if (task.isSuccessful()) {
            iVar.a(task.getResult());
        } else {
            iVar.a(task.getException());
        }
    }
}
