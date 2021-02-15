package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotSquare;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;

public class AnnotToolSquare extends BaseAnnotTool {
    private AnnotSquare mAnnot;
    private Context mContext;
    private RectF mDragArea;
    private PointF mStartPt;

    public void enter() {
    }

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    public AnnotToolSquare(Context context) {
        super(context);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        int lineColor = AnnotSetting.instance().getLineColor(BaseAnnotTool.AnnotToolType.SQUARE);
        int fillColor = AnnotSetting.instance().getFillColor(BaseAnnotTool.AnnotToolType.SQUARE);
        int opacity = AnnotSetting.instance().getOpacity(BaseAnnotTool.AnnotToolType.SQUARE);
        int lineWidth = AnnotSetting.instance().getLineWidth(BaseAnnotTool.AnnotToolType.SQUARE);
        boolean isInnerTransparent = AnnotSetting.instance().isInnerTransparent(BaseAnnotTool.AnnotToolType.SQUARE);
        this.mStartPt = getCorrectPos(i, i2);
        AnnotSquare annotSquare = (AnnotSquare) AnnotFactory.instance().createAnnot(this.mContext, "SQUARE");
        this.mAnnot = annotSquare;
        annotSquare.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setLineWidth(lineWidth);
        this.mAnnot.setARGB(opacity, Color.red(lineColor), Color.green(lineColor), Color.blue(lineColor));
        this.mAnnot.setInteriorARGB(opacity, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor));
        this.mAnnot.setScale(this.mAnnotScale);
        this.mAnnot.setInnerTransparent(isInnerTransparent);
        this.mAnnot.setIsRubberband(true);
        this.mPageView.addAnnot(this.mAnnot);
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        PointF correctPos = getCorrectPos(i, i2);
        this.mDragArea = null;
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        if (this.mStartPt.x < correctPos.x) {
            pointF.x = this.mStartPt.x;
            pointF2.x = correctPos.x;
        } else {
            pointF.x = correctPos.x;
            pointF2.x = this.mStartPt.x;
        }
        if (this.mStartPt.y < correctPos.y) {
            pointF.y = this.mStartPt.y;
            pointF2.y = correctPos.y;
        } else {
            pointF.y = correctPos.y;
            pointF2.y = this.mStartPt.y;
        }
        RectF rectF = new RectF(pointF.x, pointF.y, pointF2.x, pointF2.y);
        this.mDragArea = rectF;
        this.mAnnot.setBBox(rectF.left, this.mDragArea.top, this.mDragArea.right, this.mDragArea.bottom);
        this.mAnnot.setSquare(this.mDragArea);
        this.mAnnot.invalidate();
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        PointF correctPos = getCorrectPos(i, i2);
        this.mDragArea = null;
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        if (this.mStartPt.x < correctPos.x) {
            pointF.x = this.mStartPt.x;
            pointF2.x = correctPos.x;
        } else {
            pointF.x = correctPos.x;
            pointF2.x = this.mStartPt.x;
        }
        if (this.mStartPt.y > correctPos.y) {
            pointF.y = this.mStartPt.y;
            pointF2.y = correctPos.y;
        } else {
            pointF.y = correctPos.y;
            pointF2.y = this.mStartPt.y;
        }
        this.mDragArea = new RectF(pointF.x, pointF.y, pointF2.x, pointF2.y);
        this.mPageView.addSquareAnnot(this.mDragArea, this.mAnnot.getARGB(), this.mAnnot.getInteriorARGB(), this.mAnnot.isInnerTransparent(), this.mAnnot.getLineWidth());
        this.mPageView.removeAnnotFromPageView(this.mAnnot.getObjID());
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }
}
