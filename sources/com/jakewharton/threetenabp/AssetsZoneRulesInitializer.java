package com.jakewharton.threetenabp;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import org.threeten.bp.zone.TzdbZoneRulesProvider;
import org.threeten.bp.zone.ZoneRulesInitializer;
import org.threeten.bp.zone.ZoneRulesProvider;

final class AssetsZoneRulesInitializer extends ZoneRulesInitializer {
    private final String assetPath;
    private final Context context;

    AssetsZoneRulesInitializer(Context context2, String str) {
        this.context = context2;
        this.assetPath = str;
    }

    /* access modifiers changed from: protected */
    public void initializeProviders() {
        InputStream inputStream = null;
        try {
            inputStream = this.context.getAssets().open(this.assetPath);
            TzdbZoneRulesProvider tzdbZoneRulesProvider = new TzdbZoneRulesProvider(inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            ZoneRulesProvider.registerProvider(tzdbZoneRulesProvider);
        } catch (IOException e) {
            throw new IllegalStateException(this.assetPath + " missing from assets", e);
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }
}
