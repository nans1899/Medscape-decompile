package org.mozilla.javascript.commonjs.module.provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.provider.CachingModuleScriptProviderBase;

public class SoftCachingModuleScriptProvider extends CachingModuleScriptProviderBase {
    private static final long serialVersionUID = 1;
    private transient ReferenceQueue<Script> scriptRefQueue = new ReferenceQueue<>();
    private transient ConcurrentMap<String, ScriptReference> scripts = new ConcurrentHashMap(16, 0.75f, getConcurrencyLevel());

    public SoftCachingModuleScriptProvider(ModuleSourceProvider moduleSourceProvider) {
        super(moduleSourceProvider);
    }

    public ModuleScript getModuleScript(Context context, String str, URI uri, URI uri2, Scriptable scriptable) throws Exception {
        while (true) {
            ScriptReference scriptReference = (ScriptReference) this.scriptRefQueue.poll();
            if (scriptReference == null) {
                return super.getModuleScript(context, str, uri, uri2, scriptable);
            }
            this.scripts.remove(scriptReference.getModuleId(), scriptReference);
        }
    }

    /* access modifiers changed from: protected */
    public CachingModuleScriptProviderBase.CachedModuleScript getLoadedModule(String str) {
        ScriptReference scriptReference = (ScriptReference) this.scripts.get(str);
        if (scriptReference != null) {
            return scriptReference.getCachedModuleScript();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void putLoadedModule(String str, ModuleScript moduleScript, Object obj) {
        this.scripts.put(str, new ScriptReference(moduleScript.getScript(), str, moduleScript.getUri(), moduleScript.getBase(), obj, this.scriptRefQueue));
    }

    private static class ScriptReference extends SoftReference<Script> {
        private final URI base;
        private final String moduleId;
        private final URI uri;
        private final Object validator;

        ScriptReference(Script script, String str, URI uri2, URI uri3, Object obj, ReferenceQueue<Script> referenceQueue) {
            super(script, referenceQueue);
            this.moduleId = str;
            this.uri = uri2;
            this.base = uri3;
            this.validator = obj;
        }

        /* access modifiers changed from: package-private */
        public CachingModuleScriptProviderBase.CachedModuleScript getCachedModuleScript() {
            Script script = (Script) get();
            if (script == null) {
                return null;
            }
            return new CachingModuleScriptProviderBase.CachedModuleScript(new ModuleScript(script, this.uri, this.base), this.validator);
        }

        /* access modifiers changed from: package-private */
        public String getModuleId() {
            return this.moduleId;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.scriptRefQueue = new ReferenceQueue<>();
        this.scripts = new ConcurrentHashMap();
        for (Map.Entry entry : ((Map) objectInputStream.readObject()).entrySet()) {
            CachingModuleScriptProviderBase.CachedModuleScript cachedModuleScript = (CachingModuleScriptProviderBase.CachedModuleScript) entry.getValue();
            putLoadedModule((String) entry.getKey(), cachedModuleScript.getModule(), cachedModuleScript.getValidator());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : this.scripts.entrySet()) {
            CachingModuleScriptProviderBase.CachedModuleScript cachedModuleScript = ((ScriptReference) entry.getValue()).getCachedModuleScript();
            if (cachedModuleScript != null) {
                hashMap.put(entry.getKey(), cachedModuleScript);
            }
        }
        objectOutputStream.writeObject(hashMap);
    }
}
