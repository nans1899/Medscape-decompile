package org.mozilla.javascript.tools.debugger;

import com.google.android.gms.ads.AdError;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import org.mozilla.javascript.tools.debugger.treetable.TreeTableModel;

/* compiled from: SwingGui */
class VariableModel implements TreeTableModel {
    private static final VariableNode[] CHILDLESS = new VariableNode[0];
    private static final String[] cNames = {" Name", " Value"};
    private static final Class<?>[] cTypes = {TreeTableModel.class, String.class};
    private Dim debugger;
    private VariableNode root;

    public void addTreeModelListener(TreeModelListener treeModelListener) {
    }

    public boolean isCellEditable(Object obj, int i) {
        return i == 0;
    }

    public void removeTreeModelListener(TreeModelListener treeModelListener) {
    }

    public void setValueAt(Object obj, Object obj2, int i) {
    }

    public void valueForPathChanged(TreePath treePath, Object obj) {
    }

    public VariableModel() {
    }

    public VariableModel(Dim dim, Object obj) {
        this.debugger = dim;
        this.root = new VariableNode(obj, "this");
    }

    public Object getRoot() {
        if (this.debugger == null) {
            return null;
        }
        return this.root;
    }

    public int getChildCount(Object obj) {
        if (this.debugger == null) {
            return 0;
        }
        return children((VariableNode) obj).length;
    }

    public Object getChild(Object obj, int i) {
        if (this.debugger == null) {
            return null;
        }
        return children((VariableNode) obj)[i];
    }

    public boolean isLeaf(Object obj) {
        if (this.debugger == null || children((VariableNode) obj).length == 0) {
            return true;
        }
        return false;
    }

    public int getIndexOfChild(Object obj, Object obj2) {
        if (this.debugger == null) {
            return -1;
        }
        VariableNode variableNode = (VariableNode) obj2;
        VariableNode[] children = children((VariableNode) obj);
        for (int i = 0; i != children.length; i++) {
            if (children[i] == variableNode) {
                return i;
            }
        }
        return -1;
    }

    public int getColumnCount() {
        return cNames.length;
    }

    public String getColumnName(int i) {
        return cNames[i];
    }

    public Class<?> getColumnClass(int i) {
        return cTypes[i];
    }

    public Object getValueAt(Object obj, int i) {
        String str;
        Dim dim = this.debugger;
        if (dim == null) {
            return null;
        }
        VariableNode variableNode = (VariableNode) obj;
        if (i == 0) {
            return variableNode.toString();
        }
        if (i != 1) {
            return null;
        }
        try {
            str = dim.objectToString(getValue(variableNode));
        } catch (RuntimeException e) {
            str = e.getMessage();
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (Character.isISOControl(charAt)) {
                charAt = ' ';
            }
            stringBuffer.append(charAt);
        }
        return stringBuffer.toString();
    }

    private VariableNode[] children(VariableNode variableNode) {
        VariableNode[] variableNodeArr;
        if (variableNode.children != null) {
            return variableNode.children;
        }
        Object value = getValue(variableNode);
        Object[] objectIds = this.debugger.getObjectIds(value);
        if (objectIds == null || objectIds.length == 0) {
            variableNodeArr = CHILDLESS;
        } else {
            Arrays.sort(objectIds, new Comparator<Object>() {
                public int compare(Object obj, Object obj2) {
                    if (obj instanceof String) {
                        if (obj2 instanceof Integer) {
                            return -1;
                        }
                        return ((String) obj).compareToIgnoreCase((String) obj2);
                    } else if (obj2 instanceof String) {
                        return 1;
                    } else {
                        return ((Integer) obj).intValue() - ((Integer) obj2).intValue();
                    }
                }
            });
            variableNodeArr = new VariableNode[objectIds.length];
            for (int i = 0; i != objectIds.length; i++) {
                variableNodeArr[i] = new VariableNode(value, objectIds[i]);
            }
        }
        VariableNode[] unused = variableNode.children = variableNodeArr;
        return variableNodeArr;
    }

    public Object getValue(VariableNode variableNode) {
        try {
            return this.debugger.getObjectProperty(variableNode.object, variableNode.id);
        } catch (Exception unused) {
            return AdError.UNDEFINED_DOMAIN;
        }
    }

    /* compiled from: SwingGui */
    private static class VariableNode {
        /* access modifiers changed from: private */
        public VariableNode[] children;
        /* access modifiers changed from: private */
        public Object id;
        /* access modifiers changed from: private */
        public Object object;

        public VariableNode(Object obj, Object obj2) {
            this.object = obj;
            this.id = obj2;
        }

        public String toString() {
            Object obj = this.id;
            if (obj instanceof String) {
                return (String) obj;
            }
            return "[" + ((Integer) this.id).intValue() + "]";
        }
    }
}
