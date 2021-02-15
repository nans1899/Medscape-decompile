package com.tonicartos.superslim;

import android.text.TextUtils;
import android.view.View;
import com.tonicartos.superslim.LayoutManager;

public class SectionData {
    public final int contentEnd;
    public final int contentStart;
    public final int firstPosition;
    public final boolean hasHeader;
    public final int headerHeight;
    LayoutManager.LayoutParams headerParams;
    public final int headerWidth;
    final int marginEnd;
    final int marginStart;
    public final int minimumHeight;
    public final String sectionManager;
    public final int sectionManagerKind;

    public SectionData(LayoutManager layoutManager, View view) {
        int paddingStart = layoutManager.getPaddingStart();
        int paddingEnd = layoutManager.getPaddingEnd();
        LayoutManager.LayoutParams layoutParams = (LayoutManager.LayoutParams) view.getLayoutParams();
        this.headerParams = layoutParams;
        if (layoutParams.isHeader) {
            this.headerWidth = layoutManager.getDecoratedMeasuredWidth(view);
            this.headerHeight = layoutManager.getDecoratedMeasuredHeight(view);
            if (!this.headerParams.isHeaderInline() || this.headerParams.isHeaderOverlay()) {
                this.minimumHeight = this.headerHeight;
            } else {
                this.minimumHeight = 0;
            }
            if (!this.headerParams.headerStartMarginIsAuto) {
                this.marginStart = this.headerParams.headerMarginStart;
            } else if (!this.headerParams.isHeaderStartAligned() || this.headerParams.isHeaderOverlay()) {
                this.marginStart = 0;
            } else {
                this.marginStart = this.headerWidth;
            }
            if (!this.headerParams.headerEndMarginIsAuto) {
                this.marginEnd = this.headerParams.headerMarginEnd;
            } else if (!this.headerParams.isHeaderEndAligned() || this.headerParams.isHeaderOverlay()) {
                this.marginEnd = 0;
            } else {
                this.marginEnd = this.headerWidth;
            }
        } else {
            this.minimumHeight = 0;
            this.headerHeight = 0;
            this.headerWidth = 0;
            this.marginStart = this.headerParams.headerMarginStart;
            this.marginEnd = this.headerParams.headerMarginEnd;
        }
        this.contentEnd = this.marginEnd + paddingEnd;
        this.contentStart = this.marginStart + paddingStart;
        this.hasHeader = this.headerParams.isHeader;
        this.firstPosition = this.headerParams.getTestedFirstPosition();
        this.sectionManager = this.headerParams.sectionManager;
        this.sectionManagerKind = this.headerParams.sectionManagerKind;
    }

    public int getTotalMarginWidth() {
        return this.marginEnd + this.marginStart;
    }

    public boolean sameSectionManager(LayoutManager.LayoutParams layoutParams) {
        return layoutParams.sectionManagerKind == this.sectionManagerKind || TextUtils.equals(layoutParams.sectionManager, this.sectionManager);
    }
}
