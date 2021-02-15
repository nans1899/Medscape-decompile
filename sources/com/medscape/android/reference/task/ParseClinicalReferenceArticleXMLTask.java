package com.medscape.android.reference.task;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.R;
import com.medscape.android.reference.ClinicalReferenceContentManager;
import com.medscape.android.reference.exception.ContentParsingException;
import com.medscape.android.reference.interfaces.ContentLoaderCallback;
import com.medscape.android.reference.model.ClinicalReferenceContent;

public class ParseClinicalReferenceArticleXMLTask extends AsyncTask<String, String, ClinicalReferenceContent> {
    private Exception e;
    private ContentLoaderCallback mCallback;
    private Context mContext;

    public ParseClinicalReferenceArticleXMLTask(Context context, ContentLoaderCallback contentLoaderCallback) {
        this.mContext = context;
        this.mCallback = contentLoaderCallback;
    }

    /* access modifiers changed from: protected */
    public ClinicalReferenceContent doInBackground(String... strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            if (strArr.length <= 0 || strArr[0] == null) {
                return null;
            }
            return new ClinicalReferenceContentManager(this.mContext).parseXMLContent(strArr[0]);
        } catch (ContentParsingException e2) {
            this.e = e2;
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(ClinicalReferenceContent clinicalReferenceContent) {
        ContentLoaderCallback contentLoaderCallback;
        if (!isCancelled() && (contentLoaderCallback = this.mCallback) != null) {
            if (clinicalReferenceContent != null) {
                contentLoaderCallback.handleContentloaded(clinicalReferenceContent);
            } else if (this.e != null) {
                Context context = this.mContext;
                if (context != null) {
                    contentLoaderCallback.handleContentLoadingError(context.getResources().getString(R.string.alert_dialog_content_unavailable_message));
                }
            } else {
                Context context2 = this.mContext;
                if (context2 != null) {
                    contentLoaderCallback.handleContentLoadingError(context2.getResources().getString(R.string.alert_dialog_content_unavailable_message));
                }
            }
        }
    }
}
