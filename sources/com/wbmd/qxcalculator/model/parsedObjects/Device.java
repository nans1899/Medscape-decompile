package com.wbmd.qxcalculator.model.parsedObjects;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.StringIdHandler;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import java.io.InputStream;
import java.util.ArrayList;

@Type("device")
public class Device implements Parcelable {
    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
        public Device createFromParcel(Parcel parcel) {
            Device device = new Device();
            device.identifier = (String) parcel.readValue(String.class.getClassLoader());
            device.osVersion = (String) parcel.readValue(String.class.getClassLoader());
            device.type = (String) parcel.readValue(String.class.getClassLoader());
            device.model = (String) parcel.readValue(String.class.getClassLoader());
            return device;
        }

        public Device[] newArray(int i) {
            return new Device[i];
        }
    };
    @Id(StringIdHandler.class)
    public String identifier;
    @JsonProperty("model")
    public String model = Build.MODEL;
    @JsonProperty("os_version")
    public String osVersion = Build.VERSION.RELEASE;
    @JsonProperty("type")
    public String type = "Android";

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.osVersion);
        parcel.writeValue(this.type);
        parcel.writeValue(this.model);
    }

    public static ArrayList<Device> convertJsonInputStreamToApp(InputStream inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setConfig(objectMapper.getDeserializationConfig().without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, (Class<?>[]) new Class[]{App.class});
        resourceConverter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        return new ArrayList<>(resourceConverter.readDocumentCollection(inputStream, Device.class).get());
    }
}
