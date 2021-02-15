package com.wbmd.qxcalculator.model.api.parser;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.medscape.android.updater.UpdateManager;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.model.api.APIResponse;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.common.Favorite;
import com.wbmd.qxcalculator.model.contentItems.common.Recent;
import com.wbmd.qxcalculator.model.parsedObjects.Error;
import com.wbmd.qxcalculator.model.parsedObjects.Location;
import com.wbmd.qxcalculator.model.parsedObjects.Profession;
import com.wbmd.qxcalculator.model.parsedObjects.Specialty;
import com.wbmd.qxcalculator.model.parsedObjects.User;
import com.wbmd.qxcalculator.util.CrashLogger;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class APIParser {
    private static final String TAG = APIParser.class.getSimpleName();

    public static void parseApiResponse(InputStream inputStream, APIResponse aPIResponse) throws IOException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            readJSON(jsonReader, aPIResponse, false);
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "exception " + e);
        } catch (Throwable th) {
            jsonReader.close();
            throw th;
        }
        jsonReader.close();
    }

    public static boolean parseApiResponse(Reader reader, APIResponse aPIResponse, boolean z) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        try {
            readJSON(jsonReader, aPIResponse, z);
            jsonReader.close();
            return true;
        } catch (Exception e) {
            CrashLogger.getInstance().logHandledException(e);
            String str = TAG;
            Log.d(str, "exception " + e);
            jsonReader.close();
            return false;
        } catch (Throwable th) {
            jsonReader.close();
            throw th;
        }
    }

    public static void parseApiResponseProfessions(Reader reader, APIResponse aPIResponse) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        aPIResponse.professions = new ArrayList<>();
        try {
            aPIResponse.professions.addAll(Profession.convertJsonToProfessions(jsonReader));
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "exception " + e);
        } catch (Throwable th) {
            jsonReader.close();
            throw th;
        }
        jsonReader.close();
    }

    public static void parseApiResponseSpecialties(Reader reader, APIResponse aPIResponse) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        aPIResponse.specialties = new ArrayList<>();
        try {
            aPIResponse.specialties.addAll(Specialty.convertJsonToSpecialties(jsonReader));
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "exception " + e);
        } catch (Throwable th) {
            jsonReader.close();
            throw th;
        }
        jsonReader.close();
    }

    public static void parseApiResponseLocations(Reader reader, APIResponse aPIResponse) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        aPIResponse.locations = new ArrayList<>();
        try {
            aPIResponse.locations.addAll(Location.convertJsonToLocations(jsonReader));
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "exception " + e);
        } catch (Throwable th) {
            jsonReader.close();
            throw th;
        }
        jsonReader.close();
    }

    private static void readJSON(JsonReader jsonReader, APIResponse aPIResponse, boolean z) throws IOException, ParseException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (jsonReader.peek() == JsonToken.NULL && z) {
                jsonReader.skipValue();
            } else if (nextName.equalsIgnoreCase("calculators")) {
                aPIResponse.contentItems.addAll(ContentItem.convertJsonToContentItems(jsonReader, ContentItem.CONTENT_ITEM_TYPE_CALCULATOR));
            } else if (nextName.equalsIgnoreCase("definitions")) {
                aPIResponse.contentItems.addAll(ContentItem.convertJsonToContentItems(jsonReader, ContentItem.CONTENT_ITEM_TYPE_DEFINITION));
            } else if (nextName.equalsIgnoreCase("file_sources")) {
                aPIResponse.contentItems.addAll(ContentItem.convertJsonToContentItems(jsonReader, ContentItem.CONTENT_ITEM_TYPE_FILE_SOURCE));
            } else if (nextName.equalsIgnoreCase("reference_books")) {
                aPIResponse.contentItems.addAll(ContentItem.convertJsonToContentItems(jsonReader, ContentItem.CONTENT_ITEM_TYPE_REFERENCE_BOOK));
            } else if (nextName.equalsIgnoreCase("common_files")) {
                aPIResponse.contentItems.addAll(ContentItem.convertJsonToContentItems(jsonReader, ContentItem.CONTENT_ITEM_TYPE_COMMON_FILE));
            } else if (nextName.equalsIgnoreCase("splash_pages")) {
                aPIResponse.contentItems.addAll(ContentItem.convertJsonToContentItems(jsonReader, ContentItem.CONTENT_ITEM_TYPE_SPLASH_PAGE));
            } else if (nextName.equalsIgnoreCase("favorites")) {
                aPIResponse.favorites.addAll(Favorite.convertJsonToFavorites(jsonReader));
            } else if (nextName.equalsIgnoreCase("recents")) {
                aPIResponse.recents.addAll(Recent.convertJsonToRecents(jsonReader));
            } else if (nextName.equalsIgnoreCase(JSONAPISpecConstants.ERRORS)) {
                List<Error> convertJsonToErrors = Error.convertJsonToErrors(jsonReader);
                aPIResponse.errors = new ArrayList<>(convertJsonToErrors.size());
                for (Error next : convertJsonToErrors) {
                    aPIResponse.errors.add(new QxError(QxError.ErrorType.API, next.identifier.intValue(), Integer.parseInt(next.status), next.title, next.detail));
                }
            } else if (nextName.equalsIgnoreCase("error")) {
                Error convertJsonToError = Error.convertJsonToError(jsonReader);
                aPIResponse.errors = new ArrayList<>(1);
                aPIResponse.errors.add(new QxError(QxError.ErrorType.API, convertJsonToError.identifier.intValue(), Integer.parseInt(convertJsonToError.status), convertJsonToError.title, convertJsonToError.detail));
            } else if (nextName.equalsIgnoreCase("professions")) {
                aPIResponse.professions.addAll(Profession.convertJsonToProfessions(jsonReader));
            } else if (nextName.equalsIgnoreCase("specialties")) {
                aPIResponse.specialties.addAll(Specialty.convertJsonToSpecialties(jsonReader));
            } else if (nextName.equalsIgnoreCase("locations")) {
                aPIResponse.locations.addAll(Location.convertJsonToLocations(jsonReader));
            } else if (nextName.equalsIgnoreCase("user_data")) {
                aPIResponse.user = User.convertJsonToUser(jsonReader);
            } else if (nextName.equalsIgnoreCase("account_exists")) {
                aPIResponse.accountExists = readBoolean(jsonReader).booleanValue();
            } else if (nextName.equalsIgnoreCase(UpdateManager.SETTINGS_PREFS)) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    if (jsonReader.nextName().equalsIgnoreCase("banner_ads_enabled")) {
                        aPIResponse.bannerAdsEnabled = readBoolean(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }

    public static Integer readInteger(JsonReader jsonReader) throws IOException {
        try {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Integer.valueOf(jsonReader.nextInt());
            }
            jsonReader.nextNull();
            return null;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.toString());
            jsonReader.skipValue();
            return null;
        }
    }

    public static Boolean readBoolean(JsonReader jsonReader) throws IOException {
        Boolean bool;
        try {
            JsonToken peek = jsonReader.peek();
            if (peek != JsonToken.NULL) {
                if (peek.equals(JsonToken.NUMBER)) {
                    bool = Boolean.valueOf(jsonReader.nextInt() != 0);
                } else {
                    bool = Boolean.valueOf(jsonReader.nextBoolean());
                }
                return bool;
            }
            jsonReader.nextNull();
            return null;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.toString());
            jsonReader.skipValue();
            return null;
        }
    }

    public static Double readDouble(JsonReader jsonReader) throws IOException {
        try {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.toString());
            jsonReader.skipValue();
            return null;
        }
    }

    public static String readString(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return jsonReader.nextString();
        }
        jsonReader.nextNull();
        return null;
    }

    public static String readStringAndConvertNewLines(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return jsonReader.nextString().replace("\\n", "<br />");
        }
        jsonReader.nextNull();
        return null;
    }

    public static Long readLong(JsonReader jsonReader) throws IOException {
        try {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Long.valueOf(jsonReader.nextLong());
            }
            jsonReader.nextNull();
            return null;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.toString());
            jsonReader.skipValue();
            return null;
        }
    }

    public static Long readUnixTimestampAndConvertToMs(JsonReader jsonReader) throws IOException {
        try {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Long.valueOf(jsonReader.nextLong() * 1000);
            }
            jsonReader.nextNull();
            return null;
        } catch (NumberFormatException e) {
            Log.e(TAG, e.toString());
            jsonReader.skipValue();
            return null;
        }
    }

    public static Date readDate(JsonReader jsonReader) throws IOException, ParseException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return new Date(jsonReader.nextLong());
        }
        jsonReader.nextNull();
        return new Date(0);
    }
}
