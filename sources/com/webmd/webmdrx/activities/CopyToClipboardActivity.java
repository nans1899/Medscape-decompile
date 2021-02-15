package com.webmd.webmdrx.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.webmd.webmdrx.R;

public class CopyToClipboardActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getExtras().getString("android.intent.extra.TEXT") != null) {
            ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Text : ", getIntent().getExtras().getString("android.intent.extra.TEXT")));
            Toast.makeText(this, getString(R.string.share_dialog_clipboard), 1).show();
        } else {
            Toast.makeText(this, getString(R.string.connection_error_message), 1).show();
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
