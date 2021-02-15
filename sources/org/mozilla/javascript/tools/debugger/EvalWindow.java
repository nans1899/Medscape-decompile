package org.mozilla.javascript.tools.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

/* compiled from: SwingGui */
class EvalWindow extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = -2860585845212160176L;
    private EvalTextArea evalTextArea;

    public EvalWindow(String str, SwingGui swingGui) {
        super(str, true, false, true, true);
        EvalTextArea evalTextArea2 = new EvalTextArea(swingGui);
        this.evalTextArea = evalTextArea2;
        evalTextArea2.setRows(24);
        this.evalTextArea.setColumns(80);
        setContentPane(new JScrollPane(this.evalTextArea));
        pack();
        setVisible(true);
    }

    public void setEnabled(boolean z) {
        EvalWindow.super.setEnabled(z);
        this.evalTextArea.setEnabled(z);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Cut")) {
            this.evalTextArea.cut();
        } else if (actionCommand.equals("Copy")) {
            this.evalTextArea.copy();
        } else if (actionCommand.equals("Paste")) {
            this.evalTextArea.paste();
        }
    }
}
