package com.webmd.webmdrx.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import com.facebook.internal.NativeProtocol;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.manager.ApiManager;
import com.webmd.webmdrx.tasks.TaskRequestHelper;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.Util;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import webmd.com.environmentswitcher.EnvironmentManager;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 Z2\u00020\u0001:\u0001ZB\u0005¢\u0006\u0002\u0010\u0002J\b\u00106\u001a\u00020\u0004H\u0002J\b\u00107\u001a\u00020\u0004H\u0002J\b\u00108\u001a\u000209H\u0002J\u0006\u0010:\u001a\u000209J\u0012\u0010;\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010=H\u0014J\u0012\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u0010\u0010B\u001a\u00020?2\u0006\u0010C\u001a\u00020DH\u0016J-\u0010E\u001a\u0002092\u0006\u0010F\u001a\u00020G2\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040I2\u0006\u0010J\u001a\u00020KH\u0016¢\u0006\u0002\u0010LJ\b\u0010M\u001a\u000209H\u0014J\b\u0010N\u001a\u000209H\u0002J\b\u0010O\u001a\u000209H\u0002J\b\u0010P\u001a\u000209H\u0002J\u0016\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u00042\u0006\u0010T\u001a\u00020GJ+\u0010U\u001a\u000209*\u00020V2\u0019\b\u0004\u0010W\u001a\u0013\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u0002090X¢\u0006\u0002\bYH\bø\u0001\u0000R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8FX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020+X\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006["}, d2 = {"Lcom/webmd/webmdrx/activities/CouponActivity;", "Lcom/webmd/webmdrx/activities/RxBaseActivity;", "()V", "CVS_PHARMACY", "", "MAIL", "SMS", "WALMART_PHARMACY", "apiManager", "Lcom/webmd/webmdrx/manager/ApiManager;", "kotlin.jvm.PlatformType", "getApiManager", "()Lcom/webmd/webmdrx/manager/ApiManager;", "apiManager$delegate", "Lkotlin/Lazy;", "buttonFaq", "Landroid/widget/Button;", "cookieString", "couponAuth", "Landroid/widget/TextView;", "couponAuthWebmd", "couponBin", "couponBinWebmd", "couponContentLayout", "Landroidx/core/widget/NestedScrollView;", "couponDetailsLayout", "Landroid/widget/LinearLayout;", "couponDrugPriceWebmd", "couponGrp", "couponGrpWebmd", "couponImage", "Landroid/widget/ImageView;", "couponPcn", "couponPcnWebmd", "couponPharmacyWebmd", "cvsCouponLayout", "drugDetail", "drugDetailWebmd", "drugName", "drugNameWebmd", "mDosage", "mDrugName", "mDrugPackageSize", "", "mDrugPrice", "mForm", "mIcd", "mPharmacyName", "mProgressBar", "Landroid/widget/ProgressBar;", "mQuantity", "rxLegalDisclaimer", "singleCareText", "webMDCouponLayout", "buildDrugDetail", "buildEncodedCardCookie", "displayData", "", "fetchRxCardDetails", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "saveToGallery", "setupListeners", "setupViews", "styleStringText", "Landroid/text/SpannableStringBuilder;", "text", "start", "afterLayout", "Landroid/view/View;", "func", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Companion", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CouponActivity.kt */
public final class CouponActivity extends RxBaseActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int ICD_FROM_DRUG_MONOGRAPH = 2;
    public static final int ICD_FROM_RX_SEARCH = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 12;
    /* access modifiers changed from: private */
    public final String CVS_PHARMACY = "CVS Pharmacy";
    /* access modifiers changed from: private */
    public final String MAIL = "mail";
    /* access modifiers changed from: private */
    public final String SMS = "sms";
    /* access modifiers changed from: private */
    public final String WALMART_PHARMACY = "Walmart Pharmacy";
    private HashMap _$_findViewCache;
    private final Lazy apiManager$delegate = LazyKt.lazy(CouponActivity$apiManager$2.INSTANCE);
    private Button buttonFaq;
    /* access modifiers changed from: private */
    public String cookieString;
    /* access modifiers changed from: private */
    public TextView couponAuth;
    /* access modifiers changed from: private */
    public TextView couponAuthWebmd;
    /* access modifiers changed from: private */
    public TextView couponBin;
    /* access modifiers changed from: private */
    public TextView couponBinWebmd;
    private NestedScrollView couponContentLayout;
    /* access modifiers changed from: private */
    public LinearLayout couponDetailsLayout;
    /* access modifiers changed from: private */
    public TextView couponDrugPriceWebmd;
    /* access modifiers changed from: private */
    public TextView couponGrp;
    /* access modifiers changed from: private */
    public TextView couponGrpWebmd;
    /* access modifiers changed from: private */
    public ImageView couponImage;
    /* access modifiers changed from: private */
    public TextView couponPcn;
    /* access modifiers changed from: private */
    public TextView couponPcnWebmd;
    /* access modifiers changed from: private */
    public TextView couponPharmacyWebmd;
    private LinearLayout cvsCouponLayout;
    private TextView drugDetail;
    /* access modifiers changed from: private */
    public TextView drugDetailWebmd;
    private TextView drugName;
    /* access modifiers changed from: private */
    public TextView drugNameWebmd;
    /* access modifiers changed from: private */
    public String mDosage;
    /* access modifiers changed from: private */
    public String mDrugName;
    private double mDrugPackageSize;
    /* access modifiers changed from: private */
    public double mDrugPrice;
    /* access modifiers changed from: private */
    public String mForm;
    private String mIcd;
    /* access modifiers changed from: private */
    public String mPharmacyName;
    private ProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public double mQuantity;
    private TextView rxLegalDisclaimer;
    private TextView singleCareText;
    private LinearLayout webMDCouponLayout;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final ApiManager getApiManager() {
        return (ApiManager) this.apiManager$delegate.getValue();
    }

    public static final /* synthetic */ String access$getCookieString$p(CouponActivity couponActivity) {
        String str = couponActivity.cookieString;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cookieString");
        }
        return str;
    }

    public static final /* synthetic */ TextView access$getCouponAuth$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponAuth;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponAuth");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getCouponAuthWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponAuthWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponAuthWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getCouponBin$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponBin;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponBin");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getCouponBinWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponBinWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponBinWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ LinearLayout access$getCouponDetailsLayout$p(CouponActivity couponActivity) {
        LinearLayout linearLayout = couponActivity.couponDetailsLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponDetailsLayout");
        }
        return linearLayout;
    }

    public static final /* synthetic */ TextView access$getCouponDrugPriceWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponDrugPriceWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponDrugPriceWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getCouponGrp$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponGrp;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponGrp");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getCouponGrpWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponGrpWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponGrpWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ ImageView access$getCouponImage$p(CouponActivity couponActivity) {
        ImageView imageView = couponActivity.couponImage;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponImage");
        }
        return imageView;
    }

    public static final /* synthetic */ TextView access$getCouponPcn$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponPcn;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponPcn");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getCouponPcnWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponPcnWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponPcnWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getCouponPharmacyWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.couponPharmacyWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponPharmacyWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getDrugDetailWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.drugDetailWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugDetailWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ TextView access$getDrugNameWebmd$p(CouponActivity couponActivity) {
        TextView textView = couponActivity.drugNameWebmd;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugNameWebmd");
        }
        return textView;
    }

    public static final /* synthetic */ String access$getMDosage$p(CouponActivity couponActivity) {
        String str = couponActivity.mDosage;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDosage");
        }
        return str;
    }

    public static final /* synthetic */ String access$getMDrugName$p(CouponActivity couponActivity) {
        String str = couponActivity.mDrugName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDrugName");
        }
        return str;
    }

    public static final /* synthetic */ String access$getMForm$p(CouponActivity couponActivity) {
        String str = couponActivity.mForm;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mForm");
        }
        return str;
    }

    public static final /* synthetic */ String access$getMPharmacyName$p(CouponActivity couponActivity) {
        String str = couponActivity.mPharmacyName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPharmacyName");
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        String str3;
        String str4;
        super.onCreate(bundle);
        setContentView(R.layout.activity_coupon);
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.toolbar));
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.mQuantity = extras.getDouble(Constants.EXTRA_QUANTITY);
            String str5 = "";
            if (extras.getString(Constants.EXTRA_FORM) != null) {
                str = extras.getString(Constants.EXTRA_FORM);
                Intrinsics.checkNotNull(str);
                Intrinsics.checkNotNullExpressionValue(str, "bundle.getString(Constants.EXTRA_FORM)!!");
            } else {
                str = str5;
            }
            this.mForm = str;
            if (extras.getString(Constants.EXTRA_DOSAGE) != null) {
                str2 = extras.getString(Constants.EXTRA_DOSAGE);
                Intrinsics.checkNotNull(str2);
                Intrinsics.checkNotNullExpressionValue(str2, "bundle.getString(Constants.EXTRA_DOSAGE)!!");
            } else {
                str2 = str5;
            }
            this.mDosage = str2;
            if (extras.getString("extra_drug_name") != null) {
                str3 = extras.getString("extra_drug_name");
                Intrinsics.checkNotNull(str3);
                Intrinsics.checkNotNullExpressionValue(str3, "bundle.getString(Constants.EXTRA_DRUG_NAME)!!");
            } else {
                str3 = str5;
            }
            this.mDrugName = str3;
            if (extras.getString(Constants.EXTRA_PHARMACY_NAME) != null) {
                str4 = extras.getString(Constants.EXTRA_PHARMACY_NAME);
                Intrinsics.checkNotNull(str4);
                Intrinsics.checkNotNullExpressionValue(str4, "bundle.getString(Constants.EXTRA_PHARMACY_NAME)!!");
            } else {
                str4 = str5;
            }
            this.mPharmacyName = str4;
            this.mDrugPrice = extras.getDouble(Constants.EXTRA_DRUG_PRICE);
            this.mDrugPackageSize = extras.getDouble(Constants.EXTRA_PACKAGE_SIZE);
            if (extras.getString(Constants.EXTRA_ICD) != null) {
                str5 = extras.getString(Constants.EXTRA_ICD);
                Intrinsics.checkNotNull(str5);
                Intrinsics.checkNotNullExpressionValue(str5, "bundle.getString(Constants.EXTRA_ICD)!!");
            }
            this.mIcd = str5;
            this.mIcdDrugName = extras.getString(Constants.EXTRA_ICD_DRUG_NAME);
            this.mIcdDrugId = extras.getString(Constants.EXTRA_ICD_DRUG_ID);
        }
        setupViews();
        setupListeners();
        fetchRxCardDetails();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mRxOmnitureSender.sendPageView("drug-prices/showcard");
        Util.logFirebaseEvent(this, Constants.FIRE_BASE_RX_COUPON_SCREEN);
    }

    /* access modifiers changed from: private */
    public final void saveToGallery() {
        ViewGroup viewGroup;
        String str = this.mPharmacyName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPharmacyName");
        }
        if (!Intrinsics.areEqual((Object) str, (Object) this.CVS_PHARMACY)) {
            String str2 = this.mPharmacyName;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPharmacyName");
            }
            if (!Intrinsics.areEqual((Object) str2, (Object) this.WALMART_PHARMACY)) {
                viewGroup = (ViewGroup) findViewById(R.id.layout_content_coupon_webmd);
                Util.saveBitmapToDevice(getApplicationContext(), Util.getBitmapFromView(viewGroup));
                Toast.makeText(getApplicationContext(), "Saved to photos", 0).show();
            }
        }
        viewGroup = (ViewGroup) findViewById(R.id.coupon_frame_layout);
        Util.saveBitmapToDevice(getApplicationContext(), Util.getBitmapFromView(viewGroup));
        Toast.makeText(getApplicationContext(), "Saved to photos", 0).show();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(strArr, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 12) {
            if ((!(iArr.length == 0)) && iArr[0] == 0) {
                saveToGallery();
            }
        }
    }

    private final String buildDrugDetail() {
        StringBuilder sb = new StringBuilder();
        String str = this.mDosage;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDosage");
        }
        sb.append(str);
        sb.append(", ");
        sb.append(Integer.toString((int) this.mQuantity));
        sb.append(' ');
        String str2 = this.mForm;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mForm");
        }
        sb.append(str2);
        sb.append(" for $");
        sb.append(this.mDrugPrice);
        sb.append(" at ");
        String str3 = this.mPharmacyName;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPharmacyName");
        }
        sb.append(str3);
        return sb.toString();
    }

    private final String buildEncodedCardCookie() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"Pharmacy\":\"");
        String str = this.mPharmacyName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPharmacyName");
        }
        sb.append(str);
        sb.append("\",\"Price\":");
        sb.append(this.mDrugPrice);
        sb.append(",\"DrugName\":\"");
        String str2 = this.mDrugName;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDrugName");
        }
        sb.append(str2);
        sb.append("\",\"DrugForm\":\"");
        String str3 = this.mDosage;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDosage");
        }
        sb.append(str3);
        sb.append(' ');
        sb.append(this.mQuantity);
        sb.append(' ');
        String str4 = this.mForm;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mForm");
        }
        sb.append(str4);
        sb.append("\",");
        sb.append("\"Quantity\":\"");
        sb.append(this.mQuantity);
        sb.append("\",\"Dosage\":\"");
        String str5 = this.mDosage;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDosage");
        }
        sb.append(str5);
        sb.append("\",\"Form\":\"");
        String str6 = this.mForm;
        if (str6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mForm");
        }
        sb.append(str6);
        sb.append("\"}");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("card=");
        sb3.append(URLEncoder.encode(sb2, "UTF-8"));
        sb3.append(";?icd=");
        String str7 = this.mIcd;
        if (str7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mIcd");
        }
        sb3.append(str7);
        String sb4 = sb3.toString();
        Trace.d("Cookie", "cookie " + sb2);
        Trace.d("Cookie", "encoded cookie " + sb4);
        return sb4;
    }

    private final void setupViews() {
        View findViewById = findViewById(R.id.progress_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.progress_bar)");
        ProgressBar progressBar = (ProgressBar) findViewById;
        this.mProgressBar = progressBar;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressBar");
        }
        progressBar.setVisibility(0);
        View findViewById2 = findViewById(R.id.show_coupon_drug_name);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.show_coupon_drug_name)");
        this.drugName = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.show_coupon_drug_detail);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(R.id.show_coupon_drug_detail)");
        this.drugDetail = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.button_faq);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(R.id.button_faq)");
        this.buttonFaq = (Button) findViewById4;
        View findViewById5 = findViewById(R.id.single_care_text);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(R.id.single_care_text)");
        this.singleCareText = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.coupon_image);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(R.id.coupon_image)");
        this.couponImage = (ImageView) findViewById6;
        View findViewById7 = findViewById(R.id.coupon_auth_number);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(R.id.coupon_auth_number)");
        this.couponAuth = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.coupon_bin_number);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(R.id.coupon_bin_number)");
        this.couponBin = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.coupon_grp_number);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(R.id.coupon_grp_number)");
        this.couponGrp = (TextView) findViewById9;
        View findViewById10 = findViewById(R.id.coupon_pcn_number);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(R.id.coupon_pcn_number)");
        this.couponPcn = (TextView) findViewById10;
        View findViewById11 = findViewById(R.id.layout_content_coupon_cvs);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(R.id.layout_content_coupon_cvs)");
        this.cvsCouponLayout = (LinearLayout) findViewById11;
        View findViewById12 = findViewById(R.id.layout_content_coupon_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(R.id.layout_content_coupon_webmd)");
        this.webMDCouponLayout = (LinearLayout) findViewById12;
        View findViewById13 = findViewById(R.id.layout_content_coupon);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(R.id.layout_content_coupon)");
        NestedScrollView nestedScrollView = (NestedScrollView) findViewById13;
        this.couponContentLayout = nestedScrollView;
        if (nestedScrollView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("couponContentLayout");
        }
        nestedScrollView.setVisibility(8);
        View findViewById14 = findViewById(R.id.show_coupon_drug_name_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(R.id.show_coupon_drug_name_webmd)");
        this.drugNameWebmd = (TextView) findViewById14;
        View findViewById15 = findViewById(R.id.show_coupon_drug_detail_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(R.id.show_coupon_drug_detail_webmd)");
        this.drugDetailWebmd = (TextView) findViewById15;
        View findViewById16 = findViewById(R.id.coupon_auth_number_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(R.id.coupon_auth_number_webmd)");
        this.couponAuthWebmd = (TextView) findViewById16;
        View findViewById17 = findViewById(R.id.coupon_bin_number_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(R.id.coupon_bin_number_webmd)");
        this.couponBinWebmd = (TextView) findViewById17;
        View findViewById18 = findViewById(R.id.coupon_grp_number_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(R.id.coupon_grp_number_webmd)");
        this.couponGrpWebmd = (TextView) findViewById18;
        View findViewById19 = findViewById(R.id.coupon_pcn_number_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(R.id.coupon_pcn_number_webmd)");
        this.couponPcnWebmd = (TextView) findViewById19;
        View findViewById20 = findViewById(R.id.text_coupon_drug_price_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(R.id.text_coupon_drug_price_webmd)");
        this.couponDrugPriceWebmd = (TextView) findViewById20;
        View findViewById21 = findViewById(R.id.text_coupon_pharmacy_name_webmd);
        Intrinsics.checkNotNullExpressionValue(findViewById21, "findViewById(R.id.text_coupon_pharmacy_name_webmd)");
        this.couponPharmacyWebmd = (TextView) findViewById21;
        View findViewById22 = findViewById(R.id.layout_cvs_coupon_details);
        Intrinsics.checkNotNullExpressionValue(findViewById22, "findViewById(R.id.layout_cvs_coupon_details)");
        this.couponDetailsLayout = (LinearLayout) findViewById22;
        View findViewById23 = findViewById(R.id.rx_legal_disclaimer);
        Intrinsics.checkNotNullExpressionValue(findViewById23, "findViewById(R.id.rx_legal_disclaimer)");
        this.rxLegalDisclaimer = (TextView) findViewById23;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0103  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void displayData() {
        /*
            r8 = this;
            android.widget.TextView r0 = r8.drugName
            if (r0 != 0) goto L_0x0009
            java.lang.String r1 = "drugName"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0009:
            java.lang.String r1 = r8.mDrugName
            if (r1 != 0) goto L_0x0012
            java.lang.String r2 = "mDrugName"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x0012:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            android.widget.TextView r0 = r8.drugDetail
            if (r0 != 0) goto L_0x0020
            java.lang.String r1 = "drugDetail"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0020:
            java.lang.String r1 = r8.buildDrugDetail()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            android.widget.TextView r0 = r8.singleCareText
            if (r0 != 0) goto L_0x0032
            java.lang.String r1 = "singleCareText"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0032:
            java.lang.String r1 = r8.mPharmacyName
            java.lang.String r2 = "mPharmacyName"
            if (r1 != 0) goto L_0x003b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x003b:
            java.lang.String r3 = r8.CVS_PHARMACY
            boolean r1 = r1.equals(r3)
            r3 = 8
            r4 = 0
            if (r1 != 0) goto L_0x0059
            java.lang.String r1 = r8.mPharmacyName
            if (r1 != 0) goto L_0x004d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x004d:
            java.lang.String r5 = r8.WALMART_PHARMACY
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x0056
            goto L_0x0059
        L_0x0056:
            r1 = 8
            goto L_0x005a
        L_0x0059:
            r1 = 0
        L_0x005a:
            r0.setVisibility(r1)
            java.lang.String r0 = r8.mPharmacyName
            if (r0 != 0) goto L_0x0064
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x0064:
            java.lang.String r1 = r8.CVS_PHARMACY
            boolean r0 = r0.equals(r1)
            java.lang.String r1 = "cvsCouponLayout"
            java.lang.String r5 = "webMDCouponLayout"
            java.lang.String r6 = "couponImage"
            if (r0 == 0) goto L_0x009b
            android.widget.ImageView r0 = r8.couponImage
            if (r0 != 0) goto L_0x0079
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x0079:
            android.content.Context r2 = r8.getApplicationContext()
            int r7 = com.webmd.webmdrx.R.drawable.ic_cvs_card
            android.graphics.drawable.Drawable r2 = androidx.core.content.ContextCompat.getDrawable(r2, r7)
            r0.setImageDrawable(r2)
            android.widget.LinearLayout r0 = r8.webMDCouponLayout
            if (r0 != 0) goto L_0x008d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x008d:
            r0.setVisibility(r3)
            android.widget.LinearLayout r0 = r8.cvsCouponLayout
            if (r0 != 0) goto L_0x0097
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0097:
            r0.setVisibility(r4)
            goto L_0x00e7
        L_0x009b:
            java.lang.String r0 = r8.mPharmacyName
            if (r0 != 0) goto L_0x00a2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x00a2:
            java.lang.String r2 = r8.WALMART_PHARMACY
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00d3
            android.widget.ImageView r0 = r8.couponImage
            if (r0 != 0) goto L_0x00b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x00b1:
            android.content.Context r2 = r8.getApplicationContext()
            int r7 = com.webmd.webmdrx.R.drawable.ic_walmart_card
            android.graphics.drawable.Drawable r2 = androidx.core.content.ContextCompat.getDrawable(r2, r7)
            r0.setImageDrawable(r2)
            android.widget.LinearLayout r0 = r8.webMDCouponLayout
            if (r0 != 0) goto L_0x00c5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x00c5:
            r0.setVisibility(r3)
            android.widget.LinearLayout r0 = r8.cvsCouponLayout
            if (r0 != 0) goto L_0x00cf
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00cf:
            r0.setVisibility(r4)
            goto L_0x00e7
        L_0x00d3:
            android.widget.LinearLayout r0 = r8.cvsCouponLayout
            if (r0 != 0) goto L_0x00da
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00da:
            r0.setVisibility(r3)
            android.widget.LinearLayout r0 = r8.webMDCouponLayout
            if (r0 != 0) goto L_0x00e4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x00e4:
            r0.setVisibility(r4)
        L_0x00e7:
            android.widget.ProgressBar r0 = r8.mProgressBar
            if (r0 != 0) goto L_0x00f0
            java.lang.String r1 = "mProgressBar"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00f0:
            r0.setVisibility(r3)
            androidx.core.widget.NestedScrollView r0 = r8.couponContentLayout
            if (r0 != 0) goto L_0x00fc
            java.lang.String r1 = "couponContentLayout"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00fc:
            r0.setVisibility(r4)
            android.widget.ImageView r0 = r8.couponImage
            if (r0 != 0) goto L_0x0106
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x0106:
            android.view.View r0 = (android.view.View) r0
            android.view.ViewTreeObserver r1 = r0.getViewTreeObserver()
            com.webmd.webmdrx.activities.CouponActivity$displayData$$inlined$afterLayout$1 r2 = new com.webmd.webmdrx.activities.CouponActivity$displayData$$inlined$afterLayout$1
            r2.<init>(r0, r8)
            android.view.ViewTreeObserver$OnGlobalLayoutListener r2 = (android.view.ViewTreeObserver.OnGlobalLayoutListener) r2
            r1.addOnGlobalLayoutListener(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.activities.CouponActivity.displayData():void");
    }

    public final void afterLayout(View view, Function1<? super View, Unit> function1) {
        Intrinsics.checkNotNullParameter(view, "$this$afterLayout");
        Intrinsics.checkNotNullParameter(function1, "func");
        view.getViewTreeObserver().addOnGlobalLayoutListener(new CouponActivity$afterLayout$1(view, function1));
    }

    private final void setupListeners() {
        Button button = this.buttonFaq;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonFaq");
        }
        button.setOnClickListener(new CouponActivity$setupListeners$1(this));
        ((ImageView) findViewById(R.id.iv_save)).setOnClickListener(new CouponActivity$setupListeners$2(this));
        ((ImageView) findViewById(R.id.sendSms)).setOnClickListener(new CouponActivity$setupListeners$3(this));
        ((ImageView) findViewById(R.id.sendMail)).setOnClickListener(new CouponActivity$setupListeners$4(this));
        TextView textView = this.rxLegalDisclaimer;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rxLegalDisclaimer");
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/webmd/webmdrx/activities/CouponActivity$Companion;", "", "()V", "ICD_FROM_DRUG_MONOGRAPH", "", "ICD_FROM_RX_SEARCH", "MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: CouponActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coupon, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() != R.id.action_close) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public final void fetchRxCardDetails() {
        Context context = this;
        ApiManager.RetrofitServices provideRetrofitService = ApiManager.getInstance().provideRetrofitService(context);
        if (provideRetrofitService != null) {
            HashMap hashMap = new HashMap();
            EnvironmentManager instance = EnvironmentManager.getInstance(context);
            long currentTimeMillis = System.currentTimeMillis();
            Intrinsics.checkNotNullExpressionValue(instance, "environmentManager");
            String searchClientId = instance.getSearchClientId();
            String clientSecretHashForTimestamp = TaskRequestHelper.getClientSecretHashForTimestamp(currentTimeMillis, instance.getSearchSecretId(), searchClientId);
            Map map = hashMap;
            Intrinsics.checkNotNullExpressionValue(clientSecretHashForTimestamp, "secretHash");
            CharSequence charSequence = clientSecretHashForTimestamp;
            int length = charSequence.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) charSequence.charAt(!z ? i : length), 32) <= 0;
                if (!z) {
                    if (!z2) {
                        z = true;
                    } else {
                        i++;
                    }
                } else if (!z2) {
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
            String buildEncodedCardCookie = buildEncodedCardCookie();
            this.cookieString = buildEncodedCardCookie;
            if (buildEncodedCardCookie == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cookieString");
            }
            this.cookieString = StringsKt.replace$default(StringsKt.replace$default(buildEncodedCardCookie, "+", "%20", false, 4, (Object) null), ",", "", false, 4, (Object) null);
            StringBuilder sb = new StringBuilder();
            sb.append("cookieString ");
            String str = this.cookieString;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cookieString");
            }
            sb.append(str);
            Trace.d("Cookie", sb.toString());
            String str2 = this.cookieString;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cookieString");
            }
            map.put("cookie", str2);
            provideRetrofitService.rxCard(map).enqueue(new CouponActivity$fetchRxCardDetails$2(this));
        }
    }

    public final SpannableStringBuilder styleStringText(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "text");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new StyleSpan(1), i, str.length(), 33);
        return spannableStringBuilder;
    }
}
