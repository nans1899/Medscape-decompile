package com.medscape.android.consult.viewholders;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.activity.ConsultPostDetailActivity;
import com.medscape.android.consult.activity.ConsultProfileActivity;
import com.medscape.android.consult.activity.ConsultSearchActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.consult.interfaces.ISetVisibilityOfLowQualityPosts;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultProfileImageView;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.StringUtil;

public class ConsultHeaderViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public ConsultPost consultPost;
    private RelativeLayout mConsultQualityLayout;
    private TextView mContactField;
    private ConsultProfileImageView mContactImage;
    private TextView mContactName;
    /* access modifiers changed from: private */
    public Context mContext;
    private View mHeader;
    private View mHeaderBody;
    /* access modifiers changed from: private */
    public int mPosition;
    /* access modifiers changed from: private */
    public ISetVisibilityOfLowQualityPosts mPostVisibilityListener;
    private TextView mQualitySubtitleText;
    private TextView mQualityTitleText;
    private TextView mQualityVisibilityAction;
    private double mScreenDensity = 1.0d;
    private TextView mTimeSince;
    private ImageView partnerLogo;
    private View sponsoredPost;
    private ImageView sponsoredPostLogo;
    private View timeAndPartnerLayout;

    public ConsultHeaderViewHolder(View view, Context context) {
        super(view);
        this.mContext = context;
        this.mHeader = view.findViewById(R.id.consult_header);
        this.mContactImage = (ConsultProfileImageView) view.findViewById(R.id.contact_image);
        this.mContactName = (TextView) view.findViewById(R.id.contact_name);
        this.mContactField = (TextView) view.findViewById(R.id.contact_field);
        this.mTimeSince = (TextView) view.findViewById(R.id.time_since);
        this.partnerLogo = (ImageView) view.findViewById(R.id.consult_partner_logo);
        this.timeAndPartnerLayout = view.findViewById(R.id.time_and_partner_logo);
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        this.mScreenDensity = (double) displayMetrics.density;
        this.mConsultQualityLayout = (RelativeLayout) view.findViewById(R.id.consult_quality_root);
        this.mQualityVisibilityAction = (TextView) view.findViewById(R.id.quality_action);
        this.mQualitySubtitleText = (TextView) view.findViewById(R.id.quality_sub_title);
        this.mQualityTitleText = (TextView) view.findViewById(R.id.quality_title);
        this.sponsoredPost = view.findViewById(R.id.sponsored_post);
        this.sponsoredPostLogo = (ImageView) view.findViewById(R.id.consult_sponsored_logo);
        this.mHeaderBody = view.findViewById(R.id.consult_header_body);
        this.mContactField.setMaxLines(1);
        this.mContactField.setEllipsize(TextUtils.TruncateAt.END);
        setProfileImageDimens();
    }

    public void bindPost(ConsultPost consultPost2, int i, ISetVisibilityOfLowQualityPosts iSetVisibilityOfLowQualityPosts) {
        this.mPosition = i;
        this.mPostVisibilityListener = iSetVisibilityOfLowQualityPosts;
        this.consultPost = consultPost2;
        bindPost(consultPost2);
    }

    public void bindPost(ConsultPost consultPost2) {
        if (consultPost2 != null && consultPost2.isHeader()) {
            addClickListenerForHeader(consultPost2.getPoster());
            setViewForUserFromPost(consultPost2);
            if (this.mPosition > -1 && this.mPostVisibilityListener != null) {
                updateQualitySection(consultPost2);
            }
        }
    }

    public void bindComment(ConsultComment consultComment, int i, ISetVisibilityOfLowQualityPosts iSetVisibilityOfLowQualityPosts) {
        if (consultComment.isParentHidden()) {
            this.mHeader.setVisibility(8);
            return;
        }
        this.mHeader.setVisibility(0);
        this.mPosition = i;
        this.mPostVisibilityListener = iSetVisibilityOfLowQualityPosts;
        bindComment(consultComment);
        if (this.mPosition > -1 && this.mPostVisibilityListener != null) {
            updateQualitySection(consultComment);
        }
    }

    private void bindComment(ConsultComment consultComment) {
        if (consultComment != null && consultComment.isHeader()) {
            addClickListenerForHeader(consultComment.getPoster());
            setViewForUserFromComment(consultComment);
            this.mHeader.setPadding(0, 0, 0, 0);
        }
    }

    public void bindPost(ConsultUser consultUser) {
        if (consultUser != null) {
            addClickListenerForHeader(consultUser);
            setViewForUser(consultUser);
            this.mHeader.setPadding(0, 0, 0, 0);
        }
    }

    private void setViewForUserFromPost(ConsultPost consultPost2) {
        ConsultUser poster = consultPost2.getPoster();
        if (poster != null) {
            setViewForUser(poster);
            this.mTimeSince.setVisibility(0);
            this.mTimeSince.setText(ConsultUtils.getTimeSince(consultPost2.getTimestamp()));
        }
    }

    private void setViewForUserFromComment(ConsultComment consultComment) {
        ConsultUser poster = consultComment.getPoster();
        if (poster != null) {
            setViewForUser(poster);
            if (!ConsultUtils.isshowSponsoredLabel(consultComment.getPoster())) {
                this.mTimeSince.setVisibility(0);
                this.mTimeSince.setText(ConsultUtils.getTimeSince(consultComment.getTimestamp()));
            }
        }
    }

    private void setViewForUser(ConsultUser consultUser) {
        if (consultUser != null) {
            this.mContactImage.setMedStudent(ConsultUtils.isMedstudent(consultUser));
            this.mConsultQualityLayout.setVisibility(8);
            this.mHeaderBody.setVisibility(0);
            this.mTimeSince.setVisibility(8);
            this.mContactName.setText(consultUser.getDisplayName());
            String assetUrl = consultUser.getInstitutionLogo().getAssetUrl();
            if (this.partnerLogo != null) {
                if (assetUrl == null || assetUrl.isEmpty()) {
                    this.partnerLogo.setVisibility(8);
                } else {
                    this.partnerLogo.setVisibility(0);
                    Glide.with(this.mContext).load(consultUser.getInstitutionLogo().getAssetUrl()).into(this.partnerLogo);
                }
            }
            if (ConsultUtils.isshowSponsoredLabel(consultUser)) {
                this.timeAndPartnerLayout.setVisibility(8);
                this.sponsoredPost.setVisibility(0);
                this.sponsoredPost.setBackgroundColor(this.mContext.getResources().getColor(R.color.consult_sponsored_post));
                if (consultUser.getInstitutionLogo() != null) {
                    String assetUrl2 = consultUser.getInstitutionLogo().getAssetUrl();
                    if (!StringUtil.isNullOrEmpty(assetUrl2)) {
                        this.sponsoredPostLogo.setVisibility(0);
                        Glide.with(this.mContext).load(assetUrl2).into(this.sponsoredPostLogo);
                    } else {
                        this.sponsoredPostLogo.setVisibility(8);
                    }
                }
                Context context = this.mContext;
                if (context == null || !(context instanceof ConsultPostDetailActivity)) {
                    this.mHeaderBody.setVisibility(8);
                } else {
                    this.mHeader.setBackgroundColor(context.getResources().getColor(R.color.consult_sponsored_post));
                    this.mHeaderBody.setBackgroundColor(this.mContext.getResources().getColor(R.color.consult_sponsored_post));
                }
            } else {
                View view = this.timeAndPartnerLayout;
                if (!(view == null || this.sponsoredPost == null)) {
                    view.setVisibility(0);
                    this.sponsoredPost.setVisibility(8);
                }
                this.mHeader.setBackgroundColor(this.mContext.getResources().getColor(R.color.white));
                this.mHeaderBody.setBackgroundColor(this.mContext.getResources().getColor(R.color.white));
            }
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
            this.mContactField.setText(specialty);
            if (consultUser.getProfileImageAsset() != null && this.mContactImage != null) {
                String assetUrl3 = consultUser.getProfileImageAsset().getAssetUrl();
                if (StringUtil.isNotEmpty(assetUrl3)) {
                    GlideApp.with(this.mContext).load(assetUrl3).placeholder((int) R.drawable.anonymous_person).into((ImageView) this.mContactImage);
                }
            }
        }
    }

    private void addClickListenerForHeader(final ConsultUser consultUser) {
        if (this.mContext != null) {
            View view = this.mHeaderBody;
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!ConsultUtils.isSponsoredUser(consultUser)) {
                            Intent intent = new Intent(ConsultHeaderViewHolder.this.mContext, ConsultProfileActivity.class);
                            intent.putExtra(Constants.CONSULT_USER_BUNDLE_KEY, consultUser);
                            ConsultHeaderViewHolder.this.mContext.startActivity(intent);
                        }
                    }
                });
            }
            if (this.sponsoredPost != null) {
                Context context = this.mContext;
                if ((context instanceof ConsultTimelineActivity) || (context instanceof ConsultSearchActivity)) {
                    this.sponsoredPost.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            ConsultHeaderViewHolder consultHeaderViewHolder = ConsultHeaderViewHolder.this;
                            consultHeaderViewHolder.loadConsultPostDetails(consultHeaderViewHolder.consultPost);
                        }
                    });
                }
            }
        }
    }

    private void setProfileImageDimens() {
        double d = this.mScreenDensity;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (d * 40.0d), (int) (d * 40.0d));
        layoutParams.addRule(9);
        layoutParams.setMargins(5, 5, 5, 5);
        this.mContactImage.setLayoutParams(layoutParams);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateQualitySection(final java.lang.Object r7) {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x007a
            boolean r0 = r7 instanceof com.medscape.android.consult.models.ConsultPost
            r1 = 0
            java.lang.String r2 = "Hidden Post"
            if (r0 == 0) goto L_0x0019
            r0 = r7
            com.medscape.android.consult.models.ConsultPost r0 = (com.medscape.android.consult.models.ConsultPost) r0
            boolean r3 = r0.isLowQualityPost()
            boolean r0 = r0.isLowQualityPostShown()
            java.lang.String r4 = "Posts"
            r5 = r2
        L_0x0017:
            r2 = 0
            goto L_0x0045
        L_0x0019:
            boolean r0 = r7 instanceof com.medscape.android.consult.models.ConsultComment
            if (r0 == 0) goto L_0x003f
            r0 = r7
            com.medscape.android.consult.models.ConsultComment r0 = (com.medscape.android.consult.models.ConsultComment) r0
            boolean r3 = r0.isLowQualityPost()
            boolean r2 = r0.isLowQualityPostShown()
            int r0 = r0.getCommentType()
            r4 = 3031(0xbd7, float:4.247E-42)
            if (r0 != r4) goto L_0x0038
            r0 = 1
            java.lang.String r4 = "Replies"
            java.lang.String r5 = "Hidden Reply"
            r0 = r2
            r2 = 1
            goto L_0x0045
        L_0x0038:
            java.lang.String r4 = "Responses"
            java.lang.String r0 = "Hidden Response"
            r5 = r0
            r0 = r2
            goto L_0x0017
        L_0x003f:
            java.lang.String r4 = ""
            r5 = r2
            r0 = 0
            r2 = 0
            r3 = 0
        L_0x0045:
            if (r3 == 0) goto L_0x007a
            android.widget.RelativeLayout r3 = r6.mConsultQualityLayout
            r3.setVisibility(r1)
            android.widget.TextView r1 = r6.mQualityTitleText
            r1.setText(r5)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            java.lang.String r3 = " that exceed the downvote threshold are hidden."
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.widget.TextView r3 = r6.mQualitySubtitleText
            r3.setText(r1)
            if (r0 == 0) goto L_0x006d
            r6.showLowQualityPost()
            goto L_0x0070
        L_0x006d:
            r6.hideLowQualityPost(r2)
        L_0x0070:
            android.widget.RelativeLayout r0 = r6.mConsultQualityLayout
            com.medscape.android.consult.viewholders.ConsultHeaderViewHolder$3 r1 = new com.medscape.android.consult.viewholders.ConsultHeaderViewHolder$3
            r1.<init>(r7)
            r0.setOnClickListener(r1)
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.viewholders.ConsultHeaderViewHolder.updateQualitySection(java.lang.Object):void");
    }

    private void showLowQualityPost() {
        this.mHeaderBody.setVisibility(0);
        this.mQualityVisibilityAction.setText("Hide");
        this.mQualitySubtitleText.setVisibility(0);
    }

    private void hideLowQualityPost(boolean z) {
        this.mHeaderBody.setVisibility(8);
        this.mQualityVisibilityAction.setText("Show");
        this.mQualitySubtitleText.setVisibility(8);
        if (z) {
            this.mHeader.setPadding(0, 0, 0, (int) ((this.mScreenDensity * 10.0d) + 0.5d));
        }
    }

    /* access modifiers changed from: private */
    public void loadConsultPostDetails(ConsultPost consultPost2) {
        Intent intent = new Intent(this.mContext, ConsultPostDetailActivity.class);
        if (consultPost2 != null) {
            consultPost2.setIsHeader(false);
        }
        intent.putExtra(Constants.EXTRA_CONSULT_POST, consultPost2);
        this.mContext.startActivity(intent);
    }
}
