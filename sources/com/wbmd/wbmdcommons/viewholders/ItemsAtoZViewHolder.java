package com.wbmd.wbmdcommons.viewholders;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.wbmdcommons.R;
import com.wbmd.wbmdcommons.model.CrossLink;
import com.wbmd.wbmdcommons.model.LineItem;

public class ItemsAtoZViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView crossLinkIcon;
    private TextView mHeaderLabel;
    DataListClickListener mListener;
    public TextView textView;
    public TextView textViewDetail;

    public interface DataListClickListener {
        void onDataListItemClicked(int i);
    }

    public ItemsAtoZViewHolder(View view) {
        super(view);
    }

    public ItemsAtoZViewHolder(View view, DataListClickListener dataListClickListener) {
        super(view);
        this.mListener = dataListClickListener;
        this.mHeaderLabel = (TextView) view.findViewById(R.id.text);
        TextView textView2 = (TextView) view.findViewById(R.id.text);
        this.textView = textView2;
        textView2.setOnClickListener(this);
        this.textViewDetail = (TextView) view.findViewById(R.id.textDetail);
        this.crossLinkIcon = (ImageView) view.findViewById(R.id.crossLinkIcon);
        this.textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void bindItem(CharSequence charSequence) {
        if (charSequence != null) {
            this.textView.setText(charSequence.toString());
        }
    }

    public void bindItem(LineItem lineItem) {
        bindItem(lineItem.text);
        CrossLink crossLink = lineItem.crossLink;
        ImageView imageView = this.crossLinkIcon;
        if (imageView == null) {
            return;
        }
        if (crossLink != null) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    public void bindHeaderText(String str) {
        TextView textView2 = this.mHeaderLabel;
        if (textView2 != null) {
            textView2.setText(str);
        }
    }

    public String toString() {
        return this.textView.getText().toString();
    }

    public void onClick(View view) {
        if (this.mListener != null && getAdapterPosition() != -1) {
            this.mListener.onDataListItemClicked(getAdapterPosition());
        }
    }
}
