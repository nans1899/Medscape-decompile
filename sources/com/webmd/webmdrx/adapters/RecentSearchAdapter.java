package com.webmd.webmdrx.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.PrescriptionDetailsActivity;
import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.Util;
import java.util.ArrayList;

public class RecentSearchAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public ArrayList<DrugSearchResult> recentSearches;

    public void refresh(ArrayList<DrugSearchResult> arrayList) {
        this.recentSearches = arrayList;
        notifyDataSetChanged();
    }

    public RecentSearchAdapter(Context context, ArrayList<DrugSearchResult> arrayList) {
        this.recentSearches = arrayList;
        this.mContext = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recent_search, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        String fullNameForDrug = Util.getFullNameForDrug(this.recentSearches.get(i));
        final TextView textView = viewHolder.mTVDrugName;
        textView.setText(fullNameForDrug);
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Util.saveDrugToRecentSearches(RecentSearchAdapter.this.mContext.getApplicationContext(), (DrugSearchResult) RecentSearchAdapter.this.recentSearches.get(viewHolder.getAdapterPosition()));
                Intent intent = new Intent(textView.getContext(), PrescriptionDetailsActivity.class);
                intent.putExtra(Constants.EXTRA_DRUG_SEARCH_RESULT, (Parcelable) RecentSearchAdapter.this.recentSearches.get(viewHolder.getAdapterPosition()));
                intent.putExtra(Constants.EXTRA_ICD_FROM_ACTIVITY, 1);
                textView.getContext().startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.recentSearches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mTVDrugName;
        final View rootView;

        ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.mTVDrugName = (TextView) view.findViewById(R.id.li_recent_text_view);
        }
    }
}
