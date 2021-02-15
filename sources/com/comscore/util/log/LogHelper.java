package com.comscore.util.log;

public interface LogHelper {
    void d(String str);

    void d(String str, String str2);

    void e(String str);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void e(String str, Throwable th);

    void i(String str);

    void i(String str, String str2);

    void w(String str);

    void w(String str, String str2);
}
