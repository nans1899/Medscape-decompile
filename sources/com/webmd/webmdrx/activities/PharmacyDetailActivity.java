package com.webmd.webmdrx.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.pricing.PricingMapFullScreenActivity;
import com.webmd.webmdrx.adapters.PharmacyDetailAdapter;
import com.webmd.webmdrx.fragments.ErrorFragmentDialog;
import com.webmd.webmdrx.models.Address;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.MarkerBackgroundProvider;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.io.IOUtils;

public class PharmacyDetailActivity extends RxBaseActivity implements OnMapReadyCallback, PharmacyDetailAdapter.OnItemClickListener, GoogleMap.OnMapClickListener {
    private static final String TAG = "PharmacyDetailTAG_";
    private boolean isFirstLoad;
    private boolean isLocalPharmacy;
    private ArrayList<Address> mAddresses;
    private Address mCurrentAddress;
    /* access modifiers changed from: private */
    public Price mCurrentPrice;
    /* access modifiers changed from: private */
    public String mDosage;
    /* access modifiers changed from: private */
    public String mDrugName;
    /* access modifiers changed from: private */
    public String mForm;
    /* access modifiers changed from: private */
    public String mIcd;
    /* access modifiers changed from: private */
    public Bitmap mIcon;
    private ProgressDialog mLoadingDialog;
    private GoogleMap mMap;
    /* access modifiers changed from: private */
    public Double mPackageSize;
    private ArrayList<Price> mPriceHistory;
    /* access modifiers changed from: private */
    public Double mQuantity;
    /* access modifiers changed from: private */
    public FrameLayout mapFrame;
    private boolean sortByDistance;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pharmacy_detail);
        try {
            this.mapFrame = (FrameLayout) findViewById(R.id.map_frame);
        } catch (Throwable unused) {
        }
        showLoadingDialog();
        Bundle extras = getIntent().getExtras();
        String string = extras.getString(Constants.EXTRA_PHARMACY_GROUP);
        this.sortByDistance = extras.getBoolean("extra_sort_by_distance");
        Price price = (Price) extras.getParcelable(Constants.EXTRA_PRICES);
        this.mForm = extras.getString(Constants.EXTRA_FORM);
        this.mDosage = extras.getString(Constants.EXTRA_DOSAGE);
        this.mQuantity = Double.valueOf(extras.getDouble(Constants.EXTRA_QUANTITY));
        this.mDrugName = extras.getString("extra_drug_name");
        this.mPackageSize = Double.valueOf(extras.getDouble(Constants.EXTRA_PACKAGE_SIZE));
        this.mIcd = extras.getString(Constants.EXTRA_ICD);
        this.mIcdDrugName = getIntent().getStringExtra(Constants.EXTRA_ICD_DRUG_NAME);
        this.mIcdDrugId = getIntent().getStringExtra(Constants.EXTRA_ICD_DRUG_ID);
        if (string != null) {
            this.isLocalPharmacy = string.equals("OTHER");
        }
        double discountPricing = price.getDrugPriceInfo().getDiscountPricing();
        ((TextView) findViewById(R.id.a_pharmacy_detail_text_view_price)).setText("$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(discountPricing)}));
        if (price != null) {
            this.mCurrentPrice = price;
            Iterator<Address> it = price.getPharmacy().getAddresses().iterator();
            while (it.hasNext()) {
                Address next = it.next();
                if (next.getPharmID().equals(this.mCurrentPrice.getPharmacy().getPharmID())) {
                    this.mCurrentAddress = next;
                }
            }
            this.mCurrentAddress = this.mCurrentPrice.getPharmacy().getAddresses().get(0);
            setTitle();
            downloadIcon();
            fillPharmacyData(this.mCurrentAddress, this);
        }
        setUpActionBar();
        this.mPriceHistory = new ArrayList<>();
        ((Button) findViewById(R.id.button_show_coupon)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PharmacyDetailActivity pharmacyDetailActivity = PharmacyDetailActivity.this;
                pharmacyDetailActivity.goToSavings(pharmacyDetailActivity.getApplicationContext(), PharmacyDetailActivity.this.mCurrentPrice, PharmacyDetailActivity.this.mForm, PharmacyDetailActivity.this.mDosage, PharmacyDetailActivity.this.mQuantity.doubleValue(), PharmacyDetailActivity.this.mDrugName, PharmacyDetailActivity.this.mPackageSize.doubleValue(), PharmacyDetailActivity.this.mIcd, PharmacyDetailActivity.this.mapFrame == null);
            }
        });
        setPharmaciesAdapter();
        hideLoadingDialog();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        scrollToTop();
        super.onResume();
        this.mRxOmnitureSender.sendPageView("drug-prices/locations", "wrx-drugs-monotop", this.mIcdDrugName, this.mIcdDrugId);
        Util.logFirebaseEvent(this, Constants.FIRE_BASE_RX_LOCATION_DETAILS_SCREEN);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.a_pharmacy_detail_menu, menu);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_cancel_black);
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.toolbar_icon_gray), PorterDuff.Mode.SRC_ATOP);
        menu.findItem(R.id.a_pharmacy_detail_menu_close).setIcon(drawable);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            navigateBack();
            return true;
        } else if (itemId != R.id.a_pharmacy_detail_menu_close) {
            return true;
        } else {
            finish();
            return true;
        }
    }

    private void setUpActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.a_pharmacy_detail_toolbar));
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

    private void enableOtherLocationsViews(boolean z) {
        int i = z ? 0 : 8;
        View findViewById = findViewById(R.id.a_pharmacy_detail_separator);
        if (findViewById != null) {
            findViewById.setVisibility(i);
        }
        TextView textView = (TextView) findViewById(R.id.a_p_details_text_view_location_text);
        if (textView != null) {
            textView.setVisibility(i);
            ArrayList<Address> arrayList = this.mAddresses;
            if (arrayList != null && arrayList.size() == 2) {
                textView.setText(getString(R.string.other_rx_location));
            }
        }
        View findViewById2 = findViewById(R.id.a_pharmacy_detail_recycler_view);
        if (findViewById2 != null) {
            findViewById2.setVisibility(i);
        }
    }

    private void setPharmaciesAdapter() {
        ArrayList<Address> addresses = this.mCurrentPrice.getPharmacy().getAddresses();
        this.mAddresses = addresses;
        ArrayList arrayList = new ArrayList();
        Iterator<Address> it = addresses.iterator();
        while (it.hasNext()) {
            Address next = it.next();
            if (!next.getPharmID().equals(this.mCurrentAddress.getPharmID())) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() == 0) {
            enableOtherLocationsViews(false);
            return;
        }
        enableOtherLocationsViews(true);
        Collections.sort(arrayList, new Comparator<Address>() {
            public int compare(Address address, Address address2) {
                return Double.compare(address.getDistance().doubleValue(), address2.getDistance().doubleValue());
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.a_pharmacy_detail_recycler_view);
        PharmacyDetailAdapter pharmacyDetailAdapter = new PharmacyDetailAdapter(arrayList, this.isLocalPharmacy, this.sortByDistance);
        if (recyclerView != null) {
            recyclerView.setAdapter(pharmacyDetailAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                }
            });
            recyclerView.getLayoutParams().height = (int) (Util.convertDpToPixel(70.0f, this) * ((float) pharmacyDetailAdapter.getItemCount()));
            pharmacyDetailAdapter.setOnItemClickListener(this);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if (!this.isFirstLoad) {
            this.isFirstLoad = true;
            fillMapData(this.mCurrentAddress, false);
            Log.d(TAG, "onMapReady: ");
        }
        this.mMap.setOnMapClickListener(this);
        this.mMap.getUiSettings().setAllGesturesEnabled(false);
    }

    private void fillMapData(Address address, boolean z) {
        if (this.mapFrame != null) {
            LatLng latLng = new LatLng(address.getLatitude().doubleValue(), address.getLongitude().doubleValue());
            if (z) {
                this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
            } else {
                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
            }
            this.mMap.clear();
            if (this.mIcon == null || this.mCurrentPrice.getPharmacy().getImage().equals(getString(R.string.default_image_url))) {
                this.mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.medicine_empty2x)));
            } else {
                this.mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(this.mIcon))).setTag(this.mCurrentPrice);
            }
        }
        hideLoadingDialog();
    }

    private void showErrorDialogFragment(String str, Intent intent) {
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

    private void downloadIcon() {
        final ImageView imageView = new ImageView(this);
        String image = StringUtil.isNotEmpty(this.mCurrentPrice.getPharmacy().getImage()) ? this.mCurrentPrice.getPharmacy().getImage() : "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/webmdrx/assets/medicine-empty2x.png";
        int convertDpToPixel = (int) Util.convertDpToPixel(23.0f, this);
        Picasso.get().load(image).error(R.drawable.medicine_empty2x).resize(convertDpToPixel, convertDpToPixel).into(imageView, new Callback() {
            public void onSuccess() {
                setBMP();
            }

            public void onError(Exception exc) {
                setBMP();
            }

            private void setBMP() {
                Bitmap unused = PharmacyDetailActivity.this.mIcon = MarkerBackgroundProvider.Companion.provideBackground(((BitmapDrawable) imageView.getDrawable()).getBitmap(), MarkerBackgroundProvider.LOGO_SCALE.FULL_SIZE, PharmacyDetailActivity.this.getResources());
                PharmacyDetailActivity.this.setUpMap();
            }
        });
    }

    /* access modifiers changed from: private */
    public void setUpMap() {
        SupportMapFragment newInstance;
        if (this.mapFrame != null && (newInstance = SupportMapFragment.newInstance()) != null) {
            newInstance.getMapAsync(this);
        }
    }

    private void fillPharmacyData(Address address, Context context) {
        String str;
        if (address != null) {
            if (address.getPostalCode() == null || address.getPostalCode().length() <= 0) {
                str = "";
            } else {
                str = address.getPostalCode().length() > 5 ? address.getPostalCode().substring(0, 5) : address.getPostalCode();
            }
            String str2 = address.getAddress1() + IOUtils.LINE_SEPARATOR_UNIX + address.getCity() + ", " + address.getState() + ", " + str;
            if (!str2.isEmpty()) {
                TextView textView = (TextView) findViewById(R.id.a_pharmacy_detail_text_view_address);
                if (textView != null) {
                    textView.setText(str2);
                    textView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            PharmacyDetailActivity.this.goToFullScreenMap();
                        }
                    });
                }
                View findViewById = findViewById(R.id.a_pharmacy_detail_layout_address);
                if (findViewById != null) {
                    findViewById.setVisibility(0);
                    return;
                }
                return;
            }
            View findViewById2 = findViewById(R.id.a_pharmacy_detail_layout_address);
            if (findViewById2 != null) {
                findViewById2.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void goToFullScreenMap() {
        if (this.mapFrame != null) {
            Intent intent = new Intent(this, PricingMapFullScreenActivity.class);
            intent.putExtra(com.appboy.Constants.APPBOY_PUSH_EXTRAS_KEY, this.mCurrentPrice);
            startActivity(intent);
        }
    }

    private void navigateBack() {
        finish();
    }

    public void onItemClick(Address address) {
        if (address != null) {
            this.mPriceHistory.add(0, this.mCurrentPrice);
            reloadData(address);
        }
    }

    private void reloadData(Address address) {
        this.mCurrentAddress = address;
        showLoadingDialog();
        setPharmaciesAdapter();
        scrollToTop();
        setTitle();
        downloadIcon();
        fillPharmacyData(address, this);
        fillMapData(address, true);
    }

    private void setTitle() {
        TextView textView = (TextView) findViewById(R.id.a_pharmacy_detail_toolbar_title);
        if (textView != null) {
            textView.setText(this.mCurrentPrice.getPharmacy().getName());
        }
    }

    private void scrollToTop() {
        ScrollView scrollView = (ScrollView) findViewById(R.id.a_pharmacy_detail_scroll_view_content);
        if (scrollView != null) {
            scrollView.smoothScrollTo(0, 0);
        }
    }

    private void showLoadingDialog() {
        this.mLoadingDialog = ProgressDialog.show(this, "", getString(R.string.loading_dialog_text), true);
    }

    private void hideLoadingDialog() {
        ProgressDialog progressDialog = this.mLoadingDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
        }
    }

    public void onBackPressed() {
        navigateBack();
    }

    public void onMapClick(LatLng latLng) {
        goToFullScreenMap();
    }

    public Price getCurrentPrice() {
        return this.mCurrentPrice;
    }
}
