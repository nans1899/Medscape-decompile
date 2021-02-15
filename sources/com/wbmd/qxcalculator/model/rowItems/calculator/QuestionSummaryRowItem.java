package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;

public class QuestionSummaryRowItem extends QxRecyclerViewRowItem {
    private static int colorUnanswered;
    private int answerColor = -65536;
    private Spanned answerSpanned;
    public Question question;
    private Spanned questionSpanned;

    public QuestionSummaryRowItem(Question question2, Context context) {
        this.question = question2;
        this.questionSpanned = Html.fromHtml(question2.title);
        String answerString = question2.getAnswerString();
        if (answerString == null || answerString.isEmpty()) {
            this.answerSpanned = Html.fromHtml(context.getString(R.string.question_not_answered));
            return;
        }
        this.answerSpanned = Html.fromHtml(answerString);
        this.answerColor = context.getResources().getColor(R.color.text_calculator_question_summary_answer);
    }

    public int getResourceId() {
        return R.layout.row_item_question_summary;
    }

    public long getItemId() {
        return this.question.orderedId.longValue();
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return QuestionSummaryViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        QuestionSummaryViewHolder questionSummaryViewHolder = (QuestionSummaryViewHolder) viewHolder;
        questionSummaryViewHolder.questionTextView.setText(this.questionSpanned);
        questionSummaryViewHolder.answerTextView.setText(this.answerSpanned);
        questionSummaryViewHolder.answerTextView.setTextColor(this.answerColor);
        int i2 = AnonymousClass1.$SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition[rowPosition.ordinal()];
        if (i2 == 1) {
            questionSummaryViewHolder.separatorView.setVisibility(4);
        } else if (i2 != 2) {
            questionSummaryViewHolder.separatorView.setVisibility(0);
        } else {
            questionSummaryViewHolder.separatorView.setVisibility(4);
        }
    }

    /* renamed from: com.wbmd.qxcalculator.model.rowItems.calculator.QuestionSummaryRowItem$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.qxmd.qxrecyclerview.QxRecyclerViewRowItem$RowPosition[] r0 = com.qxmd.qxrecyclerview.QxRecyclerViewRowItem.RowPosition.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition = r0
                com.qxmd.qxrecyclerview.QxRecyclerViewRowItem$RowPosition r1 = com.qxmd.qxrecyclerview.QxRecyclerViewRowItem.RowPosition.SINGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$qxmd$qxrecyclerview$QxRecyclerViewRowItem$RowPosition     // Catch:{ NoSuchFieldError -> 0x001d }
                com.qxmd.qxrecyclerview.QxRecyclerViewRowItem$RowPosition r1 = com.qxmd.qxrecyclerview.QxRecyclerViewRowItem.RowPosition.BOTTOM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.rowItems.calculator.QuestionSummaryRowItem.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class QuestionSummaryViewHolder extends QxRecyclerRowItemViewHolder {
        TextView answerTextView;
        TextView questionTextView;
        View separatorView;

        public QuestionSummaryViewHolder(View view) {
            super(view);
            this.questionTextView = (TextView) view.findViewById(R.id.question_text_view);
            this.answerTextView = (TextView) view.findViewById(R.id.answer_text_view);
            this.separatorView = view.findViewById(R.id.separator_view);
        }
    }
}
