package fi.harism.curl;

import android.graphics.Color;
import android.graphics.RectF;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class CurlMesh {
    private static final boolean DRAW_CURL_POSITION = false;
    private static final boolean DRAW_POLYGON_OUTLINES = false;
    private static final boolean DRAW_SHADOW = true;
    private static final boolean DRAW_TEXTURE = true;
    private static final float[] SHADOW_INNER_COLOR = {0.0f, 0.0f, 0.0f, 0.5f};
    private static final float[] SHADOW_OUTER_COLOR = {0.0f, 0.0f, 0.0f, 0.0f};
    private Array<ShadowVertex> mArrDropShadowVertices;
    private Array<Vertex> mArrIntersections;
    private Array<Vertex> mArrOutputVertices;
    private Array<Vertex> mArrRotatedVertices;
    private Array<Double> mArrScanLines;
    private Array<ShadowVertex> mArrSelfShadowVertices;
    private Array<ShadowVertex> mArrTempShadowVertices;
    private Array<Vertex> mArrTempVertices;
    private FloatBuffer mBufColors;
    private FloatBuffer mBufCurlPositionLines;
    private FloatBuffer mBufShadowColors;
    private FloatBuffer mBufShadowVertices;
    private FloatBuffer mBufTexCoords;
    private FloatBuffer mBufVertices;
    private int mCurlPositionLinesCount;
    private int mDropShadowCount;
    private boolean mFlipTexture = false;
    private int mMaxCurlSplits;
    private final Vertex[] mRectangle = new Vertex[4];
    private int mSelfShadowCount;
    private boolean mTextureBack = false;
    private int[] mTextureIds = null;
    private final CurlPage mTexturePage = new CurlPage();
    private final RectF mTextureRectBack = new RectF();
    private final RectF mTextureRectFront = new RectF();
    private int mVerticesCountBack;
    private int mVerticesCountFront;

    public CurlMesh(int i) {
        this.mMaxCurlSplits = i < 1 ? 1 : i;
        this.mArrScanLines = new Array<>(i + 2);
        this.mArrOutputVertices = new Array<>(7);
        this.mArrRotatedVertices = new Array<>(4);
        this.mArrIntersections = new Array<>(2);
        this.mArrTempVertices = new Array<>(11);
        for (int i2 = 0; i2 < 11; i2++) {
            this.mArrTempVertices.add(new Vertex());
        }
        this.mArrSelfShadowVertices = new Array<>((this.mMaxCurlSplits + 2) * 2);
        this.mArrDropShadowVertices = new Array<>((this.mMaxCurlSplits + 2) * 2);
        this.mArrTempShadowVertices = new Array<>((this.mMaxCurlSplits + 2) * 2);
        for (int i3 = 0; i3 < (this.mMaxCurlSplits + 2) * 2; i3++) {
            this.mArrTempShadowVertices.add(new ShadowVertex());
        }
        for (int i4 = 0; i4 < 4; i4++) {
            this.mRectangle[i4] = new Vertex();
        }
        Vertex[] vertexArr = this.mRectangle;
        Vertex vertex = vertexArr[0];
        Vertex vertex2 = vertexArr[1];
        Vertex vertex3 = vertexArr[1];
        vertexArr[3].mPenumbraY = -1.0d;
        vertex3.mPenumbraY = -1.0d;
        vertex2.mPenumbraX = -1.0d;
        vertex.mPenumbraX = -1.0d;
        Vertex[] vertexArr2 = this.mRectangle;
        Vertex vertex4 = vertexArr2[0];
        Vertex vertex5 = vertexArr2[2];
        Vertex vertex6 = vertexArr2[2];
        vertexArr2[3].mPenumbraX = 1.0d;
        vertex6.mPenumbraY = 1.0d;
        vertex5.mPenumbraX = 1.0d;
        vertex4.mPenumbraY = 1.0d;
        int i5 = (this.mMaxCurlSplits * 2) + 6;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i5 * 3 * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        this.mBufVertices = asFloatBuffer;
        asFloatBuffer.position(0);
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(i5 * 2 * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer2 = allocateDirect2.asFloatBuffer();
        this.mBufTexCoords = asFloatBuffer2;
        asFloatBuffer2.position(0);
        ByteBuffer allocateDirect3 = ByteBuffer.allocateDirect(i5 * 4 * 4);
        allocateDirect3.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer3 = allocateDirect3.asFloatBuffer();
        this.mBufColors = asFloatBuffer3;
        asFloatBuffer3.position(0);
        int i6 = (this.mMaxCurlSplits + 2) * 2 * 2;
        ByteBuffer allocateDirect4 = ByteBuffer.allocateDirect(i6 * 4 * 4);
        allocateDirect4.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer4 = allocateDirect4.asFloatBuffer();
        this.mBufShadowColors = asFloatBuffer4;
        asFloatBuffer4.position(0);
        ByteBuffer allocateDirect5 = ByteBuffer.allocateDirect(i6 * 3 * 4);
        allocateDirect5.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer5 = allocateDirect5.asFloatBuffer();
        this.mBufShadowVertices = asFloatBuffer5;
        asFloatBuffer5.position(0);
        this.mSelfShadowCount = 0;
        this.mDropShadowCount = 0;
    }

    private void addVertex(Vertex vertex) {
        this.mBufVertices.put((float) vertex.mPosX);
        this.mBufVertices.put((float) vertex.mPosY);
        this.mBufVertices.put((float) vertex.mPosZ);
        this.mBufColors.put((vertex.mColorFactor * ((float) Color.red(vertex.mColor))) / 255.0f);
        this.mBufColors.put((vertex.mColorFactor * ((float) Color.green(vertex.mColor))) / 255.0f);
        this.mBufColors.put((vertex.mColorFactor * ((float) Color.blue(vertex.mColor))) / 255.0f);
        this.mBufColors.put(((float) Color.alpha(vertex.mColor)) / 255.0f);
        this.mBufTexCoords.put((float) vertex.mTexX);
        this.mBufTexCoords.put((float) vertex.mTexY);
    }

    /* JADX WARNING: Removed duplicated region for block: B:83:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0372  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x03ba  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x03c1  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x040b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void curl(android.graphics.PointF r28, android.graphics.PointF r29, double r30) {
        /*
            r27 = this;
            r1 = r27
            r0 = r28
            r2 = r29
            monitor-enter(r27)
            java.nio.FloatBuffer r3 = r1.mBufVertices     // Catch:{ all -> 0x053b }
            r4 = 0
            r3.position(r4)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufColors     // Catch:{ all -> 0x053b }
            r3.position(r4)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufTexCoords     // Catch:{ all -> 0x053b }
            r3.position(r4)     // Catch:{ all -> 0x053b }
            float r3 = r2.x     // Catch:{ all -> 0x053b }
            double r5 = (double) r3     // Catch:{ all -> 0x053b }
            double r5 = java.lang.Math.acos(r5)     // Catch:{ all -> 0x053b }
            float r3 = r2.y     // Catch:{ all -> 0x053b }
            r7 = 0
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x0026
            double r5 = -r5
        L_0x0026:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r3 = r1.mArrTempVertices     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r7 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            r3.addAll(r7)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r3 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            r3.clear()     // Catch:{ all -> 0x053b }
            r3 = 0
        L_0x0033:
            r7 = 4
            if (r3 >= r7) goto L_0x008a
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r7 = r1.mArrTempVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r7 = r7.remove(r4)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r7 = (fi.harism.curl.CurlMesh.Vertex) r7     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex[] r8 = r1.mRectangle     // Catch:{ all -> 0x053b }
            r8 = r8[r3]     // Catch:{ all -> 0x053b }
            r7.set(r8)     // Catch:{ all -> 0x053b }
            float r8 = r0.x     // Catch:{ all -> 0x053b }
            float r8 = -r8
            double r8 = (double) r8     // Catch:{ all -> 0x053b }
            float r10 = r0.y     // Catch:{ all -> 0x053b }
            float r10 = -r10
            double r10 = (double) r10     // Catch:{ all -> 0x053b }
            r7.translate(r8, r10)     // Catch:{ all -> 0x053b }
            double r8 = -r5
            r7.rotateZ(r8)     // Catch:{ all -> 0x053b }
            r8 = 0
        L_0x0055:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r9 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            int r9 = r9.size()     // Catch:{ all -> 0x053b }
            if (r8 >= r9) goto L_0x0082
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r9 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r9 = r9.get(r8)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r9 = (fi.harism.curl.CurlMesh.Vertex) r9     // Catch:{ all -> 0x053b }
            double r10 = r7.mPosX     // Catch:{ all -> 0x053b }
            double r12 = r9.mPosX     // Catch:{ all -> 0x053b }
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 <= 0) goto L_0x006e
            goto L_0x0082
        L_0x006e:
            double r10 = r7.mPosX     // Catch:{ all -> 0x053b }
            double r12 = r9.mPosX     // Catch:{ all -> 0x053b }
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x007f
            double r10 = r7.mPosY     // Catch:{ all -> 0x053b }
            double r12 = r9.mPosY     // Catch:{ all -> 0x053b }
            int r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r9 <= 0) goto L_0x007f
            goto L_0x0082
        L_0x007f:
            int r8 = r8 + 1
            goto L_0x0055
        L_0x0082:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r9 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            r9.add(r8, r7)     // Catch:{ all -> 0x053b }
            int r3 = r3 + 1
            goto L_0x0033
        L_0x008a:
            int[][] r3 = new int[r7][]     // Catch:{ all -> 0x053b }
            r8 = 2
            int[] r9 = new int[r8]     // Catch:{ all -> 0x053b }
            r9[r4] = r4     // Catch:{ all -> 0x053b }
            r10 = 1
            r9[r10] = r10     // Catch:{ all -> 0x053b }
            r3[r4] = r9     // Catch:{ all -> 0x053b }
            int[] r9 = new int[r8]     // Catch:{ all -> 0x053b }
            r9[r4] = r4     // Catch:{ all -> 0x053b }
            r9[r10] = r8     // Catch:{ all -> 0x053b }
            r3[r10] = r9     // Catch:{ all -> 0x053b }
            int[] r9 = new int[r8]     // Catch:{ all -> 0x053b }
            r9[r4] = r10     // Catch:{ all -> 0x053b }
            r11 = 3
            r9[r10] = r11     // Catch:{ all -> 0x053b }
            r3[r8] = r9     // Catch:{ all -> 0x053b }
            int[] r9 = new int[r8]     // Catch:{ all -> 0x053b }
            r9[r4] = r8     // Catch:{ all -> 0x053b }
            r9[r10] = r11     // Catch:{ all -> 0x053b }
            r3[r11] = r9     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r9 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r9 = r9.get(r4)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r9 = (fi.harism.curl.CurlMesh.Vertex) r9     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r12 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r12 = r12.get(r8)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r12 = (fi.harism.curl.CurlMesh.Vertex) r12     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r13 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r13 = r13.get(r11)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r13 = (fi.harism.curl.CurlMesh.Vertex) r13     // Catch:{ all -> 0x053b }
            double r14 = r9.mPosX     // Catch:{ all -> 0x053b }
            r16 = r5
            double r4 = r12.mPosX     // Catch:{ all -> 0x053b }
            double r14 = r14 - r4
            double r4 = r9.mPosX     // Catch:{ all -> 0x053b }
            double r7 = r12.mPosX     // Catch:{ all -> 0x053b }
            double r4 = r4 - r7
            double r14 = r14 * r4
            double r4 = r9.mPosY     // Catch:{ all -> 0x053b }
            double r7 = r12.mPosY     // Catch:{ all -> 0x053b }
            double r4 = r4 - r7
            double r7 = r9.mPosY     // Catch:{ all -> 0x053b }
            double r11 = r12.mPosY     // Catch:{ all -> 0x053b }
            double r7 = r7 - r11
            double r4 = r4 * r7
            double r14 = r14 + r4
            double r4 = java.lang.Math.sqrt(r14)     // Catch:{ all -> 0x053b }
            double r7 = r9.mPosX     // Catch:{ all -> 0x053b }
            double r11 = r13.mPosX     // Catch:{ all -> 0x053b }
            double r7 = r7 - r11
            double r11 = r9.mPosX     // Catch:{ all -> 0x053b }
            double r14 = r13.mPosX     // Catch:{ all -> 0x053b }
            double r11 = r11 - r14
            double r7 = r7 * r11
            double r11 = r9.mPosY     // Catch:{ all -> 0x053b }
            double r14 = r13.mPosY     // Catch:{ all -> 0x053b }
            double r11 = r11 - r14
            double r14 = r9.mPosY     // Catch:{ all -> 0x053b }
            r18 = r7
            double r6 = r13.mPosY     // Catch:{ all -> 0x053b }
            double r14 = r14 - r6
            double r11 = r11 * r14
            double r7 = r18 + r11
            double r6 = java.lang.Math.sqrt(r7)     // Catch:{ all -> 0x053b }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0114
            r4 = r3[r10]     // Catch:{ all -> 0x053b }
            r5 = 3
            r4[r10] = r5     // Catch:{ all -> 0x053b }
            r4 = 2
            r5 = r3[r4]     // Catch:{ all -> 0x053b }
            r5[r10] = r4     // Catch:{ all -> 0x053b }
        L_0x0114:
            r4 = 0
            r1.mVerticesCountBack = r4     // Catch:{ all -> 0x053b }
            r1.mVerticesCountFront = r4     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r4 = r1.mArrTempShadowVertices     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r5 = r1.mArrDropShadowVertices     // Catch:{ all -> 0x053b }
            r4.addAll(r5)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r4 = r1.mArrTempShadowVertices     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r5 = r1.mArrSelfShadowVertices     // Catch:{ all -> 0x053b }
            r4.addAll(r5)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r4 = r1.mArrDropShadowVertices     // Catch:{ all -> 0x053b }
            r4.clear()     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r4 = r1.mArrSelfShadowVertices     // Catch:{ all -> 0x053b }
            r4.clear()     // Catch:{ all -> 0x053b }
            r4 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r6 = r30 * r4
            fi.harism.curl.CurlMesh$Array<java.lang.Double> r8 = r1.mArrScanLines     // Catch:{ all -> 0x053b }
            r8.clear()     // Catch:{ all -> 0x053b }
            int r8 = r1.mMaxCurlSplits     // Catch:{ all -> 0x053b }
            r11 = 0
            if (r8 <= 0) goto L_0x014c
            fi.harism.curl.CurlMesh$Array<java.lang.Double> r8 = r1.mArrScanLines     // Catch:{ all -> 0x053b }
            java.lang.Double r13 = java.lang.Double.valueOf(r11)     // Catch:{ all -> 0x053b }
            r8.add(r13)     // Catch:{ all -> 0x053b }
        L_0x014c:
            r8 = 1
        L_0x014d:
            int r13 = r1.mMaxCurlSplits     // Catch:{ all -> 0x053b }
            if (r8 >= r13) goto L_0x016b
            fi.harism.curl.CurlMesh$Array<java.lang.Double> r13 = r1.mArrScanLines     // Catch:{ all -> 0x053b }
            double r14 = -r6
            double r4 = (double) r8     // Catch:{ all -> 0x053b }
            double r14 = r14 * r4
            int r4 = r1.mMaxCurlSplits     // Catch:{ all -> 0x053b }
            int r4 = r4 - r10
            double r4 = (double) r4     // Catch:{ all -> 0x053b }
            double r14 = r14 / r4
            java.lang.Double r4 = java.lang.Double.valueOf(r14)     // Catch:{ all -> 0x053b }
            r13.add(r4)     // Catch:{ all -> 0x053b }
            int r8 = r8 + 1
            r4 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            goto L_0x014d
        L_0x016b:
            fi.harism.curl.CurlMesh$Array<java.lang.Double> r4 = r1.mArrScanLines     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r5 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            r8 = 3
            java.lang.Object r5 = r5.get(r8)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r5 = (fi.harism.curl.CurlMesh.Vertex) r5     // Catch:{ all -> 0x053b }
            double r13 = r5.mPosX     // Catch:{ all -> 0x053b }
            r20 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r13 = r13 - r20
            java.lang.Double r5 = java.lang.Double.valueOf(r13)     // Catch:{ all -> 0x053b }
            r4.add(r5)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r4 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            r5 = 0
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r4 = (fi.harism.curl.CurlMesh.Vertex) r4     // Catch:{ all -> 0x053b }
            double r4 = r4.mPosX     // Catch:{ all -> 0x053b }
            double r4 = r4 + r20
            r8 = 0
        L_0x0191:
            fi.harism.curl.CurlMesh$Array<java.lang.Double> r13 = r1.mArrScanLines     // Catch:{ all -> 0x053b }
            int r13 = r13.size()     // Catch:{ all -> 0x053b }
            if (r8 >= r13) goto L_0x041f
            fi.harism.curl.CurlMesh$Array<java.lang.Double> r13 = r1.mArrScanLines     // Catch:{ all -> 0x053b }
            java.lang.Object r13 = r13.get(r8)     // Catch:{ all -> 0x053b }
            java.lang.Double r13 = (java.lang.Double) r13     // Catch:{ all -> 0x053b }
            double r13 = r13.doubleValue()     // Catch:{ all -> 0x053b }
            r15 = 0
        L_0x01a6:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r9 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            int r9 = r9.size()     // Catch:{ all -> 0x053b }
            if (r15 >= r9) goto L_0x022f
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r9 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r9 = r9.get(r15)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r9 = (fi.harism.curl.CurlMesh.Vertex) r9     // Catch:{ all -> 0x053b }
            double r11 = r9.mPosX     // Catch:{ all -> 0x053b }
            int r22 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r22 < 0) goto L_0x0220
            double r11 = r9.mPosX     // Catch:{ all -> 0x053b }
            int r22 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r22 > 0) goto L_0x0220
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r11 = r1.mArrTempVertices     // Catch:{ all -> 0x053b }
            r12 = 0
            java.lang.Object r11 = r11.remove(r12)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r11 = (fi.harism.curl.CurlMesh.Vertex) r11     // Catch:{ all -> 0x053b }
            r11.set(r9)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r12 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            r22 = r4
            double r4 = r11.mPosX     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array r4 = r1.getIntersections(r12, r3, r4)     // Catch:{ all -> 0x053b }
            int r5 = r4.size()     // Catch:{ all -> 0x053b }
            if (r5 != r10) goto L_0x0201
            r5 = 0
            java.lang.Object r12 = r4.get(r5)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r12 = (fi.harism.curl.CurlMesh.Vertex) r12     // Catch:{ all -> 0x053b }
            r24 = r11
            double r10 = r12.mPosY     // Catch:{ all -> 0x053b }
            r25 = r6
            double r5 = r9.mPosY     // Catch:{ all -> 0x053b }
            int r9 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r9 <= 0) goto L_0x01fe
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r5 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r5.addAll(r4)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r4 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r11 = r24
            r4.add(r11)     // Catch:{ all -> 0x053b }
            goto L_0x0224
        L_0x01fe:
            r11 = r24
            goto L_0x0203
        L_0x0201:
            r25 = r6
        L_0x0203:
            int r5 = r4.size()     // Catch:{ all -> 0x053b }
            r6 = 1
            if (r5 > r6) goto L_0x0215
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r6 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r6.add(r11)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r6 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r6.addAll(r4)     // Catch:{ all -> 0x053b }
            goto L_0x0224
        L_0x0215:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r6 = r1.mArrTempVertices     // Catch:{ all -> 0x053b }
            r6.add(r11)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r6 = r1.mArrTempVertices     // Catch:{ all -> 0x053b }
            r6.addAll(r4)     // Catch:{ all -> 0x053b }
            goto L_0x0224
        L_0x0220:
            r22 = r4
            r25 = r6
        L_0x0224:
            int r15 = r15 + 1
            r4 = r22
            r6 = r25
            r10 = 1
            r11 = 0
            goto L_0x01a6
        L_0x022f:
            r25 = r6
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r4 = r1.mArrRotatedVertices     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array r4 = r1.getIntersections(r4, r3, r13)     // Catch:{ all -> 0x053b }
            int r6 = r4.size()     // Catch:{ all -> 0x053b }
            r7 = 2
            if (r6 != r7) goto L_0x0265
            r6 = 0
            java.lang.Object r7 = r4.get(r6)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r7 = (fi.harism.curl.CurlMesh.Vertex) r7     // Catch:{ all -> 0x053b }
            r5 = 1
            java.lang.Object r6 = r4.get(r5)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r6 = (fi.harism.curl.CurlMesh.Vertex) r6     // Catch:{ all -> 0x053b }
            double r9 = r7.mPosY     // Catch:{ all -> 0x053b }
            double r11 = r6.mPosY     // Catch:{ all -> 0x053b }
            int r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r15 >= 0) goto L_0x025f
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r4 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r4.add(r6)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r4 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r4.add(r7)     // Catch:{ all -> 0x053b }
            goto L_0x0270
        L_0x025f:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r6 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r6.addAll(r4)     // Catch:{ all -> 0x053b }
            goto L_0x0270
        L_0x0265:
            int r6 = r4.size()     // Catch:{ all -> 0x053b }
            if (r6 == 0) goto L_0x0270
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r6 = r1.mArrTempVertices     // Catch:{ all -> 0x053b }
            r6.addAll(r4)     // Catch:{ all -> 0x053b }
        L_0x0270:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r4 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            int r4 = r4.size()     // Catch:{ all -> 0x053b }
            if (r4 <= 0) goto L_0x0411
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r4 = r1.mArrOutputVertices     // Catch:{ all -> 0x053b }
            r6 = 0
            java.lang.Object r4 = r4.remove(r6)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Vertex r4 = (fi.harism.curl.CurlMesh.Vertex) r4     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$Vertex> r6 = r1.mArrTempVertices     // Catch:{ all -> 0x053b }
            r6.add(r4)     // Catch:{ all -> 0x053b }
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r8 != 0) goto L_0x0293
            int r9 = r1.mVerticesCountFront     // Catch:{ all -> 0x053b }
            r5 = 1
            int r9 = r9 + r5
            r1.mVerticesCountFront = r9     // Catch:{ all -> 0x053b }
        L_0x0290:
            r9 = 1
            goto L_0x0310
        L_0x0293:
            fi.harism.curl.CurlMesh$Array<java.lang.Double> r9 = r1.mArrScanLines     // Catch:{ all -> 0x053b }
            int r9 = r9.size()     // Catch:{ all -> 0x053b }
            r5 = 1
            int r9 = r9 - r5
            if (r8 == r9) goto L_0x02f9
            r9 = 0
            int r11 = (r25 > r9 ? 1 : (r25 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x02a4
            goto L_0x02f9
        L_0x02a4:
            double r9 = r4.mPosX     // Catch:{ all -> 0x053b }
            double r9 = r9 / r25
            r11 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r9 = r9 * r11
            double r18 = java.lang.Math.sin(r9)     // Catch:{ all -> 0x053b }
            double r11 = r30 * r18
            r4.mPosX = r11     // Catch:{ all -> 0x053b }
            double r11 = java.lang.Math.cos(r9)     // Catch:{ all -> 0x053b }
            double r11 = r11 * r30
            double r11 = r30 - r11
            r4.mPosZ = r11     // Catch:{ all -> 0x053b }
            double r11 = r4.mPenumbraX     // Catch:{ all -> 0x053b }
            double r18 = java.lang.Math.cos(r9)     // Catch:{ all -> 0x053b }
            double r11 = r11 * r18
            r4.mPenumbraX = r11     // Catch:{ all -> 0x053b }
            r11 = 4591870180174331904(0x3fb99999a0000000, double:0.10000000149011612)
            r18 = 4606281698659794944(0x3fecccccc0000000, double:0.8999999761581421)
            double r9 = java.lang.Math.sin(r9)     // Catch:{ all -> 0x053b }
            double r9 = r9 + r20
            double r9 = java.lang.Math.sqrt(r9)     // Catch:{ all -> 0x053b }
            double r9 = r9 * r18
            double r9 = r9 + r11
            float r9 = (float) r9     // Catch:{ all -> 0x053b }
            r4.mColorFactor = r9     // Catch:{ all -> 0x053b }
            double r9 = r4.mPosZ     // Catch:{ all -> 0x053b }
            int r11 = (r9 > r30 ? 1 : (r9 == r30 ? 0 : -1))
            if (r11 < 0) goto L_0x02f2
            int r9 = r1.mVerticesCountBack     // Catch:{ all -> 0x053b }
            r5 = 1
            int r9 = r9 + r5
            r1.mVerticesCountBack = r9     // Catch:{ all -> 0x053b }
            goto L_0x030f
        L_0x02f2:
            r5 = 1
            int r9 = r1.mVerticesCountFront     // Catch:{ all -> 0x053b }
            int r9 = r9 + r5
            r1.mVerticesCountFront = r9     // Catch:{ all -> 0x053b }
            goto L_0x0290
        L_0x02f9:
            double r9 = r4.mPosX     // Catch:{ all -> 0x053b }
            double r9 = r25 + r9
            double r9 = -r9
            r4.mPosX = r9     // Catch:{ all -> 0x053b }
            double r9 = r30 * r6
            r4.mPosZ = r9     // Catch:{ all -> 0x053b }
            double r9 = r4.mPenumbraX     // Catch:{ all -> 0x053b }
            double r9 = -r9
            r4.mPenumbraX = r9     // Catch:{ all -> 0x053b }
            int r9 = r1.mVerticesCountBack     // Catch:{ all -> 0x053b }
            r5 = 1
            int r9 = r9 + r5
            r1.mVerticesCountBack = r9     // Catch:{ all -> 0x053b }
        L_0x030f:
            r9 = 0
        L_0x0310:
            boolean r10 = r1.mFlipTexture     // Catch:{ all -> 0x053b }
            if (r9 == r10) goto L_0x0334
            double r9 = r4.mTexX     // Catch:{ all -> 0x053b }
            android.graphics.RectF r11 = r1.mTextureRectFront     // Catch:{ all -> 0x053b }
            float r11 = r11.right     // Catch:{ all -> 0x053b }
            double r11 = (double) r11     // Catch:{ all -> 0x053b }
            double r9 = r9 * r11
            r4.mTexX = r9     // Catch:{ all -> 0x053b }
            double r9 = r4.mTexY     // Catch:{ all -> 0x053b }
            android.graphics.RectF r11 = r1.mTextureRectFront     // Catch:{ all -> 0x053b }
            float r11 = r11.bottom     // Catch:{ all -> 0x053b }
            double r11 = (double) r11     // Catch:{ all -> 0x053b }
            double r9 = r9 * r11
            r4.mTexY = r9     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlPage r9 = r1.mTexturePage     // Catch:{ all -> 0x053b }
            r5 = 1
            int r9 = r9.getColor(r5)     // Catch:{ all -> 0x053b }
            r4.mColor = r9     // Catch:{ all -> 0x053b }
            goto L_0x0353
        L_0x0334:
            double r9 = r4.mTexX     // Catch:{ all -> 0x053b }
            android.graphics.RectF r11 = r1.mTextureRectBack     // Catch:{ all -> 0x053b }
            float r11 = r11.right     // Catch:{ all -> 0x053b }
            double r11 = (double) r11     // Catch:{ all -> 0x053b }
            double r9 = r9 * r11
            r4.mTexX = r9     // Catch:{ all -> 0x053b }
            double r9 = r4.mTexY     // Catch:{ all -> 0x053b }
            android.graphics.RectF r11 = r1.mTextureRectBack     // Catch:{ all -> 0x053b }
            float r11 = r11.bottom     // Catch:{ all -> 0x053b }
            double r11 = (double) r11     // Catch:{ all -> 0x053b }
            double r9 = r9 * r11
            r4.mTexY = r9     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlPage r9 = r1.mTexturePage     // Catch:{ all -> 0x053b }
            r10 = 2
            int r9 = r9.getColor(r10)     // Catch:{ all -> 0x053b }
            r4.mColor = r9     // Catch:{ all -> 0x053b }
        L_0x0353:
            r9 = r16
            r4.rotateZ(r9)     // Catch:{ all -> 0x053b }
            float r11 = r0.x     // Catch:{ all -> 0x053b }
            double r11 = (double) r11     // Catch:{ all -> 0x053b }
            float r15 = r0.y     // Catch:{ all -> 0x053b }
            double r5 = (double) r15     // Catch:{ all -> 0x053b }
            r4.translate(r11, r5)     // Catch:{ all -> 0x053b }
            r1.addVertex(r4)     // Catch:{ all -> 0x053b }
            double r5 = r4.mPosZ     // Catch:{ all -> 0x053b }
            r11 = 0
            int r15 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r15 <= 0) goto L_0x03ba
            double r5 = r4.mPosZ     // Catch:{ all -> 0x053b }
            int r15 = (r5 > r30 ? 1 : (r5 == r30 ? 0 : -1))
            if (r15 > 0) goto L_0x03ba
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r5 = r1.mArrTempShadowVertices     // Catch:{ all -> 0x053b }
            r6 = 0
            java.lang.Object r5 = r5.remove(r6)     // Catch:{ all -> 0x053b }
            r6 = r5
            fi.harism.curl.CurlMesh$ShadowVertex r6 = (fi.harism.curl.CurlMesh.ShadowVertex) r6     // Catch:{ all -> 0x053b }
            double r11 = r4.mPosX     // Catch:{ all -> 0x053b }
            r6.mPosX = r11     // Catch:{ all -> 0x053b }
            double r11 = r4.mPosY     // Catch:{ all -> 0x053b }
            r6.mPosY = r11     // Catch:{ all -> 0x053b }
            double r11 = r4.mPosZ     // Catch:{ all -> 0x053b }
            r6.mPosZ = r11     // Catch:{ all -> 0x053b }
            double r11 = r4.mPosZ     // Catch:{ all -> 0x053b }
            r16 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r11 = r11 / r16
            float r5 = r2.x     // Catch:{ all -> 0x053b }
            float r5 = -r5
            r15 = r8
            double r7 = (double) r5     // Catch:{ all -> 0x053b }
            double r11 = r11 * r7
            r6.mPenumbraX = r11     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosZ     // Catch:{ all -> 0x053b }
            double r7 = r7 / r16
            float r5 = r2.y     // Catch:{ all -> 0x053b }
            float r5 = -r5
            double r11 = (double) r5     // Catch:{ all -> 0x053b }
            double r7 = r7 * r11
            r6.mPenumbraY = r7     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosZ     // Catch:{ all -> 0x053b }
            double r7 = r7 / r30
            r6.mPenumbraColor = r7     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r5 = r1.mArrDropShadowVertices     // Catch:{ all -> 0x053b }
            int r5 = r5.size()     // Catch:{ all -> 0x053b }
            r7 = 1
            int r8 = r5 + 1
            r7 = 2
            int r8 = r8 / r7
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r7 = r1.mArrDropShadowVertices     // Catch:{ all -> 0x053b }
            r7.add(r8, r6)     // Catch:{ all -> 0x053b }
            goto L_0x03bb
        L_0x03ba:
            r15 = r8
        L_0x03bb:
            double r6 = r4.mPosZ     // Catch:{ all -> 0x053b }
            int r8 = (r6 > r30 ? 1 : (r6 == r30 ? 0 : -1))
            if (r8 <= 0) goto L_0x040b
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r6 = r1.mArrTempShadowVertices     // Catch:{ all -> 0x053b }
            r7 = 0
            java.lang.Object r6 = r6.remove(r7)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$ShadowVertex r6 = (fi.harism.curl.CurlMesh.ShadowVertex) r6     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosX     // Catch:{ all -> 0x053b }
            r6.mPosX = r7     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosY     // Catch:{ all -> 0x053b }
            r6.mPosY = r7     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosZ     // Catch:{ all -> 0x053b }
            r6.mPosZ = r7     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosZ     // Catch:{ all -> 0x053b }
            double r7 = r7 - r30
            r11 = 4613937818241073152(0x4008000000000000, double:3.0)
            double r7 = r7 / r11
            double r11 = r4.mPenumbraX     // Catch:{ all -> 0x053b }
            double r7 = r7 * r11
            r6.mPenumbraX = r7     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosZ     // Catch:{ all -> 0x053b }
            double r7 = r7 - r30
            r11 = 4613937818241073152(0x4008000000000000, double:3.0)
            double r7 = r7 / r11
            double r11 = r4.mPenumbraY     // Catch:{ all -> 0x053b }
            double r7 = r7 * r11
            r6.mPenumbraY = r7     // Catch:{ all -> 0x053b }
            double r7 = r4.mPosZ     // Catch:{ all -> 0x053b }
            double r7 = r7 - r30
            r11 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r11 = r11 * r30
            double r7 = r7 / r11
            r6.mPenumbraColor = r7     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r4 = r1.mArrSelfShadowVertices     // Catch:{ all -> 0x053b }
            int r4 = r4.size()     // Catch:{ all -> 0x053b }
            r5 = 1
            int r4 = r4 + r5
            r7 = 2
            int r4 = r4 / r7
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r7 = r1.mArrSelfShadowVertices     // Catch:{ all -> 0x053b }
            r7.add(r4, r6)     // Catch:{ all -> 0x053b }
            goto L_0x040c
        L_0x040b:
            r5 = 1
        L_0x040c:
            r16 = r9
            r8 = r15
            goto L_0x0270
        L_0x0411:
            r15 = r8
            r9 = r16
            r5 = 1
            int r8 = r15 + 1
            r4 = r13
            r6 = r25
            r10 = 1
            r11 = 0
            goto L_0x0191
        L_0x041f:
            java.nio.FloatBuffer r0 = r1.mBufVertices     // Catch:{ all -> 0x053b }
            r2 = 0
            r0.position(r2)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r0 = r1.mBufColors     // Catch:{ all -> 0x053b }
            r0.position(r2)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r0 = r1.mBufTexCoords     // Catch:{ all -> 0x053b }
            r0.position(r2)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r0 = r1.mBufShadowColors     // Catch:{ all -> 0x053b }
            r0.position(r2)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r0 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            r0.position(r2)     // Catch:{ all -> 0x053b }
            r1.mDropShadowCount = r2     // Catch:{ all -> 0x053b }
            r0 = 0
        L_0x043c:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r2 = r1.mArrDropShadowVertices     // Catch:{ all -> 0x053b }
            int r2 = r2.size()     // Catch:{ all -> 0x053b }
            if (r0 >= r2) goto L_0x04b3
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r2 = r1.mArrDropShadowVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$ShadowVertex r2 = (fi.harism.curl.CurlMesh.ShadowVertex) r2     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r4 = r2.mPosX     // Catch:{ all -> 0x053b }
            float r4 = (float) r4     // Catch:{ all -> 0x053b }
            r3.put(r4)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r4 = r2.mPosY     // Catch:{ all -> 0x053b }
            float r4 = (float) r4     // Catch:{ all -> 0x053b }
            r3.put(r4)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r4 = r2.mPosZ     // Catch:{ all -> 0x053b }
            float r4 = (float) r4     // Catch:{ all -> 0x053b }
            r3.put(r4)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r4 = r2.mPosX     // Catch:{ all -> 0x053b }
            double r6 = r2.mPenumbraX     // Catch:{ all -> 0x053b }
            double r4 = r4 + r6
            float r4 = (float) r4     // Catch:{ all -> 0x053b }
            r3.put(r4)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r4 = r2.mPosY     // Catch:{ all -> 0x053b }
            double r6 = r2.mPenumbraY     // Catch:{ all -> 0x053b }
            double r4 = r4 + r6
            float r4 = (float) r4     // Catch:{ all -> 0x053b }
            r3.put(r4)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r3 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r4 = r2.mPosZ     // Catch:{ all -> 0x053b }
            float r4 = (float) r4     // Catch:{ all -> 0x053b }
            r3.put(r4)     // Catch:{ all -> 0x053b }
            r3 = 0
        L_0x0483:
            r4 = 4
            if (r3 >= r4) goto L_0x04a3
            float[] r4 = SHADOW_OUTER_COLOR     // Catch:{ all -> 0x053b }
            r4 = r4[r3]     // Catch:{ all -> 0x053b }
            double r4 = (double) r4     // Catch:{ all -> 0x053b }
            float[] r7 = SHADOW_INNER_COLOR     // Catch:{ all -> 0x053b }
            r7 = r7[r3]     // Catch:{ all -> 0x053b }
            float[] r8 = SHADOW_OUTER_COLOR     // Catch:{ all -> 0x053b }
            r8 = r8[r3]     // Catch:{ all -> 0x053b }
            float r7 = r7 - r8
            double r7 = (double) r7     // Catch:{ all -> 0x053b }
            double r9 = r2.mPenumbraColor     // Catch:{ all -> 0x053b }
            double r7 = r7 * r9
            double r4 = r4 + r7
            java.nio.FloatBuffer r7 = r1.mBufShadowColors     // Catch:{ all -> 0x053b }
            float r4 = (float) r4     // Catch:{ all -> 0x053b }
            r7.put(r4)     // Catch:{ all -> 0x053b }
            int r3 = r3 + 1
            goto L_0x0483
        L_0x04a3:
            java.nio.FloatBuffer r2 = r1.mBufShadowColors     // Catch:{ all -> 0x053b }
            float[] r3 = SHADOW_OUTER_COLOR     // Catch:{ all -> 0x053b }
            r2.put(r3)     // Catch:{ all -> 0x053b }
            int r2 = r1.mDropShadowCount     // Catch:{ all -> 0x053b }
            r3 = 2
            int r2 = r2 + r3
            r1.mDropShadowCount = r2     // Catch:{ all -> 0x053b }
            int r0 = r0 + 1
            goto L_0x043c
        L_0x04b3:
            r0 = 0
            r1.mSelfShadowCount = r0     // Catch:{ all -> 0x053b }
            r4 = 0
        L_0x04b7:
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r0 = r1.mArrSelfShadowVertices     // Catch:{ all -> 0x053b }
            int r0 = r0.size()     // Catch:{ all -> 0x053b }
            if (r4 >= r0) goto L_0x052e
            fi.harism.curl.CurlMesh$Array<fi.harism.curl.CurlMesh$ShadowVertex> r0 = r1.mArrSelfShadowVertices     // Catch:{ all -> 0x053b }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x053b }
            fi.harism.curl.CurlMesh$ShadowVertex r0 = (fi.harism.curl.CurlMesh.ShadowVertex) r0     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r2 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r7 = r0.mPosX     // Catch:{ all -> 0x053b }
            float r3 = (float) r7     // Catch:{ all -> 0x053b }
            r2.put(r3)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r2 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r7 = r0.mPosY     // Catch:{ all -> 0x053b }
            float r3 = (float) r7     // Catch:{ all -> 0x053b }
            r2.put(r3)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r2 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r7 = r0.mPosZ     // Catch:{ all -> 0x053b }
            float r3 = (float) r7     // Catch:{ all -> 0x053b }
            r2.put(r3)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r2 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r7 = r0.mPosX     // Catch:{ all -> 0x053b }
            double r9 = r0.mPenumbraX     // Catch:{ all -> 0x053b }
            double r7 = r7 + r9
            float r3 = (float) r7     // Catch:{ all -> 0x053b }
            r2.put(r3)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r2 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r7 = r0.mPosY     // Catch:{ all -> 0x053b }
            double r9 = r0.mPenumbraY     // Catch:{ all -> 0x053b }
            double r7 = r7 + r9
            float r3 = (float) r7     // Catch:{ all -> 0x053b }
            r2.put(r3)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r2 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            double r7 = r0.mPosZ     // Catch:{ all -> 0x053b }
            float r3 = (float) r7     // Catch:{ all -> 0x053b }
            r2.put(r3)     // Catch:{ all -> 0x053b }
            r2 = 0
            r3 = 4
        L_0x04ff:
            if (r2 >= r3) goto L_0x051e
            float[] r5 = SHADOW_OUTER_COLOR     // Catch:{ all -> 0x053b }
            r5 = r5[r2]     // Catch:{ all -> 0x053b }
            double r5 = (double) r5     // Catch:{ all -> 0x053b }
            float[] r7 = SHADOW_INNER_COLOR     // Catch:{ all -> 0x053b }
            r7 = r7[r2]     // Catch:{ all -> 0x053b }
            float[] r8 = SHADOW_OUTER_COLOR     // Catch:{ all -> 0x053b }
            r8 = r8[r2]     // Catch:{ all -> 0x053b }
            float r7 = r7 - r8
            double r7 = (double) r7     // Catch:{ all -> 0x053b }
            double r9 = r0.mPenumbraColor     // Catch:{ all -> 0x053b }
            double r7 = r7 * r9
            double r5 = r5 + r7
            java.nio.FloatBuffer r7 = r1.mBufShadowColors     // Catch:{ all -> 0x053b }
            float r5 = (float) r5     // Catch:{ all -> 0x053b }
            r7.put(r5)     // Catch:{ all -> 0x053b }
            int r2 = r2 + 1
            goto L_0x04ff
        L_0x051e:
            java.nio.FloatBuffer r0 = r1.mBufShadowColors     // Catch:{ all -> 0x053b }
            float[] r2 = SHADOW_OUTER_COLOR     // Catch:{ all -> 0x053b }
            r0.put(r2)     // Catch:{ all -> 0x053b }
            int r0 = r1.mSelfShadowCount     // Catch:{ all -> 0x053b }
            r2 = 2
            int r0 = r0 + r2
            r1.mSelfShadowCount = r0     // Catch:{ all -> 0x053b }
            int r4 = r4 + 1
            goto L_0x04b7
        L_0x052e:
            java.nio.FloatBuffer r0 = r1.mBufShadowColors     // Catch:{ all -> 0x053b }
            r2 = 0
            r0.position(r2)     // Catch:{ all -> 0x053b }
            java.nio.FloatBuffer r0 = r1.mBufShadowVertices     // Catch:{ all -> 0x053b }
            r0.position(r2)     // Catch:{ all -> 0x053b }
            monitor-exit(r27)
            return
        L_0x053b:
            r0 = move-exception
            monitor-exit(r27)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fi.harism.curl.CurlMesh.curl(android.graphics.PointF, android.graphics.PointF, double):void");
    }

    private Array<Vertex> getIntersections(Array<Vertex> array, int[][] iArr, double d) {
        int i;
        Array<Vertex> array2 = array;
        int[][] iArr2 = iArr;
        double d2 = d;
        this.mArrIntersections.clear();
        int i2 = 0;
        int i3 = 0;
        while (i3 < iArr2.length) {
            Vertex vertex = array2.get(iArr2[i3][i2]);
            Vertex vertex2 = array2.get(iArr2[i3][1]);
            if (vertex.mPosX <= d2 || vertex2.mPosX >= d2) {
                i = i3;
            } else {
                double d3 = (d2 - vertex2.mPosX) / (vertex.mPosX - vertex2.mPosX);
                Vertex remove = this.mArrTempVertices.remove(i2);
                remove.set(vertex2);
                remove.mPosX = d2;
                i = i3;
                remove.mPosY += (vertex.mPosY - vertex2.mPosY) * d3;
                remove.mTexX += (vertex.mTexX - vertex2.mTexX) * d3;
                remove.mTexY += (vertex.mTexY - vertex2.mTexY) * d3;
                remove.mPenumbraX += (vertex.mPenumbraX - vertex2.mPenumbraX) * d3;
                remove.mPenumbraY += (vertex.mPenumbraY - vertex2.mPenumbraY) * d3;
                this.mArrIntersections.add(remove);
            }
            i3 = i + 1;
            i2 = 0;
        }
        return this.mArrIntersections;
    }

    public synchronized CurlPage getTexturePage() {
        return this.mTexturePage;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onDrawFrame(javax.microedition.khronos.opengles.GL10 r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            monitor-enter(r16)
            int[] r2 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            r3 = 2
            r4 = 3553(0xde1, float:4.979E-42)
            r5 = 0
            if (r2 != 0) goto L_0x003b
            int[] r2 = new int[r3]     // Catch:{ all -> 0x016c }
            r1.mTextureIds = r2     // Catch:{ all -> 0x016c }
            r0.glGenTextures(r3, r2, r5)     // Catch:{ all -> 0x016c }
            int[] r2 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            int r6 = r2.length     // Catch:{ all -> 0x016c }
            r7 = 0
        L_0x0018:
            if (r7 >= r6) goto L_0x003b
            r8 = r2[r7]     // Catch:{ all -> 0x016c }
            r0.glBindTexture(r4, r8)     // Catch:{ all -> 0x016c }
            r8 = 10241(0x2801, float:1.435E-41)
            r9 = 1175977984(0x46180000, float:9728.0)
            r0.glTexParameterf(r4, r8, r9)     // Catch:{ all -> 0x016c }
            r8 = 10240(0x2800, float:1.4349E-41)
            r0.glTexParameterf(r4, r8, r9)     // Catch:{ all -> 0x016c }
            r8 = 10242(0x2802, float:1.4352E-41)
            r9 = 1191259904(0x47012f00, float:33071.0)
            r0.glTexParameterf(r4, r8, r9)     // Catch:{ all -> 0x016c }
            r8 = 10243(0x2803, float:1.4354E-41)
            r0.glTexParameterf(r4, r8, r9)     // Catch:{ all -> 0x016c }
            int r7 = r7 + 1
            goto L_0x0018
        L_0x003b:
            fi.harism.curl.CurlPage r2 = r1.mTexturePage     // Catch:{ all -> 0x016c }
            boolean r2 = r2.getTexturesChanged()     // Catch:{ all -> 0x016c }
            r6 = 1
            if (r2 == 0) goto L_0x0082
            int[] r2 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            r2 = r2[r5]     // Catch:{ all -> 0x016c }
            r0.glBindTexture(r4, r2)     // Catch:{ all -> 0x016c }
            fi.harism.curl.CurlPage r2 = r1.mTexturePage     // Catch:{ all -> 0x016c }
            android.graphics.RectF r7 = r1.mTextureRectFront     // Catch:{ all -> 0x016c }
            android.graphics.Bitmap r2 = r2.getTexture((android.graphics.RectF) r7, (int) r6)     // Catch:{ all -> 0x016c }
            android.opengl.GLUtils.texImage2D(r4, r5, r2, r5)     // Catch:{ all -> 0x016c }
            fi.harism.curl.CurlPage r2 = r1.mTexturePage     // Catch:{ all -> 0x016c }
            boolean r2 = r2.hasBackTexture()     // Catch:{ all -> 0x016c }
            r1.mTextureBack = r2     // Catch:{ all -> 0x016c }
            if (r2 == 0) goto L_0x0073
            int[] r2 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            r2 = r2[r6]     // Catch:{ all -> 0x016c }
            r0.glBindTexture(r4, r2)     // Catch:{ all -> 0x016c }
            fi.harism.curl.CurlPage r2 = r1.mTexturePage     // Catch:{ all -> 0x016c }
            android.graphics.RectF r7 = r1.mTextureRectBack     // Catch:{ all -> 0x016c }
            android.graphics.Bitmap r2 = r2.getTexture((android.graphics.RectF) r7, (int) r3)     // Catch:{ all -> 0x016c }
            android.opengl.GLUtils.texImage2D(r4, r5, r2, r5)     // Catch:{ all -> 0x016c }
            goto L_0x007a
        L_0x0073:
            android.graphics.RectF r2 = r1.mTextureRectBack     // Catch:{ all -> 0x016c }
            android.graphics.RectF r7 = r1.mTextureRectFront     // Catch:{ all -> 0x016c }
            r2.set(r7)     // Catch:{ all -> 0x016c }
        L_0x007a:
            fi.harism.curl.CurlPage r2 = r1.mTexturePage     // Catch:{ all -> 0x016c }
            r2.recycle()     // Catch:{ all -> 0x016c }
            r16.reset()     // Catch:{ all -> 0x016c }
        L_0x0082:
            r2 = 32884(0x8074, float:4.608E-41)
            r0.glEnableClientState(r2)     // Catch:{ all -> 0x016c }
            r0.glDisable(r4)     // Catch:{ all -> 0x016c }
            r7 = 3042(0xbe2, float:4.263E-42)
            r0.glEnable(r7)     // Catch:{ all -> 0x016c }
            r8 = 771(0x303, float:1.08E-42)
            r9 = 770(0x302, float:1.079E-42)
            r0.glBlendFunc(r9, r8)     // Catch:{ all -> 0x016c }
            r10 = 32886(0x8076, float:4.6083E-41)
            r0.glEnableClientState(r10)     // Catch:{ all -> 0x016c }
            java.nio.FloatBuffer r11 = r1.mBufShadowColors     // Catch:{ all -> 0x016c }
            r12 = 4
            r13 = 5126(0x1406, float:7.183E-42)
            r0.glColorPointer(r12, r13, r5, r11)     // Catch:{ all -> 0x016c }
            java.nio.FloatBuffer r11 = r1.mBufShadowVertices     // Catch:{ all -> 0x016c }
            r14 = 3
            r0.glVertexPointer(r14, r13, r5, r11)     // Catch:{ all -> 0x016c }
            int r11 = r1.mDropShadowCount     // Catch:{ all -> 0x016c }
            r15 = 5
            r0.glDrawArrays(r15, r5, r11)     // Catch:{ all -> 0x016c }
            r0.glDisableClientState(r10)     // Catch:{ all -> 0x016c }
            r0.glDisable(r7)     // Catch:{ all -> 0x016c }
            r11 = 32888(0x8078, float:4.6086E-41)
            r0.glEnableClientState(r11)     // Catch:{ all -> 0x016c }
            java.nio.FloatBuffer r2 = r1.mBufTexCoords     // Catch:{ all -> 0x016c }
            r0.glTexCoordPointer(r3, r13, r5, r2)     // Catch:{ all -> 0x016c }
            java.nio.FloatBuffer r2 = r1.mBufVertices     // Catch:{ all -> 0x016c }
            r0.glVertexPointer(r14, r13, r5, r2)     // Catch:{ all -> 0x016c }
            r0.glEnableClientState(r10)     // Catch:{ all -> 0x016c }
            java.nio.FloatBuffer r2 = r1.mBufColors     // Catch:{ all -> 0x016c }
            r0.glColorPointer(r12, r13, r5, r2)     // Catch:{ all -> 0x016c }
            r0.glDisable(r4)     // Catch:{ all -> 0x016c }
            int r2 = r1.mVerticesCountFront     // Catch:{ all -> 0x016c }
            r0.glDrawArrays(r15, r5, r2)     // Catch:{ all -> 0x016c }
            r0.glEnable(r7)     // Catch:{ all -> 0x016c }
            r0.glEnable(r4)     // Catch:{ all -> 0x016c }
            boolean r2 = r1.mFlipTexture     // Catch:{ all -> 0x016c }
            if (r2 == 0) goto L_0x00ee
            boolean r2 = r1.mTextureBack     // Catch:{ all -> 0x016c }
            if (r2 != 0) goto L_0x00e6
            goto L_0x00ee
        L_0x00e6:
            int[] r2 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            r2 = r2[r6]     // Catch:{ all -> 0x016c }
            r0.glBindTexture(r4, r2)     // Catch:{ all -> 0x016c }
            goto L_0x00f5
        L_0x00ee:
            int[] r2 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            r2 = r2[r5]     // Catch:{ all -> 0x016c }
            r0.glBindTexture(r4, r2)     // Catch:{ all -> 0x016c }
        L_0x00f5:
            r0.glBlendFunc(r9, r8)     // Catch:{ all -> 0x016c }
            int r2 = r1.mVerticesCountFront     // Catch:{ all -> 0x016c }
            r0.glDrawArrays(r15, r5, r2)     // Catch:{ all -> 0x016c }
            r0.glDisable(r7)     // Catch:{ all -> 0x016c }
            r0.glDisable(r4)     // Catch:{ all -> 0x016c }
            int r2 = r1.mVerticesCountFront     // Catch:{ all -> 0x016c }
            int r2 = r2 - r3
            int r2 = java.lang.Math.max(r5, r2)     // Catch:{ all -> 0x016c }
            int r3 = r1.mVerticesCountFront     // Catch:{ all -> 0x016c }
            int r14 = r1.mVerticesCountBack     // Catch:{ all -> 0x016c }
            int r3 = r3 + r14
            int r3 = r3 - r2
            r0.glDrawArrays(r15, r2, r3)     // Catch:{ all -> 0x016c }
            r0.glEnable(r7)     // Catch:{ all -> 0x016c }
            r0.glEnable(r4)     // Catch:{ all -> 0x016c }
            boolean r14 = r1.mFlipTexture     // Catch:{ all -> 0x016c }
            if (r14 != 0) goto L_0x012a
            boolean r14 = r1.mTextureBack     // Catch:{ all -> 0x016c }
            if (r14 != 0) goto L_0x0122
            goto L_0x012a
        L_0x0122:
            int[] r14 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            r6 = r14[r6]     // Catch:{ all -> 0x016c }
            r0.glBindTexture(r4, r6)     // Catch:{ all -> 0x016c }
            goto L_0x0131
        L_0x012a:
            int[] r6 = r1.mTextureIds     // Catch:{ all -> 0x016c }
            r6 = r6[r5]     // Catch:{ all -> 0x016c }
            r0.glBindTexture(r4, r6)     // Catch:{ all -> 0x016c }
        L_0x0131:
            r0.glBlendFunc(r9, r8)     // Catch:{ all -> 0x016c }
            r0.glDrawArrays(r15, r2, r3)     // Catch:{ all -> 0x016c }
            r0.glDisable(r7)     // Catch:{ all -> 0x016c }
            r0.glDisable(r4)     // Catch:{ all -> 0x016c }
            r0.glDisableClientState(r11)     // Catch:{ all -> 0x016c }
            r0.glDisableClientState(r10)     // Catch:{ all -> 0x016c }
            r0.glEnable(r7)     // Catch:{ all -> 0x016c }
            r0.glBlendFunc(r9, r8)     // Catch:{ all -> 0x016c }
            r0.glEnableClientState(r10)     // Catch:{ all -> 0x016c }
            java.nio.FloatBuffer r2 = r1.mBufShadowColors     // Catch:{ all -> 0x016c }
            r0.glColorPointer(r12, r13, r5, r2)     // Catch:{ all -> 0x016c }
            java.nio.FloatBuffer r2 = r1.mBufShadowVertices     // Catch:{ all -> 0x016c }
            r3 = 3
            r0.glVertexPointer(r3, r13, r5, r2)     // Catch:{ all -> 0x016c }
            int r2 = r1.mDropShadowCount     // Catch:{ all -> 0x016c }
            int r3 = r1.mSelfShadowCount     // Catch:{ all -> 0x016c }
            r0.glDrawArrays(r15, r2, r3)     // Catch:{ all -> 0x016c }
            r0.glDisableClientState(r10)     // Catch:{ all -> 0x016c }
            r0.glDisable(r7)     // Catch:{ all -> 0x016c }
            r2 = 32884(0x8074, float:4.608E-41)
            r0.glDisableClientState(r2)     // Catch:{ all -> 0x016c }
            monitor-exit(r16)
            return
        L_0x016c:
            r0 = move-exception
            monitor-exit(r16)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fi.harism.curl.CurlMesh.onDrawFrame(javax.microedition.khronos.opengles.GL10):void");
    }

    public synchronized void reset() {
        this.mBufVertices.position(0);
        this.mBufColors.position(0);
        this.mBufTexCoords.position(0);
        for (int i = 0; i < 4; i++) {
            Vertex vertex = this.mArrTempVertices.get(0);
            vertex.set(this.mRectangle[i]);
            if (this.mFlipTexture) {
                vertex.mTexX *= (double) this.mTextureRectBack.right;
                vertex.mTexY *= (double) this.mTextureRectBack.bottom;
                vertex.mColor = this.mTexturePage.getColor(2);
            } else {
                vertex.mTexX *= (double) this.mTextureRectFront.right;
                vertex.mTexY *= (double) this.mTextureRectFront.bottom;
                vertex.mColor = this.mTexturePage.getColor(1);
            }
            addVertex(vertex);
        }
        this.mVerticesCountFront = 4;
        this.mVerticesCountBack = 0;
        this.mBufVertices.position(0);
        this.mBufColors.position(0);
        this.mBufTexCoords.position(0);
        this.mSelfShadowCount = 0;
        this.mDropShadowCount = 0;
    }

    public synchronized void resetTexture() {
        this.mTextureIds = null;
    }

    public synchronized void setFlipTexture(boolean z) {
        this.mFlipTexture = z;
        if (z) {
            setTexCoords(1.0f, 0.0f, 0.0f, 1.0f);
        } else {
            setTexCoords(0.0f, 0.0f, 1.0f, 1.0f);
        }
    }

    public void setRect(RectF rectF) {
        this.mRectangle[0].mPosX = (double) rectF.left;
        this.mRectangle[0].mPosY = (double) rectF.top;
        this.mRectangle[1].mPosX = (double) rectF.left;
        this.mRectangle[1].mPosY = (double) rectF.bottom;
        this.mRectangle[2].mPosX = (double) rectF.right;
        this.mRectangle[2].mPosY = (double) rectF.top;
        this.mRectangle[3].mPosX = (double) rectF.right;
        this.mRectangle[3].mPosY = (double) rectF.bottom;
    }

    private synchronized void setTexCoords(float f, float f2, float f3, float f4) {
        double d = (double) f;
        this.mRectangle[0].mTexX = d;
        double d2 = (double) f2;
        this.mRectangle[0].mTexY = d2;
        this.mRectangle[1].mTexX = d;
        double d3 = (double) f4;
        this.mRectangle[1].mTexY = d3;
        double d4 = (double) f3;
        this.mRectangle[2].mTexX = d4;
        this.mRectangle[2].mTexY = d2;
        this.mRectangle[3].mTexX = d4;
        this.mRectangle[3].mTexY = d3;
    }

    private class Array<T> {
        private Object[] mArray;
        private int mCapacity;
        private int mSize;

        public Array(int i) {
            this.mCapacity = i;
            this.mArray = new Object[i];
        }

        public void add(int i, T t) {
            int i2;
            if (i < 0 || i > (i2 = this.mSize) || i2 >= this.mCapacity) {
                throw new IndexOutOfBoundsException();
            }
            while (i2 > i) {
                Object[] objArr = this.mArray;
                objArr[i2] = objArr[i2 - 1];
                i2--;
            }
            this.mArray[i] = t;
            this.mSize++;
        }

        public void add(T t) {
            int i = this.mSize;
            if (i < this.mCapacity) {
                Object[] objArr = this.mArray;
                this.mSize = i + 1;
                objArr[i] = t;
                return;
            }
            throw new IndexOutOfBoundsException();
        }

        public void addAll(Array<T> array) {
            if (this.mSize + array.size() <= this.mCapacity) {
                for (int i = 0; i < array.size(); i++) {
                    Object[] objArr = this.mArray;
                    int i2 = this.mSize;
                    this.mSize = i2 + 1;
                    objArr[i2] = array.get(i);
                }
                return;
            }
            throw new IndexOutOfBoundsException();
        }

        public void clear() {
            this.mSize = 0;
        }

        public T get(int i) {
            if (i >= 0 && i < this.mSize) {
                return this.mArray[i];
            }
            throw new IndexOutOfBoundsException();
        }

        public T remove(int i) {
            if (i < 0 || i >= this.mSize) {
                throw new IndexOutOfBoundsException();
            }
            T t = this.mArray[i];
            while (true) {
                int i2 = this.mSize;
                if (i < i2 - 1) {
                    Object[] objArr = this.mArray;
                    int i3 = i + 1;
                    objArr[i] = objArr[i3];
                    i = i3;
                } else {
                    this.mSize = i2 - 1;
                    return t;
                }
            }
        }

        public int size() {
            return this.mSize;
        }
    }

    private class ShadowVertex {
        public double mPenumbraColor;
        public double mPenumbraX;
        public double mPenumbraY;
        public double mPosX;
        public double mPosY;
        public double mPosZ;

        private ShadowVertex() {
        }
    }

    private class Vertex {
        public int mColor;
        public float mColorFactor = 1.0f;
        public double mPenumbraX;
        public double mPenumbraY;
        public double mPosX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        public double mPosY = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        public double mPosZ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        public double mTexX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        public double mTexY = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

        public Vertex() {
        }

        public void rotateZ(double d) {
            double cos = Math.cos(d);
            double sin = Math.sin(d);
            double d2 = this.mPosX;
            double d3 = this.mPosY;
            double d4 = -sin;
            this.mPosX = (d2 * cos) + (d3 * sin);
            this.mPosY = (d2 * d4) + (d3 * cos);
            double d5 = this.mPenumbraX;
            double d6 = this.mPenumbraY;
            this.mPenumbraX = (d5 * cos) + (sin * d6);
            this.mPenumbraY = (d5 * d4) + (d6 * cos);
        }

        public void set(Vertex vertex) {
            this.mPosX = vertex.mPosX;
            this.mPosY = vertex.mPosY;
            this.mPosZ = vertex.mPosZ;
            this.mTexX = vertex.mTexX;
            this.mTexY = vertex.mTexY;
            this.mPenumbraX = vertex.mPenumbraX;
            this.mPenumbraY = vertex.mPenumbraY;
            this.mColor = vertex.mColor;
            this.mColorFactor = vertex.mColorFactor;
        }

        public void translate(double d, double d2) {
            this.mPosX += d;
            this.mPosY += d2;
        }
    }
}
