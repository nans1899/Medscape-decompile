package com.ib.foreceup.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.facebook.share.internal.ShareConstants;
import com.ib.foreceup.R;

public class UpdateDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private boolean isDismissedOnButtonClick;
    private DialogInterface.OnClickListener onClickListener;
    private DialogInterface.OnDismissListener onDismissListener;

    public void onSaveInstanceState(Bundle bundle) {
    }

    public static UpdateDialogFragment newInstance(String str, String str2, String str3, String str4, String str5, boolean z, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnDismissListener onDismissListener2) {
        UpdateDialogFragment updateDialogFragment = new UpdateDialogFragment();
        updateDialogFragment.onClickListener = onClickListener2;
        updateDialogFragment.onDismissListener = onDismissListener2;
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, str2);
        bundle.putString("positiveButton", str3);
        bundle.putString("negativeButton", str4);
        bundle.putString("neutralButton", str5);
        updateDialogFragment.setCancelable(z);
        updateDialogFragment.setArguments(bundle);
        return updateDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String string = getArguments().getString("title");
        String string2 = getArguments().getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        String string3 = getArguments().getString("positiveButton");
        String string4 = getArguments().getString("negativeButton");
        String string5 = getArguments().getString("neutralButton");
        TextView textView = new TextView(new ContextThemeWrapper(getContext(), R.style.UpdateDialogText));
        SpannableString spannableString = new SpannableString(string2);
        Linkify.addLinks(spannableString, 1);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        AlertDialog.Builder view = new AlertDialog.Builder(getActivity()).setView((View) textView);
        if (string3 != null) {
            view.setPositiveButton((CharSequence) string3, (DialogInterface.OnClickListener) this);
        }
        if (string4 != null) {
            view.setNegativeButton((CharSequence) string4, (DialogInterface.OnClickListener) this);
        }
        if (string5 != null) {
            view.setNeutralButton((CharSequence) string5, (DialogInterface.OnClickListener) this);
        }
        if (!TextUtils.isEmpty(string)) {
            view.setTitle((CharSequence) string);
        }
        return view.create();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener2 = this.onDismissListener;
        if (onDismissListener2 != null && !this.isDismissedOnButtonClick) {
            onDismissListener2.onDismiss(dialogInterface);
        }
    }

    public void show(FragmentManager fragmentManager, String str) {
        if (fragmentManager.findFragmentByTag(str) == null) {
            super.show(fragmentManager, str);
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.isDismissedOnButtonClick = true;
        DialogInterface.OnClickListener onClickListener2 = this.onClickListener;
        if (onClickListener2 != null) {
            onClickListener2.onClick(dialogInterface, i);
        }
    }
}
