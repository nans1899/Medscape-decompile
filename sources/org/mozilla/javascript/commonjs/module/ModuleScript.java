package org.mozilla.javascript.commonjs.module;

import java.io.Serializable;
import java.net.URI;
import org.mozilla.javascript.Script;

public class ModuleScript implements Serializable {
    private static final long serialVersionUID = 1;
    private final URI base;
    private final Script script;
    private final URI uri;

    public ModuleScript(Script script2, URI uri2, URI uri3) {
        this.script = script2;
        this.uri = uri2;
        this.base = uri3;
    }

    public Script getScript() {
        return this.script;
    }

    public URI getUri() {
        return this.uri;
    }

    public URI getBase() {
        return this.base;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r2.uri;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isSandboxed() {
        /*
            r2 = this;
            java.net.URI r0 = r2.base
            if (r0 == 0) goto L_0x0014
            java.net.URI r1 = r2.uri
            if (r1 == 0) goto L_0x0014
            java.net.URI r0 = r0.relativize(r1)
            boolean r0 = r0.isAbsolute()
            if (r0 != 0) goto L_0x0014
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.commonjs.module.ModuleScript.isSandboxed():boolean");
    }
}
