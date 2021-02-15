package com.medscape.android.activity.calc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.medscape.android.R;
import com.wbmd.qxcalculator.fragments.calculator.QuestionDaysInputFragment;
import com.wbmd.qxcalculator.fragments.calculator.QuestionLinkedCalculator;
import com.wbmd.qxcalculator.fragments.calculator.QuestionPagerFragment;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\u0005¢\u0006\u0002\u0010\u0002JA\u0010\t\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\r¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001f"}, d2 = {"Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment;", "Lcom/wbmd/qxcalculator/fragments/calculator/QuestionPagerFragment;", "()V", "pageSelectionChangedListener", "Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment$OnPageSelectionChanged;", "getPageSelectionChangedListener", "()Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment$OnPageSelectionChanged;", "setPageSelectionChangedListener", "(Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment$OnPageSelectionChanged;)V", "newInstance", "contentItem", "Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;", "isSubCalc", "", "subCalcQuestion", "Lcom/wbmd/qxcalculator/model/contentItems/calculator/Question;", "subCalcQuestionLinkedCalcIndex", "", "selectedQuestionIndex", "allQuestionsAlreadyAnswered", "(Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;Ljava/lang/Boolean;Lcom/wbmd/qxcalculator/model/contentItems/calculator/Question;IIZ)Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment;", "onAttach", "", "context", "Landroid/content/Context;", "setupViewPager", "view", "Landroid/view/View;", "setupViewPagerOnChangeListener", "OnPageSelectionChanged", "QuestionsFragmentPagerAdapter", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuestionPagerFragment.kt */
public final class QuestionPagerFragment extends com.wbmd.qxcalculator.fragments.calculator.QuestionPagerFragment {
    private HashMap _$_findViewCache;
    public OnPageSelectionChanged pageSelectionChangedListener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment$OnPageSelectionChanged;", "", "onQuestionChanged", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: QuestionPagerFragment.kt */
    public interface OnPageSelectionChanged {
        void onQuestionChanged();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final OnPageSelectionChanged getPageSelectionChangedListener() {
        OnPageSelectionChanged onPageSelectionChanged = this.pageSelectionChangedListener;
        if (onPageSelectionChanged == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageSelectionChangedListener");
        }
        return onPageSelectionChanged;
    }

    public final void setPageSelectionChangedListener(OnPageSelectionChanged onPageSelectionChanged) {
        Intrinsics.checkNotNullParameter(onPageSelectionChanged, "<set-?>");
        this.pageSelectionChangedListener = onPageSelectionChanged;
    }

    public final QuestionPagerFragment newInstance(ContentItem contentItem, Boolean bool, Question question, int i, int i2, boolean z) {
        QuestionPagerFragment questionPagerFragment = new QuestionPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("CalculatorQuestionActivityFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        if (bool != null) {
            bundle.putBoolean("CalculatorQuestionActivityFragment.KEY_ARG_IS_SUB_CALC", bool.booleanValue());
        }
        if (question != null) {
            bundle.putParcelable("CalculatorQuestionActivityFragment.KEY_ARG_SUB_CALC_QUESTION", question);
            bundle.putInt("CalculatorQuestionActivityFragment.KEY_ARG_SUB_CALC_QUESTION_LINKED_CALC_INDEX", i);
        }
        bundle.putInt("CalculatorQuestionActivityFragment.KEY_ARG_SELECTED_QUESTION_INDEX", i2);
        bundle.putBoolean("CalculatorQuestionActivityFragment.KEY_ALL_QUESTIONS_ALREADY_ANSWERED", z);
        questionPagerFragment.setArguments(bundle);
        return questionPagerFragment;
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof OnPageSelectionChanged) {
            this.pageSelectionChangedListener = (OnPageSelectionChanged) context;
        }
    }

    public void setupViewPager(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = view.findViewById(R.id.viewpager);
        if (findViewById != null) {
            this.mViewPager = (ViewPager) findViewById;
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
            this.mPagerAdapter = new QuestionsFragmentPagerAdapter(this, childFragmentManager);
            ViewPager viewPager = this.mViewPager;
            Intrinsics.checkNotNullExpressionValue(viewPager, "mViewPager");
            viewPager.setAdapter(this.mPagerAdapter);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.viewpager.widget.ViewPager");
    }

    /* access modifiers changed from: protected */
    public void setupViewPagerOnChangeListener() {
        this.pageChangeListener = new QuestionPagerFragment$setupViewPagerOnChangeListener$1(this);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment$QuestionsFragmentPagerAdapter;", "Lcom/wbmd/qxcalculator/fragments/calculator/QuestionPagerFragment$QuestionsFragmentPagerAdapter;", "Lcom/wbmd/qxcalculator/fragments/calculator/QuestionPagerFragment;", "fm", "Landroidx/fragment/app/FragmentManager;", "(Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment;Landroidx/fragment/app/FragmentManager;)V", "getItem", "Landroidx/fragment/app/Fragment;", "index", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: QuestionPagerFragment.kt */
    public final class QuestionsFragmentPagerAdapter extends QuestionPagerFragment.QuestionsFragmentPagerAdapter {
        final /* synthetic */ QuestionPagerFragment this$0;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Question.QuestionType.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[Question.QuestionType.QuestionTypeMultipleChoice.ordinal()] = 1;
                $EnumSwitchMapping$0[Question.QuestionType.QuestionTypeNumericEntryNoUnits.ordinal()] = 2;
                $EnumSwitchMapping$0[Question.QuestionType.QuestionTypeNumericEntrySingleUnits.ordinal()] = 3;
                $EnumSwitchMapping$0[Question.QuestionType.QuestionTypeNumericEntryMultipleUnits.ordinal()] = 4;
                $EnumSwitchMapping$0[Question.QuestionType.QuestionTypeDateInput.ordinal()] = 5;
                $EnumSwitchMapping$0[Question.QuestionType.QuestionTypeDaysInput.ordinal()] = 6;
                $EnumSwitchMapping$0[Question.QuestionType.QuestionTypeLinkedCalculator.ordinal()] = 7;
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public QuestionsFragmentPagerAdapter(QuestionPagerFragment questionPagerFragment, FragmentManager fragmentManager) {
            super(fragmentManager);
            Intrinsics.checkNotNullParameter(fragmentManager, "fm");
            this.this$0 = questionPagerFragment;
        }

        public Fragment getItem(int i) {
            Question question = this.this$0.calculator.questions.get(i);
            Intrinsics.checkNotNullExpressionValue(question, "question");
            Question.QuestionType questionType = question.getQuestionType();
            if (questionType != null) {
                switch (WhenMappings.$EnumSwitchMapping$0[questionType.ordinal()]) {
                    case 1:
                        QuestionMultipleChoiceFragment questionMultipleChoiceFragment = new QuestionMultipleChoiceFragment();
                        ContentItem access$getContentItem$p = this.this$0.contentItem;
                        Intrinsics.checkNotNullExpressionValue(access$getContentItem$p, "contentItem");
                        return questionMultipleChoiceFragment.newInstance(question, access$getContentItem$p);
                    case 2:
                        QuestionNumericInputFragment questionNumericInputFragment = new QuestionNumericInputFragment();
                        ContentItem access$getContentItem$p2 = this.this$0.contentItem;
                        Intrinsics.checkNotNullExpressionValue(access$getContentItem$p2, "contentItem");
                        return questionNumericInputFragment.newInstance(question, access$getContentItem$p2);
                    case 3:
                        QuestionNumericInputFragment questionNumericInputFragment2 = new QuestionNumericInputFragment();
                        ContentItem access$getContentItem$p3 = this.this$0.contentItem;
                        Intrinsics.checkNotNullExpressionValue(access$getContentItem$p3, "contentItem");
                        return questionNumericInputFragment2.newInstance(question, access$getContentItem$p3);
                    case 4:
                        QuestionNumericInputFragment questionNumericInputFragment3 = new QuestionNumericInputFragment();
                        ContentItem access$getContentItem$p4 = this.this$0.contentItem;
                        Intrinsics.checkNotNullExpressionValue(access$getContentItem$p4, "contentItem");
                        return questionNumericInputFragment3.newInstance(question, access$getContentItem$p4);
                    case 5:
                        return new QuestionDateInputFragment().newInstance(question, this.this$0.contentItem);
                    case 6:
                        QuestionDaysInputFragment newInstance = QuestionDaysInputFragment.newInstance(question, this.this$0.contentItem);
                        Intrinsics.checkNotNullExpressionValue(newInstance, "QuestionDaysInputFragmen…ce(question, contentItem)");
                        return newInstance;
                    case 7:
                        QuestionLinkedCalculator newInstance2 = QuestionLinkedCalculator.newInstance(question, this.this$0.contentItem);
                        Intrinsics.checkNotNullExpressionValue(newInstance2, "QuestionLinkedCalculator…ce(question, contentItem)");
                        return newInstance2;
                }
            }
            Fragment item = super.getItem(i);
            Intrinsics.checkNotNullExpressionValue(item, "super.getItem(index)");
            return item;
        }
    }
}
