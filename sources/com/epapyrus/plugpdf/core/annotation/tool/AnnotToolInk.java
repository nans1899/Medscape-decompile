package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotInk;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import java.util.ArrayList;
import java.util.Iterator;

public class AnnotToolInk extends BaseAnnotTool {
    private AnnotInk mAnnot;
    private Context mContext;
    private boolean mDrag;
    private boolean mHorizontal;
    private PointF mStartPt;

    public void enter() {
    }

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    public AnnotToolInk(Context context) {
        super(context);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        showSelectedAnnotBBox(false);
        this.mAnnotScale = this.mPageView.getAnnotScale();
        int lineColor = AnnotSetting.instance().getLineColor(BaseAnnotTool.AnnotToolType.INK);
        int opacity = AnnotSetting.instance().getOpacity(BaseAnnotTool.AnnotToolType.INK);
        int lineWidth = AnnotSetting.instance().getLineWidth(BaseAnnotTool.AnnotToolType.INK);
        AnnotInk annotInk = (AnnotInk) AnnotFactory.instance().createAnnot(this.mContext, "INK");
        this.mAnnot = annotInk;
        annotInk.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setLineWidth(lineWidth);
        this.mAnnot.setScale(this.mAnnotScale);
        this.mAnnot.setARGB(opacity, Color.red(lineColor), Color.green(lineColor), Color.blue(lineColor));
        this.mPageView.addAnnot(this.mAnnot);
        PointF correctPos = getCorrectPos(i, i2);
        this.mStartPt = correctPos;
        this.mAnnot.addPoint(correctPos);
        this.mDrag = false;
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        PointF correctPos = getCorrectPos(i, i2);
        if (!this.mDrag) {
            boolean z = true;
            this.mDrag = true;
            if (AnnotSetting.instance().getInkLineStraight()) {
                if (Math.abs(this.mStartPt.x - correctPos.x) <= Math.abs(this.mStartPt.y - correctPos.y)) {
                    z = false;
                }
                this.mHorizontal = z;
            }
        }
        if (AnnotSetting.instance().getInkLineStraight()) {
            if (this.mHorizontal) {
                correctPos.y = this.mStartPt.y;
            } else {
                correctPos.x = this.mStartPt.x;
            }
        }
        this.mAnnot.addPoint(correctPos);
        this.mAnnot.invalidate();
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mDrag = false;
        this.mAnnot.invalidate();
        this.mPageView.removeAnnotFromPageView(this.mAnnot.getObjID());
        injectAnnotToPage();
    }

    private void injectAnnotToPage() {
        Iterator<ArrayList<PointF>> it = this.mAnnot.getPointContainer().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().size() < 2) {
                this.mAnnot.getPointContainer().remove(i);
                i--;
            }
            i++;
        }
        if (this.mAnnot.getPointContainer().size() > 0) {
            this.mPageView.addInkAnnot(this.mAnnot.getARGB(), this.mAnnot.getLineWidth(), this.mAnnot.getPointContainer());
        }
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }
}
