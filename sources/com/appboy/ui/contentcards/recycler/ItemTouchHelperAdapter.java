package com.appboy.ui.contentcards.recycler;

public interface ItemTouchHelperAdapter {
    boolean isItemDismissable(int i);

    void onItemDismiss(int i);
}
