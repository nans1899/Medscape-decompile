package io.grpc.stub;

import com.google.common.base.Preconditions;
import java.util.Iterator;

public final class StreamObservers {
    public static <V> void copyWithFlowControl(final Iterator<V> it, final CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(it, "source");
        Preconditions.checkNotNull(callStreamObserver, "target");
        callStreamObserver.setOnReadyHandler(new Runnable() {
            private boolean completed;

            public void run() {
                if (!this.completed) {
                    while (CallStreamObserver.this.isReady() && it.hasNext()) {
                        CallStreamObserver.this.onNext(it.next());
                    }
                    if (!it.hasNext()) {
                        this.completed = true;
                        CallStreamObserver.this.onCompleted();
                    }
                }
            }
        });
    }

    public static <V> void copyWithFlowControl(Iterable<V> iterable, CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(iterable, "source");
        copyWithFlowControl(iterable.iterator(), callStreamObserver);
    }
}
