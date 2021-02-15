package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.menu.AnnotMenuManager;
import com.epapyrus.plugpdf.core.annotation.menu.BaseMenu;
import com.epapyrus.plugpdf.core.annotation.menu.item.BaseMenuItem;

public class AnnotToolEraser extends BaseAnnotTool implements BaseMenu.MenuSelectListener {
    private static Paint mSelectedItemPaint;

    public void enter() {
    }

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    public AnnotToolEraser(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        if (annotToolEventListener != null) {
            annotToolEventListener.onTouchBegin(i, i2, this);
        }
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        if (annotToolEventListener != null) {
            annotToolEventListener.onTouchMove(i, i2, this);
        }
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        if (annotToolEventListener != null) {
            annotToolEventListener.onTouchEnd(i, i2, this);
        }
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        showSelectedAnnotBBox(false);
        BaseAnnot selectedAnnot = getSelectedAnnot(new Point(i, i2));
        if (PropertyManager.getEnableConfirmErase()) {
            showSelectedAnnotBBox(false);
            if (selectedAnnot != null) {
                showSelectedAnnotBBox(true);
                AnnotMenuManager.get().doDeleteMenu(this.mPageView, i, i2).setListener(this);
            }
        } else if (selectedAnnot != null) {
            this.mPageView.removeAnnotFromPDF(selectedAnnot.getObjID());
        }
        return selectedAnnot;
    }

    public void onSelectItem(BaseMenuItem baseMenuItem) {
        baseMenuItem.execute(this.mPageView, this.mSelectedAnnot.getObjID());
    }

    public static Paint getSelectedItemPaint() {
        return mSelectedItemPaint;
    }

    public static void setSelectedItemPaint(Paint paint) {
        mSelectedItemPaint = paint;
    }
}
