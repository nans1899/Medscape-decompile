package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;

public class EntityReferenceEvent extends BaseEvent implements EntityReference {
    private EntityDeclaration ed;
    private String name;
    private String replacementText;

    public String getBaseURI() {
        return null;
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return null;
    }

    public EntityReferenceEvent() {
        init();
    }

    public EntityReferenceEvent(String str, EntityDeclaration entityDeclaration) {
        init();
        this.name = str;
        this.ed = entityDeclaration;
    }

    public String getReplacementText() {
        return this.ed.getReplacementText();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setReplacementText(String str) {
        this.replacementText = str;
    }

    public EntityDeclaration getDeclaration() {
        return this.ed;
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(9);
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write(38);
        writer.write(getName());
        writer.write(59);
    }

    public String toString() {
        String replacementText2 = getReplacementText();
        if (replacementText2 == null) {
            replacementText2 = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&");
        stringBuffer.append(getName());
        stringBuffer.append(":='");
        stringBuffer.append(replacementText2);
        stringBuffer.append("'");
        return stringBuffer.toString();
    }
}
