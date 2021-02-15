package com.medscape.android.consult.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IMentionSelectedListener;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.viewholders.ConsultMentionableViewHolder;
import java.util.ArrayList;
import java.util.List;

public class ConsultMentionablesListAdapter extends RecyclerView.Adapter<ConsultMentionableViewHolder> {
    /* access modifiers changed from: private */
    public int mEndText;
    /* access modifiers changed from: private */
    public IMentionSelectedListener mListener;
    private List<ConsultFeedItem> mMentionables = new ArrayList();
    /* access modifiers changed from: private */
    public int mStartText;

    public ConsultMentionablesListAdapter(IMentionSelectedListener iMentionSelectedListener) {
        this.mListener = iMentionSelectedListener;
    }

    public ConsultMentionableViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ConsultMentionableViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.consult_mentionable_item, viewGroup, false), new IMentionSelectedListener() {
            public void onMentionSelected(ConsultUser consultUser, Integer num, int i) {
                ConsultMentionablesListAdapter.this.mListener.onMentionSelected(consultUser, Integer.valueOf(ConsultMentionablesListAdapter.this.mStartText), ConsultMentionablesListAdapter.this.mEndText);
            }
        });
    }

    public void onBindViewHolder(ConsultMentionableViewHolder consultMentionableViewHolder, int i) {
        ConsultFeedItem consultFeedItem = this.mMentionables.get(i);
        if (consultFeedItem instanceof ConsultUser) {
            consultMentionableViewHolder.bindPost((ConsultUser) consultFeedItem);
        }
    }

    public int getItemCount() {
        List<ConsultFeedItem> list = this.mMentionables;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<ConsultFeedItem> list, int i, int i2) {
        this.mMentionables = list;
        this.mStartText = i;
        this.mEndText = i2;
    }
}
