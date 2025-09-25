package org.bluecodesystems.pulsebridge.model;

import androidx.annotation.Nullable;

import java.util.Objects;

public class FHIRBundleModel {
    private String appName;
    private String rootPackageName;
    private String appVersion;
    private String endPointPackageName;
    private String gender;
    private String ageInDays;
    private String dob;
    private String pulseBridgeParticipantId;
    private String randomlyGeneratedId;
    private String appLanguage;
    private String displayLanguage;
    private String muacValueCode;
    private String muacValueDisplay;
    private String patientId;
    private String practitionerId;
    private String locationId;
    private String encounterId;
    private String userName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRootPackageName() {
        return rootPackageName;
    }

    public void setRootPackageName(String rootPackageName) {
        this.rootPackageName = rootPackageName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getEndPointPackageName() {
        return endPointPackageName;
    }

    public void setEndPointPackageName(String endPointPackageName) {
        this.endPointPackageName = endPointPackageName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeInDays() {
        return ageInDays;
    }

    public void setAgeInDays(String ageInDays) {
        this.ageInDays = ageInDays;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPulseBridgeParticipantId() {
        return pulseBridgeParticipantId;
    }

    public void setPulseBridgeParticipantId(String pulseBridgeParticipantId) {
        this.pulseBridgeParticipantId = pulseBridgeParticipantId;
    }

    public String getRandomlyGeneratedId() {
        return randomlyGeneratedId;
    }

    public void setRandomlyGeneratedId(String randomlyGeneratedId) {
        this.randomlyGeneratedId = randomlyGeneratedId;
    }

    public String getAppLanguage() {
        return appLanguage;
    }

    public void setAppLanguage(String appLanguage) {
        this.appLanguage = appLanguage;
    }

    public String getDisplayLanguage() {
        return displayLanguage;
    }

    public void setDisplayLanguage(String displayLanguage) {
        this.displayLanguage = displayLanguage;
    }

    public String getMuacValueCode() {
        return muacValueCode;
    }

    public void setMuacValueCode(String muacValueCode) {
        this.muacValueCode = muacValueCode;
    }

    public String getMuacValueDisplay() {
        return muacValueDisplay;
    }

    public void setMuacValueDisplay(String muacValueDisplay) {
        this.muacValueDisplay = muacValueDisplay;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Deprecated
    public String getUniqueIdGeneratedForThinkMD() {
        return pulseBridgeParticipantId;
    }

    @Deprecated
    public void setUniqueIdGeneratedForThinkMD(String value) {
        this.pulseBridgeParticipantId = value;
    }

    @Deprecated
    public String getMUACValueCode() {
        return muacValueCode;
    }

    @Deprecated
    public void setMUACValueCode(String value) {
        this.muacValueCode = value;
    }

    @Deprecated
    public String getMUACValueDisplay() {
        return muacValueDisplay;
    }

    @Deprecated
    public void setMUACValueDisplay(String value) {
        this.muacValueDisplay = value;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FHIRBundleModel)) {
            return false;
        }
        FHIRBundleModel that = (FHIRBundleModel) o;
        return Objects.equals(appName, that.appName)
                && Objects.equals(rootPackageName, that.rootPackageName)
                && Objects.equals(appVersion, that.appVersion)
                && Objects.equals(endPointPackageName, that.endPointPackageName)
                && Objects.equals(gender, that.gender)
                && Objects.equals(ageInDays, that.ageInDays)
                && Objects.equals(dob, that.dob)
                && Objects.equals(pulseBridgeParticipantId, that.pulseBridgeParticipantId)
                && Objects.equals(randomlyGeneratedId, that.randomlyGeneratedId)
                && Objects.equals(appLanguage, that.appLanguage)
                && Objects.equals(displayLanguage, that.displayLanguage)
                && Objects.equals(muacValueCode, that.muacValueCode)
                && Objects.equals(muacValueDisplay, that.muacValueDisplay)
                && Objects.equals(patientId, that.patientId)
                && Objects.equals(practitionerId, that.practitionerId)
                && Objects.equals(locationId, that.locationId)
                && Objects.equals(encounterId, that.encounterId)
                && Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appName, rootPackageName, appVersion, endPointPackageName, gender,
                ageInDays, dob, pulseBridgeParticipantId, randomlyGeneratedId, appLanguage,
                displayLanguage, muacValueCode, muacValueDisplay, patientId, practitionerId,
                locationId, encounterId, userName);
    }

    @Override
    public String toString() {
        return "FHIRBundleModel(appName=" + appName
                + ", rootPackageName=" + rootPackageName
                + ", appVersion=" + appVersion
                + ", endPointPackageName=" + endPointPackageName
                + ", gender=" + gender
                + ", ageInDays=" + ageInDays
                + ", dob=" + dob
                + ", pulseBridgeParticipantId=" + pulseBridgeParticipantId
                + ", randomlyGeneratedId=" + randomlyGeneratedId
                + ", appLanguage=" + appLanguage
                + ", displayLanguage=" + displayLanguage
                + ", MUACValueCode=" + muacValueCode
                + ", MUACValueDisplay=" + muacValueDisplay
                + ", patientId=" + patientId
                + ", practitionerId=" + practitionerId
                + ", locationId=" + locationId
                + ", encounterId=" + encounterId
                + ", userName=" + userName
                + ')';
    }
}
