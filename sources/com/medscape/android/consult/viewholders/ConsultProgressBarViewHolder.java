package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;

public class ConsultProgressBarViewHolder extends RecyclerView.ViewHolder {
    private ProgressBar mProgress;

    public ConsultProgressBarViewHolder(View view) {
        super(view);
        this.mProgress = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    public void bindProgress(boolean z) {
        if (z) {
            this.mProgress.setVisibility(0);
        } else {
            this.mProgress.setVisibility(8);
        }
    }
}
