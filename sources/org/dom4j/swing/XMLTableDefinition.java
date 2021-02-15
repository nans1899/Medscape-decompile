package org.dom4j.swing;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.jaxen.VariableContext;

public class XMLTableDefinition implements Serializable, VariableContext {
    private XMLTableColumnDefinition[] columnArray;
    private Map columnNameIndex;
    private List columns = new ArrayList();
    private Object rowValue;
    private XPath rowXPath;
    private VariableContext variableContext;

    public static XMLTableDefinition load(Document document) {
        return load(document.getRootElement());
    }

    public static XMLTableDefinition load(Element element) {
        XMLTableDefinition xMLTableDefinition = new XMLTableDefinition();
        xMLTableDefinition.setRowExpression(element.attributeValue("select"));
        Iterator elementIterator = element.elementIterator("column");
        while (elementIterator.hasNext()) {
            Element element2 = (Element) elementIterator.next();
            String attributeValue = element2.attributeValue("select");
            String text = element2.getText();
            String attributeValue2 = element2.attributeValue("type", "string");
            String attributeValue3 = element2.attributeValue("columnNameXPath");
            int parseType = XMLTableColumnDefinition.parseType(attributeValue2);
            if (attributeValue3 != null) {
                xMLTableDefinition.addColumnWithXPathName(attributeValue3, attributeValue, parseType);
            } else {
                xMLTableDefinition.addColumn(text, attributeValue, parseType);
            }
        }
        return xMLTableDefinition;
    }

    public Class getColumnClass(int i) {
        return getColumn(i).getColumnClass();
    }

    public int getColumnCount() {
        return this.columns.size();
    }

    public String getColumnName(int i) {
        return getColumn(i).getName();
    }

    public XPath getColumnXPath(int i) {
        return getColumn(i).getXPath();
    }

    public XPath getColumnNameXPath(int i) {
        return getColumn(i).getColumnNameXPath();
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public synchronized java.lang.Object getValueAt(java.lang.Object r1, int r2) {
        /*
            r0 = this;
            monitor-enter(r0)
            org.dom4j.swing.XMLTableColumnDefinition r2 = r0.getColumn((int) r2)     // Catch:{ all -> 0x0015 }
            monitor-enter(r0)     // Catch:{ all -> 0x0015 }
            r0.rowValue = r1     // Catch:{ all -> 0x0012 }
            java.lang.Object r1 = r2.getValue(r1)     // Catch:{ all -> 0x0012 }
            r2 = 0
            r0.rowValue = r2     // Catch:{ all -> 0x0012 }
            monitor-exit(r0)     // Catch:{ all -> 0x0012 }
            monitor-exit(r0)
            return r1
        L_0x0012:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0012 }
            throw r1     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.swing.XMLTableDefinition.getValueAt(java.lang.Object, int):java.lang.Object");
    }

    public void addColumn(String str, String str2) {
        addColumn(str, str2, 0);
    }

    public void addColumn(String str, String str2, int i) {
        addColumn(new XMLTableColumnDefinition(str, createColumnXPath(str2), i));
    }

    public void addColumnWithXPathName(String str, String str2, int i) {
        addColumn(new XMLTableColumnDefinition(createColumnXPath(str), createColumnXPath(str2), i));
    }

    public void addStringColumn(String str, String str2) {
        addColumn(str, str2, 1);
    }

    public void addNumberColumn(String str, String str2) {
        addColumn(str, str2, 2);
    }

    public void addColumn(XMLTableColumnDefinition xMLTableColumnDefinition) {
        clearCaches();
        this.columns.add(xMLTableColumnDefinition);
    }

    public void removeColumn(XMLTableColumnDefinition xMLTableColumnDefinition) {
        clearCaches();
        this.columns.remove(xMLTableColumnDefinition);
    }

    public void clear() {
        clearCaches();
        this.columns.clear();
    }

    public XMLTableColumnDefinition getColumn(int i) {
        if (this.columnArray == null) {
            XMLTableColumnDefinition[] xMLTableColumnDefinitionArr = new XMLTableColumnDefinition[this.columns.size()];
            this.columnArray = xMLTableColumnDefinitionArr;
            this.columns.toArray(xMLTableColumnDefinitionArr);
        }
        return this.columnArray[i];
    }

    public XMLTableColumnDefinition getColumn(String str) {
        if (this.columnNameIndex == null) {
            this.columnNameIndex = new HashMap();
            for (XMLTableColumnDefinition xMLTableColumnDefinition : this.columns) {
                this.columnNameIndex.put(xMLTableColumnDefinition.getName(), xMLTableColumnDefinition);
            }
        }
        return (XMLTableColumnDefinition) this.columnNameIndex.get(str);
    }

    public XPath getRowXPath() {
        return this.rowXPath;
    }

    public void setRowXPath(XPath xPath) {
        this.rowXPath = xPath;
    }

    public void setRowExpression(String str) {
        setRowXPath(createXPath(str));
    }

    public Object getVariableValue(String str, String str2, String str3) {
        XMLTableColumnDefinition column = getColumn(str3);
        if (column != null) {
            return column.getValue(this.rowValue);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public XPath createXPath(String str) {
        return DocumentHelper.createXPath(str);
    }

    /* access modifiers changed from: protected */
    public XPath createColumnXPath(String str) {
        XPath createXPath = createXPath(str);
        createXPath.setVariableContext(this);
        return createXPath;
    }

    /* access modifiers changed from: protected */
    public void clearCaches() {
        this.columnArray = null;
        this.columnNameIndex = null;
    }

    /* access modifiers changed from: protected */
    public void handleException(Exception exc) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Caught: ");
        stringBuffer.append(exc);
        printStream.println(stringBuffer.toString());
    }
}
