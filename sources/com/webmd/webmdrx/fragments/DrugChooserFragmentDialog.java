package com.webmd.webmdrx.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.appboy.Constants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.adapters.QuantityAdapter;
import com.webmd.webmdrx.models.Drug;
import com.webmd.webmdrx.models.PackageSize;
import com.webmd.webmdrx.models.Quantity;
import com.webmd.webmdrx.util.Util;
import java.util.List;

public class DrugChooserFragmentDialog extends DialogFragment {
    Activity attachedActivity;
    /* access modifiers changed from: private */
    public boolean isKeyboardOpen;
    /* access modifiers changed from: private */
    public OnDrugSelectedListener mDrugSelectionListener;
    private int mFilter;
    /* access modifiers changed from: private */
    public OnTouchOutOrCanceledListener mOnCancelListener;
    /* access modifiers changed from: private */
    public OnPackageSizeSelectedListener mPackageSelectionListener;
    /* access modifiers changed from: private */
    public OnQuantitySelectedListener mQuantitySelectionListener;
    private int mSelected = 0;
    private String mTitle;
    private int maxHeight;
    private int maxWidth;
    /* access modifiers changed from: private */
    public Drug[] optionDrugs;
    /* access modifiers changed from: private */
    public String[] options;
    ArrayAdapter<String> optionsAdapter;
    private TextView titleView;

    public interface OnDrugSelectedListener {
        void onDrugSelected(Drug drug);
    }

    public interface OnPackageSizeSelectedListener {
        void onPackageSizeSelectedListener(String str);
    }

    public interface OnQuantitySelectedListener {
        void onQuantitySelected(String str);
    }

    public interface OnTouchOutOrCanceledListener {
        void onCancel();
    }

    public void setMaxHeight(int i) {
        this.maxHeight = i;
    }

    public void setWidth(int i) {
        this.maxWidth = i;
    }

    public void onCreate(Bundle bundle) {
        this.titleView = (TextView) getActivity().getLayoutInflater().inflate(R.layout.drug_chooser_dialog_title, (ViewGroup) null);
        super.onCreate(bundle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        this.optionsAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_dialog_row_view, this.options);
        int i = this.mFilter;
        if (i == 0 || i == 1 || i == 2) {
            if (this.mFilter == 0) {
                this.mTitle = getString(R.string.prescription_detail_drug);
            }
            if (this.mFilter == 1) {
                this.mTitle = getString(R.string.prescription_detail_form);
            }
            if (this.mFilter == 2) {
                this.mTitle = getString(R.string.prescription_detail_dosage);
            }
            this.titleView.setText(this.mTitle);
            builder.setCustomTitle(this.titleView).setSingleChoiceItems((ListAdapter) this.optionsAdapter, this.mSelected, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DrugChooserFragmentDialog.this.mDrugSelectionListener.onDrugSelected(DrugChooserFragmentDialog.this.optionDrugs[i]);
                    DrugChooserFragmentDialog.this.dismiss();
                }
            });
        }
        if (this.mFilter == 5) {
            String string = getString(R.string.package_size);
            this.mTitle = string;
            this.titleView.setText(string);
            builder.setCustomTitle(this.titleView).setSingleChoiceItems((ListAdapter) this.optionsAdapter, this.mSelected, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DrugChooserFragmentDialog.this.mPackageSelectionListener.onPackageSizeSelectedListener(DrugChooserFragmentDialog.this.options[i]);
                    DrugChooserFragmentDialog.this.dismiss();
                }
            });
        }
        if (this.mFilter == 3) {
            this.optionsAdapter = new QuantityAdapter(getContext(), this.options, new QuantityAdapter.CustomQuantityListener() {
                public void onCustomQuantity() {
                }
            });
            this.titleView.setText(getString(R.string.prescription_detail_quantity));
            builder.setCustomTitle(this.titleView).setSingleChoiceItems((ListAdapter) this.optionsAdapter, this.mSelected, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DrugChooserFragmentDialog.this.mQuantitySelectionListener.onQuantitySelected(DrugChooserFragmentDialog.this.options[i]);
                    DrugChooserFragmentDialog.this.dismiss();
                }
            });
        }
        return builder.create();
    }

    public void onResume() {
        if (!(getDialog() == null || getDialog().getWindow() == null)) {
            View decorView = getDialog().getWindow().getDecorView();
            decorView.measure(View.MeasureSpec.makeMeasureSpec(this.maxHeight, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.maxHeight, Integer.MIN_VALUE));
            getDialog().getWindow().setLayout(this.maxWidth, decorView.getMeasuredHeight());
        }
        super.onResume();
    }

    public void onAttach(Activity activity) {
        this.attachedActivity = activity;
        super.onAttach(activity);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        OnTouchOutOrCanceledListener onTouchOutOrCanceledListener = this.mOnCancelListener;
        if (onTouchOutOrCanceledListener != null) {
            onTouchOutOrCanceledListener.onCancel();
        }
        super.onDismiss(dialogInterface);
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
    }

    public void onPause() {
        super.onPause();
        if (this.isKeyboardOpen) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).toggleSoftInput(1, 0);
        }
    }

    private void showCustomQuantityDialog() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_input, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        final EditText editText = (EditText) inflate.findViewById(R.id.d_input_edit_text_input);
        final Button button = (Button) inflate.findViewById(R.id.input_dialog_button_positive);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i;
                try {
                    i = Integer.parseInt(editText.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    i = 0;
                }
                if (i > 0) {
                    OnQuantitySelectedListener access$400 = DrugChooserFragmentDialog.this.mQuantitySelectionListener;
                    access$400.onQuantitySelected(i + "");
                } else {
                    DrugChooserFragmentDialog.this.mQuantitySelectionListener.onQuantitySelected(editText.getText().toString());
                }
                create.dismiss();
                DrugChooserFragmentDialog.this.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.input_dialog_button_negative)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View currentFocus = create.getCurrentFocus();
                if (currentFocus != null) {
                    ((InputMethodManager) DrugChooserFragmentDialog.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                }
                boolean unused = DrugChooserFragmentDialog.this.isKeyboardOpen = false;
                if (DrugChooserFragmentDialog.this.mOnCancelListener != null) {
                    DrugChooserFragmentDialog.this.mOnCancelListener.onCancel();
                }
                create.cancel();
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                int i2;
                String obj = editText.getText().toString();
                if (keyEvent.getKeyCode() != 66) {
                    return false;
                }
                if (obj.length() >= 1 && obj.length() <= 5) {
                    int parseInt = obj.length() < 5 ? Integer.parseInt(obj) : 0;
                    if (parseInt >= 1 && parseInt <= 1000) {
                        try {
                            i2 = Integer.parseInt(editText.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            i2 = 0;
                        }
                        if (i2 > 0) {
                            OnQuantitySelectedListener access$400 = DrugChooserFragmentDialog.this.mQuantitySelectionListener;
                            access$400.onQuantitySelected(i2 + "");
                        } else {
                            DrugChooserFragmentDialog.this.mQuantitySelectionListener.onQuantitySelected(editText.getText().toString());
                        }
                        create.dismiss();
                        DrugChooserFragmentDialog.this.dismiss();
                        return false;
                    }
                }
                return true;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() < 1) {
                    button.setEnabled(false);
                    button.setAlpha(0.5f);
                    return;
                }
                int parseInt = charSequence.length() < 5 ? Integer.parseInt(charSequence.toString()) : 0;
                if (parseInt < 1 || parseInt > 1000) {
                    button.setEnabled(false);
                    button.setAlpha(0.5f);
                    return;
                }
                button.setEnabled(true);
                button.setAlpha(1.0f);
            }
        });
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (DrugChooserFragmentDialog.this.isKeyboardOpen) {
                    ((InputMethodManager) DrugChooserFragmentDialog.this.getActivity().getSystemService("input_method")).toggleSoftInput(1, 0);
                }
                boolean unused = DrugChooserFragmentDialog.this.isKeyboardOpen = false;
            }
        });
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (DrugChooserFragmentDialog.this.isKeyboardOpen) {
                    ((InputMethodManager) DrugChooserFragmentDialog.this.getActivity().getSystemService("input_method")).toggleSoftInput(1, 0);
                }
                boolean unused = DrugChooserFragmentDialog.this.isKeyboardOpen = false;
            }
        });
        create.show();
        editText.requestFocus();
        Util.showKeyboard(getContext());
        this.isKeyboardOpen = true;
    }

    public void setData(List<Drug> list) {
        this.optionDrugs = (Drug[]) list.toArray(new Drug[list.size()]);
        int size = list.size();
        String[] strArr = new String[size];
        int i = this.mFilter;
        int i2 = 0;
        if (i == 0) {
            while (i2 < size) {
                strArr[i2] = this.optionDrugs[i2].getValue();
                i2++;
            }
        } else if (i == 1) {
            while (i2 < size) {
                strArr[i2] = this.optionDrugs[i2].getForm();
                i2++;
            }
        } else if (i == 2) {
            while (i2 < size) {
                strArr[i2] = this.optionDrugs[i2].getStrength();
                i2++;
            }
        } else if (i == 5) {
            while (i2 < size) {
                strArr[i2] = this.optionDrugs[i2].getPackageSize() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.optionDrugs[i2].getPackageUnit().toLowerCase();
                i2++;
            }
        }
        this.options = strArr;
    }

    public void setPackageSizes(Drug drug) {
        List<PackageSize> packageSizeList = drug.getPackageSizeList();
        this.options = new String[packageSizeList.size()];
        for (int i = 0; i < packageSizeList.size(); i++) {
            this.options[i] = packageSizeList.get(i).getDisplay();
        }
    }

    public void setSelectedPackageSize(String str) {
        String[] strArr = this.options;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = this.options;
                if (i >= strArr2.length) {
                    return;
                }
                if (strArr2[i].equals(str)) {
                    this.mSelected = i;
                    return;
                }
                i++;
            }
        }
    }

    public void setPackageDescNotAutoSelected() {
        int i = 0;
        while (true) {
            String[] strArr = this.options;
            if (i < strArr.length - 1) {
                if (!strArr[i].startsWith("1 ")) {
                    StringBuilder sb = new StringBuilder();
                    String[] strArr2 = this.options;
                    sb.append(strArr2[i]);
                    sb.append(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
                    strArr2[i] = sb.toString();
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void setQuantities(List<Quantity> list, String str) {
        String[] strArr = new String[(list.size() + 1)];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getDisplay();
        }
        this.options = strArr;
    }

    public void setSelectedQuantity(String str) {
        int i = 0;
        while (true) {
            String[] strArr = this.options;
            if (i >= strArr.length - 1) {
                this.mSelected = strArr.length - 1;
                return;
            } else if (strArr[i].equals(str)) {
                this.mSelected = i;
                return;
            } else {
                i++;
            }
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setSelected(int i) {
        this.mSelected = i;
    }

    public void setOnDrugSelectedListener(OnDrugSelectedListener onDrugSelectedListener) {
        this.mDrugSelectionListener = onDrugSelectedListener;
    }

    public void setOnQuantitySelectedListener(OnQuantitySelectedListener onQuantitySelectedListener) {
        this.mQuantitySelectionListener = onQuantitySelectedListener;
    }

    public void setOnPackageSizeSelectedListener(OnPackageSizeSelectedListener onPackageSizeSelectedListener) {
        this.mPackageSelectionListener = onPackageSizeSelectedListener;
    }

    public void setOnTouchOutOrCanceledListener(OnTouchOutOrCanceledListener onTouchOutOrCanceledListener) {
        this.mOnCancelListener = onTouchOutOrCanceledListener;
    }

    public void filterBy(int i) {
        this.mFilter = i;
    }

    public int getFilter() {
        return this.mFilter;
    }
}
