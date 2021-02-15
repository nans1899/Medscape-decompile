package com.tapstream.sdk;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {
    private final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.defaultThreadFactory.newThread(runnable);
        newThread.setDaemon(true);
        return newThread;
    }
}
