package com.medscape.android.reference.data;

import android.content.Context;
import android.database.Cursor;
import com.medscape.android.activity.calc.model.CalcsContract;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.reference.model.ClinicalReference;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.ArrayList;

public class ClinicalReferenceRepository {
    private static final String TAG = ClinicalReferenceRepository.class.getSimpleName();
    private Context mContext;

    public ClinicalReferenceRepository(Context context) {
        this.mContext = context;
    }

    public ArrayList<ClinicalReference> getFolderItems(int i) {
        Cursor cursor;
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this.mContext);
            if (i == -1) {
                cursor = databaseHelper.getDatabase().rawQuery("SELECT FolderID, SegmentName, IsBrowsable FROM tblClinicalReferenceClass WHERE ParentID IS NULL ORDER BY SegmentName COLLATE NOCASE", (String[]) null);
            } else {
                cursor = databaseHelper.getDatabase().rawQuery("SELECT FolderID, SegmentName, IsBrowsable FROM tblClinicalReferenceClass WHERE ParentID = ? ORDER BY SegmentName COLLATE NOCASE", new String[]{String.valueOf(i)});
            }
            ArrayList<ClinicalReference> arrayList = new ArrayList<>();
            while (cursor.moveToNext()) {
                ClinicalReference clinicalReference = new ClinicalReference();
                clinicalReference.setFolderId(cursor.getInt(cursor.getColumnIndex("FolderID")));
                clinicalReference.setName(cursor.getString(cursor.getColumnIndex("SegmentName")));
                clinicalReference.setBrowsable(cursor.getInt(cursor.getColumnIndex("IsBrowsable")) != 0);
                if (clinicalReference.isBrowsable()) {
                    arrayList.add(clinicalReference);
                }
            }
            cursor.close();
            databaseHelper.close();
            return arrayList;
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
            return null;
        }
    }

    public int getFolderItemsCount(ClinicalReference clinicalReference) {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this.mContext);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT COUNT(*) FROM tblClinicalReferenceClass WHERE ParentID = ? ORDER BY SegmentName COLLATE NOCASE", new String[]{String.valueOf(clinicalReference.getFolderId())});
            rawQuery.moveToNext();
            int i = rawQuery.getInt(0);
            rawQuery.close();
            databaseHelper.close();
            return i;
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
            return -1;
        }
    }

    public ArrayList<ClinicalReferenceArticle> getArticles(int i) {
        ArrayList<ClinicalReferenceArticle> arrayList = new ArrayList<>();
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this.mContext);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT Title, ArticleID, Type FROM tblClinicalReferenceTitles, tblClinicalReferenceMapping WHERE tblClinicalReferenceTitles.UniqueID = tblClinicalReferenceMapping.UniqueID AND FolderID = ? ORDER BY Title COLLATE NOCASE", new String[]{String.valueOf(i)});
            while (rawQuery.moveToNext()) {
                ClinicalReferenceArticle clinicalReferenceArticle = new ClinicalReferenceArticle();
                clinicalReferenceArticle.setArticleId(rawQuery.getInt(rawQuery.getColumnIndex("ArticleID")));
                clinicalReferenceArticle.setTitle(rawQuery.getString(rawQuery.getColumnIndex("Title")));
                clinicalReferenceArticle.setType(rawQuery.getInt(rawQuery.getColumnIndex(CalcsContract.TYPE)));
                arrayList.add(clinicalReferenceArticle);
            }
            rawQuery.close();
            databaseHelper.close();
            return arrayList;
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
            return null;
        }
    }
}
