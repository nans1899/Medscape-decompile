package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.PointF;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotNote;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;

public class AnnotToolNote extends BaseAnnotTool implements AnnotNote.AnnotNoteListner {
    private AnnotNote mAnnot;
    private Context mContext;

    public void enter() {
    }

    public void exit() {
    }

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
    }

    public AnnotToolNote(Context context) {
        super(context);
        this.mContext = context;
    }

    public void onAddedContents(PointF pointF, String str, String str2, boolean z) {
        if (z) {
            this.mPageView.updateNoteAnnot(this.mAnnot.getObjID(), str, str2, pointF);
        } else {
            this.mPageView.addNoteAnnot(pointF, str, str2);
        }
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        AnnotNote annotNote = (AnnotNote) AnnotFactory.instance().createAnnot(this.mContext, "NOTE");
        this.mAnnot = annotNote;
        annotNote.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setListener(this);
        this.mAnnot.setPos(getCorrectPos(i, i2));
        if (annotToolEventListener == null) {
            this.mAnnot.showContentsBox(this.mContext);
        } else if (!annotToolEventListener.onShowContentsBox(this.mAnnot)) {
            this.mAnnot.showContentsBox(this.mContext);
        }
        return this.mAnnot;
    }
}
