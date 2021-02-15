package com.medscape.android.consult.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.activity.ConsultPostDetailActivity;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultProfileImageView;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.StringUtil;
import java.util.List;

public class ConsultResponsesViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public Context mContext;
    TextView mThreatTitle;
    LinearLayout responsesContainer;
    LinearLayout root;

    public ConsultResponsesViewHolder(View view, Context context) {
        super(view);
        this.mContext = context;
        this.mThreatTitle = (TextView) view.findViewById(R.id.consult_user_responses_header);
        this.responsesContainer = (LinearLayout) view.findViewById(R.id.consult_user_responses_container);
        this.root = (LinearLayout) view.findViewById(R.id.consult_user_responses_root);
    }

    public void bindPost(final ConsultPost consultPost, boolean z) {
        this.mThreatTitle.setText(consultPost.getSubject());
        this.responsesContainer.removeAllViews();
        List<ConsultFeedItem> replies = consultPost.getReplies();
        if (replies != null && replies.size() > 0) {
            for (ConsultFeedItem next : replies) {
                View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.consult_reply_item, this.responsesContainer, false);
                if (inflate != null) {
                    ConsultComment consultComment = (ConsultComment) next;
                    TextView textView = (TextView) inflate.findViewById(R.id.consult_user_responses_display_name);
                    TextView textView2 = (TextView) inflate.findViewById(R.id.consult_user_responses_date);
                    TextView textView3 = (TextView) inflate.findViewById(R.id.consult_user_responses_body);
                    TextView textView4 = (TextView) inflate.findViewById(R.id.approval_label);
                    TextView textView5 = (TextView) inflate.findViewById(R.id.consult_user_responses_profession_info);
                    ConsultProfileImageView consultProfileImageView = (ConsultProfileImageView) inflate.findViewById(R.id.consult_user_responses_avatar);
                    consultProfileImageView.setMedStudent(z);
                    if (consultComment.getPoster() != null) {
                        textView.setText(consultComment.getPoster().getDisplayName());
                        setProfessionInfoForUser(consultComment.getPoster(), textView5);
                        if (consultComment.getPoster().getInstitutionLogo() != null) {
                            String assetUrl = consultComment.getPoster().getProfileImageAsset().getAssetUrl();
                            if (assetUrl == null || assetUrl.isEmpty()) {
                                GlideApp.with(this.mContext).load(Integer.valueOf(R.drawable.anonymous_person)).placeholder((int) R.drawable.anonymous_person).into((ImageView) consultProfileImageView);
                            } else {
                                GlideApp.with(this.mContext).load(assetUrl).placeholder((int) R.drawable.anonymous_person).into((ImageView) consultProfileImageView);
                            }
                        }
                    }
                    textView2.setText(ConsultUtils.getTimeSince(consultComment.getTimestamp()));
                    textView3.setText(consultComment.getCommentBody());
                    if (consultComment.isMedscapeApprovedAnswer()) {
                        textView4.setVisibility(0);
                    } else {
                        textView4.setVisibility(8);
                    }
                    this.responsesContainer.addView(inflate);
                }
            }
        }
        this.root.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultPost consultPost = new ConsultPost();
                consultPost.setPostId(consultPost.getPostId());
                Intent intent = new Intent(ConsultResponsesViewHolder.this.mContext, ConsultPostDetailActivity.class);
                intent.putExtra(Constants.EXTRA_CONSULT_POST, consultPost);
                intent.putExtra(Constants.EXTRA_CONSULT_SCROLL_TO_DETAIL_FILTER, false);
                ConsultResponsesViewHolder.this.mContext.startActivity(intent);
            }
        });
    }

    private void setProfessionInfoForUser(ConsultUser consultUser, TextView textView) {
        if (consultUser != null && textView != null) {
            String specialty = consultUser.getSpecialty();
            if (ConsultUtils.isMedstudent(consultUser)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Medical Student");
                if (StringUtil.isNotEmpty(consultUser.getEducation())) {
                    sb.append(", ");
                    sb.append(consultUser.getEducation());
                }
                specialty = sb.toString();
            }
            textView.setText(specialty);
        }
    }
}
