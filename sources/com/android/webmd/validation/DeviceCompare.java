package com.android.webmd.validation;

import android.app.Activity;
import android.content.Context;
import com.android.webmd.model.Device;
import com.android.webmd.task.GetParsedContentListener;
import com.android.webmd.task.GetTask;
import com.android.webmd.util.Util;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DeviceCompare {
    private static final String URL = "http://184.73.69.209/devicelists.xml";
    /* access modifiers changed from: private */
    public static List<Device> devicelist;
    static boolean isDone;
    private static String myDeviceId;

    public static boolean deviceValidForEnterprise(Context context) {
        getDevicelistfromServer(context);
        return false;
    }

    public static boolean compareDevice(Context context, List<Device> list) {
        myDeviceId = Util.getDeviceId(context);
        if (list == null || list.size() == 0) {
            return false;
        }
        for (Device deviceId : list) {
            if (deviceId.getDeviceId().trim().equalsIgnoreCase(myDeviceId)) {
                return true;
            }
        }
        return false;
    }

    public static List<Device> getDevicelistfromServer(final Context context) {
        GetTask getTask = new GetTask(context);
        getTask.setGetURLContentsListener(new GetParsedContentListener() {
            public void onContentsDownloaded(List<Device> list) {
                DeviceCompare.devicelist = list;
                DeviceCompare.isDone = true;
                if (!DeviceCompare.compareDevice(context, DeviceCompare.devicelist)) {
                    ((Activity) context).finish();
                }
            }
        });
        try {
            getTask.execute(new URL[]{new URL(URL)});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return getDevicelist();
    }

    public static List<Device> getDevicelist() {
        return devicelist;
    }
}
