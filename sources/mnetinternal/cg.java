package mnetinternal;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.common.b;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.WebPageInfo;

final class cg extends ch {
    cg(Context context) {
        super(context, 4);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        d();
        a(true);
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        if (b() || b(i)) {
            d();
        }
    }

    private void d() {
        this.a.a(new cz<Context>() {
            public void a() {
            }

            public void a(Context context) {
                cg.this.a(context);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(Context context) {
        if (!b.a().e() && cy.a().e()) {
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(Uri.parse("content://browser/bookmarks"), new String[]{"title", "url", "visits"}, "bookmark = 0", (String[]) null, (String) null);
                if (cursor != null) {
                    if (cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        ArrayList arrayList = new ArrayList();
                        if (cursor.moveToFirst() && cursor.getCount() > 0) {
                            while (!cursor.isAfterLast()) {
                                arrayList.add(new WebPageInfo(cursor.getString(cursor.getColumnIndex("title")), cursor.getString(cursor.getColumnIndex("url")), cursor.getInt(cursor.getColumnIndex("visits"))));
                                cursor.moveToNext();
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            ck.a().a("browsing_history", (Object) arrayList);
                            net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newSdkEvent(Constants.SDK_DATA_EVENT, 2).addProperty("browsing_history", arrayList));
                            if (cursor == null) {
                                return;
                            }
                            cursor.close();
                            return;
                        } else if (cursor != null) {
                            cursor.close();
                            return;
                        } else {
                            return;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception unused) {
                if (cursor == null) {
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }
}
