package br.com.usp.ime.ombudsmanadm.model.dao;

import android.content.Context;

public abstract class BaseDAO {

    protected SQLiteBase database;

    public BaseDAO(Context context) {
        database = SQLiteBase.getInstance(context);
    }

    abstract String getDDL();
}
