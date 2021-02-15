package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.view.View;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.IPostDetailCommentSelectedListener;
import com.medscape.android.consult.models.ConsultPost;

public class ConsultPostDetailBodyBottomViewHolder extends ConsultPostBodyViewHolder {
    private View mCommentCount;
    private View mCommentLayout;
    private IPostDetailCommentSelectedListener mCommentSelectedListener;

    public ConsultPostDetailBodyBottomViewHolder(View view, Activity activity, IPostDeletedListener iPostDeletedListener, IPostDetailCommentSelectedListener iPostDetailCommentSelectedListener) {
        super(view);
        super.initializeBottomLayout(view, activity, iPostDeletedListener);
        this.mCommentLayout = view.findViewById(R.id.comment_layout);
        this.mCommentCount = view.findViewById(R.id.chat_count);
        this.mCommentSelectedListener = iPostDetailCommentSelectedListener;
    }

    ConsultPostDetailBodyBottomViewHolder(View view, Activity activity, IPostDetailCommentSelectedListener iPostDetailCommentSelectedListener) {
        super(view);
        this.mContext = activity;
        this.mCommentSelectedListener = iPostDetailCommentSelectedListener;
    }

    public void bindPost(ConsultPost consultPost, int i) {
        super.bindBottomLayout(consultPost, i);
        this.mCommentLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultPostDetailBodyBottomViewHolder.this.handleCommentClick();
            }
        });
        this.mCommentCount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultPostDetailBodyBottomViewHolder.this.handleCommentClick();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleCommentClick() {
        this.mCommentSelectedListener.onPostDetailCommentSelected();
    }
}
