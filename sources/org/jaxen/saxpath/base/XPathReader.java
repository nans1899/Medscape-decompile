package org.jaxen.saxpath.base;

import java.util.ArrayList;
import org.jaxen.saxpath.Axis;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathHandler;
import org.jaxen.saxpath.XPathSyntaxException;
import org.jaxen.saxpath.helpers.DefaultXPathHandler;

public class XPathReader implements org.jaxen.saxpath.XPathReader {
    private static XPathHandler defaultHandler = new DefaultXPathHandler();
    private XPathHandler handler;
    private XPathLexer lexer;
    private ArrayList tokens;

    public XPathReader() {
        setXPathHandler(defaultHandler);
    }

    public void setXPathHandler(XPathHandler xPathHandler) {
        this.handler = xPathHandler;
    }

    public XPathHandler getXPathHandler() {
        return this.handler;
    }

    public void parse(String str) throws SAXPathException {
        setUpParse(str);
        getXPathHandler().startXPath();
        expr();
        getXPathHandler().endXPath();
        if (LA(1) == -1) {
            this.lexer = null;
            this.tokens = null;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unexpected '");
        stringBuffer.append(LT(1).getTokenText());
        stringBuffer.append("'");
        throw createSyntaxException(stringBuffer.toString());
    }

    /* access modifiers changed from: package-private */
    public void setUpParse(String str) {
        this.tokens = new ArrayList();
        this.lexer = new XPathLexer(str);
    }

    private void pathExpr() throws SAXPathException {
        getXPathHandler().startPathExpr();
        int LA = LA(1);
        if (LA != 9) {
            if (LA != 23) {
                if (LA != 29) {
                    if (LA != 25) {
                        if (LA != 26) {
                            switch (LA) {
                                case 12:
                                case 13:
                                    locationPath(true);
                                    break;
                                case 14:
                                case 15:
                                case 17:
                                    break;
                                case 16:
                                    if ((LA(2) == 23 && !isNodeTypeName(LT(1))) || (LA(2) == 19 && LA(4) == 23)) {
                                        filterExpr();
                                        if (LA(1) == 12 || LA(1) == 13) {
                                            locationPath(false);
                                            break;
                                        }
                                    } else {
                                        locationPath(false);
                                        break;
                                    }
                                    break;
                                default:
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append("Unexpected '");
                                    stringBuffer.append(LT(1).getTokenText());
                                    stringBuffer.append("'");
                                    throw createSyntaxException(stringBuffer.toString());
                            }
                        }
                    }
                }
                filterExpr();
                if (LA(1) == 12 || LA(1) == 13) {
                    throw createSyntaxException("Node-set expected");
                }
                getXPathHandler().endPathExpr();
            }
            filterExpr();
            if (LA(1) == 12 || LA(1) == 13) {
                locationPath(false);
            }
            getXPathHandler().endPathExpr();
        }
        locationPath(false);
        getXPathHandler().endPathExpr();
    }

    private void literal() throws SAXPathException {
        getXPathHandler().literal(match(26).getTokenText());
    }

    private void functionCall() throws SAXPathException {
        String str;
        if (LA(2) == 19) {
            str = match(16).getTokenText();
            match(19);
        } else {
            str = "";
        }
        getXPathHandler().startFunction(str, match(16).getTokenText());
        match(23);
        arguments();
        match(24);
        getXPathHandler().endFunction();
    }

    private void arguments() throws SAXPathException {
        while (LA(1) != 24) {
            expr();
            if (LA(1) == 30) {
                match(30);
            } else {
                return;
            }
        }
    }

    private void filterExpr() throws SAXPathException {
        getXPathHandler().startFilterExpr();
        int LA = LA(1);
        if (LA == 16) {
            functionCall();
        } else if (LA == 23) {
            match(23);
            expr();
            match(24);
        } else if (LA == 29) {
            getXPathHandler().number(Double.parseDouble(match(29).getTokenText()));
        } else if (LA == 25) {
            variableReference();
        } else if (LA == 26) {
            literal();
        }
        predicates();
        getXPathHandler().endFilterExpr();
    }

    private void variableReference() throws SAXPathException {
        String str;
        match(25);
        if (LA(2) == 19) {
            str = match(16).getTokenText();
            match(19);
        } else {
            str = "";
        }
        getXPathHandler().variableReference(str, match(16).getTokenText());
    }

    /* access modifiers changed from: package-private */
    public void locationPath(boolean z) throws SAXPathException {
        int LA = LA(1);
        if (LA != 9) {
            switch (LA) {
                case 12:
                case 13:
                    if (z) {
                        absoluteLocationPath();
                        return;
                    } else {
                        relativeLocationPath();
                        return;
                    }
                case 14:
                case 15:
                case 16:
                case 17:
                    break;
                default:
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unexpected '");
                    stringBuffer.append(LT(1).getTokenText());
                    stringBuffer.append("'");
                    throw createSyntaxException(stringBuffer.toString());
            }
        }
        relativeLocationPath();
    }

    private void absoluteLocationPath() throws SAXPathException {
        getXPathHandler().startAbsoluteLocationPath();
        int LA = LA(1);
        if (LA == 12) {
            match(12);
            int LA2 = LA(1);
            if (LA2 != 9) {
                switch (LA2) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        break;
                }
            }
            steps();
        } else if (LA == 13) {
            getXPathHandler().startAllNodeStep(12);
            getXPathHandler().endAllNodeStep();
            match(13);
            int LA3 = LA(1);
            if (LA3 != 9) {
                switch (LA3) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        break;
                    default:
                        throw createSyntaxException("Location path cannot end with //");
                }
            }
            steps();
        }
        getXPathHandler().endAbsoluteLocationPath();
    }

    private void relativeLocationPath() throws SAXPathException {
        getXPathHandler().startRelativeLocationPath();
        int LA = LA(1);
        if (LA == 12) {
            match(12);
        } else if (LA == 13) {
            getXPathHandler().startAllNodeStep(12);
            getXPathHandler().endAllNodeStep();
            match(13);
        }
        steps();
        getXPathHandler().endRelativeLocationPath();
    }

    private void steps() throws SAXPathException {
        int LA = LA(1);
        if (LA != -1) {
            if (LA != 9) {
                switch (LA) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        break;
                    default:
                        throw createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
                }
            }
            step();
            while (true) {
                if (LA(1) == 12 || LA(1) == 13) {
                    int LA2 = LA(1);
                    if (LA2 == 12) {
                        match(12);
                    } else if (LA2 == 13) {
                        getXPathHandler().startAllNodeStep(12);
                        getXPathHandler().endAllNodeStep();
                        match(13);
                    }
                    int LA3 = LA(1);
                    if (LA3 != 9) {
                        switch (LA3) {
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                                break;
                            default:
                                throw createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
                        }
                    }
                    step();
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void step() throws SAXPathException {
        int i = 1;
        int LA = LA(1);
        if (LA != 9) {
            switch (LA) {
                case 14:
                case 15:
                    abbrStep();
                    return;
                case 16:
                    if (LA(2) == 20) {
                        i = axisSpecifier();
                        break;
                    }
                    break;
                case 17:
                    i = axisSpecifier();
                    break;
                default:
                    i = 0;
                    break;
            }
        }
        nodeTest(i);
    }

    private int axisSpecifier() throws SAXPathException {
        int LA = LA(1);
        if (LA == 16) {
            Token LT = LT(1);
            int lookup = Axis.lookup(LT.getTokenText());
            if (lookup == 0) {
                throwInvalidAxis(LT.getTokenText());
            }
            match(16);
            match(20);
            return lookup;
        } else if (LA != 17) {
            return 0;
        } else {
            match(17);
            return 9;
        }
    }

    private void nodeTest(int i) throws SAXPathException {
        int LA = LA(1);
        if (LA == 9) {
            nameTest(i);
        } else if (LA != 16) {
            throw createSyntaxException("Expected <QName> or *");
        } else if (LA(2) != 23) {
            nameTest(i);
        } else {
            nodeTypeTest(i);
        }
    }

    private void nodeTypeTest(int i) throws SAXPathException {
        String tokenText = match(16).getTokenText();
        match(23);
        if ("processing-instruction".equals(tokenText)) {
            String tokenText2 = LA(1) == 26 ? match(26).getTokenText() : "";
            match(24);
            getXPathHandler().startProcessingInstructionNodeStep(i, tokenText2);
            predicates();
            getXPathHandler().endProcessingInstructionNodeStep();
        } else if ("node".equals(tokenText)) {
            match(24);
            getXPathHandler().startAllNodeStep(i);
            predicates();
            getXPathHandler().endAllNodeStep();
        } else if ("text".equals(tokenText)) {
            match(24);
            getXPathHandler().startTextNodeStep(i);
            predicates();
            getXPathHandler().endTextNodeStep();
        } else if ("comment".equals(tokenText)) {
            match(24);
            getXPathHandler().startCommentNodeStep(i);
            predicates();
            getXPathHandler().endCommentNodeStep();
        } else {
            throw createSyntaxException("Expected node-type");
        }
    }

    private void nameTest(int i) throws SAXPathException {
        String str;
        String str2 = null;
        if (LA(2) == 19 && LA(1) == 16) {
            str = match(16).getTokenText();
            match(19);
        } else {
            str = null;
        }
        int LA = LA(1);
        if (LA == 9) {
            match(9);
            str2 = "*";
        } else if (LA == 16) {
            str2 = match(16).getTokenText();
        }
        if (str == null) {
            str = "";
        }
        getXPathHandler().startNameStep(i, str, str2);
        predicates();
        getXPathHandler().endNameStep();
    }

    private void abbrStep() throws SAXPathException {
        int LA = LA(1);
        if (LA == 14) {
            match(14);
            getXPathHandler().startAllNodeStep(11);
            predicates();
            getXPathHandler().endAllNodeStep();
        } else if (LA == 15) {
            match(15);
            getXPathHandler().startAllNodeStep(3);
            predicates();
            getXPathHandler().endAllNodeStep();
        }
    }

    private void predicates() throws SAXPathException {
        while (LA(1) == 21) {
            predicate();
        }
    }

    /* access modifiers changed from: package-private */
    public void predicate() throws SAXPathException {
        getXPathHandler().startPredicate();
        match(21);
        predicateExpr();
        match(22);
        getXPathHandler().endPredicate();
    }

    private void predicateExpr() throws SAXPathException {
        expr();
    }

    private void expr() throws SAXPathException {
        orExpr();
    }

    private void orExpr() throws SAXPathException {
        getXPathHandler().startOrExpr();
        andExpr();
        boolean z = true;
        if (LA(1) != 28) {
            z = false;
        } else {
            match(28);
            orExpr();
        }
        getXPathHandler().endOrExpr(z);
    }

    private void andExpr() throws SAXPathException {
        getXPathHandler().startAndExpr();
        equalityExpr();
        boolean z = true;
        if (LA(1) != 27) {
            z = false;
        } else {
            match(27);
            andExpr();
        }
        getXPathHandler().endAndExpr(z);
    }

    private void equalityExpr() throws SAXPathException {
        relationalExpr();
        int LA = LA(1);
        while (true) {
            if (LA == 1 || LA == 2) {
                if (LA == 1) {
                    match(1);
                    getXPathHandler().startEqualityExpr();
                    relationalExpr();
                    getXPathHandler().endEqualityExpr(1);
                } else if (LA == 2) {
                    match(2);
                    getXPathHandler().startEqualityExpr();
                    relationalExpr();
                    getXPathHandler().endEqualityExpr(2);
                }
                LA = LA(1);
            } else {
                return;
            }
        }
    }

    private void relationalExpr() throws SAXPathException {
        additiveExpr();
        int LA = LA(1);
        while (true) {
            if (LA == 3 || LA == 5 || LA == 4 || LA == 6) {
                if (LA == 3) {
                    match(3);
                    getXPathHandler().startRelationalExpr();
                    additiveExpr();
                    getXPathHandler().endRelationalExpr(3);
                } else if (LA == 4) {
                    match(4);
                    getXPathHandler().startRelationalExpr();
                    additiveExpr();
                    getXPathHandler().endRelationalExpr(4);
                } else if (LA == 5) {
                    match(5);
                    getXPathHandler().startRelationalExpr();
                    additiveExpr();
                    getXPathHandler().endRelationalExpr(5);
                } else if (LA == 6) {
                    match(6);
                    getXPathHandler().startRelationalExpr();
                    additiveExpr();
                    getXPathHandler().endRelationalExpr(6);
                }
                LA = LA(1);
            } else {
                return;
            }
        }
    }

    private void additiveExpr() throws SAXPathException {
        multiplicativeExpr();
        int LA = LA(1);
        while (true) {
            if (LA == 7 || LA == 8) {
                if (LA == 7) {
                    match(7);
                    getXPathHandler().startAdditiveExpr();
                    multiplicativeExpr();
                    getXPathHandler().endAdditiveExpr(7);
                } else if (LA == 8) {
                    match(8);
                    getXPathHandler().startAdditiveExpr();
                    multiplicativeExpr();
                    getXPathHandler().endAdditiveExpr(8);
                }
                LA = LA(1);
            } else {
                return;
            }
        }
    }

    private void multiplicativeExpr() throws SAXPathException {
        unaryExpr();
        int LA = LA(1);
        while (true) {
            if (LA == 31 || LA == 11 || LA == 10) {
                if (LA != 31) {
                    switch (LA) {
                        case 9:
                            break;
                        case 10:
                            match(10);
                            getXPathHandler().startMultiplicativeExpr();
                            unaryExpr();
                            getXPathHandler().endMultiplicativeExpr(10);
                            continue;
                        case 11:
                            match(11);
                            getXPathHandler().startMultiplicativeExpr();
                            unaryExpr();
                            getXPathHandler().endMultiplicativeExpr(11);
                            continue;
                    }
                }
                match(31);
                getXPathHandler().startMultiplicativeExpr();
                unaryExpr();
                getXPathHandler().endMultiplicativeExpr(9);
                LA = LA(1);
            } else {
                return;
            }
        }
    }

    private void unaryExpr() throws SAXPathException {
        if (LA(1) != 8) {
            unionExpr();
            return;
        }
        getXPathHandler().startUnaryExpr();
        match(8);
        unaryExpr();
        getXPathHandler().endUnaryExpr(12);
    }

    private void unionExpr() throws SAXPathException {
        getXPathHandler().startUnionExpr();
        pathExpr();
        boolean z = true;
        if (LA(1) != 18) {
            z = false;
        } else {
            match(18);
            expr();
        }
        getXPathHandler().endUnionExpr(z);
    }

    private Token match(int i) throws XPathSyntaxException {
        LT(1);
        Token token = (Token) this.tokens.get(0);
        if (token.getTokenType() == i) {
            this.tokens.remove(0);
            return token;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Expected: ");
        stringBuffer.append(TokenTypes.getTokenText(i));
        throw createSyntaxException(stringBuffer.toString());
    }

    private int LA(int i) {
        return LT(i).getTokenType();
    }

    private Token LT(int i) {
        int i2 = i - 1;
        if (this.tokens.size() <= i2) {
            for (int i3 = 0; i3 < i; i3++) {
                this.tokens.add(this.lexer.nextToken());
            }
        }
        return (Token) this.tokens.get(i2);
    }

    private boolean isNodeTypeName(Token token) {
        String tokenText = token.getTokenText();
        return "node".equals(tokenText) || "comment".equals(tokenText) || "text".equals(tokenText) || "processing-instruction".equals(tokenText);
    }

    private XPathSyntaxException createSyntaxException(String str) {
        return new XPathSyntaxException(this.lexer.getXPath(), LT(1).getTokenBegin(), str);
    }

    private void throwInvalidAxis(String str) throws SAXPathException {
        String xPath = this.lexer.getXPath();
        int tokenBegin = LT(1).getTokenBegin();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Expected valid axis name instead of [");
        stringBuffer.append(str);
        stringBuffer.append("]");
        throw new XPathSyntaxException(xPath, tokenBegin, stringBuffer.toString());
    }
}
