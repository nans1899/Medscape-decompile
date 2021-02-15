package org.mozilla.javascript.tools.debugger;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Segment;
import org.apache.commons.io.IOUtils;

/* compiled from: SwingGui */
class EvalTextArea extends JTextArea implements KeyListener, DocumentListener {
    private static final long serialVersionUID = -3918033649601064194L;
    private SwingGui debugGui;
    private List<String> history;
    private int historyIndex = -1;
    private int outputMark;

    public EvalTextArea(SwingGui swingGui) {
        this.debugGui = swingGui;
        this.history = Collections.synchronizedList(new ArrayList());
        Document document = getDocument();
        document.addDocumentListener(this);
        addKeyListener(this);
        setLineWrap(true);
        setFont(new Font("Monospaced", 0, 12));
        append("% ");
        this.outputMark = document.getLength();
    }

    public void select(int i, int i2) {
        EvalTextArea.super.select(i, i2);
    }

    private synchronized void returnPressed() {
        Document document = getDocument();
        int length = document.getLength();
        Segment segment = new Segment();
        try {
            document.getText(this.outputMark, length - this.outputMark, segment);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        String segment2 = segment.toString();
        if (this.debugGui.dim.stringIsCompilableUnit(segment2)) {
            if (segment2.trim().length() > 0) {
                this.history.add(segment2);
                this.historyIndex = this.history.size();
            }
            append(IOUtils.LINE_SEPARATOR_UNIX);
            String eval = this.debugGui.dim.eval(segment2);
            if (eval.length() > 0) {
                append(eval);
                append(IOUtils.LINE_SEPARATOR_UNIX);
            }
            append("% ");
            this.outputMark = document.getLength();
        } else {
            append(IOUtils.LINE_SEPARATOR_UNIX);
        }
    }

    public synchronized void write(String str) {
        insert(str, this.outputMark);
        int length = this.outputMark + str.length();
        this.outputMark = length;
        select(length, length);
    }

    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 8 || keyCode == 37) {
            if (this.outputMark == getCaretPosition()) {
                keyEvent.consume();
            }
        } else if (keyCode == 36) {
            int caretPosition = getCaretPosition();
            int i = this.outputMark;
            if (caretPosition == i) {
                keyEvent.consume();
            } else if (caretPosition > i && !keyEvent.isControlDown()) {
                if (keyEvent.isShiftDown()) {
                    moveCaretPosition(this.outputMark);
                } else {
                    setCaretPosition(this.outputMark);
                }
                keyEvent.consume();
            }
        } else if (keyCode == 10) {
            returnPressed();
            keyEvent.consume();
        } else if (keyCode == 38) {
            int i2 = this.historyIndex - 1;
            this.historyIndex = i2;
            if (i2 >= 0) {
                if (i2 >= this.history.size()) {
                    this.historyIndex = this.history.size() - 1;
                }
                int i3 = this.historyIndex;
                if (i3 >= 0) {
                    String str = this.history.get(i3);
                    replaceRange(str, this.outputMark, getDocument().getLength());
                    int length = this.outputMark + str.length();
                    select(length, length);
                } else {
                    this.historyIndex = i3 + 1;
                }
            } else {
                this.historyIndex = i2 + 1;
            }
            keyEvent.consume();
        } else if (keyCode == 40) {
            int i4 = this.outputMark;
            if (this.history.size() > 0) {
                int i5 = this.historyIndex + 1;
                this.historyIndex = i5;
                if (i5 < 0) {
                    this.historyIndex = 0;
                }
                int length2 = getDocument().getLength();
                if (this.historyIndex < this.history.size()) {
                    String str2 = this.history.get(this.historyIndex);
                    replaceRange(str2, this.outputMark, length2);
                    i4 = str2.length() + this.outputMark;
                } else {
                    this.historyIndex = this.history.size();
                    replaceRange("", this.outputMark, length2);
                }
            }
            select(i4, i4);
            keyEvent.consume();
        }
    }

    public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() != 8) {
            int caretPosition = getCaretPosition();
            int i = this.outputMark;
            if (caretPosition < i) {
                setCaretPosition(i);
            }
        } else if (this.outputMark == getCaretPosition()) {
            keyEvent.consume();
        }
    }

    public synchronized void keyReleased(KeyEvent keyEvent) {
    }

    public synchronized void insertUpdate(DocumentEvent documentEvent) {
        int length = documentEvent.getLength();
        if (this.outputMark > documentEvent.getOffset()) {
            this.outputMark += length;
        }
    }

    public synchronized void removeUpdate(DocumentEvent documentEvent) {
        int length = documentEvent.getLength();
        int offset = documentEvent.getOffset();
        if (this.outputMark > offset) {
            if (this.outputMark >= offset + length) {
                this.outputMark -= length;
            } else {
                this.outputMark = offset;
            }
        }
    }

    public synchronized void postUpdateUI() {
        setCaret(getCaret());
        select(this.outputMark, this.outputMark);
    }

    public synchronized void changedUpdate(DocumentEvent documentEvent) {
    }
}
