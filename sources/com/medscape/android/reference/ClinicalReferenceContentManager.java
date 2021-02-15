package com.medscape.android.reference;

import android.content.Context;
import android.database.Cursor;
import com.medscape.android.Constants;
import com.medscape.android.activity.calc.model.CalcsContract;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.reference.exception.ContentNotLoadedException;
import com.medscape.android.reference.exception.ContentParsingException;
import com.medscape.android.reference.exception.ContentVersionNotSupportedException;
import com.medscape.android.reference.interfaces.ArticleDownloadListener;
import com.medscape.android.reference.interfaces.ContentLoaderCallback;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.task.ArticleDownloadTask;
import com.medscape.android.util.Util;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.xmlpull.v1.XmlPullParserException;

public class ClinicalReferenceContentManager {
    private static String BASE_URL = "https://img.medscape.com/pi/iphone/medscapeapp/XML/";
    private static final String BASE_URL_AT = "http://img.staging.medscape.com/pi/iphone/medscapeapp/XML/";
    private static final String BASE_URL_AT_UPDATE = "http://img.staging.medscape.com/pi/iphone/medscapeapp/XML/";
    private static final String BASE_URL_BIZDEV = "http://img.staging.medscape.com/pi/iphone/bizdev/html/";
    private static final String BASE_URL_DEV = "http://img.staging.medscape.com/pi/iphone/medscapeapp/XML/";
    private static final String BASE_URL_PROD = "https://img.medscape.com/pi/iphone/medscapeapp/XML/";
    private static final String BASE_URL_QA = "http://img.staging.medscape.com/pi/iphone/medscapeapp/XML/";
    private static final String BASE_URL_SB = "http://img.staging.medscape.com/pi/iphone/medscapeapp/XML/";
    private static final String LOCAL_BASE_URL = "file://";
    private String baseUrl;
    private String localUrl;
    Context mContext;

    public ClinicalReferenceContentManager(Context context) {
        this.mContext = context;
    }

    public ClinicalReferenceArticle fetchArticleWithId(String str) throws ContentNotLoadedException {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this.mContext);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT Title, ArticleID, Type FROM tblClinicalReferenceTitles, tblClinicalReferenceMapping WHERE tblClinicalReferenceTitles.UniqueID = tblClinicalReferenceMapping.UniqueID AND ArticleID = ? ORDER BY Title COLLATE NOCASE", new String[]{str});
            ClinicalReferenceArticle clinicalReferenceArticle = null;
            while (rawQuery.moveToNext()) {
                clinicalReferenceArticle = new ClinicalReferenceArticle();
                clinicalReferenceArticle.setArticleId(rawQuery.getInt(rawQuery.getColumnIndex("ArticleID")));
                clinicalReferenceArticle.setTitle(rawQuery.getString(rawQuery.getColumnIndex("Title")));
                clinicalReferenceArticle.setType(rawQuery.getInt(rawQuery.getColumnIndex(CalcsContract.TYPE)));
            }
            rawQuery.close();
            databaseHelper.close();
            return clinicalReferenceArticle;
        } catch (Exception e) {
            throw new ContentNotLoadedException("Could not load content from database", e);
        }
    }

    public ClinicalReferenceContent parseXMLContent(String str) throws ContentParsingException {
        try {
            return ClinicalReferenceContentParser.get().parse(str, this.mContext);
        } catch (ContentVersionNotSupportedException e) {
            throw new ContentParsingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new ContentParsingException("Could not read xml", e2);
        } catch (XmlPullParserException e3) {
            throw new ContentParsingException("Could not parse xml", e3);
        }
    }

    public void downloadClinicalReferenceArticle(String str, int i, final ContentLoaderCallback contentLoaderCallback) {
        ArticleDownloadTask articleDownloadTask = new ArticleDownloadTask(this.mContext, new ArticleDownloadListener() {
            public void articleDownloadComplete(String str) {
                if (str == null) {
                    contentLoaderCallback.handleContentNotDownloaded();
                } else if (new File(str).exists()) {
                    contentLoaderCallback.handleContentDownloaded(str);
                } else {
                    contentLoaderCallback.handleContentNotDownloaded();
                }
            }

            public void articlDownloadUnsuccessful() {
                contentLoaderCallback.handleContentNotDownloaded();
            }
        });
        articleDownloadTask.execute(new String[]{str, "" + i});
    }

    public void fetchArticleContent(ClinicalReferenceArticle clinicalReferenceArticle, ContentLoaderCallback contentLoaderCallback) {
        setENV();
        this.baseUrl = "";
        if (clinicalReferenceArticle != null) {
            try {
                File file = new File(Constants.DIRECTORY_MAIN_CR + clinicalReferenceArticle.getArticleId() + ".xml");
                this.localUrl = LOCAL_BASE_URL + Constants.DIRECTORY_MAIN_CR + clinicalReferenceArticle.getArticleId() + ".xml";
                if (!file.exists()) {
                    checkArticleOnline(clinicalReferenceArticle.getArticleId(), contentLoaderCallback);
                } else if (isArticleModified(clinicalReferenceArticle.getArticleId(), file.lastModified())) {
                    checkArticleOnline(clinicalReferenceArticle.getArticleId(), contentLoaderCallback);
                } else {
                    this.baseUrl = this.localUrl;
                    contentLoaderCallback.handleContentDownloaded(file.getPath());
                }
            } catch (Exception unused) {
                checkArticleOnline(clinicalReferenceArticle.getArticleId(), contentLoaderCallback);
            }
        }
    }

    private void checkArticleOnline(int i, ContentLoaderCallback contentLoaderCallback) {
        if (Util.isOnline(this.mContext)) {
            String str = BASE_URL + i + ".xml";
            this.baseUrl = str;
            downloadClinicalReferenceArticle(str.trim(), i, contentLoaderCallback);
            return;
        }
        contentLoaderCallback.handleContentNotDownloaded();
    }

    private boolean isArticleModified(int i, long j) {
        Calendar articleDateChanged = getArticleDateChanged(i);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.before(articleDateChanged);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d A[SYNTHETIC, Splitter:B:28:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0065 A[Catch:{ Exception -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a9 A[SYNTHETIC, Splitter:B:49:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00b1 A[Catch:{ Exception -> 0x00ad }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Calendar getArticleDateChanged(int r10) {
        /*
            r9 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "SELECT DateChanged from tblClinicalReferenceArticles where ArticleID=?"
            r2 = 0
            r3 = 0
            r4 = 1
            com.medscape.android.db.DatabaseHelper r5 = new com.medscape.android.db.DatabaseHelper     // Catch:{ Exception -> 0x0055, all -> 0x0052 }
            android.content.Context r6 = r9.mContext     // Catch:{ Exception -> 0x0055, all -> 0x0052 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0055, all -> 0x0052 }
            android.database.sqlite.SQLiteDatabase r6 = r5.getDatabase()     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            java.lang.String[] r7 = new java.lang.String[r4]     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            r8.<init>()     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            r8.append(r0)     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            r8.append(r10)     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            java.lang.String r10 = r8.toString()     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            r7[r2] = r10     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            android.database.Cursor r10 = r6.rawQuery(r1, r7)     // Catch:{ Exception -> 0x004f, all -> 0x004d }
            boolean r1 = r10.moveToFirst()     // Catch:{ Exception -> 0x004b }
            if (r1 == 0) goto L_0x003a
            java.lang.String r1 = "DateChanged"
            int r1 = r10.getColumnIndex(r1)     // Catch:{ Exception -> 0x004b }
            java.lang.String r1 = r10.getString(r1)     // Catch:{ Exception -> 0x004b }
            goto L_0x003b
        L_0x003a:
            r1 = r0
        L_0x003b:
            if (r10 == 0) goto L_0x0043
            r10.close()     // Catch:{ Exception -> 0x0041 }
            goto L_0x0043
        L_0x0041:
            r10 = move-exception
            goto L_0x0047
        L_0x0043:
            r5.close()     // Catch:{ Exception -> 0x0041 }
            goto L_0x006d
        L_0x0047:
            r10.printStackTrace()
            goto L_0x006d
        L_0x004b:
            r1 = move-exception
            goto L_0x0058
        L_0x004d:
            r0 = move-exception
            goto L_0x00a7
        L_0x004f:
            r1 = move-exception
            r10 = r3
            goto L_0x0058
        L_0x0052:
            r0 = move-exception
            r5 = r3
            goto L_0x00a7
        L_0x0055:
            r1 = move-exception
            r10 = r3
            r5 = r10
        L_0x0058:
            r1.printStackTrace()     // Catch:{ all -> 0x00a5 }
            if (r10 == 0) goto L_0x0063
            r10.close()     // Catch:{ Exception -> 0x0061 }
            goto L_0x0063
        L_0x0061:
            r10 = move-exception
            goto L_0x0069
        L_0x0063:
            if (r5 == 0) goto L_0x006c
            r5.close()     // Catch:{ Exception -> 0x0061 }
            goto L_0x006c
        L_0x0069:
            r10.printStackTrace()
        L_0x006c:
            r1 = r0
        L_0x006d:
            if (r1 == 0) goto L_0x00a4
            boolean r10 = r1.equals(r0)
            if (r10 == 0) goto L_0x0076
            goto L_0x00a4
        L_0x0076:
            java.util.Calendar r10 = java.util.Calendar.getInstance()
            java.lang.String r0 = "-"
            java.lang.String[] r0 = r1.split(r0)     // Catch:{ Exception -> 0x009f }
            r1 = r0[r2]     // Catch:{ Exception -> 0x009f }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x009f }
            r2 = r0[r4]     // Catch:{ Exception -> 0x009f }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Exception -> 0x009f }
            r3 = 2
            r0 = r0[r3]     // Catch:{ Exception -> 0x009f }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x009f }
            r10.set(r4, r1)     // Catch:{ Exception -> 0x009f }
            int r2 = r2 - r4
            r10.set(r3, r2)     // Catch:{ Exception -> 0x009f }
            r1 = 5
            r10.set(r1, r0)     // Catch:{ Exception -> 0x009f }
            goto L_0x00a3
        L_0x009f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00a3:
            return r10
        L_0x00a4:
            return r3
        L_0x00a5:
            r0 = move-exception
            r3 = r10
        L_0x00a7:
            if (r3 == 0) goto L_0x00af
            r3.close()     // Catch:{ Exception -> 0x00ad }
            goto L_0x00af
        L_0x00ad:
            r10 = move-exception
            goto L_0x00b5
        L_0x00af:
            if (r5 == 0) goto L_0x00b8
            r5.close()     // Catch:{ Exception -> 0x00ad }
            goto L_0x00b8
        L_0x00b5:
            r10.printStackTrace()
        L_0x00b8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.reference.ClinicalReferenceContentManager.getArticleDateChanged(int):java.util.Calendar");
    }

    private void setENV() {
        String environmentWithDefault = new EnvironmentManager().getEnvironmentWithDefault(this.mContext, EnvironmentConstants.MODULE_CONTENT);
        if (environmentWithDefault.equals(EnvironmentConstants.QA) || environmentWithDefault.equals(EnvironmentConstants.SANDBOX)) {
            BASE_URL = "http://img.staging.medscape.com/pi/iphone/medscapeapp/XML/";
        } else if (environmentWithDefault.equals(EnvironmentConstants.BIZDEV)) {
            BASE_URL = BASE_URL_BIZDEV;
        } else {
            BASE_URL = BASE_URL_PROD;
        }
    }
}
