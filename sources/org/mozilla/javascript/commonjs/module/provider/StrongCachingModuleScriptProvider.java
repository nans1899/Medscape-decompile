package org.mozilla.javascript.commonjs.module.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.provider.CachingModuleScriptProviderBase;

public class StrongCachingModuleScriptProvider extends CachingModuleScriptProviderBase {
    private static final long serialVersionUID = 1;
    private final Map<String, CachingModuleScriptProviderBase.CachedModuleScript> modules = new ConcurrentHashMap(16, 0.75f, getConcurrencyLevel());

    public StrongCachingModuleScriptProvider(ModuleSourceProvider moduleSourceProvider) {
        super(moduleSourceProvider);
    }

    /* access modifiers changed from: protected */
    public CachingModuleScriptProviderBase.CachedModuleScript getLoadedModule(String str) {
        return this.modules.get(str);
    }

    /* access modifiers changed from: protected */
    public void putLoadedModule(String str, ModuleScript moduleScript, Object obj) {
        this.modules.put(str, new CachingModuleScriptProviderBase.CachedModuleScript(moduleScript, obj));
    }
}
