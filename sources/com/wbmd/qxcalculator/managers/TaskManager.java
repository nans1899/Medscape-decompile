package com.wbmd.qxcalculator.managers;

import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.model.api.APITask;
import com.wbmd.qxcalculator.util.CrashLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskManager {
    private static final TaskManager ourInstance = new TaskManager();
    protected List<APITask> tasks = new ArrayList();

    public static TaskManager getInstance() {
        return ourInstance;
    }

    public void addTask(APITask aPITask) {
        if (aPITask.taskId == null) {
            CrashLogger.getInstance().leaveBreadcrumb("added new api task: no id");
        } else {
            CrashLogger instance = CrashLogger.getInstance();
            instance.leaveBreadcrumb("added new api task: " + aPITask.taskId);
        }
        this.tasks.add(aPITask);
        aPITask.startTask();
    }

    public void copyTask(APITask aPITask, boolean z) {
        int indexOf = this.tasks.indexOf(aPITask);
        if (indexOf >= 0) {
            APITask aPITask2 = new APITask(aPITask);
            this.tasks.remove(indexOf);
            this.tasks.add(indexOf, aPITask2);
            if (z) {
                aPITask2.startTask();
            }
        }
    }

    public void removeTask(APITask aPITask) {
        this.tasks.remove(aPITask);
        if (!aPITask.isCancelled()) {
            aPITask.cancel(true);
        }
    }

    public void removeAllTasks() {
        List<APITask> list = this.tasks;
        if (list != null) {
            for (APITask cancel : list) {
                cancel.cancel(true);
            }
            this.tasks.clear();
        }
    }

    public boolean isTaskCurrentlyInProgress(String str) {
        for (APITask next : this.tasks) {
            if (next != null && next.taskId != null && next.taskId.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void reauthorizationCompleted(boolean z, List<QxError> list) {
        Iterator it = new ArrayList(this.tasks).iterator();
        while (it.hasNext()) {
            APITask aPITask = (APITask) it.next();
            if (!z) {
                aPITask.reauthFailed(list);
            } else if (aPITask.isRunning) {
                APITask aPITask2 = new APITask(aPITask);
                aPITask2.retryNumber = 0;
                int indexOf = this.tasks.indexOf(aPITask);
                this.tasks.remove(indexOf);
                aPITask.cancel(true);
                this.tasks.add(indexOf, aPITask2);
                aPITask2.startTask();
            } else {
                aPITask.startTask();
            }
        }
    }

    public void cancelAllTasks() {
        removeAllTasks();
    }
}
