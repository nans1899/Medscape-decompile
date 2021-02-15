package com.adobe.mobile;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MessageFullScreenActivity extends AdobeMarketingActivity {
    protected static final String MESSAGE_STATE_MESSAGE_ID = "MessageFullScreenActivity.messageId";
    protected static final String MESSAGE_STATE_REPLACED_HTML = "MessageFullScreenActivity.replacedHtml";
    protected MessageFullScreen message;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            MessageFullScreen restoreFromSavedState = restoreFromSavedState(bundle);
            this.message = restoreFromSavedState;
            Messages.setCurrentMessageFullscreen(restoreFromSavedState);
        } else {
            this.message = Messages.getCurrentFullscreenMessage();
        }
        if (messageIsValid()) {
            this.message.messageFullScreenActivity = this;
            requestWindowFeature(1);
            setContentView(new RelativeLayout(this));
        }
    }

    public void onResume() {
        super.onResume();
        if (messageIsValid()) {
            try {
                final ViewGroup viewGroup = (ViewGroup) findViewById(16908290);
                if (viewGroup == null) {
                    StaticMethods.logErrorFormat("Messages - unable to get root view group from os", new Object[0]);
                    finish();
                    overridePendingTransition(0, 0);
                    return;
                }
                viewGroup.post(new Runnable() {
                    public void run() {
                        MessageFullScreenActivity.this.message.rootViewGroup = viewGroup;
                        MessageFullScreenActivity.this.message.showInRootViewGroup();
                    }
                });
            } catch (NullPointerException e) {
                StaticMethods.logWarningFormat("Messages - content view is in undefined state (%s)", e.getMessage());
                finish();
                overridePendingTransition(0, 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(MESSAGE_STATE_MESSAGE_ID, this.message.messageId);
        bundle.putString(MESSAGE_STATE_REPLACED_HTML, this.message.replacedHtml);
        super.onSaveInstanceState(bundle);
    }

    public void onBackPressed() {
        MessageFullScreen messageFullScreen = this.message;
        if (messageFullScreen != null) {
            messageFullScreen.isVisible = false;
            this.message.viewed();
        }
        finish();
        overridePendingTransition(0, 0);
    }

    private boolean messageIsValid() {
        if (this.message != null) {
            return true;
        }
        StaticMethods.logWarningFormat("Messages - unable to display fullscreen message, message is undefined", new Object[0]);
        Messages.setCurrentMessage((Message) null);
        finish();
        overridePendingTransition(0, 0);
        return false;
    }

    private MessageFullScreen restoreFromSavedState(Bundle bundle) {
        MessageFullScreen fullScreenMessageById = Messages.getFullScreenMessageById(bundle.getString(MESSAGE_STATE_MESSAGE_ID));
        if (fullScreenMessageById != null) {
            fullScreenMessageById.replacedHtml = bundle.getString(MESSAGE_STATE_REPLACED_HTML);
        }
        return fullScreenMessageById;
    }
}
