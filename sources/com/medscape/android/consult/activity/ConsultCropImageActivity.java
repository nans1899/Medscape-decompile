package com.medscape.android.consult.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.interfaces.IFileCreatedListener;
import com.medscape.android.consult.tasks.CreateFileForBitMapTask;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.FileInputStream;

public class ConsultCropImageActivity extends BaseActivity {
    private static final String TAG = ConsultCropImageActivity.class.getSimpleName();
    private CropImageView mCropImageView;
    /* access modifiers changed from: private */
    public View mProgressBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_crop_image);
        this.mCropImageView = (CropImageView) findViewById(R.id.cropImageView);
        this.mProgressBar = findViewById(R.id.progressBar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
        }
        Bitmap bitmap = null;
        try {
            FileInputStream openFileInput = openFileInput(getIntent().getStringExtra(Constants.EXTRA_CONSULT_CROP_IMAGE));
            bitmap = BitmapFactory.decodeStream(openFileInput);
            openFileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mCropImageView.setImageBitmap(bitmap);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.consult_crop_image_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        } else if (menuItem.getItemId() != R.id.action_image_crop) {
            return true;
        } else {
            try {
                if (this.mProgressBar != null) {
                    this.mProgressBar.setVisibility(0);
                }
                new CreateFileForBitMapTask(this, this.mCropImageView.getCroppedImage(), new IFileCreatedListener() {
                    public void onFileCreated(String str) {
                        if (ConsultCropImageActivity.this.mProgressBar != null) {
                            ConsultCropImageActivity.this.mProgressBar.setVisibility(8);
                        }
                        Intent intent = new Intent();
                        if (StringUtil.isNotEmpty(str)) {
                            intent.putExtra(Constants.EXTRA_CONSULT_CROP_IMAGE, str);
                        } else {
                            new MedscapeException(ConsultCropImageActivity.this.getResources().getString(R.string.failed_to_save_cropped_image)).showToast(ConsultCropImageActivity.this, 1);
                        }
                        ConsultCropImageActivity.this.setResult(-1, intent);
                        ConsultCropImageActivity.this.finish();
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                return true;
            } catch (Exception unused) {
                Trace.w(TAG, "Failed to return cropped image");
                return true;
            }
        }
    }
}
