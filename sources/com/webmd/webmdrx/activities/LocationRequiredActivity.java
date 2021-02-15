package com.webmd.webmdrx.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.intf.ICurrentLocationListener;
import com.webmd.webmdrx.util.Constants;
import com.webmd.webmdrx.util.LocationUtil;
import com.webmd.webmdrx.util.Util;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class LocationRequiredActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final int ID_REQUEST_LOCATION = 2002;
    /* access modifiers changed from: private */
    public boolean isKeyboardOpen;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    ICurrentLocationListener mListener;
    LocationRequest mLocationRequest;
    boolean reconnectingGoogleApi = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        createLocationRequest();
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.mGoogleApiClient.connect();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void onConnected(Bundle bundle) {
        if (this.reconnectingGoogleApi) {
            this.reconnectingGoogleApi = false;
            findCurrentLocation(this.mListener);
        }
    }

    public void onConnectionSuspended(int i) {
        ICurrentLocationListener iCurrentLocationListener = this.mListener;
        if (iCurrentLocationListener != null) {
            iCurrentLocationListener.onLocationFailed();
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        ICurrentLocationListener iCurrentLocationListener = this.mListener;
        if (iCurrentLocationListener != null) {
            iCurrentLocationListener.onLocationFailed();
        }
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 2002);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 2002) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                ICurrentLocationListener iCurrentLocationListener = this.mListener;
                if (iCurrentLocationListener != null) {
                    iCurrentLocationListener.onLocationFailed();
                    return;
                }
                return;
            }
            findCurrentLocation(this.mListener);
        }
    }

    public void findCurrentLocation(ICurrentLocationListener iCurrentLocationListener) {
        if (iCurrentLocationListener != null) {
            this.mListener = iCurrentLocationListener;
            if (!(Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0)) {
                requestLocationPermission();
            } else if (Util.isGPSOn(this, getContentResolver())) {
                GoogleApiClient googleApiClient = this.mGoogleApiClient;
                if (googleApiClient == null || !googleApiClient.isConnected()) {
                    this.reconnectingGoogleApi = true;
                    this.mGoogleApiClient.connect();
                    return;
                }
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
                this.mLastLocation = lastLocation;
                if (lastLocation == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, (LocationListener) this);
                } else {
                    isUSLocation(lastLocation);
                }
            } else {
                showTurnOnLocationDialog();
            }
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.mLastLocation = location;
            stopLocationUpdates();
            isUSLocation(this.mLastLocation);
        }
    }

    /* access modifiers changed from: protected */
    public void stopLocationUpdates() {
        if (this.mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.mGoogleApiClient, (LocationListener) this);
        }
    }

    /* access modifiers changed from: protected */
    public void createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        this.mLocationRequest = locationRequest;
        locationRequest.setInterval(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        this.mLocationRequest.setFastestInterval(2000);
        this.mLocationRequest.setNumUpdates(4);
        this.mLocationRequest.setPriority(102);
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
                LocationRequiredActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void isUSLocation(Location location) {
        try {
            if (LocationUtil.isLocationValidLatLng(location.getLatitude(), location.getLongitude(), this) == null) {
                showNotUSError(getString(R.string.only_for_us));
            } else if (this.mListener != null) {
                this.mListener.onLocationReceived(location);
            }
        } catch (IOException unused) {
            showError(getString(R.string.error_connection_required));
        }
    }

    private void showNotUSError(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) str);
        builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LocationRequiredActivity.this.showInputLocationDialog();
            }
        });
        builder.create().show();
    }

    private void showError(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) str);
        builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    /* access modifiers changed from: private */
    public void showInputLocationDialog() {
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
                LocationRequiredActivity.this.hideKeyboard(create);
                boolean z = false;
                boolean unused = LocationRequiredActivity.this.isKeyboardOpen = false;
                try {
                    List<Address> fromLocationName = new Geocoder(LocationRequiredActivity.this.getApplicationContext(), new Locale(Constants.LANGUAGE_LOCALE)).getFromLocationName(editText.getText().toString(), 5);
                    if (fromLocationName.size() == 0) {
                        create.dismiss();
                        LocationRequiredActivity.this.showSimpleDialog(LocationRequiredActivity.this.getString(R.string.invalid_search));
                        return;
                    }
                    Location location = new Location("");
                    Iterator<Address> it = fromLocationName.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Address next = it.next();
                        if (next.hasLatitude() && next.hasLongitude()) {
                            z = true;
                            location.setLongitude(next.getLongitude());
                            location.setLatitude(next.getLatitude());
                            break;
                        }
                    }
                    if (z) {
                        LocationRequiredActivity.this.isUSLocation(location);
                        create.dismiss();
                        return;
                    }
                    create.dismiss();
                    LocationRequiredActivity.this.showSimpleDialog(LocationRequiredActivity.this.getString(R.string.no_pricing_results));
                } catch (IOException unused2) {
                    create.dismiss();
                    LocationRequiredActivity locationRequiredActivity = LocationRequiredActivity.this;
                    locationRequiredActivity.showSimpleDialog(locationRequiredActivity.getString(R.string.connection_error_message));
                }
            }
        });
        ((Button) inflate.findViewById(R.id.input_dialog_button_negative)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LocationRequiredActivity.this.hideKeyboard(create);
                boolean unused = LocationRequiredActivity.this.isKeyboardOpen = false;
                create.dismiss();
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
                boolean unused = LocationRequiredActivity.this.isKeyboardOpen = false;
            }
        });
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                boolean unused = LocationRequiredActivity.this.isKeyboardOpen = false;
            }
        });
        create.show();
    }

    /* access modifiers changed from: private */
    public void showSimpleDialog(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) str);
        builder.setPositiveButton((CharSequence) getString(R.string.ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                LocationRequiredActivity.this.showInputLocationDialog();
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

    private void showMultipleLocationDialog(DialogInterface dialogInterface, final List<Address> list) {
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
                LocationRequiredActivity.this.showInputLocationDialog();
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
                Address address = (Address) list.get(i);
            }
        });
        builder.create().show();
    }
}
