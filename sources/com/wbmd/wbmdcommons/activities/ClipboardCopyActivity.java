package com.wbmd.wbmdcommons.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.wbmd.wbmdcommons.R;

public class ClipboardCopyActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getExtras().getString("android.intent.extra.TEXT") != null) {
            ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Text : ", getIntent().getExtras().getString("android.intent.extra.TEXT")));
            Toast.makeText(this, R.string.clipboard_copied, 1).show();
        } else {
            Toast.makeText(this, R.string.general_error_internet, 1).show();
        }
        finish();
    }
}
