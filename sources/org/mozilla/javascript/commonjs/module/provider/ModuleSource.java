package org.mozilla.javascript.commonjs.module.provider;

import java.io.Reader;
import java.io.Serializable;
import java.net.URI;

public class ModuleSource implements Serializable {
    private static final long serialVersionUID = 1;
    private final URI base;
    private final Reader reader;
    private final Object securityDomain;
    private final URI uri;
    private final Object validator;

    public ModuleSource(Reader reader2, Object obj, URI uri2, URI uri3, Object obj2) {
        this.reader = reader2;
        this.securityDomain = obj;
        this.uri = uri2;
        this.base = uri3;
        this.validator = obj2;
    }

    public Reader getReader() {
        return this.reader;
    }

    public Object getSecurityDomain() {
        return this.securityDomain;
    }

    public URI getUri() {
        return this.uri;
    }

    public URI getBase() {
        return this.base;
    }

    public Object getValidator() {
        return this.validator;
    }
}
