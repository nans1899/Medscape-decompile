package com.medscape.android.util.media;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import com.medscape.android.R;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoUtil {
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    static final String TAG = PhotoUtil.class.getSimpleName();
    private static String mCurrentPhotoPath;

    public static boolean hasCamera(Activity activity) {
        PackageManager packageManager;
        if (activity == null || activity.isFinishing() || (packageManager = activity.getPackageManager()) == null) {
            return false;
        }
        return packageManager.hasSystemFeature("android.hardware.camera");
    }

    public static Bitmap getBitMapForPicture(String str, int i) {
        if (StringUtil.isNotEmpty(str)) {
            mCurrentPhotoPath = str;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, options);
        int max = i > 0 ? Math.max(options.outWidth / i, options.outHeight / i) : 1;
        options.inJustDecodeBounds = false;
        options.inSampleSize = max;
        options.inPurgeable = true;
        return rotatePhotoBasedOnExifData(BitmapFactory.decodeFile(mCurrentPhotoPath, options));
    }

    public static Bitmap getThumbnailFromBitmap(Bitmap bitmap, int i) {
        return bitmap != null ? ThumbnailUtils.extractThumbnail(bitmap, i, i) : bitmap;
    }

    private static Bitmap rotatePhotoBasedOnExifData(Bitmap bitmap) {
        try {
            int attributeInt = new ExifInterface(mCurrentPhotoPath).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0);
            if (attributeInt == 3) {
                return rotateImage(bitmap, 180.0f);
            }
            if (attributeInt == 6) {
                return rotateImage(bitmap, 90.0f);
            }
            if (attributeInt != 8) {
                return bitmap;
            }
            return rotateImage(bitmap, 270.0f);
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to rotate image to proper orientation");
            return bitmap;
        }
    }

    private static Bitmap rotateImage(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void galleryAddPic(Context context) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        if (StringUtil.isNotEmpty(mCurrentPhotoPath)) {
            File file = new File(mCurrentPhotoPath);
            if (file.exists()) {
                intent.setData(Uri.fromFile(file));
                if (context != null && (context instanceof Activity)) {
                    Activity activity = (Activity) context;
                    if (!activity.isFinishing()) {
                        activity.sendBroadcast(intent);
                    }
                }
            }
        }
    }

    public static void takePhoto(Context context, int i) {
        Activity activity;
        Uri uri;
        if (context != null && (context instanceof Activity) && (activity = (Activity) context) != null && !activity.isFinishing()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                try {
                    File upPhotoFile = setUpPhotoFile(context);
                    mCurrentPhotoPath = upPhotoFile.getAbsolutePath();
                    uri = FileProvider.getUriForFile(context, "com.medscape.android.fileprovider", upPhotoFile);
                    try {
                        intent.putExtra("output", uri);
                    } catch (IOException e) {
                        e = e;
                    }
                } catch (IOException e2) {
                    e = e2;
                    uri = null;
                    e.printStackTrace();
                    mCurrentPhotoPath = null;
                    intent.setClipData(ClipData.newRawUri("", uri));
                    intent.addFlags(3);
                    activity.startActivityForResult(intent, i);
                }
                if (uri != null && Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 21) {
                    intent.setClipData(ClipData.newRawUri("", uri));
                }
                intent.addFlags(3);
                activity.startActivityForResult(intent, i);
            }
        }
    }

    private static File setUpPhotoFile(Context context) throws IOException {
        File createImageFile = createImageFile(context);
        mCurrentPhotoPath = createImageFile.getAbsolutePath();
        return createImageFile;
    }

    private static File createImageFile(Context context) throws IOException {
        return new File(getAlbumDir(context), getImageFileName());
    }

    private static String getImageFileName() {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return JPEG_FILE_PREFIX + format + "_";
    }

    private static File getAlbumDir(Context context) {
        File storageDirectory = getStorageDirectory(context.getString(R.string.medscape_app_name), context);
        if (storageDirectory.mkdirs() || storageDirectory.exists()) {
            return storageDirectory;
        }
        Trace.w(TAG, "failed to create directory");
        return null;
    }

    public static File getStorageDirectory(String str, Context context) {
        return new File(FileHelper.getDataDirectory(context) + "/DCIM/" + str);
    }

    public static File getExternalPhotoSaveDirectory(String str) {
        return new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + str);
    }

    public static void clearRecentPhotoLocation() {
        mCurrentPhotoPath = null;
    }

    public static String getRecentPhotoLocation() {
        return mCurrentPhotoPath;
    }

    public static String getFilePathForImageFromGallery(Context context, Intent intent) {
        String str;
        if (intent != null) {
            Uri data = intent.getData();
            String[] strArr = {"_data"};
            Cursor query = context.getContentResolver().query(data, strArr, (String) null, (String[]) null, (String) null);
            if (query != null) {
                query.moveToFirst();
                str = query.getString(query.getColumnIndex(strArr[0]));
                query.close();
            } else {
                str = null;
            }
            if (haveValidPhoto(context, str)) {
                return str;
            }
        }
        return null;
    }

    private static boolean haveValidPhoto(Context context, String str) {
        if (str == null || str.startsWith("http")) {
            new MedscapeException(context.getString(R.string.image_not_available)).showToast(context, 1);
            return false;
        } else if (getMediaTypeFromUri(str) == -1) {
            new MedscapeException(context.getString(R.string.invalid_file_photo)).showToast(context, 1);
            return false;
        } else if (new File(str).exists()) {
            return true;
        } else {
            new MedscapeException(context.getString(R.string.image_not_available)).showToast(context, 1);
            return false;
        }
    }

    private static int getMediaTypeFromUri(String str) {
        return (str == null || (!str.toLowerCase().endsWith(JPEG_FILE_SUFFIX) && !str.toLowerCase().endsWith(".png") && !str.toLowerCase().endsWith(".gif") && !str.toLowerCase().endsWith(".bmp") && !str.toLowerCase().endsWith(".webp") && !str.toLowerCase().endsWith(".jpeg"))) ? -1 : 1;
    }
}
