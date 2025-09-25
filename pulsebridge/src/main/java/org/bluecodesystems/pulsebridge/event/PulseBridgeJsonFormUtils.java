package org.bluecodesystems.pulsebridge.event;

import androidx.annotation.NonNull;

import org.smartregister.clientandeventmodel.Event;
import org.smartregister.domain.tag.FormTag;
import org.bluecodesystems.pulsebridge.model.FHIREventModel;

public class PulseBridgeJsonFormUtils {
    private PulseBridgeJsonFormUtils() {
    }

    public static Event createEvent(@NonNull FHIREventModel fhirEventModel,
                                    @NonNull FormTag formTag,
                                    @NonNull String encounterType,
                                    @NonNull String bindType) {
        Event event = (Event) new Event()
                .withBaseEntityId(fhirEventModel.getEntityId())
                .withEventDate(fhirEventModel.getEventDate())
                .withEventType(encounterType)
                .withLocationId(formTag.locationId)
                .withProviderId(formTag.providerId)
                .withEntityType(bindType)
                .withFormSubmissionId(fhirEventModel.getFormSubmissionId())
                .withDateCreated(fhirEventModel.getDateCreated());
        event.setTeam(formTag.team);
        event.setTeamId(formTag.teamId);
        event.setType(fhirEventModel.getType());
        event.setIdentifiers(fhirEventModel.getIdentifiers());
        event.setClientApplicationVersion(formTag.appVersion);
        event.setClientApplicationVersionName(formTag.appVersionName);
        event.setClientDatabaseVersion(formTag.databaseVersion);
        event.setObs(fhirEventModel.getObservations());
        return event;
    }
}
