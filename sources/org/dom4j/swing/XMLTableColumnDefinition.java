package org.dom4j.swing;

import java.io.PrintStream;
import java.io.Serializable;
import org.dom4j.DocumentHelper;
import org.dom4j.XPath;

public class XMLTableColumnDefinition implements Serializable {
    public static final int NODE_TYPE = 3;
    public static final int NUMBER_TYPE = 2;
    public static final int OBJECT_TYPE = 0;
    public static final int STRING_TYPE = 1;
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    static /* synthetic */ Class class$2;
    static /* synthetic */ Class class$3;
    private XPath columnNameXPath;
    private String name;
    private int type;
    private XPath xpath;

    public XMLTableColumnDefinition() {
    }

    public XMLTableColumnDefinition(String str, String str2, int i) {
        this.name = str;
        this.type = i;
        this.xpath = createXPath(str2);
    }

    public XMLTableColumnDefinition(String str, XPath xPath, int i) {
        this.name = str;
        this.xpath = xPath;
        this.type = i;
    }

    public XMLTableColumnDefinition(XPath xPath, XPath xPath2, int i) {
        this.xpath = xPath2;
        this.columnNameXPath = xPath;
        this.type = i;
    }

    public static int parseType(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        if (str.equals("string")) {
            return 1;
        }
        if (str.equals("number")) {
            return 2;
        }
        return str.equals("node") ? 3 : 0;
    }

    public Class getColumnClass() {
        int i = this.type;
        if (i == 1) {
            Class<?> cls = class$0;
            if (cls == null) {
                try {
                    cls = Class.forName("java.lang.String");
                    class$0 = cls;
                } catch (ClassNotFoundException e) {
                    throw new NoClassDefFoundError(e.getMessage());
                }
            }
            return cls;
        } else if (i == 2) {
            Class<?> cls2 = class$1;
            if (cls2 == null) {
                try {
                    cls2 = Class.forName("java.lang.Number");
                    class$1 = cls2;
                } catch (ClassNotFoundException e2) {
                    throw new NoClassDefFoundError(e2.getMessage());
                }
            }
            return cls2;
        } else if (i != 3) {
            Class<?> cls3 = class$3;
            if (cls3 == null) {
                try {
                    cls3 = Class.forName("java.lang.Object");
                    class$3 = cls3;
                } catch (ClassNotFoundException e3) {
                    throw new NoClassDefFoundError(e3.getMessage());
                }
            }
            return cls3;
        } else {
            Class<?> cls4 = class$2;
            if (cls4 == null) {
                try {
                    cls4 = Class.forName("org.dom4j.Node");
                    class$2 = cls4;
                } catch (ClassNotFoundException e4) {
                    throw new NoClassDefFoundError(e4.getMessage());
                }
            }
            return cls4;
        }
    }

    public Object getValue(Object obj) {
        int i = this.type;
        if (i == 1) {
            return this.xpath.valueOf(obj);
        }
        if (i == 2) {
            return this.xpath.numberValueOf(obj);
        }
        if (i != 3) {
            return this.xpath.evaluate(obj);
        }
        return this.xpath.selectSingleNode(obj);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public XPath getXPath() {
        return this.xpath;
    }

    public void setXPath(XPath xPath) {
        this.xpath = xPath;
    }

    public XPath getColumnNameXPath() {
        return this.columnNameXPath;
    }

    public void setColumnNameXPath(XPath xPath) {
        this.columnNameXPath = xPath;
    }

    /* access modifiers changed from: protected */
    public XPath createXPath(String str) {
        return DocumentHelper.createXPath(str);
    }

    /* access modifiers changed from: protected */
    public void handleException(Exception exc) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Caught: ");
        stringBuffer.append(exc);
        printStream.println(stringBuffer.toString());
    }
}
