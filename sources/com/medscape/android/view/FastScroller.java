package com.medscape.android.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.custom.FastScrollerAdapter;

public class FastScroller extends LinearLayout {
    private static final String ALPHA = "alpha";
    private static final int HANDLE_ANIMATION_DURATION = 50;
    private static final int HANDLE_HIDE_DELAY = 500;
    private static final String SCALE_X = "scaleX";
    private static final String SCALE_Y = "scaleY";
    private static final int TRACK_SNAP_RANGE = 5;
    private View bubble;
    /* access modifiers changed from: private */
    public AnimatorSet currentAnimator = null;
    /* access modifiers changed from: private */
    public View handle;
    private final HandleHider handleHider = new HandleHider();
    /* access modifiers changed from: private */
    public int height;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    private final ScrollListener scrollListener = new ScrollListener();
    /* access modifiers changed from: private */
    public TextView title;

    public FastScroller(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialise(context);
    }

    public FastScroller(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialise(context);
    }

    private void initialise(Context context) {
        setOrientation(0);
        setClipChildren(false);
        LayoutInflater.from(context).inflate(R.layout.fastscroller, this);
        this.bubble = findViewById(R.id.fastscroller_bubble);
        this.handle = findViewById(R.id.fastscroller_handle);
        this.title = (TextView) findViewById(R.id.fastscroller_handle_text);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.height = i2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isScrollMarginTouched(motionEvent)) {
            if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
                setPosition(motionEvent.getY());
                AnimatorSet animatorSet = this.currentAnimator;
                if (animatorSet != null) {
                    animatorSet.cancel();
                }
                getHandler().removeCallbacks(this.handleHider);
                if (this.handle.getVisibility() == 4) {
                    showHandle();
                }
                setRecyclerViewPosition(motionEvent.getY());
                return true;
            } else if (motionEvent.getAction() == 1) {
                getHandler().postDelayed(this.handleHider, 500);
                return true;
            }
        }
        getHandler().postDelayed(this.handleHider, 500);
        return super.onTouchEvent(motionEvent);
    }

    private boolean isScrollMarginTouched(MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        motionEvent.getRawY();
        int[] iArr = new int[2];
        this.bubble.getLocationOnScreen(iArr);
        if (rawX >= iArr[0] - 50) {
            return true;
        }
        return false;
    }

    public void setRecyclerView(RecyclerView recyclerView2) {
        this.recyclerView = recyclerView2;
        recyclerView2.setOnScrollListener(this.scrollListener);
        this.recyclerView.getLayoutManager();
    }

    private void setRecyclerViewPosition(float f) {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            int itemCount = recyclerView2.getAdapter().getItemCount();
            float f2 = 0.0f;
            if (this.bubble.getY() != 0.0f) {
                float y = this.bubble.getY() + ((float) this.bubble.getHeight());
                int i = this.height;
                f2 = y >= ((float) (i + -5)) ? 1.0f : f / ((float) i);
            }
            this.recyclerView.scrollToPosition(getValueInRange(0, itemCount - 1, (int) (f2 * ((float) itemCount))));
        }
    }

    private int getValueInRange(int i, int i2, int i3) {
        return Math.min(Math.max(i, i3), i2);
    }

    /* access modifiers changed from: private */
    public void setPosition(float f) {
        float f2 = f / ((float) this.height);
        int height2 = this.bubble.getHeight();
        int height3 = this.handle.getHeight() - (height2 / 2);
        int i = this.height;
        float f3 = (float) height3;
        int i2 = (int) ((((float) i) * f2) - f3);
        if (((float) i) * f2 <= f3) {
            i2 = 0;
        }
        View view = this.bubble;
        int i3 = this.height;
        view.setY((float) getValueInRange(0, i3 - height2, (int) (((float) i3) * f2)));
        this.bubble.getTop();
        this.bubble.getBottom();
        this.handle.setTranslationY((float) getValueInRange(0, (this.height - height3) - height2, i2));
    }

    private void showHandle() {
        AnimatorSet animatorSet = new AnimatorSet();
        View view = this.handle;
        view.setPivotX((float) view.getWidth());
        View view2 = this.handle;
        view2.setPivotY((float) view2.getHeight());
        this.handle.setVisibility(0);
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.handle, SCALE_X, new float[]{0.0f, 1.0f}).setDuration(50), ObjectAnimator.ofFloat(this.handle, SCALE_Y, new float[]{0.0f, 1.0f}).setDuration(50), ObjectAnimator.ofFloat(this.handle, ALPHA, new float[]{0.0f, 1.0f}).setDuration(50)});
        animatorSet.start();
    }

    /* access modifiers changed from: private */
    public void hideHandle() {
        this.currentAnimator = new AnimatorSet();
        View view = this.handle;
        view.setPivotX((float) view.getWidth());
        View view2 = this.handle;
        view2.setPivotY((float) view2.getHeight());
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.handle, SCALE_X, new float[]{1.0f, 0.0f}).setDuration(50);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(this.handle, SCALE_Y, new float[]{1.0f, 0.0f}).setDuration(50);
        ObjectAnimator duration3 = ObjectAnimator.ofFloat(this.handle, ALPHA, new float[]{1.0f, 0.0f}).setDuration(50);
        this.currentAnimator.playTogether(new Animator[]{duration, duration2, duration3});
        this.currentAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                FastScroller.this.handle.setVisibility(4);
                AnimatorSet unused = FastScroller.this.currentAnimator = null;
            }

            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                FastScroller.this.handle.setVisibility(4);
                AnimatorSet unused = FastScroller.this.currentAnimator = null;
            }
        });
        this.currentAnimator.start();
    }

    private class HandleHider implements Runnable {
        private HandleHider() {
        }

        public void run() {
            FastScroller.this.hideHandle();
        }
    }

    private class ScrollListener extends RecyclerView.OnScrollListener {
        private ScrollListener() {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            int i3 = 0;
            int childPosition = FastScroller.this.recyclerView.getChildPosition(FastScroller.this.recyclerView.getChildAt(0));
            int childCount = FastScroller.this.recyclerView.getChildCount() + childPosition;
            int itemCount = FastScroller.this.recyclerView.getAdapter().getItemCount();
            if (!(childPosition == 0 || childCount == itemCount - 1)) {
                i3 = childPosition;
            }
            float f = ((float) i3) / ((float) itemCount);
            FastScroller fastScroller = FastScroller.this;
            fastScroller.setPosition(((float) fastScroller.height) * f);
            if (recyclerView.getAdapter() instanceof FastScrollerAdapter) {
                FastScroller.this.title.setText(((FastScrollerAdapter) recyclerView.getAdapter()).getCurrentSectionTitle());
            }
        }
    }
}
