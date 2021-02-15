package com.appboy.ui.inappmessage.listeners;

import android.view.MotionEvent;
import android.view.View;
import com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener;

public class TouchAwareSwipeDismissTouchListener extends SwipeDismissTouchListener {
    private ITouchListener mTouchListener;

    public interface ITouchListener {
        void onTouchEnded();

        void onTouchStartedOrContinued();
    }

    public TouchAwareSwipeDismissTouchListener(View view, Object obj, SwipeDismissTouchListener.DismissCallbacks dismissCallbacks) {
        super(view, obj, dismissCallbacks);
    }

    public void setTouchListener(ITouchListener iTouchListener) {
        this.mTouchListener = iTouchListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        ITouchListener iTouchListener;
        int action = motionEvent.getAction();
        if (action == 0) {
            ITouchListener iTouchListener2 = this.mTouchListener;
            if (iTouchListener2 != null) {
                iTouchListener2.onTouchStartedOrContinued();
            }
        } else if ((action == 1 || action == 3) && (iTouchListener = this.mTouchListener) != null) {
            iTouchListener.onTouchEnded();
        }
        return super.onTouch(view, motionEvent);
    }
}
