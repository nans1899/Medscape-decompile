package webmd.com.environmentswitcher;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.EnvironmentConfig;

public class SwitcherDialog extends DialogFragment {
    Context context;
    String[] options;

    public Dialog onCreateDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        this.context = activity;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setSingleChoiceItems((CharSequence[]) this.options, setSelectedOption(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EnvironmentManager.getInstance(SwitcherDialog.this.context).saveEnvironment(SwitcherDialog.this.options[i]);
                SwitcherDialog.this.dismiss();
            }
        });
        return builder.create();
    }

    public void setOptions(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        this.options = strArr2;
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
    }

    private int setSelectedOption() {
        String string = PreferenceManager.getDefaultSharedPreferences(this.context).getString(Constants.pref_switcher_option, EnvironmentConfig.ENV_PROD);
        int i = 0;
        while (true) {
            String[] strArr = this.options;
            if (i >= strArr.length) {
                return 0;
            }
            if (strArr[i].equals(string)) {
                return i;
            }
            i++;
        }
    }
}
