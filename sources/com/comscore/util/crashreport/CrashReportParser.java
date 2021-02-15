package com.comscore.util.crashreport;

public interface CrashReportParser {
    String reportToString(CrashReport crashReport);

    CrashReport stringToReport(String str);
}
