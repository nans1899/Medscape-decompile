package net.media.android.bidder.base.configs;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.mnet.gson.k;
import com.mnet.gson.n;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mnetinternal.x;
import mnetinternal.y;
import mnetinternal.z;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import org.jetbrains.anko.DimensionsKt;

public final class c {
    private static volatile c a;
    /* access modifiers changed from: private */
    public Map<String, Object> b = new ConcurrentHashMap();
    private Map<String, Object> c = new ConcurrentHashMap();

    private c() {
        c();
        x.a().a(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, new z() {
            public void a(Object obj) {
                c.this.b.clear();
                c.this.b();
            }
        });
    }

    public static c a() {
        c cVar = a;
        if (cVar == null) {
            synchronized (c.class) {
                cVar = a;
                if (cVar == null) {
                    cVar = new c();
                    a = cVar;
                }
            }
        }
        cVar.b();
        return cVar;
    }

    /* access modifiers changed from: private */
    public void b() {
        Map<String, Object> map = this.b;
        if (map == null || map.size() <= 0) {
            Logger.debug("##SdkConfig##", "getting sdk configs");
            n a2 = a.a().a("sdk_config");
            if (a2 != null) {
                this.b.putAll((Map) a.b().a((k) a2, Map.class));
                x.a().a((y) new y() {
                    public String a() {
                        return AdTrackerEvent.EVENT_DONE_SDK_CONFIG_FETCH;
                    }

                    public Object b() {
                        return null;
                    }
                });
            }
        }
    }

    private void c() {
        this.c.put("mnet_context_link_base_url", "http://ma.mn");
        this.c.put("mnet_auto_update", false);
        this.c.put("mnet_video_prefetch_time", 7200000L);
        this.c.put("mnet_auto_refresh_interval", 7200L);
        this.c.put("mnet_cache_max_size", 100000000L);
        this.c.put("mnet_http_timeout", 30000);
        Map<String, Object> map = this.c;
        Integer valueOf = Integer.valueOf(DimensionsKt.XHDPI);
        map.put("mnet_banner_width", valueOf);
        this.c.put("mnet_banner_height", 50);
        this.c.put("mnet_mp_banner_width", valueOf);
        this.c.put("mnet_mp_banner_height", 50);
        this.c.put("mnet_http_disk_cache", 10485760);
        this.c.put("mnet_location_update_interval", 10000L);
        this.c.put("mnet_default_signal_level", 5);
        this.c.put("mnet_video_seek_delay", Integer.valueOf(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
        this.c.put("mnet_config_update_interval", 900000);
        this.c.put("mnet_slot_debug_rate", Double.valueOf(0.01d));
        this.c.put("mnet_am_mt", "");
        this.c.put("mnet_fire_video_events", true);
        this.c.put("mnet_show_save_image_dialog", false);
        this.c.put("mnet_video_auto_play", true);
        this.c.put("mnet_resources_url", "https://rtb.msas.media.net/r/android/");
        this.c.put("url_length_max", 1000L);
        this.c.put("is_eu", true);
        this.c.put("gdprVendorId", 142);
        this.c.put("MAX_HTTP_RETRIES", 5);
        this.c.put(Constants.DEFAULT_DTC, "gcp/us-west-2");
        this.c.put("eu_dnt", true);
        this.c.put("dnt", false);
        this.c.put("unknown_p", false);
    }

    public int a(String str) {
        Map<String, Object> map = this.b;
        if (map != null && map.get(str) != null) {
            return (int) ((Double) this.b.get(str)).doubleValue();
        }
        Logger.debug("##SdkConfig##", "configs not available, returning default value");
        return ((Integer) this.c.get(str)).intValue();
    }

    public long b(String str) {
        Map<String, Object> map = this.b;
        if (map != null && map.get(str) != null) {
            return (long) ((Double) this.b.get(str)).doubleValue();
        }
        Logger.debug("##SdkConfig##", "configs not available, returning default value");
        return ((Long) this.c.get(str)).longValue();
    }

    public double c(String str) {
        Map<String, Object> map = this.b;
        if (map != null && map.get(str) != null) {
            return ((Double) this.b.get(str)).doubleValue();
        }
        Logger.debug("##SdkConfig##", "configs not available, returning default value");
        return ((Double) this.c.get(str)).doubleValue();
    }

    public String d(String str) {
        Map<String, Object> map = this.b;
        if (map != null && map.get(str) != null) {
            return (String) this.b.get(str);
        }
        Logger.debug("##SdkConfig##", "configs not available, returning default value");
        return (String) this.c.get(str);
    }

    public boolean e(String str) {
        Map<String, Object> map = this.b;
        if (map != null && map.get(str) != null) {
            return ((Boolean) this.b.get(str)).booleanValue();
        }
        Logger.debug("##SdkConfig##", "configs not available, returning default value");
        return ((Boolean) this.c.get(str)).booleanValue();
    }
}
