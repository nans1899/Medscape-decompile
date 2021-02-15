package org.xmlpull.mxp1;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class MXParserNonValidating extends MXParserCachingStrings {
    private boolean processDocDecl;

    /* access modifiers changed from: protected */
    public void processAttlistDecl(char c) throws XmlPullParserException, IOException {
    }

    /* access modifiers changed from: protected */
    public void processEntityDecl(char c) throws XmlPullParserException, IOException {
    }

    /* access modifiers changed from: protected */
    public char processExternalId(char c) throws XmlPullParserException, IOException {
        return c;
    }

    /* access modifiers changed from: protected */
    public void processNotationDecl(char c) throws XmlPullParserException, IOException {
    }

    /* access modifiers changed from: protected */
    public void processPEReference() throws XmlPullParserException, IOException {
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if (!"http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            super.setFeature(str, z);
        } else if (this.eventType == 0) {
            this.processDocDecl = z;
        } else {
            throw new XmlPullParserException("process DOCDECL feature can only be changed before parsing", this, (Throwable) null);
        }
    }

    public boolean getFeature(String str) {
        if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            return this.processDocDecl;
        }
        return super.getFeature(str);
    }

    /* access modifiers changed from: protected */
    public char more() throws IOException, XmlPullParserException {
        return super.more();
    }

    /* access modifiers changed from: protected */
    public char[] lookuEntityReplacement(int i) throws XmlPullParserException, IOException {
        if (!this.allStringsInterned) {
            int fastHash = MXParser.fastHash(this.buf, this.posStart, this.posEnd - this.posStart);
            for (int i2 = this.entityEnd - 1; i2 >= 0; i2--) {
                if (fastHash == this.entityNameHash[i2] && i == this.entityNameBuf[i2].length) {
                    char[] cArr = this.entityNameBuf[i2];
                    int i3 = 0;
                    while (i3 < i) {
                        if (this.buf[this.posStart + i3] == cArr[i3]) {
                            i3++;
                        }
                    }
                    if (this.tokenize) {
                        this.text = this.entityReplacement[i2];
                    }
                    return this.entityReplacementBuf[i2];
                }
            }
            return null;
        }
        this.entityRefName = newString(this.buf, this.posStart, this.posEnd - this.posStart);
        for (int i4 = this.entityEnd - 1; i4 >= 0; i4--) {
            if (this.entityRefName == this.entityName[i4]) {
                if (this.tokenize) {
                    this.text = this.entityReplacement[i4];
                }
                return this.entityReplacementBuf[i4];
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void parseDocdecl() throws XmlPullParserException, IOException {
        boolean z = this.tokenize;
        try {
            if (more() != 'O') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, (Throwable) null);
            } else if (more() != 'C') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, (Throwable) null);
            } else if (more() != 'T') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, (Throwable) null);
            } else if (more() != 'Y') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, (Throwable) null);
            } else if (more() != 'P') {
                throw new XmlPullParserException("expected <!DOCTYPE", this, (Throwable) null);
            } else if (more() == 'E') {
                this.posStart = this.pos;
                char requireNextS = requireNextS();
                int i = this.pos;
                char readName = readName(requireNextS);
                int i2 = this.pos;
                char skipS = skipS(readName);
                if (skipS == 'S' || skipS == 'P') {
                    skipS = skipS(processExternalId(skipS));
                }
                if (skipS == '[') {
                    processInternalSubset();
                }
                char skipS2 = skipS(skipS);
                if (skipS2 == '>') {
                    this.posEnd = this.pos - 1;
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("expected > to finish <[DOCTYPE but got ");
                stringBuffer.append(printable(skipS2));
                throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
            } else {
                throw new XmlPullParserException("expected <!DOCTYPE", this, (Throwable) null);
            }
        } finally {
            this.tokenize = z;
        }
    }

    /* access modifiers changed from: protected */
    public void processInternalSubset() throws XmlPullParserException, IOException {
        while (true) {
            char more = more();
            if (more != ']') {
                if (more == '%') {
                    processPEReference();
                } else if (isS(more)) {
                    skipS(more);
                } else {
                    processMarkupDecl(more);
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processMarkupDecl(char c) throws XmlPullParserException, IOException {
        if (c == '<') {
            char more = more();
            if (more == '?') {
                parsePI();
            } else if (more != '!') {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("expected markupdecl in DTD not ");
                stringBuffer.append(printable(more));
                throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
            } else if (more() == '-') {
                parseComment();
            } else {
                char more2 = more();
                if (more2 == 'A') {
                    processAttlistDecl(more2);
                } else if (more2 == 'E') {
                    char more3 = more();
                    if (more3 == 'L') {
                        processElementDecl(more3);
                    } else if (more3 == 'N') {
                        processEntityDecl(more3);
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("expected ELEMENT or ENTITY after <! in DTD not ");
                        stringBuffer2.append(printable(more3));
                        throw new XmlPullParserException(stringBuffer2.toString(), this, (Throwable) null);
                    }
                } else if (more2 == 'N') {
                    processNotationDecl(more2);
                } else {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("expected markupdecl after <! in DTD not ");
                    stringBuffer3.append(printable(more2));
                    throw new XmlPullParserException(stringBuffer3.toString(), this, (Throwable) null);
                }
            }
        } else {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("expected < for markupdecl in DTD not ");
            stringBuffer4.append(printable(c));
            throw new XmlPullParserException(stringBuffer4.toString(), this, (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    public void processElementDecl(char c) throws XmlPullParserException, IOException {
        readName(requireNextS());
        requireNextS();
    }

    /* access modifiers changed from: protected */
    public char readName(char c) throws XmlPullParserException, IOException {
        if (!isNameStartChar(c)) {
            while (isNameChar(c)) {
                c = more();
            }
            return c;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("XML name must start with name start character not ");
        stringBuffer.append(printable(c));
        throw new XmlPullParserException(stringBuffer.toString(), this, (Throwable) null);
    }
}
