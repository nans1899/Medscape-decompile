package com.webmd.wbmdproffesionalauthentication.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.webmd.wbmdproffesionalauthentication.R;

public class ErrorDialog extends DialogFragment {
    private String errorMsg = "";
    boolean isGoBack = false;
    private String title = "";

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.alertDialog);
        String str = this.title;
        if (str != null && !str.isEmpty()) {
            builder.setTitle((CharSequence) this.title);
        }
        String str2 = this.errorMsg;
        if (str2 != null) {
            builder.setMessage((CharSequence) str2);
        }
        builder.setPositiveButton(R.string.registration_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ErrorDialog.this.isGoBack) {
                    ErrorDialog.this.getActivity().onBackPressed();
                } else {
                    ErrorDialog.this.dismiss();
                }
            }
        });
        return builder.create();
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setGoBack(boolean z) {
        this.isGoBack = z;
    }
}
