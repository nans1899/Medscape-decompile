package org.mozilla.javascript.tools.debugger.treetable;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;

public class TreeTableModelAdapter extends AbstractTableModel {
    private static final long serialVersionUID = 48741114609209052L;
    JTree tree;
    TreeTableModel treeTableModel;

    public TreeTableModelAdapter(TreeTableModel treeTableModel2, JTree jTree) {
        this.tree = jTree;
        this.treeTableModel = treeTableModel2;
        jTree.addTreeExpansionListener(new TreeExpansionListener() {
            public void treeExpanded(TreeExpansionEvent treeExpansionEvent) {
                TreeTableModelAdapter.this.fireTableDataChanged();
            }

            public void treeCollapsed(TreeExpansionEvent treeExpansionEvent) {
                TreeTableModelAdapter.this.fireTableDataChanged();
            }
        });
        treeTableModel2.addTreeModelListener(new TreeModelListener() {
            public void treeNodesChanged(TreeModelEvent treeModelEvent) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }

            public void treeNodesInserted(TreeModelEvent treeModelEvent) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }

            public void treeNodesRemoved(TreeModelEvent treeModelEvent) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }

            public void treeStructureChanged(TreeModelEvent treeModelEvent) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }
        });
    }

    public int getColumnCount() {
        return this.treeTableModel.getColumnCount();
    }

    public String getColumnName(int i) {
        return this.treeTableModel.getColumnName(i);
    }

    public Class<?> getColumnClass(int i) {
        return this.treeTableModel.getColumnClass(i);
    }

    public int getRowCount() {
        return this.tree.getRowCount();
    }

    /* access modifiers changed from: protected */
    public Object nodeForRow(int i) {
        return this.tree.getPathForRow(i).getLastPathComponent();
    }

    public Object getValueAt(int i, int i2) {
        return this.treeTableModel.getValueAt(nodeForRow(i), i2);
    }

    public boolean isCellEditable(int i, int i2) {
        return this.treeTableModel.isCellEditable(nodeForRow(i), i2);
    }

    public void setValueAt(Object obj, int i, int i2) {
        this.treeTableModel.setValueAt(obj, nodeForRow(i), i2);
    }

    /* access modifiers changed from: protected */
    public void delayedFireTableDataChanged() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TreeTableModelAdapter.this.fireTableDataChanged();
            }
        });
    }
}
