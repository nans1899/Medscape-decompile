package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.NotationDeclaration;

public class NotationDeclarationEvent extends BaseEvent implements NotationDeclaration {
    protected final String name;
    protected final String publicId;
    protected final String systemId;

    public NotationDeclarationEvent(String str, String str2, String str3) {
        super(14);
        this.name = str;
        this.publicId = str2;
        this.systemId = str3;
    }

    public String getName() {
        return this.name;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public String getSystemId() {
        return this.systemId;
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("<!NOTATION ");
        writer.write(getName());
        if (this.publicId != null) {
            writer.write(" PUBLIC \"");
            writer.write(this.publicId);
            writer.write(34);
        } else if (this.systemId != null) {
            writer.write(" SYSTEM");
        }
        if (this.systemId != null) {
            writer.write(" \"");
            writer.write(this.systemId);
            writer.write(34);
        }
        writer.write(62);
    }
}
