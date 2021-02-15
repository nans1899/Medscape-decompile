package com.medscape.android.drugs;

import android.content.Context;
import android.database.Cursor;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

public abstract class AbstractCursorLoader extends AsyncTaskLoader<Cursor> {
    Cursor mCursor;
    final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver();

    /* access modifiers changed from: protected */
    public abstract Cursor buildCursor();

    public Cursor loadInBackground() {
        Cursor buildCursor = buildCursor();
        if (buildCursor != null) {
            buildCursor.getCount();
            buildCursor.registerContentObserver(this.mObserver);
        }
        return buildCursor;
    }

    public void deliverResult(Cursor cursor) {
        if (!isReset()) {
            Cursor cursor2 = this.mCursor;
            this.mCursor = cursor;
            if (isStarted()) {
                super.deliverResult(cursor);
            }
            if (cursor2 != null && cursor2 != cursor && !cursor2.isClosed()) {
                cursor2.close();
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    public AbstractCursorLoader(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.mCursor == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }

    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        onStopLoading();
        Cursor cursor = this.mCursor;
        if (cursor != null && !cursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }
}
