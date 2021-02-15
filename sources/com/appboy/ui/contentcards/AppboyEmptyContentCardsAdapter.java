package com.appboy.ui.contentcards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.appboy.ui.R;

public class AppboyEmptyContentCardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int getItemCount() {
        return 1;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new NetworkUnavailableViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.com_appboy_content_cards_empty, viewGroup, false));
    }

    class NetworkUnavailableViewHolder extends RecyclerView.ViewHolder {
        NetworkUnavailableViewHolder(View view) {
            super(view);
        }
    }
}
