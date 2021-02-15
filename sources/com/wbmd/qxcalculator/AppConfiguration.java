package com.wbmd.qxcalculator;

public class AppConfiguration {
    private static AppConfiguration instance;
    private FilesProvider filesProvider;
    private AppConfigurationProvider provider;

    private AppConfiguration() {
    }

    public static synchronized AppConfiguration getInstance() {
        AppConfiguration appConfiguration;
        synchronized (AppConfiguration.class) {
            if (instance == null) {
                instance = new AppConfiguration();
            }
            appConfiguration = instance;
        }
        return appConfiguration;
    }

    public void setProvider(AppConfigurationProvider appConfigurationProvider) {
        this.provider = appConfigurationProvider;
    }

    public void setFilesProvider(FilesProvider filesProvider2) {
        this.filesProvider = filesProvider2;
    }

    public AppConfigurationProvider getProvider() {
        return this.provider;
    }

    public FilesProvider getFilesProvider() {
        return this.filesProvider;
    }

    public String getAppBuildVersion() {
        try {
            if (getProvider() != null) {
                return getProvider().getAppBuildVersion();
            }
            return "1.0";
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    public String getPlatformOsForContentItem() {
        try {
            if (getProvider() != null) {
                return getProvider().getPlatformOsForContentItem();
            }
            return "android";
        } catch (Exception e) {
            e.printStackTrace();
            return "android";
        }
    }

    public String contentJSONFileName() {
        return getFilesProvider() != null ? getFilesProvider().contentJSONFileName() : "content";
    }

    public String resourcesZipFileName() {
        return getFilesProvider() != null ? getFilesProvider().resourcesZipFileName() : "resources";
    }

    public String databaseFileName() {
        return getFilesProvider() != null ? getFilesProvider().databaseName() : "user";
    }
}
