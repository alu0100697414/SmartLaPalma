package com.user.jose.smartlapalma.Controllers.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import com.user.jose.smartlapalma.Models.ApplicationLanguage;
import com.user.jose.smartlapalma.Models.Months;
import com.user.jose.smartlapalma.R;

/**
 * Created by Jose on 18/04/2018.
 */

public class CustomUtils {

    /***
     * This function checks if user has network connection
     * @param context
     * @return If network is available
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /***
     * Check device languague and return application languague
     * @param language
     * @return Application language
     */
    public static String getApplicationLanguage(String language) {
        switch (language){
            case ApplicationLanguage.ENGLISH:
                return language;
            case ApplicationLanguage.SPANISH:
                return language;
            default:
                return ApplicationLanguage.SPANISH;
        }
    }

    /***
     * This function returns the value in dp for what we want to pass to the layout by code.
     * @param activity
     * @param pixel Value of dp that you want
     * @return Value in dp
     */
    public static int getDpValues(Activity activity, float pixel){

        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        float dp = pixel;
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }

    /****
     * Convert latitude of Open Data La Palma in latitude for Google Maps.
     * @param latitude
     * @return
     */
    public static double getLat(String latitude){

        // Detele letter
        latitude = latitude.substring(0, latitude.length() - 1);

        // Divide in degrees, minutes and seconds
        String[] temp = latitude.split(" ");

        double degrees = Double.valueOf(temp[0]);
        double minutes = Double.valueOf(temp[1]);
        double seconds = Double.valueOf(temp[2].replaceFirst(",", "."));

        return Math.signum(degrees) * (Math.abs(degrees) + (minutes / 60.0) + (seconds / 3600.0));
    }

    /****
     * Convert longitude of Open Data La Palma in longitude for Google Maps.
     * @param longitude
     * @return
     */
    public static double getLng(String longitude){

        // Detele letter
        longitude = longitude.substring(0, longitude.length() - 1);

        // Divide in degrees, minutes and seconds
        String[] temp = longitude.split(" ");

        double degrees = Double.valueOf(temp[0].replaceFirst("0", "-"));
        double minutes = Double.valueOf(temp[1]);
        double seconds = Double.valueOf(temp[2].replaceFirst(",", "."));

        return Math.signum(degrees) * (Math.abs(degrees) + (minutes / 60.0) + (seconds / 3600.0));
    }

    /****
     * This function return the formmatted month from a number
     * @param context Application context
     * @param number Number of month
     * @return
     */
    public static String getMonthFromNumber(Context context, String number){
        switch (number){
            case Months.JANUARY:
                return context.getString(R.string.january);
            case Months.FEBRUARY:
                return context.getString(R.string.february);
            case Months.MARCH:
                return context.getString(R.string.march);
            case Months.APRIL:
                return context.getString(R.string.april);
            case Months.MAY:
                return context.getString(R.string.may);
            case Months.JUNE:
                return context.getString(R.string.june);
            case Months.JULY:
                return context.getString(R.string.july);
            case Months.AUGUST:
                return context.getString(R.string.august);
            case Months.SEPTEMBER:
                return context.getString(R.string.september);
            case Months.OCTOBER:
                return context.getString(R.string.october);
            case Months.NOVEMBER:
                return context.getString(R.string.november);
            case Months.DECEMBER:
                return context.getString(R.string.december);
            default:
                return "";
        }
    }

    /****
     * This function return the formmatted sky state description from a number
     * @param context Application context
     * @param number Number of sky state
     * @return
     */
    public static String getSkyStateDescriptionFromInteger(Context context, String number){
        switch (number){
            case "12":
                return context.getString(R.string.weather_description_12);
            case "43":
                return context.getString(R.string.weather_description_43);
            case "14":
                return context.getString(R.string.weather_description_14);
            case "13":
                return context.getString(R.string.weather_description_13);
            case "44":
                return context.getString(R.string.weather_description_44);
            case "45":
                return context.getString(R.string.weather_description_45);
            case "15":
                return context.getString(R.string.weather_description_15);
            case "24":
                return context.getString(R.string.weather_description_24);
            case "23":
                return context.getString(R.string.weather_description_23);
            case "26":
                return context.getString(R.string.weather_description_26);
            case "25":
                return context.getString(R.string.weather_description_25);
            case "11":
                return context.getString(R.string.weather_description_11);
            case "16":
                return context.getString(R.string.weather_description_16);
            case "17":
                return context.getString(R.string.weather_description_17);
            case "33":
                return context.getString(R.string.weather_description_33);
            case "34":
                return context.getString(R.string.weather_description_34);
            case "35":
                return context.getString(R.string.weather_description_35);
            case "36":
                return context.getString(R.string.weather_description_36);
            case "27":
                return context.getString(R.string.weather_description_27);
            case "53":
                return context.getString(R.string.weather_description_53);
            case "granizo":
                return context.getString(R.string.weather_description_granizo);
            case "bruma":
                return context.getString(R.string.weather_description_bruma);
            case "niebla":
                return context.getString(R.string.weather_description_niebla);
            case "calima":
                return context.getString(R.string.weather_description_calima);
            default:
                return "";
        }
    }
}
