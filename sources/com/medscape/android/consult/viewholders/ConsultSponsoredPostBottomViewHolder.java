package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IPostDetailCommentSelectedListener;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.util.MedscapeException;
import com.webmd.wbmdcmepulse.models.utils.Utilities;

public class ConsultSponsoredPostBottomViewHolder extends ConsultPostDetailBodyBottomViewHolder {
    protected TextView chatCount;
    protected View commentLayout;
    protected TextView followPost;
    protected TextView respondText;

    public ConsultSponsoredPostBottomViewHolder(View view, Activity activity, IPostDetailCommentSelectedListener iPostDetailCommentSelectedListener) {
        super(view, activity, iPostDetailCommentSelectedListener);
        this.commentLayout = view.findViewById(R.id.comment_layout);
        this.chatCount = (TextView) view.findViewById(R.id.chat_count);
        this.respondText = (TextView) view.findViewById(R.id.respond);
        this.followPost = (TextView) view.findViewById(R.id.follow_post);
    }

    public void bindSponsoredPost(final ConsultPost consultPost) {
        setCommentsState(consultPost);
        setFollowUnfollowState(consultPost);
        View view = this.commentLayout;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultSponsoredPostBottomViewHolder.this.handleCommentClick();
                }
            });
        }
        TextView textView = this.followPost;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ConsultSponsoredPostBottomViewHolder.this.mContext == null) {
                        return;
                    }
                    if (Utilities.isNetworkAvailable(ConsultSponsoredPostBottomViewHolder.this.mContext)) {
                        ConsultSponsoredPostBottomViewHolder.this.toggleFollowUnfollowState();
                        ConsultSponsoredPostBottomViewHolder.this.setFollowPost(consultPost);
                        return;
                    }
                    new MedscapeException(ConsultSponsoredPostBottomViewHolder.this.mContext.getString(R.string.error_connection_required)).showAlert(ConsultSponsoredPostBottomViewHolder.this.mContext, ConsultSponsoredPostBottomViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }, (String) null, (DialogInterface.OnClickListener) null);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void toggleFollowUnfollowState() {
        TextView textView = this.followPost;
        if (textView != null && textView.getText() != null) {
            if (this.followPost.getText().equals(this.mContext.getString(R.string.consult_follow_post))) {
                this.followPost.setText(this.mContext.getString(R.string.consult_unfollow_post));
            } else if (this.followPost.getText().equals(this.mContext.getString(R.string.consult_unfollow_post))) {
                this.followPost.setText(this.mContext.getString(R.string.consult_follow_post));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setCommentsState(ConsultPost consultPost) {
        if (consultPost != null && this.respondText != null) {
            if (consultPost.getCommentCount() > 0) {
                this.respondText.setVisibility(8);
                this.chatCount.setVisibility(0);
                this.chatCount.setText(String.format("%s", new Object[]{ConsultUtils.getFormattedConsultCount(consultPost.getCommentCount())}));
                return;
            }
            this.respondText.setVisibility(0);
            this.chatCount.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void setFollowUnfollowState(ConsultPost consultPost) {
        if (consultPost != null && this.followPost != null) {
            if (consultPost.getFollowState() == 3020) {
                this.followPost.setText(this.mContext.getString(R.string.consult_unfollow_post));
            } else {
                this.followPost.setText(this.mContext.getString(R.string.consult_follow_post));
            }
        }
    }
}
