package com.medscape.android.drugs.helper;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.activity.calc.model.CalcsContract;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.DrugDataHelper;
import com.medscape.android.drugs.model.DrugsContract;
import com.medscape.android.util.LogUtil;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBTag;
import com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import com.wbmd.wbmdcommons.utils.Extensions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SearchHelper {
    private static final int SOURCE_CALC = 2;
    public static final int SOURCE_CALC_FOLDER = 0;
    public static final int SOURCE_DRUG = 1;
    private static final int SOURCE_DRUG_CLASS_TOP_LEVEL = 3;
    public static final int SOURCE_DRUG_SUBCLASS = 4;
    public static final String TYPE_CALCULATOR = "C";
    public static final String TYPE_CALCULATOR_FOLDER = "CF";
    public static final String TYPE_CLINICAL_REFERENCE = "T";
    public static final int TYPE_CONDITIONS = 0;
    public static final String TYPE_DRUG = "D";
    public static final String TYPE_DRUG_CLASS = "DC";
    public static final String TYPE_PRICING = "RX";
    public static final int TYPE_PROCEDURE = 1;

    public List<CRData> wordAllMatching(String str, int i, int i2, Context context) {
        int i3 = i;
        int i4 = i2;
        List<CRData> arrayList = new ArrayList<>();
        String[] split = !Extensions.IsNullOrEmpty(str) ? str.trim().replaceAll("[^a-zA-Z0-9]", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) : null;
        ArrayList arrayList2 = new ArrayList();
        int i5 = 0;
        if (split != null) {
            for (String str2 : split) {
                if (str2.length() > 0) {
                    arrayList2.add(str2);
                }
            }
        }
        if (arrayList2.size() > 0) {
            StringBuilder sb = new StringBuilder("SELECT ContentID, DrugName, ContentType, Type, Source FROM(");
            StringBuilder sb2 = new StringBuilder("SELECT ArticleID, Title, ContentType, Type, Source FROM(");
            StringBuilder sb3 = new StringBuilder("SELECT ClassID, ClassName, ContentType, Type, Source FROM (");
            Iterator it = arrayList2.iterator();
            int i6 = 0;
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (i6 > 0) {
                    sb.append(" INTERSECT ");
                    sb2.append(" INTERSECT ");
                    sb3.append(" INTERSECT ");
                }
                String upperCase = str3.substring(i5, 1).toUpperCase();
                sb.append("SELECT ContentID, DrugName, 'D' AS ContentType, 0 as Type, Source FROM tblDrugs, tblWords");
                sb.append(upperCase);
                sb.append(" WHERE IndividualWord LIKE '");
                sb.append(str3);
                sb.append("%' AND Source=1 AND CAID = UniqueID AND ContentID != 0");
                sb2.append("SELECT ArticleID, Title, 'T' AS ContentType, Type, Source FROM tblClinicalReferenceTitles, tblWords");
                sb2.append(upperCase);
                sb2.append(" WHERE IndividualWord LIKE '");
                sb2.append(str3);
                sb2.append("%' AND Source=0 AND CAID = UniqueID");
                sb3.append("SELECT DISTINCT(ClassID), ClassName, '");
                sb3.append(TYPE_DRUG_CLASS);
                sb3.append("' AS ContentType, 0 AS Type, Source FROM tblClassNames, tblWords");
                sb3.append(upperCase);
                sb3.append(" WHERE IndividualWord LIKE '");
                sb3.append(str3);
                sb3.append("%' AND ((Source = ");
                sb3.append(3);
                sb3.append(" AND ParentID IS NULL AND SingleLevel IS NULL) OR (Source = ");
                sb3.append(4);
                sb3.append(" AND (ParentID is NOT NULL OR SingleLevel is NOT NULL))) AND CAID = ClassID");
                sb3.append(" AND ClassName LIKE '%' || IndividualWord || '%'");
                i6++;
                i5 = 0;
            }
            String str4 = sb + ") UNION " + sb2 + ") UNION " + sb3 + ") ORDER BY 2 COLLATE NOCASE";
            try {
                LogUtil.d("Search", "all Query = %s", str4);
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                Cursor rawQuery = databaseHelper.getDatabase().rawQuery(str4, (String[]) null);
                while (rawQuery.moveToNext()) {
                    CRData cRData = new CRData();
                    cRData.setTitle(rawQuery.getString(1));
                    cRData.setType(rawQuery.getString(2));
                    cRData.setCrType(rawQuery.getInt(3));
                    if (cRData.getType().equalsIgnoreCase("c")) {
                        cRData.setCalcId(rawQuery.getString(0));
                    } else {
                        cRData.setCid(rawQuery.getInt(0));
                    }
                    if (TYPE_DRUG_CLASS.equals(cRData.getType())) {
                        cRData.setSource(rawQuery.getInt(4));
                    }
                    arrayList.add(cRData);
                }
                rawQuery.close();
                databaseHelper.close();
            } catch (Throwable th) {
                LogUtil.e("SearchHelper", "message = %s ", th.getMessage());
            }
            arrayList.addAll(getAllQxCalcsForSearchTerm(str, new ArrayList(RowItemBuilder.getInstance().getAllCalcRowItems())));
            List arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            String str5 = (String) arrayList2.get(0);
            for (CRData cRData2 : arrayList) {
                if (cRData2 != null && cRData2.getTitle() != null && !cRData2.getTitle().isEmpty() && cRData2.getTitle().length() >= str5.length()) {
                    if (cRData2.getTitle().substring(0, str5.length()).equalsIgnoreCase(str5)) {
                        arrayList3.add(cRData2);
                    } else {
                        arrayList4.add(cRData2);
                    }
                }
            }
            arrayList3.addAll(arrayList4);
            if (arrayList3.size() >= i4) {
                arrayList3 = arrayList3.subList(i4, arrayList3.size());
            }
            arrayList = arrayList3;
            if (i3 > 0 && arrayList.size() > i3) {
                arrayList.subList(i3, arrayList.size()).clear();
            }
        }
        return arrayList;
    }

    public static List<CRData> wordConditionMatching(String str, int i, int i2, Context context) {
        List<CRData> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] split = str.trim().replaceAll("[^a-zA-Z0-9]+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : split) {
            if (str2.length() > 0) {
                arrayList2.add(str2);
            }
        }
        if (arrayList2.size() > 0) {
            Iterator it = arrayList2.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (i3 > 0) {
                    sb.append(" INTERSECT ");
                }
                String upperCase = str3.substring(0, 1).toUpperCase();
                sb.append("SELECT DISTINCT(ArticleID), Title, 'T' FROM tblClinicalReferenceTitles, tblWords");
                sb.append(upperCase);
                sb.append(" WHERE IndividualWord LIKE '");
                sb.append(str3);
                sb.append("%' AND Source=0 AND CAID = UniqueID AND Type = 0");
                i3++;
            }
            sb.append(" ORDER BY 2 COLLATE NOCASE");
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                Cursor rawQuery = databaseHelper.getDatabase().rawQuery(sb.toString(), (String[]) null);
                while (rawQuery.moveToNext()) {
                    CRData cRData = new CRData();
                    cRData.setCid(rawQuery.getInt(0));
                    cRData.setTitle(rawQuery.getString(1));
                    cRData.setType(rawQuery.getString(2));
                    arrayList.add(cRData);
                }
                rawQuery.close();
                databaseHelper.close();
            } catch (Exception e) {
                LogUtil.e("SearchHelper", "getMessage = %s", e.getMessage());
            }
            List arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            String str4 = (String) arrayList2.get(0);
            for (CRData cRData2 : arrayList) {
                if (cRData2.getTitle().substring(0, str4.length()).equalsIgnoreCase(str4)) {
                    arrayList3.add(cRData2);
                } else {
                    arrayList4.add(cRData2);
                }
            }
            arrayList3.addAll(arrayList4);
            if (arrayList3.size() >= i2) {
                arrayList3 = arrayList3.subList(i2, arrayList3.size());
            }
            arrayList = arrayList3;
            if (i > 0 && arrayList.size() > i) {
                arrayList.subList(i, arrayList.size()).clear();
            }
        }
        return arrayList;
    }

    public static List<CRData> wordProcedureMatching(String str, int i, int i2, Context context) {
        List<CRData> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] split = str.trim().replaceAll("[^a-zA-Z0-9]+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : split) {
            if (str2.length() > 0) {
                arrayList2.add(str2);
            }
        }
        if (arrayList2.size() > 0) {
            Iterator it = arrayList2.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (i3 > 0) {
                    sb.append(" INTERSECT ");
                }
                String upperCase = str3.substring(0, 1).toUpperCase();
                sb.append("SELECT DISTINCT(ArticleID), Title, 'T' , Type FROM tblClinicalReferenceTitles, tblWords");
                sb.append(upperCase);
                sb.append(" WHERE IndividualWord LIKE '");
                sb.append(str3);
                sb.append("%' AND Source=0 AND CAID = UniqueID AND Type = 1");
                i3++;
            }
            sb.append(" ORDER BY 2 COLLATE NOCASE");
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                Cursor rawQuery = databaseHelper.getDatabase().rawQuery(sb.toString(), (String[]) null);
                while (rawQuery.moveToNext()) {
                    CRData cRData = new CRData();
                    cRData.setCid(rawQuery.getInt(0));
                    cRData.setTitle(rawQuery.getString(1));
                    cRData.setType(rawQuery.getString(2));
                    cRData.setCrType(rawQuery.getInt(3));
                    arrayList.add(cRData);
                }
                rawQuery.close();
                databaseHelper.close();
            } catch (Exception e) {
                LogUtil.e("SearchHelper", "getMessage = %s", e.getMessage());
            }
            List arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            String str4 = (String) arrayList2.get(0);
            for (CRData cRData2 : arrayList) {
                if (cRData2.getTitle().substring(0, str4.length()).equalsIgnoreCase(str4)) {
                    arrayList3.add(cRData2);
                } else {
                    arrayList4.add(cRData2);
                }
            }
            arrayList3.addAll(arrayList4);
            if (arrayList3.size() >= i2) {
                arrayList3 = arrayList3.subList(i2, arrayList3.size());
            }
            arrayList = arrayList3;
            if (i > 0 && arrayList.size() > i) {
                arrayList.subList(i, arrayList.size()).clear();
            }
        }
        return arrayList;
    }

    public static List<CRData> wordCalculatorMatching(String str, int i, int i2, Context context) {
        List<CRData> arrayList = new ArrayList<>();
        String[] split = str.trim().replaceAll("[^a-zA-Z0-9]+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (split.length > 0) {
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                Cursor rawQuery = databaseHelper.getDatabase().rawQuery(buildCalcsSearchSql(str, true, i), (String[]) null);
                HashSet hashSet = new HashSet();
                while (rawQuery.moveToNext()) {
                    if (hashSet.add(rawQuery.getString(2))) {
                        CRData cRData = new CRData();
                        cRData.setCalcId(rawQuery.getString(0));
                        cRData.setTitle(rawQuery.getString(2));
                        cRData.setType(rawQuery.getString(3));
                        arrayList.add(cRData);
                    }
                }
                rawQuery.close();
                databaseHelper.close();
            } catch (Exception e) {
                LogUtil.e("SearchHelper", "getMessage = %s", e.getMessage());
            }
            List arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            String str2 = split[0];
            for (CRData cRData2 : arrayList) {
                if (cRData2.getTitle().substring(0, str2.length()).equalsIgnoreCase(str2)) {
                    arrayList2.add(cRData2);
                } else {
                    arrayList3.add(cRData2);
                }
            }
            arrayList2.addAll(arrayList3);
            if (arrayList2.size() >= i2) {
                arrayList2 = arrayList2.subList(i2, arrayList2.size());
            }
            arrayList = arrayList2;
            if (i > 0 && arrayList.size() > i) {
                arrayList.subList(i, arrayList.size()).clear();
            }
        }
        return arrayList;
    }

    public static List<CRData> wordDrugMatching(String str, int i, int i2, Context context) {
        List<CRData> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] split = str.trim().replaceAll("[^a-zA-Z0-9]+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (String str2 : split) {
            if (str2.length() > 0) {
                arrayList2.add(str2);
            }
        }
        if (arrayList2.size() > 0) {
            sb2.append("SELECT ContentID, DrugName, TYPE, Source FROM (");
            sb3.append("SELECT ClassID, ClassName, TYPE, Source FROM (");
            Iterator it = arrayList2.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (i3 > 0) {
                    sb2.append(" INTERSECT ");
                    sb3.append(" INTERSECT ");
                }
                String upperCase = str3.substring(0, 1).toUpperCase();
                sb2.append("SELECT DISTINCT(ContentID), DrugName, 'D' AS TYPE, Source FROM tblDrugs, tblWords");
                sb2.append(upperCase);
                sb2.append(" WHERE IndividualWord LIKE '");
                sb2.append(str3);
                sb2.append("%' AND Source=1 AND CAID = UniqueID AND ContentID != 0");
                sb3.append("SELECT DISTINCT(ClassID), ClassName, '");
                sb3.append(TYPE_DRUG_CLASS);
                sb3.append("' AS TYPE, Source FROM tblClassNames, tblWords");
                sb3.append(upperCase);
                sb3.append(" WHERE IndividualWord LIKE '");
                sb3.append(str3);
                sb3.append("%' AND ((Source = ");
                sb3.append(3);
                sb3.append(" AND ParentID IS NULL AND SingleLevel IS NULL) OR (Source = ");
                sb3.append(4);
                sb3.append(" AND (ParentID is NOT NULL OR SingleLevel is NOT NULL))) AND CAID = ClassID");
                sb3.append(" AND ClassName LIKE '%' || IndividualWord || '%'");
                i3++;
            }
            sb.append(sb2);
            sb.append(") UNION ");
            sb.append(sb3);
            sb.append(") ORDER BY 2 COLLATE NOCASE");
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                Cursor rawQuery = databaseHelper.getDatabase().rawQuery(sb.toString(), (String[]) null);
                while (rawQuery.moveToNext()) {
                    CRData cRData = new CRData();
                    cRData.setCid(rawQuery.getInt(0));
                    cRData.setTitle(rawQuery.getString(1));
                    cRData.setType(rawQuery.getString(2));
                    cRData.setSource(rawQuery.getInt(3));
                    arrayList.add(cRData);
                }
                rawQuery.close();
                databaseHelper.close();
            } catch (Exception e) {
                LogUtil.e("SearchHelper", "getMessage = %s", e.getMessage());
            }
            List arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            String str4 = (String) arrayList2.get(0);
            for (CRData cRData2 : arrayList) {
                if (cRData2.getTitle().substring(0, str4.length()).equalsIgnoreCase(str4)) {
                    arrayList3.add(cRData2);
                } else {
                    arrayList4.add(cRData2);
                }
            }
            arrayList3.addAll(arrayList4);
            if (arrayList3.size() >= i2) {
                arrayList3 = arrayList3.subList(i2, arrayList3.size());
            }
            arrayList = arrayList3;
            if (i > 0 && arrayList.size() > i) {
                arrayList.subList(i, arrayList.size()).clear();
            }
        }
        if (arrayList.size() > 0) {
            for (CRData cRData3 : arrayList) {
                cRData3.setGenericId(DrugDataHelper.findGenericIdFromContentId(context, cRData3.getCid(), cRData3.getTitle()));
            }
        }
        return arrayList;
    }

    public static String buildDrugSearchSql(CharSequence charSequence, boolean z, int i, boolean z2) {
        String str;
        String str2;
        List<String> prepareSearchTokens = prepareSearchTokens(charSequence);
        if (prepareSearchTokens.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" DISTINCT(");
        sb.append(DrugsContract.Drug.CONTENT_ID);
        sb.append("), ");
        String str3 = "UniqueID";
        sb.append(str3);
        sb.append(" AS ");
        sb.append("_id");
        sb.append(", ");
        sb.append(DrugsContract.Drug.DRUG_NAME);
        sb.append(" AS NAME");
        sb.append(", ");
        sb.append(DrugsContract.Drug.GENERIC_ID);
        sb.append(", '");
        sb.append(TYPE_DRUG);
        sb.append("' AS TYPE");
        sb.append(", W0.Source");
        sb.append(", ");
        sb.append(DrugsContract.Drug.DRUG_NAME);
        sb.append(" LIKE ");
        sb.append("'");
        int i2 = 0;
        sb.append(prepareSearchTokens.get(0));
        sb.append("%");
        sb.append("' AS MATCH");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT ClassID");
        sb2.append(", NULL AS ");
        sb2.append(str3);
        sb2.append(", ClassName AS NAME");
        sb2.append(", NULL AS ");
        sb2.append(DrugsContract.Drug.GENERIC_ID);
        sb2.append(", '");
        sb2.append(TYPE_DRUG_CLASS);
        sb2.append("' AS TYPE, W0.Source");
        sb2.append(", ClassName LIKE ");
        sb2.append("'");
        sb2.append(prepareSearchTokens.get(0));
        sb2.append("%");
        sb2.append("' AS MATCH");
        StringBuilder sb3 = new StringBuilder("WHERE ");
        sb3.append(DrugsContract.Drug.CONTENT_ID);
        sb3.append(" != 0");
        StringBuilder sb4 = new StringBuilder("FROM ");
        String str4 = DrugsContract.Drug.TABLE;
        sb4.append(str4);
        StringBuilder sb5 = new StringBuilder("WHERE ");
        StringBuilder sb6 = new StringBuilder("FROM ");
        sb6.append("tblClassNames");
        int i3 = 0;
        while (i3 < prepareSearchTokens.size()) {
            String str5 = prepareSearchTokens.get(i3);
            List<String> list = prepareSearchTokens;
            String upperCase = str5.substring(i2, 1).toUpperCase();
            StringBuilder sb7 = new StringBuilder();
            StringBuilder sb8 = sb2;
            sb7.append("tblWords");
            sb7.append(upperCase);
            String sb9 = sb7.toString();
            String str6 = ExifInterface.LONGITUDE_WEST + i3;
            sb4.append(" INNER JOIN ");
            sb4.append(sb9);
            sb4.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb4.append(str6);
            sb4.append(" ON ");
            sb6.append(" INNER JOIN ");
            sb6.append(sb9);
            sb6.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb6.append(str6);
            sb6.append(" ON ");
            if (i3 == 0) {
                sb4.append(str4);
                sb4.append(".");
                sb4.append(str3);
                str2 = str3;
                sb6.append("tblClassNames");
                sb6.append(".");
                sb6.append("ClassID");
                str = str4;
            } else {
                str2 = str3;
                StringBuilder sb10 = new StringBuilder();
                str = str4;
                sb10.append(ExifInterface.LONGITUDE_WEST);
                sb10.append(i3 - 1);
                String sb11 = sb10.toString();
                sb4.append(sb11);
                sb4.append(".");
                sb4.append("CAID");
                sb6.append(sb11);
                sb6.append(".");
                sb6.append("CAID");
                sb5.append(" AND ");
            }
            sb4.append(" = ");
            sb4.append(str6);
            sb4.append(".");
            sb4.append("CAID");
            sb6.append(" = ");
            sb6.append(str6);
            sb6.append(".");
            sb6.append("CAID");
            sb3.append(" AND ");
            sb3.append(str6);
            sb3.append(".IndividualWord");
            sb3.append(" LIKE ");
            sb3.append("'");
            sb3.append(str5);
            sb3.append("%");
            sb3.append("'");
            sb3.append(" AND ");
            sb3.append(str6);
            sb3.append(".Source");
            sb3.append(" = 1");
            sb5.append(str6);
            sb5.append(".IndividualWord");
            sb5.append(" LIKE ");
            sb5.append("'");
            sb5.append(str5);
            sb5.append("%");
            sb5.append("'");
            sb5.append(" AND ((");
            sb5.append(str6);
            sb5.append(".Source");
            sb5.append(" = ");
            sb5.append(3);
            sb5.append(" AND ParentID IS NULL AND SingleLevel IS NULL) OR (");
            sb5.append(str6);
            sb5.append(".Source");
            sb5.append(" = ");
            sb5.append(4);
            sb5.append(" AND (ParentID is NOT NULL OR SingleLevel is NOT NULL)))");
            sb5.append(" AND ClassName LIKE '%' || ");
            sb5.append(str6);
            sb5.append(". IndividualWord || '%'");
            i3++;
            prepareSearchTokens = list;
            sb2 = sb8;
            str3 = str2;
            str4 = str;
            i2 = 0;
        }
        StringBuilder sb12 = sb2;
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(sb4);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(sb3);
        if (z2) {
            sb.append(" AND (GenericID != 0) AND (Availability = 1 OR Availability = 4)");
        }
        if (z) {
            sb.append(" UNION ");
            sb.append(sb12);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(sb6);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(sb5);
        }
        sb.append(" ORDER BY ");
        sb.append("MATCH");
        sb.append(" DESC");
        sb.append(", NAME COLLATE NOCASE");
        sb.append(" LIMIT ");
        sb.append(i);
        return sb.toString();
    }

    private static List<String> prepareSearchTokens(CharSequence charSequence) {
        String[] split = String.valueOf(charSequence).trim().replaceAll("[^a-zA-Z0-9]+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        ArrayList arrayList = new ArrayList(split.length);
        for (String str : split) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public List<CRData> wordDrugInteractionMatching(String str, int i, int i2, Context context) {
        String trim = str.trim();
        ArrayList<CRData> arrayList = new ArrayList<>();
        if (trim.length() == 0) {
            return arrayList;
        }
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery(("SELECT DISTINCT(ContentID), DrugName , DrugID,ComboID,GenericID  FROM tblDrugs WHERE (DrugName LIKE '" + trim + "%' AND GenericID!= 0) OR (DrugName LIKE '" + trim + "%'  AND GenericID = 0 AND ComboID != 0) ") + " ORDER BY DrugName COLLATE NOCASE", (String[]) null);
            while (rawQuery.moveToNext()) {
                CRData cRData = new CRData();
                cRData.setCid(rawQuery.getInt(0));
                cRData.setTitle(rawQuery.getString(1));
                cRData.setDrugId(rawQuery.getInt(2));
                cRData.setComboId(rawQuery.getInt(3));
                cRData.setGenericId(rawQuery.getInt(4));
                arrayList.add(cRData);
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            LogUtil.e("SearchHelper", "getMessage = %s", e.getMessage());
        }
        List<CRData> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        String valueOf = String.valueOf(trim.indexOf(0));
        for (CRData cRData2 : arrayList) {
            if (cRData2.getTitle().substring(0, valueOf.length()).equalsIgnoreCase(valueOf)) {
                arrayList2.add(cRData2);
            } else {
                arrayList3.add(cRData2);
            }
        }
        arrayList2.addAll(arrayList3);
        if (arrayList2.size() >= i2) {
            arrayList2 = arrayList2.subList(i2, arrayList2.size());
        }
        if (i > 0 && arrayList2.size() > i) {
            arrayList2.subList(i, arrayList2.size()).clear();
        }
        return arrayList2;
    }

    public static String buildCalcsSearchSql(CharSequence charSequence, boolean z, int i) {
        List<String> prepareSearchTokens = prepareSearchTokens(charSequence);
        if (prepareSearchTokens.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" DISTINCT(");
        sb.append(CalcsContract.CALC_ID);
        sb.append("), ");
        sb.append("UniqueID");
        sb.append(" AS ");
        sb.append("_id");
        sb.append(", ");
        sb.append("Title");
        sb.append(" AS NAME");
        sb.append(", '");
        sb.append(TYPE_CALCULATOR);
        sb.append("' AS TYPE");
        sb.append(", W0.Source");
        sb.append(", ");
        sb.append("Title");
        sb.append(" LIKE ");
        sb.append("'");
        sb.append(prepareSearchTokens.get(0));
        String str = "%";
        sb.append(str);
        sb.append("' AS MATCH");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SELECT FolderID");
        sb2.append(", NULL AS ");
        sb2.append("UniqueID");
        sb2.append(", SegmentName AS NAME");
        sb2.append(", '");
        sb2.append(TYPE_CALCULATOR_FOLDER);
        sb2.append("' AS TYPE, W0.Source");
        sb2.append(", SegmentName LIKE ");
        sb2.append("'");
        sb2.append(prepareSearchTokens.get(0));
        sb2.append(str);
        sb2.append("' AS MATCH");
        StringBuilder sb3 = new StringBuilder("WHERE ");
        sb3.append("UniqueID");
        sb3.append(" != 0");
        StringBuilder sb4 = new StringBuilder("FROM ");
        sb4.append(CalcsContract.TABLE);
        StringBuilder sb5 = new StringBuilder("WHERE ");
        sb5.append("IsBrowsable = 1 AND ");
        StringBuilder sb6 = new StringBuilder("FROM ");
        sb6.append("tblCalcsClass");
        int i2 = 0;
        while (i2 < prepareSearchTokens.size()) {
            String str2 = prepareSearchTokens.get(i2);
            List<String> list = prepareSearchTokens;
            StringBuilder sb7 = sb2;
            String upperCase = str2.substring(0, 1).toUpperCase();
            StringBuilder sb8 = new StringBuilder();
            StringBuilder sb9 = sb;
            sb8.append("tblWords");
            sb8.append(upperCase);
            String sb10 = sb8.toString();
            String str3 = ExifInterface.LONGITUDE_WEST + i2;
            String str4 = str;
            sb4.append(" INNER JOIN ");
            sb4.append(sb10);
            sb4.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb4.append(str3);
            String str5 = str2;
            sb4.append(" ON ");
            sb6.append(" INNER JOIN ");
            sb6.append(sb10);
            sb6.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb6.append(str3);
            sb6.append(" ON ");
            if (i2 == 0) {
                sb4.append(CalcsContract.TABLE);
                sb4.append(".");
                sb4.append("UniqueID");
                sb6.append("tblCalcsClass");
                sb6.append(".");
                sb6.append("FolderID");
            } else {
                StringBuilder sb11 = new StringBuilder();
                sb11.append(ExifInterface.LONGITUDE_WEST);
                sb11.append(i2 - 1);
                String sb12 = sb11.toString();
                sb4.append(sb12);
                sb4.append(".");
                sb4.append("CAID");
                sb6.append(sb12);
                sb6.append(".");
                sb6.append("CAID");
                sb5.append(" AND ");
            }
            sb4.append(" = ");
            sb4.append(str3);
            sb4.append(".");
            sb4.append("CAID");
            sb6.append(" = ");
            sb6.append(str3);
            sb6.append(".");
            sb6.append("CAID");
            sb3.append(" AND ");
            sb3.append(str3);
            sb3.append(".IndividualWord");
            sb3.append(" LIKE ");
            sb3.append("'");
            String str6 = str5;
            sb3.append(str6);
            str = str4;
            sb3.append(str);
            sb3.append("'");
            sb3.append(" AND ");
            sb3.append(str3);
            sb3.append(".Source = ");
            sb3.append(2);
            sb5.append(str3);
            sb5.append(".IndividualWord");
            sb5.append(" LIKE ");
            sb5.append("'");
            sb5.append(str6);
            sb5.append(str);
            sb5.append("'");
            i2++;
            prepareSearchTokens = list;
            sb2 = sb7;
            sb = sb9;
        }
        StringBuilder sb13 = sb;
        StringBuilder sb14 = sb2;
        sb13.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb13.append(sb4);
        sb13.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb13.append(sb3);
        if (z) {
            sb13.append(" UNION ");
            sb13.append(sb14);
            sb13.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb13.append(sb6);
            sb13.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb13.append(sb5);
        }
        sb13.append(" ORDER BY ");
        sb13.append("MATCH");
        sb13.append(" DESC");
        sb13.append(", NAME COLLATE NOCASE");
        sb13.append(" LIMIT ");
        sb13.append(i);
        return sb13.toString();
    }

    private synchronized List<CRData> getAllQxCalcsForSearchTerm(String str, List<QxRecyclerViewRowItem> list) {
        ArrayList arrayList;
        DBContentItem dBContentItem;
        CRData cRData;
        String lowerCase = str.toLowerCase();
        arrayList = new ArrayList();
        DBContentItem dBContentItem2 = null;
        for (QxRecyclerViewRowItem next : list) {
            if (next instanceof LeafItemRowItem) {
                cRData = convertQxCalcToMedscapeCalc(next);
                dBContentItem = ((LeafItemRowItem) next).contentItem;
            } else {
                dBContentItem = dBContentItem2;
                cRData = null;
            }
            if (cRData != null) {
                if (next.getTitle().toLowerCase().contains(lowerCase)) {
                    arrayList.add(cRData);
                } else if (dBContentItem != null && dBContentItem.getTags() != null && !dBContentItem.getTags().isEmpty()) {
                    Iterator<DBTag> it = dBContentItem.getTags().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            DBTag next2 = it.next();
                            if (next2 != null && next2.getName().toLowerCase().contains(lowerCase)) {
                                arrayList.add(cRData);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            dBContentItem2 = dBContentItem;
        }
        return arrayList;
    }

    private static CRData convertQxCalcToMedscapeCalc(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        CRData cRData = new CRData();
        DBContentItem dBContentItem = ((LeafItemRowItem) qxRecyclerViewRowItem).contentItem;
        cRData.setCalcId(dBContentItem.getIdentifier());
        cRData.setTitle(dBContentItem.getName());
        cRData.setType(TYPE_CALCULATOR);
        cRData.setContentItem(dBContentItem);
        Log.d("SearchHelper", "calc id : " + cRData.getCalcId() + " , Title : " + cRData.getTitle() + " , Type : " + cRData.getType());
        return cRData;
    }
}
