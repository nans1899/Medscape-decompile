package com.facebook.appevents.codeless.internal;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.core.view.NestedScrollingChild;
import com.facebook.internal.Utility;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewHierarchy {
    private static final int ADAPTER_VIEW_ITEM_BITMASK = 9;
    private static final int BUTTON_BITMASK = 2;
    private static final int CHECKBOX_BITMASK = 15;
    private static final String CHILDREN_VIEW_KEY = "childviews";
    private static final String CLASS_NAME_KEY = "classname";
    private static final String CLASS_RCTROOTVIEW = "com.facebook.react.ReactRootView";
    private static final String CLASS_RCTTEXTVIEW = "com.facebook.react.views.view.ReactTextView";
    private static final String CLASS_RCTVIEWGROUP = "com.facebook.react.views.view.ReactViewGroup";
    private static final String CLASS_TOUCHTARGETHELPER = "com.facebook.react.uimanager.TouchTargetHelper";
    private static final String CLASS_TYPE_BITMASK_KEY = "classtypebitmask";
    private static final int CLICKABLE_VIEW_BITMASK = 5;
    private static final String DESC_KEY = "description";
    private static final String DIMENSION_HEIGHT_KEY = "height";
    private static final String DIMENSION_KEY = "dimension";
    private static final String DIMENSION_LEFT_KEY = "left";
    private static final String DIMENSION_SCROLL_X_KEY = "scrollx";
    private static final String DIMENSION_SCROLL_Y_KEY = "scrolly";
    private static final String DIMENSION_TOP_KEY = "top";
    private static final String DIMENSION_VISIBILITY_KEY = "visibility";
    private static final String DIMENSION_WIDTH_KEY = "width";
    private static final String GET_ACCESSIBILITY_METHOD = "getAccessibilityDelegate";
    private static final String HINT_KEY = "hint";
    private static final String ICON_BITMAP = "icon_image";
    private static final int ICON_MAX_EDGE_LENGTH = 44;
    private static final String ID_KEY = "id";
    private static final int IMAGEVIEW_BITMASK = 1;
    private static final int INPUT_BITMASK = 11;
    private static final String IS_USER_INPUT_KEY = "is_user_input";
    private static final int LABEL_BITMASK = 10;
    private static final String METHOD_FIND_TOUCHTARGET_VIEW = "findTouchTargetView";
    private static final int PICKER_BITMASK = 12;
    private static final int RADIO_GROUP_BITMASK = 14;
    private static final int RATINGBAR_BITMASK = 16;
    private static WeakReference<View> RCTRootViewReference = new WeakReference<>((Object) null);
    private static final int REACT_NATIVE_BUTTON_BITMASK = 6;
    private static final int SWITCH_BITMASK = 13;
    private static final String TAG = ViewHierarchy.class.getCanonicalName();
    private static final String TAG_KEY = "tag";
    private static final int TEXTVIEW_BITMASK = 0;
    private static final String TEXT_IS_BOLD = "is_bold";
    private static final String TEXT_IS_ITALIC = "is_italic";
    private static final String TEXT_KEY = "text";
    private static final String TEXT_SIZE = "font_size";
    private static final String TEXT_STYLE = "text_style";
    private static Method methodFindTouchTargetView = null;

    public static ViewGroup getParentOfView(View view) {
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            return (ViewGroup) parent;
        }
        return null;
    }

    public static List<View> getChildrenOfView(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(viewGroup.getChildAt(i));
            }
        }
        return arrayList;
    }

    public static JSONObject setBasicInfoOfView(View view, JSONObject jSONObject) {
        try {
            String textOfView = getTextOfView(view);
            String hintOfView = getHintOfView(view);
            Object tag = view.getTag();
            CharSequence contentDescription = view.getContentDescription();
            jSONObject.put(CLASS_NAME_KEY, view.getClass().getCanonicalName());
            jSONObject.put(CLASS_TYPE_BITMASK_KEY, getClassTypeBitmask(view));
            jSONObject.put("id", view.getId());
            if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                jSONObject.put(TEXT_KEY, Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(textOfView), ""));
            } else {
                jSONObject.put(TEXT_KEY, "");
                jSONObject.put(IS_USER_INPUT_KEY, true);
            }
            jSONObject.put(HINT_KEY, Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(hintOfView), ""));
            if (tag != null) {
                jSONObject.put("tag", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(tag.toString()), ""));
            }
            if (contentDescription != null) {
                jSONObject.put("description", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(contentDescription.toString()), ""));
            }
            jSONObject.put(DIMENSION_KEY, getDimensionOfView(view));
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r1 = (android.widget.TextView) r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject setAppearanceOfView(android.view.View r6, org.json.JSONObject r7, float r8) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x007c }
            r0.<init>()     // Catch:{ JSONException -> 0x007c }
            boolean r1 = r6 instanceof android.widget.TextView     // Catch:{ JSONException -> 0x007c }
            if (r1 == 0) goto L_0x0034
            r1 = r6
            android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ JSONException -> 0x007c }
            android.graphics.Typeface r2 = r1.getTypeface()     // Catch:{ JSONException -> 0x007c }
            if (r2 == 0) goto L_0x0034
            java.lang.String r3 = "font_size"
            float r1 = r1.getTextSize()     // Catch:{ JSONException -> 0x007c }
            double r4 = (double) r1     // Catch:{ JSONException -> 0x007c }
            r0.put(r3, r4)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r1 = "is_bold"
            boolean r3 = r2.isBold()     // Catch:{ JSONException -> 0x007c }
            r0.put(r1, r3)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r1 = "is_italic"
            boolean r2 = r2.isItalic()     // Catch:{ JSONException -> 0x007c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r1 = "text_style"
            r7.put(r1, r0)     // Catch:{ JSONException -> 0x007c }
        L_0x0034:
            boolean r0 = r6 instanceof android.widget.ImageView     // Catch:{ JSONException -> 0x007c }
            if (r0 == 0) goto L_0x0082
            r0 = r6
            android.widget.ImageView r0 = (android.widget.ImageView) r0     // Catch:{ JSONException -> 0x007c }
            android.graphics.drawable.Drawable r0 = r0.getDrawable()     // Catch:{ JSONException -> 0x007c }
            boolean r1 = r0 instanceof android.graphics.drawable.BitmapDrawable     // Catch:{ JSONException -> 0x007c }
            if (r1 == 0) goto L_0x0082
            int r1 = r6.getHeight()     // Catch:{ JSONException -> 0x007c }
            float r1 = (float) r1     // Catch:{ JSONException -> 0x007c }
            float r1 = r1 / r8
            r2 = 1110441984(0x42300000, float:44.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x0082
            int r6 = r6.getWidth()     // Catch:{ JSONException -> 0x007c }
            float r6 = (float) r6     // Catch:{ JSONException -> 0x007c }
            float r6 = r6 / r8
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 > 0) goto L_0x0082
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0     // Catch:{ JSONException -> 0x007c }
            android.graphics.Bitmap r6 = r0.getBitmap()     // Catch:{ JSONException -> 0x007c }
            if (r6 == 0) goto L_0x0082
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream     // Catch:{ JSONException -> 0x007c }
            r8.<init>()     // Catch:{ JSONException -> 0x007c }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ JSONException -> 0x007c }
            r1 = 100
            r6.compress(r0, r1, r8)     // Catch:{ JSONException -> 0x007c }
            byte[] r6 = r8.toByteArray()     // Catch:{ JSONException -> 0x007c }
            r8 = 0
            java.lang.String r6 = android.util.Base64.encodeToString(r6, r8)     // Catch:{ JSONException -> 0x007c }
            java.lang.String r8 = "icon_image"
            r7.put(r8, r6)     // Catch:{ JSONException -> 0x007c }
            goto L_0x0082
        L_0x007c:
            r6 = move-exception
            java.lang.String r8 = TAG
            com.facebook.internal.Utility.logd((java.lang.String) r8, (java.lang.Exception) r6)
        L_0x0082:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.setAppearanceOfView(android.view.View, org.json.JSONObject, float):org.json.JSONObject");
    }

    public static JSONObject getDictionaryOfView(View view) {
        if (view.getClass().getName().equals(CLASS_RCTROOTVIEW)) {
            RCTRootViewReference = new WeakReference<>(view);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = setBasicInfoOfView(view, jSONObject);
            JSONArray jSONArray = new JSONArray();
            List<View> childrenOfView = getChildrenOfView(view);
            for (int i = 0; i < childrenOfView.size(); i++) {
                jSONArray.put(getDictionaryOfView(childrenOfView.get(i)));
            }
            jSONObject.put(CHILDREN_VIEW_KEY, jSONArray);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for view.", e);
        }
        return jSONObject;
    }

    private static int getClassTypeBitmask(View view) {
        int i = view instanceof ImageView ? 2 : 0;
        if (isClickableView(view)) {
            i |= 32;
        }
        if (isAdapterViewItem(view)) {
            i |= 512;
        }
        if (view instanceof TextView) {
            int i2 = i | 1024 | 1;
            if (view instanceof Button) {
                i2 |= 4;
                if (view instanceof Switch) {
                    i2 |= 8192;
                } else if (view instanceof CheckBox) {
                    i2 |= 32768;
                }
            }
            if (view instanceof EditText) {
                return i2 | 2048;
            }
            return i2;
        } else if ((view instanceof Spinner) || (view instanceof DatePicker)) {
            return i | 4096;
        } else {
            if (view instanceof RatingBar) {
                return i | 65536;
            }
            if (view instanceof RadioGroup) {
                return i | 16384;
            }
            return (!(view instanceof ViewGroup) || !isRCTButton(view, (View) RCTRootViewReference.get())) ? i : i | 64;
        }
    }

    public static boolean isClickableView(View view) {
        Field declaredField;
        try {
            Field declaredField2 = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField2 != null) {
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(view);
                if (obj == null || (declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener")) == null || ((View.OnClickListener) declaredField.get(obj)) == null) {
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to check if the view is clickable.", e);
        }
        return false;
    }

    private static boolean isAdapterViewItem(View view) {
        ViewParent parent = view.getParent();
        return (parent instanceof AdapterView) || (parent instanceof NestedScrollingChild);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cd A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getTextOfView(android.view.View r6) {
        /*
            boolean r0 = r6 instanceof android.widget.TextView
            r1 = 0
            if (r0 == 0) goto L_0x0020
            r0 = r6
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.CharSequence r1 = r0.getText()
            boolean r0 = r6 instanceof android.widget.Switch
            if (r0 == 0) goto L_0x00cb
            android.widget.Switch r6 = (android.widget.Switch) r6
            boolean r6 = r6.isChecked()
            if (r6 == 0) goto L_0x001b
            java.lang.String r6 = "1"
            goto L_0x001d
        L_0x001b:
            java.lang.String r6 = "0"
        L_0x001d:
            r1 = r6
            goto L_0x00cb
        L_0x0020:
            boolean r0 = r6 instanceof android.widget.Spinner
            if (r0 == 0) goto L_0x0038
            android.widget.Spinner r6 = (android.widget.Spinner) r6
            int r0 = r6.getCount()
            if (r0 <= 0) goto L_0x00cb
            java.lang.Object r6 = r6.getSelectedItem()
            if (r6 == 0) goto L_0x00cb
            java.lang.String r1 = r6.toString()
            goto L_0x00cb
        L_0x0038:
            boolean r0 = r6 instanceof android.widget.DatePicker
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0069
            android.widget.DatePicker r6 = (android.widget.DatePicker) r6
            int r0 = r6.getYear()
            int r1 = r6.getMonth()
            int r6 = r6.getDayOfMonth()
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r4] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r5[r3] = r0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5[r2] = r6
            java.lang.String r6 = "%04d-%02d-%02d"
            java.lang.String r1 = java.lang.String.format(r6, r5)
            goto L_0x00cb
        L_0x0069:
            boolean r0 = r6 instanceof android.widget.TimePicker
            if (r0 == 0) goto L_0x0094
            android.widget.TimePicker r6 = (android.widget.TimePicker) r6
            java.lang.Integer r0 = r6.getCurrentHour()
            int r0 = r0.intValue()
            java.lang.Integer r6 = r6.getCurrentMinute()
            int r6 = r6.intValue()
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1[r4] = r0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r1[r3] = r6
            java.lang.String r6 = "%02d:%02d"
            java.lang.String r1 = java.lang.String.format(r6, r1)
            goto L_0x00cb
        L_0x0094:
            boolean r0 = r6 instanceof android.widget.RadioGroup
            if (r0 == 0) goto L_0x00bd
            android.widget.RadioGroup r6 = (android.widget.RadioGroup) r6
            int r0 = r6.getCheckedRadioButtonId()
            int r2 = r6.getChildCount()
        L_0x00a2:
            if (r4 >= r2) goto L_0x00cb
            android.view.View r3 = r6.getChildAt(r4)
            int r5 = r3.getId()
            if (r5 != r0) goto L_0x00ba
            boolean r5 = r3 instanceof android.widget.RadioButton
            if (r5 == 0) goto L_0x00ba
            android.widget.RadioButton r3 = (android.widget.RadioButton) r3
            java.lang.CharSequence r6 = r3.getText()
            goto L_0x001d
        L_0x00ba:
            int r4 = r4 + 1
            goto L_0x00a2
        L_0x00bd:
            boolean r0 = r6 instanceof android.widget.RatingBar
            if (r0 == 0) goto L_0x00cb
            android.widget.RatingBar r6 = (android.widget.RatingBar) r6
            float r6 = r6.getRating()
            java.lang.String r1 = java.lang.String.valueOf(r6)
        L_0x00cb:
            if (r1 != 0) goto L_0x00d0
            java.lang.String r6 = ""
            goto L_0x00d4
        L_0x00d0:
            java.lang.String r6 = r1.toString()
        L_0x00d4:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(android.view.View):java.lang.String");
    }

    public static String getHintOfView(View view) {
        CharSequence charSequence;
        if (view instanceof EditText) {
            charSequence = ((EditText) view).getHint();
        } else {
            charSequence = view instanceof TextView ? ((TextView) view).getHint() : null;
        }
        if (charSequence == null) {
            return "";
        }
        return charSequence.toString();
    }

    private static JSONObject getDimensionOfView(View view) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("top", view.getTop());
            jSONObject.put(DIMENSION_LEFT_KEY, view.getLeft());
            jSONObject.put("width", view.getWidth());
            jSONObject.put("height", view.getHeight());
            jSONObject.put(DIMENSION_SCROLL_X_KEY, view.getScrollX());
            jSONObject.put(DIMENSION_SCROLL_Y_KEY, view.getScrollY());
            jSONObject.put(DIMENSION_VISIBILITY_KEY, view.getVisibility());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for dimension.", e);
        }
        return jSONObject;
    }

    public static View.AccessibilityDelegate getExistingDelegate(View view) {
        try {
            return (View.AccessibilityDelegate) view.getClass().getMethod(GET_ACCESSIBILITY_METHOD, new Class[0]).invoke(view, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | NullPointerException | SecurityException | InvocationTargetException unused) {
            return null;
        }
    }

    public static View.OnTouchListener getExistingOnTouchListener(View view) {
        Field declaredField;
        try {
            Field declaredField2 = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField2 != null) {
                declaredField2.setAccessible(true);
            }
            Object obj = declaredField2.get(view);
            if (obj == null || (declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener")) == null) {
                return null;
            }
            declaredField.setAccessible(true);
            return (View.OnTouchListener) declaredField.get(obj);
        } catch (NoSuchFieldException e) {
            Utility.logd(TAG, (Exception) e);
            return null;
        } catch (ClassNotFoundException e2) {
            Utility.logd(TAG, (Exception) e2);
            return null;
        } catch (IllegalAccessException e3) {
            Utility.logd(TAG, (Exception) e3);
            return null;
        }
    }

    public static View getTouchReactView(float[] fArr, View view) {
        View view2;
        initTouchTargetHelperMethods();
        Method method = methodFindTouchTargetView;
        if (!(method == null || view == null)) {
            try {
                View view3 = (View) method.invoke((Object) null, new Object[]{fArr, view});
                if (view3 == null || view3.getId() <= 0 || (view2 = (View) view3.getParent()) == null) {
                    return null;
                }
                return view2;
            } catch (IllegalAccessException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (InvocationTargetException e2) {
                Utility.logd(TAG, (Exception) e2);
            }
        }
        return null;
    }

    public static boolean isRCTButton(View view, View view2) {
        View touchReactView;
        if (!view.getClass().getName().equals(CLASS_RCTVIEWGROUP) || (touchReactView = getTouchReactView(getViewLocationOnScreen(view), view2)) == null || touchReactView.getId() != view.getId()) {
            return false;
        }
        return true;
    }

    public static boolean isRCTRootView(View view) {
        return view.getClass().getName().equals(CLASS_RCTROOTVIEW);
    }

    public static boolean isRCTTextView(View view) {
        return view.getClass().getName().equals(CLASS_RCTTEXTVIEW);
    }

    public static boolean isRCTViewGroup(View view) {
        return view.getClass().getName().equals(CLASS_RCTVIEWGROUP);
    }

    public static View findRCTRootView(View view) {
        while (view != null) {
            if (isRCTRootView(view)) {
                return view;
            }
            ViewParent parent = view.getParent();
            if (!(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
        return null;
    }

    private static float[] getViewLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new float[]{(float) iArr[0], (float) iArr[1]};
    }

    private static void initTouchTargetHelperMethods() {
        if (methodFindTouchTargetView == null) {
            try {
                Method declaredMethod = Class.forName(CLASS_TOUCHTARGETHELPER).getDeclaredMethod(METHOD_FIND_TOUCHTARGET_VIEW, new Class[]{float[].class, ViewGroup.class});
                methodFindTouchTargetView = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (ClassNotFoundException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, (Exception) e2);
            }
        }
    }
}
