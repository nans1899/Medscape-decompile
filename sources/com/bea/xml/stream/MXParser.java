package com.bea.xml.stream;

import com.bea.xml.stream.events.DTDEvent;
import com.bea.xml.stream.reader.XmlReader;
import com.bea.xml.stream.util.ElementTypeNames;
import com.bea.xml.stream.util.EmptyIterator;
import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.wutka.dtd.DTD;
import com.wutka.dtd.DTDAttlist;
import com.wutka.dtd.DTDAttribute;
import com.wutka.dtd.DTDEntity;
import com.wutka.dtd.DTDNotation;
import com.wutka.dtd.DTDParser;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.NotationDeclaration;
import kotlin.text.Typography;
import net.bytebuddy.asm.Advice;

public class MXParser implements XMLStreamReader, Location {
    protected static final char CHAR_UTF8_BOM = '﻿';
    private static final int DOCDECL = 32768;
    protected static final char[] ENCODING = {'e', 'n', 'c', 'o', Advice.OffsetMapping.ForOrigin.Renderer.ForDescriptor.SYMBOL, 'i', 'n', 'g'};
    static final String EOF_MSG = "Unexpected end of stream";
    protected static final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
    public static final String FEATURE_PROCESS_DOCDECL = "http://xmlpull.org/v1/doc/features.html#process-docdecl";
    public static final String FEATURE_PROCESS_NAMESPACES = "http://xmlpull.org/v1/doc/features.html#process-namespaces";
    public static final String FEATURE_STAX_ENTITIES = "javax.xml.stream.entities";
    public static final String FEATURE_STAX_NOTATIONS = "javax.xml.stream.notations";
    protected static final String FEATURE_XML_ROUNDTRIP = "http://xmlpull.org/v1/doc/features.html#xml-roundtrip";
    protected static final int LOOKUP_MAX = 1024;
    protected static final char LOOKUP_MAX_CHAR = 'Ѐ';
    protected static final int MAX_UNICODE_CHAR = 1114111;
    protected static final char[] NO = {'n', 'o'};
    private static final char[] NO_CHARS = new char[0];
    private static final int[] NO_INTS = new int[0];
    public static final String NO_NAMESPACE = null;
    private static final String[] NO_STRINGS = new String[0];
    protected static final int READ_CHUNK_SIZE = 8192;
    protected static final char[] STANDALONE = {Advice.OffsetMapping.ForOrigin.Renderer.ForJavaSignature.SYMBOL, Advice.OffsetMapping.ForOrigin.Renderer.ForTypeName.SYMBOL, 'a', 'n', Advice.OffsetMapping.ForOrigin.Renderer.ForDescriptor.SYMBOL, 'a', 'l', 'o', 'n', 'e'};
    private static final int TEXT = 16384;
    private static final boolean TRACE_SIZING = false;
    public static final String[] TYPES = {"[UNKNOWN]", "START_ELEMENT", "END_ELEMENT", "PROCESSING_INSTRUCTION", "CHARACTERS", "COMMENT", "SPACE", "START_DOCUMENT", "END_DOCUMENT", "ENTITY_REFERENCE", "ATTRIBUTE", "DTD", "CDATA", "NAMESPACE", "NOTATION_DECLARATION", "ENTITY_DECLARATION"};
    protected static final char[] VERSION = {'v', 'e', Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName.SYMBOL, Advice.OffsetMapping.ForOrigin.Renderer.ForJavaSignature.SYMBOL, 'i', 'o', 'n'};
    protected static final char[] YES = {'y', 'e', Advice.OffsetMapping.ForOrigin.Renderer.ForJavaSignature.SYMBOL};
    static /* synthetic */ Class class$com$wutka$dtd$DTDAttlist;
    static /* synthetic */ Class class$com$wutka$dtd$DTDEntity;
    static /* synthetic */ Class class$com$wutka$dtd$DTDNotation;
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
    protected int bufLoadFactor;
    protected int bufSoftLimit;
    protected int bufStart;
    protected String charEncodingScheme;
    protected char[] charRefOneCharBuf;
    protected char[] charRefTwoCharBuf;
    protected int columnNumber;
    private ConfigurationContextBase configurationContext;
    protected HashMap defaultAttributes;
    protected int depth;
    protected String[] elName;
    protected int[] elNamespaceCount;
    protected String[] elPrefix;
    protected char[][] elRawName;
    protected int[] elRawNameEnd;
    protected String[] elUri;
    protected boolean emptyElementTag;
    protected int entityEnd;
    protected String[] entityName;
    protected char[][] entityNameBuf;
    protected int[] entityNameHash;
    protected String entityRefName;
    protected String[] entityReplacement;
    protected char[][] entityReplacementBuf;
    protected char[] entityValue;
    protected int eventType;
    protected String inputEncoding;
    protected int lineNumber;
    protected int localNamespaceEnd;
    protected String[] localNamespacePrefix;
    protected int[] localNamespacePrefixHash;
    protected String[] localNamespaceUri;
    protected DTD mDtdIntSubset;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected int[] namespacePrefixHash;
    protected String[] namespaceUri;
    protected boolean pastEndTag;
    protected char[] pc;
    protected int pcEnd;
    protected int pcStart;
    protected String piData;
    protected String piTarget;
    protected int pos;
    protected int posEnd;
    protected int posStart;
    protected boolean processNamespaces = true;
    protected boolean reachedEnd;
    protected Reader reader;
    private boolean reportCdataEvent = false;
    protected boolean roundtripSupported = true;
    protected boolean seenAmpersand;
    protected boolean seenDocdecl;
    protected boolean seenEndTag;
    protected boolean seenMarkup;
    protected boolean seenRoot;
    protected boolean seenStartTag;
    protected boolean standalone = false;
    protected boolean standaloneSet = false;
    protected String text;
    protected boolean tokenize;
    protected boolean usePC;
    protected String xmlVersion = null;

    private static final String checkNull(String str) {
        return str != null ? str : "";
    }

    private static boolean isElementEvent(int i) {
        return i == 1 || i == 2;
    }

    public void close() throws XMLStreamException {
    }

    public Location getLocation() {
        return this;
    }

    public String getLocationURI() {
        return null;
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isS(char c) {
        return c == ' ' || c == 10 || c == 13 || c == 9;
    }

    /* access modifiers changed from: protected */
    public void resetStringCache() {
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
            char[][] cArr = new char[i2][];
            if (z) {
                System.arraycopy(this.elRawName, 0, cArr, 0, length);
            }
            this.elRawName = cArr;
        }
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
    public void checkCharValidity(int i, boolean z) throws XMLStreamException {
        if (i < 32) {
            if (!isS((char) i)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Illegal white space character (code 0x");
                stringBuffer.append(Integer.toHexString(i));
                stringBuffer.append(")");
                throw new XMLStreamException(stringBuffer.toString());
            }
        } else if (i < 55296) {
        } else {
            if (i <= 57343) {
                if (!z) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Illegal character (code 0x");
                    stringBuffer2.append(Integer.toHexString(i));
                    stringBuffer2.append("): surrogate characters are not valid XML characters");
                    throw new XMLStreamException(stringBuffer2.toString(), getLocation());
                }
            } else if (i > MAX_UNICODE_CHAR) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Illegal character (code 0x");
                stringBuffer3.append(Integer.toHexString(i));
                stringBuffer3.append("), past max. Unicode character 0x");
                stringBuffer3.append(Integer.toHexString(MAX_UNICODE_CHAR));
                throw new XMLStreamException(stringBuffer3.toString(), getLocation());
            }
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

    /* access modifiers changed from: protected */
    public void ensureLocalNamespacesCapacity(int i) {
        String[] strArr = this.localNamespacePrefix;
        if (i >= (strArr != null ? strArr.length : 0)) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr2 = new String[i2];
            String[] strArr3 = new String[i2];
            String[] strArr4 = this.localNamespacePrefix;
            if (strArr4 != null) {
                System.arraycopy(strArr4, 0, strArr2, 0, this.localNamespaceEnd);
                System.arraycopy(this.localNamespaceUri, 0, strArr3, 0, this.localNamespaceEnd);
            }
            this.localNamespacePrefix = strArr2;
            this.localNamespaceUri = strArr3;
            if (!this.allStringsInterned) {
                int[] iArr = new int[i2];
                int[] iArr2 = this.localNamespacePrefixHash;
                if (iArr2 != null) {
                    System.arraycopy(iArr2, 0, iArr, 0, this.localNamespaceEnd);
                }
                this.localNamespacePrefixHash = iArr;
            }
        }
    }

    public int getLocalNamespaceCount() {
        return this.namespaceEnd - this.elNamespaceCount[this.depth - 1];
    }

    private String getLocalNamespaceURI(int i) {
        return this.namespaceUri[i];
    }

    private String getLocalNamespacePrefix(int i) {
        return this.namespacePrefix[i];
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
                System.arraycopy(this.entityNameBuf, 0, cArr2, 0, this.entityEnd);
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

    private void reset() {
        this.lineNumber = 1;
        this.columnNumber = 0;
        this.seenRoot = false;
        this.reachedEnd = false;
        this.eventType = 7;
        this.emptyElementTag = false;
        this.depth = 0;
        this.attributeCount = 0;
        this.namespaceEnd = 0;
        this.localNamespaceEnd = 0;
        this.entityEnd = 0;
        this.reader = null;
        this.inputEncoding = null;
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
        resetStringCache();
    }

    public MXParser() {
        String[] strArr = NO_STRINGS;
        this.namespacePrefix = strArr;
        this.namespaceUri = strArr;
        this.bufLoadFactor = 95;
        int i = 8192;
        char[] cArr = new char[(Runtime.getRuntime().freeMemory() > 1000000 ? 8192 : 256)];
        this.buf = cArr;
        this.bufSoftLimit = (this.bufLoadFactor * cArr.length) / 100;
        this.pc = new char[(Runtime.getRuntime().freeMemory() <= 1000000 ? 64 : i)];
        this.entityValue = null;
        this.charRefOneCharBuf = new char[1];
        this.charRefTwoCharBuf = null;
    }

    public void setFeature(String str, boolean z) throws XMLStreamException {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be nulll");
        } else if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            if (this.eventType == 7) {
                this.processNamespaces = z;
                return;
            }
            throw new XMLStreamException("namespace processing feature can only be changed before parsing", getLocation());
        } else if (FEATURE_NAMES_INTERNED.equals(str)) {
            if (z) {
                throw new XMLStreamException("interning names in this implementation is not supported");
            }
        } else if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            if (z) {
                throw new XMLStreamException("processing DOCDECL is not supported");
            }
        } else if (!FEATURE_XML_ROUNDTRIP.equals(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("unknown feature ");
            stringBuffer.append(str);
            throw new XMLStreamException(stringBuffer.toString());
        } else if (!z) {
            throw new XMLStreamException("roundtrip feature can not be switched off");
        }
    }

    public boolean getFeature(String str) {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be null");
        } else if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            return this.processNamespaces;
        } else {
            if (!FEATURE_NAMES_INTERNED.equals(str) && !"http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str) && FEATURE_XML_ROUNDTRIP.equals(str)) {
                return true;
            }
            return false;
        }
    }

    public void setProperty(String str, Object obj) throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("unsupported property: '");
        stringBuffer.append(str);
        stringBuffer.append("'");
        throw new XMLStreamException(stringBuffer.toString());
    }

    public boolean checkForXMLDecl() throws XMLStreamException {
        try {
            BufferedReader bufferedReader = new BufferedReader(this.reader, 7);
            this.reader = bufferedReader;
            bufferedReader.mark(7);
            int read = bufferedReader.read();
            if (read == 65279) {
                bufferedReader.mark(7);
                read = bufferedReader.read();
            }
            if (read == 60 && bufferedReader.read() == 63 && bufferedReader.read() == 120 && bufferedReader.read() == 109 && bufferedReader.read() == 108) {
                bufferedReader.reset();
                return true;
            }
            bufferedReader.reset();
            return false;
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    public void setInput(Reader reader2) throws XMLStreamException {
        reset();
        this.reader = reader2;
        if (checkForXMLDecl()) {
            next();
        }
    }

    public void setInput(InputStream inputStream) throws XMLStreamException {
        try {
            Reader createReader = XmlReader.createReader(inputStream);
            String str = null;
            if (createReader instanceof XmlReader.BaseReader) {
                str = ((XmlReader.BaseReader) createReader).getEncoding();
            }
            setInput(createReader);
            if (str != null) {
                this.inputEncoding = str;
            }
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    public void setInput(InputStream inputStream, String str) throws XMLStreamException {
        Reader reader2;
        String str2;
        if (inputStream != null) {
            if (str != null) {
                try {
                    reader2 = XmlReader.createReader(inputStream, str);
                } catch (IOException e) {
                    if (str == null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("(for encoding '");
                        stringBuffer.append(str);
                        stringBuffer.append("')");
                        str2 = stringBuffer.toString();
                    } else {
                        str2 = "";
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("could not create reader ");
                    stringBuffer2.append(str2);
                    stringBuffer2.append(": ");
                    stringBuffer2.append(e);
                    throw new XMLStreamException(stringBuffer2.toString(), getLocation(), e);
                }
            } else {
                reader2 = XmlReader.createReader(inputStream);
            }
            setInput(reader2);
            if (str != null) {
                this.inputEncoding = str;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("input stream can not be null");
    }

    public String getInputEncoding() {
        return this.inputEncoding;
    }

    public void defineEntityReplacementText(String str, String str2) throws XMLStreamException {
        ensureEntityCapacity();
        char[] charArray = str.toCharArray();
        this.entityName[this.entityEnd] = newString(charArray, 0, str.length());
        char[][] cArr = this.entityNameBuf;
        int i = this.entityEnd;
        cArr[i] = charArray;
        this.entityReplacement[i] = str2;
        char[] charArray2 = str2 == null ? NO_CHARS : str2.toCharArray();
        char[][] cArr2 = this.entityReplacementBuf;
        int i2 = this.entityEnd;
        cArr2[i2] = charArray2;
        if (!this.allStringsInterned) {
            int[] iArr = this.entityNameHash;
            char[][] cArr3 = this.entityNameBuf;
            iArr[i2] = fastHash(cArr3[i2], 0, cArr3[i2].length);
        }
        this.entityEnd++;
    }

    public int getNamespaceCount() {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        return getNamespaceCount(this.depth);
    }

    public int getNamespaceCount(int i) {
        if (!this.processNamespaces || i == 0) {
            return 0;
        }
        if (i >= 0) {
            int[] iArr = this.elNamespaceCount;
            return iArr[i] - iArr[i - 1];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("namespace count may be 0..");
        stringBuffer.append(this.depth);
        stringBuffer.append(" not ");
        stringBuffer.append(i);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public String getNamespacePrefix(int i) {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        int i2 = this.depth;
        int namespaceCount = getNamespaceCount(i2);
        int i3 = this.elNamespaceCount[i2 - 1] + i;
        if (i < namespaceCount) {
            return this.namespacePrefix[i3];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("position ");
        stringBuffer.append(i);
        stringBuffer.append(" exceeded number of available namespaces ");
        stringBuffer.append(namespaceCount);
        throw new ArrayIndexOutOfBoundsException(stringBuffer.toString());
    }

    public String getNamespaceURI(int i) {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        int i2 = this.depth;
        int namespaceCount = getNamespaceCount(i2);
        int i3 = this.elNamespaceCount[i2 - 1] + i;
        if (i < namespaceCount) {
            return this.namespaceUri[i3];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("position ");
        stringBuffer.append(i);
        stringBuffer.append(" exceedded number of available namespaces ");
        stringBuffer.append(namespaceCount);
        throw new ArrayIndexOutOfBoundsException(stringBuffer.toString());
    }

    public String getNamespaceURI(String str) {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        if (str == null || str.length() <= 0) {
            for (int i = this.namespaceEnd - 1; i >= 0; i--) {
                if (this.namespacePrefix[i] == null) {
                    return this.namespaceUri[i];
                }
            }
            return null;
        }
        for (int i2 = this.namespaceEnd - 1; i2 >= 0; i2--) {
            if (str.equals(this.namespacePrefix[i2])) {
                return this.namespaceUri[i2];
            }
        }
        if ("xml".equals(str)) {
            return XMLConstants.XML_NS_URI;
        }
        if (XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
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
            if (i4 <= i || i3 - i4 > 65 || (cArr[i4] == '<' && i2 - i4 > 10)) {
                return i4;
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

    public boolean isWhiteSpace() {
        int i = this.eventType;
        if (i != 4 && i != 12) {
            return i == 6;
        }
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
    }

    public String getNamespaceURI() {
        int i = this.eventType;
        if (i == 1 || i == 2) {
            return this.processNamespaces ? this.elUri[this.depth] : NO_NAMESPACE;
        }
        return throwIllegalState(new int[]{1, 2});
    }

    public String getLocalName() {
        int i = this.eventType;
        if (i == 1) {
            return this.elName[this.depth];
        }
        if (i == 2) {
            return this.elName[this.depth];
        }
        if (i != 9) {
            return throwIllegalState(new int[]{1, 2, 9});
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
        if (i == 1 || i == 2) {
            return this.elPrefix[this.depth];
        }
        return throwIllegalState(new int[]{1, 2});
    }

    public boolean isEmptyElementTag() throws XMLStreamException {
        if (this.eventType == 1) {
            return this.emptyElementTag;
        }
        throw new XMLStreamException("parser must be on XMLStreamConstants.START_ELEMENT to check for empty element", getLocation());
    }

    public int getAttributeCount() {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        return this.attributeCount;
    }

    public String getAttributeNamespace(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (!this.processNamespaces) {
            return NO_NAMESPACE;
        }
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

    public String getAttributeLocalName(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i >= 0 && i < this.attributeCount) {
            return this.attributeName[i];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("attribute position must be 0..");
        stringBuffer.append(this.attributeCount - 1);
        stringBuffer.append(" and not ");
        stringBuffer.append(i);
        throw new IndexOutOfBoundsException(stringBuffer.toString());
    }

    public String getAttributePrefix(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (!this.processNamespaces) {
            return null;
        }
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

    public String getAttributeType(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i >= 0 && i < this.attributeCount) {
            return "CDATA";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("attribute position must be 0..");
        stringBuffer.append(this.attributeCount - 1);
        stringBuffer.append(" and not ");
        stringBuffer.append(i);
        throw new IndexOutOfBoundsException(stringBuffer.toString());
    }

    public boolean isAttributeSpecified(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i >= 0 && i < this.attributeCount) {
            return true;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("attribute position must be 0..");
        stringBuffer.append(this.attributeCount - 1);
        stringBuffer.append(" and not ");
        stringBuffer.append(i);
        throw new IndexOutOfBoundsException(stringBuffer.toString());
    }

    public String getAttributeValue(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i >= 0 && i < this.attributeCount) {
            return this.attributeValue[i];
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("attribute position must be 0..");
        stringBuffer.append(this.attributeCount - 1);
        stringBuffer.append(" and not ");
        stringBuffer.append(i);
        throw new IndexOutOfBoundsException(stringBuffer.toString());
    }

    public String getAttributeValue(String str, String str2) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (str2 != null) {
            int i = 0;
            if (str != null) {
                while (i < this.attributeCount) {
                    if (str2.equals(this.attributeName[i]) && str.equals(this.attributeUri[i])) {
                        return this.attributeValue[i];
                    }
                    i++;
                }
                return null;
            }
            while (i < this.attributeCount) {
                if (str2.equals(this.attributeName[i])) {
                    return this.attributeValue[i];
                }
                i++;
            }
            return null;
        }
        throw new IllegalArgumentException("attribute name can not be null");
    }

    public int getEventType() {
        return this.eventType;
    }

    public void require(int i, String str, String str2) throws XMLStreamException {
        String str3;
        String str4;
        String str5;
        String str6;
        int eventType2 = getEventType();
        boolean z = false;
        boolean z2 = i == eventType2;
        if (z2 && str2 != null) {
            if (eventType2 == 1 || eventType2 == 2 || eventType2 == 9) {
                z2 = str2.equals(getLocalName());
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Using non-null local name argument for require(); ");
                stringBuffer.append(ElementTypeNames.getEventTypeString(eventType2));
                stringBuffer.append(" event does not have local name");
                throw new XMLStreamException(stringBuffer.toString(), getLocation());
            }
        }
        if (z2 && str != null && (eventType2 == 1 || eventType2 == 1)) {
            String namespaceURI = getNamespaceURI();
            if (str.length() == 0) {
                if (namespaceURI == null) {
                    z = true;
                }
                z2 = z;
            } else {
                z2 = str.equals(namespaceURI);
            }
        }
        if (!z2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("expected event ");
            stringBuffer2.append(ElementTypeNames.getEventTypeString(i));
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
                stringBuffer5.append(ElementTypeNames.getEventTypeString(getEventType()));
                str5 = stringBuffer5.toString();
            } else {
                str5 = str7;
            }
            stringBuffer2.append(str5);
            if (str2 == null || getLocalName() == null || str2.equals(getName())) {
                str6 = str7;
            } else {
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append(" name '");
                stringBuffer6.append(getLocalName());
                stringBuffer6.append("'");
                str6 = stringBuffer6.toString();
            }
            stringBuffer2.append(str6);
            if (str == null || str2 == null || getLocalName() == null || str2.equals(getName()) || getNamespaceURI() == null || str.equals(getNamespaceURI())) {
                str8 = str7;
            }
            stringBuffer2.append(str8);
            if (!(str == null || getNamespaceURI() == null || str.equals(getNamespaceURI()))) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append(" namespace '");
                stringBuffer7.append(getNamespaceURI());
                stringBuffer7.append("'");
                str7 = stringBuffer7.toString();
            }
            stringBuffer2.append(str7);
            stringBuffer2.append(" (position:");
            stringBuffer2.append(getPositionDescription());
            stringBuffer2.append(")");
            throw new XMLStreamException(stringBuffer2.toString(), getLocation());
        }
    }

    public String nextText() throws XMLStreamException {
        if (getEventType() == 1) {
            int next = next();
            if (next == 4) {
                String text2 = getText();
                if (next() == 2) {
                    return text2;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("TEXT must be immediately followed by END_ELEMENT and not ");
                stringBuffer.append(ElementTypeNames.getEventTypeString(getEventType()));
                throw new XMLStreamException(stringBuffer.toString(), getLocation());
            } else if (next == 2) {
                return "";
            } else {
                throw new XMLStreamException("parser must be on START_ELEMENT or TEXT to read text", getLocation());
            }
        } else {
            throw new XMLStreamException("parser must be on START_ELEMENT to read next text", getLocation());
        }
    }

    public int nextTag() throws XMLStreamException {
        next();
        while (true) {
            int i = this.eventType;
            if (i == 6 || i == 5 || i == 3 || ((i == 4 && isWhiteSpace()) || (this.eventType == 12 && isWhiteSpace()))) {
                next();
            }
        }
        int i2 = this.eventType;
        if (i2 == 1 || i2 == 2) {
            return this.eventType;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("expected XMLStreamConstants.START_ELEMENT or XMLStreamConstants.END_ELEMENT not ");
        stringBuffer.append(ElementTypeNames.getEventTypeString(getEventType()));
        throw new XMLStreamException(stringBuffer.toString(), getLocation());
    }

    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        if (getEventType() == 1) {
            while (next() != 8) {
                if (!isStartElement()) {
                    if (isCharacters() || getEventType() == 9) {
                        stringBuffer.append(getText());
                    }
                    if (isEndElement()) {
                        return stringBuffer.toString();
                    }
                } else {
                    throw new XMLStreamException("Unexpected Element start");
                }
            }
            throw new XMLStreamException("Unexpected end of Document");
        }
        throw new XMLStreamException("Precondition for readText is getEventType() == START_ELEMENT");
    }

    public int next() throws XMLStreamException {
        this.tokenize = true;
        this.pcStart = 0;
        this.pcEnd = 0;
        this.usePC = false;
        return nextImpl();
    }

    public int nextToken() throws XMLStreamException {
        this.tokenize = true;
        return nextImpl();
    }

    public int nextElement() throws XMLStreamException {
        return nextTag();
    }

    public boolean hasNext() throws XMLStreamException {
        return this.eventType != 8;
    }

    public void skip() throws XMLStreamException {
        nextToken();
    }

    public boolean isStartElement() {
        return this.eventType == 1;
    }

    public boolean isEndElement() {
        return this.eventType == 2;
    }

    public boolean isCharacters() {
        return this.eventType == 4;
    }

    public boolean isEOF() {
        return this.eventType == 8;
    }

    public boolean moveToStartElement() throws XMLStreamException {
        if (isStartElement()) {
            return true;
        }
        while (hasNext()) {
            if (isStartElement()) {
                return true;
            }
            next();
        }
        return false;
    }

    public boolean moveToStartElement(String str) throws XMLStreamException {
        if (str == null) {
            return false;
        }
        while (moveToStartElement()) {
            if (str.equals(getLocalName())) {
                return true;
            }
            if (!hasNext()) {
                return false;
            }
            next();
        }
        return false;
    }

    public boolean moveToStartElement(String str, String str2) throws XMLStreamException {
        if (!(str == null || str2 == null)) {
            while (moveToStartElement(str)) {
                if (str2.equals(getNamespaceURI())) {
                    return true;
                }
                if (!hasNext()) {
                    return false;
                }
                next();
            }
        }
        return false;
    }

    public boolean moveToEndElement() throws XMLStreamException {
        if (isEndElement()) {
            return true;
        }
        while (hasNext()) {
            if (isEndElement()) {
                return true;
            }
            next();
        }
        return false;
    }

    public boolean moveToEndElement(String str) throws XMLStreamException {
        if (str == null) {
            return false;
        }
        while (moveToEndElement()) {
            if (str.equals(getLocalName())) {
                return true;
            }
            if (!hasNext()) {
                return false;
            }
            next();
        }
        return false;
    }

    public boolean moveToEndElement(String str, String str2) throws XMLStreamException {
        if (!(str == null || str2 == null)) {
            while (moveToEndElement(str)) {
                if (str2.equals(getNamespaceURI())) {
                    return true;
                }
                if (!hasNext()) {
                    return false;
                }
                next();
            }
        }
        return false;
    }

    public boolean hasAttributes() {
        return getAttributeCount() > 0;
    }

    public boolean hasNamespaces() {
        return getNamespaceCount() > 0;
    }

    public Iterator getAttributes() {
        if (!hasAttributes()) {
            return EmptyIterator.emptyIterator;
        }
        int attributeCount2 = getAttributeCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < attributeCount2; i++) {
            arrayList.add(new AttributeBase(getAttributePrefix(i), getAttributeNamespace(i), getAttributeLocalName(i), getAttributeValue(i), getAttributeType(i)));
        }
        return arrayList.iterator();
    }

    public Iterator internalGetNamespaces(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = this.elNamespaceCount[i - 1];
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 + i3;
            String localNamespacePrefix2 = getLocalNamespacePrefix(i5);
            if (localNamespacePrefix2 == null) {
                arrayList.add(new NamespaceBase(getLocalNamespaceURI(i5)));
            } else {
                arrayList.add(new NamespaceBase(localNamespacePrefix2, getLocalNamespaceURI(i5)));
            }
        }
        return arrayList.iterator();
    }

    public Iterator getNamespaces() {
        if (!hasNamespaces()) {
            return EmptyIterator.emptyIterator;
        }
        return internalGetNamespaces(this.depth, getLocalNamespaceCount());
    }

    public Iterator getOutOfScopeNamespaces() {
        int[] iArr = this.elNamespaceCount;
        int i = this.depth;
        return internalGetNamespaces(i, iArr[i] - iArr[i - 1]);
    }

    public XMLStreamReader subReader() throws XMLStreamException {
        return new SubReader(this);
    }

    public void recycle() throws XMLStreamException {
        reset();
    }

    public Reader getTextStream() {
        throw new UnsupportedOperationException();
    }

    private final void checkTextEvent() {
        if (!hasText()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Current state (");
            stringBuffer.append(eventTypeDesc(this.eventType));
            stringBuffer.append(") does not have textual content");
            throw new IllegalStateException(stringBuffer.toString());
        }
    }

    private final void checkTextEventXxx() {
        int i = this.eventType;
        if (i != 4 && i != 12 && i != 5 && i != 6) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("getTextXxx methods cannot be called for ");
            stringBuffer.append(eventTypeDesc(this.eventType));
            throw new IllegalStateException(stringBuffer.toString());
        }
    }

    public String getText() {
        checkTextEvent();
        if (this.eventType == 9) {
            if (this.text == null && this.entityValue != null) {
                this.text = new String(this.entityValue);
            }
            return this.text;
        }
        if (this.usePC) {
            char[] cArr = this.pc;
            int i = this.pcStart;
            this.text = new String(cArr, i, this.pcEnd - i);
        } else {
            char[] cArr2 = this.buf;
            int i2 = this.posStart;
            this.text = new String(cArr2, i2, this.posEnd - i2);
        }
        return this.text;
    }

    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        checkTextEventXxx();
        int textLength = getTextLength();
        if (i < 0 || i > textLength) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = textLength - i;
        if (i4 < i3) {
            i3 = i4;
        }
        if (i3 > 0) {
            System.arraycopy(getTextCharacters(), getTextStart() + i, cArr, i2, i3);
        }
        return i3;
    }

    public char[] getTextCharacters() {
        checkTextEventXxx();
        if (this.eventType != 4) {
            return this.buf;
        }
        if (this.usePC) {
            return this.pc;
        }
        return this.buf;
    }

    public int getTextStart() {
        checkTextEventXxx();
        if (this.usePC) {
            return this.pcStart;
        }
        return this.posStart;
    }

    public int getTextLength() {
        int i;
        int i2;
        checkTextEventXxx();
        if (this.usePC) {
            i = this.pcEnd;
            i2 = this.pcStart;
        } else {
            i = this.posEnd;
            i2 = this.posStart;
        }
        return i - i2;
    }

    public boolean hasText() {
        int i = this.eventType;
        return i == 4 || i == 11 || i == 12 || i == 5 || i == 6 || i == 9;
    }

    public String getValue() {
        return getText();
    }

    public String getEncoding() {
        return getInputEncoding();
    }

    public int getCharacterOffset() {
        return this.posEnd;
    }

    private static String eventTypeDesc(int i) {
        if (i >= 0) {
            String[] strArr = TYPES;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return "[UNKNOWN]";
    }

    public QName getAttributeName(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        return new QName(checkNull(getAttributeNamespace(i)), getAttributeLocalName(i), checkNull(getAttributePrefix(i)));
    }

    public QName getName() {
        if (isElementEvent(this.eventType)) {
            return new QName(checkNull(getNamespaceURI()), getLocalName(), checkNull(getPrefix()));
        }
        throw new IllegalStateException("Current state not START_ELEMENT or END_ELEMENT");
    }

    public boolean hasName() {
        return isElementEvent(this.eventType);
    }

    public String getVersion() {
        return this.xmlVersion;
    }

    public boolean isStandalone() {
        return this.standalone;
    }

    public boolean standaloneSet() {
        return this.standaloneSet;
    }

    public String getCharacterEncodingScheme() {
        return this.charEncodingScheme;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x013c, code lost:
        if (r5 != false) goto L_0x013e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int nextImpl() throws javax.xml.stream.XMLStreamException {
        /*
            r11 = this;
            r0 = 0
            r11.text = r0     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r11.posEnd     // Catch:{ EOFException -> 0x02a7 }
            r11.bufStart = r0     // Catch:{ EOFException -> 0x02a7 }
            boolean r0 = r11.pastEndTag     // Catch:{ EOFException -> 0x02a7 }
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x001a
            r11.pastEndTag = r1     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r11.depth     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r0 - r2
            r11.depth = r0     // Catch:{ EOFException -> 0x02a7 }
            int[] r3 = r11.elNamespaceCount     // Catch:{ EOFException -> 0x02a7 }
            r0 = r3[r0]     // Catch:{ EOFException -> 0x02a7 }
            r11.namespaceEnd = r0     // Catch:{ EOFException -> 0x02a7 }
        L_0x001a:
            boolean r0 = r11.emptyElementTag     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x0026
            r11.emptyElementTag = r1     // Catch:{ EOFException -> 0x02a7 }
            r11.pastEndTag = r2     // Catch:{ EOFException -> 0x02a7 }
            r0 = 2
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x0026:
            int r0 = r11.depth     // Catch:{ EOFException -> 0x02a7 }
            if (r0 <= 0) goto L_0x0299
            boolean r0 = r11.seenStartTag     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x0037
            r11.seenStartTag = r1     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r11.parseStartTag()     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x0037:
            boolean r0 = r11.seenEndTag     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x0044
            r11.seenEndTag = r1     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r11.parseEndTag()     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x0044:
            boolean r0 = r11.seenMarkup     // Catch:{ EOFException -> 0x02a7 }
            r3 = 38
            r4 = 60
            if (r0 == 0) goto L_0x0051
            r11.seenMarkup = r1     // Catch:{ EOFException -> 0x02a7 }
            r0 = 60
            goto L_0x005e
        L_0x0051:
            boolean r0 = r11.seenAmpersand     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x005a
            r11.seenAmpersand = r1     // Catch:{ EOFException -> 0x02a7 }
            r0 = 38
            goto L_0x005e
        L_0x005a:
            char r0 = r11.more()     // Catch:{ EOFException -> 0x02a7 }
        L_0x005e:
            int r5 = r11.pos     // Catch:{ EOFException -> 0x02a7 }
            int r5 = r5 - r2
            r11.posStart = r5     // Catch:{ EOFException -> 0x02a7 }
            r5 = 0
            r6 = 0
        L_0x0065:
            r7 = 4
            if (r0 != r4) goto L_0x0176
            if (r5 == 0) goto L_0x0073
            boolean r0 = r11.tokenize     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x0073
            r11.seenMarkup = r2     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r7     // Catch:{ EOFException -> 0x02a7 }
            return r7
        L_0x0073:
            char r0 = r11.more()     // Catch:{ EOFException -> 0x02a7 }
            r8 = 47
            if (r0 != r8) goto L_0x008d
            boolean r0 = r11.tokenize     // Catch:{ EOFException -> 0x02a7 }
            if (r0 != 0) goto L_0x0086
            if (r5 == 0) goto L_0x0086
            r11.seenEndTag = r2     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r7     // Catch:{ EOFException -> 0x02a7 }
            return r7
        L_0x0086:
            int r0 = r11.parseEndTag()     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x008d:
            r8 = 33
            java.lang.String r9 = "unexpected character in markup "
            if (r0 != r8) goto L_0x0129
            char r0 = r11.more()     // Catch:{ EOFException -> 0x02a7 }
            r7 = 45
            if (r0 != r7) goto L_0x00af
            r11.parseComment()     // Catch:{ EOFException -> 0x02a7 }
            boolean r0 = r11.tokenize     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x00a7
            r0 = 5
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x00a7:
            boolean r0 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r0 != 0) goto L_0x020c
            if (r5 == 0) goto L_0x020c
            goto L_0x013e
        L_0x00af:
            r7 = 91
            if (r0 != r7) goto L_0x010c
            int r0 = r11.posStart     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r11.posEnd     // Catch:{ EOFException -> 0x02a7 }
            r11.parseCDATA()     // Catch:{ EOFException -> 0x02a7 }
            int r8 = r11.posStart     // Catch:{ EOFException -> 0x02a7 }
            int r9 = r11.posEnd     // Catch:{ EOFException -> 0x02a7 }
            r11.posStart = r0     // Catch:{ EOFException -> 0x02a7 }
            r11.posEnd = r7     // Catch:{ EOFException -> 0x02a7 }
            int r10 = r9 - r8
            if (r10 <= 0) goto L_0x00fc
            if (r5 == 0) goto L_0x00f5
            boolean r5 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r5 != 0) goto L_0x00d8
            if (r7 <= r0) goto L_0x00d2
            r11.joinPC()     // Catch:{ EOFException -> 0x02a7 }
            goto L_0x00d8
        L_0x00d2:
            r11.usePC = r2     // Catch:{ EOFException -> 0x02a7 }
            r11.pcEnd = r1     // Catch:{ EOFException -> 0x02a7 }
            r11.pcStart = r1     // Catch:{ EOFException -> 0x02a7 }
        L_0x00d8:
            int r0 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r0 + r10
            char[] r5 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r5 = r5.length     // Catch:{ EOFException -> 0x02a7 }
            if (r0 < r5) goto L_0x00e6
            int r0 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r0 + r10
            r11.ensurePC(r0)     // Catch:{ EOFException -> 0x02a7 }
        L_0x00e6:
            char[] r0 = r11.buf     // Catch:{ EOFException -> 0x02a7 }
            char[] r5 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            java.lang.System.arraycopy(r0, r8, r5, r7, r10)     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r0 + r10
            r11.pcEnd = r0     // Catch:{ EOFException -> 0x02a7 }
            goto L_0x00fa
        L_0x00f5:
            r11.posStart = r8     // Catch:{ EOFException -> 0x02a7 }
            r11.posEnd = r9     // Catch:{ EOFException -> 0x02a7 }
            r6 = 1
        L_0x00fa:
            r5 = 1
            goto L_0x0103
        L_0x00fc:
            boolean r0 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r0 != 0) goto L_0x0103
            if (r5 == 0) goto L_0x0103
            r6 = 1
        L_0x0103:
            boolean r0 = r11.reportCdataEvent     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x020c
            r0 = 12
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x010c:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x02a7 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x02a7 }
            r2.<init>()     // Catch:{ EOFException -> 0x02a7 }
            r2.append(r9)     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r0 = r11.printable((char) r0)     // Catch:{ EOFException -> 0x02a7 }
            r2.append(r0)     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r0 = r2.toString()     // Catch:{ EOFException -> 0x02a7 }
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x02a7 }
            r1.<init>((java.lang.String) r0, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x02a7 }
            throw r1     // Catch:{ EOFException -> 0x02a7 }
        L_0x0129:
            r8 = 63
            if (r0 != r8) goto L_0x0141
            r11.parsePI()     // Catch:{ EOFException -> 0x02a7 }
            boolean r0 = r11.tokenize     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x0138
            r0 = 3
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x0138:
            boolean r0 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r0 != 0) goto L_0x020c
            if (r5 == 0) goto L_0x020c
        L_0x013e:
            r6 = 1
            goto L_0x020c
        L_0x0141:
            boolean r1 = r11.isNameStartChar(r0)     // Catch:{ EOFException -> 0x02a7 }
            if (r1 == 0) goto L_0x0159
            boolean r0 = r11.tokenize     // Catch:{ EOFException -> 0x02a7 }
            if (r0 != 0) goto L_0x0152
            if (r5 == 0) goto L_0x0152
            r11.seenStartTag = r2     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r7     // Catch:{ EOFException -> 0x02a7 }
            return r7
        L_0x0152:
            int r0 = r11.parseStartTag()     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x0159:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x02a7 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x02a7 }
            r2.<init>()     // Catch:{ EOFException -> 0x02a7 }
            r2.append(r9)     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r0 = r11.printable((char) r0)     // Catch:{ EOFException -> 0x02a7 }
            r2.append(r0)     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r0 = r2.toString()     // Catch:{ EOFException -> 0x02a7 }
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x02a7 }
            r1.<init>((java.lang.String) r0, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x02a7 }
            throw r1     // Catch:{ EOFException -> 0x02a7 }
        L_0x0176:
            if (r0 != r3) goto L_0x0212
            boolean r0 = r11.tokenize     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x0183
            if (r5 == 0) goto L_0x0183
            r11.seenAmpersand = r2     // Catch:{ EOFException -> 0x02a7 }
            r11.eventType = r7     // Catch:{ EOFException -> 0x02a7 }
            return r7
        L_0x0183:
            int r0 = r11.posStart     // Catch:{ EOFException -> 0x02a7 }
            int r8 = r11.posEnd     // Catch:{ EOFException -> 0x02a7 }
            com.bea.xml.stream.ConfigurationContextBase r9 = r11.getConfigurationContext()     // Catch:{ EOFException -> 0x02a7 }
            boolean r9 = r9.isReplacingEntities()     // Catch:{ EOFException -> 0x02a7 }
            char[] r10 = r11.parseEntityRef(r9)     // Catch:{ EOFException -> 0x02a7 }
            if (r9 != 0) goto L_0x019a
            r0 = 9
            r11.eventType = r0     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x019a:
            r11.eventType = r7     // Catch:{ EOFException -> 0x02a7 }
            if (r10 != 0) goto L_0x01d7
            java.lang.String r0 = r11.entityRefName     // Catch:{ EOFException -> 0x02a7 }
            if (r0 != 0) goto L_0x01b1
            char[] r0 = r11.buf     // Catch:{ EOFException -> 0x02a7 }
            int r1 = r11.posStart     // Catch:{ EOFException -> 0x02a7 }
            int r2 = r11.posEnd     // Catch:{ EOFException -> 0x02a7 }
            int r3 = r11.posStart     // Catch:{ EOFException -> 0x02a7 }
            int r2 = r2 - r3
            java.lang.String r0 = r11.newString(r0, r1, r2)     // Catch:{ EOFException -> 0x02a7 }
            r11.entityRefName = r0     // Catch:{ EOFException -> 0x02a7 }
        L_0x01b1:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x02a7 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x02a7 }
            r1.<init>()     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r2 = "could not resolve entity named '"
            r1.append(r2)     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r2 = r11.entityRefName     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r2 = r11.printable((java.lang.String) r2)     // Catch:{ EOFException -> 0x02a7 }
            r1.append(r2)     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r2 = "'"
            r1.append(r2)     // Catch:{ EOFException -> 0x02a7 }
            java.lang.String r1 = r1.toString()     // Catch:{ EOFException -> 0x02a7 }
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x02a7 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x02a7 }
            throw r0     // Catch:{ EOFException -> 0x02a7 }
        L_0x01d7:
            r11.posStart = r0     // Catch:{ EOFException -> 0x02a7 }
            r11.posEnd = r8     // Catch:{ EOFException -> 0x02a7 }
            boolean r0 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r0 != 0) goto L_0x01ec
            if (r5 == 0) goto L_0x01e6
            r11.joinPC()     // Catch:{ EOFException -> 0x02a7 }
            r6 = 0
            goto L_0x01ec
        L_0x01e6:
            r11.usePC = r2     // Catch:{ EOFException -> 0x02a7 }
            r11.pcEnd = r1     // Catch:{ EOFException -> 0x02a7 }
            r11.pcStart = r1     // Catch:{ EOFException -> 0x02a7 }
        L_0x01ec:
            r0 = 0
        L_0x01ed:
            int r5 = r10.length     // Catch:{ EOFException -> 0x02a7 }
            if (r0 >= r5) goto L_0x020b
            int r5 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            char[] r7 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r7.length     // Catch:{ EOFException -> 0x02a7 }
            if (r5 < r7) goto L_0x01fc
            int r5 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            r11.ensurePC(r5)     // Catch:{ EOFException -> 0x02a7 }
        L_0x01fc:
            char[] r5 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            int r8 = r7 + 1
            r11.pcEnd = r8     // Catch:{ EOFException -> 0x02a7 }
            char r8 = r10[r0]     // Catch:{ EOFException -> 0x02a7 }
            r5[r7] = r8     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r0 + 1
            goto L_0x01ed
        L_0x020b:
            r5 = 1
        L_0x020c:
            char r0 = r11.more()     // Catch:{ EOFException -> 0x02a7 }
            goto L_0x0065
        L_0x0212:
            if (r6 == 0) goto L_0x0218
            r11.joinPC()     // Catch:{ EOFException -> 0x02a7 }
            r6 = 0
        L_0x0218:
            r5 = 0
        L_0x0219:
            r7 = 13
            r8 = 10
            if (r0 != r7) goto L_0x024e
            int r0 = r11.pos     // Catch:{ EOFException -> 0x02a7 }
            int r0 = r0 - r2
            r11.posEnd = r0     // Catch:{ EOFException -> 0x02a7 }
            boolean r5 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r5 != 0) goto L_0x0236
            int r5 = r11.posStart     // Catch:{ EOFException -> 0x02a7 }
            if (r0 <= r5) goto L_0x0230
            r11.joinPC()     // Catch:{ EOFException -> 0x02a7 }
            goto L_0x0236
        L_0x0230:
            r11.usePC = r2     // Catch:{ EOFException -> 0x02a7 }
            r11.pcEnd = r1     // Catch:{ EOFException -> 0x02a7 }
            r11.pcStart = r1     // Catch:{ EOFException -> 0x02a7 }
        L_0x0236:
            int r0 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            char[] r5 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r5 = r5.length     // Catch:{ EOFException -> 0x02a7 }
            if (r0 < r5) goto L_0x0242
            int r0 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            r11.ensurePC(r0)     // Catch:{ EOFException -> 0x02a7 }
        L_0x0242:
            char[] r0 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r5 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r5 + 1
            r11.pcEnd = r7     // Catch:{ EOFException -> 0x02a7 }
            r0[r5] = r8     // Catch:{ EOFException -> 0x02a7 }
            r5 = 1
            goto L_0x0289
        L_0x024e:
            if (r0 != r8) goto L_0x026e
            if (r5 != 0) goto L_0x026c
            boolean r0 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x026c
            int r0 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            char[] r5 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r5 = r5.length     // Catch:{ EOFException -> 0x02a7 }
            if (r0 < r5) goto L_0x0262
            int r0 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            r11.ensurePC(r0)     // Catch:{ EOFException -> 0x02a7 }
        L_0x0262:
            char[] r0 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r5 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r5 + 1
            r11.pcEnd = r7     // Catch:{ EOFException -> 0x02a7 }
            r0[r5] = r8     // Catch:{ EOFException -> 0x02a7 }
        L_0x026c:
            r5 = 0
            goto L_0x0289
        L_0x026e:
            boolean r5 = r11.usePC     // Catch:{ EOFException -> 0x02a7 }
            if (r5 == 0) goto L_0x026c
            int r5 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            char[] r7 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r7.length     // Catch:{ EOFException -> 0x02a7 }
            if (r5 < r7) goto L_0x027e
            int r5 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            r11.ensurePC(r5)     // Catch:{ EOFException -> 0x02a7 }
        L_0x027e:
            char[] r5 = r11.pc     // Catch:{ EOFException -> 0x02a7 }
            int r7 = r11.pcEnd     // Catch:{ EOFException -> 0x02a7 }
            int r8 = r7 + 1
            r11.pcEnd = r8     // Catch:{ EOFException -> 0x02a7 }
            r5[r7] = r0     // Catch:{ EOFException -> 0x02a7 }
            goto L_0x026c
        L_0x0289:
            char r0 = r11.more()     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == r4) goto L_0x0291
            if (r0 != r3) goto L_0x0219
        L_0x0291:
            int r5 = r11.pos     // Catch:{ EOFException -> 0x02a7 }
            int r5 = r5 - r2
            r11.posEnd = r5     // Catch:{ EOFException -> 0x02a7 }
            r5 = 1
            goto L_0x0065
        L_0x0299:
            boolean r0 = r11.seenRoot     // Catch:{ EOFException -> 0x02a7 }
            if (r0 == 0) goto L_0x02a2
            int r0 = r11.parseEpilog()     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x02a2:
            int r0 = r11.parseProlog()     // Catch:{ EOFException -> 0x02a7 }
            return r0
        L_0x02a7:
            r0 = move-exception
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException
            javax.xml.stream.Location r2 = r11.getLocation()
            java.lang.String r3 = "Unexpected end of stream"
            r1.<init>(r3, r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.nextImpl():int");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c4, code lost:
        if (r0 == '/') goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ca, code lost:
        if (isNameStartChar(r0) == false) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cc, code lost:
        r5.seenRoot = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d2, code lost:
        return parseStartTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d3, code lost:
        r2 = new java.lang.StringBuffer();
        r2.append("expected start tag name and not ");
        r2.append(printable(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ef, code lost:
        throw new javax.xml.stream.XMLStreamException(r2.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f0, code lost:
        r2 = new java.lang.StringBuffer();
        r2.append("expected start tag name and not ");
        r2.append(printable(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010c, code lost:
        throw new javax.xml.stream.XMLStreamException(r2.toString(), getLocation());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parseProlog() throws javax.xml.stream.XMLStreamException {
        /*
            r5 = this;
            boolean r0 = r5.seenMarkup     // Catch:{ EOFException -> 0x0139 }
            r1 = 1
            if (r0 == 0) goto L_0x000d
            char[] r0 = r5.buf     // Catch:{ EOFException -> 0x0139 }
            int r2 = r5.pos     // Catch:{ EOFException -> 0x0139 }
            int r2 = r2 - r1
            char r0 = r0[r2]     // Catch:{ EOFException -> 0x0139 }
            goto L_0x0011
        L_0x000d:
            char r0 = r5.more()     // Catch:{ EOFException -> 0x0139 }
        L_0x0011:
            int r2 = r5.eventType     // Catch:{ EOFException -> 0x0139 }
            r3 = 7
            if (r2 != r3) goto L_0x0031
            r2 = 65534(0xfffe, float:9.1833E-41)
            if (r0 == r2) goto L_0x0025
            r2 = 65279(0xfeff, float:9.1475E-41)
            if (r0 != r2) goto L_0x0031
            char r0 = r5.more()     // Catch:{ EOFException -> 0x0139 }
            goto L_0x0031
        L_0x0025:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r1 = "first character in input was UNICODE noncharacter (0xFFFE)- input requires int swapping"
            javax.xml.stream.Location r2 = r5.getLocation()     // Catch:{ EOFException -> 0x0139 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0139 }
            throw r0     // Catch:{ EOFException -> 0x0139 }
        L_0x0031:
            r2 = 0
            r5.seenMarkup = r2     // Catch:{ EOFException -> 0x0139 }
            int r4 = r5.pos     // Catch:{ EOFException -> 0x0139 }
            int r4 = r4 - r1
            r5.posStart = r4     // Catch:{ EOFException -> 0x0139 }
        L_0x0039:
            r4 = 60
            if (r0 != r4) goto L_0x010d
            if (r2 == 0) goto L_0x004e
            boolean r0 = r5.tokenize     // Catch:{ EOFException -> 0x0139 }
            if (r0 == 0) goto L_0x004e
            int r0 = r5.pos     // Catch:{ EOFException -> 0x0139 }
            int r0 = r0 - r1
            r5.posEnd = r0     // Catch:{ EOFException -> 0x0139 }
            r5.seenMarkup = r1     // Catch:{ EOFException -> 0x0139 }
            r0 = 6
            r5.eventType = r0     // Catch:{ EOFException -> 0x0139 }
            return r0
        L_0x004e:
            char r0 = r5.more()     // Catch:{ EOFException -> 0x0139 }
            r4 = 63
            if (r0 != r4) goto L_0x0067
            boolean r0 = r5.parsePI()     // Catch:{ EOFException -> 0x0139 }
            boolean r4 = r5.tokenize     // Catch:{ EOFException -> 0x0139 }
            if (r4 == 0) goto L_0x0114
            if (r0 == 0) goto L_0x0063
            r5.eventType = r3     // Catch:{ EOFException -> 0x0139 }
            return r3
        L_0x0063:
            r0 = 3
            r5.eventType = r0     // Catch:{ EOFException -> 0x0139 }
            return r0
        L_0x0067:
            r4 = 33
            if (r0 != r4) goto L_0x00c0
            char r0 = r5.more()     // Catch:{ EOFException -> 0x0139 }
            r4 = 68
            if (r0 != r4) goto L_0x0091
            boolean r0 = r5.seenDocdecl     // Catch:{ EOFException -> 0x0139 }
            if (r0 != 0) goto L_0x0085
            r5.seenDocdecl = r1     // Catch:{ EOFException -> 0x0139 }
            r5.parseDocdecl()     // Catch:{ EOFException -> 0x0139 }
            boolean r0 = r5.tokenize     // Catch:{ EOFException -> 0x0139 }
            if (r0 == 0) goto L_0x0114
            r0 = 11
            r5.eventType = r0     // Catch:{ EOFException -> 0x0139 }
            return r0
        L_0x0085:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r1 = "only one docdecl allowed in XML document"
            javax.xml.stream.Location r2 = r5.getLocation()     // Catch:{ EOFException -> 0x0139 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0139 }
            throw r0     // Catch:{ EOFException -> 0x0139 }
        L_0x0091:
            r4 = 45
            if (r0 != r4) goto L_0x00a0
            r5.parseComment()     // Catch:{ EOFException -> 0x0139 }
            boolean r0 = r5.tokenize     // Catch:{ EOFException -> 0x0139 }
            if (r0 == 0) goto L_0x0114
            r0 = 5
            r5.eventType = r0     // Catch:{ EOFException -> 0x0139 }
            return r0
        L_0x00a0:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0139 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0139 }
            r2.<init>()     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r3 = "unexpected markup <!"
            r2.append(r3)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r5.printable((char) r0)     // Catch:{ EOFException -> 0x0139 }
            r2.append(r0)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r2.toString()     // Catch:{ EOFException -> 0x0139 }
            javax.xml.stream.Location r2 = r5.getLocation()     // Catch:{ EOFException -> 0x0139 }
            r1.<init>((java.lang.String) r0, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0139 }
            throw r1     // Catch:{ EOFException -> 0x0139 }
        L_0x00c0:
            r2 = 47
            java.lang.String r3 = "expected start tag name and not "
            if (r0 == r2) goto L_0x00f0
            boolean r2 = r5.isNameStartChar(r0)     // Catch:{ EOFException -> 0x0139 }
            if (r2 == 0) goto L_0x00d3
            r5.seenRoot = r1     // Catch:{ EOFException -> 0x0139 }
            int r0 = r5.parseStartTag()     // Catch:{ EOFException -> 0x0139 }
            return r0
        L_0x00d3:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0139 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0139 }
            r2.<init>()     // Catch:{ EOFException -> 0x0139 }
            r2.append(r3)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r5.printable((char) r0)     // Catch:{ EOFException -> 0x0139 }
            r2.append(r0)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r2.toString()     // Catch:{ EOFException -> 0x0139 }
            javax.xml.stream.Location r2 = r5.getLocation()     // Catch:{ EOFException -> 0x0139 }
            r1.<init>((java.lang.String) r0, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0139 }
            throw r1     // Catch:{ EOFException -> 0x0139 }
        L_0x00f0:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0139 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0139 }
            r2.<init>()     // Catch:{ EOFException -> 0x0139 }
            r2.append(r3)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r5.printable((char) r0)     // Catch:{ EOFException -> 0x0139 }
            r2.append(r0)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r2.toString()     // Catch:{ EOFException -> 0x0139 }
            javax.xml.stream.Location r2 = r5.getLocation()     // Catch:{ EOFException -> 0x0139 }
            r1.<init>((java.lang.String) r0, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0139 }
            throw r1     // Catch:{ EOFException -> 0x0139 }
        L_0x010d:
            boolean r2 = r5.isS(r0)     // Catch:{ EOFException -> 0x0139 }
            if (r2 == 0) goto L_0x011a
            r2 = 1
        L_0x0114:
            char r0 = r5.more()     // Catch:{ EOFException -> 0x0139 }
            goto L_0x0039
        L_0x011a:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0139 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0139 }
            r2.<init>()     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r3 = "only whitespace content allowed before start tag and not "
            r2.append(r3)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r5.printable((char) r0)     // Catch:{ EOFException -> 0x0139 }
            r2.append(r0)     // Catch:{ EOFException -> 0x0139 }
            java.lang.String r0 = r2.toString()     // Catch:{ EOFException -> 0x0139 }
            javax.xml.stream.Location r2 = r5.getLocation()     // Catch:{ EOFException -> 0x0139 }
            r1.<init>((java.lang.String) r0, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0139 }
            throw r1     // Catch:{ EOFException -> 0x0139 }
        L_0x0139:
            r0 = move-exception
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException
            javax.xml.stream.Location r2 = r5.getLocation()
            java.lang.String r3 = "Unexpected end of stream"
            r1.<init>(r3, r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.parseProlog():int");
    }

    /* access modifiers changed from: protected */
    public int parseEpilog() throws XMLStreamException {
        char c;
        if (this.eventType == 8) {
            throw new XMLStreamException("already reached end document", getLocation());
        } else if (this.reachedEnd) {
            this.eventType = 8;
            return 8;
        } else {
            boolean z = false;
            try {
                if (this.seenMarkup) {
                    c = this.buf[this.pos - 1];
                } else {
                    c = more();
                }
                this.seenMarkup = false;
                this.posStart = this.pos - 1;
                while (true) {
                    if (c == '<') {
                        if (!z || !this.tokenize) {
                            char more = more();
                            if (more == '?') {
                                parsePI();
                                if (this.tokenize) {
                                    this.eventType = 3;
                                    return 3;
                                }
                            } else if (more == '!') {
                                char more2 = more();
                                if (more2 == 'D') {
                                    parseDocdecl();
                                    if (this.tokenize) {
                                        this.eventType = 11;
                                        return 11;
                                    }
                                } else if (more2 == '-') {
                                    parseComment();
                                    if (this.tokenize) {
                                        this.eventType = 5;
                                        return 5;
                                    }
                                } else {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append("unexpected markup <!");
                                    stringBuffer.append(printable(more2));
                                    throw new XMLStreamException(stringBuffer.toString(), getLocation());
                                }
                            } else if (more == '/') {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("end tag not allowed in epilog but got ");
                                stringBuffer2.append(printable(more));
                                throw new XMLStreamException(stringBuffer2.toString(), getLocation());
                            } else if (isNameStartChar(more)) {
                                StringBuffer stringBuffer3 = new StringBuffer();
                                stringBuffer3.append("start tag not allowed in epilog but got ");
                                stringBuffer3.append(printable(more));
                                throw new XMLStreamException(stringBuffer3.toString(), getLocation());
                            } else {
                                StringBuffer stringBuffer4 = new StringBuffer();
                                stringBuffer4.append("in epilog expected ignorable content and not ");
                                stringBuffer4.append(printable(more));
                                throw new XMLStreamException(stringBuffer4.toString(), getLocation());
                            }
                        } else {
                            this.posEnd = this.pos - 1;
                            this.seenMarkup = true;
                            this.eventType = 6;
                            return 6;
                        }
                    } else if (isS(c)) {
                        z = true;
                    } else {
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append("in epilog non whitespace content is not allowed but got ");
                        stringBuffer5.append(printable(c));
                        throw new XMLStreamException(stringBuffer5.toString(), getLocation());
                    }
                    c = more();
                }
            } catch (EOFException unused) {
                this.reachedEnd = true;
                if (!this.tokenize || 0 == 0) {
                    this.eventType = 8;
                    return 8;
                }
                this.posEnd = this.pos;
                this.eventType = 6;
                return 6;
            }
        }
    }

    public int parseEndTag() throws XMLStreamException {
        char more;
        this.eventType = 2;
        try {
            char more2 = more();
            if (isNameStartChar(more2)) {
                this.posStart = this.pos - 3;
                int i = (this.pos - 1) + this.bufAbsoluteStart;
                do {
                    more = more();
                } while (isNameChar(more));
                int i2 = i - this.bufAbsoluteStart;
                int i3 = (this.pos - 1) - i2;
                char[] cArr = this.elRawName[this.depth];
                if (this.elRawNameEnd[this.depth] == i3) {
                    int i4 = 0;
                    while (i4 < i3) {
                        int i5 = i2 + 1;
                        if (this.buf[i2] == cArr[i4]) {
                            i4++;
                            i2 = i5;
                        } else {
                            String str = new String(cArr, 0, i3);
                            String str2 = new String(this.buf, (i5 - i4) - 1, i3);
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("end tag name '");
                            stringBuffer.append(str2);
                            stringBuffer.append("' must be the same as start tag '");
                            stringBuffer.append(str);
                            stringBuffer.append("'");
                            throw new XMLStreamException(stringBuffer.toString(), getLocation());
                        }
                    }
                    while (isS(more)) {
                        more = more();
                    }
                    if (more == '>') {
                        this.posEnd = this.pos;
                        this.pastEndTag = true;
                        return 2;
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("expected > to finsh end tag not ");
                    stringBuffer2.append(printable(more));
                    throw new XMLStreamException(stringBuffer2.toString(), getLocation());
                }
                String str3 = new String(cArr, 0, this.elRawNameEnd[this.depth]);
                String str4 = new String(this.buf, i2, i3);
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("end tag name '");
                stringBuffer3.append(str4);
                stringBuffer3.append("' must match start tag name '");
                stringBuffer3.append(str3);
                stringBuffer3.append("'");
                throw new XMLStreamException(stringBuffer3.toString(), getLocation());
            }
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("expected name start and not ");
            stringBuffer4.append(printable(more2));
            throw new XMLStreamException(stringBuffer4.toString(), getLocation());
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x026c, code lost:
        throw new javax.xml.stream.XMLStreamException(r4.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x026d, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0270, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0273, code lost:
        r11.elNamespaceCount[r11.depth] = r11.namespaceEnd;
        r11.posEnd = r11.pos;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0281, code lost:
        if (r11.defaultAttributes == null) goto L_0x029e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0283, code lost:
        if (r8 == null) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0285, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append(r8);
        r1.append(":");
        r1.append(r4);
        addDefaultAttributes(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x029b, code lost:
        addDefaultAttributes(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x029e, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x029f, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append("expected > to end empty tag not ");
        r1.append(printable(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02bd, code lost:
        throw new javax.xml.stream.XMLStreamException(r1.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02c2, code lost:
        if (isNameStartChar(r6) == false) goto L_0x02de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02c4, code lost:
        if (r2 != false) goto L_0x02d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02c6, code lost:
        if (r6 != '>') goto L_0x02c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02d4, code lost:
        throw new javax.xml.stream.XMLStreamException("expected a white space between attributes", getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02d5, code lost:
        parseAttribute();
        r6 = more();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02de, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append("start tag unexpected character ");
        r1.append(printable(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02fc, code lost:
        throw new javax.xml.stream.XMLStreamException(r1.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0044, code lost:
        ensureElementsCapacity();
        r4 = (r11.pos - 1) - (r2 - r11.bufAbsoluteStart);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        if (r11.elRawName[r11.depth] == null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005e, code lost:
        if (r11.elRawName[r11.depth].length >= r4) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
        r11.elRawName[r11.depth] = new char[(r4 * 2)];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006a, code lost:
        java.lang.System.arraycopy(r11.buf, r2 - r11.bufAbsoluteStart, r11.elRawName[r11.depth], 0, r4);
        r11.elRawNameEnd[r11.depth] = r4;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0082, code lost:
        if (r11.processNamespaces == false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0084, code lost:
        if (r5 == -1) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0086, code lost:
        r3 = r11.elPrefix;
        r4 = r11.depth;
        r8 = newString(r11.buf, r2 - r11.bufAbsoluteStart, r5 - r2);
        r3[r4] = r8;
        r2 = r11.elName;
        r3 = r11.depth;
        r4 = newString(r11.buf, (r5 + 1) - r11.bufAbsoluteStart, (r11.pos - 2) - (r5 - r11.bufAbsoluteStart));
        r2[r3] = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00b2, code lost:
        r11.elPrefix[r11.depth] = null;
        r3 = r11.elName;
        r5 = r11.depth;
        r4 = newString(r11.buf, r2 - r11.bufAbsoluteStart, r4);
        r3[r5] = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c8, code lost:
        r3 = r11.elName;
        r5 = r11.depth;
        r4 = newString(r11.buf, r2 - r11.bufAbsoluteStart, r4);
        r3[r5] = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00d7, code lost:
        r2 = isS(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00db, code lost:
        if (r2 == false) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00dd, code lost:
        r6 = more();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e5, code lost:
        if (isS(r6) != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e9, code lost:
        if (r6 != '>') goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ee, code lost:
        if (r6 != '/') goto L_0x02be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f0, code lost:
        r11.emptyElementTag = true;
        r2 = more();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00f6, code lost:
        if (r2 != '>') goto L_0x029f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0100, code lost:
        if (r11.processNamespaces == false) goto L_0x020f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r2 = getNamespaceURI(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0106, code lost:
        if (r2 != null) goto L_0x0128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0108, code lost:
        if (r8 != null) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x010a, code lost:
        r2 = NO_NAMESPACE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x010d, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append("could not determine namespace bound to element prefix ");
        r1.append(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0127, code lost:
        throw new javax.xml.stream.XMLStreamException(r1.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0128, code lost:
        r11.elUri[r11.depth] = r2;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0131, code lost:
        if (r2 >= r11.attributeCount) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0133, code lost:
        r7 = r11.attributePrefix[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0137, code lost:
        if (r7 == null) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0139, code lost:
        r9 = getNamespaceURI(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x013d, code lost:
        if (r9 == null) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x013f, code lost:
        r11.attributeUri[r2] = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0144, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append("could not determine namespace bound to attribute prefix ");
        r1.append(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x015e, code lost:
        throw new javax.xml.stream.XMLStreamException(r1.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x015f, code lost:
        r11.attributeUri[r2] = NO_NAMESPACE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0165, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0168, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x016b, code lost:
        if (r2 >= r11.attributeCount) goto L_0x0273;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x016d, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x016e, code lost:
        if (r7 >= r2) goto L_0x020b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0178, code lost:
        if (r11.attributeUri[r7] != r11.attributeUri[r2]) goto L_0x0207;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x017c, code lost:
        if (r11.allStringsInterned == false) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x018a, code lost:
        if (r11.attributeName[r7].equals(r11.attributeName[r2]) != false) goto L_0x01a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x018e, code lost:
        if (r11.allStringsInterned != false) goto L_0x0207;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0198, code lost:
        if (r11.attributeNameHash[r7] != r11.attributeNameHash[r2]) goto L_0x0207;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01a6, code lost:
        if (r11.attributeName[r7].equals(r11.attributeName[r2]) == false) goto L_0x0207;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01a8, code lost:
        r0 = r11.attributeName[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01b0, code lost:
        if (r11.attributeUri[r7] == null) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01b2, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append(r11.attributeUri[r7]);
        r1.append(":");
        r1.append(r0);
        r0 = r1.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01c8, code lost:
        r1 = r11.attributeName[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01d0, code lost:
        if (r11.attributeUri[r2] == null) goto L_0x01e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01d2, code lost:
        r4 = new java.lang.StringBuffer();
        r4.append(r11.attributeUri[r2]);
        r4.append(":");
        r4.append(r1);
        r1 = r4.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01e8, code lost:
        r4 = new java.lang.StringBuffer();
        r4.append("duplicated attributes ");
        r4.append(r0);
        r4.append(" and ");
        r4.append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0206, code lost:
        throw new javax.xml.stream.XMLStreamException(r4.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0207, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x020b, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x020f, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0212, code lost:
        if (r2 >= r11.attributeCount) goto L_0x0273;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0214, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0215, code lost:
        if (r7 >= r2) goto L_0x0270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0219, code lost:
        if (r11.allStringsInterned == false) goto L_0x0229;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0227, code lost:
        if (r11.attributeName[r7].equals(r11.attributeName[r2]) != false) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x022b, code lost:
        if (r11.allStringsInterned != false) goto L_0x026d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0235, code lost:
        if (r11.attributeNameHash[r7] != r11.attributeNameHash[r2]) goto L_0x026d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0243, code lost:
        if (r11.attributeName[r7].equals(r11.attributeName[r2]) != false) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0246, code lost:
        r0 = r11.attributeName[r7];
        r1 = r11.attributeName[r2];
        r4 = new java.lang.StringBuffer();
        r4.append("duplicated attributes ");
        r4.append(r0);
        r4.append(" and ");
        r4.append(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parseStartTag() throws javax.xml.stream.XMLStreamException {
        /*
            r11 = this;
            r0 = 1
            r11.eventType = r0
            int r1 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            int r1 = r1 + r0
            r11.depth = r1     // Catch:{ EOFException -> 0x0319 }
            int r1 = r11.pos     // Catch:{ EOFException -> 0x0319 }
            int r1 = r1 + -2
            r11.posStart = r1     // Catch:{ EOFException -> 0x0319 }
            r1 = 0
            r11.emptyElementTag = r1     // Catch:{ EOFException -> 0x0319 }
            r11.attributeCount = r1     // Catch:{ EOFException -> 0x0319 }
            r11.localNamespaceEnd = r1     // Catch:{ EOFException -> 0x0319 }
            int r2 = r11.pos     // Catch:{ EOFException -> 0x0319 }
            int r2 = r2 - r0
            int r3 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r2 = r2 + r3
            char[] r3 = r11.buf     // Catch:{ EOFException -> 0x0319 }
            int r4 = r11.pos     // Catch:{ EOFException -> 0x0319 }
            int r4 = r4 - r0
            char r3 = r3[r4]     // Catch:{ EOFException -> 0x0319 }
            r4 = 58
            if (r3 != r4) goto L_0x0038
            boolean r3 = r11.processNamespaces     // Catch:{ EOFException -> 0x0319 }
            if (r3 != 0) goto L_0x002b
            goto L_0x0038
        L_0x002b:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = "when namespaces processing enabled colon can not be at element name start"
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0319 }
            throw r0     // Catch:{ EOFException -> 0x0319 }
        L_0x0038:
            r3 = -1
            r5 = -1
        L_0x003a:
            char r6 = r11.more()     // Catch:{ EOFException -> 0x0319 }
            boolean r7 = r11.isNameChar(r6)     // Catch:{ EOFException -> 0x0319 }
            if (r7 != 0) goto L_0x02fd
            r11.ensureElementsCapacity()     // Catch:{ EOFException -> 0x0319 }
            int r4 = r11.pos     // Catch:{ EOFException -> 0x0319 }
            int r4 = r4 - r0
            int r7 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r7 = r2 - r7
            int r4 = r4 - r7
            char[][] r7 = r11.elRawName     // Catch:{ EOFException -> 0x0319 }
            int r8 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            r7 = r7[r8]     // Catch:{ EOFException -> 0x0319 }
            if (r7 == 0) goto L_0x0060
            char[][] r7 = r11.elRawName     // Catch:{ EOFException -> 0x0319 }
            int r8 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            r7 = r7[r8]     // Catch:{ EOFException -> 0x0319 }
            int r7 = r7.length     // Catch:{ EOFException -> 0x0319 }
            if (r7 >= r4) goto L_0x006a
        L_0x0060:
            char[][] r7 = r11.elRawName     // Catch:{ EOFException -> 0x0319 }
            int r8 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            int r9 = r4 * 2
            char[] r9 = new char[r9]     // Catch:{ EOFException -> 0x0319 }
            r7[r8] = r9     // Catch:{ EOFException -> 0x0319 }
        L_0x006a:
            char[] r7 = r11.buf     // Catch:{ EOFException -> 0x0319 }
            int r8 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r8 = r2 - r8
            char[][] r9 = r11.elRawName     // Catch:{ EOFException -> 0x0319 }
            int r10 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r10]     // Catch:{ EOFException -> 0x0319 }
            java.lang.System.arraycopy(r7, r8, r9, r1, r4)     // Catch:{ EOFException -> 0x0319 }
            int[] r7 = r11.elRawNameEnd     // Catch:{ EOFException -> 0x0319 }
            int r8 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            r7[r8] = r4     // Catch:{ EOFException -> 0x0319 }
            boolean r7 = r11.processNamespaces     // Catch:{ EOFException -> 0x0319 }
            r8 = 0
            if (r7 == 0) goto L_0x00c8
            if (r5 == r3) goto L_0x00b2
            java.lang.String[] r3 = r11.elPrefix     // Catch:{ EOFException -> 0x0319 }
            int r4 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            char[] r7 = r11.buf     // Catch:{ EOFException -> 0x0319 }
            int r8 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r8 = r2 - r8
            int r2 = r5 - r2
            java.lang.String r8 = r11.newString(r7, r8, r2)     // Catch:{ EOFException -> 0x0319 }
            r3[r4] = r8     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r2 = r11.elName     // Catch:{ EOFException -> 0x0319 }
            int r3 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            char[] r4 = r11.buf     // Catch:{ EOFException -> 0x0319 }
            int r7 = r5 + 1
            int r9 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r7 = r7 - r9
            int r9 = r11.pos     // Catch:{ EOFException -> 0x0319 }
            int r9 = r9 + -2
            int r10 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r5 = r5 - r10
            int r9 = r9 - r5
            java.lang.String r4 = r11.newString(r4, r7, r9)     // Catch:{ EOFException -> 0x0319 }
            r2[r3] = r4     // Catch:{ EOFException -> 0x0319 }
            goto L_0x00d7
        L_0x00b2:
            java.lang.String[] r3 = r11.elPrefix     // Catch:{ EOFException -> 0x0319 }
            int r5 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            r3[r5] = r8     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r3 = r11.elName     // Catch:{ EOFException -> 0x0319 }
            int r5 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            char[] r7 = r11.buf     // Catch:{ EOFException -> 0x0319 }
            int r9 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r2 = r2 - r9
            java.lang.String r4 = r11.newString(r7, r2, r4)     // Catch:{ EOFException -> 0x0319 }
            r3[r5] = r4     // Catch:{ EOFException -> 0x0319 }
            goto L_0x00d7
        L_0x00c8:
            java.lang.String[] r3 = r11.elName     // Catch:{ EOFException -> 0x0319 }
            int r5 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            char[] r7 = r11.buf     // Catch:{ EOFException -> 0x0319 }
            int r9 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r2 = r2 - r9
            java.lang.String r4 = r11.newString(r7, r2, r4)     // Catch:{ EOFException -> 0x0319 }
            r3[r5] = r4     // Catch:{ EOFException -> 0x0319 }
        L_0x00d7:
            boolean r2 = r11.isS(r6)     // Catch:{ EOFException -> 0x0319 }
            if (r2 == 0) goto L_0x00e7
        L_0x00dd:
            char r6 = r11.more()     // Catch:{ EOFException -> 0x0319 }
            boolean r3 = r11.isS(r6)     // Catch:{ EOFException -> 0x0319 }
            if (r3 != 0) goto L_0x00dd
        L_0x00e7:
            r3 = 62
            if (r6 != r3) goto L_0x00ec
            goto L_0x00f8
        L_0x00ec:
            r5 = 47
            if (r6 != r5) goto L_0x02be
            r11.emptyElementTag = r0     // Catch:{ EOFException -> 0x0319 }
            char r2 = r11.more()     // Catch:{ EOFException -> 0x0319 }
            if (r2 != r3) goto L_0x029f
        L_0x00f8:
            boolean r2 = r11.processNamespaces     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r3 = " and "
            java.lang.String r5 = "duplicated attributes "
            java.lang.String r6 = ":"
            if (r2 == 0) goto L_0x020f
            java.lang.String r2 = r11.getNamespaceURI((java.lang.String) r8)     // Catch:{ EOFException -> 0x0319 }
            if (r2 != 0) goto L_0x0128
            if (r8 != 0) goto L_0x010d
            java.lang.String r2 = NO_NAMESPACE     // Catch:{ EOFException -> 0x0319 }
            goto L_0x0128
        L_0x010d:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r1.<init>()     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r2 = "could not determine namespace bound to element prefix "
            r1.append(r2)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r8)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = r1.toString()     // Catch:{ EOFException -> 0x0319 }
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0319 }
            throw r0     // Catch:{ EOFException -> 0x0319 }
        L_0x0128:
            java.lang.String[] r7 = r11.elUri     // Catch:{ EOFException -> 0x0319 }
            int r9 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            r7[r9] = r2     // Catch:{ EOFException -> 0x0319 }
            r2 = 0
        L_0x012f:
            int r7 = r11.attributeCount     // Catch:{ EOFException -> 0x0319 }
            if (r2 >= r7) goto L_0x0168
            java.lang.String[] r7 = r11.attributePrefix     // Catch:{ EOFException -> 0x0319 }
            r7 = r7[r2]     // Catch:{ EOFException -> 0x0319 }
            if (r7 == 0) goto L_0x015f
            java.lang.String r9 = r11.getNamespaceURI((java.lang.String) r7)     // Catch:{ EOFException -> 0x0319 }
            if (r9 == 0) goto L_0x0144
            java.lang.String[] r7 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            r7[r2] = r9     // Catch:{ EOFException -> 0x0319 }
            goto L_0x0165
        L_0x0144:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r1.<init>()     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r2 = "could not determine namespace bound to attribute prefix "
            r1.append(r2)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r7)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = r1.toString()     // Catch:{ EOFException -> 0x0319 }
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0319 }
            throw r0     // Catch:{ EOFException -> 0x0319 }
        L_0x015f:
            java.lang.String[] r7 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r9 = NO_NAMESPACE     // Catch:{ EOFException -> 0x0319 }
            r7[r2] = r9     // Catch:{ EOFException -> 0x0319 }
        L_0x0165:
            int r2 = r2 + 1
            goto L_0x012f
        L_0x0168:
            r2 = 1
        L_0x0169:
            int r7 = r11.attributeCount     // Catch:{ EOFException -> 0x0319 }
            if (r2 >= r7) goto L_0x0273
            r7 = 0
        L_0x016e:
            if (r7 >= r2) goto L_0x020b
            java.lang.String[] r9 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r7]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r10 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            r10 = r10[r2]     // Catch:{ EOFException -> 0x0319 }
            if (r9 != r10) goto L_0x0207
            boolean r9 = r11.allStringsInterned     // Catch:{ EOFException -> 0x0319 }
            if (r9 == 0) goto L_0x018c
            java.lang.String[] r9 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r7]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r10 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r10 = r10[r2]     // Catch:{ EOFException -> 0x0319 }
            boolean r9 = r9.equals(r10)     // Catch:{ EOFException -> 0x0319 }
            if (r9 != 0) goto L_0x01a8
        L_0x018c:
            boolean r9 = r11.allStringsInterned     // Catch:{ EOFException -> 0x0319 }
            if (r9 != 0) goto L_0x0207
            int[] r9 = r11.attributeNameHash     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r7]     // Catch:{ EOFException -> 0x0319 }
            int[] r10 = r11.attributeNameHash     // Catch:{ EOFException -> 0x0319 }
            r10 = r10[r2]     // Catch:{ EOFException -> 0x0319 }
            if (r9 != r10) goto L_0x0207
            java.lang.String[] r9 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r7]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r10 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r10 = r10[r2]     // Catch:{ EOFException -> 0x0319 }
            boolean r9 = r9.equals(r10)     // Catch:{ EOFException -> 0x0319 }
            if (r9 == 0) goto L_0x0207
        L_0x01a8:
            java.lang.String[] r0 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r0 = r0[r7]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r1 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            r1 = r1[r7]     // Catch:{ EOFException -> 0x0319 }
            if (r1 == 0) goto L_0x01c8
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r1.<init>()     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r4 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            r4 = r4[r7]     // Catch:{ EOFException -> 0x0319 }
            r1.append(r4)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r6)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r0)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r0 = r1.toString()     // Catch:{ EOFException -> 0x0319 }
        L_0x01c8:
            java.lang.String[] r1 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r1 = r1[r2]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r4 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            r4 = r4[r2]     // Catch:{ EOFException -> 0x0319 }
            if (r4 == 0) goto L_0x01e8
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r4.<init>()     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r7 = r11.attributeUri     // Catch:{ EOFException -> 0x0319 }
            r2 = r7[r2]     // Catch:{ EOFException -> 0x0319 }
            r4.append(r2)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r6)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r1)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = r4.toString()     // Catch:{ EOFException -> 0x0319 }
        L_0x01e8:
            javax.xml.stream.XMLStreamException r2 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r4.<init>()     // Catch:{ EOFException -> 0x0319 }
            r4.append(r5)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r0)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r3)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r1)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r0 = r4.toString()     // Catch:{ EOFException -> 0x0319 }
            javax.xml.stream.Location r1 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r2.<init>((java.lang.String) r0, (javax.xml.stream.Location) r1)     // Catch:{ EOFException -> 0x0319 }
            throw r2     // Catch:{ EOFException -> 0x0319 }
        L_0x0207:
            int r7 = r7 + 1
            goto L_0x016e
        L_0x020b:
            int r2 = r2 + 1
            goto L_0x0169
        L_0x020f:
            r2 = 1
        L_0x0210:
            int r7 = r11.attributeCount     // Catch:{ EOFException -> 0x0319 }
            if (r2 >= r7) goto L_0x0273
            r7 = 0
        L_0x0215:
            if (r7 >= r2) goto L_0x0270
            boolean r9 = r11.allStringsInterned     // Catch:{ EOFException -> 0x0319 }
            if (r9 == 0) goto L_0x0229
            java.lang.String[] r9 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r7]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r10 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r10 = r10[r2]     // Catch:{ EOFException -> 0x0319 }
            boolean r9 = r9.equals(r10)     // Catch:{ EOFException -> 0x0319 }
            if (r9 != 0) goto L_0x0246
        L_0x0229:
            boolean r9 = r11.allStringsInterned     // Catch:{ EOFException -> 0x0319 }
            if (r9 != 0) goto L_0x026d
            int[] r9 = r11.attributeNameHash     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r7]     // Catch:{ EOFException -> 0x0319 }
            int[] r10 = r11.attributeNameHash     // Catch:{ EOFException -> 0x0319 }
            r10 = r10[r2]     // Catch:{ EOFException -> 0x0319 }
            if (r9 != r10) goto L_0x026d
            java.lang.String[] r9 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r9 = r9[r7]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r10 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r10 = r10[r2]     // Catch:{ EOFException -> 0x0319 }
            boolean r9 = r9.equals(r10)     // Catch:{ EOFException -> 0x0319 }
            if (r9 != 0) goto L_0x0246
            goto L_0x026d
        L_0x0246:
            java.lang.String[] r0 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r0 = r0[r7]     // Catch:{ EOFException -> 0x0319 }
            java.lang.String[] r1 = r11.attributeName     // Catch:{ EOFException -> 0x0319 }
            r1 = r1[r2]     // Catch:{ EOFException -> 0x0319 }
            javax.xml.stream.XMLStreamException r2 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r4.<init>()     // Catch:{ EOFException -> 0x0319 }
            r4.append(r5)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r0)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r3)     // Catch:{ EOFException -> 0x0319 }
            r4.append(r1)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r0 = r4.toString()     // Catch:{ EOFException -> 0x0319 }
            javax.xml.stream.Location r1 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r2.<init>((java.lang.String) r0, (javax.xml.stream.Location) r1)     // Catch:{ EOFException -> 0x0319 }
            throw r2     // Catch:{ EOFException -> 0x0319 }
        L_0x026d:
            int r7 = r7 + 1
            goto L_0x0215
        L_0x0270:
            int r2 = r2 + 1
            goto L_0x0210
        L_0x0273:
            int[] r1 = r11.elNamespaceCount     // Catch:{ EOFException -> 0x0319 }
            int r2 = r11.depth     // Catch:{ EOFException -> 0x0319 }
            int r3 = r11.namespaceEnd     // Catch:{ EOFException -> 0x0319 }
            r1[r2] = r3     // Catch:{ EOFException -> 0x0319 }
            int r1 = r11.pos     // Catch:{ EOFException -> 0x0319 }
            r11.posEnd = r1     // Catch:{ EOFException -> 0x0319 }
            java.util.HashMap r1 = r11.defaultAttributes     // Catch:{ EOFException -> 0x0319 }
            if (r1 == 0) goto L_0x029e
            if (r8 == 0) goto L_0x029b
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r1.<init>()     // Catch:{ EOFException -> 0x0319 }
            r1.append(r8)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r6)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r4)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = r1.toString()     // Catch:{ EOFException -> 0x0319 }
            r11.addDefaultAttributes(r1)     // Catch:{ EOFException -> 0x0319 }
            goto L_0x029e
        L_0x029b:
            r11.addDefaultAttributes(r4)     // Catch:{ EOFException -> 0x0319 }
        L_0x029e:
            return r0
        L_0x029f:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r1.<init>()     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r3 = "expected > to end empty tag not "
            r1.append(r3)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r2 = r11.printable((char) r2)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r2)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = r1.toString()     // Catch:{ EOFException -> 0x0319 }
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0319 }
            throw r0     // Catch:{ EOFException -> 0x0319 }
        L_0x02be:
            boolean r5 = r11.isNameStartChar(r6)     // Catch:{ EOFException -> 0x0319 }
            if (r5 == 0) goto L_0x02de
            if (r2 != 0) goto L_0x02d5
            if (r6 != r3) goto L_0x02c9
            goto L_0x02d5
        L_0x02c9:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = "expected a white space between attributes"
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0319 }
            throw r0     // Catch:{ EOFException -> 0x0319 }
        L_0x02d5:
            r11.parseAttribute()     // Catch:{ EOFException -> 0x0319 }
            char r6 = r11.more()     // Catch:{ EOFException -> 0x0319 }
            goto L_0x00d7
        L_0x02de:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x0319 }
            r1.<init>()     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r2 = "start tag unexpected character "
            r1.append(r2)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r2 = r11.printable((char) r6)     // Catch:{ EOFException -> 0x0319 }
            r1.append(r2)     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = r1.toString()     // Catch:{ EOFException -> 0x0319 }
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0319 }
            throw r0     // Catch:{ EOFException -> 0x0319 }
        L_0x02fd:
            if (r6 != r4) goto L_0x003a
            boolean r6 = r11.processNamespaces     // Catch:{ EOFException -> 0x0319 }
            if (r6 == 0) goto L_0x003a
            if (r5 != r3) goto L_0x030d
            int r5 = r11.pos     // Catch:{ EOFException -> 0x0319 }
            int r5 = r5 - r0
            int r6 = r11.bufAbsoluteStart     // Catch:{ EOFException -> 0x0319 }
            int r5 = r5 + r6
            goto L_0x003a
        L_0x030d:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x0319 }
            java.lang.String r1 = "only one colon is allowed in name of element when namespaces are enabled"
            javax.xml.stream.Location r2 = r11.getLocation()     // Catch:{ EOFException -> 0x0319 }
            r0.<init>((java.lang.String) r1, (javax.xml.stream.Location) r2)     // Catch:{ EOFException -> 0x0319 }
            throw r0     // Catch:{ EOFException -> 0x0319 }
        L_0x0319:
            r0 = move-exception
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException
            javax.xml.stream.Location r2 = r11.getLocation()
            java.lang.String r3 = "Unexpected end of stream"
            r1.<init>(r3, r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.parseStartTag():int");
    }

    /* access modifiers changed from: protected */
    public void addDefaultAttributes(String str) throws XMLStreamException {
        boolean z;
        HashMap hashMap = this.defaultAttributes;
        if (hashMap != null) {
            DTDAttlist dTDAttlist = (DTDAttlist) hashMap.get(str);
            if (str != null && dTDAttlist != null) {
                DTDAttribute[] attribute = dTDAttlist.getAttribute();
                for (DTDAttribute dTDAttribute : attribute) {
                    if (dTDAttribute.getDefaultValue() != null) {
                        int i = this.attributeCount;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= i) {
                                z = false;
                                break;
                            } else if (this.attributeName[i2].equals(dTDAttribute.getName())) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z) {
                            int i3 = this.attributeCount + 1;
                            this.attributeCount = i3;
                            ensureAttributesCapacity(i3);
                            String[] strArr = this.attributePrefix;
                            int i4 = this.attributeCount;
                            strArr[i4 - 1] = null;
                            this.attributeUri[i4 - 1] = NO_NAMESPACE;
                            this.attributeName[i4 - 1] = dTDAttribute.getName();
                            this.attributeValue[this.attributeCount - 1] = dTDAttribute.getDefaultValue();
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01cf, code lost:
        r5 = newStringIntern(r1.pc, r1.pcStart, r1.pcEnd - r1.pcStart);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01dc, code lost:
        ensureNamespacesCapacity(r1.namespaceEnd);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01e8, code lost:
        if (r5.equals(javax.xml.XMLConstants.XML_NS_URI) == false) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01ee, code lost:
        if ("xml".equals(r3) == false) goto L_0x01f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01f9, code lost:
        throw new javax.xml.stream.XMLStreamException("trying to bind reserved NS URI  'http://www.w3.org/XML/1998/namespace' to prefix other than 'xml'");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0200, code lost:
        if (r5.equals(javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI) != false) goto L_0x02d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0202, code lost:
        if (r11 == r9) goto L_0x0245;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0208, code lost:
        if (r5.length() == 0) goto L_0x0239;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x020e, code lost:
        if (r3.equals("xml") == false) goto L_0x0224;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0214, code lost:
        if (r5.equals(javax.xml.XMLConstants.XML_NS_URI) == false) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0223, code lost:
        throw new javax.xml.stream.XMLStreamException("trying to bind reserved NS prefix 'xml' to URI other than its standard value (http://www.w3.org/XML/1998/namespace)", getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0224, code lost:
        r1.namespacePrefix[r1.namespaceEnd] = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x022c, code lost:
        if (r1.allStringsInterned != false) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x022e, code lost:
        r0 = r1.namespacePrefixHash;
        r6 = r1.namespaceEnd;
        r9 = r3.hashCode();
        r0[r6] = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0244, code lost:
        throw new javax.xml.stream.XMLStreamException("non-default namespace can not be declared to be empty string (in xml 1.0)", getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0245, code lost:
        r1.namespacePrefix[r1.namespaceEnd] = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0250, code lost:
        if (r5.length() != 0) goto L_0x0254;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0252, code lost:
        r5 = NO_NAMESPACE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0256, code lost:
        if (r1.allStringsInterned != false) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0258, code lost:
        r1.namespacePrefixHash[r1.namespaceEnd] = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x025e, code lost:
        r1.namespaceUri[r1.namespaceEnd] = r5;
        r0 = r1.elNamespaceCount[r1.depth - 1];
        r5 = r1.namespaceEnd - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x026e, code lost:
        if (r5 < r0) goto L_0x02cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0272, code lost:
        if (r1.allStringsInterned != false) goto L_0x0276;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0274, code lost:
        if (r3 != null) goto L_0x027c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x027a, code lost:
        if (r1.namespacePrefix[r5] == r3) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x027e, code lost:
        if (r1.allStringsInterned != false) goto L_0x0293;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0280, code lost:
        if (r3 == null) goto L_0x0293;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0286, code lost:
        if (r1.namespacePrefixHash[r5] != r9) goto L_0x0293;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0290, code lost:
        if (r3.equals(r1.namespacePrefix[r5]) == false) goto L_0x0293;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0293, code lost:
        r5 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0296, code lost:
        if (r3 != null) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0298, code lost:
        r0 = "default";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x029b, code lost:
        r0 = new java.lang.StringBuffer();
        r0.append("'");
        r0.append(r3);
        r0.append("'");
        r0 = r0.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x02ad, code lost:
        r3 = new java.lang.StringBuffer();
        r3.append("duplicated namespace declaration for ");
        r3.append(r0);
        r3.append(" prefix");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x02cc, code lost:
        throw new javax.xml.stream.XMLStreamException(r3.toString(), getLocation());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02cd, code lost:
        r1.namespaceEnd++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x02db, code lost:
        throw new javax.xml.stream.XMLStreamException("trying to bind reserved NS URI  'http://www.w3.org/2000/xmlns/'");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x02de, code lost:
        if (r1.usePC != false) goto L_0x02f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x02e0, code lost:
        r1.attributeValue[r1.attributeCount] = new java.lang.String(r1.buf, r1.posStart, (r1.pos - 1) - r1.posStart);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x02f6, code lost:
        r1.attributeValue[r1.attributeCount] = new java.lang.String(r1.pc, r1.pcStart, r1.pcEnd - r1.pcStart);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x030a, code lost:
        r1.attributeCount++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x030f, code lost:
        r1.posStart = r2 - r1.bufAbsoluteStart;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0314, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0057, code lost:
        if (r7 != 'm') goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01b8, code lost:
        if (r1.processNamespaces == false) goto L_0x02dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01ba, code lost:
        if (r5 == false) goto L_0x02dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01be, code lost:
        if (r1.usePC != false) goto L_0x01cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01c0, code lost:
        r5 = newStringIntern(r1.buf, r1.posStart, (r1.pos - 1) - r1.posStart);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char parseAttribute() throws javax.xml.stream.XMLStreamException {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r0 = "http://www.w3.org/XML/1998/namespace"
            int r2 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            int r3 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r2 = r2 + r3
            int r3 = r1.pos     // Catch:{ EOFException -> 0x041b }
            r4 = 1
            int r3 = r3 - r4
            int r5 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r3 = r3 + r5
            char[] r5 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r6 = r6 - r4
            char r5 = r5[r6]     // Catch:{ EOFException -> 0x041b }
            r6 = 58
            if (r5 != r6) goto L_0x002d
            boolean r7 = r1.processNamespaces     // Catch:{ EOFException -> 0x041b }
            if (r7 != 0) goto L_0x0020
            goto L_0x002d
        L_0x0020:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "when namespaces processing enabled colon can not be at attribute name start"
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x002d:
            boolean r7 = r1.processNamespaces     // Catch:{ EOFException -> 0x041b }
            r8 = 0
            if (r7 == 0) goto L_0x0038
            r7 = 120(0x78, float:1.68E-43)
            if (r5 != r7) goto L_0x0038
            r5 = 1
            goto L_0x0039
        L_0x0038:
            r5 = 0
        L_0x0039:
            char r7 = r16.more()     // Catch:{ EOFException -> 0x041b }
            r9 = -1
            r10 = 0
            r11 = -1
        L_0x0040:
            boolean r12 = r1.isNameChar(r7)     // Catch:{ EOFException -> 0x041b }
            r13 = 4
            r14 = 2
            if (r12 == 0) goto L_0x009e
            boolean r12 = r1.processNamespaces     // Catch:{ EOFException -> 0x041b }
            if (r12 == 0) goto L_0x0099
            if (r5 == 0) goto L_0x0082
            r12 = 5
            if (r10 >= r12) goto L_0x0082
            int r10 = r10 + 1
            if (r10 != r4) goto L_0x005b
            r12 = 109(0x6d, float:1.53E-43)
            if (r7 == r12) goto L_0x0082
        L_0x0059:
            r5 = 0
            goto L_0x0082
        L_0x005b:
            if (r10 != r14) goto L_0x0062
            r12 = 108(0x6c, float:1.51E-43)
            if (r7 == r12) goto L_0x0082
            goto L_0x0059
        L_0x0062:
            r14 = 3
            if (r10 != r14) goto L_0x006a
            r12 = 110(0x6e, float:1.54E-43)
            if (r7 == r12) goto L_0x0082
            goto L_0x0059
        L_0x006a:
            if (r10 != r13) goto L_0x0071
            r12 = 115(0x73, float:1.61E-43)
            if (r7 == r12) goto L_0x0082
            goto L_0x0059
        L_0x0071:
            if (r10 != r12) goto L_0x0082
            if (r7 != r6) goto L_0x0076
            goto L_0x0082
        L_0x0076:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "after xmlns in attribute name must be colonwhen namespaces are enabled"
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x0082:
            if (r7 != r6) goto L_0x0099
            if (r11 != r9) goto L_0x008d
            int r7 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r7 = r7 - r4
            int r11 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r11 = r11 + r7
            goto L_0x0099
        L_0x008d:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "only one colon is allowed in attribute name when namespaces are enabled"
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x0099:
            char r7 = r16.more()     // Catch:{ EOFException -> 0x041b }
            goto L_0x0040
        L_0x009e:
            int r6 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            r1.ensureAttributesCapacity(r6)     // Catch:{ EOFException -> 0x041b }
            boolean r6 = r1.processNamespaces     // Catch:{ EOFException -> 0x041b }
            r12 = 0
            if (r6 == 0) goto L_0x0138
            if (r10 >= r13) goto L_0x00ab
            r5 = 0
        L_0x00ab:
            if (r5 == 0) goto L_0x00dd
            if (r11 == r9) goto L_0x00da
            char[] r3 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r6 = r11 - r6
            int r6 = r6 + r4
            int r10 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r10 = r10 - r14
            int r13 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r13 = r11 - r13
            int r10 = r10 - r13
            java.lang.String r3 = r1.newString(r3, r6, r10)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r6 = "xmlns"
            boolean r6 = r3.equals(r6)     // Catch:{ EOFException -> 0x041b }
            if (r6 != 0) goto L_0x00cd
            goto L_0x015d
        L_0x00cd:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "trying to bind reserved NS prefix 'xmlns'"
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x00da:
            r3 = r12
            goto L_0x015d
        L_0x00dd:
            if (r11 == r9) goto L_0x010c
            java.lang.String[] r6 = r1.attributePrefix     // Catch:{ EOFException -> 0x041b }
            int r10 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            char[] r13 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r15 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r15 = r3 - r15
            int r3 = r11 - r3
            java.lang.String r3 = r1.newString(r13, r15, r3)     // Catch:{ EOFException -> 0x041b }
            r6[r10] = r3     // Catch:{ EOFException -> 0x041b }
            java.lang.String[] r3 = r1.attributeName     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            char[] r10 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r13 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r13 = r11 - r13
            int r13 = r13 + r4
            int r15 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r15 = r15 - r14
            int r14 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r14 = r11 - r14
            int r15 = r15 - r14
            java.lang.String r10 = r1.newString(r10, r13, r15)     // Catch:{ EOFException -> 0x041b }
            r3[r6] = r10     // Catch:{ EOFException -> 0x041b }
            r3 = r10
            goto L_0x0129
        L_0x010c:
            java.lang.String[] r6 = r1.attributePrefix     // Catch:{ EOFException -> 0x041b }
            int r10 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            r6[r10] = r12     // Catch:{ EOFException -> 0x041b }
            java.lang.String[] r6 = r1.attributeName     // Catch:{ EOFException -> 0x041b }
            int r10 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            char[] r13 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r14 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r14 = r3 - r14
            int r15 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r15 = r15 - r4
            int r12 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r3 = r3 - r12
            int r15 = r15 - r3
            java.lang.String r3 = r1.newString(r13, r14, r15)     // Catch:{ EOFException -> 0x041b }
            r6[r10] = r3     // Catch:{ EOFException -> 0x041b }
        L_0x0129:
            boolean r6 = r1.allStringsInterned     // Catch:{ EOFException -> 0x041b }
            if (r6 != 0) goto L_0x015d
            int[] r6 = r1.attributeNameHash     // Catch:{ EOFException -> 0x041b }
            int r10 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            int r12 = r3.hashCode()     // Catch:{ EOFException -> 0x041b }
            r6[r10] = r12     // Catch:{ EOFException -> 0x041b }
            goto L_0x015d
        L_0x0138:
            java.lang.String[] r6 = r1.attributeName     // Catch:{ EOFException -> 0x041b }
            int r10 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            char[] r12 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r13 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r13 = r3 - r13
            int r14 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r14 = r14 - r4
            int r15 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r3 = r3 - r15
            int r14 = r14 - r3
            java.lang.String r3 = r1.newString(r12, r13, r14)     // Catch:{ EOFException -> 0x041b }
            r6[r10] = r3     // Catch:{ EOFException -> 0x041b }
            boolean r6 = r1.allStringsInterned     // Catch:{ EOFException -> 0x041b }
            if (r6 != 0) goto L_0x015d
            int[] r6 = r1.attributeNameHash     // Catch:{ EOFException -> 0x041b }
            int r10 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            int r12 = r3.hashCode()     // Catch:{ EOFException -> 0x041b }
            r6[r10] = r12     // Catch:{ EOFException -> 0x041b }
        L_0x015d:
            boolean r6 = r1.isS(r7)     // Catch:{ EOFException -> 0x041b }
            if (r6 == 0) goto L_0x0168
            char r7 = r16.more()     // Catch:{ EOFException -> 0x041b }
            goto L_0x015d
        L_0x0168:
            r6 = 61
            if (r7 != r6) goto L_0x040f
            char r6 = r16.more()     // Catch:{ EOFException -> 0x041b }
        L_0x0170:
            boolean r7 = r1.isS(r6)     // Catch:{ EOFException -> 0x041b }
            if (r7 == 0) goto L_0x017b
            char r6 = r16.more()     // Catch:{ EOFException -> 0x041b }
            goto L_0x0170
        L_0x017b:
            r7 = 34
            if (r6 == r7) goto L_0x01a3
            r7 = 39
            if (r6 != r7) goto L_0x0184
            goto L_0x01a3
        L_0x0184:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x041b }
            r2.<init>()     // Catch:{ EOFException -> 0x041b }
            java.lang.String r3 = "attribute value must start with quotation or apostrophe not "
            r2.append(r3)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r3 = r1.printable((char) r6)     // Catch:{ EOFException -> 0x041b }
            r2.append(r3)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = r2.toString()     // Catch:{ EOFException -> 0x041b }
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x01a3:
            r1.usePC = r8     // Catch:{ EOFException -> 0x041b }
            int r7 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            r1.pcStart = r7     // Catch:{ EOFException -> 0x041b }
            int r7 = r1.pos     // Catch:{ EOFException -> 0x041b }
            r1.posStart = r7     // Catch:{ EOFException -> 0x041b }
            r7 = 0
        L_0x01ae:
            char r10 = r16.more()     // Catch:{ EOFException -> 0x041b }
            java.lang.String r12 = "'"
            if (r10 != r6) goto L_0x0315
            boolean r6 = r1.processNamespaces     // Catch:{ EOFException -> 0x041b }
            if (r6 == 0) goto L_0x02dc
            if (r5 == 0) goto L_0x02dc
            boolean r5 = r1.usePC     // Catch:{ EOFException -> 0x041b }
            if (r5 != 0) goto L_0x01cf
            char[] r5 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            int r7 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r7 = r7 - r4
            int r8 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            int r7 = r7 - r8
            java.lang.String r5 = r1.newStringIntern(r5, r6, r7)     // Catch:{ EOFException -> 0x041b }
            goto L_0x01dc
        L_0x01cf:
            char[] r5 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.pcStart     // Catch:{ EOFException -> 0x041b }
            int r7 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            int r8 = r1.pcStart     // Catch:{ EOFException -> 0x041b }
            int r7 = r7 - r8
            java.lang.String r5 = r1.newStringIntern(r5, r6, r7)     // Catch:{ EOFException -> 0x041b }
        L_0x01dc:
            int r6 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            r1.ensureNamespacesCapacity(r6)     // Catch:{ EOFException -> 0x041b }
            boolean r6 = r5.equals(r0)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r7 = "xml"
            if (r6 == 0) goto L_0x01fa
            boolean r6 = r7.equals(r3)     // Catch:{ EOFException -> 0x041b }
            if (r6 == 0) goto L_0x01f1
            goto L_0x0202
        L_0x01f1:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "trying to bind reserved NS URI  'http://www.w3.org/XML/1998/namespace' to prefix other than 'xml'"
            r0.<init>((java.lang.String) r2)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x01fa:
            java.lang.String r6 = "http://www.w3.org/2000/xmlns/"
            boolean r6 = r5.equals(r6)     // Catch:{ EOFException -> 0x041b }
            if (r6 != 0) goto L_0x02d3
        L_0x0202:
            if (r11 == r9) goto L_0x0245
            int r6 = r5.length()     // Catch:{ EOFException -> 0x041b }
            if (r6 == 0) goto L_0x0239
            boolean r6 = r3.equals(r7)     // Catch:{ EOFException -> 0x041b }
            if (r6 == 0) goto L_0x0224
            boolean r0 = r5.equals(r0)     // Catch:{ EOFException -> 0x041b }
            if (r0 == 0) goto L_0x0217
            goto L_0x0224
        L_0x0217:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "trying to bind reserved NS prefix 'xml' to URI other than its standard value (http://www.w3.org/XML/1998/namespace)"
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x0224:
            java.lang.String[] r0 = r1.namespacePrefix     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            r0[r6] = r3     // Catch:{ EOFException -> 0x041b }
            boolean r0 = r1.allStringsInterned     // Catch:{ EOFException -> 0x041b }
            if (r0 != 0) goto L_0x025e
            int[] r0 = r1.namespacePrefixHash     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            int r9 = r3.hashCode()     // Catch:{ EOFException -> 0x041b }
            r0[r6] = r9     // Catch:{ EOFException -> 0x041b }
            goto L_0x025e
        L_0x0239:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "non-default namespace can not be declared to be empty string (in xml 1.0)"
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x0245:
            java.lang.String[] r0 = r1.namespacePrefix     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            r13 = 0
            r0[r6] = r13     // Catch:{ EOFException -> 0x041b }
            int r0 = r5.length()     // Catch:{ EOFException -> 0x041b }
            if (r0 != 0) goto L_0x0254
            java.lang.String r5 = NO_NAMESPACE     // Catch:{ EOFException -> 0x041b }
        L_0x0254:
            boolean r0 = r1.allStringsInterned     // Catch:{ EOFException -> 0x041b }
            if (r0 != 0) goto L_0x025e
            int[] r0 = r1.namespacePrefixHash     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            r0[r6] = r9     // Catch:{ EOFException -> 0x041b }
        L_0x025e:
            java.lang.String[] r0 = r1.namespaceUri     // Catch:{ EOFException -> 0x041b }
            int r6 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            r0[r6] = r5     // Catch:{ EOFException -> 0x041b }
            int[] r0 = r1.elNamespaceCount     // Catch:{ EOFException -> 0x041b }
            int r5 = r1.depth     // Catch:{ EOFException -> 0x041b }
            int r5 = r5 - r4
            r0 = r0[r5]     // Catch:{ EOFException -> 0x041b }
            int r5 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            int r5 = r5 - r4
        L_0x026e:
            if (r5 < r0) goto L_0x02cd
            boolean r6 = r1.allStringsInterned     // Catch:{ EOFException -> 0x041b }
            if (r6 != 0) goto L_0x0276
            if (r3 != 0) goto L_0x027c
        L_0x0276:
            java.lang.String[] r6 = r1.namespacePrefix     // Catch:{ EOFException -> 0x041b }
            r6 = r6[r5]     // Catch:{ EOFException -> 0x041b }
            if (r6 == r3) goto L_0x0296
        L_0x027c:
            boolean r6 = r1.allStringsInterned     // Catch:{ EOFException -> 0x041b }
            if (r6 != 0) goto L_0x0293
            if (r3 == 0) goto L_0x0293
            int[] r6 = r1.namespacePrefixHash     // Catch:{ EOFException -> 0x041b }
            r6 = r6[r5]     // Catch:{ EOFException -> 0x041b }
            if (r6 != r9) goto L_0x0293
            java.lang.String[] r6 = r1.namespacePrefix     // Catch:{ EOFException -> 0x041b }
            r6 = r6[r5]     // Catch:{ EOFException -> 0x041b }
            boolean r6 = r3.equals(r6)     // Catch:{ EOFException -> 0x041b }
            if (r6 == 0) goto L_0x0293
            goto L_0x0296
        L_0x0293:
            int r5 = r5 + -1
            goto L_0x026e
        L_0x0296:
            if (r3 != 0) goto L_0x029b
            java.lang.String r0 = "default"
            goto L_0x02ad
        L_0x029b:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x041b }
            r0.<init>()     // Catch:{ EOFException -> 0x041b }
            r0.append(r12)     // Catch:{ EOFException -> 0x041b }
            r0.append(r3)     // Catch:{ EOFException -> 0x041b }
            r0.append(r12)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r0 = r0.toString()     // Catch:{ EOFException -> 0x041b }
        L_0x02ad:
            javax.xml.stream.XMLStreamException r2 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x041b }
            r3.<init>()     // Catch:{ EOFException -> 0x041b }
            java.lang.String r4 = "duplicated namespace declaration for "
            r3.append(r4)     // Catch:{ EOFException -> 0x041b }
            r3.append(r0)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r0 = " prefix"
            r3.append(r0)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r0 = r3.toString()     // Catch:{ EOFException -> 0x041b }
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r2.<init>((java.lang.String) r0, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r2     // Catch:{ EOFException -> 0x041b }
        L_0x02cd:
            int r0 = r1.namespaceEnd     // Catch:{ EOFException -> 0x041b }
            int r0 = r0 + r4
            r1.namespaceEnd = r0     // Catch:{ EOFException -> 0x041b }
            goto L_0x030f
        L_0x02d3:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "trying to bind reserved NS URI  'http://www.w3.org/2000/xmlns/'"
            r0.<init>((java.lang.String) r2)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x02dc:
            boolean r0 = r1.usePC     // Catch:{ EOFException -> 0x041b }
            if (r0 != 0) goto L_0x02f6
            java.lang.String[] r0 = r1.attributeValue     // Catch:{ EOFException -> 0x041b }
            int r3 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            java.lang.String r5 = new java.lang.String     // Catch:{ EOFException -> 0x041b }
            char[] r6 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r7 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            int r8 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r8 = r8 - r4
            int r9 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            int r8 = r8 - r9
            r5.<init>(r6, r7, r8)     // Catch:{ EOFException -> 0x041b }
            r0[r3] = r5     // Catch:{ EOFException -> 0x041b }
            goto L_0x030a
        L_0x02f6:
            java.lang.String[] r0 = r1.attributeValue     // Catch:{ EOFException -> 0x041b }
            int r3 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            java.lang.String r5 = new java.lang.String     // Catch:{ EOFException -> 0x041b }
            char[] r6 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r7 = r1.pcStart     // Catch:{ EOFException -> 0x041b }
            int r8 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            int r9 = r1.pcStart     // Catch:{ EOFException -> 0x041b }
            int r8 = r8 - r9
            r5.<init>(r6, r7, r8)     // Catch:{ EOFException -> 0x041b }
            r0[r3] = r5     // Catch:{ EOFException -> 0x041b }
        L_0x030a:
            int r0 = r1.attributeCount     // Catch:{ EOFException -> 0x041b }
            int r0 = r0 + r4
            r1.attributeCount = r0     // Catch:{ EOFException -> 0x041b }
        L_0x030f:
            int r0 = r1.bufAbsoluteStart     // Catch:{ EOFException -> 0x041b }
            int r2 = r2 - r0
            r1.posStart = r2     // Catch:{ EOFException -> 0x041b }
            return r10
        L_0x0315:
            r13 = 0
            r14 = 60
            if (r10 == r14) goto L_0x0403
            r14 = 38
            r15 = 13
            if (r10 != r14) goto L_0x03a2
            int r7 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r7 = r7 - r4
            r1.posEnd = r7     // Catch:{ EOFException -> 0x041b }
            boolean r14 = r1.usePC     // Catch:{ EOFException -> 0x041b }
            if (r14 != 0) goto L_0x033c
            int r14 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            if (r7 <= r14) goto L_0x032f
            r7 = 1
            goto L_0x0330
        L_0x032f:
            r7 = 0
        L_0x0330:
            if (r7 == 0) goto L_0x0336
            r16.joinPC()     // Catch:{ EOFException -> 0x041b }
            goto L_0x033c
        L_0x0336:
            r1.usePC = r4     // Catch:{ EOFException -> 0x041b }
            r1.pcEnd = r8     // Catch:{ EOFException -> 0x041b }
            r1.pcStart = r8     // Catch:{ EOFException -> 0x041b }
        L_0x033c:
            com.bea.xml.stream.ConfigurationContextBase r7 = r16.getConfigurationContext()     // Catch:{ EOFException -> 0x041b }
            boolean r7 = r7.isReplacingEntities()     // Catch:{ EOFException -> 0x041b }
            char[] r7 = r1.parseEntityRef(r7)     // Catch:{ EOFException -> 0x041b }
            if (r7 != 0) goto L_0x0381
            java.lang.String r0 = r1.entityRefName     // Catch:{ EOFException -> 0x041b }
            if (r0 != 0) goto L_0x035d
            char[] r0 = r1.buf     // Catch:{ EOFException -> 0x041b }
            int r2 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            int r3 = r1.posEnd     // Catch:{ EOFException -> 0x041b }
            int r4 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            int r3 = r3 - r4
            java.lang.String r0 = r1.newString(r0, r2, r3)     // Catch:{ EOFException -> 0x041b }
            r1.entityRefName = r0     // Catch:{ EOFException -> 0x041b }
        L_0x035d:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x041b }
            r2.<init>()     // Catch:{ EOFException -> 0x041b }
            java.lang.String r3 = "could not resolve entity named '"
            r2.append(r3)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r3 = r1.entityRefName     // Catch:{ EOFException -> 0x041b }
            java.lang.String r3 = r1.printable((java.lang.String) r3)     // Catch:{ EOFException -> 0x041b }
            r2.append(r3)     // Catch:{ EOFException -> 0x041b }
            r2.append(r12)     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = r2.toString()     // Catch:{ EOFException -> 0x041b }
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x0381:
            r12 = 0
        L_0x0382:
            int r14 = r7.length     // Catch:{ EOFException -> 0x041b }
            if (r12 >= r14) goto L_0x03fb
            int r14 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            char[] r9 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r9 = r9.length     // Catch:{ EOFException -> 0x041b }
            if (r14 < r9) goto L_0x0391
            int r9 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            r1.ensurePC(r9)     // Catch:{ EOFException -> 0x041b }
        L_0x0391:
            char[] r9 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r14 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            int r13 = r14 + 1
            r1.pcEnd = r13     // Catch:{ EOFException -> 0x041b }
            char r13 = r7[r12]     // Catch:{ EOFException -> 0x041b }
            r9[r14] = r13     // Catch:{ EOFException -> 0x041b }
            int r12 = r12 + 1
            r9 = -1
            r13 = 0
            goto L_0x0382
        L_0x03a2:
            r9 = 9
            r12 = 10
            if (r10 == r9) goto L_0x03c8
            if (r10 == r12) goto L_0x03c8
            if (r10 != r15) goto L_0x03ad
            goto L_0x03c8
        L_0x03ad:
            boolean r7 = r1.usePC     // Catch:{ EOFException -> 0x041b }
            if (r7 == 0) goto L_0x03fb
            int r7 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            char[] r9 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r9 = r9.length     // Catch:{ EOFException -> 0x041b }
            if (r7 < r9) goto L_0x03bd
            int r7 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            r1.ensurePC(r7)     // Catch:{ EOFException -> 0x041b }
        L_0x03bd:
            char[] r7 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r9 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            int r12 = r9 + 1
            r1.pcEnd = r12     // Catch:{ EOFException -> 0x041b }
            r7[r9] = r10     // Catch:{ EOFException -> 0x041b }
            goto L_0x03fb
        L_0x03c8:
            boolean r9 = r1.usePC     // Catch:{ EOFException -> 0x041b }
            if (r9 != 0) goto L_0x03df
            int r9 = r1.pos     // Catch:{ EOFException -> 0x041b }
            int r9 = r9 - r4
            r1.posEnd = r9     // Catch:{ EOFException -> 0x041b }
            int r13 = r1.posStart     // Catch:{ EOFException -> 0x041b }
            if (r9 <= r13) goto L_0x03d9
            r16.joinPC()     // Catch:{ EOFException -> 0x041b }
            goto L_0x03df
        L_0x03d9:
            r1.usePC = r4     // Catch:{ EOFException -> 0x041b }
            r1.pcStart = r8     // Catch:{ EOFException -> 0x041b }
            r1.pcEnd = r8     // Catch:{ EOFException -> 0x041b }
        L_0x03df:
            int r9 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            char[] r13 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r13 = r13.length     // Catch:{ EOFException -> 0x041b }
            if (r9 < r13) goto L_0x03eb
            int r9 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            r1.ensurePC(r9)     // Catch:{ EOFException -> 0x041b }
        L_0x03eb:
            if (r10 != r12) goto L_0x03ef
            if (r7 != 0) goto L_0x03fb
        L_0x03ef:
            char[] r7 = r1.pc     // Catch:{ EOFException -> 0x041b }
            int r9 = r1.pcEnd     // Catch:{ EOFException -> 0x041b }
            int r12 = r9 + 1
            r1.pcEnd = r12     // Catch:{ EOFException -> 0x041b }
            r12 = 32
            r7[r9] = r12     // Catch:{ EOFException -> 0x041b }
        L_0x03fb:
            if (r10 != r15) goto L_0x03ff
            r7 = 1
            goto L_0x0400
        L_0x03ff:
            r7 = 0
        L_0x0400:
            r9 = -1
            goto L_0x01ae
        L_0x0403:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "markup not allowed inside attribute value - illegal < "
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x040f:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x041b }
            java.lang.String r2 = "expected = after attribute name"
            javax.xml.stream.Location r3 = r16.getLocation()     // Catch:{ EOFException -> 0x041b }
            r0.<init>((java.lang.String) r2, (javax.xml.stream.Location) r3)     // Catch:{ EOFException -> 0x041b }
            throw r0     // Catch:{ EOFException -> 0x041b }
        L_0x041b:
            r0 = move-exception
            javax.xml.stream.XMLStreamException r2 = new javax.xml.stream.XMLStreamException
            javax.xml.stream.Location r3 = r16.getLocation()
            java.lang.String r4 = "Unexpected end of stream"
            r2.<init>(r4, r3, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.parseAttribute():char");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006c, code lost:
        throw new javax.xml.stream.XMLStreamException(r1.toString(), getLocation());
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00be A[Catch:{ EOFException -> 0x01e3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] parseEntityRef(boolean r11) throws javax.xml.stream.XMLStreamException {
        /*
            r10 = this;
            r0 = 0
            r10.entityRefName = r0     // Catch:{ EOFException -> 0x01e3 }
            int r0 = r10.pos     // Catch:{ EOFException -> 0x01e3 }
            r10.posStart = r0     // Catch:{ EOFException -> 0x01e3 }
            char r0 = r10.more()     // Catch:{ EOFException -> 0x01e3 }
            r1 = 35
            r2 = 97
            r3 = 59
            r4 = 2
            r5 = 1
            r6 = 0
            if (r0 != r1) goto L_0x00dd
            char r11 = r10.more()     // Catch:{ EOFException -> 0x01e3 }
            r0 = 120(0x78, float:1.68E-43)
            r1 = 1114111(0x10ffff, float:1.561202E-39)
            r7 = 57
            r8 = 48
            if (r11 != r0) goto L_0x006d
            r11 = 0
        L_0x0026:
            char r0 = r10.more()     // Catch:{ EOFException -> 0x01e3 }
            if (r0 != r3) goto L_0x002d
            goto L_0x0081
        L_0x002d:
            int r11 = r11 << 4
            if (r0 < r8) goto L_0x0037
            if (r0 > r7) goto L_0x0037
            int r0 = r0 + -48
        L_0x0035:
            int r11 = r11 + r0
            goto L_0x004b
        L_0x0037:
            if (r0 < r2) goto L_0x0040
            r9 = 102(0x66, float:1.43E-43)
            if (r0 > r9) goto L_0x0040
            int r0 = r0 + -87
            goto L_0x0035
        L_0x0040:
            r9 = 65
            if (r0 < r9) goto L_0x004e
            r9 = 70
            if (r0 > r9) goto L_0x004e
            int r0 = r0 + -55
            goto L_0x0035
        L_0x004b:
            if (r11 <= r1) goto L_0x0026
            goto L_0x0081
        L_0x004e:
            javax.xml.stream.XMLStreamException r11 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x01e3 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x01e3 }
            r1.<init>()     // Catch:{ EOFException -> 0x01e3 }
            java.lang.String r2 = "character reference (with hex value) may not contain "
            r1.append(r2)     // Catch:{ EOFException -> 0x01e3 }
            java.lang.String r0 = r10.printable((char) r0)     // Catch:{ EOFException -> 0x01e3 }
            r1.append(r0)     // Catch:{ EOFException -> 0x01e3 }
            java.lang.String r0 = r1.toString()     // Catch:{ EOFException -> 0x01e3 }
            javax.xml.stream.Location r1 = r10.getLocation()     // Catch:{ EOFException -> 0x01e3 }
            r11.<init>((java.lang.String) r0, (javax.xml.stream.Location) r1)     // Catch:{ EOFException -> 0x01e3 }
            throw r11     // Catch:{ EOFException -> 0x01e3 }
        L_0x006d:
            r0 = 0
        L_0x006e:
            if (r11 < r8) goto L_0x007e
            if (r11 > r7) goto L_0x007e
            int r0 = r0 * 10
            int r11 = r11 + -48
            int r0 = r0 + r11
            char r11 = r10.more()     // Catch:{ EOFException -> 0x01e3 }
            if (r0 <= r1) goto L_0x006e
            goto L_0x0080
        L_0x007e:
            if (r11 != r3) goto L_0x00be
        L_0x0080:
            r11 = r0
        L_0x0081:
            int r0 = r10.pos     // Catch:{ EOFException -> 0x01e3 }
            int r0 = r0 - r5
            r10.posEnd = r0     // Catch:{ EOFException -> 0x01e3 }
            r10.checkCharValidity(r11, r6)     // Catch:{ EOFException -> 0x01e3 }
            r0 = 65535(0xffff, float:9.1834E-41)
            if (r11 <= r0) goto L_0x00b4
            char[] r0 = r10.charRefTwoCharBuf     // Catch:{ EOFException -> 0x01e3 }
            if (r0 != 0) goto L_0x0096
            char[] r0 = new char[r4]     // Catch:{ EOFException -> 0x01e3 }
            r10.charRefTwoCharBuf = r0     // Catch:{ EOFException -> 0x01e3 }
        L_0x0096:
            r0 = 65536(0x10000, float:9.18355E-41)
            int r11 = r11 - r0
            char[] r0 = r10.charRefTwoCharBuf     // Catch:{ EOFException -> 0x01e3 }
            int r1 = r11 >> 10
            r2 = 55296(0xd800, float:7.7486E-41)
            int r1 = r1 + r2
            char r1 = (char) r1     // Catch:{ EOFException -> 0x01e3 }
            r0[r6] = r1     // Catch:{ EOFException -> 0x01e3 }
            char[] r0 = r10.charRefTwoCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r11 = r11 & 1023(0x3ff, float:1.434E-42)
            r1 = 56320(0xdc00, float:7.8921E-41)
            int r11 = r11 + r1
            char r11 = (char) r11     // Catch:{ EOFException -> 0x01e3 }
            r0[r5] = r11     // Catch:{ EOFException -> 0x01e3 }
            char[] r11 = r10.charRefTwoCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x00b4:
            char[] r0 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            char r11 = (char) r11     // Catch:{ EOFException -> 0x01e3 }
            r0[r6] = r11     // Catch:{ EOFException -> 0x01e3 }
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x00be:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException     // Catch:{ EOFException -> 0x01e3 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ EOFException -> 0x01e3 }
            r1.<init>()     // Catch:{ EOFException -> 0x01e3 }
            java.lang.String r2 = "character reference (with decimal value) may not contain "
            r1.append(r2)     // Catch:{ EOFException -> 0x01e3 }
            java.lang.String r11 = r10.printable((char) r11)     // Catch:{ EOFException -> 0x01e3 }
            r1.append(r11)     // Catch:{ EOFException -> 0x01e3 }
            java.lang.String r11 = r1.toString()     // Catch:{ EOFException -> 0x01e3 }
            javax.xml.stream.Location r1 = r10.getLocation()     // Catch:{ EOFException -> 0x01e3 }
            r0.<init>((java.lang.String) r11, (javax.xml.stream.Location) r1)     // Catch:{ EOFException -> 0x01e3 }
            throw r0     // Catch:{ EOFException -> 0x01e3 }
        L_0x00dd:
            char r0 = r10.more()     // Catch:{ EOFException -> 0x01e3 }
            if (r0 != r3) goto L_0x00dd
            int r0 = r10.pos     // Catch:{ EOFException -> 0x01e3 }
            int r0 = r0 - r5
            r10.posEnd = r0     // Catch:{ EOFException -> 0x01e3 }
            int r1 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r0 = r0 - r1
            r1 = 116(0x74, float:1.63E-43)
            if (r0 != r4) goto L_0x0137
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 != r3) goto L_0x0113
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r3 + r5
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            if (r2 != r1) goto L_0x0113
            if (r11 != 0) goto L_0x0108
            java.lang.String r11 = "<"
            r10.text = r11     // Catch:{ EOFException -> 0x01e3 }
        L_0x0108:
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r0 = 60
            r11[r6] = r0     // Catch:{ EOFException -> 0x01e3 }
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x0113:
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            r3 = 103(0x67, float:1.44E-43)
            if (r2 != r3) goto L_0x01dc
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r3 + r5
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            if (r2 != r1) goto L_0x01dc
            if (r11 != 0) goto L_0x012c
            java.lang.String r11 = ">"
            r10.text = r11     // Catch:{ EOFException -> 0x01e3 }
        L_0x012c:
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r0 = 62
            r11[r6] = r0     // Catch:{ EOFException -> 0x01e3 }
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x0137:
            r3 = 112(0x70, float:1.57E-43)
            r7 = 3
            if (r0 != r7) goto L_0x0169
            char[] r1 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r7 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            char r1 = r1[r7]     // Catch:{ EOFException -> 0x01e3 }
            if (r1 != r2) goto L_0x01dc
            char[] r1 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r2 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r2 = r2 + r5
            char r1 = r1[r2]     // Catch:{ EOFException -> 0x01e3 }
            r2 = 109(0x6d, float:1.53E-43)
            if (r1 != r2) goto L_0x01dc
            char[] r1 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r2 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r2 = r2 + r4
            char r1 = r1[r2]     // Catch:{ EOFException -> 0x01e3 }
            if (r1 != r3) goto L_0x01dc
            if (r11 != 0) goto L_0x015e
            java.lang.String r11 = "&"
            r10.text = r11     // Catch:{ EOFException -> 0x01e3 }
        L_0x015e:
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r0 = 38
            r11[r6] = r0     // Catch:{ EOFException -> 0x01e3 }
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x0169:
            r8 = 4
            if (r0 != r8) goto L_0x01dc
            char[] r8 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r9 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            char r8 = r8[r9]     // Catch:{ EOFException -> 0x01e3 }
            r9 = 111(0x6f, float:1.56E-43)
            if (r8 != r2) goto L_0x01a4
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r8 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r8 = r8 + r5
            char r2 = r2[r8]     // Catch:{ EOFException -> 0x01e3 }
            if (r2 != r3) goto L_0x01a4
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r3 + r4
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            if (r2 != r9) goto L_0x01a4
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r3 + r7
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            r3 = 115(0x73, float:1.61E-43)
            if (r2 != r3) goto L_0x01a4
            if (r11 != 0) goto L_0x0199
            java.lang.String r11 = "'"
            r10.text = r11     // Catch:{ EOFException -> 0x01e3 }
        L_0x0199:
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r0 = 39
            r11[r6] = r0     // Catch:{ EOFException -> 0x01e3 }
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x01a4:
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            r3 = 113(0x71, float:1.58E-43)
            if (r2 != r3) goto L_0x01dc
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r3 + r5
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            r3 = 117(0x75, float:1.64E-43)
            if (r2 != r3) goto L_0x01dc
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r3 + r4
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            if (r2 != r9) goto L_0x01dc
            char[] r2 = r10.buf     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r10.posStart     // Catch:{ EOFException -> 0x01e3 }
            int r3 = r3 + r7
            char r2 = r2[r3]     // Catch:{ EOFException -> 0x01e3 }
            if (r2 != r1) goto L_0x01dc
            if (r11 != 0) goto L_0x01d1
            java.lang.String r11 = "\""
            r10.text = r11     // Catch:{ EOFException -> 0x01e3 }
        L_0x01d1:
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r0 = 34
            r11[r6] = r0     // Catch:{ EOFException -> 0x01e3 }
            char[] r11 = r10.charRefOneCharBuf     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x01dc:
            char[] r11 = r10.lookupEntityReplacement(r0)     // Catch:{ EOFException -> 0x01e3 }
            r10.entityValue = r11     // Catch:{ EOFException -> 0x01e3 }
            return r11
        L_0x01e3:
            r11 = move-exception
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            javax.xml.stream.Location r1 = r10.getLocation()
            java.lang.String r2 = "Unexpected end of stream"
            r0.<init>(r2, r1, r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.parseEntityRef(boolean):char[]");
    }

    /* access modifiers changed from: protected */
    public char[] lookupEntityReplacement(int i) throws XMLStreamException {
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
                        this.entityRefName = this.entityName[i3];
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
    public void parseComment() throws XMLStreamException {
        int i;
        int i2;
        try {
            if (more() == '-') {
                this.posStart = this.pos;
                i = this.lineNumber;
                i2 = this.columnNumber;
                int i3 = -1;
                boolean z = false;
                int i4 = -2;
                int i5 = -2;
                while (true) {
                    char more = more();
                    i3++;
                    if (more == '-') {
                        if (i4 >= i3) {
                            break;
                        }
                        i4 = i3 + 1;
                    } else if (more == 13) {
                        this.columnNumber = 1;
                        i5 = i3 + 1;
                        if (!z) {
                            this.buf[this.pos - 1] = 10;
                        } else {
                            more = 10;
                        }
                    } else if (more == 10 && i5 == i3) {
                        if (!z) {
                            this.posEnd = this.pos - 1;
                            z = true;
                        }
                    }
                    if (z) {
                        this.buf[this.posEnd] = more;
                        this.posEnd++;
                    }
                }
                char more2 = more();
                if (more2 != '>') {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("in COMMENT after two dashes (--) next character must be '>' not ");
                    stringBuffer.append(printable(more2));
                    throw new XMLStreamException(stringBuffer.toString(), getLocation());
                } else if (z) {
                    this.posEnd--;
                } else {
                    this.posEnd = this.pos - 3;
                }
            } else {
                throw new XMLStreamException("expected <!-- for COMMENT start", getLocation());
            }
        } catch (EOFException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("COMMENT started on line ");
            stringBuffer2.append(i);
            stringBuffer2.append(" and column ");
            stringBuffer2.append(i2);
            stringBuffer2.append(" was not closed");
            throw new XMLStreamException(stringBuffer2.toString(), getLocation(), e);
        } catch (EOFException e2) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e2);
        }
    }

    public String getPITarget() {
        if (this.eventType != 3) {
            throwIllegalState(3);
        }
        return this.piTarget;
    }

    public String getPIData() {
        if (this.eventType != 3) {
            throwIllegalState(3);
        }
        return this.piData;
    }

    public NamespaceContext getNamespaceContext() {
        return new ReadOnlyNamespaceContextBase(this.namespacePrefix, this.namespaceUri, this.namespaceEnd);
    }

    /* access modifiers changed from: protected */
    public boolean parsePI() throws XMLStreamException {
        char more;
        int i = this.lineNumber;
        int i2 = this.columnNumber;
        try {
            this.piTarget = null;
            this.piData = null;
            this.posStart = this.pos;
            while (true) {
                more = more();
                if (more == '?') {
                    break;
                } else if (!isNameChar(more)) {
                    if (!isS(more)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("unexpected character ");
                        stringBuffer.append(printable(more));
                        stringBuffer.append(" after processing instruction name; expected a white space or '?>'");
                        throw new XMLStreamException(stringBuffer.toString(), getLocation());
                    }
                }
            }
            boolean z = true;
            int i3 = (this.pos - this.posStart) - 1;
            if (i3 != 0) {
                this.piTarget = new String(this.buf, this.posStart, i3);
                if (more != '?') {
                    more = skipS(more);
                }
                boolean equalsIgnoreCase = this.piTarget.equalsIgnoreCase("xml");
                if (!equalsIgnoreCase) {
                    this.posStart = this.pos - 1;
                    int i4 = -1;
                    boolean z2 = false;
                    int i5 = -2;
                    int i6 = -2;
                    while (true) {
                        i4++;
                        if (more == '?') {
                            i5 = i4 + 1;
                        } else if (more == '>') {
                            if (i4 == i5) {
                                break;
                            }
                        } else if (more == 13) {
                            this.columnNumber = 1;
                            i6 = i4 + 1;
                            if (!z2) {
                                this.buf[this.pos - 1] = 10;
                                more = more();
                            } else {
                                more = 10;
                            }
                        } else if (more == 10 && i6 == i4) {
                            if (!z2) {
                                this.posEnd = this.pos - 1;
                                z2 = true;
                            }
                            more = more();
                        }
                        if (z2) {
                            this.buf[this.posEnd] = more;
                            this.posEnd++;
                        }
                        more = more();
                    }
                    if (z2) {
                        this.posEnd--;
                    } else {
                        this.posEnd = this.pos - 2;
                    }
                    z = equalsIgnoreCase;
                } else if (this.posStart + this.bufAbsoluteStart > 2) {
                    throw new XMLStreamException("processing instruction can not have PITarget with reserved name 'xml'", getLocation());
                } else if ("xml".equals(this.piTarget)) {
                    this.posStart = this.pos - 1;
                    parseXmlDecl(more);
                    this.posEnd = this.pos - 2;
                } else {
                    throw new XMLStreamException("XMLDecl must have xml name in lowercase", getLocation());
                }
                this.piData = new String(this.buf, this.posStart, this.posEnd - this.posStart);
                return z;
            }
            throw new XMLStreamException("processing instruction must have PITarget name", getLocation());
        } catch (EOFException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("processing instruction started on line ");
            stringBuffer2.append(i);
            stringBuffer2.append(" and column ");
            stringBuffer2.append(i2);
            stringBuffer2.append(" was not closed");
            throw new XMLStreamException(stringBuffer2.toString(), getLocation(), e);
        }
    }

    /* access modifiers changed from: protected */
    public char requireInput(char c, char[] cArr) throws XMLStreamException {
        int i = 0;
        while (i < cArr.length) {
            if (c == cArr[i]) {
                try {
                    c = more();
                    i++;
                } catch (EOFException e) {
                    throw new XMLStreamException(EOF_MSG, getLocation(), e);
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("expected ");
                stringBuffer.append(printable(cArr[i]));
                stringBuffer.append(" in ");
                stringBuffer.append(new String(cArr));
                stringBuffer.append(" and not ");
                stringBuffer.append(printable(c));
                throw new XMLStreamException(stringBuffer.toString(), getLocation());
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public char requireNextS() throws XMLStreamException {
        try {
            char more = more();
            if (isS(more)) {
                return skipS(more);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("white space is required and not ");
            stringBuffer.append(printable(more));
            throw new XMLStreamException(stringBuffer.toString(), getLocation());
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    /* access modifiers changed from: protected */
    public char skipS(char c) throws XMLStreamException {
        while (isS(c)) {
            try {
                c = more();
            } catch (EOFException e) {
                throw new XMLStreamException(EOF_MSG, getLocation(), e);
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public void parseXmlDecl(char c) throws XMLStreamException {
        try {
            char skipS = skipS(requireInput(skipS(c), VERSION));
            if (skipS == '=') {
                char skipS2 = skipS(more());
                if (skipS2 != '\'') {
                    if (skipS2 != '\"') {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("expected apostrophe (') or quotation mark (\") after version and not ");
                        stringBuffer.append(printable(skipS2));
                        throw new XMLStreamException(stringBuffer.toString(), getLocation());
                    }
                }
                int i = this.pos;
                char more = more();
                while (more != skipS2) {
                    if ((more < 'a' || more > 'z') && ((more < 'A' || more > 'Z') && !((more >= '0' && more <= '9') || more == '_' || more == '.' || more == ':'))) {
                        if (more != '-') {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("<?xml version value expected to be in ([a-zA-Z0-9_.:] | '-') not ");
                            stringBuffer2.append(printable(more));
                            throw new XMLStreamException(stringBuffer2.toString(), getLocation());
                        }
                    }
                    more = more();
                }
                parseXmlDeclWithVersion(i, this.pos - 1);
                return;
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("expected equals sign (=) after version and not ");
            stringBuffer3.append(printable(skipS));
            throw new XMLStreamException(stringBuffer3.toString(), getLocation());
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    /* access modifiers changed from: protected */
    public void parseXmlDeclWithVersion(int i, int i2) throws XMLStreamException {
        char c;
        int i3 = i;
        int i4 = i2;
        int i5 = i4 - i3;
        if (i5 == 3) {
            try {
                if (this.buf[i3] == '1' && this.buf[i3 + 1] == '.' && this.buf[i3 + 2] == '0') {
                    this.xmlVersion = new String(this.buf, i3, i5);
                    char skipS = skipS(more());
                    if (skipS != '?') {
                        skipS = skipS(skipS);
                        if (skipS == ENCODING[0]) {
                            char skipS2 = skipS(requireInput(skipS, ENCODING));
                            if (skipS2 == '=') {
                                char skipS3 = skipS(more());
                                if (skipS3 != '\'') {
                                    if (skipS3 != '\"') {
                                        StringBuffer stringBuffer = new StringBuffer();
                                        stringBuffer.append("expected apostrophe (') or quotation mark (\") after encoding and not ");
                                        stringBuffer.append(printable(skipS3));
                                        throw new XMLStreamException(stringBuffer.toString(), getLocation());
                                    }
                                }
                                int i6 = this.pos;
                                char more = more();
                                char c2 = 'a';
                                if ((more < 'a' || more > 'z') && (more < 'A' || more > 'Z')) {
                                    StringBuffer stringBuffer2 = new StringBuffer();
                                    stringBuffer2.append("<?xml encoding name expected to start with [A-Za-z] not ");
                                    stringBuffer2.append(printable(more));
                                    throw new XMLStreamException(stringBuffer2.toString(), getLocation());
                                }
                                char more2 = more();
                                while (more2 != skipS3) {
                                    if ((more2 < c2 || more2 > 'z') && ((more2 < 'A' || more2 > 'Z') && !((more2 >= '0' && more2 <= '9') || more2 == '.' || more2 == '_'))) {
                                        if (more2 != '-') {
                                            StringBuffer stringBuffer3 = new StringBuffer();
                                            stringBuffer3.append("<?xml encoding value expected to be in ([A-Za-z0-9._] | '-') not ");
                                            stringBuffer3.append(printable(more2));
                                            throw new XMLStreamException(stringBuffer3.toString(), getLocation());
                                        }
                                    }
                                    more2 = more();
                                    c2 = 'a';
                                }
                                this.charEncodingScheme = newString(this.buf, i6, (this.pos - 1) - i6);
                                skipS = skipS(more());
                            } else {
                                StringBuffer stringBuffer4 = new StringBuffer();
                                stringBuffer4.append("expected equals sign (=) after encoding and not ");
                                stringBuffer4.append(printable(skipS2));
                                throw new XMLStreamException(stringBuffer4.toString(), getLocation());
                            }
                        }
                        if (skipS != '?') {
                            char skipS4 = skipS(requireInput(skipS(skipS), STANDALONE));
                            if (skipS4 == '=') {
                                char skipS5 = skipS(more());
                                if (skipS5 != '\'') {
                                    if (skipS5 != '\"') {
                                        StringBuffer stringBuffer5 = new StringBuffer();
                                        stringBuffer5.append("expected apostrophe (') or quotation mark (\") after encoding and not ");
                                        stringBuffer5.append(printable(skipS5));
                                        throw new XMLStreamException(stringBuffer5.toString(), getLocation());
                                    }
                                }
                                char more3 = more();
                                if (more3 == 'y') {
                                    c = requireInput(more3, YES);
                                    this.standalone = true;
                                } else if (more3 == 'n') {
                                    c = requireInput(more3, NO);
                                    this.standalone = false;
                                } else {
                                    StringBuffer stringBuffer6 = new StringBuffer();
                                    stringBuffer6.append("expected 'yes' or 'no' after standalone and not ");
                                    stringBuffer6.append(printable(more3));
                                    throw new XMLStreamException(stringBuffer6.toString(), getLocation());
                                }
                                this.standaloneSet = true;
                                if (c == skipS5) {
                                    skipS = more();
                                } else {
                                    StringBuffer stringBuffer7 = new StringBuffer();
                                    stringBuffer7.append("expected ");
                                    stringBuffer7.append(skipS5);
                                    stringBuffer7.append(" after standalone value not ");
                                    stringBuffer7.append(printable(c));
                                    throw new XMLStreamException(stringBuffer7.toString(), getLocation());
                                }
                            } else {
                                StringBuffer stringBuffer8 = new StringBuffer();
                                stringBuffer8.append("expected equals sign (=) after standalone and not ");
                                stringBuffer8.append(printable(skipS4));
                                throw new XMLStreamException(stringBuffer8.toString(), getLocation());
                            }
                        }
                    }
                    char skipS6 = skipS(skipS);
                    if (skipS6 == '?') {
                        char more4 = more();
                        if (more4 != '>') {
                            StringBuffer stringBuffer9 = new StringBuffer();
                            stringBuffer9.append("expected ?> as last part of <?xml not ");
                            stringBuffer9.append(printable(more4));
                            throw new XMLStreamException(stringBuffer9.toString(), getLocation());
                        }
                        return;
                    }
                    StringBuffer stringBuffer10 = new StringBuffer();
                    stringBuffer10.append("expected ?> as last part of <?xml not ");
                    stringBuffer10.append(printable(skipS6));
                    throw new XMLStreamException(stringBuffer10.toString(), getLocation());
                }
            } catch (EOFException e) {
                throw new XMLStreamException(EOF_MSG, getLocation(), e);
            }
        }
        StringBuffer stringBuffer11 = new StringBuffer();
        stringBuffer11.append("only 1.0 is supported as <?xml version not '");
        stringBuffer11.append(printable(new String(this.buf, i3, i4)));
        stringBuffer11.append("'");
        throw new XMLStreamException(stringBuffer11.toString(), getLocation());
    }

    /* access modifiers changed from: protected */
    public void parseDocdecl() throws XMLStreamException {
        char more;
        this.posStart = this.pos - 3;
        try {
            if (more() == 'O' && more() == 'C' && more() == 'T' && more() == 'Y' && more() == 'P' && more() == 'E') {
                char requireNextS = requireNextS();
                if (!isNameStartChar(requireNextS)) {
                    throwNotNameStart(requireNextS);
                }
                do {
                    more = more();
                } while (isNameChar(more));
                char skipS = skipS(more);
                if (skipS == 'S' || skipS == 'P') {
                    if (skipS != 'S') {
                        if (more() == 'U' && more() == 'B' && more() == 'L' && more() == 'I' && more() == 'C') {
                            char requireNextS2 = requireNextS();
                            if (requireNextS2 != '\"') {
                                if (requireNextS2 != '\'') {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append("Public identifier has to be enclosed in quotes, not ");
                                    stringBuffer.append(printable(skipS));
                                    throw new XMLStreamException(stringBuffer.toString(), getLocation());
                                }
                            }
                            while (true) {
                                skipS = more();
                                if (skipS == requireNextS2) {
                                    break;
                                }
                            }
                        } else {
                            throw new XMLStreamException("expected keyword PUBLIC", getLocation());
                        }
                    } else if (more() != 'Y' || more() != 'S' || more() != 'T' || more() != 'E' || more() != 'M') {
                        throw new XMLStreamException("expected keyword SYSTEM", getLocation());
                    }
                    char requireNextS3 = requireNextS();
                    if (requireNextS3 != '\"') {
                        if (requireNextS3 != '\'') {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("System identifier has to be enclosed in quotes, not ");
                            stringBuffer2.append(printable(skipS));
                            throw new XMLStreamException(stringBuffer2.toString(), getLocation());
                        }
                    }
                    while (more() != requireNextS3) {
                    }
                    skipS = skipS(more());
                }
                if (skipS == '[') {
                    this.posStart = this.pos;
                    int i = 1;
                    while (true) {
                        char more2 = more();
                        if (more2 == '\"' || more2 == '\'') {
                            while (more() != more2) {
                            }
                        } else if (more2 != '>') {
                            if (more2 == '[') {
                                i++;
                            } else if (more2 == ']') {
                                i--;
                            }
                        } else if (i <= 0) {
                            this.posEnd = this.pos - 2;
                            processDTD();
                            return;
                        }
                    }
                } else {
                    int i2 = this.pos;
                    this.posEnd = i2;
                    this.posStart = i2;
                    char skipS2 = skipS(skipS);
                    if (skipS2 != '>') {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Expected closing '>' after internal DTD subset, not '");
                        stringBuffer3.append(printable(skipS2));
                        stringBuffer3.append("'");
                        throw new XMLStreamException(stringBuffer3.toString(), getLocation());
                    }
                }
            } else {
                throw new XMLStreamException("expected <!DOCTYPE", getLocation());
            }
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    /* access modifiers changed from: protected */
    public void processDTD() throws XMLStreamException {
        Class cls;
        Class cls2;
        try {
            DTD parse = new DTDParser((Reader) new StringReader(new String(this.buf, this.posStart, this.posEnd - this.posStart))).parse();
            this.mDtdIntSubset = parse;
            if (class$com$wutka$dtd$DTDEntity == null) {
                cls = class$("com.wutka.dtd.DTDEntity");
                class$com$wutka$dtd$DTDEntity = cls;
            } else {
                cls = class$com$wutka$dtd$DTDEntity;
            }
            Enumeration elements = parse.getItemsByType(cls).elements();
            while (elements.hasMoreElements()) {
                DTDEntity dTDEntity = (DTDEntity) elements.nextElement();
                if (!dTDEntity.isParsed()) {
                    defineEntityReplacementText(dTDEntity.getName(), dTDEntity.getValue());
                }
            }
            DTD dtd = this.mDtdIntSubset;
            if (class$com$wutka$dtd$DTDAttlist == null) {
                cls2 = class$("com.wutka.dtd.DTDAttlist");
                class$com$wutka$dtd$DTDAttlist = cls2;
            } else {
                cls2 = class$com$wutka$dtd$DTDAttlist;
            }
            Enumeration elements2 = dtd.getItemsByType(cls2).elements();
            while (elements2.hasMoreElements()) {
                DTDAttlist dTDAttlist = (DTDAttlist) elements2.nextElement();
                DTDAttribute[] attribute = dTDAttlist.getAttribute();
                for (DTDAttribute defaultValue : attribute) {
                    if (defaultValue.getDefaultValue() != null) {
                        if (this.defaultAttributes == null) {
                            this.defaultAttributes = new HashMap();
                        }
                        this.defaultAttributes.put(dTDAttlist.getName(), dTDAttlist);
                    }
                }
            }
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void parseCDATA() throws XMLStreamException {
        try {
            if (more() == 'C' && more() == 'D' && more() == 'A' && more() == 'T' && more() == 'A' && more() == '[') {
                this.posStart = this.pos;
                int i = this.lineNumber;
                int i2 = this.columnNumber;
                int i3 = -2;
                int i4 = -1;
                int i5 = 0;
                boolean z = false;
                while (true) {
                    i4++;
                    try {
                        char more = more();
                        if (more == ']') {
                            i5++;
                        } else {
                            if (more == '>') {
                                if (i5 >= 2) {
                                    break;
                                }
                            } else if (more == 13) {
                                this.columnNumber = 1;
                                i3 = i4 + 1;
                                if (!z) {
                                    this.buf[this.pos - 1] = 10;
                                    i5 = 0;
                                } else {
                                    i5 = 0;
                                    more = 10;
                                }
                            } else if (more == 10 && i3 == i4) {
                                this.posEnd = this.pos - 1;
                                i5 = 0;
                                z = true;
                            }
                            i5 = 0;
                        }
                        if (z) {
                            this.buf[this.posEnd] = more;
                            this.posEnd++;
                        }
                    } catch (EOFException e) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("CDATA section on line ");
                        stringBuffer.append(i);
                        stringBuffer.append(" and column ");
                        stringBuffer.append(i2);
                        stringBuffer.append(" was not closed");
                        throw new XMLStreamException(stringBuffer.toString(), getLocation(), e);
                    }
                }
                if (z) {
                    this.posEnd -= 2;
                } else {
                    this.posEnd = this.pos - 3;
                }
            } else {
                throw new XMLStreamException("expected <[CDATA[ for CDATA start", getLocation());
            }
        } catch (EOFException e2) {
            throw new XMLStreamException("Unexpected EOF in directive", getLocation(), e2);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fillBuf() throws javax.xml.stream.XMLStreamException, java.io.EOFException {
        /*
            r5 = this;
            java.io.Reader r0 = r5.reader
            if (r0 == 0) goto L_0x00bb
            int r0 = r5.bufEnd
            int r1 = r5.bufSoftLimit
            if (r0 <= r1) goto L_0x0074
            int r0 = r5.bufStart
            r2 = 1
            r3 = 0
            if (r0 <= r1) goto L_0x0012
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            if (r0 != 0) goto L_0x0021
            int r1 = r5.bufStart
            char[] r4 = r5.buf
            int r4 = r4.length
            int r4 = r4 / 2
            if (r1 >= r4) goto L_0x0022
            r2 = r0
            r0 = 1
            goto L_0x0023
        L_0x0021:
            r2 = r0
        L_0x0022:
            r0 = 0
        L_0x0023:
            if (r2 == 0) goto L_0x0030
            char[] r0 = r5.buf
            int r1 = r5.bufStart
            int r2 = r5.bufEnd
            int r2 = r2 - r1
            java.lang.System.arraycopy(r0, r1, r0, r3, r2)
            goto L_0x004e
        L_0x0030:
            if (r0 == 0) goto L_0x006c
            char[] r0 = r5.buf
            int r1 = r0.length
            int r1 = r1 * 2
            char[] r1 = new char[r1]
            int r2 = r5.bufStart
            int r4 = r5.bufEnd
            int r4 = r4 - r2
            java.lang.System.arraycopy(r0, r2, r1, r3, r4)
            r5.buf = r1
            int r0 = r5.bufLoadFactor
            if (r0 <= 0) goto L_0x004e
            int r1 = r1.length
            int r0 = r0 * r1
            int r0 = r0 / 100
            r5.bufSoftLimit = r0
        L_0x004e:
            int r0 = r5.bufEnd
            int r1 = r5.bufStart
            int r0 = r0 - r1
            r5.bufEnd = r0
            int r0 = r5.pos
            int r0 = r0 - r1
            r5.pos = r0
            int r0 = r5.posStart
            int r0 = r0 - r1
            r5.posStart = r0
            int r0 = r5.posEnd
            int r0 = r0 - r1
            r5.posEnd = r0
            int r0 = r5.bufAbsoluteStart
            int r0 = r0 + r1
            r5.bufAbsoluteStart = r0
            r5.bufStart = r3
            goto L_0x0074
        L_0x006c:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "internal error in fillBuffer()"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0074:
            char[] r0 = r5.buf
            int r0 = r0.length
            int r1 = r5.bufEnd
            int r0 = r0 - r1
            r1 = 8192(0x2000, float:1.14794E-41)
            if (r0 <= r1) goto L_0x0080
            r0 = 8192(0x2000, float:1.14794E-41)
        L_0x0080:
            java.io.Reader r1 = r5.reader     // Catch:{ IOException -> 0x00b4 }
            char[] r2 = r5.buf     // Catch:{ IOException -> 0x00b4 }
            int r3 = r5.bufEnd     // Catch:{ IOException -> 0x00b4 }
            int r0 = r1.read(r2, r3, r0)     // Catch:{ IOException -> 0x00b4 }
            if (r0 <= 0) goto L_0x0092
            int r1 = r5.bufEnd
            int r1 = r1 + r0
            r5.bufEnd = r1
            return
        L_0x0092:
            r1 = -1
            if (r0 != r1) goto L_0x009d
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.String r1 = "no more data available"
            r0.<init>(r1)
            throw r0
        L_0x009d:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "error reading input, returned "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>((java.lang.String) r0)
            throw r1
        L_0x00b4:
            r0 = move-exception
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException
            r1.<init>((java.lang.Throwable) r0)
            throw r1
        L_0x00bb:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "reader must be set before parsing is started"
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.fillBuf():void");
    }

    /* access modifiers changed from: protected */
    public char more() throws XMLStreamException, EOFException {
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
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            stringBuffer.append(printable(str.charAt(i)));
        }
        return stringBuffer.toString();
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

    public void setConfigurationContext(ConfigurationContextBase configurationContextBase) {
        this.configurationContext = configurationContextBase;
        Boolean.TRUE.equals(configurationContextBase.getProperty(XMLInputFactory.IS_COALESCING));
        this.reportCdataEvent = Boolean.TRUE.equals(configurationContextBase.getProperty("http://java.sun.com/xml/stream/properties/report-cdata-event"));
    }

    public ConfigurationContextBase getConfigurationContext() {
        return this.configurationContext;
    }

    public Object getProperty(String str) {
        ArrayList arrayList = null;
        if (str.equals("javax.xml.stream.entities")) {
            DTD dtd = this.mDtdIntSubset;
            if (dtd != null) {
                Class cls = class$com$wutka$dtd$DTDEntity;
                if (cls == null) {
                    cls = class$("com.wutka.dtd.DTDEntity");
                    class$com$wutka$dtd$DTDEntity = cls;
                }
                Vector itemsByType = dtd.getItemsByType(cls);
                Enumeration elements = itemsByType.elements();
                arrayList = new ArrayList(itemsByType.size());
                while (elements.hasMoreElements()) {
                    EntityDeclaration createEntityDeclaration = DTDEvent.createEntityDeclaration((DTDEntity) elements.nextElement());
                    if (createEntityDeclaration != null) {
                        arrayList.add(createEntityDeclaration);
                    }
                }
            }
            return arrayList;
        } else if (!str.equals("javax.xml.stream.notations")) {
            return this.configurationContext.getProperty(str);
        } else {
            DTD dtd2 = this.mDtdIntSubset;
            if (dtd2 != null) {
                Class cls2 = class$com$wutka$dtd$DTDNotation;
                if (cls2 == null) {
                    cls2 = class$("com.wutka.dtd.DTDNotation");
                    class$com$wutka$dtd$DTDNotation = cls2;
                }
                Vector itemsByType2 = dtd2.getItemsByType(cls2);
                Enumeration elements2 = itemsByType2.elements();
                arrayList = new ArrayList(itemsByType2.size());
                while (elements2.hasMoreElements()) {
                    NotationDeclaration createNotationDeclaration = DTDEvent.createNotationDeclaration((DTDNotation) elements2.nextElement());
                    if (createNotationDeclaration != null) {
                        arrayList.add(createNotationDeclaration);
                    }
                }
            }
            return arrayList;
        }
    }

    private String throwIllegalState(int i) throws IllegalStateException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Current state (");
        stringBuffer.append(eventTypeDesc(this.eventType));
        stringBuffer.append(") not ");
        stringBuffer.append(eventTypeDesc(i));
        throw new IllegalStateException(stringBuffer.toString());
    }

    private String throwIllegalState(int[] iArr) throws IllegalStateException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(eventTypeDesc(iArr[0]));
        int length = iArr.length - 1;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(", ");
            stringBuffer.append(eventTypeDesc(iArr[i]));
        }
        stringBuffer.append(" or ");
        stringBuffer.append(eventTypeDesc(iArr[length]));
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Current state (");
        stringBuffer2.append(eventTypeDesc(this.eventType));
        stringBuffer2.append(") not ");
        stringBuffer2.append(stringBuffer.toString());
        throw new IllegalStateException(stringBuffer2.toString());
    }

    private void throwNotNameStart(char c) throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("expected name start character and not ");
        stringBuffer.append(printable(c));
        throw new XMLStreamException(stringBuffer.toString(), getLocation());
    }
}
