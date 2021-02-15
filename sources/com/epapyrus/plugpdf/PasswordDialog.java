package com.epapyrus.plugpdf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class PasswordDialog {
    private Dialog dialog;
    /* access modifiers changed from: private */
    public EditText password;

    public abstract void onInputtedPassword(String str);

    public PasswordDialog(Context context) {
        this.dialog = createDialog(context);
    }

    public Dialog createDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(10, 10, 10, 10);
        TextView textView = new TextView(context);
        this.password = new EditText(context);
        textView.setText("PASSWORD: ");
        this.password.setInputType(129);
        this.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        textView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.password.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        linearLayout.addView(textView);
        linearLayout.addView(this.password);
        builder.setView(linearLayout);
        builder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PasswordDialog passwordDialog = PasswordDialog.this;
                passwordDialog.onInputtedPassword(passwordDialog.password.getText().toString());
                dialogInterface.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    public void show() {
        this.dialog.show();
    }
}
