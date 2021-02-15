package com.webmd.webmdrx.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.RxBaseActivity;
import com.webmd.webmdrx.manager.ApiManager;
import com.webmd.webmdrx.models.DrugPriceInfo;
import com.webmd.webmdrx.models.Pharmacy;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.tasks.TaskRequestHelper;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.NetworkUtil;
import com.webmd.webmdrx.util.UsPhoneNumberFormatter;
import com.webmd.webmdrx.util.Util;
import com.webmd.webmdrx.util.WebMDException;
import com.webmd.webmdrx.util.customtooltip.CustomToolTipWithAnimation;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import webmd.com.environmentswitcher.EnvironmentManager;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 F2\u00020\u0001:\u0002FGB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010*\u001a\u00020\u000fH\u0002J\b\u0010+\u001a\u00020,H\u0002J\u0006\u0010-\u001a\u00020,J\b\u0010.\u001a\u00020,H\u0002J\u0012\u0010/\u001a\u00020,2\b\u00100\u001a\u0004\u0018\u000101H\u0016J&\u00102\u001a\u0004\u0018\u00010!2\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00107\u001a\u00020,H\u0016J\b\u00108\u001a\u00020,H\u0016J\b\u00109\u001a\u00020,H\u0002J\u0018\u0010:\u001a\u00020,2\u0006\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u0006H\u0002J\u0006\u0010=\u001a\u00020,J\u001a\u0010>\u001a\u00020,2\b\u0010?\u001a\u0004\u0018\u00010!2\u0006\u0010@\u001a\u00020AH\u0002J\b\u0010B\u001a\u00020,H\u0002J\u0010\u0010C\u001a\u00020,2\u0006\u0010D\u001a\u00020\u000fH\u0002J\u0010\u0010E\u001a\u00020,2\u0006\u0010D\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0007\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/webmd/webmdrx/fragments/ShareSavingsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "customToolTip", "Lcom/webmd/webmdrx/util/customtooltip/CustomToolTipWithAnimation;", "isFragmentAttached", "", "()Z", "isLaunchFromSavingsCardView", "isLaunchFromSavingsCardView$wbmdrx_release", "setLaunchFromSavingsCardView$wbmdrx_release", "(Z)V", "mContactInfoEditText", "Landroid/widget/EditText;", "mDosage", "", "mDrugContentID", "mDrugName", "mDrugPackageSize", "", "mDrugPrice", "mForm", "mIcd", "mInternetException", "Lcom/webmd/webmdrx/util/WebMDException;", "mIsShareviaEmail", "mPharmacyName", "mPrice", "Lcom/webmd/webmdrx/models/Price;", "mProgressBar", "Landroid/widget/ProgressBar;", "mQuantity", "mRootView", "Landroid/view/View;", "mScreenClickability", "Lcom/webmd/webmdrx/fragments/ShareSavingsFragment$ScreenClickablity;", "mSendSavingsCardButton", "Landroid/widget/Button;", "mShareOptionSubTitle1", "Landroid/widget/TextView;", "mShareOptionSubTitle2", "mShareSavingsCardInfo", "buildEncodedCardCookie", "configureSavingsCardLaunch", "", "hideKeyboard", "hideLoadingSpinner", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPause", "onResume", "setupViews", "shareContactInfo", "contactInfo", "isEmail", "showHintBubble", "showInternetRequiredSnackBar", "root", "listener", "Landroid/view/View$OnClickListener;", "showLoadingSpinner", "showSimpleDialog", "message", "showSuccessMsg", "Companion", "ScreenClickablity", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareSavingsFragment.kt */
public final class ShareSavingsFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int SAVINGS_CARD_VIEW = 1;
    private HashMap _$_findViewCache;
    private CustomToolTipWithAnimation customToolTip;
    private boolean isLaunchFromSavingsCardView;
    /* access modifiers changed from: private */
    public EditText mContactInfoEditText;
    /* access modifiers changed from: private */
    public String mDosage = "";
    /* access modifiers changed from: private */
    public String mDrugContentID;
    /* access modifiers changed from: private */
    public String mDrugName = "";
    /* access modifiers changed from: private */
    public double mDrugPackageSize;
    private double mDrugPrice;
    /* access modifiers changed from: private */
    public String mForm = "";
    /* access modifiers changed from: private */
    public String mIcd = "";
    private WebMDException mInternetException;
    /* access modifiers changed from: private */
    public boolean mIsShareviaEmail;
    private String mPharmacyName = "";
    /* access modifiers changed from: private */
    public Price mPrice = new Price();
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public double mQuantity;
    private View mRootView;
    /* access modifiers changed from: private */
    public ScreenClickablity mScreenClickability;
    /* access modifiers changed from: private */
    public Button mSendSavingsCardButton;
    private TextView mShareOptionSubTitle1;
    private TextView mShareOptionSubTitle2;
    private TextView mShareSavingsCardInfo;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/webmd/webmdrx/fragments/ShareSavingsFragment$ScreenClickablity;", "", "setScreenClickability", "", "isClickable", "", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ShareSavingsFragment.kt */
    public interface ScreenClickablity {
        void setScreenClickability(boolean z);
    }

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

    public final boolean isLaunchFromSavingsCardView$wbmdrx_release() {
        return this.isLaunchFromSavingsCardView;
    }

    public final void setLaunchFromSavingsCardView$wbmdrx_release(boolean z) {
        this.isLaunchFromSavingsCardView = z;
    }

    public final boolean isFragmentAttached() {
        return isAdded() && getActivity() != null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_share_savings, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        String str;
        String str2;
        String str3;
        Price price;
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        setupViews();
        if (arguments != null) {
            this.mIsShareviaEmail = arguments.getBoolean("isShareViaEmail");
            this.mQuantity = arguments.getDouble(Constants.EXTRA_QUANTITY);
            String str4 = "";
            if (arguments.getString(Constants.EXTRA_FORM) != null) {
                str = arguments.getString(Constants.EXTRA_FORM);
                Intrinsics.checkNotNull(str);
                Intrinsics.checkNotNullExpressionValue(str, "args.getString(Constants.EXTRA_FORM)!!");
            } else {
                str = str4;
            }
            this.mForm = str;
            if (arguments.getString(Constants.EXTRA_DOSAGE) != null) {
                str2 = arguments.getString(Constants.EXTRA_DOSAGE);
                Intrinsics.checkNotNull(str2);
                Intrinsics.checkNotNullExpressionValue(str2, "args.getString(Constants.EXTRA_DOSAGE)!!");
            } else {
                str2 = str4;
            }
            this.mDosage = str2;
            if (arguments.getString("extra_drug_name") != null) {
                str3 = arguments.getString("extra_drug_name");
                Intrinsics.checkNotNull(str3);
                Intrinsics.checkNotNullExpressionValue(str3, "args.getString(Constants.EXTRA_DRUG_NAME)!!");
            } else {
                str3 = str4;
            }
            this.mDrugName = str3;
            this.mDrugPackageSize = arguments.getDouble(Constants.EXTRA_PACKAGE_SIZE);
            if (arguments.getString(Constants.EXTRA_ICD) != null) {
                str4 = arguments.getString(Constants.EXTRA_ICD);
                Intrinsics.checkNotNull(str4);
                Intrinsics.checkNotNullExpressionValue(str4, "args.getString(Constants.EXTRA_ICD)!!");
            }
            this.mIcd = str4;
            if (arguments.getParcelable(Constants.EXTRA_PRICES) != null) {
                Parcelable parcelable = arguments.getParcelable(Constants.EXTRA_PRICES);
                Intrinsics.checkNotNull(parcelable);
                Intrinsics.checkNotNullExpressionValue(parcelable, "args.getParcelable<Price…Constants.EXTRA_PRICES)!!");
                price = (Price) parcelable;
            } else {
                price = new Price();
            }
            this.mPrice = price;
            Pharmacy pharmacy = price.getPharmacy();
            Intrinsics.checkNotNullExpressionValue(pharmacy, "mPrice.pharmacy");
            String name = pharmacy.getName();
            Intrinsics.checkNotNullExpressionValue(name, "mPrice.pharmacy.name");
            this.mPharmacyName = name;
            DrugPriceInfo drugPriceInfo = this.mPrice.getDrugPriceInfo();
            Intrinsics.checkNotNullExpressionValue(drugPriceInfo, "mPrice.drugPriceInfo");
            this.mDrugPrice = drugPriceInfo.getDiscountPricing();
        }
        if (isFragmentAttached()) {
            if (this.mIsShareviaEmail) {
                TextView textView = this.mShareOptionSubTitle1;
                Intrinsics.checkNotNull(textView);
                textView.setText(getString(R.string.send_email));
                TextView textView2 = this.mShareOptionSubTitle2;
                Intrinsics.checkNotNull(textView2);
                textView2.setText(getString(R.string.patient_email));
                EditText editText = this.mContactInfoEditText;
                Intrinsics.checkNotNull(editText);
                editText.setHint(getString(R.string.email_hint));
                EditText editText2 = this.mContactInfoEditText;
                Intrinsics.checkNotNull(editText2);
                editText2.setInputType(33);
            } else {
                TextView textView3 = this.mShareOptionSubTitle1;
                Intrinsics.checkNotNull(textView3);
                textView3.setText(getString(R.string.text_phone));
                TextView textView4 = this.mShareOptionSubTitle2;
                Intrinsics.checkNotNull(textView4);
                textView4.setText(getString(R.string.send_text));
                EditText editText3 = this.mContactInfoEditText;
                Intrinsics.checkNotNull(editText3);
                editText3.setHint(getString(R.string.mobile_hint));
                EditText editText4 = this.mContactInfoEditText;
                Intrinsics.checkNotNull(editText4);
                editText4.setInputType(3);
                UsPhoneNumberFormatter usPhoneNumberFormatter = new UsPhoneNumberFormatter(new WeakReference(this.mContactInfoEditText));
                EditText editText5 = this.mContactInfoEditText;
                Intrinsics.checkNotNull(editText5);
                editText5.addTextChangedListener(usPhoneNumberFormatter);
                EditText editText6 = this.mContactInfoEditText;
                Intrinsics.checkNotNull(editText6);
                editText6.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
            }
            configureSavingsCardLaunch();
            EditText editText7 = this.mContactInfoEditText;
            Intrinsics.checkNotNull(editText7);
            editText7.setOnEditorActionListener(new ShareSavingsFragment$onActivityCreated$1(this));
        }
        Button button = this.mSendSavingsCardButton;
        Intrinsics.checkNotNull(button);
        button.setOnClickListener(new ShareSavingsFragment$onActivityCreated$2(this));
        showHintBubble();
    }

    public void onResume() {
        FragmentActivity activity;
        super.onResume();
        if (isAdded() && (activity = getActivity()) != null && (activity instanceof RxBaseActivity)) {
            ((RxBaseActivity) activity).mRxOmnitureSender.sendProfPageView("drug/view/webmdrx/drug-prices/share");
        }
    }

    private final void setupViews() {
        FragmentActivity activity = getActivity();
        if (isFragmentAttached()) {
            Intrinsics.checkNotNull(activity);
            View findViewById = activity.findViewById(R.id.share_option_sub_title1);
            if (findViewById != null) {
                this.mShareOptionSubTitle1 = (TextView) findViewById;
                View findViewById2 = activity.findViewById(R.id.share_option_sub_title2);
                if (findViewById2 != null) {
                    this.mShareOptionSubTitle2 = (TextView) findViewById2;
                    View findViewById3 = activity.findViewById(R.id.edit_text_contact);
                    if (findViewById3 != null) {
                        this.mContactInfoEditText = (EditText) findViewById3;
                        View findViewById4 = activity.findViewById(R.id.share_saving_card_info);
                        if (findViewById4 != null) {
                            this.mShareSavingsCardInfo = (TextView) findViewById4;
                            View findViewById5 = activity.findViewById(R.id.button_share_savings_card);
                            if (findViewById5 != null) {
                                this.mSendSavingsCardButton = (Button) findViewById5;
                                View findViewById6 = activity.findViewById(R.id.progress_bar);
                                if (findViewById6 != null) {
                                    this.mProgressBar = (ProgressBar) findViewById6;
                                    this.mRootView = activity.findViewById(R.id.root_layout);
                                    Button button = this.mSendSavingsCardButton;
                                    Intrinsics.checkNotNull(button);
                                    Context requireContext = requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    button.setTypeface(Typeface.createFromAsset(requireContext.getAssets(), "font/Roboto-Medium.ttf"));
                                    return;
                                }
                                throw new NullPointerException("null cannot be cast to non-null type android.widget.ProgressBar");
                            }
                            throw new NullPointerException("null cannot be cast to non-null type android.widget.Button");
                        }
                        throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.EditText");
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }
    }

    private final void configureSavingsCardLaunch() {
        String str;
        if (this.mIsShareviaEmail) {
            str = getString(R.string.saving_card_info_email);
        } else {
            str = getString(R.string.saving_card_info_mobile);
        }
        Intrinsics.checkNotNullExpressionValue(str, "if (mIsShareviaEmail)\n  ….saving_card_info_mobile)");
        int i = 3;
        int i2 = 22;
        CharSequence charSequence = str;
        if (StringsKt.contains$default(charSequence, (CharSequence) "image of the savings card", false, 2, (Object) null)) {
            i = StringsKt.indexOf$default(charSequence, "image of the savings card", 0, false, 6, (Object) null);
            i2 = i + 25;
        }
        SpannableString spannableString = new SpannableString(charSequence);
        ShareSavingsFragment$configureSavingsCardLaunch$clickableSpan$1 shareSavingsFragment$configureSavingsCardLaunch$clickableSpan$1 = new ShareSavingsFragment$configureSavingsCardLaunch$clickableSpan$1(this);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(requireActivity(), R.color.link_color));
        spannableString.setSpan(shareSavingsFragment$configureSavingsCardLaunch$clickableSpan$1, i, i2, 33);
        spannableString.setSpan(foregroundColorSpan, i, i2, 18);
        TextView textView = this.mShareSavingsCardInfo;
        Intrinsics.checkNotNull(textView);
        textView.setText(spannableString);
        TextView textView2 = this.mShareSavingsCardInfo;
        Intrinsics.checkNotNull(textView2);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView3 = this.mShareSavingsCardInfo;
        Intrinsics.checkNotNull(textView3);
        textView3.setHighlightColor(0);
    }

    public void onPause() {
        super.onPause();
        CustomToolTipWithAnimation customToolTipWithAnimation = this.customToolTip;
        if (customToolTipWithAnimation != null) {
            Intrinsics.checkNotNull(customToolTipWithAnimation);
            customToolTipWithAnimation.dismissImmediately();
        }
    }

    /* access modifiers changed from: private */
    public final void shareContactInfo(String str, boolean z) {
        if (isFragmentAttached() && getActivity() != null && NetworkUtil.isOnline(getActivity())) {
            HashMap hashMap = new HashMap();
            EnvironmentManager instance = EnvironmentManager.getInstance(getActivity());
            long currentTimeMillis = System.currentTimeMillis();
            Intrinsics.checkNotNullExpressionValue(instance, "environmentManager");
            String searchClientId = instance.getSearchClientId();
            String clientSecretHashForTimestamp = TaskRequestHelper.getClientSecretHashForTimestamp(currentTimeMillis, instance.getSearchSecretId(), searchClientId);
            Map map = hashMap;
            Intrinsics.checkNotNullExpressionValue(clientSecretHashForTimestamp, "secretHash");
            CharSequence charSequence = clientSecretHashForTimestamp;
            int length = charSequence.length() - 1;
            int i = 0;
            boolean z2 = false;
            while (i <= length) {
                boolean z3 = Intrinsics.compare((int) charSequence.charAt(!z2 ? i : length), 32) <= 0;
                if (!z2) {
                    if (!z3) {
                        z2 = true;
                    } else {
                        i++;
                    }
                } else if (!z3) {
                    break;
                } else {
                    length--;
                }
            }
            map.put("enc_data", charSequence.subSequence(i, length + 1).toString());
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s", Arrays.copyOf(new Object[]{Long.valueOf(currentTimeMillis)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            map.put("timestamp", format);
            Intrinsics.checkNotNullExpressionValue(searchClientId, "clientId");
            map.put("client_id", searchClientId);
            map.put("Content-Type", "application/json; charset=utf-8");
            String replace$default = StringsKt.replace$default(StringsKt.replace$default(buildEncodedCardCookie(), "+", "%20", false, 4, (Object) null), ",", "", false, 4, (Object) null);
            Trace.d("Cookie", "cookieString " + replace$default);
            map.put("cookie", replace$default);
            ApiManager.getInstance().shareSavingsCard(getActivity(), str, z, hashMap, new ShareSavingsFragment$shareContactInfo$2(this), false);
        } else if (isFragmentAttached()) {
            showInternetRequiredSnackBar(this.mRootView, new ShareSavingsFragment$shareContactInfo$3(this, str, z));
        }
    }

    /* access modifiers changed from: private */
    public final void showSimpleDialog(String str) {
        if (isFragmentAttached()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setMessage((CharSequence) str);
            builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) ShareSavingsFragment$showSimpleDialog$1.INSTANCE);
            AlertDialog create = builder.create();
            Intrinsics.checkNotNullExpressionValue(create, "builder.create()");
            create.setCanceledOnTouchOutside(true);
            create.show();
            hideLoadingSpinner();
        }
    }

    /* access modifiers changed from: private */
    public final void showLoadingSpinner() {
        ProgressBar progressBar = this.mProgressBar;
        Intrinsics.checkNotNull(progressBar);
        progressBar.setVisibility(0);
        ScreenClickablity screenClickablity = this.mScreenClickability;
        Intrinsics.checkNotNull(screenClickablity);
        screenClickablity.setScreenClickability(false);
    }

    /* access modifiers changed from: private */
    public final void hideLoadingSpinner() {
        ProgressBar progressBar = this.mProgressBar;
        Intrinsics.checkNotNull(progressBar);
        progressBar.postDelayed(new ShareSavingsFragment$hideLoadingSpinner$1(this), 100);
    }

    public final void showHintBubble() {
        String str;
        if (isFragmentAttached() && this.mSendSavingsCardButton != null) {
            if (this.mIsShareviaEmail) {
                str = getString(R.string.bubble_text_email);
            } else {
                str = getString(R.string.bubble_text_phone);
            }
            Intrinsics.checkNotNullExpressionValue(str, "if (mIsShareviaEmail)\n  …string.bubble_text_phone)");
            CustomToolTipWithAnimation build = new CustomToolTipWithAnimation.Builder(getActivity()).anchorView(this.mSendSavingsCardButton).text((CharSequence) str).gravity(80).animated(true).animationDuration(1200).animationPadding(35.0f).margin(3.0f).dismissOnOutsideTouch(true).dismissOnInsideTouch(true).padding(0.0f).maxWidth(-1.0f).arrowHeight(getResources().getDimension(R.dimen.customtooltip_arrow_height)).arrowWidth(getResources().getDimension(R.dimen.customtooltip_arrow_width)).arrowColor(getResources().getColor(R.color.share_label_text)).contentView(R.layout.tooltip_custom, R.id.tooltip_textview).build();
            this.customToolTip = build;
            Intrinsics.checkNotNull(build);
            build.show();
        }
    }

    public final void hideKeyboard() {
        Util.hideKeyboard(getActivity());
    }

    /* access modifiers changed from: private */
    public final void showSuccessMsg(String str) {
        hideLoadingSpinner();
        Context context = getContext();
        if (context != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle((CharSequence) str);
            builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) new ShareSavingsFragment$showSuccessMsg$$inlined$let$lambda$1(this, str));
            AlertDialog create = builder.create();
            Intrinsics.checkNotNullExpressionValue(create, "builder.create()");
            create.setCanceledOnTouchOutside(false);
            create.show();
        }
    }

    private final void showInternetRequiredSnackBar(View view, View.OnClickListener onClickListener) {
        hideLoadingSpinner();
        this.mInternetException = new WebMDException(getResources().getString(R.string.error_connection_required));
        Context context = getContext();
        if (context != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage((CharSequence) getString(R.string.connection_error_message));
            builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) ShareSavingsFragment$showInternetRequiredSnackBar$1$1.INSTANCE);
            AlertDialog create = builder.create();
            Intrinsics.checkNotNullExpressionValue(create, "builder.create()");
            create.setCanceledOnTouchOutside(true);
            create.show();
        }
    }

    private final String buildEncodedCardCookie() {
        String str = "{\"Pharmacy\":\"" + this.mPharmacyName + "\",\"Price\":" + this.mDrugPrice + ",\"DrugName\":\"" + this.mDrugName + "\",\"DrugForm\":\"" + this.mDosage + ' ' + this.mQuantity + ' ' + this.mForm + "\"," + "\"Quantity\":\"" + this.mQuantity + "\",\"Dosage\":\"" + this.mDosage + "\",\"Form\":\"" + this.mForm + "\"}";
        String str2 = "card=" + URLEncoder.encode(str, "UTF-8") + ";rx_ecd=" + this.mIcd;
        Trace.d("Cookie", "cookie " + str);
        Trace.d("Cookie", "encoded cookie " + str2);
        return str2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/webmd/webmdrx/fragments/ShareSavingsFragment$Companion;", "", "()V", "SAVINGS_CARD_VIEW", "", "getSAVINGS_CARD_VIEW", "()I", "newInstance", "Lcom/webmd/webmdrx/fragments/ShareSavingsFragment;", "isShareViaEmail", "", "screenClickablity", "Lcom/webmd/webmdrx/fragments/ShareSavingsFragment$ScreenClickablity;", "drugContentID", "", "bundle", "Landroid/os/Bundle;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ShareSavingsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getSAVINGS_CARD_VIEW() {
            return ShareSavingsFragment.SAVINGS_CARD_VIEW;
        }

        public final ShareSavingsFragment newInstance(boolean z, ScreenClickablity screenClickablity, String str, Bundle bundle) {
            Intrinsics.checkNotNullParameter(screenClickablity, "screenClickablity");
            Intrinsics.checkNotNullParameter(str, "drugContentID");
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            ShareSavingsFragment shareSavingsFragment = new ShareSavingsFragment();
            bundle.putBoolean("isShareViaEmail", z);
            shareSavingsFragment.setArguments(bundle);
            shareSavingsFragment.mScreenClickability = screenClickablity;
            shareSavingsFragment.setLaunchFromSavingsCardView$wbmdrx_release(false);
            shareSavingsFragment.mDrugContentID = str;
            return shareSavingsFragment;
        }
    }
}
