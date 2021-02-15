package com.epapyrus.plugpdf.core.annotation.tool;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.comscore.streaming.AdvertisementType;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotSquare;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;

public class AnnotToolLink extends BaseAnnotTool {
    private Context mContext;
    /* access modifiers changed from: private */
    public RectF mDragArea;
    private AnnotSquare mLinkArea;
    private PointF mStartPt;

    public void enter() {
    }

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    public AnnotToolLink(Context context) {
        super(context);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        this.mStartPt = getCorrectPos(i, i2);
        AnnotSquare annotSquare = (AnnotSquare) AnnotFactory.instance().createAnnot(this.mContext, "SQUARE");
        this.mLinkArea = annotSquare;
        annotSquare.setARGB(255, 112, 146, 190);
        this.mLinkArea.setInteriorARGB(80, 153, 217, AdvertisementType.BRANDED_AS_CONTENT);
        this.mLinkArea.setScale(this.mAnnotScale);
        this.mLinkArea.setIsRubberband(true);
        this.mPageView.addAnnot(this.mLinkArea);
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
        this.mLinkArea.setBBox(rectF.left, this.mDragArea.top, this.mDragArea.right, this.mDragArea.bottom);
        this.mLinkArea.setSquare(this.mDragArea);
        this.mLinkArea.invalidate();
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
        createDialog(this.mPageView.getContext());
        this.mPageView.removeAnnotFromPageView(this.mLinkArea.getObjID());
    }

    /* access modifiers changed from: protected */
    public void createDialog(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(ResourceManager.getLayoutId(context, "annot_link"));
        dialog.setCancelable(true);
        ((Button) dialog.findViewById(ResourceManager.getId(context, "annot_link_cancel"))).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(ResourceManager.getId(context, "annot_link_add"))).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i;
                RadioGroup radioGroup = (RadioGroup) dialog.findViewById(ResourceManager.getId(context, "annot_link_type"));
                String obj = ((EditText) dialog.findViewById(ResourceManager.getId(context, "annot_link_target"))).getText().toString();
                if (radioGroup.getCheckedRadioButtonId() == ResourceManager.getId(context, "annot_link_type_page")) {
                    i = 1;
                } else if (radioGroup.getCheckedRadioButtonId() == ResourceManager.getId(context, "annot_link_type_uri")) {
                    i = 6;
                } else {
                    i = radioGroup.getCheckedRadioButtonId() == ResourceManager.getId(context, "annot_link_type_launch") ? 4 : 0;
                }
                AnnotToolLink.this.mPageView.addLinkAnnot(AnnotToolLink.this.mDragArea, i, obj);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }
}
