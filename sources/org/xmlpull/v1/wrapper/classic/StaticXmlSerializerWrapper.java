package org.xmlpull.v1.wrapper.classic;

import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.internal.ServerProtocol;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.wrapper.XmlPullParserWrapper;
import org.xmlpull.v1.wrapper.XmlPullWrapperFactory;
import org.xmlpull.v1.wrapper.XmlSerializerWrapper;

public class StaticXmlSerializerWrapper extends XmlSerializerDelegate implements XmlSerializerWrapper {
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/features.html#xmldecl-standalone";
    private static final boolean TRACE_SIZING = false;
    protected String currentNs;
    protected XmlPullParserWrapper fragmentParser;
    protected int[] namespaceDepth;
    protected int namespaceEnd = 0;
    protected String[] namespacePrefix;
    protected String[] namespaceUri;
    protected XmlPullWrapperFactory wf;

    public StaticXmlSerializerWrapper(XmlSerializer xmlSerializer, XmlPullWrapperFactory xmlPullWrapperFactory) {
        super(xmlSerializer);
        String[] strArr = new String[8];
        this.namespacePrefix = strArr;
        this.namespaceUri = new String[strArr.length];
        this.namespaceDepth = new int[strArr.length];
        this.wf = xmlPullWrapperFactory;
    }

    public String getCurrentNamespaceForElements() {
        return this.currentNs;
    }

    public String setCurrentNamespaceForElements(String str) {
        String str2 = this.currentNs;
        this.currentNs = str;
        return str2;
    }

    public XmlSerializerWrapper attribute(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.attribute((String) null, str, str2);
        return this;
    }

    public XmlSerializerWrapper startTag(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.startTag(this.currentNs, str);
        return this;
    }

    public XmlSerializerWrapper endTag(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        endTag(this.currentNs, str);
        return this;
    }

    public XmlSerializerWrapper element(String str, String str2) throws IOException, XmlPullParserException {
        return element(this.currentNs, str, str2);
    }

    public XmlSerializerWrapper element(String str, String str2, String str3) throws IOException, XmlPullParserException {
        if (str2 != null) {
            this.xs.startTag(str, str2);
            if (str3 == null) {
                this.xs.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            } else {
                this.xs.text(str3);
            }
            this.xs.endTag(str, str2);
            return this;
        }
        throw new XmlPullParserException("name for element can not be null");
    }

    private void ensureNamespacesCapacity() {
        int i = this.namespaceEnd;
        int i2 = i > 7 ? i * 2 : 8;
        String[] strArr = new String[i2];
        String[] strArr2 = new String[i2];
        int[] iArr = new int[i2];
        String[] strArr3 = this.namespacePrefix;
        if (strArr3 != null) {
            System.arraycopy(strArr3, 0, strArr, 0, this.namespaceEnd);
            System.arraycopy(this.namespaceUri, 0, strArr2, 0, this.namespaceEnd);
            System.arraycopy(this.namespaceDepth, 0, iArr, 0, this.namespaceEnd);
        }
        this.namespacePrefix = strArr;
        this.namespaceUri = strArr2;
        this.namespaceDepth = iArr;
    }

    public void setPrefix(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.setPrefix(str, str2);
        int depth = getDepth();
        int i = this.namespaceEnd - 1;
        while (i >= 0 && this.namespaceDepth[i] > depth) {
            this.namespaceEnd--;
            i--;
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            ensureNamespacesCapacity();
        }
        String[] strArr = this.namespacePrefix;
        int i2 = this.namespaceEnd;
        strArr[i2] = str;
        this.namespaceUri[i2] = str2;
        this.namespaceEnd = i2 + 1;
    }

    public void fragment(String str) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException {
        StringBuffer stringBuffer = new StringBuffer(str.length() + (this.namespaceEnd * 30));
        stringBuffer.append("<fragment");
        for (int i = this.namespaceEnd - 1; i >= 0; i--) {
            String str2 = this.namespacePrefix[i];
            int i2 = this.namespaceEnd - 1;
            while (true) {
                if (i2 <= i) {
                    stringBuffer.append(" xmlns");
                    if (str2.length() > 0) {
                        stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
                        stringBuffer.append(str2);
                    }
                    stringBuffer.append("='");
                    stringBuffer.append(escapeAttributeValue(this.namespaceUri[i]));
                    stringBuffer.append("'");
                } else if (str2.equals(this.namespacePrefix[i2])) {
                    break;
                } else {
                    i2--;
                }
            }
        }
        stringBuffer.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        stringBuffer.append(str);
        stringBuffer.append("</fragment>");
        if (this.fragmentParser == null) {
            this.fragmentParser = this.wf.newPullParserWrapper();
        }
        this.fragmentParser.setInput(new StringReader(stringBuffer.toString()));
        this.fragmentParser.nextTag();
        this.fragmentParser.require(2, (String) null, "fragment");
        while (true) {
            this.fragmentParser.nextToken();
            if (this.fragmentParser.getDepth() == 1 && this.fragmentParser.getEventType() == 3) {
                this.fragmentParser.require(3, (String) null, "fragment");
                return;
            }
            event(this.fragmentParser);
        }
    }

    public void event(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        switch (xmlPullParser.getEventType()) {
            case 0:
                startDocument(xmlPullParser.getInputEncoding(), (Boolean) xmlPullParser.getProperty(PROPERTY_XMLDECL_STANDALONE));
                return;
            case 1:
                endDocument();
                return;
            case 2:
                writeStartTag(xmlPullParser);
                return;
            case 3:
                endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                return;
            case 4:
                if (xmlPullParser.getDepth() > 0) {
                    text(xmlPullParser.getText());
                    return;
                } else {
                    ignorableWhitespace(xmlPullParser.getText());
                    return;
                }
            case 5:
                cdsect(xmlPullParser.getText());
                return;
            case 6:
                entityRef(xmlPullParser.getName());
                return;
            case 7:
                ignorableWhitespace(xmlPullParser.getText());
                return;
            case 8:
                processingInstruction(xmlPullParser.getText());
                return;
            case 9:
                comment(xmlPullParser.getText());
                return;
            case 10:
                docdecl(xmlPullParser.getText());
                return;
            default:
                return;
        }
    }

    private void writeStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (!xmlPullParser.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES)) {
            int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth());
            for (int namespaceCount2 = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth() - 1); namespaceCount2 < namespaceCount; namespaceCount2++) {
                setPrefix(xmlPullParser.getNamespacePrefix(namespaceCount2), xmlPullParser.getNamespaceUri(namespaceCount2));
            }
        }
        startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
        }
    }

    public String escapeAttributeValue(String str) {
        int indexOf = str.indexOf(60);
        int indexOf2 = str.indexOf(38);
        int indexOf3 = str.indexOf(34);
        int indexOf4 = str.indexOf(39);
        if (indexOf == -1 && indexOf2 == -1 && indexOf3 == -1 && indexOf4 == -1) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 10);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                stringBuffer.append("&quot;");
            } else if (charAt == '<') {
                stringBuffer.append("&lt;");
            } else if (charAt == '&') {
                stringBuffer.append("&amp;");
            } else if (charAt != '\'') {
                stringBuffer.append(charAt);
            } else {
                stringBuffer.append("&apos;");
            }
        }
        return stringBuffer.toString();
    }

    public String escapeText(String str) {
        int indexOf = str.indexOf(60);
        int indexOf2 = str.indexOf(38);
        if (indexOf == -1 && indexOf2 == -1) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 10);
        int i = 0;
        while (true) {
            if (indexOf == -1 && indexOf2 == -1) {
                stringBuffer.append(str.substring(i));
                return stringBuffer.toString();
            } else if (indexOf == -1 || !(indexOf == -1 || indexOf2 == -1 || indexOf2 >= indexOf)) {
                if (i < indexOf2) {
                    stringBuffer.append(str.substring(i, indexOf2));
                }
                stringBuffer.append("&amp;");
                i = indexOf2 + 1;
                indexOf2 = str.indexOf(38, i);
            } else if (indexOf2 == -1 || !(indexOf == -1 || indexOf2 == -1 || indexOf >= indexOf2)) {
                if (i < indexOf) {
                    stringBuffer.append(str.substring(i, indexOf));
                }
                stringBuffer.append("&lt;");
                i = indexOf + 1;
                indexOf = str.indexOf(60, i);
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("wrong state posLt=");
        stringBuffer2.append(indexOf);
        stringBuffer2.append(" posAmp=");
        stringBuffer2.append(indexOf2);
        stringBuffer2.append(" for ");
        stringBuffer2.append(str);
        throw new IllegalStateException(stringBuffer2.toString());
    }

    public void writeDouble(double d) throws XmlPullParserException, IOException, IllegalArgumentException {
        if (d == Double.POSITIVE_INFINITY) {
            this.xs.text("INF");
        } else if (d == Double.NEGATIVE_INFINITY) {
            this.xs.text("-INF");
        } else {
            this.xs.text(Double.toString(d));
        }
    }

    public void writeFloat(float f) throws XmlPullParserException, IOException, IllegalArgumentException {
        if (f == Float.POSITIVE_INFINITY) {
            this.xs.text("INF");
        } else if (f == Float.NEGATIVE_INFINITY) {
            this.xs.text("-INF");
        } else {
            this.xs.text(Float.toString(f));
        }
    }

    public void writeInt(int i) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.xs.text(Integer.toString(i));
    }

    public void writeString(String str) throws XmlPullParserException, IOException, IllegalArgumentException {
        if (str != null) {
            this.xs.text(str);
            return;
        }
        throw new IllegalArgumentException("null string can not be written");
    }

    public void writeDoubleElement(String str, String str2, double d) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.xs.startTag(str, str2);
        writeDouble(d);
        this.xs.endTag(str, str2);
    }

    public void writeFloatElement(String str, String str2, float f) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.xs.startTag(str, str2);
        writeFloat(f);
        this.xs.endTag(str, str2);
    }

    public void writeIntElement(String str, String str2, int i) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.xs.startTag(str, str2);
        writeInt(i);
        this.xs.endTag(str, str2);
    }

    public void wiriteStringElement(String str, String str2, String str3) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.xs.startTag(str, str2);
        writeString(str3);
        this.xs.endTag(str, str2);
    }
}
