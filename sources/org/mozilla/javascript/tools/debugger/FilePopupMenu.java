package org.mozilla.javascript.tools.debugger;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/* compiled from: SwingGui */
class FilePopupMenu extends JPopupMenu {
    private static final long serialVersionUID = 3589525009546013565L;
    int x;
    int y;

    public FilePopupMenu(FileTextArea fileTextArea) {
        JMenuItem jMenuItem = new JMenuItem("Set Breakpoint");
        add(jMenuItem);
        jMenuItem.addActionListener(fileTextArea);
        JMenuItem jMenuItem2 = new JMenuItem("Clear Breakpoint");
        add(jMenuItem2);
        jMenuItem2.addActionListener(fileTextArea);
        JMenuItem jMenuItem3 = new JMenuItem("Run");
        add(jMenuItem3);
        jMenuItem3.addActionListener(fileTextArea);
    }

    public void show(JComponent jComponent, int i, int i2) {
        this.x = i;
        this.y = i2;
        FilePopupMenu.super.show(jComponent, i, i2);
    }
}
