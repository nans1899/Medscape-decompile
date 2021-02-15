package com.wutka.dtd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import net.bytebuddy.pool.TypePool;
import org.jsoup.nodes.DocumentType;

public class DTDParser implements EntityExpansion {
    protected Object defaultLocation;
    protected DTD dtd = new DTD();
    protected Scanner scanner;

    public DTDParser(Reader reader) {
        this.scanner = new Scanner(reader, false, this);
    }

    public DTDParser(Reader reader, boolean z) {
        this.scanner = new Scanner(reader, z, this);
    }

    public DTDParser(File file) throws IOException {
        this.defaultLocation = file.getParentFile();
        this.scanner = new Scanner(new BufferedReader(new FileReader(file)), false, this);
    }

    public DTDParser(File file, boolean z) throws IOException {
        this.defaultLocation = file.getParentFile();
        this.scanner = new Scanner(new BufferedReader(new FileReader(file)), z, this);
    }

    public DTDParser(URL url) throws IOException {
        String file = url.getFile();
        this.defaultLocation = new URL(url.getProtocol(), url.getHost(), url.getPort(), file.substring(0, file.lastIndexOf(47) + 1));
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())), false, this);
    }

    public DTDParser(URL url, boolean z) throws IOException {
        String file = url.getFile();
        this.defaultLocation = new URL(url.getProtocol(), url.getHost(), url.getPort(), file.substring(0, file.lastIndexOf(47) + 1));
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())), z, this);
    }

    public DTD parse() throws IOException {
        return parse(false);
    }

    public DTD parse(boolean z) throws IOException {
        while (this.scanner.peek().type != Scanner.EOF) {
            parseTopLevelElement();
        }
        if (z) {
            Hashtable hashtable = new Hashtable();
            Enumeration elements = this.dtd.elements.elements();
            while (elements.hasMoreElements()) {
                DTDElement dTDElement = (DTDElement) elements.nextElement();
                hashtable.put(dTDElement.name, dTDElement);
            }
            Enumeration elements2 = this.dtd.elements.elements();
            while (elements2.hasMoreElements()) {
                DTDElement dTDElement2 = (DTDElement) elements2.nextElement();
                if (dTDElement2.content instanceof DTDContainer) {
                    Enumeration elements3 = ((DTDContainer) dTDElement2.content).getItemsVec().elements();
                    while (elements3.hasMoreElements()) {
                        removeElements(hashtable, this.dtd, (DTDItem) elements3.nextElement());
                    }
                }
            }
            if (hashtable.size() == 1) {
                Enumeration elements4 = hashtable.elements();
                this.dtd.rootElement = (DTDElement) elements4.nextElement();
            } else {
                this.dtd.rootElement = null;
            }
        } else {
            this.dtd.rootElement = null;
        }
        return this.dtd;
    }

    /* access modifiers changed from: protected */
    public void removeElements(Hashtable hashtable, DTD dtd2, DTDItem dTDItem) {
        if (dTDItem instanceof DTDName) {
            hashtable.remove(((DTDName) dTDItem).value);
        } else if (dTDItem instanceof DTDContainer) {
            Enumeration elements = ((DTDContainer) dTDItem).getItemsVec().elements();
            while (elements.hasMoreElements()) {
                removeElements(hashtable, dtd2, (DTDItem) elements.nextElement());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseTopLevelElement() throws IOException {
        Token token = this.scanner.get();
        if (token.type == Scanner.LTQUES) {
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                stringBuffer.append(this.scanner.getUntil('?'));
                if (this.scanner.peek().type == Scanner.GT) {
                    this.scanner.get();
                    this.dtd.items.addElement(new DTDProcessingInstruction(stringBuffer.toString()));
                    return;
                }
                stringBuffer.append('?');
            }
        } else if (token.type == Scanner.CONDITIONAL) {
            Token expect = expect(Scanner.IDENTIFIER);
            if (expect.value.equals("IGNORE")) {
                this.scanner.skipConditional();
            } else if (expect.value.equals("INCLUDE")) {
                this.scanner.skipUntil(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            } else {
                String uriId = this.scanner.getUriId();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Invalid token in conditional: ");
                stringBuffer2.append(expect.value);
                throw new DTDParseException(uriId, stringBuffer2.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
        } else if (token.type != Scanner.ENDCONDITIONAL) {
            if (token.type == Scanner.COMMENT) {
                this.dtd.items.addElement(new DTDComment(token.value));
            } else if (token.type == Scanner.LTBANG) {
                Token expect2 = expect(Scanner.IDENTIFIER);
                if (expect2.value.equals("ELEMENT")) {
                    parseElement();
                } else if (expect2.value.equals("ATTLIST")) {
                    parseAttlist();
                } else if (expect2.value.equals("ENTITY")) {
                    parseEntity();
                } else if (expect2.value.equals("NOTATION")) {
                    parseNotation();
                } else {
                    skipUntil(Scanner.GT);
                }
            } else {
                String uriId2 = this.scanner.getUriId();
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Unexpected token: ");
                stringBuffer3.append(token.type.name);
                stringBuffer3.append("(");
                stringBuffer3.append(token.value);
                stringBuffer3.append(")");
                throw new DTDParseException(uriId2, stringBuffer3.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void skipUntil(TokenType tokenType) throws IOException {
        Token token = this.scanner.get();
        while (token.type != tokenType) {
            token = this.scanner.get();
        }
    }

    /* access modifiers changed from: protected */
    public Token expect(TokenType tokenType) throws IOException {
        Token token = this.scanner.get();
        if (token.type == tokenType) {
            return token;
        }
        if (token.value == null) {
            String uriId = this.scanner.getUriId();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Expected ");
            stringBuffer.append(tokenType.name);
            stringBuffer.append(" instead of ");
            stringBuffer.append(token.type.name);
            throw new DTDParseException(uriId, stringBuffer.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        String uriId2 = this.scanner.getUriId();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Expected ");
        stringBuffer2.append(tokenType.name);
        stringBuffer2.append(" instead of ");
        stringBuffer2.append(token.type.name);
        stringBuffer2.append("(");
        stringBuffer2.append(token.value);
        stringBuffer2.append(")");
        throw new DTDParseException(uriId2, stringBuffer2.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
    }

    /* access modifiers changed from: protected */
    public void parseElement() throws IOException {
        Token expect = expect(Scanner.IDENTIFIER);
        DTDElement dTDElement = (DTDElement) this.dtd.elements.get(expect.value);
        if (dTDElement == null) {
            dTDElement = new DTDElement(expect.value);
            this.dtd.elements.put(dTDElement.name, dTDElement);
        } else if (dTDElement.content != null) {
            String uriId = this.scanner.getUriId();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found second definition of element: ");
            stringBuffer.append(expect.value);
            throw new DTDParseException(uriId, stringBuffer.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        this.dtd.items.addElement(dTDElement);
        parseContentSpec(this.scanner, dTDElement);
        expect(Scanner.GT);
    }

    /* access modifiers changed from: protected */
    public void parseContentSpec(Scanner scanner2, DTDElement dTDElement) throws IOException {
        Token token = scanner2.get();
        if (token.type == Scanner.IDENTIFIER) {
            if (token.value.equals("EMPTY")) {
                dTDElement.content = new DTDEmpty();
            } else if (token.value.equals("ANY")) {
                dTDElement.content = new DTDAny();
            } else {
                String uriId = scanner2.getUriId();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid token in entity content spec ");
                stringBuffer.append(token.value);
                throw new DTDParseException(uriId, stringBuffer.toString(), scanner2.getLineNumber(), scanner2.getColumn());
            }
        } else if (token.type == Scanner.LPAREN) {
            Token peek = scanner2.peek();
            if (peek.type == Scanner.IDENTIFIER) {
                if (peek.value.equals("#PCDATA")) {
                    parseMixed(dTDElement);
                } else {
                    parseChildren(dTDElement);
                }
            } else if (peek.type == Scanner.LPAREN) {
                parseChildren(dTDElement);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseMixed(DTDElement dTDElement) throws IOException {
        DTDMixed dTDMixed = new DTDMixed();
        dTDMixed.add(new DTDPCData());
        this.scanner.get();
        dTDElement.content = dTDMixed;
        boolean z = true;
        while (true) {
            Token token = this.scanner.get();
            if (token.type == Scanner.RPAREN) {
                Token peek = this.scanner.peek();
                if (peek.type == Scanner.ASTERISK) {
                    this.scanner.get();
                    dTDMixed.cardinal = DTDCardinal.ZEROMANY;
                    return;
                } else if (z) {
                    dTDMixed.cardinal = DTDCardinal.NONE;
                    return;
                } else {
                    String uriId = this.scanner.getUriId();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Invalid token in Mixed content type, '*' required after (#PCDATA|xx ...): ");
                    stringBuffer.append(peek.type.name);
                    throw new DTDParseException(uriId, stringBuffer.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
                }
            } else if (token.type == Scanner.PIPE) {
                dTDMixed.add(new DTDName(this.scanner.get().value));
                z = false;
            } else {
                String uriId2 = this.scanner.getUriId();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Invalid token in Mixed content type: ");
                stringBuffer2.append(token.type.name);
                throw new DTDParseException(uriId2, stringBuffer2.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseChildren(DTDElement dTDElement) throws IOException {
        DTDContainer parseChoiceSequence = parseChoiceSequence();
        Token peek = this.scanner.peek();
        parseChoiceSequence.cardinal = parseCardinality();
        if (peek.type == Scanner.QUES) {
            parseChoiceSequence.cardinal = DTDCardinal.OPTIONAL;
        } else if (peek.type == Scanner.ASTERISK) {
            parseChoiceSequence.cardinal = DTDCardinal.ZEROMANY;
        } else if (peek.type == Scanner.PLUS) {
            parseChoiceSequence.cardinal = DTDCardinal.ONEMANY;
        } else {
            parseChoiceSequence.cardinal = DTDCardinal.NONE;
        }
        dTDElement.content = parseChoiceSequence;
    }

    /* access modifiers changed from: protected */
    public DTDContainer parseChoiceSequence() throws IOException {
        TokenType tokenType = null;
        DTDContainer dTDContainer = null;
        while (true) {
            DTDItem parseCP = parseCP();
            Token token = this.scanner.get();
            if (token.type == Scanner.PIPE || token.type == Scanner.COMMA) {
                if (tokenType == null || tokenType == token.type) {
                    tokenType = token.type;
                    if (dTDContainer == null) {
                        if (token.type == Scanner.PIPE) {
                            dTDContainer = new DTDChoice();
                        } else {
                            dTDContainer = new DTDSequence();
                        }
                    }
                    dTDContainer.add(parseCP);
                } else {
                    throw new DTDParseException(this.scanner.getUriId(), "Can't mix separators in a choice/sequence", this.scanner.getLineNumber(), this.scanner.getColumn());
                }
            } else if (token.type == Scanner.RPAREN) {
                if (dTDContainer == null) {
                    dTDContainer = new DTDSequence();
                }
                dTDContainer.add(parseCP);
                return dTDContainer;
            } else {
                String uriId = this.scanner.getUriId();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Found invalid token in sequence: ");
                stringBuffer.append(token.type.name);
                throw new DTDParseException(uriId, stringBuffer.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
        }
    }

    /* access modifiers changed from: protected */
    public DTDItem parseCP() throws IOException {
        DTDItem dTDItem;
        Token token = this.scanner.get();
        if (token.type == Scanner.IDENTIFIER) {
            dTDItem = new DTDName(token.value);
        } else if (token.type == Scanner.LPAREN) {
            dTDItem = parseChoiceSequence();
        } else {
            String uriId = this.scanner.getUriId();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Found invalid token in sequence: ");
            stringBuffer.append(token.type.name);
            throw new DTDParseException(uriId, stringBuffer.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        dTDItem.cardinal = parseCardinality();
        return dTDItem;
    }

    /* access modifiers changed from: protected */
    public DTDCardinal parseCardinality() throws IOException {
        Token peek = this.scanner.peek();
        if (peek.type == Scanner.QUES) {
            this.scanner.get();
            return DTDCardinal.OPTIONAL;
        } else if (peek.type == Scanner.ASTERISK) {
            this.scanner.get();
            return DTDCardinal.ZEROMANY;
        } else if (peek.type != Scanner.PLUS) {
            return DTDCardinal.NONE;
        } else {
            this.scanner.get();
            return DTDCardinal.ONEMANY;
        }
    }

    /* access modifiers changed from: protected */
    public void parseAttlist() throws IOException {
        Token expect = expect(Scanner.IDENTIFIER);
        DTDElement dTDElement = (DTDElement) this.dtd.elements.get(expect.value);
        DTDAttlist dTDAttlist = new DTDAttlist(expect.value);
        this.dtd.items.addElement(dTDAttlist);
        if (dTDElement == null) {
            dTDElement = new DTDElement(expect.value);
            this.dtd.elements.put(expect.value, dTDElement);
        }
        Token peek = this.scanner.peek();
        while (peek.type != Scanner.GT) {
            parseAttdef(this.scanner, dTDElement, dTDAttlist);
            peek = this.scanner.peek();
        }
        expect(Scanner.GT);
    }

    /* access modifiers changed from: protected */
    public void parseAttdef(Scanner scanner2, DTDElement dTDElement, DTDAttlist dTDAttlist) throws IOException {
        Token expect = expect(Scanner.IDENTIFIER);
        DTDAttribute dTDAttribute = new DTDAttribute(expect.value);
        dTDAttlist.attributes.addElement(dTDAttribute);
        dTDElement.attributes.put(expect.value, dTDAttribute);
        Token token = scanner2.get();
        if (token.type == Scanner.IDENTIFIER) {
            if (token.value.equals("NOTATION")) {
                dTDAttribute.type = parseNotationList();
            } else {
                dTDAttribute.type = token.value;
            }
        } else if (token.type == Scanner.LPAREN) {
            dTDAttribute.type = parseEnumeration();
        }
        Token peek = scanner2.peek();
        if (peek.type == Scanner.IDENTIFIER) {
            scanner2.get();
            if (peek.value.equals("#FIXED")) {
                dTDAttribute.decl = DTDDecl.FIXED;
                dTDAttribute.defaultValue = scanner2.get().value;
            } else if (peek.value.equals("#REQUIRED")) {
                dTDAttribute.decl = DTDDecl.REQUIRED;
            } else if (peek.value.equals("#IMPLIED")) {
                dTDAttribute.decl = DTDDecl.IMPLIED;
            } else {
                String uriId = scanner2.getUriId();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid token in attribute declaration: ");
                stringBuffer.append(peek.value);
                throw new DTDParseException(uriId, stringBuffer.toString(), scanner2.getLineNumber(), scanner2.getColumn());
            }
        } else if (peek.type == Scanner.STRING) {
            scanner2.get();
            dTDAttribute.decl = DTDDecl.VALUE;
            dTDAttribute.defaultValue = peek.value;
        }
    }

    /* access modifiers changed from: protected */
    public DTDNotationList parseNotationList() throws IOException {
        DTDNotationList dTDNotationList = new DTDNotationList();
        Token token = this.scanner.get();
        if (token.type == Scanner.LPAREN) {
            while (true) {
                Token token2 = this.scanner.get();
                if (token2.type == Scanner.IDENTIFIER) {
                    dTDNotationList.add(token2.value);
                    Token peek = this.scanner.peek();
                    if (peek.type == Scanner.RPAREN) {
                        this.scanner.get();
                        return dTDNotationList;
                    } else if (peek.type == Scanner.PIPE) {
                        this.scanner.get();
                    } else {
                        String uriId = this.scanner.getUriId();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Invalid token in notation: ");
                        stringBuffer.append(peek.type.name);
                        throw new DTDParseException(uriId, stringBuffer.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
                    }
                } else {
                    String uriId2 = this.scanner.getUriId();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Invalid token in notation: ");
                    stringBuffer2.append(token2.type.name);
                    throw new DTDParseException(uriId2, stringBuffer2.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
                }
            }
        } else {
            String uriId3 = this.scanner.getUriId();
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Invalid token in notation: ");
            stringBuffer3.append(token.type.name);
            throw new DTDParseException(uriId3, stringBuffer3.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
        }
    }

    /* access modifiers changed from: protected */
    public DTDEnumeration parseEnumeration() throws IOException {
        DTDEnumeration dTDEnumeration = new DTDEnumeration();
        while (true) {
            Token token = this.scanner.get();
            if (token.type == Scanner.IDENTIFIER || token.type == Scanner.NMTOKEN) {
                dTDEnumeration.add(token.value);
                Token peek = this.scanner.peek();
                if (peek.type == Scanner.RPAREN) {
                    this.scanner.get();
                    return dTDEnumeration;
                } else if (peek.type == Scanner.PIPE) {
                    this.scanner.get();
                } else {
                    String uriId = this.scanner.getUriId();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Invalid token in enumeration: ");
                    stringBuffer.append(peek.type.name);
                    throw new DTDParseException(uriId, stringBuffer.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
                }
            } else {
                String uriId2 = this.scanner.getUriId();
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Invalid token in enumeration: ");
                stringBuffer2.append(token.type.name);
                throw new DTDParseException(uriId2, stringBuffer2.toString(), this.scanner.getLineNumber(), this.scanner.getColumn());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseEntity() throws IOException {
        boolean z;
        DTDEntity dTDEntity;
        Token token = this.scanner.get();
        boolean z2 = true;
        if (token.type == Scanner.PERCENT) {
            token = expect(Scanner.IDENTIFIER);
            z = true;
        } else if (token.type == Scanner.IDENTIFIER) {
            z = false;
        } else {
            throw new DTDParseException(this.scanner.getUriId(), "Invalid entity declaration", this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        if (((DTDEntity) this.dtd.entities.get(token.value)) == null) {
            dTDEntity = new DTDEntity(token.value, this.defaultLocation);
            this.dtd.entities.put(dTDEntity.name, dTDEntity);
            z2 = false;
        } else {
            dTDEntity = new DTDEntity(token.value, this.defaultLocation);
        }
        this.dtd.items.addElement(dTDEntity);
        dTDEntity.isParsed = z;
        parseEntityDef(dTDEntity);
        if (dTDEntity.isParsed && dTDEntity.value != null && !z2) {
            this.scanner.addEntity(dTDEntity.name, dTDEntity.value);
        }
    }

    /* access modifiers changed from: protected */
    public void parseEntityDef(DTDEntity dTDEntity) throws IOException {
        Token token = this.scanner.get();
        if (token.type == Scanner.STRING) {
            if (dTDEntity.value == null) {
                dTDEntity.value = token.value;
            }
        } else if (token.type == Scanner.IDENTIFIER) {
            if (token.value.equals(DocumentType.SYSTEM_KEY)) {
                DTDSystem dTDSystem = new DTDSystem();
                dTDSystem.system = expect(Scanner.STRING).value;
                dTDEntity.externalID = dTDSystem;
            } else if (token.value.equals(DocumentType.PUBLIC_KEY)) {
                DTDPublic dTDPublic = new DTDPublic();
                dTDPublic.pub = expect(Scanner.STRING).value;
                dTDPublic.system = expect(Scanner.STRING).value;
                dTDEntity.externalID = dTDPublic;
            } else {
                throw new DTDParseException(this.scanner.getUriId(), "Invalid External ID specification", this.scanner.getLineNumber(), this.scanner.getColumn());
            }
            if (!dTDEntity.isParsed) {
                Token peek = this.scanner.peek();
                if (peek.type == Scanner.IDENTIFIER) {
                    if (peek.value.equals("NDATA")) {
                        this.scanner.get();
                        dTDEntity.ndata = expect(Scanner.IDENTIFIER).value;
                    } else {
                        throw new DTDParseException(this.scanner.getUriId(), "Invalid NData declaration", this.scanner.getLineNumber(), this.scanner.getColumn());
                    }
                }
            }
        } else {
            throw new DTDParseException(this.scanner.getUriId(), "Invalid entity definition", this.scanner.getLineNumber(), this.scanner.getColumn());
        }
        expect(Scanner.GT);
    }

    /* access modifiers changed from: protected */
    public void parseNotation() throws IOException {
        DTDNotation dTDNotation = new DTDNotation();
        dTDNotation.name = expect(Scanner.IDENTIFIER).value;
        this.dtd.notations.put(dTDNotation.name, dTDNotation);
        this.dtd.items.addElement(dTDNotation);
        Token expect = expect(Scanner.IDENTIFIER);
        if (expect.value.equals(DocumentType.SYSTEM_KEY)) {
            DTDSystem dTDSystem = new DTDSystem();
            dTDSystem.system = expect(Scanner.STRING).value;
            dTDNotation.externalID = dTDSystem;
        } else if (expect.value.equals(DocumentType.PUBLIC_KEY)) {
            DTDPublic dTDPublic = new DTDPublic();
            dTDPublic.pub = expect(Scanner.STRING).value;
            dTDPublic.system = null;
            if (this.scanner.peek().type == Scanner.STRING) {
                dTDPublic.system = this.scanner.get().value;
            }
            dTDNotation.externalID = dTDPublic;
        }
        expect(Scanner.GT);
    }

    public DTDEntity expandEntity(String str) {
        return (DTDEntity) this.dtd.entities.get(str);
    }
}
