package com.webmd.webmdrx.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmddrugscommons.model.DrugMonograph;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.pricing.PricingActivity;
import com.webmd.webmdrx.fragments.DrugChooserFragmentDialog;
import com.webmd.webmdrx.fragments.ErrorFragmentDialog;
import com.webmd.webmdrx.intf.IRxFormReceivedListener;
import com.webmd.webmdrx.manager.ApiManager;
import com.webmd.webmdrx.models.Drug;
import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.models.PackageSize;
import com.webmd.webmdrx.models.Quantity;
import com.webmd.webmdrx.models.RxForm;
import com.webmd.webmdrx.models.RxLocation;
import com.webmd.webmdrx.omnitureextensions.OmniturePageNames;
import com.webmd.webmdrx.omnitureextensions.RxOmnitureSender;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.LocationUtil;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.Trace;
import com.webmd.webmdrx.util.Util;
import com.webmd.webmdrx.util.WebMDException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PrescriptionDetailsActivity extends RxBaseActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String DIALOG_ERROR = "dialog_error";
    private static final int ID_REQUEST_LOCATION = 1001;
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String TAG = "PDetailsActivityTAG_";
    private int cameFromActivity = 0;
    /* access modifiers changed from: private */
    public Location currentLocation;
    private String drugBrandName = "";
    private String finalUserLocation = "";
    /* access modifiers changed from: private */
    public RxLocation fullCurrentLocation;
    private boolean isGoingToPriceList;
    /* access modifiers changed from: private */
    public boolean isKeyboardOpen;
    /* access modifiers changed from: private */
    public String mCurrentPageSuffix;
    private String mDrugId;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIsFirstResume;
    private ProgressDialog mLoadingDialog;
    private LocationRequest mLocationRequest;
    private List<Quantity> mQuantities = new ArrayList();
    private boolean mResolvingError = false;
    /* access modifiers changed from: private */
    public int mSelectedRadius = 5;
    private TextView mTVDrugDosage;
    private TextView mTVDrugForm;
    /* access modifiers changed from: private */
    public TextView mTVDrugLocation;
    private TextView mTVDrugName;
    private TextView mTVDrugNameBrand;
    /* access modifiers changed from: private */
    public TextView mTVDrugPackageSize;
    /* access modifiers changed from: private */
    public TextView mTVDrugQuantity;
    /* access modifiers changed from: private */
    public TextView mTVSearchRadius;
    private DrugSearchResult mainDrug;
    private int maxHeight = 0;
    private String packageName = "";
    private LinearLayout packageRow;
    /* access modifiers changed from: private */
    public Drug selectedDrug;
    private RxLocation selectedLocation;
    /* access modifiers changed from: private */
    public int selectedLocationOption = 0;
    private TextView titleView;
    private int width = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsFirstResume = true;
        setContentView(R.layout.activity_prescription_details);
        setUpActionBar();
        showLoadingDialog();
        setUpViews();
        setUpListeners();
        createGoogleApiClientInstance();
        createLocationRequest();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        this.packageName = getApplicationContext().getPackageName();
        if (extras != null) {
            Parcelable parcelable = extras.getParcelable(Constants.EXTRA_DRUG_SEARCH_RESULT);
            String string = extras.getString(com.wbmd.wbmdcommons.utils.Constants.EXTRA_DRUG_ID);
            if (parcelable instanceof DrugSearchResult) {
                DrugSearchResult drugSearchResult = (DrugSearchResult) parcelable;
                this.mainDrug = drugSearchResult;
                if (drugSearchResult.getOtherNames() == null || this.mainDrug.getOtherNames().size() != 1) {
                    this.drugBrandName = "";
                } else {
                    this.drugBrandName = this.mainDrug.getOtherNames().get(0).getDrugName();
                }
                this.mDrugId = this.mainDrug.getDrugId();
                this.mIcdDrugName = this.drugBrandName;
                this.mIcdDrugId = this.mDrugId;
                fetchPrescriptionDetailsForResult(this.mainDrug.getDrugId());
            } else if (string != null) {
                this.mIcdDrugId = string;
                this.mDrugId = string;
                this.mIcdDrugName = "";
                fetchPrescriptionDetailsForResult(string);
            }
        }
        if (intent.getSerializableExtra("drugMonograph") != null) {
            DrugMonograph drugMonograph = (DrugMonograph) intent.getSerializableExtra("drugMonograph");
            this.mIcdDrugName = drugMonograph.tDrugName;
            this.mIcdDrugId = drugMonograph.FDB_id;
            DrugSearchResult drugFromMonograph = setDrugFromMonograph(drugMonograph);
            this.mainDrug = drugFromMonograph;
            if (drugFromMonograph.getOtherNames() == null || this.mainDrug.getOtherNames().size() != 1) {
                this.drugBrandName = "";
            } else {
                this.drugBrandName = this.mainDrug.getOtherNames().get(0).getDrugName();
            }
            String drugId = this.mainDrug.getDrugId();
            this.mDrugId = drugId;
            fetchPrescriptionDetailsForResult(drugId);
            if (Util.checkInternet(this)) {
                loadLocation();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        String str;
        String str2;
        super.onResume();
        if (this.mIsFirstResume) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.cameFromActivity = extras.getInt(Constants.EXTRA_ICD_FROM_ACTIVITY);
                if (extras.getBoolean(com.wbmd.wbmdcommons.utils.Constants.EXTRA_IS_DEEP_LINK)) {
                    str2 = "deep-link";
                    this.mRxOmnitureSender.sendPageView("pres-details", str2, this.mIcdDrugName, this.mIcdDrugId);
                    this.mIsFirstResume = false;
                }
            }
            str2 = "wrx-drugs-monotop";
            this.mRxOmnitureSender.sendPageView("pres-details", str2, this.mIcdDrugName, this.mIcdDrugId);
            this.mIsFirstResume = false;
        } else if (!StringExtensions.isNullOrEmpty(this.mCurrentPageSuffix)) {
            this.mRxOmnitureSender.sendPageView("pres-details/" + this.mCurrentPageSuffix);
        } else {
            this.mRxOmnitureSender.sendPageView("pres-details");
        }
        DrugSearchResult drugSearchResult = this.mainDrug;
        if (drugSearchResult == null || !StringUtil.isNotEmpty(drugSearchResult.getprofessionalContentID())) {
            str = "";
        } else {
            str = this.mainDrug.getprofessionalContentID() + "-";
        }
        this.mRxOmnitureSender.sendProfPageView("drug/view/" + str + "webmdrx");
        this.isKeyboardOpen = false;
        this.fullCurrentLocation = new RxLocation();
        Util.logFirebaseEvent(this, Constants.FIRE_BASE_RX_PRESCRIPTION_DETAILS_SCREEN);
    }

    private DrugSearchResult setDrugFromMonograph(DrugMonograph drugMonograph) {
        DrugSearchResult drugSearchResult = new DrugSearchResult();
        if (drugMonograph != null && !StringExtensions.isNullOrEmpty(drugMonograph.gp10)) {
            drugSearchResult.setprofessionalContentID(drugMonograph.professionalContentID);
            drugSearchResult.setDrugId(drugMonograph.gp10);
            if (!StringExtensions.isNullOrEmpty(drugMonograph.tBrandNames)) {
                ArrayList arrayList = new ArrayList();
                for (String drugName : drugMonograph.tBrandNames.split(",")) {
                    DrugSearchResult drugSearchResult2 = new DrugSearchResult();
                    drugSearchResult2.setDrugName(drugName);
                    arrayList.add(drugSearchResult2);
                }
                drugSearchResult.setOtherNames(arrayList);
            }
        }
        return drugSearchResult;
    }

    private void setUpActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.a_p_details_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(this, R.drawable.abc_ic_ab_back_material);
        if (drawable != null) {
            drawable.setColorFilter(ContextCompat.getColor(this, R.color.toolbar_icon_gray), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(drawable);
        }
    }

    /* access modifiers changed from: private */
    public void showLoadingDialog() {
        this.mLoadingDialog = ProgressDialog.show(this, "", getString(R.string.loading_dialog_text), true);
    }

    /* access modifiers changed from: private */
    public void hideLoadingDialog() {
        ProgressDialog progressDialog = this.mLoadingDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    private void setUpViews() {
        this.mTVDrugName = (TextView) findViewById(R.id.a_p_details_text_view_drug_name);
        this.mTVDrugNameBrand = (TextView) findViewById(R.id.a_p_details_text_view_drug_name_brand);
        this.mTVDrugForm = (TextView) findViewById(R.id.a_p_details_text_view_drug_form);
        this.mTVDrugDosage = (TextView) findViewById(R.id.a_p_details_text_view_drug_dosage);
        this.mTVDrugPackageSize = (TextView) findViewById(R.id.a_p_details_text_view_drug_package_size);
        this.packageRow = (LinearLayout) findViewById(R.id.a_p_details_layout_drug_package_size);
        this.mTVDrugQuantity = (TextView) findViewById(R.id.a_p_details_text_view_drug_quantity);
        this.mTVDrugLocation = (TextView) findViewById(R.id.a_p_details_text_view_location);
        this.mTVSearchRadius = (TextView) findViewById(R.id.a_p_details_text_view_radius);
        this.titleView = (TextView) getLayoutInflater().inflate(R.layout.drug_chooser_dialog_title, (ViewGroup) null);
    }

    private void setUpListeners() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.a_p_details_layout_drug_name);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.a_p_details_layout_drug_form);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.a_p_details_layout_drug_dosage);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.a_p_details_layout_drug_quantity);
        LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.a_p_details_layout_drug_package_size);
        LinearLayout linearLayout6 = (LinearLayout) findViewById(R.id.a_p_details_layout_location);
        LinearLayout linearLayout7 = (LinearLayout) findViewById(R.id.a_p_details_layout_radius);
        Button button = (Button) findViewById(R.id.a_p_details_button_find);
        if (linearLayout != null && linearLayout2 != null && linearLayout3 != null && linearLayout4 != null && linearLayout5 != null && linearLayout6 != null && linearLayout7 != null && button != null) {
            linearLayout.setOnClickListener(this);
            linearLayout2.setOnClickListener(this);
            linearLayout3.setOnClickListener(this);
            linearLayout4.setOnClickListener(this);
            linearLayout5.setOnClickListener(this);
            linearLayout6.setOnClickListener(this);
            linearLayout7.setOnClickListener(this);
            button.setOnClickListener(this);
        }
    }

    private void fetchPrescriptionDetailsForResult(String str) {
        ApiManager.getInstance().fetchPrescriptionDetailsForDrugId(this, str, new IRxFormReceivedListener() {
            public void onRxFormReceived(final RxForm rxForm) {
                if (rxForm != null) {
                    PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            PrescriptionDetailsActivity.this.onDataReceived(rxForm);
                        }
                    });
                } else {
                    PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            PrescriptionDetailsActivity.this.hideLoadingDialog();
                            PrescriptionDetailsActivity.this.showErrorDialogFragment(PrescriptionDetailsActivity.this.getResources().getString(R.string.unable_to_locate_this_drug), new Intent(PrescriptionDetailsActivity.this.getApplicationContext(), PrescriptionDetailsActivity.class));
                        }
                    });
                }
            }

            public void onRxFormRequestFailed(WebMDException webMDException) {
                PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        PrescriptionDetailsActivity.this.hideLoadingDialog();
                        PrescriptionDetailsActivity.this.showErrorDialogFragment(PrescriptionDetailsActivity.this.getResources().getString(R.string.unable_to_locate_this_drug), new Intent(PrescriptionDetailsActivity.this.getApplicationContext(), PrescriptionDetailsActivity.class));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void showErrorDialogFragment(String str, Intent intent) {
        ErrorFragmentDialog errorFragmentDialog = new ErrorFragmentDialog();
        errorFragmentDialog.setErrorMessage(str);
        if (intent != null) {
            errorFragmentDialog.setAction(intent);
        }
        try {
            errorFragmentDialog.show(getSupportFragmentManager(), "errorDialog");
        } catch (IllegalStateException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void onDataReceived(RxForm rxForm) {
        if (rxForm != null) {
            if (this.mainDrug == null) {
                this.mainDrug = new DrugSearchResult();
            }
            this.mainDrug.setDrugDosages(rxForm.getDrugs());
            fillData((this.mainDrug.getDrugsDosages() == null || this.mainDrug.getDrugsDosages().isEmpty()) ? null : this.mainDrug.getDrugsDosages().get(0));
        }
        hideLoadingDialog();
    }

    /* access modifiers changed from: private */
    public void fillData(Drug drug) {
        if (drug == null) {
            showErrorDialogFragment(getResources().getString(R.string.unable_to_locate_this_drug), new Intent(getApplicationContext(), PrescriptionDetailsActivity.class));
            return;
        }
        loadLocation();
        this.selectedDrug = drug;
        checkEmptyDosage();
        this.mTVDrugName.setText(drug.getValue());
        if (!drug.isGeneric() || drug.getValue().toLowerCase().equals(this.drugBrandName.toLowerCase()) || this.drugBrandName.isEmpty()) {
            this.mTVDrugNameBrand.setVisibility(8);
        } else {
            this.mTVDrugNameBrand.setVisibility(0);
            this.mTVDrugNameBrand.setText(getString(R.string.generic, new Object[]{this.drugBrandName}));
        }
        this.mTVDrugForm.setText(drug.getForm());
        this.mTVDrugDosage.setText(drug.getStrength());
        this.packageRow.setVisibility(8);
        if (drug.getQuantityList() != null && drug.getQuantityList().size() > 0) {
            Collections.sort(drug.getQuantityList(), new Comparator<Quantity>() {
                public int compare(Quantity quantity, Quantity quantity2) {
                    return quantity.getRank() - quantity2.getRank();
                }
            });
            this.mQuantities = drug.getQuantityList();
            this.mTVDrugQuantity.setText(drug.getQuantityList().get(0).getDisplay().toLowerCase());
        }
    }

    private void loadLocation() {
        RxLocation savedLocation = Util.getSavedLocation(this);
        String savedLocationLocale = Util.getSavedLocationLocale(this);
        if (savedLocation.getCityState().equals("")) {
            return;
        }
        if (!savedLocationLocale.contains(Constants.LANGUAGE_LOCALE)) {
            try {
                Address isLocationValid = LocationUtil.isLocationValid(savedLocation.getZip(), this);
                if (isLocationValid != null) {
                    Util.saveUserLocation(this, isLocationValid);
                    savedLocation = LocationUtil.addressToLocation(isLocationValid);
                    this.selectedLocation = savedLocation;
                }
                this.mTVDrugLocation.setText(savedLocation.getCityState());
                this.selectedLocationOption = 1;
            } catch (Exception unused) {
                showErrorDialogFragment(getResources().getString(R.string.unable_to_locate_this_drug), new Intent(getApplicationContext(), PrescriptionDetailsActivity.class));
            }
        } else {
            this.mTVDrugLocation.setText(savedLocation.getCityState());
            this.selectedLocationOption = 1;
            this.selectedLocation = savedLocation;
        }
    }

    public void onClick(View view) {
        String str;
        int id = view.getId();
        if (id == R.id.a_p_details_layout_drug_name) {
            chooseDrugDialog();
            str = "drug";
        } else if (id == R.id.a_p_details_layout_drug_form) {
            chooseFormDialog();
            str = "form";
        } else if (id == R.id.a_p_details_layout_drug_dosage) {
            chooseDosageDialog();
            str = "dosage";
        } else {
            if (id == R.id.a_p_details_layout_drug_package_size) {
                choosePackageSizeDialog();
            } else if (id == R.id.a_p_details_layout_drug_quantity) {
                chooseQuantityDialog();
                str = FirebaseAnalytics.Param.QUANTITY;
            } else if (id == R.id.a_p_details_layout_location) {
                chooseLocationDialog();
                str = "location";
            } else if (id == R.id.a_p_details_layout_radius) {
                chooseRadiusDialog();
                str = "radius";
            } else if (id == R.id.a_p_details_button_find) {
                goToPricingView();
            }
            str = null;
        }
        if (str != null) {
            RxOmnitureSender rxOmnitureSender = this.mRxOmnitureSender;
            rxOmnitureSender.sendPageView("pres-details/" + str);
            this.mCurrentPageSuffix = str;
        }
    }

    /* access modifiers changed from: private */
    public void checkEmptyDosage() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.a_p_details_layout_drug_dosage);
        if (linearLayout == null) {
            return;
        }
        if (this.selectedDrug.getStrength() == null || !StringUtil.isNotEmpty(this.selectedDrug.getStrength())) {
            linearLayout.setVisibility(8);
        } else {
            linearLayout.setVisibility(0);
        }
    }

    private void chooseDrugDialog() {
        List<Drug> uniqueDrugs = Util.getUniqueDrugs(this.mainDrug.getDrugsDosages());
        String value = this.selectedDrug.getValue();
        int i = 0;
        for (Drug next : uniqueDrugs) {
            if (next.getValue().equals(value)) {
                i = uniqueDrugs.indexOf(next);
            }
        }
        showChooser(0, uniqueDrugs, i, new DrugChooserFragmentDialog.OnDrugSelectedListener() {
            public void onDrugSelected(Drug drug) {
                Drug unused = PrescriptionDetailsActivity.this.selectedDrug = drug;
                PrescriptionDetailsActivity.this.checkEmptyDosage();
                String unused2 = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                PrescriptionDetailsActivity.this.fillData(drug);
            }
        });
    }

    private void chooseFormDialog() {
        List<Drug> uniqueForms = Util.getUniqueForms(this.selectedDrug, this.mainDrug.getDrugsDosages());
        Collections.sort(uniqueForms, new Comparator<Drug>() {
            public int compare(Drug drug, Drug drug2) {
                if (drug == null || drug2 == null) {
                    return 0;
                }
                return drug.getForm().compareTo(drug2.getForm());
            }
        });
        String form = this.selectedDrug.getForm();
        int i = 0;
        for (Drug next : uniqueForms) {
            if (next.getForm().equals(form)) {
                i = uniqueForms.indexOf(next);
            }
        }
        showChooser(1, uniqueForms, i, new DrugChooserFragmentDialog.OnDrugSelectedListener() {
            public void onDrugSelected(Drug drug) {
                Drug unused = PrescriptionDetailsActivity.this.selectedDrug = drug;
                PrescriptionDetailsActivity.this.checkEmptyDosage();
                String unused2 = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                PrescriptionDetailsActivity.this.fillData(drug);
            }
        });
    }

    private void choosePackageSizeDialog() {
        getViewMeasurements();
        DrugChooserFragmentDialog drugChooserFragmentDialog = new DrugChooserFragmentDialog();
        drugChooserFragmentDialog.filterBy(5);
        drugChooserFragmentDialog.setPackageSizes(this.selectedDrug);
        drugChooserFragmentDialog.setSelectedPackageSize(this.mTVDrugPackageSize.getText().toString());
        drugChooserFragmentDialog.setOnPackageSizeSelectedListener(new DrugChooserFragmentDialog.OnPackageSizeSelectedListener() {
            public void onPackageSizeSelectedListener(String str) {
                PrescriptionDetailsActivity.this.mTVDrugPackageSize.setText(str);
                for (PackageSize next : PrescriptionDetailsActivity.this.selectedDrug.getPackageSizeList()) {
                    if (next.getDisplay().equals(str)) {
                        PrescriptionDetailsActivity.this.mTVDrugQuantity.setText(next.getQuantityList().get(0).getDisplay());
                    }
                }
            }
        });
        drugChooserFragmentDialog.setMaxHeight(this.maxHeight);
        drugChooserFragmentDialog.setWidth(this.width);
        drugChooserFragmentDialog.show(getSupportFragmentManager(), "Page size");
    }

    private void chooseDosageDialog() {
        List<Drug> uniqueDosages = Util.getUniqueDosages(this.selectedDrug, this.mainDrug.getDrugsDosages());
        Collections.sort(uniqueDosages, new Comparator<Drug>() {
            public int compare(Drug drug, Drug drug2) {
                if (drug == null || drug2 == null) {
                    return 0;
                }
                return drug.getStrength().compareTo(drug2.getStrength());
            }
        });
        Iterator<Drug> it = uniqueDosages.iterator();
        while (it.hasNext()) {
            Drug next = it.next();
            if (next.getStrength() == null || !StringUtil.isNotEmpty(next.getStrength())) {
                it.remove();
            }
        }
        int i = 0;
        String strength = this.selectedDrug.getStrength();
        for (Drug next2 : uniqueDosages) {
            if (next2.getStrength().equals(strength)) {
                i = uniqueDosages.indexOf(next2);
            }
        }
        showChooser(2, uniqueDosages, i, new DrugChooserFragmentDialog.OnDrugSelectedListener() {
            public void onDrugSelected(Drug drug) {
                Drug unused = PrescriptionDetailsActivity.this.selectedDrug = drug;
                PrescriptionDetailsActivity.this.checkEmptyDosage();
                String unused2 = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                PrescriptionDetailsActivity.this.fillData(drug);
            }
        });
    }

    private void showChooser(int i, List<Drug> list, int i2, DrugChooserFragmentDialog.OnDrugSelectedListener onDrugSelectedListener) {
        getViewMeasurements();
        DrugChooserFragmentDialog drugChooserFragmentDialog = new DrugChooserFragmentDialog();
        drugChooserFragmentDialog.filterBy(i);
        drugChooserFragmentDialog.setData(list);
        drugChooserFragmentDialog.setSelected(i2);
        drugChooserFragmentDialog.setOnDrugSelectedListener(onDrugSelectedListener);
        drugChooserFragmentDialog.setOnTouchOutOrCanceledListener(new DrugChooserFragmentDialog.OnTouchOutOrCanceledListener() {
            public void onCancel() {
                String unused = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
            }
        });
        if (RxOmnitureSender.Companion.isProfessional(this)) {
            WBMDOmnitureManager.sendModuleAction(OmniturePageNames.getModuleNameForFilters(drugChooserFragmentDialog));
        }
        drugChooserFragmentDialog.setMaxHeight(this.maxHeight);
        drugChooserFragmentDialog.setWidth(this.width);
        drugChooserFragmentDialog.show(getSupportFragmentManager(), "drugChooser");
    }

    private void chooseLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.custom_dialog_row_view, new String[]{getString(R.string.current_location), getString(R.string.dialog_input_option_custom_location)});
        if (this.mTVDrugLocation.getText().toString().equals(getString(R.string.current_location))) {
            this.selectedLocationOption = 0;
        } else {
            this.selectedLocationOption = 1;
        }
        this.titleView.setText(getString(R.string.prescription_detail_location));
        builder.setCustomTitle(this.titleView).setSingleChoiceItems((ListAdapter) arrayAdapter, this.selectedLocationOption, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    PrescriptionDetailsActivity.this.mTVDrugLocation.setText(R.string.current_location);
                    int unused = PrescriptionDetailsActivity.this.selectedLocationOption = 0;
                    Util.clearUserLocation(PrescriptionDetailsActivity.this.getApplicationContext());
                    PrescriptionDetailsActivity.this.findCurrentLocation();
                    String unused2 = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                    dialogInterface.cancel();
                }
                if (i == 1) {
                    int unused3 = PrescriptionDetailsActivity.this.selectedLocationOption = 1;
                    PrescriptionDetailsActivity.this.showInputLocationDialog(dialogInterface);
                }
            }
        });
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(true);
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                String unused = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
            }
        });
        if (RxOmnitureSender.Companion.isProfessional(this)) {
            WBMDOmnitureManager.sendModuleAction(getModuleForAlertDialog("filter-location"));
        }
        if (this.titleView.getParent() != null) {
            ((ViewGroup) this.titleView.getParent()).removeView(this.titleView);
        }
        create.show();
    }

    private void chooseRadiusDialog() {
        final String[] radiusOptions = Util.getRadiusOptions(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.custom_dialog_row_view, radiusOptions);
        this.titleView.setText(getString(R.string.dialog_input_title_radius));
        builder.setCustomTitle(this.titleView).setSingleChoiceItems((ListAdapter) arrayAdapter, this.mSelectedRadius, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PrescriptionDetailsActivity.this.mTVSearchRadius.setText(radiusOptions[i]);
                int unused = PrescriptionDetailsActivity.this.mSelectedRadius = i;
                String unused2 = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                dialogInterface.dismiss();
            }
        });
        AlertDialog create = builder.create();
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                String unused = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
            }
        });
        if (RxOmnitureSender.Companion.isProfessional(this)) {
            WBMDOmnitureManager.sendModuleAction(getModuleForAlertDialog("filter-radius"));
        }
        if (this.titleView.getParent() != null) {
            ((ViewGroup) this.titleView.getParent()).removeView(this.titleView);
        }
        create.show();
    }

    public void hideKeyboard(AlertDialog alertDialog) {
        View currentFocus = alertDialog.getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public void showKeyboard() {
        ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(2, 0);
    }

    /* access modifiers changed from: private */
    public void showInputLocationDialog(final DialogInterface dialogInterface) {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_input, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(true);
        ((TextView) inflate.findViewById(R.id.d_input_text_view_title)).setText(getString(R.string.prescription_detail_location));
        ((TextView) inflate.findViewById(R.id.d_input_text_view_sub_title)).setText(getString(R.string.dialog_input_option_custom_location));
        final EditText editText = (EditText) inflate.findViewById(R.id.d_input_edit_text_input);
        editText.setHint(getString(R.string.dialog_input_hint_location));
        editText.setInputType(112);
        final Button button = (Button) inflate.findViewById(R.id.input_dialog_button_positive);
        showKeyboard();
        this.isKeyboardOpen = true;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrescriptionDetailsActivity.this.showLoadingDialog();
                PrescriptionDetailsActivity.this.hideKeyboard(create);
                boolean unused = PrescriptionDetailsActivity.this.isKeyboardOpen = false;
                String unused2 = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                if (RxOmnitureSender.Companion.isProfessional(PrescriptionDetailsActivity.this)) {
                    WBMDOmnitureManager.sendModuleAction(PrescriptionDetailsActivity.this.getModuleForAlertDialog("submit-location"));
                }
                try {
                    List<Address> fromLocationName = new Geocoder(PrescriptionDetailsActivity.this.getApplicationContext(), new Locale(Constants.LANGUAGE_LOCALE)).getFromLocationName(editText.getText().toString(), 5);
                    if (fromLocationName.size() == 0) {
                        create.dismiss();
                        PrescriptionDetailsActivity.this.showSimpleDialog(dialogInterface, PrescriptionDetailsActivity.this.getString(R.string.invalid_search));
                    } else {
                        List<Address> uSLocations = LocationUtil.getUSLocations(fromLocationName);
                        if (uSLocations.size() > 0) {
                            List<Address> locationsWithZip = LocationUtil.getLocationsWithZip(uSLocations);
                            if (locationsWithZip.size() == 0) {
                                locationsWithZip = LocationUtil.getZipsForAddresses(uSLocations, PrescriptionDetailsActivity.this.getBaseContext());
                            }
                            if (locationsWithZip.size() <= 0) {
                                create.dismiss();
                                PrescriptionDetailsActivity.this.showSimpleDialog(dialogInterface, PrescriptionDetailsActivity.this.getString(R.string.no_pricing_results));
                            } else if (locationsWithZip.size() == 1) {
                                PrescriptionDetailsActivity.this.setSelectedUserLocation(dialogInterface, locationsWithZip.get(0));
                                create.dismiss();
                            } else {
                                create.dismiss();
                                PrescriptionDetailsActivity.this.showMultipleLocationDialog(dialogInterface, locationsWithZip);
                            }
                        } else {
                            create.dismiss();
                            PrescriptionDetailsActivity.this.showSimpleDialog(dialogInterface, PrescriptionDetailsActivity.this.getString(R.string.only_for_us));
                        }
                    }
                } catch (IOException unused3) {
                    create.dismiss();
                    PrescriptionDetailsActivity prescriptionDetailsActivity = PrescriptionDetailsActivity.this;
                    prescriptionDetailsActivity.showSimpleDialog(dialogInterface, prescriptionDetailsActivity.getString(R.string.connection_error_message));
                }
                PrescriptionDetailsActivity.this.hideLoadingDialog();
            }
        });
        ((Button) inflate.findViewById(R.id.input_dialog_button_negative)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrescriptionDetailsActivity.this.hideKeyboard(create);
                boolean unused = PrescriptionDetailsActivity.this.isKeyboardOpen = false;
                String unused2 = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                create.cancel();
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
                } else if (Util.isLetter(charSequence.charAt(0))) {
                    if (charSequence.length() >= 3) {
                        button.setEnabled(true);
                        button.setAlpha(1.0f);
                        return;
                    }
                    button.setEnabled(false);
                    button.setAlpha(0.5f);
                } else if (charSequence.length() >= 5) {
                    button.setEnabled(true);
                    button.setAlpha(1.0f);
                } else {
                    button.setEnabled(false);
                    button.setAlpha(0.5f);
                }
            }
        });
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                boolean unused = PrescriptionDetailsActivity.this.isKeyboardOpen = false;
            }
        });
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (RxOmnitureSender.Companion.isProfessional(PrescriptionDetailsActivity.this)) {
                    WBMDOmnitureManager.sendModuleAction(PrescriptionDetailsActivity.this.getModuleForAlertDialog("filter-location"));
                }
                boolean unused = PrescriptionDetailsActivity.this.isKeyboardOpen = false;
            }
        });
        if (RxOmnitureSender.Companion.isProfessional(this)) {
            WBMDOmnitureManager.sendModuleAction(getModuleForAlertDialog("location"));
        }
        create.show();
    }

    /* access modifiers changed from: private */
    public void showSimpleDialog(final DialogInterface dialogInterface, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) str);
        builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                DialogInterface dialogInterface2 = dialogInterface;
                if (dialogInterface2 != null) {
                    PrescriptionDetailsActivity.this.showInputLocationDialog(dialogInterface2);
                }
            }
        });
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(true);
        create.show();
    }

    /* access modifiers changed from: private */
    public void setSelectedUserLocation(DialogInterface dialogInterface, Address address) {
        if (address != null) {
            if (address.getCountryCode().equals(getString(R.string.pr))) {
                address.setAdminArea(getString(R.string.puerto_rico));
            }
            if (address.getAdminArea() != null) {
                String adminArea = address.getAdminArea();
                String postalCode = address.getPostalCode();
                String cityFromAddress = Util.getCityFromAddress(address);
                if (!cityFromAddress.isEmpty()) {
                    TextView textView = this.mTVDrugLocation;
                    textView.setText(cityFromAddress + ", " + Util.getStateCode(adminArea));
                    this.selectedLocation = LocationUtil.addressToLocation(address);
                    this.selectedLocationOption = 1;
                    Util.saveUserLocation(getApplicationContext(), address);
                    Util.updateRxGroupZipCode(getBaseContext(), postalCode.substring(0, 3));
                    stopLocationUpdates();
                    if (dialogInterface != null) {
                        dialogInterface.cancel();
                        return;
                    }
                    return;
                }
                showSimpleDialog(dialogInterface, getString(R.string.connection_error_message));
                return;
            }
            showSimpleDialog(dialogInterface, getString(R.string.invalid_search));
        }
    }

    /* access modifiers changed from: private */
    public void showMultipleLocationDialog(final DialogInterface dialogInterface, final List<Address> list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int dimension = (int) getResources().getDimension(R.dimen.pharmacy_detail_layout_margins);
        TextView textView = new TextView(this);
        textView.setPadding(dimension, dimension, dimension, dimension);
        textView.setText(getString(R.string.did_you_mean));
        textView.setGravity(17);
        textView.setTypeface((Typeface) null, 1);
        textView.setTextSize(20.0f);
        textView.setText(getString(R.string.did_you_mean));
        builder.setCustomTitle(textView);
        builder.setNegativeButton((CharSequence) getString(R.string.dialog_input_cancel), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PrescriptionDetailsActivity.this.showInputLocationDialog(dialogInterface);
            }
        });
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getAddressLine(0);
            for (int i2 = 1; i2 <= list.get(i).getMaxAddressLineIndex(); i2++) {
                strArr[i] = strArr[i] + ", " + list.get(i).getAddressLine(i2);
            }
        }
        builder.setItems((CharSequence[]) strArr, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PrescriptionDetailsActivity.this.setSelectedUserLocation(dialogInterface, (Address) list.get(i));
            }
        });
        builder.create().show();
    }

    private void chooseQuantityDialog() {
        getViewMeasurements();
        List arrayList = new ArrayList();
        Drug drug = this.selectedDrug;
        if (drug != null && drug.isAutoPackageSelect()) {
            arrayList = this.selectedDrug.getQuantityList();
        } else if (this.selectedDrug.getPackageSizeList() != null && this.selectedDrug.getPackageSizeList().size() > 0) {
            if (this.selectedDrug.getPackageSizeList().size() == 1) {
                arrayList = this.selectedDrug.getPackageSizeList().get(0).getQuantityList();
            } else {
                arrayList = this.selectedDrug.getPackageSizeList().get(this.selectedDrug.getSelectedPackageSize(this.mTVDrugPackageSize.getText().toString())).getQuantityList();
            }
        }
        Util.sortQuantities(arrayList);
        DrugChooserFragmentDialog drugChooserFragmentDialog = new DrugChooserFragmentDialog();
        drugChooserFragmentDialog.setQuantities(arrayList, getString(R.string.chooser_dialog_custom_quantity));
        drugChooserFragmentDialog.filterBy(3);
        if (!this.selectedDrug.isAutoPackageSelect()) {
            drugChooserFragmentDialog.setPackageDescNotAutoSelected();
        }
        drugChooserFragmentDialog.setSelectedQuantity(this.mTVDrugQuantity.getText().toString());
        drugChooserFragmentDialog.setOnQuantitySelectedListener(new DrugChooserFragmentDialog.OnQuantitySelectedListener() {
            public void onQuantitySelected(String str) {
                String unused = PrescriptionDetailsActivity.this.mCurrentPageSuffix = null;
                if (PrescriptionDetailsActivity.this.selectedDrug.isAutoPackageSelect() || str.contains(PrescriptionDetailsActivity.this.selectedDrug.getPackageDescription().toLowerCase())) {
                    if (!PrescriptionDetailsActivity.this.selectedDrug.isAutoPackageSelect() || PrescriptionDetailsActivity.this.selectedDrug.getPackageUnit() == null || !PrescriptionDetailsActivity.this.selectedDrug.getPackageUnit().equals("ML") || str.contains("ml")) {
                        PrescriptionDetailsActivity.this.mTVDrugQuantity.setText(str);
                        return;
                    }
                    PrescriptionDetailsActivity.this.mTVDrugQuantity.setText(PrescriptionDetailsActivity.this.getString(R.string.ml, new Object[]{str}));
                } else if (!str.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES) || str.length() >= 2) {
                    TextView access$800 = PrescriptionDetailsActivity.this.mTVDrugQuantity;
                    access$800.setText(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + PrescriptionDetailsActivity.this.selectedDrug.getPackageDescription().toLowerCase() + com.appboy.Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
                } else {
                    TextView access$8002 = PrescriptionDetailsActivity.this.mTVDrugQuantity;
                    access$8002.setText(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + PrescriptionDetailsActivity.this.selectedDrug.getPackageDescription().toLowerCase());
                }
            }
        });
        if (RxOmnitureSender.Companion.isProfessional(this)) {
            WBMDOmnitureManager.sendModuleAction(OmniturePageNames.getModuleNameForFilters(drugChooserFragmentDialog));
        }
        drugChooserFragmentDialog.setMaxHeight(this.maxHeight);
        drugChooserFragmentDialog.setWidth(this.width);
        drugChooserFragmentDialog.show(getSupportFragmentManager(), "drugChooser");
    }

    private void createGoogleApiClientInstance() {
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        }
    }

    /* access modifiers changed from: private */
    public void goToPricingView() {
        double d;
        this.mCurrentPageSuffix = null;
        this.isGoingToPriceList = true;
        Intent intent = new Intent(this, PricingActivity.class);
        this.selectedDrug.setOtherName(this.drugBrandName);
        intent.putExtra(Constants.EXTRA_DRUG, this.selectedDrug);
        if (this.selectedLocationOption == 0) {
            if (this.currentLocation != null) {
                if (this.fullCurrentLocation.isLocationValid()) {
                    this.selectedLocation = this.fullCurrentLocation;
                } else {
                    getLocationForUser();
                    return;
                }
            } else if (this.mGoogleApiClient.isConnected()) {
                findCurrentLocation();
                return;
            } else if (!this.mGoogleApiClient.isConnecting()) {
                this.mGoogleApiClient.connect();
                return;
            } else {
                return;
            }
        }
        String charSequence = this.mTVDrugForm.getText().toString();
        String charSequence2 = this.mTVDrugDosage.getText().toString();
        if (charSequence2 == null) {
            showErrorDialogFragment(getString(R.string.error_dosage_empty), (Intent) null);
            return;
        }
        String stripPackageDesc = Util.stripPackageDesc(this.mTVDrugQuantity.getText().toString());
        Double valueOf = Double.valueOf(Double.parseDouble(stripPackageDesc));
        List<Quantity> list = this.mQuantities;
        if (list != null) {
            d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            for (Quantity next : list) {
                if (stripPackageDesc.equalsIgnoreCase(next.getDisplay())) {
                    d = next.getPriceQuantity().doubleValue();
                }
            }
        } else {
            d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        float floatValue = Float.valueOf(stripPackageDesc).floatValue();
        boolean z = this.selectedLocationOption == 0;
        int i = this.mSelectedRadius;
        int i2 = 5;
        if (i != 0) {
            if (i == 1) {
                i2 = 10;
            } else if (i == 2) {
                i2 = 15;
            } else if (i == 3) {
                i2 = 20;
            } else if (i == 4) {
                i2 = 25;
            } else if (i == 5) {
                i2 = 50;
            }
        }
        SharedPreferences.Editor edit = getSharedPreferences(Constants.EXTRA_RADIUS, 0).edit();
        edit.putInt(Constants.EXTRA_RADIUS, i2);
        edit.apply();
        RxLocation rxLocation = this.selectedLocation;
        if (rxLocation != null) {
            this.finalUserLocation = rxLocation.getOmnitureLocation();
        }
        if (RxOmnitureSender.Companion.isProfessional(this)) {
            WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule("wrx-filter", "", WBMDOmnitureManager.shared.getLastSentPage()));
        }
        RxLocation rxLocation2 = this.selectedLocation;
        if (rxLocation2 == null || rxLocation2.isLocationValid()) {
            Location location = new Location("");
            location.setLatitude(this.selectedLocation.getLatitude());
            location.setLongitude(this.selectedLocation.getLongitude());
            intent.putExtra(Constants.EXTRA_LOCATION, location);
            intent.putExtra(Constants.EXTRA_FORM, charSequence);
            intent.putExtra(Constants.EXTRA_DOSAGE, charSequence2);
            intent.putExtra(Constants.EXTRA_RADIUS, i2);
            intent.putExtra(Constants.EXTRA_QUANTITY, valueOf);
            intent.putExtra(Constants.EXTRA_PRICE_QUANTITY, d);
            intent.putExtra(Constants.EXTRA_CURRENT_LOCATION, z);
            intent.putExtra(Constants.EXTRA_ORIGINAL_QUANTITY, floatValue);
            intent.putExtra(Constants.EXTRA_PACKAGE_SIZE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            intent.putExtra(Constants.EXTRA_LOCATION_ZIP, this.selectedLocation.getZip());
            intent.putExtra(Constants.EXTRA_ICD_DRUG_NAME, this.mIcdDrugName);
            intent.putExtra(Constants.EXTRA_ICD_DRUG_ID, this.mIcdDrugId);
            if (this.cameFromActivity != 0) {
                intent.putExtra(Constants.EXTRA_ICD, this.mRxOmnitureSender.buildIcdFromActivity(this.mIcdDrugName, this.mIcdDrugId, this.cameFromActivity, this.packageName));
            } else {
                intent.putExtra(Constants.EXTRA_ICD, this.mRxOmnitureSender.buildIcd(this.mIcdDrugName, this.mIcdDrugId));
            }
            if (Util.isFirstSearch(this)) {
                Util.saveFirstSearch(this);
            }
            this.isGoingToPriceList = false;
            startActivity(intent);
            return;
        }
        showErrorDialogFragment(getString(R.string.error_current_location_), (Intent) null);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1001) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                showInputLocationDialog((DialogInterface) null);
            } else {
                loadLocation();
            }
        }
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1001);
    }

    private void getLocationForUser() {
        new Thread(new Runnable() {
            public void run() {
                if (PrescriptionDetailsActivity.this.currentLocation != null) {
                    try {
                        List<Address> fromLocation = new Geocoder(PrescriptionDetailsActivity.this.getBaseContext(), new Locale(Constants.LANGUAGE_LOCALE)).getFromLocation(PrescriptionDetailsActivity.this.currentLocation.getLatitude(), PrescriptionDetailsActivity.this.currentLocation.getLongitude(), 5);
                        if (fromLocation.size() <= 0) {
                            PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    PrescriptionDetailsActivity.this.showErrorDialogFragment(PrescriptionDetailsActivity.this.getString(R.string.error_current_location_), (Intent) null);
                                    PrescriptionDetailsActivity.this.showInputLocationDialog((DialogInterface) null);
                                }
                            });
                        } else if (LocationUtil.getUSLocations(fromLocation).size() > 0) {
                            RxLocation unused = PrescriptionDetailsActivity.this.fullCurrentLocation = LocationUtil.addressToLocation(LocationUtil.getZipForLocation(PrescriptionDetailsActivity.this.currentLocation.getLatitude(), PrescriptionDetailsActivity.this.currentLocation.getLongitude(), PrescriptionDetailsActivity.this.getBaseContext()));
                            Util.updateRxGroupZipCode(PrescriptionDetailsActivity.this.getBaseContext(), PrescriptionDetailsActivity.this.fullCurrentLocation.getZip());
                            PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    PrescriptionDetailsActivity.this.goToPricingView();
                                }
                            });
                        } else {
                            PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    PrescriptionDetailsActivity.this.showSimpleDialog((DialogInterface) null, PrescriptionDetailsActivity.this.getString(R.string.only_for_us));
                                }
                            });
                        }
                    } catch (IOException e) {
                        Trace.e("_TAG", e.toString());
                        PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                PrescriptionDetailsActivity.this.showInputLocationDialog((DialogInterface) null);
                            }
                        });
                    }
                } else {
                    PrescriptionDetailsActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            PrescriptionDetailsActivity.this.showInputLocationDialog((DialogInterface) null);
                        }
                    });
                }
            }
        }).start();
    }

    private void showTurnOnLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.turn_on_location_title);
        builder.setMessage(R.string.turn_on_location_text);
        builder.setNegativeButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton((CharSequence) getString(R.string.settings), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PrescriptionDetailsActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });
        builder.create().show();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        Trace.w(TAG, "Connection to google api failed");
        if (connectionResult.hasResolution()) {
            try {
                this.mResolvingError = true;
                connectionResult.startResolutionForResult(this, 1001);
            } catch (IntentSender.SendIntentException unused) {
                this.mGoogleApiClient.connect();
            }
        } else {
            showErrorDialog(connectionResult.getErrorCode());
            this.mResolvingError = true;
        }
    }

    public void onConnected(Bundle bundle) {
        findCurrentLocation();
        if (this.isGoingToPriceList) {
            this.isGoingToPriceList = false;
            if (this.selectedLocation != null) {
                goToPricingView();
            }
        }
    }

    /* access modifiers changed from: private */
    public void findCurrentLocation() {
        if (!(Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0)) {
            requestLocationPermission();
        } else if (Util.isGPSOn(this, getContentResolver())) {
            GoogleApiClient googleApiClient = this.mGoogleApiClient;
            if (googleApiClient == null || !googleApiClient.isConnected()) {
                GoogleApiClient googleApiClient2 = this.mGoogleApiClient;
                if (googleApiClient2 != null) {
                    googleApiClient2.connect();
                } else {
                    createGoogleApiClientInstance();
                }
            } else {
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
                this.currentLocation = lastLocation;
                if (lastLocation == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, (LocationListener) this);
                } else if (this.isGoingToPriceList) {
                    this.isGoingToPriceList = false;
                    goToPricingView();
                }
            }
        } else if (this.isGoingToPriceList) {
            showTurnOnLocationDialog();
        }
    }

    private void stopLocationUpdates() {
        if ((Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) && this.mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.mGoogleApiClient, (LocationListener) this);
        }
    }

    public void onConnectionSuspended(int i) {
        Trace.w(TAG, "Connection to google api client suspended");
        if (this.isGoingToPriceList) {
            this.isGoingToPriceList = false;
            showErrorDialogFragment(getString(R.string.connection_error_message), (Intent) null);
        }
    }

    private void showErrorDialog(int i) {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DIALOG_ERROR, i);
        errorDialogFragment.setArguments(bundle);
        errorDialogFragment.show(getSupportFragmentManager(), "errordialog");
    }

    public void onDialogDismissed() {
        this.mResolvingError = false;
    }

    public boolean ismResolvingError() {
        return this.mResolvingError;
    }

    public void setmResolvingError(boolean z) {
        this.mResolvingError = z;
    }

    public WBMDOmnitureModule getModuleForAlertDialog(String str) {
        String str2;
        String str3 = "";
        if (str.equals("location")) {
            str3 = "wrx-location-enter";
            str2 = "search/filter/location";
        } else {
            str2 = str3;
        }
        if (str.equals("filter-location")) {
            str2 = WBMDOmnitureManager.shared.getLastSentPage();
            str3 = "wrx-filter-location";
        }
        if (str.equals("filter-radius")) {
            str2 = WBMDOmnitureManager.shared.getLastSentPage();
            str3 = "wrx-filter-radius";
        }
        if (str.equals("submit-location")) {
            str2 = WBMDOmnitureManager.shared.getLastSentPage();
            str3 = "wrx-filter";
        }
        return new WBMDOmnitureModule(str3, (String) null, str2);
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.currentLocation = location;
            stopLocationUpdates();
            if (this.isGoingToPriceList) {
                this.isGoingToPriceList = false;
                goToPricingView();
            }
        }
    }

    public static class ErrorDialogFragment extends DialogFragment {
        public Dialog onCreateDialog(Bundle bundle) {
            return GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), getArguments().getInt(PrescriptionDetailsActivity.DIALOG_ERROR), 1001);
        }

        public void onDismiss(DialogInterface dialogInterface) {
            try {
                ((PrescriptionDetailsActivity) getActivity()).onDialogDismissed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        if (!this.mGoogleApiClient.isConnecting() && !this.mGoogleApiClient.isConnected()) {
            this.mGoogleApiClient.connect();
        }
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.isKeyboardOpen) {
            ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(1, 0);
        }
        hideLoadingDialog();
        this.mLoadingDialog = null;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.mGoogleApiClient, (LocationListener) this);
        }
        this.mGoogleApiClient.disconnect();
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getExtras() != null && intent.getExtras().getBoolean(Constants.EXTRA_RELOAD_LOCATION) && Util.checkInternet(this)) {
            loadLocation();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001) {
            this.mResolvingError = false;
            if (i2 == -1 && !this.mGoogleApiClient.isConnecting() && !this.mGoogleApiClient.isConnected()) {
                this.mGoogleApiClient.connect();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        this.mLocationRequest = locationRequest;
        locationRequest.setInterval(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        this.mLocationRequest.setFastestInterval(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        this.mLocationRequest.setPriority(102);
    }

    public void getViewMeasurements() {
        TextView textView = (TextView) findViewById(R.id.a_p_details_text_view_title);
        ScrollView scrollView = (ScrollView) findViewById(R.id.a_p_details_scroll_view_content);
        if (textView != null && scrollView != null) {
            this.maxHeight = textView.getHeight() + scrollView.getHeight();
            this.width = scrollView.getWidth();
        }
    }
}
