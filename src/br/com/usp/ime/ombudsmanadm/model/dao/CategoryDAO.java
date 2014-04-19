package br.com.usp.ime.ombudsmanadm.model.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import br.com.usp.ime.ombudsmanadm.model.vo.Category;

public class CategoryDAO extends BaseDAO {
    private static final String TABLE_NAME = "category";
    private List<Category> all;

    public CategoryDAO(Context context) {
        super(context);
        //database.addDao(this);
    }

    @Override
    String getDDL() {
        return "create table " + TABLE_NAME + " (id integer primary key, abbreviation text, name text)";
    }

    public void insert(Category category) {
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("abbreviation", category.getAbbreviation());
        values.put("name", category.getName());

        database.getWritableDatabase().insert(TABLE_NAME, null, values);
        Log.i("Ouvidoria", category.getAbbreviation() + " inserido no BD.");
    }

    public void insert(List<Category> categories) {
        for (Category category : categories) {
            insert(category);
        }
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<Category>();

        Cursor c = database.getWritableDatabase().rawQuery("select * from " + TABLE_NAME, null);
        while (c.moveToNext()) {
            Category category = getCategoryFrom(c);
            categories.add(category);
        }
        c.close();

        return categories;
    }

    private Category getCategoryFrom(Cursor c) {
        Category category = new Category();

        category.setAbbreviation(c.getString(c.getColumnIndex("abbreviation")));
        category.setId(c.getLong(c.getColumnIndex("id")));
        category.setName(c.getString(c.getColumnIndex("name")));

        return category;
    }
}
