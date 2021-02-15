package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Parser {
    private ParseErrorList errors = ParseErrorList.noTracking();
    private ParseSettings settings;
    private TreeBuilder treeBuilder;

    public Parser(TreeBuilder treeBuilder2) {
        this.treeBuilder = treeBuilder2;
        this.settings = treeBuilder2.defaultSettings();
    }

    public Document parseInput(String str, String str2) {
        return this.treeBuilder.parse(new StringReader(str), str2, this);
    }

    public Document parseInput(Reader reader, String str) {
        return this.treeBuilder.parse(reader, str, this);
    }

    public List<Node> parseFragmentInput(String str, Element element, String str2) {
        return this.treeBuilder.parseFragment(str, element, str2, this);
    }

    public TreeBuilder getTreeBuilder() {
        return this.treeBuilder;
    }

    public Parser setTreeBuilder(TreeBuilder treeBuilder2) {
        this.treeBuilder = treeBuilder2;
        treeBuilder2.parser = this;
        return this;
    }

    public boolean isTrackErrors() {
        return this.errors.getMaxSize() > 0;
    }

    public Parser setTrackErrors(int i) {
        this.errors = i > 0 ? ParseErrorList.tracking(i) : ParseErrorList.noTracking();
        return this;
    }

    public ParseErrorList getErrors() {
        return this.errors;
    }

    public Parser settings(ParseSettings parseSettings) {
        this.settings = parseSettings;
        return this;
    }

    public ParseSettings settings() {
        return this.settings;
    }

    public static Document parse(String str, String str2) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        return htmlTreeBuilder.parse(new StringReader(str), str2, new Parser(htmlTreeBuilder));
    }

    public static List<Node> parseFragment(String str, Element element, String str2) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        return htmlTreeBuilder.parseFragment(str, element, str2, new Parser(htmlTreeBuilder));
    }

    public static List<Node> parseFragment(String str, Element element, String str2, ParseErrorList parseErrorList) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        Parser parser = new Parser(htmlTreeBuilder);
        parser.errors = parseErrorList;
        return htmlTreeBuilder.parseFragment(str, element, str2, parser);
    }

    public static List<Node> parseXmlFragment(String str, String str2) {
        XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
        return xmlTreeBuilder.parseFragment(str, str2, new Parser(xmlTreeBuilder));
    }

    public static Document parseBodyFragment(String str, String str2) {
        Document createShell = Document.createShell(str2);
        Element body = createShell.body();
        List<Node> parseFragment = parseFragment(str, body, str2);
        Node[] nodeArr = (Node[]) parseFragment.toArray(new Node[0]);
        for (int length = nodeArr.length - 1; length > 0; length--) {
            nodeArr[length].remove();
        }
        for (Node appendChild : nodeArr) {
            body.appendChild(appendChild);
        }
        return createShell;
    }

    public static String unescapeEntities(String str, boolean z) {
        return new Tokeniser(new CharacterReader(str), ParseErrorList.noTracking()).unescapeEntities(z);
    }

    public static Parser htmlParser() {
        return new Parser(new HtmlTreeBuilder());
    }

    public static Parser xmlParser() {
        return new Parser(new XmlTreeBuilder());
    }
}
