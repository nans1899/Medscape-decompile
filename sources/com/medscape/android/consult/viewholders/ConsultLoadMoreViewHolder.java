package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.util.StringUtil;

public class ConsultLoadMoreViewHolder extends RecyclerView.ViewHolder {
    private TextView mLoadMore;

    public ConsultLoadMoreViewHolder(View view, final ILoadMoreListener iLoadMoreListener) {
        super(view);
        TextView textView = (TextView) view.findViewById(R.id.load_more);
        this.mLoadMore = textView;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ILoadMoreListener iLoadMoreListener = iLoadMoreListener;
                    if (iLoadMoreListener != null) {
                        iLoadMoreListener.onMoreRequested();
                    }
                }
            });
        }
    }

    public void bindText(String str) {
        TextView textView;
        if (StringUtil.isNotEmpty(str) && (textView = this.mLoadMore) != null) {
            textView.setText(str);
        }
    }
}
