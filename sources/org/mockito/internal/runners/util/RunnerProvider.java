package org.mockito.internal.runners.util;

import java.lang.reflect.InvocationTargetException;
import org.mockito.internal.runners.InternalRunner;

public class RunnerProvider {
    public InternalRunner newInstance(String str, Object... objArr) throws Exception {
        try {
            Class<?> cls = Class.forName(str);
            if (cls.getConstructors().length == 1) {
                try {
                    return (InternalRunner) cls.getConstructors()[0].newInstance(objArr);
                } catch (InvocationTargetException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            } else {
                throw new IllegalArgumentException("Expected " + str + " to have exactly one constructor.");
            }
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }
}
