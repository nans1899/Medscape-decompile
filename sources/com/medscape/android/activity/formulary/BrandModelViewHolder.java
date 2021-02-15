package com.medscape.android.activity.formulary;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;

public class BrandModelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private BrandListClickListener mBrandClickListener;
    ImageView rowImage;
    RelativeLayout rowLayout;
    TextView rowTitle;

    public interface BrandListClickListener {
        void onBrandItemClicked(int i);
    }

    public BrandModelViewHolder(View view, BrandListClickListener brandListClickListener) {
        super(view);
        this.rowTitle = (TextView) view.findViewById(R.id.rowTitle);
        this.rowImage = (ImageView) view.findViewById(R.id.rowImage);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rowLayout);
        this.rowLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.mBrandClickListener = brandListClickListener;
        view.findViewById(R.id.itemDivider).setVisibility(0);
    }

    public void bindItem(BrandModel brandModel) {
        this.rowTitle.setText(brandModel.getBrandName().replaceAll("''", "'"));
        this.rowImage.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
    }

    public String toString() {
        return this.rowTitle.getText().toString();
    }

    public void onClick(View view) {
        if (this.mBrandClickListener != null && getAdapterPosition() != -1) {
            this.mBrandClickListener.onBrandItemClicked(getAdapterPosition());
        }
    }
}
