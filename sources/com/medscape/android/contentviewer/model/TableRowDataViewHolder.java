package com.medscape.android.contentviewer.model;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.medscape.android.R;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.contentviewer.TableRowLineItem;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.reference.SelectableTextView;
import com.medscape.android.reference.model.Para;
import com.medscape.android.reference.view.LocatableLinkMovementMethod;
import com.medscape.android.util.Util;
import java.util.Iterator;

public class TableRowDataViewHolder extends DataViewHolder implements View.OnClickListener {
    public static final int BOTTOM_PADDING = 1;
    public static final int NO_PADDING = 0;
    public static final int TOP_PADDING = -1;
    Context context;
    DataViewHolder.DataListClickListener mListener;
    LinearLayout root_layout;
    TableLayout tl;

    public String toString() {
        return "TableRowDataViewHolder";
    }

    public TableRowDataViewHolder(View view, DataViewHolder.DataListClickListener dataListClickListener) {
        super(view);
        this.mListener = dataListClickListener;
        this.tl = (TableLayout) view.findViewById(R.id.tableLayout1);
        this.root_layout = (LinearLayout) view.findViewById(R.id.tableLayout1);
        this.context = view.getContext();
    }

    public void bindTableRow(TableRowLineItem tableRowLineItem) {
        this.root_layout.setBackgroundColor(getBackgroundColor());
        this.tl.removeAllViews();
        TableRow tableRow = new TableRow(this.context);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(-1, -2));
        Iterator<Para> it = tableRowLineItem.tableColumns.iterator();
        while (it.hasNext()) {
            SelectableTextView selectableTextView = new SelectableTextView(this.context);
            selectableTextView.setTextColor(getTextColor());
            selectableTextView.setLayoutParams(new TableRow.LayoutParams(0, -1, 1.0f));
            selectableTextView.setPadding(10, 5, 5, 5);
            selectableTextView.setText(it.next());
            selectableTextView.setBackgroundResource(this.nightModeOn ? R.drawable.tablerow_border_white : R.drawable.tablerow_border);
            selectableTextView.setTextIsSelectable(true);
            selectableTextView.setEnabled(true);
            selectableTextView.setMovementMethod(LocatableLinkMovementMethod.getInstance());
            selectableTextView.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
            if (Build.VERSION.SDK_INT >= 17) {
                selectableTextView.setTextAlignment(4);
            }
            tableRow.addView(selectableTextView);
        }
        if (tableRowLineItem.isTableHeader) {
            tableRow.setBackgroundResource(R.color.native_article_table_divider_color);
        }
        this.tl.addView(tableRow);
        int i = tableRowLineItem.paddingPosition;
        if (i == -1) {
            LinearLayout linearLayout = this.root_layout;
            linearLayout.setPadding(0, (int) Util.dpToPixel(linearLayout.getContext(), 20), 0, 0);
        } else if (i == 0) {
            this.root_layout.setPadding(0, 0, 0, 0);
        } else if (i == 1) {
            LinearLayout linearLayout2 = this.root_layout;
            linearLayout2.setPadding(0, 0, 0, (int) Util.dpToPixel(linearLayout2.getContext(), 10));
        }
    }
}
