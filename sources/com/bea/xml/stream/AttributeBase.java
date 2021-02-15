package com.bea.xml.stream;

import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

public class AttributeBase implements Attribute, Location {
    private QName attributeType;
    private int characterOffset = 0;
    private int column = -1;
    private int eventType = -1;
    private int line = -1;
    private String locationURI;
    private QName name;
    private String value;

    public String getDTDType() {
        return "CDATA";
    }

    public int getEventType() {
        return 10;
    }

    public Location getLocation() {
        return this;
    }

    public String getPublicId() {
        return null;
    }

    public QName getSchemaType() {
        return null;
    }

    public String getSourceName() {
        return null;
    }

    public String getSystemId() {
        return null;
    }

    public boolean isAttribute() {
        return true;
    }

    public boolean isCharacters() {
        return false;
    }

    public boolean isDefault() {
        return true;
    }

    public boolean isEndDocument() {
        return false;
    }

    public boolean isEndElement() {
        return false;
    }

    public boolean isEndEntity() {
        return false;
    }

    public boolean isEntityReference() {
        return false;
    }

    public boolean isNamespace() {
        return false;
    }

    public boolean isNamespaceDeclaration() {
        return false;
    }

    public boolean isProcessingInstruction() {
        return false;
    }

    public boolean isSpecified() {
        return true;
    }

    public boolean isStartDocument() {
        return false;
    }

    public boolean isStartElement() {
        return false;
    }

    public boolean isStartEntity() {
        return false;
    }

    public void recycle() {
    }

    public AttributeBase(String str, String str2, String str3, String str4, String str5) {
        this.name = new QName(str2, str3, str == null ? "" : str);
        this.value = str4;
        this.attributeType = new QName(str5);
    }

    public AttributeBase(String str, String str2, String str3) {
        this.name = new QName("", str2, str == null ? "" : str);
        this.value = str3;
    }

    public AttributeBase(QName qName, String str) {
        this.name = qName;
        this.value = str;
    }

    public String toString() {
        if (this.name.getPrefix() == null || this.name.getPrefix().equals("")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.name.getLocalPart());
            stringBuffer.append("='");
            stringBuffer.append(this.value);
            stringBuffer.append("'");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("['");
        stringBuffer2.append(this.name.getNamespaceURI());
        stringBuffer2.append("']:");
        stringBuffer2.append(this.name.getPrefix());
        stringBuffer2.append(":");
        stringBuffer2.append(this.name.getLocalPart());
        stringBuffer2.append("='");
        stringBuffer2.append(this.value);
        stringBuffer2.append("'");
        return stringBuffer2.toString();
    }

    public int getLineNumber() {
        return this.line;
    }

    public void setLineNumber(int i) {
        this.line = i;
    }

    public int getColumnNumber() {
        return this.column;
    }

    public void setColumnNumber(int i) {
        this.column = i;
    }

    public int getCharacterOffset() {
        return this.characterOffset;
    }

    public void setCharacterOffset(int i) {
        this.characterOffset = i;
    }

    public String getLocationURI() {
        return this.locationURI;
    }

    public void setLocationURI(String str) {
        this.locationURI = str;
    }

    public boolean hasName() {
        return this.name != null;
    }

    public QName getName() {
        return this.name;
    }

    public String getLocalName() {
        return this.name.getLocalPart();
    }

    public String getValue() {
        return this.value;
    }

    public String getNamespaceURI() {
        return this.name.getNamespaceURI();
    }

    public void setNamespaceURI(String str) {
        this.name = new QName(str, this.name.getLocalPart());
    }

    public StartElement asStartElement() {
        throw new ClassCastException("cannnot cast AttributeBase to StartElement");
    }

    public EndElement asEndElement() {
        throw new ClassCastException("cannnot cast AttributeBase to EndElement");
    }

    public Characters asCharacters() {
        throw new ClassCastException("cannnot cast AttributeBase to Characters");
    }

    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        char charAt;
        try {
            String prefix = this.name.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                writer.write(prefix);
                writer.write(58);
            }
            writer.write(this.name.getLocalPart());
            writer.write("=\"");
            String str = this.value;
            int length = str.length();
            if (length > 0) {
                int i = 0;
                while (true) {
                    if (i >= length || (charAt = str.charAt(i)) == '\"' || charAt == '&' || charAt == '<') {
                        break;
                    } else if (charAt < ' ') {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i == length) {
                    writer.write(str);
                } else {
                    if (i > 0) {
                        writer.write(str, 0, i);
                    }
                    while (i < length) {
                        char charAt2 = str.charAt(i);
                        if (charAt2 == '\"') {
                            writer.write("&quot;");
                        } else if (charAt2 == '&') {
                            writer.write("&amp;");
                        } else if (charAt2 == '<') {
                            writer.write("&lt;");
                        } else if (charAt2 < ' ') {
                            writeEncodedChar(writer, charAt2);
                        } else {
                            writer.write(charAt2);
                        }
                        i++;
                    }
                }
            }
            writer.write(34);
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    public static void writeEncodedChar(Writer writer, char c) throws IOException {
        writer.write("&#");
        writer.write(Integer.toString(c));
        writer.write(59);
    }
}
