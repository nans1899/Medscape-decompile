package com.webmd.wbmdproffesionalauthentication.license;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.webmd.wbmdproffesionalauthentication.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010;\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010<\u001a\u00020\u0006J\u000e\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0006J\u0010\u0010@\u001a\u00020>2\b\u0010A\u001a\u0004\u0018\u00010\u0012J\u0010\u0010B\u001a\u00020>2\b\u0010C\u001a\u0004\u0018\u00010\u0012J\u000e\u0010D\u001a\u00020>2\u0006\u0010E\u001a\u00020\u0006R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u001a\u0010 \u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR\u001a\u0010#\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\b\"\u0004\b$\u0010\nR\u001a\u0010%\u001a\u00020&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0014\"\u0004\b-\u0010\u0016R\u001a\u0010.\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\b\"\u0004\b0\u0010\nR\u001a\u00101\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\b\"\u0004\b3\u0010\nR\u001a\u00104\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\b\"\u0004\b6\u0010\nR\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u001208¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006F"}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/license/LicenseViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "bypassValidation", "", "getBypassValidation", "()Z", "setBypassValidation", "(Z)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "countryCode", "", "getCountryCode", "()Ljava/lang/String;", "setCountryCode", "(Ljava/lang/String;)V", "environment", "getEnvironment", "setEnvironment", "fieldHint", "getFieldHint", "setFieldHint", "fieldLabel", "getFieldLabel", "setFieldLabel", "floatingHintEnabled", "getFloatingHintEnabled", "setFloatingHintEnabled", "isRegisterButtonActive", "setRegisterButtonActive", "licencesValidator", "Lcom/webmd/wbmdproffesionalauthentication/license/LicencesValidator;", "getLicencesValidator", "()Lcom/webmd/wbmdproffesionalauthentication/license/LicencesValidator;", "setLicencesValidator", "(Lcom/webmd/wbmdproffesionalauthentication/license/LicencesValidator;)V", "licenseNumber", "getLicenseNumber", "setLicenseNumber", "shouldContinueWithRegistration", "getShouldContinueWithRegistration", "setShouldContinueWithRegistration", "shouldValidate", "getShouldValidate", "setShouldValidate", "showToggle", "getShowToggle", "setShowToggle", "topNonUs", "", "getTopNonUs", "()Ljava/util/List;", "getFieldLabelByCountry", "isLicenseValid", "noLicenseChecked", "", "flag", "setCountry", "cntryCode", "setLicense", "number", "validateLicence", "fromRegistration", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LicenseViewModel.kt */
public final class LicenseViewModel extends AndroidViewModel {
    private boolean bypassValidation;
    private Context context;
    private String countryCode = "";
    private String environment = "";
    private String fieldHint = "";
    private String fieldLabel = "";
    private boolean floatingHintEnabled = true;
    private boolean isRegisterButtonActive;
    private LicencesValidator licencesValidator = new LicencesValidator();
    private String licenseNumber = "";
    private boolean shouldContinueWithRegistration;
    private boolean shouldValidate = true;
    private boolean showToggle = true;
    private final List<String> topNonUs = CollectionsKt.mutableListOf("ca", "br", "fr", "de", "it", "mx", "es", "gb");

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LicenseViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        this.context = applicationContext;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final String getFieldLabel() {
        return this.fieldLabel;
    }

    public final void setFieldLabel(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fieldLabel = str;
    }

    public final String getFieldHint() {
        return this.fieldHint;
    }

    public final void setFieldHint(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fieldHint = str;
    }

    public final boolean getShowToggle() {
        return this.showToggle;
    }

    public final void setShowToggle(boolean z) {
        this.showToggle = z;
    }

    public final List<String> getTopNonUs() {
        return this.topNonUs;
    }

    public final boolean getFloatingHintEnabled() {
        return this.floatingHintEnabled;
    }

    public final void setFloatingHintEnabled(boolean z) {
        this.floatingHintEnabled = z;
    }

    public final boolean isRegisterButtonActive() {
        return this.isRegisterButtonActive;
    }

    public final void setRegisterButtonActive(boolean z) {
        this.isRegisterButtonActive = z;
    }

    public final String getLicenseNumber() {
        return this.licenseNumber;
    }

    public final void setLicenseNumber(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.licenseNumber = str;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final void setCountryCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final String getEnvironment() {
        return this.environment;
    }

    public final void setEnvironment(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.environment = str;
    }

    public final LicencesValidator getLicencesValidator() {
        return this.licencesValidator;
    }

    public final void setLicencesValidator(LicencesValidator licencesValidator2) {
        Intrinsics.checkNotNullParameter(licencesValidator2, "<set-?>");
        this.licencesValidator = licencesValidator2;
    }

    public final boolean getBypassValidation() {
        return this.bypassValidation;
    }

    public final void setBypassValidation(boolean z) {
        this.bypassValidation = z;
    }

    public final void setCountry(String str) {
        if (str == null) {
            str = "";
        }
        this.countryCode = str;
        if (Intrinsics.areEqual((Object) str, (Object) "us")) {
            this.fieldHint = getFieldLabelByCountry("us");
            this.showToggle = false;
            this.isRegisterButtonActive = true;
        } else if (this.topNonUs.contains(str)) {
            this.fieldHint = getFieldLabelByCountry(str);
        } else {
            this.fieldLabel = getFieldLabelByCountry(str);
            String string = this.context.getString(R.string.sign_up_license_hint_nontop);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…n_up_license_hint_nontop)");
            this.fieldHint = string;
            this.floatingHintEnabled = false;
        }
    }

    public final String getFieldLabelByCountry(String str) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE);
        int hashCode = str.hashCode();
        if (hashCode != 3152) {
            if (hashCode != 3166) {
                if (hashCode != 3201) {
                    if (hashCode != 3246) {
                        if (hashCode != 3276) {
                            if (hashCode != 3291) {
                                if (hashCode != 3371) {
                                    if (hashCode != 3499) {
                                        if (hashCode == 3742 && str.equals("us")) {
                                            String string = this.context.getString(R.string.sign_up_license_title_us);
                                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…sign_up_license_title_us)");
                                            return string;
                                        }
                                    } else if (str.equals("mx")) {
                                        String string2 = this.context.getString(R.string.sign_up_license_title_mexico);
                                        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…_up_license_title_mexico)");
                                        return string2;
                                    }
                                } else if (str.equals("it")) {
                                    String string3 = this.context.getString(R.string.sign_up_license_title_italy);
                                    Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…n_up_license_title_italy)");
                                    return string3;
                                }
                            } else if (str.equals("gb")) {
                                String string4 = this.context.getString(R.string.sign_up_license_title_uk);
                                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri…sign_up_license_title_uk)");
                                return string4;
                            }
                        } else if (str.equals("fr")) {
                            String string5 = this.context.getString(R.string.sign_up_license_title_france);
                            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.stri…_up_license_title_france)");
                            return string5;
                        }
                    } else if (str.equals("es")) {
                        String string6 = this.context.getString(R.string.sign_up_license_title_spain);
                        Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.stri…n_up_license_title_spain)");
                        return string6;
                    }
                } else if (str.equals("de")) {
                    String string7 = this.context.getString(R.string.sign_up_license_title_germany);
                    Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.stri…up_license_title_germany)");
                    return string7;
                }
            } else if (str.equals("ca")) {
                String string8 = this.context.getString(R.string.sign_up_license_title_ca);
                Intrinsics.checkNotNullExpressionValue(string8, "context.getString(R.stri…sign_up_license_title_ca)");
                return string8;
            }
        } else if (str.equals("br")) {
            String string9 = this.context.getString(R.string.sign_up_license_title_brazil);
            Intrinsics.checkNotNullExpressionValue(string9, "context.getString(R.stri…_up_license_title_brazil)");
            return string9;
        }
        String string10 = this.context.getString(R.string.sign_up_license_title_nontop);
        Intrinsics.checkNotNullExpressionValue(string10, "context.getString(R.stri…_up_license_title_nontop)");
        return string10;
    }

    public final boolean getShouldContinueWithRegistration() {
        return this.shouldContinueWithRegistration;
    }

    public final void setShouldContinueWithRegistration(boolean z) {
        this.shouldContinueWithRegistration = z;
    }

    public final boolean getShouldValidate() {
        return this.shouldValidate;
    }

    public final void setShouldValidate(boolean z) {
        this.shouldValidate = z;
    }

    public final void setLicense(String str) {
        this.licenseNumber = str != null ? str : "";
        if (!this.countryCode.equals("us")) {
            CharSequence charSequence = str;
            this.isRegisterButtonActive = !(charSequence == null || StringsKt.isBlank(charSequence));
        }
        if (StringsKt.isBlank(this.licenseNumber)) {
            this.licencesValidator.getValidationResult().setValue(new LicenseResponse(1, "", 0, false));
        }
        this.shouldValidate = !StringsKt.isBlank(this.licenseNumber);
    }

    public final void noLicenseChecked(boolean z) {
        this.isRegisterButtonActive = z;
    }

    public final void validateLicence(boolean z) {
        if (this.shouldValidate) {
            this.shouldContinueWithRegistration = z;
            this.licencesValidator.makeValidationRequest(this.environment, this.countryCode, this.licenseNumber);
            return;
        }
        this.licencesValidator.getValidationResult().setValue(new LicenseResponse(1, "", 1, true));
    }

    public final boolean isLicenseValid() {
        if (this.countryCode.equals("us") && StringsKt.isBlank(this.licenseNumber)) {
            return true;
        }
        LicenseResponse value = this.licencesValidator.getValidationResult().getValue();
        if (value != null) {
            return value.isValid();
        }
        return false;
    }
}
