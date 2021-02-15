package com.medscape.android.reference.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.reference.interfaces.IFolderItemClickCallback;
import com.medscape.android.reference.model.ClinicalReference;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = FolderAdapter.class.getSimpleName();
    private ArrayList<ClinicalReferenceArticle> mArticleItems;
    private ArrayList<ClinicalReference> mClinicalReferenceItems;
    /* access modifiers changed from: private */
    public IFolderItemClickCallback mOnFolderItemClickCallback;

    public FolderAdapter(ArrayList<ClinicalReference> arrayList, ArrayList<ClinicalReferenceArticle> arrayList2, IFolderItemClickCallback iFolderItemClickCallback) {
        this.mClinicalReferenceItems = arrayList;
        this.mArticleItems = arrayList2;
        this.mOnFolderItemClickCallback = iFolderItemClickCallback;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.folder_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final TextView textView = viewHolder.item;
        ArrayList<ClinicalReference> arrayList = this.mClinicalReferenceItems;
        if (arrayList == null || arrayList.size() <= 0) {
            ArrayList<ClinicalReferenceArticle> arrayList2 = this.mArticleItems;
            if (arrayList2 != null && arrayList2.size() > 0) {
                final ClinicalReferenceArticle clinicalReferenceArticle = this.mArticleItems.get(i);
                textView.setText(clinicalReferenceArticle.getTitle());
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FolderAdapter.this.mOnFolderItemClickCallback.onItemClicked(clinicalReferenceArticle, i, (View) textView);
                    }
                });
                return;
            }
            return;
        }
        final ClinicalReference clinicalReference = this.mClinicalReferenceItems.get(i);
        textView.setText(clinicalReference.getName());
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FolderAdapter.this.mOnFolderItemClickCallback.onItemClicked(clinicalReference, i, (View) textView);
            }
        });
    }

    public int getItemCount() {
        ArrayList<ClinicalReference> arrayList = this.mClinicalReferenceItems;
        if (arrayList != null && arrayList.size() > 0) {
            return this.mClinicalReferenceItems.size();
        }
        ArrayList<ClinicalReferenceArticle> arrayList2 = this.mArticleItems;
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return 0;
        }
        return this.mArticleItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item = ((TextView) this.itemView.findViewById(R.id.row_item));

        public ViewHolder(View view) {
            super(view);
        }
    }
}
