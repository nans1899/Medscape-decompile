package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import androidx.core.app.NotificationCompat;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.HashMap;
import java.util.HashSet;

public class KeyCycle extends Key {
    public static final int KEY_TYPE = 4;
    static final String NAME = "KeyCycle";
    private static final String TAG = "KeyCycle";
    /* access modifiers changed from: private */
    public float mAlpha = Float.NaN;
    /* access modifiers changed from: private */
    public int mCurveFit = 0;
    /* access modifiers changed from: private */
    public float mElevation = Float.NaN;
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
    public String mTransitionEasing = null;
    /* access modifiers changed from: private */
    public float mTransitionPathRotate = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationX = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationY = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationZ = Float.NaN;
    /* access modifiers changed from: private */
    public float mWaveOffset = 0.0f;
    /* access modifiers changed from: private */
    public float mWavePeriod = Float.NaN;
    /* access modifiers changed from: private */
    public int mWaveShape = -1;
    /* access modifiers changed from: private */
    public int mWaveVariesBy = -1;

    public KeyCycle() {
        this.mType = 4;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyCycle));
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
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("transitionPathRotate");
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
        if (this.mCustomConstraints.size() > 0) {
            for (String str : this.mCustomConstraints.keySet()) {
                hashSet.add("CUSTOM," + str);
            }
        }
    }

    public void addCycleValues(HashMap<String, KeyCycleOscillator> hashMap) {
        HashMap<String, KeyCycleOscillator> hashMap2 = hashMap;
        for (String next : hashMap.keySet()) {
            if (next.startsWith(Constants.TOPIC_CUSTOM_TAB_TITLE)) {
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.mCustomConstraints.get(next.substring(7));
                if (constraintAttribute != null && constraintAttribute.getType() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    hashMap2.get(next).setPoint(this.mFramePosition, this.mWaveShape, this.mWaveVariesBy, this.mWavePeriod, this.mWaveOffset, constraintAttribute.getValueToInterpolate(), constraintAttribute);
                }
            } else {
                float value = getValue(next);
                if (!Float.isNaN(value)) {
                    hashMap2.get(next).setPoint(this.mFramePosition, this.mWaveShape, this.mWaveVariesBy, this.mWavePeriod, this.mWaveOffset, value);
                }
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float getValue(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            switch(r0) {
                case -1249320806: goto L_0x008e;
                case -1249320805: goto L_0x0084;
                case -1225497657: goto L_0x0078;
                case -1225497656: goto L_0x006c;
                case -1225497655: goto L_0x0060;
                case -1001078227: goto L_0x0055;
                case -908189618: goto L_0x004b;
                case -908189617: goto L_0x0041;
                case -40300674: goto L_0x0037;
                case -4379043: goto L_0x002d;
                case 37232917: goto L_0x0021;
                case 92909918: goto L_0x0016;
                case 156108012: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0098
        L_0x0009:
            java.lang.String r0 = "waveOffset"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 11
            goto L_0x0099
        L_0x0016:
            java.lang.String r0 = "alpha"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 0
            goto L_0x0099
        L_0x0021:
            java.lang.String r0 = "transitionPathRotate"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 5
            goto L_0x0099
        L_0x002d:
            java.lang.String r0 = "elevation"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 1
            goto L_0x0099
        L_0x0037:
            java.lang.String r0 = "rotation"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 2
            goto L_0x0099
        L_0x0041:
            java.lang.String r0 = "scaleY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 7
            goto L_0x0099
        L_0x004b:
            java.lang.String r0 = "scaleX"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 6
            goto L_0x0099
        L_0x0055:
            java.lang.String r0 = "progress"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 12
            goto L_0x0099
        L_0x0060:
            java.lang.String r0 = "translationZ"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 10
            goto L_0x0099
        L_0x006c:
            java.lang.String r0 = "translationY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 9
            goto L_0x0099
        L_0x0078:
            java.lang.String r0 = "translationX"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 8
            goto L_0x0099
        L_0x0084:
            java.lang.String r0 = "rotationY"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 4
            goto L_0x0099
        L_0x008e:
            java.lang.String r0 = "rotationX"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0098
            r0 = 3
            goto L_0x0099
        L_0x0098:
            r0 = -1
        L_0x0099:
            switch(r0) {
                case 0: goto L_0x00d9;
                case 1: goto L_0x00d6;
                case 2: goto L_0x00d3;
                case 3: goto L_0x00d0;
                case 4: goto L_0x00cd;
                case 5: goto L_0x00ca;
                case 6: goto L_0x00c7;
                case 7: goto L_0x00c4;
                case 8: goto L_0x00c1;
                case 9: goto L_0x00be;
                case 10: goto L_0x00bb;
                case 11: goto L_0x00b8;
                case 12: goto L_0x00b5;
                default: goto L_0x009c;
            }
        L_0x009c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WARNING! KeyCycle UNKNOWN  "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "KeyCycle"
            android.util.Log.v(r0, r3)
            r3 = 2143289344(0x7fc00000, float:NaN)
            return r3
        L_0x00b5:
            float r3 = r2.mProgress
            return r3
        L_0x00b8:
            float r3 = r2.mWaveOffset
            return r3
        L_0x00bb:
            float r3 = r2.mTranslationZ
            return r3
        L_0x00be:
            float r3 = r2.mTranslationY
            return r3
        L_0x00c1:
            float r3 = r2.mTranslationX
            return r3
        L_0x00c4:
            float r3 = r2.mScaleY
            return r3
        L_0x00c7:
            float r3 = r2.mScaleX
            return r3
        L_0x00ca:
            float r3 = r2.mTransitionPathRotate
            return r3
        L_0x00cd:
            float r3 = r2.mRotationY
            return r3
        L_0x00d0:
            float r3 = r2.mRotationX
            return r3
        L_0x00d3:
            float r3 = r2.mRotation
            return r3
        L_0x00d6:
            float r3 = r2.mElevation
            return r3
        L_0x00d9:
            float r3 = r2.mAlpha
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyCycle.getValue(java.lang.String):float");
    }

    public void addValues(HashMap<String, SplineSet> hashMap) {
        Debug.logStack("KeyCycle", "add " + hashMap.size() + " values", 2);
        for (String next : hashMap.keySet()) {
            SplineSet splineSet = hashMap.get(next);
            char c = 65535;
            switch (next.hashCode()) {
                case -1249320806:
                    if (next.equals("rotationX")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1249320805:
                    if (next.equals("rotationY")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1225497657:
                    if (next.equals("translationX")) {
                        c = 8;
                        break;
                    }
                    break;
                case -1225497656:
                    if (next.equals("translationY")) {
                        c = 9;
                        break;
                    }
                    break;
                case -1225497655:
                    if (next.equals("translationZ")) {
                        c = 10;
                        break;
                    }
                    break;
                case -1001078227:
                    if (next.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                        c = 12;
                        break;
                    }
                    break;
                case -908189618:
                    if (next.equals("scaleX")) {
                        c = 6;
                        break;
                    }
                    break;
                case -908189617:
                    if (next.equals("scaleY")) {
                        c = 7;
                        break;
                    }
                    break;
                case -40300674:
                    if (next.equals("rotation")) {
                        c = 2;
                        break;
                    }
                    break;
                case -4379043:
                    if (next.equals("elevation")) {
                        c = 1;
                        break;
                    }
                    break;
                case 37232917:
                    if (next.equals("transitionPathRotate")) {
                        c = 5;
                        break;
                    }
                    break;
                case 92909918:
                    if (next.equals("alpha")) {
                        c = 0;
                        break;
                    }
                    break;
                case 156108012:
                    if (next.equals("waveOffset")) {
                        c = 11;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    splineSet.setPoint(this.mFramePosition, this.mAlpha);
                    break;
                case 1:
                    splineSet.setPoint(this.mFramePosition, this.mElevation);
                    break;
                case 2:
                    splineSet.setPoint(this.mFramePosition, this.mRotation);
                    break;
                case 3:
                    splineSet.setPoint(this.mFramePosition, this.mRotationX);
                    break;
                case 4:
                    splineSet.setPoint(this.mFramePosition, this.mRotationY);
                    break;
                case 5:
                    splineSet.setPoint(this.mFramePosition, this.mTransitionPathRotate);
                    break;
                case 6:
                    splineSet.setPoint(this.mFramePosition, this.mScaleX);
                    break;
                case 7:
                    splineSet.setPoint(this.mFramePosition, this.mScaleY);
                    break;
                case 8:
                    splineSet.setPoint(this.mFramePosition, this.mTranslationX);
                    break;
                case 9:
                    splineSet.setPoint(this.mFramePosition, this.mTranslationY);
                    break;
                case 10:
                    splineSet.setPoint(this.mFramePosition, this.mTranslationZ);
                    break;
                case 11:
                    splineSet.setPoint(this.mFramePosition, this.mWaveOffset);
                    break;
                case 12:
                    splineSet.setPoint(this.mFramePosition, this.mProgress);
                    break;
                default:
                    Log.v("KeyCycle", "WARNING KeyCycle UNKNOWN  " + next);
                    break;
            }
        }
    }

    private static class Loader {
        private static final int ANDROID_ALPHA = 9;
        private static final int ANDROID_ELEVATION = 10;
        private static final int ANDROID_ROTATION = 11;
        private static final int ANDROID_ROTATION_X = 12;
        private static final int ANDROID_ROTATION_Y = 13;
        private static final int ANDROID_SCALE_X = 15;
        private static final int ANDROID_SCALE_Y = 16;
        private static final int ANDROID_TRANSLATION_X = 17;
        private static final int ANDROID_TRANSLATION_Y = 18;
        private static final int ANDROID_TRANSLATION_Z = 19;
        private static final int CURVE_FIT = 4;
        private static final int FRAME_POSITION = 2;
        private static final int PROGRESS = 20;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 14;
        private static final int WAVE_OFFSET = 7;
        private static final int WAVE_PERIOD = 6;
        private static final int WAVE_SHAPE = 5;
        private static final int WAVE_VARIES_BY = 8;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyCycle_motionTarget, 1);
            mAttrMap.append(R.styleable.KeyCycle_framePosition, 2);
            mAttrMap.append(R.styleable.KeyCycle_transitionEasing, 3);
            mAttrMap.append(R.styleable.KeyCycle_curveFit, 4);
            mAttrMap.append(R.styleable.KeyCycle_waveShape, 5);
            mAttrMap.append(R.styleable.KeyCycle_wavePeriod, 6);
            mAttrMap.append(R.styleable.KeyCycle_waveOffset, 7);
            mAttrMap.append(R.styleable.KeyCycle_waveVariesBy, 8);
            mAttrMap.append(R.styleable.KeyCycle_android_alpha, 9);
            mAttrMap.append(R.styleable.KeyCycle_android_elevation, 10);
            mAttrMap.append(R.styleable.KeyCycle_android_rotation, 11);
            mAttrMap.append(R.styleable.KeyCycle_android_rotationX, 12);
            mAttrMap.append(R.styleable.KeyCycle_android_rotationY, 13);
            mAttrMap.append(R.styleable.KeyCycle_transitionPathRotate, 14);
            mAttrMap.append(R.styleable.KeyCycle_android_scaleX, 15);
            mAttrMap.append(R.styleable.KeyCycle_android_scaleY, 16);
            mAttrMap.append(R.styleable.KeyCycle_android_translationX, 17);
            mAttrMap.append(R.styleable.KeyCycle_android_translationY, 18);
            mAttrMap.append(R.styleable.KeyCycle_android_translationZ, 19);
            mAttrMap.append(R.styleable.KeyCycle_motionProgress, 20);
        }

        /* access modifiers changed from: private */
        public static void read(KeyCycle keyCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        if (!MotionLayout.IS_IN_EDIT_MODE) {
                            if (typedArray.peekValue(index).type != 3) {
                                keyCycle.mTargetId = typedArray.getResourceId(index, keyCycle.mTargetId);
                                break;
                            } else {
                                keyCycle.mTargetString = typedArray.getString(index);
                                break;
                            }
                        } else {
                            keyCycle.mTargetId = typedArray.getResourceId(index, keyCycle.mTargetId);
                            if (keyCycle.mTargetId != -1) {
                                break;
                            } else {
                                keyCycle.mTargetString = typedArray.getString(index);
                                break;
                            }
                        }
                    case 2:
                        keyCycle.mFramePosition = typedArray.getInt(index, keyCycle.mFramePosition);
                        break;
                    case 3:
                        String unused = keyCycle.mTransitionEasing = typedArray.getString(index);
                        break;
                    case 4:
                        int unused2 = keyCycle.mCurveFit = typedArray.getInteger(index, keyCycle.mCurveFit);
                        break;
                    case 5:
                        int unused3 = keyCycle.mWaveShape = typedArray.getInt(index, keyCycle.mWaveShape);
                        break;
                    case 6:
                        float unused4 = keyCycle.mWavePeriod = typedArray.getFloat(index, keyCycle.mWavePeriod);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type != 5) {
                            float unused5 = keyCycle.mWaveOffset = typedArray.getFloat(index, keyCycle.mWaveOffset);
                            break;
                        } else {
                            float unused6 = keyCycle.mWaveOffset = typedArray.getDimension(index, keyCycle.mWaveOffset);
                            break;
                        }
                    case 8:
                        int unused7 = keyCycle.mWaveVariesBy = typedArray.getInt(index, keyCycle.mWaveVariesBy);
                        break;
                    case 9:
                        float unused8 = keyCycle.mAlpha = typedArray.getFloat(index, keyCycle.mAlpha);
                        break;
                    case 10:
                        float unused9 = keyCycle.mElevation = typedArray.getDimension(index, keyCycle.mElevation);
                        break;
                    case 11:
                        float unused10 = keyCycle.mRotation = typedArray.getFloat(index, keyCycle.mRotation);
                        break;
                    case 12:
                        float unused11 = keyCycle.mRotationX = typedArray.getFloat(index, keyCycle.mRotationX);
                        break;
                    case 13:
                        float unused12 = keyCycle.mRotationY = typedArray.getFloat(index, keyCycle.mRotationY);
                        break;
                    case 14:
                        float unused13 = keyCycle.mTransitionPathRotate = typedArray.getFloat(index, keyCycle.mTransitionPathRotate);
                        break;
                    case 15:
                        float unused14 = keyCycle.mScaleX = typedArray.getFloat(index, keyCycle.mScaleX);
                        break;
                    case 16:
                        float unused15 = keyCycle.mScaleY = typedArray.getFloat(index, keyCycle.mScaleY);
                        break;
                    case 17:
                        float unused16 = keyCycle.mTranslationX = typedArray.getDimension(index, keyCycle.mTranslationX);
                        break;
                    case 18:
                        float unused17 = keyCycle.mTranslationY = typedArray.getDimension(index, keyCycle.mTranslationY);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            float unused18 = keyCycle.mTranslationZ = typedArray.getDimension(index, keyCycle.mTranslationZ);
                            break;
                        }
                    case 20:
                        float unused19 = keyCycle.mProgress = typedArray.getFloat(index, keyCycle.mProgress);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                }
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(java.lang.String r2, java.lang.Object r3) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1812823328: goto L_0x00b1;
                case -1249320806: goto L_0x00a7;
                case -1249320805: goto L_0x009d;
                case -1225497657: goto L_0x0091;
                case -1225497656: goto L_0x0085;
                case -1001078227: goto L_0x007b;
                case -908189618: goto L_0x0071;
                case -908189617: goto L_0x0066;
                case -40300674: goto L_0x005c;
                case -4379043: goto L_0x0052;
                case 37232917: goto L_0x0045;
                case 92909918: goto L_0x003a;
                case 156108012: goto L_0x002d;
                case 184161818: goto L_0x0020;
                case 579057826: goto L_0x0015;
                case 1317633238: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00bd
        L_0x0009:
            java.lang.String r0 = "mTranslationZ"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 13
            goto L_0x00be
        L_0x0015:
            java.lang.String r0 = "curveFit"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 1
            goto L_0x00be
        L_0x0020:
            java.lang.String r0 = "wavePeriod"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 14
            goto L_0x00be
        L_0x002d:
            java.lang.String r0 = "waveOffset"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 15
            goto L_0x00be
        L_0x003a:
            java.lang.String r0 = "alpha"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 0
            goto L_0x00be
        L_0x0045:
            java.lang.String r0 = "transitionPathRotate"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 10
            goto L_0x00be
        L_0x0052:
            java.lang.String r0 = "elevation"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 2
            goto L_0x00be
        L_0x005c:
            java.lang.String r0 = "rotation"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 4
            goto L_0x00be
        L_0x0066:
            java.lang.String r0 = "scaleY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 8
            goto L_0x00be
        L_0x0071:
            java.lang.String r0 = "scaleX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 7
            goto L_0x00be
        L_0x007b:
            java.lang.String r0 = "progress"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 3
            goto L_0x00be
        L_0x0085:
            java.lang.String r0 = "translationY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 12
            goto L_0x00be
        L_0x0091:
            java.lang.String r0 = "translationX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 11
            goto L_0x00be
        L_0x009d:
            java.lang.String r0 = "rotationY"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 6
            goto L_0x00be
        L_0x00a7:
            java.lang.String r0 = "rotationX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 5
            goto L_0x00be
        L_0x00b1:
            java.lang.String r0 = "transitionEasing"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00bd
            r2 = 9
            goto L_0x00be
        L_0x00bd:
            r2 = -1
        L_0x00be:
            switch(r2) {
                case 0: goto L_0x012d;
                case 1: goto L_0x0126;
                case 2: goto L_0x011f;
                case 3: goto L_0x0118;
                case 4: goto L_0x0111;
                case 5: goto L_0x010a;
                case 6: goto L_0x0103;
                case 7: goto L_0x00fc;
                case 8: goto L_0x00f5;
                case 9: goto L_0x00ee;
                case 10: goto L_0x00e7;
                case 11: goto L_0x00e0;
                case 12: goto L_0x00d9;
                case 13: goto L_0x00d2;
                case 14: goto L_0x00cb;
                case 15: goto L_0x00c3;
                default: goto L_0x00c1;
            }
        L_0x00c1:
            goto L_0x0133
        L_0x00c3:
            float r2 = r1.toFloat(r3)
            r1.mWaveOffset = r2
            goto L_0x0133
        L_0x00cb:
            float r2 = r1.toFloat(r3)
            r1.mWavePeriod = r2
            goto L_0x0133
        L_0x00d2:
            float r2 = r1.toFloat(r3)
            r1.mTranslationZ = r2
            goto L_0x0133
        L_0x00d9:
            float r2 = r1.toFloat(r3)
            r1.mTranslationY = r2
            goto L_0x0133
        L_0x00e0:
            float r2 = r1.toFloat(r3)
            r1.mTranslationX = r2
            goto L_0x0133
        L_0x00e7:
            float r2 = r1.toFloat(r3)
            r1.mTransitionPathRotate = r2
            goto L_0x0133
        L_0x00ee:
            java.lang.String r2 = r3.toString()
            r1.mTransitionEasing = r2
            goto L_0x0133
        L_0x00f5:
            float r2 = r1.toFloat(r3)
            r1.mScaleY = r2
            goto L_0x0133
        L_0x00fc:
            float r2 = r1.toFloat(r3)
            r1.mScaleX = r2
            goto L_0x0133
        L_0x0103:
            float r2 = r1.toFloat(r3)
            r1.mRotationY = r2
            goto L_0x0133
        L_0x010a:
            float r2 = r1.toFloat(r3)
            r1.mRotationX = r2
            goto L_0x0133
        L_0x0111:
            float r2 = r1.toFloat(r3)
            r1.mRotation = r2
            goto L_0x0133
        L_0x0118:
            float r2 = r1.toFloat(r3)
            r1.mProgress = r2
            goto L_0x0133
        L_0x011f:
            float r2 = r1.toFloat(r3)
            r1.mElevation = r2
            goto L_0x0133
        L_0x0126:
            int r2 = r1.toInt(r3)
            r1.mCurveFit = r2
            goto L_0x0133
        L_0x012d:
            float r2 = r1.toFloat(r3)
            r1.mAlpha = r2
        L_0x0133:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyCycle.setValue(java.lang.String, java.lang.Object):void");
    }
}
