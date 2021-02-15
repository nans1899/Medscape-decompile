package com.wbmd.qxcalculator.model.parsedObjects;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.StringIdHandler;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import com.wbmd.qxcalculator.AppConfiguration;
import java.io.InputStream;
import java.util.ArrayList;

@Type("app")
public class App implements Parcelable {
    public static final Parcelable.Creator<App> CREATOR = new Parcelable.Creator<App>() {
        public App createFromParcel(Parcel parcel) {
            App app = new App();
            app.version = (String) parcel.readValue(String.class.getClassLoader());
            app.type = (String) parcel.readValue(String.class.getClassLoader());
            app.name = (String) parcel.readValue(String.class.getClassLoader());
            return app;
        }

        public App[] newArray(int i) {
            return new App[i];
        }
    };
    @Id(StringIdHandler.class)
    public String identifier;
    @JsonProperty("name")
    public String name = "calculate";
    @JsonProperty("type")
    public String type = "Android";
    @JsonProperty("version")
    public String version = AppConfiguration.getInstance().getAppBuildVersion();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.version);
        parcel.writeValue(this.type);
        parcel.writeValue(this.name);
    }

    public static ArrayList<App> convertJsonInputStreamToApp(InputStream inputStream) {
        Class<App> cls = App.class;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setConfig(objectMapper.getDeserializationConfig().without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, (Class<?>[]) new Class[]{cls});
        resourceConverter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        return new ArrayList<>(resourceConverter.readDocumentCollection(inputStream, cls).get());
    }
}
