package org.mozilla.javascript.jdk13;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.InterfaceAdapter;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.VMBridge;

public class VMBridge_jdk13 extends VMBridge {
    private ThreadLocal<Object[]> contextLocal = new ThreadLocal<>();

    /* access modifiers changed from: protected */
    public boolean isVarArgs(Member member) {
        return false;
    }

    /* access modifiers changed from: protected */
    public Object getThreadContextHelper() {
        Object[] objArr = this.contextLocal.get();
        if (objArr != null) {
            return objArr;
        }
        Object[] objArr2 = new Object[1];
        this.contextLocal.set(objArr2);
        return objArr2;
    }

    /* access modifiers changed from: protected */
    public Context getContext(Object obj) {
        return (Context) ((Object[]) obj)[0];
    }

    /* access modifiers changed from: protected */
    public void setContext(Object obj, Context context) {
        ((Object[]) obj)[0] = context;
    }

    /* access modifiers changed from: protected */
    public ClassLoader getCurrentThreadClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /* access modifiers changed from: protected */
    public boolean tryToMakeAccessible(Object obj) {
        if (!(obj instanceof AccessibleObject)) {
            return false;
        }
        AccessibleObject accessibleObject = (AccessibleObject) obj;
        if (accessibleObject.isAccessible()) {
            return true;
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (Exception unused) {
        }
        return accessibleObject.isAccessible();
    }

    /* access modifiers changed from: protected */
    public Object getInterfaceProxyHelper(ContextFactory contextFactory, Class<?>[] clsArr) {
        try {
            return Proxy.getProxyClass(clsArr[0].getClassLoader(), clsArr).getConstructor(new Class[]{InvocationHandler.class});
        } catch (NoSuchMethodException e) {
            throw Kit.initCause(new IllegalStateException(), e);
        }
    }

    /* access modifiers changed from: protected */
    public Object newInterfaceProxy(Object obj, ContextFactory contextFactory, InterfaceAdapter interfaceAdapter, Object obj2, Scriptable scriptable) {
        final Object obj3 = obj2;
        final InterfaceAdapter interfaceAdapter2 = interfaceAdapter;
        final ContextFactory contextFactory2 = contextFactory;
        final Scriptable scriptable2 = scriptable;
        try {
            return ((Constructor) obj).newInstance(new Object[]{new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) {
                    if (method.getDeclaringClass() == Object.class) {
                        String name = method.getName();
                        if (name.equals("equals")) {
                            boolean z = false;
                            if (obj == objArr[0]) {
                                z = true;
                            }
                            return Boolean.valueOf(z);
                        } else if (name.equals("hashCode")) {
                            return Integer.valueOf(obj3.hashCode());
                        } else {
                            if (name.equals("toString")) {
                                return "Proxy[" + obj3.toString() + "]";
                            }
                        }
                    }
                    return interfaceAdapter2.invoke(contextFactory2, obj3, scriptable2, obj, method, objArr);
                }
            }});
        } catch (InvocationTargetException e) {
            throw Context.throwAsScriptRuntimeEx(e);
        } catch (IllegalAccessException e2) {
            throw Kit.initCause(new IllegalStateException(), e2);
        } catch (InstantiationException e3) {
            throw Kit.initCause(new IllegalStateException(), e3);
        }
    }
}
