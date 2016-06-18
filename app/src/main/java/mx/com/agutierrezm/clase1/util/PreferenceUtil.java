package mx.com.agutierrezm.clase1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import mx.com.agutierrezm.clase1.model.user;

/**
 * Created by Alumno on 17/06/2016.
 */
public class PreferenceUtil {

    private static final String FILE_NAME = "clase1_pref";
    private  final SharedPreferences sp;
    public PreferenceUtil(Context context) {
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    public void saverUser ( user modelUser){

        sp.edit().putString("user",modelUser.userName).apply();
        sp.edit().putString("pass",modelUser.password).apply();

    }

    public user getUser(){
        String muser = sp.getString("user",null);
        String mpass = sp.getString("pass",null);
        if (TextUtils.isEmpty(muser) || TextUtils.isEmpty(mpass)){
            return null;
        }
        return new user(muser,mpass);
    }
}


