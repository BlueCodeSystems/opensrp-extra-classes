/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ca.uhn.fhir.context.FhirContext
 *  ca.uhn.fhir.parser.IParser
 *  org.hl7.fhir.r4.model.Bundle
 *  org.hl7.fhir.r4.model.Bundle$BundleEntryComponent
 *  org.hl7.fhir.r4.model.Coding
 *  org.hl7.fhir.r4.model.Condition
 *  org.hl7.fhir.r4.model.Identifier
 *  org.hl7.fhir.r4.model.Medication
 *  org.hl7.fhir.r4.model.MedicationRequest
 *  org.hl7.fhir.r4.model.Observation
 *  org.hl7.fhir.r4.model.Patient
 *  org.hl7.fhir.r4.model.Resource
 *  org.smartregister.clientandeventmodel.Obs
 *  timber.log.Timber
 */
package org.bluecodesystems.pulsebridge.utils;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Medication;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.smartregister.clientandeventmodel.Obs;
import org.bluecodesystems.pulsebridge.model.FHIREventModel;
import timber.log.Timber;

public class FHIRBundleParser {
    static FhirContext ctx = null;

    public static FHIREventModel parseFHIRBundle(String encodedBundle) {
        try {
            String decodeBase64 = Utils.decodeBase64(encodedBundle);
            FHIREventModel fhirEventModel = new FHIREventModel();
            if (ctx == null) {
                ctx = FhirContext.forR4();
            }
            IParser parser = ctx.newJsonParser();
            Bundle bundle = (Bundle)parser.parseResource(Bundle.class, decodeBase64);
            fhirEventModel.setFormSubmissionId(bundle.getId());
            fhirEventModel.addObservation(FHIRBundleParser.getObs("pulsebridge_fhir_bundle", Collections.singletonList(encodedBundle), "pulsebridge_fhir_bundle", Collections.singletonList(encodedBundle)));
            for (Bundle.BundleEntryComponent item : bundle.getEntry()) {
                switch (item.getResource().getResourceType().toString()) {
                    case "Condition": {
                        fhirEventModel.addObservations(FHIRBundleParser.parseAssessments((Condition)item.getResource()));
                        fhirEventModel.setEventDate(FHIRBundleParser.parseEventDate(item.getResource()));
                        break;
                    }
                    case "Observation": {
                        fhirEventModel.addObservation(FHIRBundleParser.parseObservation((Observation)item.getResource()));
                        break;
                    }
                    case "MedicationRequest": {
                        fhirEventModel.addObservation(FHIRBundleParser.parseMedicationRequest((MedicationRequest)item.getResource()));
                        break;
                    }
                }
            }
            return fhirEventModel;
        }
        catch (Exception e) {
            Timber.e((Throwable)e);
            return null;
        }
    }

    private static Date parseEventDate(Resource resource) {
        return ((Condition)resource).getRecordedDate();
    }

    private static List<Obs> parseAssessments(Condition condition) {
        try {
            ArrayList<Obs> obsList = new ArrayList<Obs>();
            String display = ((Coding)condition.getCode().getCoding().get(0)).getDisplay();
            obsList.add(FHIRBundleParser.getObs("generatedDiv", Collections.singletonList(condition.getText().getDiv().toString()), "generatedDiv", null));
            obsList.add(FHIRBundleParser.getObs(display, Collections.singletonList(((Coding)condition.getSeverity().getCoding().get(0)).getDisplay()), display, Collections.singletonList(((Coding)condition.getSeverity().getCoding().get(0)).getDisplay())));
            obsList.add(FHIRBundleParser.getObs(display, Collections.singletonList(((Coding)condition.getClinicalStatus().getCoding().get(0)).getCode()), display, Collections.singletonList(((Coding)condition.getClinicalStatus().getCoding().get(0)).getCode())));
            obsList.add(FHIRBundleParser.getObs(display, Collections.singletonList(((Coding)condition.getVerificationStatus().getCoding().get(0)).getCode()), display, Collections.singletonList(((Coding)condition.getVerificationStatus().getCoding().get(0)).getCode())));
            obsList.add(FHIRBundleParser.getObs(display, Collections.singletonList(condition.getOnset().primitiveValue()), display, Collections.singletonList(condition.getOnset().primitiveValue())));
            obsList.add(FHIRBundleParser.getObs(display, Collections.singletonList(condition.getRecordedDateElement().primitiveValue()), display, Collections.singletonList(condition.getRecordedDateElement().primitiveValue())));
            String formattedDate = Utils.getFormattedDate(condition.getRecordedDate(), "dd MMM YYYY");
            obsList.add(FHIRBundleParser.getObs("carePlanDate", Collections.singletonList(formattedDate), "carePlanDate", Collections.singletonList(formattedDate)));
            return obsList;
        }
        catch (Exception e) {
            Timber.e((Throwable)e);
            return null;
        }
    }

    private static Obs parseMedicationRequest(MedicationRequest medicationRequest) {
        try {
            ArrayList<Object> codeValues = new ArrayList<Object>();
            ArrayList<Object> displayValues = new ArrayList<Object>();
            Medication medication = (Medication)medicationRequest.getContained().get(0);
            List<Coding> codingList = medication.getCode().getCoding();
            for (Coding coding : codingList) {
                codeValues.add(coding.getCode());
            }
            for (Coding coding : codingList) {
                displayValues.add(coding.getDisplay());
            }
            return FHIRBundleParser.getObs("medicationRequest", codeValues, "medicationRequest", displayValues);
        }
        catch (Exception e) {
            Timber.e((Throwable)e);
            return null;
        }
    }

    private static Obs parseObservation(Observation observation) {
        try {
            ArrayList<Object> values = new ArrayList<Object>();
            String fieldDisplay = ((Coding)observation.getCode().getCoding().get(0)).getDisplay();
            if (observation.hasValueCodeableConcept()) {
                values.add(((Coding)observation.getValueCodeableConcept().getCoding().get(0)).getDisplay());
            } else if (observation.hasValueQuantity()) {
                values.add(observation.getValueQuantity().getValue());
            }
            return FHIRBundleParser.getObs(fieldDisplay, values, fieldDisplay, values);
        }
        catch (Exception e) {
            Timber.e((Throwable)e);
            return null;
        }
    }

    private static Obs getObs(String fieldCode, List<Object> values, String formSubmissionField, List<Object> humanReadableValues) {
        Obs obs = new Obs();
        obs.setFieldType("formsubmissionField");
        obs.setFieldDataType("text");
        obs.setFieldCode(fieldCode);
        obs.setParentCode("");
        obs.setValues(values);
        obs.setFormSubmissionField(formSubmissionField);
        obs.setHumanReadableValues(humanReadableValues);
        return obs;
    }

    public static String parsePulseBridgePatientId(String input) {
        try {
            if (ctx == null) {
                ctx = FhirContext.forR4();
            }
            IParser parser = ctx.newJsonParser();
            Bundle bundle = (Bundle)parser.parseResource(Bundle.class, input);
            for (Bundle.BundleEntryComponent item : bundle.getEntry()) {
                if (!"Patient".equals(item.getResource().getResourceType().toString())) continue;
                Patient patient = (Patient)item.getResource();
                List<Identifier> identifiers = patient.getIdentifier();
                for (Identifier identifier : identifiers) {
                    if (!identifier.getSystem().equals("https://fhir.smartregister.org/pulsebridge_id")) continue;
                    return identifier.getValue();
                }
            }
        }
        catch (Exception e) {
            Timber.e((Throwable)e);
        }
        return "";
    }

    @Deprecated
    public static String parseThinkMDPatientId(String input) {
        return parsePulseBridgePatientId(input);
    }
}
