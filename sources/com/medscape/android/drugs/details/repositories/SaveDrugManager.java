package com.medscape.android.drugs.details.repositories;

import android.content.Context;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.cache.DrugCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018R\u001a\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/medscape/android/drugs/details/repositories/SaveDrugManager;", "", "context", "Landroid/content/Context;", "manager", "Lcom/medscape/android/cache/CacheManager;", "(Landroid/content/Context;Lcom/medscape/android/cache/CacheManager;)V", "cacheManager", "getCacheManager", "()Lcom/medscape/android/cache/CacheManager;", "setCacheManager", "(Lcom/medscape/android/cache/CacheManager;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "isContentSaved", "", "contentId", "", "removeDrug", "", "saveContent", "drugName", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SaveDrugManager.kt */
public final class SaveDrugManager {
    private CacheManager cacheManager;
    private Context context;

    public SaveDrugManager(Context context2, CacheManager cacheManager2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        if (cacheManager2 != null) {
            this.cacheManager = cacheManager2;
        } else {
            this.cacheManager = new CacheManager(this.context);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SaveDrugManager(Context context2, CacheManager cacheManager2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? null : cacheManager2);
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "<set-?>");
        this.context = context2;
    }

    public final CacheManager getCacheManager() {
        return this.cacheManager;
    }

    public final void setCacheManager(CacheManager cacheManager2) {
        Intrinsics.checkNotNullParameter(cacheManager2, "<set-?>");
        this.cacheManager = cacheManager2;
    }

    public final boolean isContentSaved(int i) {
        DrugCache cache = this.cacheManager.getCache(i);
        if (cache != null) {
            return cache.isSaved();
        }
        return false;
    }

    public final boolean saveContent(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "drugName");
        if (i >= 0) {
            if (!(str.length() == 0)) {
                DrugCache drugCache = new DrugCache();
                drugCache.setContentId(i);
                drugCache.setSaved(true);
                drugCache.setType(1);
                drugCache.setDrugName(str);
                try {
                    this.cacheManager.addCache(drugCache);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public final void removeDrug(int i) {
        if (i > -1) {
            try {
                this.cacheManager.deleteSavedDrug(String.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
