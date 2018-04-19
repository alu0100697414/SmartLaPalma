package com.example.jose.smartlapalma.Controllers.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

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
}
