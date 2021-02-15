package org.xmlpull.mxp1;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import javax.xml.XMLConstants;
import kotlin.text.Typography;
import net.bytebuddy.asm.Advice;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MXParser implements XmlPullParser {
    protected static final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
    protected static final String FEATURE_XML_ROUNDTRIP = "http://xmlpull.org/v1/doc/features.html#xml-roundtrip";
    protected static final int LOOKUP_MAX = 1024;
    protected static final char LOOKUP_MAX_CHAR = 'Ѐ';
    protected static final char[] NCODING = {'n', 'c', 'o', Advice.OffsetMapping.ForOrigin.Renderer.ForDescriptor.SYMBOL, 'i', 'n', 'g'};
    protected static final char[] NO = {'n', 'o'};
    protected static final String PROPERTY_XMLDECL_CONTENT = "http://xmlpull.org/v1/doc/properties.html#xmldecl-content";
    protected static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
    protected static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";
    protected static final int READ_CHUNK_SIZE = 8192;
    protected static final char[] TANDALONE = {Advice.OffsetMapping.ForOrigin.Renderer.ForTypeName.SYMBOL, 'a', 'n', Advice.OffsetMapping.ForOrigin.Renderer.ForDescriptor.SYMBOL, 'a', 'l', 'o', 'n', 'e'};
    private static final boolean TRACE_SIZING = false;
    protected static final char[] VERSION = {'v', 'e', Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName.SYMBOL, Advice.OffsetMapping.ForOrigin.Renderer.ForJavaSignature.SYMBOL, 'i', 'o', 'n'};
    protected static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    protected static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    protected static final char[] YES = {'y', 'e', Advice.OffsetMapping.ForOrigin.Renderer.ForJavaSignature.SYMBOL};
    protected static boolean[] lookupNameChar = new boolean[1024];
    protected static boolean[] lookupNameStartChar = new boolean[1024];
    protected boolean allStringsInterned;
    protected int attributeCount;
    protected String[] attributeName;
    protected int[] attributeNameHash;
    protected String[] attributePrefix;
    protected String[] attributeUri;
    protected String[] attributeValue;
    protected char[] buf;
    protected int bufAbsoluteStart;
    protected int bufEnd;
    protected int bufLoadFactor = 95;
    protected int bufSoftLimit;
    protected int bufStart;
    protected char[] charRefOneCharBuf;
    protected int columnNumber;
    protected int depth;
    protected String[] elName;
    protected int[] elNamespaceCount;
    protected String[] elPrefix;
    protected char[][] elRawName;
    protected int[] elRawNameEnd;
    protected int[] elRawNameLine;
    protected String[] elUri;
    protected boolean emptyElementTag;
    protected int entityEnd;
    protected String[] entityName;
    protected char[][] entityNameBuf;
    protected int[] entityNameHash;
    protected String entityRefName;
    protected String[] entityReplacement;
    protected char[][] entityReplacementBuf;
    protected int eventType;
    protected String inputEncoding;
    protected int lineNumber;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected int[] namespacePrefixHash;
    protected String[] namespaceUri;
    protected boolean pastEndTag;
    protected char[] pc;
    protected int pcEnd;
    protected int pcStart;
    protected int pos;
    protected int posEnd;
    protected int posStart;
    protected boolean preventBufferCompaction;
    protected boolean processNamespaces;
    protected boolean reachedEnd;
    protected Reader reader;
    protected boolean roundtripSupported;
    protected boolean seenAmpersand;
    protected boolean seenDocdecl;
    protected boolean seenEndTag;
    protected boolean seenMarkup;
    protected boolean seenRoot;
    protected boolean seenStartTag;
    protected String text;
    protected boolean tokenize;
    protected boolean usePC;
    protected String xmlDeclContent;
    protected Boolean xmlDeclStandalone;
    protected String xmlDeclVersion;

    /* access modifiers changed from: protected */
    public boolean isS(char c) {
        return c == ' ' || c == 10 || c == 13 || c == 9;
    }

    /* access modifiers changed from: protected */
    public void resetStringCache() {
    }

    /* access modifiers changed from: protected */
    public String newString(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public String newStringIntern(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2).intern();
    }

    /* access modifiers changed from: protected */
    public void ensureElementsCapacity() {
        String[] strArr = this.elName;
        int length = strArr != null ? strArr.length : 0;
        int i = this.depth;
        if (i + 1 >= length) {
            int i2 = (i >= 7 ? i * 2 : 8) + 2;
            boolean z = length > 0;
            String[] strArr2 = new String[i2];
            if (z) {
                System.arraycopy(this.elName, 0, strArr2, 0, length);
            }
            this.elName = strArr2;
            String[] strArr3 = new String[i2];
            if (z) {
                System.arraycopy(this.elPrefix, 0, strArr3, 0, length);
            }
            this.elPrefix = strArr3;
            String[] strArr4 = new String[i2];
            if (z) {
                System.arraycopy(this.elUri, 0, strArr4, 0, length);
            }
            this.elUri = strArr4;
            int[] iArr = new int[i2];
            if (z) {
                System.arraycopy(this.elNamespaceCount, 0, iArr, 0, length);
            } else {
                iArr[0] = 0;
            }
            this.elNamespaceCount = iArr;
            int[] iArr2 = new int[i2];
            if (z) {
                System.arraycopy(this.elRawNameEnd, 0, iArr2, 0, length);
            }
            this.elRawNameEnd = iArr2;
            int[] iArr3 = new int[i2];
            if (z) {
                System.arraycopy(this.elRawNameLine, 0, iArr3, 0, length);
            }
            this.elRawNameLine = iArr3;
            char[][] cArr = new char[i2][];
            if (z) {
                System.arraycopy(this.elRawName, 0, cArr, 0, length);
            }
            this.elRawName = cArr;
        }
    }

    /* access modifiers changed from: protected */
    public void ensureAttributesCapacity(int i) {
        String[] strArr = this.attributeName;
        int length = strArr != null ? strArr.length : 0;
        if (i >= length) {
            int i2 = i > 7 ? i * 2 : 8;
            boolean z = length > 0;
            String[] strArr2 = new String[i2];
            if (z) {
                System.arraycopy(this.attributeName, 0, strArr2, 0, length);
            }
            this.attributeName = strArr2;
            String[] strArr3 = new String[i2];
            if (z) {
                System.arraycopy(this.attributePrefix, 0, strArr3, 0, length);
            }
            this.attributePrefix = strArr3;
            String[] strArr4 = new String[i2];
            if (z) {
                System.arraycopy(this.attributeUri, 0, strArr4, 0, length);
            }
            this.attributeUri = strArr4;
            String[] strArr5 = new String[i2];
            if (z) {
                System.arraycopy(this.attributeValue, 0, strArr5, 0, length);
            }
            this.attributeValue = strArr5;
            if (!this.allStringsInterned) {
                int[] iArr = new int[i2];
                if (z) {
                    System.arraycopy(this.attributeNameHash, 0, iArr, 0, length);
                }
                this.attributeNameHash = iArr;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ensureNamespacesCapacity(int i) {
        String[] strArr = this.namespacePrefix;
        if (i >= (strArr != null ? strArr.length : 0)) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr2 = new String[i2];
            String[] strArr3 = new String[i2];
            String[] strArr4 = this.namespacePrefix;
            if (strArr4 != null) {
                System.arraycopy(strArr4, 0, strArr2, 0, this.namespaceEnd);
                System.arraycopy(this.namespaceUri, 0, strArr3, 0, this.namespaceEnd);
            }
            this.namespacePrefix = strArr2;
            this.namespaceUri = strArr3;
            if (!this.allStringsInterned) {
                int[] iArr = new int[i2];
                int[] iArr2 = this.namespacePrefixHash;
                if (iArr2 != null) {
                    System.arraycopy(iArr2, 0, iArr, 0, this.namespaceEnd);
                }
                this.namespacePrefixHash = iArr;
            }
        }
    }

    protected static final int fastHash(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = (cArr[i] << 7) + cArr[(i + i2) - 1];
        if (i2 > 16) {
            i3 = (i3 << 7) + cArr[(i2 / 4) + i];
        }
        return i2 > 8 ? (i3 << 7) + cArr[i + (i2 / 2)] : i3;
    }

    /* access modifiers changed from: protected */
    public void ensureEntityCapacity() {
        char[][] cArr = this.entityReplacementBuf;
        int length = cArr != null ? cArr.length : 0;
        int i = this.entityEnd;
        if (i >= length) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr = new String[i2];
            char[][] cArr2 = new char[i2][];
            String[] strArr2 = new String[i2];
            char[][] cArr3 = new char[i2][];
            String[] strArr3 = this.entityName;
            if (strArr3 != null) {
                System.arraycopy(strArr3, 0, strArr, 0, this.entityEnd);
                System.arraycopy(this.entityReplacementBuf, 0, strArr2, 0, this.entityEnd);
                System.arraycopy(this.entityReplacement, 0, strArr2, 0, this.entityEnd);
                System.arraycopy(this.entityReplacementBuf, 0, cArr3, 0, this.entityEnd);
            }
            this.entityName = strArr;
            this.entityNameBuf = cArr2;
            this.entityReplacement = strArr2;
            this.entityReplacementBuf = cArr3;
            if (!this.allStringsInterned) {
                int[] iArr = new int[i2];
                int[] iArr2 = this.entityNameHash;
                if (iArr2 != null) {
                    System.arraycopy(iArr2, 0, iArr, 0, this.entityEnd);
                }
                this.entityNameHash = iArr;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void reset() {
        this.lineNumber = 1;
        this.columnNumber = 0;
        this.seenRoot = false;
        this.reachedEnd = false;
        this.eventType = 0;
        this.emptyElementTag = false;
        this.depth = 0;
        this.attributeCount = 0;
        this.namespaceEnd = 0;
        this.entityEnd = 0;
        this.reader = null;
        this.inputEncoding = null;
        this.preventBufferCompaction = false;
        this.bufAbsoluteStart = 0;
        this.bufStart = 0;
        this.bufEnd = 0;
        this.posEnd = 0;
        this.posStart = 0;
        this.pos = 0;
        this.pcStart = 0;
        this.pcEnd = 0;
        this.usePC = false;
        this.seenStartTag = false;
        this.seenEndTag = false;
        this.pastEndTag = false;
        this.seenAmpersand = false;
        this.seenMarkup = false;
        this.seenDocdecl = false;
        this.xmlDeclVersion = null;
        this.xmlDeclStandalone = null;
        this.xmlDeclContent = null;
        resetStringCache();
    }

    public MXParser() {
        int i = 8192;
        char[] cArr = new char[(Runtime.getRuntime().freeMemory() > 1000000 ? 8192 : 256)];
        this.buf = cArr;
        this.bufSoftLimit = (this.bufLoadFactor * cArr.length) / 100;
        this.pc = new char[(Runtime.getRuntime().freeMemory() <= 1000000 ? 64 : i)];
        this.charRefOneCharBuf = new char[1];
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be null");
        } else if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            if (this.eventType == 0) {
                this.processNamespaces = z;
                return;
            }
            throw new XmlPullParserException("namespace processing feature can only be changed before parsing", this, (Throwable) null);
        } else if (FEATURE_NAMES_INTERNED.equals(str)) {
            if (z) {
                throw new XmlPullParserException("interning names in this implementation is not supported");
            }
        } else if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            if (z) {
                throw new XmlPullParserException("processing DOCDECL is not supported");
            }
        } else if (FEATURE_XML_ROUNDTRIP.equals(str)) {
            this.roundtripSupported = z;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("unsupporte feature ");
            stringBuffer.append(str);
            throw new XmlPullParserException(stringBuffer.toString());
        }
    }

    public boolean getFeature(String str) {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be nulll");
        } else if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            return this.processNamespaces;
        } else {
            if (!FEATURE_NAMES_INTERNED.equals(str) && !"http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str) && FEATURE_XML_ROUNDTRIP.equals(str)) {
                return this.roundtripSupported;
            }
            return false;
        }
    }

    public void setProperty(String str, Object obj) throws XmlPullParserException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("unsupported property: '");
        stringBuffer.append(str);
        stringBuffer.append("'");
        throw new XmlPullParserException(stringBuffer.toString());
    }

    public Object getProperty(String str) {
        if (str == null) {
            throw new IllegalArgumentException("property name should not be nulll");
        } else if (PROPERTY_XMLDECL_VERSION.equals(str)) {
            return this.xmlDeclVersion;
        } else {
            if (PROPERTY_XMLDECL_STANDALONE.equals(str)) {
                return this.xmlDeclStandalone;
            }
            if (PROPERTY_XMLDECL_CONTENT.equals(str)) {
                return this.xmlDeclContent;
            }
            return null;
        }
    }

    public void setInput(Reader reader2) throws XmlPullParserException {
        reset();
        this.reader = reader2;
    }

    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        InputStreamReader inputStreamReader;
        if (inputStream != null) {
            if (str == null) {
                inputStreamReader = new InputStreamReader(inputStream);
            } else if (str != null) {
                try {
                    inputStreamReader = new InputStreamReader(inputStream, str);
                } catch (UnsupportedEncodingException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("could not create reader for encoding ");
                    stringBuffer.append(str);
                    stringBuffer.append(" : ");
                    stringBuffer.append(e);
                    throw new XmlPullParserException(stringBuffer.toString(), this, e);
                }
            } else {
                inputStreamReader = new InputStreamReader(inputStream);
            }
            setInput(inputStreamReader);
            this.inputEncoding = str;
            return;
        }
        throw new IllegalArgumentException("input stream can not be null");
    }

    public String getInputEncoding() {
        return this.inputEncoding;
    }

    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        ensureEntityCapacity();
        this.entityName[this.entityEnd] = newString(str.toCharArray(), 0, str.length());
        this.entityNameBuf[this.entityEnd] = str.toCharArray();
        String[] strArr = this.entityReplacement;
        int i = this.entityEnd;
        strArr[i] = str2;
        this.entityReplacementBuf[i] = str2.toCharArray();
        if (!this.allStringsInterned) {
            int[] iArr = this.entityNameHash;
            int i2 = this.entityEnd;
            char[][] cArr = this.entityNameBuf;
            iArr[i2] = fastHash(cArr[i2], 0, cArr[i2].length);
        }
        this.entityEnd++;
    }

    public int getNamespaceCount(int i) throws XmlPullParserException {
        if (!this.processNamespaces || i == 0) {
            return 0;
        }
        if (i >= 0 && i <= this.depth) {
            return this.elNamespaceCount[i];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("napespace count mayt be for depth 0..");
        stringBuffer.append(this.depth);
        stringBuffer.append(" not ");
        stringBuffer.append(i);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public String getNamespacePrefix(int i) throws XmlPullParserException {
        if (i < this.namespaceEnd) {
            return this.namespacePrefix[i];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("position ");
        stringBuffer.append(i);
        stringBuffer.append(" exceeded number of available namespaces ");
        stringBuffer.append(this.namespaceEnd);
        throw new XmlPullParserException(stringBuffer.toString());
    }

    public String getNamespaceUri(int i) throws XmlPullParserException {
        if (i < this.namespaceEnd) {
            return this.namespaceUri[i];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("position ");
        stringBuffer.append(i);
        stringBuffer.append(" exceedded number of available namespaces ");
        stringBuffer.append(this.namespaceEnd);
        throw new XmlPullParserException(stringBuffer.toString());
    }

    public String getNamespace(String str) {
        if (str != null) {
            for (int i = this.namespaceEnd - 1; i >= 0; i--) {
                if (str.equals(this.namespacePrefix[i])) {
                    return this.namespaceUri[i];
                }
            }
            if ("xml".equals(str)) {
                return "http://www.w3.org/XML/1998/namespace";
            }
            if (XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
                return "http://www.w3.org/2000/xmlns/";
            }
            return null;
        }
        for (int i2 = this.namespaceEnd - 1; i2 >= 0; i2--) {
            if (this.namespacePrefix[i2] == null) {
                return this.namespaceUri[i2];
            }
        }
        return null;
    }

    public int getDepth() {
        return this.depth;
    }

    private static int findFragment(int i, char[] cArr, int i2, int i3) {
        if (i2 < i) {
            return i > i3 ? i3 : i;
        }
        if (i3 - i2 > 65) {
            i2 = i3 - 10;
        }
        int i4 = i2 + 1;
        while (true) {
            i4--;
            if (i4 > i && i3 - i4 <= 65 && (cArr[i4] != '<' || i2 - i4 <= 10)) {
            }
        }
        return i4;
    }

    public String getPositionDescription() {
        String str;
        int i = this.posStart;
        int i2 = this.pos;
        String str2 = null;
        if (i <= i2) {
            int findFragment = findFragment(0, this.buf, i, i2);
            if (findFragment < this.pos) {
                str2 = new String(this.buf, findFragment, this.pos - findFragment);
            }
            if (this.bufAbsoluteStart > 0 || findFragment > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("...");
                stringBuffer.append(str2);
                str2 = stringBuffer.toString();
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer2.append(XmlPullParser.TYPES[this.eventType]);
        if (str2 != null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(" seen ");
            stringBuffer3.append(printable(str2));
            stringBuffer3.append("...");
            str = stringBuffer3.toString();
        } else {
            str = "";
        }
        stringBuffer2.append(str);
        stringBuffer2.append(" @");
        stringBuffer2.append(getLineNumber());
        stringBuffer2.append(":");
        stringBuffer2.append(getColumnNumber());
        return stringBuffer2.toString();
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public boolean isWhitespace() throws XmlPullParserException {
        int i = this.eventType;
        if (i == 4 || i == 5) {
            if (this.usePC) {
                for (int i2 = this.pcStart; i2 < this.pcEnd; i2++) {
                    if (!isS(this.pc[i2])) {
                        return false;
                    }
                }
                return true;
            }
            for (int i3 = this.posStart; i3 < this.posEnd; i3++) {
                if (!isS(this.buf[i3])) {
                    return false;
                }
            }
            return true;
        } else if (i == 7) {
            return true;
        } else {
            throw new XmlPullParserException("no content available to check for whitespaces");
        }
    }

    public String getText() {
        int i = this.eventType;
        if (i == 0 || i == 1) {
            return null;
        }
        if (i == 6) {
            return this.text;
        }
        if (this.text == null) {
            if (!this.usePC || i == 2 || i == 3) {
                char[] cArr = this.buf;
                int i2 = this.posStart;
                this.text = new String(cArr, i2, this.posEnd - i2);
            } else {
                char[] cArr2 = this.pc;
                int i3 = this.pcStart;
                this.text = new String(cArr2, i3, this.pcEnd - i3);
            }
        }
        return this.text;
    }

    public char[] getTextCharacters(int[] iArr) {
        int i = this.eventType;
        if (i == 4) {
            if (this.usePC) {
                int i2 = this.pcStart;
                iArr[0] = i2;
                iArr[1] = this.pcEnd - i2;
                return this.pc;
            }
            int i3 = this.posStart;
            iArr[0] = i3;
            iArr[1] = this.posEnd - i3;
            return this.buf;
        } else if (i == 2 || i == 3 || i == 5 || i == 9 || i == 6 || i == 8 || i == 7 || i == 10) {
            int i4 = this.posStart;
            iArr[0] = i4;
            iArr[1] = this.posEnd - i4;
            return this.buf;
        } else if (i == 0 || i == 1) {
            iArr[1] = -1;
            iArr[0] = -1;
            return null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("unknown text eventType: ");
            stringBuffer.append(this.eventType);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public String getNamespace() {
        int i = this.eventType;
        if (i == 2) {
            if (this.processNamespaces) {
                return this.elUri[this.depth];
            }
            return "";
        } else if (i != 3) {
            return null;
        } else {
            if (this.processNamespaces) {
                return this.elUri[this.depth];
            }
            return "";
        }
    }

    public String getName() {
        int i = this.eventType;
        if (i == 2) {
            return this.elName[this.depth];
        }
        if (i == 3) {
            return this.elName[this.depth];
        }
        if (i != 6) {
            return null;
        }
        if (this.entityRefName == null) {
            char[] cArr = this.buf;
            int i2 = this.posStart;
            this.entityRefName = newString(cArr, i2, this.posEnd - i2);
        }
        return this.entityRefName;
    }

    public String getPrefix() {
        int i = this.eventType;
        if (i == 2) {
            return this.elPrefix[this.depth];
        }
        if (i == 3) {
            return this.elPrefix[this.depth];
        }
        return null;
    }

    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (this.eventType == 2) {
            return this.emptyElementTag;
        }
        throw new XmlPullParserException("parser must be on START_TAG to check for empty element", this, (Throwable) null);
    }

    public int getAttributeCount() {
        if (this.eventType != 2) {
            return -1;
        }
        return this.attributeCount;
    }

    public String getAttributeNamespace(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        } else if (!this.processNamespaces) {
            return "";
        } else {
            if (i >= 0 && i < this.attributeCount) {
                return this.attributeUri[i];
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("attribute position must be 0..");
            stringBuffer.append(this.attributeCount - 1);
            stringBuffer.append(" and not ");
            stringBuffer.append(i);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
    }

    public String getAttributeName(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        } else if (i >= 0 && i < this.attributeCount) {
            return this.attributeName[i];
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("attribute position must be 0..");
            stringBuffer.append(this.attributeCount - 1);
            stringBuffer.append(" and not ");
            stringBuffer.append(i);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
    }

    public String getAttributePrefix(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        } else if (!this.processNamespaces) {
            return null;
        } else {
            if (i >= 0 && i < this.attributeCount) {
                return this.attributePrefix[i];
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("attribute position must be 0..");
            stringBuffer.append(this.attributeCount - 1);
            stringBuffer.append(" and not ");
            stringBuffer.append(i);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
    }

    public String getAttributeType(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        } else if (i >= 0 && i < this.attributeCount) {
            return "CDATA";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("attribute position must be 0..");
            stringBuffer.append(this.attributeCount - 1);
            stringBuffer.append(" and not ");
            stringBuffer.append(i);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
    }

    public boolean isAttributeDefault(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        } else if (i >= 0 && i < this.attributeCount) {
            return false;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("attribute position must be 0..");
            stringBuffer.append(this.attributeCount - 1);
            stringBuffer.append(" and not ");
            stringBuffer.append(i);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
    }

    public String getAttributeValue(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        } else if (i >= 0 && i < this.attributeCount) {
            return this.attributeValue[i];
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("attribute position must be 0..");
            stringBuffer.append(this.attributeCount - 1);
            stringBuffer.append(" and not ");
            stringBuffer.append(i);
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
    }

    public String getAttributeValue(String str, String str2) {
        if (this.eventType != 2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("only START_TAG can have attributes");
            stringBuffer.append(getPositionDescription());
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        } else if (str2 != null) {
            int i = 0;
            if (this.processNamespaces) {
                if (str == null) {
                    str = "";
                }
                String str3 = str;
                while (i < this.attributeCount) {
                    String[] strArr = this.attributeUri;
                    if ((str3 == strArr[i] || str3.equals(strArr[i])) && str2.equals(this.attributeName[i])) {
                        return this.attributeValue[i];
                    }
                    i++;
                }
            } else {
                if (str != null && str.length() == 0) {
                    str = null;
                }
                if (str == null) {
                    while (i < this.attributeCount) {
                        if (str2.equals(this.attributeName[i])) {
                            return this.attributeValue[i];
                        }
                        i++;
                    }
                } else {
                    throw new IllegalArgumentException("when namespaces processing is disabled attribute namespace must be null");
                }
            }
            return null;
        } else {
            throw new IllegalArgumentException("attribute name can not be null");
        }
    }

    public int getEventType() throws XmlPullParserException {
        return this.eventType;
    }

    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        String str3;
        String str4;
        String str5;
        String str6;
        if (!this.processNamespaces && str != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("processing namespaces must be enabled on parser (or factory) to have possible namespaces delcared on elements");
            stringBuffer.append(" (postion:");
            stringBuffer.append(getPositionDescription());
            stringBuffer.append(")");
            throw new XmlPullParserException(stringBuffer.toString());
        } else if (i != getEventType() || ((str != null && !str.equals(getNamespace())) || (str2 != null && !str2.equals(getName())))) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("expected event ");
            stringBuffer2.append(XmlPullParser.TYPES[i]);
            String str7 = "";
            if (str2 != null) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(" with name '");
                stringBuffer3.append(str2);
                stringBuffer3.append("'");
                str3 = stringBuffer3.toString();
            } else {
                str3 = str7;
            }
            stringBuffer2.append(str3);
            String str8 = " and";
            stringBuffer2.append((str == null || str2 == null) ? str7 : str8);
            if (str != null) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append(" with namespace '");
                stringBuffer4.append(str);
                stringBuffer4.append("'");
                str4 = stringBuffer4.toString();
            } else {
                str4 = str7;
            }
            stringBuffer2.append(str4);
            stringBuffer2.append(" but got");
            if (i != getEventType()) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuffer5.append(XmlPullParser.TYPES[getEventType()]);
                str5 = stringBuffer5.toString();
            } else {
                str5 = str7;
            }
            stringBuffer2.append(str5);
            if (str2 == null || getName() == null || str2.equals(getName())) {
                str6 = str7;
            } else {
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append(" name '");
                stringBuffer6.append(getName());
                stringBuffer6.append("'");
                str6 = stringBuffer6.toString();
            }
            stringBuffer2.append(str6);
            if (str == null || str2 == null || getName() == null || str2.equals(getName()) || getNamespace() == null || str.equals(getNamespace())) {
                str8 = str7;
            }
            stringBuffer2.append(str8);
            if (!(str == null || getNamespace() == null || str.equals(getNamespace()))) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append(" namespace '");
                stringBuffer7.append(getNamespace());
                stringBuffer7.append("'");
                str7 = stringBuffer7.toString();
            }
            stringBuffer2.append(str7);
            stringBuffer2.append(" (postion:");
            stringBuffer2.append(getPositionDescription());
            stringBuffer2.append(")");
            throw new XmlPullParserException(stringBuffer2.toString());
        }
    }

    public String nextText() throws XmlPullParserException, IOException {
        if (getEventType() == 2) {
            int next = next();
            if (next == 4) {
                String text2 = getText();
                if (next() == 3) {
                    return text2;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("TEXT must be immediately followed by END_TAG and not ");
                stringBuffer.append(XmlPullParser.TYPES[getEventType()]);
                throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
            } else if (next == 3) {
                return "";
            } else {
                throw new XmlPullParserException("parser must be on START_TAG or TEXT to read text", this, (Throwable) null);
            }
        } else {
            throw new XmlPullParserException("parser must be on START_TAG to read next text", this, (Throwable) null);
        }
    }

    public int nextTag() throws XmlPullParserException, IOException {
        next();
        if (this.eventType == 4 && isWhitespace()) {
            next();
        }
        int i = this.eventType;
        if (i == 2 || i == 3) {
            return this.eventType;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("expected START_TAG or END_TAG not ");
        stringBuffer.append(XmlPullParser.TYPES[getEventType()]);
        throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
    }

    public int next() throws XmlPullParserException, IOException {
        this.tokenize = false;
        return nextImpl();
    }

    public int nextToken() throws XmlPullParserException, IOException {
        this.tokenize = true;
        return nextImpl();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int nextImpl() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r12 = this;
            r0 = 0
            r12.text = r0
            r1 = 0
            r12.pcStart = r1
            r12.pcEnd = r1
            r12.usePC = r1
            int r2 = r12.posEnd
            r12.bufStart = r2
            boolean r2 = r12.pastEndTag
            r3 = 1
            if (r2 == 0) goto L_0x0020
            r12.pastEndTag = r1
            int r2 = r12.depth
            int r2 = r2 - r3
            r12.depth = r2
            int[] r4 = r12.elNamespaceCount
            r2 = r4[r2]
            r12.namespaceEnd = r2
        L_0x0020:
            boolean r2 = r12.emptyElementTag
            if (r2 == 0) goto L_0x002c
            r12.emptyElementTag = r1
            r12.pastEndTag = r3
            r0 = 3
            r12.eventType = r0
            return r0
        L_0x002c:
            int r2 = r12.depth
            if (r2 <= 0) goto L_0x0261
            boolean r2 = r12.seenStartTag
            if (r2 == 0) goto L_0x003d
            r12.seenStartTag = r1
            int r0 = r12.parseStartTag()
            r12.eventType = r0
            return r0
        L_0x003d:
            boolean r2 = r12.seenEndTag
            if (r2 == 0) goto L_0x004a
            r12.seenEndTag = r1
            int r0 = r12.parseEndTag()
            r12.eventType = r0
            return r0
        L_0x004a:
            boolean r2 = r12.seenMarkup
            r4 = 38
            r5 = 60
            if (r2 == 0) goto L_0x0057
            r12.seenMarkup = r1
            r2 = 60
            goto L_0x0064
        L_0x0057:
            boolean r2 = r12.seenAmpersand
            if (r2 == 0) goto L_0x0060
            r12.seenAmpersand = r1
            r2 = 38
            goto L_0x0064
        L_0x0060:
            char r2 = r12.more()
        L_0x0064:
            int r6 = r12.pos
            int r6 = r6 - r3
            r12.posStart = r6
            r6 = 0
            r7 = 0
        L_0x006b:
            r8 = 4
            if (r2 != r5) goto L_0x013f
            if (r6 == 0) goto L_0x0079
            boolean r2 = r12.tokenize
            if (r2 == 0) goto L_0x0079
            r12.seenMarkup = r3
            r12.eventType = r8
            return r8
        L_0x0079:
            char r2 = r12.more()
            r9 = 47
            if (r2 != r9) goto L_0x0093
            boolean r0 = r12.tokenize
            if (r0 != 0) goto L_0x008c
            if (r6 == 0) goto L_0x008c
            r12.seenEndTag = r3
            r12.eventType = r8
            return r8
        L_0x008c:
            int r0 = r12.parseEndTag()
            r12.eventType = r0
            return r0
        L_0x0093:
            r9 = 33
            java.lang.String r10 = "unexpected character in markup "
            if (r2 != r9) goto L_0x00ef
            char r2 = r12.more()
            r8 = 45
            if (r2 != r8) goto L_0x00ba
            r12.parseComment()
            boolean r2 = r12.tokenize
            if (r2 == 0) goto L_0x00ad
            r0 = 9
            r12.eventType = r0
            return r0
        L_0x00ad:
            boolean r2 = r12.usePC
            if (r2 != 0) goto L_0x00b4
            if (r6 == 0) goto L_0x00b4
            goto L_0x0105
        L_0x00b4:
            int r2 = r12.pos
            r12.posStart = r2
            goto L_0x01b4
        L_0x00ba:
            r8 = 91
            if (r2 != r8) goto L_0x00d6
            r12.parseCDSect(r6)
            boolean r2 = r12.tokenize
            if (r2 == 0) goto L_0x00c9
            r0 = 5
            r12.eventType = r0
            return r0
        L_0x00c9:
            int r2 = r12.posStart
            int r8 = r12.posEnd
            int r8 = r8 - r2
            if (r8 <= 0) goto L_0x01b4
            boolean r2 = r12.usePC
            if (r2 != 0) goto L_0x01b4
            r6 = 1
            goto L_0x0105
        L_0x00d6:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r3.append(r10)
            java.lang.String r2 = r12.printable((char) r2)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2, r12, r0)
            throw r1
        L_0x00ef:
            r9 = 63
            if (r2 != r9) goto L_0x010e
            r12.parsePI()
            boolean r2 = r12.tokenize
            if (r2 == 0) goto L_0x00ff
            r0 = 8
            r12.eventType = r0
            return r0
        L_0x00ff:
            boolean r2 = r12.usePC
            if (r2 != 0) goto L_0x0108
            if (r6 == 0) goto L_0x0108
        L_0x0105:
            r7 = 1
            goto L_0x01b4
        L_0x0108:
            int r2 = r12.pos
            r12.posStart = r2
            goto L_0x01b4
        L_0x010e:
            boolean r1 = r12.isNameStartChar(r2)
            if (r1 == 0) goto L_0x0126
            boolean r0 = r12.tokenize
            if (r0 != 0) goto L_0x011f
            if (r6 == 0) goto L_0x011f
            r12.seenStartTag = r3
            r12.eventType = r8
            return r8
        L_0x011f:
            int r0 = r12.parseStartTag()
            r12.eventType = r0
            return r0
        L_0x0126:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r3.append(r10)
            java.lang.String r2 = r12.printable((char) r2)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2, r12, r0)
            throw r1
        L_0x013f:
            if (r2 != r4) goto L_0x01d3
            boolean r2 = r12.tokenize
            if (r2 == 0) goto L_0x014c
            if (r6 == 0) goto L_0x014c
            r12.seenAmpersand = r3
            r12.eventType = r8
            return r8
        L_0x014c:
            int r2 = r12.posStart
            int r8 = r12.bufAbsoluteStart
            int r2 = r2 + r8
            int r9 = r12.posEnd
            int r9 = r9 + r8
            char[] r8 = r12.parseEntityRef()
            boolean r10 = r12.tokenize
            if (r10 == 0) goto L_0x0160
            r0 = 6
            r12.eventType = r0
            return r0
        L_0x0160:
            if (r8 != 0) goto L_0x0195
            java.lang.String r1 = r12.entityRefName
            if (r1 != 0) goto L_0x0173
            char[] r1 = r12.buf
            int r2 = r12.posStart
            int r3 = r12.posEnd
            int r3 = r3 - r2
            java.lang.String r1 = r12.newString(r1, r2, r3)
            r12.entityRefName = r1
        L_0x0173:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "could not resolve entity named '"
            r2.append(r3)
            java.lang.String r3 = r12.entityRefName
            java.lang.String r3 = r12.printable((java.lang.String) r3)
            r2.append(r3)
            java.lang.String r3 = "'"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r12, r0)
            throw r1
        L_0x0195:
            int r10 = r12.bufAbsoluteStart
            int r2 = r2 - r10
            r12.posStart = r2
            int r9 = r9 - r10
            r12.posEnd = r9
            boolean r2 = r12.usePC
            if (r2 != 0) goto L_0x01ae
            if (r6 == 0) goto L_0x01a8
            r12.joinPC()
            r9 = 0
            goto L_0x01af
        L_0x01a8:
            r12.usePC = r3
            r12.pcEnd = r1
            r12.pcStart = r1
        L_0x01ae:
            r9 = r7
        L_0x01af:
            r2 = 0
        L_0x01b0:
            int r7 = r8.length
            if (r2 < r7) goto L_0x01ba
            r7 = r9
        L_0x01b4:
            char r2 = r12.more()
            goto L_0x006b
        L_0x01ba:
            int r7 = r12.pcEnd
            char[] r10 = r12.pc
            int r10 = r10.length
            if (r7 < r10) goto L_0x01c4
            r12.ensurePC(r7)
        L_0x01c4:
            char[] r7 = r12.pc
            int r10 = r12.pcEnd
            int r11 = r10 + 1
            r12.pcEnd = r11
            char r11 = r8[r2]
            r7[r10] = r11
            int r2 = r2 + 1
            goto L_0x01b0
        L_0x01d3:
            if (r7 == 0) goto L_0x01d9
            r12.joinPC()
            r7 = 0
        L_0x01d9:
            boolean r6 = r12.tokenize
            if (r6 == 0) goto L_0x01e4
            boolean r6 = r12.roundtripSupported
            if (r6 != 0) goto L_0x01e2
            goto L_0x01e4
        L_0x01e2:
            r6 = 0
            goto L_0x01e5
        L_0x01e4:
            r6 = 1
        L_0x01e5:
            r8 = 0
        L_0x01e6:
            if (r6 == 0) goto L_0x0251
            r9 = 13
            r10 = 10
            if (r2 != r9) goto L_0x021b
            int r2 = r12.pos
            int r2 = r2 - r3
            r12.posEnd = r2
            boolean r8 = r12.usePC
            if (r8 != 0) goto L_0x0205
            int r8 = r12.posStart
            if (r2 <= r8) goto L_0x01ff
            r12.joinPC()
            goto L_0x0205
        L_0x01ff:
            r12.usePC = r3
            r12.pcEnd = r1
            r12.pcStart = r1
        L_0x0205:
            int r2 = r12.pcEnd
            char[] r8 = r12.pc
            int r8 = r8.length
            if (r2 < r8) goto L_0x020f
            r12.ensurePC(r2)
        L_0x020f:
            char[] r2 = r12.pc
            int r8 = r12.pcEnd
            int r9 = r8 + 1
            r12.pcEnd = r9
            r2[r8] = r10
            r8 = 1
            goto L_0x0251
        L_0x021b:
            if (r2 != r10) goto L_0x0238
            if (r8 != 0) goto L_0x0250
            boolean r2 = r12.usePC
            if (r2 == 0) goto L_0x0250
            int r2 = r12.pcEnd
            char[] r8 = r12.pc
            int r8 = r8.length
            if (r2 < r8) goto L_0x022d
            r12.ensurePC(r2)
        L_0x022d:
            char[] r2 = r12.pc
            int r8 = r12.pcEnd
            int r9 = r8 + 1
            r12.pcEnd = r9
            r2[r8] = r10
            goto L_0x0250
        L_0x0238:
            boolean r8 = r12.usePC
            if (r8 == 0) goto L_0x0250
            int r8 = r12.pcEnd
            char[] r9 = r12.pc
            int r9 = r9.length
            if (r8 < r9) goto L_0x0246
            r12.ensurePC(r8)
        L_0x0246:
            char[] r8 = r12.pc
            int r9 = r12.pcEnd
            int r10 = r9 + 1
            r12.pcEnd = r10
            r8[r9] = r2
        L_0x0250:
            r8 = 0
        L_0x0251:
            char r2 = r12.more()
            if (r2 == r5) goto L_0x0259
            if (r2 != r4) goto L_0x01e6
        L_0x0259:
            int r6 = r12.pos
            int r6 = r6 - r3
            r12.posEnd = r6
            r6 = 1
            goto L_0x006b
        L_0x0261:
            boolean r0 = r12.seenRoot
            if (r0 == 0) goto L_0x026a
            int r0 = r12.parseEpilog()
            return r0
        L_0x026a:
            int r0 = r12.parseProlog()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.nextImpl():int");
    }

    /* access modifiers changed from: protected */
    public int parseProlog() throws XmlPullParserException, IOException {
        char c;
        if (this.seenMarkup) {
            c = this.buf[this.pos - 1];
        } else {
            c = more();
        }
        if (this.eventType == 0) {
            if (c == 65534) {
                throw new XmlPullParserException("first character in input was UNICODE noncharacter (0xFFFE)- input requires int swapping", this, (Throwable) null);
            } else if (c == 65279) {
                c = more();
            }
        }
        this.seenMarkup = false;
        this.posStart = this.pos - 1;
        boolean z = this.tokenize && !this.roundtripSupported;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            if (c == '<') {
                if (!z2 || !this.tokenize) {
                    char more = more();
                    if (more == '?') {
                        if (!parsePI()) {
                            this.posStart = this.pos;
                            z2 = false;
                        } else if (this.tokenize) {
                            this.eventType = 8;
                            return 8;
                        }
                    } else if (more == '!') {
                        char more2 = more();
                        if (more2 == 'D') {
                            if (!this.seenDocdecl) {
                                this.seenDocdecl = true;
                                parseDocdecl();
                                if (this.tokenize) {
                                    this.eventType = 10;
                                    return 10;
                                }
                            } else {
                                throw new XmlPullParserException("only one docdecl allowed in XML document", this, (Throwable) null);
                            }
                        } else if (more2 == '-') {
                            parseComment();
                            if (this.tokenize) {
                                this.eventType = 9;
                                return 9;
                            }
                        } else {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("unexpected markup <!");
                            stringBuffer.append(printable(more2));
                            throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
                        }
                    } else if (more == '/') {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("expected start tag name and not ");
                        stringBuffer2.append(printable(more));
                        throw new XmlPullParserException(stringBuffer2.toString(), this, (Throwable) null);
                    } else if (isNameStartChar(more)) {
                        this.seenRoot = true;
                        return parseStartTag();
                    } else {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("expected start tag name and not ");
                        stringBuffer3.append(printable(more));
                        throw new XmlPullParserException(stringBuffer3.toString(), this, (Throwable) null);
                    }
                } else {
                    this.posEnd = this.pos - 1;
                    this.seenMarkup = true;
                    this.eventType = 7;
                    return 7;
                }
            } else if (!isS(c)) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("only whitespace content allowed before start tag and not ");
                stringBuffer4.append(printable(c));
                throw new XmlPullParserException(stringBuffer4.toString(), this, (Throwable) null);
            } else if (!z) {
                z2 = true;
            } else if (c == 13) {
                if (!this.usePC) {
                    int i = this.pos - 1;
                    this.posEnd = i;
                    if (i > this.posStart) {
                        joinPC();
                    } else {
                        this.usePC = true;
                        this.pcEnd = 0;
                        this.pcStart = 0;
                    }
                }
                int i2 = this.pcEnd;
                if (i2 >= this.pc.length) {
                    ensurePC(i2);
                }
                char[] cArr = this.pc;
                int i3 = this.pcEnd;
                this.pcEnd = i3 + 1;
                cArr[i3] = 10;
                z2 = true;
                z3 = true;
            } else {
                if (c == 10) {
                    if (!z3 && this.usePC) {
                        int i4 = this.pcEnd;
                        if (i4 >= this.pc.length) {
                            ensurePC(i4);
                        }
                        char[] cArr2 = this.pc;
                        int i5 = this.pcEnd;
                        this.pcEnd = i5 + 1;
                        cArr2[i5] = 10;
                    }
                } else if (this.usePC) {
                    int i6 = this.pcEnd;
                    if (i6 >= this.pc.length) {
                        ensurePC(i6);
                    }
                    char[] cArr3 = this.pc;
                    int i7 = this.pcEnd;
                    this.pcEnd = i7 + 1;
                    cArr3[i7] = c;
                }
                z2 = true;
                z3 = false;
            }
            c = more();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x019e A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parseEpilog() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r10 = this;
            int r0 = r10.eventType
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x01aa
            boolean r0 = r10.reachedEnd
            if (r0 == 0) goto L_0x000d
            r10.eventType = r2
            return r2
        L_0x000d:
            boolean r0 = r10.tokenize
            r3 = 0
            if (r0 != r2) goto L_0x0018
            boolean r0 = r10.roundtripSupported
            if (r0 != 0) goto L_0x0018
            r0 = 1
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            r4 = 7
            boolean r5 = r10.seenMarkup     // Catch:{ EOFException -> 0x0197 }
            if (r5 == 0) goto L_0x0026
            char[] r5 = r10.buf     // Catch:{ EOFException -> 0x0197 }
            int r6 = r10.pos     // Catch:{ EOFException -> 0x0197 }
            int r6 = r6 - r2
            char r5 = r5[r6]     // Catch:{ EOFException -> 0x0197 }
            goto L_0x002a
        L_0x0026:
            char r5 = r10.more()     // Catch:{ EOFException -> 0x0197 }
        L_0x002a:
            r10.seenMarkup = r3     // Catch:{ EOFException -> 0x0197 }
            int r6 = r10.pos     // Catch:{ EOFException -> 0x0197 }
            int r6 = r6 - r2
            r10.posStart = r6     // Catch:{ EOFException -> 0x0197 }
            r6 = 0
            r7 = 0
        L_0x0033:
            r8 = 60
            r9 = 10
            if (r5 != r8) goto L_0x00f9
            if (r6 == 0) goto L_0x0049
            boolean r5 = r10.tokenize     // Catch:{ EOFException -> 0x0195 }
            if (r5 == 0) goto L_0x0049
            int r0 = r10.pos     // Catch:{ EOFException -> 0x0195 }
            int r0 = r0 - r2
            r10.posEnd = r0     // Catch:{ EOFException -> 0x0195 }
            r10.seenMarkup = r2     // Catch:{ EOFException -> 0x0195 }
            r10.eventType = r4     // Catch:{ EOFException -> 0x0195 }
            return r4
        L_0x0049:
            char r5 = r10.more()     // Catch:{ EOFException -> 0x0195 }
            r8 = 63
            if (r5 != r8) goto L_0x005d
            r10.parsePI()     // Catch:{ EOFException -> 0x0195 }
            boolean r5 = r10.tokenize     // Catch:{ EOFException -> 0x0195 }
            if (r5 == 0) goto L_0x0174
            r0 = 8
            r10.eventType = r0     // Catch:{ EOFException -> 0x0195 }
            return r0
        L_0x005d:
            r8 = 33
            if (r5 != r8) goto L_0x009e
            char r5 = r10.more()     // Catch:{ EOFException -> 0x0195 }
            r8 = 68
            if (r5 != r8) goto L_0x0073
            r10.parseDocdecl()     // Catch:{ EOFException -> 0x0195 }
            boolean r5 = r10.tokenize     // Catch:{ EOFException -> 0x0195 }
            if (r5 == 0) goto L_0x0174
            r10.eventType = r9     // Catch:{ EOFException -> 0x0195 }
            return r9
        L_0x0073:
            r8 = 45
            if (r5 != r8) goto L_0x0083
            r10.parseComment()     // Catch:{ EOFException -> 0x0195 }
            boolean r5 = r10.tokenize     // Catch:{ EOFException -> 0x0195 }
            if (r5 == 0) goto L_0x0174
            r0 = 9
            r10.eventType = r0     // Catch:{ EOFException -> 0x0195 }
            return r0
        L_0x0083:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x0195 }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0195 }
            r3.<init>()     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r7 = "unexpected markup <!"
            r3.append(r7)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r5 = r10.printable((char) r5)     // Catch:{ EOFException -> 0x0195 }
            r3.append(r5)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r3 = r3.toString()     // Catch:{ EOFException -> 0x0195 }
            r0.<init>(r3, r10, r1)     // Catch:{ EOFException -> 0x0195 }
            throw r0     // Catch:{ EOFException -> 0x0195 }
        L_0x009e:
            r0 = 47
            if (r5 == r0) goto L_0x00de
            boolean r0 = r10.isNameStartChar(r5)     // Catch:{ EOFException -> 0x0195 }
            if (r0 == 0) goto L_0x00c3
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x0195 }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0195 }
            r3.<init>()     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r7 = "start tag not allowed in epilog but got "
            r3.append(r7)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r5 = r10.printable((char) r5)     // Catch:{ EOFException -> 0x0195 }
            r3.append(r5)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r3 = r3.toString()     // Catch:{ EOFException -> 0x0195 }
            r0.<init>(r3, r10, r1)     // Catch:{ EOFException -> 0x0195 }
            throw r0     // Catch:{ EOFException -> 0x0195 }
        L_0x00c3:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x0195 }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0195 }
            r3.<init>()     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r7 = "in epilog expected ignorable content and not "
            r3.append(r7)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r5 = r10.printable((char) r5)     // Catch:{ EOFException -> 0x0195 }
            r3.append(r5)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r3 = r3.toString()     // Catch:{ EOFException -> 0x0195 }
            r0.<init>(r3, r10, r1)     // Catch:{ EOFException -> 0x0195 }
            throw r0     // Catch:{ EOFException -> 0x0195 }
        L_0x00de:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x0195 }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0195 }
            r3.<init>()     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r7 = "end tag not allowed in epilog but got "
            r3.append(r7)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r5 = r10.printable((char) r5)     // Catch:{ EOFException -> 0x0195 }
            r3.append(r5)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r3 = r3.toString()     // Catch:{ EOFException -> 0x0195 }
            r0.<init>(r3, r10, r1)     // Catch:{ EOFException -> 0x0195 }
            throw r0     // Catch:{ EOFException -> 0x0195 }
        L_0x00f9:
            boolean r8 = r10.isS(r5)     // Catch:{ EOFException -> 0x0195 }
            if (r8 == 0) goto L_0x017a
            if (r0 == 0) goto L_0x0173
            r6 = 13
            if (r5 != r6) goto L_0x0137
            boolean r5 = r10.usePC     // Catch:{ EOFException -> 0x0135 }
            if (r5 != 0) goto L_0x011c
            int r5 = r10.pos     // Catch:{ EOFException -> 0x0135 }
            int r5 = r5 - r2
            r10.posEnd = r5     // Catch:{ EOFException -> 0x0135 }
            int r6 = r10.posStart     // Catch:{ EOFException -> 0x0135 }
            if (r5 <= r6) goto L_0x0116
            r10.joinPC()     // Catch:{ EOFException -> 0x0135 }
            goto L_0x011c
        L_0x0116:
            r10.usePC = r2     // Catch:{ EOFException -> 0x0135 }
            r10.pcEnd = r3     // Catch:{ EOFException -> 0x0135 }
            r10.pcStart = r3     // Catch:{ EOFException -> 0x0135 }
        L_0x011c:
            int r5 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            char[] r6 = r10.pc     // Catch:{ EOFException -> 0x0135 }
            int r6 = r6.length     // Catch:{ EOFException -> 0x0135 }
            if (r5 < r6) goto L_0x0128
            int r5 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            r10.ensurePC(r5)     // Catch:{ EOFException -> 0x0135 }
        L_0x0128:
            char[] r5 = r10.pc     // Catch:{ EOFException -> 0x0135 }
            int r6 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            int r7 = r6 + 1
            r10.pcEnd = r7     // Catch:{ EOFException -> 0x0135 }
            r5[r6] = r9     // Catch:{ EOFException -> 0x0135 }
            r6 = 1
            r7 = 1
            goto L_0x0174
        L_0x0135:
            r3 = 1
            goto L_0x0198
        L_0x0137:
            if (r5 != r9) goto L_0x0158
            if (r7 != 0) goto L_0x0155
            boolean r5 = r10.usePC     // Catch:{ EOFException -> 0x0135 }
            if (r5 == 0) goto L_0x0155
            int r5 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            char[] r6 = r10.pc     // Catch:{ EOFException -> 0x0135 }
            int r6 = r6.length     // Catch:{ EOFException -> 0x0135 }
            if (r5 < r6) goto L_0x014b
            int r5 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            r10.ensurePC(r5)     // Catch:{ EOFException -> 0x0135 }
        L_0x014b:
            char[] r5 = r10.pc     // Catch:{ EOFException -> 0x0135 }
            int r6 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            int r7 = r6 + 1
            r10.pcEnd = r7     // Catch:{ EOFException -> 0x0135 }
            r5[r6] = r9     // Catch:{ EOFException -> 0x0135 }
        L_0x0155:
            r6 = 1
            r7 = 0
            goto L_0x0174
        L_0x0158:
            boolean r6 = r10.usePC     // Catch:{ EOFException -> 0x0135 }
            if (r6 == 0) goto L_0x0155
            int r6 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            char[] r7 = r10.pc     // Catch:{ EOFException -> 0x0135 }
            int r7 = r7.length     // Catch:{ EOFException -> 0x0135 }
            if (r6 < r7) goto L_0x0168
            int r6 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            r10.ensurePC(r6)     // Catch:{ EOFException -> 0x0135 }
        L_0x0168:
            char[] r6 = r10.pc     // Catch:{ EOFException -> 0x0135 }
            int r7 = r10.pcEnd     // Catch:{ EOFException -> 0x0135 }
            int r8 = r7 + 1
            r10.pcEnd = r8     // Catch:{ EOFException -> 0x0135 }
            r6[r7] = r5     // Catch:{ EOFException -> 0x0135 }
            goto L_0x0155
        L_0x0173:
            r6 = 1
        L_0x0174:
            char r5 = r10.more()     // Catch:{ EOFException -> 0x0195 }
            goto L_0x0033
        L_0x017a:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x0195 }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0195 }
            r3.<init>()     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r7 = "in epilog non whitespace content is not allowed but got "
            r3.append(r7)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r5 = r10.printable((char) r5)     // Catch:{ EOFException -> 0x0195 }
            r3.append(r5)     // Catch:{ EOFException -> 0x0195 }
            java.lang.String r3 = r3.toString()     // Catch:{ EOFException -> 0x0195 }
            r0.<init>(r3, r10, r1)     // Catch:{ EOFException -> 0x0195 }
            throw r0     // Catch:{ EOFException -> 0x0195 }
        L_0x0195:
            r3 = r6
            goto L_0x0198
        L_0x0197:
        L_0x0198:
            r10.reachedEnd = r2
            boolean r0 = r10.tokenize
            if (r0 == 0) goto L_0x01a7
            if (r3 == 0) goto L_0x01a7
            int r0 = r10.pos
            r10.posEnd = r0
            r10.eventType = r4
            return r4
        L_0x01a7:
            r10.eventType = r2
            return r2
        L_0x01aa:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "already reached end of XML input"
            r0.<init>(r2, r10, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseEpilog():int");
    }

    public int parseEndTag() throws XmlPullParserException, IOException {
        char more;
        char more2 = more();
        if (isNameStartChar(more2)) {
            int i = this.pos;
            this.posStart = i - 3;
            int i2 = this.bufAbsoluteStart + (i - 1);
            do {
                more = more();
            } while (isNameChar(more));
            int i3 = i2 - this.bufAbsoluteStart;
            int i4 = (this.pos - 1) - i3;
            char[][] cArr = this.elRawName;
            int i5 = this.depth;
            char[] cArr2 = cArr[i5];
            if (this.elRawNameEnd[i5] == i4) {
                int i6 = 0;
                while (i6 < i4) {
                    int i7 = i3 + 1;
                    if (this.buf[i3] == cArr2[i6]) {
                        i6++;
                        i3 = i7;
                    } else {
                        String str = new String(cArr2, 0, i4);
                        String str2 = new String(this.buf, (i7 - i6) - 1, i4);
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("end tag name </");
                        stringBuffer.append(str2);
                        stringBuffer.append("> must be the same as start tag <");
                        stringBuffer.append(str);
                        stringBuffer.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
                        stringBuffer.append(" from line ");
                        stringBuffer.append(this.elRawNameLine[this.depth]);
                        throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
                    }
                }
                while (isS(more)) {
                    more = more();
                }
                if (more == '>') {
                    this.posEnd = this.pos;
                    this.pastEndTag = true;
                    this.eventType = 3;
                    return 3;
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("expected > to finsh end tag not ");
                stringBuffer2.append(printable(more));
                stringBuffer2.append(" from line ");
                stringBuffer2.append(this.elRawNameLine[this.depth]);
                throw new XmlPullParserException(stringBuffer2.toString(), this, (Throwable) null);
            }
            String str3 = new String(cArr2, 0, this.elRawNameEnd[this.depth]);
            String str4 = new String(this.buf, i3, i4);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("end tag name </");
            stringBuffer3.append(str4);
            stringBuffer3.append("> must match start tag name <");
            stringBuffer3.append(str3);
            stringBuffer3.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
            stringBuffer3.append(" from line ");
            stringBuffer3.append(this.elRawNameLine[this.depth]);
            throw new XmlPullParserException(stringBuffer3.toString(), this, (Throwable) null);
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("expected name start and not ");
        stringBuffer4.append(printable(more2));
        throw new XmlPullParserException(stringBuffer4.toString(), this, (Throwable) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x0296  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01e3 A[LOOP:5: B:78:0x01e3->B:84:0x01f9, LOOP_START, PHI: r1 
      PHI: (r1v2 int) = (r1v0 int), (r1v3 int) binds: [B:32:0x00e5, B:84:0x01f9] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parseStartTag() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r13 = this;
            int r0 = r13.depth
            r1 = 1
            int r0 = r0 + r1
            r13.depth = r0
            int r0 = r13.pos
            int r2 = r0 + -2
            r13.posStart = r2
            r2 = 0
            r13.emptyElementTag = r2
            r13.attributeCount = r2
            int r3 = r0 + -1
            int r4 = r13.bufAbsoluteStart
            int r3 = r3 + r4
            char[] r4 = r13.buf
            int r0 = r0 - r1
            char r0 = r4[r0]
            r4 = 58
            r5 = 0
            if (r0 != r4) goto L_0x002d
            boolean r0 = r13.processNamespaces
            if (r0 != 0) goto L_0x0025
            goto L_0x002d
        L_0x0025:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r1 = "when namespaces processing enabled colon can not be at element name start"
            r0.<init>(r1, r13, r5)
            throw r0
        L_0x002d:
            r0 = -1
            r6 = -1
        L_0x002f:
            char r7 = r13.more()
            boolean r8 = r13.isNameChar(r7)
            if (r8 != 0) goto L_0x029c
            r13.ensureElementsCapacity()
            int r4 = r13.pos
            int r4 = r4 - r1
            int r8 = r13.bufAbsoluteStart
            int r8 = r3 - r8
            int r4 = r4 - r8
            char[][] r8 = r13.elRawName
            int r9 = r13.depth
            r10 = r8[r9]
            if (r10 == 0) goto L_0x0051
            r8 = r8[r9]
            int r8 = r8.length
            if (r8 >= r4) goto L_0x005b
        L_0x0051:
            char[][] r8 = r13.elRawName
            int r9 = r13.depth
            int r10 = r4 * 2
            char[] r10 = new char[r10]
            r8[r9] = r10
        L_0x005b:
            char[] r8 = r13.buf
            int r9 = r13.bufAbsoluteStart
            int r9 = r3 - r9
            char[][] r10 = r13.elRawName
            int r11 = r13.depth
            r10 = r10[r11]
            java.lang.System.arraycopy(r8, r9, r10, r2, r4)
            int[] r8 = r13.elRawNameEnd
            int r9 = r13.depth
            r8[r9] = r4
            int[] r8 = r13.elRawNameLine
            int r10 = r13.lineNumber
            r8[r9] = r10
            boolean r8 = r13.processNamespaces
            r10 = 2
            if (r8 == 0) goto L_0x00b6
            if (r6 == r0) goto L_0x00a4
            java.lang.String[] r0 = r13.elPrefix
            char[] r4 = r13.buf
            int r8 = r13.bufAbsoluteStart
            int r8 = r3 - r8
            int r3 = r6 - r3
            java.lang.String r3 = r13.newString(r4, r8, r3)
            r0[r9] = r3
            java.lang.String[] r0 = r13.elName
            int r4 = r13.depth
            char[] r8 = r13.buf
            int r9 = r6 + 1
            int r11 = r13.bufAbsoluteStart
            int r9 = r9 - r11
            int r12 = r13.pos
            int r12 = r12 - r10
            int r6 = r6 - r11
            int r12 = r12 - r6
            java.lang.String r6 = r13.newString(r8, r9, r12)
            r0[r4] = r6
            goto L_0x00c4
        L_0x00a4:
            java.lang.String[] r0 = r13.elPrefix
            r0[r9] = r5
            java.lang.String[] r0 = r13.elName
            char[] r6 = r13.buf
            int r8 = r13.bufAbsoluteStart
            int r3 = r3 - r8
            java.lang.String r3 = r13.newString(r6, r3, r4)
            r0[r9] = r3
            goto L_0x00c3
        L_0x00b6:
            java.lang.String[] r0 = r13.elName
            char[] r6 = r13.buf
            int r8 = r13.bufAbsoluteStart
            int r3 = r3 - r8
            java.lang.String r3 = r13.newString(r6, r3, r4)
            r0[r9] = r3
        L_0x00c3:
            r3 = r5
        L_0x00c4:
            boolean r0 = r13.isS(r7)
            if (r0 != 0) goto L_0x0296
            r0 = 62
            if (r7 != r0) goto L_0x00cf
            goto L_0x00df
        L_0x00cf:
            r4 = 47
            if (r7 != r4) goto L_0x026c
            boolean r4 = r13.emptyElementTag
            if (r4 != 0) goto L_0x0264
            r13.emptyElementTag = r1
            char r4 = r13.more()
            if (r4 != r0) goto L_0x0249
        L_0x00df:
            boolean r0 = r13.processNamespaces
            java.lang.String r6 = " and "
            java.lang.String r7 = "duplicated attributes "
            if (r0 == 0) goto L_0x01e3
            java.lang.String r0 = r13.getNamespace(r3)
            java.lang.String r4 = ""
            if (r0 != 0) goto L_0x010a
            if (r3 != 0) goto L_0x00f3
            r0 = r4
            goto L_0x010a
        L_0x00f3:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "could not determine namespace bound to element prefix "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r13, r5)
            throw r0
        L_0x010a:
            java.lang.String[] r3 = r13.elUri
            int r8 = r13.depth
            r3[r8] = r0
            r0 = 0
        L_0x0111:
            int r3 = r13.attributeCount
            if (r0 < r3) goto L_0x01b3
            r3 = 1
        L_0x0116:
            int r0 = r13.attributeCount
            if (r3 < r0) goto L_0x011c
            goto L_0x01e7
        L_0x011c:
            r0 = 0
        L_0x011d:
            if (r0 < r3) goto L_0x0122
            int r3 = r3 + 1
            goto L_0x0116
        L_0x0122:
            java.lang.String[] r1 = r13.attributeUri
            r4 = r1[r0]
            r1 = r1[r3]
            if (r4 != r1) goto L_0x01af
            boolean r1 = r13.allStringsInterned
            if (r1 == 0) goto L_0x013a
            java.lang.String[] r1 = r13.attributeName
            r4 = r1[r0]
            r1 = r1[r3]
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L_0x0152
        L_0x013a:
            boolean r1 = r13.allStringsInterned
            if (r1 != 0) goto L_0x01af
            int[] r1 = r13.attributeNameHash
            r4 = r1[r0]
            r1 = r1[r3]
            if (r4 != r1) goto L_0x01af
            java.lang.String[] r1 = r13.attributeName
            r4 = r1[r0]
            r1 = r1[r3]
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x01af
        L_0x0152:
            java.lang.String[] r1 = r13.attributeName
            r1 = r1[r0]
            java.lang.String[] r2 = r13.attributeUri
            r2 = r2[r0]
            java.lang.String r4 = ":"
            if (r2 == 0) goto L_0x0174
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String[] r8 = r13.attributeUri
            r0 = r8[r0]
            r2.append(r0)
            r2.append(r4)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x0174:
            java.lang.String[] r0 = r13.attributeName
            r0 = r0[r3]
            java.lang.String[] r2 = r13.attributeUri
            r2 = r2[r3]
            if (r2 == 0) goto L_0x0194
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String[] r8 = r13.attributeUri
            r3 = r8[r3]
            r2.append(r3)
            r2.append(r4)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
        L_0x0194:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r3.append(r7)
            r3.append(r1)
            r3.append(r6)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0, r13, r5)
            throw r2
        L_0x01af:
            int r0 = r0 + 1
            goto L_0x011d
        L_0x01b3:
            java.lang.String[] r3 = r13.attributePrefix
            r3 = r3[r0]
            if (r3 == 0) goto L_0x01db
            java.lang.String r8 = r13.getNamespace(r3)
            if (r8 == 0) goto L_0x01c4
            java.lang.String[] r3 = r13.attributeUri
            r3[r0] = r8
            goto L_0x01df
        L_0x01c4:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "could not determine namespace bound to attribute prefix "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r13, r5)
            throw r0
        L_0x01db:
            java.lang.String[] r3 = r13.attributeUri
            r3[r0] = r4
        L_0x01df:
            int r0 = r0 + 1
            goto L_0x0111
        L_0x01e3:
            int r0 = r13.attributeCount
            if (r1 < r0) goto L_0x01f6
        L_0x01e7:
            int[] r0 = r13.elNamespaceCount
            int r1 = r13.depth
            int r2 = r13.namespaceEnd
            r0[r1] = r2
            int r0 = r13.pos
            r13.posEnd = r0
            r13.eventType = r10
            return r10
        L_0x01f6:
            r0 = 0
        L_0x01f7:
            if (r0 < r1) goto L_0x01fc
            int r1 = r1 + 1
            goto L_0x01e3
        L_0x01fc:
            boolean r3 = r13.allStringsInterned
            if (r3 == 0) goto L_0x020c
            java.lang.String[] r3 = r13.attributeName
            r4 = r3[r0]
            r3 = r3[r1]
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x0225
        L_0x020c:
            boolean r3 = r13.allStringsInterned
            if (r3 != 0) goto L_0x0246
            int[] r3 = r13.attributeNameHash
            r4 = r3[r0]
            r3 = r3[r1]
            if (r4 != r3) goto L_0x0246
            java.lang.String[] r3 = r13.attributeName
            r4 = r3[r0]
            r3 = r3[r1]
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x0225
            goto L_0x0246
        L_0x0225:
            java.lang.String[] r2 = r13.attributeName
            r0 = r2[r0]
            r1 = r2[r1]
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r3.append(r7)
            r3.append(r0)
            r3.append(r6)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0, r13, r5)
            throw r2
        L_0x0246:
            int r0 = r0 + 1
            goto L_0x01f7
        L_0x0249:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "expected > to end empty tag not "
            r1.append(r2)
            java.lang.String r2 = r13.printable((char) r4)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r13, r5)
            throw r0
        L_0x0264:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r1 = "repeated / in tag declaration"
            r0.<init>(r1, r13, r5)
            throw r0
        L_0x026c:
            boolean r0 = r13.isNameStartChar(r7)
            if (r0 == 0) goto L_0x027b
            r13.parseAttribute()
            char r7 = r13.more()
            goto L_0x00c4
        L_0x027b:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "start tag unexpected character "
            r1.append(r2)
            java.lang.String r2 = r13.printable((char) r7)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r13, r5)
            throw r0
        L_0x0296:
            char r7 = r13.more()
            goto L_0x00c4
        L_0x029c:
            if (r7 != r4) goto L_0x002f
            boolean r7 = r13.processNamespaces
            if (r7 == 0) goto L_0x002f
            if (r6 != r0) goto L_0x02ac
            int r6 = r13.pos
            int r6 = r6 - r1
            int r7 = r13.bufAbsoluteStart
            int r6 = r6 + r7
            goto L_0x002f
        L_0x02ac:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r1 = "only one colon is allowed in name of element when namespaces are enabled"
            r0.<init>(r1, r13, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseStartTag():int");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0336, code lost:
        if (r7 != 'm') goto L_0x0338;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x033e, code lost:
        if (r7 != 'l') goto L_0x0338;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0346, code lost:
        if (r7 != 'n') goto L_0x0338;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x034d, code lost:
        if (r7 != 's') goto L_0x0338;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char parseAttribute() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            int r1 = r0.posStart
            int r2 = r0.bufAbsoluteStart
            int r1 = r1 + r2
            int r3 = r0.pos
            int r4 = r3 + -1
            int r4 = r4 + r2
            char[] r2 = r0.buf
            r5 = 1
            int r3 = r3 - r5
            char r2 = r2[r3]
            r3 = 58
            r6 = 0
            if (r2 != r3) goto L_0x0024
            boolean r7 = r0.processNamespaces
            if (r7 != 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "when namespaces processing enabled colon can not be at attribute name start"
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x0024:
            boolean r7 = r0.processNamespaces
            r8 = 0
            if (r7 == 0) goto L_0x002f
            r7 = 120(0x78, float:1.68E-43)
            if (r2 != r7) goto L_0x002f
            r2 = 1
            goto L_0x0030
        L_0x002f:
            r2 = 0
        L_0x0030:
            char r7 = r16.more()
            r9 = -1
            r10 = 0
            r11 = -1
        L_0x0037:
            boolean r12 = r0.isNameChar(r7)
            r13 = 4
            r14 = 2
            if (r12 != 0) goto L_0x0327
            int r3 = r0.attributeCount
            r0.ensureAttributesCapacity(r3)
            boolean r3 = r0.processNamespaces
            if (r3 == 0) goto L_0x00c3
            if (r10 >= r13) goto L_0x004b
            r2 = 0
        L_0x004b:
            if (r2 == 0) goto L_0x006f
            if (r11 == r9) goto L_0x006c
            int r3 = r0.pos
            int r3 = r3 - r14
            int r4 = r0.bufAbsoluteStart
            int r10 = r11 - r4
            int r3 = r3 - r10
            if (r3 == 0) goto L_0x0064
            char[] r10 = r0.buf
            int r4 = r11 - r4
            int r4 = r4 + r5
            java.lang.String r3 = r0.newString(r10, r4, r3)
            goto L_0x00e7
        L_0x0064:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "namespace prefix is required after xmlns:  when namespaces are enabled"
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x006c:
            r3 = r6
            goto L_0x00e7
        L_0x006f:
            if (r11 == r9) goto L_0x009a
            int r3 = r11 - r4
            java.lang.String[] r10 = r0.attributePrefix
            int r12 = r0.attributeCount
            char[] r13 = r0.buf
            int r15 = r0.bufAbsoluteStart
            int r4 = r4 - r15
            java.lang.String r3 = r0.newString(r13, r4, r3)
            r10[r12] = r3
            int r3 = r0.pos
            int r3 = r3 - r14
            int r4 = r0.bufAbsoluteStart
            int r10 = r11 - r4
            int r3 = r3 - r10
            java.lang.String[] r10 = r0.attributeName
            int r12 = r0.attributeCount
            char[] r13 = r0.buf
            int r4 = r11 - r4
            int r4 = r4 + r5
            java.lang.String r3 = r0.newString(r13, r4, r3)
            r10[r12] = r3
            goto L_0x00b4
        L_0x009a:
            java.lang.String[] r3 = r0.attributePrefix
            int r10 = r0.attributeCount
            r3[r10] = r6
            java.lang.String[] r3 = r0.attributeName
            char[] r12 = r0.buf
            int r13 = r0.bufAbsoluteStart
            int r14 = r4 - r13
            int r15 = r0.pos
            int r15 = r15 - r5
            int r4 = r4 - r13
            int r15 = r15 - r4
            java.lang.String r4 = r0.newString(r12, r14, r15)
            r3[r10] = r4
            r3 = r4
        L_0x00b4:
            boolean r4 = r0.allStringsInterned
            if (r4 != 0) goto L_0x00e7
            int[] r4 = r0.attributeNameHash
            int r10 = r0.attributeCount
            int r12 = r3.hashCode()
            r4[r10] = r12
            goto L_0x00e7
        L_0x00c3:
            java.lang.String[] r3 = r0.attributeName
            int r10 = r0.attributeCount
            char[] r12 = r0.buf
            int r13 = r0.bufAbsoluteStart
            int r14 = r4 - r13
            int r15 = r0.pos
            int r15 = r15 - r5
            int r4 = r4 - r13
            int r15 = r15 - r4
            java.lang.String r4 = r0.newString(r12, r14, r15)
            r3[r10] = r4
            boolean r3 = r0.allStringsInterned
            if (r3 != 0) goto L_0x00e6
            int[] r3 = r0.attributeNameHash
            int r10 = r0.attributeCount
            int r12 = r4.hashCode()
            r3[r10] = r12
        L_0x00e6:
            r3 = r4
        L_0x00e7:
            boolean r4 = r0.isS(r7)
            if (r4 != 0) goto L_0x0320
            r4 = 61
            if (r7 != r4) goto L_0x0318
            char r4 = r16.more()
        L_0x00f5:
            boolean r7 = r0.isS(r4)
            if (r7 != 0) goto L_0x0311
            r7 = 34
            if (r4 == r7) goto L_0x011f
            r7 = 39
            if (r4 != r7) goto L_0x0104
            goto L_0x011f
        L_0x0104:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "attribute value must start with quotation or apostrophe not "
            r2.append(r3)
            java.lang.String r3 = r0.printable((char) r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x011f:
            r0.usePC = r8
            int r7 = r0.pcEnd
            r0.pcStart = r7
            int r7 = r0.pos
            r0.posStart = r7
            r7 = 0
        L_0x012a:
            char r10 = r16.more()
            java.lang.String r12 = "'"
            if (r10 != r4) goto L_0x022f
            boolean r4 = r0.processNamespaces
            if (r4 == 0) goto L_0x01fa
            if (r2 == 0) goto L_0x01fa
            boolean r2 = r0.usePC
            if (r2 != 0) goto L_0x0149
            char[] r2 = r0.buf
            int r4 = r0.posStart
            int r7 = r0.pos
            int r7 = r7 - r5
            int r7 = r7 - r4
            java.lang.String r2 = r0.newStringIntern(r2, r4, r7)
            goto L_0x0154
        L_0x0149:
            char[] r2 = r0.pc
            int r4 = r0.pcStart
            int r7 = r0.pcEnd
            int r7 = r7 - r4
            java.lang.String r2 = r0.newStringIntern(r2, r4, r7)
        L_0x0154:
            int r4 = r0.namespaceEnd
            r0.ensureNamespacesCapacity(r4)
            if (r11 == r9) goto L_0x017c
            int r4 = r2.length()
            if (r4 == 0) goto L_0x0174
            java.lang.String[] r4 = r0.namespacePrefix
            int r7 = r0.namespaceEnd
            r4[r7] = r3
            boolean r4 = r0.allStringsInterned
            if (r4 != 0) goto L_0x018a
            int[] r4 = r0.namespacePrefixHash
            int r9 = r3.hashCode()
            r4[r7] = r9
            goto L_0x018a
        L_0x0174:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "non-default namespace can not be declared to be empty string"
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x017c:
            java.lang.String[] r4 = r0.namespacePrefix
            int r7 = r0.namespaceEnd
            r4[r7] = r6
            boolean r4 = r0.allStringsInterned
            if (r4 != 0) goto L_0x018a
            int[] r4 = r0.namespacePrefixHash
            r4[r7] = r9
        L_0x018a:
            java.lang.String[] r4 = r0.namespaceUri
            int r7 = r0.namespaceEnd
            r4[r7] = r2
            int[] r2 = r0.elNamespaceCount
            int r4 = r0.depth
            int r4 = r4 - r5
            r2 = r2[r4]
            int r7 = r7 - r5
        L_0x0198:
            if (r7 >= r2) goto L_0x01a1
            int r2 = r0.namespaceEnd
            int r2 = r2 + r5
            r0.namespaceEnd = r2
            goto L_0x0229
        L_0x01a1:
            boolean r4 = r0.allStringsInterned
            if (r4 != 0) goto L_0x01a7
            if (r3 != 0) goto L_0x01ad
        L_0x01a7:
            java.lang.String[] r4 = r0.namespacePrefix
            r4 = r4[r7]
            if (r4 == r3) goto L_0x01c7
        L_0x01ad:
            boolean r4 = r0.allStringsInterned
            if (r4 != 0) goto L_0x01c4
            if (r3 == 0) goto L_0x01c4
            int[] r4 = r0.namespacePrefixHash
            r4 = r4[r7]
            if (r4 != r9) goto L_0x01c4
            java.lang.String[] r4 = r0.namespacePrefix
            r4 = r4[r7]
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x01c4
            goto L_0x01c7
        L_0x01c4:
            int r7 = r7 + -1
            goto L_0x0198
        L_0x01c7:
            if (r3 != 0) goto L_0x01cc
            java.lang.String r1 = "default"
            goto L_0x01de
        L_0x01cc:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r12)
            r1.append(r3)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
        L_0x01de:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            java.lang.String r4 = "duplicated namespace declaration for "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = " prefix"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1, r0, r6)
            throw r2
        L_0x01fa:
            boolean r2 = r0.usePC
            if (r2 != 0) goto L_0x0212
            java.lang.String[] r2 = r0.attributeValue
            int r3 = r0.attributeCount
            java.lang.String r4 = new java.lang.String
            char[] r6 = r0.buf
            int r7 = r0.posStart
            int r8 = r0.pos
            int r8 = r8 - r5
            int r8 = r8 - r7
            r4.<init>(r6, r7, r8)
            r2[r3] = r4
            goto L_0x0224
        L_0x0212:
            java.lang.String[] r2 = r0.attributeValue
            int r3 = r0.attributeCount
            java.lang.String r4 = new java.lang.String
            char[] r6 = r0.pc
            int r7 = r0.pcStart
            int r8 = r0.pcEnd
            int r8 = r8 - r7
            r4.<init>(r6, r7, r8)
            r2[r3] = r4
        L_0x0224:
            int r2 = r0.attributeCount
            int r2 = r2 + r5
            r0.attributeCount = r2
        L_0x0229:
            int r2 = r0.bufAbsoluteStart
            int r1 = r1 - r2
            r0.posStart = r1
            return r10
        L_0x022f:
            r13 = 60
            if (r10 == r13) goto L_0x0309
            r13 = 38
            r14 = 13
            if (r10 != r13) goto L_0x02ac
            int r7 = r0.pos
            int r7 = r7 - r5
            r0.posEnd = r7
            boolean r13 = r0.usePC
            if (r13 != 0) goto L_0x0255
            int r13 = r0.posStart
            if (r7 <= r13) goto L_0x0248
            r7 = 1
            goto L_0x0249
        L_0x0248:
            r7 = 0
        L_0x0249:
            if (r7 == 0) goto L_0x024f
            r16.joinPC()
            goto L_0x0255
        L_0x024f:
            r0.usePC = r5
            r0.pcEnd = r8
            r0.pcStart = r8
        L_0x0255:
            char[] r13 = r16.parseEntityRef()
            if (r13 != 0) goto L_0x028c
            java.lang.String r1 = r0.entityRefName
            if (r1 != 0) goto L_0x026c
            char[] r1 = r0.buf
            int r2 = r0.posStart
            int r3 = r0.posEnd
            int r3 = r3 - r2
            java.lang.String r1 = r0.newString(r1, r2, r3)
            r0.entityRefName = r1
        L_0x026c:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "could not resolve entity named '"
            r2.append(r3)
            java.lang.String r3 = r0.entityRefName
            java.lang.String r3 = r0.printable((java.lang.String) r3)
            r2.append(r3)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x028c:
            r7 = 0
        L_0x028d:
            int r12 = r13.length
            if (r7 < r12) goto L_0x0292
            goto L_0x0301
        L_0x0292:
            int r12 = r0.pcEnd
            char[] r15 = r0.pc
            int r15 = r15.length
            if (r12 < r15) goto L_0x029c
            r0.ensurePC(r12)
        L_0x029c:
            char[] r12 = r0.pc
            int r15 = r0.pcEnd
            int r9 = r15 + 1
            r0.pcEnd = r9
            char r9 = r13[r7]
            r12[r15] = r9
            int r7 = r7 + 1
            r9 = -1
            goto L_0x028d
        L_0x02ac:
            r9 = 9
            r12 = 10
            if (r10 == r9) goto L_0x02d0
            if (r10 == r12) goto L_0x02d0
            if (r10 != r14) goto L_0x02b7
            goto L_0x02d0
        L_0x02b7:
            boolean r7 = r0.usePC
            if (r7 == 0) goto L_0x0301
            int r7 = r0.pcEnd
            char[] r9 = r0.pc
            int r9 = r9.length
            if (r7 < r9) goto L_0x02c5
            r0.ensurePC(r7)
        L_0x02c5:
            char[] r7 = r0.pc
            int r9 = r0.pcEnd
            int r12 = r9 + 1
            r0.pcEnd = r12
            r7[r9] = r10
            goto L_0x0301
        L_0x02d0:
            boolean r9 = r0.usePC
            if (r9 != 0) goto L_0x02e7
            int r9 = r0.pos
            int r9 = r9 - r5
            r0.posEnd = r9
            int r13 = r0.posStart
            if (r9 <= r13) goto L_0x02e1
            r16.joinPC()
            goto L_0x02e7
        L_0x02e1:
            r0.usePC = r5
            r0.pcStart = r8
            r0.pcEnd = r8
        L_0x02e7:
            int r9 = r0.pcEnd
            char[] r13 = r0.pc
            int r13 = r13.length
            if (r9 < r13) goto L_0x02f1
            r0.ensurePC(r9)
        L_0x02f1:
            if (r10 != r12) goto L_0x02f5
            if (r7 != 0) goto L_0x0301
        L_0x02f5:
            char[] r7 = r0.pc
            int r9 = r0.pcEnd
            int r12 = r9 + 1
            r0.pcEnd = r12
            r12 = 32
            r7[r9] = r12
        L_0x0301:
            if (r10 != r14) goto L_0x0305
            r7 = 1
            goto L_0x0306
        L_0x0305:
            r7 = 0
        L_0x0306:
            r9 = -1
            goto L_0x012a
        L_0x0309:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "markup not allowed inside attribute value - illegal < "
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x0311:
            char r4 = r16.more()
            r9 = -1
            goto L_0x00f5
        L_0x0318:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "expected = after attribute name"
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x0320:
            char r7 = r16.more()
            r9 = -1
            goto L_0x00e7
        L_0x0327:
            boolean r9 = r0.processNamespaces
            if (r9 == 0) goto L_0x0371
            if (r2 == 0) goto L_0x035d
            r9 = 5
            if (r10 >= r9) goto L_0x035d
            int r10 = r10 + 1
            if (r10 != r5) goto L_0x033a
            r9 = 109(0x6d, float:1.53E-43)
            if (r7 == r9) goto L_0x035d
        L_0x0338:
            r2 = 0
            goto L_0x035d
        L_0x033a:
            if (r10 != r14) goto L_0x0341
            r9 = 108(0x6c, float:1.51E-43)
            if (r7 == r9) goto L_0x035d
            goto L_0x0338
        L_0x0341:
            r12 = 3
            if (r10 != r12) goto L_0x0349
            r9 = 110(0x6e, float:1.54E-43)
            if (r7 == r9) goto L_0x035d
            goto L_0x0338
        L_0x0349:
            if (r10 != r13) goto L_0x0350
            r9 = 115(0x73, float:1.61E-43)
            if (r7 == r9) goto L_0x035d
            goto L_0x0338
        L_0x0350:
            if (r10 != r9) goto L_0x035d
            if (r7 != r3) goto L_0x0355
            goto L_0x035d
        L_0x0355:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "after xmlns in attribute name must be colonwhen namespaces are enabled"
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x035d:
            if (r7 != r3) goto L_0x0371
            r7 = -1
            if (r11 != r7) goto L_0x0369
            int r9 = r0.pos
            int r9 = r9 - r5
            int r11 = r0.bufAbsoluteStart
            int r11 = r11 + r9
            goto L_0x0372
        L_0x0369:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "only one colon is allowed in attribute name when namespaces are enabled"
            r1.<init>(r2, r0, r6)
            throw r1
        L_0x0371:
            r7 = -1
        L_0x0372:
            char r9 = r16.more()
            r7 = r9
            r9 = -1
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseAttribute():char");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] parseEntityRef() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r13 = this;
            r0 = 0
            r13.entityRefName = r0
            int r1 = r13.pos
            r13.posStart = r1
            char r1 = r13.more()
            r2 = 97
            r3 = 59
            r4 = 1
            r5 = 0
            r6 = 35
            if (r1 != r6) goto L_0x00ab
            char r1 = r13.more()
            r6 = 120(0x78, float:1.68E-43)
            r7 = 57
            r8 = 48
            if (r1 != r6) goto L_0x0067
            r1 = 0
        L_0x0022:
            char r6 = r13.more()
            if (r6 < r8) goto L_0x0031
            if (r6 > r7) goto L_0x0031
            int r1 = r1 * 16
            int r6 = r6 + -48
        L_0x002e:
            int r1 = r1 + r6
            char r1 = (char) r1
            goto L_0x0022
        L_0x0031:
            if (r6 < r2) goto L_0x003c
            r9 = 102(0x66, float:1.43E-43)
            if (r6 > r9) goto L_0x003c
            int r1 = r1 * 16
            int r6 = r6 + -87
            goto L_0x002e
        L_0x003c:
            r9 = 65
            if (r6 < r9) goto L_0x0049
            r9 = 70
            if (r6 > r9) goto L_0x0049
            int r1 = r1 * 16
            int r6 = r6 + -55
            goto L_0x002e
        L_0x0049:
            if (r6 != r3) goto L_0x004c
            goto L_0x007a
        L_0x004c:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "character reference (with hex value) may not contain "
            r2.append(r3)
            java.lang.String r3 = r13.printable((char) r6)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r13, r0)
            throw r1
        L_0x0067:
            r2 = 0
        L_0x0068:
            if (r1 < r8) goto L_0x0077
            if (r1 > r7) goto L_0x0077
            int r2 = r2 * 10
            int r1 = r1 + -48
            int r2 = r2 + r1
            char r2 = (char) r2
            char r1 = r13.more()
            goto L_0x0068
        L_0x0077:
            if (r1 != r3) goto L_0x0090
            r1 = r2
        L_0x007a:
            int r0 = r13.pos
            int r0 = r0 - r4
            r13.posEnd = r0
            char[] r0 = r13.charRefOneCharBuf
            r0[r5] = r1
            boolean r1 = r13.tokenize
            if (r1 == 0) goto L_0x008d
            java.lang.String r0 = r13.newString(r0, r5, r4)
            r13.text = r0
        L_0x008d:
            char[] r0 = r13.charRefOneCharBuf
            return r0
        L_0x0090:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            java.lang.String r4 = "character reference (with decimal value) may not contain "
            r3.append(r4)
            java.lang.String r1 = r13.printable((char) r1)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1, r13, r0)
            throw r2
        L_0x00ab:
            char r1 = r13.more()
            if (r1 != r3) goto L_0x00ab
            int r1 = r13.pos
            int r1 = r1 - r4
            r13.posEnd = r1
            int r3 = r13.posStart
            int r1 = r1 - r3
            r6 = 116(0x74, float:1.63E-43)
            r7 = 2
            if (r1 != r7) goto L_0x00da
            char[] r8 = r13.buf
            char r9 = r8[r3]
            r10 = 108(0x6c, float:1.51E-43)
            if (r9 != r10) goto L_0x00da
            int r3 = r3 + r4
            char r3 = r8[r3]
            if (r3 != r6) goto L_0x00da
            boolean r0 = r13.tokenize
            if (r0 == 0) goto L_0x00d3
            java.lang.String r0 = "<"
            r13.text = r0
        L_0x00d3:
            char[] r0 = r13.charRefOneCharBuf
            r1 = 60
            r0[r5] = r1
            return r0
        L_0x00da:
            r3 = 112(0x70, float:1.57E-43)
            r8 = 3
            if (r1 != r8) goto L_0x0103
            char[] r9 = r13.buf
            int r10 = r13.posStart
            char r11 = r9[r10]
            if (r11 != r2) goto L_0x0103
            int r11 = r10 + 1
            char r11 = r9[r11]
            r12 = 109(0x6d, float:1.53E-43)
            if (r11 != r12) goto L_0x0103
            int r10 = r10 + r7
            char r9 = r9[r10]
            if (r9 != r3) goto L_0x0103
            boolean r0 = r13.tokenize
            if (r0 == 0) goto L_0x00fc
            java.lang.String r0 = "&"
            r13.text = r0
        L_0x00fc:
            char[] r0 = r13.charRefOneCharBuf
            r1 = 38
            r0[r5] = r1
            return r0
        L_0x0103:
            if (r1 != r7) goto L_0x0123
            char[] r7 = r13.buf
            int r9 = r13.posStart
            char r10 = r7[r9]
            r11 = 103(0x67, float:1.44E-43)
            if (r10 != r11) goto L_0x0123
            int r9 = r9 + r4
            char r4 = r7[r9]
            if (r4 != r6) goto L_0x0123
            boolean r0 = r13.tokenize
            if (r0 == 0) goto L_0x011c
            java.lang.String r0 = ">"
            r13.text = r0
        L_0x011c:
            char[] r0 = r13.charRefOneCharBuf
            r1 = 62
            r0[r5] = r1
            return r0
        L_0x0123:
            r4 = 111(0x6f, float:1.56E-43)
            r7 = 4
            if (r1 != r7) goto L_0x0152
            char[] r9 = r13.buf
            int r10 = r13.posStart
            char r11 = r9[r10]
            if (r11 != r2) goto L_0x0152
            int r2 = r10 + 1
            char r2 = r9[r2]
            if (r2 != r3) goto L_0x0152
            int r2 = r10 + 2
            char r2 = r9[r2]
            if (r2 != r4) goto L_0x0152
            int r10 = r10 + r8
            char r2 = r9[r10]
            r3 = 115(0x73, float:1.61E-43)
            if (r2 != r3) goto L_0x0152
            boolean r0 = r13.tokenize
            if (r0 == 0) goto L_0x014b
            java.lang.String r0 = "'"
            r13.text = r0
        L_0x014b:
            char[] r0 = r13.charRefOneCharBuf
            r1 = 39
            r0[r5] = r1
            return r0
        L_0x0152:
            if (r1 != r7) goto L_0x0180
            char[] r2 = r13.buf
            int r3 = r13.posStart
            char r7 = r2[r3]
            r9 = 113(0x71, float:1.58E-43)
            if (r7 != r9) goto L_0x0180
            int r7 = r3 + 1
            char r7 = r2[r7]
            r9 = 117(0x75, float:1.64E-43)
            if (r7 != r9) goto L_0x0180
            int r7 = r3 + 2
            char r7 = r2[r7]
            if (r7 != r4) goto L_0x0180
            int r3 = r3 + r8
            char r2 = r2[r3]
            if (r2 != r6) goto L_0x0180
            boolean r0 = r13.tokenize
            if (r0 == 0) goto L_0x0179
            java.lang.String r0 = "\""
            r13.text = r0
        L_0x0179:
            char[] r0 = r13.charRefOneCharBuf
            r1 = 34
            r0[r5] = r1
            return r0
        L_0x0180:
            char[] r1 = r13.lookuEntityReplacement(r1)
            if (r1 == 0) goto L_0x0187
            return r1
        L_0x0187:
            boolean r1 = r13.tokenize
            if (r1 == 0) goto L_0x018d
            r13.text = r0
        L_0x018d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseEntityRef():char[]");
    }

    /* access modifiers changed from: protected */
    public char[] lookuEntityReplacement(int i) throws XmlPullParserException, IOException {
        if (!this.allStringsInterned) {
            char[] cArr = this.buf;
            int i2 = this.posStart;
            int fastHash = fastHash(cArr, i2, this.posEnd - i2);
            for (int i3 = this.entityEnd - 1; i3 >= 0; i3--) {
                if (fastHash == this.entityNameHash[i3]) {
                    char[][] cArr2 = this.entityNameBuf;
                    if (i == cArr2[i3].length) {
                        char[] cArr3 = cArr2[i3];
                        int i4 = 0;
                        while (i4 < i) {
                            if (this.buf[this.posStart + i4] == cArr3[i4]) {
                                i4++;
                            }
                        }
                        if (this.tokenize) {
                            this.text = this.entityReplacement[i3];
                        }
                        return this.entityReplacementBuf[i3];
                    }
                    continue;
                }
            }
            return null;
        }
        char[] cArr4 = this.buf;
        int i5 = this.posStart;
        this.entityRefName = newString(cArr4, i5, this.posEnd - i5);
        for (int i6 = this.entityEnd - 1; i6 >= 0; i6--) {
            if (this.entityRefName == this.entityName[i6]) {
                if (this.tokenize) {
                    this.text = this.entityReplacement[i6];
                }
                return this.entityReplacementBuf[i6];
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a5, code lost:
        if (r10 != 10) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a7, code lost:
        if (r9 != false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ab, code lost:
        if (r13.usePC == false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b2, code lost:
        if (r13.pcEnd < r13.pc.length) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b4, code lost:
        ensurePC(r13.pcEnd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b9, code lost:
        r9 = r13.pc;
        r10 = r13.pcEnd;
        r13.pcEnd = r10 + 1;
        r9[r10] = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c7, code lost:
        if (r13.usePC == false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ce, code lost:
        if (r13.pcEnd < r13.pc.length) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d0, code lost:
        ensurePC(r13.pcEnd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d5, code lost:
        r9 = r13.pc;
        r11 = r13.pcEnd;
        r13.pcEnd = r11 + 1;
        r9[r11] = r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0025 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseComment() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r13 = this;
            char r0 = r13.more()
            r1 = 0
            r2 = 45
            if (r0 != r2) goto L_0x0106
            boolean r0 = r13.tokenize
            if (r0 == 0) goto L_0x0011
            int r0 = r13.pos
            r13.posStart = r0
        L_0x0011:
            int r0 = r13.lineNumber
            int r3 = r13.columnNumber
            boolean r4 = r13.tokenize     // Catch:{ EOFException -> 0x00e1 }
            r5 = 1
            r6 = 0
            if (r4 != r5) goto L_0x0021
            boolean r4 = r13.roundtripSupported     // Catch:{ EOFException -> 0x00e1 }
            if (r4 != 0) goto L_0x0021
            r4 = 1
            goto L_0x0022
        L_0x0021:
            r4 = 0
        L_0x0022:
            r7 = 0
            r8 = 0
        L_0x0024:
            r9 = 0
        L_0x0025:
            char r10 = r13.more()     // Catch:{ EOFException -> 0x00e1 }
            r11 = 62
            if (r7 == 0) goto L_0x004b
            if (r10 != r11) goto L_0x0030
            goto L_0x004b
        L_0x0030:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x00e1 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x00e1 }
            r4.<init>()     // Catch:{ EOFException -> 0x00e1 }
            java.lang.String r5 = "in comment after two dashes (--) next character must be > not "
            r4.append(r5)     // Catch:{ EOFException -> 0x00e1 }
            java.lang.String r5 = r13.printable((char) r10)     // Catch:{ EOFException -> 0x00e1 }
            r4.append(r5)     // Catch:{ EOFException -> 0x00e1 }
            java.lang.String r4 = r4.toString()     // Catch:{ EOFException -> 0x00e1 }
            r2.<init>(r4, r13, r1)     // Catch:{ EOFException -> 0x00e1 }
            throw r2     // Catch:{ EOFException -> 0x00e1 }
        L_0x004b:
            if (r10 != r2) goto L_0x0053
            if (r8 != 0) goto L_0x0051
            r8 = 1
            goto L_0x006e
        L_0x0051:
            r7 = 1
            goto L_0x006d
        L_0x0053:
            if (r10 != r11) goto L_0x006d
            if (r7 == 0) goto L_0x006c
            boolean r0 = r13.tokenize
            if (r0 == 0) goto L_0x006b
            int r0 = r13.pos
            int r0 = r0 + -3
            r13.posEnd = r0
            boolean r0 = r13.usePC
            if (r0 == 0) goto L_0x006b
            int r0 = r13.pcEnd
            int r0 = r0 + -2
            r13.pcEnd = r0
        L_0x006b:
            return
        L_0x006c:
            r7 = 0
        L_0x006d:
            r8 = 0
        L_0x006e:
            if (r4 == 0) goto L_0x0025
            r11 = 13
            r12 = 10
            if (r10 != r11) goto L_0x00a5
            boolean r9 = r13.usePC     // Catch:{ EOFException -> 0x00e1 }
            if (r9 != 0) goto L_0x008d
            int r9 = r13.pos     // Catch:{ EOFException -> 0x00e1 }
            int r9 = r9 - r5
            r13.posEnd = r9     // Catch:{ EOFException -> 0x00e1 }
            int r10 = r13.posStart     // Catch:{ EOFException -> 0x00e1 }
            if (r9 <= r10) goto L_0x0087
            r13.joinPC()     // Catch:{ EOFException -> 0x00e1 }
            goto L_0x008d
        L_0x0087:
            r13.usePC = r5     // Catch:{ EOFException -> 0x00e1 }
            r13.pcEnd = r6     // Catch:{ EOFException -> 0x00e1 }
            r13.pcStart = r6     // Catch:{ EOFException -> 0x00e1 }
        L_0x008d:
            int r9 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            char[] r10 = r13.pc     // Catch:{ EOFException -> 0x00e1 }
            int r10 = r10.length     // Catch:{ EOFException -> 0x00e1 }
            if (r9 < r10) goto L_0x0099
            int r9 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            r13.ensurePC(r9)     // Catch:{ EOFException -> 0x00e1 }
        L_0x0099:
            char[] r9 = r13.pc     // Catch:{ EOFException -> 0x00e1 }
            int r10 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            int r11 = r10 + 1
            r13.pcEnd = r11     // Catch:{ EOFException -> 0x00e1 }
            r9[r10] = r12     // Catch:{ EOFException -> 0x00e1 }
            r9 = 1
            goto L_0x0025
        L_0x00a5:
            if (r10 != r12) goto L_0x00c5
            if (r9 != 0) goto L_0x0024
            boolean r9 = r13.usePC     // Catch:{ EOFException -> 0x00e1 }
            if (r9 == 0) goto L_0x0024
            int r9 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            char[] r10 = r13.pc     // Catch:{ EOFException -> 0x00e1 }
            int r10 = r10.length     // Catch:{ EOFException -> 0x00e1 }
            if (r9 < r10) goto L_0x00b9
            int r9 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            r13.ensurePC(r9)     // Catch:{ EOFException -> 0x00e1 }
        L_0x00b9:
            char[] r9 = r13.pc     // Catch:{ EOFException -> 0x00e1 }
            int r10 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            int r11 = r10 + 1
            r13.pcEnd = r11     // Catch:{ EOFException -> 0x00e1 }
            r9[r10] = r12     // Catch:{ EOFException -> 0x00e1 }
            goto L_0x0024
        L_0x00c5:
            boolean r9 = r13.usePC     // Catch:{ EOFException -> 0x00e1 }
            if (r9 == 0) goto L_0x0024
            int r9 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            char[] r11 = r13.pc     // Catch:{ EOFException -> 0x00e1 }
            int r11 = r11.length     // Catch:{ EOFException -> 0x00e1 }
            if (r9 < r11) goto L_0x00d5
            int r9 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            r13.ensurePC(r9)     // Catch:{ EOFException -> 0x00e1 }
        L_0x00d5:
            char[] r9 = r13.pc     // Catch:{ EOFException -> 0x00e1 }
            int r11 = r13.pcEnd     // Catch:{ EOFException -> 0x00e1 }
            int r12 = r11 + 1
            r13.pcEnd = r12     // Catch:{ EOFException -> 0x00e1 }
            r9[r11] = r10     // Catch:{ EOFException -> 0x00e1 }
            goto L_0x0024
        L_0x00e1:
            r1 = move-exception
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.lang.String r5 = "comment started on line "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r0 = " and column "
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = " was not closed"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r2.<init>(r0, r13, r1)
            throw r2
        L_0x0106:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r2 = "expected <!-- for comment start"
            r0.<init>(r2, r13, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseComment():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0116, code lost:
        if (r11 != 10) goto L_0x0138;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0118, code lost:
        if (r10 != false) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x011c, code lost:
        if (r1.usePC == false) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0123, code lost:
        if (r1.pcEnd < r1.pc.length) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0125, code lost:
        ensurePC(r1.pcEnd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x012a, code lost:
        r10 = r1.pc;
        r11 = r1.pcEnd;
        r1.pcEnd = r11 + 1;
        r10[r11] = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x013a, code lost:
        if (r1.usePC == false) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0141, code lost:
        if (r1.pcEnd < r1.pc.length) goto L_0x0148;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0143, code lost:
        ensurePC(r1.pcEnd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0148, code lost:
        r6 = r1.pc;
        r10 = r1.pcEnd;
        r1.pcEnd = r10 + 1;
        r6[r10] = r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0100 A[Catch:{ EOFException -> 0x0157 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean parsePI() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r16 = this;
            r1 = r16
            boolean r0 = r1.tokenize
            if (r0 == 0) goto L_0x000a
            int r0 = r1.pos
            r1.posStart = r0
        L_0x000a:
            int r2 = r1.lineNumber
            int r3 = r1.columnNumber
            int r0 = r1.pos
            int r4 = r1.bufAbsoluteStart
            int r0 = r0 + r4
            boolean r4 = r1.tokenize
            r6 = 1
            if (r4 != r6) goto L_0x001e
            boolean r4 = r1.roundtripSupported
            if (r4 != 0) goto L_0x001e
            r4 = 1
            goto L_0x001f
        L_0x001e:
            r4 = 0
        L_0x001f:
            r7 = -1
            r8 = -1
            r9 = 0
        L_0x0022:
            r10 = 0
        L_0x0023:
            char r11 = r16.more()     // Catch:{ EOFException -> 0x0157 }
            r12 = 63
            if (r11 != r12) goto L_0x002e
            r9 = 1
            goto L_0x00d4
        L_0x002e:
            r12 = 62
            if (r11 != r12) goto L_0x0046
            if (r9 == 0) goto L_0x00d3
            boolean r0 = r1.tokenize
            if (r0 == 0) goto L_0x0045
            int r0 = r1.pos
            int r0 = r0 + -2
            r1.posEnd = r0
            if (r4 == 0) goto L_0x0045
            int r0 = r1.pcEnd
            int r0 = r0 - r6
            r1.pcEnd = r0
        L_0x0045:
            return r6
        L_0x0046:
            if (r8 != r7) goto L_0x00d3
            boolean r9 = r1.isS(r11)     // Catch:{ EOFException -> 0x0157 }
            if (r9 == 0) goto L_0x00d3
            int r8 = r1.pos     // Catch:{ EOFException -> 0x0157 }
            int r8 = r8 - r6
            int r9 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x0157 }
            int r8 = r8 + r9
            int r9 = r8 - r0
            r12 = 3
            if (r9 != r12) goto L_0x00d3
            char[] r9 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            char r9 = r9[r0]     // Catch:{ EOFException -> 0x0157 }
            r13 = 120(0x78, float:1.68E-43)
            if (r9 == r13) goto L_0x0069
            char[] r9 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            char r9 = r9[r0]     // Catch:{ EOFException -> 0x0157 }
            r14 = 88
            if (r9 != r14) goto L_0x00d3
        L_0x0069:
            char[] r9 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            int r14 = r0 + 1
            char r9 = r9[r14]     // Catch:{ EOFException -> 0x0157 }
            r15 = 109(0x6d, float:1.53E-43)
            if (r9 == r15) goto L_0x007b
            char[] r9 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            char r9 = r9[r14]     // Catch:{ EOFException -> 0x0157 }
            r7 = 77
            if (r9 != r7) goto L_0x00d3
        L_0x007b:
            char[] r7 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            int r9 = r0 + 2
            char r7 = r7[r9]     // Catch:{ EOFException -> 0x0157 }
            r6 = 108(0x6c, float:1.51E-43)
            if (r7 == r6) goto L_0x008d
            char[] r7 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            char r7 = r7[r9]     // Catch:{ EOFException -> 0x0157 }
            r5 = 76
            if (r7 != r5) goto L_0x00d3
        L_0x008d:
            r4 = 0
            if (r0 > r12) goto L_0x00cb
            char[] r5 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            char r5 = r5[r0]     // Catch:{ EOFException -> 0x0157 }
            if (r5 == r13) goto L_0x00ab
            char[] r5 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            char r5 = r5[r14]     // Catch:{ EOFException -> 0x0157 }
            if (r5 == r15) goto L_0x00ab
            char[] r5 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            char r5 = r5[r9]     // Catch:{ EOFException -> 0x0157 }
            if (r5 != r6) goto L_0x00a3
            goto L_0x00ab
        L_0x00a3:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x0157 }
            java.lang.String r5 = "XMLDecl must have xml name in lowercase"
            r0.<init>(r5, r1, r4)     // Catch:{ EOFException -> 0x0157 }
            throw r0     // Catch:{ EOFException -> 0x0157 }
        L_0x00ab:
            r1.parseXmlDecl(r11)     // Catch:{ EOFException -> 0x0157 }
            boolean r4 = r1.tokenize     // Catch:{ EOFException -> 0x0157 }
            if (r4 == 0) goto L_0x00b8
            int r4 = r1.pos     // Catch:{ EOFException -> 0x0157 }
            int r4 = r4 + -2
            r1.posEnd = r4     // Catch:{ EOFException -> 0x0157 }
        L_0x00b8:
            int r4 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x0157 }
            int r0 = r0 - r4
            int r0 = r0 + r12
            int r4 = r1.pos     // Catch:{ EOFException -> 0x0157 }
            int r4 = r4 + -2
            int r4 = r4 - r0
            char[] r5 = r1.buf     // Catch:{ EOFException -> 0x0157 }
            java.lang.String r0 = r1.newString(r5, r0, r4)     // Catch:{ EOFException -> 0x0157 }
            r1.xmlDeclContent = r0     // Catch:{ EOFException -> 0x0157 }
            r0 = 0
            return r0
        L_0x00cb:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ EOFException -> 0x0157 }
            java.lang.String r5 = "processing instruction can not have PITarget with reserveld xml name"
            r0.<init>(r5, r1, r4)     // Catch:{ EOFException -> 0x0157 }
            throw r0     // Catch:{ EOFException -> 0x0157 }
        L_0x00d3:
            r9 = 0
        L_0x00d4:
            if (r4 == 0) goto L_0x0153
            r5 = 13
            r6 = 10
            if (r11 != r5) goto L_0x0114
            boolean r5 = r1.usePC     // Catch:{ EOFException -> 0x0157 }
            if (r5 != 0) goto L_0x00f7
            int r5 = r1.pos     // Catch:{ EOFException -> 0x0157 }
            r7 = 1
            int r5 = r5 - r7
            r1.posEnd = r5     // Catch:{ EOFException -> 0x0157 }
            int r7 = r1.posStart     // Catch:{ EOFException -> 0x0157 }
            if (r5 <= r7) goto L_0x00ee
            r16.joinPC()     // Catch:{ EOFException -> 0x0157 }
            goto L_0x00f7
        L_0x00ee:
            r5 = 1
            r1.usePC = r5     // Catch:{ EOFException -> 0x0157 }
            r7 = 0
            r1.pcEnd = r7     // Catch:{ EOFException -> 0x0157 }
            r1.pcStart = r7     // Catch:{ EOFException -> 0x0157 }
            goto L_0x00f9
        L_0x00f7:
            r5 = 1
            r7 = 0
        L_0x00f9:
            int r10 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            char[] r11 = r1.pc     // Catch:{ EOFException -> 0x0157 }
            int r11 = r11.length     // Catch:{ EOFException -> 0x0157 }
            if (r10 < r11) goto L_0x0105
            int r10 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            r1.ensurePC(r10)     // Catch:{ EOFException -> 0x0157 }
        L_0x0105:
            char[] r10 = r1.pc     // Catch:{ EOFException -> 0x0157 }
            int r11 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            int r12 = r11 + 1
            r1.pcEnd = r12     // Catch:{ EOFException -> 0x0157 }
            r10[r11] = r6     // Catch:{ EOFException -> 0x0157 }
            r6 = 1
            r7 = -1
            r10 = 1
            goto L_0x0023
        L_0x0114:
            r5 = 1
            r7 = 0
            if (r11 != r6) goto L_0x0138
            if (r10 != 0) goto L_0x0134
            boolean r10 = r1.usePC     // Catch:{ EOFException -> 0x0157 }
            if (r10 == 0) goto L_0x0134
            int r10 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            char[] r11 = r1.pc     // Catch:{ EOFException -> 0x0157 }
            int r11 = r11.length     // Catch:{ EOFException -> 0x0157 }
            if (r10 < r11) goto L_0x012a
            int r10 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            r1.ensurePC(r10)     // Catch:{ EOFException -> 0x0157 }
        L_0x012a:
            char[] r10 = r1.pc     // Catch:{ EOFException -> 0x0157 }
            int r11 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            int r12 = r11 + 1
            r1.pcEnd = r12     // Catch:{ EOFException -> 0x0157 }
            r10[r11] = r6     // Catch:{ EOFException -> 0x0157 }
        L_0x0134:
            r6 = 1
            r7 = -1
            goto L_0x0022
        L_0x0138:
            boolean r6 = r1.usePC     // Catch:{ EOFException -> 0x0157 }
            if (r6 == 0) goto L_0x0134
            int r6 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            char[] r10 = r1.pc     // Catch:{ EOFException -> 0x0157 }
            int r10 = r10.length     // Catch:{ EOFException -> 0x0157 }
            if (r6 < r10) goto L_0x0148
            int r6 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            r1.ensurePC(r6)     // Catch:{ EOFException -> 0x0157 }
        L_0x0148:
            char[] r6 = r1.pc     // Catch:{ EOFException -> 0x0157 }
            int r10 = r1.pcEnd     // Catch:{ EOFException -> 0x0157 }
            int r12 = r10 + 1
            r1.pcEnd = r12     // Catch:{ EOFException -> 0x0157 }
            r6[r10] = r11     // Catch:{ EOFException -> 0x0157 }
            goto L_0x0134
        L_0x0153:
            r6 = 1
            r7 = -1
            goto L_0x0023
        L_0x0157:
            r0 = move-exception
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.String r6 = "processing instruction started on line "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = " and column "
            r5.append(r2)
            r5.append(r3)
            java.lang.String r2 = " was not closed"
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r4.<init>(r2, r1, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parsePI():boolean");
    }

    static {
        setNameStart(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
            setNameStart(c);
        }
        setNameStart('_');
        for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
            setNameStart(c2);
        }
        for (char c3 = 192; c3 <= 767; c3 = (char) (c3 + 1)) {
            setNameStart(c3);
        }
        for (char c4 = 880; c4 <= 893; c4 = (char) (c4 + 1)) {
            setNameStart(c4);
        }
        for (char c5 = 895; c5 < 1024; c5 = (char) (c5 + 1)) {
            setNameStart(c5);
        }
        setName('-');
        setName('.');
        for (char c6 = '0'; c6 <= '9'; c6 = (char) (c6 + 1)) {
            setName(c6);
        }
        setName(Typography.middleDot);
        for (char c7 = 768; c7 <= 879; c7 = (char) (c7 + 1)) {
            setName(c7);
        }
    }

    /* access modifiers changed from: protected */
    public void parseXmlDecl(char c) throws XmlPullParserException, IOException {
        this.preventBufferCompaction = true;
        this.bufStart = 0;
        char skipS = skipS(requireInput(skipS(c), VERSION));
        if (skipS == '=') {
            char skipS2 = skipS(more());
            if (skipS2 == '\'' || skipS2 == '\"') {
                int i = this.pos;
                char more = more();
                while (more != skipS2) {
                    if ((more < 'a' || more > 'z') && ((more < 'A' || more > 'Z') && !((more >= '0' && more <= '9') || more == '_' || more == '.' || more == ':' || more == '-'))) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("<?xml version value expected to be in ([a-zA-Z0-9_.:] | '-') not ");
                        stringBuffer.append(printable(more));
                        throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
                    }
                    more = more();
                }
                parseXmlDeclWithVersion(i, this.pos - 1);
                this.preventBufferCompaction = false;
                return;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("expected apostrophe (') or quotation mark (\") after version and not ");
            stringBuffer2.append(printable(skipS2));
            throw new XmlPullParserException(stringBuffer2.toString(), this, (Throwable) null);
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("expected equals sign (=) after version and not ");
        stringBuffer3.append(printable(skipS));
        throw new XmlPullParserException(stringBuffer3.toString(), this, (Throwable) null);
    }

    /* access modifiers changed from: protected */
    public void parseXmlDeclWithVersion(int i, int i2) throws XmlPullParserException, IOException {
        char c;
        int i3 = i;
        int i4 = i2 - i3;
        if (i4 == 3) {
            char[] cArr = this.buf;
            if (cArr[i3] == '1' && cArr[i3 + 1] == '.') {
                char c2 = '0';
                if (cArr[i3 + 2] == '0') {
                    this.xmlDeclVersion = newString(cArr, i3, i4);
                    char skipS = skipS(more());
                    if (skipS == 'e') {
                        char skipS2 = skipS(requireInput(more(), NCODING));
                        if (skipS2 == '=') {
                            char skipS3 = skipS(more());
                            if (skipS3 == '\'' || skipS3 == '\"') {
                                int i5 = this.pos;
                                char more = more();
                                if ((more < 'a' || more > 'z') && (more < 'A' || more > 'Z')) {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append("<?xml encoding name expected to start with [A-Za-z] not ");
                                    stringBuffer.append(printable(more));
                                    throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
                                }
                                char more2 = more();
                                while (more2 != skipS3) {
                                    if ((more2 < 'a' || more2 > 'z') && ((more2 < 'A' || more2 > 'Z') && !((more2 >= c2 && more2 <= '9') || more2 == '.' || more2 == '_' || more2 == '-'))) {
                                        StringBuffer stringBuffer2 = new StringBuffer();
                                        stringBuffer2.append("<?xml encoding value expected to be in ([A-Za-z0-9._] | '-') not ");
                                        stringBuffer2.append(printable(more2));
                                        throw new XmlPullParserException(stringBuffer2.toString(), this, (Throwable) null);
                                    }
                                    more2 = more();
                                    c2 = '0';
                                }
                                this.inputEncoding = newString(this.buf, i5, (this.pos - 1) - i5);
                                skipS = more();
                            } else {
                                StringBuffer stringBuffer3 = new StringBuffer();
                                stringBuffer3.append("expected apostrophe (') or quotation mark (\") after encoding and not ");
                                stringBuffer3.append(printable(skipS3));
                                throw new XmlPullParserException(stringBuffer3.toString(), this, (Throwable) null);
                            }
                        } else {
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append("expected equals sign (=) after encoding and not ");
                            stringBuffer4.append(printable(skipS2));
                            throw new XmlPullParserException(stringBuffer4.toString(), this, (Throwable) null);
                        }
                    }
                    char skipS4 = skipS(skipS);
                    if (skipS4 == 's') {
                        char skipS5 = skipS(requireInput(more(), TANDALONE));
                        if (skipS5 == '=') {
                            char skipS6 = skipS(more());
                            if (skipS6 == '\'' || skipS6 == '\"') {
                                char more3 = more();
                                if (more3 == 'y') {
                                    c = requireInput(more3, YES);
                                    this.xmlDeclStandalone = new Boolean(true);
                                } else if (more3 == 'n') {
                                    c = requireInput(more3, NO);
                                    this.xmlDeclStandalone = new Boolean(false);
                                } else {
                                    StringBuffer stringBuffer5 = new StringBuffer();
                                    stringBuffer5.append("expected 'yes' or 'no' after standalone and not ");
                                    stringBuffer5.append(printable(more3));
                                    throw new XmlPullParserException(stringBuffer5.toString(), this, (Throwable) null);
                                }
                                if (c == skipS6) {
                                    skipS4 = more();
                                } else {
                                    StringBuffer stringBuffer6 = new StringBuffer();
                                    stringBuffer6.append("expected ");
                                    stringBuffer6.append(skipS6);
                                    stringBuffer6.append(" after standalone value not ");
                                    stringBuffer6.append(printable(c));
                                    throw new XmlPullParserException(stringBuffer6.toString(), this, (Throwable) null);
                                }
                            } else {
                                StringBuffer stringBuffer7 = new StringBuffer();
                                stringBuffer7.append("expected apostrophe (') or quotation mark (\") after encoding and not ");
                                stringBuffer7.append(printable(skipS6));
                                throw new XmlPullParserException(stringBuffer7.toString(), this, (Throwable) null);
                            }
                        } else {
                            StringBuffer stringBuffer8 = new StringBuffer();
                            stringBuffer8.append("expected equals sign (=) after standalone and not ");
                            stringBuffer8.append(printable(skipS5));
                            throw new XmlPullParserException(stringBuffer8.toString(), this, (Throwable) null);
                        }
                    }
                    char skipS7 = skipS(skipS4);
                    if (skipS7 == '?') {
                        char more4 = more();
                        if (more4 != '>') {
                            StringBuffer stringBuffer9 = new StringBuffer();
                            stringBuffer9.append("expected ?> as last part of <?xml not ");
                            stringBuffer9.append(printable(more4));
                            throw new XmlPullParserException(stringBuffer9.toString(), this, (Throwable) null);
                        }
                        return;
                    }
                    StringBuffer stringBuffer10 = new StringBuffer();
                    stringBuffer10.append("expected ?> as last part of <?xml not ");
                    stringBuffer10.append(printable(skipS7));
                    throw new XmlPullParserException(stringBuffer10.toString(), this, (Throwable) null);
                }
            }
        }
        StringBuffer stringBuffer11 = new StringBuffer();
        stringBuffer11.append("only 1.0 is supported as <?xml version not '");
        stringBuffer11.append(printable(new String(this.buf, i3, i4)));
        stringBuffer11.append("'");
        throw new XmlPullParserException(stringBuffer11.toString(), this, (Throwable) null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0097, code lost:
        if (r5 != 10) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0099, code lost:
        if (r2 != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009d, code lost:
        if (r8.usePC == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009f, code lost:
        r2 = r8.pcEnd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a4, code lost:
        if (r2 < r8.pc.length) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a6, code lost:
        ensurePC(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a9, code lost:
        r2 = r8.pc;
        r5 = r8.pcEnd;
        r8.pcEnd = r5 + 1;
        r2[r5] = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b6, code lost:
        if (r8.usePC == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b8, code lost:
        r2 = r8.pcEnd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00bd, code lost:
        if (r2 < r8.pc.length) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00bf, code lost:
        ensurePC(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c2, code lost:
        r2 = r8.pc;
        r6 = r8.pcEnd;
        r8.pcEnd = r6 + 1;
        r2[r6] = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseDocdecl() throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r8 = this;
            char r0 = r8.more()
            r1 = 0
            java.lang.String r2 = "expected <!DOCTYPE"
            r3 = 79
            if (r0 != r3) goto L_0x00ec
            char r0 = r8.more()
            r3 = 67
            if (r0 != r3) goto L_0x00e6
            char r0 = r8.more()
            r3 = 84
            if (r0 != r3) goto L_0x00e0
            char r0 = r8.more()
            r3 = 89
            if (r0 != r3) goto L_0x00da
            char r0 = r8.more()
            r3 = 80
            if (r0 != r3) goto L_0x00d4
            char r0 = r8.more()
            r3 = 69
            if (r0 != r3) goto L_0x00ce
            int r0 = r8.pos
            r8.posStart = r0
            boolean r0 = r8.tokenize
            r3 = 0
            r4 = 1
            if (r0 != r4) goto L_0x0043
            boolean r0 = r8.roundtripSupported
            if (r0 != 0) goto L_0x0043
            r0 = 1
            goto L_0x0044
        L_0x0043:
            r0 = 0
        L_0x0044:
            r1 = 0
        L_0x0045:
            r2 = 0
        L_0x0046:
            char r5 = r8.more()
            r6 = 91
            if (r5 != r6) goto L_0x0050
            int r1 = r1 + 1
        L_0x0050:
            r6 = 93
            if (r5 != r6) goto L_0x0056
            int r1 = r1 + -1
        L_0x0056:
            r6 = 62
            if (r5 != r6) goto L_0x0062
            if (r1 != 0) goto L_0x0062
            int r0 = r8.pos
            int r0 = r0 - r4
            r8.posEnd = r0
            return
        L_0x0062:
            if (r0 == 0) goto L_0x0046
            r6 = 13
            r7 = 10
            if (r5 != r6) goto L_0x0097
            boolean r2 = r8.usePC
            if (r2 != 0) goto L_0x0081
            int r2 = r8.pos
            int r2 = r2 - r4
            r8.posEnd = r2
            int r5 = r8.posStart
            if (r2 <= r5) goto L_0x007b
            r8.joinPC()
            goto L_0x0081
        L_0x007b:
            r8.usePC = r4
            r8.pcEnd = r3
            r8.pcStart = r3
        L_0x0081:
            int r2 = r8.pcEnd
            char[] r5 = r8.pc
            int r5 = r5.length
            if (r2 < r5) goto L_0x008b
            r8.ensurePC(r2)
        L_0x008b:
            char[] r2 = r8.pc
            int r5 = r8.pcEnd
            int r6 = r5 + 1
            r8.pcEnd = r6
            r2[r5] = r7
            r2 = 1
            goto L_0x0046
        L_0x0097:
            if (r5 != r7) goto L_0x00b4
            if (r2 != 0) goto L_0x0045
            boolean r2 = r8.usePC
            if (r2 == 0) goto L_0x0045
            int r2 = r8.pcEnd
            char[] r5 = r8.pc
            int r5 = r5.length
            if (r2 < r5) goto L_0x00a9
            r8.ensurePC(r2)
        L_0x00a9:
            char[] r2 = r8.pc
            int r5 = r8.pcEnd
            int r6 = r5 + 1
            r8.pcEnd = r6
            r2[r5] = r7
            goto L_0x0045
        L_0x00b4:
            boolean r2 = r8.usePC
            if (r2 == 0) goto L_0x0045
            int r2 = r8.pcEnd
            char[] r6 = r8.pc
            int r6 = r6.length
            if (r2 < r6) goto L_0x00c2
            r8.ensurePC(r2)
        L_0x00c2:
            char[] r2 = r8.pc
            int r6 = r8.pcEnd
            int r7 = r6 + 1
            r8.pcEnd = r7
            r2[r6] = r5
            goto L_0x0045
        L_0x00ce:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            r0.<init>(r2, r8, r1)
            throw r0
        L_0x00d4:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            r0.<init>(r2, r8, r1)
            throw r0
        L_0x00da:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            r0.<init>(r2, r8, r1)
            throw r0
        L_0x00e0:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            r0.<init>(r2, r8, r1)
            throw r0
        L_0x00e6:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            r0.<init>(r2, r8, r1)
            throw r0
        L_0x00ec:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            r0.<init>(r2, r8, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseDocdecl():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00cd, code lost:
        if (r8 != 10) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00cf, code lost:
        if (r7 != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d3, code lost:
        if (r11.usePC == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00da, code lost:
        if (r11.pcEnd < r11.pc.length) goto L_0x00e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00dc, code lost:
        ensurePC(r11.pcEnd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e1, code lost:
        r7 = r11.pc;
        r8 = r11.pcEnd;
        r11.pcEnd = r8 + 1;
        r7[r8] = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ef, code lost:
        if (r11.usePC == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00f6, code lost:
        if (r11.pcEnd < r11.pc.length) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f8, code lost:
        ensurePC(r11.pcEnd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00fd, code lost:
        r7 = r11.pc;
        r9 = r11.pcEnd;
        r11.pcEnd = r9 + 1;
        r7[r9] = r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseCDSect(boolean r12) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r11 = this;
            char r0 = r11.more()
            r1 = 0
            java.lang.String r2 = "expected <[CDATA[ for comment start"
            r3 = 67
            if (r0 != r3) goto L_0x014c
            char r0 = r11.more()
            r3 = 68
            if (r0 != r3) goto L_0x0146
            char r0 = r11.more()
            r3 = 65
            if (r0 != r3) goto L_0x0140
            char r0 = r11.more()
            r4 = 84
            if (r0 != r4) goto L_0x013a
            char r0 = r11.more()
            if (r0 != r3) goto L_0x0134
            char r0 = r11.more()
            r3 = 91
            if (r0 != r3) goto L_0x012e
            int r0 = r11.pos
            int r1 = r11.bufAbsoluteStart
            int r0 = r0 + r1
            int r1 = r11.lineNumber
            int r2 = r11.columnNumber
            boolean r3 = r11.tokenize
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x0047
            boolean r3 = r11.roundtripSupported
            if (r3 != 0) goto L_0x0045
            goto L_0x0047
        L_0x0045:
            r3 = 0
            goto L_0x0048
        L_0x0047:
            r3 = 1
        L_0x0048:
            if (r3 == 0) goto L_0x0060
            if (r12 == 0) goto L_0x0060
            boolean r12 = r11.usePC     // Catch:{ EOFException -> 0x0109 }
            if (r12 != 0) goto L_0x0060
            int r12 = r11.posEnd     // Catch:{ EOFException -> 0x0109 }
            int r6 = r11.posStart     // Catch:{ EOFException -> 0x0109 }
            if (r12 <= r6) goto L_0x005a
            r11.joinPC()     // Catch:{ EOFException -> 0x0109 }
            goto L_0x0060
        L_0x005a:
            r11.usePC = r4     // Catch:{ EOFException -> 0x0109 }
            r11.pcEnd = r5     // Catch:{ EOFException -> 0x0109 }
            r11.pcStart = r5     // Catch:{ EOFException -> 0x0109 }
        L_0x0060:
            r12 = 0
            r6 = 0
        L_0x0062:
            r7 = 0
        L_0x0063:
            char r8 = r11.more()     // Catch:{ EOFException -> 0x0109 }
            r9 = 93
            if (r8 != r9) goto L_0x0071
            if (r12 != 0) goto L_0x006f
            r12 = 1
            goto L_0x0093
        L_0x006f:
            r6 = 1
            goto L_0x0093
        L_0x0071:
            r12 = 62
            if (r8 != r12) goto L_0x0092
            if (r6 == 0) goto L_0x008f
            if (r3 == 0) goto L_0x0083
            boolean r12 = r11.usePC
            if (r12 == 0) goto L_0x0083
            int r12 = r11.pcEnd
            int r12 = r12 + -2
            r11.pcEnd = r12
        L_0x0083:
            int r12 = r11.bufAbsoluteStart
            int r0 = r0 - r12
            r11.posStart = r0
            int r12 = r11.pos
            int r12 = r12 + -3
            r11.posEnd = r12
            return
        L_0x008f:
            r12 = 0
            r6 = 0
            goto L_0x0093
        L_0x0092:
            r12 = 0
        L_0x0093:
            if (r3 == 0) goto L_0x0063
            r9 = 13
            r10 = 10
            if (r8 != r9) goto L_0x00cd
            int r7 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0109 }
            int r7 = r0 - r7
            r11.posStart = r7     // Catch:{ EOFException -> 0x0109 }
            int r8 = r11.pos     // Catch:{ EOFException -> 0x0109 }
            r11.posEnd = r8     // Catch:{ EOFException -> 0x0109 }
            boolean r9 = r11.usePC     // Catch:{ EOFException -> 0x0109 }
            if (r9 != 0) goto L_0x00b5
            if (r8 <= r7) goto L_0x00af
            r11.joinPC()     // Catch:{ EOFException -> 0x0109 }
            goto L_0x00b5
        L_0x00af:
            r11.usePC = r4     // Catch:{ EOFException -> 0x0109 }
            r11.pcEnd = r5     // Catch:{ EOFException -> 0x0109 }
            r11.pcStart = r5     // Catch:{ EOFException -> 0x0109 }
        L_0x00b5:
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            char[] r8 = r11.pc     // Catch:{ EOFException -> 0x0109 }
            int r8 = r8.length     // Catch:{ EOFException -> 0x0109 }
            if (r7 < r8) goto L_0x00c1
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            r11.ensurePC(r7)     // Catch:{ EOFException -> 0x0109 }
        L_0x00c1:
            char[] r7 = r11.pc     // Catch:{ EOFException -> 0x0109 }
            int r8 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            int r9 = r8 + 1
            r11.pcEnd = r9     // Catch:{ EOFException -> 0x0109 }
            r7[r8] = r10     // Catch:{ EOFException -> 0x0109 }
            r7 = 1
            goto L_0x0063
        L_0x00cd:
            if (r8 != r10) goto L_0x00ed
            if (r7 != 0) goto L_0x0062
            boolean r7 = r11.usePC     // Catch:{ EOFException -> 0x0109 }
            if (r7 == 0) goto L_0x0062
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            char[] r8 = r11.pc     // Catch:{ EOFException -> 0x0109 }
            int r8 = r8.length     // Catch:{ EOFException -> 0x0109 }
            if (r7 < r8) goto L_0x00e1
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            r11.ensurePC(r7)     // Catch:{ EOFException -> 0x0109 }
        L_0x00e1:
            char[] r7 = r11.pc     // Catch:{ EOFException -> 0x0109 }
            int r8 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            int r9 = r8 + 1
            r11.pcEnd = r9     // Catch:{ EOFException -> 0x0109 }
            r7[r8] = r10     // Catch:{ EOFException -> 0x0109 }
            goto L_0x0062
        L_0x00ed:
            boolean r7 = r11.usePC     // Catch:{ EOFException -> 0x0109 }
            if (r7 == 0) goto L_0x0062
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            char[] r9 = r11.pc     // Catch:{ EOFException -> 0x0109 }
            int r9 = r9.length     // Catch:{ EOFException -> 0x0109 }
            if (r7 < r9) goto L_0x00fd
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            r11.ensurePC(r7)     // Catch:{ EOFException -> 0x0109 }
        L_0x00fd:
            char[] r7 = r11.pc     // Catch:{ EOFException -> 0x0109 }
            int r9 = r11.pcEnd     // Catch:{ EOFException -> 0x0109 }
            int r10 = r9 + 1
            r11.pcEnd = r10     // Catch:{ EOFException -> 0x0109 }
            r7[r9] = r8     // Catch:{ EOFException -> 0x0109 }
            goto L_0x0062
        L_0x0109:
            r12 = move-exception
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            java.lang.String r4 = "CDATA section started on line "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = " and column "
            r3.append(r1)
            r3.append(r2)
            java.lang.String r1 = " was not closed"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1, r11, r12)
            throw r0
        L_0x012e:
            org.xmlpull.v1.XmlPullParserException r12 = new org.xmlpull.v1.XmlPullParserException
            r12.<init>(r2, r11, r1)
            throw r12
        L_0x0134:
            org.xmlpull.v1.XmlPullParserException r12 = new org.xmlpull.v1.XmlPullParserException
            r12.<init>(r2, r11, r1)
            throw r12
        L_0x013a:
            org.xmlpull.v1.XmlPullParserException r12 = new org.xmlpull.v1.XmlPullParserException
            r12.<init>(r2, r11, r1)
            throw r12
        L_0x0140:
            org.xmlpull.v1.XmlPullParserException r12 = new org.xmlpull.v1.XmlPullParserException
            r12.<init>(r2, r11, r1)
            throw r12
        L_0x0146:
            org.xmlpull.v1.XmlPullParserException r12 = new org.xmlpull.v1.XmlPullParserException
            r12.<init>(r2, r11, r1)
            throw r12
        L_0x014c:
            org.xmlpull.v1.XmlPullParserException r12 = new org.xmlpull.v1.XmlPullParserException
            r12.<init>(r2, r11, r1)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseCDSect(boolean):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fillBuf() throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            r6 = this;
            java.io.Reader r0 = r6.reader
            if (r0 == 0) goto L_0x017c
            int r0 = r6.bufEnd
            int r1 = r6.bufSoftLimit
            r2 = 1
            r3 = 0
            if (r0 <= r1) goto L_0x0079
            int r0 = r6.bufStart
            if (r0 <= r1) goto L_0x0012
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            boolean r1 = r6.preventBufferCompaction
            if (r1 == 0) goto L_0x001a
            r0 = 0
        L_0x0018:
            r1 = 1
            goto L_0x0028
        L_0x001a:
            if (r0 != 0) goto L_0x0027
            int r1 = r6.bufStart
            char[] r4 = r6.buf
            int r4 = r4.length
            int r4 = r4 / 2
            if (r1 >= r4) goto L_0x0026
            goto L_0x0018
        L_0x0026:
            r0 = 1
        L_0x0027:
            r1 = 0
        L_0x0028:
            if (r0 == 0) goto L_0x0035
            char[] r0 = r6.buf
            int r1 = r6.bufStart
            int r4 = r6.bufEnd
            int r4 = r4 - r1
            java.lang.System.arraycopy(r0, r1, r0, r3, r4)
            goto L_0x0053
        L_0x0035:
            if (r1 == 0) goto L_0x0071
            char[] r0 = r6.buf
            int r1 = r0.length
            int r1 = r1 * 2
            char[] r1 = new char[r1]
            int r4 = r6.bufStart
            int r5 = r6.bufEnd
            int r5 = r5 - r4
            java.lang.System.arraycopy(r0, r4, r1, r3, r5)
            r6.buf = r1
            int r0 = r6.bufLoadFactor
            if (r0 <= 0) goto L_0x0053
            int r1 = r1.length
            int r0 = r0 * r1
            int r0 = r0 / 100
            r6.bufSoftLimit = r0
        L_0x0053:
            int r0 = r6.bufEnd
            int r1 = r6.bufStart
            int r0 = r0 - r1
            r6.bufEnd = r0
            int r0 = r6.pos
            int r0 = r0 - r1
            r6.pos = r0
            int r0 = r6.posStart
            int r0 = r0 - r1
            r6.posStart = r0
            int r0 = r6.posEnd
            int r0 = r0 - r1
            r6.posEnd = r0
            int r0 = r6.bufAbsoluteStart
            int r0 = r0 + r1
            r6.bufAbsoluteStart = r0
            r6.bufStart = r3
            goto L_0x0079
        L_0x0071:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r1 = "internal error in fillBuffer()"
            r0.<init>(r1)
            throw r0
        L_0x0079:
            char[] r0 = r6.buf
            int r1 = r0.length
            int r4 = r6.bufEnd
            int r1 = r1 - r4
            r5 = 8192(0x2000, float:1.14794E-41)
            if (r1 <= r5) goto L_0x0084
            goto L_0x0087
        L_0x0084:
            int r0 = r0.length
            int r5 = r0 - r4
        L_0x0087:
            java.io.Reader r0 = r6.reader
            char[] r1 = r6.buf
            int r4 = r6.bufEnd
            int r0 = r0.read(r1, r4, r5)
            if (r0 <= 0) goto L_0x0099
            int r1 = r6.bufEnd
            int r1 = r1 + r0
            r6.bufEnd = r1
            return
        L_0x0099:
            r1 = -1
            if (r0 != r1) goto L_0x0165
            int r0 = r6.bufAbsoluteStart
            if (r0 != 0) goto L_0x00ad
            int r0 = r6.pos
            if (r0 == 0) goto L_0x00a5
            goto L_0x00ad
        L_0x00a5:
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.String r1 = "input contained no data"
            r0.<init>(r1)
            throw r0
        L_0x00ad:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            int r1 = r6.depth
            if (r1 <= 0) goto L_0x0143
            java.lang.String r1 = " - expected end tag"
            r0.append(r1)
            int r1 = r6.depth
            if (r1 <= r2) goto L_0x00c4
            java.lang.String r1 = "s"
            r0.append(r1)
        L_0x00c4:
            java.lang.String r1 = " "
            r0.append(r1)
            int r1 = r6.depth
        L_0x00cb:
            if (r1 > 0) goto L_0x0126
            java.lang.String r1 = " to close"
            r0.append(r1)
            int r1 = r6.depth
        L_0x00d4:
            if (r1 <= 0) goto L_0x0120
            int r2 = r6.depth
            if (r1 == r2) goto L_0x00df
            java.lang.String r2 = " and"
            r0.append(r2)
        L_0x00df:
            java.lang.String r2 = new java.lang.String
            char[][] r4 = r6.elRawName
            r4 = r4[r1]
            int[] r5 = r6.elRawNameEnd
            r5 = r5[r1]
            r2.<init>(r4, r3, r5)
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            java.lang.String r5 = " start tag <"
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = ">"
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r0.append(r2)
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r4 = " from line "
            r2.append(r4)
            int[] r4 = r6.elRawNameLine
            r4 = r4[r1]
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.append(r2)
            int r1 = r1 + -1
            goto L_0x00d4
        L_0x0120:
            java.lang.String r1 = ", parser stopped on"
            r0.append(r1)
            goto L_0x0143
        L_0x0126:
            java.lang.String r2 = new java.lang.String
            char[][] r4 = r6.elRawName
            r4 = r4[r1]
            int[] r5 = r6.elRawNameEnd
            r5 = r5[r1]
            r2.<init>(r4, r3, r5)
            java.lang.String r4 = "</"
            r0.append(r4)
            r0.append(r2)
            r2 = 62
            r0.append(r2)
            int r1 = r1 + -1
            goto L_0x00cb
        L_0x0143:
            java.io.EOFException r1 = new java.io.EOFException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "no more data available"
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r6.getPositionDescription()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x0165:
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "error reading input, returned "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x017c:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r1 = "reader must be set before parsing is started"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.fillBuf():void");
    }

    /* access modifiers changed from: protected */
    public char more() throws IOException, XmlPullParserException {
        if (this.pos >= this.bufEnd) {
            fillBuf();
        }
        char[] cArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        char c = cArr[i];
        if (c == 10) {
            this.lineNumber++;
            this.columnNumber = 1;
        } else {
            this.columnNumber++;
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public void ensurePC(int i) {
        char[] cArr = new char[(i > 8192 ? i * 2 : 16384)];
        System.arraycopy(this.pc, 0, cArr, 0, this.pcEnd);
        this.pc = cArr;
    }

    /* access modifiers changed from: protected */
    public void joinPC() {
        int i = this.posEnd - this.posStart;
        int i2 = this.pcEnd + i + 1;
        if (i2 >= this.pc.length) {
            ensurePC(i2);
        }
        System.arraycopy(this.buf, this.posStart, this.pc, this.pcEnd, i);
        this.pcEnd += i;
        this.usePC = true;
    }

    /* access modifiers changed from: protected */
    public char requireInput(char c, char[] cArr) throws XmlPullParserException, IOException {
        int i = 0;
        while (i < cArr.length) {
            if (c == cArr[i]) {
                c = more();
                i++;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("expected ");
                stringBuffer.append(printable(cArr[i]));
                stringBuffer.append(" in ");
                stringBuffer.append(new String(cArr));
                stringBuffer.append(" and not ");
                stringBuffer.append(printable(c));
                throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public char requireNextS() throws XmlPullParserException, IOException {
        char more = more();
        if (isS(more)) {
            return skipS(more);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("white space is required and not ");
        stringBuffer.append(printable(more));
        throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
    }

    /* access modifiers changed from: protected */
    public char skipS(char c) throws XmlPullParserException, IOException {
        while (isS(c)) {
            c = more();
        }
        return c;
    }

    private static final void setName(char c) {
        lookupNameChar[c] = true;
    }

    private static final void setNameStart(char c) {
        lookupNameStartChar[c] = true;
        setName(c);
    }

    /* access modifiers changed from: protected */
    public boolean isNameStartChar(char c) {
        return (c < 1024 && lookupNameStartChar[c]) || (c >= 1024 && c <= 8231) || ((c >= 8234 && c <= 8591) || (c >= 10240 && c <= 65519));
    }

    /* access modifiers changed from: protected */
    public boolean isNameChar(char c) {
        return (c < 1024 && lookupNameChar[c]) || (c >= 1024 && c <= 8231) || ((c >= 8234 && c <= 8591) || (c >= 10240 && c <= 65519));
    }

    /* access modifiers changed from: protected */
    public String printable(char c) {
        if (c == 10) {
            return "\\n";
        }
        if (c == 13) {
            return "\\r";
        }
        if (c == 9) {
            return "\\t";
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c > 127 || c < ' ') {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\\u");
            stringBuffer.append(Integer.toHexString(c));
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("");
        stringBuffer2.append(c);
        return stringBuffer2.toString();
    }

    /* access modifiers changed from: protected */
    public String printable(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 10);
        for (int i = 0; i < length; i++) {
            stringBuffer.append(printable(str.charAt(i)));
        }
        return stringBuffer.toString();
    }
}
