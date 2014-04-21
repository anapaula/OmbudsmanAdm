package br.com.usp.ime.ombudsmanadm.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.view.adapter.IncidentListAdapter;

public class SortedIncidentActivity extends Activity {
	
	List<Incident> incidents;
	ListView incidentListView;
	IncidentListAdapter adapter;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incident_list);
		
		setTitle("Incidentes");
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		incidentListView = (ListView) findViewById(R.id.incident_list);
		
		loadList();
		
		incidentListView.setTextFilterEnabled(true);
		
		incidentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent(SortedIncidentActivity.this, IncidentFormActivity.class);
				intent.putExtra("incident", (Incident) incidentListView.getItemAtPosition(position));
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadList();
	}
	
	private void loadList() {
		String incidentsId = (String)getIntent().getSerializableExtra("incidentsId");
		IncidentDAO dao = new IncidentSqLiteDAO(this);
		incidents = dao.getIncidentByIds(incidentsId);
		dao.close();
		
		adapter = new IncidentListAdapter(this, incidents);
		incidentListView.setAdapter(adapter);
	}
}
