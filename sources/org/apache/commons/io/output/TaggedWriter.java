package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

public class TaggedWriter extends ProxyWriter {
    private final Serializable tag = UUID.randomUUID();

    public TaggedWriter(Writer writer) {
        super(writer);
    }

    public boolean isCauseOf(Exception exc) {
        return TaggedIOException.isTaggedWith(exc, this.tag);
    }

    public void throwIfCauseOf(Exception exc) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(exc, this.tag);
    }

    /* access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw new TaggedIOException(iOException, this.tag);
    }
}
