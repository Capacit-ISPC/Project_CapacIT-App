package com.capacitapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USER_ID = "userId";

    private static final String IMG_USER_PERFIL = "imageUser";

    public static void saveUserIdToPreferences(Context context, int userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, userId);
        editor.commit();
    }

    public static int getLoggedUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID, -1); // -1 indica que no se encontró el ID
    }

    public static void saveUserProfileImage(Context context, String imageUri) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IMG_USER_PERFIL, imageUri);
        editor.apply();
    }

    public static String getUserProfileImage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(IMG_USER_PERFIL, null);
    }
}
