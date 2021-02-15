package com.medscape.android.contentviewer.view_holders;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.medscape.android.R;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.model.Contributor;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class ContentHeaderViewHolder extends DataViewHolder implements View.OnClickListener {
    private ImageView arrow;
    private LinearLayout iconBackground;
    private TextView mAuthorLabel;
    private TextView mAuthorValue;
    private Context mContext;
    private TextView mEditorLabel;
    private TextView mEditorValue;
    private View mRootView;
    public TextView mTitle;
    private TextView mUpdateDate;
    private TextView mUpdateDateLabel;

    public ContentHeaderViewHolder(View view, Context context, final DataViewHolder.DataListClickListener dataListClickListener) {
        super(view);
        this.mContext = context;
        this.mRootView = view.findViewById(R.id.base_layout);
        this.mUpdateDate = (TextView) view.findViewById(R.id.update_date);
        this.mUpdateDateLabel = (TextView) view.findViewById(R.id.update_date_label);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mAuthorLabel = (TextView) view.findViewById(R.id.author);
        this.mAuthorValue = (TextView) view.findViewById(R.id.author_value);
        this.mEditorLabel = (TextView) view.findViewById(R.id.chief_editor);
        this.mEditorValue = (TextView) view.findViewById(R.id.editor_value);
        this.iconBackground = (LinearLayout) view.findViewById(R.id.icon_background);
        this.arrow = (ImageView) view.findViewById(R.id.icon);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (dataListClickListener != null && ContentHeaderViewHolder.this.getAdapterPosition() != -1) {
                    dataListClickListener.onDataListItemClicked(ContentHeaderViewHolder.this.getAdapterPosition());
                }
            }
        });
    }

    public void bindItem(ClinicalReferenceContent clinicalReferenceContent) {
        boolean z;
        boolean z2;
        if (clinicalReferenceContent != null) {
            setNightMode();
            setTextSize();
            if (StringUtil.isNotEmpty(clinicalReferenceContent.title)) {
                this.mTitle.setText(clinicalReferenceContent.title);
            }
            if (StringUtil.isNotEmpty(clinicalReferenceContent.lastUpdate)) {
                this.mUpdateDate.setText(clinicalReferenceContent.lastUpdate);
            }
            ArrayList<Contributor> arrayList = clinicalReferenceContent.contributors;
            if (arrayList != null) {
                Iterator<Contributor> it = arrayList.iterator();
                z2 = false;
                z = false;
                while (it.hasNext()) {
                    Contributor next = it.next();
                    if (next.author != null && StringUtil.isNotEmpty(next.author)) {
                        if (!z2 && next.typeLabel.equalsIgnoreCase("Author")) {
                            setAuthorVisibility(true);
                            this.mAuthorValue.setText(next.author);
                            z2 = true;
                        } else if (!z && next.typeLabel.equalsIgnoreCase("Chief Editor")) {
                            setEditorVisibility(true);
                            this.mEditorValue.setText(next.author);
                            z = true;
                        }
                        if (z2 && z) {
                            break;
                        }
                    }
                }
            } else {
                z2 = false;
                z = false;
            }
            if (z2 || z) {
                this.mRootView.setVisibility(0);
                if (!z2) {
                    setAuthorVisibility(false);
                }
                if (!z) {
                    setEditorVisibility(false);
                    return;
                }
                return;
            }
            this.mRootView.setVisibility(8);
        }
    }

    private void setEditorVisibility(boolean z) {
        if (z) {
            this.mEditorLabel.setVisibility(0);
            this.mEditorValue.setVisibility(0);
            return;
        }
        this.mEditorValue.setVisibility(8);
        this.mEditorLabel.setVisibility(8);
    }

    private void setAuthorVisibility(boolean z) {
        if (z) {
            this.mAuthorLabel.setVisibility(0);
            this.mAuthorValue.setVisibility(0);
            return;
        }
        this.mAuthorLabel.setVisibility(8);
        this.mAuthorValue.setVisibility(8);
    }

    private void setNightMode() {
        if (this.nightModeOn) {
            this.mRootView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.iconBackground.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.mTitle.setTextColor(-1);
            this.mUpdateDateLabel.setTextColor(-1);
            this.mAuthorLabel.setTextColor(-1);
            this.mEditorLabel.setTextColor(-1);
            this.mUpdateDate.setTextColor(Color.parseColor("#BDBDBD"));
            this.mAuthorValue.setTextColor(Color.parseColor("#BDBDBD"));
            this.mEditorValue.setTextColor(Color.parseColor("#BDBDBD"));
            Context context = this.mContext;
            if (context != null) {
                this.arrow.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_white_24dp));
                return;
            }
            return;
        }
        this.mRootView.setBackgroundColor(-1);
        this.iconBackground.setBackgroundColor(-1);
        this.mTitle.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mUpdateDateLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mAuthorLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mEditorLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mUpdateDate.setTextColor(Color.parseColor("#616161"));
        this.mAuthorValue.setTextColor(Color.parseColor("#616161"));
        this.mEditorValue.setTextColor(Color.parseColor("#616161"));
        Context context2 = this.mContext;
        if (context2 != null) {
            this.arrow.setImageDrawable(context2.getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black_24dp));
        }
    }

    private void setTextSize() {
        this.mTitle.setTextSize((float) ContentUtils.getHeaderFontSize(getTextSizeIndex()));
        this.mUpdateDateLabel.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
        this.mAuthorLabel.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
        this.mEditorLabel.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
        this.mUpdateDate.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
        this.mAuthorValue.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
        this.mEditorValue.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
    }
}
