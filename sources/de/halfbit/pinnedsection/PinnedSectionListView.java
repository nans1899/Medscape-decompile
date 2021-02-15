package de.halfbit.pinnedsection;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class PinnedSectionListView extends ListView {
    private final DataSetObserver mDataSetObserver = new DataSetObserver() {
        public void onChanged() {
            PinnedSectionListView.this.recreatePinnedShadow();
        }

        public void onInvalidated() {
            PinnedSectionListView.this.recreatePinnedShadow();
        }
    };
    AbsListView.OnScrollListener mDelegateOnScrollListener;
    private MotionEvent mDownEvent;
    private final AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (PinnedSectionListView.this.mDelegateOnScrollListener != null) {
                PinnedSectionListView.this.mDelegateOnScrollListener.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (PinnedSectionListView.this.mDelegateOnScrollListener != null) {
                PinnedSectionListView.this.mDelegateOnScrollListener.onScroll(absListView, i, i2, i3);
            }
            ListAdapter adapter = PinnedSectionListView.this.getAdapter();
            if (adapter != null && i2 != 0) {
                if (!PinnedSectionListView.isItemViewTypePinned(adapter, adapter.getItemViewType(i))) {
                    int findCurrentSectionPosition = PinnedSectionListView.this.findCurrentSectionPosition(i);
                    if (findCurrentSectionPosition > -1) {
                        PinnedSectionListView.this.ensureShadowForPosition(findCurrentSectionPosition, i, i2);
                    } else {
                        PinnedSectionListView.this.destroyPinnedShadow();
                    }
                } else if (PinnedSectionListView.this.getChildAt(0).getTop() == PinnedSectionListView.this.getPaddingTop()) {
                    PinnedSectionListView.this.destroyPinnedShadow();
                } else {
                    PinnedSectionListView.this.ensureShadowForPosition(i, i, i2);
                }
            }
        }
    };
    PinnedSection mPinnedSection;
    PinnedSection mRecycleSection;
    private int mSectionsDistanceY;
    private GradientDrawable mShadowDrawable;
    private int mShadowHeight;
    private final PointF mTouchPoint = new PointF();
    private final Rect mTouchRect = new Rect();
    private int mTouchSlop;
    private View mTouchTarget;
    int mTranslateY;

    public interface PinnedSectionListAdapter extends ListAdapter {
        boolean isItemViewTypePinned(int i);
    }

    static class PinnedSection {
        public long id;
        public int position;
        public View view;

        PinnedSection() {
        }
    }

    public PinnedSectionListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public PinnedSectionListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        setOnScrollListener(this.mOnScrollListener);
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        initShadow(true);
    }

    public void setShadowVisible(boolean z) {
        initShadow(z);
        PinnedSection pinnedSection = this.mPinnedSection;
        if (pinnedSection != null) {
            View view = pinnedSection.view;
            invalidate(view.getLeft(), view.getTop(), view.getRight(), view.getBottom() + this.mShadowHeight);
        }
    }

    public void initShadow(boolean z) {
        if (z) {
            if (this.mShadowDrawable == null) {
                this.mShadowDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor("#ffa0a0a0"), Color.parseColor("#50a0a0a0"), Color.parseColor("#00a0a0a0")});
                this.mShadowHeight = (int) (getResources().getDisplayMetrics().density * 8.0f);
            }
        } else if (this.mShadowDrawable != null) {
            this.mShadowDrawable = null;
            this.mShadowHeight = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void createPinnedShadow(int i) {
        PinnedSection pinnedSection = this.mRecycleSection;
        this.mRecycleSection = null;
        if (pinnedSection == null) {
            pinnedSection = new PinnedSection();
        }
        View view = getAdapter().getView(i, pinnedSection.view, this);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
            view.setLayoutParams(layoutParams);
        }
        int mode = View.MeasureSpec.getMode(layoutParams.height);
        int size = View.MeasureSpec.getSize(layoutParams.height);
        if (mode == 0) {
            mode = 1073741824;
        }
        int height = (getHeight() - getListPaddingTop()) - getListPaddingBottom();
        if (size > height) {
            size = height;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getWidth() - getListPaddingLeft()) - getListPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec(size, mode));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        this.mTranslateY = 0;
        pinnedSection.view = view;
        pinnedSection.position = i;
        pinnedSection.id = getAdapter().getItemId(i);
        this.mPinnedSection = pinnedSection;
    }

    /* access modifiers changed from: package-private */
    public void destroyPinnedShadow() {
        PinnedSection pinnedSection = this.mPinnedSection;
        if (pinnedSection != null) {
            this.mRecycleSection = pinnedSection;
            this.mPinnedSection = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureShadowForPosition(int i, int i2, int i3) {
        if (i3 < 2) {
            destroyPinnedShadow();
            return;
        }
        PinnedSection pinnedSection = this.mPinnedSection;
        if (!(pinnedSection == null || pinnedSection.position == i)) {
            destroyPinnedShadow();
        }
        if (this.mPinnedSection == null) {
            createPinnedShadow(i);
        }
        int i4 = i + 1;
        if (i4 < getCount()) {
            int findFirstVisibleSectionPosition = findFirstVisibleSectionPosition(i4, i3 - (i4 - i2));
            if (findFirstVisibleSectionPosition > -1) {
                int top = getChildAt(findFirstVisibleSectionPosition - i2).getTop() - (this.mPinnedSection.view.getBottom() + getPaddingTop());
                this.mSectionsDistanceY = top;
                if (top < 0) {
                    this.mTranslateY = top;
                } else {
                    this.mTranslateY = 0;
                }
            } else {
                this.mTranslateY = 0;
                this.mSectionsDistanceY = Integer.MAX_VALUE;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int findFirstVisibleSectionPosition(int i, int i2) {
        ListAdapter adapter = getAdapter();
        int count = adapter.getCount();
        if (getLastVisiblePosition() >= count) {
            return -1;
        }
        if (i + i2 >= count) {
            i2 = count - i;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i + i3;
            if (isItemViewTypePinned(adapter, adapter.getItemViewType(i4))) {
                return i4;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int findCurrentSectionPosition(int i) {
        ListAdapter adapter = getAdapter();
        if (i >= adapter.getCount()) {
            return -1;
        }
        if (adapter instanceof SectionIndexer) {
            SectionIndexer sectionIndexer = (SectionIndexer) adapter;
            int positionForSection = sectionIndexer.getPositionForSection(sectionIndexer.getSectionForPosition(i));
            if (isItemViewTypePinned(adapter, adapter.getItemViewType(positionForSection))) {
                return positionForSection;
            }
        }
        while (i >= 0) {
            if (isItemViewTypePinned(adapter, adapter.getItemViewType(i))) {
                return i;
            }
            i--;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
        r0 = getFirstVisiblePosition();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void recreatePinnedShadow() {
        /*
            r3 = this;
            r3.destroyPinnedShadow()
            android.widget.ListAdapter r0 = r3.getAdapter()
            if (r0 == 0) goto L_0x0023
            int r0 = r0.getCount()
            if (r0 <= 0) goto L_0x0023
            int r0 = r3.getFirstVisiblePosition()
            int r1 = r3.findCurrentSectionPosition(r0)
            r2 = -1
            if (r1 != r2) goto L_0x001b
            return
        L_0x001b:
            int r2 = r3.getLastVisiblePosition()
            int r2 = r2 - r0
            r3.ensureShadowForPosition(r1, r0, r2)
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: de.halfbit.pinnedsection.PinnedSectionListView.recreatePinnedShadow():void");
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        if (onScrollListener == this.mOnScrollListener) {
            super.setOnScrollListener(onScrollListener);
        } else {
            this.mDelegateOnScrollListener = onScrollListener;
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        post(new Runnable() {
            public void run() {
                PinnedSectionListView.this.recreatePinnedShadow();
            }
        });
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            if (!(listAdapter instanceof PinnedSectionListAdapter)) {
                throw new IllegalArgumentException("Does your adapter implement PinnedSectionListAdapter?");
            } else if (listAdapter.getViewTypeCount() < 2) {
                throw new IllegalArgumentException("Does your adapter handle at least two types of views in getViewTypeCount() method: items and sections?");
            }
        }
        ListAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mDataSetObserver);
        }
        if (adapter != listAdapter) {
            destroyPinnedShadow();
        }
        super.setAdapter(listAdapter);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mPinnedSection != null && ((i3 - i) - getPaddingLeft()) - getPaddingRight() != this.mPinnedSection.view.getWidth()) {
            recreatePinnedShadow();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int i;
        super.dispatchDraw(canvas);
        if (this.mPinnedSection != null) {
            int listPaddingLeft = getListPaddingLeft();
            int listPaddingTop = getListPaddingTop();
            View view = this.mPinnedSection.view;
            canvas.save();
            int height = view.getHeight();
            if (this.mShadowDrawable == null) {
                i = 0;
            } else {
                i = Math.min(this.mShadowHeight, this.mSectionsDistanceY);
            }
            canvas.clipRect(listPaddingLeft, listPaddingTop, view.getWidth() + listPaddingLeft, height + i + listPaddingTop);
            canvas.translate((float) listPaddingLeft, (float) (listPaddingTop + this.mTranslateY));
            drawChild(canvas, this.mPinnedSection.view, getDrawingTime());
            GradientDrawable gradientDrawable = this.mShadowDrawable;
            if (gradientDrawable != null && this.mSectionsDistanceY > 0) {
                gradientDrawable.setBounds(this.mPinnedSection.view.getLeft(), this.mPinnedSection.view.getBottom(), this.mPinnedSection.view.getRight(), this.mPinnedSection.view.getBottom() + this.mShadowHeight);
                this.mShadowDrawable.draw(canvas);
            }
            canvas.restore();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        PinnedSection pinnedSection;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0 && this.mTouchTarget == null && (pinnedSection = this.mPinnedSection) != null && isPinnedViewTouched(pinnedSection.view, x, y)) {
            this.mTouchTarget = this.mPinnedSection.view;
            this.mTouchPoint.x = x;
            this.mTouchPoint.y = y;
            this.mDownEvent = MotionEvent.obtain(motionEvent);
        }
        View view = this.mTouchTarget;
        if (view == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (isPinnedViewTouched(view, x, y)) {
            this.mTouchTarget.dispatchTouchEvent(motionEvent);
        }
        if (action == 1) {
            super.dispatchTouchEvent(motionEvent);
            performPinnedItemClick();
            clearTouchTarget();
        } else if (action == 3) {
            clearTouchTarget();
        } else if (action == 2 && Math.abs(y - this.mTouchPoint.y) > ((float) this.mTouchSlop)) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            this.mTouchTarget.dispatchTouchEvent(obtain);
            obtain.recycle();
            super.dispatchTouchEvent(this.mDownEvent);
            super.dispatchTouchEvent(motionEvent);
            clearTouchTarget();
        }
        return true;
    }

    private boolean isPinnedViewTouched(View view, float f, float f2) {
        view.getHitRect(this.mTouchRect);
        this.mTouchRect.top += this.mTranslateY;
        this.mTouchRect.bottom += this.mTranslateY + getPaddingTop();
        this.mTouchRect.left += getPaddingLeft();
        this.mTouchRect.right -= getPaddingRight();
        return this.mTouchRect.contains((int) f, (int) f2);
    }

    private void clearTouchTarget() {
        this.mTouchTarget = null;
        MotionEvent motionEvent = this.mDownEvent;
        if (motionEvent != null) {
            motionEvent.recycle();
            this.mDownEvent = null;
        }
    }

    private boolean performPinnedItemClick() {
        AdapterView.OnItemClickListener onItemClickListener;
        if (this.mPinnedSection == null || (onItemClickListener = getOnItemClickListener()) == null || !getAdapter().isEnabled(this.mPinnedSection.position)) {
            return false;
        }
        View view = this.mPinnedSection.view;
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        onItemClickListener.onItemClick(this, view, this.mPinnedSection.position, this.mPinnedSection.id);
        return true;
    }

    public static boolean isItemViewTypePinned(ListAdapter listAdapter, int i) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            listAdapter = ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        }
        return ((PinnedSectionListAdapter) listAdapter).isItemViewTypePinned(i);
    }
}
