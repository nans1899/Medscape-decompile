package org.mozilla.javascript.tools.debugger;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/* compiled from: SwingGui */
class FileTextArea extends JTextArea implements ActionListener, PopupMenuListener, KeyListener, MouseListener {
    private static final long serialVersionUID = -25032065448563720L;
    private FilePopupMenu popup;
    private FileWindow w;

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
    }

    public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
    }

    public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
    }

    public FileTextArea(FileWindow fileWindow) {
        this.w = fileWindow;
        FilePopupMenu filePopupMenu = new FilePopupMenu(this);
        this.popup = filePopupMenu;
        filePopupMenu.addPopupMenuListener(this);
        addMouseListener(this);
        addKeyListener(this);
        setFont(new Font("Monospaced", 0, 12));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:5|6|7|(1:9)|10|11|(2:13|19)(2:14|21)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0030 A[Catch:{ BadLocationException -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034 A[Catch:{ BadLocationException -> 0x0047 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void select(int r5) {
        /*
            r4 = this;
            if (r5 < 0) goto L_0x004a
            int r0 = r4.getLineOfOffset(r5)     // Catch:{ BadLocationException -> 0x0047 }
            java.awt.Rectangle r1 = r4.modelToView(r5)     // Catch:{ BadLocationException -> 0x0047 }
            if (r1 != 0) goto L_0x0010
            r4.select(r5, r5)     // Catch:{ BadLocationException -> 0x0047 }
            goto L_0x004a
        L_0x0010:
            int r0 = r0 + 1
            int r0 = r4.getLineStartOffset(r0)     // Catch:{ Exception -> 0x001d }
            java.awt.Rectangle r0 = r4.modelToView(r0)     // Catch:{ Exception -> 0x001d }
            if (r0 == 0) goto L_0x001d
            r1 = r0
        L_0x001d:
            java.awt.Container r0 = r4.getParent()     // Catch:{ BadLocationException -> 0x0047 }
            javax.swing.JViewport r0 = (javax.swing.JViewport) r0     // Catch:{ BadLocationException -> 0x0047 }
            java.awt.Rectangle r0 = r0.getViewRect()     // Catch:{ BadLocationException -> 0x0047 }
            int r2 = r0.y     // Catch:{ BadLocationException -> 0x0047 }
            int r3 = r0.height     // Catch:{ BadLocationException -> 0x0047 }
            int r2 = r2 + r3
            int r3 = r1.y     // Catch:{ BadLocationException -> 0x0047 }
            if (r2 <= r3) goto L_0x0034
            r4.select(r5, r5)     // Catch:{ BadLocationException -> 0x0047 }
            goto L_0x004a
        L_0x0034:
            int r2 = r1.y     // Catch:{ BadLocationException -> 0x0047 }
            int r0 = r0.height     // Catch:{ BadLocationException -> 0x0047 }
            int r3 = r1.height     // Catch:{ BadLocationException -> 0x0047 }
            int r0 = r0 - r3
            int r0 = r0 / 2
            int r2 = r2 + r0
            r1.y = r2     // Catch:{ BadLocationException -> 0x0047 }
            r4.scrollRectToVisible(r1)     // Catch:{ BadLocationException -> 0x0047 }
            r4.select(r5, r5)     // Catch:{ BadLocationException -> 0x0047 }
            goto L_0x004a
        L_0x0047:
            r4.select(r5, r5)
        L_0x004a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.debugger.FileTextArea.select(int):void");
    }

    private void checkPopup(MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.popup.show(this, mouseEvent.getX(), mouseEvent.getY());
        }
    }

    public void mousePressed(MouseEvent mouseEvent) {
        checkPopup(mouseEvent);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        checkPopup(mouseEvent);
        requestFocus();
        getCaret().setVisible(true);
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        checkPopup(mouseEvent);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        int i;
        int viewToModel = viewToModel(new Point(this.popup.x, this.popup.y));
        this.popup.setVisible(false);
        String actionCommand = actionEvent.getActionCommand();
        try {
            i = getLineOfOffset(viewToModel);
        } catch (Exception unused) {
            i = -1;
        }
        if (actionCommand.equals("Set Breakpoint")) {
            this.w.setBreakPoint(i + 1);
        } else if (actionCommand.equals("Clear Breakpoint")) {
            this.w.clearBreakPoint(i + 1);
        } else if (actionCommand.equals("Run")) {
            this.w.load();
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 127) {
            switch (keyCode) {
                case 8:
                case 9:
                case 10:
                    break;
                default:
                    return;
            }
        }
        keyEvent.consume();
    }

    public void keyTyped(KeyEvent keyEvent) {
        keyEvent.consume();
    }

    public void keyReleased(KeyEvent keyEvent) {
        keyEvent.consume();
    }
}
