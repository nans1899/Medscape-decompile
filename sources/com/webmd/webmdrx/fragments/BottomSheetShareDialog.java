package com.webmd.webmdrx.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.webmd.webmdrx.R;

public class BottomSheetShareDialog extends BottomSheetDialogFragment {
    /* access modifiers changed from: private */
    public Intent mCopyIntent;
    private Drawable mGmailIcon;
    /* access modifiers changed from: private */
    public Intent mGmailIntent;
    private Drawable mMapsIcon;
    /* access modifiers changed from: private */
    public Intent mMapsIntent;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.dialog_share, viewGroup, false);
        setUpImages(inflate);
        setUpListeners(inflate);
        return inflate;
    }

    private void setUpImages(View view) {
        ((ImageView) view.findViewById(R.id.dialog_share_image_view_gmail)).setImageDrawable(this.mGmailIcon);
        ((ImageView) view.findViewById(R.id.dialog_share_image_view_maps)).setImageDrawable(this.mMapsIcon);
    }

    private void setUpListeners(View view) {
        view.findViewById(R.id.dialog_share_layout_gmail).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BottomSheetShareDialog.this.getActivity().startActivity(BottomSheetShareDialog.this.mGmailIntent);
                BottomSheetShareDialog.this.dismiss();
            }
        });
        view.findViewById(R.id.dialog_share_layout_maps).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BottomSheetShareDialog.this.getActivity().startActivity(BottomSheetShareDialog.this.mMapsIntent);
                BottomSheetShareDialog.this.dismiss();
            }
        });
        view.findViewById(R.id.dialog_share_layout_copy).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BottomSheetShareDialog.this.getActivity().startActivity(BottomSheetShareDialog.this.mCopyIntent);
                BottomSheetShareDialog.this.dismiss();
            }
        });
    }

    public void setGmailIntent(Intent intent) {
        this.mGmailIntent = intent;
    }

    public void setGmailIcon(Drawable drawable) {
        this.mGmailIcon = drawable;
    }

    public void setMapsIntent(Intent intent) {
        this.mMapsIntent = intent;
    }

    public void setMapsIcon(Drawable drawable) {
        this.mMapsIcon = drawable;
    }

    public void setCopyIntent(Intent intent) {
        this.mCopyIntent = intent;
    }
}
