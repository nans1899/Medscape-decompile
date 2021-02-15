package com.qxmd.eventssdkandroid.managers;

import com.qxmd.eventssdkandroid.api.APITask;
import com.qxmd.eventssdkandroid.api.BackgroundTask;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final TaskManager ourInstance = new TaskManager();
    protected List<APITask> tasks = new ArrayList();

    public static TaskManager getInstance() {
        return ourInstance;
    }

    public void addTask(APITask aPITask) {
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

    public void removeTask(BackgroundTask backgroundTask) {
        this.tasks.remove(backgroundTask);
        if (!backgroundTask.isCancelled()) {
            backgroundTask.cancel(true);
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
        for (APITask aPITask : this.tasks) {
            if (aPITask.taskId.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
