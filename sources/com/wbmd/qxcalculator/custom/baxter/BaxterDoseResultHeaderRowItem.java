package com.wbmd.qxcalculator.custom.baxter;

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
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;

public class BaxterDoseResultHeaderRowItem extends QxRecyclerViewRowItem {
    public Result result;
    private Spanned title;

    public BaxterDoseResultHeaderRowItem(Result result2) {
        this.result = result2;
        if (result2.titleFormulaResult != null) {
            this.title = Html.fromHtml(result2.titleFormulaResult);
        } else if (result2.title != null) {
            this.title = Html.fromHtml(result2.title);
        }
    }

    public int getResourceId() {
        return R.layout.row_item_baxter_dose_result_header;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return BaxterDoseResultHeaderViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        BaxterDoseResultHeaderViewHolder baxterDoseResultHeaderViewHolder = (BaxterDoseResultHeaderViewHolder) viewHolder;
        if (this.title != null) {
            baxterDoseResultHeaderViewHolder.textView.setText(this.title);
            baxterDoseResultHeaderViewHolder.textView.setVisibility(0);
            return;
        }
        baxterDoseResultHeaderViewHolder.textView.setVisibility(8);
    }

    public static final class BaxterDoseResultHeaderViewHolder extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public BaxterDoseResultHeaderViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.result_title_text_view);
        }
    }
}
