package com.wbmd.qxcalculator.activities.common;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.net.MailTo;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.wbmd.qxcalculator.CalculateModule;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.fragments.common.QxMDFragment;
import com.wbmd.qxcalculator.managers.AuthManager;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.managers.QxFirebaseEventManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.wbmd.qxcalculator.util.FirebaseEventsConstants;
import com.wbmd.qxcalculator.util.SharedPreferenceHelper;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class QxMDActivity extends AppCompatActivity {
    public static final String KEY_ACTIVITY_TITLE = "QxMDActivity.KEY_ACTIVITY_TITLE";
    public static final String KEY_DARK_MODE_VALUE_CHANGED = "KEY_DARK_MODE_VALUE_CHANGED";
    private static final String KEY_ERROR_STRING = "KEY_ERROR_STRING";
    public static final String KEY_MODAL_ANIM_STYLE = "QxMDActivity.KEY_MODAL_ANIM_STYLE";
    public static final String KEY_TRACKER_ID = "QxMDActivity.KEY_TRACKER_ID";
    public static final String KEY_TRACKER_PAGE_NAME = "QxMDActivity.KEY_TRACKER_PAGE_NAME";
    public static final String KEY_TRACK_ACTIVITY_STATE = "QxMDActivity.KEY_TRACK_ACTIVITY_STATE";
    private static final String KEY_VIEW_MODE_ORDINAL = "KEY_VIEW_MODE_ORDINAL";
    protected static final String TAG_FRAGMENT = "QxMDActivity.TAG_FRAGMENT";
    private static boolean hasActivityAppearedAtLeastOnce;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.d("BROADCAST", "receive " + intent.getAction());
            if (intent.getAction().equals(QxMDActivity.KEY_DARK_MODE_VALUE_CHANGED)) {
                QxMDActivity.this.darkModeValueChanged();
            } else if (intent.getAction().equals(AuthManager.KEY_NEEDS_LOGIN)) {
                QxMDActivity.this.presentReAuthLoginScreen();
            }
        }
    };
    private String cachedTitle;
    private View contentView;
    protected DrawerLayout drawerLayout;
    private String errorString;
    private TextView errorTextView;
    private View errorView;
    protected boolean isActivityForeground;
    protected boolean isSaving;
    private View loadingView;
    protected View savingOverlayView;
    private Date sessionStartDate;
    public boolean shouldRecreateOnDarkModeChanged = true;
    protected boolean shouldTrackActivityState = true;
    public Toolbar toolbar;
    public ProgressBar toolbarProgressBar;
    public String trackerId;
    public String trackerPageName;
    protected boolean userInteractionEnabled = true;
    protected ViewMode viewMode = ViewMode.IDLE;

    protected enum ViewMode {
        NOT_SET,
        IDLE,
        LOADING,
        SAVING,
        ERROR
    }

    /* access modifiers changed from: private */
    public void presentReAuthLoginScreen() {
    }

    /* access modifiers changed from: protected */
    public boolean activityHasViewModes() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void errorViewTapped() {
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutResourceId();

    /* access modifiers changed from: protected */
    public void onBackConfirmed() {
    }

    public IntentFilter getBroadcastIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(KEY_DARK_MODE_VALUE_CHANGED);
        intentFilter.addAction(AuthManager.KEY_NEEDS_LOGIN);
        return intentFilter;
    }

    /* access modifiers changed from: protected */
    public void darkModeValueChanged() {
        if (this.shouldRecreateOnDarkModeChanged) {
            recreate();
        }
    }

    public boolean isInDarkMode() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    public void setLoadingView(View view) {
        this.loadingView = view;
        View findViewById = view.findViewById(R.id.refreshing_text_view);
        if (findViewById != null && (findViewById instanceof TextView)) {
            ((TextView) findViewById).setText(textForLoadingView());
        }
        View findViewById2 = view.findViewById(R.id.loading_progress_bar);
        if (findViewById2 != null && (findViewById2 instanceof ProgressBar)) {
            ((ProgressBar) findViewById2).getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.progress_bar), PorterDuff.Mode.SRC_IN);
        }
    }

    public void setErrorView(View view) {
        this.errorView = view;
        TextView textView = (TextView) view.findViewById(R.id.error_text_view);
        this.errorTextView = textView;
        if (textView != null && (textView instanceof TextView)) {
            textView.setText(textForErrorView((String) null));
        }
        if (this.errorTextView != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    QxMDActivity.this.errorViewTapped();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public String textForErrorView(String str) {
        return getString(R.string.general_error_message);
    }

    /* access modifiers changed from: protected */
    public String textForLoadingView() {
        return getString(R.string.loading);
    }

    public void setQxContentView(View view) {
        this.contentView = view;
    }

    public void setUserInteractionEnabled(boolean z) {
        this.userInteractionEnabled = z;
        if (this.toolbar != null) {
            if (z) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(false);
            }
        }
        setBarColors(getStatusBarColor(), getActionBarColor());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResourceId());
        LocalBroadcastManager.getInstance(this).registerReceiver(this.broadcastReceiver, getBroadcastIntentFilter());
        CrashLogger instance = CrashLogger.getInstance();
        instance.leaveBreadcrumb("onCreate: " + getClass().getName());
        this.shouldTrackActivityState = getIntent().getBooleanExtra(KEY_TRACK_ACTIVITY_STATE, true);
        this.trackerId = getIntent().getStringExtra(KEY_TRACKER_ID);
        this.trackerPageName = getIntent().getStringExtra(KEY_TRACKER_PAGE_NAME);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
        }
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar = toolbar2;
        if (toolbar2 != null) {
            setSupportActionBar(toolbar2);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
            ProgressBar progressBar = (ProgressBar) this.toolbar.findViewById(R.id.progress_bar);
            this.toolbarProgressBar = progressBar;
            if (!(progressBar == null || progressBar.getIndeterminateDrawable() == null)) {
                this.toolbarProgressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.toolbar_tint), PorterDuff.Mode.SRC_IN);
            }
        }
        if (bundle != null) {
            this.viewMode = ViewMode.values()[bundle.getInt(KEY_VIEW_MODE_ORDINAL, 0)];
            this.errorString = bundle.getString(KEY_ERROR_STRING);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_VIEW_MODE_ORDINAL, this.viewMode.ordinal());
        bundle.putString(KEY_ERROR_STRING, this.errorString);
    }

    public void addFragmentToContainer(Fragment fragment, int i) {
        getSupportFragmentManager().beginTransaction().add(i, fragment, TAG_FRAGMENT).commit();
    }

    public Fragment getDefaultFragment() {
        return getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
    }

    public void setSupportActionBar(Toolbar toolbar2) {
        super.setSupportActionBar(toolbar2);
        setBarColors(getStatusBarColor(), getActionBarColor());
    }

    public int getStatusBarColor() {
        if (this.userInteractionEnabled) {
            return getResources().getColor(R.color.status_bar_color_default);
        }
        return getResources().getColor(R.color.status_bar_color_ui_disabled);
    }

    public int getActionBarColor() {
        if (this.userInteractionEnabled) {
            return getResources().getColor(R.color.action_bar_color_default);
        }
        return getResources().getColor(R.color.action_bar_color_ui_disabled);
    }

    public void setBarColors(int i, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(i);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(i2));
            if (Build.VERSION.SDK_INT >= 21) {
                setTaskDescription(new ActivityManager.TaskDescription(getString(R.string.app_name), BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), i2));
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        if (this.cachedTitle == null) {
            if (charSequence == null) {
                this.cachedTitle = null;
            } else {
                this.cachedTitle = charSequence.toString();
            }
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(charSequence);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setTaskDescription(new ActivityManager.TaskDescription(getString(R.string.app_name), BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), getActionBarColor()));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onToolbarBackPressed();
        return true;
    }

    public void onResume() {
        super.onResume();
        this.isActivityForeground = true;
        if (!hasActivityAppearedAtLeastOnce) {
            hasActivityAppearedAtLeastOnce = true;
            EventsManager.getInstance().startSessionWithQxEvents();
        }
        if (this.shouldTrackActivityState) {
            if (CalculateModule.getApplicationState() == CalculateModule.ApplicationState.InBackground) {
                trackSessionStart();
                EventsManager.getInstance().startSessionWithQxEvents();
            }
            CalculateModule.setApplicationState(CalculateModule.ApplicationState.InForeground);
        } else if (this.sessionStartDate == null) {
            trackSessionStart();
        }
    }

    private void trackSessionStart() {
        if (UserManager.getInstance().getActiveUserAccountType() != UserManager.AccountType.REGULAR) {
            this.sessionStartDate = new Date();
        } else if (this.sessionStartDate == null) {
            DataManager.getInstance().incrementUsageCount();
            this.sessionStartDate = new Date();
        } else if (TimeUnit.MILLISECONDS.toMinutes(new Date().getTime() - this.sessionStartDate.getTime()) > 30) {
            DataManager.getInstance().incrementUsageCount();
            this.sessionStartDate = new Date();
        }
    }

    public void onStart() {
        super.onStart();
        if (activityHasViewModes()) {
            setViewMode(this.viewMode, true, this.errorString);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.shouldTrackActivityState) {
            CalculateModule.setApplicationState(CalculateModule.ApplicationState.TransitioningView);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.shouldTrackActivityState && CalculateModule.getApplicationState() == CalculateModule.ApplicationState.TransitioningView) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (CalculateModule.getApplicationState() == CalculateModule.ApplicationState.TransitioningView) {
                        CalculateModule.setApplicationState(CalculateModule.ApplicationState.InBackground);
                        SharedPreferenceHelper.getInstance().setNumCategoriesFirEventSent(false);
                        QxMDActivity.this.sendFirebaseEventForNumberOfCalcsOpenedInSession(FirebaseEventsConstants.VIEWED_CALCULATOR);
                        if (UserManager.getInstance().getActiveUserAccountType() == UserManager.AccountType.REGULAR) {
                            EventsManager.getInstance().stopSession();
                        }
                    }
                }
            }, 500);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("API", getClass().getCanonicalName() + " onDestroy");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.broadcastReceiver);
    }

    public void finishWithResults(int i, Intent intent) {
        setResult(i, intent);
        finish();
    }

    public void onToolbarBackPressed() {
        onBackPressed();
    }

    public void onBackPressed() {
        onBackPressed(false);
    }

    public void onBackPressed(boolean z) {
        if (z) {
            super.onBackPressed();
            return;
        }
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (findFragmentByTag == null || !(findFragmentByTag instanceof QxMDFragment)) {
            super.onBackPressed();
            return;
        }
        QxMDFragment qxMDFragment = (QxMDFragment) findFragmentByTag;
        if (qxMDFragment.onBackButtonPressed()) {
            super.onBackPressed();
            qxMDFragment.willBeFinished();
            onBackConfirmed();
        }
    }

    public void finish() {
        super.finish();
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (findFragmentByTag != null && (findFragmentByTag instanceof QxMDFragment)) {
            ((QxMDFragment) findFragmentByTag).willBeFinished();
        }
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2) {
        setViewMode(viewMode2, false);
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2, boolean z) {
        setViewMode(viewMode2, z, (String) null);
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2, String str) {
        setViewMode(viewMode2, false, str);
    }

    /* access modifiers changed from: protected */
    public void setViewMode(ViewMode viewMode2, boolean z, String str) {
        if (this.viewMode != viewMode2 || z) {
            ViewMode viewMode3 = this.viewMode;
            this.viewMode = viewMode2;
            int i = AnonymousClass5.$SwitchMap$com$wbmd$qxcalculator$activities$common$QxMDActivity$ViewMode[viewMode2.ordinal()];
            if (i == 1) {
                View view = this.contentView;
                if (view != null) {
                    view.setVisibility(0);
                }
                View view2 = this.loadingView;
                if (view2 != null) {
                    view2.setVisibility(8);
                }
                View view3 = this.errorView;
                if (view3 != null) {
                    view3.setVisibility(8);
                }
                View view4 = this.savingOverlayView;
                if (view4 != null) {
                    view4.setVisibility(8);
                }
                this.isSaving = false;
                if (viewMode3 == ViewMode.SAVING) {
                    setUserInteractionEnabled(true);
                    setTitle(this.cachedTitle);
                }
                DrawerLayout drawerLayout2 = this.drawerLayout;
                if (drawerLayout2 != null) {
                    drawerLayout2.setDrawerLockMode(0);
                }
                invalidateOptionsMenu();
            } else if (i == 2) {
                View view5 = this.contentView;
                if (view5 != null) {
                    view5.setVisibility(8);
                }
                View view6 = this.loadingView;
                if (view6 != null) {
                    view6.setVisibility(0);
                }
                View view7 = this.errorView;
                if (view7 != null) {
                    view7.setVisibility(8);
                }
                View view8 = this.savingOverlayView;
                if (view8 != null) {
                    view8.setVisibility(8);
                }
                this.isSaving = false;
                if (viewMode3 == ViewMode.SAVING) {
                    setUserInteractionEnabled(true);
                    setTitle(this.cachedTitle);
                }
                DrawerLayout drawerLayout3 = this.drawerLayout;
                if (drawerLayout3 != null) {
                    drawerLayout3.setDrawerLockMode(1);
                }
                invalidateOptionsMenu();
            } else if (i == 3) {
                this.errorString = str;
                View view9 = this.contentView;
                if (view9 != null) {
                    view9.setVisibility(8);
                }
                View view10 = this.loadingView;
                if (view10 != null) {
                    view10.setVisibility(8);
                }
                View view11 = this.errorView;
                if (view11 != null) {
                    view11.setVisibility(0);
                    TextView textView = this.errorTextView;
                    if (textView != null) {
                        textView.setText(textForErrorView(str));
                    }
                }
                View view12 = this.savingOverlayView;
                if (view12 != null) {
                    view12.setVisibility(8);
                }
                this.isSaving = false;
                if (viewMode3 == ViewMode.SAVING) {
                    setUserInteractionEnabled(true);
                    setTitle(this.cachedTitle);
                } else {
                    setTitle((CharSequence) null);
                }
                DrawerLayout drawerLayout4 = this.drawerLayout;
                if (drawerLayout4 != null) {
                    drawerLayout4.setDrawerLockMode(1);
                }
                invalidateOptionsMenu();
            } else if (i == 4) {
                View view13 = this.contentView;
                if (view13 != null) {
                    view13.setVisibility(0);
                }
                View view14 = this.loadingView;
                if (view14 != null) {
                    view14.setVisibility(8);
                }
                View view15 = this.errorView;
                if (view15 != null) {
                    view15.setVisibility(8);
                }
                View view16 = this.savingOverlayView;
                if (view16 != null) {
                    view16.setVisibility(0);
                }
                this.isSaving = true;
                View currentFocus = getCurrentFocus();
                if (currentFocus != null) {
                    ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                }
                setUserInteractionEnabled(false);
                setTitle(getStringForSavingTitle());
                DrawerLayout drawerLayout5 = this.drawerLayout;
                if (drawerLayout5 != null) {
                    drawerLayout5.setDrawerLockMode(1);
                }
                invalidateOptionsMenu();
            }
        }
    }

    /* renamed from: com.wbmd.qxcalculator.activities.common.QxMDActivity$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$activities$common$QxMDActivity$ViewMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.wbmd.qxcalculator.activities.common.QxMDActivity$ViewMode[] r0 = com.wbmd.qxcalculator.activities.common.QxMDActivity.ViewMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$activities$common$QxMDActivity$ViewMode = r0
                com.wbmd.qxcalculator.activities.common.QxMDActivity$ViewMode r1 = com.wbmd.qxcalculator.activities.common.QxMDActivity.ViewMode.IDLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$activities$common$QxMDActivity$ViewMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.activities.common.QxMDActivity$ViewMode r1 = com.wbmd.qxcalculator.activities.common.QxMDActivity.ViewMode.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$activities$common$QxMDActivity$ViewMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.activities.common.QxMDActivity$ViewMode r1 = com.wbmd.qxcalculator.activities.common.QxMDActivity.ViewMode.ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$activities$common$QxMDActivity$ViewMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.activities.common.QxMDActivity$ViewMode r1 = com.wbmd.qxcalculator.activities.common.QxMDActivity.ViewMode.SAVING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.activities.common.QxMDActivity.AnonymousClass5.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public String getStringForSavingTitle() {
        return getString(R.string.saving);
    }

    /* access modifiers changed from: protected */
    public String getErrorSavingTitle(List<QxError> list) {
        return getString(R.string.dialog_error_saving_changes_title);
    }

    /* access modifiers changed from: protected */
    public String getErrorSavingMessage(List<QxError> list) {
        return getString(R.string.dialog_error_saving_changes_message, new Object[]{QxError.concatenateErrorsIntoString(list)});
    }

    /* access modifiers changed from: protected */
    public void showErrorSavingDialog(List<QxError> list) {
        new AlertDialog.Builder(this).setTitle(getErrorSavingTitle(list)).setMessage(getErrorSavingMessage(list)).setPositiveButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: protected */
    public void showErrorDialog(int i, int i2) {
        new AlertDialog.Builder(this).setTitle(i).setMessage(i2).setPositiveButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: private */
    public void sendFirebaseEventForNumberOfCalcsOpenedInSession(String str) {
        if (UserManager.getInstance().getActiveUserId() != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(FirebaseEventsConstants.NUM_CALCS_OPENED_KEY, SharedPreferenceHelper.getInstance().getNumberOfCalculatorsOpened());
            QxFirebaseEventManager.getInstance(this).sendEventName(str, bundle);
            SharedPreferenceHelper.getInstance().setNumberOfCalculatorsOpened(0);
        }
    }

    public void showErrorDialogSupport(final String str) {
        new AlertDialog.Builder(this).setTitle(R.string.error_title_generic).setMessage(R.string.dialog_error_calculated_value).setNeutralButton(R.string.dialog_contact_support, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                QxMDActivity.this.sendEmailToSupport(str);
                dialogInterface.cancel();
            }
        }).setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: private */
    public void sendEmailToSupport(String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(MailTo.MAILTO_SCHEME));
        intent.putExtra("android.intent.extra.EMAIL", new String[]{getString(R.string.dialog_qxmd_support_email)});
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.dialog_qxmd_support_email_subject));
        intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, getString(R.string.dialog_qxmd_support_email_no_email_client), 0).show();
        }
    }
}
