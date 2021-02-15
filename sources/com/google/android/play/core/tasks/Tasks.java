package com.google.android.play.core.tasks;

import com.google.android.play.core.splitcompat.d;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {
    private Tasks() {
    }

    public static <ResultT> Task<ResultT> a(Exception exc) {
        m mVar = new m();
        mVar.a(exc);
        return mVar;
    }

    public static <ResultT> Task<ResultT> a(ResultT resultt) {
        m mVar = new m();
        mVar.a(resultt);
        return mVar;
    }

    private static <ResultT> ResultT a(Task<ResultT> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    private static void a(Task<?> task, n nVar) {
        task.addOnSuccessListener(TaskExecutors.a, nVar);
        task.addOnFailureListener(TaskExecutors.a, nVar);
    }

    public static <ResultT> ResultT await(Task<ResultT> task) throws ExecutionException, InterruptedException {
        d.a(task, (Object) "Task must not be null");
        if (task.isComplete()) {
            return a(task);
        }
        n nVar = new n((byte[]) null);
        a(task, nVar);
        nVar.a();
        return a(task);
    }

    public static <ResultT> ResultT await(Task<ResultT> task, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        d.a(task, (Object) "Task must not be null");
        d.a(timeUnit, (Object) "TimeUnit must not be null");
        if (task.isComplete()) {
            return a(task);
        }
        n nVar = new n((byte[]) null);
        a(task, nVar);
        if (nVar.a(j, timeUnit)) {
            return a(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }
}
