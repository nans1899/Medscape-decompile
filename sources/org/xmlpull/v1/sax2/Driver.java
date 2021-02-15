package org.xmlpull.v1.sax2;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Driver implements Locator, XMLReader, Attributes {
    protected static final String APACHE_DYNAMIC_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/dynamic";
    protected static final String APACHE_SCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
    protected static final String DECLARATION_HANDLER_PROPERTY = "http://xml.org/sax/properties/declaration-handler";
    protected static final String LEXICAL_HANDLER_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
    protected static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected ContentHandler contentHandler = new DefaultHandler();
    protected ErrorHandler errorHandler = new DefaultHandler();
    protected XmlPullParser pp;
    protected String systemId;

    public DTDHandler getDTDHandler() {
        return null;
    }

    public EntityResolver getEntityResolver() {
        return null;
    }

    public String getPublicId() {
        return null;
    }

    public void setDTDHandler(DTDHandler dTDHandler) {
    }

    public void setEntityResolver(EntityResolver entityResolver) {
    }

    public Driver() throws XmlPullParserException {
        XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
        newInstance.setNamespaceAware(true);
        this.pp = newInstance.newPullParser();
    }

    public Driver(XmlPullParser xmlPullParser) throws XmlPullParserException {
        this.pp = xmlPullParser;
    }

    public int getLength() {
        return this.pp.getAttributeCount();
    }

    public String getURI(int i) {
        return this.pp.getAttributeNamespace(i);
    }

    public String getLocalName(int i) {
        return this.pp.getAttributeName(i);
    }

    public String getQName(int i) {
        String attributePrefix = this.pp.getAttributePrefix(i);
        if (attributePrefix == null) {
            return this.pp.getAttributeName(i);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(attributePrefix);
        stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        stringBuffer.append(this.pp.getAttributeName(i));
        return stringBuffer.toString();
    }

    public String getType(int i) {
        return this.pp.getAttributeType(i);
    }

    public String getValue(int i) {
        return this.pp.getAttributeValue(i);
    }

    public int getIndex(String str, String str2) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeNamespace(i).equals(str) && this.pp.getAttributeName(i).equals(str2)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndex(String str) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeName(i).equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public String getType(String str, String str2) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeNamespace(i).equals(str) && this.pp.getAttributeName(i).equals(str2)) {
                return this.pp.getAttributeType(i);
            }
        }
        return null;
    }

    public String getType(String str) {
        for (int i = 0; i < this.pp.getAttributeCount(); i++) {
            if (this.pp.getAttributeName(i).equals(str)) {
                return this.pp.getAttributeType(i);
            }
        }
        return null;
    }

    public String getValue(String str, String str2) {
        return this.pp.getAttributeValue(str, str2);
    }

    public String getValue(String str) {
        return this.pp.getAttributeValue((String) null, str);
    }

    public String getSystemId() {
        return this.systemId;
    }

    public int getLineNumber() {
        return this.pp.getLineNumber();
    }

    public int getColumnNumber() {
        return this.pp.getColumnNumber();
    }

    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (NAMESPACES_FEATURE.equals(str)) {
            return this.pp.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
        }
        if (NAMESPACE_PREFIXES_FEATURE.equals(str)) {
            return this.pp.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES);
        }
        if (VALIDATION_FEATURE.equals(str)) {
            return this.pp.getFeature(XmlPullParser.FEATURE_VALIDATION);
        }
        return this.pp.getFeature(str);
    }

    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (NAMESPACES_FEATURE.equals(str)) {
                this.pp.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", z);
            } else if (NAMESPACE_PREFIXES_FEATURE.equals(str)) {
                if (this.pp.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES) != z) {
                    this.pp.setFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES, z);
                }
            } else if (VALIDATION_FEATURE.equals(str)) {
                this.pp.setFeature(XmlPullParser.FEATURE_VALIDATION, z);
            } else {
                this.pp.setFeature(str, z);
            }
        } catch (XmlPullParserException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("problem with setting feature ");
            stringBuffer.append(str);
            stringBuffer.append(": ");
            stringBuffer.append(e);
            throw new SAXNotSupportedException(stringBuffer.toString());
        }
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (!DECLARATION_HANDLER_PROPERTY.equals(str) && !LEXICAL_HANDLER_PROPERTY.equals(str)) {
            return this.pp.getProperty(str);
        }
        return null;
    }

    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (DECLARATION_HANDLER_PROPERTY.equals(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("not supported setting property ");
            stringBuffer.append(str);
            throw new SAXNotSupportedException(stringBuffer.toString());
        } else if (!LEXICAL_HANDLER_PROPERTY.equals(str)) {
            try {
                this.pp.setProperty(str, obj);
            } catch (XmlPullParserException e) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("not supported set property ");
                stringBuffer2.append(str);
                stringBuffer2.append(": ");
                stringBuffer2.append(e);
                throw new SAXNotSupportedException(stringBuffer2.toString());
            }
        } else {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("not supported setting property ");
            stringBuffer3.append(str);
            throw new SAXNotSupportedException(stringBuffer3.toString());
        }
    }

    public void setContentHandler(ContentHandler contentHandler2) {
        this.contentHandler = contentHandler2;
    }

    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler2) {
        this.errorHandler = errorHandler2;
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = new java.io.FileInputStream(r4.systemId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2 = new java.lang.StringBuffer();
        r2.append("could not open file with systemId ");
        r2.append(r4.systemId);
        r4.errorHandler.fatalError(new org.xml.sax.SAXParseException(r2.toString(), r4, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0064, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parse(org.xml.sax.InputSource r5) throws org.xml.sax.SAXException, java.io.IOException {
        /*
            r4 = this;
            java.lang.String r0 = r5.getSystemId()
            r4.systemId = r0
            org.xml.sax.ContentHandler r0 = r4.contentHandler
            r0.setDocumentLocator(r4)
            java.io.Reader r0 = r5.getCharacterStream()
            java.lang.String r1 = "parsing initialization error: "
            if (r0 != 0) goto L_0x006b
            java.io.InputStream r0 = r5.getByteStream()     // Catch:{ XmlPullParserException -> 0x00cb }
            java.lang.String r2 = r5.getEncoding()     // Catch:{ XmlPullParserException -> 0x00cb }
            if (r0 != 0) goto L_0x0065
            java.lang.String r5 = r5.getSystemId()     // Catch:{ XmlPullParserException -> 0x00cb }
            r4.systemId = r5     // Catch:{ XmlPullParserException -> 0x00cb }
            if (r5 != 0) goto L_0x0032
            org.xml.sax.SAXParseException r5 = new org.xml.sax.SAXParseException     // Catch:{ XmlPullParserException -> 0x00cb }
            java.lang.String r0 = "null source systemId"
            r5.<init>(r0, r4)     // Catch:{ XmlPullParserException -> 0x00cb }
            org.xml.sax.ErrorHandler r0 = r4.errorHandler     // Catch:{ XmlPullParserException -> 0x00cb }
            r0.fatalError(r5)     // Catch:{ XmlPullParserException -> 0x00cb }
            return
        L_0x0032:
            java.net.URL r5 = new java.net.URL     // Catch:{ MalformedURLException -> 0x003e }
            java.lang.String r0 = r4.systemId     // Catch:{ MalformedURLException -> 0x003e }
            r5.<init>(r0)     // Catch:{ MalformedURLException -> 0x003e }
            java.io.InputStream r0 = r5.openStream()     // Catch:{ MalformedURLException -> 0x003e }
            goto L_0x0065
        L_0x003e:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0046 }
            java.lang.String r5 = r4.systemId     // Catch:{ FileNotFoundException -> 0x0046 }
            r0.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0046 }
            goto L_0x0065
        L_0x0046:
            r5 = move-exception
            org.xml.sax.SAXParseException r0 = new org.xml.sax.SAXParseException     // Catch:{ XmlPullParserException -> 0x00cb }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ XmlPullParserException -> 0x00cb }
            r2.<init>()     // Catch:{ XmlPullParserException -> 0x00cb }
            java.lang.String r3 = "could not open file with systemId "
            r2.append(r3)     // Catch:{ XmlPullParserException -> 0x00cb }
            java.lang.String r3 = r4.systemId     // Catch:{ XmlPullParserException -> 0x00cb }
            r2.append(r3)     // Catch:{ XmlPullParserException -> 0x00cb }
            java.lang.String r2 = r2.toString()     // Catch:{ XmlPullParserException -> 0x00cb }
            r0.<init>(r2, r4, r5)     // Catch:{ XmlPullParserException -> 0x00cb }
            org.xml.sax.ErrorHandler r5 = r4.errorHandler     // Catch:{ XmlPullParserException -> 0x00cb }
            r5.fatalError(r0)     // Catch:{ XmlPullParserException -> 0x00cb }
            return
        L_0x0065:
            org.xmlpull.v1.XmlPullParser r5 = r4.pp     // Catch:{ XmlPullParserException -> 0x00cb }
            r5.setInput(r0, r2)     // Catch:{ XmlPullParserException -> 0x00cb }
            goto L_0x0070
        L_0x006b:
            org.xmlpull.v1.XmlPullParser r5 = r4.pp     // Catch:{ XmlPullParserException -> 0x00cb }
            r5.setInput(r0)     // Catch:{ XmlPullParserException -> 0x00cb }
        L_0x0070:
            org.xml.sax.ContentHandler r5 = r4.contentHandler     // Catch:{ XmlPullParserException -> 0x00b0 }
            r5.startDocument()     // Catch:{ XmlPullParserException -> 0x00b0 }
            org.xmlpull.v1.XmlPullParser r5 = r4.pp     // Catch:{ XmlPullParserException -> 0x00b0 }
            r5.next()     // Catch:{ XmlPullParserException -> 0x00b0 }
            org.xmlpull.v1.XmlPullParser r5 = r4.pp     // Catch:{ XmlPullParserException -> 0x00b0 }
            int r5 = r5.getEventType()     // Catch:{ XmlPullParserException -> 0x00b0 }
            r0 = 2
            if (r5 == r0) goto L_0x00a5
            org.xml.sax.SAXParseException r5 = new org.xml.sax.SAXParseException     // Catch:{ XmlPullParserException -> 0x00b0 }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ XmlPullParserException -> 0x00b0 }
            r0.<init>()     // Catch:{ XmlPullParserException -> 0x00b0 }
            java.lang.String r2 = "expected start tag not"
            r0.append(r2)     // Catch:{ XmlPullParserException -> 0x00b0 }
            org.xmlpull.v1.XmlPullParser r2 = r4.pp     // Catch:{ XmlPullParserException -> 0x00b0 }
            java.lang.String r2 = r2.getPositionDescription()     // Catch:{ XmlPullParserException -> 0x00b0 }
            r0.append(r2)     // Catch:{ XmlPullParserException -> 0x00b0 }
            java.lang.String r0 = r0.toString()     // Catch:{ XmlPullParserException -> 0x00b0 }
            r5.<init>(r0, r4)     // Catch:{ XmlPullParserException -> 0x00b0 }
            org.xml.sax.ErrorHandler r0 = r4.errorHandler     // Catch:{ XmlPullParserException -> 0x00b0 }
            r0.fatalError(r5)     // Catch:{ XmlPullParserException -> 0x00b0 }
            return
        L_0x00a5:
            org.xmlpull.v1.XmlPullParser r5 = r4.pp
            r4.parseSubTree(r5)
            org.xml.sax.ContentHandler r5 = r4.contentHandler
            r5.endDocument()
            return
        L_0x00b0:
            r5 = move-exception
            org.xml.sax.SAXParseException r0 = new org.xml.sax.SAXParseException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            r2.append(r5)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1, r4, r5)
            org.xml.sax.ErrorHandler r5 = r4.errorHandler
            r5.fatalError(r0)
            return
        L_0x00cb:
            r5 = move-exception
            org.xml.sax.SAXParseException r0 = new org.xml.sax.SAXParseException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            r2.append(r5)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1, r4, r5)
            org.xml.sax.ErrorHandler r5 = r4.errorHandler
            r5.fatalError(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.v1.sax2.Driver.parse(org.xml.sax.InputSource):void");
    }

    public void parse(String str) throws SAXException, IOException {
        parse(new InputSource(str));
    }

    public void parseSubTree(XmlPullParser xmlPullParser) throws SAXException, IOException {
        this.pp = xmlPullParser;
        boolean feature = xmlPullParser.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
        try {
            if (xmlPullParser.getEventType() == 2) {
                int[] iArr = new int[2];
                StringBuffer stringBuffer = new StringBuffer(16);
                int depth = xmlPullParser.getDepth() - 1;
                int i = 2;
                while (i != 1) {
                    int i2 = 0;
                    if (i != 2) {
                        if (i != 3) {
                            if (i == 4) {
                                this.contentHandler.characters(xmlPullParser.getTextCharacters(iArr), iArr[0], iArr[1]);
                            }
                        } else if (feature) {
                            String name = xmlPullParser.getName();
                            String prefix = xmlPullParser.getPrefix();
                            if (prefix != null) {
                                stringBuffer.setLength(0);
                                stringBuffer.append(prefix);
                                stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
                                stringBuffer.append(name);
                            }
                            this.contentHandler.endElement(xmlPullParser.getNamespace(), name, prefix != null ? name : stringBuffer.toString());
                            if (depth > xmlPullParser.getDepth()) {
                                i2 = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth());
                            }
                            for (int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth() - 1) - 1; namespaceCount >= i2; namespaceCount--) {
                                this.contentHandler.endPrefixMapping(xmlPullParser.getNamespacePrefix(namespaceCount));
                            }
                        } else {
                            this.contentHandler.endElement(xmlPullParser.getNamespace(), xmlPullParser.getName(), xmlPullParser.getName());
                        }
                    } else if (feature) {
                        int depth2 = xmlPullParser.getDepth() - 1;
                        int namespaceCount2 = xmlPullParser.getNamespaceCount(depth2 + 1);
                        for (int namespaceCount3 = depth > depth2 ? xmlPullParser.getNamespaceCount(depth2) : 0; namespaceCount3 < namespaceCount2; namespaceCount3++) {
                            this.contentHandler.startPrefixMapping(xmlPullParser.getNamespacePrefix(namespaceCount3), xmlPullParser.getNamespaceUri(namespaceCount3));
                        }
                        String name2 = xmlPullParser.getName();
                        String prefix2 = xmlPullParser.getPrefix();
                        if (prefix2 != null) {
                            stringBuffer.setLength(0);
                            stringBuffer.append(prefix2);
                            stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
                            stringBuffer.append(name2);
                        }
                        startElement(xmlPullParser.getNamespace(), name2, prefix2 != null ? name2 : stringBuffer.toString());
                    } else {
                        startElement(xmlPullParser.getNamespace(), xmlPullParser.getName(), xmlPullParser.getName());
                    }
                    i = xmlPullParser.next();
                    if (xmlPullParser.getDepth() <= depth) {
                        return;
                    }
                }
                return;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("start tag must be read before skiping subtree");
            stringBuffer2.append(xmlPullParser.getPositionDescription());
            throw new SAXException(stringBuffer2.toString());
        } catch (XmlPullParserException e) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("parsing error: ");
            stringBuffer3.append(e);
            SAXParseException sAXParseException = new SAXParseException(stringBuffer3.toString(), this, e);
            e.printStackTrace();
            this.errorHandler.fatalError(sAXParseException);
        }
    }

    /* access modifiers changed from: protected */
    public void startElement(String str, String str2, String str3) throws SAXException {
        this.contentHandler.startElement(str, str2, str3, this);
    }
}
