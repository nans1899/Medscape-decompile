package com.webmd.webmdrx.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.models.RxLocation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationUtil {
    public static Address getZipForLocation(double d, double d2, Context context) throws IOException {
        if (context != null) {
            List<Address> fromLocation = new Geocoder(context, new Locale(Constants.LANGUAGE_LOCALE)).getFromLocation(d, d2, 5);
            if (fromLocation.size() != 0) {
                for (Address next : fromLocation) {
                    if (next.getPostalCode() != null) {
                        return next;
                    }
                }
                throw new IOException(context.getString(R.string.location_utils_error_zip));
            }
            throw new IOException(context.getString(R.string.location_utils_error_address));
        }
        throw new IOException("Context was null");
    }

    public static List<Address> getUSLocations(List<Address> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (Address next : list) {
                if (isUSAddress(next)) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public static List<Address> getLocationsWithZip(List<Address> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (Address next : list) {
                if (next.getPostalCode() != null) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public static List<Address> getZipsForAddresses(List<Address> list, Context context) throws IOException {
        Address zipForLocation;
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            try {
                for (Address next : list) {
                    if (!(next == null || (zipForLocation = getZipForLocation(next.getLatitude(), next.getLongitude(), context)) == null || zipForLocation.getPostalCode() == null)) {
                        arrayList.add(zipForLocation);
                    }
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return arrayList;
    }

    public static boolean isUSAddress(Address address) {
        if (address.getCountryCode() == null) {
            return false;
        }
        if (address.getCountryCode().equals("US") || address.getCountryCode().equals("PR")) {
            return true;
        }
        return false;
    }

    public static Address isLocationValid(String str, Context context) throws IOException {
        if (str == null || context == null) {
            return null;
        }
        List<Address> fromLocationName = new Geocoder(context).getFromLocationName(str, 3);
        if (fromLocationName.size() != 0) {
            for (Address next : fromLocationName) {
                if (!(next.getCountryCode() == null || next.getPostalCode() == null)) {
                    if (next.getCountryCode().equals(context.getString(R.string.us)) || next.getCountryCode().equals(context.getString(R.string.pr))) {
                        return next;
                    }
                }
            }
            return null;
        }
        throw new IOException();
    }

    public static Address isLocationValidLatLng(double d, double d2, Context context) throws IOException {
        if (context != null) {
            List<Address> fromLocation = new Geocoder(context, new Locale(Constants.LANGUAGE_LOCALE)).getFromLocation(d, d2, 1);
            if (fromLocation.size() != 0) {
                Address address = fromLocation.get(0);
                if (address == null) {
                    throw new IOException(context.getString(R.string.connection_error_message));
                } else if (address.getCountryCode().equals(context.getString(R.string.us)) || address.getCountryCode().equals(context.getString(R.string.pr))) {
                    return address;
                } else {
                    return null;
                }
            } else {
                throw new IOException(context.getString(R.string.connection_error_message));
            }
        } else {
            throw new IOException("Context was null");
        }
    }

    public static RxLocation addressToLocation(Address address) {
        RxLocation rxLocation = new RxLocation();
        if (address != null) {
            rxLocation.setLatitude(address.getLatitude());
            rxLocation.setLongitude(address.getLongitude());
            String cityFromAddress = Util.getCityFromAddress(address);
            String stateCode = Util.getStateCode(address.getAdminArea());
            if (cityFromAddress.isEmpty() || stateCode.isEmpty()) {
                rxLocation.setCityState("");
            } else {
                rxLocation.setCityState(cityFromAddress + ", " + stateCode);
            }
            rxLocation.setZip(address.getPostalCode());
        }
        return rxLocation;
    }
}
