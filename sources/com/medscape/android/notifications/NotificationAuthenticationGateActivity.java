package com.medscape.android.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.R;
import com.medscape.android.activity.AdBlockerWebViewAcitivity;
import com.medscape.android.activity.interactions.DrugInteractionActivity;
import com.medscape.android.activity.saved.views.SaveActivity;
import com.medscape.android.analytics.remoteconfig.RemoteConfig;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.forceup.ForceUpManager;
import com.medscape.android.search.MedscapeSearchActivity;
import com.medscape.android.util.GDPRFailureLog;
import com.medscape.android.util.GetAdvertisingIdTask;
import com.medscape.android.util.RedirectConstants;
import com.medscape.android.util.RedirectHandler;
import com.medscape.android.util.Util;
import com.medscape.android.welcome.WelcomeActivity;
import com.wbmd.wbmddatacompliance.callbacks.IShowAcceptDialogCallback;
import com.wbmd.wbmddatacompliance.gdpr.GDPRState;
import com.wbmd.wbmddatacompliance.gdpr.GDPRStateManager;
import com.wbmd.wbmddatacompliance.utils.Constants;
import com.webmd.wbmdomnituremanager.ProfessionalOmnitureData;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.Map;

public class NotificationAuthenticationGateActivity extends BaseActivity {
    private static final String TAG = "_Notifi";
    boolean addParent = false;
    private BroadcastReceiver gdprReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action;
            if (intent != null && (action = intent.getAction()) != null) {
                if (action.equals(Constants.BROADCAST_ACCEPT_ACTION)) {
                    NotificationAuthenticationGateActivity.this.performAppStartup();
                    OmnitureManager.get().trackModuleAbsolute(NotificationAuthenticationGateActivity.this.getApplicationContext(), (String) null, "gdpr-accept", (String) null, (Map<String, Object>) null);
                } else if (action.equals(Constants.BROADCAST_ACTIVITY_VIEW)) {
                    OmnitureManager.get().trackPageView(NotificationAuthenticationGateActivity.this.getApplicationContext(), (String) null, "gdpr-roadblock", (String) null, (String) null, (String) null, (Map<String, Object>) null);
                }
            }
        }
    };
    String mPayload;
    String mVoiceQuery;
    RedirectHandler redirectHandler;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.loadin_layout);
        new ProfessionalOmnitureData(this).refreshInitialData();
        getSupportActionBar().hide();
        Util.resetForceupAndAuthFlags(this);
        if ((getIntent().getFlags() & 1048576) == 0) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey("payload")) {
                this.mPayload = extras.getString("payload");
            } else if (getIntent().getData() != null) {
                this.mPayload = getIntent().getData().toString();
            }
        } else {
            this.mPayload = "";
        }
        if (getIntent().hasExtra("voice_query")) {
            this.mVoiceQuery = getIntent().getStringExtra("voice_query");
        }
        this.redirectHandler = new RedirectHandler(true);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.gdprReceiver, new IntentFilter(Constants.BROADCAST_ACCEPT_ACTION));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.gdprReceiver, new IntentFilter(Constants.BROADCAST_ACTIVITY_VIEW));
        new GetAdvertisingIdTask(this).execute(new Void[0]);
        new RemoteConfig().fetchRemoteConfig(this);
        checkGdpr();
    }

    private void checkGdpr() {
        GDPRStateManager gDPRStateManager = new GDPRStateManager(getApplicationContext(), new GDPRFailureLog(this));
        if (GDPRState.getInstance(this).getStatus().equals(GDPRState.STATE_NOT_DETERMINED) || GDPRState.getInstance(this).getStatus().equals(GDPRState.STATE_SHOWING_ROADBLOCK)) {
            OmnitureManager.get().setOmniturePrivacyStatus(false, this);
        }
        gDPRStateManager.shouldShowAcceptancePrompt(new IShowAcceptDialogCallback() {
            public void shouldShowAcceptancePromptResult(boolean z) {
                if (z) {
                    NotificationAuthenticationGateActivity.this.startActivity(Util.getGDPRRoadBlock(NotificationAuthenticationGateActivity.this));
                    return;
                }
                NotificationAuthenticationGateActivity.this.performAppStartup();
            }
        });
    }

    private void handleVoiceCommandShortcut(String str) {
        Intent intent;
        if (str.equals("saved articles")) {
            intent = new Intent(this, SaveActivity.class);
        } else if (str.equals("interactions")) {
            intent = new Intent(this, DrugInteractionActivity.class);
        } else {
            intent = new Intent(this, MedscapeSearchActivity.class);
        }
        intent.putExtra("voice_query", str);
        startActivity(intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null && extras.containsKey("payload")) {
            this.mPayload = extras.getString("payload");
        }
        checkGdpr();
    }

    /* access modifiers changed from: private */
    public void performAppStartup() {
        OmnitureManager.get().setOmniturePrivacyStatus(true, this);
        Util.updateMediaNetGDPRStatus(GDPRState.getInstance(this));
        if (Util.isAdBlockerInstalled()) {
            startAdBlocker();
        } else {
            checkIfLoggedIn();
        }
    }

    /* access modifiers changed from: private */
    public void initializeForceUp() {
        Log.d(TAG, "configureForceUpComponent: starting forceup");
        new ForceUpManager().initializeForceup(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.gdprReceiver);
        super.onDestroy();
    }

    private void checkIfLoggedIn() {
        if (!AccountProvider.isUserLoggedIn(this)) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra(RedirectConstants.REDIRECT_BUNDLE_KEY, this.mPayload);
            intent.setFlags(335577088);
            startActivity(intent);
            return;
        }
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                NotificationAuthenticationGateActivity.this.initializeForceUp();
            }
        }, 200);
        if (this.mPayload != null) {
            startDeepLinkActivity();
        } else {
            handleVoiceCommandShortcut(this.mVoiceQuery);
        }
    }

    private void startDeepLinkActivity() {
        if (isTaskRoot()) {
            this.addParent = true;
        } else {
            this.addParent = false;
        }
        this.redirectHandler.handleRedirect(this, this.mPayload, this.addParent);
        finish();
    }

    private void startAdBlocker() {
        startActivityForResult(new Intent(this, AdBlockerWebViewAcitivity.class), com.medscape.android.Constants.AD_BLOCKER_REQUEST_CODE);
    }
}
