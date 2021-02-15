package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class WrapperViewList extends ListView {
    private boolean mBlockLayoutChildren = false;
    private boolean mClippingToPadding = true;
    private List<View> mFooterViews;
    private LifeCycleListener mLifeCycleListener;
    private Field mSelectorPositionField;
    private Rect mSelectorRect = new Rect();
    private int mTopClippingLength;

    interface LifeCycleListener {
        void onDispatchDrawOccurred(Canvas canvas);
    }

    public WrapperViewList(Context context) {
        super(context);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mSelectorRect");
            declaredField.setAccessible(true);
            this.mSelectorRect = (Rect) declaredField.get(this);
            if (Build.VERSION.SDK_INT >= 14) {
                Field declaredField2 = AbsListView.class.getDeclaredField("mSelectorPosition");
                this.mSelectorPositionField = declaredField2;
                declaredField2.setAccessible(true);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public boolean performItemClick(View view, int i, long j) {
        if (view instanceof WrapperView) {
            view = ((WrapperView) view).mItem;
        }
        return super.performItemClick(view, i, j);
    }

    private void positionSelectorRect() {
        int selectorPosition;
        if (!this.mSelectorRect.isEmpty() && (selectorPosition = getSelectorPosition()) >= 0) {
            View childAt = getChildAt(selectorPosition - getFixedFirstVisibleItem());
            if (childAt instanceof WrapperView) {
                WrapperView wrapperView = (WrapperView) childAt;
                this.mSelectorRect.top = wrapperView.getTop() + wrapperView.mItemTop;
            }
        }
    }

    private int getSelectorPosition() {
        Field field = this.mSelectorPositionField;
        if (field == null) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i).getBottom() == this.mSelectorRect.bottom) {
                    return i + getFixedFirstVisibleItem();
                }
            }
            return -1;
        }
        try {
            return field.getInt(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        positionSelectorRect();
        if (this.mTopClippingLength != 0) {
            canvas.save();
            Rect clipBounds = canvas.getClipBounds();
            clipBounds.top = this.mTopClippingLength;
            canvas.clipRect(clipBounds);
            super.dispatchDraw(canvas);
            canvas.restore();
        } else {
            super.dispatchDraw(canvas);
        }
        this.mLifeCycleListener.onDispatchDrawOccurred(canvas);
    }

    /* access modifiers changed from: package-private */
    public void setLifeCycleListener(LifeCycleListener lifeCycleListener) {
        this.mLifeCycleListener = lifeCycleListener;
    }

    public void addFooterView(View view) {
        super.addFooterView(view);
        addInternalFooterView(view);
    }

    public void addFooterView(View view, Object obj, boolean z) {
        super.addFooterView(view, obj, z);
        addInternalFooterView(view);
    }

    private void addInternalFooterView(View view) {
        if (this.mFooterViews == null) {
            this.mFooterViews = new ArrayList();
        }
        this.mFooterViews.add(view);
    }

    public boolean removeFooterView(View view) {
        if (!super.removeFooterView(view)) {
            return false;
        }
        this.mFooterViews.remove(view);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean containsFooterView(View view) {
        List<View> list = this.mFooterViews;
        if (list == null) {
            return false;
        }
        return list.contains(view);
    }

    /* access modifiers changed from: package-private */
    public void setTopClippingLength(int i) {
        this.mTopClippingLength = i;
    }

    /* access modifiers changed from: package-private */
    public int getFixedFirstVisibleItem() {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (Build.VERSION.SDK_INT >= 11) {
            return firstVisiblePosition;
        }
        int i = 0;
        while (true) {
            if (i >= getChildCount()) {
                break;
            } else if (getChildAt(i).getBottom() >= 0) {
                firstVisiblePosition += i;
                break;
            } else {
                i++;
            }
        }
        return (this.mClippingToPadding || getPaddingTop() <= 0 || firstVisiblePosition <= 0 || getChildAt(0).getTop() <= 0) ? firstVisiblePosition : firstVisiblePosition - 1;
    }

    public void setClipToPadding(boolean z) {
        this.mClippingToPadding = z;
        super.setClipToPadding(z);
    }

    public void setBlockLayoutChildren(boolean z) {
        this.mBlockLayoutChildren = z;
    }

    /* access modifiers changed from: protected */
    public void layoutChildren() {
        if (!this.mBlockLayoutChildren) {
            super.layoutChildren();
        }
    }
}
