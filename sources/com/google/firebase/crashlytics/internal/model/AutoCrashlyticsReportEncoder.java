package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.io.IOException;
import net.media.android.bidder.base.models.internal.Logger;

public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 1;
    public static final Configurator CONFIG = new AutoCrashlyticsReportEncoder();

    private AutoCrashlyticsReportEncoder() {
    }

    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.class, (ObjectEncoder<? super U>) CrashlyticsReportEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport.class, (ObjectEncoder<? super U>) CrashlyticsReportEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Application.Organization.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Application_Organization.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.User.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionUserEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_User.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionUserEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.OperatingSystem.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_OperatingSystem.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Thread.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Exception.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Signal.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.CustomAttribute.class, (ObjectEncoder<? super U>) CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_CustomAttribute.class, (ObjectEncoder<? super U>) CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Device.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Log.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Log.class, (ObjectEncoder<? super U>) CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.FilesPayload.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_FilesPayload.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.FilesPayload.File.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_FilesPayload_File.class, (ObjectEncoder<? super U>) CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
    }

    private static final class CrashlyticsReportEncoder implements ObjectEncoder<CrashlyticsReport> {
        static final CrashlyticsReportEncoder INSTANCE = new CrashlyticsReportEncoder();

        private CrashlyticsReportEncoder() {
        }

        public void encode(CrashlyticsReport crashlyticsReport, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, (Object) crashlyticsReport.getSdkVersion());
            objectEncoderContext.add("gmpAppId", (Object) crashlyticsReport.getGmpAppId());
            objectEncoderContext.add("platform", crashlyticsReport.getPlatform());
            objectEncoderContext.add("installationUuid", (Object) crashlyticsReport.getInstallationUuid());
            objectEncoderContext.add("buildVersion", (Object) crashlyticsReport.getBuildVersion());
            objectEncoderContext.add("displayVersion", (Object) crashlyticsReport.getDisplayVersion());
            objectEncoderContext.add("session", (Object) crashlyticsReport.getSession());
            objectEncoderContext.add("ndkPayload", (Object) crashlyticsReport.getNdkPayload());
        }
    }

    private static final class CrashlyticsReportSessionEncoder implements ObjectEncoder<CrashlyticsReport.Session> {
        static final CrashlyticsReportSessionEncoder INSTANCE = new CrashlyticsReportSessionEncoder();

        private CrashlyticsReportSessionEncoder() {
        }

        public void encode(CrashlyticsReport.Session session, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("generator", (Object) session.getGenerator());
            objectEncoderContext.add("identifier", (Object) session.getIdentifierUtf8Bytes());
            objectEncoderContext.add("startedAt", session.getStartedAt());
            objectEncoderContext.add("endedAt", (Object) session.getEndedAt());
            objectEncoderContext.add("crashed", session.isCrashed());
            objectEncoderContext.add(AdParameterKeys.SECTION_ID, (Object) session.getApp());
            objectEncoderContext.add("user", (Object) session.getUser());
            objectEncoderContext.add(AdParameterKeys.OS, (Object) session.getOs());
            objectEncoderContext.add("device", (Object) session.getDevice());
            objectEncoderContext.add(OmnitureConstants.CHANNEL, (Object) session.getEvents());
            objectEncoderContext.add("generatorType", session.getGeneratorType());
        }
    }

    private static final class CrashlyticsReportSessionApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application> {
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = new CrashlyticsReportSessionApplicationEncoder();

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("identifier", (Object) application.getIdentifier());
            objectEncoderContext.add("version", (Object) application.getVersion());
            objectEncoderContext.add("displayVersion", (Object) application.getDisplayVersion());
            objectEncoderContext.add("organization", (Object) application.getOrganization());
            objectEncoderContext.add("installationUuid", (Object) application.getInstallationUuid());
        }
    }

    private static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application.Organization> {
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application.Organization organization, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("clsId", (Object) organization.getClsId());
        }
    }

    private static final class CrashlyticsReportSessionUserEncoder implements ObjectEncoder<CrashlyticsReport.Session.User> {
        static final CrashlyticsReportSessionUserEncoder INSTANCE = new CrashlyticsReportSessionUserEncoder();

        private CrashlyticsReportSessionUserEncoder() {
        }

        public void encode(CrashlyticsReport.Session.User user, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("identifier", (Object) user.getIdentifier());
        }
    }

    private static final class CrashlyticsReportSessionOperatingSystemEncoder implements ObjectEncoder<CrashlyticsReport.Session.OperatingSystem> {
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        public void encode(CrashlyticsReport.Session.OperatingSystem operatingSystem, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("platform", operatingSystem.getPlatform());
            objectEncoderContext.add("version", (Object) operatingSystem.getVersion());
            objectEncoderContext.add("buildVersion", (Object) operatingSystem.getBuildVersion());
            objectEncoderContext.add("jailbroken", operatingSystem.isJailbroken());
        }
    }

    private static final class CrashlyticsReportSessionDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Device> {
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = new CrashlyticsReportSessionDeviceEncoder();

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("arch", device.getArch());
            objectEncoderContext.add("model", (Object) device.getModel());
            objectEncoderContext.add("cores", device.getCores());
            objectEncoderContext.add("ram", device.getRam());
            objectEncoderContext.add("diskSpace", device.getDiskSpace());
            objectEncoderContext.add("simulator", device.isSimulator());
            objectEncoderContext.add("state", device.getState());
            objectEncoderContext.add("manufacturer", (Object) device.getManufacturer());
            objectEncoderContext.add("modelClass", (Object) device.getModelClass());
        }
    }

    private static final class CrashlyticsReportSessionEventEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event> {
        static final CrashlyticsReportSessionEventEncoder INSTANCE = new CrashlyticsReportSessionEventEncoder();

        private CrashlyticsReportSessionEventEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event event, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("timestamp", event.getTimestamp());
            objectEncoderContext.add("type", (Object) event.getType());
            objectEncoderContext.add(AdParameterKeys.SECTION_ID, (Object) event.getApp());
            objectEncoderContext.add("device", (Object) event.getDevice());
            objectEncoderContext.add(Logger.LOG, (Object) event.getLog());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application> {
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("execution", (Object) application.getExecution());
            objectEncoderContext.add("customAttributes", (Object) application.getCustomAttributes());
            objectEncoderContext.add("background", (Object) application.getBackground());
            objectEncoderContext.add("uiOrientation", application.getUiOrientation());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution> {
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution execution, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("threads", (Object) execution.getThreads());
            objectEncoderContext.add("exception", (Object) execution.getException());
            objectEncoderContext.add("signal", (Object) execution.getSignal());
            objectEncoderContext.add("binaries", (Object) execution.getBinaries());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread thread, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("name", (Object) thread.getName());
            objectEncoderContext.add("importance", thread.getImportance());
            objectEncoderContext.add("frames", (Object) thread.getFrames());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame frame, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("pc", frame.getPc());
            objectEncoderContext.add("symbol", (Object) frame.getSymbol());
            objectEncoderContext.add("file", (Object) frame.getFile());
            objectEncoderContext.add("offset", frame.getOffset());
            objectEncoderContext.add("importance", frame.getImportance());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Exception> {
        static final CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception exception, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("type", (Object) exception.getType());
            objectEncoderContext.add("reason", (Object) exception.getReason());
            objectEncoderContext.add("frames", (Object) exception.getFrames());
            objectEncoderContext.add("causedBy", (Object) exception.getCausedBy());
            objectEncoderContext.add("overflowCount", exception.getOverflowCount());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Signal> {
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal signal, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("name", (Object) signal.getName());
            objectEncoderContext.add("code", (Object) signal.getCode());
            objectEncoderContext.add("address", signal.getAddress());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {
        static final CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("baseAddress", binaryImage.getBaseAddress());
            objectEncoderContext.add("size", binaryImage.getSize());
            objectEncoderContext.add("name", (Object) binaryImage.getName());
            objectEncoderContext.add("uuid", (Object) binaryImage.getUuidUtf8Bytes());
        }
    }

    private static final class CrashlyticsReportCustomAttributeEncoder implements ObjectEncoder<CrashlyticsReport.CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = new CrashlyticsReportCustomAttributeEncoder();

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        public void encode(CrashlyticsReport.CustomAttribute customAttribute, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("key", (Object) customAttribute.getKey());
            objectEncoderContext.add("value", (Object) customAttribute.getValue());
        }
    }

    private static final class CrashlyticsReportSessionEventDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Device> {
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("batteryLevel", (Object) device.getBatteryLevel());
            objectEncoderContext.add("batteryVelocity", device.getBatteryVelocity());
            objectEncoderContext.add("proximityOn", device.isProximityOn());
            objectEncoderContext.add("orientation", device.getOrientation());
            objectEncoderContext.add("ramUsed", device.getRamUsed());
            objectEncoderContext.add("diskUsed", device.getDiskUsed());
        }
    }

    private static final class CrashlyticsReportSessionEventLogEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Log> {
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = new CrashlyticsReportSessionEventLogEncoder();

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Log log, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("content", (Object) log.getContent());
        }
    }

    private static final class CrashlyticsReportFilesPayloadEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload> {
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = new CrashlyticsReportFilesPayloadEncoder();

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload filesPayload, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("files", (Object) filesPayload.getFiles());
            objectEncoderContext.add("orgId", (Object) filesPayload.getOrgId());
        }
    }

    private static final class CrashlyticsReportFilesPayloadFileEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload.File> {
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload.File file, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("filename", (Object) file.getFilename());
            objectEncoderContext.add("contents", (Object) file.getContents());
        }
    }
}
