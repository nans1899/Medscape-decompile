package com.epapyrus.plugpdf.core;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class PropertyManager {
    private static String mCancelBtnText = "Cancel";
    private static String mCopyBtnText = "Copy";
    private static String mCopyTextToast = "Text copied to clipboard";
    private static double mDoubleTabZoomScale = 3.0d;
    private static boolean mEnableAnnotTransform = false;
    private static Bitmap mEnableCustomNoteIcon = null;
    private static boolean mEnableEraseConfirm = true;
    private static boolean mEnableLog = true;
    private static boolean mEnableNightMode = false;
    private static boolean mEnableResetZoomAfterSearching = true;
    private static boolean mIgnoreZoomGesture = false;
    private static boolean mIncludeUnAccentSearchResults = false;
    private static boolean mKeepMinimumZoomLevel = false;
    private static double mMaximumZoomScale = 3.0d;
    private static double mMinimumZoomScale = 1.0d;
    private static int mPageBackgroundOpacity = 255;
    private static BasePlugPDFDisplay.PageDisplayMode mPageDisplayModeAfterThumbnail = null;
    private static int mPageGap = 5;
    private static PageProperty mPageProperty = null;
    private static double mPreviewQualityCoef = 1.0d;
    private static float mScrollFrictionCoef = 1.0f;
    private static float mScrollVelocityCoef = 1.0f;
    private static String mSearchBtnText = "Search";
    private static int mSearchProgressDelay = 200;
    private static float mSelectToolHeightInterval = 0.7f;
    private static boolean mUseBilateralCoverDisplay = false;
    private static boolean mUsePageLoadThread = true;
    private static boolean mUseTextSelectPopUp = true;
    private static int mWrapTextColor = Color.argb(255, 67, 116, 217);
    private static boolean misEnableAdjustpatch = true;

    static {
        setPageProperty(new PageProperty(2113863680, -2130719608, -1, 200));
    }

    public static boolean isIncludeUnAccentSearchResults() {
        return mIncludeUnAccentSearchResults;
    }

    public static void setIncludeUnAccentSearchResults(boolean z) {
        mIncludeUnAccentSearchResults = z;
    }

    public static void setPageProperty(PageProperty pageProperty) {
        mPageProperty = pageProperty;
    }

    public static PageProperty getPageProperty() {
        return mPageProperty;
    }

    public static class PageProperty {
        private int mBackgroundColor;
        private int mHighlightColor;
        private int mLinkColor;
        private int mProgressDialogDelay;

        public PageProperty(int i, int i2, int i3, int i4) {
            this.mHighlightColor = i;
            this.mLinkColor = i2;
            this.mBackgroundColor = i3;
            this.mProgressDialogDelay = i4;
        }

        public int getHighlightColor() {
            return this.mHighlightColor;
        }

        public int getLinkColor() {
            return this.mLinkColor;
        }

        public int getBackgroundColor() {
            return this.mBackgroundColor;
        }

        public int getProgressDialogDelay() {
            return this.mProgressDialogDelay;
        }
    }

    public static void setDoubleTapZoomLevel(double d) {
        mDoubleTabZoomScale = d;
    }

    public static double getDoubleTapZoomLevel() {
        return mDoubleTabZoomScale;
    }

    @Deprecated
    public static void setPinchToZoomLevel(double d) {
        mMaximumZoomScale = d;
    }

    @Deprecated
    public static double getPinchToZoomMaxScale() {
        return mMaximumZoomScale;
    }

    public static void setMaximumZoomLevel(double d) {
        mMaximumZoomScale = d;
    }

    public static double getMaximumZoomLevel() {
        return mMaximumZoomScale;
    }

    public static float getScrollFrictionCoef() {
        return mScrollFrictionCoef;
    }

    public static void setScrollFrictionCoef(float f) {
        mScrollFrictionCoef = f;
    }

    public static float getScrollVelocityCoef() {
        return mScrollVelocityCoef;
    }

    public static void setScrollVelocityCoef(float f) {
        mScrollVelocityCoef = f;
    }

    public static double getPreviewQualityCoef() {
        return mPreviewQualityCoef;
    }

    public static void setPreviewQualityCoef(double d) {
        mPreviewQualityCoef = d;
    }

    public static void setKeepMinimumZoomLevel(boolean z) {
        mKeepMinimumZoomLevel = z;
    }

    public static boolean isKeepMinimumZoomLevel() {
        return mKeepMinimumZoomLevel;
    }

    public static boolean isIgnoreZoomGesture() {
        return mIgnoreZoomGesture;
    }

    public static void setIgnoreZoomGesture(boolean z) {
        mIgnoreZoomGesture = z;
    }

    public static boolean usePageLoadThread() {
        return mUsePageLoadThread;
    }

    public static void setUsePageLoadThread(boolean z) {
        mUsePageLoadThread = z;
    }

    public static void setPageGap(int i) {
        mPageGap = i;
    }

    public static int getPageGap() {
        return mPageGap;
    }

    public static double getMinimumZoomLevel() {
        return mMinimumZoomScale;
    }

    public static void setMinimumZoomLevel(double d) {
        mMinimumZoomScale = d;
    }

    public static boolean isEnableResetZoomAfterSearching() {
        return mEnableResetZoomAfterSearching;
    }

    public static void setEnableResetZoomAfterSearching(boolean z) {
        mEnableResetZoomAfterSearching = z;
    }

    public static void setSearchProgressDelay(int i) {
        mSearchProgressDelay = i;
    }

    @Deprecated
    public static int getSearchProgressDelay() {
        return mSearchProgressDelay;
    }

    public static boolean getUseBilateralCoverDisplay() {
        return mUseBilateralCoverDisplay;
    }

    public static void setUseBilateralCoverDisplay(boolean z) {
        mUseBilateralCoverDisplay = z;
    }

    public static void setEnableNightMode(boolean z) {
        mEnableNightMode = z;
    }

    public static boolean getEnableNightMode() {
        return mEnableNightMode;
    }

    public static void setEnableConfirmErase(boolean z) {
        mEnableEraseConfirm = z;
    }

    public static boolean getEnableConfirmErase() {
        return mEnableEraseConfirm;
    }

    public static void setEnableCustomNoteIcon(Bitmap bitmap) {
        mEnableCustomNoteIcon = bitmap;
    }

    public static Bitmap getEnableCustomNoteIcon() {
        return mEnableCustomNoteIcon;
    }

    public static void setmSelectToolHeightInterval(float f) {
        mSelectToolHeightInterval = f;
    }

    public static float getSelectToolHeightInterval() {
        return mSelectToolHeightInterval;
    }

    public static int getmWrapTextColor() {
        return mWrapTextColor;
    }

    public static void setWrapTextColor(int i) {
        mWrapTextColor = i;
    }

    public static int getPageBackgroundOpacity() {
        return mPageBackgroundOpacity;
    }

    public static void setPageBackgroundOpacity(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > 255) {
            i = 255;
        }
        mPageBackgroundOpacity = i;
    }

    public static void setPageDisplayModeAfterThumbnail(BasePlugPDFDisplay.PageDisplayMode pageDisplayMode) {
        mPageDisplayModeAfterThumbnail = pageDisplayMode;
    }

    public static BasePlugPDFDisplay.PageDisplayMode getPageDisplayModeAfterThumbnail() {
        return mPageDisplayModeAfterThumbnail;
    }

    public static boolean isEnableAdjustpatch() {
        return misEnableAdjustpatch;
    }

    public static void setEnableAdjustpatch(boolean z) {
        misEnableAdjustpatch = z;
    }

    public static void setEnableLog(boolean z) {
        mEnableLog = z;
    }

    public static boolean isEnableLog() {
        return mEnableLog;
    }

    public static void setEnableAnnotTransform(boolean z) {
        mEnableAnnotTransform = z;
    }

    public static boolean isEnableAnnotTransform() {
        return mEnableAnnotTransform;
    }

    @Deprecated
    public static void setmOpacity(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > 255) {
            i = 255;
        }
        mPageBackgroundOpacity = i;
    }

    public static void setCopyBtnText(String str) {
        mCopyBtnText = str;
    }

    public static String getCopyBtnText() {
        return mCopyBtnText;
    }

    public static void setSearchBtnText(String str) {
        mSearchBtnText = str;
    }

    public static String getSearchBtnText() {
        return mSearchBtnText;
    }

    public static void setCancelBtnText(String str) {
        mCancelBtnText = str;
    }

    public static String getCancelBtnText() {
        return mCancelBtnText;
    }

    public static void setCopyTextToast(String str) {
        mCopyTextToast = str;
    }

    public static String getCopyTextToast() {
        return mCopyTextToast;
    }

    @Deprecated
    public static void setUseTextSelectPopUp(boolean z) {
        mUseTextSelectPopUp = z;
    }

    @Deprecated
    public static boolean getUseTextSelectPopUp() {
        return mUseTextSelectPopUp;
    }
}
