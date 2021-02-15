package com.tapstream.sdk;

import java.util.HashSet;
import java.util.Set;

public class OneTimeOnlyEventTracker {
    private final Set<String> eventsAlreadyFired;
    private final Set<String> eventsInProgress = new HashSet();
    private final Platform platform;

    public OneTimeOnlyEventTracker(Platform platform2) {
        this.platform = platform2;
        this.eventsAlreadyFired = platform2.loadFiredEvents();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean hasBeenAlreadySent(com.tapstream.sdk.Event r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r4.isOneTimeOnly()     // Catch:{ all -> 0x0025 }
            r1 = 0
            if (r0 != 0) goto L_0x000a
            monitor-exit(r3)
            return r1
        L_0x000a:
            java.util.Set<java.lang.String> r0 = r3.eventsAlreadyFired     // Catch:{ all -> 0x0025 }
            java.lang.String r2 = r4.getName()     // Catch:{ all -> 0x0025 }
            boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x0022
            java.util.Set<java.lang.String> r0 = r3.eventsInProgress     // Catch:{ all -> 0x0025 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0025 }
            boolean r4 = r0.contains(r4)     // Catch:{ all -> 0x0025 }
            if (r4 == 0) goto L_0x0023
        L_0x0022:
            r1 = 1
        L_0x0023:
            monitor-exit(r3)
            return r1
        L_0x0025:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapstream.sdk.OneTimeOnlyEventTracker.hasBeenAlreadySent(com.tapstream.sdk.Event):boolean");
    }

    public synchronized void sent(Event event) {
        if (event.isOneTimeOnly()) {
            this.eventsInProgress.remove(event.getName());
            this.eventsAlreadyFired.add(event.getName());
            this.platform.saveFiredEvents(this.eventsAlreadyFired);
        }
    }

    public synchronized void failed(Event event) {
        sent(event);
    }

    public synchronized void inProgress(Event event) {
        if (event.isOneTimeOnly()) {
            this.eventsInProgress.add(event.getName());
        }
    }
}
