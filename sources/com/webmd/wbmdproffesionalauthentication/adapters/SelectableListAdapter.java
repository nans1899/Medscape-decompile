package com.webmd.wbmdproffesionalauthentication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.interfaces.IListItemSelectionListener;
import com.webmd.wbmdproffesionalauthentication.utilities.ListTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectableListAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    /* access modifiers changed from: private */
    public List<String> mDataList;
    /* access modifiers changed from: private */
    public Map<String, String> mListItems;
    /* access modifiers changed from: private */
    public ListTypes mListTypes;
    /* access modifiers changed from: private */
    public IListItemSelectionListener mOnItemClickListener;
    /* access modifiers changed from: private */
    public String mSelectedItem;

    public SelectableListAdapter(Map<String, String> map, String str, ListTypes listTypes, IListItemSelectionListener iListItemSelectionListener) {
        if (map != null) {
            this.mDataList = new ArrayList(map.keySet());
        }
        this.mSelectedItem = str;
        this.mOnItemClickListener = iListItemSelectionListener;
        this.mListItems = map;
        this.mListTypes = listTypes;
    }

    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ListItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selection_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ListItemViewHolder listItemViewHolder, final int i) {
        List<String> list = this.mDataList;
        if (list != null && list.size() > 0) {
            listItemViewHolder.mLabelTextView.setText(this.mDataList.get(i));
            if (this.mSelectedItem.equals(this.mDataList.get(i))) {
                listItemViewHolder.mSelectedLabel.setVisibility(0);
            } else {
                listItemViewHolder.mSelectedLabel.setVisibility(8);
            }
            listItemViewHolder.rowLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SelectableListAdapter selectableListAdapter = SelectableListAdapter.this;
                    String unused = selectableListAdapter.mSelectedItem = (String) selectableListAdapter.mDataList.get(i);
                    SelectableListAdapter.this.mOnItemClickListener.onItemSelected(SelectableListAdapter.this.mSelectedItem, (String) SelectableListAdapter.this.mListItems.get(SelectableListAdapter.this.mSelectedItem), SelectableListAdapter.this.mListTypes);
                }
            });
        }
    }

    public int getItemCount() {
        List<String> list = this.mDataList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mLabelTextView;
        public ImageView mSelectedLabel;
        public LinearLayout rowLayout;

        public ListItemViewHolder(View view) {
            super(view);
            this.mLabelTextView = (TextView) view.findViewById(R.id.selection_textViewLabel);
            this.mSelectedLabel = (ImageView) view.findViewById(R.id.selection_imageViewSelected);
            this.rowLayout = (LinearLayout) view.findViewById(R.id.selection_row_layout);
        }
    }
}
