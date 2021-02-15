package com.medscape.android.cache;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.medscape.android.util.StringUtil;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import net.bytebuddy.description.type.TypeDescription;

public class CacheBroadCastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            if (action.equals(Constants.CONTENT_SAVE_UNSAVE_ACTION)) {
                sendSaveSatusBroadCast(context, performSaveUnSave(context, intent.getStringExtra(Constants.CONTENT_SAVE_TITLE), intent.getStringExtra(Constants.CONTENT_SAVE_URL), Boolean.valueOf(intent.getBooleanExtra(Constants.CONTENT_IS_SAVED, false))));
            } else if (action.equals(Constants.CONTENT_CHECK_SAVE)) {
                sendSaveSatusBroadCast(context, isContentSaved(context, intent.getStringExtra(Constants.CONTENT_SAVE_URL)));
            }
        }
    }

    public boolean isContentSaved(Context context, String str) {
        if (context == null || !StringUtil.isNotEmpty(str)) {
            return false;
        }
        String cleanURl = cleanURl(str);
        Cache cache = new CacheManager(context).getCache(cleanURl.substring(0, cleanURl.contains(TypeDescription.Generic.OfWildcardType.SYMBOL) ? cleanURl.indexOf(TypeDescription.Generic.OfWildcardType.SYMBOL) : cleanURl.length()));
        if (cache == null || !cache.isSaved()) {
            return false;
        }
        return true;
    }

    public boolean performSaveUnSave(Context context, String str, String str2, Boolean bool) {
        String cleanURl = cleanURl(str2);
        boolean z = !bool.booleanValue();
        if (StringUtil.isNotEmpty(cleanURl) && (StringUtil.isNotEmpty(str) || !z)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("isSaved", Integer.valueOf(z ? 1 : 0));
            contentValues.put("type", Integer.valueOf(Cache.CME));
            String[] strArr = {cleanURl.trim()};
            CacheManager cacheManager = new CacheManager(context);
            if (cacheManager.updateCache(contentValues, "url = ?", strArr) || !z) {
                return z;
            }
            Cache cache = new Cache();
            cache.setUrl(cleanURl.trim());
            cache.setType(Cache.CME);
            cache.setTime("");
            cache.setTitle(str);
            cache.setSaved(true);
            cacheManager.addCache(cache);
            return z;
        } else if (z) {
            return false;
        } else {
            return z;
        }
    }

    public String cleanURl(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return str;
        }
        return str.substring(0, str.contains(TypeDescription.Generic.OfWildcardType.SYMBOL) ? str.indexOf(TypeDescription.Generic.OfWildcardType.SYMBOL) : str.length());
    }

    private void sendSaveSatusBroadCast(Context context, boolean z) {
        Intent intent = new Intent(Constants.CONTENT_RESPONSE_SAVE);
        intent.putExtra(Constants.CONTENT_IS_SAVED, z);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
