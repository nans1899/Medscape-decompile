package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import com.epapyrus.plugpdf.core.annotation.AnnotFile;
import com.epapyrus.plugpdf.core.annotation.AnnotInk;
import com.epapyrus.plugpdf.core.annotation.AnnotLink;
import com.epapyrus.plugpdf.core.annotation.AnnotNote;
import com.epapyrus.plugpdf.core.annotation.AnnotRichMedia;
import com.epapyrus.plugpdf.core.annotation.AnnotSound;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.menu.AnnotMenuManager;
import com.epapyrus.plugpdf.core.annotation.menu.BaseMenu;
import com.epapyrus.plugpdf.core.annotation.menu.item.BaseMenuItem;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class AnnotToolNav extends BaseAnnotTool implements AnnotNote.AnnotNoteListner, AnnotFile.AnnotFileListner, BaseMenu.MenuSelectListener {
    private BasePlugPDFDisplay mDisplay;

    public void enter() {
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
    }

    public AnnotToolNav(Context context, BasePlugPDFDisplay basePlugPDFDisplay) {
        super(context);
        this.mDisplay = basePlugPDFDisplay;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        showSelectedAnnotBBox(false);
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        BaseAnnot selectedAnnot = getSelectedAnnot(new Point(i, i2));
        if (selectedAnnot == null) {
            return null;
        }
        if (selectedAnnot instanceof AnnotNote) {
            showSelectedAnnotBBox(true);
            AnnotNote annotNote = (AnnotNote) selectedAnnot;
            annotNote.setListener(this);
            if (annotToolEventListener == null) {
                annotNote.showContentsBox(this.mContext);
            } else if (!annotToolEventListener.onShowContentsBox(annotNote)) {
                annotNote.showContentsBox(this.mContext);
            }
        }
        if (selectedAnnot instanceof AnnotLink) {
            ((AnnotLink) selectedAnnot).runLink(this.mDisplay);
        }
        if (selectedAnnot instanceof AnnotFile) {
            showSelectedAnnotBBox(true);
            AnnotFile annotFile = (AnnotFile) selectedAnnot;
            annotFile.setListener(this);
            annotFile.showContentsBox(this.mContext);
        }
        if (selectedAnnot instanceof AnnotSound) {
            ((AnnotSound) selectedAnnot).playSound(this.mPageView.getDocument());
        }
        if (selectedAnnot instanceof AnnotRichMedia) {
            ((AnnotRichMedia) selectedAnnot).playMovie(this.mPageView.getDocument());
        }
        return selectedAnnot;
    }

    public BaseAnnot longPress(int i, int i2) {
        boolean z = false;
        showSelectedAnnotBBox(false);
        BaseAnnot selectedAnnot = getSelectedAnnot(new Point(i, i2));
        if (selectedAnnot == null) {
            return null;
        }
        if (selectedAnnot instanceof AnnotInk) {
            showSelectedAnnotBBox(true);
            if (this.annotEventListener != null) {
                z = this.annotEventListener.onDoEditMenu(selectedAnnot, i, i2);
            }
            if (z) {
                AnnotMenuManager.get().doEditMenu(selectedAnnot, i, i2).setListener(this);
            }
        }
        return selectedAnnot;
    }

    public void onSelectItem(BaseMenuItem baseMenuItem) {
        baseMenuItem.execute(this.mPageView, this.mSelectedAnnot.getObjID());
    }

    public void onAddedContents(PointF pointF, String str, String str2, boolean z) {
        if (z) {
            this.mPageView.getDocument().updateNoteAnnot(this.mPageView.getPageIdx(), this.mSelectedAnnot.getObjID(), str, str2, pointF);
        }
    }

    public void onAddedContents(PointF pointF, String str, boolean z) {
        if (z) {
            this.mPageView.getDocument().updateFileAnnot(this.mPageView.getPageIdx(), this.mSelectedAnnot.getObjID(), str);
        }
    }
}
