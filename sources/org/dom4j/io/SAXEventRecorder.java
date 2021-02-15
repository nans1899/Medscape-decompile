package org.dom4j.io;

import com.google.common.base.Ascii;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class SAXEventRecorder extends DefaultHandler implements LexicalHandler, DeclHandler, DTDHandler, Externalizable {
    private static final String EMPTY_STRING = "";
    private static final byte NULL = 2;
    private static final byte OBJECT = 1;
    private static final byte STRING = 0;
    private static final String XMLNS = "xmlns";
    public static final long serialVersionUID = 1;
    private List events = new ArrayList();
    private Map prefixMappings = new HashMap();

    public void replay(ContentHandler contentHandler) throws SAXException {
        ContentHandler contentHandler2 = contentHandler;
        for (SAXEvent sAXEvent : this.events) {
            switch (sAXEvent.event) {
                case 1:
                    contentHandler2.processingInstruction((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1));
                    break;
                case 2:
                    contentHandler2.startPrefixMapping((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1));
                    break;
                case 3:
                    contentHandler2.endPrefixMapping((String) sAXEvent.getParm(0));
                    break;
                case 4:
                    contentHandler.startDocument();
                    break;
                case 5:
                    contentHandler.endDocument();
                    break;
                case 6:
                    AttributesImpl attributesImpl = new AttributesImpl();
                    List<String[]> list = (List) sAXEvent.getParm(3);
                    if (list != null) {
                        for (String[] strArr : list) {
                            attributesImpl.addAttribute(strArr[0], strArr[1], strArr[2], strArr[3], strArr[4]);
                        }
                    }
                    contentHandler2.startElement((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1), (String) sAXEvent.getParm(2), attributesImpl);
                    break;
                case 7:
                    contentHandler2.endElement((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1), (String) sAXEvent.getParm(2));
                    break;
                case 8:
                    contentHandler2.characters((char[]) sAXEvent.getParm(0), ((Integer) sAXEvent.getParm(1)).intValue(), ((Integer) sAXEvent.getParm(2)).intValue());
                    break;
                case 9:
                    ((LexicalHandler) contentHandler2).startDTD((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1), (String) sAXEvent.getParm(2));
                    break;
                case 10:
                    ((LexicalHandler) contentHandler2).endDTD();
                    break;
                case 11:
                    ((LexicalHandler) contentHandler2).startEntity((String) sAXEvent.getParm(0));
                    break;
                case 12:
                    ((LexicalHandler) contentHandler2).endEntity((String) sAXEvent.getParm(0));
                    break;
                case 13:
                    ((LexicalHandler) contentHandler2).startCDATA();
                    break;
                case 14:
                    ((LexicalHandler) contentHandler2).endCDATA();
                    break;
                case 15:
                    ((LexicalHandler) contentHandler2).comment((char[]) sAXEvent.getParm(0), ((Integer) sAXEvent.getParm(1)).intValue(), ((Integer) sAXEvent.getParm(2)).intValue());
                    break;
                case 16:
                    ((DeclHandler) contentHandler2).elementDecl((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1));
                    break;
                case 17:
                    ((DeclHandler) contentHandler2).attributeDecl((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1), (String) sAXEvent.getParm(2), (String) sAXEvent.getParm(3), (String) sAXEvent.getParm(4));
                    break;
                case 18:
                    ((DeclHandler) contentHandler2).internalEntityDecl((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1));
                    break;
                case 19:
                    ((DeclHandler) contentHandler2).externalEntityDecl((String) sAXEvent.getParm(0), (String) sAXEvent.getParm(1), (String) sAXEvent.getParm(2));
                    break;
                default:
                    StringBuffer stringBuffer = new StringBuffer("Unrecognized event: ");
                    stringBuffer.append(sAXEvent.event);
                    throw new SAXException(stringBuffer.toString());
            }
        }
    }

    public void processingInstruction(String str, String str2) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 1);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        this.events.add(sAXEvent);
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 2);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        this.events.add(sAXEvent);
    }

    public void endPrefixMapping(String str) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 3);
        sAXEvent.addParm(str);
        this.events.add(sAXEvent);
    }

    public void startDocument() throws SAXException {
        this.events.add(new SAXEvent((byte) 4));
    }

    public void endDocument() throws SAXException {
        this.events.add(new SAXEvent((byte) 5));
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        QName qName;
        SAXEvent sAXEvent = new SAXEvent((byte) 6);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        sAXEvent.addParm(str3);
        if (str != null) {
            qName = new QName(str2, Namespace.get(str));
        } else {
            qName = new QName(str2);
        }
        if (attributes != null && attributes.getLength() > 0) {
            ArrayList arrayList = new ArrayList(attributes.getLength());
            String[] strArr = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                String localName = attributes.getLocalName(i);
                if (localName.startsWith("xmlns")) {
                    String substring = localName.length() > 5 ? localName.substring(6) : "";
                    SAXEvent sAXEvent2 = new SAXEvent((byte) 2);
                    sAXEvent2.addParm(substring);
                    sAXEvent2.addParm(attributes.getValue(i));
                    this.events.add(sAXEvent2);
                    List list = (List) this.prefixMappings.get(qName);
                    if (list == null) {
                        list = new ArrayList();
                        this.prefixMappings.put(qName, list);
                    }
                    list.add(substring);
                } else {
                    arrayList.add(new String[]{attributes.getURI(i), localName, attributes.getQName(i), attributes.getType(i), attributes.getValue(i)});
                }
            }
            sAXEvent.addParm(arrayList);
        }
        this.events.add(sAXEvent);
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        QName qName;
        SAXEvent sAXEvent = new SAXEvent((byte) 7);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        sAXEvent.addParm(str3);
        this.events.add(sAXEvent);
        if (str != null) {
            qName = new QName(str2, Namespace.get(str));
        } else {
            qName = new QName(str2);
        }
        List<Object> list = (List) this.prefixMappings.get(qName);
        if (list != null) {
            for (Object addParm : list) {
                SAXEvent sAXEvent2 = new SAXEvent((byte) 3);
                sAXEvent2.addParm(addParm);
                this.events.add(sAXEvent2);
            }
        }
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 8);
        sAXEvent.addParm(cArr);
        sAXEvent.addParm(new Integer(i));
        sAXEvent.addParm(new Integer(i2));
        this.events.add(sAXEvent);
    }

    public void startDTD(String str, String str2, String str3) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 9);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        sAXEvent.addParm(str3);
        this.events.add(sAXEvent);
    }

    public void endDTD() throws SAXException {
        this.events.add(new SAXEvent((byte) 10));
    }

    public void startEntity(String str) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 11);
        sAXEvent.addParm(str);
        this.events.add(sAXEvent);
    }

    public void endEntity(String str) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent(Ascii.FF);
        sAXEvent.addParm(str);
        this.events.add(sAXEvent);
    }

    public void startCDATA() throws SAXException {
        this.events.add(new SAXEvent(Ascii.CR));
    }

    public void endCDATA() throws SAXException {
        this.events.add(new SAXEvent(Ascii.SO));
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent(Ascii.SI);
        sAXEvent.addParm(cArr);
        sAXEvent.addParm(new Integer(i));
        sAXEvent.addParm(new Integer(i2));
        this.events.add(sAXEvent);
    }

    public void elementDecl(String str, String str2) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent(Ascii.DLE);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        this.events.add(sAXEvent);
    }

    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 17);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        sAXEvent.addParm(str3);
        sAXEvent.addParm(str4);
        sAXEvent.addParm(str5);
        this.events.add(sAXEvent);
    }

    public void internalEntityDecl(String str, String str2) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent(Ascii.DC2);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        this.events.add(sAXEvent);
    }

    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
        SAXEvent sAXEvent = new SAXEvent((byte) 19);
        sAXEvent.addParm(str);
        sAXEvent.addParm(str2);
        sAXEvent.addParm(str3);
        this.events.add(sAXEvent);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        if (this.events == null) {
            objectOutput.writeByte(2);
            return;
        }
        objectOutput.writeByte(1);
        objectOutput.writeObject(this.events);
    }

    public void readExternal(ObjectInput objectInput) throws ClassNotFoundException, IOException {
        if (objectInput.readByte() != 2) {
            this.events = (List) objectInput.readObject();
        }
    }

    static class SAXEvent implements Externalizable {
        static final byte ATTRIBUTE_DECL = 17;
        static final byte CHARACTERS = 8;
        static final byte COMMENT = 15;
        static final byte ELEMENT_DECL = 16;
        static final byte END_CDATA = 14;
        static final byte END_DOCUMENT = 5;
        static final byte END_DTD = 10;
        static final byte END_ELEMENT = 7;
        static final byte END_ENTITY = 12;
        static final byte END_PREFIX_MAPPING = 3;
        static final byte EXTERNAL_ENTITY_DECL = 19;
        static final byte INTERNAL_ENTITY_DECL = 18;
        static final byte PROCESSING_INSTRUCTION = 1;
        static final byte START_CDATA = 13;
        static final byte START_DOCUMENT = 4;
        static final byte START_DTD = 9;
        static final byte START_ELEMENT = 6;
        static final byte START_ENTITY = 11;
        static final byte START_PREFIX_MAPPING = 2;
        public static final long serialVersionUID = 1;
        protected byte event;
        protected List parms;

        public SAXEvent() {
        }

        SAXEvent(byte b) {
            this.event = b;
        }

        /* access modifiers changed from: package-private */
        public void addParm(Object obj) {
            if (this.parms == null) {
                this.parms = new ArrayList(3);
            }
            this.parms.add(obj);
        }

        /* access modifiers changed from: package-private */
        public Object getParm(int i) {
            List list = this.parms;
            if (list == null || i >= list.size()) {
                return null;
            }
            return this.parms.get(i);
        }

        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeByte(this.event);
            if (this.parms == null) {
                objectOutput.writeByte(2);
                return;
            }
            objectOutput.writeByte(1);
            objectOutput.writeObject(this.parms);
        }

        public void readExternal(ObjectInput objectInput) throws ClassNotFoundException, IOException {
            this.event = objectInput.readByte();
            if (objectInput.readByte() != 2) {
                this.parms = (List) objectInput.readObject();
            }
        }
    }
}
