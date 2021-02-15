package org.mozilla.javascript.commonjs.module.provider;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import org.mozilla.javascript.Scriptable;

public interface ModuleSourceProvider {
    public static final ModuleSource NOT_MODIFIED = new ModuleSource((Reader) null, (Object) null, (URI) null, (URI) null, (Object) null);

    ModuleSource loadSource(String str, Scriptable scriptable, Object obj) throws IOException, URISyntaxException;

    ModuleSource loadSource(URI uri, URI uri2, Object obj) throws IOException, URISyntaxException;
}
