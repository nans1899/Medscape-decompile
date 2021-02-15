package com.google.firebase.inappmessaging.display.internal.layout.util;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class BackButtonHandler {
    private View.OnClickListener listener;
    private ViewGroup viewGroup;

    public BackButtonHandler(ViewGroup viewGroup2, View.OnClickListener onClickListener) {
        this.viewGroup = viewGroup2;
        this.listener = onClickListener;
    }

    public Boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent == null || keyEvent.getKeyCode() != 4 || keyEvent.getAction() != 1) {
            return null;
        }
        View.OnClickListener onClickListener = this.listener;
        if (onClickListener == null) {
            return false;
        }
        onClickListener.onClick(this.viewGroup);
        return true;
    }
}
