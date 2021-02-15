package com.google.firebase.crashlytics.internal.report.model;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.report.model.Report;
import java.io.File;
import java.util.Map;

public class NativeSessionReport implements Report {
    private final File reportDirectory;

    public Map<String, String> getCustomHeaders() {
        return null;
    }

    public File getFile() {
        return null;
    }

    public String getFileName() {
        return null;
    }

    public NativeSessionReport(File file) {
        this.reportDirectory = file;
    }

    public void remove() {
        for (File file : getFiles()) {
            Logger.getLogger().d("Removing native report file at " + file.getPath());
            file.delete();
        }
        Logger.getLogger().d("Removing native report directory at " + this.reportDirectory);
        this.reportDirectory.delete();
    }

    public String getIdentifier() {
        return this.reportDirectory.getName();
    }

    public File[] getFiles() {
        return this.reportDirectory.listFiles();
    }

    public Report.Type getType() {
        return Report.Type.NATIVE;
    }
}
