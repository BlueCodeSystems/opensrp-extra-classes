/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.smartregister.clientandeventmodel.Obs
 */
package org.bluecodesystems.pulsebridge.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.smartregister.clientandeventmodel.Obs;

public class FHIREventModel {
    private String type = "Event";
    private Map<String, String> identifiers;
    private String eventType = "pulseBridge_assessment";
    private String duration = "0";
    private String entityType = "ec_child";
    private String entityId;
    private Date eventDate;
    private String formSubmissionId;
    private Date dateCreated;
    private List<Obs> observations = new ArrayList<Obs>();

    public void addObservation(Obs obs) {
        if (obs == null) {
            return;
        }
        this.observations.add(obs);
    }

    public void addObservations(List<Obs> obsList) {
        if (obsList == null || obsList.size() == 0) {
            return;
        }
        this.observations.addAll(obsList);
    }

    public String getType() {
        return this.type;
    }

    public Map<String, String> getIdentifiers() {
        return this.identifiers;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public String getFormSubmissionId() {
        return this.formSubmissionId;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public List<Obs> getObservations() {
        return this.observations;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdentifiers(Map<String, String> identifiers) {
        this.identifiers = identifiers;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setFormSubmissionId(String formSubmissionId) {
        this.formSubmissionId = formSubmissionId;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setObservations(List<Obs> observations) {
        this.observations = observations;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FHIREventModel)) {
            return false;
        }
        FHIREventModel other = (FHIREventModel)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        Map<String, String> this$identifiers = this.getIdentifiers();
        Map<String, String> other$identifiers = other.getIdentifiers();
        if (this$identifiers == null ? other$identifiers != null : !((Object)this$identifiers).equals(other$identifiers)) {
            return false;
        }
        String this$eventType = this.getEventType();
        String other$eventType = other.getEventType();
        if (this$eventType == null ? other$eventType != null : !this$eventType.equals(other$eventType)) {
            return false;
        }
        String this$duration = this.getDuration();
        String other$duration = other.getDuration();
        if (this$duration == null ? other$duration != null : !this$duration.equals(other$duration)) {
            return false;
        }
        String this$entityType = this.getEntityType();
        String other$entityType = other.getEntityType();
        if (this$entityType == null ? other$entityType != null : !this$entityType.equals(other$entityType)) {
            return false;
        }
        String this$entityId = this.getEntityId();
        String other$entityId = other.getEntityId();
        if (this$entityId == null ? other$entityId != null : !this$entityId.equals(other$entityId)) {
            return false;
        }
        Date this$eventDate = this.getEventDate();
        Date other$eventDate = other.getEventDate();
        if (this$eventDate == null ? other$eventDate != null : !((Object)this$eventDate).equals(other$eventDate)) {
            return false;
        }
        String this$formSubmissionId = this.getFormSubmissionId();
        String other$formSubmissionId = other.getFormSubmissionId();
        if (this$formSubmissionId == null ? other$formSubmissionId != null : !this$formSubmissionId.equals(other$formSubmissionId)) {
            return false;
        }
        Date this$dateCreated = this.getDateCreated();
        Date other$dateCreated = other.getDateCreated();
        if (this$dateCreated == null ? other$dateCreated != null : !((Object)this$dateCreated).equals(other$dateCreated)) {
            return false;
        }
        List<Obs> this$observations = this.getObservations();
        List<Obs> other$observations = other.getObservations();
        return !(this$observations == null ? other$observations != null : !((Object)this$observations).equals(other$observations));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FHIREventModel;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Map<String, String> $identifiers = this.getIdentifiers();
        result = result * 59 + ($identifiers == null ? 43 : ((Object)$identifiers).hashCode());
        String $eventType = this.getEventType();
        result = result * 59 + ($eventType == null ? 43 : $eventType.hashCode());
        String $duration = this.getDuration();
        result = result * 59 + ($duration == null ? 43 : $duration.hashCode());
        String $entityType = this.getEntityType();
        result = result * 59 + ($entityType == null ? 43 : $entityType.hashCode());
        String $entityId = this.getEntityId();
        result = result * 59 + ($entityId == null ? 43 : $entityId.hashCode());
        Date $eventDate = this.getEventDate();
        result = result * 59 + ($eventDate == null ? 43 : ((Object)$eventDate).hashCode());
        String $formSubmissionId = this.getFormSubmissionId();
        result = result * 59 + ($formSubmissionId == null ? 43 : $formSubmissionId.hashCode());
        Date $dateCreated = this.getDateCreated();
        result = result * 59 + ($dateCreated == null ? 43 : ((Object)$dateCreated).hashCode());
        List<Obs> $observations = this.getObservations();
        result = result * 59 + ($observations == null ? 43 : ((Object)$observations).hashCode());
        return result;
    }

    public String toString() {
        return "FHIREventModel(type=" + this.getType() + ", identifiers=" + this.getIdentifiers() + ", eventType=" + this.getEventType() + ", duration=" + this.getDuration() + ", entityType=" + this.getEntityType() + ", entityId=" + this.getEntityId() + ", eventDate=" + this.getEventDate() + ", formSubmissionId=" + this.getFormSubmissionId() + ", dateCreated=" + this.getDateCreated() + ", observations=" + this.getObservations() + ")";
    }
}
