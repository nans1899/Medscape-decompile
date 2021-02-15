package com.facebook.appevents.codeless;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.RCTCodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ParameterComponent;
import com.facebook.appevents.codeless.internal.PathComponent;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.InternalSettings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CodelessMatcher {
    private static final String CURRENT_CLASS_NAME = ".";
    private static final String PARENT_CLASS_NAME = "..";
    /* access modifiers changed from: private */
    public static final String TAG = CodelessMatcher.class.getCanonicalName();
    private Set<Activity> activitiesSet = new HashSet();
    private HashMap<String, String> delegateMap = new HashMap<>();
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private Set<ViewMatcher> viewMatchers = new HashSet();

    public void add(Activity activity) {
        if (!InternalSettings.isUnityApp()) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                this.activitiesSet.add(activity);
                this.delegateMap.clear();
                startTracking();
                return;
            }
            throw new FacebookException("Can't add activity to CodelessMatcher on non-UI thread");
        }
    }

    public void remove(Activity activity) {
        if (!InternalSettings.isUnityApp()) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                this.activitiesSet.remove(activity);
                this.viewMatchers.clear();
                this.delegateMap.clear();
                return;
            }
            throw new FacebookException("Can't remove activity from CodelessMatcher on non-UI thread");
        }
    }

    public static Bundle getParameters(EventBinding eventBinding, View view, View view2) {
        List<ParameterComponent> viewParameters;
        List<MatchedView> list;
        Bundle bundle = new Bundle();
        if (!(eventBinding == null || (viewParameters = eventBinding.getViewParameters()) == null)) {
            for (ParameterComponent next : viewParameters) {
                if (next.value == null || next.value.length() <= 0) {
                    if (next.path.size() > 0) {
                        if (next.pathType.equals(Constants.PATH_TYPE_RELATIVE)) {
                            list = ViewMatcher.findViewByPath(eventBinding, view2, next.path, 0, -1, view2.getClass().getSimpleName());
                        } else {
                            list = ViewMatcher.findViewByPath(eventBinding, view, next.path, 0, -1, view.getClass().getSimpleName());
                        }
                        Iterator<MatchedView> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            MatchedView next2 = it.next();
                            if (next2.getView() != null) {
                                String textOfView = ViewHierarchy.getTextOfView(next2.getView());
                                if (textOfView.length() > 0) {
                                    bundle.putString(next.name, textOfView);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    bundle.putString(next.name, next.value);
                }
            }
        }
        return bundle;
    }

    private void startTracking() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            matchViews();
        } else {
            this.uiThreadHandler.post(new Runnable() {
                public void run() {
                    CodelessMatcher.this.matchViews();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void matchViews() {
        for (Activity next : this.activitiesSet) {
            this.viewMatchers.add(new ViewMatcher(next.getWindow().getDecorView().getRootView(), this.uiThreadHandler, this.delegateMap, next.getClass().getSimpleName()));
        }
    }

    public static class MatchedView {
        private WeakReference<View> view;
        private String viewMapKey;

        public MatchedView(View view2, String str) {
            this.view = new WeakReference<>(view2);
            this.viewMapKey = str;
        }

        public View getView() {
            WeakReference<View> weakReference = this.view;
            if (weakReference == null) {
                return null;
            }
            return (View) weakReference.get();
        }

        public String getViewMapKey() {
            return this.viewMapKey;
        }
    }

    protected static class ViewMatcher implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        private final String activityName;
        private HashMap<String, String> delegateMap;
        private List<EventBinding> eventBindings;
        private final Handler handler;
        private WeakReference<View> rootView;

        public ViewMatcher(View view, Handler handler2, HashMap<String, String> hashMap, String str) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler2;
            this.delegateMap = hashMap;
            this.activityName = str;
            handler2.postDelayed(this, 200);
        }

        public void run() {
            View view;
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                List<EventBinding> parseArray = EventBinding.parseArray(appSettingsWithoutQuery.getEventBindings());
                this.eventBindings = parseArray;
                if (parseArray != null && (view = (View) this.rootView.get()) != null) {
                    ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.addOnGlobalLayoutListener(this);
                        viewTreeObserver.addOnScrollChangedListener(this);
                    }
                    startMatch();
                }
            }
        }

        public void onGlobalLayout() {
            startMatch();
        }

        public void onScrollChanged() {
            startMatch();
        }

        private void startMatch() {
            if (this.eventBindings != null && this.rootView.get() != null) {
                for (int i = 0; i < this.eventBindings.size(); i++) {
                    findView(this.eventBindings.get(i), (View) this.rootView.get());
                }
            }
        }

        public void findView(EventBinding eventBinding, View view) {
            if (eventBinding != null && view != null) {
                if (TextUtils.isEmpty(eventBinding.getActivityName()) || eventBinding.getActivityName().equals(this.activityName)) {
                    List<PathComponent> viewPath = eventBinding.getViewPath();
                    if (viewPath.size() <= 25) {
                        for (MatchedView attachListener : findViewByPath(eventBinding, view, viewPath, 0, -1, this.activityName)) {
                            attachListener(attachListener, view, eventBinding);
                        }
                    }
                }
            }
        }

        public static List<MatchedView> findViewByPath(EventBinding eventBinding, View view, List<PathComponent> list, int i, int i2, String str) {
            String str2 = str + CodelessMatcher.CURRENT_CLASS_NAME + String.valueOf(i2);
            ArrayList arrayList = new ArrayList();
            if (view == null) {
                return arrayList;
            }
            if (i >= list.size()) {
                arrayList.add(new MatchedView(view, str2));
            } else {
                PathComponent pathComponent = list.get(i);
                if (pathComponent.className.equals(CodelessMatcher.PARENT_CLASS_NAME)) {
                    ViewParent parent = view.getParent();
                    if (parent instanceof ViewGroup) {
                        List<View> findVisibleChildren = findVisibleChildren((ViewGroup) parent);
                        int size = findVisibleChildren.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            arrayList.addAll(findViewByPath(eventBinding, findVisibleChildren.get(i3), list, i + 1, i3, str2));
                        }
                    }
                    return arrayList;
                } else if (pathComponent.className.equals(CodelessMatcher.CURRENT_CLASS_NAME)) {
                    arrayList.add(new MatchedView(view, str2));
                    return arrayList;
                } else if (!isTheSameView(view, pathComponent, i2)) {
                    return arrayList;
                } else {
                    if (i == list.size() - 1) {
                        arrayList.add(new MatchedView(view, str2));
                    }
                }
            }
            if (view instanceof ViewGroup) {
                List<View> findVisibleChildren2 = findVisibleChildren((ViewGroup) view);
                int size2 = findVisibleChildren2.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    arrayList.addAll(findViewByPath(eventBinding, findVisibleChildren2.get(i4), list, i + 1, i4, str2));
                }
            }
            return arrayList;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
            if (r5.getClass().getSimpleName().equals(r7[r7.length - 1]) == false) goto L_0x0043;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean isTheSameView(android.view.View r5, com.facebook.appevents.codeless.internal.PathComponent r6, int r7) {
            /*
                int r0 = r6.index
                r1 = 0
                r2 = -1
                if (r0 == r2) goto L_0x000b
                int r0 = r6.index
                if (r7 == r0) goto L_0x000b
                return r1
            L_0x000b:
                java.lang.Class r7 = r5.getClass()
                java.lang.String r7 = r7.getCanonicalName()
                java.lang.String r0 = r6.className
                boolean r7 = r7.equals(r0)
                r0 = 1
                if (r7 != 0) goto L_0x0044
                java.lang.String r7 = r6.className
                java.lang.String r2 = ".*android\\..*"
                boolean r7 = r7.matches(r2)
                if (r7 == 0) goto L_0x0043
                java.lang.String r7 = r6.className
                java.lang.String r2 = "\\."
                java.lang.String[] r7 = r7.split(r2)
                int r2 = r7.length
                if (r2 <= 0) goto L_0x0043
                int r2 = r7.length
                int r2 = r2 - r0
                r7 = r7[r2]
                java.lang.Class r2 = r5.getClass()
                java.lang.String r2 = r2.getSimpleName()
                boolean r7 = r2.equals(r7)
                if (r7 != 0) goto L_0x0044
            L_0x0043:
                return r1
            L_0x0044:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.ID
                int r2 = r2.getValue()
                r7 = r7 & r2
                if (r7 <= 0) goto L_0x0058
                int r7 = r6.id
                int r2 = r5.getId()
                if (r7 == r2) goto L_0x0058
                return r1
            L_0x0058:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r2 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TEXT
                int r2 = r2.getValue()
                r7 = r7 & r2
                java.lang.String r2 = ""
                if (r7 <= 0) goto L_0x0080
                java.lang.String r7 = r6.text
                java.lang.String r3 = com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(r5)
                java.lang.String r4 = com.facebook.internal.Utility.sha256hash((java.lang.String) r3)
                java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r2)
                boolean r3 = r7.equals(r3)
                if (r3 != 0) goto L_0x0080
                boolean r7 = r7.equals(r4)
                if (r7 != 0) goto L_0x0080
                return r1
            L_0x0080:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.DESCRIPTION
                int r3 = r3.getValue()
                r7 = r7 & r3
                if (r7 <= 0) goto L_0x00b2
                java.lang.String r7 = r6.description
                java.lang.CharSequence r3 = r5.getContentDescription()
                if (r3 != 0) goto L_0x0095
                r3 = r2
                goto L_0x009d
            L_0x0095:
                java.lang.CharSequence r3 = r5.getContentDescription()
                java.lang.String r3 = java.lang.String.valueOf(r3)
            L_0x009d:
                java.lang.String r4 = com.facebook.internal.Utility.sha256hash((java.lang.String) r3)
                java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r2)
                boolean r3 = r7.equals(r3)
                if (r3 != 0) goto L_0x00b2
                boolean r7 = r7.equals(r4)
                if (r7 != 0) goto L_0x00b2
                return r1
            L_0x00b2:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.HINT
                int r3 = r3.getValue()
                r7 = r7 & r3
                if (r7 <= 0) goto L_0x00d8
                java.lang.String r7 = r6.hint
                java.lang.String r3 = com.facebook.appevents.codeless.internal.ViewHierarchy.getHintOfView(r5)
                java.lang.String r4 = com.facebook.internal.Utility.sha256hash((java.lang.String) r3)
                java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r2)
                boolean r3 = r7.equals(r3)
                if (r3 != 0) goto L_0x00d8
                boolean r7 = r7.equals(r4)
                if (r7 != 0) goto L_0x00d8
                return r1
            L_0x00d8:
                int r7 = r6.matchBitmask
                com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TAG
                int r3 = r3.getValue()
                r7 = r7 & r3
                if (r7 <= 0) goto L_0x010a
                java.lang.String r6 = r6.tag
                java.lang.Object r7 = r5.getTag()
                if (r7 != 0) goto L_0x00ed
                r5 = r2
                goto L_0x00f5
            L_0x00ed:
                java.lang.Object r5 = r5.getTag()
                java.lang.String r5 = java.lang.String.valueOf(r5)
            L_0x00f5:
                java.lang.String r7 = com.facebook.internal.Utility.sha256hash((java.lang.String) r5)
                java.lang.String r7 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r7, r2)
                boolean r5 = r6.equals(r5)
                if (r5 != 0) goto L_0x010a
                boolean r5 = r6.equals(r7)
                if (r5 != 0) goto L_0x010a
                return r1
            L_0x010a:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.CodelessMatcher.ViewMatcher.isTheSameView(android.view.View, com.facebook.appevents.codeless.internal.PathComponent, int):boolean");
        }

        private static List<View> findVisibleChildren(ViewGroup viewGroup) {
            ArrayList arrayList = new ArrayList();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    arrayList.add(childAt);
                }
            }
            return arrayList;
        }

        private void attachListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            if (eventBinding != null) {
                try {
                    View view2 = matchedView.getView();
                    if (view2 != null) {
                        View findRCTRootView = ViewHierarchy.findRCTRootView(view2);
                        if (findRCTRootView != null && ViewHierarchy.isRCTButton(view2, findRCTRootView)) {
                            attachRCTListener(matchedView, view, findRCTRootView, eventBinding);
                        } else if (!view2.getClass().getName().startsWith("com.facebook.react")) {
                            String viewMapKey = matchedView.getViewMapKey();
                            View.AccessibilityDelegate existingDelegate = ViewHierarchy.getExistingDelegate(view2);
                            boolean z = true;
                            boolean z2 = existingDelegate != null;
                            boolean z3 = z2 && (existingDelegate instanceof CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate);
                            if (!z3 || !((CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate) existingDelegate).getSupportCodelessLogging()) {
                                z = false;
                            }
                            if (this.delegateMap.containsKey(viewMapKey)) {
                                return;
                            }
                            if (!z2 || !z3 || !z) {
                                view2.setAccessibilityDelegate(CodelessLoggingEventListener.getAccessibilityDelegate(eventBinding, view, view2));
                                this.delegateMap.put(viewMapKey, eventBinding.getEventName());
                            }
                        }
                    }
                } catch (FacebookException e) {
                    Log.e(CodelessMatcher.TAG, "Failed to attach auto logging event listener.", e);
                }
            }
        }

        private void attachRCTListener(MatchedView matchedView, View view, View view2, EventBinding eventBinding) {
            View view3;
            if (eventBinding != null && (view3 = matchedView.getView()) != null && ViewHierarchy.isRCTButton(view3, view2)) {
                String viewMapKey = matchedView.getViewMapKey();
                View.OnTouchListener existingOnTouchListener = ViewHierarchy.getExistingOnTouchListener(view3);
                boolean z = true;
                boolean z2 = existingOnTouchListener != null;
                boolean z3 = z2 && (existingOnTouchListener instanceof RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener);
                if (!z3 || !((RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener) existingOnTouchListener).getSupportCodelessLogging()) {
                    z = false;
                }
                if (this.delegateMap.containsKey(viewMapKey)) {
                    return;
                }
                if (!z2 || !z3 || !z) {
                    view3.setOnTouchListener(RCTCodelessLoggingEventListener.getOnTouchListener(eventBinding, view, view3));
                    this.delegateMap.put(viewMapKey, eventBinding.getEventName());
                }
            }
        }
    }
}
