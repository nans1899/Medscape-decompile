package org.mozilla.javascript.commonjs.module.provider;

import java.io.Reader;
import java.io.Serializable;
import java.net.URI;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;

public abstract class CachingModuleScriptProviderBase implements ModuleScriptProvider, Serializable {
    private static final int loadConcurrencyLevel = (Runtime.getRuntime().availableProcessors() * 8);
    private static final int loadLockCount;
    private static final int loadLockMask;
    private static final int loadLockShift;
    private static final long serialVersionUID = 1;
    private final Object[] loadLocks = new Object[loadLockCount];
    private final ModuleSourceProvider moduleSourceProvider;

    /* access modifiers changed from: protected */
    public abstract CachedModuleScript getLoadedModule(String str);

    /* access modifiers changed from: protected */
    public abstract void putLoadedModule(String str, ModuleScript moduleScript, Object obj);

    static {
        int i = 1;
        int i2 = 0;
        while (i < loadConcurrencyLevel) {
            i2++;
            i <<= 1;
        }
        loadLockShift = 32 - i2;
        loadLockMask = i - 1;
        loadLockCount = i;
    }

    protected CachingModuleScriptProviderBase(ModuleSourceProvider moduleSourceProvider2) {
        int i = 0;
        while (true) {
            Object[] objArr = this.loadLocks;
            if (i < objArr.length) {
                objArr[i] = new Object();
                i++;
            } else {
                this.moduleSourceProvider = moduleSourceProvider2;
                return;
            }
        }
    }

    public ModuleScript getModuleScript(Context context, String str, URI uri, URI uri2, Scriptable scriptable) throws Exception {
        CachedModuleScript loadedModule = getLoadedModule(str);
        Object validator = getValidator(loadedModule);
        ModuleSource loadSource = uri == null ? this.moduleSourceProvider.loadSource(str, scriptable, validator) : this.moduleSourceProvider.loadSource(uri, uri2, validator);
        if (loadSource == ModuleSourceProvider.NOT_MODIFIED) {
            return loadedModule.getModule();
        }
        if (loadSource == null) {
            return null;
        }
        Reader reader = loadSource.getReader();
        try {
            synchronized (this.loadLocks[(str.hashCode() >>> loadLockShift) & loadLockMask]) {
                CachedModuleScript loadedModule2 = getLoadedModule(str);
                if (loadedModule2 == null || equal(validator, getValidator(loadedModule2))) {
                    URI uri3 = loadSource.getUri();
                    ModuleScript moduleScript = new ModuleScript(context.compileReader(reader, uri3.toString(), 1, loadSource.getSecurityDomain()), uri3, loadSource.getBase());
                    putLoadedModule(str, moduleScript, loadSource.getValidator());
                    reader.close();
                    return moduleScript;
                }
                ModuleScript module = loadedModule2.getModule();
                reader.close();
                return module;
            }
        } catch (Throwable th) {
            reader.close();
            throw th;
        }
    }

    public static class CachedModuleScript {
        private final ModuleScript moduleScript;
        private final Object validator;

        public CachedModuleScript(ModuleScript moduleScript2, Object obj) {
            this.moduleScript = moduleScript2;
            this.validator = obj;
        }

        /* access modifiers changed from: package-private */
        public ModuleScript getModule() {
            return this.moduleScript;
        }

        /* access modifiers changed from: package-private */
        public Object getValidator() {
            return this.validator;
        }
    }

    private static Object getValidator(CachedModuleScript cachedModuleScript) {
        if (cachedModuleScript == null) {
            return null;
        }
        return cachedModuleScript.getValidator();
    }

    private static boolean equal(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    protected static int getConcurrencyLevel() {
        return loadLockCount;
    }
}
