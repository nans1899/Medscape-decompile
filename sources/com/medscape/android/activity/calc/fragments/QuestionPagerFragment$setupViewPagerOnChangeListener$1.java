package com.medscape.android.activity.calc.fragments;

import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.wbmd.qxcalculator.fragments.calculator.QuestionFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/medscape/android/activity/calc/fragments/QuestionPagerFragment$setupViewPagerOnChangeListener$1", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuestionPagerFragment.kt */
public final class QuestionPagerFragment$setupViewPagerOnChangeListener$1 implements ViewPager.OnPageChangeListener {
    final /* synthetic */ QuestionPagerFragment this$0;

    public void onPageScrollStateChanged(int i) {
    }

    QuestionPagerFragment$setupViewPagerOnChangeListener$1(QuestionPagerFragment questionPagerFragment) {
        this.this$0 = questionPagerFragment;
    }

    public void onPageScrolled(int i, float f, int i2) {
        Log.d("CHAN", "onPageScrolled " + i + "; posOffset: " + f + "; selectedQI: " + this.this$0.selectedQuestionIndex);
        if (f != 0.0f) {
            return;
        }
        if (this.this$0.shouldNotifyDidAppearToInitialQuestion) {
            Fragment registeredFragment = this.this$0.mPagerAdapter.getRegisteredFragment(this.this$0.selectedQuestionIndex);
            if (registeredFragment != null) {
                ((QuestionFragment) registeredFragment).didAppear();
                View access$getInputBlockerView$p = this.this$0.inputBlockerView;
                Intrinsics.checkNotNullExpressionValue(access$getInputBlockerView$p, "inputBlockerView");
                access$getInputBlockerView$p.setVisibility(8);
                this.this$0.shouldNotifyDidAppearToInitialQuestion = false;
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.wbmd.qxcalculator.fragments.calculator.QuestionFragment");
        } else if (this.this$0.selectedQuestionIndex != i) {
            Fragment registeredFragment2 = this.this$0.mPagerAdapter.getRegisteredFragment(this.this$0.selectedQuestionIndex);
            if (registeredFragment2 != null) {
                QuestionFragment questionFragment = (QuestionFragment) registeredFragment2;
                Fragment registeredFragment3 = this.this$0.mPagerAdapter.getRegisteredFragment(i);
                if (registeredFragment3 != null) {
                    this.this$0.selectedQuestionIndex = i;
                    questionFragment.didDisappear();
                    ((QuestionFragment) registeredFragment3).didAppear();
                    View access$getInputBlockerView$p2 = this.this$0.inputBlockerView;
                    Intrinsics.checkNotNullExpressionValue(access$getInputBlockerView$p2, "inputBlockerView");
                    access$getInputBlockerView$p2.setVisibility(8);
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.wbmd.qxcalculator.fragments.calculator.QuestionFragment");
            }
            throw new NullPointerException("null cannot be cast to non-null type com.wbmd.qxcalculator.fragments.calculator.QuestionFragment");
        }
    }

    public void onPageSelected(int i) {
        this.this$0.updateTitle(i);
        this.this$0.getPageSelectionChangedListener().onQuestionChanged();
    }
}
