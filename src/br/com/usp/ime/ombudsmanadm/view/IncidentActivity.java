package br.com.usp.ime.ombudsmanadm.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.Incident;
import br.com.usp.ime.ombudsmanadm.task.LoadNewIncidentsTask;
import br.com.usp.ime.ombudsmanadm.view.adapter.IncidentListAdapter;

public class IncidentActivity extends Activity {
	
	List<Incident> incidents;
	ListView incidentListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.incident_list);
		
		setTitle("Incidentes");
		
		incidentListView = (ListView) findViewById(R.id.incident_list);
		
		loadList();
		
		incidentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "Item clicado" + position, 20).show();
			}
		});
		
		registerForContextMenu(incidentListView);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadList();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.incident_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_sync :
			new LoadNewIncidentsTask(this).execute();
			return false;
			
		default :
			return super.onOptionsItemSelected(item);
		}
	}

	private void loadList() {
//		IncidentDAO dao = new IncidentDAO(this);
//		incidents = dao.getIncidents();
//		dao.close();
		
		incidents = new ArrayList<>();
		Incident inc = new Incident();
		inc.setDescription("IME 1");
		incidents.add(inc);
		Incident inc2 = new Incident();
		inc2.setDescription("IME 2");
		incidents.add(inc2);
		
		IncidentListAdapter adapter = new IncidentListAdapter(this, incidents);
		incidentListView.setAdapter(adapter);
	}
}
