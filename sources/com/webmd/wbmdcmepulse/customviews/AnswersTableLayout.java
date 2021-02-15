package com.webmd.wbmdcmepulse.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.articles.Answer;
import com.webmd.wbmdcmepulse.models.articles.Question;
import com.webmd.wbmdcmepulse.models.articles.QuestionResponseValues;
import com.webmd.wbmdcmepulse.models.articles.SingleAnswerViewHolder;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AnswersTableLayout extends TableLayout {
    public static final int ANSWER_STATE_CORRECT = 1;
    public static final int ANSWER_STATE_INCORRECT = 2;
    private final String TAG = AnswersTableLayout.class.getSimpleName();
    private boolean mAreNoAnswersCorrect;
    private ICallbackEvent<Integer, CMEPulseException> mCallback;
    private Context mContext;
    private int mCurrentAnswer;
    private boolean mIsCorrectAnswerSubmitted;
    private boolean mIsDisabled;
    private Question mQuestion;
    private List<QuestionResponseValues> mQuestionResponseValues;
    private HashMap<Integer, SingleAnswerViewHolder> mSingleAnswerViews;
    private int mTotalResponses = 0;

    public AnswersTableLayout(Context context, Question question, ICallbackEvent<Integer, CMEPulseException> iCallbackEvent) {
        super(context);
        this.mContext = context;
        this.mQuestion = question;
        this.mSingleAnswerViews = new HashMap<>();
        this.mQuestionResponseValues = new ArrayList();
        this.mCallback = iCallbackEvent;
        this.mIsCorrectAnswerSubmitted = false;
        this.mIsDisabled = false;
        init();
    }

    public List<QuestionResponseValues> getQuestionResponseValues() {
        return this.mQuestionResponseValues;
    }

    public void animatePoll(boolean z) {
        this.mAreNoAnswersCorrect = true;
        Iterator<Answer> it = this.mQuestion.answers.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().isCorrect) {
                    this.mAreNoAnswersCorrect = false;
                    break;
                }
            } else {
                break;
            }
        }
        int i = 0;
        for (Answer next : this.mQuestion.answers) {
            if (next.totalResponse > 0 && this.mTotalResponses > 0) {
                SingleAnswerViewHolder singleAnswerViewHolder = this.mSingleAnswerViews.get(Integer.valueOf(i));
                View view = singleAnswerViewHolder.background;
                view.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.answer_border));
                if (singleAnswerViewHolder.answerId == this.mSingleAnswerViews.get(Integer.valueOf(this.mCurrentAnswer)).answerId) {
                    singleAnswerViewHolder.textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    singleAnswerViewHolder.radioButton.setEnabled(false);
                }
                final View findViewById = view.findViewById(R.id.background);
                final float f = ((float) next.totalResponse) / ((float) this.mTotalResponses);
                final TextView textView = (TextView) view.findViewById(R.id.response_percent_text_view);
                textView.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.valueOf((int) (100.0f * f)) + "%");
                textView.setVisibility(0);
                textView.post(new Runnable() {
                    public void run() {
                        textView.requestLayout();
                    }
                });
                findViewById.setVisibility(0);
                if (next.isCorrect || this.mAreNoAnswersCorrect) {
                    findViewById.setBackgroundColor(this.mContext.getResources().getColor(R.color.medscapegreentransparent));
                }
                if (!z) {
                    findViewById.startAnimation(inFromLeftAnimation(0.0f, f, 1000));
                    findViewById.post(new Runnable() {
                        public void run() {
                            findViewById.requestLayout();
                        }
                    });
                } else {
                    float f2 = -1.0f * f;
                    findViewById.startAnimation(inFromLeftAnimation(f2, f2, 0));
                    findViewById.postDelayed(new Runnable() {
                        public void run() {
                            findViewById.requestLayout();
                            findViewById.startAnimation(AnswersTableLayout.this.inFromLeftAnimation(0.0f, f, 1000));
                            findViewById.postDelayed(new Runnable() {
                                public void run() {
                                    findViewById.requestLayout();
                                }
                            }, 300);
                        }
                    }, 300);
                }
                i++;
                setDisabled();
            }
        }
    }

    public void setDisabled() {
        this.mIsDisabled = true;
    }

    public void setAnswerState(int i) {
        for (Integer intValue : this.mSingleAnswerViews.keySet()) {
            int intValue2 = intValue.intValue();
            this.mSingleAnswerViews.get(Integer.valueOf(intValue2)).textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.mSingleAnswerViews.get(Integer.valueOf(intValue2)).background.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.answer_border));
        }
        SingleAnswerViewHolder singleAnswerViewHolder = this.mSingleAnswerViews.get(Integer.valueOf(this.mCurrentAnswer));
        singleAnswerViewHolder.textView.setTextColor(-1);
        View view = singleAnswerViewHolder.background;
        view.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.question_border_selected));
        if (i == 2) {
            view.setBackgroundColor(this.mContext.getResources().getColor(R.color.linkcolor));
        }
        if (i == 1) {
            view.setEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public Animation inFromLeftAnimation(float f, float f2, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, f - 4.0f, 1, f2 - 4.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setStartOffset(0);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                animation.setFillAfter(true);
            }
        });
        return translateAnimation;
    }

    private void init() {
        int i = 0;
        for (Answer next : this.mQuestion.answers) {
            TableRow tableRow = (TableRow) inflateAndSetViewValues(next);
            addView(tableRow, tableRow.getLayoutParams());
            QuestionResponseValues questionResponseValues = new QuestionResponseValues();
            questionResponseValues.answerId = next.id;
            questionResponseValues.questionId = this.mQuestion.id;
            questionResponseValues.displayIndex = i;
            this.mQuestionResponseValues.add(questionResponseValues);
            i++;
            this.mTotalResponses += next.totalResponse;
        }
    }

    private View inflateAndSetViewValues(Answer answer) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.cme_single_answer_layout, this, false);
        int size = this.mSingleAnswerViews.size();
        inflate.setTag(Integer.valueOf(size));
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) inflate.findViewById(R.id.answer_text);
        articleCopyTextView.setText(Utilities.getFormattedText(answer.text.trim()));
        RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.answer_radio);
        radioButton.setTag(Integer.valueOf(size));
        radioButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AnswersTableLayout.this.toggleCustomRadioGroup(view, motionEvent);
                return true;
            }
        });
        this.mSingleAnswerViews.put(Integer.valueOf(size), new SingleAnswerViewHolder(answer.id, inflate, radioButton, articleCopyTextView));
        if (answer.isSelected) {
            toggleCustomRadioGroup(radioButton, (MotionEvent) null);
            this.mCurrentAnswer = ((Integer) radioButton.getTag()).intValue();
            if (answer.isCorrect) {
                setAnswerState(1);
            } else {
                setAnswerState(2);
            }
        }
        inflate.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AnswersTableLayout.this.toggleCustomRadioGroup(view, motionEvent);
                return true;
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void toggleCustomRadioGroup(View view, MotionEvent motionEvent) {
        if ((motionEvent == null || isEventViewCheckable(motionEvent)) && !this.mIsDisabled && !this.mIsCorrectAnswerSubmitted) {
            for (Integer next : this.mSingleAnswerViews.keySet()) {
                RadioButton radioButton = this.mSingleAnswerViews.get(next).radioButton;
                if (next == view.getTag()) {
                    this.mCurrentAnswer = next.intValue();
                    if (!radioButton.isChecked()) {
                        radioButton.setChecked(true);
                        this.mSingleAnswerViews.get(next).background.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.question_border_selected));
                        this.mSingleAnswerViews.get(next).textView.setTextColor(-1);
                    }
                    this.mCallback.onCompleted(Integer.valueOf(this.mCurrentAnswer));
                } else if (radioButton.isChecked()) {
                    radioButton.setChecked(false);
                    this.mSingleAnswerViews.get(next).background.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.answer_border));
                    this.mSingleAnswerViews.get(next).textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }
            }
        }
    }

    private boolean isEventViewCheckable(MotionEvent motionEvent) {
        return (motionEvent.getAction() == 0 || motionEvent.getAction() == 3 || motionEvent.getAction() == 2) ? false : true;
    }
}
