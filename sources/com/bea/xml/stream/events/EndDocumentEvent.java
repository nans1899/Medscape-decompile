package com.bea.xml.stream.events;

import java.io.Writer;
import javax.xml.stream.events.EndDocument;

public class EndDocumentEvent extends BaseEvent implements EndDocument {
    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) {
    }

    public String toString() {
        return "<? EndDocument ?>";
    }

    public EndDocumentEvent() {
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(8);
    }
}
