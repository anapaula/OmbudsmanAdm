package br.com.usp.ime.ombudsmanadm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkAvailability {
    public static TypeNetworkAnalyser check(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return new TypeNetworkAnalyser(info);
    }

    public static class TypeNetworkAnalyser {
        private static NetworkInfo info;

        private TypeNetworkAnalyser(NetworkInfo info) {
            TypeNetworkAnalyser.info = info;
        }

        public static boolean isWifiOn() {
            if (info != null && info.isConnected()) {
                return info.getType() == ConnectivityManager.TYPE_WIFI;
            } else {
                return false;
            }
        }

        public static boolean is3GOn() {
            if (info != null && info.isConnected()) {
                return info.getType() == ConnectivityManager.TYPE_MOBILE;
            } else {
                return false;
            }
        }
    }
}
