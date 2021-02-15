package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.ILoadMoreRepliesListener;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.util.StringUtil;

public class ConsultGetRepliesViewHolder extends RecyclerView.ViewHolder {
    private View mGetRepliesLayout;
    private TextView mLoadMore;
    /* access modifiers changed from: private */
    public ILoadMoreRepliesListener mMoreRepliesListener;

    public ConsultGetRepliesViewHolder(View view, ILoadMoreRepliesListener iLoadMoreRepliesListener) {
        super(view);
        this.mLoadMore = (TextView) view.findViewById(R.id.get_replies);
        this.mGetRepliesLayout = view.findViewById(R.id.get_replies_layout);
        this.mMoreRepliesListener = iLoadMoreRepliesListener;
    }

    public void bindText(String str, final ConsultComment consultComment, final int i) {
        if (consultComment.isParentHidden()) {
            this.mGetRepliesLayout.setVisibility(8);
        } else {
            this.mGetRepliesLayout.setVisibility(0);
        }
        if (StringUtil.isNotEmpty(str)) {
            this.mLoadMore.setText(str);
        }
        this.mLoadMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultGetRepliesViewHolder.this.mMoreRepliesListener.onLoadMoreRepliesForComment(consultComment, i);
            }
        });
    }
}
