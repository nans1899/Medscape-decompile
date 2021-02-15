package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class WrapperView extends ViewGroup {
    Drawable mDivider;
    int mDividerHeight;
    View mHeader;
    View mItem;
    int mItemTop;

    WrapperView(Context context) {
        super(context);
    }

    public boolean hasHeader() {
        return this.mHeader != null;
    }

    public View getItem() {
        return this.mItem;
    }

    public View getHeader() {
        return this.mHeader;
    }

    /* access modifiers changed from: package-private */
    public void update(View view, View view2, Drawable drawable, int i) {
        if (view != null) {
            View view3 = this.mItem;
            if (view3 != view) {
                removeView(view3);
                this.mItem = view;
                ViewParent parent = view.getParent();
                if (!(parent == null || parent == this || !(parent instanceof ViewGroup))) {
                    ((ViewGroup) parent).removeView(view);
                }
                addView(view);
            }
            View view4 = this.mHeader;
            if (view4 != view2) {
                if (view4 != null) {
                    removeView(view4);
                }
                this.mHeader = view2;
                if (view2 != null) {
                    addView(view2);
                }
            }
            if (this.mDivider != drawable) {
                this.mDivider = drawable;
                this.mDividerHeight = i;
                invalidate();
                return;
            }
            return;
        }
        throw new NullPointerException("List view item must not be null.");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int size = View.MeasureSpec.getSize(i);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
        View view = this.mHeader;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                this.mHeader.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
            } else {
                this.mHeader.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
            }
            i3 = this.mHeader.getMeasuredHeight() + 0;
        } else {
            i3 = (this.mDivider == null || this.mItem.getVisibility() == 8) ? 0 : this.mDividerHeight + 0;
        }
        ViewGroup.LayoutParams layoutParams2 = this.mItem.getLayoutParams();
        if (this.mItem.getVisibility() == 8) {
            this.mItem.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 1073741824));
        } else {
            if (layoutParams2 == null || layoutParams2.height < 0) {
                this.mItem.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
                i4 = this.mItem.getMeasuredHeight();
            } else {
                this.mItem.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824));
                i4 = this.mItem.getMeasuredHeight();
            }
            i3 += i4;
        }
        setMeasuredDimension(size, i3);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int height = getHeight();
        View view = this.mHeader;
        if (view != null) {
            int measuredHeight = view.getMeasuredHeight();
            this.mHeader.layout(0, 0, width, measuredHeight);
            this.mItemTop = measuredHeight;
            this.mItem.layout(0, measuredHeight, width, height);
            return;
        }
        Drawable drawable = this.mDivider;
        if (drawable != null) {
            drawable.setBounds(0, 0, width, this.mDividerHeight);
            int i5 = this.mDividerHeight;
            this.mItemTop = i5;
            this.mItem.layout(0, i5, width, height);
            return;
        }
        this.mItemTop = 0;
        this.mItem.layout(0, 0, width, height);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mHeader == null && this.mDivider != null && this.mItem.getVisibility() != 8) {
            if (Build.VERSION.SDK_INT < 11) {
                canvas.clipRect(0, 0, getWidth(), this.mDividerHeight);
            }
            this.mDivider.draw(canvas);
        }
    }
}
