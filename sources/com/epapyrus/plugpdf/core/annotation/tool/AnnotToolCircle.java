package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import com.epapyrus.plugpdf.core.annotation.AnnotCircle;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;

public class AnnotToolCircle extends BaseAnnotTool {
    private AnnotCircle mAnnot;
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

    public AnnotToolCircle(Context context) {
        super(context);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        int lineColor = AnnotSetting.instance().getLineColor(BaseAnnotTool.AnnotToolType.CIRCLE);
        int fillColor = AnnotSetting.instance().getFillColor(BaseAnnotTool.AnnotToolType.CIRCLE);
        int opacity = AnnotSetting.instance().getOpacity(BaseAnnotTool.AnnotToolType.CIRCLE);
        int lineWidth = AnnotSetting.instance().getLineWidth(BaseAnnotTool.AnnotToolType.CIRCLE);
        boolean isInnerTransparent = AnnotSetting.instance().isInnerTransparent(BaseAnnotTool.AnnotToolType.CIRCLE);
        this.mStartPt = getCorrectPos(i, i2);
        AnnotCircle annotCircle = (AnnotCircle) AnnotFactory.instance().createAnnot(this.mContext, "CIRCLE");
        this.mAnnot = annotCircle;
        annotCircle.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setLineWidth(lineWidth);
        this.mAnnot.setARGB(opacity, Color.red(lineColor), Color.green(lineColor), Color.blue(lineColor));
        this.mAnnot.setInteriorARGB(opacity, Color.red(fillColor), Color.green(fillColor), Color.blue(fillColor));
        this.mAnnot.setScale(this.mAnnotScale);
        this.mAnnot.setInnerTransparent(isInnerTransparent);
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
        this.mAnnot.setOvalRect(this.mDragArea);
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
        this.mPageView.removeAnnotFromPageView(this.mAnnot.getObjID());
        this.mPageView.addCircleAnnot(this.mDragArea, this.mAnnot.getARGB(), this.mAnnot.getInteriorARGB(), this.mAnnot.isInnerTransparent(), this.mAnnot.getLineWidth());
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }
}
