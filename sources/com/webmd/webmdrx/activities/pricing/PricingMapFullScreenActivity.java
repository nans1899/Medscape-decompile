package com.webmd.webmdrx.activities.pricing;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.appboy.Constants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.activities.CopyToClipboardActivity;
import com.webmd.webmdrx.activities.RxBaseActivity;
import com.webmd.webmdrx.fragments.BottomSheetShareDialog;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.util.StringUtil;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.IOUtils;

public class PricingMapFullScreenActivity extends RxBaseActivity implements OnMapReadyCallback, View.OnClickListener {
    /* access modifiers changed from: private */
    public Bitmap mIcon;
    private GoogleMap mMap;
    /* access modifiers changed from: private */
    public Price mPrice;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pricing_map_full_screen);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(enterTransition());
        }
        this.mPrice = (Price) getIntent().getExtras().getParcelable(Constants.APPBOY_PUSH_EXTRAS_KEY);
        downloadIcon();
        setUpActionBar();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mRxOmnitureSender.sendPageView("drug-prices/locations/expanded-map");
    }

    private void downloadIcon() {
        final ImageView imageView = new ImageView(this);
        Picasso.get().load(StringUtil.isNotEmpty(this.mPrice.getPharmacy().getImage()) ? this.mPrice.getPharmacy().getImage() : "http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/webmdrx/assets/medicine-empty2x.png").error(R.drawable.medicine_empty2x).resize(70, 70).into(imageView, new Callback() {
            public void onSuccess() {
                setBMP();
            }

            public void onError(Exception exc) {
                setBMP();
            }

            private void setBMP() {
                Bitmap unused = PricingMapFullScreenActivity.this.mIcon = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                PricingMapFullScreenActivity.this.setUpMap();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public Price getPrice() {
        return this.mPrice;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        super.onBackPressed();
        return true;
    }

    private void setUpActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.a_map_fullscreen_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(this, R.drawable.abc_ic_ab_back_material);
        if (drawable != null) {
            drawable.setColorFilter(ContextCompat.getColor(this, R.color.toolbar_icon_gray), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(drawable);
        }
        TextView textView = (TextView) findViewById(R.id.a_map_fullscreen_text_view_title);
        if (textView != null) {
            textView.setText(this.mPrice.getPharmacy().getName());
        }
    }

    /* access modifiers changed from: private */
    public void setUpMap() {
        SupportMapFragment.newInstance().getMapAsync(this);
    }

    private Transition enterTransition() {
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(2000);
        return changeBounds;
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        LatLng latLng = new LatLng(this.mPrice.getPharmacy().getLatitude().doubleValue(), this.mPrice.getPharmacy().getLongitude().doubleValue());
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
        if (this.mIcon == null || this.mPrice.getPharmacy().getImage().equals(getString(R.string.default_image_url))) {
            this.mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.medicine_empty2x)));
        } else {
            this.mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(this.mIcon)));
        }
        setIWA();
    }

    private void setIWA() {
        this.mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            public View getInfoWindow(Marker marker) {
                return null;
            }

            public View getInfoContents(Marker marker) {
                View inflate = PricingMapFullScreenActivity.this.getLayoutInflater().inflate(R.layout.pricing_marker_layout, (ViewGroup) PricingMapFullScreenActivity.this.findViewById(R.id.marker_layout_root));
                ImageView imageView = (ImageView) inflate.findViewById(R.id.marker_layout_image_view_logo);
                ((TextView) inflate.findViewById(R.id.marker_layout_text_view_name)).setText(PricingMapFullScreenActivity.this.mPrice.getPharmacy().getName());
                ((TextView) inflate.findViewById(R.id.marker_layout_text_view_distance)).setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(PricingMapFullScreenActivity.this.mPrice.getPharmacy().getDistance())}) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + PricingMapFullScreenActivity.this.getString(R.string.miles_away));
                double discountPricing = PricingMapFullScreenActivity.this.mPrice.getDrugPriceInfo().getDiscountPricing();
                ((TextView) inflate.findViewById(R.id.marker_layout_text_view_price)).setText("$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(discountPricing)}));
                if (PricingMapFullScreenActivity.this.mIcon == null || PricingMapFullScreenActivity.this.mPrice.getPharmacy().getImage().equals(PricingMapFullScreenActivity.this.getString(R.string.default_image_url))) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(PricingMapFullScreenActivity.this, R.drawable.medicine_empty2x));
                } else {
                    imageView.setImageBitmap(PricingMapFullScreenActivity.this.mIcon);
                }
                return inflate;
            }
        });
    }

    public void onClick(View view) {
        String str = "\nhttp://maps.google.com/?q=" + this.mPrice.getPharmacy().getLatitude() + "," + this.mPrice.getPharmacy().getLongitude();
        String str2 = this.mPrice.getPharmacy().getName() + IOUtils.LINE_SEPARATOR_UNIX + this.mPrice.getPharmacy().getAddress1() + IOUtils.LINE_SEPARATOR_UNIX + this.mPrice.getPharmacy().getCity() + ", " + this.mPrice.getPharmacy().getState() + ", " + this.mPrice.getPharmacy().getPostalCode();
        String phone = this.mPrice.getPharmacy().getPhone();
        if (phone != null && !phone.isEmpty()) {
            str2 = str2 + IOUtils.LINE_SEPARATOR_UNIX + phone;
        }
        String str3 = str2 + IOUtils.LINE_SEPARATOR_UNIX + str;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", this.mPrice.getPharmacy().getName());
        intent.putExtra("android.intent.extra.TEXT", str3);
        intent.setType("text/plain");
        Iterator<ResolveInfo> it = getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo next = it.next();
            if (next.activityInfo.packageName.startsWith("com.google.android.gm")) {
                intent.setPackage(next.activityInfo.packageName);
                break;
            }
        }
        double doubleValue = this.mPrice.getPharmacy().getLatitude().doubleValue();
        double doubleValue2 = this.mPrice.getPharmacy().getLongitude().doubleValue();
        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("geo:" + doubleValue + "," + doubleValue2 + "?q=" + doubleValue + "," + doubleValue2 + "(" + this.mPrice.getPharmacy().getName() + ")"));
        intent2.setPackage("com.google.android.apps.maps");
        Intent intent3 = new Intent(this, CopyToClipboardActivity.class);
        intent3.putExtra("android.intent.extra.TEXT", str3);
        BottomSheetShareDialog bottomSheetShareDialog = new BottomSheetShareDialog();
        bottomSheetShareDialog.setGmailIntent(intent);
        bottomSheetShareDialog.setMapsIntent(intent2);
        bottomSheetShareDialog.setCopyIntent(intent3);
        try {
            bottomSheetShareDialog.setGmailIcon(getPackageManager().getApplicationIcon(intent.getPackage()));
            bottomSheetShareDialog.setMapsIcon(getPackageManager().getApplicationIcon(intent2.getPackage()));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        bottomSheetShareDialog.show(getSupportFragmentManager(), "ShareSheet");
        WBMDOmnitureManager.sendPageView(WBMDOmnitureManager.shared.getLastSentPage(), (Map<String, String>) null, getModuleForShare());
    }

    private WBMDOmnitureModule getModuleForShare() {
        return new WBMDOmnitureModule("wrx-mapshare", (String) null, WBMDOmnitureManager.shared.getLastSentPage());
    }
}
