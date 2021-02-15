package org.dom4j.rule;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.xpath.DefaultXPath;

public class StylesheetTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected Stylesheet stylesheet;
    protected String[] templates = {"/", "*", "root", "author", "@name", "root/author", "author[@location='UK']", "root/author[@location='UK']", "root//author[@location='UK']"};
    protected String[] templates2 = {"/", "title", "para", "*"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.rule.StylesheetTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testRules() throws Exception {
        for (String addTemplate : this.templates) {
            addTemplate(addTemplate);
        }
        log("");
        log("........................................");
        log("");
        log("Running stylesheet");
        this.stylesheet.run((Node) this.document);
        log("Finished");
    }

    public void testLittleDoc() throws Exception {
        for (String addTemplate : this.templates2) {
            addTemplate(addTemplate);
        }
        Document document = getDocument("/xml/test/littledoc.xml");
        Stylesheet stylesheet2 = new Stylesheet();
        this.stylesheet = stylesheet2;
        stylesheet2.setValueOfAction(new Action() {
            public void run(Node node) {
                StylesheetTest stylesheetTest = StylesheetTest.this;
                StringBuffer stringBuffer = new StringBuffer("Default ValueOf action on node: ");
                stringBuffer.append(node);
                stylesheetTest.log(stringBuffer.toString());
                StylesheetTest.this.log("........................................");
            }
        });
        this.stylesheet.run((Node) document);
    }

    public void testFireRuleForNode() throws Exception {
        final StringBuffer stringBuffer = new StringBuffer();
        final Stylesheet stylesheet2 = new Stylesheet();
        stylesheet2.addRule(new Rule(DocumentHelper.createPattern("url"), (Action) new Action() {
            public void run(Node node) throws Exception {
                stringBuffer.append("url");
                stylesheet2.applyTemplates(node);
            }
        }));
        stylesheet2.applyTemplates((Object) this.document, (XPath) new DefaultXPath("root/author/url"));
        assertEquals("Check url is processed twice", "urlurl", stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        Stylesheet stylesheet2 = new Stylesheet();
        this.stylesheet = stylesheet2;
        stylesheet2.setValueOfAction(new Action() {
            public void run(Node node) {
                StylesheetTest stylesheetTest = StylesheetTest.this;
                StringBuffer stringBuffer = new StringBuffer("Default ValueOf action on node: ");
                stringBuffer.append(node);
                stylesheetTest.log(stringBuffer.toString());
                StylesheetTest.this.log("........................................");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void addTemplate(final String str) {
        StringBuffer stringBuffer = new StringBuffer("Adding template match: ");
        stringBuffer.append(str);
        log(stringBuffer.toString());
        Pattern createPattern = DocumentHelper.createPattern(str);
        StringBuffer stringBuffer2 = new StringBuffer("Pattern: ");
        stringBuffer2.append(createPattern);
        log(stringBuffer2.toString());
        log("........................................");
        this.stylesheet.addRule(new Rule(createPattern, (Action) new Action() {
            public void run(Node node) throws Exception {
                StylesheetTest stylesheetTest = StylesheetTest.this;
                StringBuffer stringBuffer = new StringBuffer("Matched pattern: ");
                stringBuffer.append(str);
                stylesheetTest.log(stringBuffer.toString());
                StylesheetTest stylesheetTest2 = StylesheetTest.this;
                StringBuffer stringBuffer2 = new StringBuffer("Node: ");
                stringBuffer2.append(node.asXML());
                stylesheetTest2.log(stringBuffer2.toString());
                StylesheetTest.this.log("........................................");
                StylesheetTest.this.stylesheet.applyTemplates(node);
            }
        }));
    }
}
