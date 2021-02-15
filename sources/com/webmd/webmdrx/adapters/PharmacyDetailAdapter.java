package com.webmd.webmdrx.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.models.Address;
import java.util.ArrayList;
import java.util.Locale;

public class PharmacyDetailAdapter extends RecyclerView.Adapter<ViewHolder> {
    private boolean isLocalPharmacy;
    /* access modifiers changed from: private */
    public ArrayList<Address> mData;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Address address);
    }

    public PharmacyDetailAdapter(ArrayList<Address> arrayList, boolean z, boolean z2) {
        this.mData = arrayList;
        this.isLocalPharmacy = z;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_pharmacy_detail, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Address address = this.mData.get(i);
        viewHolder.mTVAddress.setText(address.getAddress1());
        if (this.onItemClickListener != null) {
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PharmacyDetailAdapter.this.onItemClickListener.onItemClick((Address) PharmacyDetailAdapter.this.mData.get(viewHolder.getAdapterPosition()));
                }
            });
        }
        viewHolder.mTVDistance.setText(String.format(Locale.US, "%.2f", new Object[]{address.getDistance()}) + " mi");
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mTVAddress;
        final TextView mTVDistance;
        final TextView mTVPrice;
        final View rootView;

        ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.mTVAddress = (TextView) view.findViewById(R.id.li_pharmacy_address);
            this.mTVDistance = (TextView) view.findViewById(R.id.li_pharmacy_distance);
            this.mTVPrice = (TextView) view.findViewById(R.id.li_pharmacy_price);
        }
    }
}
