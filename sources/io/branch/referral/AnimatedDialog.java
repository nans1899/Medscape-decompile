package io.branch.referral;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

public class AnimatedDialog extends Dialog {
    private boolean isClosing_;
    private final boolean isFullWidthStyle_;

    public AnimatedDialog(Context context, boolean z) {
        super(context);
        this.isClosing_ = false;
        this.isFullWidthStyle_ = z;
        init(context);
    }

    public AnimatedDialog(Context context, int i) {
        super(context, i);
        this.isClosing_ = false;
        this.isFullWidthStyle_ = false;
        init(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.isFullWidthStyle_) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = -1;
            getWindow().setAttributes(attributes);
        }
    }

    public void show() {
        slideOpen();
    }

    public void cancel() {
        slideClose();
    }

    public void setContentView(int i) {
        setDialogWindowAttributes();
        super.setContentView(i);
    }

    private void init(Context context) {
        setDialogWindowAttributes();
        setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return true;
                }
                AnimatedDialog.this.slideClose();
                return true;
            }
        });
    }

    public void setDialogWindowAttributes() {
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().addFlags(2);
        getWindow().addFlags(1024);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 80;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
        getWindow().setWindowAnimations(17432578);
        setCanceledOnTouchOutside(true);
    }

    private void slideOpen() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        ((ViewGroup) getWindow().getDecorView()).getChildAt(0).startAnimation(translateAnimation);
        super.show();
    }

    /* access modifiers changed from: private */
    public void slideClose() {
        if (!this.isClosing_) {
            this.isClosing_ = true;
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            translateAnimation.setDuration(500);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            ((ViewGroup) getWindow().getDecorView()).getChildAt(0).startAnimation(translateAnimation);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    AnimatedDialog.this.dismiss();
                }
            });
        }
    }
}
