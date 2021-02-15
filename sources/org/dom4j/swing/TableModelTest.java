package org.dom4j.swing;

import javax.swing.table.TableModel;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;

public class TableModelTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.swing.TableModelTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testServletTable() throws Exception {
        Document document = getDocument("/xml/web.xml");
        XMLTableDefinition xMLTableDefinition = new XMLTableDefinition();
        xMLTableDefinition.setRowExpression("/web-app/servlet");
        xMLTableDefinition.addStringColumn("Name", "servlet-name");
        xMLTableDefinition.addStringColumn("Class", "servlet-class");
        xMLTableDefinition.addStringColumn("Mapping", "../servlet-mapping[servlet-name=$Name]/url-pattern");
        XMLTableModel xMLTableModel = new XMLTableModel(xMLTableDefinition, (Object) document);
        assertEquals("correct row count", xMLTableModel.getRowCount(), 2);
        assertEquals("correct column count", xMLTableModel.getColumnCount(), 3);
        assertColumnNameEquals(xMLTableModel, 0, "Name");
        assertColumnNameEquals(xMLTableModel, 1, "Class");
        assertColumnNameEquals(xMLTableModel, 2, "Mapping");
        assertCellEquals(xMLTableModel, 0, 0, "snoop");
        assertCellEquals(xMLTableModel, 1, 0, "file");
        assertCellEquals(xMLTableModel, 0, 1, "SnoopServlet");
        assertCellEquals(xMLTableModel, 1, 1, "ViewFile");
        assertCellEquals(xMLTableModel, 0, 2, "/foo/snoop");
        assertCellEquals(xMLTableModel, 1, 2, "");
    }

    public void testServletTableViaXMLDescription() throws Exception {
        XMLTableModel xMLTableModel = new XMLTableModel(getDocument("/xml/swing/tableForWeb.xml"), (Object) getDocument("/xml/web.xml"));
        assertEquals("correct row count", xMLTableModel.getRowCount(), 2);
        assertEquals("correct column count", xMLTableModel.getColumnCount(), 3);
        assertColumnNameEquals(xMLTableModel, 0, "Name");
        assertColumnNameEquals(xMLTableModel, 1, "Class");
        assertColumnNameEquals(xMLTableModel, 2, "Mapping");
        assertCellEquals(xMLTableModel, 0, 0, "snoop");
        assertCellEquals(xMLTableModel, 1, 0, "file");
        assertCellEquals(xMLTableModel, 0, 1, "SnoopServlet");
        assertCellEquals(xMLTableModel, 1, 1, "ViewFile");
        assertCellEquals(xMLTableModel, 0, 2, "/foo/snoop");
        assertCellEquals(xMLTableModel, 1, 2, "");
    }

    /* access modifiers changed from: protected */
    public void assertColumnNameEquals(TableModel tableModel, int i, String str) {
        StringBuffer stringBuffer = new StringBuffer("Column name correct for index: ");
        stringBuffer.append(i);
        assertEquals(stringBuffer.toString(), str, tableModel.getColumnName(i));
    }

    /* access modifiers changed from: protected */
    public void assertCellEquals(TableModel tableModel, int i, int i2, Object obj) {
        StringBuffer stringBuffer = new StringBuffer("Cell value at row: ");
        stringBuffer.append(i);
        stringBuffer.append(" col: ");
        stringBuffer.append(i2);
        assertEquals(stringBuffer.toString(), obj, tableModel.getValueAt(i, i2));
    }
}
