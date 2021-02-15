package com.bea.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

public abstract class NamedEvent extends BaseEvent {
    private QName name;

    /* access modifiers changed from: protected */
    public abstract void doWriteAsEncodedUnicode(Writer writer) throws IOException, XMLStreamException;

    public NamedEvent() {
    }

    public NamedEvent(QName qName) {
        this.name = qName;
    }

    public NamedEvent(String str) {
        this.name = new QName(str);
    }

    public NamedEvent(String str, String str2, String str3) {
        this.name = new QName(str2, str3, str);
    }

    public QName getName() {
        return this.name;
    }

    public void setName(QName qName) {
        this.name = qName;
    }

    public String nameAsString() {
        if ("".equals(this.name.getNamespaceURI())) {
            return this.name.getLocalPart();
        }
        if (this.name.getPrefix() == null || this.name.getPrefix().equals("")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("['");
            stringBuffer.append(this.name.getNamespaceURI());
            stringBuffer.append("']:");
            stringBuffer.append(this.name.getLocalPart());
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("['");
        stringBuffer2.append(this.name.getNamespaceURI());
        stringBuffer2.append("']:");
        stringBuffer2.append(this.name.getPrefix());
        stringBuffer2.append(":");
        stringBuffer2.append(this.name.getLocalPart());
        return stringBuffer2.toString();
    }
}
