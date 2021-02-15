package com.wbmd.qxcalculator.util.legacy;

import android.content.Context;
import android.os.Bundle;
import com.wbmd.qxcalculator.managers.CompletionHandler;
import com.wbmd.qxcalculator.managers.TaskManager;
import com.wbmd.qxcalculator.model.api.APIResponse;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.managers.DatabaseManager;
import com.wbmd.qxcalculator.model.parsedObjects.Location;
import com.wbmd.qxcalculator.model.parsedObjects.Profession;
import com.wbmd.qxcalculator.model.parsedObjects.Specialty;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LegacyUpdateHelper {
    private static LegacyUpdateHelper mInstance;
    private final String SAVED_VAULT_DEFAULT_UNITS = "DefaultUnits";
    private final String SAVED_VAULT_EMAIL = "SAVED_VAULT_EMAIL";
    private final String SAVED_VAULT_FIRST_NAME = "SAVED_VAULT_FIRST_NAME";
    private final String SAVED_VAULT_LAST_NAME = "SAVED_VAULT_LAST_NAME";
    private final String SAVED_VAULT_LIST_VERSION = "SAVED_VAULT_LIST_VERSION";
    private final String SAVED_VAULT_LOCATION = "SAVED_VAULT_LOCATION";
    private final String SAVED_VAULT_NAME = "CardioCalcBeta4";
    private final String SAVED_VAULT_PROFESSION = "SAVED_VAULT_PROFESSION";
    private final String SAVED_VAULT_SPECIALTY = "SAVED_VAULT_SPECIALTY";
    private final String SAVED_VAULT_USAGE_COUNT = "SAVED_VAULT_USAGE_COUNT";
    private final String SAVED_VAULT_ZIP = "SAVED_VAULT_ZIP";
    private Context context;
    public boolean isConverting;
    public CompletionHandler onConversionCompletionHandler;
    private APIResponse registrationDataApiResponse;
    private TaskManager taskManager = new TaskManager();

    public static LegacyUpdateHelper getInstance() {
        if (mInstance == null) {
            mInstance = new LegacyUpdateHelper();
        }
        return mInstance;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    private LegacyUpdateHelper() {
    }

    public boolean doesLegacyVersionExist() {
        return !this.context.getSharedPreferences("CardioCalcBeta4", 0).getAll().isEmpty();
    }

    public void convertUser() {
        if (!this.isConverting) {
            this.isConverting = true;
            new Bundle();
        }
    }

    public String getFirstName() {
        return this.context.getSharedPreferences("CardioCalcBeta4", 0).getString("SAVED_VAULT_FIRST_NAME", (String) null);
    }

    public String getLastName() {
        return this.context.getSharedPreferences("CardioCalcBeta4", 0).getString("SAVED_VAULT_LAST_NAME", (String) null);
    }

    public Profession getProfession() {
        String string = this.context.getSharedPreferences("CardioCalcBeta4", 0).getString("SAVED_VAULT_PROFESSION", "NA");
        if (string.equalsIgnoreCase("EMT/Paramedic")) {
            string = "EMT, Paramedic";
        } else if (string.equalsIgnoreCase("Student")) {
            string = "Medical Student";
        }
        Profession profession = null;
        Iterator<Profession> it = this.registrationDataApiResponse.professions.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Profession next = it.next();
            if (next.name.equalsIgnoreCase(string)) {
                profession = next;
                break;
            }
        }
        if (profession != null) {
            return profession;
        }
        Profession profession2 = new Profession();
        profession2.identifier = 21L;
        profession2.name = "Not Indicated";
        return profession2;
    }

    public Specialty getSpecialty() {
        Specialty specialty;
        String string = this.context.getSharedPreferences("CardioCalcBeta4", 0).getString("SAVED_VAULT_SPECIALTY", "NA");
        if (string.equalsIgnoreCase("Anesthesiology/Anaesthesiology")) {
            string = "Anesthesiology";
        } else if (string.equalsIgnoreCase("Critical Care/Intensive Care")) {
            string = "Critical Care, Intensive Care";
        } else if (string.equalsIgnoreCase("Endocrinology")) {
            string = "Endocrinology, Metabolism";
        } else if (string.equalsIgnoreCase("Oncology")) {
            string = "Medical Oncology";
        } else if (string.equalsIgnoreCase("Surgery - Other")) {
            string = "Surgery - General";
        } else if (string.equalsIgnoreCase("Other Specialty") || string.equalsIgnoreCase("Not Applicable")) {
            string = "Family Medicine";
        }
        Iterator<Specialty> it = this.registrationDataApiResponse.specialties.iterator();
        while (true) {
            if (!it.hasNext()) {
                specialty = null;
                break;
            }
            specialty = it.next();
            if (specialty.name.equalsIgnoreCase(string)) {
                break;
            }
        }
        if (specialty != null) {
            return specialty;
        }
        Specialty specialty2 = new Specialty();
        specialty2.name = "Family Medicine";
        specialty2.identifier = 24L;
        specialty2.categoryIdentifier = null;
        return specialty2;
    }

    public Location getLocation() {
        Location location;
        String string = this.context.getSharedPreferences("CardioCalcBeta4", 0).getString("SAVED_VAULT_LOCATION", "NA");
        Iterator<Location> it = this.registrationDataApiResponse.locations.iterator();
        while (true) {
            if (!it.hasNext()) {
                location = null;
                break;
            }
            location = it.next();
            if (location.name.equalsIgnoreCase(string)) {
                break;
            }
        }
        if (location != null) {
            return location;
        }
        Location location2 = new Location();
        location2.identifier = 242L;
        location2.name = "Not Indicated";
        return location2;
    }

    public String getZip() {
        return this.context.getSharedPreferences("CardioCalcBeta4", 0).getString("SAVED_VAULT_ZIP", "");
    }

    public String getEmail() {
        return this.context.getSharedPreferences("CardioCalcBeta4", 0).getString("SAVED_VAULT_EMAIL", "NA@qxmd.com");
    }

    public long getUsageCount() {
        return (long) this.context.getSharedPreferences("CardioCalcBeta4", 0).getInt("SAVED_VAULT_USAGE_COUNT", 1);
    }

    public Unit.UnitType getDefaultUnits() {
        if (this.context.getSharedPreferences("CardioCalcBeta4", 0).getInt("DefaultUnits", 1) == 0) {
            return Unit.UnitType.SI_UNITS;
        }
        return Unit.UnitType.US_UNITS;
    }

    private int getSavedVaultListVersion() {
        return this.context.getSharedPreferences("CardioCalcBeta4", 0).getInt("SAVED_VAULT_LIST_VERSION", 1);
    }

    public void convertRecentAndFavLists(List<DBContentItem> list) {
        List<ContentNode> list2;
        List<ContentNode> list3;
        Iterator<ContentNode> it;
        String title;
        String str;
        String title2;
        File file = new File(this.context.getFilesDir(), "favoriteContentList.xml");
        File file2 = new File(this.context.getFilesDir(), "recentContentList.xml");
        try {
            list2 = ContentParser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            list2 = null;
        }
        ArrayList arrayList = new ArrayList();
        String str2 = "PaO₂:FiO₂ Ratio";
        String str3 = "Nasal Cannula FiO₂ Estimation";
        if (list2 != null) {
            Iterator<ContentNode> it2 = list2.iterator();
            while (it2.hasNext()) {
                Iterator<ContentNode> it3 = it2;
                ContentNode next = it2.next();
                String str4 = str2;
                if ((next instanceof ContentItem) && (title2 = ((ContentItem) next).getTitle()) != null) {
                    if (title2.equalsIgnoreCase("CMML Prognosis Scoring System")) {
                        title2 = "CMML Prognostic Scoring Systems (from Spain and Dusseldorf)";
                    } else if (title2.equalsIgnoreCase("FINDRISC Diabetes Risk")) {
                        title2 = "FINDRISC Diabetes Risk Calculator";
                    } else if (title2.equalsIgnoreCase("In-flight PaO2 (ABG & PFT)")) {
                        title2 = "In-flight PaO₂ (ABG & PFT)";
                    } else if (title2.equalsIgnoreCase("In-flight PaO2 (based on ABG)")) {
                        title2 = "In-flight PaO₂ (based on ABG)";
                    } else if (title2.equalsIgnoreCase("Myelofibrosis Scoring System (DIPPS)")) {
                        title2 = "Myelofibrosis Scoring System";
                    } else if (title2.equalsIgnoreCase("Nasal Cannula FiO2 Estimation")) {
                        title2 = str3;
                    } else if (title2.equalsIgnoreCase("PaO2:FiO2 Ratio")) {
                        title2 = str4;
                    }
                    Iterator<DBContentItem> it4 = list.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            break;
                        }
                        Iterator<DBContentItem> it5 = it4;
                        DBContentItem next2 = it4.next();
                        str = str3;
                        if (next2.getName().equalsIgnoreCase(title2)) {
                            next2.setIsFavorite(true);
                            arrayList.add(next2);
                            break;
                        }
                        str3 = str;
                        it4 = it5;
                    }
                }
                str = str3;
                str2 = str4;
                it2 = it3;
                str3 = str;
            }
        }
        String str5 = str2;
        String str6 = str3;
        try {
            list3 = ContentParser.parse(file2);
        } catch (Exception e2) {
            e2.printStackTrace();
            list3 = null;
        }
        if (list3 != null) {
            Iterator<ContentNode> it6 = list3.iterator();
            while (it6.hasNext()) {
                ContentNode next3 = it6.next();
                if ((next3 instanceof ContentItem) && (title = ((ContentItem) next3).getTitle()) != null) {
                    if (title.equalsIgnoreCase("CMML Prognosis Scoring System")) {
                        title = "CMML Prognostic Scoring Systems (from Spain and Dusseldorf)";
                    } else if (title.equalsIgnoreCase("FINDRISC Diabetes Risk")) {
                        title = "FINDRISC Diabetes Risk Calculator";
                    } else if (title.equalsIgnoreCase("In-flight PaO2 (ABG & PFT)")) {
                        title = "In-flight PaO₂ (ABG & PFT)";
                    } else if (title.equalsIgnoreCase("In-flight PaO2 (based on ABG)")) {
                        title = "In-flight PaO₂ (based on ABG)";
                    } else if (title.equalsIgnoreCase("Myelofibrosis Scoring System (DIPPS)")) {
                        title = "Myelofibrosis Scoring System";
                    } else if (title.equalsIgnoreCase("Nasal Cannula FiO2 Estimation")) {
                        title = str6;
                    } else if (title.equalsIgnoreCase("PaO2:FiO2 Ratio")) {
                        title = str5;
                    }
                    Iterator<DBContentItem> it7 = list.iterator();
                    while (true) {
                        if (!it7.hasNext()) {
                            break;
                        }
                        DBContentItem next4 = it7.next();
                        it = it6;
                        if (next4.getName().equalsIgnoreCase(title)) {
                            next4.setLastUsedDate(1L);
                            arrayList.add(next4);
                            break;
                        }
                        it6 = it;
                    }
                }
                it = it6;
                it6 = it;
            }
        }
        if (!arrayList.isEmpty()) {
            DatabaseManager.getInstance().getDaoSession().getDBContentItemDao().updateInTx(arrayList);
        }
    }
}
