package com.comscore.util.crashreport;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrashReport {
    private String a;
    private String b;
    private Map<String, String> c;

    public CrashReport(String str, String str2, Map<String, String> map) {
        this.b = str2 == null ? "" : str2;
        this.a = str == null ? "" : str;
        a(map);
    }

    public CrashReport(String str, Throwable th) {
        this(str, th, (Map<String, String>) null);
    }

    public CrashReport(String str, Throwable th, Map<String, String> map) {
        String str2;
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            str2 = stringWriter.toString();
        } else {
            str2 = "";
        }
        this.b = str2;
        this.a = str == null ? "" : str;
        a(map);
    }

    private void a(Map<String, String> map) {
        this.c = new LinkedHashMap();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                String str2 = (String) next.getValue();
                if (str2 == null) {
                    str2 = "";
                }
                this.c.put(str, str2);
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CrashReport)) {
            return false;
        }
        CrashReport crashReport = (CrashReport) obj;
        return crashReport.getMessage().equals(getMessage()) && crashReport.getStackTrace().equals(getStackTrace()) && crashReport.getExtras().equals(getExtras());
    }

    public Map<String, String> getExtras() {
        return this.c;
    }

    public String getMessage() {
        return this.a;
    }

    public String getStackTrace() {
        return this.b;
    }
}
