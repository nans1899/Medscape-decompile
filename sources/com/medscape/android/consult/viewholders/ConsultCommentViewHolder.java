package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.BaseMovementMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AndroidPdfViewerActivity;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.consult.activity.ConsultProfileActivity;
import com.medscape.android.consult.interfaces.ICommentReplySelectedListener;
import com.medscape.android.consult.interfaces.ICommunityDataReceivedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.util.CustomTypefaceSpan;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.GlideRequest;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.view.RoundImage;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.Map;
import org.json.JSONObject;

public class ConsultCommentViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public static final String TAG = ConsultCommentViewHolder.class.getSimpleName();
    public TextView mApprovalLabel;
    public View mBodyLayout;
    public Context mContext;
    public TextView mDescriptionView;
    public View mDownVoteProgress;
    public TextView mDownVoteView;
    ForegroundColorSpan mForegroundBlueSpan;
    ForegroundColorSpan mForegroundGreySpan;
    public View mOverflowView;
    public ImageView mReplyButton;
    public View mReplyLayout;
    public View mReplyRootLayout;
    /* access modifiers changed from: private */
    public ICommentReplySelectedListener mReplySelectedListener;
    public TextView mReplyText;
    private TextView mReviewedByDisplayName;
    private View mReviewedByHeader;
    private TextView mReviewedByInstitution;
    private View mReviewedByLayout;
    private ImageView mReviewedByLogo;
    private TextView mReviewedByTitle;
    private TextView mReviewedByUserRole;
    private ImageView mReviewerLogo;
    TypefaceSpan mRobotoMediumSpan;
    private TextView mSignatureDisplayName;
    private View mSignatureHeader;
    private TextView mSignatureInstitution;
    private View mSignatureLayout;
    private ImageView mSignatureLogo;
    private TextView mSignatureUserRole;
    private TextView mSigntatureTitle;
    public View mUpVoteProgress;
    public TextView mUpVoteView;

    public ConsultCommentViewHolder(View view, Context context, ICommentReplySelectedListener iCommentReplySelectedListener) {
        super(view);
        this.mContext = context;
        this.mReplySelectedListener = iCommentReplySelectedListener;
        this.mBodyLayout = view.findViewById(R.id.body_layout);
        this.mReplyRootLayout = view.findViewById(R.id.reply_root);
        this.mApprovalLabel = (TextView) view.findViewById(R.id.approval_label);
        this.mDescriptionView = (TextView) view.findViewById(R.id.post_description);
        this.mOverflowView = view.findViewById(R.id.overflow_icon);
        this.mReplyLayout = view.findViewById(R.id.reply_layout);
        this.mReplyText = (TextView) view.findViewById(R.id.reply);
        ImageView imageView = (ImageView) view.findViewById(R.id.reply_icon);
        this.mReplyButton = imageView;
        if (imageView != null) {
            imageView.setColorFilter(Color.argb(255, 85, 85, 85));
        }
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
        this.mReviewedByLogo = (ImageView) view.findViewById(R.id.reviewed_institution_image);
        this.mUpVoteProgress = view.findViewById(R.id.progress_bar_up_vote);
        this.mDownVoteProgress = view.findViewById(R.id.progress_bar_down_vote);
        this.mUpVoteView = (TextView) view.findViewById(R.id.up_vote);
        this.mDownVoteView = (TextView) view.findViewById(R.id.down_vote);
        this.mRobotoMediumSpan = new CustomTypefaceSpan("", Typeface.createFromAsset(this.mContext.getAssets(), "font/Roboto-Medium.ttf"));
        this.mForegroundBlueSpan = new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.medscape_blue));
        this.mForegroundGreySpan = new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.material_grey));
    }

    public void bindComment(ConsultComment consultComment, ConsultComment consultComment2, int i) {
        if (consultComment == null) {
            return;
        }
        if ((!consultComment.isLowQualityPost() || consultComment.isLowQualityPostShown()) && !consultComment.isParentHidden()) {
            this.mBodyLayout.setVisibility(0);
            View view = this.mReplyRootLayout;
            if (view != null) {
                view.setVisibility(0);
            }
            updateUpVoteView(consultComment);
            updateDownVoteView(consultComment);
            addUpVoteClickListener(consultComment);
            addDownVoteClickListener(consultComment);
            addReplyClickListener(consultComment, consultComment2, i);
            if (consultComment.getCommentType() == 3030) {
                this.mReplyText.setText(String.format("%s", new Object[]{ConsultUtils.getFormattedConsultCount(consultComment.getReplyCount())}));
            } else {
                this.mReplyText.setText("");
            }
            if (StringUtil.isNotEmpty(consultComment.getCommentBody())) {
                if (consultComment.isMedscapeApprovedAnswer()) {
                    this.mApprovalLabel.setVisibility(0);
                    if (StringUtil.isNotEmpty(consultComment.getParentCommentId())) {
                        this.mReplyLayout.setVisibility(8);
                        this.mReplyText.setVisibility(8);
                    } else {
                        this.mReplyLayout.setVisibility(0);
                        this.mReplyText.setVisibility(0);
                    }
                } else {
                    this.mApprovalLabel.setVisibility(8);
                    this.mReplyLayout.setVisibility(0);
                    this.mReplyText.setVisibility(0);
                }
                this.mDescriptionView.setText(Html.fromHtml(consultComment.getCommentBody()));
                this.mDescriptionView.setMovementMethod(new BaseMovementMethod() {
                    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
                        int indexOf;
                        if (motionEvent.getAction() != 1) {
                            return super.onTouchEvent(textView, spannable, motionEvent);
                        }
                        int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
                        int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
                        int scrollX = x + textView.getScrollX();
                        int scrollY = y + textView.getScrollY();
                        Layout layout = textView.getLayout();
                        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), (float) scrollX);
                        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, URLSpan.class);
                        if (uRLSpanArr.length > 0 && uRLSpanArr[0] != null) {
                            SpannableString spannableString = (SpannableString) textView.getText();
                            String charSequence = spannableString.subSequence(spannableString.getSpanStart(uRLSpanArr[0]) + 1, spannableString.getSpanEnd(uRLSpanArr[0])).toString();
                            String url = uRLSpanArr[0].getURL();
                            if (StringUtil.isNotEmpty(url)) {
                                boolean contains = url.contains("/members/");
                                if (!ConsultUtils.isUrlInString(url) || contains) {
                                    if (contains && (indexOf = url.indexOf("/members/")) != -1) {
                                        url = url.substring(indexOf + 9);
                                    }
                                    if (StringUtil.isNotEmpty(url) && StringUtil.isNotEmpty(charSequence)) {
                                        ConsultUser consultUser = new ConsultUser();
                                        consultUser.setUserId(url);
                                        consultUser.setDisplayName(charSequence);
                                        Intent intent = new Intent(ConsultCommentViewHolder.this.mContext, ConsultProfileActivity.class);
                                        intent.putExtra(Constants.CONSULT_USER_BUNDLE_KEY, consultUser);
                                        ConsultCommentViewHolder.this.mContext.startActivity(intent);
                                    }
                                } else if (!url.endsWith(".pdf")) {
                                    WebviewUtil.Companion.launchPlainWebView(ConsultCommentViewHolder.this.mContext, url, "", "", "none", "consult", "", 0, false);
                                } else {
                                    Intent intent2 = new Intent(ConsultCommentViewHolder.this.mContext, AndroidPdfViewerActivity.class);
                                    intent2.putExtra("pdf_url", url);
                                    ConsultCommentViewHolder.this.mContext.startActivity(intent2);
                                }
                            }
                        }
                        return true;
                    }
                });
            }
            addOverflowListener(consultComment, i);
            if (shouldDisplaySignature(consultComment)) {
                displaySignature(consultComment);
            } else {
                View view2 = this.mSignatureLayout;
                if (view2 != null) {
                    view2.setVisibility(8);
                }
            }
            if (shouldDisplayReviewer(consultComment)) {
                displayReviewedBySignature(consultComment);
                return;
            }
            View view3 = this.mReviewedByLayout;
            if (view3 != null) {
                view3.setVisibility(8);
                return;
            }
            return;
        }
        this.mBodyLayout.setVisibility(8);
        View view4 = this.mReplyRootLayout;
        if (view4 != null) {
            view4.setVisibility(8);
        }
    }

    private void addOverflowListener(final ConsultComment consultComment, int i) {
        if (this.mOverflowView != null && consultComment != null) {
            if (ConsultDataManager.getInstance(this.mContext, (Activity) null).isCurrentUser(consultComment.getPoster()) || ConsultUtils.isSponsoredUser(consultComment.getPoster())) {
                this.mOverflowView.setVisibility(4);
                return;
            }
            this.mOverflowView.setVisibility(0);
            this.mOverflowView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(ConsultCommentViewHolder.this.mContext, view);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getItemId() != R.id.inappropriate) {
                                return true;
                            }
                            ConsultCommentViewHolder.this.reportAbuse(consultComment);
                            return true;
                        }
                    });
                    popupMenu.inflate(R.menu.consult_comment_overflow_menu);
                    popupMenu.show();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void reportAbuse(ConsultComment consultComment) {
        if (consultComment != null) {
            ConsultDataManager.getInstance(this.mContext, (Activity) null).reportAbuseForContentId(consultComment.getContentId(), consultComment.getContentTypeId(), new ICommunityDataReceivedListener() {
                public void onCommunityDataReceived(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        ConsultCommentViewHolder.this.showReportAbuseSuccess();
                    } else {
                        onFailedToReceiveCommunityData(new MedscapeException(ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                    }
                }

                public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                    try {
                        if (ConsultCommentViewHolder.this.mContext != null && (ConsultCommentViewHolder.this.mContext instanceof Activity)) {
                            medscapeException.showAlert((Activity) ConsultCommentViewHolder.this.mContext, ConsultCommentViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }, (String) null, (DialogInterface.OnClickListener) null);
                        }
                    } catch (Exception unused) {
                        Trace.w(ConsultCommentViewHolder.TAG, "Failed to show error liking post");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showReportAbuseSuccess() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setTitle(this.mContext.getResources().getString(R.string.consult_report_abuse_thanks));
            builder.setMessage(this.mContext.getResources().getString(R.string.consult_comment_report_abuse_success));
            builder.setPositiveButton(this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show disclaimer for consult photo editing");
        }
    }

    private void addUpVoteClickListener(final ConsultComment consultComment) {
        if (this.mUpVoteView != null && consultComment != null) {
            if (ConsultUtils.isSponsoredUser(consultComment.getPoster())) {
                this.mUpVoteView.setVisibility(4);
            } else {
                this.mUpVoteView.setVisibility(0);
            }
            this.mUpVoteView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultCommentViewHolder.this.updateVoteVisibility(true, true);
                    if (!consultComment.isUpVoted()) {
                        OmnitureManager omnitureManager = OmnitureManager.get();
                        Context context = ConsultCommentViewHolder.this.mContext;
                        omnitureManager.trackModule(context, "consult", "consult-upv-c" + consultComment.getCommentId(), "upv", (Map<String, Object>) null);
                        ConsultCommentViewHolder.this.upVoteCommentApplied(consultComment);
                        return;
                    }
                    ConsultCommentViewHolder.this.upVoteCommentRemoved(consultComment);
                }
            });
        }
    }

    private void addDownVoteClickListener(final ConsultComment consultComment) {
        if (this.mDownVoteView != null && consultComment != null) {
            if (ConsultUtils.isSponsoredUser(consultComment.getPoster())) {
                this.mDownVoteView.setVisibility(4);
            } else {
                this.mDownVoteView.setVisibility(0);
            }
            this.mDownVoteView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultCommentViewHolder.this.updateVoteVisibility(false, true);
                    if (!consultComment.isDownVoted()) {
                        OmnitureManager omnitureManager = OmnitureManager.get();
                        Context context = ConsultCommentViewHolder.this.mContext;
                        omnitureManager.trackModule(context, "consult", "consult-dnv-c" + consultComment.getCommentId(), "dnv", (Map<String, Object>) null);
                        ConsultCommentViewHolder.this.downVoteCommentApplied(consultComment);
                        return;
                    }
                    ConsultCommentViewHolder.this.downVoteCommentRemoved(consultComment);
                }
            });
        }
    }

    private void addReplyClickListener(final ConsultComment consultComment, final ConsultComment consultComment2, final int i) {
        if (consultComment != null) {
            this.mReplyLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultCommentViewHolder.this.mReplySelectedListener.onCommentReplySelected(consultComment, consultComment2, i);
                }
            });
            this.mReplyText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultCommentViewHolder.this.mReplySelectedListener.onCommentReplySelected(consultComment, consultComment2, i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateUpVoteView(ConsultComment consultComment) {
        if (this.mUpVoteView != null && consultComment != null) {
            String string = this.mContext.getString(R.string.up_vote);
            String voteLabelWithCount = ConsultUtils.getVoteLabelWithCount(string, consultComment.getUpVoteCount());
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(voteLabelWithCount);
            spannableStringBuilder.setSpan(consultComment.isUpVoted() ? this.mForegroundBlueSpan : this.mForegroundGreySpan, 0, string.length(), 18);
            spannableStringBuilder.setSpan(this.mRobotoMediumSpan, 0, voteLabelWithCount.length(), 18);
            this.mUpVoteView.setText(spannableStringBuilder);
        }
    }

    /* access modifiers changed from: private */
    public void updateDownVoteView(ConsultComment consultComment) {
        if (this.mDownVoteView != null && consultComment != null) {
            String string = this.mContext.getString(R.string.down_vote);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            spannableStringBuilder.setSpan(consultComment.isDownVoted() ? this.mForegroundBlueSpan : this.mForegroundGreySpan, 0, string.length(), 17);
            spannableStringBuilder.setSpan(this.mRobotoMediumSpan, 0, string.length(), 18);
            this.mDownVoteView.setText(spannableStringBuilder);
        }
    }

    /* access modifiers changed from: private */
    public void updateVoteVisibility(boolean z, boolean z2) {
        View view = z ? this.mUpVoteProgress : this.mDownVoteProgress;
        TextView textView = z ? this.mUpVoteView : this.mDownVoteView;
        int i = 0;
        int i2 = z2 ? 0 : 8;
        if (z2) {
            i = 4;
        }
        view.setVisibility(i2);
        textView.setVisibility(i);
        textView.setEnabled(!z2);
    }

    /* access modifiers changed from: private */
    public void upVoteCommentApplied(final ConsultComment consultComment) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).upVoteContent(consultComment.getContentId(), consultComment.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                ConsultCommentViewHolder.this.updateVoteVisibility(true, false);
                if (jSONObject != null) {
                    consultComment.setIsUpVoted(true);
                    ConsultComment consultComment = consultComment;
                    consultComment.setUpVoteCount(consultComment.getUpVoteCount() + 1);
                    ConsultCommentViewHolder.this.updateUpVoteView(consultComment);
                    if (consultComment.isDownVoted()) {
                        consultComment.setIsDownVoted(false);
                        ConsultComment consultComment2 = consultComment;
                        consultComment2.setDownVoteCount(consultComment2.getDownVoteCount() - 1);
                        ConsultCommentViewHolder.this.updateDownVoteView(consultComment);
                        return;
                    }
                    return;
                }
                onFailedToReceiveCommunityData(new MedscapeException(ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                ConsultCommentViewHolder.this.updateVoteVisibility(true, false);
                try {
                    if (ConsultCommentViewHolder.this.mContext != null && (ConsultCommentViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert((Activity) ConsultCommentViewHolder.this.mContext, ConsultCommentViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultCommentViewHolder.TAG, "Failed to show error liking comment");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void downVoteCommentApplied(final ConsultComment consultComment) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).downVoteContent(consultComment.getContentId(), consultComment.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                ConsultCommentViewHolder.this.updateVoteVisibility(false, false);
                if (jSONObject != null) {
                    consultComment.setIsDownVoted(true);
                    ConsultComment consultComment = consultComment;
                    consultComment.setDownVoteCount(consultComment.getDownVoteCount() + 1);
                    ConsultCommentViewHolder.this.updateDownVoteView(consultComment);
                    if (consultComment.isUpVoted()) {
                        consultComment.setIsUpVoted(false);
                        ConsultComment consultComment2 = consultComment;
                        consultComment2.setUpVoteCount(consultComment2.getUpVoteCount() - 1);
                        ConsultCommentViewHolder.this.updateUpVoteView(consultComment);
                        return;
                    }
                    return;
                }
                onFailedToReceiveCommunityData(new MedscapeException(ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                ConsultCommentViewHolder.this.updateVoteVisibility(false, false);
                try {
                    if (ConsultCommentViewHolder.this.mContext != null && (ConsultCommentViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert((Activity) ConsultCommentViewHolder.this.mContext, ConsultCommentViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultCommentViewHolder.TAG, "Failed to show error downVoting comment");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void upVoteCommentRemoved(final ConsultComment consultComment) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).deleteUpVoteContent(consultComment.getContentId(), consultComment.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                ConsultCommentViewHolder.this.updateVoteVisibility(true, false);
                if (jSONObject == null || !jSONObject.optBoolean("data")) {
                    onFailedToReceiveCommunityData(new MedscapeException(ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                    return;
                }
                consultComment.setIsUpVoted(false);
                ConsultComment consultComment = consultComment;
                consultComment.setUpVoteCount(consultComment.getUpVoteCount() - 1);
                ConsultCommentViewHolder.this.updateUpVoteView(consultComment);
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                ConsultCommentViewHolder.this.updateVoteVisibility(true, false);
                try {
                    if (ConsultCommentViewHolder.this.mContext != null && (ConsultCommentViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert((Activity) ConsultCommentViewHolder.this.mContext, ConsultCommentViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultCommentViewHolder.TAG, "Failed to show error liking comment");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void downVoteCommentRemoved(final ConsultComment consultComment) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).deleteDownVoteContent(consultComment.getContentId(), consultComment.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                ConsultCommentViewHolder.this.updateVoteVisibility(false, false);
                if (jSONObject == null || !jSONObject.optBoolean("data")) {
                    onFailedToReceiveCommunityData(new MedscapeException(ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultCommentViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                    return;
                }
                consultComment.setIsDownVoted(false);
                ConsultComment consultComment = consultComment;
                consultComment.setDownVoteCount(consultComment.getDownVoteCount() - 1);
                ConsultCommentViewHolder.this.updateDownVoteView(consultComment);
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                ConsultCommentViewHolder.this.updateVoteVisibility(false, false);
                try {
                    if (ConsultCommentViewHolder.this.mContext != null && (ConsultCommentViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert((Activity) ConsultCommentViewHolder.this.mContext, ConsultCommentViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultCommentViewHolder.TAG, "Failed to show error liking comment");
                }
            }
        });
    }

    private void displaySignature(ConsultComment consultComment) {
        this.mSignatureLayout.setVisibility(0);
        ConsultUser poster = consultComment.getPoster();
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
                    loadEmbeddedImage(this.mSignatureLogo, poster.getInstitutionLogo().getAssetUrl(), false);
                }
            }
        }
        addClickListenerForPoster(poster, this.mSignatureHeader);
    }

    private void loadEmbeddedImage(final ImageView imageView, String str, final boolean z) {
        if (StringUtil.isNotEmpty(str)) {
            String str2 = TAG;
            Trace.i(str2, "Start to get embedded logo image: " + str);
            Trace.i(TAG, "Attempting to get embedded logo image");
            imageView.setImageDrawable((Drawable) null);
            imageView.setVisibility(0);
            Glide.with(this.mContext).asBitmap().load(str).into(new CustomViewTarget<ImageView, Bitmap>(imageView) {
                public void onLoadFailed(Drawable drawable) {
                }

                /* access modifiers changed from: protected */
                public void onResourceCleared(Drawable drawable) {
                }

                public void onResourceReady(final Bitmap bitmap, Transition<? super Bitmap> transition) {
                    imageView.post(new Runnable() {
                        public void run() {
                            LinearLayout.LayoutParams layoutParams;
                            if (bitmap.getWidth() < ((int) Util.dpToPixel(ConsultCommentViewHolder.this.mContext, 110))) {
                                layoutParams = new LinearLayout.LayoutParams((int) Util.dpToPixel(ConsultCommentViewHolder.this.mContext, bitmap.getWidth()), -2);
                            } else {
                                layoutParams = new LinearLayout.LayoutParams((int) Util.dpToPixel(ConsultCommentViewHolder.this.mContext, 110), -2);
                            }
                            imageView.setLayoutParams(layoutParams);
                            imageView.setAdjustViewBounds(true);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            if (!z) {
                                imageView.setImageBitmap(bitmap);
                            } else {
                                imageView.setImageDrawable(new RoundImage(bitmap, -1, -1));
                            }
                        }
                    });
                }
            });
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
        Trace.i(TAG, "Attempting to get institutional logo image");
        imageView.setImageDrawable((Drawable) null);
        imageView.setVisibility(0);
        GlideRequest load = GlideApp.with((View) imageView).load(str);
        if (z) {
            load.circleCrop();
        }
        load.into(imageView);
        String str2 = TAG;
        Trace.i(str2, "Loading institution image " + str);
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

    private boolean shouldDisplaySignature(ConsultComment consultComment) {
        ConsultUser poster;
        if (!(consultComment == null || (poster = consultComment.getPoster()) == null)) {
            String userType = poster.getUserType();
            return StringUtil.isNotEmpty(userType) && userType.equalsIgnoreCase("Partner");
        }
    }

    private void displayReviewedBySignature(ConsultComment consultComment) {
        this.mReviewedByLayout.setVisibility(0);
        ConsultUser reviewer = consultComment.getReviewer();
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
                loadImage(this.mReviewedByLogo, reviewer.getInstitutionLogo().getAssetUrl(), false);
            }
            addClickListenerForPoster(reviewer, this.mReviewerLogo);
            addClickListenerForPoster(reviewer, this.mReviewedByHeader);
        }
    }

    private boolean shouldDisplayReviewer(ConsultComment consultComment) {
        return (consultComment == null || consultComment.getReviewer() == null) ? false : true;
    }

    public class InternalURLSpan extends URLSpan {
        private String mInternalUrl;

        public InternalURLSpan(String str) {
            super(str);
            this.mInternalUrl = str;
        }

        public void onClick(View view) {
            String str = this.mInternalUrl;
            if (str != null) {
                try {
                    Uri parse = Uri.parse(str);
                    if (parse != null && parse.getHost() != null && parse.getScheme() != null) {
                        ConsultCommentViewHolder.this.mContext.startActivity(new Intent("android.intent.action.VIEW", parse));
                    }
                } catch (ActivityNotFoundException unused) {
                    Trace.w(ConsultCommentViewHolder.TAG, "No activity found for intent");
                }
            }
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
                    Intent intent = new Intent(ConsultCommentViewHolder.this.mContext, ConsultProfileActivity.class);
                    intent.putExtra(Constants.CONSULT_USER_BUNDLE_KEY, consultUser);
                    ConsultCommentViewHolder.this.mContext.startActivity(intent);
                }
            });
        }
    }
}
