package com.comscore.util.crashreport;

public interface CrashReportFlusher {
    boolean flush(String str, CrashReportParser crashReportParser, CrashReport crashReport);
}
