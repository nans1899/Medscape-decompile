package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotLine;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;

public class AnnotToolLine extends BaseAnnotTool {
    private AnnotLine mAnnot;
    private Context mContext;
    private PointF mEndPt;
    private PointF mStartPt;

    public void enter() {
    }

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    public AnnotToolLine(Context context) {
        super(context);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        int lineColor = AnnotSetting.instance().getLineColor(BaseAnnotTool.AnnotToolType.LINE);
        int opacity = AnnotSetting.instance().getOpacity(BaseAnnotTool.AnnotToolType.LINE);
        int lineWidth = AnnotSetting.instance().getLineWidth(BaseAnnotTool.AnnotToolType.LINE);
        this.mStartPt = getCorrectPos(i, i2);
        AnnotLine annotLine = (AnnotLine) AnnotFactory.instance().createAnnot(this.mContext, "LINE");
        this.mAnnot = annotLine;
        annotLine.setStartPoint(this.mStartPt);
        this.mAnnot.setEndPoint(this.mStartPt);
        this.mAnnot.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setLineWidth(lineWidth);
        this.mAnnot.setARGB(opacity, Color.red(lineColor), Color.green(lineColor), Color.blue(lineColor));
        this.mAnnot.setScale(this.mAnnotScale);
        this.mPageView.addAnnot(this.mAnnot);
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnot.setEndPoint(getCorrectPos(i, i2));
        this.mAnnot.invalidate();
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        PointF correctPos = getCorrectPos(i, i2);
        this.mEndPt = correctPos;
        this.mAnnot.setEndPoint(correctPos);
        this.mPageView.removeAnnotFromPageView(this.mAnnot.getObjID());
        injectAnnotToPage();
    }

    private void injectAnnotToPage() {
        if (this.mStartPt != this.mEndPt) {
            this.mPageView.addLineAnnot(this.mStartPt, this.mEndPt, this.mAnnot.getLineWidth(), this.mAnnot.getARGB());
        }
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }
}
