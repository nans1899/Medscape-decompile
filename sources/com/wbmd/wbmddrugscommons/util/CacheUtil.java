package com.wbmd.wbmddrugscommons.util;

import android.content.Context;
import com.wbmd.wbmdcommons.caching.CacheProvider;
import com.wbmd.wbmdcommons.caching.ICacheProvider;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddrugscommons.model.Drug;
import com.wbmd.wbmddrugscommons.model.Tug;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CacheUtil {
    private final String TAG = getClass().getSimpleName();
    private ICacheProvider mCacheProvider;
    private Context mContext;
    private Long mExpiryTime;

    public Long getExpiryTime() {
        return this.mExpiryTime;
    }

    public void setExpiryTime(Long l) {
        this.mExpiryTime = l;
    }

    public CacheUtil(Context context) {
        this.mContext = context;
    }

    public void saveStringDataToCache(String str, String str2) {
        if (this.mCacheProvider != null) {
            CacheProvider.Entry entry = new CacheProvider.Entry();
            if (getExpiryTime() != null) {
                entry.ExpireTime = getExpiryTime().longValue();
            }
            entry.key = str2;
            entry.data = SerializerUtil.serialize((Object) str.toString());
            this.mCacheProvider.put(entry);
        }
    }

    public void saveDataToCache(List<Drug> list, String str) {
        if (this.mCacheProvider != null) {
            CacheProvider.Entry entry = new CacheProvider.Entry();
            if (getExpiryTime() != null) {
                entry.ExpireTime = getExpiryTime().longValue();
            }
            entry.key = str;
            entry.data = SerializerUtil.serialize(list);
            this.mCacheProvider.put(entry);
        }
    }

    public void saveTugsToCache(HashMap<String, Tug> hashMap, String str) {
        if (this.mCacheProvider != null) {
            CacheProvider.Entry entry = new CacheProvider.Entry();
            if (getExpiryTime() != null) {
                entry.ExpireTime = getExpiryTime().longValue();
            }
            entry.key = str;
            entry.data = SerializerUtil.serializeTugs(hashMap);
            this.mCacheProvider.put(entry);
        }
    }

    public void initCacheProvider(String str) {
        Context context;
        if (this.mCacheProvider == null && (context = this.mContext) != null) {
            this.mCacheProvider = new CacheProvider(context);
            this.mCacheProvider.initialize(new File(this.mContext.getFilesDir(), str), 2097152);
        }
    }

    private ICacheProvider getCacheProvider() {
        return this.mCacheProvider;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r2 = r0.get(r3, r2.booleanValue());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getStringDataFromCache(java.lang.Boolean r2, java.lang.String r3) {
        /*
            r1 = this;
            com.wbmd.wbmdcommons.caching.ICacheProvider r0 = r1.getCacheProvider()
            r1.mCacheProvider = r0
            if (r0 == 0) goto L_0x0026
            boolean r2 = r2.booleanValue()
            com.wbmd.wbmdcommons.caching.CacheProvider$Entry r2 = r0.get(r3, r2)
            if (r2 == 0) goto L_0x0026
            byte[] r3 = r2.data
            if (r3 == 0) goto L_0x0026
            byte[] r3 = r2.data
            int r3 = r3.length
            if (r3 <= 0) goto L_0x0026
            byte[] r2 = r2.data
            java.lang.Object r2 = com.wbmd.wbmddrugscommons.util.SerializerUtil.deserializeString(r2)
            java.lang.String r2 = r2.toString()
            return r2
        L_0x0026:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmddrugscommons.util.CacheUtil.getStringDataFromCache(java.lang.Boolean, java.lang.String):java.lang.String");
    }

    public HashMap<String, Tug> getTTSDataFromCache(Boolean bool, String str) {
        CacheProvider.Entry entry;
        ICacheProvider cacheProvider = getCacheProvider();
        this.mCacheProvider = cacheProvider;
        if (cacheProvider == null || (entry = cacheProvider.get(str, bool.booleanValue())) == null || entry.data == null || entry.data.length <= 0) {
            return null;
        }
        HashMap<String, Tug> deserializeTugs = SerializerUtil.deserializeTugs(entry.data);
        try {
            String str2 = this.TAG;
            Trace.i(str2, "drugs read from cache - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
            return deserializeTugs;
        } catch (Exception e) {
            e.printStackTrace();
            return deserializeTugs;
        }
    }

    public List<Drug> getDataFromCache(Boolean bool, String str) {
        CacheProvider.Entry entry;
        ICacheProvider cacheProvider = getCacheProvider();
        this.mCacheProvider = cacheProvider;
        if (cacheProvider == null || (entry = cacheProvider.get(str, bool.booleanValue())) == null || entry.data == null || entry.data.length <= 0) {
            return null;
        }
        List<Drug> deserialize = SerializerUtil.deserialize(entry.data);
        try {
            String str2 = this.TAG;
            Trace.i(str2, "drugs read from cache - " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
            return deserialize;
        } catch (Exception e) {
            e.printStackTrace();
            return deserialize;
        }
    }

    public void removeKey(String str) {
        ICacheProvider iCacheProvider = this.mCacheProvider;
        if (iCacheProvider != null) {
            iCacheProvider.removeKey(str);
        }
    }
}
