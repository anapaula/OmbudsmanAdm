package br.com.usp.ime.ombudsmanadm.model.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class IncidentSqLiteDAO extends SQLiteOpenHelper implements IncidentDAO {

	private static final String DATABASE = "OMBUDSMAN_DB";
	private static final String TABLE_NAME = "INCIDENT";
	private static final int VERSION = 1;

	private static final String[] COLS = { "id", "uspNumber", "description",
			"localization", "latitude", "longitude", "photo", "createdAt",
			"updatedAt" };

	public IncidentSqLiteDAO(Context context) {
		super(context, DATABASE, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE "
				+ TABLE_NAME
				+ "(id INTEGER PRIMARY KEY"
				+ ", uspNumber INTEGER NOT NULL, description TEXT NOT NULL, localization TEXT NOT NULL"
				+ ", latitude REAL NOT NULL, longitude REAL NOT NULL, photo BLOB NOT NULL,"
				+ " createdAt TEXT NOT NULL, updatedAt TEXT NOT NULL);";

		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	public List<Incident> getIncidents() {
		List<Incident> incidents = new ArrayList<Incident>();

		Cursor cs = getReadableDatabase().query(TABLE_NAME, COLS, null, null,
				null, null, null);

		while (cs.moveToNext()) {
			Incident incident = getIncidentFromCursor(cs);
			incidents.add(incident);
		}
		cs.close();

		return incidents;
	}
	
	public long getLastIncidentId() {
		Cursor cs = getReadableDatabase().query(TABLE_NAME, new String[] {"max(id)"}, null, null, null, null, null);
		if (cs.getPosition() != -1) {
			Incident incident = getIncidentFromCursor(cs);
			return incident.getId();
		} else {
			return 0L;
		}
	}

	public List<Incident> getIncidentsByKeyValue(String key) {
		List<Incident> incidents = new ArrayList<Incident>();

		Cursor cs = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME
								+ " WHERE description like '%?%'", new String[] { key });

		while (cs.moveToNext()) {
			Incident incident = getIncidentFromCursor(cs);
			incidents.add(incident);
		}
		
		cs.close();

		return incidents;
	}

	@Override
	public Incident getIncidentById(Long id) {
		Cursor c = getReadableDatabase().query(TABLE_NAME, COLS, "id=?", new String[] { id.toString() }, null, null, null);
		c.moveToFirst();
		Incident incident = getIncidentFromCursor(c);
		c.close();
		return incident;
	}

	@Override
	public void insert(Incident incident) {
		ContentValues values = incident.toContentValues();
		getWritableDatabase().insert(TABLE_NAME, null, values);
	}

	@Override
	public void update(Incident incident) {
		ContentValues values = incident.toContentValues();
		getWritableDatabase().update(TABLE_NAME, values, "id=?",
				new String[] { incident.getId().toString() });

	}

	@Override
	public void delete(Incident incident) {
		getWritableDatabase().delete(TABLE_NAME, "id=?",
				new String[] { incident.getId().toString() });
	}
	
	private Incident getIncidentFromCursor(Cursor cs) {
		Incident incident = new Incident();
		incident.setId(cs.getLong(0));
		incident.setUspNumber(cs.getLong(1));
		incident.setDescription(cs.getString(2));
		incident.setLocalization(cs.getString(3));
		incident.setLatitude(cs.getFloat(4));
		incident.setLongitude(cs.getFloat(5));
		incident.setPhoto(cs.getBlob(6));
		incident.setCreatedAt(cs.getString(7));
		incident.setUpdatedAt(cs.getString(8));

		return incident;
	}
}