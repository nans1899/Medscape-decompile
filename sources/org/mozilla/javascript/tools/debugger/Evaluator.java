package org.mozilla.javascript.tools.debugger;

import javax.swing.JTable;

/* compiled from: SwingGui */
class Evaluator extends JTable {
    private static final long serialVersionUID = 8133672432982594256L;
    MyTableModel tableModel = getModel();

    public Evaluator(SwingGui swingGui) {
        super(new MyTableModel(swingGui));
    }
}
