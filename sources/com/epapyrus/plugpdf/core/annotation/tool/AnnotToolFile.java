package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotFile;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.wbmd.wbmddrugscommons.constants.Constants;

public class AnnotToolFile extends BaseAnnotTool implements AnnotFile.AnnotFileListner {
    private AnnotFile mAnnot;
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

    public AnnotToolFile(Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        AnnotFile annotFile = (AnnotFile) AnnotFactory.instance().createAnnot(this.mContext, "FILE_ATTACHMENT");
        this.mAnnot = annotFile;
        annotFile.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setPos(getCorrectPos(i, i2));
        this.mAnnot.setListener(this);
        this.mAnnot.showContentsBox(this.mContext);
        return this.mAnnot;
    }

    public void onAddedContents(PointF pointF, String str, boolean z) {
        if (z) {
            this.mPageView.updateFileAnnot(this.mAnnot.getObjID(), str);
            return;
        }
        this.mPageView.addFileAnnot(pointF, str);
        Log.i(Constants.WBMDDrugKeyTag, "ANDDFILEANNOT");
    }
}
