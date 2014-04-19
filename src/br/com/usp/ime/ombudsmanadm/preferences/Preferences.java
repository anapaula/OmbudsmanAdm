package br.com.usp.ime.ombudsmanadm.preferences;

import android.content.Context;

public class Preferences {

    public static final String LOGGED_USER = "LoggedUser";
    public static final String CATEGORIES_LOADED = "CategoriesLoaded";
    private Context context;
    SharedPreferencesManager manager;

    public Preferences(Context context) {
        this.context = context;
        manager = new SharedPreferencesManager(context);
    }

    public void setCategoriesLoaded(boolean loaded) {
        manager.setBoolean(CATEGORIES_LOADED, loaded);
    }

    public boolean getCategoriesLoaded() {
        return manager.getBoolean(CATEGORIES_LOADED);
    }

    public void setLoggedUser(boolean logged) {
        manager.setBoolean(LOGGED_USER, logged);
    }

    public boolean getLoggedUser() {
        return manager.getBoolean(LOGGED_USER);
    }
}
