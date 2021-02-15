package com.webmd.webmdrx.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.util.Util;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShareFragmentDialog extends DialogFragment implements View.OnClickListener {
    private static final int ID_SHARE_DOWNLOAD = 1003;
    private static final int ID_SHARE_GMAIL = 1001;
    private static final int ID_SHARE_MESSAGE = 1002;
    private Activity attachedActivity;
    private ClickableSpan clickableSpan;
    CallbackManager facebookCallbackManager;
    LoginManager facebookLoginManager;
    private View.OnClickListener flipListener;
    private boolean isBackVisible = false;
    private View mCardBack;
    private View mCardFront;
    private OnCardSharedListener mCardSharedListener;
    private AnimatorSet mFlipLeftIn;
    private AnimatorSet mFlipRightOut;
    private TextView savingCardBackDisclaimer;

    public interface OnCardSharedListener {
        void onCardShared(String str);
    }

    public void sendShareAction(String str) {
    }

    public void setOnCardSharedListener(OnCardSharedListener onCardSharedListener) {
        this.mCardSharedListener = onCardSharedListener;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.ShareDialogStyle);
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(-1, -1);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onAttach(Activity activity) {
        this.attachedActivity = activity;
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public Activity getAttachedActivity() {
        return this.attachedActivity;
    }

    public void setAttachedActivity(Activity activity) {
        this.attachedActivity = activity;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_dialog_share, viewGroup, false);
        this.mCardFront = inflate.findViewById(R.id.f_dialog_layout_savings_card).findViewById(R.id.f_savings_card_front);
        this.mCardBack = inflate.findViewById(R.id.f_dialog_layout_savings_card).findViewById(R.id.f_savings_card_back);
        this.mFlipRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.savings_card_animator_flip_card_to_back);
        this.mFlipLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.savings_card_animator_flip_card_to_front);
        ((TextView) this.mCardFront.findViewById(R.id.f_savings_text_view_group)).setText(Util.getRxGroup(getActivity()));
        ((TextView) this.mCardFront.findViewById(R.id.f_savings_text_view_member)).setText(Util.getMemberId(getActivity()));
        this.flipListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (view.getTag() != null) {
                    view.getTag().equals("f_savings_image_view_info");
                }
                ShareFragmentDialog.this.flipCard();
            }
        };
        this.mCardFront.findViewById(R.id.f_savings_image_view_info).setOnClickListener(this.flipListener);
        inflate.findViewById(R.id.f_dialog_layout_savings_card).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        inflate.findViewById(R.id.f_dialog_layout_savings_card).findViewById(R.id.f_savings_image_view_share).setVisibility(8);
        setUpListeners(inflate);
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        double d = (double) displayMetrics.density;
        if (getTwitterIntent() == null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (50.0d * d), (int) (59.0d * d), 1.0f);
            layoutParams.setMargins((int) (d * FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), 0, 0, 0);
            inflate.findViewById(R.id.f_dialog_facebook_icon_layout).setLayoutParams(layoutParams);
            inflate.findViewById(R.id.f_dialog_layout_twitter_layout).setVisibility(8);
        }
        this.savingCardBackDisclaimer = (TextView) inflate.findViewById(R.id.f_savings_card_back_disclaimer);
        this.clickableSpan = new ClickableSpan() {
            public void onClick(View view) {
                ShareFragmentDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.webmd.com/rx")));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        };
        return inflate;
    }

    public void clicableLink(ClickableSpan clickableSpan2) {
        String charSequence = this.savingCardBackDisclaimer.getText().toString();
        int length = charSequence.length() - 17;
        int length2 = charSequence.length() - 1;
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(clickableSpan2, length, length2, 33);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#1682C5")), length, length2, 33);
        this.savingCardBackDisclaimer.setText(spannableString);
        this.savingCardBackDisclaimer.setMovementMethod(LinkMovementMethod.getInstance());
        this.savingCardBackDisclaimer.setHighlightColor(0);
    }

    private void setUpListeners(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareFragmentDialog.this.dismiss();
            }
        });
        view.findViewById(R.id.f_dialog_share_image_view_email).setOnClickListener(this);
        view.findViewById(R.id.f_dialog_share_image_view_message).setOnClickListener(this);
        view.findViewById(R.id.f_dialog_share_image_view_facebook).setOnClickListener(this);
        view.findViewById(R.id.f_dialog_share_image_view_twitter).setOnClickListener(this);
        view.findViewById(R.id.f_dialog_share_image_view_save).setOnClickListener(this);
        view.findViewById(R.id.f_dialog_share_image_view_copy).setOnClickListener(this);
    }

    private Intent getTwitterIntent() {
        boolean z;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        Iterator<ResolveInfo> it = getActivity().getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            ResolveInfo next = it.next();
            if (next.activityInfo.packageName.startsWith("com.twitter.android")) {
                intent.setClassName(next.activityInfo.packageName, next.activityInfo.name);
                z = true;
                break;
            }
        }
        if (z) {
            return intent;
        }
        return null;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.f_dialog_share_image_view_email) {
            shareEmail();
        } else if (id == R.id.f_dialog_share_image_view_message) {
            shareSms();
        } else if (id == R.id.f_dialog_share_image_view_facebook) {
            shareFacebook();
        } else if (id == R.id.f_dialog_share_image_view_twitter) {
            shareTwitter();
        } else if (id == R.id.f_dialog_share_image_view_save) {
            saveBmpToDevice();
        } else if (id == R.id.f_dialog_share_image_view_copy) {
            copyToClipboard();
        }
        OnCardSharedListener onCardSharedListener = this.mCardSharedListener;
        if (onCardSharedListener != null) {
            onCardSharedListener.onCardShared("");
        }
    }

    private void shareTwitter() {
        Intent twitterIntent = getTwitterIntent();
        if (twitterIntent != null) {
            twitterIntent.putExtra("android.intent.extra.TEXT", Util.savingsCardToString(getActivity(), this.mCardFront));
            sendShareAction("twitter");
            startActivity(twitterIntent);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.facebookCallbackManager.onActivityResult(i, i2, intent);
    }

    private void loginToFacebook() {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        this.facebookCallbackManager = CallbackManager.Factory.create();
        List singletonList = Collections.singletonList("publish_actions");
        LoginManager instance = LoginManager.getInstance();
        this.facebookLoginManager = instance;
        instance.logInWithPublishPermissions((Activity) getActivity(), (Collection<String>) singletonList);
        this.facebookLoginManager.registerCallback(this.facebookCallbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                ShareFragmentDialog.this.shareBitmapToFacebook();
            }

            public void onCancel() {
                System.out.println("onCancel");
            }

            public void onError(FacebookException facebookException) {
                System.out.println("onError");
            }
        });
    }

    /* access modifiers changed from: private */
    public void shareBitmapToFacebook() {
        Bitmap bitmapFromView = Util.getBitmapFromView(this.mCardFront);
        Util.saveBitmapToDevice(getActivity(), bitmapFromView);
        ShareDialog.show((Activity) getActivity(), (ShareContent) new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setBitmap(bitmapFromView).setCaption(Util.savingsCardToString(getActivity(), this.mCardFront)).build()).build());
    }

    private void shareFacebook() {
        sendShareAction("facebook");
        if (AccessToken.getCurrentAccessToken() != null) {
            shareBitmapToFacebook();
        } else {
            loginToFacebook();
        }
    }

    private void showToast(String str) {
        Toast.makeText(getActivity(), str, 1).show();
    }

    private void copyToClipboard() {
        ((ClipboardManager) getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Text : ", Util.savingsCardToString(getActivity(), this.mCardFront)));
        sendShareAction("copy");
        showToast(getActivity().getString(R.string.share_dialog_clipboard));
    }

    private void saveBmpToDevice() {
        sendShareAction("save");
        if (hasReadWritePermission()) {
            Util.saveBitmapToDevice(getActivity(), Util.getBitmapFromView(this.mCardFront));
            showToast(getActivity().getString(R.string.share_dialog_save));
            return;
        }
        requestReadWritePermission(1003);
    }

    private void shareEmail() {
        if (hasReadWritePermission()) {
            Util.saveBitmapToDevice(getActivity(), Util.getBitmapFromView(this.mCardFront));
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "", (String) null));
            intent.putExtra("android.intent.extra.SUBJECT", getActivity().getString(R.string.share_dialog_email_subject));
            intent.putExtra("android.intent.extra.TEXT", Util.savingsCardToString(getActivity(), this.mCardFront));
            if (Util.getSavedSavingsCard(getActivity()) == null) {
                saveBmpToDevice();
            }
            intent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + Util.getSavedSavingsCard(getActivity())));
            boolean z = false;
            Iterator<ResolveInfo> it = getActivity().getPackageManager().queryIntentActivities(intent, 65536).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (next.activityInfo.packageName.startsWith("com.google.android.gm")) {
                    intent.setClassName(next.activityInfo.packageName, next.activityInfo.name);
                    z = true;
                    break;
                }
            }
            sendShareAction("mail");
            if (z) {
                startActivity(intent);
            } else {
                startActivity(Intent.createChooser(intent, getString(R.string.share_intent_email_title)));
            }
        } else {
            requestReadWritePermission(1001);
        }
    }

    private void shareSms() {
        if (hasReadWritePermission()) {
            sendShareAction("Text");
            Util.saveBitmapToDevice(getActivity(), Util.getBitmapFromView(this.mCardFront));
            String savingsCardToString = Util.savingsCardToString(getActivity(), this.mCardFront);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            if (Util.isMarshmallow()) {
                intent.putExtra("android.intent.extra.TEXT", savingsCardToString);
            } else {
                intent.putExtra("sms_body", savingsCardToString);
            }
            intent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + Util.getSavedSavingsCard(getActivity())));
            boolean z = false;
            for (ResolveInfo next : getActivity().getPackageManager().queryIntentActivities(intent, 65536)) {
                if (next.activityInfo.packageName.contains("com.android.mms") || next.activityInfo.packageName.contains("com.google.android.apps.messaging")) {
                    intent.setClassName(next.activityInfo.packageName, next.activityInfo.name);
                    z = true;
                }
            }
            if (z) {
                intent.setType("image/png");
                startActivity(intent);
                return;
            }
            startActivity(Intent.createChooser(intent, getString(R.string.share_intent_with_title)));
            return;
        }
        requestReadWritePermission(1002);
    }

    private boolean hasReadWritePermission() {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private void requestReadWritePermission(int i) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, i);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        handleOnRequestPermissionResult(i, strArr, iArr);
    }

    public boolean handleOnRequestPermissionResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 1001:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    showToast(getString(R.string.without_permissions));
                    return true;
                }
                shareEmail();
                return true;
            case 1002:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    showToast(getString(R.string.without_permissions));
                    return true;
                }
                shareSms();
                return true;
            case 1003:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    showToast(getString(R.string.without_permissions));
                    return true;
                }
                saveBmpToDevice();
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public void flipCard() {
        changeCameraDistance();
        if (!this.isBackVisible) {
            flipToBack();
            clicableLink(this.clickableSpan);
            return;
        }
        flipToFront();
        clicableLink((ClickableSpan) null);
        this.savingCardBackDisclaimer.setMovementMethod((MovementMethod) null);
    }

    private void changeCameraDistance() {
        float f = getResources().getDisplayMetrics().density * ((float) 10000);
        this.mCardFront.setCameraDistance(f);
        this.mCardBack.setCameraDistance(f);
    }

    private void flipToFront() {
        this.mCardBack.findViewById(R.id.savings_card_back_text_view_back).setOnClickListener((View.OnClickListener) null);
        this.mCardFront.findViewById(R.id.f_savings_image_view_info).setOnClickListener(this.flipListener);
        this.mFlipRightOut.setTarget(this.mCardBack);
        this.mFlipLeftIn.setTarget(this.mCardFront);
        this.mFlipRightOut.start();
        this.mFlipLeftIn.start();
        this.isBackVisible = false;
    }

    private void flipToBack() {
        this.mCardBack.findViewById(R.id.savings_card_back_text_view_back).setOnClickListener(this.flipListener);
        this.mCardFront.findViewById(R.id.f_savings_image_view_info).setOnClickListener((View.OnClickListener) null);
        this.mFlipRightOut.setTarget(this.mCardFront);
        this.mFlipLeftIn.setTarget(this.mCardBack);
        this.mFlipRightOut.start();
        this.mFlipLeftIn.start();
        this.isBackVisible = true;
    }
}
