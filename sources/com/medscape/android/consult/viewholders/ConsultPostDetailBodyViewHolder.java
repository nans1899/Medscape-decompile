package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.activity.ConsultProfileActivity;
import com.medscape.android.consult.interfaces.IImageSelectedListener;
import com.medscape.android.consult.interfaces.IViewUpdateListener;
import com.medscape.android.consult.models.ConsultAsset;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.contentviewer.ImageViewerActivity;
import com.medscape.android.contentviewer.model.Slide;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.ArrayList;

public class ConsultPostDetailBodyViewHolder extends ConsultPostBodyViewHolder {
    private static final String TAG = ConsultPostDetailBodyViewHolder.class.getSimpleName();
    private TextView mReviewedByDisplayName;
    private View mReviewedByHeader;
    private TextView mReviewedByInstitution;
    private ImageView mReviewedByInstitutionLogo;
    private View mReviewedByLayout;
    private TextView mReviewedByTitle;
    private TextView mReviewedByUserRole;
    private ImageView mReviewerLogo;
    private TextView mSignatureDisplayName;
    private View mSignatureHeader;
    private TextView mSignatureInstitution;
    private View mSignatureLayout;
    private ImageView mSignatureLogo;
    private TextView mSignatureUserRole;
    private TextView mSigntatureTitle;

    public ConsultPostDetailBodyViewHolder(View view, Activity activity) {
        super(view);
        initializeTopLayout(view, activity);
        this.mSignatureLayout = view.findViewById(R.id.signature_layout);
        this.mSignatureHeader = view.findViewById(R.id.signature_clickableHeader);
        this.mSignatureDisplayName = (TextView) view.findViewById(R.id.signature_displayname);
        this.mSignatureUserRole = (TextView) view.findViewById(R.id.signature_userrole);
        this.mSigntatureTitle = (TextView) view.findViewById(R.id.signature_title);
        this.mSignatureInstitution = (TextView) view.findViewById(R.id.signature_institution);
        this.mSignatureLogo = (ImageView) view.findViewById(R.id.institution_image);
        this.mReviewedByLayout = view.findViewById(R.id.reviewed_by_layout);
        this.mReviewedByHeader = view.findViewById(R.id.reviewed_by_header);
        this.mReviewerLogo = (ImageView) view.findViewById(R.id.reviewer_image);
        this.mReviewedByDisplayName = (TextView) view.findViewById(R.id.reviewed_signature_displayname);
        this.mReviewedByUserRole = (TextView) view.findViewById(R.id.reviewed_signature_userrole);
        this.mReviewedByTitle = (TextView) view.findViewById(R.id.reviewed_signature_title);
        this.mReviewedByInstitution = (TextView) view.findViewById(R.id.reviewed_signature_institution);
        this.mReviewedByInstitutionLogo = (ImageView) view.findViewById(R.id.reviewed_institution_image);
    }

    public void bindPost(final ConsultPost consultPost, final RecyclerView.Adapter adapter) {
        super.bindTopLayout(consultPost, new IImageSelectedListener() {
            public void onImageSelected(int i) {
                ConsultPostDetailBodyViewHolder.this.showMediaGallery(consultPost, i);
            }
        }, new IViewUpdateListener() {
            public void onViewUpdated() {
                RecyclerView.Adapter adapter = adapter;
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
        consultPost.setmIsImageRefreshNotRequired(false);
        if (shouldDisplaySignature(consultPost)) {
            displaySignature(consultPost);
        } else {
            this.mSignatureLayout.setVisibility(8);
        }
        if (shouldDisplayReviewer(consultPost)) {
            displayReviewedBySignature(consultPost);
        } else {
            this.mReviewedByLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void showMediaGallery(ConsultPost consultPost, int i) {
        if (consultPost != null && consultPost.getConsultAssets() != null) {
            ArrayList arrayList = new ArrayList();
            for (ConsultAsset next : consultPost.getConsultAssets()) {
                if (StringUtil.isNotEmpty(next.getAssetUrl())) {
                    Slide slide = new Slide();
                    slide.graphicUrl = next.getAssetUrl();
                    arrayList.add(slide);
                }
            }
            if (!arrayList.isEmpty()) {
                Intent intent = new Intent(this.mContext, ImageViewerActivity.class);
                intent.putParcelableArrayListExtra("bundle_key_image_slides", arrayList);
                intent.putExtra("bundle_key_image_slide_position", i);
                this.mContext.startActivity(intent);
            }
        }
    }

    private void displaySignature(ConsultPost consultPost) {
        this.mSignatureLayout.setVisibility(0);
        ConsultUser poster = consultPost.getPoster();
        if (poster != null) {
            setTextForLabel(this.mSignatureDisplayName, poster.getDisplayName());
            setTextForLabel(this.mSignatureUserRole, poster.getUserRole());
            if (StringUtil.isNotEmpty(poster.getProfessionalTitle())) {
                TextView textView = this.mSigntatureTitle;
                if (textView != null) {
                    textView.setVisibility(0);
                    SpannableString spannableString = new SpannableString(poster.getProfessionalTitle());
                    String professionalURL = poster.getProfessionalURL();
                    if (StringUtil.isNotEmpty(professionalURL)) {
                        spannableString.setSpan(new InternalURLSpan(professionalURL), 0, spannableString.length(), 17);
                        this.mSigntatureTitle.setText(spannableString);
                        addLinkMovementMethod(this.mSigntatureTitle);
                    } else {
                        this.mSigntatureTitle.setText(spannableString);
                    }
                }
            } else {
                TextView textView2 = this.mSigntatureTitle;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                }
            }
            setTextForLabel(this.mSignatureInstitution, poster.getInstitution());
            if (poster.getInstitutionLogo() != null) {
                if (poster.getInstitutionLogo().getAssetUrl() == null || poster.getInstitutionLogo().getAssetUrl().equals("")) {
                    this.mSignatureLogo.setVisibility(8);
                } else {
                    this.mSignatureLogo.setVisibility(0);
                }
                loadEmbeddedImage(this.mSignatureLogo, poster.getInstitutionLogo().getAssetUrl(), false);
            }
            addClickListenerForPoster(poster, this.mSignatureHeader);
        }
    }

    private void loadEmbeddedImage(ImageView imageView, String str, boolean z) {
        if (StringUtil.isNotEmpty(str)) {
            imageView.setVisibility(0);
            String str2 = TAG;
            Trace.i(str2, "Start to get embedded logo image: " + str);
            Trace.i(TAG, "Attempting to get embedded logo image");
            Glide.with(this.mContext).load(str).into(imageView);
        }
    }

    private void loadImage(ImageView imageView, String str, boolean z) {
        if (StringUtil.isNotEmpty(str)) {
            String str2 = TAG;
            Trace.i(str2, "Start to get institutional logo image: " + str);
            setImageFromAssetUrl(str, imageView, z);
        }
    }

    private void setImageFromAssetUrl(String str, ImageView imageView, boolean z) {
        imageView.setImageDrawable((Drawable) null);
        imageView.setVisibility(0);
        String str2 = TAG;
        Trace.i(str2, "Loading institution image " + str);
        if (z) {
            Glide.with(this.mContext).load(str).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(imageView);
        } else {
            Glide.with(this.mContext).load(str).into(imageView);
        }
    }

    private void setTextForLabel(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if (StringUtil.isNotEmpty(str)) {
            textView.setVisibility(0);
            textView.setText(str);
            return;
        }
        textView.setVisibility(8);
    }

    private boolean shouldDisplaySignature(ConsultPost consultPost) {
        ConsultUser poster;
        if (!(consultPost == null || (poster = consultPost.getPoster()) == null)) {
            String userType = poster.getUserType();
            return StringUtil.isNotEmpty(userType) && userType.equalsIgnoreCase("Partner");
        }
    }

    private void displayReviewedBySignature(ConsultPost consultPost) {
        this.mReviewedByLayout.setVisibility(0);
        ConsultUser reviewer = consultPost.getReviewer();
        if (reviewer != null) {
            if (reviewer.getProfileImageAsset() != null) {
                loadImage(this.mReviewerLogo, reviewer.getProfileImageAsset().getAssetUrl(), true);
            }
            setTextForLabel(this.mReviewedByDisplayName, reviewer.getDisplayName());
            setTextForLabel(this.mReviewedByUserRole, reviewer.getUserRole());
            if (StringUtil.isNotEmpty(reviewer.getProfessionalTitle())) {
                TextView textView = this.mReviewedByTitle;
                if (textView != null) {
                    textView.setVisibility(0);
                    SpannableString spannableString = new SpannableString(reviewer.getProfessionalTitle());
                    String professionalURL = reviewer.getProfessionalURL();
                    if (StringUtil.isNotEmpty(professionalURL)) {
                        spannableString.setSpan(new InternalURLSpan(professionalURL), 0, spannableString.length(), 17);
                        this.mReviewedByTitle.setText(spannableString);
                        addLinkMovementMethod(this.mReviewedByTitle);
                    } else {
                        this.mReviewedByTitle.setText(spannableString);
                    }
                }
            } else {
                TextView textView2 = this.mReviewedByTitle;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                }
            }
            setTextForLabel(this.mReviewedByInstitution, reviewer.getInstitution());
            if (reviewer.getInstitutionLogo() != null) {
                loadEmbeddedImage(this.mReviewedByInstitutionLogo, reviewer.getInstitutionLogo().getAssetUrl(), false);
            }
            addClickListenerForPoster(reviewer, this.mReviewerLogo);
            addClickListenerForPoster(reviewer, this.mReviewedByHeader);
        }
    }

    private boolean shouldDisplayReviewer(ConsultPost consultPost) {
        return (consultPost == null || consultPost.getReviewer() == null) ? false : true;
    }

    public class InternalURLSpan extends URLSpan {
        private String mInternalUrl;

        public InternalURLSpan(String str) {
            super(str);
            this.mInternalUrl = str;
        }

        public void onClick(View view) {
            ConsultPostDetailBodyViewHolder.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.mInternalUrl)));
        }
    }

    private static void addLinkMovementMethod(TextView textView) {
        MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void addClickListenerForPoster(final ConsultUser consultUser, View view) {
        if (view != null && this.mContext != null) {
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ConsultPostDetailBodyViewHolder.this.mContext, ConsultProfileActivity.class);
                    intent.putExtra(Constants.CONSULT_USER_BUNDLE_KEY, consultUser);
                    ConsultPostDetailBodyViewHolder.this.mContext.startActivity(intent);
                }
            });
        }
    }
}
