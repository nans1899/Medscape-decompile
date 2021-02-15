package com.webmd.webmdrx.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.webmd.webmdrx.R;

public class ErrorFragmentDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private Intent action;
    private String errorMessage;

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.ErrorDialogStyle);
        builder.setMessage((CharSequence) this.errorMessage);
        builder.setPositiveButton((CharSequence) getContext().getString(R.string.ok), (DialogInterface.OnClickListener) this);
        final AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                TextView textView = (TextView) create.findViewById(16908299);
                if (textView != null) {
                    textView.setTextSize(1, 16.0f);
                }
            }
        });
        return create;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setAction(Intent intent) {
        this.action = intent;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dismiss();
        if (this.action != null) {
            getActivity().finish();
        } else {
            dismiss();
        }
    }
}
