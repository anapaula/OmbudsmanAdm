package br.com.usp.ime.ombudsmanadm.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.view.adapter.IncidentListAdapter;

public class SortedIncidentActivity extends Activity {
	
	private static final int SECONDS = 1;
	List<Incident> incidents;
	ListView incidentListView;
	IncidentListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incident_list);
		
		setTitle("Incidentes");
		
		incidentListView = (ListView) findViewById(R.id.incident_list);
		
		loadList();
		
		incidentListView.setTextFilterEnabled(true);
		
		incidentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "Item clicado " + position, SECONDS).show();
				
				Intent intent = new Intent(SortedIncidentActivity.this, IncidentFormActivity.class);
				intent.putExtra("incident", (Incident) incidentListView.getItemAtPosition(position));
				startActivity(intent);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void loadList() {
		incidents = (ArrayList<Incident>)getIntent().getSerializableExtra("incidents");
		
		adapter = new IncidentListAdapter(this, incidents);
		incidentListView.setAdapter(adapter);
	}
}
