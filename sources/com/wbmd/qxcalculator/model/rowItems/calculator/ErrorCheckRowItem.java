package com.wbmd.qxcalculator.model.rowItems.calculator;

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
import com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck;

public class ErrorCheckRowItem extends QxRecyclerViewRowItem {
    private Spanned answerSpanned;
    public ErrorCheck errorCheck;

    public ErrorCheckRowItem(ErrorCheck errorCheck2) {
        this.errorCheck = errorCheck2;
        this.answerSpanned = Html.fromHtml(errorCheck2.answer);
    }

    public int getResourceId() {
        return R.layout.row_item_error_check;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return ErrorCheckViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ((ErrorCheckViewHolder) viewHolder).textView.setText(this.answerSpanned);
    }

    public static final class ErrorCheckViewHolder extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public ErrorCheckViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.result_title_text_view);
        }
    }
}
