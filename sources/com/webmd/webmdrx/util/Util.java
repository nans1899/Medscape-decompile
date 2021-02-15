package com.webmd.webmdrx.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.medscape.android.Constants;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.models.Day;
import com.webmd.webmdrx.models.Drug;
import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.models.Pharmacy;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.models.Quantity;
import com.webmd.webmdrx.models.RxLocation;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    private static final String TAG = Util.class.getSimpleName();

    public static char getMonthLetter(int i) {
        if (i < 0 || i > 11) {
            return 'A';
        }
        if (i > 9) {
            i++;
        }
        if (i > 7) {
            i++;
        }
        return (char) (i + 65);
    }

    public static char getYearLetter(int i) {
        if (i < 16) {
            return 'A';
        }
        if (i > 36) {
            i++;
        }
        if (i > 25) {
            i++;
        }
        if (i > 23) {
            i++;
        }
        return (char) ((i - 16) + 65);
    }

    public static boolean isLetter(char c) {
        if (c < 'A' || c > 'Z') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public static void setAlarmOnStartUp(Context context, boolean z) {
    }

    public static String severityToText(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "Contraindicated" : "Serious - Use Alternative" : "Monitor Closely" : "Minor";
    }

    public static void showKeyboard(Context context) {
        Object systemService;
        if (context != null && context.getApplicationContext() != null && (systemService = context.getApplicationContext().getApplicationContext().getSystemService("input_method")) != null && (systemService instanceof InputMethodManager)) {
            ((InputMethodManager) systemService).toggleSoftInput(2, 0);
        }
    }

    public static void hideKeyboard(Activity activity) {
        Object systemService;
        InputMethodManager inputMethodManager;
        if (activity != null && (systemService = activity.getSystemService("input_method")) != null && (systemService instanceof InputMethodManager) && (inputMethodManager = (InputMethodManager) systemService) != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static String getUUID(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_user_id), (String) null);
    }

    public static String getRxGroup(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_rx_group), context.getString(R.string.cxafxxxxx));
    }

    public static String getMemberId(Context context) {
        String uniqueMemberId = getUniqueMemberId(context);
        return (uniqueMemberId == null || uniqueMemberId.isEmpty()) ? PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_member_id), context.getString(R.string.wbmd0616)) : uniqueMemberId;
    }

    public static String getUniqueMemberId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_unique_member_id), "");
    }

    public static void setUniqueMemberId(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_unique_member_id), str);
        edit.apply();
    }

    public static boolean isFirstLaunch(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.pref_is_first_launch), true);
    }

    public static boolean isFirstSearch(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.pref_is_first_search), true);
    }

    public static boolean isFirstShare(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.pref_is_first_share), true);
    }

    public static long getInstallDateInMillis(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(context.getString(R.string.pref_install_date_millis), 0);
    }

    public static int getPriceSearchCount(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.pref_price_search_count), 0);
    }

    public static boolean isFirstTimeSaveMessageShowed(Context context) {
        if (context != null) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.pref_price_search_count), false);
        }
        return false;
    }

    public static void saveFirstTimeSaveMessageShowed(Context context, boolean z) {
        if (context != null) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putBoolean(context.getString(R.string.pref_price_search_count), z);
            edit.commit();
        }
    }

    public static void increasePriceSearchCount(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(context.getString(R.string.pref_price_search_count), getPriceSearchCount(context) + 1);
        edit.commit();
    }

    public static void updateRxGroupZipCode(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String substring = getRxGroup(context).substring(0, 4);
        edit.putString(context.getString(R.string.pref_rx_group), substring + str + "XX");
        edit.commit();
    }

    public static void updateRxGroupDate(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(2);
        String str = ("CX" + getYearLetter(instance.get(1) - 2000)) + getMonthLetter(i);
        edit.putString(context.getString(R.string.pref_rx_group), (str + getRxGroup(context).substring(4, 7)) + "XX");
        edit.apply();
    }

    public static void saveFirstSearch(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(context.getString(R.string.pref_is_first_search), false);
        edit.apply();
    }

    public static void saveFirstShare(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(context.getString(R.string.pref_is_first_share), false);
        edit.apply();
    }

    public static void saveFirstLaunchData(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(2);
        int i2 = instance.get(1) - 2000;
        String uuid = UUID.randomUUID().toString();
        String rxGroup = getRxGroup(context);
        if (rxGroup.equals(context.getString(R.string.cxafxxxxx))) {
            rxGroup = buildRxGroup(i, i2);
        }
        edit.putString(context.getString(R.string.pref_rx_group), rxGroup);
        edit.putString(context.getString(R.string.pref_member_id), buildMemberId(i, i2));
        edit.putBoolean(context.getString(R.string.pref_is_first_launch), false);
        edit.putString(context.getString(R.string.pref_user_id), uuid);
        edit.putLong(context.getString(R.string.pref_install_date_millis), date.getTime());
        edit.apply();
    }

    public static String buildMemberId(int i, int i2) {
        int i3 = i + 1;
        return ((i3 < 10 ? "WBMD0" : "WBMD") + i3) + i2;
    }

    public static String buildRxGroup(int i, int i2) {
        return ((("CX" + getYearLetter(i2)) + getMonthLetter(i)) + "XXX") + "XX";
    }

    public static void saveDrugToRecentSearches(Context context, DrugSearchResult drugSearchResult) {
        DrugSearchResult[] drugSearchResultArr;
        DrugSearchResult[] drugSearchResultArr2;
        String recentSearches = getRecentSearches(context);
        Gson gson = new Gson();
        DrugSearchResult[] convertRecentSearchJson = convertRecentSearchJson(recentSearches);
        if (convertRecentSearchJson == null) {
            drugSearchResultArr = new DrugSearchResult[1];
        } else {
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= convertRecentSearchJson.length) {
                    break;
                } else if (convertRecentSearchJson[i2].getDrugId().equals(drugSearchResult.getDrugId())) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i >= 0) {
                DrugSearchResult[] drugSearchResultArr3 = new DrugSearchResult[convertRecentSearchJson.length];
                drugSearchResultArr3[0] = drugSearchResult;
                System.arraycopy(convertRecentSearchJson, 0, drugSearchResultArr3, 1, i);
                int i3 = i + 1;
                System.arraycopy(convertRecentSearchJson, i3, drugSearchResultArr3, i3, convertRecentSearchJson.length - i3);
                drugSearchResultArr = drugSearchResultArr3;
            } else {
                if (convertRecentSearchJson.length < 20) {
                    drugSearchResultArr2 = new DrugSearchResult[(convertRecentSearchJson.length + 1)];
                    System.arraycopy(convertRecentSearchJson, 0, drugSearchResultArr2, 1, convertRecentSearchJson.length);
                } else {
                    drugSearchResultArr2 = new DrugSearchResult[20];
                    System.arraycopy(convertRecentSearchJson, 0, drugSearchResultArr2, 1, 19);
                }
                drugSearchResultArr = drugSearchResultArr2;
            }
        }
        drugSearchResultArr[0] = drugSearchResult;
        saveRecentSearches(context, gson.toJson((Object) drugSearchResultArr));
    }

    public static DrugSearchResult[] convertRecentSearchJson(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (!str.contains("drugId")) {
            return (DrugSearchResult[]) new Gson().fromJson(str, DrugSearchResult[].class);
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                return null;
            }
            DrugSearchResult[] drugSearchResultArr = new DrugSearchResult[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                DrugSearchResult drugSearchResult = new DrugSearchResult();
                drugSearchResult.setDrugId(jSONObject.getString("drugId"));
                drugSearchResult.setDrugName(jSONObject.getString("drugName"));
                drugSearchResult.setIsGeneric(jSONObject.getBoolean("isGeneric"));
                DrugSearchResult drugSearchResult2 = new DrugSearchResult();
                drugSearchResult2.setDrugName(jSONObject.getString("otherName"));
                ArrayList arrayList = new ArrayList();
                arrayList.add(drugSearchResult2);
                drugSearchResult.setOtherNames(arrayList);
                drugSearchResultArr[i] = drugSearchResult;
            }
            return drugSearchResultArr;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFullNameForDrug(DrugSearchResult drugSearchResult) {
        if (drugSearchResult == null) {
            return "";
        }
        String drugName = drugSearchResult.getDrugName();
        if (drugSearchResult.getOtherNames() == null || drugSearchResult.getOtherNames().size() != 1 || drugSearchResult.getOtherNames().get(0).getDrugName().isEmpty() || drugSearchResult.getOtherNames().get(0).getDrugName() == null) {
            return drugName;
        }
        String str = drugName + " (";
        if (drugSearchResult.isGeneric()) {
            str = str + "generic ";
        }
        return str + drugSearchResult.getOtherNames().get(0).getDrugName() + ")";
    }

    public static String getRecentSearchesAppboy(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_recent_search_appboy), "");
    }

    public static String getRecentSearches(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_recent_search), "");
    }

    public static void clearRecentSearches(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_recent_search), "");
        edit.apply();
    }

    private static void saveRecentSearches(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_recent_search), str);
        edit.putString(context.getString(R.string.pref_recent_search_appboy), str);
        edit.apply();
    }

    public static void saveLanguagePreference(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_language_preferences), str);
        edit.apply();
    }

    public static String getLanguagePreferences(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).getString(activity.getString(R.string.pref_language_preferences), Locale.getDefault().getLanguage());
    }

    public static void setAppLanguage(Activity activity) {
        Resources resources = activity.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(Constants.LANGUAGE_LOCALE);
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static String stripSearch(String str) {
        boolean z = false;
        while (!z && str.length() > 0) {
            if (isLetter(str.charAt(0)) || isNumber(str.charAt(0))) {
                z = true;
            } else {
                str = str.substring(1);
            }
        }
        return str.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20");
    }

    public static String savingsCardToString(Context context, View view) {
        String string = context.getString(R.string.tag_rx_bin);
        String string2 = context.getString(R.string.tag_rx_pcn);
        String string3 = context.getString(R.string.tag_rx_group);
        String string4 = context.getString(R.string.tag_member_id);
        return (((string + ": " + context.getString(R.string.value_rx_bin)) + IOUtils.LINE_SEPARATOR_UNIX + string2 + ": " + context.getString(R.string.value_rx_pcn)) + IOUtils.LINE_SEPARATOR_UNIX + string3 + ": " + ((TextView) view.findViewById(R.id.f_savings_text_view_group)).getText()) + IOUtils.LINE_SEPARATOR_UNIX + string4 + ": " + ((TextView) view.findViewById(R.id.f_savings_text_view_member)).getText();
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static void saveAppIconToDevice(Activity activity, Bitmap bitmap) {
        String file = Environment.getExternalStorageDirectory().toString();
        File file2 = new File(file + activity.getString(R.string.app_icon_save_folder_name));
        file2.mkdirs();
        File file3 = new File(file2, activity.getString(R.string.app_icon_save_file_name));
        if (!file3.exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(file3));
                activity.sendBroadcast(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveBitmapToDevice(Context context, Bitmap bitmap) {
        String file = Environment.getExternalStorageDirectory().toString();
        File file2 = new File(file + context.getString(R.string.share_dialog_save_folder_name));
        file2.mkdirs();
        String valueOf = String.valueOf(System.currentTimeMillis());
        File file3 = new File(file2, valueOf + ".jpg");
        if (file3.exists()) {
            file3.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            addToGallery(context, file3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addToGallery(Context context, File file) {
        MediaScannerConnection.scanFile(context, new String[]{file.getPath()}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
    }

    public static File getSavedSavingsCard(Context context) {
        String file = Environment.getExternalStorageDirectory().toString();
        File file2 = new File(file + context.getString(R.string.share_dialog_save_folder_name));
        file2.mkdirs();
        File file3 = new File(file2, context.getString(R.string.share_dialog_save_file_name));
        if (file3.exists()) {
            return file3;
        }
        return null;
    }

    public static File getAppIconFile(Context context) {
        String file = Environment.getExternalStorageDirectory().toString();
        File file2 = new File(file + context.getString(R.string.app_icon_save_folder_name));
        file2.mkdirs();
        File file3 = new File(file2, context.getString(R.string.app_icon_save_file_name));
        if (file3.exists()) {
            return file3;
        }
        return null;
    }

    public static List<Drug> getUniqueDrugs(List<Drug> list) {
        boolean z;
        if (list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            String value = list.get(i).getValue();
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    z = true;
                    break;
                } else if (value.equals(((Drug) arrayList.get(i2)).getValue())) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    public static List<Drug> getUniqueForms(Drug drug, List<Drug> list) {
        boolean z;
        if (drug == null || list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        String value = drug.getValue();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(value)) {
                String form = list.get(i).getForm();
                int i2 = 0;
                while (true) {
                    if (i2 >= arrayList.size()) {
                        z = true;
                        break;
                    } else if (form.equals(((Drug) arrayList.get(i2)).getForm())) {
                        z = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    arrayList.add(list.get(i));
                }
            }
        }
        return arrayList;
    }

    public static List<Drug> getUniqueDosages(Drug drug, List<Drug> list) {
        boolean z;
        if (drug == null || list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        String value = drug.getValue();
        String form = drug.getForm();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(value) && list.get(i).getForm().equals(form)) {
                String strength = list.get(i).getStrength();
                int i2 = 0;
                while (true) {
                    if (i2 >= arrayList.size()) {
                        z = true;
                        break;
                    } else if (strength.equals(((Drug) arrayList.get(i2)).getStrength())) {
                        z = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    arrayList.add(list.get(i));
                }
            }
        }
        return arrayList;
    }

    public static List<Drug> getUniquePackageSizes(Drug drug, List<Drug> list) {
        if (drug == null || list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        String value = drug.getValue();
        String form = drug.getForm();
        String strength = drug.getStrength();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(value) && list.get(i).getForm().equals(form) && list.get(i).getStrength().equals(strength)) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    public static void sortQuantities(List<Quantity> list) {
        Collections.sort(list, new Comparator<Quantity>() {
            public int compare(Quantity quantity, Quantity quantity2) {
                return quantity.getQuantity() - quantity2.getQuantity();
            }
        });
    }

    public static String[] getRadiusOptions(Context context) {
        return new String[]{context.getString(R.string.dialog_radius_5_miles), "10 " + context.getString(R.string.dialog_miles), "15 " + context.getString(R.string.dialog_miles), "20 " + context.getString(R.string.dialog_miles), "25 " + context.getString(R.string.dialog_miles), "50 " + context.getString(R.string.dialog_miles)};
    }

    public static String getPriceRange(ArrayList<Price> arrayList, Context context) {
        double d;
        if (arrayList == null || arrayList.size() < 1) {
            return context.getString(R.string.error_calculating_price_range);
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList2.addAll(arrayList);
        arrayList3.addAll(arrayList);
        Collections.sort(arrayList2, new Comparator<Price>() {
            public int compare(Price price, Price price2) {
                return price.getDrugPriceInfo().getRegularPricing().getWAvg() - price2.getDrugPriceInfo().getRegularPricing().getWAvg() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : -1;
            }
        });
        Collections.sort(arrayList3, new Comparator<Price>() {
            public int compare(Price price, Price price2) {
                return price.getDrugPriceInfo().getRegularPricing().getMax() - price2.getDrugPriceInfo().getRegularPricing().getMax() < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : -1;
            }
        });
        Collections.sort(arrayList, new Comparator<Price>() {
            public int compare(Price price, Price price2) {
                return price.getDrugPriceInfo().getDiscountPricing() - price2.getDrugPriceInfo().getDiscountPricing() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : -1;
            }
        });
        double max = ((Price) arrayList3.get(0)).getDrugPriceInfo().getRegularPricing().getMax();
        double discountPricing = arrayList.get(0).getDrugPriceInfo().getDiscountPricing();
        Iterator it = arrayList2.iterator();
        while (true) {
            if (!it.hasNext()) {
                d = 0.0d;
                break;
            }
            Price price = (Price) it.next();
            if (price.getDrugPriceInfo().getRegularPricing().getWAvg() > discountPricing) {
                d = price.getDrugPriceInfo().getRegularPricing().getWAvg();
                break;
            }
        }
        if (d <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || max <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d == max) {
            return "";
        }
        return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(d)}) + " - $" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(max)});
    }

    public static Price getPriceFromMarker(double d, double d2, List<Price> list) {
        Price price = null;
        for (Price next : list) {
            if (next.getPharmacy().getLatitude().doubleValue() == d && next.getPharmacy().getLongitude().doubleValue() == d2) {
                price = next;
            }
        }
        return price;
    }

    public static String getPackageDescription(Drug drug) {
        if (!drug.isAutoPackageSelect()) {
            return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + drug.getPackageDescription().toLowerCase();
        } else if (!drug.getPackageUnit().equals("ML")) {
            return "";
        } else {
            return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + drug.getPackageUnit().toLowerCase();
        }
    }

    public static String stripPackageDesc(String str) {
        return str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[0];
    }

    public static String getTodayHours(Pharmacy pharmacy, Context context) {
        Day day;
        int i = Calendar.getInstance().get(7);
        if (pharmacy.getPharmacyHours() != null && pharmacy.getPharmacyHours().length > 0) {
            switch (i) {
                case 1:
                    day = pharmacy.getPharmacyHours()[0].getSunday();
                    break;
                case 2:
                    day = pharmacy.getPharmacyHours()[0].getMonday();
                    break;
                case 3:
                    day = pharmacy.getPharmacyHours()[0].getTuesday();
                    break;
                case 4:
                    day = pharmacy.getPharmacyHours()[0].getWednesday();
                    break;
                case 5:
                    day = pharmacy.getPharmacyHours()[0].getThursday();
                    break;
                case 6:
                    day = pharmacy.getPharmacyHours()[0].getFriday();
                    break;
                case 7:
                    day = pharmacy.getPharmacyHours()[0].getSaturday();
                    break;
                default:
                    day = null;
                    break;
            }
            if (day != null) {
                if (!day.getStartTime().equals("00") || !day.getEndTime().equals("00")) {
                    return (((context.getString(R.string.hours_today) + ": ") + parsePharmacyTime(day.getStartTime())) + " - ") + parsePharmacyTime(day.getEndTime());
                }
                return day.getName() + " - " + context.getString(R.string.closed);
            }
        }
        return null;
    }

    public static String parsePharmacyTime(String str) {
        boolean z = false;
        String substring = str.substring(0, 2);
        String substring2 = str.substring(2, str.length());
        int parseInt = Integer.parseInt(substring);
        if (parseInt > 11) {
            z = true;
        }
        int i = 12;
        if (parseInt > 12) {
            parseInt -= 12;
        }
        if (parseInt != 0) {
            i = parseInt;
        }
        String str2 = "" + i + ":" + substring2;
        if (z) {
            return str2 + "PM";
        }
        return str2 + "AM";
    }

    public static void saveUserLocation(Context context, Address address) {
        String str;
        String str2;
        String str3;
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String str4 = "";
        if (address != null) {
            if (getCityFromAddress(address) != null) {
                str4 = str4 + getCityFromAddress(address) + ", " + getStateCode(address.getAdminArea());
            } else {
                str4 = str4 + getStateCode(address.getAdminArea());
            }
            str3 = String.valueOf(address.getLatitude());
            str2 = String.valueOf(address.getLongitude());
            str = address.getPostalCode();
        } else {
            str = str4;
            str3 = str;
            str2 = str3;
        }
        edit.putString(context.getString(R.string.pref_saved_location_string), str4);
        edit.putString(context.getString(R.string.pref_saved_location_latitude), str3);
        edit.putString(context.getString(R.string.pref_saved_location_longitude), str2);
        edit.putString(context.getString(R.string.pref_saved_location_zip), str);
        edit.putString(context.getString(R.string.pref_saved_location_locale), Constants.LANGUAGE_LOCALE);
        edit.apply();
    }

    public static RxLocation getSavedLocation(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        RxLocation rxLocation = new RxLocation();
        rxLocation.setCityState(defaultSharedPreferences.getString(context.getString(R.string.pref_saved_location_string), ""));
        rxLocation.setLatitude(Double.valueOf(defaultSharedPreferences.getString(context.getString(R.string.pref_saved_location_latitude), AppEventsConstants.EVENT_PARAM_VALUE_NO)).doubleValue());
        rxLocation.setLongitude(Double.valueOf(defaultSharedPreferences.getString(context.getString(R.string.pref_saved_location_longitude), AppEventsConstants.EVENT_PARAM_VALUE_NO)).doubleValue());
        rxLocation.setZip(defaultSharedPreferences.getString(context.getString(R.string.pref_saved_location_zip), ""));
        return rxLocation;
    }

    public static String getSavedLocationLocale(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_saved_location_locale), "");
    }

    public static void clearUserLocation(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_saved_location_string), (String) null);
        edit.putString(context.getString(R.string.pref_saved_location_latitude), (String) null);
        edit.putString(context.getString(R.string.pref_saved_location_longitude), (String) null);
        edit.putString(context.getString(R.string.pref_saved_location_zip), (String) null);
        edit.apply();
    }

    public static String getStateCode(String str) {
        String str2;
        if (Constants.LANGUAGE_LOCALE.contains("es")) {
            str2 = Constants.STATE_MAP_ES.get(str.toLowerCase());
        } else {
            str2 = Constants.STATE_MAP.get(str.toLowerCase());
        }
        return str2 == null ? str : str2;
    }

    public static String getCityFromAddress(Address address) {
        if (address == null) {
            return "";
        }
        if (address.getLocality() != null) {
            return "" + address.getLocality();
        } else if (address.getSubLocality() != null) {
            return "" + address.getSubLocality();
        } else if (address.getAdminArea() == null || !Constants.STATE_MAP.containsKey(address.getAdminArea().toLowerCase())) {
            return "";
        } else {
            return address.getAdminArea();
        }
    }

    public static boolean checkInternet(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 0;
        }
        return false;
    }

    public static LatLngBounds getLatLngBound(ArrayList<Price> arrayList) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<Price> it = arrayList.iterator();
        while (it.hasNext()) {
            Price next = it.next();
            builder.include(new LatLng(next.getPharmacy().getLatitude().doubleValue(), next.getPharmacy().getLongitude().doubleValue()));
            Iterator<com.webmd.webmdrx.models.Address> it2 = next.getPharmacy().getAddresses().iterator();
            while (it2.hasNext()) {
                com.webmd.webmdrx.models.Address next2 = it2.next();
                builder.include(new LatLng(next2.getLatitude().doubleValue(), next2.getLongitude().doubleValue()));
            }
        }
        return builder.build();
    }

    public static boolean isGPSOn(Context context, ContentResolver contentResolver) {
        if (Build.VERSION.SDK_INT < 19) {
            return ((LocationManager) context.getSystemService("location")).isProviderEnabled("gps");
        }
        int i = -1;
        try {
            i = Settings.Secure.getInt(contentResolver, "location_mode");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return i > 0;
    }

    public static float convertDpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0028 A[EDGE_INSN: B:35:0x0028->B:8:0x0028 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.String> orderPackages(java.util.List<java.lang.String> r4) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r1 = r4.iterator()
        L_0x0009:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0028
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "com.android.mms"
            boolean r3 = r2.contains(r3)
            if (r3 != 0) goto L_0x0025
            java.lang.String r3 = "com.google.android.apps.messaging"
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L_0x0009
        L_0x0025:
            r0.add(r2)
        L_0x0028:
            java.util.Iterator r1 = r4.iterator()
        L_0x002c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0043
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "com.google.android.gm"
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L_0x002c
            r0.add(r2)
        L_0x0043:
            java.util.Iterator r1 = r4.iterator()
        L_0x0047:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x005e
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "com.facebook.katana"
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L_0x0047
            r0.add(r2)
        L_0x005e:
            java.util.Iterator r1 = r4.iterator()
        L_0x0062:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0079
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "com.twitter.android"
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L_0x0062
            r0.add(r2)
        L_0x0079:
            java.util.Iterator r4 = r4.iterator()
        L_0x007d:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0093
            java.lang.Object r1 = r4.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r0.contains(r1)
            if (r2 != 0) goto L_0x007d
            r0.add(r1)
            goto L_0x007d
        L_0x0093:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.util.Util.orderPackages(java.util.List):java.util.ArrayList");
    }

    public static ResolveInfo getResInfoForPackage(List<ResolveInfo> list, String str) {
        for (ResolveInfo next : list) {
            if (next.activityInfo.packageName.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static List<Price> groupLocalPharmacies(List<Price> list, boolean z) {
        Comparator<Price> comparator;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Price next : list) {
            String pharmacyGroup = next.getPharmacy().getPharmacyGroup();
            if (pharmacyGroup == null || !pharmacyGroup.equals("OTHER")) {
                arrayList.add(next);
            } else {
                arrayList2.add(next);
            }
        }
        if (arrayList2.size() > 0) {
            if (z) {
                comparator = getPriceComparatorByPrice();
            } else {
                comparator = getPriceComparatorByDistance();
            }
            Collections.sort(arrayList2, comparator);
            arrayList.add(arrayList2.get(0));
        }
        return arrayList;
    }

    public static Comparator<Price> getPriceComparatorForPricingActivity() {
        return new Comparator<Price>() {
            public int compare(Price price, Price price2) {
                if (price.getPharmacy().getPharmacyGroup().equals("OTHER")) {
                    return 1;
                }
                if (price2.getPharmacy().getPharmacyGroup().equals("OTHER")) {
                    return -1;
                }
                if (price.getDrugPriceInfo().getDiscountPricing() == price2.getDrugPriceInfo().getDiscountPricing()) {
                    if (price.getPharmacy().getDistance() - price2.getPharmacy().getDistance() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        return 1;
                    }
                    return -1;
                } else if (price.getDrugPriceInfo().getDiscountPricing() - price2.getDrugPriceInfo().getDiscountPricing() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }

    public static Comparator<Price> getDistanceComparatorForPricingActivity() {
        return new Comparator<Price>() {
            public int compare(Price price, Price price2) {
                if (price.getPharmacy().getPharmacyGroup().equals("OTHER")) {
                    return 1;
                }
                if (price2.getPharmacy().getPharmacyGroup().equals("OTHER")) {
                    return -1;
                }
                if (price.getPharmacy().getDistance() == price2.getPharmacy().getDistance()) {
                    if (price.getDrugPriceInfo().getDiscountPricing() - price2.getDrugPriceInfo().getDiscountPricing() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        return -1;
                    }
                    return 1;
                } else if (price.getPharmacy().getDistance() - price2.getPharmacy().getDistance() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }

    public static Comparator<Price> getPriceComparatorByPrice() {
        return new Comparator<Price>() {
            public int compare(Price price, Price price2) {
                if (price.getDrugPriceInfo().getDiscountPricing() == price2.getDrugPriceInfo().getDiscountPricing()) {
                    if (price.getPharmacy().getDistance() - price2.getPharmacy().getDistance() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        return 1;
                    }
                    return -1;
                } else if (price.getDrugPriceInfo().getDiscountPricing() - price2.getDrugPriceInfo().getDiscountPricing() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }

    public static Comparator<Price> getPriceComparatorByDistance() {
        return new Comparator<Price>() {
            public int compare(Price price, Price price2) {
                if (price.getPharmacy().getDistance() == price2.getPharmacy().getDistance()) {
                    if (price.getDrugPriceInfo().getDiscountPricing() - price2.getDrugPriceInfo().getDiscountPricing() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        return -1;
                    }
                    return 1;
                } else if (price.getPharmacy().getDistance() - price2.getPharmacy().getDistance() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }

    public static void saveBackgroundState(Context context, boolean z) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(context.getString(R.string.shared_pref_save_background_state), 0).edit();
            edit.putBoolean(context.getString(R.string.webmd_rx_background_state), z);
            edit.commit();
        }
    }

    public static boolean getBackgroundState(Context context) {
        if (context != null) {
            return context.getSharedPreferences(context.getString(R.string.shared_pref_save_background_state), 0).getBoolean(context.getString(R.string.webmd_rx_background_state), false);
        }
        return false;
    }

    public static int generateID(String str, String str2, String str3, int i) {
        try {
            if (str3.isEmpty()) {
                str3 = AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
            if (str.isEmpty()) {
                str = AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
            if (str2.isEmpty()) {
                str2 = AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
            String replaceAll = str.replaceAll("[^\\d.]", "");
            String replaceAll2 = str2.replaceAll("[^\\d.]", "");
            String replaceAll3 = str3.replaceAll("[^\\d.]", "");
            int i2 = 1;
            int parseInt = !replaceAll3.isEmpty() ? Integer.parseInt(replaceAll3) : 1;
            int parseInt2 = !replaceAll.isEmpty() ? Integer.parseInt(replaceAll) : 1;
            if (!replaceAll2.isEmpty()) {
                i2 = Integer.parseInt(replaceAll2);
            }
            return parseInt2 + (parseInt * i * i2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long timestampToMillis(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long convertRepeatIntervalToMillis(String str, int i) {
        long j;
        long j2;
        if (str.equalsIgnoreCase("weeks")) {
            j = (long) i;
            j2 = 604800000;
        } else if (str.equalsIgnoreCase("months")) {
            j = (long) i;
            j2 = 18144000000L;
        } else {
            j = (long) i;
            j2 = Constants.DAY_IN_MILLIS;
        }
        return j * j2;
    }

    public static String reminderDateToString(Calendar calendar, boolean z) {
        String str;
        String str2;
        int i = calendar.get(2);
        int i2 = calendar.get(5);
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        int i5 = calendar.get(1);
        String str3 = (i < 0 || i > 11) ? "" : new DateFormatSymbols().getMonths()[i];
        String str4 = i3 < 12 ? "AM" : "PM";
        if (String.valueOf(i4).length() < 2) {
            str = AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(i4);
        } else {
            str = String.valueOf(i4);
        }
        int i6 = i3 % 12;
        if (i6 == 0) {
            str2 = UserProfile.NURSE_ID;
        } else {
            str2 = String.valueOf(i6);
        }
        String valueOf = String.valueOf(i2);
        if (z) {
            return str2 + ":" + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str4;
        }
        return str3 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + valueOf + ", " + String.valueOf(i5);
    }

    public static void setFirebaseCurrentScreen(Activity activity, String str) {
        FirebaseAnalytics instance = FirebaseAnalytics.getInstance(activity.getApplicationContext());
        String[] split = activity.getLocalClassName().split("\\.");
        instance.setCurrentScreen(activity, str, split[split.length - 1]);
    }

    public static void logFirebaseEvent(Context context, String str) {
        FirebaseAnalytics.getInstance(context).logEvent(str, (Bundle) null);
    }

    public static boolean isValidEmail(CharSequence charSequence) {
        return StringUtil.isNotEmpty((String) charSequence) && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public static boolean isValidUSMobile(String str) {
        return str.length() == 10 && Patterns.PHONE.matcher(str).matches();
    }
}
