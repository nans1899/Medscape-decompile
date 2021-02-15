package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.EmojiFilter;
import com.medscape.android.consult.interfaces.IDataSetChangedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.interfaces.ICallbackEvent;
import com.medscape.android.interfaces.IHttpRequestCompleteListener;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.media.PhotoUtil;
import com.medscape.android.view.RoundImage;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.Extensions;
import java.security.MessageDigest;
import java.util.Map;
import org.apache.commons.io.IOUtils;

public class ConsultProfileViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public static final String TAG = ConsultProfileViewHolder.class.getSimpleName();
    /* access modifiers changed from: private */
    public Button mActionButton;
    private TextView mBioLabel;
    private View mCheckMark;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public ConsultUser mCurrentUser;
    /* access modifiers changed from: private */
    public IDataSetChangedListener mDataSetChangedListener;
    /* access modifiers changed from: private */
    public ProgressBar mFollowProgress;
    private int mImageSize = -1;
    private LayoutInflater mInflater;
    private TextView mInstitutionLabel;
    private ImageView mInstitutionLogo;
    /* access modifiers changed from: private */
    public boolean mIsLoadingData;
    private TextView mProfessionalTitle;
    /* access modifiers changed from: private */
    public Bitmap mProfileBitmap = null;
    private View mProfileCamera;
    private ImageView mProfileImage;
    private View mProfileImageLayout;
    private ProgressBar mProgress;
    private double mScreenDensity;
    private int mScreenWidth = -1;
    private TextView mSpecialtyLabel;
    private TextView mUserRole;

    public ConsultProfileViewHolder(View view, Context context, IDataSetChangedListener iDataSetChangedListener) {
        super(view);
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            this.mScreenWidth = defaultDisplay.getWidth();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            this.mScreenDensity = (double) displayMetrics.density;
            this.mImageSize = this.mScreenWidth / 2;
        }
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        this.mSpecialtyLabel = (TextView) view.findViewById(R.id.specialty);
        this.mBioLabel = (TextView) view.findViewById(R.id.bio);
        this.mUserRole = (TextView) view.findViewById(R.id.userRole);
        this.mProfessionalTitle = (TextView) view.findViewById(R.id.professionalTitle);
        this.mInstitutionLabel = (TextView) view.findViewById(R.id.institution);
        this.mInstitutionLogo = (ImageView) view.findViewById(R.id.institution_image);
        this.mActionButton = (Button) view.findViewById(R.id.action_button);
        this.mProfileImage = (ImageView) view.findViewById(R.id.profile_image);
        this.mProfileCamera = view.findViewById(R.id.profile_camera);
        this.mProgress = (ProgressBar) view.findViewById(R.id.loading_progress);
        this.mFollowProgress = (ProgressBar) view.findViewById(R.id.follow_progress);
        this.mProfileImageLayout = view.findViewById(R.id.image_profile_layout);
        this.mCheckMark = view.findViewById(R.id.checkmark);
        this.mDataSetChangedListener = iDataSetChangedListener;
    }

    public void bindPost(Object obj, boolean z, boolean z2, boolean z3) {
        this.mIsLoadingData = z3;
        if (obj != null && (obj instanceof ConsultUser)) {
            this.mCurrentUser = (ConsultUser) obj;
            handleUserItem(obj, z, z2);
        }
    }

    private void handleUserItem(Object obj, boolean z, boolean z2) {
        if (obj != null) {
            ConsultUser consultUser = (ConsultUser) obj;
            setCameraVisibility(consultUser);
            loadProfileImage(consultUser, z, z2);
            loadInstitutionLogo(consultUser);
            if (z) {
                updateScreenWithFullProfileInfo(consultUser);
            } else if (z2) {
                updateScreenAfterFailedToReceiveFullProfile();
            } else {
                toggleViews(true, consultUser);
            }
            handleProgressBar();
            if (ConsultUtils.isMedstudent(consultUser)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Medical Student");
                if (StringUtil.isNotEmpty(consultUser.getEducation())) {
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                    sb.append(consultUser.getEducation());
                }
                this.mSpecialtyLabel.setVisibility(0);
                this.mSpecialtyLabel.setText(sb.toString());
            } else if (StringUtil.isNotEmpty(consultUser.getSpecialty())) {
                this.mSpecialtyLabel.setText(consultUser.getSpecialty());
                this.mSpecialtyLabel.setVisibility(0);
            } else {
                this.mSpecialtyLabel.setVisibility(8);
            }
            if (!StringUtil.isNotEmpty(consultUser.getBio()) || consultUser.getBio().equalsIgnoreCase("null")) {
                this.mBioLabel.setVisibility(8);
            } else {
                this.mBioLabel.setText(consultUser.getBio());
                this.mBioLabel.setVisibility(0);
            }
            if (!StringUtil.isNotEmpty(consultUser.getUserRole()) || consultUser.getUserRole().equalsIgnoreCase("null")) {
                this.mUserRole.setVisibility(8);
            } else {
                this.mUserRole.setText(consultUser.getUserRole());
                this.mUserRole.setVisibility(0);
            }
            if (!StringUtil.isNotEmpty(consultUser.getProfessionalTitle()) || consultUser.getProfessionalTitle().equalsIgnoreCase("null")) {
                this.mProfessionalTitle.setVisibility(8);
            } else {
                SpannableString spannableString = new SpannableString(consultUser.getProfessionalTitle());
                String professionalURL = consultUser.getProfessionalURL();
                if (StringUtil.isNotEmpty(professionalURL)) {
                    spannableString.setSpan(new InternalURLSpan(professionalURL), 0, spannableString.length(), 17);
                    this.mProfessionalTitle.setText(spannableString);
                    addLinkMovementMethod(this.mProfessionalTitle);
                } else {
                    this.mProfessionalTitle.setText(spannableString);
                }
                this.mProfessionalTitle.setVisibility(0);
            }
            if (!StringUtil.isNotEmpty(consultUser.getInstitution()) || consultUser.getInstitution().equalsIgnoreCase("null")) {
                this.mInstitutionLabel.setVisibility(8);
            } else {
                this.mInstitutionLabel.setText(consultUser.getInstitution());
                this.mInstitutionLabel.setVisibility(0);
                if (Extensions.IsNullOrEmpty(consultUser.getProfessionalTitle()) && !consultUser.getProfessionalTitle().equalsIgnoreCase("null")) {
                    this.mInstitutionLabel.setPadding(0, 30, 0, 0);
                }
            }
            setActionButton(consultUser);
            addProfileLayoutImageClickHandler(consultUser);
        }
    }

    private boolean isInstitutionalLogoOnlyPartnerShipBrandingElement(ConsultUser consultUser) {
        return consultUser != null && consultUser.getInstitutionLogo() != null && StringUtil.isNotEmpty(consultUser.getInstitutionLogo().getAssetUrl()) && !consultUser.getInstitutionLogo().getAssetUrl().equalsIgnoreCase("null") && (StringUtil.isNullOrEmpty(consultUser.getInstitution()) || consultUser.getInstitution().equalsIgnoreCase("null")) && (StringUtil.isNullOrEmpty(consultUser.getProfessionalTitle()) || consultUser.getProfessionalTitle().equalsIgnoreCase("null"));
    }

    private void toggleViews(boolean z, ConsultUser consultUser) {
        if (z) {
            hideViews();
        } else {
            showViews(consultUser);
        }
    }

    private void showViews(ConsultUser consultUser) {
        Button button = this.mActionButton;
        if (button != null) {
            button.setVisibility(0);
        }
        TextView textView = this.mBioLabel;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.mSpecialtyLabel;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.mUserRole;
        if (textView3 != null) {
            textView3.setVisibility(0);
        }
        TextView textView4 = this.mProfessionalTitle;
        if (textView4 != null) {
            textView4.setVisibility(0);
        }
        TextView textView5 = this.mInstitutionLabel;
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
        setCameraVisibility(consultUser);
    }

    private void hideViews() {
        Button button = this.mActionButton;
        if (button != null) {
            button.setVisibility(4);
        }
        TextView textView = this.mBioLabel;
        if (textView != null) {
            textView.setVisibility(4);
        }
        TextView textView2 = this.mSpecialtyLabel;
        if (textView2 != null) {
            textView2.setVisibility(4);
        }
        TextView textView3 = this.mUserRole;
        if (textView3 != null) {
            textView3.setVisibility(4);
        }
        TextView textView4 = this.mProfessionalTitle;
        if (textView4 != null) {
            textView4.setVisibility(4);
        }
        TextView textView5 = this.mInstitutionLabel;
        if (textView5 != null) {
            textView5.setVisibility(4);
        }
        View view = this.mProfileCamera;
        if (view != null) {
            view.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    public void handleProgressBar() {
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            return;
        }
        if (this.mIsLoadingData) {
            progressBar.setVisibility(0);
        } else {
            progressBar.setVisibility(8);
        }
    }

    private void loadInstitutionLogo(ConsultUser consultUser) {
        if (consultUser != null && consultUser.getInstitutionLogo() != null) {
            String assetUrl = consultUser.getInstitutionLogo().getAssetUrl();
            String str = TAG;
            Trace.i(str, "institution url: " + assetUrl);
            Trace.i(TAG, "Start to get institutional logo image");
            setInstitutionLogoFromAssetUrl(assetUrl);
        }
    }

    private void setInstitutionLogoFromAssetUrl(String str) {
        Trace.i(TAG, "Attempting to get institutional logo image");
        this.mInstitutionLogo.setImageDrawable((Drawable) null);
        if (StringUtil.isNotEmpty(str)) {
            this.mInstitutionLogo.setVisibility(0);
            Glide.with(this.mContext).load(str).into(this.mInstitutionLogo);
        }
    }

    private void loadProfileImage(ConsultUser consultUser, boolean z, boolean z2) {
        ImageView imageView;
        if (!z && !z2) {
            showPlaceHolderImage();
        } else if (consultUser != null && (imageView = this.mProfileImage) != null) {
            if (this.mProfileBitmap != null) {
                if (!this.mIsLoadingData) {
                    updateProfileImage();
                }
            } else if (imageView.getTag() == null && consultUser.getProfileImageAsset() != null && StringUtil.isNotEmpty(consultUser.getProfileImageAsset().getAssetUrl())) {
                showPlaceHolderImage();
                setProfileImageFromAssetUrl(consultUser.getProfileImageAsset().getAssetUrl());
            } else if (consultUser.getProfileImageAsset() == null || !StringUtil.isNotEmpty(consultUser.getProfileImageAsset().getAssetUrl())) {
                showPlaceHolderImage();
            }
            double d = this.mScreenDensity;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (d * 30.0d), (int) (d * 30.0d));
            layoutParams.addRule(9);
            layoutParams.setMargins(0, (this.mImageSize * 3) / 4, 0, 0);
            this.mProfileCamera.setLayoutParams(layoutParams);
        }
    }

    private void showPlaceHolderImage() {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.consult_placeholder_image);
        int i = this.mImageSize;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(13);
        double d = this.mScreenDensity;
        layoutParams.setMargins(0, (int) (d * 5.0d), 0, (int) (d * 5.0d));
        this.mProfileImage.setLayoutParams(layoutParams);
        int i2 = this.mImageSize;
        this.mProfileImage.setImageDrawable(new RoundImage(decodeResource, i2, i2));
    }

    /* access modifiers changed from: private */
    public void updateProfileImage() {
        Trace.i(TAG, "Updating image on screen");
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        this.mProfileImage.setImageDrawable((Drawable) null);
        int i = this.mImageSize;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(13);
        double d = this.mScreenDensity;
        layoutParams.setMargins(0, (int) (30.0d * d), 0, (int) (d * 10.0d));
        this.mProfileImage.setLayoutParams(layoutParams);
        Bitmap bitmap = this.mProfileBitmap;
        int i2 = this.mImageSize;
        this.mProfileImage.setImageDrawable(new RoundImage(bitmap, i2, i2));
    }

    private void setProfileImageFromAssetUrl(String str) {
        this.mProfileImage.setImageDrawable((Drawable) null);
        this.mProfileImage.setVisibility(0);
        int i = this.mImageSize;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(13);
        double d = this.mScreenDensity;
        layoutParams.setMargins(0, (int) (30.0d * d), 0, (int) (d * 10.0d));
        this.mProfileImage.setLayoutParams(layoutParams);
        String str2 = TAG;
        Trace.i(str2, "Loading image " + str);
        GlideApp.with(this.mContext).load(str).transform((Transformation) new CircleTransformation()).placeholder((int) R.drawable.consult_placeholder_image).into(this.mProfileImage);
    }

    public class CircleTransformation extends BitmapTransformation {
        public void updateDiskCacheKey(MessageDigest messageDigest) {
        }

        public CircleTransformation() {
        }

        /* access modifiers changed from: protected */
        public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create((Resources) null, bitmap);
            create.setCircular(true);
            Bitmap bitmap2 = bitmapPool.get(i, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap2);
            create.setBounds(0, 0, i, i2);
            create.draw(canvas);
            return bitmap2;
        }
    }

    private void updateScreenWithFullProfileInfo(ConsultUser consultUser) {
        toggleViews(false, consultUser);
    }

    private void updateScreenAfterFailedToReceiveFullProfile() {
        Button button = this.mActionButton;
        if (button != null) {
            button.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void setActionButton(final ConsultUser consultUser) {
        if (consultUser != null) {
            if (ConsultDataManager.getInstance(this.mContext, (Activity) null).isCurrentUser(consultUser)) {
                setActionButtonForCurrentUser(consultUser);
            } else if (isFollowingConsultUser(consultUser)) {
                this.mCheckMark.setVisibility(0);
                this.mActionButton.setText(this.mContext.getString(R.string.profile_following));
                setActionButtonColor(this.mContext.getResources().getDrawable(R.drawable.blue_rectangular_ripple));
                this.mActionButton.setTextColor(this.mContext.getResources().getColor(R.color.white));
                this.mActionButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ConsultProfileViewHolder.this.unFollowUser(consultUser);
                    }
                });
            } else {
                this.mCheckMark.setVisibility(8);
                this.mActionButton.setText(this.mContext.getString(R.string.profile_follow));
                setActionButtonColor(this.mContext.getResources().getDrawable(R.drawable.white_rectangular_ripple));
                this.mActionButton.setTextColor(this.mContext.getResources().getColor(R.color.medscape_blue));
                this.mActionButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ConsultProfileViewHolder.this.followUser(consultUser);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void unFollowUser(final ConsultUser consultUser) {
        this.mCheckMark.setVisibility(8);
        this.mActionButton.setEnabled(false);
        this.mActionButton.setText(this.mContext.getString(R.string.consult_profile_unfollowing));
        this.mFollowProgress.setVisibility(0);
        this.mFollowProgress.getIndeterminateDrawable().setColorFilter(-1, PorterDuff.Mode.MULTIPLY);
        ConsultDataManager.getInstance(this.mContext, (Activity) null).unFollowUser(consultUser, new ICallbackEvent<Boolean, MedscapeException>() {
            public void onError(MedscapeException medscapeException) {
                ConsultProfileViewHolder.this.mActionButton.setText(ConsultProfileViewHolder.this.mContext.getString(R.string.profile_following));
                ConsultProfileViewHolder.this.mFollowProgress.setVisibility(8);
                ConsultProfileViewHolder.this.mActionButton.setEnabled(true);
                try {
                    if (ConsultProfileViewHolder.this.mContext != null && (ConsultProfileViewHolder.this.mContext instanceof Activity)) {
                        Activity activity = (Activity) ConsultProfileViewHolder.this.mContext;
                        if (!activity.isFinishing()) {
                            medscapeException.showAlert(activity, "OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }, (String) null, (DialogInterface.OnClickListener) null);
                        }
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultProfileViewHolder.TAG, "Failed to show error when following user");
                }
            }

            public void onCompleted(Boolean bool) {
                OmnitureManager.get().trackModule(ConsultProfileViewHolder.this.mContext, "consult", "consult-profilefollow", "unfollow", (Map<String, Object>) null);
                ConsultProfileViewHolder.this.sendTimeLineUpdatedBroadcast();
                ConsultProfileViewHolder.this.mFollowProgress.setVisibility(8);
                ConsultUser consultUser = consultUser;
                consultUser.setFollowerCount(consultUser.getFollowerCount() - 1);
                ConsultProfileViewHolder.this.mActionButton.setEnabled(true);
                consultUser.setFollowState(Constants.FOLLOWING_STATE_NOT_FOLLOWING);
                ConsultProfileViewHolder.this.setActionButton(consultUser);
                if (ConsultProfileViewHolder.this.mDataSetChangedListener != null) {
                    ConsultProfileViewHolder.this.mDataSetChangedListener.updateUserAndPosition(1, consultUser);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void followUser(final ConsultUser consultUser) {
        this.mActionButton.setEnabled(false);
        this.mActionButton.setText(this.mContext.getString(R.string.consult_profile_following));
        this.mFollowProgress.setVisibility(0);
        this.mFollowProgress.getIndeterminateDrawable().setColorFilter(-16762030, PorterDuff.Mode.MULTIPLY);
        ConsultDataManager.getInstance(this.mContext, (Activity) null).followUser(consultUser, new ICallbackEvent<Boolean, MedscapeException>() {
            public void onError(MedscapeException medscapeException) {
                ConsultProfileViewHolder.this.mActionButton.setText(ConsultProfileViewHolder.this.mContext.getString(R.string.profile_follow));
                ConsultProfileViewHolder.this.mActionButton.setEnabled(true);
                ConsultProfileViewHolder.this.mFollowProgress.setVisibility(8);
                try {
                    if (ConsultProfileViewHolder.this.mContext != null && (ConsultProfileViewHolder.this.mContext instanceof Activity)) {
                        Activity activity = (Activity) ConsultProfileViewHolder.this.mContext;
                        if (!activity.isFinishing()) {
                            medscapeException.showAlert(activity, "OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }, (String) null, (DialogInterface.OnClickListener) null);
                        }
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultProfileViewHolder.TAG, "Failed to show error when following user");
                }
            }

            public void onCompleted(Boolean bool) {
                OmnitureManager.get().trackModule(ConsultProfileViewHolder.this.mContext, "consult", "consult-profilefollow", "follow", (Map<String, Object>) null);
                ConsultProfileViewHolder.this.sendTimeLineUpdatedBroadcast();
                ConsultProfileViewHolder.this.mFollowProgress.setVisibility(8);
                ConsultUser consultUser = consultUser;
                consultUser.setFollowerCount(consultUser.getFollowerCount() + 1);
                ConsultProfileViewHolder.this.mActionButton.setEnabled(true);
                consultUser.setFollowState(Constants.FOLLOWING_STATE_FOLLOWING);
                ConsultProfileViewHolder.this.setActionButton(consultUser);
                if (ConsultProfileViewHolder.this.mDataSetChangedListener != null) {
                    ConsultProfileViewHolder.this.mDataSetChangedListener.updateUserAndPosition(1, consultUser);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendTimeLineUpdatedBroadcast() {
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
    }

    private void setActionButtonColor(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mActionButton.setBackground(drawable);
        } else {
            this.mActionButton.setBackgroundDrawable(drawable);
        }
    }

    private boolean isFollowingConsultUser(ConsultUser consultUser) {
        return consultUser.getFollowState() == 3020;
    }

    private void setActionButtonForCurrentUser(ConsultUser consultUser) {
        if (!StringUtil.isNotEmpty(consultUser.getBio()) || consultUser.getBio().equalsIgnoreCase("null")) {
            this.mActionButton.setText(this.mContext.getString(R.string.profile_add_title));
        } else {
            this.mActionButton.setText(this.mContext.getString(R.string.profile_edit_title));
        }
        setActionButtonColor(this.mContext.getResources().getDrawable(R.drawable.white_rectangular_ripple));
        this.mActionButton.setTextColor(this.mContext.getResources().getColor(R.color.medscape_blue));
        this.mActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConsultProfileViewHolder.this.showEditTitleAndAffiliationDialog();
            }
        });
    }

    private void setCameraVisibility(ConsultUser consultUser) {
        if (consultUser == null || !ConsultDataManager.getInstance(this.mContext, (Activity) null).isCurrentUser(consultUser)) {
            this.mProfileCamera.setVisibility(8);
        } else {
            this.mProfileCamera.setVisibility(0);
        }
    }

    private void addProfileLayoutImageClickHandler(ConsultUser consultUser) {
        if (consultUser != null && ConsultDataManager.getInstance(this.mContext, (Activity) null).isCurrentUser(consultUser)) {
            this.mProfileImageLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultProfileViewHolder.this.showProfileLayoutImageAlert();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showProfileLayoutImageAlert() {
        Activity activity;
        OmnitureManager.get().trackModule(this.mContext, "consult", "consult-profilephoto", "edit", (Map<String, Object>) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setMessage(this.mContext.getString(R.string.profile_image_update_title));
        builder.setPositiveButton(this.mContext.getString(R.string.take_a_photo), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Activity activity;
                if (ConsultProfileViewHolder.this.mContext != null && (ConsultProfileViewHolder.this.mContext instanceof Activity) && (activity = (Activity) ConsultProfileViewHolder.this.mContext) != null && !activity.isFinishing()) {
                    if (PhotoUtil.hasCamera(activity)) {
                        PhotoUtil.takePhoto(ConsultProfileViewHolder.this.mContext, 4000);
                        return;
                    }
                    try {
                        new MedscapeException(ConsultProfileViewHolder.this.mContext.getString(R.string.no_camera_error_title), ConsultProfileViewHolder.this.mContext.getString(R.string.no_camera_error)).showAlert(activity, "OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    } catch (Exception unused) {
                        Trace.w(ConsultProfileViewHolder.TAG, "Failed to show error for no camera");
                    }
                }
            }
        });
        builder.setNegativeButton(this.mContext.getString(R.string.choose_from_library), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ConsultProfileViewHolder.this.mContext != null && (ConsultProfileViewHolder.this.mContext instanceof Activity)) {
                    if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(ConsultProfileViewHolder.this.mContext, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
                        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        Activity activity = (Activity) ConsultProfileViewHolder.this.mContext;
                        if (activity != null && !activity.isFinishing()) {
                            activity.startActivityForResult(intent, 4001);
                            return;
                        }
                        return;
                    }
                    ActivityCompat.requestPermissions((Activity) ConsultProfileViewHolder.this.mContext, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
                }
            }
        });
        final AlertDialog create = builder.create();
        Util.adjustAlertDialogueButtonSize(create);
        Context context = this.mContext;
        if ((context instanceof Activity) && (activity = (Activity) context) != null && !activity.isFinishing()) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Trace.i(ConsultProfileViewHolder.TAG, "Showing profile image upload alert");
                        create.show();
                    } catch (Exception unused) {
                        Trace.w(ConsultProfileViewHolder.TAG, "Failed to show profile image upload alert");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showEditTitleAndAffiliationDialog() {
        Activity activity;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        View inflate = this.mInflater.inflate(R.layout.dialog_edit_title_affiliation, (ViewGroup) null);
        builder.setView(inflate);
        final EditText editText = (EditText) inflate.findViewById(R.id.titleAndAffiliation);
        editText.setFilters(new InputFilter[]{new EmojiFilter()});
        final View findViewById = inflate.findViewById(R.id.loading_progress);
        ConsultUser consultUser = this.mCurrentUser;
        if (consultUser == null || !StringUtil.isNotEmpty(consultUser.getBio())) {
            OmnitureManager.get().trackModule(this.mContext, "consult", "consult-profiletitle", "add", (Map<String, Object>) null);
        } else {
            editText.setText(this.mCurrentUser.getBio());
            OmnitureManager.get().trackModule(this.mContext, "consult", "consult-profiletitle", "edit", (Map<String, Object>) null);
        }
        builder.setNegativeButton(this.mContext.getString(R.string.alert_dialog_confirmation_cancel_button_text), (DialogInterface.OnClickListener) null);
        builder.setPositiveButton(this.mContext.getString(R.string.done), (DialogInterface.OnClickListener) null);
        Context context = this.mContext;
        if ((context instanceof Activity) && (activity = (Activity) context) != null && !activity.isFinishing()) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Trace.i(ConsultProfileViewHolder.TAG, "Showing edit title and affiliation alert");
                        final AlertDialog create = builder.create();
                        create.show();
                        create.getButton(-1).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                ConsultProfileViewHolder.this.handleEditTitleAndAffiliationDoneClick(create, editText, findViewById);
                            }
                        });
                        create.getButton(-2).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                create.dismiss();
                                ConsultProfileViewHolder.this.hideKeyboard();
                            }
                        });
                        Util.showKeyboard(ConsultProfileViewHolder.this.mContext);
                    } catch (Exception unused) {
                        Trace.w(ConsultProfileViewHolder.TAG, "Failed to show edit title and affiliation alert");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleEditTitleAndAffiliationDoneClick(AlertDialog alertDialog, EditText editText, View view) {
        Context context = this.mContext;
        if (context != null && (context instanceof Activity) && !((Activity) context).isFinishing() && editText != null && editText.getText() != null) {
            if (view != null) {
                view.setVisibility(0);
            }
            updateProfileWithBio(alertDialog, editText.getText().toString());
        }
    }

    private void updateProfileWithBio(final AlertDialog alertDialog, final String str) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).updateProfile(this.mContext, str, new IHttpRequestCompleteListener() {
            public void onHttpRequestFailed(MedscapeException medscapeException) {
                if (medscapeException != null && (ConsultProfileViewHolder.this.mContext instanceof Activity)) {
                    try {
                        Activity activity = (Activity) ConsultProfileViewHolder.this.mContext;
                        if (!activity.isFinishing()) {
                            alertDialog.dismiss();
                            ConsultProfileViewHolder.this.hideKeyboard();
                            medscapeException.showAlert(activity, "OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }, (String) null, (DialogInterface.OnClickListener) null);
                        }
                    } catch (Exception unused) {
                        Trace.w(ConsultProfileViewHolder.TAG, "Failed to show exception when updating profile info");
                    }
                }
            }

            public void onHttpRequestSucceeded(HttpResponseObject httpResponseObject) {
                ConsultProfileViewHolder.this.mCurrentUser.setBio(str);
                if (ConsultProfileViewHolder.this.mDataSetChangedListener != null) {
                    ConsultProfileViewHolder.this.mDataSetChangedListener.updateUserAndPosition(0, ConsultProfileViewHolder.this.mCurrentUser);
                }
                alertDialog.dismiss();
                ConsultProfileViewHolder.this.hideKeyboard();
            }
        });
    }

    /* access modifiers changed from: private */
    public void hideKeyboard() {
        Context context = this.mContext;
        if (context != null && (context instanceof Activity)) {
            final Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        try {
                            Util.hideKeyboard(activity);
                        } catch (Exception unused) {
                            Trace.w(ConsultProfileViewHolder.TAG, "Failed to close keyboard");
                        }
                    }
                }, 250);
            }
        }
    }

    public void updateProfileBitMap(String str) {
        boolean z = true;
        this.mIsLoadingData = true;
        boolean z2 = !StringUtil.isNotEmpty(str);
        Bitmap thumbnailFromBitmap = PhotoUtil.getThumbnailFromBitmap(PhotoUtil.getBitMapForPicture(str, this.mScreenWidth), this.mImageSize);
        if (thumbnailFromBitmap == null) {
            z = false;
        }
        if (z2) {
            PhotoUtil.galleryAddPic(this.mContext);
        }
        PhotoUtil.clearRecentPhotoLocation();
        if (z) {
            int i = this.mImageSize;
            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(thumbnailFromBitmap, i, i, false);
            ConsultDataManager.getInstance(this.mContext, (Activity) null).updateProfileImage(this.mContext, thumbnailFromBitmap, new IHttpRequestCompleteListener() {
                public void onHttpRequestFailed(MedscapeException medscapeException) {
                    boolean unused = ConsultProfileViewHolder.this.mIsLoadingData = false;
                    try {
                        medscapeException.showAlert((Activity) ConsultProfileViewHolder.this.mContext, "OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    } catch (Exception unused2) {
                        Trace.w(ConsultProfileViewHolder.TAG, "Failed to show upload failed error");
                    }
                    ConsultProfileViewHolder.this.handleProgressBar();
                }

                public void onHttpRequestSucceeded(HttpResponseObject httpResponseObject) {
                    boolean unused = ConsultProfileViewHolder.this.mIsLoadingData = false;
                    Bitmap unused2 = ConsultProfileViewHolder.this.mProfileBitmap = createScaledBitmap;
                    ConsultProfileViewHolder.this.updateProfileImage();
                }
            });
            return;
        }
        try {
            new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.image_upload_failed)).showAlert((Activity) this.mContext, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (String) null, (DialogInterface.OnClickListener) null);
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show upload failed error");
        }
    }

    public class InternalURLSpan extends URLSpan {
        private String mInternalUrl;

        InternalURLSpan(String str) {
            super(str);
            this.mInternalUrl = str;
        }

        public void onClick(View view) {
            ConsultProfileViewHolder.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.mInternalUrl)));
        }
    }

    private static void addLinkMovementMethod(TextView textView) {
        MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
