package com.medscape.android.activity.interactions;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.interfaces.INewListCreationListener;

public class SaveListDialogFragment extends DialogFragment {
    TextView cancel;
    INewListCreationListener mListener;
    EditText name;
    View rootView;
    TextView submit;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.drug_interaction_create_list_layout, viewGroup, false);
        setUpViews();
        setUpListeners();
        return this.rootView;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.requestWindowFeature(1);
        return onCreateDialog;
    }

    public void onResume() {
        super.onResume();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(-1, -2);
        }
    }

    private void setUpViews() {
        this.cancel = (TextView) this.rootView.findViewById(R.id.drug_interaction_dialog_cancel);
        this.submit = (TextView) this.rootView.findViewById(R.id.drug_interaction_dialog_submit);
        EditText editText = (EditText) this.rootView.findViewById(R.id.drug_interaction_dialog_list_name);
        this.name = editText;
        editText.requestFocus();
    }

    private void setUpListeners() {
        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SaveListDialogFragment.this.dismiss();
            }
        });
        this.submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SaveListDialogFragment.this.name.getText().length() > 0 && SaveListDialogFragment.this.mListener != null) {
                    SaveListDialogFragment.this.mListener.onNewListCreation(SaveListDialogFragment.this.name.getText().toString());
                    SaveListDialogFragment.this.dismiss();
                }
            }
        });
    }

    public void setListener(INewListCreationListener iNewListCreationListener) {
        this.mListener = iNewListCreationListener;
    }
}
