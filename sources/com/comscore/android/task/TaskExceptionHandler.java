package com.comscore.android.task;

public interface TaskExceptionHandler {
    void exception(Exception exc, TaskExecutor taskExecutor, Runnable runnable);
}
