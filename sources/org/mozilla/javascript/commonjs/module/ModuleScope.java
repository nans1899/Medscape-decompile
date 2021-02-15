package org.mozilla.javascript.commonjs.module;

import java.net.URI;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;

public class ModuleScope extends TopLevel {
    private static final long serialVersionUID = 1;
    private final URI base;
    private final URI uri;

    public ModuleScope(Scriptable scriptable, URI uri2, URI uri3) {
        this.uri = uri2;
        this.base = uri3;
        setPrototype(scriptable);
        cacheBuiltins();
    }

    public URI getUri() {
        return this.uri;
    }

    public URI getBase() {
        return this.base;
    }
}
