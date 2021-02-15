package com.webmd.webmdrx.adapters;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.ArrayAdapter;
import java.util.List;

public class ShareAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> mPackages;
    private PackageManager mPm;
    private int mTextViewResourceId;

    public ShareAdapter(Context context2, int i, int i2, List<String> list) {
        super(context2, i, i2, list);
        this.context = context2;
        this.mPm = context2.getPackageManager();
        this.mTextViewResourceId = i2;
        this.mPackages = list;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r0 = ((android.graphics.drawable.BitmapDrawable) androidx.core.content.ContextCompat.getDrawable(r7.context, com.webmd.webmdrx.R.drawable.ic_nfc_black_24dp)).getBitmap();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x007d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r8, android.view.View r9, android.view.ViewGroup r10) {
        /*
            r7 = this;
            java.util.List<java.lang.String> r0 = r7.mPackages
            java.lang.Object r0 = r0.get(r8)
            java.lang.String r0 = (java.lang.String) r0
            android.view.View r8 = super.getView(r8, r9, r10)
            int r9 = r7.mTextViewResourceId
            android.view.View r9 = r8.findViewById(r9)
            android.widget.TextView r9 = (android.widget.TextView) r9
            android.content.Context r10 = r7.context
            r1 = 1109393408(0x42200000, float:40.0)
            float r10 = com.webmd.webmdrx.util.Util.convertDpToPixel(r1, r10)
            int r10 = (int) r10
            java.lang.String r1 = "webmdrx"
            boolean r1 = r0.contains(r1)     // Catch:{ NameNotFoundException -> 0x00b5 }
            r2 = 1094713344(0x41400000, float:12.0)
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0063
            int r0 = com.webmd.webmdrx.R.string.copy_to_clipboard     // Catch:{ NameNotFoundException -> 0x00b5 }
            r9.setText(r0)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.Context r0 = r7.context     // Catch:{ NameNotFoundException -> 0x00b5 }
            int r1 = com.webmd.webmdrx.R.drawable.ic_content_copy_black_24dp     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.drawable.Drawable r0 = androidx.core.content.ContextCompat.getDrawable(r0, r1)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.Bitmap r0 = r0.getBitmap()     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.Context r5 = r7.context     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.res.Resources r5 = r5.getResources()     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.Bitmap r10 = android.graphics.Bitmap.createScaledBitmap(r0, r10, r10, r3)     // Catch:{ NameNotFoundException -> 0x00b5 }
            r1.<init>(r5, r10)     // Catch:{ NameNotFoundException -> 0x00b5 }
            r9.setCompoundDrawablesWithIntrinsicBounds(r1, r4, r4, r4)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.Context r10 = r7.getContext()     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.res.Resources r10 = r10.getResources()     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()     // Catch:{ NameNotFoundException -> 0x00b5 }
            float r10 = android.util.TypedValue.applyDimension(r3, r2, r10)     // Catch:{ NameNotFoundException -> 0x00b5 }
            int r10 = (int) r10     // Catch:{ NameNotFoundException -> 0x00b5 }
            r9.setCompoundDrawablePadding(r10)     // Catch:{ NameNotFoundException -> 0x00b5 }
            goto L_0x00b9
        L_0x0063:
            android.content.pm.PackageManager r1 = r7.mPm     // Catch:{ NameNotFoundException -> 0x00b5 }
            r5 = 0
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo(r0, r5)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.pm.PackageManager r5 = r7.mPm     // Catch:{ NameNotFoundException -> 0x00b5 }
            java.lang.CharSequence r1 = r5.getApplicationLabel(r1)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.pm.PackageManager r5 = r7.mPm     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.drawable.Drawable r0 = r5.getApplicationIcon(r0)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0     // Catch:{ Exception -> 0x007d }
            android.graphics.Bitmap r0 = r0.getBitmap()     // Catch:{ Exception -> 0x007d }
            goto L_0x008b
        L_0x007d:
            android.content.Context r0 = r7.context     // Catch:{ NameNotFoundException -> 0x00b5 }
            int r5 = com.webmd.webmdrx.R.drawable.ic_nfc_black_24dp     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.drawable.Drawable r0 = androidx.core.content.ContextCompat.getDrawable(r0, r5)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.Bitmap r0 = r0.getBitmap()     // Catch:{ NameNotFoundException -> 0x00b5 }
        L_0x008b:
            android.graphics.drawable.BitmapDrawable r5 = new android.graphics.drawable.BitmapDrawable     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.Context r6 = r7.context     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.res.Resources r6 = r6.getResources()     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.graphics.Bitmap r10 = android.graphics.Bitmap.createScaledBitmap(r0, r10, r10, r3)     // Catch:{ NameNotFoundException -> 0x00b5 }
            r5.<init>(r6, r10)     // Catch:{ NameNotFoundException -> 0x00b5 }
            r9.setText(r1)     // Catch:{ NameNotFoundException -> 0x00b5 }
            r9.setCompoundDrawablesWithIntrinsicBounds(r5, r4, r4, r4)     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.Context r10 = r7.getContext()     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.content.res.Resources r10 = r10.getResources()     // Catch:{ NameNotFoundException -> 0x00b5 }
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()     // Catch:{ NameNotFoundException -> 0x00b5 }
            float r10 = android.util.TypedValue.applyDimension(r3, r2, r10)     // Catch:{ NameNotFoundException -> 0x00b5 }
            int r10 = (int) r10     // Catch:{ NameNotFoundException -> 0x00b5 }
            r9.setCompoundDrawablePadding(r10)     // Catch:{ NameNotFoundException -> 0x00b5 }
            goto L_0x00b9
        L_0x00b5:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00b9:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.adapters.ShareAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
