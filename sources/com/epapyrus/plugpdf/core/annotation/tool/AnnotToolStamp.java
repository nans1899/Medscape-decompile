package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotStamp;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;

public class AnnotToolStamp extends BaseAnnotTool {
    private AnnotStamp mAnnot;
    private Context mContext;
    private RectF mDragArea;
    private Bitmap mStampBitamp;
    private PointF mStartPt;

    public void enter() {
    }

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    public AnnotToolStamp(Context context) {
        super(context);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        this.mStartPt = getCorrectPos(i, i2);
        this.mStampBitamp = AnnotSetting.instance().getStampImage();
        AnnotStamp annotStamp = (AnnotStamp) AnnotFactory.instance().createAnnot(this.mContext, "STAMP");
        this.mAnnot = annotStamp;
        annotStamp.setApBitmap(this.mStampBitamp);
        this.mAnnot.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setOpacity(AnnotSetting.instance().getOpacity(BaseAnnotTool.AnnotToolType.STAMP));
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
        this.mPageView.addStampAnnot(this.mDragArea, this.mAnnot.getOpacity(), this.mStampBitamp);
        this.mPageView.removeAnnotFromPageView(this.mAnnot.getObjID());
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }
}
