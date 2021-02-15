package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.activity.ConsultPostDetailActivity;
import com.medscape.android.consult.interfaces.IImageSelectedListener;
import com.medscape.android.consult.interfaces.IViewUpdateListener;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.util.StringUtil;
import java.util.Map;

public class ConsultSponsoredPostViewHolder extends ConsultPostBodyViewHolder implements View.OnClickListener {
    private int feedType;
    public ConsultPost mPost;
    private View sponsoredPost;
    private ImageView sponsoredPostLogo;

    public ConsultSponsoredPostViewHolder(View view, Activity activity, int i) {
        super(view);
        initializeTopLayout(view, activity);
        this.sponsoredPostLogo = (ImageView) view.findViewById(R.id.consult_sponsored_logo);
        this.sponsoredPost = view.findViewById(R.id.sponsored_post);
        this.feedType = i;
    }

    public void bindPost(ConsultPost consultPost, final RecyclerView.Adapter adapter) {
        this.mPost = consultPost;
        super.bindTopLayout(consultPost, new IImageSelectedListener() {
            public void onImageSelected(int i) {
                ConsultSponsoredPostViewHolder.this.loadConsultPostDetails();
            }
        }, new IViewUpdateListener() {
            public void onViewUpdated() {
                RecyclerView.Adapter adapter = adapter;
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
        View view = this.sponsoredPost;
        if (view != null) {
            view.setBackgroundColor(this.mContext.getResources().getColor(R.color.consult_sponsored_post));
            this.sponsoredPost.setOnClickListener(this);
        }
        String sponserdPostInsitutionImageUrl = getSponserdPostInsitutionImageUrl(consultPost);
        if (this.sponsoredPostLogo != null) {
            if (StringUtil.isNotEmpty(sponserdPostInsitutionImageUrl)) {
                this.sponsoredPostLogo.setVisibility(0);
                Glide.with(this.mContext).load(sponserdPostInsitutionImageUrl).into(this.sponsoredPostLogo);
            } else {
                this.sponsoredPostLogo.setVisibility(8);
            }
        }
        if (this.mBodyLayout != null) {
            this.mBodyLayout.setOnClickListener(this);
        }
        if (this.mTitleView != null) {
            this.mTitleView.setOnClickListener(this);
        }
        if (this.mDescriptionView != null) {
            this.mDescriptionView.setOnClickListener(this);
            this.mDescriptionView.setText(this.mContext.getString(R.string.read_more));
            this.mDescriptionView.setTextSize(16.0f);
            this.mDescriptionView.setTextColor(this.mContext.getResources().getColor(R.color.title_color));
        }
    }

    public void loadConsultPostDetails() {
        if (this.mContext != null && this.mPost != null) {
            sendOmnitureCallForSponsoredPost();
            Intent intent = new Intent(this.mContext, ConsultPostDetailActivity.class);
            intent.putExtra(Constants.EXTRA_CONSULT_POST, this.mPost);
            intent.putExtra(Constants.EXTRA_CONSULT_SCROLL_TO_DETAIL_FILTER, false);
            this.mContext.startActivity(intent);
        }
    }

    private void sendOmnitureCallForSponsoredPost() {
        ConsultPost consultPost = this.mPost;
        if (consultPost != null && ConsultUtils.isshowSponsoredLabel(consultPost.getPoster())) {
            if (this.mPost.isFromAD()) {
                OmnitureManager.get().markModule("consult-spsr", "ad", (Map<String, Object>) null);
                return;
            }
            int i = this.feedType;
            if (i == 3006 || i == 3001) {
                OmnitureManager.get().markModule("consult-spsr", "pst", (Map<String, Object>) null);
            }
        }
    }

    public String getSponserdPostInsitutionImageUrl(ConsultPost consultPost) {
        if (consultPost == null || consultPost.getPoster() == null || consultPost.getPoster().getInstitutionLogo() == null) {
            return null;
        }
        return consultPost.getPoster().getInstitutionLogo().getAssetUrl();
    }

    public void onClick(View view) {
        loadConsultPostDetails();
    }
}
