package com.medscape.android.drugs.details.util;

import android.content.Context;
import android.database.Cursor;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.drugs.InteractionsDataUtil;
import com.medscape.android.drugs.details.datamodels.InteractionListItem;
import com.medscape.android.drugs.model.DrugsContract;
import com.wbmd.wbmdcommons.utils.Extensions;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRequests {
    DatabaseHelper dbHelper = null;

    public int getSimplifiedStrengthId(int i) {
        if (i == 2 || i == 3) {
            return 1;
        }
        if (i == 4) {
            return 2;
        }
        if (i == 5 || i == 6 || i == 33) {
            return 3;
        }
        switch (i) {
            case 10:
            case 11:
                return 1;
            case 12:
                break;
            default:
                switch (i) {
                    case 20:
                        break;
                    case 21:
                        return 3;
                    case 22:
                        return 2;
                    default:
                        return 0;
                }
        }
        return 2;
    }

    public DatabaseRequests(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.String[], android.database.Cursor] */
    public List<InteractionListItem> getInteractionDrugList(String str) {
        int i;
        ArrayList arrayList = new ArrayList();
        ? r1 = 0;
        try {
            Cursor rawQuery = this.dbHelper.getDatabase().rawQuery("SELECT DISTINCT GenericID,ComboID FROM tblDrugs WHERE ContentID = " + str + " LIMIT 1", r1);
            int i2 = -2;
            if (rawQuery == null || !rawQuery.moveToFirst()) {
                i = -2;
            } else {
                i = rawQuery.getInt(rawQuery.getColumnIndex(DrugsContract.Drug.GENERIC_ID));
                i2 = rawQuery.getInt(rawQuery.getColumnIndex("ComboID"));
            }
            List<InteractionListItem> drugs = getDrugs(getInteractionDrugIds(i2, i));
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                if (this.dbHelper != null) {
                    this.dbHelper.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return drugs;
        } catch (Exception e3) {
            e3.printStackTrace();
            if (r1 != 0) {
                try {
                    r1.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            try {
                if (this.dbHelper != null) {
                    this.dbHelper.close();
                }
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            return arrayList;
        } catch (Throwable th) {
            if (r1 != 0) {
                try {
                    r1.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            try {
                if (this.dbHelper != null) {
                    this.dbHelper.close();
                }
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            throw th;
        }
    }

    public List<Integer> getInteractionDrugIds(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        if (i != 0) {
            try {
                Cursor rawQuery = this.dbHelper.getDatabase().rawQuery("SELECT DISTINCT A.GenericID FROM tblDrugs A JOIN tblComboDrugs B ON A.GenericID=B.GenericID WHERE B.ComboID = " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, (String[]) null);
                if (rawQuery != null && rawQuery.getCount() > 0) {
                    while (rawQuery.moveToNext()) {
                        arrayList.add(Integer.valueOf(rawQuery.getInt(0)));
                    }
                    rawQuery.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i2 != 0) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    public List<InteractionListItem> getDrugs(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            int intValue = list.get(i).intValue();
            try {
                Cursor rawQuery = this.dbHelper.getDatabase().rawQuery(getInteractionquery(intValue), (String[]) null);
                if (rawQuery != null) {
                    while (rawQuery.moveToNext()) {
                        try {
                            InteractionListItem interactionListItem = new InteractionListItem();
                            if (rawQuery.getInt(3) == intValue) {
                                interactionListItem.setText(rawQuery.getString(2));
                            } else {
                                interactionListItem.setText(rawQuery.getString(4));
                            }
                            interactionListItem.setDetails(InteractionsDataUtil.getInteractionDetailText(rawQuery.getInt(0)));
                            interactionListItem.setExpandable(true);
                            interactionListItem.setStrength(getSimplifiedStrengthId(rawQuery.getInt(7)));
                            if (!Extensions.IsNullOrEmpty(interactionListItem.getText().toString())) {
                                arrayList.add(interactionListItem);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    rawQuery.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public String getInteractionquery(int i) {
        return "SELECT InteractionID, SubjectID, D1.DrugName AS SubjectName, ObjectID, D2.DrugName AS ObjectName, InteractionType, MechID, Strength, Direction, Effect, Comment  FROM tblInteractions LEFT JOIN tblDrugs D1 ON SubjectID = D1.DrugID LEFT JOIN tblDrugs D2 ON ObjectID = D2.DrugID  WHERE SubjectID = " + i + "  OR  ObjectID = " + i;
    }
}
