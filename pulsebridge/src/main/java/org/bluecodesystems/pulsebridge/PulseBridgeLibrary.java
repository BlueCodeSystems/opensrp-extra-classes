package org.bluecodesystems.pulsebridge;

import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.domain.tag.FormTag;
import org.bluecodesystems.pulsebridge.event.PulseBridgeJsonFormUtils;
import org.bluecodesystems.pulsebridge.model.FHIRBundleModel;
import org.bluecodesystems.pulsebridge.model.FHIREventModel;
import org.bluecodesystems.pulsebridge.utils.FHIRBundleGenerator;
import org.bluecodesystems.pulsebridge.utils.FHIRBundleParser;
import org.bluecodesystems.pulsebridge.utils.Utils;

import timber.log.Timber;

public class PulseBridgeLibrary {
    private static PulseBridgeLibrary instance;
    private final Context context;
    private final PulseBridgeConfig configuration;

    private PulseBridgeLibrary(Context context, PulseBridgeConfig configuration) {
        this.context = context.getApplicationContext();
        this.configuration = configuration;
    }

    public static PulseBridgeLibrary getInstance() {
        if (instance == null) {
            throw new IllegalStateException("PulseBridgeLibrary.init must be called from Application.onCreate()");
        }
        return instance;
    }

    public static void init(@NonNull Context context, @NonNull PulseBridgeConfig configuration) {
        if (instance == null) {
            instance = new PulseBridgeLibrary(context, configuration);
        }
    }

    public void processHealthAssessment(@NonNull Context context, @NonNull FHIRBundleModel model) {
        try {
            JSONObject bundle = FHIRBundleGenerator.getInstance().prepareFHIRBundle(model, configuration);
            if (bundle == null) {
                return;
            }
            String encodedBundle = Utils.encodeBase64(bundle);
            String launchUrl = Utils.attachBundleToUrl(Utils.getAppUrl(configuration), encodedBundle);
            Utils.openPWA(context, launchUrl);
        } catch (JSONException e) {
            Timber.e(e);
            throw new RuntimeException(this.context.getResources().getString(org.bluecodesystems.pulsebridge.R.string.bundle_exception_message));
        }
    }

    public Event createCarePlanEvent(@NonNull String encodedBundle, @NonNull FormTag formTag, @NonNull String entityId) {
        try {
            FHIREventModel eventModel = FHIRBundleParser.parseFHIRBundle(encodedBundle);
            eventModel.setDateCreated(Utils.getCurrentFormattedDate());
            eventModel.setEntityId(entityId);
            return PulseBridgeJsonFormUtils.createEvent(
                    eventModel,
                    formTag,
                    getContext().getString(org.bluecodesystems.pulsebridge.R.string.encounterType),
                    getContext().getString(org.bluecodesystems.pulsebridge.R.string.bindType));
        } catch (Exception e) {
            Timber.e(e);
            throw new RuntimeException(this.context.getResources().getString(org.bluecodesystems.pulsebridge.R.string.bundle_exception_message) + " : " + e.getLocalizedMessage());
        }
    }

    public String getPulseBridgePatientId(@NonNull String encodedBundle) {
        String decoded = Utils.decodeBase64(encodedBundle);
        return FHIRBundleParser.parsePulseBridgePatientId(decoded);
    }

    @Deprecated
    public String getThinkMDPatientId(@NonNull String encodedBundle) {
        return getPulseBridgePatientId(encodedBundle);
    }

    public PulseBridgeConfig getConfiguration() {
        return configuration;
    }

    @Deprecated
    public PulseBridgeConfig getThinkMDConfig() {
        return configuration;
    }

    public Context getContext() {
        return context;
    }
}
