package org.dom4j.swing;

import java.io.PrintStream;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;

public class XMLTableModel extends AbstractTableModel {
    private XMLTableDefinition definition;
    private List rows;
    private Object source;

    public XMLTableModel(Element element, Object obj) {
        this(XMLTableDefinition.load(element), obj);
    }

    public XMLTableModel(Document document, Object obj) {
        this(XMLTableDefinition.load(document), obj);
    }

    public XMLTableModel(XMLTableDefinition xMLTableDefinition, Object obj) {
        this.definition = xMLTableDefinition;
        this.source = obj;
    }

    public Object getRowValue(int i) {
        return getRows().get(i);
    }

    public List getRows() {
        if (this.rows == null) {
            this.rows = this.definition.getRowXPath().selectNodes(this.source);
        }
        return this.rows;
    }

    public Class getColumnClass(int i) {
        return this.definition.getColumnClass(i);
    }

    public int getColumnCount() {
        return this.definition.getColumnCount();
    }

    public String getColumnName(int i) {
        XPath columnNameXPath = this.definition.getColumnNameXPath(i);
        if (columnNameXPath == null) {
            return this.definition.getColumnName(i);
        }
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Evaluating column xpath: ");
        stringBuffer.append(columnNameXPath);
        stringBuffer.append(" value: ");
        stringBuffer.append(columnNameXPath.valueOf(this.source));
        printStream.println(stringBuffer.toString());
        return columnNameXPath.valueOf(this.source);
    }

    public Object getValueAt(int i, int i2) {
        try {
            return this.definition.getValueAt(getRowValue(i), i2);
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public int getRowCount() {
        return getRows().size();
    }

    public XMLTableDefinition getDefinition() {
        return this.definition;
    }

    public void setDefinition(XMLTableDefinition xMLTableDefinition) {
        this.definition = xMLTableDefinition;
    }

    public Object getSource() {
        return this.source;
    }

    public void setSource(Object obj) {
        this.source = obj;
        this.rows = null;
    }

    /* access modifiers changed from: protected */
    public void handleException(Exception exc) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Caught: ");
        stringBuffer.append(exc);
        printStream.println(stringBuffer.toString());
    }
}
