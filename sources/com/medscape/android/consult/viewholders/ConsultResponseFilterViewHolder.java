package com.medscape.android.consult.viewholders;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IResponseFilterListener;

public class ConsultResponseFilterViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public Context mContext;
    private TextView mCurrentResponseCount;
    /* access modifiers changed from: private */
    public TextView mCurrentResponseFilter;
    /* access modifiers changed from: private */
    public View mResponseFilterDropDown;
    private View mRoot;

    public ConsultResponseFilterViewHolder(View view, Context context, IResponseFilterListener iResponseFilterListener) {
        super(view);
        this.mContext = context;
        this.mRoot = view.findViewById(R.id.root);
        this.mCurrentResponseFilter = (TextView) view.findViewById(R.id.current_response_filter);
        this.mCurrentResponseCount = (TextView) view.findViewById(R.id.current_responses_count);
        this.mResponseFilterDropDown = view.findViewById(R.id.response_filter_dropdown);
        addResponseFilterDropDownListener(iResponseFilterListener);
    }

    private void addResponseFilterDropDownListener(final IResponseFilterListener iResponseFilterListener) {
        this.mResponseFilterDropDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultResponseFilterViewHolder.this.handleDropDownClick(view, iResponseFilterListener);
            }
        });
        this.mRoot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultResponseFilterViewHolder consultResponseFilterViewHolder = ConsultResponseFilterViewHolder.this;
                consultResponseFilterViewHolder.handleDropDownClick(consultResponseFilterViewHolder.mResponseFilterDropDown, iResponseFilterListener);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleDropDownClick(View view, final IResponseFilterListener iResponseFilterListener) {
        PopupMenu popupMenu = new PopupMenu(this.mContext, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.most_popular) {
                    ConsultResponseFilterViewHolder.this.mCurrentResponseFilter.setText(ConsultResponseFilterViewHolder.this.mContext.getResources().getString(R.string.consult_replies_most_popular));
                    iResponseFilterListener.onResponseFilterSelected(Constants.CONSULT_COMMENT_FILTER_POPULAR);
                    return true;
                } else if (itemId == R.id.newest_first) {
                    ConsultResponseFilterViewHolder.this.mCurrentResponseFilter.setText(ConsultResponseFilterViewHolder.this.mContext.getResources().getString(R.string.consult_responses_newest));
                    iResponseFilterListener.onResponseFilterSelected(Constants.CONSULT_COMMENT_FILTER_NEWEST);
                    return true;
                } else if (itemId != R.id.oldest_first) {
                    return true;
                } else {
                    ConsultResponseFilterViewHolder.this.mCurrentResponseFilter.setText(ConsultResponseFilterViewHolder.this.mContext.getResources().getString(R.string.consult_responses_oldest));
                    iResponseFilterListener.onResponseFilterSelected(Constants.CONSULT_COMMENT_FILTER_OLDEST);
                    return true;
                }
            }
        });
        popupMenu.inflate(R.menu.consult_response_filter_menu);
        popupMenu.show();
    }

    public void setCurrentResponseCount(String str) {
        this.mCurrentResponseCount.setText(str + " Responses");
    }
}
