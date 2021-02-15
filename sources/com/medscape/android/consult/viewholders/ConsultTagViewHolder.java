package com.medscape.android.consult.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.util.StringUtil;

public class ConsultTagViewHolder extends RecyclerView.ViewHolder {
    private View mRootView;
    /* access modifiers changed from: private */
    public TextView mTextLabel;

    public ConsultTagViewHolder(View view, final Context context) {
        super(view);
        this.mTextLabel = (TextView) view.findViewById(R.id.tag_text);
        View findViewById = view.findViewById(R.id.root);
        this.mRootView = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String charSequence = ConsultTagViewHolder.this.mTextLabel.getText().toString();
                if (context != null && StringUtil.isNotEmpty(charSequence)) {
                    Intent intent = new Intent(context, ConsultTimelineActivity.class);
                    intent.putExtra(Constants.EXTRA_CONSULT_CLICKED_TAG, charSequence);
                    context.startActivity(intent);
                }
            }
        });
    }

    public void bindPost(String str) {
        this.mTextLabel.setText(str);
    }
}
