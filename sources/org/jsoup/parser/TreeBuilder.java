package org.jsoup.parser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Token;

abstract class TreeBuilder {
    protected String baseUri;
    protected Token currentToken;
    protected Document doc;
    private Token.EndTag end = new Token.EndTag();
    protected Parser parser;
    CharacterReader reader;
    protected ParseSettings settings;
    protected ArrayList<Element> stack;
    private Token.StartTag start = new Token.StartTag();
    Tokeniser tokeniser;

    /* access modifiers changed from: package-private */
    public abstract ParseSettings defaultSettings();

    /* access modifiers changed from: package-private */
    public abstract List<Node> parseFragment(String str, Element element, String str2, Parser parser2);

    /* access modifiers changed from: protected */
    public abstract boolean process(Token token);

    TreeBuilder() {
    }

    /* access modifiers changed from: protected */
    public void initialiseParse(Reader reader2, String str, Parser parser2) {
        Validate.notNull(reader2, "String input must not be null");
        Validate.notNull(str, "BaseURI must not be null");
        Document document = new Document(str);
        this.doc = document;
        document.parser(parser2);
        this.parser = parser2;
        this.settings = parser2.settings();
        this.reader = new CharacterReader(reader2);
        this.currentToken = null;
        this.tokeniser = new Tokeniser(this.reader, parser2.getErrors());
        this.stack = new ArrayList<>(32);
        this.baseUri = str;
    }

    /* access modifiers changed from: package-private */
    public Document parse(Reader reader2, String str, Parser parser2) {
        initialiseParse(reader2, str, parser2);
        runParser();
        this.reader.close();
        this.reader = null;
        this.tokeniser = null;
        this.stack = null;
        return this.doc;
    }

    /* access modifiers changed from: protected */
    public void runParser() {
        Token read;
        Tokeniser tokeniser2 = this.tokeniser;
        Token.TokenType tokenType = Token.TokenType.EOF;
        do {
            read = tokeniser2.read();
            process(read);
            read.reset();
        } while (read.type != tokenType);
    }

    /* access modifiers changed from: protected */
    public boolean processStartTag(String str) {
        Token.StartTag startTag = this.start;
        if (this.currentToken == startTag) {
            return process(new Token.StartTag().name(str));
        }
        return process(startTag.reset().name(str));
    }

    public boolean processStartTag(String str, Attributes attributes) {
        Token.StartTag startTag = this.start;
        if (this.currentToken == startTag) {
            return process(new Token.StartTag().nameAttr(str, attributes));
        }
        startTag.reset();
        startTag.nameAttr(str, attributes);
        return process(startTag);
    }

    /* access modifiers changed from: protected */
    public boolean processEndTag(String str) {
        Token token = this.currentToken;
        Token.EndTag endTag = this.end;
        if (token == endTag) {
            return process(new Token.EndTag().name(str));
        }
        return process(endTag.reset().name(str));
    }

    /* access modifiers changed from: protected */
    public Element currentElement() {
        int size = this.stack.size();
        if (size > 0) {
            return this.stack.get(size - 1);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void error(String str) {
        ParseErrorList errors = this.parser.getErrors();
        if (errors.canAddError()) {
            errors.add(new ParseError(this.reader.pos(), str));
        }
    }
}
