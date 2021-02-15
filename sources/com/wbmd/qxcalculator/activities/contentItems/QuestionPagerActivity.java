package com.wbmd.qxcalculator.activities.contentItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.fragments.calculator.QuestionPagerFragment;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;

public class QuestionPagerActivity extends QxMDActivity {
    protected boolean allQuestionsAlreadyAnswered;
    protected ContentItem contentItem;
    protected boolean isSubCalc;
    protected int selectedQuestionIndex;
    protected Question subCalcQuestion;
    protected int subCalcQuestionLinkedCalcIndex;

    public int getStatusBarColor() {
        if (this.isSubCalc) {
            return getResources().getColor(R.color.status_bar_color_sub_calc);
        }
        return getResources().getColor(R.color.status_bar_color_default);
    }

    public int getActionBarColor() {
        if (this.isSubCalc) {
            return getResources().getColor(R.color.action_bar_color_sub_calc);
        }
        return getResources().getColor(R.color.action_bar_color_default);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_question_pager;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.isSubCalc = getIntent().getBooleanExtra(CalculatorActivity.KEY_EXTRA_IS_SUB_CALC, false);
        super.onCreate(bundle);
        this.contentItem = (ContentItem) getIntent().getParcelableExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM);
        this.selectedQuestionIndex = getIntent().getIntExtra(CalculatorActivity.KEY_EXTRA_SELECTED_QUESTION, 0);
        this.allQuestionsAlreadyAnswered = getIntent().getBooleanExtra(CalculatorActivity.KEY_ALL_QUESTIONS_ALREADY_ANSWERED, false);
        this.subCalcQuestion = (Question) getIntent().getParcelableExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION);
        this.subCalcQuestionLinkedCalcIndex = getIntent().getIntExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX, 0);
        ContentItem contentItem2 = this.contentItem;
        if (contentItem2 == null || contentItem2.calculator == null) {
            finish();
            return;
        }
        setTitle(this.contentItem.name);
        if (bundle == null) {
            setupFirstFragment();
        }
    }

    public void onResume() {
        super.onResume();
        AnalyticsHandler.getInstance().trackPageView(this, "question_input");
    }

    public void onBackPressed() {
        Log.d("API", "onBackBtnPressed");
        QuestionPagerFragment questionPagerFragment = (QuestionPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (questionPagerFragment.keyboard.isCustomKeyboardVisible()) {
            questionPagerFragment.keyboard.hideCustomKeyboard();
        } else {
            questionPagerFragment.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        ((QuestionPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).onBackPressed();
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        QuestionPagerFragment questionPagerFragment = (QuestionPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        questionPagerFragment.notifyReturningToCalculator();
        questionPagerFragment.forwardOnActivityResultToCurrentQuestion(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void setupFirstFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, (Fragment) QuestionPagerFragment.newInstance(this.contentItem, this.isSubCalc, this.subCalcQuestion, this.subCalcQuestionLinkedCalcIndex, this.selectedQuestionIndex, this.allQuestionsAlreadyAnswered)).commit();
    }
}
