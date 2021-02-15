package com.medscape.android.consult.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SwitchCompat;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.consult.interfaces.IGetNotificationPreferencesListener;
import com.medscape.android.consult.interfaces.IUpdateNotificationPreferencesListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.NotificationPreference;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.webmdrx.util.NetworkUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class ConsultPushPreferencesActivity extends AbstractBreadcrumbNavigableActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = ConsultPushPreferencesActivity.class.getSimpleName();
    private View mContainer;
    private SwitchCompat mFollowsSwitch;
    private SwitchCompat mMentionsSwitch;
    ArrayList<NotificationPreference> mNewNotificationPreferences;
    private ProgressBar mProgress;
    private SwitchCompat mRepliesSwitch;
    private View mRootView;
    ArrayList<NotificationPreference> mSavedNotificationPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_consult_push_preferences);
        setTitle(R.string.notifications_push_preferences);
        this.mRootView = findViewById(R.id.rootView);
        this.mContainer = findViewById(R.id.settings_container);
        this.mProgress = (ProgressBar) findViewById(R.id.progress);
        this.mFollowsSwitch = (SwitchCompat) findViewById(R.id.consult_push_follows_switch);
        this.mRepliesSwitch = (SwitchCompat) findViewById(R.id.consult_push_replies_switch);
        this.mMentionsSwitch = (SwitchCompat) findViewById(R.id.consult_push_mentions_switch);
        this.mFollowsSwitch.setOnCheckedChangeListener(this);
        this.mRepliesSwitch.setOnCheckedChangeListener(this);
        this.mMentionsSwitch.setOnCheckedChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        fetchConsultPushPreferences();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!compoundButton.isPressed()) {
            return;
        }
        if (NetworkUtil.isOnline(this)) {
            saveConsultPushPreferences();
            return;
        }
        handleError(getString(R.string.internet_required));
        compoundButton.setChecked(!z);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.home) {
            return super.onOptionsItemSelected(menuItem);
        }
        handleClose();
        return true;
    }

    private boolean hasUnsavedChanges() {
        readUI();
        if (!(this.mNewNotificationPreferences == null || this.mSavedNotificationPreferences == null)) {
            for (int i = 0; i < this.mSavedNotificationPreferences.size(); i++) {
                if (!this.mSavedNotificationPreferences.get(i).equals(this.mNewNotificationPreferences.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void handleClose() {
        if (!hasUnsavedChanges()) {
            finish();
        } else {
            showConfirmDiscardAlert();
        }
    }

    private void showConfirmDiscardAlert() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.notifications_confirm_discard_title));
            builder.setMessage(getResources().getString(R.string.notifications_confirm_discard_message));
            builder.setNegativeButton(getResources().getString(R.string.notifications_confirm_keep), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.notifications_confirm_discard), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ConsultPushPreferencesActivity.this.finish();
                }
            });
            builder.show();
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show discard message alert");
        }
    }

    /* access modifiers changed from: private */
    public void updateUI() {
        this.mNewNotificationPreferences = new ArrayList<>();
        Iterator<NotificationPreference> it = this.mSavedNotificationPreferences.iterator();
        while (it.hasNext()) {
            NotificationPreference next = it.next();
            if (next.mType == 2 && next.mDistribution == 2) {
                this.mFollowsSwitch.setChecked(next.mEnabled);
            } else if (next.mType == 1 && next.mDistribution == 2) {
                this.mRepliesSwitch.setChecked(next.mEnabled);
            } else if (next.mType == 3 && next.mDistribution == 2) {
                this.mMentionsSwitch.setChecked(next.mEnabled);
            }
            NotificationPreference notificationPreference = new NotificationPreference();
            notificationPreference.mEnabled = next.mEnabled;
            notificationPreference.mDistribution = next.mDistribution;
            notificationPreference.mType = next.mType;
            this.mNewNotificationPreferences.add(notificationPreference);
        }
        this.mContainer.setVisibility(0);
    }

    private void readUI() {
        ArrayList<NotificationPreference> arrayList = this.mNewNotificationPreferences;
        if (arrayList != null) {
            Iterator<NotificationPreference> it = arrayList.iterator();
            while (it.hasNext()) {
                NotificationPreference next = it.next();
                if (next.mType == 2 && next.mDistribution == 2) {
                    next.mEnabled = this.mFollowsSwitch.isChecked();
                } else if (next.mType == 1 && next.mDistribution == 2) {
                    next.mEnabled = this.mRepliesSwitch.isChecked();
                } else if (next.mType == 3 && next.mDistribution == 2) {
                    next.mEnabled = this.mMentionsSwitch.isChecked();
                }
            }
        }
    }

    public void onBackPressed() {
        handleClose();
    }

    /* access modifiers changed from: package-private */
    public void saveConsultPushPreferences() {
        try {
            readUI();
            ConsultDataManager.getInstance(this, this).updateNotificationPreferences(this, this.mNewNotificationPreferences, new IUpdateNotificationPreferencesListener() {
                public void onNotificationPreferencesUpdated() {
                    ConsultPushPreferencesActivity consultPushPreferencesActivity = ConsultPushPreferencesActivity.this;
                    consultPushPreferencesActivity.updatedSavedPreferences(consultPushPreferencesActivity.mNewNotificationPreferences);
                }

                public void onNotificationPreferencesFailedToUpdated() {
                    ConsultPushPreferencesActivity.this.updateUI();
                    ConsultPushPreferencesActivity consultPushPreferencesActivity = ConsultPushPreferencesActivity.this;
                    consultPushPreferencesActivity.handleError(consultPushPreferencesActivity.getString(R.string.message_pref_get_fail));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void updatedSavedPreferences(ArrayList<NotificationPreference> arrayList) {
        this.mSavedNotificationPreferences.clear();
        Iterator<NotificationPreference> it = arrayList.iterator();
        while (it.hasNext()) {
            NotificationPreference next = it.next();
            NotificationPreference notificationPreference = new NotificationPreference();
            notificationPreference.mEnabled = next.mEnabled;
            notificationPreference.mDistribution = next.mDistribution;
            notificationPreference.mType = next.mType;
            this.mSavedNotificationPreferences.add(notificationPreference);
        }
    }

    /* access modifiers changed from: private */
    public void handleError(String str) {
        hideProgress();
        if (StringUtil.isNullOrEmpty(str)) {
            str = getString(R.string.message_pref_get_fail);
        }
        MedscapeException medscapeException = new MedscapeException(str);
        medscapeException.showSnackBar(this.mRootView, 3000, getString(R.string.dismiss), new View.OnClickListener() {
            public final void onClick(View view) {
                MedscapeException.this.dismissSnackBar();
            }
        });
    }

    public void fetchConsultPushPreferences() {
        showProgress();
        if (!NetworkUtil.isOnline(this)) {
            handleError(getString(R.string.internet_required));
            return;
        }
        try {
            ConsultDataManager.getInstance(this, this).getNotificationPreferences(this, new IGetNotificationPreferencesListener() {
                public void onNotificationPreferencesReceived(ArrayList<NotificationPreference> arrayList) {
                    if (arrayList != null) {
                        ConsultPushPreferencesActivity.this.mSavedNotificationPreferences = arrayList;
                        ConsultPushPreferencesActivity.this.hideProgress();
                        ConsultPushPreferencesActivity.this.updateUI();
                        return;
                    }
                    ConsultPushPreferencesActivity consultPushPreferencesActivity = ConsultPushPreferencesActivity.this;
                    consultPushPreferencesActivity.handleError(consultPushPreferencesActivity.getString(R.string.message_pref_get_fail));
                }

                public void onNotificationPreferencesFailedToReceive() {
                    ConsultPushPreferencesActivity consultPushPreferencesActivity = ConsultPushPreferencesActivity.this;
                    consultPushPreferencesActivity.handleError(consultPushPreferencesActivity.getString(R.string.message_pref_get_fail));
                }
            });
        } catch (Exception unused) {
            handleError(getString(R.string.message_pref_get_fail));
        }
    }

    private void showProgress() {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void hideProgress() {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
    }
}
