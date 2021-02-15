package com.wbmd.qxcalculator.fragments.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorItem;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {
    protected static final String KEY_ARG_CONTENT_ITEM = "QuestionFragment.KEY_ARG_CONTENT_ITEM";
    protected static final String KEY_ARG_QUESTION = "QuestionFragment.KEY_ARG_QUESTION";
    private static final String KEY_SELECTED_LINKED_CALC_INDEX = "QuestionFragment.KEY_SELECTED_LINKED_CALC_INDEX";
    public static final int REQUEST_CODE_LINKED_CALCULATOR = 1;
    protected ContentItem contentItem;
    protected PopupMenu linkedCalculatorPopupMenu;
    protected Question question;
    /* access modifiers changed from: protected */
    public int selectedLinkedCalculatorIndex = -1;

    public void didAppear() {
    }

    public void didDisappear() {
    }

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onLinkedCalculatorResultsReady() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.selectedLinkedCalculatorIndex = bundle.getInt(KEY_SELECTED_LINKED_CALC_INDEX, -1);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Log.d("API", "CalculatorQuestionFragment onSaveInstanceState");
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_SELECTED_LINKED_CALC_INDEX, this.selectedLinkedCalculatorIndex);
        bundle.putParcelable(KEY_ARG_QUESTION, this.question);
    }

    /* access modifiers changed from: protected */
    public void linkedCalculatorRowTapped(View view) {
        if (this.question.linkedCalculatorItems != null && !this.question.linkedCalculatorItems.isEmpty()) {
            if (this.question.linkedCalculatorItems.size() == 1) {
                ContentDataManager instance = ContentDataManager.getInstance();
                launchLinkedContentItem(instance.getContentItemForIdentifier("calculator_" + this.question.linkedCalculatorItems.get(0).calculatorIdentifier), 0);
                return;
            }
            this.linkedCalculatorPopupMenu = new PopupMenu(getActivity(), view);
            ArrayList arrayList = new ArrayList(this.question.linkedCalculatorItems.size());
            for (LinkedCalculatorItem linkedCalculatorItem : this.question.linkedCalculatorItems) {
                arrayList.add(linkedCalculatorItem.calculatorIdentifier);
            }
            final List<DBContentItem> contentItemsForIdentifiers = ContentDataManager.getInstance().getContentItemsForIdentifiers(arrayList);
            for (int i = 0; i < contentItemsForIdentifiers.size(); i++) {
                this.linkedCalculatorPopupMenu.getMenu().add(0, i, i, contentItemsForIdentifiers.get(i).getName());
            }
            this.linkedCalculatorPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem menuItem) {
                    QuestionFragment.this.selectedLinkedCalculatorIndex = menuItem.getItemId();
                    QuestionFragment questionFragment = QuestionFragment.this;
                    questionFragment.launchLinkedContentItem((DBContentItem) contentItemsForIdentifiers.get(QuestionFragment.this.selectedLinkedCalculatorIndex), questionFragment.selectedLinkedCalculatorIndex);
                    return true;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void launchLinkedContentItem(DBContentItem dBContentItem, int i) {
        if (dBContentItem != null) {
            ((QuestionPagerFragment) getParentFragment()).notifyLeavingCalculator();
            Intent intent = new Intent();
            intent.putExtra(CalculatorActivity.KEY_EXTRA_IS_SUB_CALC, true);
            intent.putExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION, this.question);
            intent.putExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX, i);
            ContentItemLaunchManager.getInstance().launchContentItem(dBContentItem, getActivity(), intent, 1, true);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        r5 = (com.wbmd.qxcalculator.model.contentItems.common.ContentItem) r7.getParcelableExtra(com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity.KEY_EXTRA_CALCULATOR);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLinkedCalculatorResult(int r5, int r6, android.content.Intent r7) {
        /*
            r4 = this;
            r6 = 1
            if (r5 != r6) goto L_0x006d
            if (r7 == 0) goto L_0x006d
            java.lang.String r5 = "CalculatorActivity.KEY_EXTRA_CALCULATOR"
            android.os.Parcelable r5 = r7.getParcelableExtra(r5)
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r5 = (com.wbmd.qxcalculator.model.contentItems.common.ContentItem) r5
            com.wbmd.qxcalculator.model.contentItems.calculator.Calculator r0 = r5.calculator
            if (r0 == 0) goto L_0x006d
            boolean r0 = r0.allQuestionsAnswered()
            if (r0 == 0) goto L_0x006d
            java.lang.String r0 = "CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION"
            android.os.Parcelable r7 = r7.getParcelableExtra(r0)
            com.wbmd.qxcalculator.model.contentItems.calculator.Question r7 = (com.wbmd.qxcalculator.model.contentItems.calculator.Question) r7
            if (r7 == 0) goto L_0x0031
            com.wbmd.qxcalculator.model.contentItems.calculator.Question r5 = r4.question
            java.lang.String r6 = r7.linkedCalculatorConvertResult
            r5.linkedCalculatorConvertResult = r6
            com.wbmd.qxcalculator.model.contentItems.calculator.Question r5 = r4.question
            java.lang.Integer r6 = r7.linkedCalculatorConvertResultUnit
            r5.linkedCalculatorConvertResultUnit = r6
            r4.onLinkedCalculatorResultsReady()
            goto L_0x006d
        L_0x0031:
            androidx.fragment.app.FragmentActivity r7 = r4.getActivity()
            com.wbmd.qxcalculator.activities.common.QxMDActivity r7 = (com.wbmd.qxcalculator.activities.common.QxMDActivity) r7
            int r0 = com.wbmd.qxcalculator.R.string.dialog_qxmd_support_email_body_linked_calculator_body
            r1 = 5
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.String r3 = r5.name
            r1[r2] = r3
            java.lang.String r5 = r5.identifier
            java.lang.String r5 = com.wbmd.qxcalculator.util.Util.truncateCalculatorIdForFirebase(r5)
            r1[r6] = r5
            r5 = 2
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r6 = r4.contentItem
            java.lang.String r6 = r6.name
            r1[r5] = r6
            r5 = 3
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r6 = r4.contentItem
            java.lang.String r6 = r6.identifier
            java.lang.String r6 = com.wbmd.qxcalculator.util.Util.truncateCalculatorIdForFirebase(r6)
            r1[r5] = r6
            r5 = 4
            com.wbmd.qxcalculator.AppConfiguration r6 = com.wbmd.qxcalculator.AppConfiguration.getInstance()
            java.lang.String r6 = r6.getAppBuildVersion()
            r1[r5] = r6
            java.lang.String r5 = r4.getString(r0, r1)
            r7.showErrorDialogSupport(r5)
        L_0x006d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.fragments.calculator.QuestionFragment.onLinkedCalculatorResult(int, int, android.content.Intent):void");
    }

    public void tabBecameVisible() {
        if (getAnalyticsScreenName() != null) {
            Log.d("QuestionsTab", "tab became visible: " + getAnalyticsScreenName());
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && isResumed()) {
            tabBecameVisible();
        }
    }

    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            tabBecameVisible();
        }
    }
}
