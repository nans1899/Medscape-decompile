package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AnnotInk extends BaseAnnot {
    private int mB;
    private int mG;
    private int mLineWidth;
    private int mMax_DrawPathSize = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
    private Paint mPaint = new Paint();
    private ArrayList<ArrayList<PointF>> mPointContainer;
    private int mR;

    public AnnotInk(Context context) {
        super(context, "INK");
        ArrayList<ArrayList<PointF>> arrayList = new ArrayList<>();
        this.mPointContainer = arrayList;
        arrayList.add(new ArrayList());
        setScale(1.0f);
    }

    public void setPointContainer(ArrayList<ArrayList<PointF>> arrayList) {
        this.mPointContainer = arrayList;
    }

    public void setARGB(int i, int i2, int i3, int i4) {
        this.mAlpha = i;
        this.mR = i2;
        this.mG = i3;
        this.mB = i4;
    }

    public int getARGB() {
        return Color.argb(this.mAlpha, this.mR, this.mG, this.mB);
    }

    public void setLineWidth(int i) {
        this.mLineWidth = i;
    }

    public int getLineWidth() {
        return this.mLineWidth;
    }

    public void addPointList(PointF[] pointFArr) {
        this.mPointContainer.add(new ArrayList(Arrays.asList(pointFArr)));
    }

    public void addPoint(PointF pointF) {
        if (this.mPointContainer.get(0).isEmpty()) {
            this.mPointContainer.get(0).add(pointF);
        } else if (!pointF.equals(this.mPointContainer.get(0).get(this.mPointContainer.get(0).size() - 1))) {
            this.mPointContainer.get(0).add(pointF);
        }
    }

    public ArrayList<ArrayList<PointF>> getPointContainer() {
        return this.mPointContainer;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawInk(canvas);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawInk(Canvas canvas) {
        if (!this.mPointContainer.isEmpty()) {
            Path path = new Path();
            loadPaint();
            Iterator<ArrayList<PointF>> it = this.mPointContainer.iterator();
            while (it.hasNext()) {
                ArrayList next = it.next();
                if (next.size() >= 2) {
                    Iterator it2 = next.iterator();
                    PointF pointF = (PointF) it2.next();
                    float f = pointF.x * this.mScale;
                    float f2 = pointF.y * this.mScale;
                    while (it2.hasNext()) {
                        path.moveTo(f, f2);
                        float f3 = f2;
                        float f4 = f;
                        while (Math.abs(f - f4) < ((float) this.mMax_DrawPathSize) && Math.abs(f2 - f3) < ((float) this.mMax_DrawPathSize) && it2.hasNext()) {
                            PointF pointF2 = (PointF) it2.next();
                            float f5 = pointF2.x * this.mScale;
                            float f6 = pointF2.y * this.mScale;
                            path.quadTo(f4, f3, (f4 + f5) / 2.0f, (f3 + f6) / 2.0f);
                            f3 = f6;
                            f4 = f5;
                        }
                        path.lineTo(f4, f3);
                        canvas.drawPath(path, this.mPaint);
                        path.reset();
                        f2 = f3;
                        f = f4;
                    }
                }
            }
        }
    }

    private void loadPaint() {
        this.mPaint.setARGB(this.mAlpha, this.mR, this.mG, this.mB);
        this.mPaint.setStrokeWidth(((float) this.mLineWidth) * this.mScale);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }
}
