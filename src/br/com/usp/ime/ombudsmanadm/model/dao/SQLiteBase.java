package br.com.usp.ime.ombudsmanadm.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteBase extends SQLiteOpenHelper {
    private static SQLiteBase instance;
    private List<BaseDAO> daos = new ArrayList<BaseDAO>();

    private final String DDL_CATEGORY = "create table category (id integer primary key, abbreviation text, name text)";
    private final String DDL_USER = "create table user (id integer primary key, name text, usp_number text, email text)";

    public static SQLiteBase getInstance(Context context) {
        if (instance == null) {
            instance = new SQLiteBase(context);
        }

        return instance;
    }

    private SQLiteBase(Context context) {
        super(context, "OuvidoriaBD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        for (BaseDAO dao : daos) {
//            db.execSQL(dao.getDDL());
//        }

        db.execSQL(DDL_CATEGORY);
        db.execSQL(DDL_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void addDao(BaseDAO dao) {
        daos.add(dao);
    }

}
