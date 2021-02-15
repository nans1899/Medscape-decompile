package com.medscape.android.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.slideshow.SlideshowUtil;
import com.medscape.android.slideshow.SlideshowViewer;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u0012\u0010D\u001a\u00020A2\b\u0010E\u001a\u0004\u0018\u00010FH\u0014J\b\u0010G\u001a\u00020AH\u0014J\b\u0010H\u001a\u00020AH\u0014J\u0006\u0010I\u001a\u00020AJ\u001e\u0010J\u001a\u00020A2\u0006\u0010K\u001a\u00020\u001a2\u0006\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\u001aJ\u0006\u0010N\u001a\u00020AR\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u00107\u001a\u000202X.¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00104\"\u0004\b9\u00106R\u001a\u0010:\u001a\u00020;X.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006O"}, d2 = {"Lcom/medscape/android/activity/PromoDebugActivity;", "Lcom/medscape/android/base/NavigableBaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "btnLaunch", "Landroid/widget/Button;", "getBtnLaunch", "()Landroid/widget/Button;", "setBtnLaunch", "(Landroid/widget/Button;)V", "btnRestore", "getBtnRestore", "setBtnRestore", "demoType", "", "getDemoType", "()I", "setDemoType", "(I)V", "orientationLayout", "Landroid/widget/RelativeLayout;", "getOrientationLayout", "()Landroid/widget/RelativeLayout;", "setOrientationLayout", "(Landroid/widget/RelativeLayout;)V", "orientationLock", "", "getOrientationLock", "()Ljava/lang/String;", "setOrientationLock", "(Ljava/lang/String;)V", "orientationRdoGroup", "Landroid/widget/RadioGroup;", "getOrientationRdoGroup", "()Landroid/widget/RadioGroup;", "setOrientationRdoGroup", "(Landroid/widget/RadioGroup;)V", "promoTypesSpinner", "Landroid/widget/Spinner;", "getPromoTypesSpinner", "()Landroid/widget/Spinner;", "setPromoTypesSpinner", "(Landroid/widget/Spinner;)V", "promoUrl", "Landroid/widget/EditText;", "getPromoUrl", "()Landroid/widget/EditText;", "setPromoUrl", "(Landroid/widget/EditText;)V", "rdoLandscape", "Landroid/widget/RadioButton;", "getRdoLandscape", "()Landroid/widget/RadioButton;", "setRdoLandscape", "(Landroid/widget/RadioButton;)V", "rdoPortrait", "getRdoPortrait", "setRdoPortrait", "urlType", "Landroid/widget/TextView;", "getUrlType", "()Landroid/widget/TextView;", "setUrlType", "(Landroid/widget/TextView;)V", "onClick", "", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupActionBar", "setupSpinner", "setupSpinnerSelection", "promoUrlType", "position", "demoUrl", "setupViews", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PromoDebugActivity.kt */
public final class PromoDebugActivity extends NavigableBaseActivity implements View.OnClickListener {
    private HashMap _$_findViewCache;
    public Button btnLaunch;
    public Button btnRestore;
    private int demoType;
    public RelativeLayout orientationLayout;
    private String orientationLock;
    public RadioGroup orientationRdoGroup;
    public Spinner promoTypesSpinner;
    public EditText promoUrl;
    public RadioButton rdoLandscape;
    public RadioButton rdoPortrait;
    public TextView urlType;

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

    public final Spinner getPromoTypesSpinner() {
        Spinner spinner = this.promoTypesSpinner;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
        }
        return spinner;
    }

    public final void setPromoTypesSpinner(Spinner spinner) {
        Intrinsics.checkNotNullParameter(spinner, "<set-?>");
        this.promoTypesSpinner = spinner;
    }

    public final TextView getUrlType() {
        TextView textView = this.urlType;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("urlType");
        }
        return textView;
    }

    public final void setUrlType(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.urlType = textView;
    }

    public final EditText getPromoUrl() {
        EditText editText = this.promoUrl;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
        }
        return editText;
    }

    public final void setPromoUrl(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<set-?>");
        this.promoUrl = editText;
    }

    public final RelativeLayout getOrientationLayout() {
        RelativeLayout relativeLayout = this.orientationLayout;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationLayout");
        }
        return relativeLayout;
    }

    public final void setOrientationLayout(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.orientationLayout = relativeLayout;
    }

    public final RadioButton getRdoPortrait() {
        RadioButton radioButton = this.rdoPortrait;
        if (radioButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rdoPortrait");
        }
        return radioButton;
    }

    public final void setRdoPortrait(RadioButton radioButton) {
        Intrinsics.checkNotNullParameter(radioButton, "<set-?>");
        this.rdoPortrait = radioButton;
    }

    public final RadioButton getRdoLandscape() {
        RadioButton radioButton = this.rdoLandscape;
        if (radioButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rdoLandscape");
        }
        return radioButton;
    }

    public final void setRdoLandscape(RadioButton radioButton) {
        Intrinsics.checkNotNullParameter(radioButton, "<set-?>");
        this.rdoLandscape = radioButton;
    }

    public final RadioGroup getOrientationRdoGroup() {
        RadioGroup radioGroup = this.orientationRdoGroup;
        if (radioGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationRdoGroup");
        }
        return radioGroup;
    }

    public final void setOrientationRdoGroup(RadioGroup radioGroup) {
        Intrinsics.checkNotNullParameter(radioGroup, "<set-?>");
        this.orientationRdoGroup = radioGroup;
    }

    public final Button getBtnLaunch() {
        Button button = this.btnLaunch;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
        }
        return button;
    }

    public final void setBtnLaunch(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btnLaunch = button;
    }

    public final Button getBtnRestore() {
        Button button = this.btnRestore;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
        }
        return button;
    }

    public final void setBtnRestore(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btnRestore = button;
    }

    public final int getDemoType() {
        return this.demoType;
    }

    public final void setDemoType(int i) {
        this.demoType = i;
    }

    public final String getOrientationLock() {
        return this.orientationLock;
    }

    public final void setOrientationLock(String str) {
        this.orientationLock = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_promo_debug);
        setupViews();
        setupSpinner();
        EditText editText = this.promoUrl;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
        }
        editText.addTextChangedListener(new PromoDebugActivity$onCreate$1(this));
        RadioGroup radioGroup = this.orientationRdoGroup;
        if (radioGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationRdoGroup");
        }
        radioGroup.setOnCheckedChangeListener(new PromoDebugActivity$onCreate$2(this));
        Button button = this.btnLaunch;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
        }
        View.OnClickListener onClickListener = this;
        button.setOnClickListener(onClickListener);
        Button button2 = this.btnRestore;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
        }
        button2.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MedscapeApplication medscapeApplication = MedscapeApplication.get();
        Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
        int i = medscapeApplication.getPreferences().getInt(Constants.PREF_DEBUG_SLIDE_DEMO_MODE, 0);
        MedscapeApplication medscapeApplication2 = MedscapeApplication.get();
        Intrinsics.checkNotNullExpressionValue(medscapeApplication2, "MedscapeApplication.get()");
        String string = medscapeApplication2.getPreferences().getString(Constants.PREF_DEBUG_SLIDE_DEMO_URL, "");
        if (i == 1) {
            Spinner spinner = this.promoTypesSpinner;
            if (spinner == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
            }
            spinner.setSelection(1);
            if (string != null) {
                String string2 = getResources().getString(R.string.manifest_url);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.manifest_url)");
                setupSpinnerSelection(string2, 1, string);
            }
            RelativeLayout relativeLayout = this.orientationLayout;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationLayout");
            }
            relativeLayout.setVisibility(0);
        } else if (i == 2) {
            Spinner spinner2 = this.promoTypesSpinner;
            if (spinner2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
            }
            spinner2.setSelection(2);
            if (string != null) {
                String string3 = getResources().getString(R.string.promo_url);
                Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.promo_url)");
                setupSpinnerSelection(string3, 2, string);
            }
            RelativeLayout relativeLayout2 = this.orientationLayout;
            if (relativeLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationLayout");
            }
            relativeLayout2.setVisibility(0);
        } else if (i == 3) {
            Spinner spinner3 = this.promoTypesSpinner;
            if (spinner3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
            }
            spinner3.setSelection(3);
            if (string != null) {
                String string4 = getResources().getString(R.string.manifest_url);
                Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.manifest_url)");
                setupSpinnerSelection(string4, 3, string);
            }
            RelativeLayout relativeLayout3 = this.orientationLayout;
            if (relativeLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationLayout");
            }
            relativeLayout3.setVisibility(0);
        } else if (i != 4) {
            Spinner spinner4 = this.promoTypesSpinner;
            if (spinner4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
            }
            spinner4.setSelection(0);
            TextView textView = this.urlType;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("urlType");
            }
            textView.setVisibility(8);
            EditText editText = this.promoUrl;
            if (editText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
            }
            editText.setVisibility(8);
            RelativeLayout relativeLayout4 = this.orientationLayout;
            if (relativeLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationLayout");
            }
            relativeLayout4.setVisibility(8);
            Button button = this.btnLaunch;
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button.setEnabled(false);
            Button button2 = this.btnLaunch;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button2.setClickable(false);
        } else {
            Spinner spinner5 = this.promoTypesSpinner;
            if (spinner5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
            }
            spinner5.setSelection(4);
            if (string != null) {
                String string5 = getResources().getString(R.string.promo_url);
                Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.promo_url)");
                setupSpinnerSelection(string5, 4, string);
            }
            RelativeLayout relativeLayout5 = this.orientationLayout;
            if (relativeLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationLayout");
            }
            relativeLayout5.setVisibility(0);
        }
        MedscapeApplication medscapeApplication3 = MedscapeApplication.get();
        Intrinsics.checkNotNullExpressionValue(medscapeApplication3, "MedscapeApplication.get()");
        String string6 = medscapeApplication3.getPreferences().getString(Constants.PREF_DEBUG_SLIDE_DEMO_ORIENTATION, (String) null);
        if (string6 != null) {
            String string7 = getResources().getString(R.string.orientation_portrait);
            Intrinsics.checkNotNullExpressionValue(string7, "resources.getString(R.string.orientation_portrait)");
            if (string7 != null) {
                String lowerCase = string7.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
                if (Intrinsics.areEqual((Object) string6, (Object) lowerCase)) {
                    RadioButton radioButton = this.rdoPortrait;
                    if (radioButton == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rdoPortrait");
                    }
                    radioButton.setChecked(true);
                    RadioButton radioButton2 = this.rdoLandscape;
                    if (radioButton2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("rdoLandscape");
                    }
                    radioButton2.setChecked(false);
                    return;
                }
                RadioButton radioButton3 = this.rdoPortrait;
                if (radioButton3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rdoPortrait");
                }
                radioButton3.setChecked(false);
                RadioButton radioButton4 = this.rdoLandscape;
                if (radioButton4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rdoLandscape");
                }
                radioButton4.setChecked(true);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
    }

    public void onClick(View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == R.id.btn_launch) {
            EditText editText = this.promoUrl;
            if (editText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
            }
            String obj = editText.getText().toString();
            String str = obj + SlideshowUtil.toAppend(obj);
            int i = this.demoType;
            if (i == 1 || i == 3) {
                Intent intent = new Intent(this, SlideshowViewer.class);
                intent.putExtra("slideshowUrl", str);
                if (this.demoType == 3) {
                    Boolean bool = Boolean.TRUE;
                    Intrinsics.checkNotNullExpressionValue(bool, "java.lang.Boolean.TRUE");
                    intent.putExtra("isBrandPlay", bool.booleanValue());
                }
                String str2 = this.orientationLock;
                if (str2 != null) {
                    intent.putExtra("orientationLock", str2);
                }
                startActivity(intent);
            }
            if (this.demoType == 2) {
                WebviewUtil.Companion.launchShareableWebView(this, str, "", "", "", "", "", 1);
            }
            if (this.demoType == 4) {
                WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this, str, "", "", "", "", "", 1, false, 256, (Object) null);
            }
            MedscapeApplication medscapeApplication = MedscapeApplication.get();
            Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
            medscapeApplication.getPreferences().edit().putString(Constants.PREF_DEBUG_SLIDE_DEMO_URL, obj).commit();
            MedscapeApplication medscapeApplication2 = MedscapeApplication.get();
            Intrinsics.checkNotNullExpressionValue(medscapeApplication2, "MedscapeApplication.get()");
            medscapeApplication2.getPreferences().edit().putInt(Constants.PREF_DEBUG_SLIDE_DEMO_MODE, this.demoType).commit();
            MedscapeApplication medscapeApplication3 = MedscapeApplication.get();
            Intrinsics.checkNotNullExpressionValue(medscapeApplication3, "MedscapeApplication.get()");
            medscapeApplication3.getPreferences().edit().putString(Constants.PREF_DEBUG_SLIDE_DEMO_ORIENTATION, this.orientationLock).commit();
        } else if (valueOf != null && valueOf.intValue() == R.id.btn_default) {
            int i2 = this.demoType;
            if (i2 == 1) {
                EditText editText2 = this.promoUrl;
                if (editText2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
                }
                editText2.setText(Constants.PROMO_SLIDESHOW_DEFAULT_URL);
            } else if (i2 == 2) {
                EditText editText3 = this.promoUrl;
                if (editText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
                }
                editText3.setText(Constants.PROMO_SLIDESHOW_2_0_DEFAULT_URL);
            } else if (i2 == 3) {
                EditText editText4 = this.promoUrl;
                if (editText4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
                }
                editText4.setText(Constants.PROMO_BRANDPLAY_DEFAULT_URL);
            } else if (i2 == 4) {
                EditText editText5 = this.promoUrl;
                if (editText5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
                }
                editText5.setText("");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
            }
            ActionBar supportActionBar2 = getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
            }
            ActionBar supportActionBar3 = getSupportActionBar();
            if (supportActionBar3 != null) {
                supportActionBar3.setLogo((Drawable) null);
            }
            ActionBar supportActionBar4 = getSupportActionBar();
            if (supportActionBar4 != null) {
                supportActionBar4.setDisplayHomeAsUpEnabled(true);
            }
            ActionBar supportActionBar5 = getSupportActionBar();
            if (supportActionBar5 != null) {
                supportActionBar5.setDisplayShowTitleEnabled(true);
            }
            ActionBar supportActionBar6 = getSupportActionBar();
            if (supportActionBar6 != null) {
                supportActionBar6.setDisplayUseLogoEnabled(false);
            }
            ActionBar supportActionBar7 = getSupportActionBar();
            if (supportActionBar7 != null) {
                supportActionBar7.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.debug_promo_section_header) + "</font>"));
            }
        }
    }

    public final void setupViews() {
        View findViewById = findViewById(R.id.promo_types);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.promo_types)");
        this.promoTypesSpinner = (Spinner) findViewById;
        View findViewById2 = findViewById(R.id.txt_url);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.txt_url)");
        this.urlType = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.promo_url);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(R.id.promo_url)");
        this.promoUrl = (EditText) findViewById3;
        View findViewById4 = findViewById(R.id.orientation_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(R.id.orientation_layout)");
        this.orientationLayout = (RelativeLayout) findViewById4;
        View findViewById5 = findViewById(R.id.rdo_portrait);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(R.id.rdo_portrait)");
        this.rdoPortrait = (RadioButton) findViewById5;
        View findViewById6 = findViewById(R.id.rdo_landscape);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(R.id.rdo_landscape)");
        this.rdoLandscape = (RadioButton) findViewById6;
        View findViewById7 = findViewById(R.id.radio_group);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(R.id.radio_group)");
        this.orientationRdoGroup = (RadioGroup) findViewById7;
        View findViewById8 = findViewById(R.id.btn_launch);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(R.id.btn_launch)");
        this.btnLaunch = (Button) findViewById8;
        View findViewById9 = findViewById(R.id.btn_default);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(R.id.btn_default)");
        this.btnRestore = (Button) findViewById9;
    }

    public final void setupSpinner() {
        String[] stringArray = getResources().getStringArray(R.array.promo_types);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.promo_types)");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.demo_type_spinner_adapter, stringArray);
        Spinner spinner = this.promoTypesSpinner;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
        }
        spinner.setAdapter(arrayAdapter);
        Spinner spinner2 = this.promoTypesSpinner;
        if (spinner2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promoTypesSpinner");
        }
        if (spinner2 != null) {
            spinner2.setOnItemSelectedListener(new PromoDebugActivity$setupSpinner$1(this));
        }
    }

    public final void setupSpinnerSelection(String str, int i, String str2) {
        Intrinsics.checkNotNullParameter(str, "promoUrlType");
        Intrinsics.checkNotNullParameter(str2, "demoUrl");
        TextView textView = this.urlType;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("urlType");
        }
        textView.setVisibility(0);
        TextView textView2 = this.urlType;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("urlType");
        }
        textView2.setText(str);
        EditText editText = this.promoUrl;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
        }
        editText.setText(str2);
        if (i == 1) {
            Button button = this.btnLaunch;
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button.setEnabled(true);
            Button button2 = this.btnLaunch;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button2.setClickable(true);
            Button button3 = this.btnRestore;
            if (button3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
            }
            button3.setEnabled(true);
            Button button4 = this.btnRestore;
            if (button4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
            }
            button4.setClickable(true);
        } else if (i == 2) {
            Button button5 = this.btnLaunch;
            if (button5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button5.setEnabled(true);
            Button button6 = this.btnLaunch;
            if (button6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button6.setClickable(true);
            Button button7 = this.btnRestore;
            if (button7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
            }
            button7.setEnabled(true);
            Button button8 = this.btnRestore;
            if (button8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
            }
            button8.setClickable(true);
        } else if (i == 3) {
            Button button9 = this.btnLaunch;
            if (button9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button9.setEnabled(true);
            Button button10 = this.btnLaunch;
            if (button10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnLaunch");
            }
            button10.setClickable(true);
            Button button11 = this.btnRestore;
            if (button11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
            }
            button11.setEnabled(true);
            Button button12 = this.btnRestore;
            if (button12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnRestore");
            }
            button12.setClickable(true);
        } else if (i == 4) {
            EditText editText2 = this.promoUrl;
            if (editText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
            }
            editText2.setText("");
        }
        EditText editText3 = this.promoUrl;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promoUrl");
        }
        editText3.setVisibility(0);
    }
}
