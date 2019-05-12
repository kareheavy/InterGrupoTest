package com.jhonjimenez.intergrupotest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

    private static ConnectionDetector instance = null;

    protected ConnectionDetector() {
    }

    public static ConnectionDetector getInstance() {
        if (instance == null) {
            instance = new ConnectionDetector();
        }
        return instance;
    }

    /**
     * Método que valida la conexion a datos móviles, evalua la conexion por WIFI y MOBILE, si alguna de las dos funciona indica que posee conexion
     *
     * @return true si posee conexion, false sino contiene
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ((checkConnectedByConnectionType(check.getNetworkInfo(ConnectivityManager.TYPE_MOBILE))
                || checkConnectedByConnectionType(check.getNetworkInfo(ConnectivityManager.TYPE_WIFI)))) {
            return true;
        } else if (checkDisconnectedByConnectionType(check.getNetworkInfo(ConnectivityManager.TYPE_MOBILE))
                || checkDisconnectedByConnectionType(check.getNetworkInfo(ConnectivityManager.TYPE_WIFI))) {
            return false;
        }

        return false;

    }

    /**
     * Dado un tipo de conexion, valida si esta desconectado o desconectandose
     *
     * @param networkInfo tipo de conexionvvasquezdose
     */
    private static boolean checkDisconnectedByConnectionType(NetworkInfo networkInfo) {

        if (networkInfo == null) {
            return true;
        } else {
            if (networkInfo.getState() == NetworkInfo.State.DISCONNECTED || networkInfo.getState() == NetworkInfo.State.DISCONNECTING) {
                return true;
            }
        }
        return false;
    }

    /**
     * Dado un tipo de conexion, valida si esta conectado o conectandose
     *
     * @param networkInfo tipo de conexion
     * @return true si esta conectado o conectandose
     */
    private static boolean checkConnectedByConnectionType(NetworkInfo networkInfo) {

        if (networkInfo == null) {
            return false;
        } else {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED || networkInfo.getState() == NetworkInfo.State.CONNECTING) {
                return true;
            }
        }
        return false;
    }

}