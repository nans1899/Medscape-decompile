package com.appboy.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.appboy.Appboy;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;

public class AppboyFeedbackFragment extends Fragment {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyFeedbackFragment.class);
    private Button mCancelButton;
    private View.OnClickListener mCancelListener;
    /* access modifiers changed from: private */
    public EditText mEmailEditText;
    /* access modifiers changed from: private */
    public boolean mErrorMessageShown;
    /* access modifiers changed from: private */
    public IFeedbackFinishedListener mFeedbackFinishedListener;
    /* access modifiers changed from: private */
    public CheckBox mIsBugCheckBox;
    /* access modifiers changed from: private */
    public EditText mMessageEditText;
    private int mOriginalSoftInputMode;
    private Button mSendButton;
    private TextWatcher mSendButtonWatcher;
    private View.OnClickListener mSendListener;

    public enum FeedbackResult {
        SUBMITTED,
        CANCELLED
    }

    public interface IFeedbackFinishedListener {
        String beforeFeedbackSubmitted(String str);

        void onFeedbackFinished(FeedbackResult feedbackResult);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mSendButtonWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (AppboyFeedbackFragment.this.mErrorMessageShown) {
                    boolean unused = AppboyFeedbackFragment.this.ensureSendButton();
                }
            }
        };
        this.mCancelListener = new View.OnClickListener() {
            public void onClick(View view) {
                AppboyFeedbackFragment.this.hideSoftKeyboard();
                if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                    AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(FeedbackResult.CANCELLED);
                }
                AppboyFeedbackFragment.this.clearData();
            }
        };
        this.mSendListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (AppboyFeedbackFragment.this.ensureSendButton()) {
                    AppboyFeedbackFragment.this.hideSoftKeyboard();
                    boolean isChecked = AppboyFeedbackFragment.this.mIsBugCheckBox.isChecked();
                    String obj = AppboyFeedbackFragment.this.mMessageEditText.getText().toString();
                    String obj2 = AppboyFeedbackFragment.this.mEmailEditText.getText().toString();
                    if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                        obj = AppboyFeedbackFragment.this.mFeedbackFinishedListener.beforeFeedbackSubmitted(obj);
                    }
                    Appboy.getInstance(AppboyFeedbackFragment.this.getActivity()).submitFeedback(obj2, obj, isChecked);
                    if (AppboyFeedbackFragment.this.mFeedbackFinishedListener != null) {
                        AppboyFeedbackFragment.this.mFeedbackFinishedListener.onFeedbackFinished(FeedbackResult.SUBMITTED);
                    }
                    AppboyFeedbackFragment.this.clearData();
                    return;
                }
                boolean unused = AppboyFeedbackFragment.this.mErrorMessageShown = true;
            }
        };
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.com_appboy_feedback, viewGroup, false);
        this.mCancelButton = (Button) inflate.findViewById(R.id.com_appboy_feedback_cancel);
        this.mSendButton = (Button) inflate.findViewById(R.id.com_appboy_feedback_send);
        this.mIsBugCheckBox = (CheckBox) inflate.findViewById(R.id.com_appboy_feedback_is_bug);
        this.mMessageEditText = (EditText) inflate.findViewById(R.id.com_appboy_feedback_message);
        this.mEmailEditText = (EditText) inflate.findViewById(R.id.com_appboy_feedback_email);
        this.mMessageEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.addTextChangedListener(this.mSendButtonWatcher);
        this.mCancelButton.setOnClickListener(this.mCancelListener);
        this.mSendButton.setOnClickListener(this.mSendListener);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getActivity()).logFeedbackDisplayed();
        FragmentActivity activity = getActivity();
        Window window = activity.getWindow();
        this.mOriginalSoftInputMode = window.getAttributes().softInputMode;
        window.setSoftInputMode(16);
        Appboy.getInstance(activity).logFeedbackDisplayed();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMessageEditText.removeTextChangedListener(this.mSendButtonWatcher);
        this.mEmailEditText.removeTextChangedListener(this.mSendButtonWatcher);
    }

    public void setFeedbackFinishedListener(IFeedbackFinishedListener iFeedbackFinishedListener) {
        this.mFeedbackFinishedListener = iFeedbackFinishedListener;
    }

    public EditText getMessageEditText() {
        return this.mMessageEditText;
    }

    public EditText getEmailEditText() {
        return this.mEmailEditText;
    }

    private boolean validatedMessage() {
        boolean z = this.mMessageEditText.getText() != null && !StringUtils.isNullOrBlank(this.mMessageEditText.getText().toString());
        if (z) {
            this.mMessageEditText.setError((CharSequence) null);
        } else {
            displayMessageTextError(R.string.com_appboy_feedback_form_invalid_message);
        }
        return z;
    }

    private boolean validatedEmail() {
        boolean z = true;
        boolean z2 = this.mEmailEditText.getText() != null && !StringUtils.isNullOrBlank(this.mEmailEditText.getText().toString()) && ValidationUtils.isValidEmailAddress(this.mEmailEditText.getText().toString());
        if (this.mEmailEditText.getText() == null || !StringUtils.isNullOrBlank(this.mEmailEditText.getText().toString())) {
            z = false;
        }
        if (z2) {
            this.mEmailEditText.setError((CharSequence) null);
        } else if (z) {
            displayEmailTextError(R.string.com_appboy_feedback_form_empty_email);
        } else {
            displayEmailTextError(R.string.com_appboy_feedback_form_invalid_email);
        }
        return z2;
    }

    private void displayEmailTextError(int i) {
        if (getActivity() != null) {
            this.mEmailEditText.setError(getResources().getString(i));
        } else {
            AppboyLogger.w(TAG, "Activity is null. Cannot set feedback form email error message");
        }
    }

    private void displayMessageTextError(int i) {
        if (getActivity() != null) {
            this.mMessageEditText.setError(getResources().getString(i));
        } else {
            AppboyLogger.w(TAG, "Activity is null. Cannot set feedback form message error.");
        }
    }

    /* access modifiers changed from: private */
    public boolean ensureSendButton() {
        return validatedMessage() & validatedEmail();
    }

    /* access modifiers changed from: private */
    public void clearData() {
        this.mEmailEditText.setText("");
        this.mMessageEditText.setText("");
        this.mIsBugCheckBox.setChecked(false);
        this.mErrorMessageShown = false;
        this.mEmailEditText.setError((CharSequence) null);
        this.mMessageEditText.setError((CharSequence) null);
    }

    /* access modifiers changed from: private */
    public void hideSoftKeyboard() {
        FragmentActivity activity = getActivity();
        activity.getWindow().setSoftInputMode(this.mOriginalSoftInputMode);
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
