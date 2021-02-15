package com.medscape.android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.install.DrugInstallationActivity;
import com.medscape.android.activity.install.InstallationActivity;
import com.webmd.wbmdproffesionalauthentication.activities.RegistrationActivity;

public class DialogUtil {
    public static final int MSG_SHOW_ARTICLE_NETWORK_ERROR_DIALOG = 16;
    public static final int MSG_SHOW_CLINICAL_ARTICLE_NETWORK_ERROR_DIALOG = 14;
    public static final int MSG_SHOW_CONFIGURE_EMAIL = 24;
    public static final int MSG_SHOW_DATA_INSTALLATION_NETWORK_ERROR_DIALOG = 25;
    public static final int MSG_SHOW_EDUCATION_NETWORK_ERROR_DIALOG = 21;
    public static final int MSG_SHOW_EMAIL_REQUIRED_DIALOG = 4;
    public static final int MSG_SHOW_FEATURE_NOT_AVAILABLE_DIALOG = 8;
    public static final int MSG_SHOW_FILEDS_REQUIRED_DIALOG = 6;
    public static final int MSG_SHOW_INSTALLATION_FAILED_DIALOG = 15;
    public static final int MSG_SHOW_INSTALLATION_FAILED_DO_RETRY_DIALOG = 26;
    public static final int MSG_SHOW_INSTALLATION_NETWORK_ERROR_DIALOG = 11;
    public static final int MSG_SHOW_INVALID_EMAIL_DIALOG = 2;
    public static final int MSG_SHOW_MEDLINE_NETWORK_ERROR_DIALOG = 22;
    public static final int MSG_SHOW_NETWORK_ERROR_DIALOG = 5;
    public static final int MSG_SHOW_NEWS_NETWORK_ERROR_DIALOG = 7;
    public static final int MSG_SHOW_NO_UPDATE_AVAILABLE = 10;
    public static final int MSG_SHOW_PDF_NOT_SUPPORTED_DIALOG = 333;
    public static final int MSG_SHOW_REQUEST_SEND_DIALOG = 1;
    public static final int MSG_SHOW_REQUIRED_VALID_EMAIL_DIALOG = 3;
    public static final int MSG_SHOW_SAVE_DIALOG = 23;
    public static final int MSG_SHOW_SDCARD_REQUIRED_DIALOG = 9;
    public static final int MSG_SHOW_SPECIALITY_NETWORK_ERROR_DIALOG = 12;
    public static final int MSG_SHOW_UPDATE_NETWORK_ERROR_DIALOG = 13;
    public static final int SHOW_DIALOG_DRUG_DATA_DOWNLOAD_REQUEST = 17;
    public static final int SHOW_DIALOG_FORCED_DRUG_DATA_DOWNLOAD_REQUEST = 18;
    private static final int SHOW_MANDATORY_APP_UPGRADE_DIALOG = 109;
    private static final int SHOW_OPTIONAL_APP_UPGRADE_DIALOG = 110;
    protected static final String TAG = "DialogUtil";

    public static AlertDialog showAlertDialog(int i, String str, String str2, Context context) {
        return showAlertDialog(i, str, str2, context, (DialogInterface.OnClickListener) null);
    }

    public static AlertDialog showAlertDialog(int i, String str, String str2, final Context context, DialogInterface.OnClickListener onClickListener) {
        Spanned blacktext = getBlacktext(str);
        Spanned blacktext2 = getBlacktext(str2);
        if (i != 1) {
            int i2 = R.string.alert_dialog_confirmation_positive_button_text_skip;
            if (i != 11) {
                if (i == 15) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    if (!(context instanceof InstallationActivity)) {
                        i2 = R.string.alert_dialog_confirmation_button_text_close;
                    }
                    builder.setMessage(blacktext2).setCancelable(false).setIcon(17301543).setPositiveButton(context.getString(i2), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ConnectivityUtils connectivityUtils = new ConnectivityUtils();
                            dialogInterface.cancel();
                            ((Activity) context).setResult(1);
                            connectivityUtils.releaseLocks();
                            ((Activity) context).finish();
                        }
                    }).setNegativeButton(context.getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DrugInstallationActivity drugInstallationActivity;
                            dialogInterface.cancel();
                            Context context = context;
                            if ((context instanceof DrugInstallationActivity) && (drugInstallationActivity = (DrugInstallationActivity) context) != null && drugInstallationActivity.getUpdateList() != null) {
                                drugInstallationActivity.retryDownloadGetContentURL();
                            }
                        }
                    }).setOnKeyListener(new DialogInterface.OnKeyListener() {
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            return i == 84 || i == 82;
                        }
                    });
                    if (blacktext != null) {
                        builder.setTitle(blacktext);
                    }
                    return builder.create();
                } else if (i == 8) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                    builder2.setMessage(blacktext2).setCancelable(false).setIcon(17301543).setPositiveButton(context.getString(R.string.alert_dialog_confirmation_button_text_cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).setNegativeButton(context.getString(R.string.alert_dialog_confirmation_button_text_signup), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DialogUtil.click(context);
                            dialogInterface.cancel();
                            Context context = context;
                            if (context instanceof Activity) {
                                ((Activity) context).finish();
                            }
                        }
                    });
                    if (blacktext != null) {
                        builder2.setTitle(blacktext);
                    }
                    return builder2.create();
                } else if (i != 9) {
                    switch (i) {
                        case 23:
                            AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
                            builder3.setMessage(blacktext2).setCancelable(true).setPositiveButton(context.getString(R.string.alert_dialog_confirmation_positive_button_text_OK), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            if (blacktext != null) {
                                builder3.setTitle(blacktext);
                            }
                            return builder3.create();
                        case 24:
                            AlertDialog create = new AlertDialog.Builder(context).create();
                            create.setMessage(blacktext2);
                            create.setButton(context.getString(R.string.alert_dialog_confirmation_positive_button_text_OK), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            return create;
                        case 25:
                            AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
                            if (!(context instanceof InstallationActivity)) {
                                i2 = R.string.alert_dialog_confirmation_button_text_close;
                            }
                            builder4.setMessage(blacktext2).setCancelable(false).setIcon(17301543).setPositiveButton(context.getString(i2), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Settings.singleton(context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
                                    ConnectivityUtils connectivityUtils = new ConnectivityUtils();
                                    dialogInterface.cancel();
                                    ((Activity) context).setResult(1);
                                    connectivityUtils.releaseLocks();
                                    ((Activity) context).sendBroadcast(new Intent("CLOSE_ALL"));
                                }
                            }).setNegativeButton(context.getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DrugInstallationActivity drugInstallationActivity;
                                    dialogInterface.cancel();
                                    Context context = context;
                                    if (context instanceof InstallationActivity) {
                                        InstallationActivity installationActivity = (InstallationActivity) context;
                                        if (installationActivity != null) {
                                            installationActivity.retryDownload();
                                        }
                                    } else if ((context instanceof DrugInstallationActivity) && (drugInstallationActivity = (DrugInstallationActivity) context) != null && drugInstallationActivity.getUpdateList() != null) {
                                        drugInstallationActivity.retryDownload();
                                    }
                                }
                            }).setOnKeyListener(new DialogInterface.OnKeyListener() {
                                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                    return i == 84 || i == 82;
                                }
                            });
                            if (blacktext != null) {
                                builder4.setTitle(blacktext);
                            }
                            return builder4.create();
                        case 26:
                            AlertDialog.Builder builder5 = new AlertDialog.Builder(context);
                            if (!(context instanceof InstallationActivity)) {
                                i2 = R.string.alert_dialog_confirmation_button_text_close;
                            }
                            builder5.setMessage(blacktext2).setCancelable(false).setIcon(17301543).setPositiveButton(context.getString(i2), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ConnectivityUtils connectivityUtils = new ConnectivityUtils();
                                    dialogInterface.cancel();
                                    ((Activity) context).setResult(1);
                                    connectivityUtils.releaseLocks();
                                    ((Activity) context).finish();
                                }
                            }).setNegativeButton(context.getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DrugInstallationActivity drugInstallationActivity;
                                    dialogInterface.cancel();
                                    Context context = context;
                                    if ((context instanceof DrugInstallationActivity) && (drugInstallationActivity = (DrugInstallationActivity) context) != null && drugInstallationActivity.getUpdateList() != null) {
                                        drugInstallationActivity.retryReferencePlistDownload();
                                    }
                                }
                            }).setOnKeyListener(new DialogInterface.OnKeyListener() {
                                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                    return i == 84 || i == 82;
                                }
                            });
                            if (blacktext != null) {
                                builder5.setTitle(blacktext);
                            }
                            return builder5.create();
                        default:
                            AlertDialog.Builder builder6 = new AlertDialog.Builder(context);
                            builder6.setMessage(blacktext2);
                            builder6.setCancelable(false);
                            builder6.setIcon(17301543);
                            if (onClickListener != null) {
                                builder6.setPositiveButton(context.getString(R.string.alert_dialog_confirmation_button_text_close), onClickListener);
                            } else {
                                builder6.setPositiveButton(context.getString(R.string.alert_dialog_confirmation_button_text_close), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                            }
                            if (blacktext != null) {
                                builder6.setTitle(blacktext);
                            }
                            return builder6.create();
                    }
                }
            }
            AlertDialog.Builder builder7 = new AlertDialog.Builder(context);
            if (!(context instanceof InstallationActivity)) {
                i2 = R.string.alert_dialog_confirmation_button_text_close;
            }
            builder7.setMessage(blacktext2).setCancelable(false).setIcon(17301543).setPositiveButton(context.getString(i2), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Settings.singleton(context).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
                    ConnectivityUtils connectivityUtils = new ConnectivityUtils();
                    dialogInterface.cancel();
                    ((Activity) context).setResult(1);
                    connectivityUtils.releaseLocks();
                    ((Activity) context).finish();
                }
            }).setNegativeButton(context.getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DrugInstallationActivity drugInstallationActivity;
                    dialogInterface.cancel();
                    Context context = context;
                    if (context instanceof InstallationActivity) {
                        InstallationActivity installationActivity = (InstallationActivity) context;
                        if (installationActivity != null) {
                            installationActivity.retryDownload();
                        }
                    } else if ((context instanceof DrugInstallationActivity) && (drugInstallationActivity = (DrugInstallationActivity) context) != null && drugInstallationActivity.getUpdateList() != null) {
                        drugInstallationActivity.retryDownload();
                    }
                }
            }).setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == 84 || i == 82;
                }
            });
            if (blacktext != null) {
                builder7.setTitle(blacktext);
            }
            return builder7.create();
        }
        AlertDialog.Builder builder8 = new AlertDialog.Builder(context);
        builder8.setMessage(blacktext2).setCancelable(false).setIcon(17301543).setPositiveButton(context.getString(R.string.alert_dialog_confirmation_positive_button_text_OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                ((Activity) context).finish();
            }
        });
        if (blacktext != null) {
            builder8.setTitle(blacktext);
        }
        return builder8.create();
    }

    protected static void click(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        intent.setFlags(67108864);
        intent.putExtra("close", 1);
        context.startActivity(intent);
    }

    private static Spanned getBlacktext(String str) {
        if (str == null) {
            return null;
        }
        return Html.fromHtml("<font color=#000000>" + str + "</font>");
    }
}
