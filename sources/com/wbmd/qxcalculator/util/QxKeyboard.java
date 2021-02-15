package com.wbmd.qxcalculator.util;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatEditText;

public class QxKeyboard {
    public static final int CodeCancel = -3;
    public static final int CodeDelete = -5;
    public static final int CodePlusMinus = -6;
    public static final int CodeSave = -4;
    private static final int KB_ANIM_DURATION = 200;
    /* access modifiers changed from: private */
    public View containerView;
    /* access modifiers changed from: private */
    public ValueAnimator hideKbAnimator;
    /* access modifiers changed from: private */
    public boolean hideKbAnimatorCancelled = false;
    /* access modifiers changed from: private */
    public KeyboardState keyboardState = KeyboardState.HIDDEN;
    /* access modifiers changed from: private */
    public Activity mHostActivity;
    private KeyboardView mKeyboardView;
    private KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        public void onPress(int i) {
        }

        public void onRelease(int i) {
        }

        public void onText(CharSequence charSequence) {
        }

        public void swipeDown() {
        }

        public void swipeLeft() {
        }

        public void swipeRight() {
        }

        public void swipeUp() {
        }

        public void onKey(int i, int[] iArr) {
            View currentFocus = QxKeyboard.this.mHostActivity.getWindow().getCurrentFocus();
            if (currentFocus == null) {
                return;
            }
            if (currentFocus.getClass() == EditText.class || currentFocus.getClass() == AppCompatEditText.class) {
                EditText editText = (EditText) currentFocus;
                Editable text = editText.getText();
                int selectionStart = editText.getSelectionStart();
                int selectionEnd = editText.getSelectionEnd();
                if (i == -3) {
                    QxKeyboard.this.hideCustomKeyboard();
                } else if (i == -5) {
                    if (text == null) {
                        return;
                    }
                    if (selectionStart != selectionEnd) {
                        text.delete(selectionStart, selectionEnd);
                    } else if (selectionStart > 0) {
                        text.delete(selectionStart - 1, selectionStart);
                    }
                } else if (i == -4) {
                    if (QxKeyboard.this.onKeyboardSavedListener != null) {
                        QxKeyboard.this.onKeyboardSavedListener.onKeyboardSaved();
                    }
                } else if (i == -6) {
                    if (editText.getText() != null) {
                        String obj = editText.getText().toString();
                        if (obj.isEmpty()) {
                            editText.setText("-");
                            editText.setSelection(selectionStart + 1, selectionEnd + 1);
                        } else if (obj.substring(0, 1).equals("-")) {
                            editText.setText(obj.substring(1, obj.length()));
                            editText.setSelection(Math.max(0, selectionStart - 1), selectionEnd - 1);
                        } else {
                            editText.setText("-" + obj);
                            editText.setSelection(selectionStart + 1, selectionEnd + 1);
                        }
                    } else {
                        editText.setText("-");
                        editText.setSelection(selectionStart + 1, selectionEnd + 1);
                    }
                } else if (selectionStart != selectionEnd) {
                    text.replace(selectionStart, selectionEnd, Character.toString((char) i));
                    editText.setSelection(selectionStart + 1);
                } else {
                    text.insert(selectionStart, Character.toString((char) i));
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public OnKeyboardSavedListener onKeyboardSavedListener;
    /* access modifiers changed from: private */
    public ValueAnimator showKbAnimator;
    /* access modifiers changed from: private */
    public boolean showKbAnimatorCancelled = false;

    private enum KeyboardState {
        HIDDEN,
        SHOWING,
        SHOWN,
        HIDING
    }

    public interface OnKeyboardSavedListener {
        void onKeyboardSaved();
    }

    public void setOnKeyboardSavedListener(OnKeyboardSavedListener onKeyboardSavedListener2) {
        this.onKeyboardSavedListener = onKeyboardSavedListener2;
    }

    public void attachToHost(Activity activity, View view, KeyboardView keyboardView, Keyboard keyboard) {
        this.mHostActivity = activity;
        this.mKeyboardView = keyboardView;
        keyboardView.setKeyboard(keyboard);
        this.mKeyboardView.setPreviewEnabled(false);
        this.mKeyboardView.setOnKeyboardActionListener(this.mOnKeyboardActionListener);
        this.mKeyboardView.setEnabled(false);
        this.containerView = view;
        this.keyboardState = KeyboardState.HIDDEN;
        view.getLayoutParams().height = 0;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.mKeyboardView.setKeyboard(keyboard);
    }

    public void hideCustomKeyboard() {
        Log.d("API", "hideCustomKeyboard");
        if (this.showKbAnimator != null) {
            Log.d("API", "showCustomKeyboard - cancel hide anim");
            this.showKbAnimator.cancel();
        }
        if (this.keyboardState != KeyboardState.HIDDEN) {
            this.keyboardState = KeyboardState.HIDING;
            final LinearLayout linearLayout = (LinearLayout) this.containerView.getParent();
            this.containerView.measure(View.MeasureSpec.makeMeasureSpec((linearLayout.getWidth() - linearLayout.getPaddingLeft()) - linearLayout.getPaddingRight(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
            final int measuredHeight = this.containerView.getMeasuredHeight();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            this.hideKbAnimator = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    QxKeyboard.this.containerView.getLayoutParams().height = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * ((float) measuredHeight));
                    linearLayout.requestLayout();
                }
            });
            this.hideKbAnimator.setDuration(200);
            this.hideKbAnimator.setInterpolator(new DecelerateInterpolator());
            this.hideKbAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    if (!QxKeyboard.this.hideKbAnimatorCancelled) {
                        KeyboardState unused = QxKeyboard.this.keyboardState = KeyboardState.HIDDEN;
                    }
                    ValueAnimator unused2 = QxKeyboard.this.hideKbAnimator = null;
                }

                public void onAnimationCancel(Animator animator) {
                    boolean unused = QxKeyboard.this.hideKbAnimatorCancelled = true;
                }
            });
            this.hideKbAnimator.start();
            this.hideKbAnimatorCancelled = false;
        }
    }

    public void showCustomKeyboard(boolean z) {
        Log.d("API", "showCustomKeyboard - animated " + z);
        if (this.hideKbAnimator != null) {
            Log.d("API", "showCustomKeyboard - cancel hide anim");
            this.hideKbAnimator.cancel();
        }
        Log.d("API", "showCustomKeyboard 2 - animated " + z);
        if (this.keyboardState == KeyboardState.SHOWN) {
            Log.d("API", "showCustomKeyboard 2 - already shown ");
            return;
        }
        Log.d("API", "showCustomKeyboard 3");
        if (!z) {
            this.containerView.getLayoutParams().height = -2;
            this.keyboardState = KeyboardState.SHOWN;
            this.containerView.getParent().requestLayout();
            Log.d("API", "showCustomKeyboard 3 - animated " + z);
            return;
        }
        int i = this.containerView.getLayoutParams().height;
        final LinearLayout linearLayout = (LinearLayout) this.containerView.getParent();
        if (linearLayout.getWidth() != 0) {
            this.keyboardState = KeyboardState.SHOWING;
            Log.d("API", "parent width " + linearLayout.getWidth());
            this.containerView.measure(View.MeasureSpec.makeMeasureSpec((linearLayout.getWidth() - linearLayout.getPaddingLeft()) - linearLayout.getPaddingRight(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
            final int measuredHeight = this.containerView.getMeasuredHeight();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) (i / measuredHeight), 1.0f});
            this.showKbAnimator = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    QxKeyboard.this.containerView.getLayoutParams().height = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * ((float) measuredHeight));
                    linearLayout.requestLayout();
                }
            });
            this.showKbAnimator.setDuration(200);
            this.showKbAnimator.setInterpolator(new DecelerateInterpolator());
            this.showKbAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    if (!QxKeyboard.this.showKbAnimatorCancelled) {
                        QxKeyboard.this.containerView.getLayoutParams().height = -2;
                        KeyboardState unused = QxKeyboard.this.keyboardState = KeyboardState.SHOWN;
                    }
                    ValueAnimator unused2 = QxKeyboard.this.showKbAnimator = null;
                }

                public void onAnimationCancel(Animator animator) {
                    boolean unused = QxKeyboard.this.showKbAnimatorCancelled = true;
                }
            });
            this.showKbAnimator.start();
            this.showKbAnimatorCancelled = false;
        }
    }

    public boolean isCustomKeyboardVisible() {
        return this.keyboardState == KeyboardState.SHOWN || this.keyboardState == KeyboardState.SHOWING;
    }

    public void registerEditText(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    QxKeyboard.this.showCustomKeyboard(true);
                } else {
                    QxKeyboard.this.hideCustomKeyboard();
                }
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QxKeyboard.this.showCustomKeyboard(true);
            }
        });
    }
}
