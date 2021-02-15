package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import android.view.View;

public class AnnotMenuManager {
    private static AnnotMenuManager mAnnotMenuManager;
    private DeleteMenu mDeleteMenu;
    private EditMenu mEditMenu;

    public static AnnotMenuManager get() {
        return mAnnotMenuManager;
    }

    public static void init(Context context) {
        mAnnotMenuManager = new AnnotMenuManager(context);
    }

    private AnnotMenuManager(Context context) {
        this.mDeleteMenu = new DeleteMenu(context);
        this.mEditMenu = new EditMenu(context);
    }

    public DeleteMenu doDeleteMenu(View view, int i, int i2) {
        this.mDeleteMenu.show(view, i, i2);
        return this.mDeleteMenu;
    }

    public EditMenu doEditMenu(View view, int i, int i2) {
        this.mEditMenu.show(view, i, i2);
        return this.mEditMenu;
    }
}
