package org.smartregister.thinkmd.event;

import androidx.annotation.NonNull;

import org.smartregister.clientandeventmodel.Event;
import org.smartregister.domain.tag.FormTag;
import org.bluecodesystems.pulsebridge.event.PulseBridgeJsonFormUtils;
import org.bluecodesystems.pulsebridge.model.FHIREventModel;

@Deprecated
public final class ThinkMdJsonFormUtils {
    private ThinkMdJsonFormUtils() {
    }

    public static Event createEvent(@NonNull FHIREventModel fhirEventModel,
                                    @NonNull FormTag formTag,
                                    @NonNull String encounterType,
                                    @NonNull String bindType) {
        return PulseBridgeJsonFormUtils.createEvent(fhirEventModel, formTag, encounterType, bindType);
    }
}
