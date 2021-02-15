package com.comscore.util.cpp;

import com.comscore.util.log.Logger;
import com.comscore.util.setup.Setup;

public abstract class CppJavaBinder {
    private int a = 0;

    static {
        Setup.setUp();
    }

    /* access modifiers changed from: protected */
    public abstract void destroyCppObject();

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        destroyCppObject();
    }

    /* access modifiers changed from: protected */
    public int getExceptionCounter() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void printException(Throwable th) {
        Logger.e("Error using the native library: ", th);
        this.a++;
    }
}
