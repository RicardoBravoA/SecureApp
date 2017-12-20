package com.rba.secureapp.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Bravo on 14/12/2017.
 */

public class DeviceUtil {

    protected DeviceUtil(){}

    static {
        System.loadLibrary("native-lib");
    }

    public static boolean isSecure(Context context){

        if(context.getPackageName().compareTo(getPackageName()) != 0){
            return true;
        }

        String installer = context.getPackageManager().getInstallerPackageName(getPackageName());

        if(installer == null){
            return false;
        }

        if(installer.compareTo(getGooglePackageName()) != 0){
            return false;
        }

        return true;
    }

    public static boolean checkAppSignature(Context context) {
        List<String> list = new ArrayList<>();

        try{
            Signature[] sigs = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_SIGNATURES).signatures;
            for (Signature sig : sigs) {
                list.add(String.valueOf(sig.hashCode()));
            }
        }catch (PackageManager.NameNotFoundException e){
            Log.i("exception", e.toString());
        }

        boolean isSecure = false;

        for(String value : list){
            if(value.equals(getHash())){
                isSecure = true;
            }
        }

        return isSecure;
    }

    public static native String getPackageName();

    public static native String getGooglePackageName();

    public static native String getHash();

}