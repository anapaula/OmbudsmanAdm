package br.com.usp.ime.ombudsmanadm.model.dao;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public interface IncidentDAO {
	
	public void onCreate(SQLiteDatabase database);
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion);
	public Incident getIncidentById(Long id);
	public List<Incident> getIncidents();
	public List<Incident> getIncidentsByKeyValue(String key);
	public void insert(Incident incident);
	public void update(Incident incident);
	public void delete(Incident incident);
	public long getLastIncidentId();
	public void close();
}
