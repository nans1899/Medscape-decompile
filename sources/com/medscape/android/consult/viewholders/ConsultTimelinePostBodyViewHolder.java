package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.activity.ConsultPostDetailActivity;
import com.medscape.android.consult.interfaces.IImageSelectedListener;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.IViewUpdateListener;
import com.medscape.android.consult.models.ConsultPost;
import java.util.Map;

public class ConsultTimelinePostBodyViewHolder extends ConsultPostBodyViewHolder {
    private View mBottomLayout;
    private TextView mPostDescription;
    private View mPostTitle;

    public ConsultTimelinePostBodyViewHolder(View view, Activity activity, IPostDeletedListener iPostDeletedListener) {
        super(view, activity, iPostDeletedListener);
        this.mBottomLayout = view.findViewById(R.id.bottom_layout);
        this.mPostTitle = view.findViewById(R.id.post_title);
        this.mPostDescription = (TextView) view.findViewById(R.id.post_description);
    }

    public void bindPost(final ConsultPost consultPost, int i, final RecyclerView.Adapter adapter) {
        super.bindPost(consultPost, i, new IImageSelectedListener() {
            public void onImageSelected(int i) {
                ConsultTimelinePostBodyViewHolder.this.loadConsultPostDetails(consultPost, false);
            }
        }, new IViewUpdateListener() {
            public void onViewUpdated() {
                RecyclerView.Adapter adapter = adapter;
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
        this.mBodyLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultTimelinePostBodyViewHolder.this.loadConsultPostDetails(consultPost, false);
            }
        });
        this.mPostTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultTimelinePostBodyViewHolder.this.loadConsultPostDetails(consultPost, false);
            }
        });
        this.mPostDescription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultTimelinePostBodyViewHolder.this.loadConsultPostDetails(consultPost, false);
            }
        });
        this.mBottomLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OmnitureManager omnitureManager = OmnitureManager.get();
                Activity activity = ConsultTimelinePostBodyViewHolder.this.mContext;
                omnitureManager.trackModule(activity, "consult", "consult-reply-" + consultPost.getPostId(), "reply", (Map<String, Object>) null);
                ConsultTimelinePostBodyViewHolder.this.loadConsultPostDetails(consultPost, true);
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadConsultPostDetails(ConsultPost consultPost, boolean z) {
        Intent intent = new Intent(this.mContext, ConsultPostDetailActivity.class);
        intent.putExtra(Constants.EXTRA_CONSULT_POST, consultPost);
        intent.putExtra(Constants.EXTRA_CONSULT_SCROLL_TO_DETAIL_FILTER, z);
        this.mContext.startActivity(intent);
    }
}
