package com.webmd.wbmdcmepulse.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CMETrackerActivity;
import com.webmd.wbmdcmepulse.customviews.AnswersTableLayout;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.articles.ActivityTestViewHolder;
import com.webmd.wbmdcmepulse.models.articles.Answer;
import com.webmd.wbmdcmepulse.models.articles.Question;
import com.webmd.wbmdcmepulse.models.articles.QuestionResponse;
import com.webmd.wbmdcmepulse.models.articles.QuestionResponseValues;
import com.webmd.wbmdcmepulse.models.interfaces.ITestScoreListener;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.QnaScoreCalculator;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArticleQuestionsFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public final String TAG = ArticleQuestionsFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public int answeredQuestionId;
    private Map<Integer, ActivityTestViewHolder> mActivityTestViewHolders;
    /* access modifiers changed from: private */
    public Context mContext;
    private Map<Integer, Integer> mCorrectAnswersKey;
    private boolean mIsActivityExpired;
    private boolean mIsAnimated;
    /* access modifiers changed from: private */
    public boolean mIsAnswerChanged;
    private double mPassingScore;
    public List<Question> mQuestions;
    /* access modifiers changed from: private */
    public int mQuestionsRemaining = 0;
    private View mRootView;
    /* access modifiers changed from: private */
    public Map<Integer, Integer> mSelectedAnswers;
    /* access modifiers changed from: private */
    public ITestScoreListener mTestScoreListener;
    private int mTotalAnswers;
    private int mWrongAnswers;
    public EditText questionAnswer;
    private LinearLayout questionsLinearLayout;
    /* access modifiers changed from: private */
    public String userAnswer;

    static /* synthetic */ int access$710(ArticleQuestionsFragment articleQuestionsFragment) {
        int i = articleQuestionsFragment.mQuestionsRemaining;
        articleQuestionsFragment.mQuestionsRemaining = i - 1;
        return i;
    }

    public static ArticleQuestionsFragment newInstance(List<Question> list, double d, boolean z, ITestScoreListener iTestScoreListener, UserProfile userProfile) {
        ArticleQuestionsFragment articleQuestionsFragment = new ArticleQuestionsFragment();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        Bundle bundle = new Bundle();
        bundle.putDouble("passingScore", d);
        bundle.putParcelableArrayList("questions", arrayList);
        bundle.putBoolean("isActivityExpired", z);
        bundle.putParcelable(Constants.BUNDLE_KEY_USER_PROFILE, userProfile);
        articleQuestionsFragment.mUserProfile = userProfile;
        articleQuestionsFragment.mTestScoreListener = iTestScoreListener;
        articleQuestionsFragment.mIsAnimated = false;
        articleQuestionsFragment.setArguments(bundle);
        return articleQuestionsFragment;
    }

    private boolean isPassed() {
        boolean z = true;
        int i = 0;
        for (Question next : this.mQuestions) {
            if (!next.isPassed) {
                i++;
            }
            if (!next.isScorable) {
                z = false;
            }
        }
        if (z && QnaScoreCalculator.getCurrentScore(i, this.mQuestions.size()) >= this.mPassingScore) {
            return true;
        }
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mIsAnimated) {
            animatePoll(true);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("selectedAnswers", (Serializable) this.mSelectedAnswers);
        bundle.putSerializable("correctAnswersKey", (Serializable) this.mCorrectAnswersKey);
        bundle.putInt("questionsRemaining", this.mQuestionsRemaining);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.cme_fragment_article_questions, viewGroup, false);
        this.mContext = getContext();
        this.questionsLinearLayout = (LinearLayout) this.mRootView.findViewById(R.id.questions_linear_layout);
        Bundle arguments = getArguments();
        this.mPassingScore = arguments.getDouble("passingScore");
        this.mQuestions = arguments.getParcelableArrayList("questions");
        this.mIsActivityExpired = arguments.getBoolean("isActivityExpired");
        if (!isPassed()) {
            this.mActivityTestViewHolders = new ConcurrentHashMap();
            if (bundle == null) {
                this.mSelectedAnswers = new ConcurrentHashMap();
                this.mCorrectAnswersKey = new ConcurrentHashMap();
                this.mQuestionsRemaining = 0;
            } else {
                this.mSelectedAnswers = (Map) bundle.getSerializable("answersTableLayouts");
                this.mCorrectAnswersKey = (Map) bundle.getSerializable("correctAnswersKey");
                this.mQuestionsRemaining = ((Integer) bundle.get("questionsRemaining")).intValue();
                if (this.mSelectedAnswers == null) {
                    this.mSelectedAnswers = new ConcurrentHashMap();
                }
                if (this.mCorrectAnswersKey == null) {
                    this.mCorrectAnswersKey = new ConcurrentHashMap();
                }
                if (this.mQuestionsRemaining < 0) {
                    this.mQuestionsRemaining = 0;
                }
            }
            setUpQuestions();
        } else {
            ((TextView) this.mRootView.findViewById(R.id.test_taken_msg_text_view)).setVisibility(0);
            TextView textView = (TextView) this.mRootView.findViewById(R.id.proceed_to_cme_tracker_text_view);
            textView.setText(Utilities.getTrackerLabel(getActivity()));
            textView.setVisibility(0);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ArticleQuestionsFragment.this.mContext, CMETrackerActivity.class);
                    intent.putExtra(Constants.RETURN_ACTIVITY, Constants.HOME_ACTIVITY_NAME);
                    intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, ArticleQuestionsFragment.this.mUserProfile);
                    ArticleQuestionsFragment.this.startActivity(intent);
                    ArticleQuestionsFragment.this.getActivity().finish();
                }
            });
        }
        return this.mRootView;
    }

    public void setTestScoreListener(ITestScoreListener iTestScoreListener) {
        this.mTestScoreListener = iTestScoreListener;
    }

    public void submitAnswers() {
        QuestionResponse[] questionResponseArr;
        if (this.mSelectedAnswers.size() != 0 || this.userAnswer == null) {
            questionResponseArr = new QuestionResponse[this.mSelectedAnswers.size()];
            ArrayList arrayList = new ArrayList();
            this.mWrongAnswers = 0;
            int i = 0;
            for (Integer intValue : this.mSelectedAnswers.keySet()) {
                int intValue2 = intValue.intValue();
                for (QuestionResponseValues next : this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).answersTableLayout.getQuestionResponseValues()) {
                    if (next.displayIndex == this.mSelectedAnswers.get(Integer.valueOf(intValue2)).intValue()) {
                        QuestionResponse questionResponse = new QuestionResponse();
                        questionResponse.choideId = Integer.toString(next.answerId);
                        questionResponse.questionId = Integer.toString(next.questionId);
                        questionResponse.responseText = "";
                        questionResponseArr[i] = questionResponse;
                        i++;
                    }
                }
                if (this.mSelectedAnswers.get(Integer.valueOf(intValue2)) != this.mCorrectAnswersKey.get(Integer.valueOf(intValue2))) {
                    this.mWrongAnswers++;
                    arrayList.add(Integer.valueOf(intValue2));
                }
            }
        } else {
            QuestionResponse questionResponse2 = new QuestionResponse();
            questionResponse2.questionId = Integer.toString(this.answeredQuestionId);
            questionResponse2.responseText = this.userAnswer;
            questionResponseArr = new QuestionResponse[]{questionResponse2};
        }
        QuestionResponse[] questionResponseArr2 = questionResponseArr;
        double currentScore = QnaScoreCalculator.getCurrentScore(this.mWrongAnswers, this.mTotalAnswers);
        ITestScoreListener iTestScoreListener = this.mTestScoreListener;
        if (iTestScoreListener != null) {
            iTestScoreListener.onAnswersSubmitted(isScorePassing(currentScore), currentScore, questionResponseArr2, this.mIsAnswerChanged);
        }
        this.mIsAnswerChanged = false;
    }

    public void displayValidationDisableAllQuestions() {
        setValidation(true);
    }

    public void displayValidation() {
        setValidation(false);
    }

    public void disableAll() {
        Map<Integer, ActivityTestViewHolder> map = this.mActivityTestViewHolders;
        if (map != null && map.size() > 0) {
            for (Integer intValue : this.mActivityTestViewHolders.keySet()) {
                this.mActivityTestViewHolders.get(Integer.valueOf(intValue.intValue())).answersTableLayout.setDisabled();
            }
        }
    }

    public void animatePoll(boolean z) {
        this.mIsAnimated = true;
        for (Integer intValue : this.mActivityTestViewHolders.keySet()) {
            this.mActivityTestViewHolders.get(Integer.valueOf(intValue.intValue())).answersTableLayout.animatePoll(z);
        }
    }

    private void setValidation(boolean z) {
        for (Integer intValue : this.mSelectedAnswers.keySet()) {
            int intValue2 = intValue.intValue();
            if (Utilities.isNetworkAvailable(getActivity())) {
                if (this.mSelectedAnswers.get(Integer.valueOf(intValue2)) == this.mCorrectAnswersKey.get(Integer.valueOf(intValue2))) {
                    this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).answersTableLayout.setAnswerState(1);
                    this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).answersTableLayout.setDisabled();
                    this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).backgroundLinearLayout.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.question_border_correct));
                } else {
                    this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).answersTableLayout.setAnswerState(2);
                    if (z) {
                        this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).answersTableLayout.setDisabled();
                    }
                    this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).backgroundLinearLayout.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.question_border_wrong));
                }
            }
        }
    }

    private void setUpQuestions() {
        ITestScoreListener iTestScoreListener;
        boolean z = false;
        for (Question next : this.mQuestions) {
            for (Answer next2 : next.answers) {
                if (next2.isSelected) {
                    if (!next2.isCorrect) {
                        this.mWrongAnswers++;
                    }
                    z = true;
                }
            }
            if (!z) {
                this.mQuestionsRemaining++;
                this.mWrongAnswers++;
            }
            this.mTotalAnswers++;
            buildQuestion(next);
        }
        if (z && (iTestScoreListener = this.mTestScoreListener) != null) {
            iTestScoreListener.onTestAnswerChanged(this.mQuestionsRemaining, false);
        }
    }

    private void buildQuestion(final Question question) {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.question_border));
        linearLayout.setOrientation(1);
        if (!Extensions.isStringNullOrEmpty(question.questionIntro)) {
            ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(this.mContext);
            articleCopyTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            articleCopyTextView.setPadding(30, 30, 30, 25);
            articleCopyTextView.setText(Utilities.getFormattedText(question.questionIntro));
            articleCopyTextView.setFocusable(true);
            articleCopyTextView.setFocusableInTouchMode(true);
            linearLayout.addView(articleCopyTextView);
        }
        ArticleCopyTextView articleCopyTextView2 = new ArticleCopyTextView(this.mContext);
        articleCopyTextView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        articleCopyTextView2.setPadding(30, 30, 30, 25);
        articleCopyTextView2.setText(Utilities.getFormattedText(question.questionText));
        articleCopyTextView2.setFocusable(true);
        articleCopyTextView2.setFocusableInTouchMode(true);
        linearLayout.addView(articleCopyTextView2);
        if (question.answers.size() == 0) {
            this.questionAnswer = new EditText(this.mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(30, 0, 30, 0);
            this.questionAnswer.setLayoutParams(layoutParams);
            this.questionAnswer.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.questionAnswer.setPadding(30, 30, 30, 30);
            if (question.isResponded) {
                this.questionAnswer.setText("");
                this.questionAnswer.setEnabled(false);
            } else {
                this.questionAnswer.setHint(getResources().getString(R.string.cme_article_qna_answer_hint));
                this.questionAnswer.setEnabled(true);
            }
            this.questionAnswer.setInputType(32);
            this.questionAnswer.setFocusable(true);
            this.questionAnswer.setFocusableInTouchMode(true);
            this.questionAnswer.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence.length() <= 0 || charSequence.toString().trim().isEmpty()) {
                        ArticleQuestionsFragment.this.mTestScoreListener.onTestAnswerChanged(1, true);
                        return;
                    }
                    String unused = ArticleQuestionsFragment.this.userAnswer = charSequence.toString();
                    int unused2 = ArticleQuestionsFragment.this.answeredQuestionId = question.id;
                    if (ArticleQuestionsFragment.this.mTestScoreListener != null) {
                        ArticleQuestionsFragment.this.mTestScoreListener.onTestAnswerChanged(0, false);
                    }
                }
            });
            linearLayout.addView(this.questionAnswer);
        }
        AnswersTableLayout answersTableLayout = new AnswersTableLayout(this.mContext, question, new ICallbackEvent<Integer, CMEPulseException>() {
            public void onError(CMEPulseException cMEPulseException) {
                Trace.e(ArticleQuestionsFragment.this.TAG, cMEPulseException.getMessage());
            }

            public void onCompleted(Integer num) {
                boolean z = true;
                boolean unused = ArticleQuestionsFragment.this.mIsAnswerChanged = true;
                if (ArticleQuestionsFragment.this.mSelectedAnswers.containsKey(Integer.valueOf(question.id))) {
                    ArticleQuestionsFragment.this.mSelectedAnswers.remove(Integer.valueOf(question.id));
                    ArticleQuestionsFragment.this.mSelectedAnswers.put(Integer.valueOf(question.id), num);
                } else {
                    ArticleQuestionsFragment.this.mSelectedAnswers.put(Integer.valueOf(question.id), num);
                    ArticleQuestionsFragment.access$710(ArticleQuestionsFragment.this);
                    z = false;
                }
                if (ArticleQuestionsFragment.this.mTestScoreListener != null) {
                    ArticleQuestionsFragment.this.mTestScoreListener.onTestAnswerChanged(ArticleQuestionsFragment.this.mQuestionsRemaining, z);
                }
            }
        });
        answersTableLayout.setTag(Integer.valueOf(question.id));
        answersTableLayout.setPadding(30, 30, 30, 0);
        this.mCorrectAnswersKey.put(Integer.valueOf(question.id), Integer.valueOf(question.getCorrectAnswerIndex()));
        TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(10, 0, 10, 75);
        answersTableLayout.setLayoutParams(layoutParams2);
        linearLayout.addView(answersTableLayout);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(10, 30, 10, 30);
        linearLayout.setLayoutParams(layoutParams3);
        this.questionsLinearLayout.addView(linearLayout);
        this.mActivityTestViewHolders.put(Integer.valueOf(question.id), new ActivityTestViewHolder(answersTableLayout, linearLayout, articleCopyTextView2));
    }

    private boolean isScorePassing(double d) {
        return d >= this.mPassingScore;
    }

    public View getNextAnswerableQuestion() {
        int bottom;
        Map<Integer, ActivityTestViewHolder> map = this.mActivityTestViewHolders;
        LinearLayout linearLayout = null;
        if (map != null) {
            int i = -1;
            for (Integer intValue : map.keySet()) {
                int intValue2 = intValue.intValue();
                if (this.mSelectedAnswers.size() <= this.mActivityTestViewHolders.size() && !this.mSelectedAnswers.containsKey(Integer.valueOf(intValue2))) {
                    LinearLayout linearLayout2 = this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).backgroundLinearLayout;
                    if (i < 0) {
                        bottom = linearLayout2.getBottom();
                    } else if (linearLayout2.getBottom() < i) {
                        bottom = linearLayout2.getBottom();
                    }
                    i = bottom;
                    linearLayout = linearLayout2;
                }
            }
        }
        return linearLayout;
    }

    public View getNextIncorrectQuestion() {
        View view;
        int bottom;
        Map<Integer, ActivityTestViewHolder> map = this.mActivityTestViewHolders;
        View view2 = null;
        if (map == null) {
            return null;
        }
        int i = -1;
        for (Integer intValue : map.keySet()) {
            int intValue2 = intValue.intValue();
            if (this.mSelectedAnswers.size() < this.mCorrectAnswersKey.size()) {
                if (!this.mSelectedAnswers.containsKey(Integer.valueOf(intValue2))) {
                    view = this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).backgroundLinearLayout;
                    if (i < 0) {
                        i = view.getBottom();
                    } else if (view.getBottom() < i) {
                        i = view.getBottom();
                    }
                }
            } else if (this.mSelectedAnswers.get(Integer.valueOf(intValue2)) != this.mCorrectAnswersKey.get(Integer.valueOf(intValue2))) {
                view = this.mActivityTestViewHolders.get(Integer.valueOf(intValue2)).answersTableLayout;
                if (i < 0) {
                    bottom = view.getBottom();
                } else if (view.getBottom() < i) {
                    bottom = view.getBottom();
                }
                i = bottom;
            }
            view2 = view;
        }
        return view2;
    }
}
