package br.com.usp.ime.ombudsmanadm.preferences;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferencesManager {
    private static final String PREFERENCE_NAME = "OuvidoriaPreferences";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public int getInt(String item) {
        return preferences.getInt(item, 0);
    }

    public void setInt(String item, int valor) {
        editor.putInt(item, valor);
        editor.commit();
    }

    public float getFloat(String item) {
        return preferences.getFloat(item, 0.0f);
    }

    public void setFloat(String item, float valor) {
        editor.putFloat(item, valor);
        editor.commit();
    }

    public String getString(String item) {
        return preferences.getString(item, null);
    }

    public void setString(String item, String valor) {
        editor.putString(item, valor);
        editor.commit();
    }

    public long getLong(String item) {
        return preferences.getLong(item, 0);
    }

    public void setLong(String item, long valor) {
        editor.putLong(item, valor);
        editor.commit();
    }

    public boolean getBoolean(String item) {
        return preferences.getBoolean(item, false);
    }

    public void setBoolean(String item, boolean valor) {
        editor.putBoolean(item, valor);
        editor.commit();
    }
}
