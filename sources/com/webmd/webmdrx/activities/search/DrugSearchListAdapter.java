package com.webmd.webmdrx.activities.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class DrugSearchListAdapter extends RecyclerView.Adapter<DrugSearchResultViewHolder> {
    private static final int VIEW_TYPE_DRUG_SEARCH_RESULT = 0;
    private Context mContext;
    private List<DrugSearchResult> mItems = new ArrayList();
    private String mRequestedSearchString;

    public int getItemViewType(int i) {
        return 0;
    }

    public DrugSearchListAdapter(Context context) {
        this.mContext = context;
    }

    public DrugSearchResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DrugSearchResultViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drug_search_result_list_item, viewGroup, false), this.mContext);
    }

    public void onBindViewHolder(DrugSearchResultViewHolder drugSearchResultViewHolder, int i) {
        DrugSearchResult drugSearchResult;
        List<DrugSearchResult> list = this.mItems;
        if (list != null && list.size() > i && (drugSearchResult = this.mItems.get(i)) != null && StringUtil.isNotEmpty(drugSearchResult.getDrugName())) {
            drugSearchResultViewHolder.bindDrug(this.mRequestedSearchString, drugSearchResult);
        }
    }

    public int getItemCount() {
        List<DrugSearchResult> list = this.mItems;
        if (list == null || list.size() == 0) {
            return 0;
        }
        if (this.mItems.size() < 50) {
            return this.mItems.size();
        }
        return 50;
    }

    public void setItemsForRequest(String str, List<DrugSearchResult> list) {
        this.mRequestedSearchString = str;
        this.mItems = list;
        notifyDataSetChanged();
    }
}
