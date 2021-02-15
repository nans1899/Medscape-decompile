package com.medscape.android.slideshow;

import android.view.View;
import android.view.animation.Animation;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SequentialAnimationSet {
    Queue<Map.Entry<Animation, View>> animations = new LinkedBlockingQueue();
    Map.Entry<Animation, View> currentAnimation;

    public void add(Map.Entry<Animation, View> entry) {
        this.animations.add(entry);
    }

    public void playSequence() {
        Map.Entry<Animation, View> poll = this.animations.poll();
        this.currentAnimation = poll;
        if (poll != null) {
            Animation key = poll.getKey();
            key.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    SequentialAnimationSet.this.playSequence();
                }
            });
            this.currentAnimation.getValue().startAnimation(key);
        }
    }

    public boolean empty() {
        return this.animations.isEmpty();
    }

    public boolean clearAnimation() {
        this.animations.clear();
        Map.Entry<Animation, View> entry = this.currentAnimation;
        if (entry == null) {
            return true;
        }
        entry.getValue().clearAnimation();
        return true;
    }
}
