package org.smartregister.pulsebridge;

import android.content.Context;

import androidx.annotation.NonNull;

import org.smartregister.clientandeventmodel.Event;
import org.smartregister.domain.tag.FormTag;
import org.smartregister.thinkmd.ThinkMDConfig;
import org.bluecodesystems.pulsebridge.model.FHIRBundleModel;

@Deprecated
public final class PulseBridgeLibrary {
    private PulseBridgeLibrary() {
    }

    public static org.bluecodesystems.pulsebridge.PulseBridgeLibrary getInstance() {
        return org.bluecodesystems.pulsebridge.PulseBridgeLibrary.getInstance();
    }

    public static void init(@NonNull Context context, @NonNull PulseBridgeConfig configuration) {
        org.bluecodesystems.pulsebridge.PulseBridgeLibrary.init(context, configuration);
    }

    public static void init(@NonNull Context context, @NonNull ThinkMDConfig configuration) {
        org.bluecodesystems.pulsebridge.PulseBridgeLibrary.init(context, configuration);
    }

    public static void processHealthAssessment(@NonNull Context context, @NonNull FHIRBundleModel model) {
        getInstance().processHealthAssessment(context, model);
    }

    public static Event createCarePlanEvent(@NonNull String encodedBundle, @NonNull FormTag formTag, @NonNull String entityId) {
        return getInstance().createCarePlanEvent(encodedBundle, formTag, entityId);
    }

    public static String getPulseBridgePatientId(@NonNull String encodedBundle) {
        return getInstance().getPulseBridgePatientId(encodedBundle);
    }

    public static String getThinkMDPatientId(@NonNull String encodedBundle) {
        return getInstance().getPulseBridgePatientId(encodedBundle);
    }

    public static PulseBridgeConfig getConfiguration() {
        org.bluecodesystems.pulsebridge.PulseBridgeConfig delegateConfig = getInstance().getConfiguration();
        PulseBridgeConfig config = new PulseBridgeConfig();
        config.setServiceEndpoint(delegateConfig.getServiceEndpoint());
        config.setWebAppPath(delegateConfig.getWebAppPath());
        return config;
    }
}
