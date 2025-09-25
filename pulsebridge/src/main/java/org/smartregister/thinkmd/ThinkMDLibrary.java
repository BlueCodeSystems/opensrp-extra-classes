package org.smartregister.thinkmd;

import android.content.Context;

import androidx.annotation.NonNull;

import org.smartregister.clientandeventmodel.Event;
import org.smartregister.domain.tag.FormTag;
import org.bluecodesystems.pulsebridge.PulseBridgeLibrary;
import org.bluecodesystems.pulsebridge.model.FHIRBundleModel;

@Deprecated
public final class ThinkMDLibrary {
    private final PulseBridgeLibrary delegate;
    private static ThinkMDLibrary legacyInstance;

    private ThinkMDLibrary(PulseBridgeLibrary delegate) {
        this.delegate = delegate;
    }

    public static ThinkMDLibrary getInstance() {
        if (legacyInstance == null) {
            legacyInstance = new ThinkMDLibrary(PulseBridgeLibrary.getInstance());
        }
        return legacyInstance;
    }

    public static void init(@NonNull Context context, @NonNull ThinkMDConfig config) {
        PulseBridgeLibrary.init(context, config);
        legacyInstance = null;
    }

    public void processHealthAssessment(@NonNull Context context, @NonNull FHIRBundleModel model) {
        delegate.processHealthAssessment(context, model);
    }

    public Event createCarePlanEvent(@NonNull String encodedBundle, FormTag formTag, @NonNull String entityId) {
        return delegate.createCarePlanEvent(encodedBundle, formTag, entityId);
    }

    public String getThinkMDPatientId(@NonNull String encodedBundle) {
        return delegate.getPulseBridgePatientId(encodedBundle);
    }

    public org.bluecodesystems.pulsebridge.PulseBridgeConfig getThinkMDConfig() {
        return delegate.getConfiguration();
    }

    public Context getContext() {
        return delegate.getContext();
    }
}
