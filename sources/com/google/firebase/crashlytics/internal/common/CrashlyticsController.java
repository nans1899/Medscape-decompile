package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.NativeSessionFileProvider;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.ndk.NativeFileUtils;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.proto.ClsFileOutputStream;
import com.google.firebase.crashlytics.internal.proto.CodedOutputStream;
import com.google.firebase.crashlytics.internal.proto.SessionProtobufHelper;
import com.google.firebase.crashlytics.internal.report.ReportManager;
import com.google.firebase.crashlytics.internal.report.ReportUploader;
import com.google.firebase.crashlytics.internal.report.model.Report;
import com.google.firebase.crashlytics.internal.report.model.SessionReport;
import com.google.firebase.crashlytics.internal.report.network.CompositeCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.CreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.DefaultCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.NativeCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.stacktrace.MiddleOutFallbackStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.RemoveRepeatsStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import com.google.firebase.crashlytics.internal.unity.UnityVersionProvider;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CrashlyticsController {
    private static final int ANALYZER_VERSION = 1;
    static final FilenameFilter APP_EXCEPTION_MARKER_FILTER = CrashlyticsController$$Lambda$1.lambdaFactory$();
    static final String APP_EXCEPTION_MARKER_PREFIX = ".ae";
    private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    static final String FATAL_SESSION_DIR = "fatal-sessions";
    static final String FIREBASE_APPLICATION_EXCEPTION = "_ae";
    static final String FIREBASE_CRASH_TYPE = "fatal";
    static final int FIREBASE_CRASH_TYPE_FATAL = 1;
    static final String FIREBASE_TIMESTAMP = "timestamp";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    private static final String[] INITIAL_SESSION_PART_TAGS = {SESSION_USER_TAG, SESSION_APP_TAG, SESSION_OS_TAG, SESSION_DEVICE_TAG};
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    };
    private static final int MAX_CHAINED_EXCEPTION_DEPTH = 8;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    static final int MAX_STACK_SIZE = 1024;
    static final String NATIVE_SESSION_DIR = "native-sessions";
    static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    static final String SESSION_APP_TAG = "SessionApp";
    static final FilenameFilter SESSION_BEGIN_FILE_FILTER = new FileNameContainsFilter(SESSION_BEGIN_TAG) {
        public boolean accept(File file, String str) {
            return super.accept(file, str) && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.length() == 39 && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    /* access modifiers changed from: private */
    public static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_USER_TAG = "SessionUser";
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };
    /* access modifiers changed from: private */
    public final AnalyticsEventLogger analyticsEventLogger;
    /* access modifiers changed from: private */
    public final AppData appData;
    /* access modifiers changed from: private */
    public final CrashlyticsBackgroundWorker backgroundWorker;
    AtomicBoolean checkForUnsentReportsCalled = new AtomicBoolean(false);
    private final Context context;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    /* access modifiers changed from: private */
    public final CrashlyticsFileMarker crashMarker;
    /* access modifiers changed from: private */
    public final DataCollectionArbiter dataCollectionArbiter;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    /* access modifiers changed from: private */
    public final ReportUploader.HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    /* access modifiers changed from: private */
    public final LogFileManager logFileManager;
    private final CrashlyticsNativeComponent nativeComponent;
    TaskCompletionSource<Boolean> reportActionProvided = new TaskCompletionSource<>();
    /* access modifiers changed from: private */
    public final ReportManager reportManager;
    /* access modifiers changed from: private */
    public final ReportUploader.Provider reportUploaderProvider;
    /* access modifiers changed from: private */
    public final SessionReportingCoordinator reportingCoordinator;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    /* access modifiers changed from: private */
    public final String unityVersion;
    TaskCompletionSource<Boolean> unsentReportsAvailable = new TaskCompletionSource<>();
    TaskCompletionSource<Void> unsentReportsHandled = new TaskCompletionSource<>();
    private final UserMetadata userMetadata;

    private interface CodedOutputStreamWriteAction {
        void writeTo(CodedOutputStream codedOutputStream) throws Exception;
    }

    private static File[] ensureFileArrayNotNull(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String str) {
            this.string = str;
        }

        public boolean accept(File file, String str) {
            return str.contains(this.string) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String str) {
            this.sessionId = str;
        }

        public boolean accept(File file, String str) {
            if (!str.equals(this.sessionId + ClsFileOutputStream.SESSION_FILE_EXTENSION) && str.contains(this.sessionId) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION)) {
                return true;
            }
            return false;
        }
    }

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, str) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, str) || str.contains(CrashlyticsController.SESSION_EVENT_MISSING_BINARY_IMGS_TAG);
        }
    }

    CrashlyticsController(Context context2, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker, HttpRequestFactory httpRequestFactory2, IdManager idManager2, DataCollectionArbiter dataCollectionArbiter2, FileStore fileStore2, CrashlyticsFileMarker crashlyticsFileMarker, AppData appData2, ReportManager reportManager2, ReportUploader.Provider provider, CrashlyticsNativeComponent crashlyticsNativeComponent, UnityVersionProvider unityVersionProvider, AnalyticsEventLogger analyticsEventLogger2, SettingsDataProvider settingsDataProvider) {
        FileStore fileStore3 = fileStore2;
        ReportUploader.Provider provider2 = provider;
        this.context = context2;
        this.backgroundWorker = crashlyticsBackgroundWorker;
        this.httpRequestFactory = httpRequestFactory2;
        this.idManager = idManager2;
        this.dataCollectionArbiter = dataCollectionArbiter2;
        this.fileStore = fileStore3;
        this.crashMarker = crashlyticsFileMarker;
        this.appData = appData2;
        if (provider2 != null) {
            this.reportUploaderProvider = provider2;
        } else {
            this.reportUploaderProvider = defaultReportUploader();
        }
        this.nativeComponent = crashlyticsNativeComponent;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.analyticsEventLogger = analyticsEventLogger2;
        this.userMetadata = new UserMetadata();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(fileStore3);
        this.logFileManager = new LogFileManager(context2, this.logFileDirectoryProvider);
        this.reportManager = reportManager2 == null ? new ReportManager(new ReportUploaderFilesProvider()) : reportManager2;
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        MiddleOutFallbackStrategy middleOutFallbackStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
        this.stackTraceTrimmingStrategy = middleOutFallbackStrategy;
        this.reportingCoordinator = SessionReportingCoordinator.create(context2, idManager2, fileStore2, appData2, this.logFileManager, this.userMetadata, middleOutFallbackStrategy, settingsDataProvider);
    }

    private Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: package-private */
    public void enableExceptionHandling(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, SettingsDataProvider settingsDataProvider) {
        openSession();
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = new CrashlyticsUncaughtExceptionHandler(new CrashlyticsUncaughtExceptionHandler.CrashListener() {
            public void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th) {
                CrashlyticsController.this.handleUncaughtException(settingsDataProvider, thread, th);
            }
        }, settingsDataProvider, uncaughtExceptionHandler);
        this.crashHandler = crashlyticsUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(crashlyticsUncaughtExceptionHandler);
    }

    /* access modifiers changed from: package-private */
    public synchronized void handleUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th) {
        Logger logger = Logger.getLogger();
        logger.d("Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
        final Date date = new Date();
        final Throwable th2 = th;
        final Thread thread2 = thread;
        final SettingsDataProvider settingsDataProvider2 = settingsDataProvider;
        try {
            Utils.awaitEvenIfOnMainThread(this.backgroundWorker.submitTask(new Callable<Task<Void>>() {
                public Task<Void> call() throws Exception {
                    long access$300 = CrashlyticsController.getTimestampSeconds(date);
                    String access$400 = CrashlyticsController.this.getCurrentSessionId();
                    if (access$400 == null) {
                        Logger.getLogger().e("Tried to write a fatal exception while no session was open.");
                        return Tasks.forResult(null);
                    }
                    CrashlyticsController.this.crashMarker.create();
                    long j = access$300;
                    CrashlyticsController.this.reportingCoordinator.persistFatalEvent(th2, thread2, CrashlyticsController.makeFirebaseSessionIdentifier(access$400), j);
                    CrashlyticsController.this.doWriteFatal(thread2, th2, access$400, j);
                    CrashlyticsController.this.doWriteAppExceptionMarker(date.getTime());
                    Settings settings = settingsDataProvider2.getSettings();
                    int i = settings.getSessionData().maxCustomExceptionEvents;
                    int i2 = settings.getSessionData().maxCompleteSessionsCount;
                    CrashlyticsController.this.doCloseSessions(i);
                    CrashlyticsController.this.doOpenSession();
                    CrashlyticsController.this.trimSessionFiles(i2);
                    if (!CrashlyticsController.this.dataCollectionArbiter.isAutomaticDataCollectionEnabled()) {
                        return Tasks.forResult(null);
                    }
                    final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                    return settingsDataProvider2.getAppSettings().onSuccessTask(executor, new SuccessContinuation<AppSettingsData, Void>() {
                        public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                            if (appSettingsData == null) {
                                Logger.getLogger().w("Received null app settings, cannot send reports at crash time.");
                                return Tasks.forResult(null);
                            }
                            CrashlyticsController.this.sendSessionReports(appSettingsData, true);
                            return Tasks.whenAll((Task<?>[]) new Task[]{CrashlyticsController.this.logAnalyticsAppExceptionEvents(), CrashlyticsController.this.reportingCoordinator.sendReports(executor, DataTransportState.getState(appSettingsData))});
                        }
                    });
                }
            }));
        } catch (Exception unused) {
        }
    }

    private Task<Boolean> waitForReportAction() {
        if (this.dataCollectionArbiter.isAutomaticDataCollectionEnabled()) {
            Logger.getLogger().d("Automatic data collection is enabled. Allowing upload.");
            this.unsentReportsAvailable.trySetResult(false);
            return Tasks.forResult(true);
        }
        Logger.getLogger().d("Automatic data collection is disabled.");
        Logger.getLogger().d("Notifying that unsent reports are available.");
        this.unsentReportsAvailable.trySetResult(true);
        Task<TContinuationResult> onSuccessTask = this.dataCollectionArbiter.waitForAutomaticDataCollectionEnabled().onSuccessTask(new SuccessContinuation<Void, Boolean>() {
            public Task<Boolean> then(Void voidR) throws Exception {
                return Tasks.forResult(true);
            }
        });
        Logger.getLogger().d("Waiting for send/deleteUnsentReports to be called.");
        return Utils.race(onSuccessTask, this.reportActionProvided.getTask());
    }

    /* access modifiers changed from: package-private */
    public boolean didCrashOnPreviousExecution() {
        if (!this.crashMarker.isPresent()) {
            String currentSessionId = getCurrentSessionId();
            return currentSessionId != null && this.nativeComponent.hasCrashDataForSession(currentSessionId);
        }
        Logger.getLogger().d("Found previous crash marker.");
        this.crashMarker.remove();
        return Boolean.TRUE.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> checkForUnsentReports() {
        if (this.checkForUnsentReportsCalled.compareAndSet(false, true)) {
            return this.unsentReportsAvailable.getTask();
        }
        Logger.getLogger().d("checkForUnsentReports should only be called once per execution.");
        return Tasks.forResult(false);
    }

    /* access modifiers changed from: package-private */
    public Task<Void> sendUnsentReports() {
        this.reportActionProvided.trySetResult(true);
        return this.unsentReportsHandled.getTask();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> deleteUnsentReports() {
        this.reportActionProvided.trySetResult(false);
        return this.unsentReportsHandled.getTask();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> submitAllReports(final float f, final Task<AppSettingsData> task) {
        if (!this.reportManager.areReportsAvailable()) {
            Logger.getLogger().d("No reports are available.");
            this.unsentReportsAvailable.trySetResult(false);
            return Tasks.forResult(null);
        }
        Logger.getLogger().d("Unsent reports are available.");
        return waitForReportAction().onSuccessTask(new SuccessContinuation<Boolean, Void>() {
            public Task<Void> then(final Boolean bool) throws Exception {
                return CrashlyticsController.this.backgroundWorker.submitTask(new Callable<Task<Void>>() {
                    public Task<Void> call() throws Exception {
                        final List<Report> findReports = CrashlyticsController.this.reportManager.findReports();
                        if (!bool.booleanValue()) {
                            Logger.getLogger().d("Reports are being deleted.");
                            CrashlyticsController.deleteFiles(CrashlyticsController.this.listAppExceptionMarkerFiles());
                            CrashlyticsController.this.reportManager.deleteReports(findReports);
                            CrashlyticsController.this.reportingCoordinator.removeAllReports();
                            CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                            return Tasks.forResult(null);
                        }
                        Logger.getLogger().d("Reports are being sent.");
                        final boolean booleanValue = bool.booleanValue();
                        CrashlyticsController.this.dataCollectionArbiter.grantDataCollectionPermission(booleanValue);
                        final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                        return task.onSuccessTask(executor, new SuccessContinuation<AppSettingsData, Void>() {
                            public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                                if (appSettingsData == null) {
                                    Logger.getLogger().w("Received null app settings, cannot send reports during app startup.");
                                    return Tasks.forResult(null);
                                }
                                for (Report report : findReports) {
                                    if (report.getType() == Report.Type.JAVA) {
                                        CrashlyticsController.appendOrganizationIdToSessionFile(appSettingsData.organizationId, report.getFile());
                                    }
                                }
                                Task unused = CrashlyticsController.this.logAnalyticsAppExceptionEvents();
                                CrashlyticsController.this.reportUploaderProvider.createReportUploader(appSettingsData).uploadReportsAsync(findReports, booleanValue, f);
                                CrashlyticsController.this.reportingCoordinator.sendReports(executor, DataTransportState.getState(appSettingsData));
                                CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                                return Tasks.forResult(null);
                            }
                        });
                    }
                });
            }
        });
    }

    private ReportUploader.Provider defaultReportUploader() {
        return new ReportUploader.Provider() {
            public ReportUploader createReportUploader(AppSettingsData appSettingsData) {
                String str = appSettingsData.reportsUrl;
                String str2 = appSettingsData.ndkReportsUrl;
                return new ReportUploader(appSettingsData.organizationId, CrashlyticsController.this.appData.googleAppId, DataTransportState.getState(appSettingsData), CrashlyticsController.this.reportManager, CrashlyticsController.this.getCreateReportSpiCall(str, str2), CrashlyticsController.this.handlingExceptionCheck);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void writeToLog(final long j, final String str) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    return null;
                }
                CrashlyticsController.this.logFileManager.writeToLog(j, str);
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void writeNonFatalException(final Thread thread, final Throwable th) {
        final Date date = new Date();
        this.backgroundWorker.submit((Runnable) new Runnable() {
            public void run() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    long access$300 = CrashlyticsController.getTimestampSeconds(date);
                    String access$400 = CrashlyticsController.this.getCurrentSessionId();
                    if (access$400 == null) {
                        Logger.getLogger().d("Tried to write a non-fatal exception while no session was open.");
                        return;
                    }
                    long j = access$300;
                    CrashlyticsController.this.reportingCoordinator.persistNonFatalEvent(th, thread, CrashlyticsController.makeFirebaseSessionIdentifier(access$400), j);
                    CrashlyticsController.this.doWriteNonFatal(thread, th, access$400, j);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void setUserId(String str) {
        this.userMetadata.setUserId(str);
        cacheUserData(this.userMetadata);
    }

    /* access modifiers changed from: package-private */
    public void setCustomKey(String str, String str2) {
        try {
            this.userMetadata.setCustomKey(str, str2);
            cacheKeyData(this.userMetadata.getCustomKeys());
        } catch (IllegalArgumentException e) {
            Context context2 = this.context;
            if (context2 == null || !CommonUtils.isAppDebuggable(context2)) {
                Logger.getLogger().e("Attempting to set custom attribute with null key, ignoring.");
                return;
            }
            throw e;
        }
    }

    private void cacheUserData(final UserMetadata userMetadata2) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                String access$400 = CrashlyticsController.this.getCurrentSessionId();
                if (access$400 == null) {
                    Logger.getLogger().d("Tried to cache user data while no session was open.");
                    return null;
                }
                CrashlyticsController.this.reportingCoordinator.persistUserId(CrashlyticsController.makeFirebaseSessionIdentifier(access$400));
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(access$400, userMetadata2);
                return null;
            }
        });
    }

    private void cacheKeyData(final Map<String, String> map) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(CrashlyticsController.this.getCurrentSessionId(), map);
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void openSession() {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() throws Exception {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public String getCurrentSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length > 0) {
            return getSessionIdFromSessionFile(listSortedSessionBeginFiles[0]);
        }
        return null;
    }

    static String getSessionIdFromSessionFile(File file) {
        return file.getName().substring(0, 35);
    }

    /* access modifiers changed from: package-private */
    public boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean finalizeSessions(int i) {
        this.backgroundWorker.checkRunningOnThread();
        if (isHandlingException()) {
            Logger.getLogger().d("Skipping session finalization because a crash has already occurred.");
            return Boolean.FALSE.booleanValue();
        }
        Logger.getLogger().d("Finalizing previously open sessions.");
        try {
            doCloseSessions(i, true);
            Logger.getLogger().d("Closed all previously open sessions");
            return true;
        } catch (Exception e) {
            Logger.getLogger().e("Unable to finalize previously open sessions.", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void doOpenSession() throws Exception {
        long currentTimestampSeconds = getCurrentTimestampSeconds();
        String clsuuid = new CLSUUID(this.idManager).toString();
        Logger logger = Logger.getLogger();
        logger.d("Opening a new session with ID " + clsuuid);
        this.nativeComponent.openSession(clsuuid);
        writeBeginSession(clsuuid, currentTimestampSeconds);
        writeSessionApp(clsuuid);
        writeSessionOS(clsuuid);
        writeSessionDevice(clsuuid);
        this.logFileManager.setCurrentSession(clsuuid);
        this.reportingCoordinator.onBeginSession(makeFirebaseSessionIdentifier(clsuuid), currentTimestampSeconds);
    }

    /* access modifiers changed from: package-private */
    public void doCloseSessions(int i) throws Exception {
        doCloseSessions(i, false);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [int, boolean] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doCloseSessions(int r6, boolean r7) throws java.lang.Exception {
        /*
            r5 = this;
            int r0 = r7 + 8
            r5.trimOpenSessions(r0)
            java.io.File[] r0 = r5.listSortedSessionBeginFiles()
            int r1 = r0.length
            if (r1 > r7) goto L_0x0016
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.String r7 = "No open sessions to be closed."
            r6.d(r7)
            return
        L_0x0016:
            r1 = r0[r7]
            java.lang.String r1 = getSessionIdFromSessionFile(r1)
            r5.writeSessionUser(r1)
            com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent r2 = r5.nativeComponent
            boolean r2 = r2.hasCrashDataForSession(r1)
            if (r2 == 0) goto L_0x004a
            r5.finalizePreviousNativeSession(r1)
            com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent r2 = r5.nativeComponent
            boolean r2 = r2.finalizeSession(r1)
            if (r2 != 0) goto L_0x004a
            com.google.firebase.crashlytics.internal.Logger r2 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Could not finalize native session: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.d(r1)
        L_0x004a:
            r5.closeOpenSessions(r0, r7, r6)
            r6 = 0
            if (r7 == 0) goto L_0x005b
            r6 = 0
            r6 = r0[r6]
            java.lang.String r6 = getSessionIdFromSessionFile(r6)
            java.lang.String r6 = makeFirebaseSessionIdentifier(r6)
        L_0x005b:
            com.google.firebase.crashlytics.internal.common.SessionReportingCoordinator r7 = r5.reportingCoordinator
            long r0 = getCurrentTimestampSeconds()
            r7.finalizeSessions(r0, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.CrashlyticsController.doCloseSessions(int, boolean):void");
    }

    private void closeOpenSessions(File[] fileArr, int i, int i2) {
        Logger.getLogger().d("Closing open sessions.");
        while (i < fileArr.length) {
            File file = fileArr[i];
            String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            Logger logger = Logger.getLogger();
            logger.d("Closing session: " + sessionIdFromSessionFile);
            writeSessionPartsToSessionFile(file, sessionIdFromSessionFile, i2);
            i++;
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream != null) {
            try {
                clsFileOutputStream.closeInProgressStream();
            } catch (IOException e) {
                Logger.getLogger().e("Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private File[] listSessionPartFilesFor(String str) {
        return listFilesMatching(new SessionPartFileFilter(str));
    }

    /* access modifiers changed from: package-private */
    public File[] listCompleteSessionFiles() {
        LinkedList linkedList = new LinkedList();
        Collections.addAll(linkedList, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) linkedList.toArray(new File[linkedList.size()]);
    }

    /* access modifiers changed from: package-private */
    public File[] listNativeSessionFileDirectories() {
        return ensureFileArrayNotNull(getNativeSessionFilesDir().listFiles());
    }

    /* access modifiers changed from: package-private */
    public File[] listSessionBeginFiles() {
        return listFilesMatching(SESSION_BEGIN_FILE_FILTER);
    }

    /* access modifiers changed from: package-private */
    public File[] listAppExceptionMarkerFiles() {
        return listFilesMatching(APP_EXCEPTION_MARKER_FILTER);
    }

    private File[] listSortedSessionBeginFiles() {
        File[] listSessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(listSessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return listSessionBeginFiles;
    }

    /* access modifiers changed from: private */
    public File[] listFilesMatching(FilenameFilter filenameFilter) {
        return listFilesMatching(getFilesDir(), filenameFilter);
    }

    private static File[] listFilesMatching(File file, FilenameFilter filenameFilter) {
        return ensureFileArrayNotNull(file.listFiles(filenameFilter));
    }

    private void trimSessionEventFiles(String str, int i) {
        File filesDir = getFilesDir();
        Utils.capFileCount(filesDir, new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG), i, SMALLEST_FILE_NAME_FIRST);
    }

    /* access modifiers changed from: package-private */
    public void trimSessionFiles(int i) {
        int capSessionCount = i - Utils.capSessionCount(getNativeSessionFilesDir(), getFatalSessionFilesDir(), i, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, capSessionCount - Utils.capFileCount(getNonFatalSessionFilesDir(), capSessionCount, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int i) {
        HashSet hashSet = new HashSet();
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        int min = Math.min(i, listSortedSessionBeginFiles.length);
        for (int i2 = 0; i2 < min; i2++) {
            hashSet.add(getSessionIdFromSessionFile(listSortedSessionBeginFiles[i2]));
        }
        this.logFileManager.discardOldLogFiles(hashSet);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), hashSet);
    }

    private void retainSessions(File[] fileArr, Set<String> set) {
        for (File file : fileArr) {
            String name = file.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(name);
            if (!matcher.matches()) {
                Logger.getLogger().d("Deleting unknown file: " + name);
                file.delete();
            } else if (!set.contains(matcher.group(1))) {
                Logger.getLogger().d("Trimming session file: " + name);
                file.delete();
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String str, File[] fileArr, int i) {
        if (fileArr.length <= i) {
            return fileArr;
        }
        Logger.getLogger().d(String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(i)}));
        trimSessionEventFiles(str, i);
        return listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
    }

    /* access modifiers changed from: package-private */
    public void cleanInvalidTempFiles() {
        this.backgroundWorker.submit((Runnable) new Runnable() {
            public void run() {
                CrashlyticsController crashlyticsController = CrashlyticsController.this;
                crashlyticsController.doCleanInvalidTempFiles(crashlyticsController.listFilesMatching(new InvalidPartFileFilter()));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void doCleanInvalidTempFiles(File[] fileArr) {
        final HashSet hashSet = new HashSet();
        for (File file : fileArr) {
            Logger.getLogger().d("Found invalid session part file: " + file);
            hashSet.add(getSessionIdFromSessionFile(file));
        }
        if (!hashSet.isEmpty()) {
            for (File file2 : listFilesMatching(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    if (str.length() < 35) {
                        return false;
                    }
                    return hashSet.contains(str.substring(0, 35));
                }
            })) {
                Logger.getLogger().d("Deleting invalid session file: " + file2);
                file2.delete();
            }
        }
    }

    private void finalizePreviousNativeSession(String str) {
        Logger logger = Logger.getLogger();
        logger.d("Finalizing native report for session " + str);
        NativeSessionFileProvider sessionFileProvider = this.nativeComponent.getSessionFileProvider(str);
        File minidumpFile = sessionFileProvider.getMinidumpFile();
        if (minidumpFile == null || !minidumpFile.exists()) {
            Logger logger2 = Logger.getLogger();
            logger2.w("No minidump data found for session " + str);
            return;
        }
        long lastModified = minidumpFile.lastModified();
        LogFileManager logFileManager2 = new LogFileManager(this.context, this.logFileDirectoryProvider, str);
        File file = new File(getNativeSessionFilesDir(), str);
        if (!file.mkdirs()) {
            Logger.getLogger().d("Couldn't create native sessions directory");
            return;
        }
        doWriteAppExceptionMarker(lastModified);
        List<NativeSessionFile> nativeSessionFiles = getNativeSessionFiles(sessionFileProvider, str, getContext(), getFilesDir(), logFileManager2.getBytesForLog());
        NativeSessionFileGzipper.processNativeSessions(file, nativeSessionFiles);
        this.reportingCoordinator.finalizeSessionWithNativeEvent(makeFirebaseSessionIdentifier(str), nativeSessionFiles);
        logFileManager2.clearLog();
    }

    private static long getCurrentTimestampSeconds() {
        return getTimestampSeconds(new Date());
    }

    /* access modifiers changed from: private */
    public static long getTimestampSeconds(Date date) {
        return date.getTime() / 1000;
    }

    /* access modifiers changed from: private */
    public static String makeFirebaseSessionIdentifier(String str) {
        return str.replaceAll("-", "");
    }

    /* access modifiers changed from: private */
    public void doWriteFatal(Thread thread, Throwable th, String str, long j) {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), str + SESSION_FATAL_TAG);
            try {
                codedOutputStream = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                writeSessionEvent(codedOutputStream, thread, th, j, "crash", true);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            clsFileOutputStream = null;
            try {
                Logger.getLogger().e("An error occurred in the fatal exception logger", e);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            } catch (Throwable th2) {
                th = th2;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw th;
        }
        CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session begin file.");
        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close fatal exception file output stream.");
    }

    /* access modifiers changed from: private */
    public void doWriteAppExceptionMarker(long j) {
        try {
            File filesDir = getFilesDir();
            new File(filesDir, APP_EXCEPTION_MARKER_PREFIX + j).createNewFile();
        } catch (IOException unused) {
            Logger.getLogger().d("Could not write app exception marker.");
        }
    }

    /* access modifiers changed from: private */
    public void doWriteNonFatal(Thread thread, Throwable th, String str, long j) {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream newInstance;
        String str2 = str;
        CodedOutputStream codedOutputStream = null;
        try {
            Logger.getLogger().d("Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), str2 + SESSION_NON_FATAL_TAG + CommonUtils.padWithZerosToMaxIntWidth(this.eventCounter.getAndIncrement()));
            try {
                newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
            } catch (Exception e) {
                e = e;
                try {
                    Logger.getLogger().e("An error occurred in the non-fatal exception logger", e);
                    CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                    trimSessionEventFiles(str2, 64);
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            }
            try {
                writeSessionEvent(newInstance, thread, th, j, "error", false);
                CommonUtils.flushOrLog(newInstance, "Failed to flush to non-fatal file.");
            } catch (Exception e2) {
                e = e2;
                codedOutputStream = newInstance;
                Logger.getLogger().e("An error occurred in the non-fatal exception logger", e);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                trimSessionEventFiles(str2, 64);
            } catch (Throwable th3) {
                th = th3;
                codedOutputStream = newInstance;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            Logger.getLogger().e("An error occurred in the non-fatal exception logger", e);
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
            trimSessionEventFiles(str2, 64);
        } catch (Throwable th4) {
            th = th4;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to non-fatal file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
            throw th;
        }
        CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close non-fatal file output stream.");
        try {
            trimSessionEventFiles(str2, 64);
        } catch (Exception e4) {
            Logger.getLogger().e("An error occurred when trimming non-fatal files.", e4);
        }
    }

    private void writeSessionPartFile(String str, String str2, CodedOutputStreamWriteAction codedOutputStreamWriteAction) throws Exception {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), str + str2);
            try {
                codedOutputStream = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                codedOutputStreamWriteAction.writeTo(codedOutputStream);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session " + str2 + " file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session " + str2 + " file.");
            } catch (Throwable th) {
                th = th;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session " + str2 + " file.");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session " + str2 + " file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to session " + str2 + " file.");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close session " + str2 + " file.");
            throw th;
        }
    }

    private static void appendToProtoFile(File file, CodedOutputStreamWriteAction codedOutputStreamWriteAction) throws Exception {
        FileOutputStream fileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            try {
                codedOutputStream = CodedOutputStream.newInstance((OutputStream) fileOutputStream);
                codedOutputStreamWriteAction.writeTo(codedOutputStream);
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to append to " + file.getPath());
                CommonUtils.closeOrLog(fileOutputStream, "Failed to close " + file.getPath());
            } catch (Throwable th) {
                th = th;
                CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to append to " + file.getPath());
                CommonUtils.closeOrLog(fileOutputStream, "Failed to close " + file.getPath());
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            CommonUtils.flushOrLog(codedOutputStream, "Failed to flush to append to " + file.getPath());
            CommonUtils.closeOrLog(fileOutputStream, "Failed to close " + file.getPath());
            throw th;
        }
    }

    private void writeBeginSession(String str, long j) throws Exception {
        String format = String.format(Locale.US, GENERATOR_FORMAT, new Object[]{CrashlyticsCore.getVersion()});
        final String str2 = str;
        final String str3 = format;
        final long j2 = j;
        writeSessionPartFile(str, SESSION_BEGIN_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) throws Exception {
                SessionProtobufHelper.writeBeginSession(codedOutputStream, str2, str3, j2);
            }
        });
        this.nativeComponent.writeBeginSession(str, format, j);
    }

    private void writeSessionApp(String str) throws Exception {
        String appIdentifier = this.idManager.getAppIdentifier();
        String str2 = this.appData.versionCode;
        String str3 = this.appData.versionName;
        final String str4 = appIdentifier;
        final String str5 = str2;
        final String str6 = str3;
        final String crashlyticsInstallId = this.idManager.getCrashlyticsInstallId();
        final int id = DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId();
        writeSessionPartFile(str, SESSION_APP_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) throws Exception {
                SessionProtobufHelper.writeSessionApp(codedOutputStream, str4, str5, str6, crashlyticsInstallId, id, CrashlyticsController.this.unityVersion);
            }
        });
        this.nativeComponent.writeSessionApp(str, str4, str5, str6, crashlyticsInstallId, id, this.unityVersion);
    }

    private void writeSessionOS(String str) throws Exception {
        final String str2 = Build.VERSION.RELEASE;
        final String str3 = Build.VERSION.CODENAME;
        final boolean isRooted = CommonUtils.isRooted(getContext());
        writeSessionPartFile(str, SESSION_OS_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) throws Exception {
                SessionProtobufHelper.writeSessionOS(codedOutputStream, str2, str3, isRooted);
            }
        });
        this.nativeComponent.writeSessionOs(str, str2, str3, isRooted);
    }

    private void writeSessionDevice(String str) throws Exception {
        Context context2 = getContext();
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int cpuArchitectureInt = CommonUtils.getCpuArchitectureInt();
        String str2 = Build.MODEL;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long totalRamInBytes = CommonUtils.getTotalRamInBytes();
        long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        boolean isEmulator = CommonUtils.isEmulator(context2);
        int deviceState = CommonUtils.getDeviceState(context2);
        String str3 = Build.MANUFACTURER;
        String str4 = Build.PRODUCT;
        final int i = cpuArchitectureInt;
        final String str5 = str2;
        final int i2 = availableProcessors;
        final long j = totalRamInBytes;
        final long j2 = blockCount;
        final boolean z = isEmulator;
        final int i3 = deviceState;
        final String str6 = str3;
        final String str7 = str4;
        writeSessionPartFile(str, SESSION_DEVICE_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) throws Exception {
                SessionProtobufHelper.writeSessionDevice(codedOutputStream, i, str5, i2, j, j2, z, i3, str6, str7);
            }
        });
        this.nativeComponent.writeSessionDevice(str, cpuArchitectureInt, str2, availableProcessors, totalRamInBytes, blockCount, isEmulator, deviceState, str3, str4);
    }

    private void writeSessionUser(String str) throws Exception {
        final UserMetadata userMetadata2 = getUserMetadata(str);
        writeSessionPartFile(str, SESSION_USER_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) throws Exception {
                SessionProtobufHelper.writeSessionUser(codedOutputStream, userMetadata2.getUserId(), (String) null, (String) null);
            }
        });
    }

    private void writeSessionEvent(CodedOutputStream codedOutputStream, Thread thread, Throwable th, long j, String str, boolean z) throws Exception {
        Thread[] threadArr;
        TreeMap treeMap;
        Map map;
        TrimmedThrowableData trimmedThrowableData = new TrimmedThrowableData(th, this.stackTraceTrimmingStrategy);
        Context context2 = getContext();
        BatteryState batteryState = BatteryState.get(context2);
        Float batteryLevel = batteryState.getBatteryLevel();
        int batteryVelocity = batteryState.getBatteryVelocity();
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(context2);
        int i = context2.getResources().getConfiguration().orientation;
        long totalRamInBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context2);
        long calculateUsedDiskSpaceInBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        ActivityManager.RunningAppProcessInfo appProcessInfo = CommonUtils.getAppProcessInfo(context2.getPackageName(), context2);
        LinkedList linkedList = new LinkedList();
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        String str2 = this.appData.buildId;
        String appIdentifier = this.idManager.getAppIdentifier();
        int i2 = 0;
        if (z) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            Thread[] threadArr2 = new Thread[allStackTraces.size()];
            for (Map.Entry next : allStackTraces.entrySet()) {
                threadArr2[i2] = (Thread) next.getKey();
                linkedList.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) next.getValue()));
                i2++;
            }
            threadArr = threadArr2;
        } else {
            threadArr = new Thread[0];
        }
        if (!CommonUtils.getBooleanResourceValue(context2, COLLECT_CUSTOM_KEYS, true)) {
            map = new TreeMap();
        } else {
            map = this.userMetadata.getCustomKeys();
            if (map != null && map.size() > 1) {
                treeMap = new TreeMap(map);
                SessionProtobufHelper.writeSessionEvent(codedOutputStream, j, str, trimmedThrowableData, thread, stackTraceElementArr, threadArr, linkedList, 8, treeMap, this.logFileManager.getBytesForLog(), appProcessInfo, i, appIdentifier, str2, batteryLevel, batteryVelocity, proximitySensorEnabled, totalRamInBytes, calculateUsedDiskSpaceInBytes);
                this.logFileManager.clearLog();
            }
        }
        treeMap = map;
        SessionProtobufHelper.writeSessionEvent(codedOutputStream, j, str, trimmedThrowableData, thread, stackTraceElementArr, threadArr, linkedList, 8, treeMap, this.logFileManager.getBytesForLog(), appProcessInfo, i, appIdentifier, str2, batteryLevel, batteryVelocity, proximitySensorEnabled, totalRamInBytes, calculateUsedDiskSpaceInBytes);
        this.logFileManager.clearLog();
    }

    private void writeSessionPartsToSessionFile(File file, String str, int i) {
        Logger logger = Logger.getLogger();
        logger.d("Collecting session parts for ID " + str);
        File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + SESSION_FATAL_TAG));
        boolean z = listFilesMatching != null && listFilesMatching.length > 0;
        Logger.getLogger().d(String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{str, Boolean.valueOf(z)}));
        File[] listFilesMatching2 = listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
        boolean z2 = listFilesMatching2 != null && listFilesMatching2.length > 0;
        Logger.getLogger().d(String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{str, Boolean.valueOf(z2)}));
        if (z || z2) {
            synthesizeSessionFile(file, str, getTrimmedNonFatalFiles(str, listFilesMatching2, i), z ? listFilesMatching[0] : null);
        } else {
            Logger logger2 = Logger.getLogger();
            logger2.d("No events present for session ID " + str);
        }
        Logger logger3 = Logger.getLogger();
        logger3.d("Removing session part files for ID " + str);
        deleteFiles(listSessionPartFilesFor(str));
    }

    private void synthesizeSessionFile(File file, String str, File[] fileArr, File file2) {
        ClsFileOutputStream clsFileOutputStream;
        boolean z = file2 != null;
        File fatalSessionFilesDir = z ? getFatalSessionFilesDir() : getNonFatalSessionFilesDir();
        if (!fatalSessionFilesDir.exists()) {
            fatalSessionFilesDir.mkdirs();
        }
        try {
            clsFileOutputStream = new ClsFileOutputStream(fatalSessionFilesDir, str);
            try {
                CodedOutputStream newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                Logger.getLogger().d("Collecting SessionStart data for session ID " + str);
                writeToCosFromFile(newInstance, file);
                newInstance.writeUInt64(4, getCurrentTimestampSeconds());
                newInstance.writeBool(5, z);
                newInstance.writeUInt32(11, 1);
                newInstance.writeEnum(12, 3);
                writeInitialPartsTo(newInstance, str);
                writeNonFatalEventsTo(newInstance, fileArr, str);
                if (z) {
                    writeToCosFromFile(newInstance, file2);
                }
                CommonUtils.flushOrLog(newInstance, "Error flushing session file stream");
                CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
            } catch (Exception e) {
                e = e;
                try {
                    Logger.getLogger().e("Failed to write session file for session ID: " + str, e);
                    CommonUtils.flushOrLog((Flushable) null, "Error flushing session file stream");
                    closeWithoutRenamingOrLog(clsFileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.flushOrLog((Flushable) null, "Error flushing session file stream");
                    CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            clsFileOutputStream = null;
            Logger.getLogger().e("Failed to write session file for session ID: " + str, e);
            CommonUtils.flushOrLog((Flushable) null, "Error flushing session file stream");
            closeWithoutRenamingOrLog(clsFileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            clsFileOutputStream = null;
            CommonUtils.flushOrLog((Flushable) null, "Error flushing session file stream");
            CommonUtils.closeOrLog(clsFileOutputStream, "Failed to close CLS file");
            throw th;
        }
    }

    private static void writeNonFatalEventsTo(CodedOutputStream codedOutputStream, File[] fileArr, String str) {
        Arrays.sort(fileArr, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File file : fileArr) {
            try {
                Logger.getLogger().d(String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, file.getName()}));
                writeToCosFromFile(codedOutputStream, file);
            } catch (Exception e) {
                Logger.getLogger().e("Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream codedOutputStream, String str) throws IOException {
        for (String str2 : INITIAL_SESSION_PART_TAGS) {
            File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + str2 + ClsFileOutputStream.SESSION_FILE_EXTENSION));
            if (listFilesMatching.length == 0) {
                Logger.getLogger().d("Can't find " + str2 + " data for session ID " + str);
            } else {
                Logger.getLogger().d("Collecting " + str2 + " data for session ID " + str);
                writeToCosFromFile(codedOutputStream, listFilesMatching[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void appendOrganizationIdToSessionFile(final String str, File file) throws Exception {
        if (str != null) {
            appendToProtoFile(file, new CodedOutputStreamWriteAction() {
                public void writeTo(CodedOutputStream codedOutputStream) throws Exception {
                    SessionProtobufHelper.writeSessionAppClsId(codedOutputStream, str);
                }
            });
        }
    }

    private static void writeToCosFromFile(CodedOutputStream codedOutputStream, File file) throws IOException {
        if (!file.exists()) {
            Logger logger = Logger.getLogger();
            logger.e("Tried to include a file that doesn't exist: " + file.getName());
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                copyToCodedOutputStream(fileInputStream2, codedOutputStream, (int) file.length());
                CommonUtils.closeOrLog(fileInputStream2, "Failed to close file input stream.");
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            CommonUtils.closeOrLog(fileInputStream, "Failed to close file input stream.");
            throw th;
        }
    }

    private static void copyToCodedOutputStream(InputStream inputStream, CodedOutputStream codedOutputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read < 0) {
                break;
            }
            i2 += read;
        }
        codedOutputStream.writeRawBytes(bArr);
    }

    /* access modifiers changed from: package-private */
    public UserMetadata getUserMetadata() {
        return this.userMetadata;
    }

    private UserMetadata getUserMetadata(String str) {
        if (isHandlingException()) {
            return this.userMetadata;
        }
        return new MetaDataStore(getFilesDir()).readUserData(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isHandlingException() {
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = this.crashHandler;
        return crashlyticsUncaughtExceptionHandler != null && crashlyticsUncaughtExceptionHandler.isHandlingException();
    }

    /* access modifiers changed from: package-private */
    public File getFilesDir() {
        return this.fileStore.getFilesDir();
    }

    /* access modifiers changed from: package-private */
    public File getNativeSessionFilesDir() {
        return new File(getFilesDir(), NATIVE_SESSION_DIR);
    }

    /* access modifiers changed from: package-private */
    public File getFatalSessionFilesDir() {
        return new File(getFilesDir(), FATAL_SESSION_DIR);
    }

    /* access modifiers changed from: package-private */
    public File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), NONFATAL_SESSION_DIR);
    }

    /* access modifiers changed from: private */
    public CreateReportSpiCall getCreateReportSpiCall(String str, String str2) {
        String stringsFileValue = CommonUtils.getStringsFileValue(getContext(), CRASHLYTICS_API_ENDPOINT);
        return new CompositeCreateReportSpiCall(new DefaultCreateReportSpiCall(stringsFileValue, str, this.httpRequestFactory, CrashlyticsCore.getVersion()), new NativeCreateReportSpiCall(stringsFileValue, str2, this.httpRequestFactory, CrashlyticsCore.getVersion()));
    }

    /* access modifiers changed from: private */
    public void sendSessionReports(AppSettingsData appSettingsData, boolean z) throws Exception {
        Context context2 = getContext();
        ReportUploader createReportUploader = this.reportUploaderProvider.createReportUploader(appSettingsData);
        for (File file : listCompleteSessionFiles()) {
            appendOrganizationIdToSessionFile(appSettingsData.organizationId, file);
            this.backgroundWorker.submit((Runnable) new SendReportRunnable(context2, new SessionReport(file, SEND_AT_CRASHTIME_HEADER), createReportUploader, z));
        }
    }

    /* access modifiers changed from: private */
    public Task<Void> logAnalyticsAppExceptionEvents() {
        ArrayList arrayList = new ArrayList();
        for (File file : listAppExceptionMarkerFiles()) {
            try {
                arrayList.add(logAnalyticsAppExceptionEvent(Long.parseLong(file.getName().substring(3))));
            } catch (NumberFormatException unused) {
                Logger.getLogger().d("Could not parse timestamp from file " + file.getName());
            }
            file.delete();
        }
        return Tasks.whenAll((Collection<? extends Task<?>>) arrayList);
    }

    private Task<Void> logAnalyticsAppExceptionEvent(final long j) {
        if (!firebaseCrashExists()) {
            return Tasks.call(new ScheduledThreadPoolExecutor(1), new Callable<Void>() {
                public Void call() throws Exception {
                    Bundle bundle = new Bundle();
                    bundle.putInt("fatal", 1);
                    bundle.putLong("timestamp", j);
                    CrashlyticsController.this.analyticsEventLogger.logEvent("_ae", bundle);
                    return null;
                }
            });
        }
        Logger.getLogger().d("Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
        return Tasks.forResult(null);
    }

    /* access modifiers changed from: private */
    public static void deleteFiles(File[] fileArr) {
        if (fileArr != null) {
            for (File delete : fileArr) {
                delete.delete();
            }
        }
    }

    private static boolean firebaseCrashExists() {
        try {
            Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private final class ReportUploaderHandlingExceptionCheck implements ReportUploader.HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    private final class ReportUploaderFilesProvider implements ReportUploader.ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        public File[] getNativeReportFiles() {
            return CrashlyticsController.this.listNativeSessionFileDirectories();
        }
    }

    private static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final boolean dataCollectionToken;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context2, Report report2, ReportUploader reportUploader2, boolean z) {
            this.context = context2;
            this.report = report2;
            this.reportUploader = reportUploader2;
            this.dataCollectionToken = z;
        }

        public void run() {
            if (CommonUtils.canTryConnection(this.context)) {
                Logger.getLogger().d("Attempting to send crash report at time of crash...");
                this.reportUploader.uploadReport(this.report, this.dataCollectionToken);
            }
        }
    }

    static List<NativeSessionFile> getNativeSessionFiles(NativeSessionFileProvider nativeSessionFileProvider, String str, Context context2, File file, byte[] bArr) {
        byte[] bArr2;
        MetaDataStore metaDataStore = new MetaDataStore(file);
        File userDataFileForSession = metaDataStore.getUserDataFileForSession(str);
        File keysFileForSession = metaDataStore.getKeysFileForSession(str);
        try {
            bArr2 = NativeFileUtils.binaryImagesJsonFromMapsFile(nativeSessionFileProvider.getBinaryImagesFile(), context2);
        } catch (Exception unused) {
            bArr2 = null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BytesBackedNativeSessionFile("logs_file", "logs", bArr));
        arrayList.add(new BytesBackedNativeSessionFile("binary_images_file", "binaryImages", bArr2));
        arrayList.add(new FileBackedNativeSessionFile("crash_meta_file", "metadata", nativeSessionFileProvider.getMetadataFile()));
        arrayList.add(new FileBackedNativeSessionFile("session_meta_file", "session", nativeSessionFileProvider.getSessionFile()));
        arrayList.add(new FileBackedNativeSessionFile("app_meta_file", AdParameterKeys.SECTION_ID, nativeSessionFileProvider.getAppFile()));
        arrayList.add(new FileBackedNativeSessionFile("device_meta_file", "device", nativeSessionFileProvider.getDeviceFile()));
        arrayList.add(new FileBackedNativeSessionFile("os_meta_file", AdParameterKeys.OS, nativeSessionFileProvider.getOsFile()));
        arrayList.add(new FileBackedNativeSessionFile("minidump_file", "minidump", nativeSessionFileProvider.getMinidumpFile()));
        arrayList.add(new FileBackedNativeSessionFile("user_meta_file", "user", userDataFileForSession));
        arrayList.add(new FileBackedNativeSessionFile("keys_file", "keys", keysFileForSession));
        return arrayList;
    }

    private static final class LogFileDirectoryProvider implements LogFileManager.DirectoryProvider {
        private static final String LOG_FILES_DIR = "log-files";
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore fileStore) {
            this.rootFileStore = fileStore;
        }

        public File getLogFileDir() {
            File file = new File(this.rootFileStore.getFilesDir(), LOG_FILES_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
    }
}
