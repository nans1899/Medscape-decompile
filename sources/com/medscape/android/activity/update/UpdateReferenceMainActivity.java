package com.medscape.android.activity.update;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.Scopes;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.AdBlockerWebViewAcitivity;
import com.medscape.android.activity.install.DrugInstallationActivity;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.reference.ClinicalReferenceInstallationRequestActivity;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.updater.model.Update;
import com.medscape.android.util.ConnectivityUtils;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UpdateReferenceMainActivity extends NavigableBaseActivity implements OnUpdateListener, View.OnClickListener {
    private static final int AD_BLOCKER_REQUEST_CODE = 12390;
    private static final int CLINICAL_REFERENCE = 1;
    public static final int DATA_UPDATE_FROM_UPDATE_REFERENCE = 555;
    private static final int DIALOG_BACKGROUND_UPDATE = 1001;
    private static final int SHOW_APP_UPGRADE_DIALOG = 12;
    protected static final int START_DRUG_UPDATE_REQUEST = 6;
    private ConnectivityUtils _connectivityUtils = new ConnectivityUtils();
    private TextView clinicalReferenceUpdateButton;
    private boolean downloadClinicalReference = false;
    private int downloadType;
    private boolean getReferenceUpdates = false;
    public Handler h = new Handler(new Handler.Callback() {
        Intent intent = null;

        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 5) {
                if (i != 11) {
                    if (i != 13) {
                        switch (i) {
                            case 17:
                                Intent intent2 = new Intent(UpdateReferenceMainActivity.this, DrugInstallationActivity.class);
                                this.intent = intent2;
                                UpdateReferenceMainActivity.this.startActivityForResult(intent2, 99);
                                return true;
                            case 18:
                                Intent intent3 = new Intent(UpdateReferenceMainActivity.this, DrugInstallationActivity.class);
                                this.intent = intent3;
                                UpdateReferenceMainActivity.this.startActivityForResult(intent3, 99);
                                return true;
                            case 19:
                                UpdateReferenceMainActivity.this.displayAdBlockerMessage();
                                return true;
                            default:
                                return true;
                        }
                    } else if (UpdateReferenceMainActivity.this.isFinishing()) {
                        return true;
                    } else {
                        UpdateReferenceMainActivity.this.showDialog(13);
                        return true;
                    }
                } else if (UpdateReferenceMainActivity.this.isFinishing()) {
                    return true;
                } else {
                    UpdateReferenceMainActivity.this.showDialog(11);
                    return true;
                }
            } else if (UpdateReferenceMainActivity.this.isFinishing()) {
                return true;
            } else {
                UpdateReferenceMainActivity.this.showDialog(5);
                return true;
            }
        }
    });
    private boolean isCheckingForUpdate = false;
    CompoundButton mNetworkToggle;
    /* access modifiers changed from: private */
    public TextView referenceUpdateButton;
    private UpdateManager updater;

    public void onProgressUpdate(int i) {
    }

    public void setMaxProgress(int i) {
    }

    public void setProgressMessage(String str) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.data_updates_layout);
        setTitle(getResources().getString(R.string.dataupdates_header_text));
        this.referenceUpdateButton = (TextView) findViewById(R.id.referenceButton);
        this.clinicalReferenceUpdateButton = (TextView) findViewById(R.id.clinicalReferenceButton);
        this.referenceUpdateButton.setOnClickListener(this);
        this.clinicalReferenceUpdateButton.setOnClickListener(this);
        this.mNetworkToggle = (CompoundButton) findViewById(R.id.bttn_network);
        setNetworkToggle();
    }

    private void setNetworkToggle() {
        boolean z = MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DOWNLOAD_OVER_NETWORK, false);
        this.mNetworkToggle.setChecked(z);
        setNetworkToggleStateMessage(z);
        this.mNetworkToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedscapeApplication.get().getPreferences().edit().putBoolean(Constants.PREF_DOWNLOAD_OVER_NETWORK, z).apply();
                UpdateReferenceMainActivity.this.setNetworkToggleStateMessage(z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setNetworkToggleStateMessage(boolean z) {
        if (z) {
            ((TextView) findViewById(R.id.NetworkToggleStateTextMessage)).setText(R.string.text_update_cellular_preference_yes);
        } else {
            ((TextView) findViewById(R.id.NetworkToggleStateTextMessage)).setText(R.string.text_update_cellular_preference_no);
        }
    }

    public void checkForDataUpdateWithServerRequest() {
        UpdateManager updateManager = MedscapeApplication.get().getUpdateManager();
        this.updater = updateManager;
        updateManager.setOnUpdateListener(this);
        this.updater.checkVerXml(DATA_UPDATE_FROM_UPDATE_REFERENCE);
    }

    public void onClick(View view) {
        if (!this.isCheckingForUpdate) {
            if (MedscapeApplication.get().isBackgroundUpdateInProgress()) {
                if (!isFinishing()) {
                    showDialog(1001);
                }
            } else if (view.equals(this.clinicalReferenceUpdateButton)) {
                OmnitureManager.get().trackPageView(this, "other", Scopes.PROFILE, "data-updates/downloadckb", (String) null, (String) null, (Map<String, Object>) null);
                if (Util.isAdBlockerInstalled()) {
                    startAdBlockerActivity();
                } else if (Util.isTestDriveTimeFinished(this) || !Util.isTestDriveTimeSet(this)) {
                    if (Util.isOnline(getApplicationContext())) {
                        this.isCheckingForUpdate = true;
                        this.downloadClinicalReference = true;
                        UpdateManager updateManager = new UpdateManager(getApplicationContext());
                        this.updater = updateManager;
                        updateManager.isVersionCheck = true;
                        this.updater.setOnUpdateListener(this);
                        this._connectivityUtils.createLock(this);
                        this._connectivityUtils.acquireLock();
                        this.updater.getReferencePList();
                        return;
                    }
                    showDialog(13);
                } else if (!isFinishing()) {
                    showDialog(8);
                }
            } else if (!view.equals(this.referenceUpdateButton)) {
            } else {
                if (Util.isAdBlockerInstalled()) {
                    startAdBlockerActivity();
                } else if (Util.isTestDriveTimeFinished(this) || !Util.isTestDriveTimeSet(this)) {
                    if (Util.isOnline(getApplicationContext())) {
                        checkForDataUpdateWithServerRequest();
                    } else {
                        showDialog(13);
                    }
                } else if (!isFinishing()) {
                    showDialog(8);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!AccountProvider.isUserLoggedIn(this)) {
            finish();
            return;
        }
        try {
            if (Double.valueOf(Double.parseDouble(Settings.singleton(this).getSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO))).doubleValue() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                findViewById(R.id.clinicalReferenceUpdateButtonRow).setVisibility(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        float f = getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", -1.0f);
        String setting = Settings.singleton(this).getSetting(Constants.PREF_SINGLE_AD_SEGVARS_UPDATE_CLIENT_VERSION, "-1");
        ((TextView) findViewById(R.id.DrugVersionValTextView)).setText("v" + f);
        ((TextView) findViewById(R.id.ApplicationVersionValTextView)).setText("v" + Util.getApplicationVersion(this));
        ((TextView) findViewById(R.id.DFPAdSegvarsVersionValTextView)).setText("v" + setting);
        ((TextView) findViewById(R.id.BuildDateValTextView)).setText(Util.getBuildDate());
        ((TextView) findViewById(R.id.InstallDateValTextView)).setText(Util.getInstallDate(this));
        ((TextView) findViewById(R.id.LastUpdateValTextView)).setText(Util.getLastUpdateDate(this));
        Double valueOf = Double.valueOf(Double.parseDouble(Util.getClinicalRefVersion(this)));
        if (valueOf.doubleValue() <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            findViewById(R.id.clinicalReferenceVersionRow).setVisibility(4);
            return;
        }
        findViewById(R.id.clinicalReferenceVersionRow).setVisibility(0);
        ((TextView) findViewById(R.id.ClinicalVersionValTextView)).setText("v" + valueOf);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            this._connectivityUtils.releaseLocks();
        }
    }

    private void onDataUpdateAvailable() {
        SharedPreferences sharedPreferences = getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0);
        double parseDouble = Double.parseDouble(Settings.singleton(this).getSetting(Constants.PREF_OPTIONAL_SERVER_DATA_VERSION, "-1"));
        double parseDouble2 = Double.parseDouble(Settings.singleton(this).getSetting(Constants.PREF_MIN_SERVER_DATA_VERSION, "-1"));
        float f = sharedPreferences.getFloat("version", -1.0f);
        long parseLong = Long.parseLong(Settings.singleton(this).getSetting(Constants.PREF_OPTIONAL_DATA_UPDATE_TIME, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        int i = (parseLong > 0 ? 1 : (parseLong == 0 ? 0 : -1));
        if (i == 0) {
            System.currentTimeMillis();
            Calendar instance3 = Calendar.getInstance();
            instance3.setTimeInMillis(System.currentTimeMillis());
            instance3.add(2, 3);
            Settings singleton = Settings.singleton(this);
            singleton.saveSetting(Constants.PREF_OPTIONAL_DATA_UPDATE_TIME, "" + instance3.getTimeInMillis());
        } else {
            instance.setTimeInMillis(System.currentTimeMillis());
            instance2.setTimeInMillis(parseLong);
        }
        if (f < 946.0f) {
            startActivityForResult(new Intent(this, DrugInstallationActivity.class), 6);
            return;
        }
        double d = (double) f;
        if (d < parseDouble2) {
            this.h.sendEmptyMessage(18);
        } else if (d >= parseDouble) {
        } else {
            if (i == 0 || !instance.after(instance2)) {
                this.h.sendEmptyMessage(17);
            } else {
                this.h.sendEmptyMessage(18);
            }
        }
    }

    public void onUpdateAvailable(int i, List<Update> list, int i2) {
        this.isCheckingForUpdate = false;
        this.getReferenceUpdates = false;
        this.downloadClinicalReference = false;
        if (!Util.isSDCardAvailable()) {
            if (!isFinishing()) {
                showDialog(9);
            }
        } else if (i == 2) {
            startActivityForResult(new Intent(this, ClinicalReferenceInstallationRequestActivity.class), 1);
        } else if (i == 1) {
            onDataUpdateAvailable();
        }
    }

    public void onUpdateComplete(int i, Update update) {
        this._connectivityUtils.releaseLocks();
    }

    public void onUpdateNotAvailable(int i) {
        this.isCheckingForUpdate = false;
        this.getReferenceUpdates = false;
        this.downloadClinicalReference = false;
        this._connectivityUtils.releaseLocks();
        if (!isFinishing()) {
            showDialog(10);
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        this.isCheckingForUpdate = false;
        this.getReferenceUpdates = false;
        this.downloadClinicalReference = false;
        if (i == 5) {
            return DialogUtil.showAlertDialog(5, (String) null, getString(R.string.alert_dialog_clinical_download_network_connection_error_message), this);
        }
        if (i == 1001) {
            return DialogUtil.showAlertDialog(1001, (String) null, "Data update currently in progress", this);
        }
        if (i == 17) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.alert_dialog_drug_update_available_title)).setMessage(getDialogMessage()).setCancelable(false).setPositiveButton(getString(R.string.alert_dialog_update_now_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UpdateReferenceMainActivity.this.startActivityForResult(new Intent(UpdateReferenceMainActivity.this, DrugInstallationActivity.class), 99);
                    dialogInterface.cancel();
                }
            }).setNegativeButton(getString(R.string.alert_dialog_update_later_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == 84 || i == 82;
                }
            });
            return builder.create();
        } else if (i != 18) {
            switch (i) {
                case 8:
                    return DialogUtil.showAlertDialog(8, (String) null, getString(R.string.alert_dialog_signup_required_message), this);
                case 9:
                    return DialogUtil.showAlertDialog(9, (String) null, getResources().getString(R.string.alert_dialog_sdcard_required_message), this);
                case 10:
                    return DialogUtil.showAlertDialog(10, (String) null, "No New Updates Available", this);
                case 11:
                    return DialogUtil.showAlertDialog(5, (String) null, getResources().getString(R.string.alert_dialog_download_clinical_reference_network_connection_error_message), this);
                case 12:
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                    builder2.setTitle(getResources().getString(R.string.alert_dialog_app_update_available_title)).setMessage(getResources().getString(R.string.alert_dialog_app_update_available_mandatory_message)).setCancelable(false).setPositiveButton(getString(R.string.alert_dialog_update_now_button_text), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!Util.isOnline(UpdateReferenceMainActivity.this)) {
                                if (!UpdateReferenceMainActivity.this.isFinishing()) {
                                    UpdateReferenceMainActivity.this.showDialog(5);
                                }
                                dialogInterface.cancel();
                                return;
                            }
                            UpdateReferenceMainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(UpdateManager.APP_UPDATE_URL)));
                            dialogInterface.cancel();
                            UpdateReferenceMainActivity.this.finish();
                        }
                    }).setOnKeyListener(new DialogInterface.OnKeyListener() {
                        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            return i == 84 || i == 82;
                        }
                    });
                    return builder2.create();
                case 13:
                    return DialogUtil.showAlertDialog(5, (String) null, getResources().getString(R.string.alert_dialog_get_reference_updates_network_connection_error_message), this);
                default:
                    return null;
            }
        } else {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
            builder3.setTitle(getResources().getString(R.string.alert_dialog_drug_update_available_title)).setMessage(getDialogMessage()).setCancelable(false).setPositiveButton(getString(R.string.alert_dialog_update_now_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UpdateReferenceMainActivity.this.startActivityForResult(new Intent(UpdateReferenceMainActivity.this, DrugInstallationActivity.class), 99);
                    dialogInterface.cancel();
                }
            }).setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == 84 || i == 82;
                }
            });
            return builder3.create();
        }
    }

    private void startAdBlockerActivity() {
        startActivityForResult(new Intent(this, AdBlockerWebViewAcitivity.class), 12390);
    }

    public void onNetworkError(int i) {
        if (this.isCheckingForUpdate) {
            this.isCheckingForUpdate = false;
            if (this.getReferenceUpdates) {
                this.getReferenceUpdates = false;
                this.h.sendEmptyMessage(13);
            } else if (this.downloadClinicalReference) {
                this.downloadClinicalReference = false;
                this.h.sendEmptyMessage(11);
            }
        } else {
            this.h.sendEmptyMessage(5);
        }
    }

    public String getDialogMessage() {
        int i = this.downloadType;
        if (i == 1) {
            return "Contains new Drug and Clinical Reference information.\n\nTypically takes 2 - 10 minutes to install (depending on connection speed)";
        }
        if (i == 2) {
            return "Contains new Drug and Clinical Reference information.\n\nTypically takes 20 seconds to 2 minutes to install (depending on connection speed)";
        }
        if (i != 3) {
            return i != 4 ? "" : "Contains new Drug and Clinical Reference information.\n\nThis update is required.\n\nTypically takes 20 seconds to 2 minutes to install (depending on connection speed)";
        }
        return "Contains new Drug and Clinical Reference information.\n\nThis update is required.\n\nTypically takes 2 - 10 minutes to install (depending on connection speed)";
    }

    public void displayAdBlockerMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.ad_blocker_dialog_title));
        builder.setMessage(getResources().getString(R.string.ad_blocker_dialog_message));
        builder.setCancelable(false);
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                UpdateReferenceMainActivity.this.referenceUpdateButton.performClick();
            }
        });
        AlertDialog create = builder.create();
        if (!isFinishing()) {
            create.show();
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
        }
    }
}
