package org.mozilla.javascript.commonjs.module.provider;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public abstract class ModuleSourceProviderBase implements ModuleSourceProvider, Serializable {
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    public boolean entityNeedsRevalidation(Object obj) {
        return true;
    }

    /* access modifiers changed from: protected */
    public ModuleSource loadFromFallbackLocations(String str, Object obj) throws IOException, URISyntaxException {
        return null;
    }

    /* access modifiers changed from: protected */
    public ModuleSource loadFromPrivilegedLocations(String str, Object obj) throws IOException, URISyntaxException {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract ModuleSource loadFromUri(URI uri, URI uri2, Object obj) throws IOException, URISyntaxException;

    public ModuleSource loadSource(String str, Scriptable scriptable, Object obj) throws IOException, URISyntaxException {
        ModuleSource loadFromPathArray;
        if (!entityNeedsRevalidation(obj)) {
            return NOT_MODIFIED;
        }
        ModuleSource loadFromPrivilegedLocations = loadFromPrivilegedLocations(str, obj);
        if (loadFromPrivilegedLocations != null) {
            return loadFromPrivilegedLocations;
        }
        if (scriptable == null || (loadFromPathArray = loadFromPathArray(str, scriptable, obj)) == null) {
            return loadFromFallbackLocations(str, obj);
        }
        return loadFromPathArray;
    }

    public ModuleSource loadSource(URI uri, URI uri2, Object obj) throws IOException, URISyntaxException {
        return loadFromUri(uri, uri2, obj);
    }

    private ModuleSource loadFromPathArray(String str, Scriptable scriptable, Object obj) throws IOException {
        long uint32 = ScriptRuntime.toUint32(ScriptableObject.getProperty(scriptable, Name.LENGTH));
        int i = uint32 > 2147483647L ? Integer.MAX_VALUE : (int) uint32;
        int i2 = 0;
        while (i2 < i) {
            String ensureTrailingSlash = ensureTrailingSlash((String) ScriptableObject.getTypedProperty(scriptable, i2, String.class));
            try {
                URI uri = new URI(ensureTrailingSlash);
                if (!uri.isAbsolute()) {
                    uri = new File(ensureTrailingSlash).toURI().resolve("");
                }
                ModuleSource loadFromUri = loadFromUri(uri.resolve(str), uri, obj);
                if (loadFromUri != null) {
                    return loadFromUri;
                }
                i2++;
            } catch (URISyntaxException e) {
                throw new MalformedURLException(e.getMessage());
            }
        }
        return null;
    }

    private static String ensureTrailingSlash(String str) {
        return str.endsWith("/") ? str : str.concat("/");
    }
}
