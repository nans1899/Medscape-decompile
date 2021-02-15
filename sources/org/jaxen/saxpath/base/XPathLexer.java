package org.jaxen.saxpath.base;

import kotlin.jvm.internal.CharCompanionObject;

class XPathLexer {
    private int currentPosition;
    private int endPosition;
    private boolean expectOperator = false;
    private String xpath;

    XPathLexer(String str) {
        setXPath(str);
    }

    private void setXPath(String str) {
        this.xpath = str;
        this.currentPosition = 0;
        this.endPosition = str.length();
    }

    /* access modifiers changed from: package-private */
    public String getXPath() {
        return this.xpath;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jaxen.saxpath.base.Token nextToken() {
        /*
            r6 = this;
        L_0x0000:
            r0 = 0
            r1 = 1
            char r2 = r6.LA(r1)
            r3 = 9
            if (r2 == r3) goto L_0x00c6
            r3 = 10
            if (r2 == r3) goto L_0x00c6
            r3 = 13
            if (r2 == r3) goto L_0x00c6
            r3 = 36
            if (r2 == r3) goto L_0x00c1
            r3 = 64
            if (r2 == r3) goto L_0x00bc
            r3 = 91
            if (r2 == r3) goto L_0x00b7
            r3 = 93
            if (r2 == r3) goto L_0x00b2
            r3 = 124(0x7c, float:1.74E-43)
            if (r2 == r3) goto L_0x00ad
            r3 = 2
            switch(r2) {
                case 32: goto L_0x00c6;
                case 33: goto L_0x00a0;
                case 34: goto L_0x009b;
                default: goto L_0x002a;
            }
        L_0x002a:
            switch(r2) {
                case 39: goto L_0x009b;
                case 40: goto L_0x0096;
                case 41: goto L_0x0091;
                case 42: goto L_0x008c;
                case 43: goto L_0x0087;
                case 44: goto L_0x0082;
                case 45: goto L_0x007d;
                case 46: goto L_0x006c;
                case 47: goto L_0x0066;
                case 48: goto L_0x0060;
                case 49: goto L_0x0060;
                case 50: goto L_0x0060;
                case 51: goto L_0x0060;
                case 52: goto L_0x0060;
                case 53: goto L_0x0060;
                case 54: goto L_0x0060;
                case 55: goto L_0x0060;
                case 56: goto L_0x0060;
                case 57: goto L_0x0060;
                case 58: goto L_0x004c;
                default: goto L_0x002d;
            }
        L_0x002d:
            switch(r2) {
                case 60: goto L_0x0046;
                case 61: goto L_0x0040;
                case 62: goto L_0x0046;
                default: goto L_0x0030;
            }
        L_0x0030:
            char r2 = r6.LA(r1)
            boolean r2 = org.jaxen.saxpath.base.Verifier.isXMLNCNameStartCharacter(r2)
            if (r2 == 0) goto L_0x00ca
            org.jaxen.saxpath.base.Token r0 = r6.identifierOrOperatorName()
            goto L_0x00ca
        L_0x0040:
            org.jaxen.saxpath.base.Token r0 = r6.equals()
            goto L_0x00ca
        L_0x0046:
            org.jaxen.saxpath.base.Token r0 = r6.relationalOperator()
            goto L_0x00ca
        L_0x004c:
            char r0 = r6.LA(r3)
            r2 = 58
            if (r0 != r2) goto L_0x005a
            org.jaxen.saxpath.base.Token r0 = r6.doubleColon()
            goto L_0x00ca
        L_0x005a:
            org.jaxen.saxpath.base.Token r0 = r6.colon()
            goto L_0x00ca
        L_0x0060:
            org.jaxen.saxpath.base.Token r0 = r6.number()
            goto L_0x00ca
        L_0x0066:
            org.jaxen.saxpath.base.Token r0 = r6.slashes()
            goto L_0x00ca
        L_0x006c:
            char r0 = r6.LA(r3)
            switch(r0) {
                case 48: goto L_0x0078;
                case 49: goto L_0x0078;
                case 50: goto L_0x0078;
                case 51: goto L_0x0078;
                case 52: goto L_0x0078;
                case 53: goto L_0x0078;
                case 54: goto L_0x0078;
                case 55: goto L_0x0078;
                case 56: goto L_0x0078;
                case 57: goto L_0x0078;
                default: goto L_0x0073;
            }
        L_0x0073:
            org.jaxen.saxpath.base.Token r0 = r6.dots()
            goto L_0x00ca
        L_0x0078:
            org.jaxen.saxpath.base.Token r0 = r6.number()
            goto L_0x00ca
        L_0x007d:
            org.jaxen.saxpath.base.Token r0 = r6.minus()
            goto L_0x00ca
        L_0x0082:
            org.jaxen.saxpath.base.Token r0 = r6.comma()
            goto L_0x00ca
        L_0x0087:
            org.jaxen.saxpath.base.Token r0 = r6.plus()
            goto L_0x00ca
        L_0x008c:
            org.jaxen.saxpath.base.Token r0 = r6.star()
            goto L_0x00ca
        L_0x0091:
            org.jaxen.saxpath.base.Token r0 = r6.rightParen()
            goto L_0x00ca
        L_0x0096:
            org.jaxen.saxpath.base.Token r0 = r6.leftParen()
            goto L_0x00ca
        L_0x009b:
            org.jaxen.saxpath.base.Token r0 = r6.literal()
            goto L_0x00ca
        L_0x00a0:
            char r2 = r6.LA(r3)
            r3 = 61
            if (r2 != r3) goto L_0x00ca
            org.jaxen.saxpath.base.Token r0 = r6.notEquals()
            goto L_0x00ca
        L_0x00ad:
            org.jaxen.saxpath.base.Token r0 = r6.pipe()
            goto L_0x00ca
        L_0x00b2:
            org.jaxen.saxpath.base.Token r0 = r6.rightBracket()
            goto L_0x00ca
        L_0x00b7:
            org.jaxen.saxpath.base.Token r0 = r6.leftBracket()
            goto L_0x00ca
        L_0x00bc:
            org.jaxen.saxpath.base.Token r0 = r6.at()
            goto L_0x00ca
        L_0x00c1:
            org.jaxen.saxpath.base.Token r0 = r6.dollar()
            goto L_0x00ca
        L_0x00c6:
            org.jaxen.saxpath.base.Token r0 = r6.whitespace()
        L_0x00ca:
            if (r0 != 0) goto L_0x00ef
            boolean r0 = r6.hasMoreChars()
            if (r0 != 0) goto L_0x00e1
            org.jaxen.saxpath.base.Token r0 = new org.jaxen.saxpath.base.Token
            r2 = -1
            java.lang.String r3 = r6.getXPath()
            int r4 = r6.currentPosition
            int r5 = r6.endPosition
            r0.<init>(r2, r3, r4, r5)
            goto L_0x00ef
        L_0x00e1:
            org.jaxen.saxpath.base.Token r0 = new org.jaxen.saxpath.base.Token
            r2 = -3
            java.lang.String r3 = r6.getXPath()
            int r4 = r6.currentPosition
            int r5 = r6.endPosition
            r0.<init>(r2, r3, r4, r5)
        L_0x00ef:
            int r2 = r0.getTokenType()
            r3 = -2
            if (r2 == r3) goto L_0x0000
            int r2 = r0.getTokenType()
            switch(r2) {
                case 1: goto L_0x0100;
                case 2: goto L_0x0100;
                case 3: goto L_0x0100;
                case 4: goto L_0x0100;
                case 5: goto L_0x0100;
                case 6: goto L_0x0100;
                case 7: goto L_0x0100;
                case 8: goto L_0x0100;
                case 9: goto L_0x00fd;
                case 10: goto L_0x0100;
                case 11: goto L_0x0100;
                case 12: goto L_0x0100;
                case 13: goto L_0x0100;
                case 14: goto L_0x00fd;
                case 15: goto L_0x00fd;
                case 16: goto L_0x00fd;
                case 17: goto L_0x0100;
                case 18: goto L_0x0100;
                case 19: goto L_0x0100;
                case 20: goto L_0x0100;
                case 21: goto L_0x0100;
                case 22: goto L_0x00fd;
                case 23: goto L_0x0100;
                case 24: goto L_0x00fd;
                case 25: goto L_0x0100;
                case 26: goto L_0x00fd;
                case 27: goto L_0x0100;
                case 28: goto L_0x0100;
                case 29: goto L_0x00fd;
                case 30: goto L_0x0100;
                case 31: goto L_0x0100;
                default: goto L_0x00fd;
            }
        L_0x00fd:
            r6.expectOperator = r1
            goto L_0x0103
        L_0x0100:
            r1 = 0
            r6.expectOperator = r1
        L_0x0103:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaxen.saxpath.base.XPathLexer.nextToken():org.jaxen.saxpath.base.Token");
    }

    private Token identifierOrOperatorName() {
        if (this.expectOperator) {
            return operatorName();
        }
        return identifier();
    }

    private Token identifier() {
        int i = this.currentPosition;
        while (hasMoreChars() && Verifier.isXMLNCNameCharacter(LA(1))) {
            consume();
        }
        return new Token(16, getXPath(), i, this.currentPosition);
    }

    private Token operatorName() {
        char LA = LA(1);
        if (LA == 'a') {
            return and();
        }
        if (LA == 'd') {
            return div();
        }
        if (LA == 'm') {
            return mod();
        }
        if (LA != 'o') {
            return null;
        }
        return or();
    }

    private Token mod() {
        if (LA(1) != 'm' || LA(2) != 'o' || LA(3) != 'd') {
            return null;
        }
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(10, xPath, i, i + 3);
        consume();
        consume();
        consume();
        return token;
    }

    private Token div() {
        if (LA(1) != 'd' || LA(2) != 'i' || LA(3) != 'v') {
            return null;
        }
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(11, xPath, i, i + 3);
        consume();
        consume();
        consume();
        return token;
    }

    private Token and() {
        if (LA(1) != 'a' || LA(2) != 'n' || LA(3) != 'd') {
            return null;
        }
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(27, xPath, i, i + 3);
        consume();
        consume();
        consume();
        return token;
    }

    private Token or() {
        if (LA(1) != 'o' || LA(2) != 'r') {
            return null;
        }
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(28, xPath, i, i + 2);
        consume();
        consume();
        return token;
    }

    private Token number() {
        int i = this.currentPosition;
        boolean z = true;
        while (true) {
            switch (LA(1)) {
                case '.':
                    if (!z) {
                        break;
                    } else {
                        z = false;
                        consume();
                        continue;
                    }
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    consume();
                    continue;
            }
        }
        return new Token(29, getXPath(), i, this.currentPosition);
    }

    private Token whitespace() {
        consume();
        while (hasMoreChars() && ((r0 = LA(1)) == 9 || r0 == 10 || r0 == 13 || r0 == ' ')) {
            consume();
        }
        return new Token(-2, getXPath(), 0, 0);
    }

    private Token comma() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(30, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token equals() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(1, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token minus() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(8, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token plus() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(7, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token dollar() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(25, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token pipe() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(18, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token at() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(17, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token colon() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(19, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token doubleColon() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(20, xPath, i, i + 2);
        consume();
        consume();
        return token;
    }

    private Token notEquals() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(2, xPath, i, i + 2);
        consume();
        consume();
        return token;
    }

    private Token relationalOperator() {
        Token token;
        Token token2;
        char LA = LA(1);
        if (LA == '<') {
            if (LA(2) == '=') {
                String xPath = getXPath();
                int i = this.currentPosition;
                token = new Token(4, xPath, i, i + 2);
                consume();
            } else {
                String xPath2 = getXPath();
                int i2 = this.currentPosition;
                token = new Token(3, xPath2, i2, i2 + 1);
            }
            consume();
            return token;
        } else if (LA != '>') {
            return null;
        } else {
            if (LA(2) == '=') {
                String xPath3 = getXPath();
                int i3 = this.currentPosition;
                token2 = new Token(6, xPath3, i3, i3 + 2);
                consume();
            } else {
                String xPath4 = getXPath();
                int i4 = this.currentPosition;
                token2 = new Token(5, xPath4, i4, i4 + 1);
            }
            consume();
            return token2;
        }
    }

    private Token star() {
        int i = this.expectOperator ? 31 : 9;
        String xPath = getXPath();
        int i2 = this.currentPosition;
        Token token = new Token(i, xPath, i2, i2 + 1);
        consume();
        return token;
    }

    private Token literal() {
        char LA = LA(1);
        consume();
        int i = this.currentPosition;
        Token token = null;
        while (token == null && hasMoreChars()) {
            if (LA(1) == LA) {
                token = new Token(26, getXPath(), i, this.currentPosition);
            }
            consume();
        }
        return token;
    }

    private Token dots() {
        if (LA(2) != '.') {
            String xPath = getXPath();
            int i = this.currentPosition;
            Token token = new Token(14, xPath, i, i + 1);
            consume();
            return token;
        }
        String xPath2 = getXPath();
        int i2 = this.currentPosition;
        Token token2 = new Token(15, xPath2, i2, i2 + 2);
        consume();
        consume();
        return token2;
    }

    private Token leftBracket() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(21, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token rightBracket() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(22, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token leftParen() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(23, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token rightParen() {
        String xPath = getXPath();
        int i = this.currentPosition;
        Token token = new Token(24, xPath, i, i + 1);
        consume();
        return token;
    }

    private Token slashes() {
        if (LA(2) != '/') {
            String xPath = getXPath();
            int i = this.currentPosition;
            Token token = new Token(12, xPath, i, i + 1);
            consume();
            return token;
        }
        String xPath2 = getXPath();
        int i2 = this.currentPosition;
        Token token2 = new Token(13, xPath2, i2, i2 + 2);
        consume();
        consume();
        return token2;
    }

    private char LA(int i) {
        int i2 = i - 1;
        if (this.currentPosition + i2 >= this.endPosition) {
            return CharCompanionObject.MAX_VALUE;
        }
        return getXPath().charAt(this.currentPosition + i2);
    }

    private void consume() {
        this.currentPosition++;
    }

    private boolean hasMoreChars() {
        return this.currentPosition < this.endPosition;
    }
}
