package com.wbmd.adlibrary.cache;

import android.content.Context;
import android.location.Location;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.wbmd.adlibrary.R;
import com.wbmd.adlibrary.callbacks.IGetLocationCallback;
import com.wbmd.wbmdcommons.logging.Trace;

public class LocationMemCache {
    private static final String TAG = LocationMemCache.class.getSimpleName();
    private static LocationMemCache mInstance;
    /* access modifiers changed from: private */
    public Location mLocation;

    private LocationMemCache() {
    }

    public static LocationMemCache getInstance() {
        if (mInstance == null) {
            mInstance = new LocationMemCache();
        }
        return mInstance;
    }

    public void cacheCurrentLocation(Context context) {
        try {
            if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                LocationServices.getFusedLocationProviderClient(context).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Location unused = LocationMemCache.this.mLocation = location;
                        }
                    }
                });
            }
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
        }
    }

    public Location getCurrentLocation() {
        return this.mLocation;
    }

    public void fetchLocation(final Context context, final IGetLocationCallback iGetLocationCallback) {
        try {
            if (this.mLocation != null || context == null) {
                iGetLocationCallback.onGetLocation(this.mLocation);
            } else if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Location unused = LocationMemCache.this.mLocation = location;
                            iGetLocationCallback.onGetLocation(LocationMemCache.this.mLocation);
                            return;
                        }
                        iGetLocationCallback.onGetLocationError(new Exception(context.getString(R.string.location_error_null)));
                    }
                });
                fusedLocationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                        iGetLocationCallback.onGetLocationError(exc);
                    }
                });
            } else {
                iGetLocationCallback.onGetLocationError(new Exception(context.getString(R.string.location_error_manifest)));
            }
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
            iGetLocationCallback.onGetLocationError(e);
        }
    }
}
