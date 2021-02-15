package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotSquare;
import com.epapyrus.plugpdf.core.annotation.AnnotTextMarkup;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnnotToolSelect extends BaseAnnotTool {
    private List<BaseAnnot> mAnnotList;
    /* access modifiers changed from: private */
    public Context mContext;
    private PointF mDragStartPt;
    private TextSelectListener mListener;
    /* access modifiers changed from: private */
    public PinSelect mPaintView;
    /* access modifiers changed from: private */
    public ArrayList<RectF> mTextRectSet = null;
    /* access modifiers changed from: private */
    public BaseAnnotTool.AnnotToolType mType;
    /* access modifiers changed from: private */
    public PopupWindow popup;

    public interface TextSelectListener {
        boolean onSelectedText(List<RectF> list, String str);
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    public void setListener(TextSelectListener textSelectListener) {
        this.mListener = textSelectListener;
    }

    public AnnotToolSelect(Context context, BaseAnnotTool.AnnotToolType annotToolType) {
        super(context);
        this.mContext = context;
        this.mAnnotList = new ArrayList();
        this.mType = annotToolType;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.mAnnotScale = this.mPageView.getAnnotScale();
        this.mDragStartPt = getCorrectPos(i, i2);
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        RectF dragArea = getDragArea(i, i2);
        wrapText(mergeRectF(this.mPageView.extractTextRects(dragArea, false, false, true)), dragArea);
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        BaseAnnotTool.AnnotToolType annotToolType;
        for (BaseAnnot objID : this.mAnnotList) {
            this.mPageView.removeAnnotFromPageView(objID.getObjID());
        }
        this.mAnnotList.clear();
        RectF dragArea = getDragArea(i, i2);
        ArrayList<RectF> extractTextRects = this.mPageView.extractTextRects(dragArea, false, false, true);
        if (extractTextRects != null) {
            ArrayList<RectF> mergeRectF = mergeRectF(extractTextRects);
            AnnotSetting instance = AnnotSetting.instance();
            if (this.mType != BaseAnnotTool.AnnotToolType.UNDERLINE || !instance.isSquiggly()) {
                annotToolType = this.mType;
            } else {
                annotToolType = BaseAnnotTool.AnnotToolType.SQUIGGLY;
            }
            if (annotToolType != BaseAnnotTool.AnnotToolType.TEXT_SELECT) {
                this.mPageView.addTextMarkupAnnot((RectF[]) mergeRectF.toArray(new RectF[mergeRectF.size()]), annotToolType, instance.getLineColor(annotToolType));
            }
            String extractText = this.mPageView.extractText(dragArea);
            ArrayList arrayList = new ArrayList();
            arrayList.add(dragArea);
            if (annotToolType == BaseAnnotTool.AnnotToolType.TEXT_SELECT) {
                TextSelectListener textSelectListener = this.mListener;
                if (textSelectListener == null || textSelectListener.onSelectedText(arrayList, extractText)) {
                    ClipboardManager clipboardManager = (ClipboardManager) this.mContext.getSystemService("clipboard");
                    if (extractText != null && extractText.length() > 0) {
                        clipboardManager.setPrimaryClip(ClipData.newPlainText("Extracted text", extractText));
                        Toast.makeText(this.mContext, "Text copied to clipboard", 0).show();
                    }
                }
            }
        }
    }

    private void wrapText(List<RectF> list, RectF rectF) {
        if (list != null && list.size() != 0) {
            for (BaseAnnot objID : this.mAnnotList) {
                this.mPageView.removeAnnotFromPageView(objID.getObjID());
                this.mPageView.removeView(this.mPaintView);
            }
            int i = PropertyManager.getmWrapTextColor();
            int i2 = i & 255;
            int i3 = (i >> 8) & 255;
            int i4 = (i >> 16) & 255;
            int i5 = (i >> 24) & 255;
            this.mAnnotList.clear();
            for (RectF next : list) {
                AnnotTextMarkup annotTextMarkup = (AnnotTextMarkup) AnnotFactory.instance().createAnnot(this.mContext, "HIGHLIGHT");
                annotTextMarkup.setARGB(i5, i4, i3, i2);
                annotTextMarkup.setBBox(next.left, next.top, next.right, next.bottom);
                annotTextMarkup.setScale(this.mAnnotScale);
                annotTextMarkup.invalidate();
                this.mPageView.addAnnot(annotTextMarkup);
                this.mAnnotList.add(annotTextMarkup);
            }
            AnnotSquare annotSquare = (AnnotSquare) AnnotFactory.instance().createAnnot(this.mContext, "SQUARE");
            annotSquare.setIsRubberband(true);
            annotSquare.setBBox(rectF.left, rectF.top, rectF.right, rectF.bottom);
            annotSquare.setARGB(255, 0, 0, 0);
            annotSquare.setScale(this.mAnnotScale);
            annotSquare.addDashedPattern(3);
            annotSquare.addDashedPattern(2);
            annotSquare.setSquare(rectF);
            annotSquare.invalidate();
            this.mPageView.addAnnot(annotSquare);
            this.mAnnotList.add(annotSquare);
        }
    }

    private RectF getDragArea(int i, int i2) {
        PointF correctPos = getCorrectPos(i, i2);
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        if (this.mDragStartPt.x < correctPos.x) {
            pointF.x = this.mDragStartPt.x;
            pointF2.x = correctPos.x;
        } else {
            pointF.x = correctPos.x;
            pointF2.x = this.mDragStartPt.x;
        }
        if (this.mDragStartPt.y > correctPos.y) {
            pointF.y = this.mDragStartPt.y;
            pointF2.y = correctPos.y;
        } else {
            pointF.y = correctPos.y;
            pointF2.y = this.mDragStartPt.y;
        }
        return new RectF(pointF.x, pointF.y, pointF2.x, pointF2.y);
    }

    public void enter() {
        PopupWindow popupWindow = this.popup;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        this.mTextRectSet = null;
    }

    public void exit() {
        PopupWindow popupWindow = this.popup;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        showSelectedAnnotBBox(false);
    }

    /* access modifiers changed from: private */
    public void onSelectedText(List<RectF> list, String str) {
        TextSelectListener textSelectListener = this.mListener;
        if (textSelectListener != null) {
            textSelectListener.onSelectedText(list, str);
        }
    }

    public BaseAnnot longPress(int i, int i2) {
        PointF correctPos = getCorrectPos(i, i2);
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.mPageView.getWidth(), (float) this.mPageView.getHeight());
        if (this.mTextRectSet == null) {
            this.mTextRectSet = this.mPageView.extractTextRects(rectF, false, false, false);
        }
        Iterator<RectF> it = this.mTextRectSet.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RectF next = it.next();
            if (next.contains(correctPos.x, correctPos.y)) {
                PinSelect pinSelect = new PinSelect(this.mContext, next);
                this.mPaintView = pinSelect;
                pinSelect.setOnTouchListener(new View.OnTouchListener() {
                    /* access modifiers changed from: private */
                    public final float TOLERANCE = PlugPDFUtility.convertDipToPx(AnnotToolSelect.this.mContext, AnnotToolSelect.this.mAnnotScale * 3.0f);
                    /* access modifiers changed from: private */
                    public Button actionBtn;
                    private View.OnClickListener btnListener = new View.OnClickListener() {
                        public void onClick(View view) {
                            BaseAnnotTool.AnnotToolType annotToolType;
                            if (view == AnonymousClass1.this.copyBtn) {
                                ArrayList<RectF> textRects = AnnotToolSelect.this.mPaintView.getTextRects();
                                String extractText = AnnotToolSelect.this.mPageView.extractText(textRects.get(0), textRects.get(textRects.size() - 1));
                                if (extractText != null && extractText.length() > 0) {
                                    ((ClipboardManager) AnnotToolSelect.this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Extracted text", extractText));
                                    Toast.makeText(AnnotToolSelect.this.mContext, PropertyManager.getCopyTextToast(), 0).show();
                                    AnnotToolSelect.this.onSelectedText(AnnotToolSelect.this.mPaintView.getTextRects(), extractText);
                                }
                            } else if (view == AnonymousClass1.this.searchBtn) {
                                Iterator<RectF> it = AnnotToolSelect.this.mPaintView.getTextRects().iterator();
                                String str = "";
                                while (it.hasNext()) {
                                    RectF next = it.next();
                                    next.left -= AnonymousClass1.this.TOLERANCE;
                                    next.top -= AnonymousClass1.this.TOLERANCE;
                                    next.right += AnonymousClass1.this.TOLERANCE;
                                    next.bottom += AnonymousClass1.this.TOLERANCE;
                                    str = str + AnnotToolSelect.this.mPageView.extractText(next);
                                }
                                if (str != null && str.length() > 0) {
                                    Intent intent = new Intent("android.intent.action.WEB_SEARCH");
                                    intent.putExtra(SearchIntents.EXTRA_QUERY, str);
                                    AnnotToolSelect.this.mContext.startActivity(intent);
                                    AnnotToolSelect.this.onSelectedText(AnnotToolSelect.this.mPaintView.getTextRects(), str);
                                }
                            } else if (view == AnonymousClass1.this.actionBtn) {
                                ArrayList<RectF> textRects2 = AnnotToolSelect.this.mPaintView.getTextRects();
                                if (textRects2.size() > 0) {
                                    AnnotSetting instance = AnnotSetting.instance();
                                    if (AnnotToolSelect.this.mType != BaseAnnotTool.AnnotToolType.UNDERLINE || !instance.isSquiggly()) {
                                        annotToolType = AnnotToolSelect.this.mType;
                                    } else {
                                        annotToolType = BaseAnnotTool.AnnotToolType.SQUIGGLY;
                                    }
                                    if (annotToolType != BaseAnnotTool.AnnotToolType.TEXT_SELECT) {
                                        AnnotToolSelect.this.mPageView.addTextMarkupAnnot((RectF[]) textRects2.toArray(new RectF[textRects2.size()]), annotToolType, instance.getLineColor(annotToolType));
                                    }
                                }
                            }
                            AnnotToolSelect.this.mPageView.removeView(AnnotToolSelect.this.mPaintView);
                            AnnotToolSelect.this.popup.dismiss();
                        }
                    };
                    private Button cancelBtn;
                    /* access modifiers changed from: private */
                    public Button copyBtn;
                    /* access modifiers changed from: private */
                    public Button searchBtn;

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        int i;
                        int i2;
                        float x = motionEvent.getX() / AnnotToolSelect.this.mAnnotScale;
                        float y = motionEvent.getY() / AnnotToolSelect.this.mAnnotScale;
                        if (motionEvent.getAction() == 0) {
                            if (AnnotToolSelect.this.popup != null) {
                                AnnotToolSelect.this.popup.dismiss();
                            }
                            if (AnnotToolSelect.this.mPaintView.getStartPinRect() != null) {
                                if (AnnotToolSelect.this.mPaintView.getStartPinRect().contains(motionEvent.getX(), motionEvent.getY())) {
                                    AnnotToolSelect.this.mPaintView.setPressedStartPoint(true);
                                    AnnotToolSelect.this.mPaintView.setPressedEndPoint(false);
                                } else if (AnnotToolSelect.this.mPaintView.getEndPinRect().contains(motionEvent.getX(), motionEvent.getY())) {
                                    AnnotToolSelect.this.mPaintView.setPressedStartPoint(false);
                                    AnnotToolSelect.this.mPaintView.setPressedEndPoint(true);
                                } else {
                                    AnnotToolSelect.this.mPaintView.setPressedStartPoint(false);
                                    AnnotToolSelect.this.mPaintView.setPressedEndPoint(false);
                                }
                            }
                        } else if (motionEvent.getAction() == 2) {
                            if (AnnotToolSelect.this.mPaintView.isPressedStartPoint()) {
                                AnnotToolSelect.this.mPaintView.setStartPoint(new PointF(x, y));
                            } else if (AnnotToolSelect.this.mPaintView.isPressedEndPoint()) {
                                AnnotToolSelect.this.mPaintView.setEndPoint(new PointF(x, y));
                            }
                            AnnotToolSelect.this.mPaintView.invalidate();
                        } else if (motionEvent.getAction() == 1) {
                            LinearLayout linearLayout = new LinearLayout(AnnotToolSelect.this.mContext);
                            linearLayout.setBackgroundColor(-1140850689);
                            linearLayout.setOrientation(1);
                            PopupWindow unused = AnnotToolSelect.this.popup = new PopupWindow(linearLayout);
                            this.copyBtn = new Button(AnnotToolSelect.this.mContext);
                            this.actionBtn = new Button(AnnotToolSelect.this.mContext);
                            this.searchBtn = new Button(AnnotToolSelect.this.mContext);
                            this.cancelBtn = new Button(AnnotToolSelect.this.mContext);
                            this.copyBtn.setText(PropertyManager.getCopyBtnText());
                            this.searchBtn.setText(PropertyManager.getSearchBtnText());
                            this.cancelBtn.setText(PropertyManager.getCancelBtnText());
                            int i3 = AnonymousClass3.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[AnnotToolSelect.this.mType.ordinal()];
                            if (i3 == 1) {
                                this.actionBtn.setVisibility(8);
                            } else if (i3 == 2) {
                                this.copyBtn.setVisibility(8);
                                this.searchBtn.setVisibility(8);
                                this.actionBtn.setText("Strike out");
                            } else if (i3 == 3) {
                                this.copyBtn.setVisibility(8);
                                this.searchBtn.setVisibility(8);
                                this.actionBtn.setText("Highlight");
                            } else if (i3 == 4) {
                                this.copyBtn.setVisibility(8);
                                this.searchBtn.setVisibility(8);
                                this.actionBtn.setText("Underline");
                            }
                            linearLayout.addView(this.copyBtn);
                            linearLayout.addView(this.searchBtn);
                            linearLayout.addView(this.actionBtn);
                            linearLayout.addView(this.cancelBtn);
                            this.copyBtn.setOnClickListener(this.btnListener);
                            this.searchBtn.setOnClickListener(this.btnListener);
                            this.actionBtn.setOnClickListener(this.btnListener);
                            this.cancelBtn.setOnClickListener(this.btnListener);
                            if (view.getLeft() <= 0) {
                                int[] iArr = new int[2];
                                view.getLocationOnScreen(iArr);
                                i = iArr[0] - view.getLeft();
                                i2 = iArr[1] - view.getTop();
                            } else {
                                i2 = 0;
                                i = 0;
                            }
                            AnnotToolSelect.this.popup.setWindowLayoutMode(-2, -2);
                            if (AnnotSetting.instance().getTextSelectPopupPosition() == null) {
                                AnnotToolSelect.this.popup.showAtLocation(view, 0, (int) (motionEvent.getX() + ((float) i)), (int) (motionEvent.getY() + ((float) i2)));
                            } else {
                                PointF textSelectPopupPosition = AnnotSetting.instance().getTextSelectPopupPosition();
                                AnnotToolSelect.this.popup.showAtLocation(view, 0, (int) textSelectPopupPosition.x, (int) textSelectPopupPosition.y);
                            }
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
                break;
            }
        }
        return null;
    }

    /* renamed from: com.epapyrus.plugpdf.core.annotation.tool.AnnotToolSelect$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType[] r0 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType = r0
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.TEXT_SELECT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.STRIKEOUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.HIGHLIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.UNDERLINE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.annotation.tool.AnnotToolSelect.AnonymousClass3.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public ArrayList<RectF> mergeRectF(ArrayList<RectF> arrayList) {
        ArrayList<RectF> arrayList2 = new ArrayList<>();
        Iterator<RectF> it = arrayList.iterator();
        while (it.hasNext()) {
            RectF next = it.next();
            if (!arrayList2.isEmpty()) {
                boolean z = false;
                Iterator<RectF> it2 = arrayList2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        arrayList2.add(new RectF(next));
                        break;
                    }
                    RectF next2 = it2.next();
                    if (next.top == next2.top && next.bottom == next2.bottom && (Math.abs(next.left - next2.right) < this.mPageView.getAnnotScale() * 3.0f || RectF.intersects(next2, next))) {
                        z = true;
                        continue;
                    }
                    if (z) {
                        next2.left = next.left < next2.left ? next.left : next2.left;
                        next2.right = next.right > next2.right ? next.right : next2.right;
                    }
                }
            } else {
                arrayList2.add(new RectF(next));
            }
        }
        return arrayList2;
    }

    private class PinSelect extends View {
        private final int PIN_SIZE = 20;
        private PointF dragEndPoint;
        private PointF dragStartPoint;
        private float endHeight;
        private RectF endPin;
        private PointF endPoint;
        private RectF endRect = null;
        private final Paint paint = new Paint();
        private boolean pressedEndPoint = false;
        private boolean pressedStartPoint = false;
        private float scale = AnnotToolSelect.this.mPageView.getAnnotScale();
        private float startHeight;
        private RectF startPin;
        private PointF startPoint;
        private RectF startRect = null;
        ArrayList<RectF> textRects;

        public PinSelect(Context context, RectF rectF) {
            super(context);
            setStartPoint(new PointF(rectF.left, rectF.top));
            setEndPoint(new PointF(rectF.right, rectF.bottom));
            prepareDrawing();
        }

        public void prepareDrawing() {
            calculateTextRects();
            calculatePinLocates();
        }

        private void calculateTextRects() {
            if (sortPinPoints()) {
                ArrayList<RectF> arrayList = new ArrayList<>();
                boolean z = false;
                Iterator it = AnnotToolSelect.this.mTextRectSet.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    RectF rectF = (RectF) it.next();
                    if (z || rectF == this.startRect) {
                        arrayList.add(rectF);
                        z = true;
                    }
                    RectF rectF2 = this.endRect;
                    if (rectF == rectF2) {
                        if (!z) {
                            RectF rectF3 = this.startRect;
                            this.startRect = rectF2;
                            this.endRect = rectF3;
                            PointF pointF = this.dragStartPoint;
                            this.dragStartPoint = this.dragEndPoint;
                            this.dragEndPoint = pointF;
                            if (isPressedStartPoint()) {
                                setPressedEndPoint(true);
                            } else if (isPressedEndPoint()) {
                                setPressedStartPoint(true);
                            }
                        }
                    }
                }
                this.textRects = arrayList;
            }
        }

        private boolean sortPinPoints() {
            if (!(this.dragStartPoint == null || this.dragEndPoint == null)) {
                Iterator it = AnnotToolSelect.this.mTextRectSet.iterator();
                float f = 9.8765434E8f;
                float f2 = 9.8765434E8f;
                while (it.hasNext()) {
                    RectF rectF = (RectF) it.next();
                    PointF pointF = new PointF((rectF.left + rectF.right) / 2.0f, (rectF.top + rectF.bottom) / 2.0f);
                    if (f > Math.abs(pointF.x - this.dragStartPoint.x) + Math.abs(pointF.y - this.dragStartPoint.y)) {
                        f = Math.abs(pointF.x - this.dragStartPoint.x) + Math.abs(pointF.y - this.dragStartPoint.y);
                        this.startRect = rectF;
                    }
                    if (f2 > Math.abs(pointF.x - this.dragEndPoint.x) + Math.abs(pointF.y - this.dragEndPoint.y)) {
                        f2 = Math.abs(pointF.x - this.dragEndPoint.x) + Math.abs(pointF.y - this.dragEndPoint.y);
                        this.endRect = rectF;
                    }
                }
                if (this.startRect == null || this.endRect == null) {
                    return false;
                }
                return true;
            }
            return false;
        }

        private void calculatePinLocates() {
            RectF rectF = this.startRect;
            if (rectF != null && this.endRect != null) {
                this.startHeight = rectF.height() * this.scale;
                this.startPoint = new PointF(this.startRect.left * this.scale, this.startRect.top * this.scale);
                this.startPin = new RectF(this.startPoint.x - 20.0f, this.startPoint.y - 40.0f, this.startPoint.x + 20.0f, this.startPoint.y);
                this.endHeight = this.endRect.height() * this.scale;
                this.endPoint = new PointF(this.endRect.right * this.scale, this.endRect.bottom * this.scale);
                this.endPin = new RectF(this.endPoint.x - 20.0f, this.endPoint.y, this.endPoint.x + 20.0f, this.endPoint.y + 40.0f);
                invalidate();
            }
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.paint.setColor(-7829368);
            this.paint.setStrokeWidth(4.0f);
            PointF pointF = this.startPoint;
            if (pointF != null && this.endPoint != null) {
                canvas.drawLine(pointF.x, this.startPoint.y, this.startPoint.x, this.startPoint.y + this.startHeight, this.paint);
                canvas.drawLine(this.endPoint.x, this.endPoint.y, this.endPoint.x, this.endPoint.y - this.endHeight, this.paint);
                this.paint.setColor(-14774017);
                this.paint.setStyle(Paint.Style.STROKE);
                RectF rectF = new RectF(this.startPoint.x - 10.0f, this.startPoint.y - 20.0f, this.startPoint.x + 10.0f, this.startPoint.y);
                RectF rectF2 = new RectF(this.endPoint.x - 10.0f, this.endPoint.y, this.endPoint.x + 10.0f, this.endPoint.y + 20.0f);
                canvas.drawOval(rectF, this.paint);
                canvas.drawOval(rectF2, this.paint);
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setColor(1713279231);
                ArrayList<RectF> access$1000 = AnnotToolSelect.this.mergeRectF(this.textRects);
                Iterator<RectF> it = access$1000.iterator();
                while (it.hasNext()) {
                    RectF next = it.next();
                    canvas.drawRect(new RectF(next.left * AnnotToolSelect.this.mAnnotScale, next.top * AnnotToolSelect.this.mAnnotScale, next.right * AnnotToolSelect.this.mAnnotScale, next.bottom * AnnotToolSelect.this.mAnnotScale), this.paint);
                }
                this.textRects = access$1000;
            }
        }

        public RectF getStartPinRect() {
            return this.startPin;
        }

        public RectF getEndPinRect() {
            return this.endPin;
        }

        public void setStartPoint(PointF pointF) {
            this.dragStartPoint = pointF;
            prepareDrawing();
        }

        public void setEndPoint(PointF pointF) {
            this.dragEndPoint = pointF;
            prepareDrawing();
        }

        public void setPressedStartPoint(boolean z) {
            this.pressedStartPoint = z;
            if (z) {
                this.pressedEndPoint = false;
            }
        }

        public void setPressedEndPoint(boolean z) {
            this.pressedEndPoint = z;
            if (z) {
                this.pressedStartPoint = false;
            }
        }

        public boolean isPressedStartPoint() {
            return this.pressedStartPoint;
        }

        public boolean isPressedEndPoint() {
            return this.pressedEndPoint;
        }

        public ArrayList<RectF> getTextRects() {
            return this.textRects;
        }
    }
}
