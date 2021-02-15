package fi.harism.curl;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import java.util.Vector;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CurlRenderer implements GLSurfaceView.Renderer {
    public static final int PAGE_LEFT = 1;
    public static final int PAGE_RIGHT = 2;
    public static final int SHOW_ONE_PAGE = 1;
    public static final int SHOW_TWO_PAGES = 2;
    private static final boolean USE_PERSPECTIVE_PROJECTION = false;
    private int mBackgroundColor;
    private Vector<CurlMesh> mCurlMeshes;
    private RectF mMargins = new RectF();
    private Observer mObserver;
    private RectF mPageRectLeft;
    private RectF mPageRectRight;
    private int mViewMode = 1;
    private RectF mViewRect = new RectF();
    private int mViewportHeight;
    private int mViewportWidth;

    public interface Observer {
        void onDrawFrame();

        void onPageSizeChanged(int i, int i2);

        void onSurfaceCreated();
    }

    public CurlRenderer(Observer observer) {
        this.mObserver = observer;
        this.mCurlMeshes = new Vector<>();
        this.mPageRectLeft = new RectF();
        this.mPageRectRight = new RectF();
    }

    public synchronized void addCurlMesh(CurlMesh curlMesh) {
        removeCurlMesh(curlMesh);
        this.mCurlMeshes.add(curlMesh);
    }

    public RectF getPageRect(int i) {
        if (i == 1) {
            return this.mPageRectLeft;
        }
        if (i == 2) {
            return this.mPageRectRight;
        }
        return null;
    }

    public synchronized void onDrawFrame(GL10 gl10) {
        this.mObserver.onDrawFrame();
        gl10.glClearColor(((float) Color.red(this.mBackgroundColor)) / 255.0f, ((float) Color.green(this.mBackgroundColor)) / 255.0f, ((float) Color.blue(this.mBackgroundColor)) / 255.0f, ((float) Color.alpha(this.mBackgroundColor)) / 255.0f);
        gl10.glClear(16384);
        gl10.glLoadIdentity();
        for (int i = 0; i < this.mCurlMeshes.size(); i++) {
            this.mCurlMeshes.get(i).onDrawFrame(gl10);
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        gl10.glViewport(0, 0, i, i2);
        this.mViewportWidth = i;
        this.mViewportHeight = i2;
        float f = ((float) i) / ((float) i2);
        this.mViewRect.top = 1.0f;
        this.mViewRect.bottom = -1.0f;
        this.mViewRect.left = -f;
        this.mViewRect.right = f;
        updatePageRects();
        gl10.glMatrixMode(5889);
        gl10.glLoadIdentity();
        GLU.gluOrtho2D(gl10, this.mViewRect.left, this.mViewRect.right, this.mViewRect.bottom, this.mViewRect.top);
        gl10.glMatrixMode(5888);
        gl10.glLoadIdentity();
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        gl10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl10.glShadeModel(7425);
        gl10.glHint(3152, 4354);
        gl10.glHint(3154, 4354);
        gl10.glHint(3155, 4354);
        gl10.glEnable(2848);
        gl10.glDisable(2929);
        gl10.glDisable(2884);
        this.mObserver.onSurfaceCreated();
    }

    public synchronized void removeCurlMesh(CurlMesh curlMesh) {
        do {
        } while (this.mCurlMeshes.remove(curlMesh));
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
    }

    public synchronized void setMargins(float f, float f2, float f3, float f4) {
        this.mMargins.left = f;
        this.mMargins.top = f2;
        this.mMargins.right = f3;
        this.mMargins.bottom = f4;
        updatePageRects();
    }

    public synchronized RectF getMargins() {
        return this.mMargins;
    }

    public synchronized void setViewMode(int i) {
        if (i == 1) {
            try {
                this.mViewMode = i;
                updatePageRects();
            } catch (Throwable th) {
                throw th;
            }
        } else if (i == 2) {
            this.mViewMode = i;
            updatePageRects();
        }
    }

    public void translate(PointF pointF) {
        pointF.x = this.mViewRect.left + ((this.mViewRect.width() * pointF.x) / ((float) this.mViewportWidth));
        pointF.y = this.mViewRect.top - (((-this.mViewRect.height()) * pointF.y) / ((float) this.mViewportHeight));
    }

    private void updatePageRects() {
        if (this.mViewRect.width() != 0.0f && this.mViewRect.height() != 0.0f) {
            int i = this.mViewMode;
            if (i == 1) {
                this.mPageRectRight.set(this.mViewRect);
                this.mPageRectRight.left += this.mViewRect.width() * this.mMargins.left;
                this.mPageRectRight.right -= this.mViewRect.width() * this.mMargins.right;
                this.mPageRectRight.top += this.mViewRect.height() * this.mMargins.top;
                this.mPageRectRight.bottom -= this.mViewRect.height() * this.mMargins.bottom;
                this.mPageRectLeft.set(this.mPageRectRight);
                this.mPageRectLeft.offset(-this.mPageRectRight.width(), 0.0f);
                this.mObserver.onPageSizeChanged((int) ((this.mPageRectRight.width() * ((float) this.mViewportWidth)) / this.mViewRect.width()), (int) ((this.mPageRectRight.height() * ((float) this.mViewportHeight)) / this.mViewRect.height()));
            } else if (i == 2) {
                this.mPageRectRight.set(this.mViewRect);
                this.mPageRectRight.left += this.mViewRect.width() * this.mMargins.left;
                this.mPageRectRight.right -= this.mViewRect.width() * this.mMargins.right;
                this.mPageRectRight.top += this.mViewRect.height() * this.mMargins.top;
                this.mPageRectRight.bottom -= this.mViewRect.height() * this.mMargins.bottom;
                this.mPageRectLeft.set(this.mPageRectRight);
                RectF rectF = this.mPageRectLeft;
                rectF.right = (rectF.right + this.mPageRectLeft.left) / 2.0f;
                this.mPageRectRight.left = this.mPageRectLeft.right;
                this.mObserver.onPageSizeChanged((int) ((this.mPageRectRight.width() * ((float) this.mViewportWidth)) / this.mViewRect.width()), (int) ((this.mPageRectRight.height() * ((float) this.mViewportHeight)) / this.mViewRect.height()));
            }
        }
    }
}
