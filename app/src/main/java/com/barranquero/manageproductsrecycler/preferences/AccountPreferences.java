package com.barranquero.manageproductsrecycler.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.barranquero.manageproductsrecycler.interfaces.IPreferences;

/**
 *
 */
public class AccountPreferences implements IPreferences {
    private static IPreferences accountPreferences;
    public static final String FILE = "com.barranquero.manageproductsrecycler_preferences";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private static Context context;

    private AccountPreferences() {

    }

    //Singleton
    public static IPreferences getInstance(Context c) {
        if (accountPreferences == null) {
            accountPreferences = new AccountPreferences();
            context = c;
        }
        return accountPreferences;
    }

    public static void putUser(Context c, String user) {
        getEditor(c).putString(USER, user).apply();
    }

    private static SharedPreferences.Editor getEditor(Context c) {
        return null;
    }
}
