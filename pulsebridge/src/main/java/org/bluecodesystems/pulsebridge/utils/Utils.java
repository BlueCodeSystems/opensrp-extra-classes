package org.bluecodesystems.pulsebridge.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.widget.Toast;

import org.json.JSONObject;
import org.bluecodesystems.pulsebridge.PulseBridgeConfig;
import org.bluecodesystems.pulsebridge.R;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public final class Utils {
    public static final String PULSE_BRIDGE_DATE_FORMAT = "YYYY-MM-dd'T'HH:mm:ss.SSS+00:00";
    public static final String CARE_PLAN_DATE_FORMAT = "dd MMM YYYY";

    private Utils() {
    }

    public static String encodeBase64(JSONObject jsonObject) {
        byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(jsonObject.toString().getBytes());
        return new String(encoded);
    }

    public static String decodeBase64(String payload) {
        byte[] decoded = Base64.decode(payload, Base64.NO_WRAP);
        try {
            return new String(decoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Timber.e(e);
            return null;
        }
    }

    public static void openPWA(Context context, String url) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (ActivityNotFoundException e) {
            showShortToast(context, context.getString(R.string.chrome_not_installed));
        }
    }

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String attachBundleToUrl(String baseUrl, String encodedBundle) {
        return baseUrl + "?fhirBundle=" + encodedBundle;
    }

    @Deprecated
    public static String attatchBundleWithURL(String baseUrl, String encodedBundle) {
        return attachBundleToUrl(baseUrl, encodedBundle);
    }

    public static Date getCurrentFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(PULSE_BRIDGE_DATE_FORMAT, Locale.getDefault());
        String formatted = sdf.format(new Date());
        try {
            return sdf.parse(formatted);
        } catch (ParseException e) {
            Timber.e(e);
            return null;
        }
    }

    public static String getFormattedDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return sdf.format(date);
    }

    public static String getAppUrl(PulseBridgeConfig config) {
        return config.getServiceEndpoint() + config.getWebAppPath();
    }

    @Deprecated
    public static String getAppUrl(org.smartregister.thinkmd.ThinkMDConfig config) {
        return getAppUrl((PulseBridgeConfig) config);
    }

    @Deprecated
    public static final String THINK_MD_DATE_FORMAT = PULSE_BRIDGE_DATE_FORMAT;
}
