package br.com.usp.ime.ombudsmanadm.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usp.ime.ombudsmanadm.model.Incident;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IncidentDAO extends SQLiteOpenHelper {
	
	private static final String DATABASE = "OMBUDSMAN_DB";
	private static final String TABLE_NAME = "INCIDENT";
	private static final int VERSION = 1;
	
	private static final String[] COLS = {"id", "user", "description", "localization", "latitude"
		, "longitude", "photo", "createdAt", "updatedAt"};
	
	public IncidentDAO(Context context) {
		super(context, DATABASE, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY" +
				", user INTEGER NOT NULL, description TEXT NOT NULL, localization TEXT NOT NULL" +
				", latitude REAL NOT NULL, longitude REAL NOT NULL, photo BLOB NOT NULL," +
				" createdAt TEXT NOT NULL, updatedAt TEXT NOT NULL);";
		
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
	
	//ANA PRECISA IMPLEMENTAR ISSO AQUI, USANDO O EXEMPLO DA SUELEN NAO TEM ERRO
	public void save() {
		
	}
	
	public List<Incident> getIncidents() {
		List<Incident> incidents = new ArrayList<Incident>();
		
		Cursor cs = getWritableDatabase().query(TABLE_NAME, COLS, null, null, null, null, null);
		while(cs.moveToNext()) {
			Incident incident = getIncidentFromCursor(cs);
			incidents.add(incident);
		}
		cs.close();
		
		return incidents;
	}

	private Incident getIncidentFromCursor(Cursor cs) {
		Incident incident = new Incident();
		incident.setId(0);
		incident.setUser(cs.getInt(1));
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