package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class QuestionMoreInfoRowItem extends QxRecyclerViewRowItem {
    public ContentItem contentItem;
    private Spanned moreInfoSpanned;
    public Question question;

    public QuestionMoreInfoRowItem(Question question2, final ContentItem contentItem2) {
        this.question = question2;
        this.contentItem = contentItem2;
        if (question2.moreInformation != null && !question2.moreInformation.isEmpty()) {
            this.moreInfoSpanned = Html.fromHtml(question2.moreInformation, new Html.ImageGetter() {
                public Drawable getDrawable(String str) {
                    Log.d("API", "getDrawable " + str);
                    BitmapDrawable scaledDrawable = FileHelper.getInstance().getScaledDrawable(contentItem2, str, -1.0d);
                    if (scaledDrawable != null) {
                        scaledDrawable.setBounds(0, 0, scaledDrawable.getIntrinsicWidth(), scaledDrawable.getIntrinsicHeight());
                    }
                    return scaledDrawable;
                }
            }, (Html.TagHandler) null);
        }
    }

    public int getResourceId() {
        return R.layout.row_item_question_more_info;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return QuestionMoreInfoViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        QuestionMoreInfoViewHolder questionMoreInfoViewHolder = (QuestionMoreInfoViewHolder) viewHolder;
        if (this.moreInfoSpanned != null) {
            questionMoreInfoViewHolder.textView.setText(this.moreInfoSpanned);
            questionMoreInfoViewHolder.textView.setVisibility(0);
            return;
        }
        questionMoreInfoViewHolder.textView.setVisibility(8);
    }

    public static final class QuestionMoreInfoViewHolder extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public QuestionMoreInfoViewHolder(View view) {
            super(view);
            TextView textView2 = (TextView) view.findViewById(R.id.more_info_text_view);
            this.textView = textView2;
            textView2.setMovementMethod(BetterLinkMovementMethod.getInstance());
        }
    }
}
