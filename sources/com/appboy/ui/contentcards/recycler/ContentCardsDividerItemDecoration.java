package com.appboy.ui.contentcards.recycler;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.appboy.ui.R;

public class ContentCardsDividerItemDecoration extends RecyclerView.ItemDecoration {
    private final Context mContext;
    private final int mItemDividerHeight = getItemDividerHeight();
    private final int mItemDividerMaxWidth = getContentCardsItemMaxWidth();

    public ContentCardsDividerItemDecoration(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getItemOffsets(android.graphics.Rect r3, android.view.View r4, androidx.recyclerview.widget.RecyclerView r5, androidx.recyclerview.widget.RecyclerView.State r6) {
        /*
            r2 = this;
            super.getItemOffsets(r3, r4, r5, r6)
            int r4 = r5.getChildAdapterPosition(r4)
            androidx.recyclerview.widget.RecyclerView$Adapter r6 = r5.getAdapter()
            boolean r6 = r6 instanceof com.appboy.ui.contentcards.AppboyCardAdapter
            r0 = 0
            if (r6 == 0) goto L_0x001d
            androidx.recyclerview.widget.RecyclerView$Adapter r6 = r5.getAdapter()
            com.appboy.ui.contentcards.AppboyCardAdapter r6 = (com.appboy.ui.contentcards.AppboyCardAdapter) r6
            if (r4 <= 0) goto L_0x001d
            boolean r6 = r6.isControlCardAtPosition(r4)
            goto L_0x001e
        L_0x001d:
            r6 = 0
        L_0x001e:
            if (r6 == 0) goto L_0x0022
            r1 = 0
            goto L_0x0024
        L_0x0022:
            int r1 = r2.mItemDividerHeight
        L_0x0024:
            r3.bottom = r1
            if (r4 != 0) goto L_0x002f
            if (r6 == 0) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            int r0 = r2.mItemDividerHeight
        L_0x002d:
            r3.top = r0
        L_0x002f:
            int r4 = r5.getWidth()
            int r4 = r2.getSidePaddingValue(r4)
            r3.left = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.ui.contentcards.recycler.ContentCardsDividerItemDecoration.getItemOffsets(android.graphics.Rect, android.view.View, androidx.recyclerview.widget.RecyclerView, androidx.recyclerview.widget.RecyclerView$State):void");
    }

    private int getItemDividerHeight() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.com_appboy_content_cards_divider_height);
    }

    private int getContentCardsItemMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.com_appboy_content_cards_max_width);
    }

    private int getSidePaddingValue(int i) {
        return Math.max((i - this.mItemDividerMaxWidth) / 2, 0);
    }
}
