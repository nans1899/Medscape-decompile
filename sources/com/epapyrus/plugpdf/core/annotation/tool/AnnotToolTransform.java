package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.epapyrus.plugpdf.core.annotation.AnnotCircle;
import com.epapyrus.plugpdf.core.annotation.AnnotFile;
import com.epapyrus.plugpdf.core.annotation.AnnotFreeText;
import com.epapyrus.plugpdf.core.annotation.AnnotInk;
import com.epapyrus.plugpdf.core.annotation.AnnotLine;
import com.epapyrus.plugpdf.core.annotation.AnnotLink;
import com.epapyrus.plugpdf.core.annotation.AnnotNote;
import com.epapyrus.plugpdf.core.annotation.AnnotRichMedia;
import com.epapyrus.plugpdf.core.annotation.AnnotSound;
import com.epapyrus.plugpdf.core.annotation.AnnotSquare;
import com.epapyrus.plugpdf.core.annotation.AnnotStamp;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.menu.AnnotMenuManager;
import com.epapyrus.plugpdf.core.annotation.menu.BaseMenu;
import com.epapyrus.plugpdf.core.annotation.menu.item.BaseMenuItem;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import java.util.ArrayList;

public class AnnotToolTransform extends BaseAnnotTool implements AnnotNote.AnnotNoteListner, AnnotFile.AnnotFileListner, BaseMenu.MenuSelectListener {
    /* access modifiers changed from: private */
    public PointF beginPoint;
    /* access modifiers changed from: private */
    public boolean isMovable = false;
    /* access modifiers changed from: private */
    public BasePlugPDFDisplay mDisplay;
    /* access modifiers changed from: private */
    public PinSelect mPaintView;

    public void enter() {
    }

    public AnnotToolTransform(Context context, BasePlugPDFDisplay basePlugPDFDisplay) {
        super(context);
        this.mDisplay = basePlugPDFDisplay;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, final AnnotToolEventListener annotToolEventListener) {
        this.mSelectedAnnot = getSelectedAnnot(new Point(i, i2));
        this.beginPoint = getCorrectPos(i, i2);
        if (this.mSelectedAnnot != null && ((this.mSelectedAnnot instanceof AnnotSquare) || (this.mSelectedAnnot instanceof AnnotCircle) || (this.mSelectedAnnot instanceof AnnotInk) || (this.mSelectedAnnot instanceof AnnotLine) || (this.mSelectedAnnot instanceof AnnotStamp) || (this.mSelectedAnnot instanceof AnnotFreeText))) {
            this.mAnnotScale = this.mPageView.getAnnotScale();
            this.mSelectedAnnot.setMovable(true);
            showSelectedAnnotBBox(true);
            RectF bBox = this.mSelectedAnnot.getBBox();
            if (this.mPaintView == null) {
                if (this.mSelectedAnnot instanceof AnnotLine) {
                    this.mPaintView = new PinSelect(this.mContext, ((AnnotLine) this.mSelectedAnnot).getStartPoint(), ((AnnotLine) this.mSelectedAnnot).getEndPoint());
                } else {
                    this.mPaintView = new PinSelect(this.mContext, bBox);
                }
                this.mPaintView.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        float x = motionEvent.getX() / AnnotToolTransform.this.mAnnotScale;
                        float y = motionEvent.getY() / AnnotToolTransform.this.mAnnotScale;
                        if (AnnotToolTransform.this.mSelectedAnnot != null) {
                            RectF bBox = AnnotToolTransform.this.mSelectedAnnot.getBBox();
                            RectF rectF = new RectF(bBox.left + ((float) AnnotToolTransform.this.mPaintView.getPIN_SIZE()), bBox.top + ((float) AnnotToolTransform.this.mPaintView.getPIN_SIZE()), bBox.right - ((float) AnnotToolTransform.this.mPaintView.getPIN_SIZE()), bBox.bottom - ((float) AnnotToolTransform.this.mPaintView.getPIN_SIZE()));
                            if (!AnnotToolTransform.this.mPaintView.isPressed()) {
                                if (rectF.contains(x, y) && motionEvent.getAction() == 0) {
                                    AnnotToolTransform annotToolTransform = AnnotToolTransform.this;
                                    PointF unused = annotToolTransform.beginPoint = annotToolTransform.getCorrectPos((int) motionEvent.getX(), (int) motionEvent.getY());
                                    AnnotToolTransform.this.setBeingEdited(true);
                                    boolean unused2 = AnnotToolTransform.this.isMovable = true;
                                } else if (!rectF.contains(x, y) || motionEvent.getAction() != 1) {
                                    AnnotToolTransform.this.onTouchMove((int) motionEvent.getX(), (int) motionEvent.getY(), annotToolEventListener);
                                } else {
                                    AnnotToolTransform.this.onTouchEnd((int) motionEvent.getX(), (int) motionEvent.getY(), annotToolEventListener);
                                    boolean unused3 = AnnotToolTransform.this.isMovable = false;
                                }
                                if (AnnotToolTransform.this.isMovable) {
                                    return false;
                                }
                            }
                        }
                        if (motionEvent.getAction() == 0) {
                            if (!AnnotToolTransform.this.mPaintView.isLineAnnot()) {
                                for (int i = 0; i < 8; i++) {
                                    RectF rectF2 = AnnotToolTransform.this.mPaintView.getBoundaryRects()[i];
                                    float pin_size = (float) AnnotToolTransform.this.mPaintView.getPIN_SIZE();
                                    if (new RectF(rectF2.left - pin_size, rectF2.top - pin_size, rectF2.right + pin_size, rectF2.bottom + pin_size).contains(motionEvent.getX(), motionEvent.getY())) {
                                        AnnotToolTransform.this.mPaintView.setPressed(i);
                                    }
                                }
                            } else {
                                PointF startPt = AnnotToolTransform.this.mPaintView.getStartPt();
                                PointF endPt = AnnotToolTransform.this.mPaintView.getEndPt();
                                float pin_size2 = (float) (AnnotToolTransform.this.mPaintView.getPIN_SIZE() * 2);
                                RectF rectF3 = new RectF(startPt.x - pin_size2, startPt.y - pin_size2, startPt.x + pin_size2, startPt.y + pin_size2);
                                RectF rectF4 = new RectF(endPt.x - pin_size2, endPt.y - pin_size2, endPt.x + pin_size2, endPt.y + pin_size2);
                                if (rectF3.contains(motionEvent.getX() / AnnotToolTransform.this.mAnnotScale, motionEvent.getY() / AnnotToolTransform.this.mAnnotScale)) {
                                    AnnotToolTransform.this.mPaintView.setPressedStartPt(true);
                                } else if (rectF4.contains(motionEvent.getX() / AnnotToolTransform.this.mAnnotScale, motionEvent.getY() / AnnotToolTransform.this.mAnnotScale)) {
                                    AnnotToolTransform.this.mPaintView.setPressedEndtPt(true);
                                }
                            }
                            AnnotToolTransform.this.mPaintView.invalidate();
                            if (!AnnotToolTransform.this.mPaintView.isPressed() && !AnnotToolTransform.this.mPaintView.getBoundaryBox().contains(x * AnnotToolTransform.this.mAnnotScale, y * AnnotToolTransform.this.mAnnotScale)) {
                                AnnotToolTransform.this.mPageView.removeView(AnnotToolTransform.this.mPaintView);
                                AnnotToolTransform.this.mDisplay.changeGesture(BaseGestureProcessor.GestureType.VIEW);
                                AnnotToolTransform.this.mDisplay.setAnnotationTool((BaseAnnotTool.AnnotToolType) null);
                            }
                        } else if (motionEvent.getAction() == 2) {
                            float pin_size3 = (float) AnnotToolTransform.this.mPaintView.getPIN_SIZE();
                            if (x < pin_size3) {
                                x = pin_size3;
                            }
                            if (x > (((float) AnnotToolTransform.this.mPageView.getWidth()) / AnnotToolTransform.this.mAnnotScale) - pin_size3) {
                                x = (((float) AnnotToolTransform.this.mPageView.getWidth()) / AnnotToolTransform.this.mAnnotScale) - pin_size3;
                            }
                            if (y < pin_size3) {
                                y = pin_size3;
                            }
                            if (y > (((float) AnnotToolTransform.this.mPageView.getHeight()) / AnnotToolTransform.this.mAnnotScale) - pin_size3) {
                                y = (((float) AnnotToolTransform.this.mPageView.getHeight()) / AnnotToolTransform.this.mAnnotScale) - pin_size3;
                            }
                            if (AnnotToolTransform.this.mSelectedAnnot instanceof AnnotLine) {
                                AnnotToolTransform.this.mPaintView.setMovePoint(new PointF(x / AnnotToolTransform.this.mAnnotScale, y / AnnotToolTransform.this.mAnnotScale));
                            } else {
                                AnnotToolTransform.this.mPaintView.setMovePoint(new PointF(x, y));
                            }
                            AnnotToolTransform.this.mPaintView.invalidate();
                            if (!AnnotToolTransform.this.mPaintView.isLineAnnot()) {
                                RectF boundaryBox = AnnotToolTransform.this.mPaintView.getBoundaryBox();
                                RectF rectF5 = new RectF(boundaryBox.left / AnnotToolTransform.this.mAnnotScale, boundaryBox.top / AnnotToolTransform.this.mAnnotScale, boundaryBox.right / AnnotToolTransform.this.mAnnotScale, boundaryBox.bottom / AnnotToolTransform.this.mAnnotScale);
                                AnnotToolTransform annotToolTransform2 = AnnotToolTransform.this;
                                annotToolTransform2.updateAnnot(annotToolTransform2.mSelectedAnnot, rectF5);
                            } else {
                                AnnotToolTransform annotToolTransform3 = AnnotToolTransform.this;
                                annotToolTransform3.updateLineAnnot(annotToolTransform3.mSelectedAnnot, new PointF(x / AnnotToolTransform.this.mAnnotScale, y / AnnotToolTransform.this.mAnnotScale));
                            }
                        } else if (motionEvent.getAction() == 1) {
                            if (AnnotToolTransform.this.mSelectedAnnot != null) {
                                RectF bBox2 = AnnotToolTransform.this.mSelectedAnnot.getBBox();
                                if (!AnnotToolTransform.this.mPaintView.isLineAnnot()) {
                                    if (bBox2.left > bBox2.right) {
                                        float f = bBox2.left;
                                        bBox2.left = bBox2.right;
                                        bBox2.right = f;
                                    }
                                    if (bBox2.top > bBox2.bottom) {
                                        float f2 = bBox2.top;
                                        bBox2.top = bBox2.bottom;
                                        bBox2.bottom = f2;
                                    }
                                    if (AnnotToolTransform.this.mSelectedAnnot instanceof AnnotInk) {
                                        if (AnnotToolTransform.this.mSelectedAnnot.getObjID() > 0) {
                                            AnnotToolTransform.this.mPageView.updateInkAnnot(((AnnotInk) AnnotToolTransform.this.mSelectedAnnot).getARGB(), ((AnnotInk) AnnotToolTransform.this.mSelectedAnnot).getLineWidth(), ((AnnotInk) AnnotToolTransform.this.mSelectedAnnot).getPointContainer(), AnnotToolTransform.this.mSelectedAnnot.getObjID());
                                        }
                                    } else if (AnnotToolTransform.this.mSelectedAnnot instanceof AnnotCircle) {
                                        if (AnnotToolTransform.this.mSelectedAnnot.getObjID() > 0) {
                                            AnnotToolTransform.this.mPageView.updateCircleAnnot(bBox2, ((AnnotCircle) AnnotToolTransform.this.mSelectedAnnot).getARGB(), ((AnnotCircle) AnnotToolTransform.this.mSelectedAnnot).getInteriorARGB(), ((AnnotCircle) AnnotToolTransform.this.mSelectedAnnot).isInnerTransparent(), ((AnnotCircle) AnnotToolTransform.this.mSelectedAnnot).getLineWidth(), AnnotToolTransform.this.mSelectedAnnot.getObjID());
                                        }
                                    } else if (AnnotToolTransform.this.mSelectedAnnot instanceof AnnotSquare) {
                                        if (AnnotToolTransform.this.mSelectedAnnot.getObjID() > 0) {
                                            AnnotToolTransform.this.mPageView.updateSquareAnnot(bBox2, ((AnnotSquare) AnnotToolTransform.this.mSelectedAnnot).getARGB(), ((AnnotSquare) AnnotToolTransform.this.mSelectedAnnot).getInteriorARGB(), ((AnnotSquare) AnnotToolTransform.this.mSelectedAnnot).isInnerTransparent(), ((AnnotSquare) AnnotToolTransform.this.mSelectedAnnot).getLineWidth(), AnnotToolTransform.this.mSelectedAnnot.getObjID());
                                        }
                                    } else if (AnnotToolTransform.this.mSelectedAnnot instanceof AnnotStamp) {
                                        if (AnnotToolTransform.this.mSelectedAnnot.getObjID() > 0) {
                                            AnnotToolTransform.this.mPageView.updateStampAnnot(bBox2, AnnotToolTransform.this.mSelectedAnnot.getOpacity(), AnnotToolTransform.this.mSelectedAnnot.getObjID());
                                        }
                                    } else if ((AnnotToolTransform.this.mSelectedAnnot instanceof AnnotFreeText) && AnnotToolTransform.this.mSelectedAnnot.getObjID() > 0) {
                                        AnnotToolTransform.this.mPageView.updateFreeTextAnnot(new PointF(bBox2.left, bBox2.top), ((AnnotFreeText) AnnotToolTransform.this.mSelectedAnnot).getText(), ((AnnotFreeText) AnnotToolTransform.this.mSelectedAnnot).getFont(), (double) ((AnnotFreeText) AnnotToolTransform.this.mSelectedAnnot).getTextSize(), (double) AnnotToolTransform.this.mSelectedAnnot.getRotation(), (double) AnnotToolTransform.this.mSelectedAnnot.getOpacity(), ((AnnotFreeText) AnnotToolTransform.this.mSelectedAnnot).getARGB(), bBox2, AnnotToolTransform.this.mSelectedAnnot.getObjID());
                                    }
                                } else if (AnnotToolTransform.this.mSelectedAnnot instanceof AnnotLine) {
                                    AnnotToolTransform.this.mPageView.updateLineAnnot(AnnotToolTransform.this.mPaintView.getStartPt(), AnnotToolTransform.this.mPaintView.getEndPt(), ((AnnotLine) AnnotToolTransform.this.mSelectedAnnot).getLineWidth(), ((AnnotLine) AnnotToolTransform.this.mSelectedAnnot).getARGB(), AnnotToolTransform.this.mSelectedAnnot.getObjID());
                                }
                                AnnotToolTransform.this.showSelectedAnnotBBox(false);
                            }
                            LinearLayout linearLayout = new LinearLayout(AnnotToolTransform.this.mContext);
                            linearLayout.setBackgroundColor(-1140850689);
                            linearLayout.setOrientation(1);
                            AnnotToolTransform.this.mPaintView.setPressedInit();
                            AnnotToolTransform.this.mDisplay.changeGesture(BaseGestureProcessor.GestureType.VIEW);
                            AnnotToolTransform.this.mDisplay.setAnnotationTool((BaseAnnotTool.AnnotToolType) null);
                        }
                        return false;
                    }
                });
                this.mPaintView.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return false;
                    }
                });
                this.mPaintView.setClickable(true);
                this.mPageView.addView(this.mPaintView, new ViewGroup.LayoutParams(-1, -1));
                this.mPaintView.layout(0, 0, this.mPageView.getWidth(), this.mPageView.getHeight());
                this.mPaintView.invalidate();
            }
        } else if (this.mSelectedAnnot == null || !(this.mSelectedAnnot instanceof AnnotNote) || !this.mSelectedAnnot.isMovable()) {
            this.mPageView.removeView(this.mPaintView);
            showSelectedAnnotBBox(false);
            this.mDisplay.changeGesture(BaseGestureProcessor.GestureType.VIEW);
            this.mDisplay.setAnnotationTool((BaseAnnotTool.AnnotToolType) null);
        } else {
            this.mPageView.removeView(this.mPaintView);
        }
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        if (this.mSelectedAnnot != null && this.mSelectedAnnot.isMovable()) {
            PointF correctPos = getCorrectPos(i, i2);
            RectF bBox = this.mSelectedAnnot.getBBox();
            float f = correctPos.x - this.beginPoint.x;
            float f2 = correctPos.y - this.beginPoint.y;
            this.beginPoint = correctPos;
            float pin_size = (float) (this.mPaintView.getPIN_SIZE() / 2);
            if (bBox.left + f > pin_size && bBox.right + f < (((float) this.mPageView.getWidth()) / this.mAnnotScale) - pin_size && bBox.top + f2 > pin_size && bBox.bottom + f2 < (((float) this.mPageView.getHeight()) / this.mAnnotScale) - pin_size) {
                if (this.mSelectedAnnot instanceof AnnotLine) {
                    PointF startPt = this.mPaintView.getStartPt();
                    PointF endPt = this.mPaintView.getEndPt();
                    startPt.x += f;
                    startPt.y += f2;
                    endPt.x += f;
                    endPt.y += f2;
                    updateLineAnnot(this.mSelectedAnnot, startPt, endPt);
                    PinSelect pinSelect = this.mPaintView;
                    if (pinSelect != null) {
                        pinSelect.setStartPt(startPt);
                        this.mPaintView.setEndPt(endPt);
                        this.mPaintView.invalidate();
                    }
                } else {
                    updateAnnot(this.mSelectedAnnot, new RectF(bBox.left + f, bBox.top + f2, bBox.right + f, bBox.bottom + f2));
                    PinSelect pinSelect2 = this.mPaintView;
                    if (pinSelect2 != null) {
                        pinSelect2.setBoundaryBox(this.mSelectedAnnot.getBBox());
                        this.mPaintView.invalidate();
                    }
                }
            }
            showSelectedAnnotBBox(true);
        }
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        if (this.mSelectedAnnot != null && (this.mSelectedAnnot instanceof AnnotInk)) {
            if (this.mSelectedAnnot.getObjID() > 0) {
                this.mPageView.updateInkAnnot(((AnnotInk) this.mSelectedAnnot).getARGB(), ((AnnotInk) this.mSelectedAnnot).getLineWidth(), ((AnnotInk) this.mSelectedAnnot).getPointContainer(), this.mSelectedAnnot.getObjID());
            }
            PinSelect pinSelect = this.mPaintView;
            if (pinSelect != null) {
                pinSelect.setBoundaryBox(this.mSelectedAnnot.getBBox());
                this.mPaintView.invalidate();
            }
            showSelectedAnnotBBox(false);
        } else if (this.mSelectedAnnot != null && (this.mSelectedAnnot instanceof AnnotNote)) {
            PointF correctPos = getCorrectPos(i, i2);
            correctPos.x -= 10.0f;
            correctPos.y -= 9.0f;
            this.mPageView.updateNoteAnnot(this.mSelectedAnnot.getObjID(), ((AnnotNote) this.mSelectedAnnot).getTitle(), ((AnnotNote) this.mSelectedAnnot).getContents(), correctPos);
        } else if (this.mSelectedAnnot == null || !(this.mSelectedAnnot instanceof AnnotCircle)) {
            if (this.mSelectedAnnot == null || !(this.mSelectedAnnot instanceof AnnotSquare)) {
                if (this.mSelectedAnnot == null || !(this.mSelectedAnnot instanceof AnnotStamp)) {
                    if (this.mSelectedAnnot instanceof AnnotFreeText) {
                        if (this.mSelectedAnnot.getObjID() > 0) {
                            this.mPageView.updateFreeTextAnnot(new PointF(this.mPaintView.getBoundaryBox().left, this.mPaintView.getBoundaryBox().top), ((AnnotFreeText) this.mSelectedAnnot).getText(), ((AnnotFreeText) this.mSelectedAnnot).getFont(), (double) ((AnnotFreeText) this.mSelectedAnnot).getTextSize(), (double) this.mSelectedAnnot.getRotation(), (double) this.mSelectedAnnot.getOpacity(), ((AnnotFreeText) this.mSelectedAnnot).getARGB(), this.mPaintView.getBoundaryBox(), this.mSelectedAnnot.getObjID());
                        }
                    } else if (this.mSelectedAnnot != null && (this.mSelectedAnnot instanceof AnnotLine) && this.mSelectedAnnot.getObjID() > 0) {
                        this.mPageView.updateLineAnnot(this.mPaintView.getStartPt(), this.mPaintView.getEndPt(), ((AnnotLine) this.mSelectedAnnot).getLineWidth(), ((AnnotLine) this.mSelectedAnnot).getARGB(), this.mSelectedAnnot.getObjID());
                    }
                } else if (this.mSelectedAnnot.getObjID() > 0) {
                    this.mPageView.updateStampAnnot(this.mPaintView.getBoundaryBox(), this.mSelectedAnnot.getOpacity(), this.mSelectedAnnot.getObjID());
                }
            } else if (this.mSelectedAnnot.getObjID() > 0) {
                this.mPageView.updateSquareAnnot(this.mPaintView.getBoundaryBox(), ((AnnotSquare) this.mSelectedAnnot).getARGB(), ((AnnotSquare) this.mSelectedAnnot).getInteriorARGB(), ((AnnotSquare) this.mSelectedAnnot).isInnerTransparent(), ((AnnotSquare) this.mSelectedAnnot).getLineWidth(), this.mSelectedAnnot.getObjID());
            }
        } else if (this.mSelectedAnnot.getObjID() > 0) {
            this.mPageView.updateCircleAnnot(this.mPaintView.getBoundaryBox(), ((AnnotCircle) this.mSelectedAnnot).getARGB(), ((AnnotCircle) this.mSelectedAnnot).getInteriorARGB(), ((AnnotCircle) this.mSelectedAnnot).isInnerTransparent(), ((AnnotCircle) this.mSelectedAnnot).getLineWidth(), this.mSelectedAnnot.getObjID());
        }
    }

    public void exit() {
        showSelectedAnnotBBox(false);
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        setPageView(this.mDisplay.getPageView());
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
        if (selectedAnnot instanceof AnnotInk) {
            onTouchBegin(i, i2, annotToolEventListener);
        }
        return selectedAnnot;
    }

    public BaseAnnot longPress(int i, int i2) {
        setPageView(this.mDisplay.getPageView());
        showSelectedAnnotBBox(false);
        BaseAnnot selectedAnnot = getSelectedAnnot(new Point(i, i2));
        if (selectedAnnot == null) {
            return null;
        }
        if (selectedAnnot instanceof AnnotInk) {
            showSelectedAnnotBBox(true);
            if (this.annotEventListener != null ? this.annotEventListener.onDoEditMenu(selectedAnnot, i, i2) : true) {
                AnnotMenuManager.get().doEditMenu(selectedAnnot, i, i2).setListener(this);
            }
        }
        if (selectedAnnot instanceof AnnotNote) {
            showSelectedAnnotBBox(true);
            selectedAnnot.setMovable(true);
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

    public void updateAnnot(BaseAnnot baseAnnot, RectF rectF) {
        AnnotInk annotInk;
        if (baseAnnot != null) {
            RectF bBox = baseAnnot.getBBox();
            if (baseAnnot instanceof AnnotInk) {
                if (this.mPaintView != null) {
                    AnnotInk annotInk2 = (AnnotInk) baseAnnot;
                    if (Math.abs(rectF.left - rectF.right) < ((float) annotInk2.getLineWidth()) && rectF.left > rectF.right) {
                        if (this.mPaintView.isPressedLeft() || this.mPaintView.isPressedLeftBottom() || this.mPaintView.isPressedLeftTop()) {
                            rectF.left = rectF.right + ((float) annotInk2.getLineWidth());
                        }
                        if (this.mPaintView.isPressedRight() || this.mPaintView.isPressedRightTop() || this.mPaintView.isPressedRightBottom()) {
                            rectF.right = rectF.left - ((float) annotInk2.getLineWidth());
                        }
                    } else if (Math.abs(rectF.right - rectF.left) < ((float) annotInk2.getLineWidth()) && rectF.left < rectF.right) {
                        if (this.mPaintView.isPressedLeft() || this.mPaintView.isPressedLeftBottom() || this.mPaintView.isPressedLeftTop()) {
                            rectF.left = rectF.right - ((float) annotInk2.getLineWidth());
                        }
                        if (this.mPaintView.isPressedRight() || this.mPaintView.isPressedRightTop() || this.mPaintView.isPressedRightBottom()) {
                            rectF.right = rectF.left + ((float) annotInk2.getLineWidth());
                        }
                    }
                    if (Math.abs(rectF.top - rectF.bottom) < ((float) annotInk2.getLineWidth()) && rectF.top > rectF.bottom) {
                        if (this.mPaintView.isPressedTop() || this.mPaintView.isPressedLeftTop() || this.mPaintView.isPressedRightTop()) {
                            rectF.top = rectF.bottom + ((float) annotInk2.getLineWidth());
                        }
                        if (this.mPaintView.isPressedBottom() || this.mPaintView.isPressedRightBottom() || this.mPaintView.isPressedLeftBottom()) {
                            rectF.bottom = rectF.top - ((float) annotInk2.getLineWidth());
                        }
                    } else if (Math.abs(rectF.bottom - rectF.top) < ((float) annotInk2.getLineWidth()) && rectF.top < rectF.bottom) {
                        if (this.mPaintView.isPressedTop() || this.mPaintView.isPressedLeftTop() || this.mPaintView.isPressedRightTop()) {
                            rectF.top = rectF.bottom - ((float) annotInk2.getLineWidth());
                        }
                        if (this.mPaintView.isPressedBottom() || this.mPaintView.isPressedRightBottom() || this.mPaintView.isPressedLeftBottom()) {
                            rectF.bottom = rectF.top + ((float) annotInk2.getLineWidth());
                        }
                    }
                }
                float f = (rectF.right - rectF.left) / (bBox.right - bBox.left);
                float f2 = (rectF.bottom - rectF.top) / (bBox.bottom - bBox.top);
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (true) {
                    annotInk = (AnnotInk) baseAnnot;
                    if (i >= annotInk.getPointContainer().size()) {
                        break;
                    }
                    ArrayList arrayList2 = annotInk.getPointContainer().get(i);
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        if (this.beginPoint != null) {
                            PointF pointF = (PointF) arrayList2.get(i2);
                            PointF pointF2 = new PointF();
                            pointF2.x = ((pointF.x - bBox.left) * f) + rectF.left;
                            pointF2.y = ((pointF.y - bBox.top) * f2) + rectF.top;
                            arrayList2.set(i2, pointF2);
                            arrayList.add(pointF2);
                        }
                    }
                    i++;
                }
                ArrayList arrayList3 = new ArrayList(0);
                arrayList3.add(arrayList);
                annotInk.setPointContainer(arrayList3);
            }
            baseAnnot.setBBox(rectF.left, rectF.top, rectF.right, rectF.bottom);
            if (baseAnnot instanceof AnnotCircle) {
                ((AnnotCircle) baseAnnot).setOvalRect(baseAnnot.getBBox());
            } else if (baseAnnot instanceof AnnotSquare) {
                ((AnnotSquare) baseAnnot).setSquare(baseAnnot.getBBox());
            }
            baseAnnot.invalidate();
        }
    }

    public void updateLineAnnot(BaseAnnot baseAnnot, PointF pointF) {
        if (baseAnnot != null) {
            PinSelect pinSelect = this.mPaintView;
            if (pinSelect != null && (baseAnnot instanceof AnnotLine)) {
                if (pinSelect.isPressedStartPt()) {
                    ((AnnotLine) baseAnnot).setStartPoint(new PointF(pointF.x * this.mAnnotScale, pointF.y * this.mAnnotScale));
                }
                if (this.mPaintView.isPressedEndtPt()) {
                    ((AnnotLine) baseAnnot).setEndPoint(new PointF(pointF.x * this.mAnnotScale, pointF.y * this.mAnnotScale));
                }
            }
            baseAnnot.setBBox(Math.min(this.mPaintView.getStartPt().x, this.mPaintView.getEndPt().x), Math.min(this.mPaintView.getStartPt().y, this.mPaintView.getEndPt().y), Math.max(this.mPaintView.getStartPt().x, this.mPaintView.getEndPt().x), Math.max(this.mPaintView.getStartPt().y, this.mPaintView.getEndPt().y));
            baseAnnot.invalidate();
        }
    }

    public void updateLineAnnot(BaseAnnot baseAnnot, PointF pointF, PointF pointF2) {
        if (baseAnnot != null) {
            if (this.mPaintView != null && (baseAnnot instanceof AnnotLine)) {
                AnnotLine annotLine = (AnnotLine) baseAnnot;
                annotLine.setStartPoint(pointF);
                annotLine.setEndPoint(pointF2);
            }
            baseAnnot.setBBox(Math.min(pointF.x, pointF2.x) - ((float) this.mPaintView.getPIN_SIZE()), Math.min(pointF.y, pointF2.y) - ((float) this.mPaintView.getPIN_SIZE()), Math.max(pointF.x, pointF2.x) + ((float) this.mPaintView.getPIN_SIZE()), Math.max(pointF.y, pointF2.y) + ((float) this.mPaintView.getPIN_SIZE()));
            baseAnnot.invalidate();
        }
    }

    private class PinSelect extends View {
        private final int PIN_SIZE = 20;
        private RectF boundaryBox;
        private PointF[] boundaryPts;
        private RectF[] boundaryRects;
        private PointF mEndPt;
        private boolean mIsLineAnnot = false;
        private PointF mStartPt;
        private final Paint paint = new Paint();
        private boolean pressedBottom = false;
        private boolean pressedEndtPt = false;
        private boolean pressedLeft = false;
        private boolean pressedLeftBottom = false;
        private boolean pressedLeftTop = false;
        private boolean pressedRight = false;
        private boolean pressedRightBottom = false;
        private boolean pressedRightTop = false;
        private boolean pressedStartPt = false;
        private boolean pressedTop = false;
        private float scale = AnnotToolTransform.this.mPageView.getAnnotScale();

        public int getPIN_SIZE() {
            return 20;
        }

        public PinSelect(Context context, RectF rectF) {
            super(context);
            RectF rectF2 = new RectF(rectF.left * this.scale, rectF.top * this.scale, rectF.right * this.scale, rectF.bottom * this.scale);
            this.boundaryBox = rectF2;
            setBoundaryPts(rectF2);
            setBourdaryRects(getBoundaryPts());
            invalidate();
        }

        public PinSelect(Context context, PointF pointF, PointF pointF2) {
            super(context);
            this.mStartPt = new PointF(pointF.x, pointF.y);
            this.mEndPt = new PointF(pointF2.x, pointF2.y);
            this.mIsLineAnnot = true;
            this.boundaryBox = new RectF(Math.min(this.mStartPt.x * this.scale, this.mEndPt.x * this.scale), Math.min(this.mStartPt.y * this.scale, this.mEndPt.y * this.scale), Math.max(this.mStartPt.x * this.scale, this.mEndPt.x * this.scale), Math.max(this.mStartPt.y * this.scale, this.mEndPt.y * this.scale));
            invalidate();
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.paint.setStrokeWidth(2.0f);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setColor(-65536);
            if (this.mIsLineAnnot) {
                canvas.drawOval(new RectF((this.mStartPt.x * this.scale) - 20.0f, (this.mStartPt.y * this.scale) - 20.0f, (this.mStartPt.x * this.scale) + 20.0f, (this.mStartPt.y * this.scale) + 20.0f), this.paint);
                canvas.drawOval(new RectF((this.mEndPt.x * this.scale) - 20.0f, (this.mEndPt.y * this.scale) - 20.0f, (this.mEndPt.x * this.scale) + 20.0f, (this.mEndPt.y * this.scale) + 20.0f), this.paint);
                this.paint.setColor(-1);
                canvas.drawOval(new RectF((this.mStartPt.x * this.scale) - 10.0f, (this.mStartPt.y * this.scale) - 10.0f, (this.mStartPt.x * this.scale) + 10.0f, (this.mStartPt.y * this.scale) + 10.0f), this.paint);
                canvas.drawOval(new RectF((this.mEndPt.x * this.scale) - 10.0f, (this.mEndPt.y * this.scale) - 10.0f, (this.mEndPt.x * this.scale) + 10.0f, (this.mEndPt.y * this.scale) + 10.0f), this.paint);
            } else if (getBoundaryRects() != null) {
                this.paint.setStrokeWidth(4.0f);
                this.paint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 2.0f));
                Canvas canvas2 = canvas;
                canvas2.drawRect(Math.min(getBoundaryPts()[0].x, getBoundaryPts()[7].x), Math.min(getBoundaryPts()[0].y, getBoundaryPts()[7].y), Math.max(getBoundaryPts()[0].x, getBoundaryPts()[7].x), Math.max(getBoundaryPts()[0].y, getBoundaryPts()[7].y), this.paint);
                this.paint.setStyle(Paint.Style.FILL);
                for (int i = 0; i < 8; i++) {
                    this.paint.setColor(-65536);
                    RectF rectF = getBoundaryRects()[i];
                    canvas.drawOval(rectF, this.paint);
                    this.paint.setColor(-1);
                    float width = rectF.width() / 4.0f;
                    canvas.drawOval(new RectF(rectF.left + width, rectF.top + width, rectF.right - width, rectF.bottom - width), this.paint);
                }
            }
        }

        public void setBoundaryPts(RectF rectF) {
            float[] fArr = {rectF.left - 1.0f, (rectF.left + rectF.right) / 2.0f, rectF.right + 1.0f};
            float[] fArr2 = {rectF.top - 1.0f, (rectF.top + rectF.bottom) / 2.0f, rectF.bottom + 1.0f};
            this.boundaryPts = new PointF[8];
            int i = 0;
            for (int i2 = 0; i2 < 3; i2++) {
                for (int i3 = 0; i3 < 3; i3++) {
                    if (i2 != 1 || i3 != 1) {
                        this.boundaryPts[i] = new PointF(fArr[i3], fArr2[i2]);
                        i++;
                    }
                }
            }
        }

        public PointF[] getBoundaryPts() {
            return this.boundaryPts;
        }

        public void setBourdaryRects(PointF[] pointFArr) {
            this.boundaryRects = new RectF[8];
            for (int i = 0; i < 8; i++) {
                PointF pointF = pointFArr[i];
                this.boundaryRects[i] = new RectF(pointF.x - 20.0f, pointF.y - 20.0f, pointF.x + 20.0f, pointF.y + 20.0f);
            }
        }

        public RectF[] getBoundaryRects() {
            return this.boundaryRects;
        }

        public void setBoundaryBox(RectF rectF) {
            RectF rectF2 = new RectF(rectF.left * this.scale, rectF.top * this.scale, rectF.right * this.scale, rectF.bottom * this.scale);
            this.boundaryBox = rectF2;
            setBoundaryPts(rectF2);
            setBourdaryRects(getBoundaryPts());
        }

        public RectF getBoundaryBox() {
            return this.boundaryBox;
        }

        public void setPressed(int i) {
            setPressedInit();
            switch (i) {
                case 0:
                    setPressedLeftTop(true);
                    return;
                case 1:
                    setPressedTop(true);
                    return;
                case 2:
                    setPressedRightTop(true);
                    return;
                case 3:
                    setPressedLeft(true);
                    return;
                case 4:
                    setPressedRight(true);
                    return;
                case 5:
                    setPressedLeftBottom(true);
                    return;
                case 6:
                    setPressedBottom(true);
                    return;
                case 7:
                    setPressedRightBottom(true);
                    return;
                default:
                    return;
            }
        }

        public void setMovePoint(PointF pointF) {
            if (isPressedLeftTop()) {
                this.boundaryBox.left = pointF.x * this.scale;
                this.boundaryBox.top = pointF.y * this.scale;
            } else if (isPressedTop()) {
                this.boundaryBox.top = pointF.y * this.scale;
            } else if (isPressedRightTop()) {
                this.boundaryBox.top = pointF.y * this.scale;
                this.boundaryBox.right = pointF.x * this.scale;
            } else if (isPressedRight()) {
                this.boundaryBox.right = pointF.x * this.scale;
            } else if (isPressedRightBottom()) {
                this.boundaryBox.right = pointF.x * this.scale;
                this.boundaryBox.bottom = pointF.y * this.scale;
            } else if (isPressedBottom()) {
                this.boundaryBox.bottom = pointF.y * this.scale;
            } else if (isPressedLeftBottom()) {
                this.boundaryBox.bottom = pointF.y * this.scale;
                this.boundaryBox.left = pointF.x * this.scale;
            } else if (isPressedLeft()) {
                this.boundaryBox.left = pointF.x * this.scale;
            } else if (isPressedStartPt()) {
                this.mStartPt = new PointF(pointF.x * this.scale, pointF.y * this.scale);
            } else if (isPressedEndtPt()) {
                this.mEndPt = new PointF(pointF.x * this.scale, pointF.y * this.scale);
            }
            if (!isPressedStartPt() && !isPressedEndtPt()) {
                setBoundaryPts(this.boundaryBox);
                setBourdaryRects(getBoundaryPts());
            }
            invalidate();
        }

        public PointF getStartPt() {
            return new PointF(this.mStartPt.x, this.mStartPt.y);
        }

        public PointF getEndPt() {
            return new PointF(this.mEndPt.x, this.mEndPt.y);
        }

        public void setStartPt(PointF pointF) {
            this.mStartPt = pointF;
        }

        public void setEndPt(PointF pointF) {
            this.mEndPt = pointF;
        }

        public boolean isPressedLeftTop() {
            return this.pressedLeftTop;
        }

        public boolean isPressedTop() {
            return this.pressedTop;
        }

        public boolean isPressedLeft() {
            return this.pressedLeft;
        }

        public boolean isPressedLeftBottom() {
            return this.pressedLeftBottom;
        }

        public boolean isPressedRightTop() {
            return this.pressedRightTop;
        }

        public boolean isPressedRight() {
            return this.pressedRight;
        }

        public boolean isPressedRightBottom() {
            return this.pressedRightBottom;
        }

        public boolean isPressedBottom() {
            return this.pressedBottom;
        }

        public boolean isPressedStartPt() {
            return this.pressedStartPt;
        }

        public boolean isPressedEndtPt() {
            return this.pressedEndtPt;
        }

        public boolean isPressed() {
            return isPressedBottom() || isPressedLeft() || isPressedRight() || isPressedTop() || isPressedLeftBottom() || isPressedRightBottom() || isPressedRightTop() || isPressedLeftTop() || isPressedStartPt() || isPressedEndtPt();
        }

        public void setPressedLeftTop(boolean z) {
            this.pressedLeftTop = z;
        }

        public void setPressedTop(boolean z) {
            this.pressedTop = z;
        }

        public void setPressedLeft(boolean z) {
            this.pressedLeft = z;
        }

        public void setPressedLeftBottom(boolean z) {
            this.pressedLeftBottom = z;
        }

        public void setPressedRightTop(boolean z) {
            this.pressedRightTop = z;
        }

        public void setPressedRight(boolean z) {
            this.pressedRight = z;
        }

        public void setPressedRightBottom(boolean z) {
            this.pressedRightBottom = z;
        }

        public void setPressedBottom(boolean z) {
            this.pressedBottom = z;
        }

        public void setPressedStartPt(boolean z) {
            this.pressedStartPt = z;
        }

        public void setPressedEndtPt(boolean z) {
            this.pressedEndtPt = z;
        }

        public void setPressedInit() {
            setPressedBottom(false);
            setPressedLeft(false);
            setPressedRight(false);
            setPressedLeftBottom(false);
            setPressedLeftTop(false);
            setPressedRightBottom(false);
            setPressedRightTop(false);
            setPressedRight(false);
            setPressedTop(false);
            setPressedStartPt(false);
            setPressedEndtPt(false);
        }

        public boolean isLineAnnot() {
            return this.mIsLineAnnot;
        }
    }

    public enum DirectionType {
        TOPLEFT(0),
        TOP(1),
        TOPRIGHT(2),
        MIDLEFT(3),
        MIDRIGHT(4),
        BOTTOMLEFT(5),
        BOTTOM(6),
        BOTTOMRIGHT(7);
        
        private int mValue;

        private DirectionType(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }
    }
}
