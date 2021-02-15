package com.webmd.wbmdcmepulse.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.utils.Extensions;

public class CMEPulseException extends Throwable {
    private int mCode;
    private String mMessage;
    Snackbar mSnackBar;

    public void log() {
    }

    public CMEPulseException(String str) {
        this.mMessage = str;
    }

    public CMEPulseException(int i, String str) {
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
            this.mSnackBar = Snackbar.make(view, (CharSequence) this.mMessage, i);
            if (!Extensions.isStringNullOrEmpty(str) && onClickListener != null) {
                this.mSnackBar.setAction((CharSequence) str, onClickListener);
            }
            View view2 = this.mSnackBar.getView();
            ((TextView) view2.findViewById(R.id.snackbar_text)).setTextColor(-1);
            this.mSnackBar.setActionTextColor(ContextCompat.getColor(view.getContext(), R.color.alert_dialogue_accent));
            view2.setBackgroundColor(view.getResources().getColor(R.color.app_accent_color));
            this.mSnackBar.show();
        }
    }

    public void showToast(Context context, int i) {
        String str;
        if (context != null && (str = this.mMessage) != null) {
            Toast makeText = Toast.makeText(context, str, i);
            if (!(makeText == null || makeText.getView() == null || context.getResources() == null)) {
                makeText.getView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toast));
            }
            makeText.show();
        }
    }
}
