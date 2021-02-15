package com.comscore.util.crashreport;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpGetCrashReportParser implements CrashReportParser {
    private final String a = " | ";
    private final String b = "ns_ap_uxc";

    private String a(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String b(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String reportToString(CrashReport crashReport) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : crashReport.getExtras().entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if (str != null) {
                if (str2 == null) {
                    str2 = "";
                }
                sb.append("&");
                sb.append(str);
                sb.append("=");
                sb.append(b(str2));
            }
        }
        String message = crashReport.getMessage();
        if (crashReport.getStackTrace() != null) {
            message = message + " | " + crashReport.getStackTrace();
        }
        sb.append("&");
        sb.append("ns_ap_uxc");
        sb.append("=");
        sb.append(b(message));
        return (sb.length() <= 0 || sb.charAt(0) != '&') ? sb.toString() : sb.substring(1);
    }

    public CrashReport stringToReport(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("\\&", -1)) {
            int indexOf = str2.indexOf("=");
            hashMap.put(str2.substring(0, indexOf), a(str2.substring(indexOf + 1, str2.length())));
        }
        String str3 = (String) hashMap.get("ns_ap_uxc");
        hashMap.remove("ns_ap_uxc");
        String str4 = "";
        if (str3 != null) {
            int indexOf2 = str3.indexOf(" | ");
            if (indexOf2 >= 0) {
                String substring = str3.substring(0, indexOf2);
                String substring2 = str3.substring(indexOf2 + 3, str3.length());
                str3 = substring;
                str4 = substring2;
            }
        } else {
            str3 = str4;
        }
        return new CrashReport(str3, str4, (Map<String, String>) hashMap);
    }
}
