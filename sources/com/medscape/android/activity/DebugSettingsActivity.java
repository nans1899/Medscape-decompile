package com.medscape.android.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.material.snackbar.Snackbar;
import com.medscape.android.Constants;
import com.medscape.android.EnvironmentConfig;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.ads.proclivity.ProclivityConstants;
import com.medscape.android.analytics.NotificationAnalyticsHandler;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.util.DebugSingleton;
import com.medscape.android.util.Util;
import com.wbmd.environment.data.Environment;
import com.wbmd.environment.data.Module;
import com.wbmd.environment.interfaces.OnEnvironmentListener;
import com.wbmd.environment.interfaces.OnModuleListener;
import com.wbmd.environment.ui.adapters.ModuleAdapter;
import com.wbmd.environment.ui.fragments.EnvironmentDialogFragment;
import com.wbmd.wbmddatacompliance.gdpr.GDPRState;
import com.wbmd.wbmddatacompliance.sharepreferences.SharedPreferenceManager;
import com.webmd.wbmdsimullytics.services.SimulLyticsFirebaseMessagingService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

public class DebugSettingsActivity extends NavigableBaseActivity implements AdapterView.OnItemSelectedListener {
    private static int CKB_PUSH_REQUEST_CODE = 203;
    private static int DRUGS_PUSH_REQUEST_CODE = 202;
    private static int NEWS_PUSH_REQUEST_CODE = 201;
    /* access modifiers changed from: private */
    public TextView crashCopyTV;
    private CompoundButton mBlurToggle;
    private NotificationCompat.Builder mBuilder;
    private CompoundButton mCacheToggle;
    private TextView mConsultVoteURLsTv;
    private CompoundButton mForceGDPRToggle;
    private CompoundButton mForceLongSectionNameToggle;
    private CompoundButton mForceNativeAds;
    private CompoundButton mForceUpdateToggle;
    private CompoundButton mMyInvitesDemoToggle;
    private NotificationManager mNotificationManager;
    private CompoundButton mShowOmnitureTagToggle;
    private CompoundButton mShowWebviewErrorToggle;
    /* access modifiers changed from: private */
    public ModuleAdapter moduleAdapter = null;
    private ArrayList<Module> modules = new EnvironmentConfig().getModules();
    /* access modifiers changed from: private */
    public TextView proclivityTimeoutTv;
    private TextView promoDebug;
    /* access modifiers changed from: private */
    public TextView virtualPageView;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.debug_settings);
        setupActionBar();
        initializeUI();
        this.mShowOmnitureTagToggle = (CompoundButton) findViewById(R.id.button_show_omniture_tag);
        setOmnitureTagToggle();
        this.mCacheToggle = (CompoundButton) findViewById(R.id.bttn_cache);
        setCacheToggle();
        this.mBlurToggle = (CompoundButton) findViewById(R.id.bttn_blur_carousel);
        setBlurToggle();
        TextView textView = (TextView) findViewById(R.id.txt_promo_debug);
        this.promoDebug = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DebugSettingsActivity.this.startActivity(new Intent(DebugSettingsActivity.this, PromoDebugActivity.class));
            }
        });
        setSlideCacheToggle();
        this.mMyInvitesDemoToggle = (CompoundButton) findViewById(R.id.bttn_myinvites_demo);
        setMyInvitesToggle();
        this.mForceGDPRToggle = (CompoundButton) findViewById(R.id.bttn_gdpr_force);
        setGDPRToggle();
        this.mForceLongSectionNameToggle = (CompoundButton) findViewById(R.id.bttn_long_sectionname_force);
        setLongSectionNameToggle();
        this.mShowWebviewErrorToggle = (CompoundButton) findViewById(R.id.bttn_show_webview_errors);
        setWebViewErrorToggle();
        this.mForceUpdateToggle = (CompoundButton) findViewById(R.id.force_data_update);
        setForceUpdateToggle();
        this.mForceNativeAds = (CompoundButton) findViewById(R.id.force_native_ads);
        setForceNativeAds();
        ((TextView) findViewById(R.id.npid)).setText(NotificationAnalyticsHandler.INSTANCE.getProviderId(this));
        if (Build.VERSION.SDK_INT >= 19) {
            ((TextView) findViewById(R.id.system_notifications_enabled)).setText(Util.isNotificationChannelEnabled() ? "Yes" : "No");
        }
        this.mBuilder = new NotificationCompat.Builder((Context) this, getString(R.string.default_notification_channel_id));
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        this.mConsultVoteURLsTv = (TextView) findViewById(R.id.consult_vote_urls);
        setConsultVoteURLS();
        setUpProclivityTimeout();
        setCopyCrash();
        setUpVirtualPageViewUi();
    }

    private void setGDPRToggle() {
        this.mForceGDPRToggle.setChecked(GDPRState.getInstance(getApplicationContext()).isForceShowOverride());
        this.mForceGDPRToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GDPRState.getInstance(DebugSettingsActivity.this.getApplicationContext()).setForeceShowOverride(z);
                Toast.makeText(DebugSettingsActivity.this.getApplicationContext(), z ? "Force GDPR Enabled" : "Force GDPR Disabled", 0).show();
                new SharedPreferenceManager().saveGDRPState(GDPRState.getInstance(DebugSettingsActivity.this.getApplicationContext()), DebugSettingsActivity.this.getApplicationContext());
            }
        });
    }

    private void setLongSectionNameToggle() {
        this.mForceLongSectionNameToggle.setChecked(DebugSingleton.getInstance().isForceLongSectionName);
        this.mForceLongSectionNameToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DebugSingleton.getInstance().isForceLongSectionName = z;
            }
        });
    }

    private void setWebViewErrorToggle() {
        this.mShowWebviewErrorToggle.setChecked(DebugSingleton.getInstance().isShowWebViewError);
        this.mShowWebviewErrorToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DebugSingleton.getInstance().isShowWebViewError = z;
            }
        });
    }

    private void setForceUpdateToggle() {
        this.mForceUpdateToggle.setChecked(Settings.singleton(this).getSetting(Constants.PREF_DEBUG_FORCE_UP_TOGGLE, false));
        this.mForceUpdateToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Settings.singleton(DebugSettingsActivity.this).saveSetting(Constants.PREF_DEBUG_FORCE_UP_TOGGLE, z);
            }
        });
    }

    private void setForceNativeAds() {
        this.mForceNativeAds.setChecked(Settings.singleton(this).getSetting(Constants.PREF_DEBUG_FORCE_NATIVE_ADS, false));
        this.mForceNativeAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Settings.singleton(DebugSettingsActivity.this).saveSetting(Constants.PREF_DEBUG_FORCE_NATIVE_ADS, z);
            }
        });
    }

    private void setOmnitureTagToggle() {
        this.mShowOmnitureTagToggle.setChecked(MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEBUG_OMNITURE_TAG_TOGGLE, false));
        this.mShowOmnitureTagToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedscapeApplication.get().getPreferences().edit().putBoolean(Constants.PREF_DEBUG_OMNITURE_TAG_TOGGLE, z).commit();
            }
        });
    }

    private void setCacheToggle() {
        this.mCacheToggle.setChecked(MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEBUG_CAROUSEL_CACHE, true));
        this.mCacheToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedscapeApplication.get().getPreferences().edit().putBoolean(Constants.PREF_DEBUG_CAROUSEL_CACHE, z).commit();
            }
        });
    }

    private void setSlideCacheToggle() {
        this.mCacheToggle.setChecked(MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEBUG_CAROUSEL_CACHE, true));
        this.mCacheToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedscapeApplication.get().getPreferences().edit().putBoolean(Constants.PREF_DEBUG_CAROUSEL_CACHE, z).commit();
            }
        });
    }

    private void setBlurToggle() {
        this.mBlurToggle.setChecked(MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEBUG_CAROUSEL_IMAGE_BLUR, false));
        this.mBlurToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedscapeApplication.get().getPreferences().edit().putBoolean(Constants.PREF_DEBUG_CAROUSEL_IMAGE_BLUR, z).commit();
            }
        });
    }

    private void setMyInvitesToggle() {
        this.mMyInvitesDemoToggle.setChecked(MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEBUG_MYINVITES_DEMO, false));
        this.mMyInvitesDemoToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MedscapeApplication.get().getPreferences().edit().putBoolean(Constants.PREF_DEBUG_MYINVITES_DEMO, z).commit();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showEnvironmentSwitcherDialog(Module module) {
        EnvironmentDialogFragment newInstance = EnvironmentDialogFragment.Companion.newInstance(module.getEnvironments(), module.getId());
        newInstance.setListener(new OnEnvironmentListener() {
            public void onChanged(Environment environment) {
                DebugSettingsActivity.this.moduleAdapter.notifyDataSetChanged();
            }
        });
        newInstance.show(getSupportFragmentManager(), EnvironmentDialogFragment.TAG);
    }

    private void initializeUI() {
        ModuleAdapter moduleAdapter2 = new ModuleAdapter(this.modules, new OnModuleListener() {
            public void onChanged(Module module) {
                DebugSettingsActivity.this.showEnvironmentSwitcherDialog(module);
            }
        });
        this.moduleAdapter = moduleAdapter2;
        ((RecyclerView) findViewById(R.id.environment_list)).setAdapter(moduleAdapter2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        Settings.singleton(this).saveSetting(adapterView.getTag().toString(), Integer.toString(i));
    }

    public void pushNewsNotification(View view) {
        Intent intent = new Intent(this, SimulLyticsFirebaseMessagingService.class);
        intent.setAction("com.medscape.android.debug.intent.APPBOY_NOTIFICATION_OPENED");
        intent.putExtra("uri", "medscape://host/asdf?path=viewarticle%2F823276");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, NEWS_PUSH_REQUEST_CODE, intent, 1073741824);
        this.mBuilder.setOnlyAlertOnce(true).setContentTitle("Medscape").setContentText("Article : 823276").setTicker("Medscape").setSmallIcon((int) R.drawable.ic_micon_inverse);
        this.mBuilder.setContentIntent(broadcast);
        this.mBuilder.setAutoCancel(true);
        this.mBuilder.setPriority(0);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.default_notification_channel_id), "Notifications", 3);
            notificationChannel.setDescription("Notifications");
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
        this.mNotificationManager.notify(NEWS_PUSH_REQUEST_CODE, this.mBuilder.build());
    }

    public void pushDrugNotification(View view) {
        Intent intent = new Intent(this, SimulLyticsFirebaseMessagingService.class);
        intent.setAction("com.medscape.android.debug.intent.APPBOY_NOTIFICATION_OPENED");
        intent.putExtra("uri", "medscape://host/reference?url=drug%3A%2F%2F342997");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, DRUGS_PUSH_REQUEST_CODE, intent, 1073741824);
        this.mBuilder.setOnlyAlertOnce(true).setContentTitle("Medscape").setContentText("Drug : 343100").setTicker("Medscape").setSmallIcon((int) R.drawable.ic_micon_inverse);
        this.mBuilder.setContentIntent(broadcast);
        this.mBuilder.setAutoCancel(true);
        this.mBuilder.setPriority(0);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.default_notification_channel_id), "Notifications", 3);
            notificationChannel.setDescription("Notifications");
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
        this.mNotificationManager.notify(DRUGS_PUSH_REQUEST_CODE, this.mBuilder.build());
    }

    public void pushCKBNotification(View view) {
        Intent intent = new Intent(this, SimulLyticsFirebaseMessagingService.class);
        intent.setAction("com.medscape.android.debug.intent.APPBOY_NOTIFICATION_OPENED");
        intent.putExtra("uri", "medscape://host/reference?url=ckb://168230");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, CKB_PUSH_REQUEST_CODE, intent, 1073741824);
        this.mBuilder.setOnlyAlertOnce(true).setContentTitle("Medscape").setContentText("CKB : 168230").setTicker("Medscape").setSmallIcon((int) R.drawable.ic_micon_inverse);
        this.mBuilder.setContentIntent(broadcast);
        this.mBuilder.setAutoCancel(true);
        this.mBuilder.setPriority(0);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.default_notification_channel_id), "Notifications", 3);
            notificationChannel.setDescription("Notifications");
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
        this.mNotificationManager.notify(CKB_PUSH_REQUEST_CODE, this.mBuilder.build());
    }

    public void clearClinicalCache(View view) {
        if (FileHelper.deleteDir(new File(Constants.DIRECTORY_MAIN_CR))) {
            Settings.singleton(this).saveSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO);
            Snackbar.make(view, (CharSequence) getString(R.string.debug_cache_cleared_message), -1).show();
        }
    }

    private void setUpProclivityTimeout() {
        TextView textView = (TextView) findViewById(R.id.proclivity_timeout);
        this.proclivityTimeoutTv = textView;
        textView.setText("" + (((double) ProclivityConstants.PROCLIVITY_TIMEOUT) / 1000.0d));
        this.proclivityTimeoutTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DebugSettingsActivity.this);
                builder.setTitle((CharSequence) "Enter Timeout in seconds");
                final EditText editText = new EditText(DebugSettingsActivity.this);
                editText.setInputType(8194);
                builder.setView((View) editText);
                builder.setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String obj = editText.getText().toString();
                        if (!obj.isEmpty()) {
                            ProclivityConstants.setProclivityTimeout((long) (Double.valueOf(obj).doubleValue() * 1000.0d));
                            TextView access$200 = DebugSettingsActivity.this.proclivityTimeoutTv;
                            access$200.setText((((double) ProclivityConstants.PROCLIVITY_TIMEOUT) / 1000.0d) + "");
                        }
                    }
                });
                builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    public void crashTheApp(View view) {
        throw new RuntimeException("Crash");
    }

    public void copyTheDB(View view) {
        try {
            File externalStorageDirectory = android.os.Environment.getExternalStorageDirectory();
            android.os.Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(getFilesDir().getPath() + "/Medscape/" + DatabaseHelper.DB_NAME + "");
                File file2 = new File(externalStorageDirectory, "medscape_backup.db");
                if (file.exists()) {
                    FileChannel channel = new FileInputStream(file).getChannel();
                    FileChannel channel2 = new FileOutputStream(file2).getChannel();
                    channel2.transferFrom(channel, 0, channel.size());
                    channel.close();
                    channel2.close();
                    Toast.makeText(this, "Copied at " + file2.getPath(), 1).show();
                    return;
                }
                Toast.makeText(this, "Internal DB not found", 1).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), 1).show();
        }
    }

    private void setConsultVoteURLS() {
        this.mConsultVoteURLsTv.setText(ConsultUrls.getUpVoteUrl(this) + IOUtils.LINE_SEPARATOR_UNIX + ConsultUrls.getDeleteUpVoteUrl(this) + IOUtils.LINE_SEPARATOR_UNIX + ConsultUrls.getDownVoteUrl(this) + IOUtils.LINE_SEPARATOR_UNIX + ConsultUrls.getDeleteDownVoteUrl(this) + "\n \n");
    }

    private void setCopyCrash() {
        this.crashCopyTV = (TextView) findViewById(R.id.copy_crash);
        if (SharedPreferenceProvider.get().get(Constants.DEBUG_COPY_CRASH, (int) Constants.COPY_CRASH_FALSE) == 1110) {
            this.crashCopyTV.setText("false");
        } else {
            this.crashCopyTV.setText(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        this.crashCopyTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DebugSettingsActivity.this);
                builder.setMessage((CharSequence) "Crash copy process in 4.4.1?");
                builder.setPositiveButton((CharSequence) "Crash", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferenceProvider.get().save(Constants.DEBUG_COPY_CRASH, (int) Constants.COPY_CRASH_TRUE);
                        DebugSettingsActivity.this.crashCopyTV.setText(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    }
                });
                builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferenceProvider.get().save(Constants.DEBUG_COPY_CRASH, (int) Constants.COPY_CRASH_FALSE);
                        DebugSettingsActivity.this.crashCopyTV.setText("false");
                    }
                });
                builder.create().show();
            }
        });
    }

    private void setUpVirtualPageViewUi() {
        this.virtualPageView = (TextView) findViewById(R.id.virtual_pageview);
        if (SharedPreferenceProvider.get().get(Constants.DEBUG_VIRTUAL_PAGEVIEW, 10) == 10) {
            this.virtualPageView.setText("Off");
        } else {
            this.virtualPageView.setText("On");
        }
        this.virtualPageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DebugSettingsActivity.this);
                builder.setMessage((CharSequence) "Show indication of Virtual pageview.");
                builder.setPositiveButton((CharSequence) "On", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferenceProvider.get().save(Constants.DEBUG_VIRTUAL_PAGEVIEW, 11);
                        DebugSettingsActivity.this.virtualPageView.setText("On");
                    }
                });
                builder.setNegativeButton((CharSequence) "Off", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferenceProvider.get().save(Constants.DEBUG_VIRTUAL_PAGEVIEW, 10);
                        DebugSettingsActivity.this.virtualPageView.setText("Off");
                    }
                });
                builder.create().show();
            }
        });
    }

    public void resetGDPR(View view) {
        GDPRState instance = GDPRState.getInstance(getApplicationContext());
        instance.setIsAccepted(false);
        instance.setGeoLocated(false);
        instance.setGeoCodeEU(false);
        this.mForceGDPRToggle.setChecked(GDPRState.getInstance(this).isForceShowOverride());
        new SharedPreferenceManager().saveGDRPState(instance, getApplicationContext());
        Toast.makeText(getApplicationContext(), "GDPR Reset", 0).show();
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            getSupportActionBar().setTitle((CharSequence) getResources().getString(R.string.debug_settings));
        }
    }
}
