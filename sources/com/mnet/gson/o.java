package com.mnet.gson;

public class o extends RuntimeException {
    public o(String str) {
        super(str);
    }

    public o(String str, Throwable th) {
        super(str, th);
    }

    public o(Throwable th) {
        super(th);
    }
}
