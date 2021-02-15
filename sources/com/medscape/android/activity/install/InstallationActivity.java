package com.medscape.android.activity.install;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.reference.ClinicalReferenceInstallationRequestActivity;
import com.medscape.android.task.PurgeFolderTask;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.updater.model.Update;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import java.io.File;
import java.util.List;

public class InstallationActivity extends BaseActivity implements OnUpdateListener, ViewSwitcher.ViewFactory {
    private static final int MSG_SET_UPDATE_PLIST = 2;
    private static final int SET_MAX_PROGRESS = 3;
    protected static final int SET_PROGRESS_LOADING_COUNT_INITIAL_MESSAGE = 6;
    protected static final int SET_PROGRESS_LOADING_COUNT_MESSAGE = 5;
    protected static final int SET_PROGRESS_MESSAGE = 4;
    private static final int START_COMPLETION_ACITIVITY = 12;
    protected static final int START_PROGRESS = 1;
    protected static final String TAG = "InstalltionActivity";
    public static int loadingCount = 1;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                InstallationActivity.this.mProgressbar.setProgress(Integer.parseInt(message.obj.toString()));
            } else if (message.what == 2) {
                InstallationActivity.this.setUpdateTextview(message.obj);
            } else if (message.what == 3) {
                InstallationActivity.this.mProgressbar.setMax(Integer.parseInt(message.obj.toString()));
            } else if (message.what == 4) {
                InstallationActivity.this.loadingTextView.setText(message.obj.toString());
            } else if (message.what == 5) {
                InstallationActivity.loadingCount++;
                InstallationActivity.this.loadingCountTextView.setText("Step " + InstallationActivity.loadingCount + " of " + message.obj.toString());
            } else if (message.what == 6) {
                InstallationActivity.loadingCount = 1;
                InstallationActivity.this.loadingCountTextView.setText("Step " + InstallationActivity.loadingCount + " of " + message.obj.toString());
            } else if (message.what == 11) {
                if (!InstallationActivity.this.isFinishing()) {
                    InstallationActivity.this.showDialog(11);
                }
            } else if (message.what == 15 && !InstallationActivity.this.isFinishing()) {
                InstallationActivity.this.showDialog(15);
            }
            return true;
        }
    });
    /* access modifiers changed from: private */
    public TextSwitcher loadingCountTextView;
    /* access modifiers changed from: private */
    public TextSwitcher loadingTextView;
    /* access modifiers changed from: private */
    public ProgressBar mProgressbar;
    protected String mTotalListSize;
    private List<Update> mUpdateList;
    protected UpdateManager mUpdater;

    /* access modifiers changed from: protected */
    public void setUpdateTextview(Object obj) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.drug_installtion_layout);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.DownloadProgressBar);
        this.mProgressbar = progressBar;
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_bg));
        Animation loadAnimation = AnimationUtils.loadAnimation(this, 17432576);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, 17432577);
        TextSwitcher textSwitcher = (TextSwitcher) findViewById(R.id.loadingCountTextView);
        this.loadingCountTextView = textSwitcher;
        textSwitcher.setFactory(this);
        this.loadingCountTextView.setInAnimation(loadAnimation);
        this.loadingCountTextView.setOutAnimation(loadAnimation2);
        this.loadingCountTextView.setText("");
        TextSwitcher textSwitcher2 = (TextSwitcher) findViewById(R.id.loadingTextView);
        this.loadingTextView = textSwitcher2;
        textSwitcher2.setFactory(this);
        this.loadingTextView.setInAnimation(loadAnimation);
        this.loadingTextView.setOutAnimation(loadAnimation2);
        this.loadingTextView.setText(getResources().getString(R.string.loading_text));
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        getSupportActionBar().hide();
    }

    public void onUpdateAvailable(int i, List<Update> list, int i2) {
        setUpdateList(list);
        Update update = getUpdateList().get(0);
        if (update.getUrl() == null || update.getUrl().equals("")) {
            Settings.singleton(this).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_PLIST_TEXT, update.getType());
            Handler handler = this.h;
            handler.sendMessage(Message.obtain(handler, 2, update.getType()));
            list.remove(0);
            return;
        }
        loadingCount = 0;
        this.mTotalListSize = String.valueOf(list.size());
        Settings.singleton(this).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_TOTAL_FILES, this.mTotalListSize);
        Handler handler2 = this.h;
        handler2.sendMessage(Message.obtain(handler2, 6, Integer.valueOf(list.size())));
        if (getUpdateList().size() > 0) {
            this.mUpdater.downloadAndInstallUpdate(2, getUpdateList().get(0), Constants.DIRECTORY_MAIN_CR, true);
        }
    }

    public void onUpdateComplete(int i, Update update) {
        if (getUpdateList().size() > 0 && update != null) {
            getUpdateList().remove(0);
        }
        if (!getUpdateList().get(0).getType().equals("purgeClinical")) {
            this.loadingTextView.setText("Downloading...");
            this.mUpdater.downloadAndInstallUpdate(1, getUpdateList().get(0), Constants.DIRECTORY_MAIN, false);
        } else {
            this.loadingTextView.setText("Executing...");
            new PurgeFolderTask(new File(Constants.DIRECTORY_MAIN), new PurgeFolderTask.PurgeListener() {
                public void onPurgeComplete() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException unused) {
                    }
                    InstallationActivity.this.mUpdater.markAllUpdatesCompleted(2);
                    InstallationActivity.this.showCompletedScreen();
                }
            }).execute(new File[]{new File(Constants.DIRECTORY_TEMP)});
        }
        Handler handler = this.h;
        handler.sendMessage(Message.obtain(handler, 5, this.mTotalListSize));
    }

    /* access modifiers changed from: private */
    public void showCompletedScreen() {
        startActivity(new Intent(this, ClinicalReferenceInstallationRequestActivity.class));
        finish();
    }

    public void onUpdateNotAvailable(int i) {
        setResult(0);
        Settings.singleton(this).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
        finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 12 && i2 == -1) {
            setResult(i2);
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i != 4 && super.onKeyDown(i, keyEvent);
    }

    public void onProgressUpdate(int i) {
        Handler handler = this.h;
        handler.sendMessage(Message.obtain(handler, 1, String.valueOf(i)));
    }

    public void setMaxProgress(int i) {
        Handler handler = this.h;
        handler.sendMessage(Message.obtain(handler, 3, String.valueOf(i)));
    }

    public View makeView() {
        TextView textView = new TextView(this);
        textView.setGravity(49);
        textView.setTextSize(14.0f);
        return textView;
    }

    public void setProgressMessage(String str) {
        Handler handler = this.h;
        handler.sendMessage(Message.obtain(handler, 4, str));
    }

    public void onNetworkError(int i) {
        saveDownlodUrl();
        if (i == 9) {
            this.h.sendEmptyMessage(11);
        } else if (i == 8) {
            this.h.sendEmptyMessage(15);
        } else if (i == 1) {
            this.h.sendEmptyMessage(11);
        }
    }

    private void saveDownlodUrl() {
        if (!Settings.singleton(this).getSetting(Constants.PREF_CLINICAL_INSTALLTION_PLIST_TEXT, "").equals("") && getUpdateList() != null && getUpdateList().size() > 0) {
            Settings.singleton(this).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_FAIL, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            StringBuilder sb = new StringBuilder();
            for (Update next : getUpdateList()) {
                if ((next.getUrl() == null || next.getUrl().equals("")) && (next.getType() == null || next.getType().equals(""))) {
                    sb.append(next.getUrl());
                    sb.append("|");
                }
            }
            Settings.singleton(this).saveSetting(Constants.PREF_CLINICAL_INSTALLTION_URL_LIST, sb.toString());
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i == 8) {
            return DialogUtil.showAlertDialog(8, (String) null, getResources().getString(R.string.alert_dialog_signup_required_message), this);
        }
        if (i == 15) {
            return DialogUtil.showAlertDialog(11, (String) null, "Not able to complete download at this time.", this);
        }
        if (i == 10) {
            return DialogUtil.showAlertDialog(10, (String) null, "Update Not Available", this);
        }
        if (i != 11) {
            return null;
        }
        return DialogUtil.showAlertDialog(11, (String) null, getResources().getString(R.string.alert_dialog_clinical_download_network_connection_interrupt_message), this);
    }

    public void setUpdateList(List<Update> list) {
        this.mUpdateList = list;
    }

    public List<Update> getUpdateList() {
        return this.mUpdateList;
    }

    public void retryDownload() {
        if (!Util.isOnline(this)) {
            this.h.sendEmptyMessage(11);
        } else if (getUpdateList() == null || getUpdateList().size() == 0) {
            this.mUpdater.getReferencePList();
        } else {
            this.mUpdater.downloadAndInstallUpdate(2, getUpdateList().get(0), Constants.DIRECTORY_MAIN_CR, true);
        }
    }
}
