package org.simpleframework.xml.core;

import java.lang.reflect.Method;
import java.util.Map;

class Function {
    private final boolean contextual;
    private final Method method;

    public Function(Method method2) {
        this(method2, false);
    }

    public Function(Method method2, boolean z) {
        this.contextual = z;
        this.method = method2;
    }

    public Object call(Context context, Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map map = context.getSession().getMap();
        if (!this.contextual) {
            return this.method.invoke(obj, new Object[0]);
        }
        return this.method.invoke(obj, new Object[]{map});
    }
}
