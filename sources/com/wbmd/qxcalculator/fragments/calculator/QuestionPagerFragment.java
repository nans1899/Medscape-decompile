package com.wbmd.qxcalculator.fragments.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.activities.contentItems.ContentItemActivity;
import com.wbmd.qxcalculator.model.contentItems.calculator.AnswerChoice;
import com.wbmd.qxcalculator.model.contentItems.calculator.Calculator;
import com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.util.CalculationJavascriptCallback;
import com.wbmd.qxcalculator.util.QxKeyboard;
import java.util.Iterator;

public class QuestionPagerFragment extends Fragment implements OnAnswerChangedListener {
    protected static final String KEY_ALL_QUESTIONS_ALREADY_ANSWERED = "CalculatorQuestionActivityFragment.KEY_ALL_QUESTIONS_ALREADY_ANSWERED";
    protected static final String KEY_ARG_CONTENT_ITEM = "CalculatorQuestionActivityFragment.KEY_ARG_CONTENT_ITEM";
    protected static final String KEY_ARG_IS_SUB_CALC = "CalculatorQuestionActivityFragment.KEY_ARG_IS_SUB_CALC";
    protected static final String KEY_ARG_SELECTED_QUESTION_INDEX = "CalculatorQuestionActivityFragment.KEY_ARG_SELECTED_QUESTION_INDEX";
    protected static final String KEY_ARG_SUB_CALC_QUESTION = "CalculatorQuestionActivityFragment.KEY_ARG_SUB_CALC_QUESTION";
    protected static final String KEY_ARG_SUB_CALC_QUESTION_LINKED_CALC_INDEX = "CalculatorQuestionActivityFragment.KEY_ARG_SUB_CALC_QUESTION_LINKED_CALC_INDEX";
    protected static final String KEY_SHOULD_FWD_DID_APPEAR_TO_QUESTION = "CalculatorQuestionActivityFragment.shouldNotifyDidAppearToInitialQuestion";
    private boolean allQuestionsAlreadyAnswered;
    /* access modifiers changed from: protected */
    public Calculator calculator;
    /* access modifiers changed from: protected */
    public ContentItem contentItem;
    private Handler handler = new Handler();
    /* access modifiers changed from: protected */
    public View inputBlockerView;
    /* access modifiers changed from: private */
    public boolean isCalculating;
    private boolean isSubCalc;
    public QxKeyboard keyboard;
    private Keyboard mKeyboard;
    private KeyboardView mKeyboardView;
    private Keyboard mKeyboardWithPlusMinus;
    /* access modifiers changed from: protected */
    public QuestionsFragmentPagerAdapter mPagerAdapter;
    protected ViewPager mViewPager;
    protected ViewPager.OnPageChangeListener pageChangeListener;
    /* access modifiers changed from: protected */
    public int selectedQuestionIndex;
    /* access modifiers changed from: protected */
    public boolean shouldNotifyDidAppearToInitialQuestion;
    /* access modifiers changed from: private */
    public Question subCalcQuestion;
    /* access modifiers changed from: private */
    public int subCalcQuestionLinkedCalcIndex;

    public void notifyLeavingCalculator() {
    }

    public void notifyReturningToCalculator() {
    }

    public static QuestionPagerFragment newInstance(ContentItem contentItem2, boolean z, Question question, int i, int i2, boolean z2) {
        QuestionPagerFragment questionPagerFragment = new QuestionPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ARG_CONTENT_ITEM, contentItem2);
        bundle.putBoolean(KEY_ARG_IS_SUB_CALC, z);
        if (question != null) {
            bundle.putParcelable(KEY_ARG_SUB_CALC_QUESTION, question);
            bundle.putInt(KEY_ARG_SUB_CALC_QUESTION_LINKED_CALC_INDEX, i);
        }
        bundle.putInt(KEY_ARG_SELECTED_QUESTION_INDEX, i2);
        bundle.putBoolean(KEY_ALL_QUESTIONS_ALREADY_ANSWERED, z2);
        questionPagerFragment.setArguments(bundle);
        return questionPagerFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("API", "CalculatorQuestionFragment onCreate");
        if (getArguments() != null) {
            ContentItem contentItem2 = (ContentItem) getArguments().getParcelable(KEY_ARG_CONTENT_ITEM);
            this.contentItem = contentItem2;
            this.calculator = contentItem2.calculator;
            this.isSubCalc = getArguments().getBoolean(KEY_ARG_IS_SUB_CALC, false);
            this.subCalcQuestion = (Question) getArguments().getParcelable(KEY_ARG_SUB_CALC_QUESTION);
            this.subCalcQuestionLinkedCalcIndex = getArguments().getInt(KEY_ARG_SUB_CALC_QUESTION_LINKED_CALC_INDEX, 0);
            this.selectedQuestionIndex = getArguments().getInt(KEY_ARG_SELECTED_QUESTION_INDEX, 0);
            this.allQuestionsAlreadyAnswered = getArguments().getBoolean(KEY_ALL_QUESTIONS_ALREADY_ANSWERED, false);
        }
        if (bundle != null) {
            this.selectedQuestionIndex = bundle.getInt(KEY_ARG_SELECTED_QUESTION_INDEX, this.selectedQuestionIndex);
            this.shouldNotifyDidAppearToInitialQuestion = bundle.getBoolean(KEY_SHOULD_FWD_DID_APPEAR_TO_QUESTION, false);
            return;
        }
        this.shouldNotifyDidAppearToInitialQuestion = true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("API", "CalculatorQuestionFragment onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_question_pager, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.input_blocker_view);
        this.inputBlockerView = findViewById;
        findViewById.setVisibility(8);
        setupViewPager(inflate);
        setupViewPagerOnChangeListener();
        this.mViewPager.addOnPageChangeListener(this.pageChangeListener);
        this.mViewPager.setCurrentItem(this.selectedQuestionIndex);
        updateTitle(this.selectedQuestionIndex);
        this.mKeyboardView = (KeyboardView) inflate.findViewById(R.id.keyboardview);
        this.mKeyboard = new Keyboard(getActivity(), R.xml.numerickb);
        this.mKeyboardWithPlusMinus = new Keyboard(getActivity(), R.xml.numerickbplusminus);
        Iterator<Keyboard.Key> it = this.mKeyboard.getKeys().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Keyboard.Key next = it.next();
            if (next.codes[0] == -4) {
                next.height = this.mKeyboard.getHeight();
                break;
            }
        }
        QxKeyboard qxKeyboard = new QxKeyboard();
        this.keyboard = qxKeyboard;
        qxKeyboard.attachToHost(getActivity(), inflate.findViewById(R.id.kb_container), this.mKeyboardView, this.mKeyboard);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void setupViewPagerOnChangeListener() {
        this.pageChangeListener = new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                Log.d("CHAN", "onPageScrolled " + i + "; posOffset: " + f + "; selectedQI: " + QuestionPagerFragment.this.selectedQuestionIndex);
                if (f != 0.0f) {
                    return;
                }
                if (QuestionPagerFragment.this.shouldNotifyDidAppearToInitialQuestion) {
                    QuestionFragment questionFragment = (QuestionFragment) QuestionPagerFragment.this.mPagerAdapter.getRegisteredFragment(QuestionPagerFragment.this.selectedQuestionIndex);
                    if (questionFragment != null) {
                        questionFragment.didAppear();
                    }
                    QuestionPagerFragment.this.inputBlockerView.setVisibility(8);
                    QuestionPagerFragment.this.shouldNotifyDidAppearToInitialQuestion = false;
                } else if (QuestionPagerFragment.this.selectedQuestionIndex != i) {
                    QuestionFragment questionFragment2 = (QuestionFragment) QuestionPagerFragment.this.mPagerAdapter.getRegisteredFragment(QuestionPagerFragment.this.selectedQuestionIndex);
                    QuestionFragment questionFragment3 = (QuestionFragment) QuestionPagerFragment.this.mPagerAdapter.getRegisteredFragment(i);
                    QuestionPagerFragment.this.selectedQuestionIndex = i;
                    if (questionFragment2 != null) {
                        questionFragment2.didDisappear();
                    }
                    if (questionFragment3 != null) {
                        questionFragment3.didAppear();
                    }
                    QuestionPagerFragment.this.inputBlockerView.setVisibility(8);
                }
            }

            public void onPageSelected(int i) {
                QuestionPagerFragment.this.updateTitle(i);
            }
        };
    }

    public void setupViewPager(View view) {
        this.mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        QuestionsFragmentPagerAdapter questionsFragmentPagerAdapter = new QuestionsFragmentPagerAdapter(getChildFragmentManager());
        this.mPagerAdapter = questionsFragmentPagerAdapter;
        this.mViewPager.setAdapter(questionsFragmentPagerAdapter);
    }

    public void showPlusMinusKeyboard(boolean z) {
        this.keyboard.setKeyboard(z ? this.mKeyboardWithPlusMinus : this.mKeyboard);
    }

    /* access modifiers changed from: protected */
    public void updateTitle(int i) {
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) getResources().getString(R.string.question_index, new Object[]{Integer.valueOf(i + 1), Integer.valueOf(this.calculator.questions.size())}));
        }
    }

    public void onResume() {
        Log.d("API", "CalculatorQuestionFragment onResume");
        super.onResume();
    }

    public void onStop() {
        Log.d("API", "CalculatorQuestionFragment onStop");
        super.onStop();
    }

    public void onDestroy() {
        Log.d("API", "CalculatorQuestionFragment onDestroy");
        super.onDestroy();
    }

    public void onDestroyView() {
        Log.d("API", "CalculatorQuestionFragment onDestroyView");
        super.onDestroyView();
        this.mViewPager.removeOnPageChangeListener(this.pageChangeListener);
        this.pageChangeListener = null;
    }

    public void onBackPressed() {
        calculateAndGoBack(true);
    }

    private void calculateAndGoBack() {
        calculateAndGoBack(false);
    }

    private void calculateAndGoBack(boolean z) {
        if (!this.isCalculating) {
            this.isCalculating = true;
            this.inputBlockerView.setVisibility(0);
            if ((!this.isSubCalc || !z) && this.calculator.allQuestionsAnswered()) {
                this.calculator.resetAnswer();
                this.calculator.getAnswer(new Calculator.CalculateAnswerCompletionHandler() {
                    public void onCalculateCompletion(boolean z) {
                        if (QuestionPagerFragment.this.subCalcQuestion == null) {
                            QuestionPagerFragment.this.finishWithResult();
                            boolean unused = QuestionPagerFragment.this.isCalculating = false;
                        } else if (z) {
                            QuestionPagerFragment.this.subCalcQuestion.calculateLinkedCalculatorConvertFormula(QuestionPagerFragment.this.subCalcQuestionLinkedCalcIndex, QuestionPagerFragment.this.calculator, new CalculationJavascriptCallback.LinkedCalculatorConvertResultJavascriptCompletionHandler() {
                                public void onResultJavascriptCompleted(boolean z) {
                                    if (!z) {
                                        Question unused = QuestionPagerFragment.this.subCalcQuestion = null;
                                    }
                                    QuestionPagerFragment.this.finishWithResult();
                                    boolean unused2 = QuestionPagerFragment.this.isCalculating = false;
                                }
                            });
                        } else {
                            boolean unused2 = QuestionPagerFragment.this.isCalculating = false;
                            if (QuestionPagerFragment.this.calculator.errorChecks != null && !QuestionPagerFragment.this.calculator.errorChecks.isEmpty()) {
                                for (ErrorCheck next : QuestionPagerFragment.this.calculator.errorChecks) {
                                    if (next.hasError.booleanValue()) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionPagerFragment.this.getActivity());
                                        builder.setTitle(next.title);
                                        builder.setMessage(next.answer);
                                        builder.setPositiveButton(R.string.dismiss, (DialogInterface.OnClickListener) null);
                                        builder.create().show();
                                        QuestionPagerFragment.this.inputBlockerView.setVisibility(8);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                });
                return;
            }
            finishWithResult();
            this.isCalculating = false;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Log.d("API", "CalculatorQuestionFragment onSaveInstanceState");
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_ARG_SELECTED_QUESTION_INDEX, this.selectedQuestionIndex);
        bundle.putBoolean(KEY_SHOULD_FWD_DID_APPEAR_TO_QUESTION, this.shouldNotifyDidAppearToInitialQuestion);
    }

    public void finishWithResult() {
        Log.d("API", "CalculatorQuestionFragment finishWithResult");
        if (getActivity() != null) {
            Intent intent = new Intent();
            intent.putExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM, this.contentItem);
            Question question = this.subCalcQuestion;
            if (question != null) {
                intent.putExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION, question);
            }
            getActivity().setResult(-1, intent);
            getActivity().finish();
        }
    }

    public void onAnswerSelected(Question question, AnswerChoice answerChoice) {
        onAnswerSelected(question, answerChoice, false);
    }

    public void onAnswerSelected(Question question, AnswerChoice answerChoice, boolean z) {
        Iterator<Question> it = this.calculator.questions.iterator();
        int i = 0;
        while (it.hasNext() && !it.next().identifier.equals(question.identifier)) {
            i++;
        }
        this.calculator.questions.set(i, question);
        if (i < this.calculator.questions.size() - 1 && !this.allQuestionsAlreadyAnswered) {
            goToQuestion(i + 1, z);
        } else if (!this.allQuestionsAlreadyAnswered) {
            calculateAndGoBack();
        }
    }

    private void goToQuestion(int i) {
        goToQuestion(i, false);
    }

    private void goToQuestion(final int i, boolean z) {
        this.inputBlockerView.setVisibility(0);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                QuestionPagerFragment.this.mViewPager.setCurrentItem(i);
            }
        }, z ? 650 : 350);
    }

    public void onAnswerChanged(Question question) {
        Iterator<Question> it = this.calculator.questions.iterator();
        int i = 0;
        while (it.hasNext() && !it.next().identifier.equals(question.identifier)) {
            i++;
        }
        this.calculator.questions.set(i, question);
    }

    public void onDateChanged(Question question) {
        Iterator<Question> it = this.calculator.questions.iterator();
        int i = 0;
        while (it.hasNext() && !it.next().identifier.equals(question.identifier)) {
            i++;
        }
        this.calculator.questions.set(i, question);
    }

    public void onNextButtonPressed(Question question) {
        Iterator<Question> it = this.calculator.questions.iterator();
        int i = 0;
        while (it.hasNext() && !it.next().identifier.equals(question.identifier)) {
            i++;
        }
        if (i < this.calculator.questions.size() - 1 && !this.allQuestionsAlreadyAnswered) {
            goToQuestion(i + 1);
        } else if (!this.allQuestionsAlreadyAnswered) {
            calculateAndGoBack();
        } else {
            this.keyboard.hideCustomKeyboard();
            if (question.getQuestionType() != Question.QuestionType.QuestionTypeMultipleChoice) {
                Toast.makeText(getActivity(), R.string.answer_saved, 0).show();
            }
        }
    }

    public class QuestionsFragmentPagerAdapter extends FragmentStatePagerAdapter {
        SparseArray<Fragment> registeredFragments = new SparseArray<>();

        public QuestionsFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            Question question = QuestionPagerFragment.this.calculator.questions.get(i);
            switch (AnonymousClass4.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType[question.getQuestionType().ordinal()]) {
                case 1:
                    return QuestionMultipleChoiceFragment.newInstance(question, QuestionPagerFragment.this.contentItem);
                case 2:
                    return QuestionNumericInputFragment.newInstance(question, QuestionPagerFragment.this.contentItem);
                case 3:
                    return QuestionNumericInputFragment.newInstance(question, QuestionPagerFragment.this.contentItem);
                case 4:
                    return QuestionNumericInputFragment.newInstance(question, QuestionPagerFragment.this.contentItem);
                case 5:
                    return QuestionDateInputFragment.newInstance(question, QuestionPagerFragment.this.contentItem);
                case 6:
                    return QuestionDaysInputFragment.newInstance(question, QuestionPagerFragment.this.contentItem);
                case 7:
                    return QuestionLinkedCalculator.newInstance(question, QuestionPagerFragment.this.contentItem);
                default:
                    return null;
            }
        }

        public int getCount() {
            return QuestionPagerFragment.this.calculator.questions.size();
        }

        public CharSequence getPageTitle(int i) {
            return QuestionPagerFragment.this.getResources().getString(R.string.question_index, new Object[]{Integer.valueOf(i), Integer.valueOf(QuestionPagerFragment.this.calculator.questions.size())});
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
            this.registeredFragments.put(i, fragment);
            return fragment;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            this.registeredFragments.remove(i);
            super.destroyItem(viewGroup, i, obj);
        }

        public Fragment getRegisteredFragment(int i) {
            return this.registeredFragments.get(i);
        }
    }

    /* renamed from: com.wbmd.qxcalculator.fragments.calculator.QuestionPagerFragment$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType[] r0 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType = r0
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeMultipleChoice     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeNumericEntryNoUnits     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeNumericEntrySingleUnits     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeNumericEntryMultipleUnits     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeDateInput     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeDaysInput     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeLinkedCalculator     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.fragments.calculator.QuestionPagerFragment.AnonymousClass4.<clinit>():void");
        }
    }

    public void forwardOnActivityResultToCurrentQuestion(int i, int i2, Intent intent) {
        ((QuestionFragment) this.mPagerAdapter.instantiateItem(this.mViewPager, this.selectedQuestionIndex)).onLinkedCalculatorResult(i, i2, intent);
    }
}
