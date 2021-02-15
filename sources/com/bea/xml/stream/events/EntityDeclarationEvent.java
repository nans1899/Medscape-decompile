package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.EntityDeclaration;

public class EntityDeclarationEvent extends BaseEvent implements EntityDeclaration {
    protected final String name;
    protected final String replacementText;

    public String getBaseURI() {
        return null;
    }

    public String getNotationName() {
        return null;
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return null;
    }

    public EntityDeclarationEvent(String str, String str2) {
        super(15);
        this.name = str;
        this.replacementText = str2;
    }

    public String getReplacementText() {
        return this.replacementText;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<!ENTITY ");
        writer.write(getName());
        writer.write(34);
        writer.write(getReplacementText());
        writer.write("\">");
    }
}
