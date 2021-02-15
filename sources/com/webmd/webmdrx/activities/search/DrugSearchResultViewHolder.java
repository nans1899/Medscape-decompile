package com.webmd.webmdrx.activities.search;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.PrescriptionDetailsActivity;
import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.Util;

public class DrugSearchResultViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public Context mContext;
    private View mRootView;
    private TextView mTextLabel;

    public DrugSearchResultViewHolder(View view, Context context) {
        super(view);
        this.mTextLabel = (TextView) view.findViewById(R.id.text_label);
        this.mRootView = view.findViewById(R.id.root);
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public void bindDrug(String str, final DrugSearchResult drugSearchResult) {
        String replaceAll = str.replaceAll("%20", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (drugSearchResult != null) {
            String fullNameForDrug = Util.getFullNameForDrug(drugSearchResult);
            if (StringUtil.isNotEmpty(fullNameForDrug)) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fullNameForDrug);
                StyleSpan styleSpan = new StyleSpan(1);
                StyleSpan styleSpan2 = new StyleSpan(0);
                if (fullNameForDrug.length() >= replaceAll.length()) {
                    spannableStringBuilder.setSpan(styleSpan, 0, replaceAll.length(), 18);
                    spannableStringBuilder.setSpan(styleSpan2, replaceAll.length(), fullNameForDrug.length(), 18);
                    this.mTextLabel.setText(spannableStringBuilder);
                }
                this.mRootView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Util.saveDrugToRecentSearches(DrugSearchResultViewHolder.this.mContext.getApplicationContext(), drugSearchResult);
                        Intent intent = new Intent(DrugSearchResultViewHolder.this.mContext, PrescriptionDetailsActivity.class);
                        intent.putExtra(Constants.EXTRA_DRUG_SEARCH_RESULT, drugSearchResult);
                        intent.putExtra(Constants.EXTRA_ICD_FROM_ACTIVITY, 1);
                        DrugSearchResultViewHolder.this.mContext.startActivity(intent);
                    }
                });
            }
        }
    }
}
