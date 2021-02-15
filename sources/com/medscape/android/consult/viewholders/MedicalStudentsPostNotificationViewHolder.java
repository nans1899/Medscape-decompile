package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;

public class MedicalStudentsPostNotificationViewHolder extends RecyclerView.ViewHolder {
    public TextView menuHint;
    public Button notNow;
    public LinearLayout notificationRoot;
    public Button showInFeed;

    public MedicalStudentsPostNotificationViewHolder(View view) {
        super(view);
        this.showInFeed = (Button) view.findViewById(R.id.med_student_notification_show);
        this.notNow = (Button) view.findViewById(R.id.med_student_notification_not_now);
        this.notificationRoot = (LinearLayout) view.findViewById(R.id.med_student_notification_root);
        this.menuHint = (TextView) view.findViewById(R.id.med_student_notification_menu_hint);
    }

    public void bindViews(boolean z) {
        if (z) {
            this.notificationRoot.setVisibility(8);
            this.menuHint.setVisibility(0);
            return;
        }
        this.notificationRoot.setVisibility(0);
        this.menuHint.setVisibility(8);
    }
}
