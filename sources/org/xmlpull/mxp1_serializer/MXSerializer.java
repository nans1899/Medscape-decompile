package org.xmlpull.mxp1_serializer;

import com.appboy.Constants;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.xml.XMLConstants;
import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlSerializer;

public class MXSerializer implements XmlSerializer {
    private static final boolean TRACE_SIZING = false;
    protected static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    protected static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    protected static final String[] precomputedPrefixes = new String[32];
    protected final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
    protected final String FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE = "http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe";
    protected final String PROPERTY_SERIALIZER_INDENTATION = "http://xmlpull.org/v1/doc/properties.html#serializer-indentation";
    protected final String PROPERTY_SERIALIZER_LINE_SEPARATOR = "http://xmlpull.org/v1/doc/properties.html#serializer-line-separator";
    protected boolean attributeUseApostrophe;
    protected int autoDeclaredPrefixes;
    private boolean checkNamesInterned;
    protected int depth = 0;
    protected boolean doIndent;
    protected String[] elName;
    protected String[] elNamespace;
    protected int[] elNamespaceCount;
    protected boolean finished;
    protected char[] indentationBuf;
    protected int indentationJump;
    protected String indentationString = null;
    protected String lineSeparator = IOUtils.LINE_SEPARATOR_UNIX;
    protected int maxIndentLevel;
    protected boolean namesInterned;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected String[] namespaceUri;
    protected int offsetNewLine;
    protected Writer out;
    protected boolean pastRoot;
    protected boolean seenTag;
    protected boolean setPrefixCalled;
    protected boolean startTagIncomplete;
    protected boolean writeIndentation;
    protected boolean writeLineSepartor;

    public MXSerializer() {
        String[] strArr = new String[2];
        this.elNamespace = strArr;
        this.elName = new String[strArr.length];
        this.elNamespaceCount = new int[strArr.length];
        this.namespaceEnd = 0;
        String[] strArr2 = new String[8];
        this.namespacePrefix = strArr2;
        this.namespaceUri = new String[strArr2.length];
        this.checkNamesInterned = false;
    }

    static {
        int i = 0;
        while (true) {
            String[] strArr = precomputedPrefixes;
            if (i < strArr.length) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID);
                stringBuffer.append(i);
                strArr[i] = stringBuffer.toString().intern();
                i++;
            } else {
                return;
            }
        }
    }

    private void checkInterning(String str) {
        if (this.namesInterned && str != str.intern()) {
            throw new IllegalArgumentException("all names passed as arguments must be internedwhen NAMES INTERNED feature is enabled");
        }
    }

    /* access modifiers changed from: protected */
    public void reset() {
        this.out = null;
        this.autoDeclaredPrefixes = 0;
        this.depth = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.elNamespaceCount;
            if (i >= iArr.length) {
                this.namespaceEnd = 0;
                String[] strArr = this.namespacePrefix;
                strArr[0] = XMLConstants.XMLNS_ATTRIBUTE;
                String[] strArr2 = this.namespaceUri;
                strArr2[0] = "http://www.w3.org/2000/xmlns/";
                int i2 = 0 + 1;
                this.namespaceEnd = i2;
                strArr[i2] = "xml";
                strArr2[i2] = "http://www.w3.org/XML/1998/namespace";
                this.namespaceEnd = i2 + 1;
                this.finished = false;
                this.pastRoot = false;
                this.setPrefixCalled = false;
                this.startTagIncomplete = false;
                this.seenTag = false;
                return;
            }
            this.elName[i] = null;
            this.elNamespace[i] = null;
            iArr[i] = 2;
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void ensureElementsCapacity() {
        int length = this.elName.length;
        int i = this.depth;
        int i2 = (i >= 7 ? i * 2 : 8) + 2;
        boolean z = length > 0;
        String[] strArr = new String[i2];
        if (z) {
            System.arraycopy(this.elName, 0, strArr, 0, length);
        }
        this.elName = strArr;
        String[] strArr2 = new String[i2];
        if (z) {
            System.arraycopy(this.elNamespace, 0, strArr2, 0, length);
        }
        this.elNamespace = strArr2;
        int[] iArr = new int[i2];
        if (z) {
            System.arraycopy(this.elNamespaceCount, 0, iArr, 0, length);
        } else {
            iArr[0] = 0;
        }
        this.elNamespaceCount = iArr;
    }

    /* access modifiers changed from: protected */
    public void ensureNamespacesCapacity() {
        int i = this.namespaceEnd;
        int i2 = i > 7 ? i * 2 : 8;
        String[] strArr = new String[i2];
        String[] strArr2 = new String[i2];
        String[] strArr3 = this.namespacePrefix;
        if (strArr3 != null) {
            System.arraycopy(strArr3, 0, strArr, 0, this.namespaceEnd);
            System.arraycopy(this.namespaceUri, 0, strArr2, 0, this.namespaceEnd);
        }
        this.namespacePrefix = strArr;
        this.namespaceUri = strArr2;
    }

    public void setFeature(String str, boolean z) throws IllegalArgumentException, IllegalStateException {
        if (str == null) {
            throw new IllegalArgumentException("feature name can not be null");
        } else if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(str)) {
            this.namesInterned = z;
        } else if ("http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe".equals(str)) {
            this.attributeUseApostrophe = z;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("unsupported feature ");
            stringBuffer.append(str);
            throw new IllegalStateException(stringBuffer.toString());
        }
    }

    public boolean getFeature(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("feature name can not be null");
        } else if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(str)) {
            return this.namesInterned;
        } else {
            if ("http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe".equals(str)) {
                return this.attributeUseApostrophe;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void rebuildIndentationBuf() {
        int i;
        int i2;
        if (this.doIndent) {
            this.offsetNewLine = 0;
            if (this.writeLineSepartor) {
                int length = this.lineSeparator.length();
                this.offsetNewLine = length;
                i = length + 0;
            } else {
                i = 0;
            }
            this.maxIndentLevel = 0;
            if (this.writeIndentation) {
                int length2 = this.indentationString.length();
                this.indentationJump = length2;
                int i3 = 65 / length2;
                this.maxIndentLevel = i3;
                i += i3 * length2;
            }
            char[] cArr = this.indentationBuf;
            if (cArr == null || cArr.length < i) {
                this.indentationBuf = new char[(i + 8)];
            }
            if (this.writeLineSepartor) {
                int i4 = 0;
                i2 = 0;
                while (i4 < this.lineSeparator.length()) {
                    this.indentationBuf[i2] = this.lineSeparator.charAt(i4);
                    i4++;
                    i2++;
                }
            } else {
                i2 = 0;
            }
            if (this.writeIndentation) {
                for (int i5 = 0; i5 < this.maxIndentLevel; i5++) {
                    int i6 = 0;
                    while (i6 < this.indentationString.length()) {
                        this.indentationBuf[i2] = this.indentationString.charAt(i6);
                        i6++;
                        i2++;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void writeIndent() throws IOException {
        int i = this.writeLineSepartor ? 0 : this.offsetNewLine;
        int i2 = this.depth;
        int i3 = this.maxIndentLevel;
        if (i2 > i3) {
            i2 = i3;
        }
        this.out.write(this.indentationBuf, i, (i2 * this.indentationJump) + this.offsetNewLine);
    }

    public void setProperty(String str, Object obj) throws IllegalArgumentException, IllegalStateException {
        if (str != null) {
            if ("http://xmlpull.org/v1/doc/properties.html#serializer-indentation".equals(str)) {
                this.indentationString = (String) obj;
            } else if ("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator".equals(str)) {
                this.lineSeparator = (String) obj;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("unsupported property ");
                stringBuffer.append(str);
                throw new IllegalStateException(stringBuffer.toString());
            }
            String str2 = this.lineSeparator;
            boolean z = true;
            this.writeLineSepartor = str2 != null && str2.length() > 0;
            String str3 = this.indentationString;
            boolean z2 = str3 != null && str3.length() > 0;
            this.writeIndentation = z2;
            if (this.indentationString == null || (!this.writeLineSepartor && !z2)) {
                z = false;
            }
            this.doIndent = z;
            rebuildIndentationBuf();
            this.seenTag = false;
            return;
        }
        throw new IllegalArgumentException("property name can not be null");
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("property name can not be null");
        } else if ("http://xmlpull.org/v1/doc/properties.html#serializer-indentation".equals(str)) {
            return this.indentationString;
        } else {
            if ("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator".equals(str)) {
                return this.lineSeparator;
            }
            return null;
        }
    }

    public void setOutput(Writer writer) {
        reset();
        this.out = writer;
    }

    public void setOutput(OutputStream outputStream, String str) throws IOException {
        if (outputStream != null) {
            reset();
            if (str != null) {
                this.out = new OutputStreamWriter(outputStream, str);
            } else {
                this.out = new OutputStreamWriter(outputStream);
            }
        } else {
            throw new IllegalArgumentException("output stream can not be null");
        }
    }

    public void startDocument(String str, Boolean bool) throws IOException {
        this.out.write("<?xml version=\"1.0\"");
        if (str != null) {
            this.out.write(" encoding='");
            this.out.write(str);
            this.out.write(39);
        }
        if (bool != null) {
            if (bool.booleanValue()) {
                this.out.write(" standalone='yes'");
            } else {
                this.out.write(" standalone='no'");
            }
        }
        this.out.write("?>");
    }

    public void endDocument() throws IOException {
        while (true) {
            int i = this.depth;
            if (i <= 0) {
                this.startTagIncomplete = true;
                this.pastRoot = true;
                this.finished = true;
                this.out.flush();
                return;
            }
            endTag(this.elNamespace[i], this.elName[i]);
        }
    }

    public void setPrefix(String str, String str2) throws IOException {
        if (this.startTagIncomplete) {
            closeStartTag();
        }
        if (str == null) {
            str = "";
        }
        if (!this.namesInterned) {
            str = str.intern();
        } else if (this.checkNamesInterned) {
            checkInterning(str);
        } else if (str == null) {
            throw new IllegalArgumentException("prefix must be not null");
        }
        int i = this.elNamespaceCount[this.depth];
        while (i < this.namespaceEnd) {
            if (str != this.namespacePrefix[i]) {
                i++;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("duplicated prefix ");
                stringBuffer.append(printable(str));
                throw new IllegalStateException(stringBuffer.toString());
            }
        }
        if (!this.namesInterned) {
            str2 = str2.intern();
        } else if (this.checkNamesInterned) {
            checkInterning(str2);
        } else if (str2 == null) {
            throw new IllegalArgumentException("namespace must be not null");
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            ensureNamespacesCapacity();
        }
        String[] strArr = this.namespacePrefix;
        int i2 = this.namespaceEnd;
        strArr[i2] = str;
        this.namespaceUri[i2] = str2;
        this.namespaceEnd = i2 + 1;
        this.setPrefixCalled = true;
    }

    /* access modifiers changed from: protected */
    public String lookupOrDeclarePrefix(String str) {
        return getPrefix(str, true);
    }

    public String getPrefix(String str, boolean z) {
        if (!this.namesInterned) {
            str = str.intern();
        } else if (this.checkNamesInterned) {
            checkInterning(str);
        } else if (str == null) {
            throw new IllegalArgumentException("namespace must be not null");
        }
        for (int i = this.namespaceEnd - 1; i >= 0; i--) {
            if (str == this.namespaceUri[i]) {
                String str2 = this.namespacePrefix[i];
                for (int i2 = this.namespaceEnd - 1; i2 > i; i2--) {
                    String str3 = this.namespacePrefix[i2];
                }
                return str2;
            }
        }
        if (!z) {
            return null;
        }
        return generatePrefix(str);
    }

    private String generatePrefix(String str) {
        String str2;
        int i = this.autoDeclaredPrefixes + 1;
        this.autoDeclaredPrefixes = i;
        String[] strArr = precomputedPrefixes;
        if (i < strArr.length) {
            str2 = strArr[i];
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID);
            stringBuffer.append(this.autoDeclaredPrefixes);
            str2 = stringBuffer.toString().intern();
        }
        for (int i2 = this.namespaceEnd - 1; i2 >= 0; i2--) {
            String str3 = this.namespacePrefix[i2];
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            ensureNamespacesCapacity();
        }
        String[] strArr2 = this.namespacePrefix;
        int i3 = this.namespaceEnd;
        strArr2[i3] = str2;
        this.namespaceUri[i3] = str;
        this.namespaceEnd = i3 + 1;
        return str2;
    }

    public int getDepth() {
        return this.depth;
    }

    public String getNamespace() {
        return this.elNamespace[this.depth];
    }

    public String getName() {
        return this.elName[this.depth];
    }

    public XmlSerializer startTag(String str, String str2) throws IOException {
        if (this.startTagIncomplete) {
            closeStartTag();
        }
        if (this.doIndent && this.depth > 0 && this.seenTag) {
            writeIndent();
        }
        this.seenTag = true;
        this.setPrefixCalled = false;
        this.startTagIncomplete = true;
        int i = this.depth + 1;
        this.depth = i;
        if (i + 1 >= this.elName.length) {
            ensureElementsCapacity();
        }
        if (this.checkNamesInterned && this.namesInterned) {
            checkInterning(str);
        }
        this.elNamespace[this.depth] = (this.namesInterned || str == null) ? str : str.intern();
        if (this.checkNamesInterned && this.namesInterned) {
            checkInterning(str2);
        }
        this.elName[this.depth] = (this.namesInterned || str2 == null) ? str2 : str2.intern();
        this.out.write(60);
        if (str != null) {
            if (str.length() <= 0) {
                int i2 = this.namespaceEnd - 1;
                while (true) {
                    if (i2 < 0) {
                        break;
                    } else if (this.namespacePrefix[i2] == "") {
                        String str3 = this.namespaceUri[i2];
                        if (str3 == null) {
                            setPrefix("", "");
                        } else if (str3.length() > 0) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("start tag can not be written in empty default namespace as default namespace is currently bound to '");
                            stringBuffer.append(str3);
                            stringBuffer.append("'");
                            throw new IllegalStateException(stringBuffer.toString());
                        }
                    } else {
                        i2--;
                    }
                }
            } else {
                String lookupOrDeclarePrefix = lookupOrDeclarePrefix(str);
                if (lookupOrDeclarePrefix.length() > 0) {
                    this.out.write(lookupOrDeclarePrefix);
                    this.out.write(58);
                }
            }
        }
        this.out.write(str2);
        return this;
    }

    public XmlSerializer attribute(String str, String str2, String str3) throws IOException {
        if (this.startTagIncomplete) {
            this.out.write(32);
            if (str != null && str.length() > 0) {
                if (!this.namesInterned) {
                    str = str.intern();
                } else if (this.checkNamesInterned) {
                    checkInterning(str);
                }
                String lookupOrDeclarePrefix = lookupOrDeclarePrefix(str);
                if (lookupOrDeclarePrefix.length() == 0) {
                    lookupOrDeclarePrefix = generatePrefix(str);
                }
                this.out.write(lookupOrDeclarePrefix);
                this.out.write(58);
            }
            this.out.write(str2);
            this.out.write(61);
            int i = 39;
            this.out.write(this.attributeUseApostrophe ? 39 : 34);
            writeAttributeValue(str3, this.out);
            Writer writer = this.out;
            if (!this.attributeUseApostrophe) {
                i = 34;
            }
            writer.write(i);
            return this;
        }
        throw new IllegalArgumentException("startTag() must be called before attribute()");
    }

    /* access modifiers changed from: protected */
    public void closeStartTag() throws IOException {
        if (this.finished) {
            throw new IllegalArgumentException("trying to write past already finished output");
        } else if (this.setPrefixCalled) {
            throw new IllegalArgumentException("startTag() must be called immediately after setPrefix()");
        } else if (this.startTagIncomplete) {
            writeNamespaceDeclarations();
            this.out.write(62);
            this.elNamespaceCount[this.depth] = this.namespaceEnd;
            this.startTagIncomplete = false;
        } else {
            throw new IllegalArgumentException("trying to close start tag that is not opened");
        }
    }

    private void writeNamespaceDeclarations() throws IOException {
        for (int i = this.elNamespaceCount[this.depth - 1]; i < this.namespaceEnd; i++) {
            if (this.namespacePrefix[i] != "") {
                this.out.write(" xmlns:");
                this.out.write(this.namespacePrefix[i]);
                this.out.write(61);
            } else {
                this.out.write(" xmlns=");
            }
            int i2 = 39;
            this.out.write(this.attributeUseApostrophe ? 39 : 34);
            writeAttributeValue(this.namespaceUri[i], this.out);
            Writer writer = this.out;
            if (!this.attributeUseApostrophe) {
                i2 = 34;
            }
            writer.write(i2);
        }
    }

    public XmlSerializer endTag(String str, String str2) throws IOException {
        if (str != null) {
            if (!this.namesInterned) {
                str = str.intern();
            } else if (this.checkNamesInterned) {
                checkInterning(str);
            }
        }
        if (str != this.elNamespace[this.depth]) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("expected namespace ");
            stringBuffer.append(printable(this.elNamespace[this.depth]));
            stringBuffer.append(" and not ");
            stringBuffer.append(printable(str));
            throw new IllegalArgumentException(stringBuffer.toString());
        } else if (str2 != null) {
            if (this.checkNamesInterned && this.namesInterned) {
                checkInterning(str2);
            }
            if ((this.namesInterned || str2.equals(this.elName[this.depth])) && (!this.namesInterned || str2 == this.elName[this.depth])) {
                if (this.startTagIncomplete) {
                    writeNamespaceDeclarations();
                    this.out.write(" />");
                    this.depth--;
                } else {
                    this.depth--;
                    if (this.doIndent && this.seenTag) {
                        writeIndent();
                    }
                    this.out.write("</");
                    if (str != null && str.length() > 0) {
                        String lookupOrDeclarePrefix = lookupOrDeclarePrefix(str);
                        if (lookupOrDeclarePrefix.length() > 0) {
                            this.out.write(lookupOrDeclarePrefix);
                            this.out.write(58);
                        }
                    }
                    this.out.write(str2);
                    this.out.write(62);
                }
                this.namespaceEnd = this.elNamespaceCount[this.depth];
                this.startTagIncomplete = false;
                this.seenTag = true;
                return this;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("expected element name ");
            stringBuffer2.append(printable(this.elName[this.depth]));
            stringBuffer2.append(" and not ");
            stringBuffer2.append(printable(str2));
            throw new IllegalArgumentException(stringBuffer2.toString());
        } else {
            throw new IllegalArgumentException("end tag name can not be null");
        }
    }

    public XmlSerializer text(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        writeElementContent(str, this.out);
        return this;
    }

    public XmlSerializer text(char[] cArr, int i, int i2) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        writeElementContent(cArr, i, i2, this.out);
        return this;
    }

    public void cdsect(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<![CDATA[");
        this.out.write(str);
        this.out.write("]]>");
    }

    public void entityRef(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write(38);
        this.out.write(str);
        this.out.write(59);
    }

    public void processingInstruction(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<?");
        this.out.write(str);
        this.out.write("?>");
    }

    public void comment(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<!--");
        this.out.write(str);
        this.out.write("-->");
    }

    public void docdecl(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<!DOCTYPE");
        this.out.write(str);
        this.out.write(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
    }

    public void ignorableWhitespace(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write(str);
    }

    public void flush() throws IOException {
        if (this.startTagIncomplete) {
            closeStartTag();
        }
        this.out.flush();
    }

    /* access modifiers changed from: protected */
    public void writeAttributeValue(String str, Writer writer) throws IOException {
        int i = this.attributeUseApostrophe ? 39 : 34;
        String str2 = this.attributeUseApostrophe ? "&apos;" : "&quot;";
        int indexOf = str.indexOf(60);
        int indexOf2 = str.indexOf(38);
        int indexOf3 = str.indexOf(i);
        int i2 = 0;
        while (true) {
            if (indexOf == -1 && indexOf2 == -1 && indexOf3 == -1) {
                if (i2 > 0) {
                    writer.write(str.substring(i2));
                    return;
                } else {
                    writer.write(str);
                    return;
                }
            } else if (indexOf3 != -1 && ((indexOf2 == -1 || (indexOf2 != -1 && indexOf3 < indexOf2)) && (indexOf == -1 || (indexOf != -1 && indexOf3 < indexOf)))) {
                if (i2 < indexOf3) {
                    writer.write(str.substring(i2, indexOf3));
                }
                writer.write(str2);
                i2 = indexOf3 + 1;
                indexOf3 = str.indexOf(i, i2);
            } else if (indexOf2 != -1 && ((indexOf3 == -1 || (indexOf3 != -1 && indexOf2 < indexOf3)) && (indexOf == -1 || (indexOf != -1 && indexOf2 < indexOf)))) {
                if (i2 < indexOf2) {
                    writer.write(str.substring(i2, indexOf2));
                }
                writer.write("&amp;");
                i2 = indexOf2 + 1;
                indexOf2 = str.indexOf(38, i2);
            } else if (indexOf == -1 || ((indexOf3 != -1 && (indexOf3 == -1 || indexOf >= indexOf3)) || (indexOf2 != -1 && (indexOf2 == -1 || indexOf >= indexOf2)))) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("wrong state #1 posLt=");
                stringBuffer.append(indexOf);
                stringBuffer.append(" posAmp=");
                stringBuffer.append(indexOf2);
                stringBuffer.append(" posQuot=");
                stringBuffer.append(indexOf3);
                stringBuffer.append(" for ");
                stringBuffer.append(str);
            } else {
                if (i2 < indexOf) {
                    writer.write(str.substring(i2, indexOf));
                }
                writer.write("&lt;");
                i2 = indexOf + 1;
                indexOf = str.indexOf(60, i2);
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("wrong state #1 posLt=");
        stringBuffer2.append(indexOf);
        stringBuffer2.append(" posAmp=");
        stringBuffer2.append(indexOf2);
        stringBuffer2.append(" posQuot=");
        stringBuffer2.append(indexOf3);
        stringBuffer2.append(" for ");
        stringBuffer2.append(str);
        throw new IllegalStateException(stringBuffer2.toString());
    }

    /* access modifiers changed from: protected */
    public void writeElementContent(String str, Writer writer) throws IOException {
        int indexOf = str.indexOf(60);
        int indexOf2 = str.indexOf(38);
        int i = 0;
        while (true) {
            if (indexOf == -1 && indexOf2 == -1) {
                writer.write(str.substring(i));
                return;
            } else if (indexOf == -1 || !(indexOf == -1 || indexOf2 == -1 || indexOf2 >= indexOf)) {
                if (i < indexOf2) {
                    writer.write(str.substring(i, indexOf2));
                }
                writer.write("&amp;");
                i = indexOf2 + 1;
                indexOf2 = str.indexOf(38, i);
            } else if (indexOf2 == -1 || !(indexOf == -1 || indexOf2 == -1 || indexOf >= indexOf2)) {
                if (i < indexOf) {
                    writer.write(str.substring(i, indexOf));
                }
                writer.write("&lt;");
                i = indexOf + 1;
                indexOf = str.indexOf(60, i);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("wrong state posLt=");
        stringBuffer.append(indexOf);
        stringBuffer.append(" posAmp=");
        stringBuffer.append(indexOf2);
        stringBuffer.append(" for ");
        stringBuffer.append(str);
        throw new IllegalStateException(stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public void writeElementContent(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3 = i2 + i;
        int i4 = i;
        while (i < i3) {
            char c = cArr[i];
            if (c == '&') {
                if (i > i4) {
                    writer.write(cArr, i4, i - i4);
                }
                writer.write("&amp;");
            } else if (c != '<') {
                i++;
            } else {
                if (i > i4) {
                    writer.write(cArr, i4, i - i4);
                }
                writer.write("&lt;");
            }
            i4 = i + 1;
            i++;
        }
        if (i3 > i4) {
            writer.write(cArr, i4, i3 - i4);
        }
    }

    protected static final String printable(String str) {
        if (str == null) {
            return "null";
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 16);
        stringBuffer.append("'");
        for (int i = 0; i < str.length(); i++) {
            addPrintable(stringBuffer, str.charAt(i));
        }
        stringBuffer.append("'");
        return stringBuffer.toString();
    }

    protected static final String printable(char c) {
        StringBuffer stringBuffer = new StringBuffer();
        addPrintable(stringBuffer, c);
        return stringBuffer.toString();
    }

    private static void addPrintable(StringBuffer stringBuffer, char c) {
        if (c == 12) {
            stringBuffer.append("\\f");
        } else if (c == 13) {
            stringBuffer.append("\\r");
        } else if (c == '\"') {
            stringBuffer.append("\\\"");
        } else if (c == '\'') {
            stringBuffer.append("\\'");
        } else if (c != '\\') {
            switch (c) {
                case 8:
                    stringBuffer.append("\\b");
                    return;
                case 9:
                    stringBuffer.append("\\t");
                    return;
                case 10:
                    stringBuffer.append("\\n");
                    return;
                default:
                    if (c < ' ' || c > '~') {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("0000");
                        stringBuffer2.append(Integer.toString(c, 16));
                        String stringBuffer3 = stringBuffer2.toString();
                        StringBuffer stringBuffer4 = new StringBuffer();
                        stringBuffer4.append("\\u");
                        stringBuffer4.append(stringBuffer3.substring(stringBuffer3.length() - 4, stringBuffer3.length()));
                        stringBuffer.append(stringBuffer4.toString());
                        return;
                    }
                    stringBuffer.append(c);
                    return;
            }
        } else {
            stringBuffer.append("\\\\");
        }
    }
}
