package com.medscape.android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.medscape.android.R;
import com.wbmd.wbmdcommons.logging.Trace;

public class MedscapeException extends Throwable {
    static String TAG = MedscapeException.class.getSimpleName();
    private int mCode;
    /* access modifiers changed from: private */
    public String mMessage;
    Snackbar mSnackBar;
    private String mTitle;

    public void log() {
    }

    public MedscapeException(String str) {
        this.mMessage = str;
    }

    public MedscapeException(String str, String str2) {
        this.mTitle = str;
        this.mMessage = str2;
    }

    public MedscapeException(int i, String str) {
        this.mCode = i;
        this.mMessage = str;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public void showSnackBar(View view, int i, String str, View.OnClickListener onClickListener) {
        if (view != null && view.getContext() != null) {
            final View view2 = view;
            final int i2 = i;
            final String str2 = str;
            final View.OnClickListener onClickListener2 = onClickListener;
            view.postDelayed(new Runnable() {
                public void run() {
                    MedscapeException medscapeException = MedscapeException.this;
                    medscapeException.mSnackBar = Snackbar.make(view2, (CharSequence) medscapeException.mMessage, i2);
                    String str = str2;
                    if (!(str == null || str.trim().equalsIgnoreCase("") || onClickListener2 == null)) {
                        MedscapeException.this.mSnackBar.setAction((CharSequence) str2, onClickListener2);
                        MedscapeException.this.mSnackBar.setActionTextColor(ContextCompat.getColor(view2.getContext(), R.color.alert_dialogue_accent));
                    }
                    View view = MedscapeException.this.mSnackBar.getView();
                    TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                    textView.setTextColor(-1);
                    textView.setMaxLines(10);
                    view.setBackgroundColor(view2.getResources().getColor(R.color.medscapedarkblue));
                    MedscapeException.this.mSnackBar.show();
                }
            }, 200);
        }
    }

    public void showSnackBar(View view, int i) {
        showSnackBar(view, i, (String) null, (View.OnClickListener) null);
    }

    public void dismissSnackBar() {
        Snackbar snackbar = this.mSnackBar;
        if (snackbar != null && snackbar.isShown()) {
            this.mSnackBar.dismiss();
        }
    }

    public void showToast(Context context, int i) {
        String str;
        if (context != null && (str = this.mMessage) != null) {
            Toast makeText = Toast.makeText(context, str, i);
            if (!(makeText == null || makeText.getView() == null || context.getResources() == null)) {
                makeText.getView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.custom_toast));
            }
            makeText.show();
        }
    }

    public void showAlert(Activity activity, String str, DialogInterface.OnClickListener onClickListener, String str2, DialogInterface.OnClickListener onClickListener2) {
        try {
            if (!StringUtil.isNotEmpty(this.mTitle)) {
                if (!StringUtil.isNotEmpty(this.mMessage)) {
                    Trace.w(TAG, "Not title or message for alert");
                    return;
                }
            }
            boolean z = false;
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.BlackTextDialog);
            if (StringUtil.isNotEmpty(this.mTitle)) {
                builder.setTitle(this.mTitle);
            }
            if (StringUtil.isNotEmpty(this.mMessage)) {
                builder.setMessage(this.mMessage);
            }
            boolean z2 = true;
            if (StringUtil.isNotEmpty(str) && onClickListener != null) {
                builder.setPositiveButton(str, onClickListener);
                z = true;
            }
            if (!StringUtil.isNotEmpty(str2) || onClickListener2 == null) {
                z2 = z;
            } else {
                builder.setNegativeButton(str2, onClickListener2);
            }
            if (z2) {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            Trace.w(MedscapeException.TAG, "Showing medscape exception as alert");
                            builder.show();
                        } catch (Exception unused) {
                            Trace.w(MedscapeException.TAG, "Failed to show medscape exception as alert");
                        }
                    }
                });
            } else {
                Trace.w(TAG, "No button set for alert");
            }
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show alert");
        }
    }
}
