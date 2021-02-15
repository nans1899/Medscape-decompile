package com.epapyrus.plugpdf.core;

import android.app.Activity;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.view.Window;
import com.epapyrus.plugpdf.core.viewer.ReaderView;

public class CoordConverter {
    private static ReaderView mReader;
    private static int mStatusBarHeight;
    private static int mTilteBarHeight;

    public static void initCoordConverter(Activity activity, ReaderView readerView) {
        mReader = readerView;
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        mStatusBarHeight = rect.top;
        mTilteBarHeight = window.findViewById(16908290).getTop() - mStatusBarHeight;
        Log.d("PlugPDF", "[DEBUG] statusBar=" + mStatusBarHeight + ", titleBarHeight=" + mTilteBarHeight);
    }

    public static PointF tranlastePointToView(PointF pointF) {
        double calculatePageViewScaleInReaderView = mReader.getPlugPDFDisplay().calculatePageViewScaleInReaderView();
        PointF pointF2 = new PointF(0.0f, 0.0f);
        float f = (float) calculatePageViewScaleInReaderView;
        pointF2.x = (pointF.x * f) + ((float) mReader.getLeft());
        pointF2.y = (pointF.y * f) + ((float) mReader.getTop()) + ((float) mStatusBarHeight);
        return pointF2;
    }
}
