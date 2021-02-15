package org.mozilla.javascript;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.mozilla.javascript.xml.XMLLib;

public class ContextFactory {
    /* access modifiers changed from: private */
    public static ContextFactory global = new ContextFactory();
    private static volatile boolean hasCustomGlobal;
    private ClassLoader applicationClassLoader;
    private boolean disabledListening;
    private volatile Object listeners;
    private final Object listenersLock = new Object();
    private volatile boolean sealed;

    public interface GlobalSetter {
        ContextFactory getContextFactoryGlobal();

        void setContextFactoryGlobal(ContextFactory contextFactory);
    }

    public interface Listener {
        void contextCreated(Context context);

        void contextReleased(Context context);
    }

    /* access modifiers changed from: protected */
    public void observeInstructionCount(Context context, int i) {
    }

    public static ContextFactory getGlobal() {
        return global;
    }

    public static boolean hasExplicitGlobal() {
        return hasCustomGlobal;
    }

    public static synchronized void initGlobal(ContextFactory contextFactory) {
        synchronized (ContextFactory.class) {
            if (contextFactory != null) {
                try {
                    if (!hasCustomGlobal) {
                        hasCustomGlobal = true;
                        global = contextFactory;
                    } else {
                        throw new IllegalStateException();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static synchronized GlobalSetter getGlobalSetter() {
        AnonymousClass1GlobalSetterImpl r1;
        synchronized (ContextFactory.class) {
            if (!hasCustomGlobal) {
                hasCustomGlobal = true;
                r1 = new GlobalSetter() {
                    public void setContextFactoryGlobal(ContextFactory contextFactory) {
                        if (contextFactory == null) {
                            contextFactory = new ContextFactory();
                        }
                        ContextFactory unused = ContextFactory.global = contextFactory;
                    }

                    public ContextFactory getContextFactoryGlobal() {
                        return ContextFactory.global;
                    }
                };
            } else {
                throw new IllegalStateException();
            }
        }
        return r1;
    }

    /* access modifiers changed from: protected */
    public Context makeContext() {
        return new Context(this);
    }

    /* access modifiers changed from: protected */
    public boolean hasFeature(Context context, int i) {
        switch (i) {
            case 1:
                int languageVersion = context.getLanguageVersion();
                if (languageVersion == 100 || languageVersion == 110 || languageVersion == 120) {
                    return true;
                }
                return false;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                if (context.getLanguageVersion() == 120) {
                    return true;
                }
                return false;
            case 5:
                return true;
            case 6:
                int languageVersion2 = context.getLanguageVersion();
                if (languageVersion2 == 0 || languageVersion2 >= 160) {
                    return true;
                }
                return false;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return false;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    private boolean isDom3Present() {
        Class<?> classOrNull = Kit.classOrNull("org.w3c.dom.Node");
        if (classOrNull == null) {
            return false;
        }
        try {
            classOrNull.getMethod("getUserData", new Class[]{String.class});
            return true;
        } catch (NoSuchMethodException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public XMLLib.Factory getE4xImplementationFactory() {
        if (isDom3Present()) {
            return XMLLib.Factory.create("org.mozilla.javascript.xmlimpl.XMLLibImpl");
        }
        if (Kit.classOrNull("org.apache.xmlbeans.XmlCursor") != null) {
            return XMLLib.Factory.create("org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl");
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public GeneratedClassLoader createClassLoader(final ClassLoader classLoader) {
        return (GeneratedClassLoader) AccessController.doPrivileged(new PrivilegedAction<DefiningClassLoader>() {
            public DefiningClassLoader run() {
                return new DefiningClassLoader(classLoader);
            }
        });
    }

    public final ClassLoader getApplicationClassLoader() {
        return this.applicationClassLoader;
    }

    public final void initApplicationClassLoader(ClassLoader classLoader) {
        if (classLoader == null) {
            throw new IllegalArgumentException("loader is null");
        } else if (!Kit.testIfCanLoadRhinoClasses(classLoader)) {
            throw new IllegalArgumentException("Loader can not resolve Rhino classes");
        } else if (this.applicationClassLoader == null) {
            checkNotSealed();
            this.applicationClassLoader = classLoader;
        } else {
            throw new IllegalStateException("applicationClassLoader can only be set once");
        }
    }

    /* access modifiers changed from: protected */
    public Object doTopCall(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object call = callable.call(context, scriptable, scriptable2, objArr);
        return call instanceof ConsString ? call.toString() : call;
    }

    /* access modifiers changed from: protected */
    public void onContextCreated(Context context) {
        Object obj = this.listeners;
        int i = 0;
        while (true) {
            Listener listener = (Listener) Kit.getListener(obj, i);
            if (listener != null) {
                listener.contextCreated(context);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onContextReleased(Context context) {
        Object obj = this.listeners;
        int i = 0;
        while (true) {
            Listener listener = (Listener) Kit.getListener(obj, i);
            if (listener != null) {
                listener.contextReleased(context);
                i++;
            } else {
                return;
            }
        }
    }

    public final void addListener(Listener listener) {
        checkNotSealed();
        synchronized (this.listenersLock) {
            if (!this.disabledListening) {
                this.listeners = Kit.addListener(this.listeners, listener);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final void removeListener(Listener listener) {
        checkNotSealed();
        synchronized (this.listenersLock) {
            if (!this.disabledListening) {
                this.listeners = Kit.removeListener(this.listeners, listener);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void disableContextListening() {
        checkNotSealed();
        synchronized (this.listenersLock) {
            this.disabledListening = true;
            this.listeners = null;
        }
    }

    public final boolean isSealed() {
        return this.sealed;
    }

    public final void seal() {
        checkNotSealed();
        this.sealed = true;
    }

    /* access modifiers changed from: protected */
    public final void checkNotSealed() {
        if (this.sealed) {
            throw new IllegalStateException();
        }
    }

    public final Object call(ContextAction contextAction) {
        return Context.call(this, contextAction);
    }

    public Context enterContext() {
        return enterContext((Context) null);
    }

    public final Context enter() {
        return enterContext((Context) null);
    }

    public final void exit() {
        Context.exit();
    }

    public final Context enterContext(Context context) {
        return Context.enter(context, this);
    }
}
