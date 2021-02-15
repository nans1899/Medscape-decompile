package com.epapyrus.plugpdf.core;

public class LicenseInfo {
    private int mDay;
    private int mMonth;
    private ProductVersion mProductVersion;
    private VerificationResult mVerificationResult;
    private int mYear;

    public enum ProductVersion {
        VER_KR_RETAIL,
        VER_US_RETAIL,
        VER_KR_TRIAL,
        VER_US_TRIAL,
        VER_KR_PERSONAL,
        VER_US_PERSONAL,
        VER_KR_FREE,
        VER_UNLIMITED,
        VER_CROSS_UNLIMITED,
        VER_WRONG
    }

    public enum VerificationResult {
        NONE,
        OKAY,
        INVALID,
        WRONG_PRODUCT_VERSION,
        TRIAL_TIME_OUT,
        UNUSABLE_SDK_VERSION,
        UNUSABLE_OS,
        MISMATCH_APP_ID
    }

    public LicenseInfo(int i, int i2, int i3, int i4, int i5) {
        this.mVerificationResult = translateVerifiactionResult(i);
        this.mProductVersion = translateProductVersion(i2);
        this.mYear = i3;
        this.mMonth = i4;
        this.mDay = i5;
    }

    public VerificationResult getVerificationResult() {
        return this.mVerificationResult;
    }

    public int getYear() {
        return this.mYear;
    }

    public int getMonth() {
        return this.mMonth;
    }

    public int getDay() {
        return this.mDay;
    }

    public ProductVersion getProductVersion() {
        return this.mProductVersion;
    }

    private VerificationResult translateVerifiactionResult(int i) {
        switch (i) {
            case -6:
                return VerificationResult.MISMATCH_APP_ID;
            case -5:
                return VerificationResult.UNUSABLE_OS;
            case -4:
                return VerificationResult.UNUSABLE_SDK_VERSION;
            case -3:
                return VerificationResult.TRIAL_TIME_OUT;
            case -2:
                return VerificationResult.WRONG_PRODUCT_VERSION;
            case -1:
                return VerificationResult.INVALID;
            case 0:
                return VerificationResult.OKAY;
            default:
                return VerificationResult.NONE;
        }
    }

    private ProductVersion translateProductVersion(int i) {
        if (i == 0) {
            return ProductVersion.VER_KR_RETAIL;
        }
        if (i == 16) {
            return ProductVersion.VER_US_RETAIL;
        }
        if (i == 32) {
            return ProductVersion.VER_KR_TRIAL;
        }
        if (i == 64) {
            return ProductVersion.VER_US_TRIAL;
        }
        if (i == 76) {
            return ProductVersion.VER_KR_PERSONAL;
        }
        if (i == 84) {
            return ProductVersion.VER_US_PERSONAL;
        }
        switch (i) {
            case 96:
                return ProductVersion.VER_KR_FREE;
            case 97:
                return ProductVersion.VER_UNLIMITED;
            case 98:
                return ProductVersion.VER_CROSS_UNLIMITED;
            default:
                return ProductVersion.VER_WRONG;
        }
    }
}
