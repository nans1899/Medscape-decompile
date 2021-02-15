package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import com.epapyrus.plugpdf.core.annotation.AnnotEventListener;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.viewer.PageView;
import java.util.List;
import java.util.ListIterator;

public abstract class BaseAnnotTool {
    protected AnnotEventListener annotEventListener = null;
    private boolean isBeingEdited = false;
    private List<BaseAnnot> mAnnotList;
    protected float mAnnotScale;
    protected Context mContext;
    protected PageView mPageView;
    protected BaseAnnot mSelectedAnnot;

    public enum AnnotToolType {
        NONE,
        INK,
        ERASER,
        NOTE,
        TEXT_SELECT,
        HIGHLIGHT,
        UNDERLINE,
        STRIKEOUT,
        SQUIGGLY,
        LINK,
        SQUARE,
        CIRCLE,
        FILE_ATTACTH,
        LINE,
        TRANSFORM,
        FREE_TEXT,
        STAMP
    }

    public abstract void enter();

    public abstract void exit();

    public BaseAnnot longPress(int i, int i2) {
        return null;
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener);

    /* access modifiers changed from: protected */
    public abstract void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener);

    /* access modifiers changed from: protected */
    public abstract void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener);

    public BaseAnnotTool(Context context) {
        this.mContext = context;
    }

    public void setPageView(PageView pageView) {
        this.mPageView = pageView;
        if (pageView != null) {
            this.mAnnotList = pageView.getAnnotList();
        }
    }

    public void setAnnotEventListener(AnnotEventListener annotEventListener2) {
        this.annotEventListener = annotEventListener2;
    }

    /* access modifiers changed from: protected */
    public PointF getCorrectPos(int i, int i2) {
        PageView pageView = this.mPageView;
        if (pageView != null) {
            return new PointF(((float) (i - pageView.getLeft())) / this.mAnnotScale, ((float) (i2 - this.mPageView.getTop())) / this.mAnnotScale);
        }
        return null;
    }

    public boolean onTouchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        this.isBeingEdited = true;
        touchBegin(i, i2, annotToolEventListener);
        return true;
    }

    public boolean onTouchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        if (!this.isBeingEdited) {
            return false;
        }
        touchMove(i, i2, annotToolEventListener);
        return true;
    }

    public boolean onTouchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        if (!this.isBeingEdited) {
            return false;
        }
        touchEnd(i, i2, annotToolEventListener);
        this.isBeingEdited = false;
        return true;
    }

    public BaseAnnot getSelectedAnnot(Point point) {
        this.mSelectedAnnot = null;
        PageView pageView = this.mPageView;
        if (pageView != null) {
            this.mAnnotScale = pageView.getAnnotScale();
        }
        if (this.mAnnotList == null) {
            return null;
        }
        PointF correctPos = getCorrectPos(point.x, point.y);
        List<BaseAnnot> list = this.mAnnotList;
        ListIterator<BaseAnnot> listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            BaseAnnot previous = listIterator.previous();
            if (previous.isContains(correctPos.x, correctPos.y)) {
                this.mSelectedAnnot = previous;
                break;
            }
        }
        return this.mSelectedAnnot;
    }

    /* access modifiers changed from: protected */
    public void showSelectedAnnotBBox(boolean z) {
        BaseAnnot baseAnnot = this.mSelectedAnnot;
        if (baseAnnot != null) {
            baseAnnot.showBBox(z);
            this.mSelectedAnnot.invalidate();
        }
    }

    public void setBeingEdited(boolean z) {
        this.isBeingEdited = z;
    }
}
