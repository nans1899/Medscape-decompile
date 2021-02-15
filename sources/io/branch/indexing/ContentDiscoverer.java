package io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.TextView;
import io.branch.indexing.ContentDiscoveryManifest;
import io.branch.referral.PrefHelper;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentDiscoverer {
    private static final String COLLECTION_VIEW_KEY_PREFIX = "$";
    private static final String CONTENT_DATA_KEY = "cd";
    private static final String CONTENT_KEYS_KEY = "ck";
    private static final String CONTENT_LINK_KEY = "cl";
    private static final String CONTENT_META_DATA_KEY = "cm";
    private static final String ENABLE_SCROLL_WATCH = "bnc_esw";
    private static final String ENTITIES_KEY = "e";
    private static final String NAV_PATH_KEY = "n";
    private static final String PACKAGE_NAME_KEY = "p";
    private static final String RECYCLER_VIEW = "RecyclerView";
    private static final String REFERRAL_LINK_KEY = "rl";
    private static final int SCROLL_SETTLE_TIME = 1500;
    private static final String TIME_STAMP_CLOSE_KEY = "tc";
    private static final String TIME_STAMP_KEY = "ts";
    private static final String VIEW_KEY = "v";
    private static final int VIEW_SETTLE_TIME = 1000;
    private static ContentDiscoverer thisInstance_;
    /* access modifiers changed from: private */
    public ContentDiscoveryManifest cdManifest_;
    /* access modifiers changed from: private */
    public JSONObject contentEvent_;
    /* access modifiers changed from: private */
    public ArrayList<String> discoveredViewList_ = new ArrayList<>();
    /* access modifiers changed from: private */
    public int discoveryRepeatCnt_;
    /* access modifiers changed from: private */
    public Handler handler_ = new Handler();
    private final HashHelper hashHelper_ = new HashHelper();
    /* access modifiers changed from: private */
    public WeakReference<Activity> lastActivityReference_;
    /* access modifiers changed from: private */
    public int maxDiscoveryRepeatCnt = 15;
    /* access modifiers changed from: private */
    public Runnable readContentRunnable = new Runnable() {
        public void run() {
            int discoveryRepeatInterval;
            try {
                ContentDiscoverer.access$008(ContentDiscoverer.this);
                if (ContentDiscoverer.this.cdManifest_.isCDEnabled() && ContentDiscoverer.this.lastActivityReference_ != null && ContentDiscoverer.this.lastActivityReference_.get() != null) {
                    Activity activity = (Activity) ContentDiscoverer.this.lastActivityReference_.get();
                    JSONObject unused = ContentDiscoverer.this.contentEvent_ = new JSONObject();
                    ContentDiscoverer.this.contentEvent_.put(ContentDiscoverer.TIME_STAMP_KEY, System.currentTimeMillis());
                    if (!TextUtils.isEmpty(ContentDiscoverer.this.referredUrl_)) {
                        ContentDiscoverer.this.contentEvent_.put(ContentDiscoverer.REFERRAL_LINK_KEY, ContentDiscoverer.this.referredUrl_);
                    }
                    String str = "/" + activity.getClass().getSimpleName();
                    ContentDiscoverer.this.contentEvent_.put(ContentDiscoverer.VIEW_KEY, str);
                    ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
                    if (viewGroup != null) {
                        ContentDiscoveryManifest.CDPathProperties cDPathProperties = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity);
                        boolean z = true;
                        boolean z2 = cDPathProperties != null && cDPathProperties.isClearTextRequested();
                        JSONArray jSONArray = null;
                        if (cDPathProperties != null) {
                            z2 = cDPathProperties.isClearTextRequested();
                            JSONObject access$300 = ContentDiscoverer.this.contentEvent_;
                            if (z2) {
                                z = false;
                            }
                            access$300.put("h", z);
                            jSONArray = cDPathProperties.getFilteredElements();
                        }
                        JSONArray jSONArray2 = jSONArray;
                        boolean z3 = z2;
                        if (jSONArray2 != null) {
                            if (jSONArray2.length() > 0) {
                                JSONArray jSONArray3 = new JSONArray();
                                ContentDiscoverer.this.contentEvent_.put(ContentDiscoverer.CONTENT_KEYS_KEY, jSONArray3);
                                JSONArray jSONArray4 = new JSONArray();
                                ContentDiscoverer.this.contentEvent_.put("cd", jSONArray4);
                                ContentDiscoverer.this.discoverContentData(jSONArray2, jSONArray4, jSONArray3, activity, z3);
                                ContentDiscoverer.this.discoveredViewList_.add(str);
                                PrefHelper.getInstance(activity).saveBranchAnalyticsData(ContentDiscoverer.this.contentEvent_);
                                discoveryRepeatInterval = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity).getDiscoveryRepeatInterval();
                                int unused2 = ContentDiscoverer.this.maxDiscoveryRepeatCnt = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity).getMaxDiscoveryRepeatNumber();
                                if (ContentDiscoverer.this.discoveryRepeatCnt_ >= ContentDiscoverer.this.maxDiscoveryRepeatCnt && discoveryRepeatInterval < 500 && jSONArray2 != null && jSONArray2.length() > 0) {
                                    ContentDiscoverer.this.handler_.postDelayed(ContentDiscoverer.this.readContentRunnable, (long) discoveryRepeatInterval);
                                    return;
                                }
                                return;
                            }
                        }
                        if (!ContentDiscoverer.this.discoveredViewList_.contains(str)) {
                            JSONArray jSONArray5 = new JSONArray();
                            ContentDiscoverer.this.contentEvent_.put(ContentDiscoverer.CONTENT_KEYS_KEY, jSONArray5);
                            ContentDiscoverer.this.discoverContentKeys(viewGroup, jSONArray5, activity.getResources());
                        }
                        ContentDiscoverer.this.discoveredViewList_.add(str);
                        PrefHelper.getInstance(activity).saveBranchAnalyticsData(ContentDiscoverer.this.contentEvent_);
                        discoveryRepeatInterval = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity).getDiscoveryRepeatInterval();
                        int unused3 = ContentDiscoverer.this.maxDiscoveryRepeatCnt = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity).getMaxDiscoveryRepeatNumber();
                        if (ContentDiscoverer.this.discoveryRepeatCnt_ >= ContentDiscoverer.this.maxDiscoveryRepeatCnt && discoveryRepeatInterval < 500) {
                        }
                    }
                }
            } catch (Exception unused4) {
            }
        }
    };
    /* access modifiers changed from: private */
    public Runnable readListRunnable = new Runnable() {
        public void run() {
            ContentDiscoverer.this.readContentRunnable.run();
        }
    };
    /* access modifiers changed from: private */
    public String referredUrl_;
    private ViewTreeObserver.OnScrollChangedListener scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
        public void onScrollChanged() {
            ContentDiscoverer.this.handler_.removeCallbacks(ContentDiscoverer.this.readListRunnable);
            if (ContentDiscoverer.this.maxDiscoveryRepeatCnt > ContentDiscoverer.this.discoveryRepeatCnt_) {
                ContentDiscoverer.this.handler_.postDelayed(ContentDiscoverer.this.readListRunnable, 1500);
            }
        }
    };
    private final Map<String, WeakReference<ViewTreeObserver>> viewTreeObserverMap = new HashMap();

    static /* synthetic */ int access$008(ContentDiscoverer contentDiscoverer) {
        int i = contentDiscoverer.discoveryRepeatCnt_;
        contentDiscoverer.discoveryRepeatCnt_ = i + 1;
        return i;
    }

    public static ContentDiscoverer getInstance() {
        if (thisInstance_ == null) {
            thisInstance_ = new ContentDiscoverer();
        }
        return thisInstance_;
    }

    private ContentDiscoverer() {
    }

    public void discoverContent(Activity activity, String str) {
        ContentDiscoveryManifest instance = ContentDiscoveryManifest.getInstance(activity);
        this.cdManifest_ = instance;
        this.referredUrl_ = str;
        ContentDiscoveryManifest.CDPathProperties cDPathProperties = instance.getCDPathProperties(activity);
        if (cDPathProperties != null) {
            if (!cDPathProperties.isSkipContentDiscovery()) {
                discoverContent(activity);
            }
        } else if (!TextUtils.isEmpty(this.referredUrl_)) {
            discoverContent(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        WeakReference<Activity> weakReference = this.lastActivityReference_;
        if (!(weakReference == null || weakReference.get() == null || !((Activity) this.lastActivityReference_.get()).getClass().getName().equals(activity.getClass().getName()))) {
            this.handler_.removeCallbacks(this.readContentRunnable);
            this.lastActivityReference_ = null;
        }
        updateLastViewTimeStampIfNeeded();
        for (WeakReference<ViewTreeObserver> weakReference2 : this.viewTreeObserverMap.values()) {
            ViewTreeObserver viewTreeObserver = (ViewTreeObserver) weakReference2.get();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnScrollChangedListener(this.scrollChangedListener);
            }
        }
        this.viewTreeObserverMap.clear();
    }

    public void onSessionStarted(Activity activity, String str) {
        this.discoveredViewList_ = new ArrayList<>();
        discoverContent(activity, str);
    }

    private void discoverContent(Activity activity) {
        this.discoveryRepeatCnt_ = 0;
        if (this.discoveredViewList_.size() < this.cdManifest_.getMaxViewHistorySize()) {
            this.handler_.removeCallbacks(this.readContentRunnable);
            this.lastActivityReference_ = new WeakReference<>(activity);
            this.handler_.postDelayed(this.readContentRunnable, 1000);
        }
    }

    private void updateLastViewTimeStampIfNeeded() {
        try {
            if (this.contentEvent_ != null) {
                this.contentEvent_.put(TIME_STAMP_CLOSE_KEY, System.currentTimeMillis());
            }
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void discoverContentKeys(ViewGroup viewGroup, JSONArray jSONArray, Resources resources) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0) {
                if ((childAt instanceof AbsListView) || childAt.getClass().getSimpleName().equals(RECYCLER_VIEW)) {
                    discoverListViewContentKeys((ViewGroup) childAt, resources, jSONArray);
                } else if (childAt instanceof ViewGroup) {
                    discoverContentKeys((ViewGroup) childAt, jSONArray, resources);
                } else if (childAt instanceof TextView) {
                    jSONArray.put(getViewName(childAt, resources));
                }
            }
        }
    }

    private void discoverListViewContentKeys(ViewGroup viewGroup, Resources resources, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        if (viewGroup != null && viewGroup.getChildCount() > -1) {
            int i = 1;
            if (viewGroup.getChildCount() <= 1) {
                i = 0;
            }
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    jSONObject.put(getViewName(viewGroup, resources), jSONArray2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (childAt instanceof ViewGroup) {
                    discoverContentKeys((ViewGroup) childAt, jSONArray2, resources);
                } else if (childAt instanceof TextView) {
                    jSONArray2.put(getViewName(childAt, resources));
                }
                if (jSONObject.length() > 0) {
                    jSONArray.put(COLLECTION_VIEW_KEY_PREFIX + jSONObject);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void discoverContentData(JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, Activity activity, boolean z) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                String string = jSONArray.getString(i);
                if (string.startsWith(COLLECTION_VIEW_KEY_PREFIX)) {
                    discoverListViewContentData(string, activity, z, jSONArray2, jSONArray3);
                } else {
                    updateElementData(string, activity.findViewById(activity.getResources().getIdentifier(jSONArray.getString(i), "id", activity.getPackageName())), z, jSONArray2, jSONArray3);
                }
                i++;
            } catch (JSONException unused) {
                return;
            }
        }
    }

    private void discoverListViewContentData(String str, Activity activity, boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        int i;
        String str2 = str;
        JSONObject jSONObject = new JSONObject();
        jSONArray2.put(str2);
        jSONArray.put(jSONObject);
        String replace = str2.replace(COLLECTION_VIEW_KEY_PREFIX, "");
        try {
            JSONObject jSONObject2 = new JSONObject(replace);
            if (jSONObject2.length() > 0) {
                String next = jSONObject2.keys().next();
                int identifier = activity.getResources().getIdentifier(next, "id", activity.getPackageName());
                View findViewById = activity.getCurrentFocus() != null ? activity.getCurrentFocus().findViewById(identifier) : null;
                if (findViewById == null) {
                    findViewById = activity.findViewById(identifier);
                } else {
                    Activity activity2 = activity;
                }
                if (findViewById != null && (findViewById instanceof ViewGroup)) {
                    ViewGroup viewGroup = (ViewGroup) findViewById;
                    JSONArray jSONArray3 = jSONObject2.getJSONArray(next);
                    int length = jSONArray3.length();
                    int[] iArr = new int[length];
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        iArr[i2] = activity.getResources().getIdentifier(jSONArray3.getString(i2), "id", activity.getPackageName());
                    }
                    int firstVisiblePosition = viewGroup instanceof AbsListView ? ((AbsListView) viewGroup).getFirstVisiblePosition() : 0;
                    int i3 = 0;
                    while (i3 < viewGroup.getChildCount()) {
                        if (viewGroup.getChildAt(i3) != null) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject.put("" + (i3 + firstVisiblePosition), jSONObject3);
                            int i4 = 0;
                            while (i4 < length) {
                                if (viewGroup.getChildAt(i3) != null) {
                                    View findViewById2 = viewGroup.getChildAt(i3).findViewById(iArr[i4]);
                                    if (findViewById2 instanceof TextView) {
                                        i = firstVisiblePosition;
                                        jSONObject3.put(jSONArray3.getString(i4), getTextViewValue(findViewById2, z));
                                        i4++;
                                        firstVisiblePosition = i;
                                    }
                                }
                                i = firstVisiblePosition;
                                boolean z2 = z;
                                i4++;
                                firstVisiblePosition = i;
                            }
                        }
                        int i5 = firstVisiblePosition;
                        boolean z3 = z;
                        i3++;
                        firstVisiblePosition = i5;
                    }
                    if ((jSONObject2.has(ENABLE_SCROLL_WATCH) && jSONObject2.getBoolean(ENABLE_SCROLL_WATCH)) && !this.viewTreeObserverMap.containsKey(replace)) {
                        viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
                        this.viewTreeObserverMap.put(replace, new WeakReference(viewGroup.getViewTreeObserver()));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getViewName(View view, Resources resources) {
        try {
            return resources.getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return String.valueOf(view.getId());
        }
    }

    private String getTextViewValue(View view, boolean z) {
        TextView textView = (TextView) view;
        if (textView.getText() == null) {
            return null;
        }
        String substring = textView.getText().toString().substring(0, Math.min(textView.getText().toString().length(), this.cdManifest_.getMaxTextLen()));
        return z ? substring : this.hashHelper_.hashContent(substring);
    }

    private void updateElementData(String str, View view, boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        if (view instanceof TextView) {
            jSONArray.put(getTextViewValue(view, z));
            jSONArray2.put(str);
        }
    }

    public JSONObject getContentDiscoverDataForCloseRequest(Context context) {
        JSONObject jSONObject;
        JSONObject branchAnalyticsData = PrefHelper.getInstance(context).getBranchAnalyticsData();
        if (branchAnalyticsData.length() <= 0 || branchAnalyticsData.toString().length() >= this.cdManifest_.getMaxPacketSize()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject();
            try {
                jSONObject.put(ContentDiscoveryManifest.MANIFEST_VERSION_KEY, ContentDiscoveryManifest.getInstance(context).getManifestVersion()).put(ENTITIES_KEY, branchAnalyticsData);
                if (context != null) {
                    jSONObject.put("p", context.getPackageName());
                    jSONObject.put("p", context.getPackageName());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        PrefHelper.getInstance(context).clearBranchAnalyticsData();
        return jSONObject;
    }

    private class HashHelper {
        MessageDigest messageDigest_;

        HashHelper() {
            try {
                this.messageDigest_ = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException unused) {
            }
        }

        /* access modifiers changed from: package-private */
        public String hashContent(String str) {
            MessageDigest messageDigest = this.messageDigest_;
            if (messageDigest == null) {
                return "";
            }
            messageDigest.reset();
            this.messageDigest_.update(str.getBytes());
            return new String(this.messageDigest_.digest());
        }
    }
}
