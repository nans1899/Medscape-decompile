package org.mozilla.javascript.tools.debugger.treetable;

import java.util.EventObject;
import javax.swing.CellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

public class AbstractCellEditor implements CellEditor {
    protected EventListenerList listenerList = new EventListenerList();

    public void cancelCellEditing() {
    }

    public Object getCellEditorValue() {
        return null;
    }

    public boolean isCellEditable(EventObject eventObject) {
        return true;
    }

    public boolean shouldSelectCell(EventObject eventObject) {
        return false;
    }

    public boolean stopCellEditing() {
        return true;
    }

    public void addCellEditorListener(CellEditorListener cellEditorListener) {
        this.listenerList.add(CellEditorListener.class, cellEditorListener);
    }

    public void removeCellEditorListener(CellEditorListener cellEditorListener) {
        this.listenerList.remove(CellEditorListener.class, cellEditorListener);
    }

    /* access modifiers changed from: protected */
    public void fireEditingStopped() {
        Object[] listenerList2 = this.listenerList.getListenerList();
        for (int length = listenerList2.length - 2; length >= 0; length -= 2) {
            if (listenerList2[length] == CellEditorListener.class) {
                ((CellEditorListener) listenerList2[length + 1]).editingStopped(new ChangeEvent(this));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void fireEditingCanceled() {
        Object[] listenerList2 = this.listenerList.getListenerList();
        for (int length = listenerList2.length - 2; length >= 0; length -= 2) {
            if (listenerList2[length] == CellEditorListener.class) {
                ((CellEditorListener) listenerList2[length + 1]).editingCanceled(new ChangeEvent(this));
            }
        }
    }
}
