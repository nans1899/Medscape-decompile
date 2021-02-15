package org.mozilla.javascript.tools.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import org.mozilla.javascript.tools.debugger.Dim;

/* compiled from: SwingGui */
class FileWindow extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = -6212382604952082370L;
    int currentPos = -1;
    private SwingGui debugGui;
    private FileHeader fileHeader;
    private JScrollPane p;
    private Dim.SourceInfo sourceInfo;
    FileTextArea textArea;

    /* access modifiers changed from: package-private */
    public void load() {
        String url = getUrl();
        if (url != null) {
            RunProxy runProxy = new RunProxy(this.debugGui, 2);
            runProxy.fileName = url;
            runProxy.text = this.sourceInfo.source();
            new Thread(runProxy).start();
        }
    }

    public int getPosition(int i) {
        try {
            return this.textArea.getLineStartOffset(i);
        } catch (BadLocationException unused) {
            return -1;
        }
    }

    public boolean isBreakPoint(int i) {
        return this.sourceInfo.breakableLine(i) && this.sourceInfo.breakpoint(i);
    }

    public void toggleBreakPoint(int i) {
        if (!isBreakPoint(i)) {
            setBreakPoint(i);
        } else {
            clearBreakPoint(i);
        }
    }

    public void setBreakPoint(int i) {
        if (this.sourceInfo.breakableLine(i) && this.sourceInfo.breakpoint(i, true)) {
            this.fileHeader.repaint();
        }
    }

    public void clearBreakPoint(int i) {
        if (this.sourceInfo.breakableLine(i) && this.sourceInfo.breakpoint(i, false)) {
            this.fileHeader.repaint();
        }
    }

    public FileWindow(SwingGui swingGui, Dim.SourceInfo sourceInfo2) {
        super(SwingGui.getShortName(sourceInfo2.url()), true, true, true, true);
        this.debugGui = swingGui;
        this.sourceInfo = sourceInfo2;
        updateToolTip();
        FileTextArea fileTextArea = new FileTextArea(this);
        this.textArea = fileTextArea;
        fileTextArea.setRows(24);
        this.textArea.setColumns(80);
        this.p = new JScrollPane();
        this.fileHeader = new FileHeader(this);
        this.p.setViewportView(this.textArea);
        this.p.setRowHeaderView(this.fileHeader);
        setContentPane(this.p);
        pack();
        updateText(sourceInfo2);
        this.textArea.select(0);
    }

    private void updateToolTip() {
        int i = 1;
        int componentCount = getComponentCount() - 1;
        if (componentCount <= 1) {
            if (componentCount >= 0) {
                i = componentCount;
            } else {
                return;
            }
        }
        JComponent component = getComponent(i);
        if (component != null && (component instanceof JComponent)) {
            component.setToolTipText(getUrl());
        }
    }

    public String getUrl() {
        return this.sourceInfo.url();
    }

    public void updateText(Dim.SourceInfo sourceInfo2) {
        this.sourceInfo = sourceInfo2;
        String source = sourceInfo2.source();
        if (!this.textArea.getText().equals(source)) {
            this.textArea.setText(source);
            int i = 0;
            int i2 = this.currentPos;
            if (i2 != -1) {
                i = i2;
            }
            this.textArea.select(i);
        }
        this.fileHeader.update();
        this.fileHeader.repaint();
    }

    public void setPosition(int i) {
        this.textArea.select(i);
        this.currentPos = i;
        this.fileHeader.repaint();
    }

    public void select(int i, int i2) {
        int length = this.textArea.getDocument().getLength();
        this.textArea.select(length, length);
        this.textArea.select(i, i2);
    }

    public void dispose() {
        this.debugGui.removeWindow(this);
        FileWindow.super.dispose();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (!actionCommand.equals("Cut")) {
            if (actionCommand.equals("Copy")) {
                this.textArea.copy();
            } else {
                actionCommand.equals("Paste");
            }
        }
    }
}
