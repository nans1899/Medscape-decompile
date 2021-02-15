package androidx.exifinterface.media;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.core.view.InputDeviceCompat;
import com.comscore.streaming.ContentFeedType;
import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.appevents.AppEventsConstants;
import com.google.common.base.Ascii;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.logging.type.LogSeverity;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import net.bytebuddy.description.modifier.ModifierContributor;
import org.mozilla.classfile.ByteCode;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

public class ExifInterface {
    public static final short ALTITUDE_ABOVE_SEA_LEVEL = 0;
    public static final short ALTITUDE_BELOW_SEA_LEVEL = 1;
    static final Charset ASCII;
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    public static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    static final short BYTE_ALIGN_II = 18761;
    static final short BYTE_ALIGN_MM = 19789;
    public static final int COLOR_SPACE_S_RGB = 1;
    public static final int COLOR_SPACE_UNCALIBRATED = 65535;
    public static final short CONTRAST_HARD = 2;
    public static final short CONTRAST_NORMAL = 0;
    public static final short CONTRAST_SOFT = 1;
    public static final int DATA_DEFLATE_ZIP = 8;
    public static final int DATA_HUFFMAN_COMPRESSED = 2;
    public static final int DATA_JPEG = 6;
    public static final int DATA_JPEG_COMPRESSED = 7;
    public static final int DATA_LOSSY_JPEG = 34892;
    public static final int DATA_PACK_BITS_COMPRESSED = 32773;
    public static final int DATA_UNCOMPRESSED = 1;
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};
    private static final ExifTag[] EXIF_POINTER_TAGS = {new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 1), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 1)};
    static final ExifTag[][] EXIF_TAGS;
    public static final short EXPOSURE_MODE_AUTO = 0;
    public static final short EXPOSURE_MODE_AUTO_BRACKET = 2;
    public static final short EXPOSURE_MODE_MANUAL = 1;
    public static final short EXPOSURE_PROGRAM_ACTION = 6;
    public static final short EXPOSURE_PROGRAM_APERTURE_PRIORITY = 3;
    public static final short EXPOSURE_PROGRAM_CREATIVE = 5;
    public static final short EXPOSURE_PROGRAM_LANDSCAPE_MODE = 8;
    public static final short EXPOSURE_PROGRAM_MANUAL = 1;
    public static final short EXPOSURE_PROGRAM_NORMAL = 2;
    public static final short EXPOSURE_PROGRAM_NOT_DEFINED = 0;
    public static final short EXPOSURE_PROGRAM_PORTRAIT_MODE = 7;
    public static final short EXPOSURE_PROGRAM_SHUTTER_PRIORITY = 4;
    public static final short FILE_SOURCE_DSC = 3;
    public static final short FILE_SOURCE_OTHER = 0;
    public static final short FILE_SOURCE_REFLEX_SCANNER = 2;
    public static final short FILE_SOURCE_TRANSPARENT_SCANNER = 1;
    public static final short FLAG_FLASH_FIRED = 1;
    public static final short FLAG_FLASH_MODE_AUTO = 24;
    public static final short FLAG_FLASH_MODE_COMPULSORY_FIRING = 8;
    public static final short FLAG_FLASH_MODE_COMPULSORY_SUPPRESSION = 16;
    public static final short FLAG_FLASH_NO_FLASH_FUNCTION = 32;
    public static final short FLAG_FLASH_RED_EYE_SUPPORTED = 64;
    public static final short FLAG_FLASH_RETURN_LIGHT_DETECTED = 6;
    public static final short FLAG_FLASH_RETURN_LIGHT_NOT_DETECTED = 4;
    private static final List<Integer> FLIPPED_ROTATION_ORDER = Arrays.asList(new Integer[]{2, 7, 4, 5});
    public static final short FORMAT_CHUNKY = 1;
    public static final short FORMAT_PLANAR = 2;
    public static final short GAIN_CONTROL_HIGH_GAIN_DOWN = 4;
    public static final short GAIN_CONTROL_HIGH_GAIN_UP = 2;
    public static final short GAIN_CONTROL_LOW_GAIN_DOWN = 3;
    public static final short GAIN_CONTROL_LOW_GAIN_UP = 1;
    public static final short GAIN_CONTROL_NONE = 0;
    public static final String GPS_DIRECTION_MAGNETIC = "M";
    public static final String GPS_DIRECTION_TRUE = "T";
    public static final String GPS_DISTANCE_KILOMETERS = "K";
    public static final String GPS_DISTANCE_MILES = "M";
    public static final String GPS_DISTANCE_NAUTICAL_MILES = "N";
    public static final String GPS_MEASUREMENT_2D = "2";
    public static final String GPS_MEASUREMENT_3D = "3";
    public static final short GPS_MEASUREMENT_DIFFERENTIAL_CORRECTED = 1;
    public static final String GPS_MEASUREMENT_INTERRUPTED = "V";
    public static final String GPS_MEASUREMENT_IN_PROGRESS = "A";
    public static final short GPS_MEASUREMENT_NO_DIFFERENTIAL = 0;
    public static final String GPS_SPEED_KILOMETERS_PER_HOUR = "K";
    public static final String GPS_SPEED_KNOTS = "N";
    public static final String GPS_SPEED_MILES_PER_HOUR = "M";
    private static final byte[] HEIF_BRAND_HEIC = {104, 101, 105, 99};
    private static final byte[] HEIF_BRAND_MIF1 = {109, 105, 102, 49};
    private static final byte[] HEIF_TYPE_FTYP = {102, 116, 121, 112};
    static final byte[] IDENTIFIER_EXIF_APP1;
    private static final byte[] IDENTIFIER_XMP_APP1 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(ASCII);
    private static final ExifTag[] IFD_EXIF_TAGS = {new ExifTag(TAG_EXPOSURE_TIME, 33434, 5), new ExifTag(TAG_F_NUMBER, 33437, 5), new ExifTag(TAG_EXPOSURE_PROGRAM, 34850, 3), new ExifTag(TAG_SPECTRAL_SENSITIVITY, 34852, 2), new ExifTag(TAG_PHOTOGRAPHIC_SENSITIVITY, 34855, 3), new ExifTag(TAG_OECF, 34856, 7), new ExifTag(TAG_SENSITIVITY_TYPE, 34864, 3), new ExifTag(TAG_STANDARD_OUTPUT_SENSITIVITY, 34865, 4), new ExifTag(TAG_RECOMMENDED_EXPOSURE_INDEX, 34866, 4), new ExifTag(TAG_ISO_SPEED, 34867, 4), new ExifTag(TAG_ISO_SPEED_LATITUDE_YYY, 34868, 4), new ExifTag(TAG_ISO_SPEED_LATITUDE_ZZZ, 34869, 4), new ExifTag(TAG_EXIF_VERSION, 36864, 2), new ExifTag(TAG_DATETIME_ORIGINAL, 36867, 2), new ExifTag(TAG_DATETIME_DIGITIZED, 36868, 2), new ExifTag(TAG_OFFSET_TIME, ModifierContributor.ForParameter.MASK, 2), new ExifTag(TAG_OFFSET_TIME_ORIGINAL, 36881, 2), new ExifTag(TAG_OFFSET_TIME_DIGITIZED, 36882, 2), new ExifTag(TAG_COMPONENTS_CONFIGURATION, 37121, 7), new ExifTag(TAG_COMPRESSED_BITS_PER_PIXEL, 37122, 5), new ExifTag(TAG_SHUTTER_SPEED_VALUE, 37377, 10), new ExifTag(TAG_APERTURE_VALUE, 37378, 5), new ExifTag(TAG_BRIGHTNESS_VALUE, 37379, 10), new ExifTag(TAG_EXPOSURE_BIAS_VALUE, 37380, 10), new ExifTag(TAG_MAX_APERTURE_VALUE, 37381, 5), new ExifTag(TAG_SUBJECT_DISTANCE, 37382, 5), new ExifTag(TAG_METERING_MODE, 37383, 3), new ExifTag(TAG_LIGHT_SOURCE, 37384, 3), new ExifTag(TAG_FLASH, 37385, 3), new ExifTag(TAG_FOCAL_LENGTH, 37386, 5), new ExifTag(TAG_SUBJECT_AREA, 37396, 3), new ExifTag(TAG_MAKER_NOTE, 37500, 7), new ExifTag(TAG_USER_COMMENT, 37510, 7), new ExifTag(TAG_SUBSEC_TIME, 37520, 2), new ExifTag(TAG_SUBSEC_TIME_ORIGINAL, 37521, 2), new ExifTag(TAG_SUBSEC_TIME_DIGITIZED, 37522, 2), new ExifTag(TAG_FLASHPIX_VERSION, 40960, 7), new ExifTag(TAG_COLOR_SPACE, 40961, 3), new ExifTag(TAG_PIXEL_X_DIMENSION, 40962, 3, 4), new ExifTag(TAG_PIXEL_Y_DIMENSION, 40963, 3, 4), new ExifTag(TAG_RELATED_SOUND_FILE, 40964, 2), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(TAG_FLASH_ENERGY, 41483, 5), new ExifTag(TAG_SPATIAL_FREQUENCY_RESPONSE, 41484, 7), new ExifTag(TAG_FOCAL_PLANE_X_RESOLUTION, 41486, 5), new ExifTag(TAG_FOCAL_PLANE_Y_RESOLUTION, 41487, 5), new ExifTag(TAG_FOCAL_PLANE_RESOLUTION_UNIT, 41488, 3), new ExifTag(TAG_SUBJECT_LOCATION, 41492, 3), new ExifTag(TAG_EXPOSURE_INDEX, 41493, 5), new ExifTag(TAG_SENSING_METHOD, 41495, 3), new ExifTag(TAG_FILE_SOURCE, 41728, 7), new ExifTag(TAG_SCENE_TYPE, 41729, 7), new ExifTag(TAG_CFA_PATTERN, 41730, 7), new ExifTag(TAG_CUSTOM_RENDERED, 41985, 3), new ExifTag(TAG_EXPOSURE_MODE, 41986, 3), new ExifTag(TAG_WHITE_BALANCE, 41987, 3), new ExifTag(TAG_DIGITAL_ZOOM_RATIO, 41988, 5), new ExifTag(TAG_FOCAL_LENGTH_IN_35MM_FILM, 41989, 3), new ExifTag(TAG_SCENE_CAPTURE_TYPE, 41990, 3), new ExifTag(TAG_GAIN_CONTROL, 41991, 3), new ExifTag(TAG_CONTRAST, 41992, 3), new ExifTag(TAG_SATURATION, 41993, 3), new ExifTag(TAG_SHARPNESS, 41994, 3), new ExifTag(TAG_DEVICE_SETTING_DESCRIPTION, 41995, 7), new ExifTag(TAG_SUBJECT_DISTANCE_RANGE, 41996, 3), new ExifTag(TAG_IMAGE_UNIQUE_ID, 42016, 2), new ExifTag("CameraOwnerName", 42032, 2), new ExifTag(TAG_BODY_SERIAL_NUMBER, 42033, 2), new ExifTag(TAG_LENS_SPECIFICATION, 42034, 5), new ExifTag(TAG_LENS_MAKE, 42035, 2), new ExifTag(TAG_LENS_MODEL, 42036, 2), new ExifTag(TAG_GAMMA, 42240, 5), new ExifTag(TAG_DNG_VERSION, 50706, 1), new ExifTag(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
    private static final int IFD_FORMAT_BYTE = 1;
    static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    private static final int IFD_FORMAT_DOUBLE = 12;
    private static final int IFD_FORMAT_IFD = 13;
    static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    private static final int IFD_FORMAT_SBYTE = 6;
    private static final int IFD_FORMAT_SINGLE = 11;
    private static final int IFD_FORMAT_SLONG = 9;
    private static final int IFD_FORMAT_SRATIONAL = 10;
    private static final int IFD_FORMAT_SSHORT = 8;
    private static final int IFD_FORMAT_STRING = 2;
    private static final int IFD_FORMAT_ULONG = 4;
    private static final int IFD_FORMAT_UNDEFINED = 7;
    private static final int IFD_FORMAT_URATIONAL = 5;
    private static final int IFD_FORMAT_USHORT = 3;
    private static final ExifTag[] IFD_GPS_TAGS = {new ExifTag(TAG_GPS_VERSION_ID, 0, 1), new ExifTag(TAG_GPS_LATITUDE_REF, 1, 2), new ExifTag(TAG_GPS_LATITUDE, 2, 5), new ExifTag(TAG_GPS_LONGITUDE_REF, 3, 2), new ExifTag(TAG_GPS_LONGITUDE, 4, 5), new ExifTag(TAG_GPS_ALTITUDE_REF, 5, 1), new ExifTag(TAG_GPS_ALTITUDE, 6, 5), new ExifTag(TAG_GPS_TIMESTAMP, 7, 5), new ExifTag(TAG_GPS_SATELLITES, 8, 2), new ExifTag(TAG_GPS_STATUS, 9, 2), new ExifTag(TAG_GPS_MEASURE_MODE, 10, 2), new ExifTag(TAG_GPS_DOP, 11, 5), new ExifTag(TAG_GPS_SPEED_REF, 12, 2), new ExifTag(TAG_GPS_SPEED, 13, 5), new ExifTag(TAG_GPS_TRACK_REF, 14, 2), new ExifTag(TAG_GPS_TRACK, 15, 5), new ExifTag(TAG_GPS_IMG_DIRECTION_REF, 16, 2), new ExifTag(TAG_GPS_IMG_DIRECTION, 17, 5), new ExifTag(TAG_GPS_MAP_DATUM, 18, 2), new ExifTag(TAG_GPS_DEST_LATITUDE_REF, 19, 2), new ExifTag(TAG_GPS_DEST_LATITUDE, 20, 5), new ExifTag(TAG_GPS_DEST_LONGITUDE_REF, 21, 2), new ExifTag(TAG_GPS_DEST_LONGITUDE, 22, 5), new ExifTag(TAG_GPS_DEST_BEARING_REF, 23, 2), new ExifTag(TAG_GPS_DEST_BEARING, 24, 5), new ExifTag(TAG_GPS_DEST_DISTANCE_REF, 25, 2), new ExifTag(TAG_GPS_DEST_DISTANCE, 26, 5), new ExifTag(TAG_GPS_PROCESSING_METHOD, 27, 7), new ExifTag(TAG_GPS_AREA_INFORMATION, 28, 7), new ExifTag(TAG_GPS_DATESTAMP, 29, 2), new ExifTag(TAG_GPS_DIFFERENTIAL, 30, 3), new ExifTag(TAG_GPS_H_POSITIONING_ERROR, 31, 5)};
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = {new ExifTag(TAG_INTEROPERABILITY_INDEX, 1, 2)};
    private static final int IFD_OFFSET = 8;
    private static final ExifTag[] IFD_THUMBNAIL_TAGS = {new ExifTag(TAG_NEW_SUBFILE_TYPE, ByteCode.IMPDEP1, 4), new ExifTag(TAG_SUBFILE_TYPE, 255, 4), new ExifTag(TAG_THUMBNAIL_IMAGE_WIDTH, 256, 3, 4), new ExifTag(TAG_THUMBNAIL_IMAGE_LENGTH, 257, 3, 4), new ExifTag(TAG_BITS_PER_SAMPLE, 258, 3), new ExifTag(TAG_COMPRESSION, 259, 3), new ExifTag(TAG_PHOTOMETRIC_INTERPRETATION, 262, 3), new ExifTag(TAG_IMAGE_DESCRIPTION, 270, 2), new ExifTag(TAG_MAKE, 271, 2), new ExifTag(TAG_MODEL, 272, 2), new ExifTag(TAG_STRIP_OFFSETS, 273, 3, 4), new ExifTag(TAG_THUMBNAIL_ORIENTATION, 274, 3), new ExifTag(TAG_SAMPLES_PER_PIXEL, 277, 3), new ExifTag(TAG_ROWS_PER_STRIP, 278, 3, 4), new ExifTag(TAG_STRIP_BYTE_COUNTS, 279, 3, 4), new ExifTag(TAG_X_RESOLUTION, 282, 5), new ExifTag(TAG_Y_RESOLUTION, 283, 5), new ExifTag(TAG_PLANAR_CONFIGURATION, 284, 3), new ExifTag(TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(TAG_TRANSFER_FUNCTION, ContentFeedType.EAST_HD, 3), new ExifTag(TAG_SOFTWARE, 305, 2), new ExifTag(TAG_DATETIME, 306, 2), new ExifTag(TAG_ARTIST, 315, 2), new ExifTag(TAG_WHITE_POINT, 318, 5), new ExifTag(TAG_PRIMARY_CHROMATICITIES, 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new ExifTag(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new ExifTag(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new ExifTag(TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag(TAG_REFERENCE_BLACK_WHITE, 532, 5), new ExifTag(TAG_COPYRIGHT, 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_DNG_VERSION, 50706, 1), new ExifTag(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
    private static final ExifTag[] IFD_TIFF_TAGS = {new ExifTag(TAG_NEW_SUBFILE_TYPE, ByteCode.IMPDEP1, 4), new ExifTag(TAG_SUBFILE_TYPE, 255, 4), new ExifTag(TAG_IMAGE_WIDTH, 256, 3, 4), new ExifTag(TAG_IMAGE_LENGTH, 257, 3, 4), new ExifTag(TAG_BITS_PER_SAMPLE, 258, 3), new ExifTag(TAG_COMPRESSION, 259, 3), new ExifTag(TAG_PHOTOMETRIC_INTERPRETATION, 262, 3), new ExifTag(TAG_IMAGE_DESCRIPTION, 270, 2), new ExifTag(TAG_MAKE, 271, 2), new ExifTag(TAG_MODEL, 272, 2), new ExifTag(TAG_STRIP_OFFSETS, 273, 3, 4), new ExifTag(TAG_ORIENTATION, 274, 3), new ExifTag(TAG_SAMPLES_PER_PIXEL, 277, 3), new ExifTag(TAG_ROWS_PER_STRIP, 278, 3, 4), new ExifTag(TAG_STRIP_BYTE_COUNTS, 279, 3, 4), new ExifTag(TAG_X_RESOLUTION, 282, 5), new ExifTag(TAG_Y_RESOLUTION, 283, 5), new ExifTag(TAG_PLANAR_CONFIGURATION, 284, 3), new ExifTag(TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(TAG_TRANSFER_FUNCTION, ContentFeedType.EAST_HD, 3), new ExifTag(TAG_SOFTWARE, 305, 2), new ExifTag(TAG_DATETIME, 306, 2), new ExifTag(TAG_ARTIST, 315, 2), new ExifTag(TAG_WHITE_POINT, 318, 5), new ExifTag(TAG_PRIMARY_CHROMATICITIES, 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new ExifTag(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new ExifTag(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new ExifTag(TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag(TAG_REFERENCE_BLACK_WHITE, 532, 5), new ExifTag(TAG_COPYRIGHT, 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_RW2_SENSOR_TOP_BORDER, 4, 4), new ExifTag(TAG_RW2_SENSOR_LEFT_BORDER, 5, 4), new ExifTag(TAG_RW2_SENSOR_BOTTOM_BORDER, 6, 4), new ExifTag(TAG_RW2_SENSOR_RIGHT_BORDER, 7, 4), new ExifTag(TAG_RW2_ISO, 23, 3), new ExifTag(TAG_RW2_JPG_FROM_RAW, 46, 7), new ExifTag(TAG_XMP, LogSeverity.ALERT_VALUE, 1)};
    private static final int IFD_TYPE_EXIF = 1;
    private static final int IFD_TYPE_GPS = 2;
    private static final int IFD_TYPE_INTEROPERABILITY = 3;
    private static final int IFD_TYPE_ORF_CAMERA_SETTINGS = 7;
    private static final int IFD_TYPE_ORF_IMAGE_PROCESSING = 8;
    private static final int IFD_TYPE_ORF_MAKER_NOTE = 6;
    private static final int IFD_TYPE_PEF = 9;
    static final int IFD_TYPE_PREVIEW = 5;
    static final int IFD_TYPE_PRIMARY = 0;
    static final int IFD_TYPE_THUMBNAIL = 4;
    private static final int IMAGE_TYPE_ARW = 1;
    private static final int IMAGE_TYPE_CR2 = 2;
    private static final int IMAGE_TYPE_DNG = 3;
    private static final int IMAGE_TYPE_HEIF = 12;
    private static final int IMAGE_TYPE_JPEG = 4;
    private static final int IMAGE_TYPE_NEF = 5;
    private static final int IMAGE_TYPE_NRW = 6;
    private static final int IMAGE_TYPE_ORF = 7;
    private static final int IMAGE_TYPE_PEF = 8;
    private static final int IMAGE_TYPE_PNG = 13;
    private static final int IMAGE_TYPE_RAF = 9;
    private static final int IMAGE_TYPE_RW2 = 10;
    private static final int IMAGE_TYPE_SRW = 11;
    private static final int IMAGE_TYPE_UNKNOWN = 0;
    private static final int IMAGE_TYPE_WEBP = 14;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4);
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, InputDeviceCompat.SOURCE_DPAD, 4);
    static final byte[] JPEG_SIGNATURE = {-1, MARKER_SOI, -1};
    public static final String LATITUDE_NORTH = "N";
    public static final String LATITUDE_SOUTH = "S";
    public static final short LIGHT_SOURCE_CLOUDY_WEATHER = 10;
    public static final short LIGHT_SOURCE_COOL_WHITE_FLUORESCENT = 14;
    public static final short LIGHT_SOURCE_D50 = 23;
    public static final short LIGHT_SOURCE_D55 = 20;
    public static final short LIGHT_SOURCE_D65 = 21;
    public static final short LIGHT_SOURCE_D75 = 22;
    public static final short LIGHT_SOURCE_DAYLIGHT = 1;
    public static final short LIGHT_SOURCE_DAYLIGHT_FLUORESCENT = 12;
    public static final short LIGHT_SOURCE_DAY_WHITE_FLUORESCENT = 13;
    public static final short LIGHT_SOURCE_FINE_WEATHER = 9;
    public static final short LIGHT_SOURCE_FLASH = 4;
    public static final short LIGHT_SOURCE_FLUORESCENT = 2;
    public static final short LIGHT_SOURCE_ISO_STUDIO_TUNGSTEN = 24;
    public static final short LIGHT_SOURCE_OTHER = 255;
    public static final short LIGHT_SOURCE_SHADE = 11;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_A = 17;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_B = 18;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_C = 19;
    public static final short LIGHT_SOURCE_TUNGSTEN = 3;
    public static final short LIGHT_SOURCE_UNKNOWN = 0;
    public static final short LIGHT_SOURCE_WARM_WHITE_FLUORESCENT = 16;
    public static final short LIGHT_SOURCE_WHITE_FLUORESCENT = 15;
    public static final String LONGITUDE_EAST = "E";
    public static final String LONGITUDE_WEST = "W";
    static final byte MARKER = -1;
    static final byte MARKER_APP1 = -31;
    private static final byte MARKER_COM = -2;
    static final byte MARKER_EOI = -39;
    private static final byte MARKER_SOF0 = -64;
    private static final byte MARKER_SOF1 = -63;
    private static final byte MARKER_SOF10 = -54;
    private static final byte MARKER_SOF11 = -53;
    private static final byte MARKER_SOF13 = -51;
    private static final byte MARKER_SOF14 = -50;
    private static final byte MARKER_SOF15 = -49;
    private static final byte MARKER_SOF2 = -62;
    private static final byte MARKER_SOF3 = -61;
    private static final byte MARKER_SOF5 = -59;
    private static final byte MARKER_SOF6 = -58;
    private static final byte MARKER_SOF7 = -57;
    private static final byte MARKER_SOF9 = -55;
    private static final byte MARKER_SOI = -40;
    private static final byte MARKER_SOS = -38;
    private static final int MAX_THUMBNAIL_SIZE = 512;
    public static final short METERING_MODE_AVERAGE = 1;
    public static final short METERING_MODE_CENTER_WEIGHT_AVERAGE = 2;
    public static final short METERING_MODE_MULTI_SPOT = 4;
    public static final short METERING_MODE_OTHER = 255;
    public static final short METERING_MODE_PARTIAL = 6;
    public static final short METERING_MODE_PATTERN = 5;
    public static final short METERING_MODE_SPOT = 3;
    public static final short METERING_MODE_UNKNOWN = 0;
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS = {new ExifTag(TAG_ORF_PREVIEW_IMAGE_START, 257, 4), new ExifTag(TAG_ORF_PREVIEW_IMAGE_LENGTH, 258, 4)};
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS = {new ExifTag(TAG_ORF_ASPECT_FRAME, 4371, 3)};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, ClassDefinitionUtils.OPS_dup, 77, 80, 0};
    private static final int ORF_MAKER_NOTE_HEADER_1_SIZE = 8;
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, ClassDefinitionUtils.OPS_dup, 77, 80, 85, 83, 0, 73, 73};
    private static final int ORF_MAKER_NOTE_HEADER_2_SIZE = 12;
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS = {new ExifTag(TAG_ORF_THUMBNAIL_IMAGE, 256, 7), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 4), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 4)};
    private static final short ORF_SIGNATURE_1 = 20306;
    private static final short ORF_SIGNATURE_2 = 21330;
    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
    public static final int ORIENTATION_FLIP_VERTICAL = 4;
    public static final int ORIENTATION_NORMAL = 1;
    public static final int ORIENTATION_ROTATE_180 = 3;
    public static final int ORIENTATION_ROTATE_270 = 8;
    public static final int ORIENTATION_ROTATE_90 = 6;
    public static final int ORIENTATION_TRANSPOSE = 5;
    public static final int ORIENTATION_TRANSVERSE = 7;
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final int ORIGINAL_RESOLUTION_IMAGE = 0;
    private static final int PEF_MAKER_NOTE_SKIP_SIZE = 6;
    private static final String PEF_SIGNATURE = "PENTAX";
    private static final ExifTag[] PEF_TAGS;
    public static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    private static final int PNG_CHUNK_CRC_BYTE_LENGTH = 4;
    private static final int PNG_CHUNK_TYPE_BYTE_LENGTH = 4;
    private static final byte[] PNG_CHUNK_TYPE_EXIF = {101, 88, 73, 102};
    private static final byte[] PNG_CHUNK_TYPE_IEND = {73, 69, 78, 68};
    private static final byte[] PNG_CHUNK_TYPE_IHDR = {73, 72, 68, 82};
    private static final byte[] PNG_SIGNATURE = {-119, 80, 78, 71, Ascii.CR, 10, Ascii.SUB, 10};
    private static final int RAF_JPEG_LENGTH_VALUE_SIZE = 4;
    private static final int RAF_OFFSET_TO_JPEG_IMAGE_OFFSET = 84;
    private static final String RAF_SIGNATURE = "FUJIFILMCCD-RAW";
    public static final int REDUCED_RESOLUTION_IMAGE = 1;
    public static final short RENDERED_PROCESS_CUSTOM = 1;
    public static final short RENDERED_PROCESS_NORMAL = 0;
    public static final short RESOLUTION_UNIT_CENTIMETERS = 3;
    public static final short RESOLUTION_UNIT_INCHES = 2;
    private static final List<Integer> ROTATION_ORDER = Arrays.asList(new Integer[]{1, 6, 3, 8});
    private static final short RW2_SIGNATURE = 85;
    public static final short SATURATION_HIGH = 0;
    public static final short SATURATION_LOW = 0;
    public static final short SATURATION_NORMAL = 0;
    public static final short SCENE_CAPTURE_TYPE_LANDSCAPE = 1;
    public static final short SCENE_CAPTURE_TYPE_NIGHT = 3;
    public static final short SCENE_CAPTURE_TYPE_PORTRAIT = 2;
    public static final short SCENE_CAPTURE_TYPE_STANDARD = 0;
    public static final short SCENE_TYPE_DIRECTLY_PHOTOGRAPHED = 1;
    public static final short SENSITIVITY_TYPE_ISO_SPEED = 3;
    public static final short SENSITIVITY_TYPE_REI = 2;
    public static final short SENSITIVITY_TYPE_REI_AND_ISO = 6;
    public static final short SENSITIVITY_TYPE_SOS = 1;
    public static final short SENSITIVITY_TYPE_SOS_AND_ISO = 5;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI = 4;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI_AND_ISO = 7;
    public static final short SENSITIVITY_TYPE_UNKNOWN = 0;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL = 5;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL_LINEAR = 8;
    public static final short SENSOR_TYPE_NOT_DEFINED = 1;
    public static final short SENSOR_TYPE_ONE_CHIP = 2;
    public static final short SENSOR_TYPE_THREE_CHIP = 4;
    public static final short SENSOR_TYPE_TRILINEAR = 7;
    public static final short SENSOR_TYPE_TWO_CHIP = 3;
    public static final short SHARPNESS_HARD = 2;
    public static final short SHARPNESS_NORMAL = 0;
    public static final short SHARPNESS_SOFT = 1;
    private static final int SIGNATURE_CHECK_SIZE = 5000;
    static final byte START_CODE = 42;
    public static final int STREAM_TYPE_EXIF_DATA_ONLY = 1;
    public static final int STREAM_TYPE_FULL_IMAGE_DATA = 0;
    public static final short SUBJECT_DISTANCE_RANGE_CLOSE_VIEW = 2;
    public static final short SUBJECT_DISTANCE_RANGE_DISTANT_VIEW = 3;
    public static final short SUBJECT_DISTANCE_RANGE_MACRO = 1;
    public static final short SUBJECT_DISTANCE_RANGE_UNKNOWN = 0;
    private static final String TAG = "ExifInterface";
    public static final String TAG_APERTURE_VALUE = "ApertureValue";
    public static final String TAG_ARTIST = "Artist";
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";
    public static final String TAG_BODY_SERIAL_NUMBER = "BodySerialNumber";
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";
    @Deprecated
    public static final String TAG_CAMARA_OWNER_NAME = "CameraOwnerName";
    public static final String TAG_CAMERA_OWNER_NAME = "CameraOwnerName";
    public static final String TAG_CFA_PATTERN = "CFAPattern";
    public static final String TAG_COLOR_SPACE = "ColorSpace";
    public static final String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
    public static final String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";
    public static final String TAG_COMPRESSION = "Compression";
    public static final String TAG_CONTRAST = "Contrast";
    public static final String TAG_COPYRIGHT = "Copyright";
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";
    public static final String TAG_DATETIME = "DateTime";
    public static final String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";
    public static final String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";
    public static final String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";
    public static final String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    public static final String TAG_DNG_VERSION = "DNGVersion";
    private static final String TAG_EXIF_IFD_POINTER = "ExifIFDPointer";
    public static final String TAG_EXIF_VERSION = "ExifVersion";
    public static final String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
    public static final String TAG_EXPOSURE_INDEX = "ExposureIndex";
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";
    public static final String TAG_FILE_SOURCE = "FileSource";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FLASHPIX_VERSION = "FlashpixVersion";
    public static final String TAG_FLASH_ENERGY = "FlashEnergy";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";
    public static final String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
    public static final String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";
    public static final String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";
    public static final String TAG_F_NUMBER = "FNumber";
    public static final String TAG_GAIN_CONTROL = "GainControl";
    public static final String TAG_GAMMA = "Gamma";
    public static final String TAG_GPS_ALTITUDE = "GPSAltitude";
    public static final String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";
    public static final String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";
    public static final String TAG_GPS_DEST_BEARING = "GPSDestBearing";
    public static final String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";
    public static final String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";
    public static final String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
    public static final String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";
    public static final String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";
    public static final String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";
    public static final String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";
    public static final String TAG_GPS_DIFFERENTIAL = "GPSDifferential";
    public static final String TAG_GPS_DOP = "GPSDOP";
    public static final String TAG_GPS_H_POSITIONING_ERROR = "GPSHPositioningError";
    public static final String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";
    public static final String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
    private static final String TAG_GPS_INFO_IFD_POINTER = "GPSInfoIFDPointer";
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
    public static final String TAG_GPS_MAP_DATUM = "GPSMapDatum";
    public static final String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    public static final String TAG_GPS_SATELLITES = "GPSSatellites";
    public static final String TAG_GPS_SPEED = "GPSSpeed";
    public static final String TAG_GPS_SPEED_REF = "GPSSpeedRef";
    public static final String TAG_GPS_STATUS = "GPSStatus";
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String TAG_GPS_TRACK = "GPSTrack";
    public static final String TAG_GPS_TRACK_REF = "GPSTrackRef";
    public static final String TAG_GPS_VERSION_ID = "GPSVersionID";
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    private static final String TAG_INTEROPERABILITY_IFD_POINTER = "InteroperabilityIFDPointer";
    public static final String TAG_INTEROPERABILITY_INDEX = "InteroperabilityIndex";
    public static final String TAG_ISO_SPEED = "ISOSpeed";
    public static final String TAG_ISO_SPEED_LATITUDE_YYY = "ISOSpeedLatitudeyyy";
    public static final String TAG_ISO_SPEED_LATITUDE_ZZZ = "ISOSpeedLatitudezzz";
    @Deprecated
    public static final String TAG_ISO_SPEED_RATINGS = "ISOSpeedRatings";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT = "JPEGInterchangeFormat";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = "JPEGInterchangeFormatLength";
    public static final String TAG_LENS_MAKE = "LensMake";
    public static final String TAG_LENS_MODEL = "LensModel";
    public static final String TAG_LENS_SERIAL_NUMBER = "LensSerialNumber";
    public static final String TAG_LENS_SPECIFICATION = "LensSpecification";
    public static final String TAG_LIGHT_SOURCE = "LightSource";
    public static final String TAG_MAKE = "Make";
    public static final String TAG_MAKER_NOTE = "MakerNote";
    public static final String TAG_MAX_APERTURE_VALUE = "MaxApertureValue";
    public static final String TAG_METERING_MODE = "MeteringMode";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_NEW_SUBFILE_TYPE = "NewSubfileType";
    public static final String TAG_OECF = "OECF";
    public static final String TAG_OFFSET_TIME = "OffsetTime";
    public static final String TAG_OFFSET_TIME_DIGITIZED = "OffsetTimeDigitized";
    public static final String TAG_OFFSET_TIME_ORIGINAL = "OffsetTimeOriginal";
    public static final String TAG_ORF_ASPECT_FRAME = "AspectFrame";
    private static final String TAG_ORF_CAMERA_SETTINGS_IFD_POINTER = "CameraSettingsIFDPointer";
    private static final String TAG_ORF_IMAGE_PROCESSING_IFD_POINTER = "ImageProcessingIFDPointer";
    public static final String TAG_ORF_PREVIEW_IMAGE_LENGTH = "PreviewImageLength";
    public static final String TAG_ORF_PREVIEW_IMAGE_START = "PreviewImageStart";
    public static final String TAG_ORF_THUMBNAIL_IMAGE = "ThumbnailImage";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_PHOTOGRAPHIC_SENSITIVITY = "PhotographicSensitivity";
    public static final String TAG_PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";
    public static final String TAG_PIXEL_X_DIMENSION = "PixelXDimension";
    public static final String TAG_PIXEL_Y_DIMENSION = "PixelYDimension";
    public static final String TAG_PLANAR_CONFIGURATION = "PlanarConfiguration";
    public static final String TAG_PRIMARY_CHROMATICITIES = "PrimaryChromaticities";
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag(TAG_STRIP_OFFSETS, 273, 3);
    public static final String TAG_RECOMMENDED_EXPOSURE_INDEX = "RecommendedExposureIndex";
    public static final String TAG_REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";
    public static final String TAG_RELATED_SOUND_FILE = "RelatedSoundFile";
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";
    public static final String TAG_ROWS_PER_STRIP = "RowsPerStrip";
    public static final String TAG_RW2_ISO = "ISO";
    public static final String TAG_RW2_JPG_FROM_RAW = "JpgFromRaw";
    public static final String TAG_RW2_SENSOR_BOTTOM_BORDER = "SensorBottomBorder";
    public static final String TAG_RW2_SENSOR_LEFT_BORDER = "SensorLeftBorder";
    public static final String TAG_RW2_SENSOR_RIGHT_BORDER = "SensorRightBorder";
    public static final String TAG_RW2_SENSOR_TOP_BORDER = "SensorTopBorder";
    public static final String TAG_SAMPLES_PER_PIXEL = "SamplesPerPixel";
    public static final String TAG_SATURATION = "Saturation";
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";
    public static final String TAG_SCENE_TYPE = "SceneType";
    public static final String TAG_SENSING_METHOD = "SensingMethod";
    public static final String TAG_SENSITIVITY_TYPE = "SensitivityType";
    public static final String TAG_SHARPNESS = "Sharpness";
    public static final String TAG_SHUTTER_SPEED_VALUE = "ShutterSpeedValue";
    public static final String TAG_SOFTWARE = "Software";
    public static final String TAG_SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";
    public static final String TAG_SPECTRAL_SENSITIVITY = "SpectralSensitivity";
    public static final String TAG_STANDARD_OUTPUT_SENSITIVITY = "StandardOutputSensitivity";
    public static final String TAG_STRIP_BYTE_COUNTS = "StripByteCounts";
    public static final String TAG_STRIP_OFFSETS = "StripOffsets";
    public static final String TAG_SUBFILE_TYPE = "SubfileType";
    public static final String TAG_SUBJECT_AREA = "SubjectArea";
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    public static final String TAG_SUBJECT_LOCATION = "SubjectLocation";
    public static final String TAG_SUBSEC_TIME = "SubSecTime";
    public static final String TAG_SUBSEC_TIME_DIGITIZED = "SubSecTimeDigitized";
    public static final String TAG_SUBSEC_TIME_ORIGINAL = "SubSecTimeOriginal";
    private static final String TAG_SUB_IFD_POINTER = "SubIFDPointer";
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";
    public static final String TAG_THUMBNAIL_ORIENTATION = "ThumbnailOrientation";
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";
    public static final String TAG_USER_COMMENT = "UserComment";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_WHITE_POINT = "WhitePoint";
    public static final String TAG_XMP = "Xmp";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    public static final String TAG_Y_RESOLUTION = "YResolution";
    private static final int WEBP_CHUNK_SIZE_BYTE_LENGTH = 4;
    private static final byte[] WEBP_CHUNK_TYPE_ANIM = "ANIM".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_ANMF = "ANMF".getBytes(Charset.defaultCharset());
    private static final int WEBP_CHUNK_TYPE_BYTE_LENGTH = 4;
    private static final byte[] WEBP_CHUNK_TYPE_EXIF = {69, 88, 73, 70};
    private static final byte[] WEBP_CHUNK_TYPE_VP8 = "VP8 ".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_VP8L = "VP8L".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_VP8X = "VP8X".getBytes(Charset.defaultCharset());
    private static final int WEBP_CHUNK_TYPE_VP8X_DEFAULT_LENGTH = 10;
    private static final byte[] WEBP_CHUNK_TYPE_XMP = "XMP ".getBytes(Charset.defaultCharset());
    private static final int WEBP_FILE_SIZE_BYTE_LENGTH = 4;
    private static final byte[] WEBP_SIGNATURE_1 = {82, 73, 70, 70};
    private static final byte[] WEBP_SIGNATURE_2 = {87, 69, 66, 80};
    private static final byte WEBP_VP8L_SIGNATURE = 47;
    private static final byte[] WEBP_VP8_SIGNATURE = {-99, 1, 42};
    @Deprecated
    public static final int WHITEBALANCE_AUTO = 0;
    @Deprecated
    public static final int WHITEBALANCE_MANUAL = 1;
    public static final short WHITE_BALANCE_AUTO = 0;
    public static final short WHITE_BALANCE_MANUAL = 1;
    public static final short Y_CB_CR_POSITIONING_CENTERED = 1;
    public static final short Y_CB_CR_POSITIONING_CO_SITED = 2;
    private static final HashMap<Integer, Integer> sExifPointerTagMap = new HashMap<>();
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading;
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting;
    private static SimpleDateFormat sFormatter;
    private static final Pattern sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    private static final Pattern sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
    private static final HashSet<String> sTagSetForCompatibility = new HashSet<>(Arrays.asList(new String[]{TAG_F_NUMBER, TAG_DIGITAL_ZOOM_RATIO, TAG_EXPOSURE_TIME, TAG_SUBJECT_DISTANCE, TAG_GPS_TIMESTAMP}));
    private boolean mAreThumbnailStripsConsecutive;
    private AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes;
    private Set<Integer> mAttributesOffsets;
    private ByteOrder mExifByteOrder;
    private int mExifOffset;
    private String mFilename;
    private boolean mHasThumbnail;
    private boolean mHasThumbnailStrips;
    private boolean mIsExifDataOnly;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private boolean mModified;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private FileDescriptor mSeekableFileDescriptor;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;
    private boolean mXmpIsFromSeparateMarker;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExifStreamType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IfdType {
    }

    static {
        ExifTag[] exifTagArr = {new ExifTag(TAG_COLOR_SPACE, 55, 3)};
        PEF_TAGS = exifTagArr;
        ExifTag[] exifTagArr2 = IFD_TIFF_TAGS;
        EXIF_TAGS = new ExifTag[][]{exifTagArr2, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, exifTagArr2, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, exifTagArr};
        ExifTag[][] exifTagArr3 = EXIF_TAGS;
        sExifTagMapsForReading = new HashMap[exifTagArr3.length];
        sExifTagMapsForWriting = new HashMap[exifTagArr3.length];
        Charset forName = Charset.forName("US-ASCII");
        ASCII = forName;
        IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(forName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        sFormatter = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            sExifTagMapsForReading[i] = new HashMap<>();
            sExifTagMapsForWriting[i] = new HashMap<>();
            for (ExifTag exifTag : EXIF_TAGS[i]) {
                sExifTagMapsForReading[i].put(Integer.valueOf(exifTag.number), exifTag);
                sExifTagMapsForWriting[i].put(exifTag.name, exifTag);
            }
        }
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[0].number), 5);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[1].number), 1);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[2].number), 2);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[3].number), 3);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[4].number), 7);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[5].number), 8);
    }

    private static class Rational {
        public final long denominator;
        public final long numerator;

        Rational(double d) {
            this((long) (d * 10000.0d), 10000);
        }

        Rational(long j, long j2) {
            if (j2 == 0) {
                this.numerator = 0;
                this.denominator = 1;
                return;
            }
            this.numerator = j;
            this.denominator = j2;
        }

        public String toString() {
            return this.numerator + "/" + this.denominator;
        }

        public double calculate() {
            return ((double) this.numerator) / ((double) this.denominator);
        }
    }

    private static class ExifAttribute {
        public static final long BYTES_OFFSET_UNKNOWN = -1;
        public final byte[] bytes;
        public final long bytesOffset;
        public final int format;
        public final int numberOfComponents;

        ExifAttribute(int i, int i2, byte[] bArr) {
            this(i, i2, -1, bArr);
        }

        ExifAttribute(int i, int i2, long j, byte[] bArr) {
            this.format = i;
            this.numberOfComponents = i2;
            this.bytesOffset = j;
            this.bytes = bArr;
        }

        public static ExifAttribute createUShort(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * iArr.length)]);
            wrap.order(byteOrder);
            for (int i : iArr) {
                wrap.putShort((short) i);
            }
            return new ExifAttribute(3, iArr.length, wrap.array());
        }

        public static ExifAttribute createUShort(int i, ByteOrder byteOrder) {
            return createUShort(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createULong(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * jArr.length)]);
            wrap.order(byteOrder);
            for (long j : jArr) {
                wrap.putInt((int) j);
            }
            return new ExifAttribute(4, jArr.length, wrap.array());
        }

        public static ExifAttribute createULong(long j, ByteOrder byteOrder) {
            return createULong(new long[]{j}, byteOrder);
        }

        public static ExifAttribute createSLong(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[9] * iArr.length)]);
            wrap.order(byteOrder);
            for (int putInt : iArr) {
                wrap.putInt(putInt);
            }
            return new ExifAttribute(9, iArr.length, wrap.array());
        }

        public static ExifAttribute createSLong(int i, ByteOrder byteOrder) {
            return createSLong(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createByte(String str) {
            if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
                byte[] bytes2 = str.getBytes(ExifInterface.ASCII);
                return new ExifAttribute(1, bytes2.length, bytes2);
            }
            return new ExifAttribute(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
        }

        public static ExifAttribute createString(String str) {
            byte[] bytes2 = (str + 0).getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, bytes2.length, bytes2);
        }

        public static ExifAttribute createURational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(5, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createURational(Rational rational, ByteOrder byteOrder) {
            return createURational(new Rational[]{rational}, byteOrder);
        }

        public static ExifAttribute createSRational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[10] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(10, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createSRational(Rational rational, ByteOrder byteOrder) {
            return createSRational(new Rational[]{rational}, byteOrder);
        }

        public static ExifAttribute createDouble(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[12] * dArr.length)]);
            wrap.order(byteOrder);
            for (double putDouble : dArr) {
                wrap.putDouble(putDouble);
            }
            return new ExifAttribute(12, dArr.length, wrap.array());
        }

        public static ExifAttribute createDouble(double d, ByteOrder byteOrder) {
            return createDouble(new double[]{d}, byteOrder);
        }

        public String toString() {
            return "(" + ExifInterface.IFD_FORMAT_NAMES[this.format] + ", data length:" + this.bytes.length + ")";
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:164:0x01ab A[SYNTHETIC, Splitter:B:164:0x01ab] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getValue(java.nio.ByteOrder r11) {
            /*
                r10 = this;
                java.lang.String r0 = "IOException occurred while closing InputStream"
                java.lang.String r1 = "ExifInterface"
                r2 = 0
                androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r3 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ IOException -> 0x0195, all -> 0x0193 }
                byte[] r4 = r10.bytes     // Catch:{ IOException -> 0x0195, all -> 0x0193 }
                r3.<init>((byte[]) r4)     // Catch:{ IOException -> 0x0195, all -> 0x0193 }
                r3.setByteOrder(r11)     // Catch:{ IOException -> 0x0191 }
                int r11 = r10.format     // Catch:{ IOException -> 0x0191 }
                r4 = 1
                r5 = 0
                switch(r11) {
                    case 1: goto L_0x014c;
                    case 2: goto L_0x00fd;
                    case 3: goto L_0x00e3;
                    case 4: goto L_0x00c9;
                    case 5: goto L_0x00a6;
                    case 6: goto L_0x014c;
                    case 7: goto L_0x00fd;
                    case 8: goto L_0x008c;
                    case 9: goto L_0x0072;
                    case 10: goto L_0x004d;
                    case 11: goto L_0x0032;
                    case 12: goto L_0x0018;
                    default: goto L_0x0016;
                }     // Catch:{ IOException -> 0x0191 }
            L_0x0016:
                goto L_0x0188
            L_0x0018:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                double[] r11 = new double[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x001c:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x0029
                double r6 = r3.readDouble()     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r6     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x001c
            L_0x0029:
                r3.close()     // Catch:{ IOException -> 0x002d }
                goto L_0x0031
            L_0x002d:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x0031:
                return r11
            L_0x0032:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                double[] r11 = new double[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x0036:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x0044
                float r4 = r3.readFloat()     // Catch:{ IOException -> 0x0191 }
                double r6 = (double) r4     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r6     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x0036
            L_0x0044:
                r3.close()     // Catch:{ IOException -> 0x0048 }
                goto L_0x004c
            L_0x0048:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x004c:
                return r11
            L_0x004d:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                androidx.exifinterface.media.ExifInterface$Rational[] r11 = new androidx.exifinterface.media.ExifInterface.Rational[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x0051:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x0069
                int r4 = r3.readInt()     // Catch:{ IOException -> 0x0191 }
                long r6 = (long) r4     // Catch:{ IOException -> 0x0191 }
                int r4 = r3.readInt()     // Catch:{ IOException -> 0x0191 }
                long r8 = (long) r4     // Catch:{ IOException -> 0x0191 }
                androidx.exifinterface.media.ExifInterface$Rational r4 = new androidx.exifinterface.media.ExifInterface$Rational     // Catch:{ IOException -> 0x0191 }
                r4.<init>(r6, r8)     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r4     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x0051
            L_0x0069:
                r3.close()     // Catch:{ IOException -> 0x006d }
                goto L_0x0071
            L_0x006d:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x0071:
                return r11
            L_0x0072:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                int[] r11 = new int[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x0076:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x0083
                int r4 = r3.readInt()     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r4     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x0076
            L_0x0083:
                r3.close()     // Catch:{ IOException -> 0x0087 }
                goto L_0x008b
            L_0x0087:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x008b:
                return r11
            L_0x008c:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                int[] r11 = new int[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x0090:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x009d
                short r4 = r3.readShort()     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r4     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x0090
            L_0x009d:
                r3.close()     // Catch:{ IOException -> 0x00a1 }
                goto L_0x00a5
            L_0x00a1:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x00a5:
                return r11
            L_0x00a6:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                androidx.exifinterface.media.ExifInterface$Rational[] r11 = new androidx.exifinterface.media.ExifInterface.Rational[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x00aa:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x00c0
                long r6 = r3.readUnsignedInt()     // Catch:{ IOException -> 0x0191 }
                long r8 = r3.readUnsignedInt()     // Catch:{ IOException -> 0x0191 }
                androidx.exifinterface.media.ExifInterface$Rational r4 = new androidx.exifinterface.media.ExifInterface$Rational     // Catch:{ IOException -> 0x0191 }
                r4.<init>(r6, r8)     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r4     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x00aa
            L_0x00c0:
                r3.close()     // Catch:{ IOException -> 0x00c4 }
                goto L_0x00c8
            L_0x00c4:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x00c8:
                return r11
            L_0x00c9:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                long[] r11 = new long[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x00cd:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x00da
                long r6 = r3.readUnsignedInt()     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r6     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x00cd
            L_0x00da:
                r3.close()     // Catch:{ IOException -> 0x00de }
                goto L_0x00e2
            L_0x00de:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x00e2:
                return r11
            L_0x00e3:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                int[] r11 = new int[r11]     // Catch:{ IOException -> 0x0191 }
            L_0x00e7:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x00f4
                int r4 = r3.readUnsignedShort()     // Catch:{ IOException -> 0x0191 }
                r11[r5] = r4     // Catch:{ IOException -> 0x0191 }
                int r5 = r5 + 1
                goto L_0x00e7
            L_0x00f4:
                r3.close()     // Catch:{ IOException -> 0x00f8 }
                goto L_0x00fc
            L_0x00f8:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x00fc:
                return r11
            L_0x00fd:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                byte[] r6 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0191 }
                int r6 = r6.length     // Catch:{ IOException -> 0x0191 }
                if (r11 < r6) goto L_0x011e
                r11 = 0
            L_0x0105:
                byte[] r6 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0191 }
                int r6 = r6.length     // Catch:{ IOException -> 0x0191 }
                if (r11 >= r6) goto L_0x0119
                byte[] r6 = r10.bytes     // Catch:{ IOException -> 0x0191 }
                byte r6 = r6[r11]     // Catch:{ IOException -> 0x0191 }
                byte[] r7 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0191 }
                byte r7 = r7[r11]     // Catch:{ IOException -> 0x0191 }
                if (r6 == r7) goto L_0x0116
                r4 = 0
                goto L_0x0119
            L_0x0116:
                int r11 = r11 + 1
                goto L_0x0105
            L_0x0119:
                if (r4 == 0) goto L_0x011e
                byte[] r11 = androidx.exifinterface.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x0191 }
                int r5 = r11.length     // Catch:{ IOException -> 0x0191 }
            L_0x011e:
                java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0191 }
                r11.<init>()     // Catch:{ IOException -> 0x0191 }
            L_0x0123:
                int r4 = r10.numberOfComponents     // Catch:{ IOException -> 0x0191 }
                if (r5 >= r4) goto L_0x013f
                byte[] r4 = r10.bytes     // Catch:{ IOException -> 0x0191 }
                byte r4 = r4[r5]     // Catch:{ IOException -> 0x0191 }
                if (r4 != 0) goto L_0x012e
                goto L_0x013f
            L_0x012e:
                r6 = 32
                if (r4 < r6) goto L_0x0137
                char r4 = (char) r4     // Catch:{ IOException -> 0x0191 }
                r11.append(r4)     // Catch:{ IOException -> 0x0191 }
                goto L_0x013c
            L_0x0137:
                r4 = 63
                r11.append(r4)     // Catch:{ IOException -> 0x0191 }
            L_0x013c:
                int r5 = r5 + 1
                goto L_0x0123
            L_0x013f:
                java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x0191 }
                r3.close()     // Catch:{ IOException -> 0x0147 }
                goto L_0x014b
            L_0x0147:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x014b:
                return r11
            L_0x014c:
                byte[] r11 = r10.bytes     // Catch:{ IOException -> 0x0191 }
                int r11 = r11.length     // Catch:{ IOException -> 0x0191 }
                if (r11 != r4) goto L_0x0176
                byte[] r11 = r10.bytes     // Catch:{ IOException -> 0x0191 }
                byte r11 = r11[r5]     // Catch:{ IOException -> 0x0191 }
                if (r11 < 0) goto L_0x0176
                byte[] r11 = r10.bytes     // Catch:{ IOException -> 0x0191 }
                byte r11 = r11[r5]     // Catch:{ IOException -> 0x0191 }
                if (r11 > r4) goto L_0x0176
                java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x0191 }
                char[] r4 = new char[r4]     // Catch:{ IOException -> 0x0191 }
                byte[] r6 = r10.bytes     // Catch:{ IOException -> 0x0191 }
                byte r6 = r6[r5]     // Catch:{ IOException -> 0x0191 }
                int r6 = r6 + 48
                char r6 = (char) r6     // Catch:{ IOException -> 0x0191 }
                r4[r5] = r6     // Catch:{ IOException -> 0x0191 }
                r11.<init>(r4)     // Catch:{ IOException -> 0x0191 }
                r3.close()     // Catch:{ IOException -> 0x0171 }
                goto L_0x0175
            L_0x0171:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x0175:
                return r11
            L_0x0176:
                java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x0191 }
                byte[] r4 = r10.bytes     // Catch:{ IOException -> 0x0191 }
                java.nio.charset.Charset r5 = androidx.exifinterface.media.ExifInterface.ASCII     // Catch:{ IOException -> 0x0191 }
                r11.<init>(r4, r5)     // Catch:{ IOException -> 0x0191 }
                r3.close()     // Catch:{ IOException -> 0x0183 }
                goto L_0x0187
            L_0x0183:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x0187:
                return r11
            L_0x0188:
                r3.close()     // Catch:{ IOException -> 0x018c }
                goto L_0x0190
            L_0x018c:
                r11 = move-exception
                android.util.Log.e(r1, r0, r11)
            L_0x0190:
                return r2
            L_0x0191:
                r11 = move-exception
                goto L_0x0197
            L_0x0193:
                r11 = move-exception
                goto L_0x01a9
            L_0x0195:
                r11 = move-exception
                r3 = r2
            L_0x0197:
                java.lang.String r4 = "IOException occurred during reading a value"
                android.util.Log.w(r1, r4, r11)     // Catch:{ all -> 0x01a7 }
                if (r3 == 0) goto L_0x01a6
                r3.close()     // Catch:{ IOException -> 0x01a2 }
                goto L_0x01a6
            L_0x01a2:
                r11 = move-exception
                android.util.Log.e(r1, r0, r11)
            L_0x01a6:
                return r2
            L_0x01a7:
                r11 = move-exception
                r2 = r3
            L_0x01a9:
                if (r2 == 0) goto L_0x01b3
                r2.close()     // Catch:{ IOException -> 0x01af }
                goto L_0x01b3
            L_0x01af:
                r2 = move-exception
                android.util.Log.e(r1, r0, r2)
            L_0x01b3:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object");
        }

        public double getDoubleValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (double) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return (double) iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof double[]) {
                    double[] dArr = (double[]) value;
                    if (dArr.length == 1) {
                        return dArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof Rational[]) {
                    Rational[] rationalArr = (Rational[]) value;
                    if (rationalArr.length == 1) {
                        return rationalArr[0].calculate();
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
            }
        }

        public int getIntValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (int) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a integer value");
                }
            }
        }

        public String getStringValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    i++;
                    if (i != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (value instanceof int[]) {
                int[] iArr = (int[]) value;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    i++;
                    if (i != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    i++;
                    if (i != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (!(value instanceof Rational[])) {
                return null;
            } else {
                Rational[] rationalArr = (Rational[]) value;
                while (i < rationalArr.length) {
                    sb.append(rationalArr[i].numerator);
                    sb.append('/');
                    sb.append(rationalArr[i].denominator);
                    i++;
                    if (i != rationalArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
        }

        public int size() {
            return ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[this.format] * this.numberOfComponents;
        }
    }

    static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        ExifTag(String str, int i, int i2) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = -1;
        }

        ExifTag(String str, int i, int i2, int i3) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = i3;
        }

        /* access modifiers changed from: package-private */
        public boolean isFormatCompatible(int i) {
            int i2;
            int i3 = this.primaryFormat;
            if (i3 == 7 || i == 7 || i3 == i || (i2 = this.secondaryFormat) == i) {
                return true;
            }
            if ((i3 == 4 || i2 == 4) && i == 3) {
                return true;
            }
            if ((this.primaryFormat == 9 || this.secondaryFormat == 9) && i == 8) {
                return true;
            }
            if ((this.primaryFormat == 12 || this.secondaryFormat == 12) && i == 11) {
                return true;
            }
            return false;
        }
    }

    public ExifInterface(File file) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mAttributesOffsets = new HashSet(EXIF_TAGS.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (file != null) {
            initForFilename(file.getAbsolutePath());
            return;
        }
        throw new NullPointerException("file cannot be null");
    }

    public ExifInterface(String str) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mAttributesOffsets = new HashSet(EXIF_TAGS.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (str != null) {
            initForFilename(str);
            return;
        }
        throw new NullPointerException("filename cannot be null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExifInterface(java.io.FileDescriptor r6) throws java.io.IOException {
        /*
            r5 = this;
            r5.<init>()
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r0 = EXIF_TAGS
            int r0 = r0.length
            java.util.HashMap[] r0 = new java.util.HashMap[r0]
            r5.mAttributes = r0
            java.util.HashSet r0 = new java.util.HashSet
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r1 = EXIF_TAGS
            int r1 = r1.length
            r0.<init>(r1)
            r5.mAttributesOffsets = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.BIG_ENDIAN
            r5.mExifByteOrder = r0
            if (r6 == 0) goto L_0x005f
            r0 = 0
            r5.mAssetInputStream = r0
            r5.mFilename = r0
            r1 = 0
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            if (r2 < r3) goto L_0x003d
            boolean r2 = isSeekableFD(r6)
            if (r2 == 0) goto L_0x003d
            r5.mSeekableFileDescriptor = r6
            java.io.FileDescriptor r6 = android.system.Os.dup(r6)     // Catch:{ Exception -> 0x0034 }
            r1 = 1
            goto L_0x003f
        L_0x0034:
            r6 = move-exception
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to duplicate file descriptor"
            r0.<init>(r1, r6)
            throw r0
        L_0x003d:
            r5.mSeekableFileDescriptor = r0
        L_0x003f:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0052 }
            r2.<init>(r6)     // Catch:{ all -> 0x0052 }
            r5.loadAttributes(r2)     // Catch:{ all -> 0x0050 }
            closeQuietly(r2)
            if (r1 == 0) goto L_0x004f
            closeFileDescriptor(r6)
        L_0x004f:
            return
        L_0x0050:
            r0 = move-exception
            goto L_0x0056
        L_0x0052:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
        L_0x0056:
            closeQuietly(r2)
            if (r1 == 0) goto L_0x005e
            closeFileDescriptor(r6)
        L_0x005e:
            throw r0
        L_0x005f:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r0 = "fileDescriptor cannot be null"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.<init>(java.io.FileDescriptor):void");
    }

    public ExifInterface(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ExifInterface(InputStream inputStream, int i) throws IOException {
        this(inputStream, i != 1 ? false : true);
    }

    private ExifInterface(InputStream inputStream, boolean z) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mAttributesOffsets = new HashSet(EXIF_TAGS.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (inputStream != null) {
            this.mFilename = null;
            if (z) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
                if (!isExifDataOnly(bufferedInputStream)) {
                    Log.w(TAG, "Given data does not follow the structure of an Exif-only data.");
                    return;
                }
                this.mIsExifDataOnly = true;
                this.mAssetInputStream = null;
                this.mSeekableFileDescriptor = null;
                inputStream = bufferedInputStream;
            } else if (inputStream instanceof AssetManager.AssetInputStream) {
                this.mAssetInputStream = (AssetManager.AssetInputStream) inputStream;
                this.mSeekableFileDescriptor = null;
            } else {
                if (inputStream instanceof FileInputStream) {
                    FileInputStream fileInputStream = (FileInputStream) inputStream;
                    if (isSeekableFD(fileInputStream.getFD())) {
                        this.mAssetInputStream = null;
                        this.mSeekableFileDescriptor = fileInputStream.getFD();
                    }
                }
                this.mAssetInputStream = null;
                this.mSeekableFileDescriptor = null;
            }
            loadAttributes(inputStream);
            return;
        }
        throw new NullPointerException("inputStream cannot be null");
    }

    public static boolean isSupportedMimeType(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            char c = 65535;
            switch (lowerCase.hashCode()) {
                case -1875291391:
                    if (lowerCase.equals("image/x-fuji-raf")) {
                        c = 10;
                        break;
                    }
                    break;
                case -1635437028:
                    if (lowerCase.equals("image/x-samsung-srw")) {
                        c = 9;
                        break;
                    }
                    break;
                case -1594371159:
                    if (lowerCase.equals("image/x-sony-arw")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1487464693:
                    if (lowerCase.equals("image/heic")) {
                        c = 11;
                        break;
                    }
                    break;
                case -1487464690:
                    if (lowerCase.equals("image/heif")) {
                        c = 12;
                        break;
                    }
                    break;
                case -1487394660:
                    if (lowerCase.equals("image/jpeg")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1487018032:
                    if (lowerCase.equals("image/webp")) {
                        c = 14;
                        break;
                    }
                    break;
                case -1423313290:
                    if (lowerCase.equals("image/x-adobe-dng")) {
                        c = 1;
                        break;
                    }
                    break;
                case -985160897:
                    if (lowerCase.equals("image/x-panasonic-rw2")) {
                        c = 6;
                        break;
                    }
                    break;
                case -879258763:
                    if (lowerCase.equals("image/png")) {
                        c = ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN;
                        break;
                    }
                    break;
                case -332763809:
                    if (lowerCase.equals("image/x-pentax-pef")) {
                        c = 8;
                        break;
                    }
                    break;
                case 1378106698:
                    if (lowerCase.equals("image/x-olympus-orf")) {
                        c = 7;
                        break;
                    }
                    break;
                case 2099152104:
                    if (lowerCase.equals("image/x-nikon-nef")) {
                        c = 3;
                        break;
                    }
                    break;
                case 2099152524:
                    if (lowerCase.equals("image/x-nikon-nrw")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2111234748:
                    if (lowerCase.equals("image/x-canon-cr2")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    return true;
                default:
                    return false;
            }
        } else {
            throw new NullPointerException("mimeType shouldn't be null");
        }
    }

    private ExifAttribute getExifAttribute(String str) {
        if (str != null) {
            if (TAG_ISO_SPEED_RATINGS.equals(str)) {
                if (DEBUG) {
                    Log.d(TAG, "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
                }
                str = TAG_PHOTOGRAPHIC_SENSITIVITY;
            }
            for (int i = 0; i < EXIF_TAGS.length; i++) {
                ExifAttribute exifAttribute = this.mAttributes[i].get(str);
                if (exifAttribute != null) {
                    return exifAttribute;
                }
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public String getAttribute(String str) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute != null) {
                if (!sTagSetForCompatibility.contains(str)) {
                    return exifAttribute.getStringValue(this.mExifByteOrder);
                }
                if (!str.equals(TAG_GPS_TIMESTAMP)) {
                    try {
                        return Double.toString(exifAttribute.getDoubleValue(this.mExifByteOrder));
                    } catch (NumberFormatException unused) {
                    }
                } else if (exifAttribute.format == 5 || exifAttribute.format == 10) {
                    Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
                    if (rationalArr == null || rationalArr.length != 3) {
                        Log.w(TAG, "Invalid GPS Timestamp array. array=" + Arrays.toString(rationalArr));
                        return null;
                    }
                    return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf((int) (((float) rationalArr[0].numerator) / ((float) rationalArr[0].denominator))), Integer.valueOf((int) (((float) rationalArr[1].numerator) / ((float) rationalArr[1].denominator))), Integer.valueOf((int) (((float) rationalArr[2].numerator) / ((float) rationalArr[2].denominator)))});
                } else {
                    Log.w(TAG, "GPS Timestamp format is not rational. format=" + exifAttribute.format);
                    return null;
                }
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public int getAttributeInt(String str, int i) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute == null) {
                return i;
            }
            try {
                return exifAttribute.getIntValue(this.mExifByteOrder);
            } catch (NumberFormatException unused) {
                return i;
            }
        } else {
            throw new NullPointerException("tag shouldn't be null");
        }
    }

    public double getAttributeDouble(String str, double d) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute == null) {
                return d;
            }
            try {
                return exifAttribute.getDoubleValue(this.mExifByteOrder);
            } catch (NumberFormatException unused) {
                return d;
            }
        } else {
            throw new NullPointerException("tag shouldn't be null");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x031f, code lost:
        r4 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0270, code lost:
        r4 = r16;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setAttribute(java.lang.String r18, java.lang.String r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            if (r1 == 0) goto L_0x032b
            java.lang.String r3 = "ISOSpeedRatings"
            boolean r3 = r3.equals(r1)
            java.lang.String r4 = "ExifInterface"
            if (r3 == 0) goto L_0x001d
            boolean r1 = DEBUG
            if (r1 == 0) goto L_0x001b
            java.lang.String r1 = "setAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY."
            android.util.Log.d(r4, r1)
        L_0x001b:
            java.lang.String r1 = "PhotographicSensitivity"
        L_0x001d:
            r3 = 2
            r5 = 1
            if (r2 == 0) goto L_0x00ba
            java.util.HashSet<java.lang.String> r6 = sTagSetForCompatibility
            boolean r6 = r6.contains(r1)
            if (r6 == 0) goto L_0x00ba
            java.lang.String r6 = "GPSTimeStamp"
            boolean r6 = r1.equals(r6)
            java.lang.String r7 = " : "
            java.lang.String r8 = "Invalid value for "
            if (r6 == 0) goto L_0x0093
            java.util.regex.Pattern r6 = sGpsTimestampPattern
            java.util.regex.Matcher r6 = r6.matcher(r2)
            boolean r9 = r6.find()
            if (r9 != 0) goto L_0x005a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r8)
            r3.append(r1)
            r3.append(r7)
            r3.append(r2)
            java.lang.String r1 = r3.toString()
            android.util.Log.w(r4, r1)
            return
        L_0x005a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = r6.group(r5)
            int r7 = java.lang.Integer.parseInt(r7)
            r2.append(r7)
            java.lang.String r7 = "/1,"
            r2.append(r7)
            java.lang.String r8 = r6.group(r3)
            int r8 = java.lang.Integer.parseInt(r8)
            r2.append(r8)
            r2.append(r7)
            r7 = 3
            java.lang.String r6 = r6.group(r7)
            int r6 = java.lang.Integer.parseInt(r6)
            r2.append(r6)
            java.lang.String r6 = "/1"
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            goto L_0x00ba
        L_0x0093:
            double r9 = java.lang.Double.parseDouble(r19)     // Catch:{ NumberFormatException -> 0x00a1 }
            androidx.exifinterface.media.ExifInterface$Rational r6 = new androidx.exifinterface.media.ExifInterface$Rational     // Catch:{ NumberFormatException -> 0x00a1 }
            r6.<init>(r9)     // Catch:{ NumberFormatException -> 0x00a1 }
            java.lang.String r2 = r6.toString()     // Catch:{ NumberFormatException -> 0x00a1 }
            goto L_0x00ba
        L_0x00a1:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r8)
            r3.append(r1)
            r3.append(r7)
            r3.append(r2)
            java.lang.String r1 = r3.toString()
            android.util.Log.w(r4, r1)
            return
        L_0x00ba:
            r6 = 0
            r7 = 0
        L_0x00bc:
            androidx.exifinterface.media.ExifInterface$ExifTag[][] r8 = EXIF_TAGS
            int r8 = r8.length
            if (r7 >= r8) goto L_0x032a
            r8 = 4
            if (r7 != r8) goto L_0x00ca
            boolean r8 = r0.mHasThumbnail
            if (r8 != 0) goto L_0x00ca
            goto L_0x0322
        L_0x00ca:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifTag>[] r8 = sExifTagMapsForWriting
            r8 = r8[r7]
            java.lang.Object r8 = r8.get(r1)
            androidx.exifinterface.media.ExifInterface$ExifTag r8 = (androidx.exifinterface.media.ExifInterface.ExifTag) r8
            if (r8 == 0) goto L_0x0322
            if (r2 != 0) goto L_0x00e1
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r7]
            r8.remove(r1)
            goto L_0x0322
        L_0x00e1:
            android.util.Pair r9 = guessDataFormat(r2)
            int r10 = r8.primaryFormat
            java.lang.Object r11 = r9.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r12 = -1
            if (r10 == r11) goto L_0x01be
            int r10 = r8.primaryFormat
            java.lang.Object r11 = r9.second
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            if (r10 != r11) goto L_0x0100
            goto L_0x01be
        L_0x0100:
            int r10 = r8.secondaryFormat
            if (r10 == r12) goto L_0x0120
            int r10 = r8.secondaryFormat
            java.lang.Object r11 = r9.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            if (r10 == r11) goto L_0x011c
            int r10 = r8.secondaryFormat
            java.lang.Object r11 = r9.second
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            if (r10 != r11) goto L_0x0120
        L_0x011c:
            int r8 = r8.secondaryFormat
            goto L_0x01c0
        L_0x0120:
            int r10 = r8.primaryFormat
            if (r10 == r5) goto L_0x01bb
            int r10 = r8.primaryFormat
            r11 = 7
            if (r10 == r11) goto L_0x01bb
            int r10 = r8.primaryFormat
            if (r10 != r3) goto L_0x012f
            goto L_0x01bb
        L_0x012f:
            boolean r10 = DEBUG
            if (r10 == 0) goto L_0x0322
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Given tag ("
            r10.append(r11)
            r10.append(r1)
            java.lang.String r11 = ") value didn't match with one of expected formats: "
            r10.append(r11)
            java.lang.String[] r11 = IFD_FORMAT_NAMES
            int r13 = r8.primaryFormat
            r11 = r11[r13]
            r10.append(r11)
            int r11 = r8.secondaryFormat
            java.lang.String r13 = ""
            java.lang.String r14 = ", "
            if (r11 != r12) goto L_0x0158
            r8 = r13
            goto L_0x016d
        L_0x0158:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r14)
            java.lang.String[] r15 = IFD_FORMAT_NAMES
            int r8 = r8.secondaryFormat
            r8 = r15[r8]
            r11.append(r8)
            java.lang.String r8 = r11.toString()
        L_0x016d:
            r10.append(r8)
            java.lang.String r8 = " (guess: "
            r10.append(r8)
            java.lang.String[] r8 = IFD_FORMAT_NAMES
            java.lang.Object r11 = r9.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r8 = r8[r11]
            r10.append(r8)
            java.lang.Object r8 = r9.second
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r8 != r12) goto L_0x018f
            goto L_0x01aa
        L_0x018f:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r14)
            java.lang.String[] r11 = IFD_FORMAT_NAMES
            java.lang.Object r9 = r9.second
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r9 = r11[r9]
            r8.append(r9)
            java.lang.String r13 = r8.toString()
        L_0x01aa:
            r10.append(r13)
            java.lang.String r8 = ")"
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            android.util.Log.d(r4, r8)
            goto L_0x0322
        L_0x01bb:
            int r8 = r8.primaryFormat
            goto L_0x01c0
        L_0x01be:
            int r8 = r8.primaryFormat
        L_0x01c0:
            java.lang.String r9 = "/"
            java.lang.String r10 = ","
            switch(r8) {
                case 1: goto L_0x0311;
                case 2: goto L_0x0302;
                case 3: goto L_0x02da;
                case 4: goto L_0x02b2;
                case 5: goto L_0x0274;
                case 6: goto L_0x01c7;
                case 7: goto L_0x0302;
                case 8: goto L_0x01c7;
                case 9: goto L_0x024b;
                case 10: goto L_0x020b;
                case 11: goto L_0x01c7;
                case 12: goto L_0x01e6;
                default: goto L_0x01c7;
            }
        L_0x01c7:
            r16 = r4
            r15 = 1
            boolean r3 = DEBUG
            if (r3 == 0) goto L_0x031f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Data format isn't one of expected formats: "
            r3.append(r4)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r4 = r16
            android.util.Log.d(r4, r3)
            goto L_0x0323
        L_0x01e6:
            java.lang.String[] r8 = r2.split(r10, r12)
            int r9 = r8.length
            double[] r9 = new double[r9]
            r10 = 0
        L_0x01ee:
            int r11 = r8.length
            if (r10 >= r11) goto L_0x01fc
            r11 = r8[r10]
            double r11 = java.lang.Double.parseDouble(r11)
            r9[r10] = r11
            int r10 = r10 + 1
            goto L_0x01ee
        L_0x01fc:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r8 = r0.mAttributes
            r8 = r8[r7]
            java.nio.ByteOrder r10 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r9 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createDouble((double[]) r9, (java.nio.ByteOrder) r10)
            r8.put(r1, r9)
            goto L_0x0322
        L_0x020b:
            java.lang.String[] r8 = r2.split(r10, r12)
            int r10 = r8.length
            androidx.exifinterface.media.ExifInterface$Rational[] r10 = new androidx.exifinterface.media.ExifInterface.Rational[r10]
            r11 = 0
        L_0x0213:
            int r13 = r8.length
            if (r11 >= r13) goto L_0x023b
            r13 = r8[r11]
            java.lang.String[] r13 = r13.split(r9, r12)
            androidx.exifinterface.media.ExifInterface$Rational r14 = new androidx.exifinterface.media.ExifInterface$Rational
            r15 = r13[r6]
            r16 = r4
            double r3 = java.lang.Double.parseDouble(r15)
            long r3 = (long) r3
            r13 = r13[r5]
            double r5 = java.lang.Double.parseDouble(r13)
            long r5 = (long) r5
            r14.<init>(r3, r5)
            r10[r11] = r14
            int r11 = r11 + 1
            r4 = r16
            r3 = 2
            r5 = 1
            r6 = 0
            goto L_0x0213
        L_0x023b:
            r16 = r4
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r7]
            java.nio.ByteOrder r4 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createSRational((androidx.exifinterface.media.ExifInterface.Rational[]) r10, (java.nio.ByteOrder) r4)
            r3.put(r1, r4)
            goto L_0x0270
        L_0x024b:
            r16 = r4
            java.lang.String[] r3 = r2.split(r10, r12)
            int r4 = r3.length
            int[] r4 = new int[r4]
            r5 = 0
        L_0x0255:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x0263
            r6 = r3[r5]
            int r6 = java.lang.Integer.parseInt(r6)
            r4[r5] = r6
            int r5 = r5 + 1
            goto L_0x0255
        L_0x0263:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r7]
            java.nio.ByteOrder r5 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createSLong((int[]) r4, (java.nio.ByteOrder) r5)
            r3.put(r1, r4)
        L_0x0270:
            r4 = r16
            goto L_0x0322
        L_0x0274:
            r16 = r4
            java.lang.String[] r3 = r2.split(r10, r12)
            int r4 = r3.length
            androidx.exifinterface.media.ExifInterface$Rational[] r4 = new androidx.exifinterface.media.ExifInterface.Rational[r4]
            r5 = 0
        L_0x027e:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x02a2
            r6 = r3[r5]
            java.lang.String[] r6 = r6.split(r9, r12)
            androidx.exifinterface.media.ExifInterface$Rational r8 = new androidx.exifinterface.media.ExifInterface$Rational
            r11 = 0
            r10 = r6[r11]
            double r13 = java.lang.Double.parseDouble(r10)
            long r13 = (long) r13
            r15 = 1
            r6 = r6[r15]
            double r11 = java.lang.Double.parseDouble(r6)
            long r10 = (long) r11
            r8.<init>(r13, r10)
            r4[r5] = r8
            int r5 = r5 + 1
            r12 = -1
            goto L_0x027e
        L_0x02a2:
            r15 = 1
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r7]
            java.nio.ByteOrder r5 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createURational((androidx.exifinterface.media.ExifInterface.Rational[]) r4, (java.nio.ByteOrder) r5)
            r3.put(r1, r4)
            goto L_0x031f
        L_0x02b2:
            r16 = r4
            r3 = -1
            r15 = 1
            java.lang.String[] r3 = r2.split(r10, r3)
            int r4 = r3.length
            long[] r4 = new long[r4]
            r5 = 0
        L_0x02be:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x02cc
            r6 = r3[r5]
            long r8 = java.lang.Long.parseLong(r6)
            r4[r5] = r8
            int r5 = r5 + 1
            goto L_0x02be
        L_0x02cc:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r7]
            java.nio.ByteOrder r5 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long[]) r4, (java.nio.ByteOrder) r5)
            r3.put(r1, r4)
            goto L_0x031f
        L_0x02da:
            r16 = r4
            r3 = -1
            r15 = 1
            java.lang.String[] r3 = r2.split(r10, r3)
            int r4 = r3.length
            int[] r4 = new int[r4]
            r5 = 0
        L_0x02e6:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x02f4
            r6 = r3[r5]
            int r6 = java.lang.Integer.parseInt(r6)
            r4[r5] = r6
            int r5 = r5 + 1
            goto L_0x02e6
        L_0x02f4:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r7]
            java.nio.ByteOrder r5 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createUShort((int[]) r4, (java.nio.ByteOrder) r5)
            r3.put(r1, r4)
            goto L_0x031f
        L_0x0302:
            r16 = r4
            r15 = 1
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r7]
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createString(r2)
            r3.put(r1, r4)
            goto L_0x031f
        L_0x0311:
            r16 = r4
            r15 = 1
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r7]
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createByte(r2)
            r3.put(r1, r4)
        L_0x031f:
            r4 = r16
            goto L_0x0323
        L_0x0322:
            r15 = 1
        L_0x0323:
            int r7 = r7 + 1
            r3 = 2
            r5 = 1
            r6 = 0
            goto L_0x00bc
        L_0x032a:
            return
        L_0x032b:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "tag shouldn't be null"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.setAttribute(java.lang.String, java.lang.String):void");
    }

    public void resetOrientation() {
        setAttribute(TAG_ORIENTATION, Integer.toString(1));
    }

    public void rotate(int i) {
        if (i % 90 == 0) {
            int attributeInt = getAttributeInt(TAG_ORIENTATION, 1);
            int i2 = 0;
            if (ROTATION_ORDER.contains(Integer.valueOf(attributeInt))) {
                int indexOf = (ROTATION_ORDER.indexOf(Integer.valueOf(attributeInt)) + (i / 90)) % 4;
                if (indexOf < 0) {
                    i2 = 4;
                }
                i2 = ROTATION_ORDER.get(indexOf + i2).intValue();
            } else if (FLIPPED_ROTATION_ORDER.contains(Integer.valueOf(attributeInt))) {
                int indexOf2 = (FLIPPED_ROTATION_ORDER.indexOf(Integer.valueOf(attributeInt)) + (i / 90)) % 4;
                if (indexOf2 < 0) {
                    i2 = 4;
                }
                i2 = FLIPPED_ROTATION_ORDER.get(indexOf2 + i2).intValue();
            }
            setAttribute(TAG_ORIENTATION, Integer.toString(i2));
            return;
        }
        throw new IllegalArgumentException("degree should be a multiple of 90");
    }

    public void flipVertically() {
        int i = 1;
        switch (getAttributeInt(TAG_ORIENTATION, 1)) {
            case 1:
                i = 4;
                break;
            case 2:
                i = 3;
                break;
            case 3:
                i = 2;
                break;
            case 4:
                break;
            case 5:
                i = 8;
                break;
            case 6:
                i = 7;
                break;
            case 7:
                i = 6;
                break;
            case 8:
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        setAttribute(TAG_ORIENTATION, Integer.toString(i));
    }

    public void flipHorizontally() {
        int i = 1;
        switch (getAttributeInt(TAG_ORIENTATION, 1)) {
            case 1:
                i = 2;
                break;
            case 2:
                break;
            case 3:
                i = 4;
                break;
            case 4:
                i = 3;
                break;
            case 5:
                i = 6;
                break;
            case 6:
                i = 5;
                break;
            case 7:
                i = 8;
                break;
            case 8:
                i = 7;
                break;
            default:
                i = 0;
                break;
        }
        setAttribute(TAG_ORIENTATION, Integer.toString(i));
    }

    public boolean isFlipped() {
        int attributeInt = getAttributeInt(TAG_ORIENTATION, 1);
        return attributeInt == 2 || attributeInt == 7 || attributeInt == 4 || attributeInt == 5;
    }

    public int getRotationDegrees() {
        switch (getAttributeInt(TAG_ORIENTATION, 1)) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 8:
                return 270;
            case 6:
            case 7:
                return 90;
            default:
                return 0;
        }
    }

    private void removeAttribute(String str) {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            this.mAttributes[i].remove(str);
        }
    }

    private void loadAttributes(InputStream inputStream) {
        if (inputStream != null) {
            int i = 0;
            while (i < EXIF_TAGS.length) {
                try {
                    this.mAttributes[i] = new HashMap<>();
                    i++;
                } catch (IOException e) {
                    this.mIsSupportedFile = false;
                    if (DEBUG) {
                        Log.w(TAG, "Invalid image: ExifInterface got an unsupported image format file(ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e);
                    }
                    addDefaultValuesForCompatibility();
                    if (!DEBUG) {
                        return;
                    }
                } catch (Throwable th) {
                    addDefaultValuesForCompatibility();
                    if (DEBUG) {
                        printAttributes();
                    }
                    throw th;
                }
            }
            if (!this.mIsExifDataOnly) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
                this.mMimeType = getMimeType(bufferedInputStream);
                inputStream = bufferedInputStream;
            }
            ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(inputStream);
            if (!this.mIsExifDataOnly) {
                switch (this.mMimeType) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 11:
                        getRawAttributes(byteOrderedDataInputStream);
                        break;
                    case 4:
                        getJpegAttributes(byteOrderedDataInputStream, 0, 0);
                        break;
                    case 7:
                        getOrfAttributes(byteOrderedDataInputStream);
                        break;
                    case 9:
                        getRafAttributes(byteOrderedDataInputStream);
                        break;
                    case 10:
                        getRw2Attributes(byteOrderedDataInputStream);
                        break;
                    case 12:
                        getHeifAttributes(byteOrderedDataInputStream);
                        break;
                    case 13:
                        getPngAttributes(byteOrderedDataInputStream);
                        break;
                    case 14:
                        getWebpAttributes(byteOrderedDataInputStream);
                        break;
                }
            } else {
                getStandaloneAttributes(byteOrderedDataInputStream);
            }
            setThumbnailData(byteOrderedDataInputStream);
            this.mIsSupportedFile = true;
            addDefaultValuesForCompatibility();
            if (!DEBUG) {
                return;
            }
            printAttributes();
            return;
        }
        throw new NullPointerException("inputstream shouldn't be null");
    }

    private static boolean isSeekableFD(FileDescriptor fileDescriptor) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.lseek(fileDescriptor, 0, OsConstants.SEEK_CUR);
                return true;
            } catch (Exception unused) {
                if (DEBUG) {
                    Log.d(TAG, "The file descriptor for the given input is not seekable");
                }
            }
        }
        return false;
    }

    private void printAttributes() {
        for (int i = 0; i < this.mAttributes.length; i++) {
            Log.d(TAG, "The size of tag group[" + i + "]: " + this.mAttributes[i].size());
            for (Map.Entry next : this.mAttributes[i].entrySet()) {
                ExifAttribute exifAttribute = (ExifAttribute) next.getValue();
                Log.d(TAG, "tagName: " + ((String) next.getKey()) + ", tagType: " + exifAttribute.toString() + ", tagValue: '" + exifAttribute.getStringValue(this.mExifByteOrder) + "'");
            }
        }
    }

    /* JADX WARNING: type inference failed for: r6v10, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ba A[Catch:{ Exception -> 0x011b, all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c2 A[Catch:{ Exception -> 0x011b, all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e9 A[Catch:{ Exception -> 0x010e, all -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ed A[Catch:{ Exception -> 0x010e, all -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0127 A[Catch:{ all -> 0x014a }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0142 A[Catch:{ all -> 0x014a }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveAttributes() throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.isSupportedFormatForSavingAttributes()
            if (r0 == 0) goto L_0x016a
            java.io.FileDescriptor r0 = r8.mSeekableFileDescriptor
            if (r0 != 0) goto L_0x0017
            java.lang.String r0 = r8.mFilename
            if (r0 == 0) goto L_0x000f
            goto L_0x0017
        L_0x000f:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "ExifInterface does not support saving attributes for the current input."
            r0.<init>(r1)
            throw r0
        L_0x0017:
            r0 = 1
            r8.mModified = r0
            byte[] r0 = r8.getThumbnail()
            r8.mThumbnailBytes = r0
            java.lang.String r0 = r8.mFilename
            r1 = 0
            if (r0 == 0) goto L_0x002d
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r8.mFilename
            r0.<init>(r2)
            goto L_0x002e
        L_0x002d:
            r0 = r1
        L_0x002e:
            java.lang.String r2 = r8.mFilename     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r3 = 0
            r5 = 21
            if (r2 == 0) goto L_0x0071
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r6.<init>()     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.String r7 = r8.mFilename     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r6.append(r7)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.String r7 = ".tmp"
            r6.append(r7)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            boolean r6 = r0.renameTo(r2)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            if (r6 == 0) goto L_0x0056
            r6 = r1
            goto L_0x00aa
        L_0x0056:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r3.<init>()     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.String r4 = "Couldn't rename to "
            r3.append(r4)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r3.append(r2)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            throw r0     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
        L_0x0071:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            if (r2 < r5) goto L_0x00a8
            java.io.FileDescriptor r2 = r8.mSeekableFileDescriptor     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            if (r2 == 0) goto L_0x00a8
            java.lang.String r2 = "temp"
            java.lang.String r6 = "tmp"
            java.io.File r2 = java.io.File.createTempFile(r2, r6)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.io.FileDescriptor r6 = r8.mSeekableFileDescriptor     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            int r7 = android.system.OsConstants.SEEK_SET     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            android.system.Os.lseek(r6, r3, r7)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.io.FileDescriptor r7 = r8.mSeekableFileDescriptor     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0158, all -> 0x0155 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00a3, all -> 0x009e }
            r7.<init>(r2)     // Catch:{ Exception -> 0x00a3, all -> 0x009e }
            copy(r6, r7)     // Catch:{ Exception -> 0x009c, all -> 0x009a }
            goto L_0x00ab
        L_0x009a:
            r0 = move-exception
            goto L_0x00a0
        L_0x009c:
            r0 = move-exception
            goto L_0x00a5
        L_0x009e:
            r0 = move-exception
            r7 = r1
        L_0x00a0:
            r1 = r6
            goto L_0x0163
        L_0x00a3:
            r0 = move-exception
            r7 = r1
        L_0x00a5:
            r1 = r6
            goto L_0x015a
        L_0x00a8:
            r2 = r1
            r6 = r2
        L_0x00aa:
            r7 = r6
        L_0x00ab:
            closeQuietly(r6)
            closeQuietly(r7)
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.lang.String r7 = r8.mFilename     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            if (r7 == 0) goto L_0x00c2
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = r8.mFilename     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            goto L_0x00da
        L_0x00c2:
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            if (r7 < r5) goto L_0x00d9
            java.io.FileDescriptor r5 = r8.mSeekableFileDescriptor     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            if (r5 == 0) goto L_0x00d9
            java.io.FileDescriptor r5 = r8.mSeekableFileDescriptor     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            int r7 = android.system.OsConstants.SEEK_SET     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            android.system.Os.lseek(r5, r3, r7)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.io.FileDescriptor r4 = r8.mSeekableFileDescriptor     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            goto L_0x00da
        L_0x00d9:
            r3 = r1
        L_0x00da:
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x011b, all -> 0x0118 }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0114, all -> 0x0110 }
            r5.<init>(r3)     // Catch:{ Exception -> 0x0114, all -> 0x0110 }
            int r3 = r8.mMimeType     // Catch:{ Exception -> 0x010e, all -> 0x010c }
            r6 = 4
            if (r3 != r6) goto L_0x00ed
            r8.saveJpegAttributes(r4, r5)     // Catch:{ Exception -> 0x010e, all -> 0x010c }
            goto L_0x0100
        L_0x00ed:
            int r3 = r8.mMimeType     // Catch:{ Exception -> 0x010e, all -> 0x010c }
            r6 = 13
            if (r3 != r6) goto L_0x00f7
            r8.savePngAttributes(r4, r5)     // Catch:{ Exception -> 0x010e, all -> 0x010c }
            goto L_0x0100
        L_0x00f7:
            int r3 = r8.mMimeType     // Catch:{ Exception -> 0x010e, all -> 0x010c }
            r6 = 14
            if (r3 != r6) goto L_0x0100
            r8.saveWebpAttributes(r4, r5)     // Catch:{ Exception -> 0x010e, all -> 0x010c }
        L_0x0100:
            closeQuietly(r4)
            closeQuietly(r5)
            r2.delete()
            r8.mThumbnailBytes = r1
            return
        L_0x010c:
            r0 = move-exception
            goto L_0x0112
        L_0x010e:
            r3 = move-exception
            goto L_0x0116
        L_0x0110:
            r0 = move-exception
            r5 = r1
        L_0x0112:
            r1 = r4
            goto L_0x014b
        L_0x0114:
            r3 = move-exception
            r5 = r1
        L_0x0116:
            r1 = r4
            goto L_0x011d
        L_0x0118:
            r0 = move-exception
            r5 = r1
            goto L_0x014b
        L_0x011b:
            r3 = move-exception
            r5 = r1
        L_0x011d:
            java.lang.String r4 = r8.mFilename     // Catch:{ all -> 0x014a }
            if (r4 == 0) goto L_0x0142
            boolean r4 = r2.renameTo(r0)     // Catch:{ all -> 0x014a }
            if (r4 != 0) goto L_0x0142
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x014a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x014a }
            r4.<init>()     // Catch:{ all -> 0x014a }
            java.lang.String r6 = "Couldn't restore original file: "
            r4.append(r6)     // Catch:{ all -> 0x014a }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x014a }
            r4.append(r0)     // Catch:{ all -> 0x014a }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x014a }
            r3.<init>(r0)     // Catch:{ all -> 0x014a }
            throw r3     // Catch:{ all -> 0x014a }
        L_0x0142:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x014a }
            java.lang.String r4 = "Failed to save new file"
            r0.<init>(r4, r3)     // Catch:{ all -> 0x014a }
            throw r0     // Catch:{ all -> 0x014a }
        L_0x014a:
            r0 = move-exception
        L_0x014b:
            closeQuietly(r1)
            closeQuietly(r5)
            r2.delete()
            throw r0
        L_0x0155:
            r0 = move-exception
            r7 = r1
            goto L_0x0163
        L_0x0158:
            r0 = move-exception
            r7 = r1
        L_0x015a:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0162 }
            java.lang.String r3 = "Failed to copy original file to temp file"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0162 }
            throw r2     // Catch:{ all -> 0x0162 }
        L_0x0162:
            r0 = move-exception
        L_0x0163:
            closeQuietly(r1)
            closeQuietly(r7)
            throw r0
        L_0x016a:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "ExifInterface only supports saving attributes on JPEG, PNG, or WebP formats."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.saveAttributes():void");
    }

    public boolean hasThumbnail() {
        return this.mHasThumbnail;
    }

    public boolean hasAttribute(String str) {
        return getExifAttribute(str) != null;
    }

    public byte[] getThumbnail() {
        int i = this.mThumbnailCompression;
        if (i == 6 || i == 7) {
            return getThumbnailBytes();
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0068 A[SYNTHETIC, Splitter:B:41:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x009d A[Catch:{ Exception -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getThumbnailBytes() {
        /*
            r11 = this;
            java.lang.String r0 = "ExifInterface"
            boolean r1 = r11.mHasThumbnail
            r2 = 0
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            byte[] r1 = r11.mThumbnailBytes
            if (r1 == 0) goto L_0x000d
            return r1
        L_0x000d:
            android.content.res.AssetManager$AssetInputStream r1 = r11.mAssetInputStream     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            if (r1 == 0) goto L_0x0030
            android.content.res.AssetManager$AssetInputStream r1 = r11.mAssetInputStream     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            boolean r3 = r1.markSupported()     // Catch:{ Exception -> 0x002b, all -> 0x0027 }
            if (r3 == 0) goto L_0x001e
            r1.reset()     // Catch:{ Exception -> 0x002b, all -> 0x0027 }
        L_0x001c:
            r3 = r2
            goto L_0x0066
        L_0x001e:
            java.lang.String r3 = "Cannot read thumbnail from inputstream without mark/reset support"
            android.util.Log.d(r0, r3)     // Catch:{ Exception -> 0x002b, all -> 0x0027 }
            closeQuietly(r1)
            return r2
        L_0x0027:
            r0 = move-exception
            r3 = r2
            goto L_0x00b9
        L_0x002b:
            r3 = move-exception
            r4 = r3
            r3 = r2
            goto L_0x00aa
        L_0x0030:
            java.lang.String r1 = r11.mFilename     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            if (r1 == 0) goto L_0x003c
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            java.lang.String r3 = r11.mFilename     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            goto L_0x001c
        L_0x003c:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            r3 = 21
            if (r1 < r3) goto L_0x0064
            java.io.FileDescriptor r1 = r11.mSeekableFileDescriptor     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            if (r1 == 0) goto L_0x0064
            java.io.FileDescriptor r1 = r11.mSeekableFileDescriptor     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            java.io.FileDescriptor r1 = android.system.Os.dup(r1)     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            r3 = 0
            int r5 = android.system.OsConstants.SEEK_SET     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            android.system.Os.lseek(r1, r3, r5)     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            r3.<init>(r1)     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            r10 = r3
            r3 = r1
            r1 = r10
            goto L_0x0066
        L_0x005c:
            r0 = move-exception
            r3 = r1
            goto L_0x00ba
        L_0x005f:
            r3 = move-exception
            r4 = r3
            r3 = r1
            r1 = r2
            goto L_0x00aa
        L_0x0064:
            r1 = r2
            r3 = r1
        L_0x0066:
            if (r1 == 0) goto L_0x009d
            int r4 = r11.mThumbnailOffset     // Catch:{ Exception -> 0x009b }
            long r4 = (long) r4     // Catch:{ Exception -> 0x009b }
            long r4 = r1.skip(r4)     // Catch:{ Exception -> 0x009b }
            int r6 = r11.mThumbnailOffset     // Catch:{ Exception -> 0x009b }
            long r6 = (long) r6
            java.lang.String r8 = "Corrupted image"
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L_0x0095
            int r4 = r11.mThumbnailLength     // Catch:{ Exception -> 0x009b }
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x009b }
            int r5 = r1.read(r4)     // Catch:{ Exception -> 0x009b }
            int r6 = r11.mThumbnailLength     // Catch:{ Exception -> 0x009b }
            if (r5 != r6) goto L_0x008f
            r11.mThumbnailBytes = r4     // Catch:{ Exception -> 0x009b }
            closeQuietly(r1)
            if (r3 == 0) goto L_0x008e
            closeFileDescriptor(r3)
        L_0x008e:
            return r4
        L_0x008f:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ Exception -> 0x009b }
            r4.<init>(r8)     // Catch:{ Exception -> 0x009b }
            throw r4     // Catch:{ Exception -> 0x009b }
        L_0x0095:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ Exception -> 0x009b }
            r4.<init>(r8)     // Catch:{ Exception -> 0x009b }
            throw r4     // Catch:{ Exception -> 0x009b }
        L_0x009b:
            r4 = move-exception
            goto L_0x00aa
        L_0x009d:
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException     // Catch:{ Exception -> 0x009b }
            r4.<init>()     // Catch:{ Exception -> 0x009b }
            throw r4     // Catch:{ Exception -> 0x009b }
        L_0x00a3:
            r0 = move-exception
            r3 = r2
            goto L_0x00ba
        L_0x00a6:
            r3 = move-exception
            r1 = r2
            r4 = r3
            r3 = r1
        L_0x00aa:
            java.lang.String r5 = "Encountered exception while getting thumbnail"
            android.util.Log.d(r0, r5, r4)     // Catch:{ all -> 0x00b8 }
            closeQuietly(r1)
            if (r3 == 0) goto L_0x00b7
            closeFileDescriptor(r3)
        L_0x00b7:
            return r2
        L_0x00b8:
            r0 = move-exception
        L_0x00b9:
            r2 = r1
        L_0x00ba:
            closeQuietly(r2)
            if (r3 == 0) goto L_0x00c2
            closeFileDescriptor(r3)
        L_0x00c2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getThumbnailBytes():byte[]");
    }

    public Bitmap getThumbnailBitmap() {
        if (!this.mHasThumbnail) {
            return null;
        }
        if (this.mThumbnailBytes == null) {
            this.mThumbnailBytes = getThumbnailBytes();
        }
        int i = this.mThumbnailCompression;
        if (i == 6 || i == 7) {
            return BitmapFactory.decodeByteArray(this.mThumbnailBytes, 0, this.mThumbnailLength);
        }
        if (i == 1) {
            int length = this.mThumbnailBytes.length / 3;
            int[] iArr = new int[length];
            for (int i2 = 0; i2 < length; i2++) {
                byte[] bArr = this.mThumbnailBytes;
                int i3 = i2 * 3;
                iArr[i2] = (bArr[i3] << Ascii.DLE) + 0 + (bArr[i3 + 1] << 8) + bArr[i3 + 2];
            }
            ExifAttribute exifAttribute = this.mAttributes[4].get(TAG_IMAGE_LENGTH);
            ExifAttribute exifAttribute2 = this.mAttributes[4].get(TAG_IMAGE_WIDTH);
            if (!(exifAttribute == null || exifAttribute2 == null)) {
                return Bitmap.createBitmap(iArr, exifAttribute2.getIntValue(this.mExifByteOrder), exifAttribute.getIntValue(this.mExifByteOrder), Bitmap.Config.ARGB_8888);
            }
        }
        return null;
    }

    public boolean isThumbnailCompressed() {
        if (!this.mHasThumbnail) {
            return false;
        }
        int i = this.mThumbnailCompression;
        if (i == 6 || i == 7) {
            return true;
        }
        return false;
    }

    public long[] getThumbnailRange() {
        if (this.mModified) {
            throw new IllegalStateException("The underlying file has been modified since being parsed");
        } else if (!this.mHasThumbnail) {
            return null;
        } else {
            if (this.mHasThumbnailStrips && !this.mAreThumbnailStripsConsecutive) {
                return null;
            }
            return new long[]{(long) this.mThumbnailOffset, (long) this.mThumbnailLength};
        }
    }

    public long[] getAttributeRange(String str) {
        if (str == null) {
            throw new NullPointerException("tag shouldn't be null");
        } else if (!this.mModified) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute == null) {
                return null;
            }
            return new long[]{exifAttribute.bytesOffset, (long) exifAttribute.bytes.length};
        } else {
            throw new IllegalStateException("The underlying file has been modified since being parsed");
        }
    }

    public byte[] getAttributeBytes(String str) {
        if (str != null) {
            ExifAttribute exifAttribute = getExifAttribute(str);
            if (exifAttribute != null) {
                return exifAttribute.bytes;
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    @Deprecated
    public boolean getLatLong(float[] fArr) {
        double[] latLong = getLatLong();
        if (latLong == null) {
            return false;
        }
        fArr[0] = (float) latLong[0];
        fArr[1] = (float) latLong[1];
        return true;
    }

    public double[] getLatLong() {
        String attribute = getAttribute(TAG_GPS_LATITUDE);
        String attribute2 = getAttribute(TAG_GPS_LATITUDE_REF);
        String attribute3 = getAttribute(TAG_GPS_LONGITUDE);
        String attribute4 = getAttribute(TAG_GPS_LONGITUDE_REF);
        if (attribute == null || attribute2 == null || attribute3 == null || attribute4 == null) {
            return null;
        }
        try {
            return new double[]{convertRationalLatLonToDouble(attribute, attribute2), convertRationalLatLonToDouble(attribute3, attribute4)};
        } catch (IllegalArgumentException unused) {
            Log.w(TAG, "Latitude/longitude values are not parsable. " + String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", new Object[]{attribute, attribute2, attribute3, attribute4}));
            return null;
        }
    }

    public void setGpsInfo(Location location) {
        if (location != null) {
            setAttribute(TAG_GPS_PROCESSING_METHOD, location.getProvider());
            setLatLong(location.getLatitude(), location.getLongitude());
            setAltitude(location.getAltitude());
            setAttribute(TAG_GPS_SPEED_REF, "K");
            setAttribute(TAG_GPS_SPEED, new Rational((double) ((location.getSpeed() * ((float) TimeUnit.HOURS.toSeconds(1))) / 1000.0f)).toString());
            String[] split = sFormatter.format(new Date(location.getTime())).split("\\s+", -1);
            setAttribute(TAG_GPS_DATESTAMP, split[0]);
            setAttribute(TAG_GPS_TIMESTAMP, split[1]);
        }
    }

    public void setLatLong(double d, double d2) {
        if (d < -90.0d || d > 90.0d || Double.isNaN(d)) {
            throw new IllegalArgumentException("Latitude value " + d + " is not valid.");
        } else if (d2 < -180.0d || d2 > 180.0d || Double.isNaN(d2)) {
            throw new IllegalArgumentException("Longitude value " + d2 + " is not valid.");
        } else {
            setAttribute(TAG_GPS_LATITUDE_REF, d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? "N" : LATITUDE_SOUTH);
            setAttribute(TAG_GPS_LATITUDE, convertDecimalDegree(Math.abs(d)));
            setAttribute(TAG_GPS_LONGITUDE_REF, d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? LONGITUDE_EAST : LONGITUDE_WEST);
            setAttribute(TAG_GPS_LONGITUDE, convertDecimalDegree(Math.abs(d2)));
        }
    }

    public double getAltitude(double d) {
        double attributeDouble = getAttributeDouble(TAG_GPS_ALTITUDE, -1.0d);
        int i = -1;
        int attributeInt = getAttributeInt(TAG_GPS_ALTITUDE_REF, -1);
        if (attributeDouble < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || attributeInt < 0) {
            return d;
        }
        if (attributeInt != 1) {
            i = 1;
        }
        return attributeDouble * ((double) i);
    }

    public void setAltitude(double d) {
        String str = d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        setAttribute(TAG_GPS_ALTITUDE, new Rational(Math.abs(d)).toString());
        setAttribute(TAG_GPS_ALTITUDE_REF, str);
    }

    public void setDateTime(long j) {
        setAttribute(TAG_DATETIME, sFormatter.format(new Date(j)));
        setAttribute(TAG_SUBSEC_TIME, Long.toString(j % 1000));
    }

    public long getDateTime() {
        return parseDateTime(getAttribute(TAG_DATETIME), getAttribute(TAG_SUBSEC_TIME));
    }

    public long getDateTimeDigitized() {
        return parseDateTime(getAttribute(TAG_DATETIME_DIGITIZED), getAttribute(TAG_SUBSEC_TIME_DIGITIZED));
    }

    public long getDateTimeOriginal() {
        return parseDateTime(getAttribute(TAG_DATETIME_ORIGINAL), getAttribute(TAG_SUBSEC_TIME_ORIGINAL));
    }

    private static long parseDateTime(String str, String str2) {
        if (str != null && sNonZeroTimePattern.matcher(str).matches()) {
            try {
                Date parse = sFormatter.parse(str, new ParsePosition(0));
                if (parse == null) {
                    return -1;
                }
                long time = parse.getTime();
                if (str2 == null) {
                    return time;
                }
                try {
                    long parseLong = Long.parseLong(str2);
                    while (parseLong > 1000) {
                        parseLong /= 10;
                    }
                    return time + parseLong;
                } catch (NumberFormatException unused) {
                    return time;
                }
            } catch (IllegalArgumentException unused2) {
            }
        }
        return -1;
    }

    public long getGpsDateTime() {
        String attribute = getAttribute(TAG_GPS_DATESTAMP);
        String attribute2 = getAttribute(TAG_GPS_TIMESTAMP);
        if (!(attribute == null || attribute2 == null || (!sNonZeroTimePattern.matcher(attribute).matches() && !sNonZeroTimePattern.matcher(attribute2).matches()))) {
            try {
                Date parse = sFormatter.parse(attribute + ' ' + attribute2, new ParsePosition(0));
                if (parse == null) {
                    return -1;
                }
                return parse.getTime();
            } catch (IllegalArgumentException unused) {
            }
        }
        return -1;
    }

    private void initForFilename(String str) throws IOException {
        if (str != null) {
            FileInputStream fileInputStream = null;
            this.mAssetInputStream = null;
            this.mFilename = str;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    if (isSeekableFD(fileInputStream2.getFD())) {
                        this.mSeekableFileDescriptor = fileInputStream2.getFD();
                    } else {
                        this.mSeekableFileDescriptor = null;
                    }
                    loadAttributes(fileInputStream2);
                    closeQuietly(fileInputStream2);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    closeQuietly(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                closeQuietly(fileInputStream);
                throw th;
            }
        } else {
            throw new NullPointerException("filename cannot be null");
        }
    }

    private static double convertRationalLatLonToDouble(String str, String str2) {
        try {
            String[] split = str.split(",", -1);
            String[] split2 = split[0].split("/", -1);
            String[] split3 = split[1].split("/", -1);
            String[] split4 = split[2].split("/", -1);
            double parseDouble = (Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim())) + ((Double.parseDouble(split3[0].trim()) / Double.parseDouble(split3[1].trim())) / 60.0d) + ((Double.parseDouble(split4[0].trim()) / Double.parseDouble(split4[1].trim())) / 3600.0d);
            if (!str2.equals(LATITUDE_SOUTH)) {
                if (!str2.equals(LONGITUDE_WEST)) {
                    if (!str2.equals("N")) {
                        if (!str2.equals(LONGITUDE_EAST)) {
                            throw new IllegalArgumentException();
                        }
                    }
                    return parseDouble;
                }
            }
            return -parseDouble;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
            throw new IllegalArgumentException();
        }
    }

    private String convertDecimalDegree(double d) {
        long j = (long) d;
        double d2 = d - ((double) j);
        long j2 = (long) (d2 * 60.0d);
        long round = Math.round((d2 - (((double) j2) / 60.0d)) * 3600.0d * 1.0E7d);
        return j + "/1," + j2 + "/1," + round + "/10000000";
    }

    private int getMimeType(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(5000);
        byte[] bArr = new byte[5000];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (isJpegFormat(bArr)) {
            return 4;
        }
        if (isRafFormat(bArr)) {
            return 9;
        }
        if (isHeifFormat(bArr)) {
            return 12;
        }
        if (isOrfFormat(bArr)) {
            return 7;
        }
        if (isRw2Format(bArr)) {
            return 10;
        }
        if (isPngFormat(bArr)) {
            return 13;
        }
        return isWebpFormat(bArr) ? 14 : 0;
    }

    private static boolean isJpegFormat(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = JPEG_SIGNATURE;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    private boolean isRafFormat(byte[] bArr) throws IOException {
        byte[] bytes = RAF_SIGNATURE.getBytes(Charset.defaultCharset());
        for (int i = 0; i < bytes.length; i++) {
            if (bArr[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0092 A[Catch:{ all -> 0x008b }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isHeifFormat(byte[] r15) throws java.io.IOException {
        /*
            r14 = this;
            r0 = 0
            r1 = 0
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r2 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ Exception -> 0x008d }
            r2.<init>((byte[]) r15)     // Catch:{ Exception -> 0x008d }
            int r1 = r2.readInt()     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            long r3 = (long) r1     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            r1 = 4
            byte[] r5 = new byte[r1]     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            r2.read(r5)     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            byte[] r6 = HEIF_TYPE_FTYP     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            boolean r5 = java.util.Arrays.equals(r5, r6)     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            if (r5 != 0) goto L_0x001e
            r2.close()
            return r0
        L_0x001e:
            r5 = 16
            r7 = 8
            r9 = 1
            int r11 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0034
            long r3 = r2.readLong()     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            int r11 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r11 >= 0) goto L_0x0035
            r2.close()
            return r0
        L_0x0034:
            r5 = r7
        L_0x0035:
            int r11 = r15.length     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            long r11 = (long) r11     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            int r13 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x003d
            int r15 = r15.length     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            long r3 = (long) r15
        L_0x003d:
            long r3 = r3 - r5
            int r15 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r15 >= 0) goto L_0x0046
            r2.close()
            return r0
        L_0x0046:
            byte[] r15 = new byte[r1]     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            r5 = 0
            r7 = 0
            r8 = 0
        L_0x004c:
            r11 = 4
            long r11 = r3 / r11
            int r13 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r13 >= 0) goto L_0x0081
            int r11 = r2.read(r15)     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            if (r11 == r1) goto L_0x005e
            r2.close()
            return r0
        L_0x005e:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0063
            goto L_0x007f
        L_0x0063:
            byte[] r11 = HEIF_BRAND_MIF1     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            boolean r11 = java.util.Arrays.equals(r15, r11)     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            r12 = 1
            if (r11 == 0) goto L_0x006e
            r7 = 1
            goto L_0x0077
        L_0x006e:
            byte[] r11 = HEIF_BRAND_HEIC     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            boolean r11 = java.util.Arrays.equals(r15, r11)     // Catch:{ Exception -> 0x0088, all -> 0x0085 }
            if (r11 == 0) goto L_0x0077
            r8 = 1
        L_0x0077:
            if (r7 == 0) goto L_0x007f
            if (r8 == 0) goto L_0x007f
            r2.close()
            return r12
        L_0x007f:
            long r5 = r5 + r9
            goto L_0x004c
        L_0x0081:
            r2.close()
            goto L_0x009e
        L_0x0085:
            r15 = move-exception
            r1 = r2
            goto L_0x009f
        L_0x0088:
            r15 = move-exception
            r1 = r2
            goto L_0x008e
        L_0x008b:
            r15 = move-exception
            goto L_0x009f
        L_0x008d:
            r15 = move-exception
        L_0x008e:
            boolean r2 = DEBUG     // Catch:{ all -> 0x008b }
            if (r2 == 0) goto L_0x0099
            java.lang.String r2 = "ExifInterface"
            java.lang.String r3 = "Exception parsing HEIF file type box."
            android.util.Log.d(r2, r3, r15)     // Catch:{ all -> 0x008b }
        L_0x0099:
            if (r1 == 0) goto L_0x009e
            r1.close()
        L_0x009e:
            return r0
        L_0x009f:
            if (r1 == 0) goto L_0x00a4
            r1.close()
        L_0x00a4:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.isHeifFormat(byte[]):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isOrfFormat(byte[] r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r2 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            r2.<init>((byte[]) r4)     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            java.nio.ByteOrder r4 = r3.readByteOrder(r2)     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r3.mExifByteOrder = r4     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r2.setByteOrder(r4)     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            short r4 = r2.readShort()     // Catch:{ Exception -> 0x0024, all -> 0x0021 }
            r1 = 20306(0x4f52, float:2.8455E-41)
            if (r4 == r1) goto L_0x001c
            r1 = 21330(0x5352, float:2.989E-41)
            if (r4 != r1) goto L_0x001d
        L_0x001c:
            r0 = 1
        L_0x001d:
            r2.close()
            return r0
        L_0x0021:
            r4 = move-exception
            r1 = r2
            goto L_0x0027
        L_0x0024:
            r1 = r2
            goto L_0x002e
        L_0x0026:
            r4 = move-exception
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()
        L_0x002c:
            throw r4
        L_0x002d:
        L_0x002e:
            if (r1 == 0) goto L_0x0033
            r1.close()
        L_0x0033:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.isOrfFormat(byte[]):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isRw2Format(byte[] r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream r2 = new androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ Exception -> 0x0029, all -> 0x0022 }
            r2.<init>((byte[]) r4)     // Catch:{ Exception -> 0x0029, all -> 0x0022 }
            java.nio.ByteOrder r4 = r3.readByteOrder(r2)     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            r3.mExifByteOrder = r4     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            r2.setByteOrder(r4)     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            short r4 = r2.readShort()     // Catch:{ Exception -> 0x0020, all -> 0x001d }
            r1 = 85
            if (r4 != r1) goto L_0x0019
            r0 = 1
        L_0x0019:
            r2.close()
            return r0
        L_0x001d:
            r4 = move-exception
            r1 = r2
            goto L_0x0023
        L_0x0020:
            r1 = r2
            goto L_0x002a
        L_0x0022:
            r4 = move-exception
        L_0x0023:
            if (r1 == 0) goto L_0x0028
            r1.close()
        L_0x0028:
            throw r4
        L_0x0029:
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.isRw2Format(byte[]):boolean");
    }

    private boolean isPngFormat(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = PNG_SIGNATURE;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    private boolean isWebpFormat(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = WEBP_SIGNATURE_1;
            if (i >= bArr2.length) {
                int i2 = 0;
                while (true) {
                    byte[] bArr3 = WEBP_SIGNATURE_2;
                    if (i2 >= bArr3.length) {
                        return true;
                    }
                    if (bArr[WEBP_SIGNATURE_1.length + i2 + 4] != bArr3[i2]) {
                        return false;
                    }
                    i2++;
                }
            } else if (bArr[i] != bArr2[i]) {
                return false;
            } else {
                i++;
            }
        }
    }

    private static boolean isExifDataOnly(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(IDENTIFIER_EXIF_APP1.length);
        byte[] bArr = new byte[IDENTIFIER_EXIF_APP1.length];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        int i = 0;
        while (true) {
            byte[] bArr2 = IDENTIFIER_EXIF_APP1;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00f5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0193 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getJpegAttributes(androidx.exifinterface.media.ExifInterface.ByteOrderedDataInputStream r20, int r21, int r22) throws java.io.IOException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            boolean r4 = DEBUG
            java.lang.String r5 = "ExifInterface"
            if (r4 == 0) goto L_0x0022
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "getJpegAttributes starting with: "
            r4.append(r6)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r5, r4)
        L_0x0022:
            java.nio.ByteOrder r4 = java.nio.ByteOrder.BIG_ENDIAN
            r1.setByteOrder(r4)
            long r6 = (long) r2
            r1.seek(r6)
            byte r4 = r20.readByte()
            java.lang.String r6 = "Invalid marker: "
            r7 = -1
            if (r4 != r7) goto L_0x01dd
            r8 = 1
            int r2 = r2 + r8
            byte r9 = r20.readByte()
            r10 = -40
            if (r9 != r10) goto L_0x01c2
            int r2 = r2 + r8
        L_0x003f:
            byte r4 = r20.readByte()
            if (r4 != r7) goto L_0x01a5
            int r2 = r2 + r8
            byte r4 = r20.readByte()
            boolean r6 = DEBUG
            if (r6 == 0) goto L_0x0068
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "Found JPEG segment indicator: "
            r6.append(r9)
            r9 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r9 = java.lang.Integer.toHexString(r9)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r5, r6)
        L_0x0068:
            int r2 = r2 + r8
            r6 = -39
            if (r4 == r6) goto L_0x019f
            r6 = -38
            if (r4 != r6) goto L_0x0073
            goto L_0x019f
        L_0x0073:
            int r6 = r20.readUnsignedShort()
            int r6 = r6 + -2
            int r2 = r2 + 2
            boolean r9 = DEBUG
            if (r9 == 0) goto L_0x00a8
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "JPEG segment: "
            r9.append(r10)
            r10 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r10 = java.lang.Integer.toHexString(r10)
            r9.append(r10)
            java.lang.String r10 = " (length: "
            r9.append(r10)
            int r10 = r6 + 2
            r9.append(r10)
            java.lang.String r10 = ")"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.d(r5, r9)
        L_0x00a8:
            java.lang.String r9 = "Invalid length"
            if (r6 < 0) goto L_0x0199
            r10 = -31
            r11 = 0
            if (r4 == r10) goto L_0x012a
            r10 = -2
            if (r4 == r10) goto L_0x00fd
            switch(r4) {
                case -64: goto L_0x00c4;
                case -63: goto L_0x00c4;
                case -62: goto L_0x00c4;
                case -61: goto L_0x00c4;
                default: goto L_0x00b7;
            }
        L_0x00b7:
            switch(r4) {
                case -59: goto L_0x00c4;
                case -58: goto L_0x00c4;
                case -57: goto L_0x00c4;
                default: goto L_0x00ba;
            }
        L_0x00ba:
            switch(r4) {
                case -55: goto L_0x00c4;
                case -54: goto L_0x00c4;
                case -53: goto L_0x00c4;
                default: goto L_0x00bd;
            }
        L_0x00bd:
            switch(r4) {
                case -51: goto L_0x00c4;
                case -50: goto L_0x00c4;
                case -49: goto L_0x00c4;
                default: goto L_0x00c0;
            }
        L_0x00c0:
            r10 = r2
            r2 = 1
            goto L_0x017c
        L_0x00c4:
            int r4 = r1.skipBytes(r8)
            if (r4 != r8) goto L_0x00f5
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r3]
            int r10 = r20.readUnsignedShort()
            long r10 = (long) r10
            java.nio.ByteOrder r12 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r10 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r10, (java.nio.ByteOrder) r12)
            java.lang.String r11 = "ImageLength"
            r4.put(r11, r10)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r3]
            int r10 = r20.readUnsignedShort()
            long r10 = (long) r10
            java.nio.ByteOrder r12 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r10 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r10, (java.nio.ByteOrder) r12)
            java.lang.String r11 = "ImageWidth"
            r4.put(r11, r10)
            int r6 = r6 + -5
            goto L_0x00c0
        L_0x00f5:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Invalid SOFx"
            r1.<init>(r2)
            throw r1
        L_0x00fd:
            byte[] r4 = new byte[r6]
            int r10 = r1.read(r4)
            if (r10 != r6) goto L_0x0122
            java.lang.String r6 = "UserComment"
            java.lang.String r10 = r0.getAttribute(r6)
            if (r10 != 0) goto L_0x011f
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r10 = r0.mAttributes
            r10 = r10[r8]
            java.lang.String r12 = new java.lang.String
            java.nio.charset.Charset r13 = ASCII
            r12.<init>(r4, r13)
            androidx.exifinterface.media.ExifInterface$ExifAttribute r4 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createString(r12)
            r10.put(r6, r4)
        L_0x011f:
            r10 = r2
        L_0x0120:
            r2 = 1
            goto L_0x017b
        L_0x0122:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Invalid exif"
            r1.<init>(r2)
            throw r1
        L_0x012a:
            byte[] r4 = new byte[r6]
            r1.readFully(r4)
            int r10 = r2 + r6
            byte[] r12 = IDENTIFIER_EXIF_APP1
            boolean r12 = startsWith(r4, r12)
            if (r12 == 0) goto L_0x0148
            byte[] r12 = IDENTIFIER_EXIF_APP1
            int r13 = r12.length
            int r2 = r2 + r13
            int r12 = r12.length
            byte[] r4 = java.util.Arrays.copyOfRange(r4, r12, r6)
            r0.mExifOffset = r2
            r0.readExifSegment(r4, r3)
            goto L_0x0120
        L_0x0148:
            byte[] r12 = IDENTIFIER_XMP_APP1
            boolean r12 = startsWith(r4, r12)
            if (r12 == 0) goto L_0x0120
            byte[] r12 = IDENTIFIER_XMP_APP1
            int r13 = r12.length
            int r2 = r2 + r13
            int r12 = r12.length
            byte[] r4 = java.util.Arrays.copyOfRange(r4, r12, r6)
            java.lang.String r6 = "Xmp"
            java.lang.String r12 = r0.getAttribute(r6)
            if (r12 != 0) goto L_0x0120
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r12 = r0.mAttributes
            r12 = r12[r11]
            androidx.exifinterface.media.ExifInterface$ExifAttribute r15 = new androidx.exifinterface.media.ExifInterface$ExifAttribute
            r14 = 1
            int r13 = r4.length
            long r7 = (long) r2
            r2 = r13
            r13 = r15
            r11 = r15
            r15 = r2
            r16 = r7
            r18 = r4
            r13.<init>(r14, r15, r16, r18)
            r12.put(r6, r11)
            r2 = 1
            r0.mXmpIsFromSeparateMarker = r2
        L_0x017b:
            r6 = 0
        L_0x017c:
            if (r6 < 0) goto L_0x0193
            int r4 = r1.skipBytes(r6)
            if (r4 != r6) goto L_0x018b
            int r4 = r10 + r6
            r2 = r4
            r7 = -1
            r8 = 1
            goto L_0x003f
        L_0x018b:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Invalid JPEG segment"
            r1.<init>(r2)
            throw r1
        L_0x0193:
            java.io.IOException r1 = new java.io.IOException
            r1.<init>(r9)
            throw r1
        L_0x0199:
            java.io.IOException r1 = new java.io.IOException
            r1.<init>(r9)
            throw r1
        L_0x019f:
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            r1.setByteOrder(r2)
            return
        L_0x01a5:
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid marker:"
            r2.append(r3)
            r3 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x01c2:
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            r3 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x01dd:
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            r3 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getJpegAttributes(androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream, int, int):void");
    }

    private void getRawAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        ExifAttribute exifAttribute;
        parseTiffHeaders(byteOrderedDataInputStream, byteOrderedDataInputStream.available());
        readImageFileDirectory(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 5);
        updateImageSizeValues(byteOrderedDataInputStream, 4);
        validateImages();
        if (this.mMimeType == 8 && (exifAttribute = this.mAttributes[1].get(TAG_MAKER_NOTE)) != null) {
            ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
            byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
            byteOrderedDataInputStream2.seek(6);
            readImageFileDirectory(byteOrderedDataInputStream2, 9);
            ExifAttribute exifAttribute2 = this.mAttributes[9].get(TAG_COLOR_SPACE);
            if (exifAttribute2 != null) {
                this.mAttributes[1].put(TAG_COLOR_SPACE, exifAttribute2);
            }
        }
    }

    private void getRafAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        byteOrderedDataInputStream.skipBytes(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byteOrderedDataInputStream.read(bArr);
        byteOrderedDataInputStream.skipBytes(4);
        byteOrderedDataInputStream.read(bArr2);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        getJpegAttributes(byteOrderedDataInputStream, i, 5);
        byteOrderedDataInputStream.seek((long) i2);
        byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        int readInt = byteOrderedDataInputStream.readInt();
        if (DEBUG) {
            Log.d(TAG, "numberOfDirectoryEntry: " + readInt);
        }
        for (int i3 = 0; i3 < readInt; i3++) {
            int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
            int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
            if (readUnsignedShort == TAG_RAF_IMAGE_SIZE.number) {
                short readShort = byteOrderedDataInputStream.readShort();
                short readShort2 = byteOrderedDataInputStream.readShort();
                ExifAttribute createUShort = ExifAttribute.createUShort((int) readShort, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort((int) readShort2, this.mExifByteOrder);
                this.mAttributes[0].put(TAG_IMAGE_LENGTH, createUShort);
                this.mAttributes[0].put(TAG_IMAGE_WIDTH, createUShort2);
                if (DEBUG) {
                    Log.d(TAG, "Updated to length: " + readShort + ", width: " + readShort2);
                    return;
                }
                return;
            }
            byteOrderedDataInputStream.skipBytes(readUnsignedShort2);
        }
    }

    private void getHeifAttributes(final ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        String str;
        String str2;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                mediaMetadataRetriever.setDataSource(new MediaDataSource() {
                    long mPosition;

                    public void close() throws IOException {
                    }

                    public long getSize() throws IOException {
                        return -1;
                    }

                    public int readAt(long j, byte[] bArr, int i, int i2) throws IOException {
                        if (i2 == 0) {
                            return 0;
                        }
                        if (j < 0) {
                            return -1;
                        }
                        try {
                            if (this.mPosition != j) {
                                if (this.mPosition >= 0 && j >= this.mPosition + ((long) byteOrderedDataInputStream.available())) {
                                    return -1;
                                }
                                byteOrderedDataInputStream.seek(j);
                                this.mPosition = j;
                            }
                            if (i2 > byteOrderedDataInputStream.available()) {
                                i2 = byteOrderedDataInputStream.available();
                            }
                            int read = byteOrderedDataInputStream.read(bArr, i, i2);
                            if (read >= 0) {
                                this.mPosition += (long) read;
                                return read;
                            }
                        } catch (IOException unused) {
                        }
                        this.mPosition = -1;
                        return -1;
                    }
                });
            } else if (this.mSeekableFileDescriptor != null) {
                mediaMetadataRetriever.setDataSource(this.mSeekableFileDescriptor);
            } else if (this.mFilename != null) {
                mediaMetadataRetriever.setDataSource(this.mFilename);
            } else {
                mediaMetadataRetriever.release();
                return;
            }
            String extractMetadata = mediaMetadataRetriever.extractMetadata(33);
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(34);
            String extractMetadata3 = mediaMetadataRetriever.extractMetadata(26);
            String extractMetadata4 = mediaMetadataRetriever.extractMetadata(17);
            String str3 = null;
            if ("yes".equals(extractMetadata3)) {
                str3 = mediaMetadataRetriever.extractMetadata(29);
                str2 = mediaMetadataRetriever.extractMetadata(30);
                str = mediaMetadataRetriever.extractMetadata(31);
            } else if ("yes".equals(extractMetadata4)) {
                str3 = mediaMetadataRetriever.extractMetadata(18);
                str2 = mediaMetadataRetriever.extractMetadata(19);
                str = mediaMetadataRetriever.extractMetadata(24);
            } else {
                str2 = null;
                str = null;
            }
            if (str3 != null) {
                this.mAttributes[0].put(TAG_IMAGE_WIDTH, ExifAttribute.createUShort(Integer.parseInt(str3), this.mExifByteOrder));
            }
            if (str2 != null) {
                this.mAttributes[0].put(TAG_IMAGE_LENGTH, ExifAttribute.createUShort(Integer.parseInt(str2), this.mExifByteOrder));
            }
            if (str != null) {
                int i = 1;
                int parseInt = Integer.parseInt(str);
                if (parseInt == 90) {
                    i = 6;
                } else if (parseInt == 180) {
                    i = 3;
                } else if (parseInt == 270) {
                    i = 8;
                }
                this.mAttributes[0].put(TAG_ORIENTATION, ExifAttribute.createUShort(i, this.mExifByteOrder));
            }
            if (!(extractMetadata == null || extractMetadata2 == null)) {
                int parseInt2 = Integer.parseInt(extractMetadata);
                int parseInt3 = Integer.parseInt(extractMetadata2);
                if (parseInt3 > 6) {
                    byteOrderedDataInputStream.seek((long) parseInt2);
                    byte[] bArr = new byte[6];
                    if (byteOrderedDataInputStream.read(bArr) == 6) {
                        int i2 = parseInt2 + 6;
                        int i3 = parseInt3 - 6;
                        if (Arrays.equals(bArr, IDENTIFIER_EXIF_APP1)) {
                            byte[] bArr2 = new byte[i3];
                            if (byteOrderedDataInputStream.read(bArr2) == i3) {
                                this.mExifOffset = i2;
                                readExifSegment(bArr2, 0);
                            } else {
                                throw new IOException("Can't read exif");
                            }
                        } else {
                            throw new IOException("Invalid identifier");
                        }
                    } else {
                        throw new IOException("Can't read identifier");
                    }
                } else {
                    throw new IOException("Invalid exif length");
                }
            }
            if (DEBUG) {
                Log.d(TAG, "Heif meta: " + str3 + "x" + str2 + ", rotation " + str);
            }
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    private void getStandaloneAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        byteOrderedDataInputStream.skipBytes(IDENTIFIER_EXIF_APP1.length);
        byte[] bArr = new byte[byteOrderedDataInputStream.available()];
        byteOrderedDataInputStream.readFully(bArr);
        this.mExifOffset = IDENTIFIER_EXIF_APP1.length;
        readExifSegment(bArr, 0);
    }

    private void getOrfAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        getRawAttributes(byteOrderedDataInputStream);
        ExifAttribute exifAttribute = this.mAttributes[1].get(TAG_MAKER_NOTE);
        if (exifAttribute != null) {
            ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
            byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
            byte[] bArr = new byte[ORF_MAKER_NOTE_HEADER_1.length];
            byteOrderedDataInputStream2.readFully(bArr);
            byteOrderedDataInputStream2.seek(0);
            byte[] bArr2 = new byte[ORF_MAKER_NOTE_HEADER_2.length];
            byteOrderedDataInputStream2.readFully(bArr2);
            if (Arrays.equals(bArr, ORF_MAKER_NOTE_HEADER_1)) {
                byteOrderedDataInputStream2.seek(8);
            } else if (Arrays.equals(bArr2, ORF_MAKER_NOTE_HEADER_2)) {
                byteOrderedDataInputStream2.seek(12);
            }
            readImageFileDirectory(byteOrderedDataInputStream2, 6);
            ExifAttribute exifAttribute2 = this.mAttributes[7].get(TAG_ORF_PREVIEW_IMAGE_START);
            ExifAttribute exifAttribute3 = this.mAttributes[7].get(TAG_ORF_PREVIEW_IMAGE_LENGTH);
            if (!(exifAttribute2 == null || exifAttribute3 == null)) {
                this.mAttributes[5].put(TAG_JPEG_INTERCHANGE_FORMAT, exifAttribute2);
                this.mAttributes[5].put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, exifAttribute3);
            }
            ExifAttribute exifAttribute4 = this.mAttributes[8].get(TAG_ORF_ASPECT_FRAME);
            if (exifAttribute4 != null) {
                int[] iArr = (int[]) exifAttribute4.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 4) {
                    Log.w(TAG, "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                } else if (iArr[2] > iArr[0] && iArr[3] > iArr[1]) {
                    int i = (iArr[2] - iArr[0]) + 1;
                    int i2 = (iArr[3] - iArr[1]) + 1;
                    if (i < i2) {
                        int i3 = i + i2;
                        i2 = i3 - i2;
                        i = i3 - i2;
                    }
                    ExifAttribute createUShort = ExifAttribute.createUShort(i, this.mExifByteOrder);
                    ExifAttribute createUShort2 = ExifAttribute.createUShort(i2, this.mExifByteOrder);
                    this.mAttributes[0].put(TAG_IMAGE_WIDTH, createUShort);
                    this.mAttributes[0].put(TAG_IMAGE_LENGTH, createUShort2);
                }
            }
        }
    }

    private void getRw2Attributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        getRawAttributes(byteOrderedDataInputStream);
        if (this.mAttributes[0].get(TAG_RW2_JPG_FROM_RAW) != null) {
            getJpegAttributes(byteOrderedDataInputStream, this.mRw2JpgFromRawOffset, 5);
        }
        ExifAttribute exifAttribute = this.mAttributes[0].get(TAG_RW2_ISO);
        ExifAttribute exifAttribute2 = this.mAttributes[1].get(TAG_PHOTOGRAPHIC_SENSITIVITY);
        if (exifAttribute != null && exifAttribute2 == null) {
            this.mAttributes[1].put(TAG_PHOTOGRAPHIC_SENSITIVITY, exifAttribute);
        }
    }

    private void getPngAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "getPngAttributes starting with: " + byteOrderedDataInputStream);
        }
        byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        byteOrderedDataInputStream.skipBytes(PNG_SIGNATURE.length);
        int length = PNG_SIGNATURE.length + 0;
        while (true) {
            try {
                int readInt = byteOrderedDataInputStream.readInt();
                int i = length + 4;
                byte[] bArr = new byte[4];
                if (byteOrderedDataInputStream.read(bArr) == 4) {
                    int i2 = i + 4;
                    if (i2 == 16) {
                        if (!Arrays.equals(bArr, PNG_CHUNK_TYPE_IHDR)) {
                            throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                        }
                    }
                    if (!Arrays.equals(bArr, PNG_CHUNK_TYPE_IEND)) {
                        if (Arrays.equals(bArr, PNG_CHUNK_TYPE_EXIF)) {
                            byte[] bArr2 = new byte[readInt];
                            if (byteOrderedDataInputStream.read(bArr2) == readInt) {
                                int readInt2 = byteOrderedDataInputStream.readInt();
                                CRC32 crc32 = new CRC32();
                                crc32.update(bArr);
                                crc32.update(bArr2);
                                if (((int) crc32.getValue()) == readInt2) {
                                    this.mExifOffset = i2;
                                    readExifSegment(bArr2, 0);
                                    validateImages();
                                    return;
                                }
                                throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + readInt2 + ", calculated CRC value: " + crc32.getValue());
                            }
                            throw new IOException("Failed to read given length for given PNG chunk type: " + byteArrayToHexString(bArr));
                        }
                        int i3 = readInt + 4;
                        byteOrderedDataInputStream.skipBytes(i3);
                        length = i2 + i3;
                    } else {
                        return;
                    }
                } else {
                    throw new IOException("Encountered invalid length while parsing PNG chunktype");
                }
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    private void getWebpAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "getWebpAttributes starting with: " + byteOrderedDataInputStream);
        }
        byteOrderedDataInputStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        byteOrderedDataInputStream.skipBytes(WEBP_SIGNATURE_1.length);
        int readInt = byteOrderedDataInputStream.readInt() + 8;
        int skipBytes = byteOrderedDataInputStream.skipBytes(WEBP_SIGNATURE_2.length) + 8;
        while (true) {
            try {
                byte[] bArr = new byte[4];
                if (byteOrderedDataInputStream.read(bArr) == 4) {
                    int readInt2 = byteOrderedDataInputStream.readInt();
                    int i = skipBytes + 4 + 4;
                    if (Arrays.equals(WEBP_CHUNK_TYPE_EXIF, bArr)) {
                        byte[] bArr2 = new byte[readInt2];
                        if (byteOrderedDataInputStream.read(bArr2) == readInt2) {
                            this.mExifOffset = i;
                            readExifSegment(bArr2, 0);
                            this.mExifOffset = i;
                            return;
                        }
                        throw new IOException("Failed to read given length for given PNG chunk type: " + byteArrayToHexString(bArr));
                    }
                    if (readInt2 % 2 == 1) {
                        readInt2++;
                    }
                    int i2 = i + readInt2;
                    if (i2 != readInt) {
                        if (i2 <= readInt) {
                            int skipBytes2 = byteOrderedDataInputStream.skipBytes(readInt2);
                            if (skipBytes2 == readInt2) {
                                skipBytes = i + skipBytes2;
                            } else {
                                throw new IOException("Encountered WebP file with invalid chunk size");
                            }
                        } else {
                            throw new IOException("Encountered WebP file with invalid chunk size");
                        }
                    } else {
                        return;
                    }
                } else {
                    throw new IOException("Encountered invalid length while parsing WebP chunktype");
                }
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    private void saveJpegAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "saveJpegAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        if (dataInputStream.readByte() == -1) {
            byteOrderedDataOutputStream.writeByte(-1);
            if (dataInputStream.readByte() == -40) {
                byteOrderedDataOutputStream.writeByte(-40);
                ExifAttribute exifAttribute = null;
                if (getAttribute(TAG_XMP) != null && this.mXmpIsFromSeparateMarker) {
                    exifAttribute = this.mAttributes[0].remove(TAG_XMP);
                }
                byteOrderedDataOutputStream.writeByte(-1);
                byteOrderedDataOutputStream.writeByte(-31);
                writeExifSegment(byteOrderedDataOutputStream);
                if (exifAttribute != null) {
                    this.mAttributes[0].put(TAG_XMP, exifAttribute);
                }
                byte[] bArr = new byte[4096];
                while (dataInputStream.readByte() == -1) {
                    byte readByte = dataInputStream.readByte();
                    if (readByte == -39 || readByte == -38) {
                        byteOrderedDataOutputStream.writeByte(-1);
                        byteOrderedDataOutputStream.writeByte(readByte);
                        copy(dataInputStream, byteOrderedDataOutputStream);
                        return;
                    } else if (readByte != -31) {
                        byteOrderedDataOutputStream.writeByte(-1);
                        byteOrderedDataOutputStream.writeByte(readByte);
                        int readUnsignedShort = dataInputStream.readUnsignedShort();
                        byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort);
                        int i = readUnsignedShort - 2;
                        if (i >= 0) {
                            while (i > 0) {
                                int read = dataInputStream.read(bArr, 0, Math.min(i, 4096));
                                if (read < 0) {
                                    break;
                                }
                                byteOrderedDataOutputStream.write(bArr, 0, read);
                                i -= read;
                            }
                        } else {
                            throw new IOException("Invalid length");
                        }
                    } else {
                        int readUnsignedShort2 = dataInputStream.readUnsignedShort() - 2;
                        if (readUnsignedShort2 >= 0) {
                            byte[] bArr2 = new byte[6];
                            if (readUnsignedShort2 >= 6) {
                                if (dataInputStream.read(bArr2) != 6) {
                                    throw new IOException("Invalid exif");
                                } else if (Arrays.equals(bArr2, IDENTIFIER_EXIF_APP1)) {
                                    int i2 = readUnsignedShort2 - 6;
                                    if (dataInputStream.skipBytes(i2) != i2) {
                                        throw new IOException("Invalid length");
                                    }
                                }
                            }
                            byteOrderedDataOutputStream.writeByte(-1);
                            byteOrderedDataOutputStream.writeByte(readByte);
                            byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort2 + 2);
                            if (readUnsignedShort2 >= 6) {
                                readUnsignedShort2 -= 6;
                                byteOrderedDataOutputStream.write(bArr2);
                            }
                            while (readUnsignedShort2 > 0) {
                                int read2 = dataInputStream.read(bArr, 0, Math.min(readUnsignedShort2, 4096));
                                if (read2 < 0) {
                                    break;
                                }
                                byteOrderedDataOutputStream.write(bArr, 0, read2);
                                readUnsignedShort2 -= read2;
                            }
                        } else {
                            throw new IOException("Invalid length");
                        }
                    }
                }
                throw new IOException("Invalid marker");
            }
            throw new IOException("Invalid marker");
        }
        throw new IOException("Invalid marker");
    }

    private void savePngAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "savePngAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        copy(dataInputStream, byteOrderedDataOutputStream, PNG_SIGNATURE.length);
        int i = this.mExifOffset;
        if (i == 0) {
            int readInt = dataInputStream.readInt();
            byteOrderedDataOutputStream.writeInt(readInt);
            copy(dataInputStream, byteOrderedDataOutputStream, readInt + 4 + 4);
        } else {
            copy(dataInputStream, byteOrderedDataOutputStream, ((i - PNG_SIGNATURE.length) - 4) - 4);
            dataInputStream.skipBytes(dataInputStream.readInt() + 4 + 4);
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                ByteOrderedDataOutputStream byteOrderedDataOutputStream2 = new ByteOrderedDataOutputStream(byteArrayOutputStream2, ByteOrder.BIG_ENDIAN);
                writeExifSegment(byteOrderedDataOutputStream2);
                byte[] byteArray = ((ByteArrayOutputStream) byteOrderedDataOutputStream2.mOutputStream).toByteArray();
                byteOrderedDataOutputStream.write(byteArray);
                CRC32 crc32 = new CRC32();
                crc32.update(byteArray, 4, byteArray.length - 4);
                byteOrderedDataOutputStream.writeInt((int) crc32.getValue());
                closeQuietly(byteArrayOutputStream2);
                copy(dataInputStream, byteOrderedDataOutputStream);
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream2;
                closeQuietly(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(byteArrayOutputStream);
            throw th;
        }
    }

    private void saveWebpAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "saveWebpAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(inputStream, ByteOrder.LITTLE_ENDIAN);
        ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.LITTLE_ENDIAN);
        copy(byteOrderedDataInputStream, byteOrderedDataOutputStream, WEBP_SIGNATURE_1.length);
        byteOrderedDataInputStream.skipBytes(WEBP_SIGNATURE_2.length + 4);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                ByteOrderedDataOutputStream byteOrderedDataOutputStream2 = new ByteOrderedDataOutputStream(byteArrayOutputStream2, ByteOrder.LITTLE_ENDIAN);
                if (this.mExifOffset != 0) {
                    copy(byteOrderedDataInputStream, byteOrderedDataOutputStream2, ((this.mExifOffset - ((WEBP_SIGNATURE_1.length + 4) + WEBP_SIGNATURE_2.length)) - 4) - 4);
                    byteOrderedDataInputStream.skipBytes(4);
                    byteOrderedDataInputStream.skipBytes(byteOrderedDataInputStream.readInt());
                    writeExifSegment(byteOrderedDataOutputStream2);
                } else {
                    byte[] bArr = new byte[4];
                    if (byteOrderedDataInputStream.read(bArr) != 4) {
                        throw new IOException("Encountered invalid length while parsing WebP chunk type");
                    } else if (Arrays.equals(bArr, WEBP_CHUNK_TYPE_VP8X)) {
                        int readInt = byteOrderedDataInputStream.readInt();
                        boolean z = true;
                        byte[] bArr2 = new byte[(readInt % 2 == 1 ? readInt + 1 : readInt)];
                        byteOrderedDataInputStream.read(bArr2);
                        bArr2[0] = (byte) (bArr2[0] | 8);
                        if (((bArr2[0] >> 1) & 1) != 1) {
                            z = false;
                        }
                        byteOrderedDataOutputStream2.write(WEBP_CHUNK_TYPE_VP8X);
                        byteOrderedDataOutputStream2.writeInt(readInt);
                        byteOrderedDataOutputStream2.write(bArr2);
                        if (z) {
                            copyChunksUpToGivenChunkType(byteOrderedDataInputStream, byteOrderedDataOutputStream2, WEBP_CHUNK_TYPE_ANIM, (byte[]) null);
                            while (true) {
                                byte[] bArr3 = new byte[4];
                                inputStream.read(bArr3);
                                if (!Arrays.equals(bArr3, WEBP_CHUNK_TYPE_ANMF)) {
                                    break;
                                }
                                copyWebPChunk(byteOrderedDataInputStream, byteOrderedDataOutputStream2, bArr3);
                            }
                            writeExifSegment(byteOrderedDataOutputStream2);
                        } else {
                            copyChunksUpToGivenChunkType(byteOrderedDataInputStream, byteOrderedDataOutputStream2, WEBP_CHUNK_TYPE_VP8, WEBP_CHUNK_TYPE_VP8L);
                            writeExifSegment(byteOrderedDataOutputStream2);
                        }
                    } else if (Arrays.equals(bArr, WEBP_CHUNK_TYPE_VP8) || Arrays.equals(bArr, WEBP_CHUNK_TYPE_VP8L)) {
                        throw new IOException("WebP files with only VP8 or VP8L chunks are currently not supported");
                    }
                }
                copy(byteOrderedDataInputStream, byteOrderedDataOutputStream2);
                byteOrderedDataOutputStream.writeInt(byteArrayOutputStream2.size() + WEBP_SIGNATURE_2.length);
                byteOrderedDataOutputStream.write(WEBP_SIGNATURE_2);
                byteArrayOutputStream2.writeTo(byteOrderedDataOutputStream);
                closeQuietly(byteArrayOutputStream2);
            } catch (Exception e) {
                e = e;
                byteArrayOutputStream = byteArrayOutputStream2;
                try {
                    throw new IOException("Failed to save WebP file", e);
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = byteArrayOutputStream2;
                closeQuietly(byteArrayOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            throw new IOException("Failed to save WebP file", e);
        }
    }

    private void copyChunksUpToGivenChunkType(ByteOrderedDataInputStream byteOrderedDataInputStream, ByteOrderedDataOutputStream byteOrderedDataOutputStream, byte[] bArr, byte[] bArr2) throws IOException {
        String str;
        while (true) {
            byte[] bArr3 = new byte[4];
            if (byteOrderedDataInputStream.read(bArr3) != 4) {
                StringBuilder sb = new StringBuilder();
                sb.append("Encountered invalid length while copying WebP chunks up tochunk type ");
                sb.append(new String(bArr, ASCII));
                if (bArr2 == null) {
                    str = "";
                } else {
                    str = " or " + new String(bArr2, ASCII);
                }
                sb.append(str);
                throw new IOException(sb.toString());
            }
            copyWebPChunk(byteOrderedDataInputStream, byteOrderedDataOutputStream, bArr3);
            if (Arrays.equals(bArr3, bArr)) {
                return;
            }
            if (bArr2 != null && Arrays.equals(bArr3, bArr2)) {
                return;
            }
        }
    }

    private void copyWebPChunk(ByteOrderedDataInputStream byteOrderedDataInputStream, ByteOrderedDataOutputStream byteOrderedDataOutputStream, byte[] bArr) throws IOException {
        int readInt = byteOrderedDataInputStream.readInt();
        byteOrderedDataOutputStream.write(bArr);
        byteOrderedDataOutputStream.writeInt(readInt);
        if (readInt % 2 == 1) {
            readInt++;
        }
        copy(byteOrderedDataInputStream, byteOrderedDataOutputStream, readInt);
    }

    private void readExifSegment(byte[] bArr, int i) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        parseTiffHeaders(byteOrderedDataInputStream, bArr.length);
        readImageFileDirectory(byteOrderedDataInputStream, i);
    }

    private void addDefaultValuesForCompatibility() {
        String attribute = getAttribute(TAG_DATETIME_ORIGINAL);
        if (attribute != null && getAttribute(TAG_DATETIME) == null) {
            this.mAttributes[0].put(TAG_DATETIME, ExifAttribute.createString(attribute));
        }
        if (getAttribute(TAG_IMAGE_WIDTH) == null) {
            this.mAttributes[0].put(TAG_IMAGE_WIDTH, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute(TAG_IMAGE_LENGTH) == null) {
            this.mAttributes[0].put(TAG_IMAGE_LENGTH, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute(TAG_ORIENTATION) == null) {
            this.mAttributes[0].put(TAG_ORIENTATION, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute(TAG_LIGHT_SOURCE) == null) {
            this.mAttributes[1].put(TAG_LIGHT_SOURCE, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        short readShort = byteOrderedDataInputStream.readShort();
        if (readShort == 18761) {
            if (DEBUG) {
                Log.d(TAG, "readExifSegment: Byte Align II");
            }
            return ByteOrder.LITTLE_ENDIAN;
        } else if (readShort == 19789) {
            if (DEBUG) {
                Log.d(TAG, "readExifSegment: Byte Align MM");
            }
            return ByteOrder.BIG_ENDIAN;
        } else {
            throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
        }
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        ByteOrder readByteOrder = readByteOrder(byteOrderedDataInputStream);
        this.mExifByteOrder = readByteOrder;
        byteOrderedDataInputStream.setByteOrder(readByteOrder);
        int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
        int i2 = this.mMimeType;
        if (i2 == 7 || i2 == 10 || readUnsignedShort == 42) {
            int readInt = byteOrderedDataInputStream.readInt();
            if (readInt < 8 || readInt >= i) {
                throw new IOException("Invalid first Ifd offset: " + readInt);
            }
            int i3 = readInt - 8;
            if (i3 > 0 && byteOrderedDataInputStream.skipBytes(i3) != i3) {
                throw new IOException("Couldn't jump to first Ifd: " + i3);
            }
            return;
        }
        throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02f8  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x015b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readImageFileDirectory(androidx.exifinterface.media.ExifInterface.ByteOrderedDataInputStream r28, int r29) throws java.io.IOException {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            r2 = r29
            java.util.Set<java.lang.Integer> r3 = r0.mAttributesOffsets
            int r4 = r1.mPosition
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.add(r4)
            int r3 = r1.mPosition
            r4 = 2
            int r3 = r3 + r4
            int r5 = r1.mLength
            if (r3 <= r5) goto L_0x001a
            return
        L_0x001a:
            short r3 = r28.readShort()
            boolean r5 = DEBUG
            java.lang.String r6 = "ExifInterface"
            if (r5 == 0) goto L_0x0038
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "numberOfDirectoryEntry: "
            r5.append(r7)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r6, r5)
        L_0x0038:
            int r5 = r1.mPosition
            int r7 = r3 * 12
            int r5 = r5 + r7
            int r7 = r1.mLength
            if (r5 > r7) goto L_0x0433
            if (r3 > 0) goto L_0x0045
            goto L_0x0433
        L_0x0045:
            r5 = 0
            r7 = 0
        L_0x0047:
            r8 = 5
            if (r7 >= r3) goto L_0x03a2
            int r13 = r28.readUnsignedShort()
            int r14 = r28.readUnsignedShort()
            int r15 = r28.readInt()
            int r9 = r28.peek()
            long r9 = (long) r9
            r18 = 4
            long r9 = r9 + r18
            java.util.HashMap<java.lang.Integer, androidx.exifinterface.media.ExifInterface$ExifTag>[] r20 = sExifTagMapsForReading
            r12 = r20[r2]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            java.lang.Object r4 = r12.get(r4)
            androidx.exifinterface.media.ExifInterface$ExifTag r4 = (androidx.exifinterface.media.ExifInterface.ExifTag) r4
            boolean r12 = DEBUG
            r11 = 3
            if (r12 == 0) goto L_0x00a3
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r29)
            r8[r5] = r12
            java.lang.Integer r12 = java.lang.Integer.valueOf(r13)
            r20 = 1
            r8[r20] = r12
            if (r4 == 0) goto L_0x0087
            java.lang.String r12 = r4.name
            goto L_0x0088
        L_0x0087:
            r12 = 0
        L_0x0088:
            r22 = 2
            r8[r22] = r12
            java.lang.Integer r12 = java.lang.Integer.valueOf(r14)
            r8[r11] = r12
            java.lang.Integer r12 = java.lang.Integer.valueOf(r15)
            r21 = 4
            r8[r21] = r12
            java.lang.String r12 = "ifdType: %d, tagNumber: %d, tagName: %s, dataFormat: %d, numberOfComponents: %d"
            java.lang.String r8 = java.lang.String.format(r12, r8)
            android.util.Log.d(r6, r8)
        L_0x00a3:
            r8 = 7
            if (r4 != 0) goto L_0x00c2
            boolean r12 = DEBUG
            if (r12 == 0) goto L_0x00be
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r5 = "Skip the tag entry since tag number is not defined: "
            r12.append(r5)
            r12.append(r13)
            java.lang.String r5 = r12.toString()
            android.util.Log.d(r6, r5)
        L_0x00be:
            r24 = r9
            goto L_0x0149
        L_0x00c2:
            if (r14 <= 0) goto L_0x012f
            int[] r5 = IFD_FORMAT_BYTES_PER_FORMAT
            int r5 = r5.length
            if (r14 < r5) goto L_0x00ca
            goto L_0x012f
        L_0x00ca:
            boolean r5 = r4.isFormatCompatible(r14)
            if (r5 != 0) goto L_0x00f7
            boolean r5 = DEBUG
            if (r5 == 0) goto L_0x00be
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r12 = "Skip the tag entry since data format ("
            r5.append(r12)
            java.lang.String[] r12 = IFD_FORMAT_NAMES
            r12 = r12[r14]
            r5.append(r12)
            java.lang.String r12 = ") is unexpected for tag: "
            r5.append(r12)
            java.lang.String r12 = r4.name
            r5.append(r12)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r6, r5)
            goto L_0x00be
        L_0x00f7:
            if (r14 != r8) goto L_0x00fb
            int r14 = r4.primaryFormat
        L_0x00fb:
            long r11 = (long) r15
            int[] r23 = IFD_FORMAT_BYTES_PER_FORMAT
            r5 = r23[r14]
            r24 = r9
            long r8 = (long) r5
            long r8 = r8 * r11
            r11 = 0
            int r5 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r5 < 0) goto L_0x0115
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r5 <= 0) goto L_0x0113
            goto L_0x0115
        L_0x0113:
            r5 = 1
            goto L_0x014c
        L_0x0115:
            boolean r5 = DEBUG
            if (r5 == 0) goto L_0x012d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r11 = "Skip the tag entry since the number of components is invalid: "
            r5.append(r11)
            r5.append(r15)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r6, r5)
        L_0x012d:
            r5 = 0
            goto L_0x014c
        L_0x012f:
            r24 = r9
            boolean r5 = DEBUG
            if (r5 == 0) goto L_0x0149
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "Skip the tag entry since data format is invalid: "
            r5.append(r8)
            r5.append(r14)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r6, r5)
        L_0x0149:
            r5 = 0
            r8 = 0
        L_0x014c:
            if (r5 != 0) goto L_0x015b
            r11 = r24
            r1.seek(r11)
            r23 = r3
            r13 = r6
            r24 = r7
        L_0x0158:
            r7 = 2
            goto L_0x0395
        L_0x015b:
            r11 = r24
            java.lang.String r5 = "Compression"
            int r23 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1))
            if (r23 <= 0) goto L_0x022a
            int r10 = r28.readInt()
            boolean r19 = DEBUG
            r23 = r3
            if (r19 == 0) goto L_0x0184
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r24 = r7
            java.lang.String r7 = "seek to data offset: "
            r3.append(r7)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r6, r3)
            goto L_0x0186
        L_0x0184:
            r24 = r7
        L_0x0186:
            int r3 = r0.mMimeType
            r7 = 7
            if (r3 != r7) goto L_0x01e6
            java.lang.String r3 = r4.name
            java.lang.String r7 = "MakerNote"
            boolean r3 = r7.equals(r3)
            if (r3 == 0) goto L_0x0198
            r0.mOrfMakerNoteOffset = r10
            goto L_0x01e1
        L_0x0198:
            r3 = 6
            if (r2 != r3) goto L_0x01e1
            java.lang.String r7 = r4.name
            java.lang.String r3 = "ThumbnailImage"
            boolean r3 = r3.equals(r7)
            if (r3 == 0) goto L_0x01e1
            r0.mOrfThumbnailOffset = r10
            r0.mOrfThumbnailLength = r15
            java.nio.ByteOrder r3 = r0.mExifByteOrder
            r7 = 6
            androidx.exifinterface.media.ExifInterface$ExifAttribute r3 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createUShort((int) r7, (java.nio.ByteOrder) r3)
            int r7 = r0.mOrfThumbnailOffset
            r19 = r14
            r18 = r15
            long r14 = (long) r7
            java.nio.ByteOrder r7 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r7 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r14, (java.nio.ByteOrder) r7)
            int r14 = r0.mOrfThumbnailLength
            long r14 = (long) r14
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            androidx.exifinterface.media.ExifInterface$ExifAttribute r2 = androidx.exifinterface.media.ExifInterface.ExifAttribute.createULong((long) r14, (java.nio.ByteOrder) r2)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r14 = r0.mAttributes
            r15 = 4
            r14 = r14[r15]
            r14.put(r5, r3)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r15]
            java.lang.String r14 = "JPEGInterchangeFormat"
            r3.put(r14, r7)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r3 = r0.mAttributes
            r3 = r3[r15]
            java.lang.String r7 = "JPEGInterchangeFormatLength"
            r3.put(r7, r2)
            goto L_0x01fa
        L_0x01e1:
            r19 = r14
            r18 = r15
            goto L_0x01fa
        L_0x01e6:
            r19 = r14
            r18 = r15
            r2 = 10
            if (r3 != r2) goto L_0x01fa
            java.lang.String r2 = r4.name
            java.lang.String r3 = "JpgFromRaw"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x01fa
            r0.mRw2JpgFromRawOffset = r10
        L_0x01fa:
            long r2 = (long) r10
            long r14 = r2 + r8
            int r7 = r1.mLength
            r25 = r4
            r26 = r5
            long r4 = (long) r7
            int r7 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x020c
            r1.seek(r2)
            goto L_0x0236
        L_0x020c:
            boolean r2 = DEBUG
            if (r2 == 0) goto L_0x0224
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Skip the tag entry since data offset is invalid: "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r6, r2)
        L_0x0224:
            r1.seek(r11)
            r13 = r6
            goto L_0x0158
        L_0x022a:
            r23 = r3
            r25 = r4
            r26 = r5
            r24 = r7
            r19 = r14
            r18 = r15
        L_0x0236:
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r2 = sExifPointerTagMap
            java.lang.Integer r3 = java.lang.Integer.valueOf(r13)
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            boolean r3 = DEBUG
            if (r3 == 0) goto L_0x0262
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "nextIfdType: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = " byteCount: "
            r3.append(r4)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r6, r3)
        L_0x0262:
            r3 = 8
            if (r2 == 0) goto L_0x0316
            r4 = -1
            r14 = r19
            r7 = 3
            if (r14 == r7) goto L_0x028a
            r7 = 4
            if (r14 == r7) goto L_0x0285
            if (r14 == r3) goto L_0x0280
            r3 = 9
            if (r14 == r3) goto L_0x027b
            r3 = 13
            if (r14 == r3) goto L_0x027b
            goto L_0x028f
        L_0x027b:
            int r3 = r28.readInt()
            goto L_0x028e
        L_0x0280:
            short r3 = r28.readShort()
            goto L_0x028e
        L_0x0285:
            long r4 = r28.readUnsignedInt()
            goto L_0x028f
        L_0x028a:
            int r3 = r28.readUnsignedShort()
        L_0x028e:
            long r4 = (long) r3
        L_0x028f:
            boolean r3 = DEBUG
            r7 = 2
            if (r3 == 0) goto L_0x02ad
            java.lang.Object[] r3 = new java.lang.Object[r7]
            java.lang.Long r8 = java.lang.Long.valueOf(r4)
            r9 = 0
            r3[r9] = r8
            r10 = r25
            java.lang.String r8 = r10.name
            r9 = 1
            r3[r9] = r8
            java.lang.String r8 = "Offset: %d, tagName: %s"
            java.lang.String r3 = java.lang.String.format(r8, r3)
            android.util.Log.d(r6, r3)
        L_0x02ad:
            r8 = 0
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 <= 0) goto L_0x02f8
            int r3 = r1.mLength
            long r8 = (long) r3
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x02f8
            java.util.Set<java.lang.Integer> r3 = r0.mAttributesOffsets
            int r8 = (int) r4
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            boolean r3 = r3.contains(r8)
            if (r3 != 0) goto L_0x02d2
            r1.seek(r4)
            int r2 = r2.intValue()
            r0.readImageFileDirectory(r1, r2)
            goto L_0x0310
        L_0x02d2:
            boolean r3 = DEBUG
            if (r3 == 0) goto L_0x0310
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r8 = "Skip jump into the IFD since it has already been read: IfdType "
            r3.append(r8)
            r3.append(r2)
            java.lang.String r2 = " (at "
            r3.append(r2)
            r3.append(r4)
            java.lang.String r2 = ")"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            android.util.Log.d(r6, r2)
            goto L_0x0310
        L_0x02f8:
            boolean r2 = DEBUG
            if (r2 == 0) goto L_0x0310
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Skip jump into the IFD since its offset is invalid: "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r6, r2)
        L_0x0310:
            r1.seek(r11)
            r13 = r6
            goto L_0x0395
        L_0x0316:
            r14 = r19
            r10 = r25
            r2 = r26
            r7 = 2
            int r4 = r28.peek()
            int r13 = r0.mExifOffset
            int r4 = r4 + r13
            int r9 = (int) r8
            byte[] r8 = new byte[r9]
            r1.readFully(r8)
            androidx.exifinterface.media.ExifInterface$ExifAttribute r9 = new androidx.exifinterface.media.ExifInterface$ExifAttribute
            r13 = r6
            long r5 = (long) r4
            r4 = r18
            r15 = r9
            r16 = r14
            r17 = r4
            r18 = r5
            r20 = r8
            r15.<init>(r16, r17, r18, r20)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r4 = r0.mAttributes
            r4 = r4[r29]
            java.lang.String r5 = r10.name
            r4.put(r5, r9)
            java.lang.String r4 = r10.name
            java.lang.String r5 = "DNGVersion"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0352
            r4 = 3
            r0.mMimeType = r4
        L_0x0352:
            java.lang.String r4 = r10.name
            java.lang.String r5 = "Make"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x0366
            java.lang.String r4 = r10.name
            java.lang.String r5 = "Model"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0374
        L_0x0366:
            java.nio.ByteOrder r4 = r0.mExifByteOrder
            java.lang.String r4 = r9.getStringValue(r4)
            java.lang.String r5 = "PENTAX"
            boolean r4 = r4.contains(r5)
            if (r4 != 0) goto L_0x0387
        L_0x0374:
            java.lang.String r4 = r10.name
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0389
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            int r2 = r9.getIntValue(r2)
            r4 = 65535(0xffff, float:9.1834E-41)
            if (r2 != r4) goto L_0x0389
        L_0x0387:
            r0.mMimeType = r3
        L_0x0389:
            int r2 = r28.peek()
            long r2 = (long) r2
            int r4 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x0395
            r1.seek(r11)
        L_0x0395:
            int r2 = r24 + 1
            short r2 = (short) r2
            r7 = r2
            r6 = r13
            r3 = r23
            r4 = 2
            r5 = 0
            r2 = r29
            goto L_0x0047
        L_0x03a2:
            r13 = r6
            int r2 = r28.peek()
            r3 = 4
            int r2 = r2 + r3
            int r3 = r1.mLength
            if (r2 > r3) goto L_0x0433
            int r2 = r28.readInt()
            boolean r3 = DEBUG
            if (r3 == 0) goto L_0x03ca
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            r5 = 0
            r3[r5] = r4
            java.lang.String r4 = "nextIfdOffset: %d"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r4 = r13
            android.util.Log.d(r4, r3)
            goto L_0x03cb
        L_0x03ca:
            r4 = r13
        L_0x03cb:
            long r5 = (long) r2
            r9 = 0
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x041b
            int r3 = r1.mLength
            if (r2 >= r3) goto L_0x041b
            java.util.Set<java.lang.Integer> r3 = r0.mAttributesOffsets
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)
            boolean r3 = r3.contains(r7)
            if (r3 != 0) goto L_0x0402
            r1.seek(r5)
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r3 = 4
            r2 = r2[r3]
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x03f4
            r0.readImageFileDirectory(r1, r3)
            goto L_0x0433
        L_0x03f4:
            java.util.HashMap<java.lang.String, androidx.exifinterface.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r8]
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0433
            r0.readImageFileDirectory(r1, r8)
            goto L_0x0433
        L_0x0402:
            boolean r1 = DEBUG
            if (r1 == 0) goto L_0x0433
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Stop reading file since re-reading an IFD may cause an infinite loop: "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r4, r1)
            goto L_0x0433
        L_0x041b:
            boolean r1 = DEBUG
            if (r1 == 0) goto L_0x0433
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Stop reading file since a wrong offset may cause an infinite loop: "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r4, r1)
        L_0x0433:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.readImageFileDirectory(androidx.exifinterface.media.ExifInterface$ByteOrderedDataInputStream, int):void");
    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        ExifAttribute exifAttribute;
        ExifAttribute exifAttribute2 = this.mAttributes[i].get(TAG_IMAGE_LENGTH);
        ExifAttribute exifAttribute3 = this.mAttributes[i].get(TAG_IMAGE_WIDTH);
        if ((exifAttribute2 == null || exifAttribute3 == null) && (exifAttribute = this.mAttributes[i].get(TAG_JPEG_INTERCHANGE_FORMAT)) != null) {
            getJpegAttributes(byteOrderedDataInputStream, exifAttribute.getIntValue(this.mExifByteOrder), i);
        }
    }

    private void setThumbnailData(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        HashMap<String, ExifAttribute> hashMap = this.mAttributes[4];
        ExifAttribute exifAttribute = hashMap.get(TAG_COMPRESSION);
        if (exifAttribute != null) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            this.mThumbnailCompression = intValue;
            if (intValue != 1) {
                if (intValue == 6) {
                    handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
                    return;
                } else if (intValue != 7) {
                    return;
                }
            }
            if (isSupportedDataType(hashMap)) {
                handleThumbnailFromStrips(byteOrderedDataInputStream, hashMap);
                return;
            }
            return;
        }
        this.mThumbnailCompression = 6;
        handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT);
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        if (exifAttribute != null && exifAttribute2 != null) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
            if (this.mMimeType == 7) {
                intValue += this.mOrfMakerNoteOffset;
            }
            int min = Math.min(intValue2, byteOrderedDataInputStream.getLength() - intValue);
            if (intValue > 0 && min > 0) {
                this.mHasThumbnail = true;
                int i = this.mExifOffset + intValue;
                this.mThumbnailOffset = i;
                this.mThumbnailLength = min;
                if (this.mFilename == null && this.mAssetInputStream == null && this.mSeekableFileDescriptor == null) {
                    byte[] bArr = new byte[min];
                    byteOrderedDataInputStream.seek((long) i);
                    byteOrderedDataInputStream.readFully(bArr);
                    this.mThumbnailBytes = bArr;
                }
            }
            if (DEBUG) {
                Log.d(TAG, "Setting thumbnail attributes with offset: " + intValue + ", length: " + min);
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream2 = byteOrderedDataInputStream;
        HashMap hashMap2 = hashMap;
        ExifAttribute exifAttribute = (ExifAttribute) hashMap2.get(TAG_STRIP_OFFSETS);
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap2.get(TAG_STRIP_BYTE_COUNTS);
        if (exifAttribute != null && exifAttribute2 != null) {
            long[] convertToLongArray = convertToLongArray(exifAttribute.getValue(this.mExifByteOrder));
            long[] convertToLongArray2 = convertToLongArray(exifAttribute2.getValue(this.mExifByteOrder));
            if (convertToLongArray == null || convertToLongArray.length == 0) {
                Log.w(TAG, "stripOffsets should not be null or have zero length.");
            } else if (convertToLongArray2 == null || convertToLongArray2.length == 0) {
                Log.w(TAG, "stripByteCounts should not be null or have zero length.");
            } else if (convertToLongArray.length != convertToLongArray2.length) {
                Log.w(TAG, "stripOffsets and stripByteCounts should have same length.");
            } else {
                long j = 0;
                for (long j2 : convertToLongArray2) {
                    j += j2;
                }
                int i = (int) j;
                byte[] bArr = new byte[i];
                this.mAreThumbnailStripsConsecutive = true;
                this.mHasThumbnailStrips = true;
                this.mHasThumbnail = true;
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < convertToLongArray.length; i4++) {
                    int i5 = (int) convertToLongArray[i4];
                    int i6 = (int) convertToLongArray2[i4];
                    if (i4 < convertToLongArray.length - 1 && ((long) (i5 + i6)) != convertToLongArray[i4 + 1]) {
                        this.mAreThumbnailStripsConsecutive = false;
                    }
                    int i7 = i5 - i2;
                    if (i7 < 0) {
                        Log.d(TAG, "Invalid strip offset value");
                    }
                    byteOrderedDataInputStream2.seek((long) i7);
                    int i8 = i2 + i7;
                    byte[] bArr2 = new byte[i6];
                    byteOrderedDataInputStream2.read(bArr2);
                    i2 = i8 + i6;
                    System.arraycopy(bArr2, 0, bArr, i3, i6);
                    i3 += i6;
                }
                this.mThumbnailBytes = bArr;
                if (this.mAreThumbnailStripsConsecutive) {
                    this.mThumbnailOffset = ((int) convertToLongArray[0]) + this.mExifOffset;
                    this.mThumbnailLength = i;
                }
            }
        }
    }

    private boolean isSupportedDataType(HashMap hashMap) throws IOException {
        ExifAttribute exifAttribute;
        int intValue;
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_BITS_PER_SAMPLE);
        if (exifAttribute2 != null) {
            int[] iArr = (int[]) exifAttribute2.getValue(this.mExifByteOrder);
            if (Arrays.equals(BITS_PER_SAMPLE_RGB, iArr)) {
                return true;
            }
            if (this.mMimeType == 3 && (exifAttribute = (ExifAttribute) hashMap.get(TAG_PHOTOMETRIC_INTERPRETATION)) != null && (((intValue = exifAttribute.getIntValue(this.mExifByteOrder)) == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (intValue == 6 && Arrays.equals(iArr, BITS_PER_SAMPLE_RGB)))) {
                return true;
            }
        }
        if (!DEBUG) {
            return false;
        }
        Log.d(TAG, "Unsupported data type value");
        return false;
    }

    private boolean isThumbnail(HashMap hashMap) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_IMAGE_LENGTH);
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_IMAGE_WIDTH);
        if (exifAttribute == null || exifAttribute2 == null) {
            return false;
        }
        return exifAttribute.getIntValue(this.mExifByteOrder) <= 512 && exifAttribute2.getIntValue(this.mExifByteOrder) <= 512;
    }

    private void validateImages() throws IOException {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute exifAttribute = this.mAttributes[1].get(TAG_PIXEL_X_DIMENSION);
        ExifAttribute exifAttribute2 = this.mAttributes[1].get(TAG_PIXEL_Y_DIMENSION);
        if (!(exifAttribute == null || exifAttribute2 == null)) {
            this.mAttributes[0].put(TAG_IMAGE_WIDTH, exifAttribute);
            this.mAttributes[0].put(TAG_IMAGE_LENGTH, exifAttribute2);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            HashMap<String, ExifAttribute>[] hashMapArr = this.mAttributes;
            hashMapArr[4] = hashMapArr[5];
            hashMapArr[5] = new HashMap<>();
        }
        if (!isThumbnail(this.mAttributes[4])) {
            Log.d(TAG, "No image meets the size requirements of a thumbnail image.");
        }
    }

    private void updateImageSizeValues(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        ExifAttribute exifAttribute;
        ExifAttribute exifAttribute2;
        ExifAttribute exifAttribute3 = this.mAttributes[i].get(TAG_DEFAULT_CROP_SIZE);
        ExifAttribute exifAttribute4 = this.mAttributes[i].get(TAG_RW2_SENSOR_TOP_BORDER);
        ExifAttribute exifAttribute5 = this.mAttributes[i].get(TAG_RW2_SENSOR_LEFT_BORDER);
        ExifAttribute exifAttribute6 = this.mAttributes[i].get(TAG_RW2_SENSOR_BOTTOM_BORDER);
        ExifAttribute exifAttribute7 = this.mAttributes[i].get(TAG_RW2_SENSOR_RIGHT_BORDER);
        if (exifAttribute3 != null) {
            if (exifAttribute3.format == 5) {
                Rational[] rationalArr = (Rational[]) exifAttribute3.getValue(this.mExifByteOrder);
                if (rationalArr == null || rationalArr.length != 2) {
                    Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(rationalArr));
                    return;
                }
                exifAttribute2 = ExifAttribute.createURational(rationalArr[0], this.mExifByteOrder);
                exifAttribute = ExifAttribute.createURational(rationalArr[1], this.mExifByteOrder);
            } else {
                int[] iArr = (int[]) exifAttribute3.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 2) {
                    Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                    return;
                }
                exifAttribute2 = ExifAttribute.createUShort(iArr[0], this.mExifByteOrder);
                exifAttribute = ExifAttribute.createUShort(iArr[1], this.mExifByteOrder);
            }
            this.mAttributes[i].put(TAG_IMAGE_WIDTH, exifAttribute2);
            this.mAttributes[i].put(TAG_IMAGE_LENGTH, exifAttribute);
        } else if (exifAttribute4 == null || exifAttribute5 == null || exifAttribute6 == null || exifAttribute7 == null) {
            retrieveJpegImageSize(byteOrderedDataInputStream, i);
        } else {
            int intValue = exifAttribute4.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute6.getIntValue(this.mExifByteOrder);
            int intValue3 = exifAttribute7.getIntValue(this.mExifByteOrder);
            int intValue4 = exifAttribute5.getIntValue(this.mExifByteOrder);
            if (intValue2 > intValue && intValue3 > intValue4) {
                ExifAttribute createUShort = ExifAttribute.createUShort(intValue2 - intValue, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort(intValue3 - intValue4, this.mExifByteOrder);
                this.mAttributes[i].put(TAG_IMAGE_LENGTH, createUShort);
                this.mAttributes[i].put(TAG_IMAGE_WIDTH, createUShort2);
            }
        }
    }

    private int writeExifSegment(ByteOrderedDataOutputStream byteOrderedDataOutputStream) throws IOException {
        ByteOrderedDataOutputStream byteOrderedDataOutputStream2 = byteOrderedDataOutputStream;
        ExifTag[][] exifTagArr = EXIF_TAGS;
        int[] iArr = new int[exifTagArr.length];
        int[] iArr2 = new int[exifTagArr.length];
        for (ExifTag exifTag : EXIF_POINTER_TAGS) {
            removeAttribute(exifTag.name);
        }
        removeAttribute(JPEG_INTERCHANGE_FORMAT_TAG.name);
        removeAttribute(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name);
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            for (Object obj : this.mAttributes[i].entrySet().toArray()) {
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getValue() == null) {
                    this.mAttributes[i].remove(entry.getKey());
                }
            }
        }
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (this.mHasThumbnail) {
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(0, this.mExifByteOrder));
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name, ExifAttribute.createULong((long) this.mThumbnailLength, this.mExifByteOrder));
        }
        for (int i2 = 0; i2 < EXIF_TAGS.length; i2++) {
            int i3 = 0;
            for (Map.Entry<String, ExifAttribute> value : this.mAttributes[i2].entrySet()) {
                int size = ((ExifAttribute) value.getValue()).size();
                if (size > 4) {
                    i3 += size;
                }
            }
            iArr2[i2] = iArr2[i2] + i3;
        }
        int i4 = 8;
        for (int i5 = 0; i5 < EXIF_TAGS.length; i5++) {
            if (!this.mAttributes[i5].isEmpty()) {
                iArr[i5] = i4;
                i4 += (this.mAttributes[i5].size() * 12) + 2 + 4 + iArr2[i5];
            }
        }
        if (this.mHasThumbnail) {
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong((long) i4, this.mExifByteOrder));
            this.mThumbnailOffset = this.mExifOffset + i4;
            i4 += this.mThumbnailLength;
        }
        if (this.mMimeType == 4) {
            i4 += 8;
        }
        if (DEBUG) {
            for (int i6 = 0; i6 < EXIF_TAGS.length; i6++) {
                Log.d(TAG, String.format("index: %d, offsets: %d, tag count: %d, data sizes: %d, total size: %d", new Object[]{Integer.valueOf(i6), Integer.valueOf(iArr[i6]), Integer.valueOf(this.mAttributes[i6].size()), Integer.valueOf(iArr2[i6]), Integer.valueOf(i4)}));
            }
        }
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong((long) iArr[1], this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong((long) iArr[2], this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong((long) iArr[3], this.mExifByteOrder));
        }
        int i7 = this.mMimeType;
        if (i7 == 4) {
            byteOrderedDataOutputStream2.writeUnsignedShort(i4);
            byteOrderedDataOutputStream2.write(IDENTIFIER_EXIF_APP1);
        } else if (i7 == 13) {
            byteOrderedDataOutputStream2.writeInt(i4);
            byteOrderedDataOutputStream2.write(PNG_CHUNK_TYPE_EXIF);
        } else if (i7 == 14) {
            byteOrderedDataOutputStream2.write(WEBP_CHUNK_TYPE_EXIF);
            byteOrderedDataOutputStream2.writeInt(i4);
        }
        byteOrderedDataOutputStream2.writeShort(this.mExifByteOrder == ByteOrder.BIG_ENDIAN ? BYTE_ALIGN_MM : BYTE_ALIGN_II);
        byteOrderedDataOutputStream2.setByteOrder(this.mExifByteOrder);
        byteOrderedDataOutputStream2.writeUnsignedShort(42);
        byteOrderedDataOutputStream2.writeUnsignedInt(8);
        for (int i8 = 0; i8 < EXIF_TAGS.length; i8++) {
            if (!this.mAttributes[i8].isEmpty()) {
                byteOrderedDataOutputStream2.writeUnsignedShort(this.mAttributes[i8].size());
                int size2 = iArr[i8] + 2 + (this.mAttributes[i8].size() * 12) + 4;
                for (Map.Entry next : this.mAttributes[i8].entrySet()) {
                    int i9 = sExifTagMapsForWriting[i8].get(next.getKey()).number;
                    ExifAttribute exifAttribute = (ExifAttribute) next.getValue();
                    int size3 = exifAttribute.size();
                    byteOrderedDataOutputStream2.writeUnsignedShort(i9);
                    byteOrderedDataOutputStream2.writeUnsignedShort(exifAttribute.format);
                    byteOrderedDataOutputStream2.writeInt(exifAttribute.numberOfComponents);
                    if (size3 > 4) {
                        byteOrderedDataOutputStream2.writeUnsignedInt((long) size2);
                        size2 += size3;
                    } else {
                        byteOrderedDataOutputStream2.write(exifAttribute.bytes);
                        if (size3 < 4) {
                            while (size3 < 4) {
                                byteOrderedDataOutputStream2.writeByte(0);
                                size3++;
                            }
                        }
                    }
                }
                if (i8 != 0 || this.mAttributes[4].isEmpty()) {
                    byteOrderedDataOutputStream2.writeUnsignedInt(0);
                } else {
                    byteOrderedDataOutputStream2.writeUnsignedInt((long) iArr[4]);
                }
                for (Map.Entry<String, ExifAttribute> value2 : this.mAttributes[i8].entrySet()) {
                    ExifAttribute exifAttribute2 = (ExifAttribute) value2.getValue();
                    if (exifAttribute2.bytes.length > 4) {
                        byteOrderedDataOutputStream2.write(exifAttribute2.bytes, 0, exifAttribute2.bytes.length);
                    }
                }
            }
        }
        if (this.mHasThumbnail) {
            byteOrderedDataOutputStream2.write(getThumbnailBytes());
        }
        if (this.mMimeType == 14 && i4 % 2 == 1) {
            byteOrderedDataOutputStream2.writeByte(0);
        }
        byteOrderedDataOutputStream2.setByteOrder(ByteOrder.BIG_ENDIAN);
        return i4;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:68|69|70) */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        java.lang.Double.parseDouble(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x015c, code lost:
        return new android.util.Pair<>(12, -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0162, code lost:
        return new android.util.Pair<>(2, -1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:68:0x014e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair<java.lang.Integer, java.lang.Integer> guessDataFormat(java.lang.String r12) {
        /*
            java.lang.String r0 = ","
            boolean r1 = r12.contains(r0)
            r2 = 0
            r3 = 1
            r4 = 2
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            r6 = -1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)
            if (r1 == 0) goto L_0x00a6
            java.lang.String[] r12 = r12.split(r0, r6)
            r0 = r12[r2]
            android.util.Pair r0 = guessDataFormat(r0)
            java.lang.Object r1 = r0.first
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r1 != r4) goto L_0x0029
            return r0
        L_0x0029:
            int r1 = r12.length
            if (r3 >= r1) goto L_0x00a5
            r1 = r12[r3]
            android.util.Pair r1 = guessDataFormat(r1)
            java.lang.Object r2 = r1.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.lang.Object r4 = r0.first
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x004d
            java.lang.Object r2 = r1.second
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.lang.Object r4 = r0.first
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x004b
            goto L_0x004d
        L_0x004b:
            r2 = -1
            goto L_0x0055
        L_0x004d:
            java.lang.Object r2 = r0.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
        L_0x0055:
            java.lang.Object r4 = r0.second
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            if (r4 == r6) goto L_0x0080
            java.lang.Object r4 = r1.first
            java.lang.Integer r4 = (java.lang.Integer) r4
            java.lang.Object r8 = r0.second
            boolean r4 = r4.equals(r8)
            if (r4 != 0) goto L_0x0077
            java.lang.Object r1 = r1.second
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.lang.Object r4 = r0.second
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0080
        L_0x0077:
            java.lang.Object r1 = r0.second
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            goto L_0x0081
        L_0x0080:
            r1 = -1
        L_0x0081:
            if (r2 != r6) goto L_0x008b
            if (r1 != r6) goto L_0x008b
            android.util.Pair r12 = new android.util.Pair
            r12.<init>(r5, r7)
            return r12
        L_0x008b:
            if (r2 != r6) goto L_0x0097
            android.util.Pair r0 = new android.util.Pair
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.<init>(r1, r7)
            goto L_0x00a2
        L_0x0097:
            if (r1 != r6) goto L_0x00a2
            android.util.Pair r0 = new android.util.Pair
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r0.<init>(r1, r7)
        L_0x00a2:
            int r3 = r3 + 1
            goto L_0x0029
        L_0x00a5:
            return r0
        L_0x00a6:
            java.lang.String r0 = "/"
            boolean r1 = r12.contains(r0)
            r8 = 0
            if (r1 == 0) goto L_0x0105
            java.lang.String[] r12 = r12.split(r0, r6)
            int r0 = r12.length
            if (r0 != r4) goto L_0x00ff
            r0 = r12[r2]     // Catch:{ NumberFormatException -> 0x00ff }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ NumberFormatException -> 0x00ff }
            long r0 = (long) r0     // Catch:{ NumberFormatException -> 0x00ff }
            r12 = r12[r3]     // Catch:{ NumberFormatException -> 0x00ff }
            double r2 = java.lang.Double.parseDouble(r12)     // Catch:{ NumberFormatException -> 0x00ff }
            long r2 = (long) r2     // Catch:{ NumberFormatException -> 0x00ff }
            r12 = 10
            int r4 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x00f5
            int r4 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r4 >= 0) goto L_0x00d0
            goto L_0x00f5
        L_0x00d0:
            r4 = 5
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r6 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r6 > 0) goto L_0x00eb
            int r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x00dd
            goto L_0x00eb
        L_0x00dd:
            android.util.Pair r0 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x00ff }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ NumberFormatException -> 0x00ff }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)     // Catch:{ NumberFormatException -> 0x00ff }
            r0.<init>(r12, r1)     // Catch:{ NumberFormatException -> 0x00ff }
            return r0
        L_0x00eb:
            android.util.Pair r12 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x00ff }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)     // Catch:{ NumberFormatException -> 0x00ff }
            r12.<init>(r0, r7)     // Catch:{ NumberFormatException -> 0x00ff }
            return r12
        L_0x00f5:
            android.util.Pair r0 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x00ff }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ NumberFormatException -> 0x00ff }
            r0.<init>(r12, r7)     // Catch:{ NumberFormatException -> 0x00ff }
            return r0
        L_0x00ff:
            android.util.Pair r12 = new android.util.Pair
            r12.<init>(r5, r7)
            return r12
        L_0x0105:
            long r0 = java.lang.Long.parseLong(r12)     // Catch:{ NumberFormatException -> 0x014e }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ NumberFormatException -> 0x014e }
            long r1 = r0.longValue()     // Catch:{ NumberFormatException -> 0x014e }
            r3 = 4
            int r4 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x0130
            long r1 = r0.longValue()     // Catch:{ NumberFormatException -> 0x014e }
            r10 = 65535(0xffff, double:3.23786E-319)
            int r4 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r4 > 0) goto L_0x0130
            android.util.Pair r0 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x014e }
            r1 = 3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x014e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x014e }
            r0.<init>(r1, r2)     // Catch:{ NumberFormatException -> 0x014e }
            return r0
        L_0x0130:
            long r0 = r0.longValue()     // Catch:{ NumberFormatException -> 0x014e }
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x0144
            android.util.Pair r0 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x014e }
            r1 = 9
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x014e }
            r0.<init>(r1, r7)     // Catch:{ NumberFormatException -> 0x014e }
            return r0
        L_0x0144:
            android.util.Pair r0 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x014e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x014e }
            r0.<init>(r1, r7)     // Catch:{ NumberFormatException -> 0x014e }
            return r0
        L_0x014e:
            java.lang.Double.parseDouble(r12)     // Catch:{ NumberFormatException -> 0x015d }
            android.util.Pair r12 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x015d }
            r0 = 12
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x015d }
            r12.<init>(r0, r7)     // Catch:{ NumberFormatException -> 0x015d }
            return r12
        L_0x015d:
            android.util.Pair r12 = new android.util.Pair
            r12.<init>(r5, r7)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.guessDataFormat(java.lang.String):android.util.Pair");
    }

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        final int mLength;
        int mPosition;

        public ByteOrderedDataInputStream(InputStream inputStream) throws IOException {
            this(inputStream, ByteOrder.BIG_ENDIAN);
        }

        ByteOrderedDataInputStream(InputStream inputStream, ByteOrder byteOrder) throws IOException {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.mDataInputStream = dataInputStream;
            int available = dataInputStream.available();
            this.mLength = available;
            this.mPosition = 0;
            this.mDataInputStream.mark(available);
            this.mByteOrder = byteOrder;
        }

        public ByteOrderedDataInputStream(byte[] bArr) throws IOException {
            this((InputStream) new ByteArrayInputStream(bArr));
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void seek(long j) throws IOException {
            int i = this.mPosition;
            if (((long) i) > j) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
                this.mDataInputStream.mark(this.mLength);
            } else {
                j -= (long) i;
            }
            int i2 = (int) j;
            if (skipBytes(i2) != i2) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public int peek() {
            return this.mPosition;
        }

        public int available() throws IOException {
            return this.mDataInputStream.available();
        }

        public int read() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.mDataInputStream.read(bArr, i, i2);
            this.mPosition += read;
            return read;
        }

        public int readUnsignedByte() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        public String readLine() throws IOException {
            Log.d(ExifInterface.TAG, "Currently unsupported");
            return null;
        }

        public boolean readBoolean() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        public char readChar() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        public String readUTF() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        public void readFully(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.mPosition + i2;
            this.mPosition = i3;
            if (i3 > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, i, i2) != i2) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public void readFully(byte[] bArr) throws IOException {
            int length = this.mPosition + bArr.length;
            this.mPosition = length;
            if (length > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, 0, bArr.length) != bArr.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public byte readByte() throws IOException {
            int i = this.mPosition + 1;
            this.mPosition = i;
            if (i <= this.mLength) {
                int read = this.mDataInputStream.read();
                if (read >= 0) {
                    return (byte) read;
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        public short readShort() throws IOException {
            int i = this.mPosition + 2;
            this.mPosition = i;
            if (i <= this.mLength) {
                int read = this.mDataInputStream.read();
                int read2 = this.mDataInputStream.read();
                if ((read | read2) >= 0) {
                    ByteOrder byteOrder = this.mByteOrder;
                    if (byteOrder == LITTLE_ENDIAN) {
                        return (short) ((read2 << 8) + read);
                    }
                    if (byteOrder == BIG_ENDIAN) {
                        return (short) ((read << 8) + read2);
                    }
                    throw new IOException("Invalid byte order: " + this.mByteOrder);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        public int readInt() throws IOException {
            int i = this.mPosition + 4;
            this.mPosition = i;
            if (i <= this.mLength) {
                int read = this.mDataInputStream.read();
                int read2 = this.mDataInputStream.read();
                int read3 = this.mDataInputStream.read();
                int read4 = this.mDataInputStream.read();
                if ((read | read2 | read3 | read4) >= 0) {
                    ByteOrder byteOrder = this.mByteOrder;
                    if (byteOrder == LITTLE_ENDIAN) {
                        return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                    }
                    if (byteOrder == BIG_ENDIAN) {
                        return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                    }
                    throw new IOException("Invalid byte order: " + this.mByteOrder);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        public int skipBytes(int i) throws IOException {
            int min = Math.min(i, this.mLength - this.mPosition);
            int i2 = 0;
            while (i2 < min) {
                i2 += this.mDataInputStream.skipBytes(min - i2);
            }
            this.mPosition += i2;
            return i2;
        }

        public int readUnsignedShort() throws IOException {
            int i = this.mPosition + 2;
            this.mPosition = i;
            if (i <= this.mLength) {
                int read = this.mDataInputStream.read();
                int read2 = this.mDataInputStream.read();
                if ((read | read2) >= 0) {
                    ByteOrder byteOrder = this.mByteOrder;
                    if (byteOrder == LITTLE_ENDIAN) {
                        return (read2 << 8) + read;
                    }
                    if (byteOrder == BIG_ENDIAN) {
                        return (read << 8) + read2;
                    }
                    throw new IOException("Invalid byte order: " + this.mByteOrder);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        public long readUnsignedInt() throws IOException {
            return ((long) readInt()) & 4294967295L;
        }

        public long readLong() throws IOException {
            int i = this.mPosition + 8;
            this.mPosition = i;
            if (i <= this.mLength) {
                int read = this.mDataInputStream.read();
                int read2 = this.mDataInputStream.read();
                int read3 = this.mDataInputStream.read();
                int read4 = this.mDataInputStream.read();
                int read5 = this.mDataInputStream.read();
                int read6 = this.mDataInputStream.read();
                int read7 = this.mDataInputStream.read();
                int read8 = this.mDataInputStream.read();
                if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                    ByteOrder byteOrder = this.mByteOrder;
                    if (byteOrder == LITTLE_ENDIAN) {
                        return (((long) read8) << 56) + (((long) read7) << 48) + (((long) read6) << 40) + (((long) read5) << 32) + (((long) read4) << 24) + (((long) read3) << 16) + (((long) read2) << 8) + ((long) read);
                    }
                    int i2 = read2;
                    if (byteOrder == BIG_ENDIAN) {
                        return (((long) read) << 56) + (((long) i2) << 48) + (((long) read3) << 40) + (((long) read4) << 32) + (((long) read5) << 24) + (((long) read6) << 16) + (((long) read7) << 8) + ((long) read8);
                    }
                    throw new IOException("Invalid byte order: " + this.mByteOrder);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }

        public int getLength() {
            return this.mLength;
        }
    }

    private static class ByteOrderedDataOutputStream extends FilterOutputStream {
        private ByteOrder mByteOrder;
        final OutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.mOutputStream = outputStream;
            this.mByteOrder = byteOrder;
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void write(byte[] bArr) throws IOException {
            this.mOutputStream.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.mOutputStream.write(bArr, i, i2);
        }

        public void writeByte(int i) throws IOException {
            this.mOutputStream.write(i);
        }

        public void writeShort(short s) throws IOException {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((s >>> 0) & 255);
                this.mOutputStream.write((s >>> 8) & 255);
            } else if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((s >>> 8) & 255);
                this.mOutputStream.write((s >>> 0) & 255);
            }
        }

        public void writeInt(int i) throws IOException {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((i >>> 0) & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 24) & 255);
            } else if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((i >>> 24) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write((i >>> 0) & 255);
            }
        }

        public void writeUnsignedShort(int i) throws IOException {
            writeShort((short) i);
        }

        public void writeUnsignedInt(long j) throws IOException {
            writeInt((int) j);
        }
    }

    private void swapBasedOnImageSize(int i, int i2) throws IOException {
        if (!this.mAttributes[i].isEmpty() && !this.mAttributes[i2].isEmpty()) {
            ExifAttribute exifAttribute = this.mAttributes[i].get(TAG_IMAGE_LENGTH);
            ExifAttribute exifAttribute2 = this.mAttributes[i].get(TAG_IMAGE_WIDTH);
            ExifAttribute exifAttribute3 = this.mAttributes[i2].get(TAG_IMAGE_LENGTH);
            ExifAttribute exifAttribute4 = this.mAttributes[i2].get(TAG_IMAGE_WIDTH);
            if (exifAttribute == null || exifAttribute2 == null) {
                if (DEBUG) {
                    Log.d(TAG, "First image does not contain valid size information");
                }
            } else if (exifAttribute3 != null && exifAttribute4 != null) {
                int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
                int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
                int intValue3 = exifAttribute3.getIntValue(this.mExifByteOrder);
                int intValue4 = exifAttribute4.getIntValue(this.mExifByteOrder);
                if (intValue < intValue3 && intValue2 < intValue4) {
                    HashMap<String, ExifAttribute>[] hashMapArr = this.mAttributes;
                    HashMap<String, ExifAttribute> hashMap = hashMapArr[i];
                    hashMapArr[i] = hashMapArr[i2];
                    hashMapArr[i2] = hashMap;
                }
            } else if (DEBUG) {
                Log.d(TAG, "Second image does not contain valid size information");
            }
        } else if (DEBUG) {
            Log.d(TAG, "Cannot perform swap since only one image data exists");
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    private static void closeFileDescriptor(FileDescriptor fileDescriptor) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.close(fileDescriptor);
            } catch (Exception unused) {
                Log.e(TAG, "Error closing fd.");
            }
        } else {
            Log.e(TAG, "closeFileDescriptor is called in API < 21, which must be wrong.");
        }
    }

    private static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return i;
            }
            i += read;
            outputStream.write(bArr, 0, read);
        }
    }

    private static void copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[8192];
        while (i > 0) {
            int min = Math.min(i, 8192);
            int read = inputStream.read(bArr, 0, min);
            if (read == min) {
                i -= read;
                outputStream.write(bArr, 0, read);
            } else {
                throw new IOException("Failed to copy the given amount of bytes from the inputstream to the output stream.");
            }
        }
    }

    private static long[] convertToLongArray(Object obj) {
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            long[] jArr = new long[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                jArr[i] = (long) iArr[i];
            }
            return jArr;
        } else if (obj instanceof long[]) {
            return (long[]) obj;
        } else {
            return null;
        }
    }

    private static boolean startsWith(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return sb.toString();
    }

    private boolean isSupportedFormatForSavingAttributes() {
        if (!this.mIsSupportedFile) {
            return false;
        }
        int i = this.mMimeType;
        return i == 4 || i == 13 || i == 14;
    }
}
