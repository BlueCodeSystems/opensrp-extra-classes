package org.bluecodesystems.pulsebridge;

import androidx.annotation.Nullable;

/**
 * Configuration payload for launching the PulseBridge web workflow.
 */
public class PulseBridgeConfig {
    private String webAppPath;
    private String serviceEndpoint;

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    @Deprecated
    public String getThinkmdBaseUrl() {
        return webAppPath;
    }

    @Deprecated
    public void setThinkmdBaseUrl(String value) {
        this.webAppPath = value;
    }

    @Deprecated
    public String getThinkmdEndPoint() {
        return serviceEndpoint;
    }

    @Deprecated
    public void setThinkmdEndPoint(String value) {
        this.serviceEndpoint = value;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PulseBridgeConfig)) {
            return false;
        }
        PulseBridgeConfig other = (PulseBridgeConfig) obj;
        if (webAppPath != null ? !webAppPath.equals(other.webAppPath) : other.webAppPath != null) {
            return false;
        }
        return serviceEndpoint != null ? serviceEndpoint.equals(other.serviceEndpoint) : other.serviceEndpoint == null;
    }

    @Override
    public int hashCode() {
        int result = webAppPath != null ? webAppPath.hashCode() : 0;
        result = 31 * result + (serviceEndpoint != null ? serviceEndpoint.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PulseBridgeConfig(webAppPath=" + webAppPath + ", serviceEndpoint=" + serviceEndpoint + ')';
    }
}
