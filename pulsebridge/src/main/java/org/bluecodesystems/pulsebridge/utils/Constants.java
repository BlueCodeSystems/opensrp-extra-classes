package org.bluecodesystems.pulsebridge.utils;

public final class Constants {
    public static final String PULSE_BRIDGE_FHIR_BUNDLE = "pulsebridge_fhir_bundle";
    public static final String PULSE_BRIDGE_PWA_URL = "pulsebridge_pwa_url";

    @Deprecated
    public static final String THINKMD_FHIR_BUNDLE = PULSE_BRIDGE_FHIR_BUNDLE;
    @Deprecated
    public static final String THINKMD_PWA_URL = PULSE_BRIDGE_PWA_URL;

    private Constants() {
    }

    public static final class FHIREventConstants {
        public static final String RESOURCE_TYPE_MESSAGE_HEADER = "MessageHeader";
        public static final String RESOURCE_TYPE_ENCOUNTER = "Encounter";
        public static final String RESOURCE_TYPE_CONDITION = "Condition";
        public static final String RESOURCE_TYPE_OBSERVATION = "Observation";
        public static final String RESOURCE_TYPE_PATIENT = "Patient";
        public static final String RESOURCE_TYPE_PRACTITIONER = "Practitioner";
        public static final String RESOURCE_TYPE_MEDICATION_REQUEST = "MedicationRequest";

        private FHIREventConstants() {
        }
    }

    public static final class FHIRBundleConstants {
        public static final String ID = "id";
        public static final String RESOURCE_TYPE = "resourceType";
        public static final String EVENT_URI = "eventUri";
        public static final String NAME = "name";
        public static final String DESTINATION = "destination";
        public static final String SOFTWARE = "software";
        public static final String ENDPOINT = "endpoint";
        public static final String VERSION = "version";
        public static final String SOURCE = "source";
        public static final String LAST_UPDATED = "lastUpdated";
        public static final String IDENTIFIER = "identifier";
        public static final String TYPE = "type";
        public static final String CODING = "coding";
        public static final String SYSTEM = "system";
        public static final String DISPLAY = "display";
        public static final String CODE = "code";
        public static final String VALUE = "value";
        public static final String USE = "use";
        public static final String GENDER = "gender";
        public static final String ACTIVE = "active";
        public static final String VALUE_QUANTITY = "valueQuantity";
        public static final String UNIT = "unit";
        public static final String PERFORMER = "performer";
        public static final String REFERENCE = "reference";
        public static final String SUBJECT = "subject";
        public static final String STATUS = "status";
        public static final String EFFECTIVE_DATE_TIME = "effectiveDateTime";
        public static final String VALUE_CODEABLE_CONCEPT = "valueCodeableConcept";
        public static final String CLASS = "class";
        public static final String PARTICIPANT = "participant";
        public static final String INDIVIDUAL = "individual";
        public static final String LOCATION = "location";
        public static final String COMMUNICATION = "communication";
        public static final String RESOURCE = "resource";
        public static final String ENTRY = "entry";
        public static final String META = "meta";

        private FHIRBundleConstants() {
        }
    }
}
