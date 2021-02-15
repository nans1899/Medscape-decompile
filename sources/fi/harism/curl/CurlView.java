package fi.harism.curl;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import fi.harism.curl.CurlRenderer;

public class CurlView extends GLSurfaceView implements View.OnTouchListener, CurlRenderer.Observer {
    private static final int CURL_LEFT = 1;
    private static final int CURL_NONE = 0;
    private static final int CURL_RIGHT = 2;
    private static final int SET_CURL_TO_LEFT = 1;
    private static final int SET_CURL_TO_RIGHT = 2;
    public static final int SHOW_ONE_PAGE = 1;
    public static final int SHOW_TWO_PAGES = 2;
    private boolean checked;
    private CurlListener listener;
    private boolean mAllowLastPageCurl;
    private boolean mAnimate;
    private long mAnimationDurationTime;
    private PointF mAnimationSource;
    private long mAnimationStartTime;
    private PointF mAnimationTarget;
    private int mAnimationTargetEvent;
    private PointF mCurlDir;
    private PointF mCurlPos;
    private int mCurlState;
    private int mCurrentIndex;
    private PointF mDragStartPos;
    private boolean mEnableTouchPressure;
    private int mPageBitmapHeight;
    private int mPageBitmapWidth;
    private CurlMesh mPageCurl;
    private CurlMesh mPageLeft;
    private PageProvider mPageProvider;
    private CurlMesh mPageRight;
    private PointerPosition mPointerPos;
    private boolean mRenderLeftPage;
    private CurlRenderer mRenderer;
    private SizeChangedObserver mSizeChangedObserver;
    private boolean mUpdatePageIscalled;
    private int mViewMode;

    public interface CurlListener {
        void onAnimateFinish();

        void onChangePage(int i);
    }

    public interface PageProvider {
        int getPageCount();

        void updatePage(CurlPage curlPage, int i, int i2, int i3);
    }

    public interface SizeChangedObserver {
        void onSizeChanged(int i, int i2);
    }

    public void setListener(CurlListener curlListener) {
        this.listener = curlListener;
    }

    public CurlView(Context context) {
        super(context);
        this.mAllowLastPageCurl = true;
        this.mUpdatePageIscalled = false;
        this.mAnimate = false;
        this.mAnimationDurationTime = 300;
        this.mAnimationSource = new PointF();
        this.mAnimationTarget = new PointF();
        this.mCurlDir = new PointF();
        this.mCurlPos = new PointF();
        this.mCurlState = 0;
        this.mCurrentIndex = 0;
        this.mDragStartPos = new PointF();
        this.mEnableTouchPressure = false;
        this.mPageBitmapHeight = -1;
        this.mPageBitmapWidth = -1;
        this.mPointerPos = new PointerPosition();
        this.mRenderLeftPage = true;
        this.mViewMode = 1;
        init(context);
    }

    public CurlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAllowLastPageCurl = true;
        this.mUpdatePageIscalled = false;
        this.mAnimate = false;
        this.mAnimationDurationTime = 300;
        this.mAnimationSource = new PointF();
        this.mAnimationTarget = new PointF();
        this.mCurlDir = new PointF();
        this.mCurlPos = new PointF();
        this.mCurlState = 0;
        this.mCurrentIndex = 0;
        this.mDragStartPos = new PointF();
        this.mEnableTouchPressure = false;
        this.mPageBitmapHeight = -1;
        this.mPageBitmapWidth = -1;
        this.mPointerPos = new PointerPosition();
        this.mRenderLeftPage = true;
        this.mViewMode = 1;
        init(context);
    }

    public CurlView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public int getCurrentIndex() {
        return this.mCurrentIndex;
    }

    private void init(Context context) {
        CurlRenderer curlRenderer = new CurlRenderer(this);
        this.mRenderer = curlRenderer;
        setRenderer(curlRenderer);
        setRenderMode(0);
        setOnTouchListener(this);
        this.mPageLeft = new CurlMesh(10);
        this.mPageRight = new CurlMesh(10);
        this.mPageCurl = new CurlMesh(10);
        this.mPageLeft.setFlipTexture(true);
        this.mPageRight.setFlipTexture(false);
    }

    public void onDrawFrame() {
        if (this.mAnimate) {
            long currentTimeMillis = System.currentTimeMillis();
            int i = 0;
            if (currentTimeMillis >= this.mAnimationStartTime + this.mAnimationDurationTime) {
                int i2 = this.mAnimationTargetEvent;
                if (i2 == 2) {
                    CurlMesh curlMesh = this.mPageCurl;
                    CurlMesh curlMesh2 = this.mPageRight;
                    curlMesh.setRect(this.mRenderer.getPageRect(2));
                    curlMesh.setFlipTexture(false);
                    curlMesh.reset();
                    this.mRenderer.removeCurlMesh(curlMesh2);
                    this.mPageCurl = curlMesh2;
                    this.mPageRight = curlMesh;
                    if (this.mCurlState == 1) {
                        this.mCurrentIndex--;
                    }
                    this.checked = false;
                } else if (i2 == 1) {
                    CurlMesh curlMesh3 = this.mPageCurl;
                    CurlMesh curlMesh4 = this.mPageLeft;
                    curlMesh3.setRect(this.mRenderer.getPageRect(1));
                    curlMesh3.setFlipTexture(true);
                    curlMesh3.reset();
                    this.mRenderer.removeCurlMesh(curlMesh4);
                    if (!this.mRenderLeftPage) {
                        this.mRenderer.removeCurlMesh(curlMesh3);
                    }
                    this.mPageCurl = curlMesh4;
                    this.mPageLeft = curlMesh3;
                    if (this.mCurlState == 2) {
                        this.mCurrentIndex++;
                    }
                    this.checked = false;
                }
                this.mCurlState = 0;
                this.mAnimate = false;
                requestRender();
                CurlListener curlListener = this.listener;
                if (curlListener != null) {
                    curlListener.onAnimateFinish();
                    return;
                }
                return;
            }
            CurlListener curlListener2 = this.listener;
            if (curlListener2 != null && !this.checked) {
                int i3 = this.mCurrentIndex + ((this.mCurlState == 2 && this.mAnimationTargetEvent == 1) ? 1 : 0);
                if (this.mCurlState == 1 && this.mAnimationTargetEvent == 2) {
                    i = 1;
                }
                curlListener2.onChangePage(i3 - i);
            }
            this.checked = true;
            this.mPointerPos.mPos.set(this.mAnimationSource);
            float f = 1.0f - (((float) (currentTimeMillis - this.mAnimationStartTime)) / ((float) this.mAnimationDurationTime));
            float f2 = 1.0f - (((f * f) * f) * (3.0f - (f * 2.0f)));
            this.mPointerPos.mPos.x += (this.mAnimationTarget.x - this.mAnimationSource.x) * f2;
            this.mPointerPos.mPos.y += (this.mAnimationTarget.y - this.mAnimationSource.y) * f2;
            updateCurlPos(this.mPointerPos);
        }
    }

    public void onPageSizeChanged(int i, int i2) {
        this.mPageBitmapWidth = i;
        this.mPageBitmapHeight = i2;
        updatePages();
        requestRender();
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        requestRender();
        SizeChangedObserver sizeChangedObserver = this.mSizeChangedObserver;
        if (sizeChangedObserver != null) {
            sizeChangedObserver.onSizeChanged(i, i2);
        }
    }

    public void onSurfaceCreated() {
        this.mPageLeft.resetTexture();
        this.mPageRight.resetTexture();
        this.mPageCurl.resetTexture();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        if (r9 != 3) goto L_0x0196;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            boolean r8 = r7.mAnimate
            r0 = 0
            if (r8 != 0) goto L_0x0197
            fi.harism.curl.CurlView$PageProvider r8 = r7.mPageProvider
            if (r8 != 0) goto L_0x000b
            goto L_0x0197
        L_0x000b:
            r7.bringToFront()
            fi.harism.curl.CurlRenderer r8 = r7.mRenderer
            r1 = 2
            android.graphics.RectF r8 = r8.getPageRect(r1)
            fi.harism.curl.CurlRenderer r2 = r7.mRenderer
            r3 = 1
            android.graphics.RectF r2 = r2.getPageRect(r3)
            fi.harism.curl.CurlView$PointerPosition r4 = r7.mPointerPos
            android.graphics.PointF r4 = r4.mPos
            float r5 = r9.getX()
            float r6 = r9.getY()
            r4.set(r5, r6)
            fi.harism.curl.CurlRenderer r4 = r7.mRenderer
            fi.harism.curl.CurlView$PointerPosition r5 = r7.mPointerPos
            android.graphics.PointF r5 = r5.mPos
            r4.translate(r5)
            boolean r4 = r7.mEnableTouchPressure
            if (r4 == 0) goto L_0x0041
            fi.harism.curl.CurlView$PointerPosition r4 = r7.mPointerPos
            float r5 = r9.getPressure()
            r4.mPressure = r5
            goto L_0x0048
        L_0x0041:
            fi.harism.curl.CurlView$PointerPosition r4 = r7.mPointerPos
            r5 = 1061997773(0x3f4ccccd, float:0.8)
            r4.mPressure = r5
        L_0x0048:
            int r9 = r9.getAction()
            r4 = 1073741824(0x40000000, float:2.0)
            if (r9 == 0) goto L_0x00ce
            if (r9 == r3) goto L_0x0059
            if (r9 == r1) goto L_0x0191
            r0 = 3
            if (r9 == r0) goto L_0x0059
            goto L_0x0196
        L_0x0059:
            int r9 = r7.mCurlState
            if (r9 == r3) goto L_0x005f
            if (r9 != r1) goto L_0x0196
        L_0x005f:
            android.graphics.PointF r9 = r7.mAnimationSource
            fi.harism.curl.CurlView$PointerPosition r0 = r7.mPointerPos
            android.graphics.PointF r0 = r0.mPos
            r9.set(r0)
            long r5 = java.lang.System.currentTimeMillis()
            r7.mAnimationStartTime = r5
            int r9 = r7.mViewMode
            if (r9 != r3) goto L_0x0082
            fi.harism.curl.CurlView$PointerPosition r9 = r7.mPointerPos
            android.graphics.PointF r9 = r9.mPos
            float r9 = r9.x
            float r0 = r8.left
            float r5 = r8.right
            float r0 = r0 + r5
            float r0 = r0 / r4
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 > 0) goto L_0x0092
        L_0x0082:
            int r9 = r7.mViewMode
            if (r9 != r1) goto L_0x00a8
            fi.harism.curl.CurlView$PointerPosition r9 = r7.mPointerPos
            android.graphics.PointF r9 = r9.mPos
            float r9 = r9.x
            float r0 = r8.left
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 <= 0) goto L_0x00a8
        L_0x0092:
            android.graphics.PointF r8 = r7.mAnimationTarget
            android.graphics.PointF r9 = r7.mDragStartPos
            r8.set(r9)
            android.graphics.PointF r8 = r7.mAnimationTarget
            fi.harism.curl.CurlRenderer r9 = r7.mRenderer
            android.graphics.RectF r9 = r9.getPageRect(r1)
            float r9 = r9.right
            r8.x = r9
            r7.mAnimationTargetEvent = r1
            goto L_0x00c7
        L_0x00a8:
            android.graphics.PointF r9 = r7.mAnimationTarget
            android.graphics.PointF r0 = r7.mDragStartPos
            r9.set(r0)
            int r9 = r7.mCurlState
            if (r9 == r1) goto L_0x00bf
            int r9 = r7.mViewMode
            if (r9 != r1) goto L_0x00b8
            goto L_0x00bf
        L_0x00b8:
            android.graphics.PointF r9 = r7.mAnimationTarget
            float r8 = r8.left
            r9.x = r8
            goto L_0x00c5
        L_0x00bf:
            android.graphics.PointF r8 = r7.mAnimationTarget
            float r9 = r2.left
            r8.x = r9
        L_0x00c5:
            r7.mAnimationTargetEvent = r3
        L_0x00c7:
            r7.mAnimate = r3
            r7.requestRender()
            goto L_0x0196
        L_0x00ce:
            android.graphics.PointF r9 = r7.mDragStartPos
            fi.harism.curl.CurlView$PointerPosition r5 = r7.mPointerPos
            android.graphics.PointF r5 = r5.mPos
            r9.set(r5)
            android.graphics.PointF r9 = r7.mDragStartPos
            float r9 = r9.y
            float r5 = r8.top
            int r9 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r9 <= 0) goto L_0x00e8
            android.graphics.PointF r9 = r7.mDragStartPos
            float r5 = r8.top
            r9.y = r5
            goto L_0x00f8
        L_0x00e8:
            android.graphics.PointF r9 = r7.mDragStartPos
            float r9 = r9.y
            float r5 = r8.bottom
            int r9 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r9 >= 0) goto L_0x00f8
            android.graphics.PointF r9 = r7.mDragStartPos
            float r5 = r8.bottom
            r9.y = r5
        L_0x00f8:
            int r9 = r7.mViewMode
            if (r9 != r1) goto L_0x0143
            android.graphics.PointF r9 = r7.mDragStartPos
            float r9 = r9.x
            float r4 = r8.left
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 >= 0) goto L_0x0115
            int r9 = r7.mCurrentIndex
            if (r9 <= 0) goto L_0x0115
            android.graphics.PointF r8 = r7.mDragStartPos
            float r9 = r2.left
            r8.x = r9
            r7.startCurl(r3)
            goto L_0x018c
        L_0x0115:
            android.graphics.PointF r9 = r7.mDragStartPos
            float r9 = r9.x
            float r2 = r8.left
            int r9 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r9 < 0) goto L_0x018c
            int r9 = r7.mCurrentIndex
            fi.harism.curl.CurlView$PageProvider r2 = r7.mPageProvider
            int r2 = r2.getPageCount()
            if (r9 >= r2) goto L_0x018c
            android.graphics.PointF r9 = r7.mDragStartPos
            float r8 = r8.right
            r9.x = r8
            boolean r8 = r7.mAllowLastPageCurl
            if (r8 != 0) goto L_0x013f
            int r8 = r7.mCurrentIndex
            fi.harism.curl.CurlView$PageProvider r9 = r7.mPageProvider
            int r9 = r9.getPageCount()
            int r9 = r9 - r3
            if (r8 < r9) goto L_0x013f
            return r0
        L_0x013f:
            r7.startCurl(r1)
            goto L_0x018c
        L_0x0143:
            if (r9 != r3) goto L_0x018c
            float r9 = r8.right
            float r2 = r8.left
            float r9 = r9 + r2
            float r9 = r9 / r4
            android.graphics.PointF r2 = r7.mDragStartPos
            float r2 = r2.x
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x0161
            int r2 = r7.mCurrentIndex
            if (r2 <= 0) goto L_0x0161
            android.graphics.PointF r9 = r7.mDragStartPos
            float r8 = r8.left
            r9.x = r8
            r7.startCurl(r3)
            goto L_0x018c
        L_0x0161:
            android.graphics.PointF r2 = r7.mDragStartPos
            float r2 = r2.x
            int r9 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r9 < 0) goto L_0x018c
            int r9 = r7.mCurrentIndex
            fi.harism.curl.CurlView$PageProvider r2 = r7.mPageProvider
            int r2 = r2.getPageCount()
            if (r9 >= r2) goto L_0x018c
            android.graphics.PointF r9 = r7.mDragStartPos
            float r8 = r8.right
            r9.x = r8
            boolean r8 = r7.mAllowLastPageCurl
            if (r8 != 0) goto L_0x0189
            int r8 = r7.mCurrentIndex
            fi.harism.curl.CurlView$PageProvider r9 = r7.mPageProvider
            int r9 = r9.getPageCount()
            int r9 = r9 - r3
            if (r8 < r9) goto L_0x0189
            return r0
        L_0x0189:
            r7.startCurl(r1)
        L_0x018c:
            int r8 = r7.mCurlState
            if (r8 != 0) goto L_0x0191
            return r0
        L_0x0191:
            fi.harism.curl.CurlView$PointerPosition r8 = r7.mPointerPos
            r7.updateCurlPos(r8)
        L_0x0196:
            return r3
        L_0x0197:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fi.harism.curl.CurlView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void setAllowLastPageCurl(boolean z) {
        this.mAllowLastPageCurl = z;
    }

    public void setBackgroundColor(int i) {
        this.mRenderer.setBackgroundColor(i);
        requestRender();
    }

    private void setCurlPos(PointF pointF, PointF pointF2, double d) {
        int i = this.mCurlState;
        if (i == 2 || (i == 1 && this.mViewMode == 1)) {
            RectF pageRect = this.mRenderer.getPageRect(2);
            if (pointF.x >= pageRect.right) {
                this.mPageCurl.reset();
                requestRender();
                return;
            }
            if (pointF.x < pageRect.left) {
                pointF.x = pageRect.left;
            }
            if (pointF2.y != 0.0f) {
                float f = pointF.y + (((pointF.x - pageRect.left) * pointF2.x) / pointF2.y);
                if (pointF2.y < 0.0f && f < pageRect.top) {
                    pointF2.x = pointF.y - pageRect.top;
                    pointF2.y = pageRect.left - pointF.x;
                } else if (pointF2.y > 0.0f && f > pageRect.bottom) {
                    pointF2.x = pageRect.bottom - pointF.y;
                    pointF2.y = pointF.x - pageRect.left;
                }
            }
        } else if (this.mCurlState == 1) {
            RectF pageRect2 = this.mRenderer.getPageRect(1);
            if (pointF.x <= pageRect2.left) {
                this.mPageCurl.reset();
                requestRender();
                return;
            }
            if (pointF.x > pageRect2.right) {
                pointF.x = pageRect2.right;
            }
            if (pointF2.y != 0.0f) {
                float f2 = pointF.y + (((pointF.x - pageRect2.right) * pointF2.x) / pointF2.y);
                if (pointF2.y < 0.0f && f2 < pageRect2.top) {
                    pointF2.x = pageRect2.top - pointF.y;
                    pointF2.y = pointF.x - pageRect2.right;
                } else if (pointF2.y > 0.0f && f2 > pageRect2.bottom) {
                    pointF2.x = pointF.y - pageRect2.bottom;
                    pointF2.y = pageRect2.right - pointF.x;
                }
            }
        }
        double sqrt = Math.sqrt((double) ((pointF2.x * pointF2.x) + (pointF2.y * pointF2.y)));
        if (sqrt != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            pointF2.x = (float) (((double) pointF2.x) / sqrt);
            pointF2.y = (float) (((double) pointF2.y) / sqrt);
            this.mPageCurl.curl(pointF, pointF2, d);
        } else {
            this.mPageCurl.reset();
        }
        requestRender();
    }

    public void setCurrentIndex(int i) {
        PageProvider pageProvider = this.mPageProvider;
        if (pageProvider == null || i < 0) {
            this.mCurrentIndex = 0;
        } else if (this.mAllowLastPageCurl) {
            this.mCurrentIndex = Math.min(i, pageProvider.getPageCount());
        } else {
            this.mCurrentIndex = Math.min(i, pageProvider.getPageCount() - 1);
        }
        updatePages();
        requestRender();
    }

    public void setEnableTouchPressure(boolean z) {
        this.mEnableTouchPressure = z;
    }

    public void setMargins(float f, float f2, float f3, float f4) {
        this.mRenderer.setMargins(f, f2, f3, f4);
    }

    public RectF getMargins() {
        return this.mRenderer.getMargins();
    }

    public void setPageProvider(PageProvider pageProvider) {
        this.mPageProvider = pageProvider;
        this.mCurrentIndex = 0;
        updatePages();
        requestRender();
    }

    public void setRenderLeftPage(boolean z) {
        this.mRenderLeftPage = z;
    }

    public void setSizeChangedObserver(SizeChangedObserver sizeChangedObserver) {
        this.mSizeChangedObserver = sizeChangedObserver;
    }

    public void setViewMode(int i) {
        if (i == 1) {
            this.mViewMode = i;
            this.mPageLeft.setFlipTexture(true);
            this.mRenderer.setViewMode(1);
        } else if (i == 2) {
            this.mViewMode = i;
            this.mPageLeft.setFlipTexture(false);
            this.mRenderer.setViewMode(2);
        }
    }

    private void startCurl(int i) {
        if (i == 1) {
            this.mRenderer.removeCurlMesh(this.mPageLeft);
            this.mRenderer.removeCurlMesh(this.mPageRight);
            this.mRenderer.removeCurlMesh(this.mPageCurl);
            CurlMesh curlMesh = this.mPageLeft;
            CurlMesh curlMesh2 = this.mPageCurl;
            this.mPageLeft = curlMesh2;
            this.mPageCurl = curlMesh;
            if (this.mCurrentIndex > 1) {
                if (this.mUpdatePageIscalled) {
                    updatePage(curlMesh2.getTexturePage(), this.mCurrentIndex - 2);
                    this.mUpdatePageIscalled = false;
                } else {
                    updatePage(curlMesh.getTexturePage(), this.mCurrentIndex - 1);
                }
                this.mPageLeft.setFlipTexture(true);
                this.mPageLeft.setRect(this.mRenderer.getPageRect(1));
                this.mPageLeft.reset();
                if (this.mRenderLeftPage) {
                    this.mRenderer.addCurlMesh(this.mPageLeft);
                }
            }
            this.mPageRight.setFlipTexture(false);
            this.mPageRight.setRect(this.mRenderer.getPageRect(2));
            this.mPageRight.reset();
            this.mRenderer.addCurlMesh(this.mPageRight);
            if (this.mCurrentIndex < this.mPageProvider.getPageCount()) {
                this.mPageRight.setFlipTexture(false);
                this.mPageRight.setRect(this.mRenderer.getPageRect(2));
                this.mPageRight.reset();
                this.mRenderer.addCurlMesh(this.mPageRight);
            }
            int i2 = this.mViewMode;
            if (i2 == 1 || (this.mCurlState == 1 && i2 == 2)) {
                this.mPageCurl.setRect(this.mRenderer.getPageRect(2));
                this.mPageCurl.setFlipTexture(false);
            } else {
                this.mPageCurl.setRect(this.mRenderer.getPageRect(1));
                this.mPageCurl.setFlipTexture(true);
            }
            this.mPageCurl.reset();
            this.mRenderer.addCurlMesh(this.mPageCurl);
            this.mCurlState = 1;
        } else if (i == 2) {
            this.mRenderer.removeCurlMesh(this.mPageLeft);
            this.mRenderer.removeCurlMesh(this.mPageRight);
            this.mRenderer.removeCurlMesh(this.mPageCurl);
            CurlMesh curlMesh3 = this.mPageRight;
            this.mPageRight = this.mPageCurl;
            this.mPageCurl = curlMesh3;
            updatePage(curlMesh3.getTexturePage(), this.mCurrentIndex);
            if (this.mCurrentIndex > 0) {
                this.mPageLeft.setFlipTexture(true);
                this.mPageLeft.setRect(this.mRenderer.getPageRect(1));
                this.mPageLeft.reset();
            }
            if (this.mRenderLeftPage) {
                this.mRenderer.addCurlMesh(this.mPageLeft);
            }
            if (this.mCurrentIndex < this.mPageProvider.getPageCount() - 1) {
                updatePage(this.mPageRight.getTexturePage(), this.mCurrentIndex + 1);
                this.mPageRight.setRect(this.mRenderer.getPageRect(2));
                this.mPageRight.setFlipTexture(false);
                this.mPageRight.reset();
                this.mRenderer.addCurlMesh(this.mPageRight);
            }
            this.mPageCurl.setRect(this.mRenderer.getPageRect(2));
            this.mPageCurl.setFlipTexture(false);
            this.mPageCurl.reset();
            this.mRenderer.addCurlMesh(this.mPageCurl);
            this.mCurlState = 2;
        }
    }

    private void updateCurlPos(PointerPosition pointerPosition) {
        PointerPosition pointerPosition2 = pointerPosition;
        double width = ((double) (this.mRenderer.getPageRect(2).width() / 3.0f)) * ((double) Math.max(1.0f - pointerPosition2.mPressure, 0.0f));
        this.mCurlPos.set(pointerPosition2.mPos);
        int i = this.mCurlState;
        if (i == 2 || (i == 1 && this.mViewMode == 2)) {
            this.mCurlDir.x = this.mCurlPos.x - this.mDragStartPos.x;
            this.mCurlDir.y = this.mCurlPos.y - this.mDragStartPos.y;
            float sqrt = (float) Math.sqrt((double) ((this.mCurlDir.x * this.mCurlDir.x) + (this.mCurlDir.y * this.mCurlDir.y)));
            double d = width * 3.141592653589793d;
            double d2 = (double) sqrt;
            float width2 = this.mRenderer.getPageRect(2).width() * 2.0f;
            if (d2 > ((double) width2) - d) {
                d = (double) Math.max(width2 - sqrt, 0.0f);
                width = d / 3.141592653589793d;
            }
            if (d2 >= d) {
                double d3 = (d2 - d) / 2.0d;
                if (this.mViewMode == 2) {
                    PointF pointF = this.mCurlPos;
                    pointF.x = (float) (((double) pointF.x) - ((((double) this.mCurlDir.x) * d3) / d2));
                } else {
                    width = Math.max(Math.min((double) (this.mCurlPos.x - this.mRenderer.getPageRect(2).left), width), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                }
                PointF pointF2 = this.mCurlPos;
                pointF2.y = (float) (((double) pointF2.y) - ((((double) this.mCurlDir.y) * d3) / d2));
            } else {
                double sin = Math.sin(Math.sqrt(d2 / d) * 3.141592653589793d) * width;
                PointF pointF3 = this.mCurlPos;
                pointF3.x = (float) (((double) pointF3.x) + ((((double) this.mCurlDir.x) * sin) / d2));
                PointF pointF4 = this.mCurlPos;
                pointF4.y = (float) (((double) pointF4.y) + ((((double) this.mCurlDir.y) * sin) / d2));
            }
        } else if (this.mCurlState == 1) {
            width = Math.max(Math.min((double) (this.mCurlPos.x - this.mRenderer.getPageRect(2).left), width), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            float f = this.mRenderer.getPageRect(2).right;
            PointF pointF5 = this.mCurlPos;
            pointF5.x = (float) (((double) pointF5.x) - Math.min((double) (f - this.mCurlPos.x), width));
            this.mCurlDir.x = this.mCurlPos.x + this.mDragStartPos.x;
            this.mCurlDir.y = this.mCurlPos.y - this.mDragStartPos.y;
        }
        setCurlPos(this.mCurlPos, this.mCurlDir, width);
    }

    private void updatePage(CurlPage curlPage, int i) {
        curlPage.reset();
        this.mPageProvider.updatePage(curlPage, this.mPageBitmapWidth, this.mPageBitmapHeight, i);
    }

    public void updatePages() {
        int i;
        if (this.mPageProvider != null && this.mPageBitmapWidth > 0 && this.mPageBitmapHeight > 0) {
            this.mUpdatePageIscalled = true;
            this.mRenderer.removeCurlMesh(this.mPageLeft);
            this.mRenderer.removeCurlMesh(this.mPageRight);
            this.mRenderer.removeCurlMesh(this.mPageCurl);
            int i2 = this.mCurrentIndex;
            int i3 = i2 - 1;
            int i4 = this.mCurlState;
            if (i4 == 1) {
                i = i3 - 1;
            } else if (i4 == 2) {
                int i5 = i3;
                i3 = i2;
                i2++;
                i = i5;
            } else {
                i = i3;
                i3 = -1;
            }
            if (i2 >= 0 && i2 < this.mPageProvider.getPageCount()) {
                updatePage(this.mPageRight.getTexturePage(), i2);
                this.mPageRight.setFlipTexture(false);
                this.mPageRight.setRect(this.mRenderer.getPageRect(2));
                this.mPageRight.reset();
                this.mRenderer.addCurlMesh(this.mPageRight);
            }
            if (i >= 0 && i < this.mPageProvider.getPageCount()) {
                updatePage(this.mPageLeft.getTexturePage(), i);
                this.mPageLeft.setFlipTexture(true);
                this.mPageLeft.setRect(this.mRenderer.getPageRect(1));
                this.mPageLeft.reset();
                if (this.mRenderLeftPage) {
                    this.mRenderer.addCurlMesh(this.mPageLeft);
                }
            }
            if (i3 >= 0 && i3 < this.mPageProvider.getPageCount()) {
                updatePage(this.mPageCurl.getTexturePage(), i3);
                if (this.mCurlState == 2) {
                    this.mPageCurl.setFlipTexture(true);
                    this.mPageCurl.setRect(this.mRenderer.getPageRect(2));
                } else {
                    this.mPageCurl.setFlipTexture(false);
                    this.mPageCurl.setRect(this.mRenderer.getPageRect(1));
                }
                this.mPageCurl.reset();
                this.mRenderer.addCurlMesh(this.mPageCurl);
            }
        }
    }

    private class PointerPosition {
        PointF mPos;
        float mPressure;

        private PointerPosition() {
            this.mPos = new PointF();
        }
    }
}
