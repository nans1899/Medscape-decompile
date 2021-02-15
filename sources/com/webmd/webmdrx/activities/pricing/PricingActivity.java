package com.webmd.webmdrx.activities.pricing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.PharmacyDetailActivity;
import com.webmd.webmdrx.activities.PrescriptionDetailsActivity;
import com.webmd.webmdrx.activities.RxBaseActivity;
import com.webmd.webmdrx.adapters.PharmacyInfoWindowAdapter;
import com.webmd.webmdrx.adapters.PharmacyListAdapter;
import com.webmd.webmdrx.fragments.ControlledSubstanceDialog;
import com.webmd.webmdrx.fragments.ErrorFragmentDialog;
import com.webmd.webmdrx.fragments.ShareFragmentDialog;
import com.webmd.webmdrx.intf.IDrugDetailsListener;
import com.webmd.webmdrx.intf.IPharmacyStarListener;
import com.webmd.webmdrx.intf.IPricingReceivedListener;
import com.webmd.webmdrx.manager.ApiManager;
import com.webmd.webmdrx.manager.RxSharedPreferenceManager;
import com.webmd.webmdrx.models.Address;
import com.webmd.webmdrx.models.Drug;
import com.webmd.webmdrx.models.MarkerWindow;
import com.webmd.webmdrx.models.Pharmacy;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.models.RxDrugDetail;
import com.webmd.webmdrx.models.RxLocation;
import com.webmd.webmdrx.models.RxPricing;
import com.webmd.webmdrx.omnitureextensions.RxOmnitureSender;
import com.webmd.webmdrx.parsers.RxProfileParser;
import com.webmd.webmdrx.util.LocationUtil;
import com.webmd.webmdrx.util.MarkerBackgroundProvider;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.Util;
import com.webmd.webmdrx.util.WebMDException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class PricingActivity extends RxBaseActivity implements PharmacyListAdapter.onPharmacyClickListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnMapLoadedCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final int ID_REQUEST_LOCATION = 1001;
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String SEARCHED_PRICES_RX = "searched_prices_rx";
    boolean cameFromProfile = false;
    private Location currentLocation;
    TextView currentLocationText;
    private String drugDetails;
    boolean fetchFailed = false;
    /* access modifiers changed from: private */
    public boolean isDataLoaded;
    /* access modifiers changed from: private */
    public boolean isIconsLoaded;
    /* access modifiers changed from: private */
    public boolean isKeyboardOpen;
    private boolean isMapLoaded;
    /* access modifiers changed from: private */
    public int loadedIcons;
    private String locationZip;
    /* access modifiers changed from: private */
    public PharmacyListAdapter mAdapter;
    private String mCurrentPageSuffix = Constants.XML_LIST;
    /* access modifiers changed from: private */
    public ArrayList<Price> mData = new ArrayList<>();
    /* access modifiers changed from: private */
    public String mDosage;
    /* access modifiers changed from: private */
    public Drug mDrug;
    /* access modifiers changed from: private */
    public String mForm;
    private GoogleApiClient mGoogleApiClient;
    /* access modifiers changed from: private */
    public String mIcd;
    private ProgressDialog mLoadingDialog;
    private Location mLocation;
    private LocationRequest mLocationRequest;
    /* access modifiers changed from: private */
    public GoogleMap mMap;
    private LatLngBounds mMapBounds;
    private double mPriceQuantity;
    /* access modifiers changed from: private */
    public double mQuantity;
    private int mRadius = 5;
    private RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public ShimmerFrameLayout mShimmerViewContainer;
    private boolean mShowCurrentLocation;
    /* access modifiers changed from: private */
    public FrameLayout mapFrame;
    private Intent mapIntent;
    private MenuItem mapToggle;
    float originalQuantity;
    double packageSize;
    private Button redoSearchButton;
    /* access modifiers changed from: private */
    public boolean redoSearchDone = false;
    private int selectedFilter = 0;
    /* access modifiers changed from: private */
    public int selectedLocationOption = -1;
    private ShareFragmentDialog sfd;
    private TextView titleView;

    private void showLoadingDialog() {
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
    }

    static /* synthetic */ int access$2908(PricingActivity pricingActivity) {
        int i = pricingActivity.loadedIcons;
        pricingActivity.loadedIcons = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.loadedIcons = 0;
        setContentView(R.layout.activity_pricing);
        setUpViews();
        createGoogleApiClientInstance();
        createLocationRequest();
        setUpActionBar();
        setUpRecyclerView();
        setUpIntent(getIntent());
        setUpListeners();
        this.mIcdDrugName = getIntent().getStringExtra(com.webmd.webmdrx.util.Constants.EXTRA_ICD_DRUG_NAME);
        this.mIcdDrugId = getIntent().getStringExtra(com.webmd.webmdrx.util.Constants.EXTRA_ICD_DRUG_ID);
        setFirebaseUserProperty(SEARCHED_PRICES_RX, true);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        RxOmnitureSender rxOmnitureSender = this.mRxOmnitureSender;
        rxOmnitureSender.sendPageView("drug-prices/" + this.mCurrentPageSuffix);
        this.mRxOmnitureSender.sendProfPageView("drug/view/webmdrx/drug-prices/list");
        this.isKeyboardOpen = false;
        this.mShimmerViewContainer.startShimmer();
        Util.logFirebaseEvent(this, com.webmd.webmdrx.util.Constants.FIRE_BASE_RX_PRICING_SCREEN);
        RxSharedPreferenceManager.Companion.setHasUserVisitedRxPricingScreen(true, this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.mapFrame == null) {
            return true;
        }
        getMenuInflater().inflate(R.menu.a_pricing_menu, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        this.mapToggle = menu.findItem(R.id.a_pricing_image_view_map);
        return super.onPrepareOptionsMenu(menu);
    }

    private void createGoogleApiClientInstance() {
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
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

    /* access modifiers changed from: private */
    public void setUpMap(boolean z) {
        FrameLayout frameLayout = this.mapFrame;
        if (frameLayout != null) {
            if (z) {
                frameLayout.setVisibility(0);
            } else {
                frameLayout.setVisibility(8);
            }
            try {
                SupportMapFragment.newInstance().getMapAsync(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setUpViews() {
        this.titleView = (TextView) getLayoutInflater().inflate(R.layout.drug_chooser_dialog_title, (ViewGroup) null);
        this.mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        try {
            this.mapFrame = (FrameLayout) findViewById(R.id.map_frame);
        } catch (Throwable unused) {
        }
    }

    private void setUpListeners() {
        Button button = (Button) findViewById(R.id.a_pricing_map_button_redo_search);
        this.redoSearchButton = button;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PricingActivity.this.redoSearch();
                }
            });
        }
    }

    private void setUpIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Parcelable parcelable = extras.getParcelable(com.webmd.webmdrx.util.Constants.EXTRA_DRUG);
            Parcelable parcelable2 = extras.getParcelable(com.webmd.webmdrx.util.Constants.EXTRA_LOCATION);
            this.mQuantity = extras.getDouble(com.webmd.webmdrx.util.Constants.EXTRA_QUANTITY);
            this.mPriceQuantity = extras.getDouble(com.webmd.webmdrx.util.Constants.EXTRA_PRICE_QUANTITY);
            this.mForm = extras.getString(com.webmd.webmdrx.util.Constants.EXTRA_FORM);
            this.mDosage = extras.getString(com.webmd.webmdrx.util.Constants.EXTRA_DOSAGE);
            this.mRadius = extras.getInt(com.webmd.webmdrx.util.Constants.EXTRA_RADIUS);
            this.mShowCurrentLocation = extras.getBoolean(com.webmd.webmdrx.util.Constants.EXTRA_CURRENT_LOCATION);
            this.cameFromProfile = extras.getBoolean("extra_sort_by_distance");
            this.originalQuantity = extras.getFloat(com.webmd.webmdrx.util.Constants.EXTRA_ORIGINAL_QUANTITY);
            this.packageSize = extras.getDouble(com.webmd.webmdrx.util.Constants.EXTRA_PACKAGE_SIZE);
            this.mIcd = extras.getString(com.webmd.webmdrx.util.Constants.EXTRA_ICD);
            this.locationZip = extras.getString(com.webmd.webmdrx.util.Constants.EXTRA_LOCATION_ZIP);
            if ((parcelable instanceof Drug) && (parcelable2 instanceof Location)) {
                Drug drug = (Drug) parcelable;
                this.mDrug = drug;
                drug.setPackageSize(this.packageSize + "");
                Location location = (Location) parcelable2;
                this.mLocation = location;
                fetchPricingForDrugAtLocation(this.mDrug, this.mPriceQuantity, location, this.locationZip);
                fetchAdditionalIds();
                TextView textView = (TextView) findViewById(R.id.a_pricing_text_view_drug_name);
                TextView textView2 = (TextView) findViewById(R.id.tv_controlled_substance);
                if (textView != null) {
                    textView.setText(this.mDrug.getValue());
                }
                textView2.setVisibility(!this.mDrug.getDeaClassCode().isEmpty() ? 0 : 8);
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ControlledSubstanceDialog.Companion.newInstance().show(PricingActivity.this.getSupportFragmentManager(), "dialog");
                    }
                });
            }
            setDrugDetailsComma();
            TextView textView3 = (TextView) findViewById(R.id.a_pricing_text_view_drug_details);
            if (textView3 != null) {
                textView3.setText(this.drugDetails);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
            return true;
        }
        if (itemId == R.id.a_pricing_image_view_map) {
            if (menuItem.getTitle().toString().equalsIgnoreCase("Map View")) {
                findViewById(R.id.list_layout).setVisibility(8);
                FrameLayout frameLayout = this.mapFrame;
                if (frameLayout != null) {
                    frameLayout.setVisibility(0);
                }
                menuItem.setIcon(R.drawable.ic_list_black_24dp);
                menuItem.setTitle("List View");
                this.mRxOmnitureSender.sendPageView("drug-prices/map");
                this.mCurrentPageSuffix = "map";
                downloadImagesMap(true);
            } else {
                menuItem.setTitle("Map View");
                menuItem.setIcon(R.drawable.map3x);
                findViewById(R.id.list_layout).setVisibility(0);
                FrameLayout frameLayout2 = this.mapFrame;
                if (frameLayout2 != null) {
                    frameLayout2.setVisibility(8);
                }
                this.redoSearchDone = false;
                this.mRxOmnitureSender.sendPageView("drug-prices/list");
                this.mCurrentPageSuffix = Constants.XML_LIST;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (!this.mGoogleApiClient.isConnecting() && !this.mGoogleApiClient.isConnected()) {
            this.mGoogleApiClient.connect();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.isKeyboardOpen) {
            ((InputMethodManager) getSystemService("input_method")).toggleSoftInput(1, 0);
        }
        this.mShimmerViewContainer.stopShimmer();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Parcelable parcelable;
        super.onNewIntent(intent);
        if (intent.getExtras() != null && (parcelable = intent.getExtras().getParcelable(com.webmd.webmdrx.util.Constants.EXTRA_LOCATION)) != null && Util.checkInternet(this)) {
            Location location = (Location) parcelable;
            this.mLocation = location;
            locationToText(location);
        }
    }

    public ShareFragmentDialog getSfd() {
        return this.sfd;
    }

    private void setUpActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.a_pricing_toolbar));
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

    private void setUpRecyclerView() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.pricing_recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PharmacyListAdapter pharmacyListAdapter = new PharmacyListAdapter(this.mData, this, (IPharmacyStarListener) null, this);
        this.mAdapter = pharmacyListAdapter;
        this.mRecyclerView.setAdapter(pharmacyListAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchPricingForDrugAtLocation(Drug drug, double d, Location location, String str) {
        if (areParametersValid(drug, location)) {
            hideLoadingDialog();
            showLoadingDialog();
            this.mLocation = location;
            locationToText(location);
            ApiManager.getInstance().fetchPricingDetailsForDrugIdAtLocation(this, drug.getDrugId(), d, location, this.mRadius, new IPricingReceivedListener() {
                public void onPricingReceived(RxPricing rxPricing) {
                    if (rxPricing != null) {
                        PricingActivity.this.fetchFailed = false;
                        final ArrayList arrayList = new ArrayList();
                        Collections.addAll(arrayList, rxPricing.getData().getPrices());
                        if (arrayList.size() > 0) {
                            PricingActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (PricingActivity.this.redoSearchDone) {
                                        PricingActivity.this.mData.clear();
                                        PricingActivity.this.mData.addAll(arrayList);
                                        PricingActivity.this.mAdapter.notifyDataSetChanged();
                                        boolean unused = PricingActivity.this.redoSearchDone = false;
                                    } else {
                                        PricingActivity.this.enableViews();
                                        PricingActivity.this.setUpData(arrayList);
                                        PricingActivity.this.setUpDataViews();
                                    }
                                    boolean unused2 = PricingActivity.this.isDataLoaded = true;
                                    PricingActivity.this.hideLoadingDialog();
                                    if (PricingActivity.this.mapFrame != null) {
                                        if (PricingActivity.this.mapFrame.getVisibility() == 0) {
                                            PricingActivity.this.downloadImagesMap(true);
                                            PricingActivity.this.onMapReady(PricingActivity.this.mMap);
                                        } else {
                                            PricingActivity.this.downloadImagesMap(false);
                                        }
                                    }
                                    PricingActivity.this.filterAfterLocationUpdate();
                                    PricingActivity.this.setMapMenuItemVisibility(true);
                                    PricingActivity.this.mShimmerViewContainer.stopShimmer();
                                    PricingActivity.this.mShimmerViewContainer.setVisibility(8);
                                }
                            });
                        } else {
                            PricingActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    PricingActivity.this.showNoCouponsState();
                                    PricingActivity.this.mData.clear();
                                    PricingActivity.this.hideLoadingDialog();
                                }
                            });
                        }
                    } else {
                        PricingActivity.this.hideLoadingDialog();
                    }
                }

                public void onPricingRequestFailed(WebMDException webMDException) {
                    PricingActivity.this.mShimmerViewContainer.stopShimmer();
                    PricingActivity.this.mShimmerViewContainer.setVisibility(8);
                    PricingActivity.this.mData.clear();
                    PricingActivity.this.fetchFailed = true;
                    boolean unused = PricingActivity.this.isDataLoaded = true;
                    boolean unused2 = PricingActivity.this.isIconsLoaded = true;
                    PricingActivity.this.hideLoadingDialog();
                    if (webMDException.getMessage().equalsIgnoreCase(PricingActivity.this.getString(R.string.location_error_message))) {
                        PricingActivity.this.showErrorDialog(webMDException.getMessage());
                    } else if (webMDException.getMessage().equalsIgnoreCase(PricingActivity.this.getString(R.string.no_pricing_results))) {
                        PricingActivity.this.showNoCouponsState();
                    } else {
                        PricingActivity.this.showErrorWithNavigationBack(webMDException.getMessage());
                    }
                    PricingActivity.this.hideLoadingDialog();
                }
            }, str);
        }
    }

    private void fetchAdditionalIds() {
        ApiManager.getInstance().fetchDrugDetails(this, this.mDrug.getDrugId(), new IDrugDetailsListener() {
            public void onDrugDetailsFetch(JSONObject jSONObject) {
                PricingActivity.this.fetchFailed = false;
                RxDrugDetail parseDrugDetails = RxProfileParser.parseDrugDetails(jSONObject);
                if (parseDrugDetails != null && !parseDrugDetails.getFdbId().isEmpty() && !parseDrugDetails.getMonoId().isEmpty()) {
                    if (PricingActivity.this.mDrug.getFdbId() == null) {
                        PricingActivity.this.mDrug.setFdbId(parseDrugDetails.getFdbId());
                    }
                    if (PricingActivity.this.mDrug.getMonoId() == null) {
                        PricingActivity.this.mDrug.setMonoId(parseDrugDetails.getMonoId());
                    }
                    if (PricingActivity.this.mDrug.getTitle() == null || PricingActivity.this.mDrug.getTitle().isEmpty()) {
                        PricingActivity.this.mDrug.setTitle(parseDrugDetails.getDrugName());
                    }
                }
            }

            public void onDrugDetailsFailed(String str) {
                PricingActivity.this.fetchFailed = true;
                PricingActivity pricingActivity = PricingActivity.this;
                pricingActivity.showErrorWithNavigationBack(pricingActivity.getResources().getString(R.string.error_connection_required));
            }
        });
    }

    /* access modifiers changed from: private */
    public void showErrorWithNavigationBack(final String str) {
        runOnUiThread(new Runnable() {
            public void run() {
                PricingActivity.this.hideLoadingDialog();
                PricingActivity.this.showErrorDialogFragment(str, new Intent(PricingActivity.this.getApplicationContext(), PrescriptionDetailsActivity.class));
            }
        });
    }

    /* access modifiers changed from: private */
    public void showNoCouponsState() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.list_layout);
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        FrameLayout frameLayout = this.mapFrame;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.rx_no_result_layout);
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        setMapMenuItemVisibility(false);
    }

    /* access modifiers changed from: private */
    public void setMapMenuItemVisibility(boolean z) {
        MenuItem menuItem = this.mapToggle;
        if (menuItem != null) {
            menuItem.setVisible(z);
        }
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
    public void showErrorDialog(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) str);
        builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                PricingActivity.this.showInputLocationDialog((DialogInterface) null);
            }
        });
        builder.show();
    }

    private void downloadImages() {
        HashSet<String> hashSet = new HashSet<>();
        Iterator<Price> it = this.mData.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getPharmacy().getImage());
        }
        for (String str : hashSet) {
            if (str == null || str.equals("")) {
                str = "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/webmdrx/assets/medicine_empty2x.png";
            }
            Picasso.get().load(str).into((Target) new Target() {
                public void onBitmapFailed(Exception exc, Drawable drawable) {
                }

                public void onPrepareLoad(Drawable drawable) {
                }

                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    Iterator it = PricingActivity.this.mData.iterator();
                    while (it.hasNext()) {
                        Price price = (Price) it.next();
                        if (price != null && price.getPharmacy() != null && price.getPharmacy().getImageBitmap() == null && bitmap != null) {
                            price.getPharmacy().setImageBitmap(bitmap);
                            return;
                        }
                    }
                }
            });
        }
    }

    private boolean areParametersValid(Drug drug, Location location) {
        return (drug == null || !StringUtil.isNotEmpty(drug.getDrugId()) || location == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public void hideLoadingDialog() {
        ProgressDialog progressDialog = this.mLoadingDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
        }
    }

    private void hideLoadingDialogMap() {
        ProgressDialog progressDialog;
        if (this.isDataLoaded && this.isMapLoaded && this.isIconsLoaded && (progressDialog = this.mLoadingDialog) != null && progressDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
        }
    }

    public void onPharmacyClick(Price price) {
        Intent intent = new Intent(this, PharmacyDetailActivity.class);
        boolean z = true;
        if (this.selectedFilter != 1) {
            z = false;
        }
        intent.putExtra("extra_sort_by_distance", z);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_DRUG, this.mDrug.getDrugId());
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_QUANTITY, this.mQuantity);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_LOCATION, this.mLocation);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_FORM, this.mForm);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_DOSAGE, this.mDosage);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_ICD, this.mIcd);
        intent.putExtra("extra_drug_name", this.mDrug.getValue());
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_PACKAGE_SIZE, this.packageSize);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_RADIUS, this.mRadius);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_PHARMACY_GROUP, price.getPharmacy().getPharmacyGroup());
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_PRICES, price);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_ICD_DRUG_NAME, this.mIcdDrugName);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_ICD_DRUG_ID, this.mIcdDrugId);
        intent.putExtra(com.webmd.webmdrx.util.Constants.EXTRA_LOCATION_ZIP, this.locationZip);
        startActivity(intent);
    }

    public void onPharmacyCouponClick(Price price) {
        goToSavings(getApplicationContext(), price, this.mForm, this.mDosage, this.mQuantity, this.mDrug.getValue(), this.packageSize, this.mIcd, this.mapFrame == null);
    }

    public void filterAfterLocationUpdate() {
        if (this.selectedFilter == 0) {
            Collections.sort(this.mData, Util.getPriceComparatorForPricingActivity());
        } else {
            Collections.sort(this.mData, Util.getDistanceComparatorForPricingActivity());
        }
        this.mAdapter.notifyDataSetChanged();
    }

    public WBMDOmnitureModule getModuleForDialog(String str) {
        String str2;
        String str3 = "";
        if (str.equals("filter-sort")) {
            str3 = "wrx-pricesort";
            str2 = WBMDOmnitureManager.shared.getLastSentPage();
        } else {
            str2 = str3;
        }
        if (str.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            str2 = WBMDOmnitureManager.shared.getLastSentPage();
            str3 = "wrx-pricesort-price";
        }
        if (str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            str2 = WBMDOmnitureManager.shared.getLastSentPage();
            str3 = "wrx-pricesort-distance";
        }
        return new WBMDOmnitureModule(str3, (String) null, str2);
    }

    /* access modifiers changed from: private */
    public void enableViews() {
        this.mRecyclerView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void setUpData(List<Price> list) {
        String str;
        this.mData.clear();
        this.mData.addAll(list);
        Collections.sort(this.mData, Util.getPriceComparatorForPricingActivity());
        Iterator<Price> it = this.mData.iterator();
        int i = 0;
        while (it.hasNext()) {
            Price next = it.next();
            if (!next.getPharmacy().getPharmacyGroup().equals("OTHER")) {
                str = "$" + String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(next.getDrugPriceInfo().getDiscountPricing())}) + " -";
            } else if (next.getPharmacy().getPharmacyGroupMinPrice() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || next.getPharmacy().getPharmacyGroupMaxPrice() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                str = "$" + String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(next.getDrugPriceInfo().getDiscountPricing())}) + " -";
            } else {
                str = "$" + String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(next.getPharmacy().getPharmacyGroupMaxPrice())}) + " -";
            }
            if (i < getWidth(str)) {
                i = getWidth(str);
            }
        }
        this.mAdapter.setMaxPriceWidth(i);
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void setUpDataViews() {
        downloadImages();
    }

    public void setDrugDetailsComma() {
        if (this.mDosage.isEmpty() || this.mDosage == null) {
            this.drugDetails = this.mForm + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            return;
        }
        this.drugDetails = this.mDosage + ", " + Integer.toString((int) this.mQuantity) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.mForm.toLowerCase();
    }

    private int getScreenWidth() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x;
    }

    public int getWidth(String str) {
        TextView textView = new TextView(getBaseContext());
        textView.setText(str, TextView.BufferType.SPANNABLE);
        textView.setTextSize(1, (float) 15);
        View.MeasureSpec.makeMeasureSpec(getScreenWidth(), Integer.MIN_VALUE);
        View.MeasureSpec.makeMeasureSpec(0, 0);
        textView.measure(0, 0);
        return textView.getMeasuredWidth();
    }

    private void showDialogWithTitleAndOk(String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) str);
        builder.setMessage((CharSequence) str2);
        builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void redoSearch() {
        this.loadedIcons = 0;
        this.isIconsLoaded = false;
        this.isDataLoaded = false;
        if (this.mMap != null) {
            this.redoSearchButton.setVisibility(8);
            final LatLng latLng = this.mMap.getCameraPosition().target;
            Location location = new Location("");
            location.setLatitude(latLng.latitude);
            location.setLongitude(latLng.longitude);
            this.mLocation = location;
            RxLocation locationToText = locationToText(location);
            new Thread(new Runnable() {
                public void run() {
                    PricingActivity.this.updateUserLocation(latLng.latitude, latLng.longitude);
                }
            }).start();
            this.redoSearchDone = true;
            String zip = locationToText != null ? locationToText.getZip() : this.locationZip;
            fetchPricingForDrugAtLocation(this.mDrug, this.mPriceQuantity, location, zip);
            this.mMapBounds = this.mMap.getProjection().getVisibleRegion().latLngBounds;
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if (googleMap != null) {
            setMarkers();
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.map_frame);
            RxLocation savedLocation = Util.getSavedLocation(this);
            LatLng latLng = new LatLng(savedLocation.getLatitude(), savedLocation.getLongitude());
            ArrayList<Price> arrayList = this.mData;
            if (arrayList != null && !arrayList.isEmpty()) {
                this.mMapBounds = Util.getLatLngBound(this.mData);
                if (frameLayout.getVisibility() != 8) {
                    this.mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(this.mMapBounds, 14), 500, (GoogleMap.CancelableCallback) null);
                } else {
                    this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                }
            } else if (frameLayout.getVisibility() != 8) {
                this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
            } else {
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
            }
            this.mMap.setOnMarkerClickListener(this);
            this.mMap.setOnCameraMoveStartedListener(this);
            this.mMap.setOnMapLoadedCallback(this);
            addUserLocation();
        }
    }

    private void addUserLocation() {
        if (!this.mShowCurrentLocation) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            GoogleMap googleMap = this.mMap;
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                return;
            }
            return;
        }
        requestLocationPermission();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        ShareFragmentDialog shareFragmentDialog = this.sfd;
        if (shareFragmentDialog != null && shareFragmentDialog.isAdded()) {
            this.sfd.handleOnRequestPermissionResult(i, strArr, iArr);
        }
        if (i == 1001 && iArr.length > 0 && iArr[0] == 0) {
            addUserLocation();
            findCurrentLocation();
        }
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1001);
    }

    private void setMarkers() {
        this.mMap.clear();
        LatLngBounds latLngBounds = this.mMapBounds;
        if (latLngBounds != null) {
            double d = latLngBounds.northeast.latitude;
            ArrayList<Price> arrayList = this.mData;
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<Price> it = this.mData.iterator();
                while (it.hasNext()) {
                    Price next = it.next();
                    Pharmacy pharmacy = next.getPharmacy();
                    if (pharmacy != null) {
                        new LatLng(pharmacy.getLatitude().doubleValue(), pharmacy.getLongitude().doubleValue());
                        int i = 0;
                        if (pharmacy.getImageBitmap() == null) {
                            ArrayList<Address> addresses = next.getPharmacy().getAddresses();
                            while (i < addresses.size()) {
                                Address address = addresses.get(i);
                                Marker addMarker = this.mMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude().doubleValue(), address.getLongitude().doubleValue())).icon(BitmapDescriptorFactory.defaultMarker()));
                                addMarker.setTag(new MarkerWindow(pharmacy.getName(), address, next.getDrugPriceInfo().getDiscountPricing()));
                                dropPinEffect(addMarker);
                                i++;
                            }
                        } else {
                            ArrayList<Address> addresses2 = next.getPharmacy().getAddresses();
                            while (i < addresses2.size()) {
                                Address address2 = addresses2.get(i);
                                Marker addMarker2 = this.mMap.addMarker(new MarkerOptions().position(new LatLng(address2.getLatitude().doubleValue(), address2.getLongitude().doubleValue())).icon(BitmapDescriptorFactory.fromBitmap(MarkerBackgroundProvider.Companion.provideBackground(pharmacy.getImageBitmap(), MarkerBackgroundProvider.LOGO_SCALE.FULL_SIZE, getResources()))));
                                addMarker2.setTag(new MarkerWindow(pharmacy.getName(), address2, next.getDrugPriceInfo().getDiscountPricing()));
                                dropPinEffect(addMarker2);
                                i++;
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void setIwa() {
        this.mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                PricingActivity pricingActivity = PricingActivity.this;
                pricingActivity.showSavingsCard((Context) pricingActivity, (MarkerWindow) marker.getTag(), pricingActivity.mForm, PricingActivity.this.mDosage, PricingActivity.this.mQuantity, PricingActivity.this.mDrug.getValue(), PricingActivity.this.packageSize, PricingActivity.this.mIcd);
            }
        });
        this.mMap.setInfoWindowAdapter(new PharmacyInfoWindowAdapter(this));
    }

    private void dropPinEffect(Marker marker) {
        Handler handler = new Handler();
        final long uptimeMillis = SystemClock.uptimeMillis();
        final OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
        final Marker marker2 = marker;
        final Handler handler2 = handler;
        handler.post(new Runnable() {
            public void run() {
                float max = Math.max(1.0f - overshootInterpolator.getInterpolation(((float) (SystemClock.uptimeMillis() - uptimeMillis)) / 1500.0f), 0.0f);
                marker2.setAnchor(0.5f, (14.0f * max) + 1.0f);
                if (((double) max) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    handler2.postDelayed(this, 15);
                } else {
                    PricingActivity.this.setIwa();
                }
            }
        });
    }

    public boolean onMarkerClick(Marker marker) {
        this.redoSearchButton.setVisibility(8);
        marker.showInfoWindow();
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(marker.getPosition(), 15.0f)));
        return true;
    }

    /* access modifiers changed from: private */
    public void updateUserLocation(double d, double d2) {
        android.location.Address address;
        try {
            List<android.location.Address> fromLocation = new Geocoder(this).getFromLocation(d, d2, 1);
            if (fromLocation != null && fromLocation.size() > 0 && (address = fromLocation.get(0)) != null) {
                if ((address.getCountryCode().equals("US") || address.getCountryCode().equals("PR")) && address.getAdminArea() != null && address.getPostalCode() != null) {
                    address.getAdminArea();
                    String postalCode = address.getPostalCode();
                    String str = "";
                    if (address.getLocality() != null) {
                        str = address.getLocality();
                    } else if (address.getSubLocality() != null) {
                        str = address.getSubLocality();
                    }
                    if (str != null) {
                        Util.saveUserLocation(getApplicationContext(), address);
                        Util.updateRxGroupZipCode(this, postalCode.substring(0, 3));
                    }
                }
            }
        } catch (IOException unused) {
        }
    }

    public void onCameraMoveStarted(int i) {
        if (i == 1) {
            this.redoSearchButton.setVisibility(0);
        }
    }

    public void onMapLoaded() {
        this.isMapLoaded = true;
        hideLoadingDialogMap();
    }

    /* access modifiers changed from: private */
    public void downloadImagesMap(final boolean z) {
        String str;
        if (this.mData.size() == 0) {
            this.loadedIcons = 0;
            this.isIconsLoaded = true;
            hideLoadingDialog();
            setUpMap(z);
            return;
        }
        int convertDpToPixel = (int) Util.convertDpToPixel(24.0f, this);
        for (int i = 0; i < this.mData.size(); i++) {
            final Price price = this.mData.get(i);
            if (StringUtil.isNotEmpty(price.getPharmacy().getImage())) {
                str = price.getPharmacy().getImage();
                if (!str.startsWith("http")) {
                    str = "https:" + str;
                }
            } else {
                str = "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/webmdrx/assets/medicine-empty2x.png";
            }
            final ImageView imageView = new ImageView(this);
            Picasso.get().load(str).error(R.drawable.medicine_empty2x).resize(convertDpToPixel, convertDpToPixel).into(imageView, new Callback() {
                public void onSuccess() {
                    setBitmap();
                    PricingActivity.this.setUpMap(z);
                }

                public void onError(Exception exc) {
                    setBitmap();
                }

                private void setBitmap() {
                    price.getPharmacy().setImageBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());
                    PricingActivity.access$2908(PricingActivity.this);
                    if (PricingActivity.this.loadedIcons == PricingActivity.this.mData.size()) {
                        int unused = PricingActivity.this.loadedIcons = 0;
                        boolean unused2 = PricingActivity.this.isIconsLoaded = true;
                        PricingActivity.this.hideLoadingDialog();
                    }
                }
            });
        }
    }

    private void chooseLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.custom_dialog_row_view, new String[]{getString(R.string.current_location), getString(R.string.dialog_input_option_custom_location)});
        this.titleView.setText(R.string.set_location);
        builder.setCustomTitle(this.titleView).setSingleChoiceItems((ListAdapter) arrayAdapter, this.selectedLocationOption, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    int unused = PricingActivity.this.selectedLocationOption = 0;
                    Util.clearUserLocation(PricingActivity.this.getApplicationContext());
                    PricingActivity.this.findCurrentLocation();
                    dialogInterface.cancel();
                    WBMDOmnitureManager.shared.getLastSentPage();
                }
                if (i == 1) {
                    int unused2 = PricingActivity.this.selectedLocationOption = 1;
                    PricingActivity.this.showInputLocationDialog(dialogInterface);
                    WBMDOmnitureManager.shared.getLastSentPage();
                }
            }
        });
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(true);
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        if (this.titleView.getParent() != null) {
            ((ViewGroup) this.titleView.getParent()).removeView(this.titleView);
        }
        create.show();
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
                    return;
                }
                createGoogleApiClientInstance();
                this.mGoogleApiClient.connect();
                return;
            }
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
            this.currentLocation = lastLocation;
            if (lastLocation == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, (LocationListener) this);
            } else {
                fetchPricingForDrugAtLocation(this.mDrug, this.mQuantity, lastLocation, this.locationZip);
            }
        } else {
            showTurnOnLocationDialog();
        }
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
                PricingActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });
        builder.create().show();
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
        return new WBMDOmnitureModule(str3, (String) null, str2);
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
                PricingActivity.this.hideKeyboard(create);
                boolean unused = PricingActivity.this.isKeyboardOpen = false;
                try {
                    List<android.location.Address> fromLocationName = new Geocoder(PricingActivity.this.getApplicationContext(), new Locale(com.webmd.webmdrx.util.Constants.LANGUAGE_LOCALE)).getFromLocationName(editText.getText().toString(), 5);
                    if (fromLocationName.size() == 0) {
                        create.dismiss();
                        PricingActivity.this.showSimpleDialog(dialogInterface, PricingActivity.this.getString(R.string.invalid_search));
                        return;
                    }
                    List<android.location.Address> uSLocations = LocationUtil.getUSLocations(fromLocationName);
                    if (uSLocations.size() > 0) {
                        List<android.location.Address> locationsWithZip = LocationUtil.getLocationsWithZip(uSLocations);
                        if (locationsWithZip.size() == 0) {
                            locationsWithZip = LocationUtil.getZipsForAddresses(uSLocations, PricingActivity.this.getBaseContext());
                        }
                        if (locationsWithZip.size() <= 0) {
                            create.dismiss();
                            PricingActivity.this.showSimpleDialog(dialogInterface, PricingActivity.this.getString(R.string.no_pricing_results));
                        } else if (locationsWithZip.size() == 1) {
                            PricingActivity.this.setSelectedUserLocation(dialogInterface, locationsWithZip.get(0));
                            create.dismiss();
                        } else {
                            create.dismiss();
                            PricingActivity.this.showMultipleLocationDialog(dialogInterface, locationsWithZip);
                        }
                    } else {
                        create.dismiss();
                        PricingActivity.this.showSimpleDialog(dialogInterface, PricingActivity.this.getString(R.string.only_for_us));
                    }
                } catch (IOException unused2) {
                    create.dismiss();
                    PricingActivity pricingActivity = PricingActivity.this;
                    pricingActivity.showSimpleDialog(dialogInterface, pricingActivity.getString(R.string.connection_error_message));
                }
            }
        });
        ((Button) inflate.findViewById(R.id.input_dialog_button_negative)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PricingActivity.this.hideKeyboard(create);
                boolean unused = PricingActivity.this.isKeyboardOpen = false;
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
                boolean unused = PricingActivity.this.isKeyboardOpen = false;
            }
        });
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                boolean unused = PricingActivity.this.isKeyboardOpen = false;
            }
        });
        create.show();
    }

    /* access modifiers changed from: private */
    public void setSelectedUserLocation(DialogInterface dialogInterface, android.location.Address address) {
        if (address != null) {
            if (address.getCountryCode().equals(getString(R.string.pr))) {
                address.setAdminArea(getString(R.string.puerto_rico));
            }
            if (address.getAdminArea() != null) {
                String adminArea = address.getAdminArea();
                String postalCode = address.getPostalCode();
                String cityFromAddress = Util.getCityFromAddress(address);
                if (!cityFromAddress.isEmpty()) {
                    TextView textView = this.currentLocationText;
                    textView.setText(cityFromAddress + ", " + Util.getStateCode(adminArea));
                    RxLocation addressToLocation = LocationUtil.addressToLocation(address);
                    this.selectedLocationOption = 1;
                    Util.saveUserLocation(getApplicationContext(), address);
                    Util.updateRxGroupZipCode(getBaseContext(), postalCode.substring(0, 3));
                    if (addressToLocation != null) {
                        Location location = new Location("");
                        location.setLatitude(addressToLocation.getLatitude());
                        location.setLongitude(addressToLocation.getLongitude());
                        this.mLocation = location;
                        fetchPricingForDrugAtLocation(this.mDrug, this.mQuantity, location, this.locationZip);
                    }
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
    public void showMultipleLocationDialog(final DialogInterface dialogInterface, final List<android.location.Address> list) {
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
                PricingActivity.this.showInputLocationDialog(dialogInterface);
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
                PricingActivity.this.setSelectedUserLocation(dialogInterface, (android.location.Address) list.get(i));
            }
        });
        builder.create().show();
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
                    PricingActivity.this.showInputLocationDialog(dialogInterface2);
                }
            }
        });
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(true);
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

    private void stopLocationUpdates() {
        if ((Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) && this.mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.mGoogleApiClient, (LocationListener) this);
        }
    }

    private RxLocation locationToText(Location location) {
        try {
            List<android.location.Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (fromLocation.size() > 0) {
                return LocationUtil.addressToLocation(fromLocation.get(0));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 1001);
            } catch (IntentSender.SendIntentException unused) {
                this.mGoogleApiClient.connect();
            }
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.currentLocation = location;
            stopLocationUpdates();
            fetchPricingForDrugAtLocation(this.mDrug, this.mQuantity, location, this.locationZip);
        }
    }

    private void setFirebaseUserProperty(String str, Boolean bool) {
        new PlatformRouteDispatcher(this).routeUserAttribute(str, bool.booleanValue());
        Trace.d(RemoteConfigComponent.DEFAULT_NAMESPACE, "setUserProperty : " + str + " : " + bool + " in PricingActivity");
    }
}
