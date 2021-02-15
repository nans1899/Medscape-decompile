package com.medscape.android.reference.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.medscape.android.reference.interfaces.AccordionAnimation;
import java.util.ArrayList;
import java.util.List;

public class AnimatedExpandableListView extends ExpandableListView implements AccordionAnimation {
    private static final int ANIMATION_DURATION = 100;
    private static final String TAG = AnimatedExpandableListAdapter.class.getSimpleName();
    private AnimatedExpandableListAdapter adapter;
    private int currentExpandedGroupPosition = -1;

    /* access modifiers changed from: private */
    public int getAnimationDuration() {
        return 100;
    }

    public AnimatedExpandableListView(Context context) {
        super(context);
    }

    public AnimatedExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimatedExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAdapter(ExpandableListAdapter expandableListAdapter) {
        super.setAdapter(expandableListAdapter);
        if (expandableListAdapter instanceof AnimatedExpandableListAdapter) {
            AnimatedExpandableListAdapter animatedExpandableListAdapter = (AnimatedExpandableListAdapter) expandableListAdapter;
            this.adapter = animatedExpandableListAdapter;
            animatedExpandableListAdapter.setParent(this);
            return;
        }
        throw new ClassCastException(expandableListAdapter.toString() + " must implement AnimatedExpandableListAdapter");
    }

    public boolean expandGroupWithAnimation(int i) {
        int firstVisiblePosition;
        View childAt;
        this.currentExpandedGroupPosition = i;
        if ((i == this.adapter.getGroupCount() - 1) && Build.VERSION.SDK_INT >= 14) {
            return expandGroup(i, true);
        }
        int flatListPosition = getFlatListPosition(getPackedPositionForGroup(i));
        if (flatListPosition == -1 || (firstVisiblePosition = flatListPosition - getFirstVisiblePosition()) >= getChildCount() || (childAt = getChildAt(firstVisiblePosition)) == null || childAt.getBottom() < getBottom()) {
            this.adapter.startExpandAnimation(i, 0);
            return expandGroup(i);
        }
        this.adapter.notifyGroupExpanded(i);
        return expandGroup(i);
    }

    public boolean collapseGroupWithAnimation(int i) {
        resetCurrentExpandedGroupPosition();
        int flatListPosition = getFlatListPosition(getPackedPositionForGroup(i));
        if (flatListPosition != -1) {
            int firstVisiblePosition = flatListPosition - getFirstVisiblePosition();
            if (firstVisiblePosition < 0 || firstVisiblePosition >= getChildCount()) {
                return collapseGroup(i);
            }
            if (getChildAt(firstVisiblePosition).getBottom() >= getBottom()) {
                return collapseGroup(i);
            }
        }
        long expandableListPosition = getExpandableListPosition(getFirstVisiblePosition());
        int packedPositionChild = getPackedPositionChild(expandableListPosition);
        int packedPositionGroup = getPackedPositionGroup(expandableListPosition);
        if (packedPositionChild == -1 || packedPositionGroup != i) {
            packedPositionChild = 0;
        }
        this.adapter.startCollapseAnimation(i, packedPositionChild);
        this.adapter.notifyDataSetChanged();
        return isGroupExpanded(i);
    }

    public int getCurrentExpandedGroupPosition() {
        return this.currentExpandedGroupPosition;
    }

    public void resetCurrentExpandedGroupPosition() {
        this.currentExpandedGroupPosition = -1;
    }

    public boolean collapseCurrentlyExpandedGroup() {
        int currentExpandedGroupPosition2 = getCurrentExpandedGroupPosition();
        if (currentExpandedGroupPosition2 < 0) {
            return true;
        }
        collapseGroupWithAnimation(currentExpandedGroupPosition2);
        return true;
    }

    private static class GroupInfo {
        boolean animating;
        int dummyHeight;
        boolean expanding;
        int firstChildPosition;

        private GroupInfo() {
            this.animating = false;
            this.expanding = false;
            this.dummyHeight = -1;
        }
    }

    public static abstract class AnimatedExpandableListAdapter extends BaseExpandableListAdapter {
        private static final int STATE_COLLAPSING = 2;
        private static final int STATE_EXPANDING = 1;
        private static final int STATE_IDLE = 0;
        private SparseArray<GroupInfo> groupInfo = new SparseArray<>();
        private AnimatedExpandableListView parent;

        public int getRealChildType(int i, int i2) {
            return 0;
        }

        public int getRealChildTypeCount() {
            return 1;
        }

        public abstract View getRealChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup);

        public abstract int getRealChildrenCount(int i);

        /* access modifiers changed from: private */
        public void setParent(AnimatedExpandableListView animatedExpandableListView) {
            this.parent = animatedExpandableListView;
        }

        private GroupInfo getGroupInfo(int i) {
            GroupInfo groupInfo2 = this.groupInfo.get(i);
            if (groupInfo2 != null) {
                return groupInfo2;
            }
            GroupInfo groupInfo3 = new GroupInfo();
            this.groupInfo.put(i, groupInfo3);
            return groupInfo3;
        }

        public void notifyGroupExpanded(int i) {
            getGroupInfo(i).dummyHeight = -1;
        }

        /* access modifiers changed from: private */
        public void startExpandAnimation(int i, int i2) {
            GroupInfo groupInfo2 = getGroupInfo(i);
            groupInfo2.animating = true;
            groupInfo2.firstChildPosition = i2;
            groupInfo2.expanding = true;
        }

        /* access modifiers changed from: private */
        public void startCollapseAnimation(int i, int i2) {
            GroupInfo groupInfo2 = getGroupInfo(i);
            groupInfo2.animating = true;
            groupInfo2.firstChildPosition = i2;
            groupInfo2.expanding = false;
        }

        /* access modifiers changed from: private */
        public void stopAnimation(int i) {
            getGroupInfo(i).animating = false;
        }

        public final int getChildType(int i, int i2) {
            if (getGroupInfo(i).animating) {
                return 0;
            }
            return getRealChildType(i, i2) + 1;
        }

        public final int getChildTypeCount() {
            return getRealChildTypeCount() + 1;
        }

        /* access modifiers changed from: protected */
        public ViewGroup.LayoutParams generateDefaultLayoutParams() {
            return new AbsListView.LayoutParams(-1, -2, 0);
        }

        public final View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            int i3;
            int i4;
            GroupInfo groupInfo2 = getGroupInfo(i);
            if (groupInfo2.animating) {
                View view2 = view;
                if (!(view2 instanceof DummyView)) {
                    view2 = new DummyView(viewGroup.getContext());
                    view2.setLayoutParams(new AbsListView.LayoutParams(-1, 0));
                }
                View view3 = view2;
                if (i2 < groupInfo2.firstChildPosition) {
                    view3.getLayoutParams().height = 0;
                    return view3;
                }
                ExpandableListView expandableListView = (ExpandableListView) viewGroup;
                final DummyView dummyView = (DummyView) view3;
                dummyView.clearViews();
                dummyView.setDivider(expandableListView.getDivider(), viewGroup.getMeasuredWidth(), expandableListView.getDividerHeight());
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(viewGroup.getWidth(), 1073741824);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
                int height = viewGroup.getHeight();
                int realChildrenCount = getRealChildrenCount(i);
                int i5 = groupInfo2.firstChildPosition;
                int i6 = 0;
                while (true) {
                    if (i5 >= realChildrenCount) {
                        i3 = 1;
                        i4 = i6;
                        break;
                    }
                    i3 = 1;
                    int i7 = i5;
                    int i8 = realChildrenCount;
                    int i9 = height;
                    View realChildView = getRealChildView(i, i5, i5 == realChildrenCount + -1, (View) null, viewGroup);
                    AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) realChildView.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = (AbsListView.LayoutParams) generateDefaultLayoutParams();
                        realChildView.setLayoutParams(layoutParams);
                    }
                    int i10 = layoutParams.height;
                    realChildView.measure(makeMeasureSpec, i10 > 0 ? View.MeasureSpec.makeMeasureSpec(i10, 1073741824) : makeMeasureSpec2);
                    int measuredHeight = i6 + realChildView.getMeasuredHeight();
                    if (measuredHeight >= i9) {
                        dummyView.addFakeView(realChildView);
                        i4 = measuredHeight + (((i8 - i7) - 1) * (measuredHeight / (i7 + 1)));
                        break;
                    }
                    dummyView.addFakeView(realChildView);
                    i5 = i7 + 1;
                    i6 = measuredHeight;
                    height = i9;
                    realChildrenCount = i8;
                }
                Object tag = dummyView.getTag();
                int intValue = tag == null ? 0 : ((Integer) tag).intValue();
                if (!groupInfo2.expanding || intValue == i3) {
                    int i11 = i;
                    if (!groupInfo2.expanding && intValue != 2) {
                        if (groupInfo2.dummyHeight == -1) {
                            groupInfo2.dummyHeight = i4;
                        }
                        final GroupInfo groupInfo3 = groupInfo2;
                        ExpandAnimation expandAnimation = new ExpandAnimation(dummyView, groupInfo2.dummyHeight, 0, groupInfo3);
                        expandAnimation.setDuration((long) this.parent.getAnimationDuration());
                        final int i12 = i;
                        final ExpandableListView expandableListView2 = expandableListView;
                        final DummyView dummyView2 = dummyView;
                        expandAnimation.setAnimationListener(new Animation.AnimationListener() {
                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                                AnimatedExpandableListAdapter.this.stopAnimation(i12);
                                expandableListView2.collapseGroup(i12);
                                AnimatedExpandableListAdapter.this.notifyDataSetChanged();
                                groupInfo3.dummyHeight = -1;
                                dummyView2.setTag(0);
                            }
                        });
                        dummyView.startAnimation(expandAnimation);
                        dummyView.setTag(2);
                    }
                } else {
                    ExpandAnimation expandAnimation2 = new ExpandAnimation(dummyView, 0, i4, groupInfo2);
                    expandAnimation2.setDuration((long) this.parent.getAnimationDuration());
                    final int i13 = i;
                    expandAnimation2.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            AnimatedExpandableListAdapter.this.stopAnimation(i13);
                            AnimatedExpandableListAdapter.this.notifyDataSetChanged();
                            dummyView.setTag(0);
                        }
                    });
                    dummyView.startAnimation(expandAnimation2);
                    dummyView.setTag(Integer.valueOf(i3));
                }
                return view3;
            }
            int i14 = i;
            int i15 = i2;
            View view4 = view;
            return getRealChildView(i, i2, z, view, viewGroup);
        }

        public final int getChildrenCount(int i) {
            GroupInfo groupInfo2 = getGroupInfo(i);
            if (groupInfo2.animating) {
                return groupInfo2.firstChildPosition + 1;
            }
            return getRealChildrenCount(i);
        }
    }

    private static class DummyView extends View {
        private Drawable divider;
        private int dividerHeight;
        private int dividerWidth;
        private List<View> views = new ArrayList();

        public DummyView(Context context) {
            super(context);
        }

        public void setDivider(Drawable drawable, int i, int i2) {
            if (drawable != null) {
                this.divider = drawable;
                this.dividerWidth = i;
                this.dividerHeight = i2;
                drawable.setBounds(0, 0, i, i2);
            }
        }

        public void addFakeView(View view) {
            view.layout(0, 0, getWidth(), view.getMeasuredHeight());
            this.views.add(view);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            int size = this.views.size();
            for (int i5 = 0; i5 < size; i5++) {
                View view = this.views.get(i5);
                view.layout(i, i2, view.getMeasuredWidth() + i, view.getMeasuredHeight() + i2);
            }
        }

        public void clearViews() {
            this.views.clear();
        }

        public void dispatchDraw(Canvas canvas) {
            canvas.save();
            Drawable drawable = this.divider;
            if (drawable != null) {
                drawable.setBounds(0, 0, this.dividerWidth, this.dividerHeight);
            }
            int size = this.views.size();
            for (int i = 0; i < size; i++) {
                View view = this.views.get(i);
                canvas.save();
                canvas.clipRect(0, 0, getWidth(), view.getMeasuredHeight());
                view.draw(canvas);
                canvas.restore();
                Drawable drawable2 = this.divider;
                if (drawable2 != null) {
                    drawable2.draw(canvas);
                    canvas.translate(0.0f, (float) this.dividerHeight);
                }
                canvas.translate(0.0f, (float) view.getMeasuredHeight());
            }
            canvas.restore();
        }
    }

    private static class ExpandAnimation extends Animation {
        private int baseHeight;
        private int delta;
        private GroupInfo groupInfo;
        private View view;

        private ExpandAnimation(View view2, int i, int i2, GroupInfo groupInfo2) {
            this.baseHeight = i;
            this.delta = i2 - i;
            this.view = view2;
            this.groupInfo = groupInfo2;
            view2.getLayoutParams().height = i;
            this.view.requestLayout();
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            if (f < 1.0f) {
                int i = this.baseHeight + ((int) (((float) this.delta) * f));
                this.view.getLayoutParams().height = i;
                this.groupInfo.dummyHeight = i;
                this.view.requestLayout();
                return;
            }
            int i2 = this.baseHeight + this.delta;
            this.view.getLayoutParams().height = i2;
            this.groupInfo.dummyHeight = i2;
            this.view.requestLayout();
        }
    }
}
