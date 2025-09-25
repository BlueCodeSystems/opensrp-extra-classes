/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.StringUtils
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package org.bluecodesystems.pulsebridge.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.bluecodesystems.pulsebridge.PulseBridgeConfig;
import org.bluecodesystems.pulsebridge.model.FHIRBundleModel;

public class FHIRBundleGenerator {
    public static final String PATIENT_IDENTIFIER = "https://fhir.smartregister.org/pulsebridge_id";
    public static final String ENCOUNTER_IDENTIFIER = "https://fhir.smartregister.org/encounterUuid";
    public static final String MESSAGE_EVENT_URL = "http://hl7.org/fhir/ValueSet/message-events";
    public static final String PRACTITIONER_COMMUNICATION_CODING_SYSTEM = "urn:ietf:bcp:47";
    private static final String BUNDLE_RESOURCE_TYPE = "Bundle";
    private static final String ENCOUNTER_RESOURCE_TYPE = "Encounter";
    private static final String USUAL_IDENTIFIER = "usual";
    private static final String COLLECTION_TYPE = "collection";
    private static final String PRACTITIONER_TYPE = "Practitioner";
    private static final String IN_PROGRESS = "in-progress";
    private static final String HOME_HEALTH_DISPLAY = "home health";
    private static final String HEALTH_CODE = "HH";
    private static final String ENCOUNTER_CLASS_CODE = "http://terminology.hl7.org/CodeSystem/v3-ActCode";
    private static final String OBSERVATION_RESOURCE_TYPE = "Observation";
    private static final String PRELIMINARY_STATUS = "preliminary";
    private static final String DISPLAY_AGE = "age";
    private static final String CODE_AGE = "424144002";
    private static final String AGE_SYSTEM_VALUE = "http://snomed.info/sct";
    private static final String UNIT_DAYS = "days";
    private static final String PATIENT = "Patient";
    private static final String USUAL_CODE = "usual";
    private static final String DESTINATION_NAME = "PulseBridge";
    private static final String MESSAGE_HEADER_RESOURCE_TYPE = "MessageHeader";
    private static final String PATIENT_CODE = "MR";
    private static final String PATIENT_SYSTEM = "http://terminology.hl7.org/CodeSystem/v2-0203";
    private static final String OBSERVATION_SYSTEM = "http://hl7.org/fhir/metric-color";
    private static final String OBSERVATION_CODE = "284473002";
    private static final String OBSERVATION_DISPLAY = "Mid upper arm circumference (MUAC)";
    private static final String OBSERVATION_CODE_SYSTEM = "http://snomed.info/sct";
    private static final String TEXT = "text";
    private static FHIRBundleGenerator instance;

    private FHIRBundleGenerator() {
    }

    public static synchronized FHIRBundleGenerator getInstance() {
        if (instance == null) {
            instance = new FHIRBundleGenerator();
        }
        return instance;
    }

    public JSONObject prepareFHIRBundle(FHIRBundleModel model, PulseBridgeConfig config) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resourceType", (Object)BUNDLE_RESOURCE_TYPE);
        jsonObject.put("id", (Object)model.getRandomlyGeneratedId());
        jsonObject.put("type", (Object)COLLECTION_TYPE);
        jsonObject.put("meta", (Object)this.prepareFHIRMetaObject());
        jsonObject.put("entry", (Object)this.prepareFHIREntryArray(model, config));
        return jsonObject;
    }

    private JSONArray prepareFHIREntryArray(FHIRBundleModel model, PulseBridgeConfig config) throws JSONException {
        JSONArray entryArray = new JSONArray();
        JSONObject messageHeaderObject = new JSONObject();
        messageHeaderObject.put("resource", (Object)this.prepareFHIRMessageHeader(model, config));
        entryArray.put((Object)messageHeaderObject);
        JSONObject patientObject = new JSONObject();
        patientObject.put("resource", (Object)this.prepareFHIRPatient(model));
        entryArray.put((Object)patientObject);
        JSONObject observationObject = new JSONObject();
        observationObject.put("resource", (Object)this.prepareFHIRObservation(model));
        entryArray.put((Object)observationObject);
        JSONObject muacValue = new JSONObject();
        if (StringUtils.isNotEmpty((CharSequence)model.getMUACValueCode())) {
            muacValue.put("resource", (Object)this.prepareMUACObservation(model));
            entryArray.put((Object)muacValue);
        }
        JSONObject encounterObject = new JSONObject();
        encounterObject.put("resource", (Object)this.prepareFHIREncounter(model, config));
        entryArray.put((Object)encounterObject);
        JSONObject practitionerObject = new JSONObject();
        practitionerObject.put("resource", (Object)this.prepareFHIRPractitioner(model));
        entryArray.put((Object)practitionerObject);
        return entryArray;
    }

    private JSONObject prepareFHIRPractitioner(FHIRBundleModel model) throws JSONException {
        JSONObject practitionerObject = new JSONObject();
        practitionerObject.put("resourceType", (Object)PRACTITIONER_TYPE);
        practitionerObject.put("id", (Object)model.getUserName());
        practitionerObject.put("active", true);
        JSONObject communicationObject = new JSONObject();
        JSONArray codingArray = new JSONArray();
        JSONObject codingObject = new JSONObject();
        codingObject.put("code", (Object)model.getAppLanguage());
        codingObject.put("display", (Object)model.getDisplayLanguage());
        codingObject.put("system", (Object)PRACTITIONER_COMMUNICATION_CODING_SYSTEM);
        codingArray.put((Object)codingObject);
        communicationObject.put("coding", (Object)codingArray);
        practitionerObject.put("communication", (Object)communicationObject);
        return practitionerObject;
    }

    private JSONObject prepareFHIREncounter(FHIRBundleModel model, PulseBridgeConfig config) throws JSONException {
        JSONObject encounterObject = new JSONObject();
        encounterObject.put("resourceType", (Object)ENCOUNTER_RESOURCE_TYPE);
        encounterObject.put("id", (Object)model.getRandomlyGeneratedId());
        encounterObject.put("status", (Object)IN_PROGRESS);
        JSONArray identifierArray = new JSONArray();
        JSONObject identifierObject = new JSONObject();
        identifierObject.put("system", (Object)ENCOUNTER_IDENTIFIER);
        identifierObject.put("use", (Object)"usual");
        identifierObject.put("value", (Object)model.getEncounterId());
        identifierArray.put((Object)identifierObject);
        encounterObject.put("identifier", (Object)identifierArray);
        JSONArray locationArray = new JSONArray();
        JSONObject locationObject = new JSONObject();
        locationObject.put("location", (Object)model.getLocationId());
        locationArray.put((Object)locationObject);
        encounterObject.put("location", (Object)locationArray);
        JSONArray participantArray = new JSONArray();
        JSONObject participantObject = new JSONObject();
        participantObject.put("individual", (Object)("Practitioner/" + model.getPractitionerId()));
        participantArray.put((Object)participantObject);
        encounterObject.put("participant", (Object)participantArray);
        JSONObject subjectObject = new JSONObject();
        subjectObject.put("reference", (Object)("Patient/" + model.getPatientId()));
        encounterObject.put("subject", (Object)subjectObject);
        JSONObject classObject = new JSONObject();
        classObject.put("system", (Object)ENCOUNTER_CLASS_CODE);
        classObject.put("code", (Object)HEALTH_CODE);
        classObject.put("display", (Object)HOME_HEALTH_DISPLAY);
        encounterObject.put("class", (Object)classObject);
        return encounterObject;
    }

    private JSONObject prepareMUACObservation(FHIRBundleModel model) throws JSONException {
        JSONObject observationObject = new JSONObject();
        observationObject.put("resourceType", (Object)OBSERVATION_RESOURCE_TYPE);
        observationObject.put("id", (Object)model.getRandomlyGeneratedId());
        observationObject.put("effectiveDateTime", (Object)this.getCurrentFormattedDate());
        observationObject.put("status", (Object)PRELIMINARY_STATUS);
        JSONObject subjectObject = new JSONObject();
        subjectObject.put("reference", (Object)("Patient/" + model.getPatientId()));
        observationObject.put("subject", (Object)subjectObject);
        JSONArray performerArray = new JSONArray();
        JSONObject referenceObject = new JSONObject();
        referenceObject.put("reference", (Object)("Practitioner/" + model.getPractitionerId()));
        performerArray.put((Object)referenceObject);
        observationObject.put("performer", (Object)performerArray);
        JSONObject codeObject = new JSONObject();
        JSONArray codingArray = new JSONArray();
        JSONObject codingObject = new JSONObject();
        codingObject.put("code", (Object)OBSERVATION_CODE);
        codingObject.put("display", (Object)OBSERVATION_DISPLAY);
        codingObject.put("system", (Object)"http://snomed.info/sct");
        codingArray.put((Object)codingObject);
        codeObject.put("coding", (Object)codingArray);
        observationObject.put("code", (Object)codeObject);
        JSONObject valueCodeableConceptObject = new JSONObject();
        valueCodeableConceptObject.put(TEXT, (Object)model.getMUACValueCode());
        JSONArray conceptCodingArray = new JSONArray();
        JSONObject conceptCodingObject = new JSONObject();
        conceptCodingObject.put("code", (Object)model.getMUACValueCode());
        conceptCodingObject.put("display", (Object)model.getMUACValueDisplay());
        conceptCodingObject.put("system", (Object)OBSERVATION_SYSTEM);
        conceptCodingArray.put((Object)conceptCodingObject);
        valueCodeableConceptObject.put("coding", (Object)conceptCodingArray);
        observationObject.put("valueCodeableConcept", (Object)valueCodeableConceptObject);
        return observationObject;
    }

    private JSONObject prepareFHIRObservation(FHIRBundleModel model) throws JSONException {
        JSONObject observationObject = new JSONObject();
        observationObject.put("resourceType", (Object)OBSERVATION_RESOURCE_TYPE);
        observationObject.put("id", (Object)model.getRandomlyGeneratedId());
        observationObject.put("effectiveDateTime", (Object)this.getCurrentFormattedDate());
        observationObject.put("status", (Object)PRELIMINARY_STATUS);
        JSONObject subjectObject = new JSONObject();
        subjectObject.put("reference", (Object)("Patient/" + model.getPatientId()));
        observationObject.put("subject", (Object)subjectObject);
        JSONArray performerArray = new JSONArray();
        JSONObject referenceObject = new JSONObject();
        referenceObject.put("reference", (Object)("Practitioner/" + model.getPractitionerId()));
        performerArray.put((Object)referenceObject);
        observationObject.put("performer", (Object)performerArray);
        JSONObject codeObject = new JSONObject();
        JSONArray codingArray = new JSONArray();
        JSONObject codingObject = new JSONObject();
        codingObject.put("code", (Object)CODE_AGE);
        codingObject.put("display", (Object)DISPLAY_AGE);
        codingObject.put("system", (Object)"http://snomed.info/sct");
        codingArray.put((Object)codingObject);
        codeObject.put("coding", (Object)codingArray);
        observationObject.put("code", (Object)codeObject);
        JSONObject valueQuantityObject = new JSONObject();
        valueQuantityObject.put("unit", (Object)UNIT_DAYS);
        valueQuantityObject.put("value", (Object)model.getAgeInDays());
        observationObject.put("valueQuantity", (Object)valueQuantityObject);
        return observationObject;
    }

    private JSONObject prepareFHIRPatient(FHIRBundleModel model) throws JSONException {
        JSONObject patientObject = new JSONObject();
        patientObject.put("resourceType", (Object)PATIENT);
        patientObject.put("id", (Object)model.getPulseBridgeParticipantId());
        patientObject.put("active", true);
        patientObject.put("gender", (Object)model.getGender());
        JSONArray identifierArray = new JSONArray();
        JSONObject identifierObject = new JSONObject();
        identifierObject.put("system", (Object)PATIENT_IDENTIFIER);
        identifierObject.put("use", (Object)"usual");
        identifierObject.put("value", (Object)model.getPulseBridgeParticipantId());
        JSONObject typeObject = new JSONObject();
        JSONArray codingArray = new JSONArray();
        JSONObject codingObject = new JSONObject();
        codingObject.put("code", (Object)PATIENT_CODE);
        codingObject.put("display", null);
        codingObject.put("system", (Object)PATIENT_SYSTEM);
        codingArray.put((Object)codingObject);
        typeObject.put("coding", (Object)codingArray);
        identifierObject.put("type", (Object)typeObject);
        identifierArray.put((Object)identifierObject);
        patientObject.put("identifier", (Object)identifierArray);
        return patientObject;
    }

    private JSONObject prepareFHIRMessageHeader(FHIRBundleModel model, PulseBridgeConfig config) throws JSONException {
        JSONObject messageHeaderObject = new JSONObject();
        messageHeaderObject.put("resourceType", (Object)MESSAGE_HEADER_RESOURCE_TYPE);
        messageHeaderObject.put("id", (Object)model.getRandomlyGeneratedId());
        messageHeaderObject.put("eventUri", (Object)MESSAGE_EVENT_URL);
        JSONArray destinationArray = new JSONArray();
        JSONObject destinationObject = new JSONObject();
        destinationObject.put("name", (Object)DESTINATION_NAME);
        destinationObject.put("endpoint", (Object)config.getServiceEndpoint());
        destinationArray.put((Object)destinationObject);
        messageHeaderObject.put("destination", (Object)destinationArray);
        JSONObject sourceObject = new JSONObject();
        sourceObject.put("name", (Object)model.getAppName());
        sourceObject.put("software", (Object)model.getRootPackageName());
        sourceObject.put("version", (Object)model.getAppVersion());
        sourceObject.put("endpoint", (Object)model.getEndPointPackageName());
        messageHeaderObject.put("source", (Object)sourceObject);
        return messageHeaderObject;
    }

    private JSONObject prepareFHIRMetaObject() throws JSONException {
        JSONObject metaObject = new JSONObject();
        metaObject.put("lastUpdated", (Object)this.getCurrentFormattedDate());
        return metaObject;
    }

    String getCurrentFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS+00:00", Locale.getDefault());
        return sdf.format(new Date());
    }
}
