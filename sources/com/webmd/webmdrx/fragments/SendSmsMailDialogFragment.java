package com.webmd.webmdrx.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.viewmodels.SendSmsMailViewModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0012\u0010\u001e\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J&\u0010!\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010&\u001a\u00020\u0016H\u0016J\u000e\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u0004J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/webmd/webmdrx/fragments/SendSmsMailDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "OMNI_RX_SECTION", "", "OMNI_RX_SHARE_EMAIL", "OMNI_RX_SHARE_TEXT", "SMS", "cardCookie", "errorLayout", "Landroid/widget/LinearLayout;", "errorText", "Landroid/widget/TextView;", "progressBar", "Landroid/widget/ProgressBar;", "shareTerms", "shareTitle", "smsOrMailText", "viewMode", "viewModel", "Lcom/webmd/webmdrx/viewmodels/SendSmsMailViewModel;", "displayError", "", "errorMsg", "formatLegalText", "initView", "root", "Landroid/view/View;", "isRequiredFieldsEmpty", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "sendOmnitureAction", "action", "setListeners", "Companion", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SendSmsMailDialogFragment.kt */
public final class SendSmsMailDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String EXTRA_CARD_COOKIE = "card_cookie";
    /* access modifiers changed from: private */
    public static final String EXTRA_VIEW_MODE = "view_mode";
    private final String OMNI_RX_SECTION = "rx";
    /* access modifiers changed from: private */
    public final String OMNI_RX_SHARE_EMAIL = "frx-share-email";
    /* access modifiers changed from: private */
    public final String OMNI_RX_SHARE_TEXT = "frx-share-text";
    /* access modifiers changed from: private */
    public final String SMS = "sms";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String cardCookie;
    private LinearLayout errorLayout;
    private TextView errorText;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private TextView shareTerms;
    private TextView shareTitle;
    /* access modifiers changed from: private */
    public TextView smsOrMailText;
    /* access modifiers changed from: private */
    public String viewMode;
    /* access modifiers changed from: private */
    public SendSmsMailViewModel viewModel;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ String access$getCardCookie$p(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        String str = sendSmsMailDialogFragment.cardCookie;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardCookie");
        }
        return str;
    }

    public static final /* synthetic */ ProgressBar access$getProgressBar$p(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        ProgressBar progressBar2 = sendSmsMailDialogFragment.progressBar;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        }
        return progressBar2;
    }

    public static final /* synthetic */ TextView access$getSmsOrMailText$p(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        TextView textView = sendSmsMailDialogFragment.smsOrMailText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("smsOrMailText");
        }
        return textView;
    }

    public static final /* synthetic */ String access$getViewMode$p(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        String str = sendSmsMailDialogFragment.viewMode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewMode");
        }
        return str;
    }

    public static final /* synthetic */ SendSmsMailViewModel access$getViewModel$p(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        SendSmsMailViewModel sendSmsMailViewModel = sendSmsMailDialogFragment.viewModel;
        if (sendSmsMailViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return sendSmsMailViewModel;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_send_sms_mail, viewGroup, false);
        if (getArguments() != null) {
            this.viewMode = String.valueOf(requireArguments().getString(EXTRA_VIEW_MODE));
            this.cardCookie = String.valueOf(requireArguments().getString(EXTRA_CARD_COOKIE));
        }
        ViewModel viewModel2 = ViewModelProviders.of((Fragment) this).get(SendSmsMailViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProviders.of(th…ailViewModel::class.java)");
        this.viewModel = (SendSmsMailViewModel) viewModel2;
        Intrinsics.checkNotNullExpressionValue(inflate, "root");
        initView(inflate);
        setListeners(inflate);
        return inflate;
    }

    public final void initView(View view) {
        Intrinsics.checkNotNullParameter(view, "root");
        View findViewById = view.findViewById(R.id.share_mail_sms);
        Intrinsics.checkNotNullExpressionValue(findViewById, "root.findViewById(R.id.share_mail_sms)");
        this.smsOrMailText = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.share_title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "root.findViewById(R.id.share_title)");
        this.shareTitle = (TextView) findViewById2;
        View findViewById3 = view.findViewById(R.id.share_terms);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "root.findViewById<TextView>(R.id.share_terms)");
        this.shareTerms = (TextView) findViewById3;
        String str = this.viewMode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewMode");
        }
        if (Intrinsics.areEqual((Object) str, (Object) this.SMS)) {
            TextView textView = this.shareTitle;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("shareTitle");
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = requireActivity.getResources().getString(R.string.share_title);
            Intrinsics.checkNotNullExpressionValue(string, "requireActivity().resour…ing(R.string.share_title)");
            String format = String.format(string, Arrays.copyOf(new Object[]{"Text"}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            textView.setText(format);
            TextView textView2 = this.smsOrMailText;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("smsOrMailText");
            }
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            textView2.setHint(requireActivity2.getResources().getString(R.string.phone_hint));
            TextView textView3 = this.smsOrMailText;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("smsOrMailText");
            }
            textView3.setInputType(3);
            TextView textView4 = this.smsOrMailText;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("smsOrMailText");
            }
            textView4.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
            TextView textView5 = this.shareTerms;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("shareTerms");
            }
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
            textView5.setText(Html.fromHtml(requireActivity3.getResources().getString(R.string.sms_terms)));
        } else {
            TextView textView6 = this.shareTitle;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("shareTitle");
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            FragmentActivity requireActivity4 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
            String string2 = requireActivity4.getResources().getString(R.string.share_title);
            Intrinsics.checkNotNullExpressionValue(string2, "requireActivity().resour…ing(R.string.share_title)");
            String format2 = String.format(string2, Arrays.copyOf(new Object[]{"Email"}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "java.lang.String.format(format, *args)");
            textView6.setText(format2);
            TextView textView7 = this.smsOrMailText;
            if (textView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("smsOrMailText");
            }
            FragmentActivity requireActivity5 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
            textView7.setHint(requireActivity5.getResources().getString(R.string.email_address_hint));
            TextView textView8 = this.smsOrMailText;
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("smsOrMailText");
            }
            textView8.setInputType(1);
            TextView textView9 = this.shareTerms;
            if (textView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("shareTerms");
            }
            FragmentActivity requireActivity6 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
            textView9.setText(requireActivity6.getResources().getString(R.string.mail_terms));
        }
        TextView textView10 = this.shareTerms;
        if (textView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shareTerms");
        }
        textView10.setMovementMethod(LinkMovementMethod.getInstance());
        View findViewById4 = view.findViewById(R.id.error_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "root.findViewById(R.id.error_layout)");
        this.errorLayout = (LinearLayout) findViewById4;
        View findViewById5 = view.findViewById(R.id.error_text);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "root.findViewById(R.id.error_text)");
        this.errorText = (TextView) findViewById5;
        View findViewById6 = view.findViewById(R.id.share_progress_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "root.findViewById(R.id.share_progress_bar)");
        this.progressBar = (ProgressBar) findViewById6;
        formatLegalText();
    }

    private final void formatLegalText() {
        String str;
        String str2 = this.viewMode;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewMode");
        }
        if (Intrinsics.areEqual((Object) str2, (Object) this.SMS)) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            str = requireActivity.getResources().getString(R.string.sms_terms);
        } else {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            str = requireActivity2.getResources().getString(R.string.mail_terms);
        }
        Intrinsics.checkNotNullExpressionValue(str, "if (viewMode == SMS)\n   …ring(R.string.mail_terms)");
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new SendSmsMailDialogFragment$formatLegalText$1(this), 29, 53, 33);
        spannableString.setSpan(new SendSmsMailDialogFragment$formatLegalText$2(this), 56, 70, 33);
        TextView textView = this.shareTerms;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shareTerms");
        }
        textView.setText(spannableString);
        TextView textView2 = this.shareTerms;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shareTerms");
        }
        textView2.setClickable(true);
        TextView textView3 = this.shareTerms;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shareTerms");
        }
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private final void setListeners(View view) {
        ((ImageView) view.findViewById(R.id.close)).setOnClickListener(new SendSmsMailDialogFragment$setListeners$1(this));
        SendSmsMailViewModel sendSmsMailViewModel = this.viewModel;
        if (sendSmsMailViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Intrinsics.checkNotNull(sendSmsMailViewModel);
        ObservableBoolean isSending = sendSmsMailViewModel.isSending();
        Intrinsics.checkNotNull(isSending);
        isSending.addOnPropertyChangedCallback(new SendSmsMailDialogFragment$setListeners$2(this));
        ((Button) view.findViewById(R.id.share_send)).setOnClickListener(new SendSmsMailDialogFragment$setListeners$3(this));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/webmd/webmdrx/fragments/SendSmsMailDialogFragment$Companion;", "", "()V", "EXTRA_CARD_COOKIE", "", "EXTRA_VIEW_MODE", "newInstance", "Lcom/webmd/webmdrx/fragments/SendSmsMailDialogFragment;", "viewMode", "cardCookie", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SendSmsMailDialogFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SendSmsMailDialogFragment newInstance(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "viewMode");
            Intrinsics.checkNotNullParameter(str2, "cardCookie");
            SendSmsMailDialogFragment sendSmsMailDialogFragment = new SendSmsMailDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(SendSmsMailDialogFragment.EXTRA_VIEW_MODE, str);
            bundle.putString(SendSmsMailDialogFragment.EXTRA_CARD_COOKIE, str2);
            sendSmsMailDialogFragment.setStyle(2, R.style.ControlDialog);
            sendSmsMailDialogFragment.setCancelable(false);
            sendSmsMailDialogFragment.setArguments(bundle);
            return sendSmsMailDialogFragment;
        }
    }

    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        WindowManager windowManager = requireActivity.getWindowManager();
        Intrinsics.checkNotNullExpressionValue(windowManager, "requireActivity().windowManager");
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = (int) (((double) ((float) displayMetrics.widthPixels)) * 0.9d);
        int i3 = (int) (((double) ((float) i)) * 0.5d);
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        Intrinsics.checkNotNull(window);
        window.setLayout(i2, i3);
    }

    public final void displayError(String str) {
        LinearLayout linearLayout = this.errorLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorLayout");
        }
        if (linearLayout != null) {
            TextView textView = this.errorText;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorText");
            }
            if (textView != null && str != null) {
                LinearLayout linearLayout2 = this.errorLayout;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("errorLayout");
                }
                linearLayout2.setVisibility(0);
                TextView textView2 = this.errorText;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("errorText");
                }
                textView2.setText(str);
                ProgressBar progressBar2 = this.progressBar;
                if (progressBar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                }
                progressBar2.setVisibility(8);
            }
        }
    }

    public final boolean isRequiredFieldsEmpty() {
        String str;
        TextView textView = this.smsOrMailText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("smsOrMailText");
        }
        if (!(textView.getText().toString().length() == 0)) {
            return false;
        }
        String str2 = this.viewMode;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewMode");
        }
        if (str2.equals(this.SMS)) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            str = requireActivity.getResources().getString(R.string.error_invalid_phone);
        } else {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            str = requireActivity2.getResources().getString(R.string.error_invalid_email);
        }
        displayError(str);
        return true;
    }

    public final void sendOmnitureAction(String str) {
        Intrinsics.checkNotNullParameter(str, "action");
        WBMDOmnitureManager wBMDOmnitureManager = WBMDOmnitureManager.shared;
        Intrinsics.checkNotNullExpressionValue(wBMDOmnitureManager, "WBMDOmnitureManager.shared");
        WBMDOmnitureModule wBMDOmnitureModule = new WBMDOmnitureModule(str, (String) null, StringExtensions.removeTrailingSlash(wBMDOmnitureManager.getLastSentPage()));
        Map hashMap = new HashMap();
        hashMap.put("wapp.section", this.OMNI_RX_SECTION);
        wBMDOmnitureModule.setProperties(hashMap);
        WBMDOmnitureManager.sendModuleAction(wBMDOmnitureModule);
    }
}
