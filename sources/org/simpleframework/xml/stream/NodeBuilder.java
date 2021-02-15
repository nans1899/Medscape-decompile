package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

public final class NodeBuilder {
    private static Provider PROVIDER = ProviderFactory.getInstance();

    public static InputNode read(InputStream inputStream) throws Exception {
        return read(PROVIDER.provide(inputStream));
    }

    public static InputNode read(Reader reader) throws Exception {
        return read(PROVIDER.provide(reader));
    }

    private static InputNode read(EventReader eventReader) throws Exception {
        return new NodeReader(eventReader).readRoot();
    }

    public static OutputNode write(Writer writer) throws Exception {
        return write(writer, new Format());
    }

    public static OutputNode write(Writer writer, Format format) throws Exception {
        return new NodeWriter(writer, format).writeRoot();
    }
}
