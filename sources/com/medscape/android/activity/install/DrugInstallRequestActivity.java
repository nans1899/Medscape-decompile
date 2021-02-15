package com.medscape.android.activity.install;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;

public class DrugInstallRequestActivity extends BaseActivity implements View.OnClickListener {
    private static final int START_DOWNLOAD_REQUEST = 1;
    private Button cancelButton;
    private Button downloadButton;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.drug_install_permission);
        Button button = (Button) findViewById(R.id.cancelButton);
        this.cancelButton = button;
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.downloadButton);
        this.downloadButton = button2;
        button2.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.equals(this.cancelButton)) {
            setResult(0);
            finish();
        } else if (view.equals(this.downloadButton)) {
            try {
                startActivityForResult(new Intent(this, DrugInstallationActivity.class), 1);
            } catch (Exception e) {
                e.printStackTrace();
                setResult(0);
                finish();
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            setResult(i2);
            finish();
        }
    }
}
