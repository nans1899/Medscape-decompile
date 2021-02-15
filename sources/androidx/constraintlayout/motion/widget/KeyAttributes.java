package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.HashSet;

public class KeyAttributes extends Key {
    public static final int KEY_TYPE = 1;
    static final String NAME = "KeyAttribute";
    private static final String TAG = "KeyAttribute";
    /* access modifiers changed from: private */
    public float mAlpha = Float.NaN;
    /* access modifiers changed from: private */
    public int mCurveFit = -1;
    /* access modifiers changed from: private */
    public float mElevation = Float.NaN;
    /* access modifiers changed from: private */
    public float mPivotX = Float.NaN;
    /* access modifiers changed from: private */
    public float mPivotY = Float.NaN;
    /* access modifiers changed from: private */
    public float mProgress = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotation = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotationX = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotationY = Float.NaN;
    /* access modifiers changed from: private */
    public float mScaleX = Float.NaN;
    /* access modifiers changed from: private */
    public float mScaleY = Float.NaN;
    /* access modifiers changed from: private */
    public String mTransitionEasing;
    /* access modifiers changed from: private */
    public float mTransitionPathRotate = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationX = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationY = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationZ = Float.NaN;
    private boolean mVisibility = false;

    public KeyAttributes() {
        this.mType = 1;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyAttribute));
    }

    /* access modifiers changed from: package-private */
    public int getCurveFit() {
        return this.mCurveFit;
    }

    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            hashSet.add(NotificationCompat.CATEGORY_PROGRESS);
        }
        if (this.mCustomConstraints.size() > 0) {
            for (String str : this.mCustomConstraints.keySet()) {
                hashSet.add("CUSTOM," + str);
            }
        }
    }

    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.mCurveFit != -1) {
            if (!Float.isNaN(this.mAlpha)) {
                hashMap.put("alpha", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mElevation)) {
                hashMap.put("elevation", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotation)) {
                hashMap.put("rotation", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationX)) {
                hashMap.put("rotationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationY)) {
                hashMap.put("rotationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mPivotX)) {
                hashMap.put("transformPivotX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mPivotY)) {
                hashMap.put("transformPivotY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationX)) {
                hashMap.put("translationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationY)) {
                hashMap.put("translationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationZ)) {
                hashMap.put("translationZ", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTransitionPathRotate)) {
                hashMap.put("transitionPathRotate", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleX)) {
                hashMap.put("scaleX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleY)) {
                hashMap.put("scaleY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mProgress)) {
                hashMap.put(NotificationCompat.CATEGORY_PROGRESS, Integer.valueOf(this.mCurveFit));
            }
            if (this.mCustomConstraints.size() > 0) {
                for (String str : this.mCustomConstraints.keySet()) {
                    hashMap.put("CUSTOM," + str, Integer.valueOf(this.mCurveFit));
                }
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0055, code lost:
        if (r1.equals("transitionPathRotate") != false) goto L_0x00df;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.widget.SplineSet> r7) {
        /*
            r6 = this;
            java.util.Set r0 = r7.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01ed
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r7.get(r1)
            androidx.constraintlayout.motion.widget.SplineSet r2 = (androidx.constraintlayout.motion.widget.SplineSet) r2
            java.lang.String r3 = "CUSTOM"
            boolean r3 = r1.startsWith(r3)
            r4 = 7
            if (r3 == 0) goto L_0x0039
            java.lang.String r1 = r1.substring(r4)
            java.util.HashMap r3 = r6.mCustomConstraints
            java.lang.Object r1 = r3.get(r1)
            androidx.constraintlayout.widget.ConstraintAttribute r1 = (androidx.constraintlayout.widget.ConstraintAttribute) r1
            if (r1 == 0) goto L_0x0008
            androidx.constraintlayout.motion.widget.SplineSet$CustomSet r2 = (androidx.constraintlayout.motion.widget.SplineSet.CustomSet) r2
            int r3 = r6.mFramePosition
            r2.setPoint((int) r3, (androidx.constraintlayout.widget.ConstraintAttribute) r1)
            goto L_0x0008
        L_0x0039:
            r3 = -1
            int r5 = r1.hashCode()
            switch(r5) {
                case -1249320806: goto L_0x00d4;
                case -1249320805: goto L_0x00ca;
                case -1225497657: goto L_0x00be;
                case -1225497656: goto L_0x00b2;
                case -1225497655: goto L_0x00a6;
                case -1001078227: goto L_0x009b;
                case -908189618: goto L_0x0090;
                case -908189617: goto L_0x0085;
                case -760884510: goto L_0x007a;
                case -760884509: goto L_0x006f;
                case -40300674: goto L_0x0064;
                case -4379043: goto L_0x0059;
                case 37232917: goto L_0x004e;
                case 92909918: goto L_0x0043;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x00de
        L_0x0043:
            java.lang.String r4 = "alpha"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 0
            goto L_0x00df
        L_0x004e:
            java.lang.String r5 = "transitionPathRotate"
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x00de
            goto L_0x00df
        L_0x0059:
            java.lang.String r4 = "elevation"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 1
            goto L_0x00df
        L_0x0064:
            java.lang.String r4 = "rotation"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 2
            goto L_0x00df
        L_0x006f:
            java.lang.String r4 = "transformPivotY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 6
            goto L_0x00df
        L_0x007a:
            java.lang.String r4 = "transformPivotX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 5
            goto L_0x00df
        L_0x0085:
            java.lang.String r4 = "scaleY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 9
            goto L_0x00df
        L_0x0090:
            java.lang.String r4 = "scaleX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 8
            goto L_0x00df
        L_0x009b:
            java.lang.String r4 = "progress"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 13
            goto L_0x00df
        L_0x00a6:
            java.lang.String r4 = "translationZ"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 12
            goto L_0x00df
        L_0x00b2:
            java.lang.String r4 = "translationY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 11
            goto L_0x00df
        L_0x00be:
            java.lang.String r4 = "translationX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 10
            goto L_0x00df
        L_0x00ca:
            java.lang.String r4 = "rotationY"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 4
            goto L_0x00df
        L_0x00d4:
            java.lang.String r4 = "rotationX"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00de
            r4 = 3
            goto L_0x00df
        L_0x00de:
            r4 = -1
        L_0x00df:
            switch(r4) {
                case 0: goto L_0x01dc;
                case 1: goto L_0x01cb;
                case 2: goto L_0x01ba;
                case 3: goto L_0x01a9;
                case 4: goto L_0x0198;
                case 5: goto L_0x0187;
                case 6: goto L_0x0176;
                case 7: goto L_0x0165;
                case 8: goto L_0x0154;
                case 9: goto L_0x0143;
                case 10: goto L_0x0132;
                case 11: goto L_0x0121;
                case 12: goto L_0x0110;
                case 13: goto L_0x00ff;
                default: goto L_0x00e2;
            }
        L_0x00e2:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "UNKNOWN addValues \""
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "\""
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "KeyAttributes"
            android.util.Log.v(r2, r1)
            goto L_0x0008
        L_0x00ff:
            float r1 = r6.mProgress
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mProgress
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0110:
            float r1 = r6.mTranslationZ
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationZ
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0121:
            float r1 = r6.mTranslationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0132:
            float r1 = r6.mTranslationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0143:
            float r1 = r6.mScaleY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mScaleY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0154:
            float r1 = r6.mScaleX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mScaleX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0165:
            float r1 = r6.mTransitionPathRotate
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTransitionPathRotate
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0176:
            float r1 = r6.mRotationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mPivotY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0187:
            float r1 = r6.mRotationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mPivotX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0198:
            float r1 = r6.mRotationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotationY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01a9:
            float r1 = r6.mRotationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotationX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01ba:
            float r1 = r6.mRotation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotation
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01cb:
            float r1 = r6.mElevation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mElevation
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01dc:
            float r1 = r6.mAlpha
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mAlpha
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01ed:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.addValues(java.util.HashMap):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(java.lang.String r2, java.lang.Object r3) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1913008125: goto L_0x00be;
                case -1812823328: goto L_0x00b2;
                case -1249320806: goto L_0x00a8;
                case -1249320805: goto L_0x009e;
                case -1225497657: goto L_0x0092;
                case -1225497656: goto L_0x0086;
                case -987906986: goto L_0x007c;
                case -987906985: goto L_0x0071;
                case -908189618: goto L_0x0066;
                case -908189617: goto L_0x005b;
                case -40300674: goto L_0x0050;
                case -4379043: goto L_0x0045;
                case 37232917: goto L_0x0038;
                case 92909918: goto L_0x002d;
                case 579057826: goto L_0x0022;
                case 1317633238: goto L_0x0016;
                case 1941332754: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00c8
        L_0x0009:
            java.lang.String r0 = "visibility"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 12
            goto L_0x00c9
        L_0x0016:
            java.lang.String r0 = "mTranslationZ"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 16
            goto L_0x00c9
        L_0x0022:
            java.lang.String r0 = "curveFit"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 1
            goto L_0x00c9
        L_0x002d:
            java.lang.String r0 = "alpha"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 0
            goto L_0x00c9
        L_0x0038:
            java.lang.String r0 = "transitionPathRotate"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 13
            goto L_0x00c9
        L_0x0045:
            java.lang.String r0 = "elevation"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 2
            goto L_0x00c9
        L_0x0050:
            java.lang.String r0 = "rotation"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 4
            goto L_0x00c9
        L_0x005b:
            java.lang.String r0 = "scaleY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 10
            goto L_0x00c9
        L_0x0066:
            java.lang.String r0 = "scaleX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 9
            goto L_0x00c9
        L_0x0071:
            java.lang.String r0 = "pivotY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 8
            goto L_0x00c9
        L_0x007c:
            java.lang.String r0 = "pivotX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 7
            goto L_0x00c9
        L_0x0086:
            java.lang.String r0 = "translationY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 15
            goto L_0x00c9
        L_0x0092:
            java.lang.String r0 = "translationX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 14
            goto L_0x00c9
        L_0x009e:
            java.lang.String r0 = "rotationY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 6
            goto L_0x00c9
        L_0x00a8:
            java.lang.String r0 = "rotationX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 5
            goto L_0x00c9
        L_0x00b2:
            java.lang.String r0 = "transitionEasing"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 11
            goto L_0x00c9
        L_0x00be:
            java.lang.String r0 = "motionProgress"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00c8
            r2 = 3
            goto L_0x00c9
        L_0x00c8:
            r2 = -1
        L_0x00c9:
            switch(r2) {
                case 0: goto L_0x0140;
                case 1: goto L_0x0139;
                case 2: goto L_0x0132;
                case 3: goto L_0x012b;
                case 4: goto L_0x0124;
                case 5: goto L_0x011d;
                case 6: goto L_0x0116;
                case 7: goto L_0x010f;
                case 8: goto L_0x0108;
                case 9: goto L_0x0101;
                case 10: goto L_0x00fa;
                case 11: goto L_0x00f3;
                case 12: goto L_0x00ec;
                case 13: goto L_0x00e5;
                case 14: goto L_0x00de;
                case 15: goto L_0x00d6;
                case 16: goto L_0x00ce;
                default: goto L_0x00cc;
            }
        L_0x00cc:
            goto L_0x0146
        L_0x00ce:
            float r2 = r1.toFloat(r3)
            r1.mTranslationZ = r2
            goto L_0x0146
        L_0x00d6:
            float r2 = r1.toFloat(r3)
            r1.mTranslationY = r2
            goto L_0x0146
        L_0x00de:
            float r2 = r1.toFloat(r3)
            r1.mTranslationX = r2
            goto L_0x0146
        L_0x00e5:
            float r2 = r1.toFloat(r3)
            r1.mTransitionPathRotate = r2
            goto L_0x0146
        L_0x00ec:
            boolean r2 = r1.toBoolean(r3)
            r1.mVisibility = r2
            goto L_0x0146
        L_0x00f3:
            java.lang.String r2 = r3.toString()
            r1.mTransitionEasing = r2
            goto L_0x0146
        L_0x00fa:
            float r2 = r1.toFloat(r3)
            r1.mScaleY = r2
            goto L_0x0146
        L_0x0101:
            float r2 = r1.toFloat(r3)
            r1.mScaleX = r2
            goto L_0x0146
        L_0x0108:
            float r2 = r1.toFloat(r3)
            r1.mPivotY = r2
            goto L_0x0146
        L_0x010f:
            float r2 = r1.toFloat(r3)
            r1.mPivotX = r2
            goto L_0x0146
        L_0x0116:
            float r2 = r1.toFloat(r3)
            r1.mRotationY = r2
            goto L_0x0146
        L_0x011d:
            float r2 = r1.toFloat(r3)
            r1.mRotationX = r2
            goto L_0x0146
        L_0x0124:
            float r2 = r1.toFloat(r3)
            r1.mRotation = r2
            goto L_0x0146
        L_0x012b:
            float r2 = r1.toFloat(r3)
            r1.mProgress = r2
            goto L_0x0146
        L_0x0132:
            float r2 = r1.toFloat(r3)
            r1.mElevation = r2
            goto L_0x0146
        L_0x0139:
            int r2 = r1.toInt(r3)
            r1.mCurveFit = r2
            goto L_0x0146
        L_0x0140:
            float r2 = r1.toFloat(r3)
            r1.mAlpha = r2
        L_0x0146:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.setValue(java.lang.String, java.lang.Object):void");
    }

    private static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
        private static final int ANDROID_PIVOT_X = 19;
        private static final int ANDROID_PIVOT_Y = 20;
        private static final int ANDROID_ROTATION = 4;
        private static final int ANDROID_ROTATION_X = 5;
        private static final int ANDROID_ROTATION_Y = 6;
        private static final int ANDROID_SCALE_X = 7;
        private static final int ANDROID_SCALE_Y = 14;
        private static final int ANDROID_TRANSLATION_X = 15;
        private static final int ANDROID_TRANSLATION_Y = 16;
        private static final int ANDROID_TRANSLATION_Z = 17;
        private static final int CURVE_FIT = 13;
        private static final int FRAME_POSITION = 12;
        private static final int PROGRESS = 18;
        private static final int TARGET_ID = 10;
        private static final int TRANSITION_EASING = 9;
        private static final int TRANSITION_PATH_ROTATE = 8;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyAttribute_android_alpha, 1);
            mAttrMap.append(R.styleable.KeyAttribute_android_elevation, 2);
            mAttrMap.append(R.styleable.KeyAttribute_android_rotation, 4);
            mAttrMap.append(R.styleable.KeyAttribute_android_rotationX, 5);
            mAttrMap.append(R.styleable.KeyAttribute_android_rotationY, 6);
            mAttrMap.append(R.styleable.KeyAttribute_android_transformPivotX, 19);
            mAttrMap.append(R.styleable.KeyAttribute_android_transformPivotY, 20);
            mAttrMap.append(R.styleable.KeyAttribute_android_scaleX, 7);
            mAttrMap.append(R.styleable.KeyAttribute_transitionPathRotate, 8);
            mAttrMap.append(R.styleable.KeyAttribute_transitionEasing, 9);
            mAttrMap.append(R.styleable.KeyAttribute_motionTarget, 10);
            mAttrMap.append(R.styleable.KeyAttribute_framePosition, 12);
            mAttrMap.append(R.styleable.KeyAttribute_curveFit, 13);
            mAttrMap.append(R.styleable.KeyAttribute_android_scaleY, 14);
            mAttrMap.append(R.styleable.KeyAttribute_android_translationX, 15);
            mAttrMap.append(R.styleable.KeyAttribute_android_translationY, 16);
            mAttrMap.append(R.styleable.KeyAttribute_android_translationZ, 17);
            mAttrMap.append(R.styleable.KeyAttribute_motionProgress, 18);
        }

        public static void read(KeyAttributes keyAttributes, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        float unused = keyAttributes.mAlpha = typedArray.getFloat(index, keyAttributes.mAlpha);
                        break;
                    case 2:
                        float unused2 = keyAttributes.mElevation = typedArray.getDimension(index, keyAttributes.mElevation);
                        break;
                    case 4:
                        float unused3 = keyAttributes.mRotation = typedArray.getFloat(index, keyAttributes.mRotation);
                        break;
                    case 5:
                        float unused4 = keyAttributes.mRotationX = typedArray.getFloat(index, keyAttributes.mRotationX);
                        break;
                    case 6:
                        float unused5 = keyAttributes.mRotationY = typedArray.getFloat(index, keyAttributes.mRotationY);
                        break;
                    case 7:
                        float unused6 = keyAttributes.mScaleX = typedArray.getFloat(index, keyAttributes.mScaleX);
                        break;
                    case 8:
                        float unused7 = keyAttributes.mTransitionPathRotate = typedArray.getFloat(index, keyAttributes.mTransitionPathRotate);
                        break;
                    case 9:
                        String unused8 = keyAttributes.mTransitionEasing = typedArray.getString(index);
                        break;
                    case 10:
                        if (!MotionLayout.IS_IN_EDIT_MODE) {
                            if (typedArray.peekValue(index).type != 3) {
                                keyAttributes.mTargetId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                                break;
                            } else {
                                keyAttributes.mTargetString = typedArray.getString(index);
                                break;
                            }
                        } else {
                            keyAttributes.mTargetId = typedArray.getResourceId(index, keyAttributes.mTargetId);
                            if (keyAttributes.mTargetId != -1) {
                                break;
                            } else {
                                keyAttributes.mTargetString = typedArray.getString(index);
                                break;
                            }
                        }
                    case 12:
                        keyAttributes.mFramePosition = typedArray.getInt(index, keyAttributes.mFramePosition);
                        break;
                    case 13:
                        int unused9 = keyAttributes.mCurveFit = typedArray.getInteger(index, keyAttributes.mCurveFit);
                        break;
                    case 14:
                        float unused10 = keyAttributes.mScaleY = typedArray.getFloat(index, keyAttributes.mScaleY);
                        break;
                    case 15:
                        float unused11 = keyAttributes.mTranslationX = typedArray.getDimension(index, keyAttributes.mTranslationX);
                        break;
                    case 16:
                        float unused12 = keyAttributes.mTranslationY = typedArray.getDimension(index, keyAttributes.mTranslationY);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            float unused13 = keyAttributes.mTranslationZ = typedArray.getDimension(index, keyAttributes.mTranslationZ);
                            break;
                        }
                    case 18:
                        float unused14 = keyAttributes.mProgress = typedArray.getFloat(index, keyAttributes.mProgress);
                        break;
                    case 19:
                        float unused15 = keyAttributes.mPivotX = typedArray.getDimension(index, keyAttributes.mPivotX);
                        break;
                    case 20:
                        float unused16 = keyAttributes.mPivotY = typedArray.getDimension(index, keyAttributes.mPivotY);
                        break;
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                }
            }
        }
    }
}
