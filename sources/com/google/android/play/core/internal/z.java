package com.google.android.play.core.internal;

import com.google.android.play.core.listener.StateUpdatedListener;
import java.util.HashSet;
import java.util.Set;

public final class z<StateT> {
    protected final Set<StateUpdatedListener<StateT>> a = new HashSet();

    public final synchronized void a(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.a.add(stateUpdatedListener);
    }

    public final synchronized void a(StateT statet) {
        for (StateUpdatedListener<StateT> onStateUpdate : this.a) {
            onStateUpdate.onStateUpdate(statet);
        }
    }

    public final synchronized void b(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.a.remove(stateUpdatedListener);
    }
}
