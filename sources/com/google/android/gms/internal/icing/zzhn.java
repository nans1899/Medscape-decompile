package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzhn implements zzhk {
    private static final zzbq<Boolean> zzrg;
    private static final zzbq<Boolean> zzrh;
    private static final zzbq<Boolean> zzri;
    private static final zzbq<Boolean> zzrj;
    private static final zzbq<Boolean> zzrk;
    private static final zzbq<Boolean> zzrl;
    private static final zzbq<Boolean> zzrm;
    private static final zzbq<Boolean> zzrn;
    private static final zzbq<Boolean> zzro;
    private static final zzbq<Boolean> zzrp;
    private static final zzbq<Boolean> zzrq;

    public final boolean zzeb() {
        return zzrj.get().booleanValue();
    }

    static {
        zzbu zzbu = new zzbu(zzbn.zzl("com.google.android.gms.icing"));
        zzrg = zzbu.zza("block_action_upload_if_data_sharing_disabled", false);
        zzrh = zzbu.zza("drop_usage_reports_for_account_mismatch", false);
        zzri = zzbu.zza("enable_additional_type_for_email", true);
        zzrj = zzbu.zza("enable_client_grant_slice_permission", true);
        zzrk = zzbu.zza("enable_custom_action_url_generation", false);
        zzrl = zzbu.zza("enable_failure_response_for_apitask_exceptions", false);
        zzrm = zzbu.zza("enable_on_device_sharing_control_ui", false);
        zzrn = zzbu.zza("enable_safe_app_indexing_package_removal", false);
        zzro = zzbu.zza("enable_slice_authority_validation", false);
        zzrp = zzbu.zza("redirect_user_actions_from_persistent_to_main", false);
        zzrq = zzbu.zza("type_access_whitelist_enforce_platform_permissions", true);
    }
}
