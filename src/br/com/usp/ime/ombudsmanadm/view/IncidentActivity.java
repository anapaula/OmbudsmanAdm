package br.com.usp.ime.ombudsmanadm.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.task.LoadNewIncidentsTask;
import br.com.usp.ime.ombudsmanadm.view.adapter.IncidentListAdapter;

public class IncidentActivity extends Activity {
	
	private static final int SECONDS = 10;
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
				Toast.makeText(getApplicationContext(), "Item clicado " + position, SECONDS).show();
				
				Intent intent = new Intent(IncidentActivity.this, IncidentFormActivity.class);
				intent.putExtra("incident", (Incident) incidentListView.getItemAtPosition(position));
				startActivity(intent);
			}
		});
		
		registerForContextMenu(incidentListView);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadList();
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.incident_menu, menu);
		
		SearchManager manager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
		SearchView view = (SearchView) menu.findItem(R.id.menu_search).getActionView();
		view.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d(IncidentActivity.class.getSimpleName(), "item selecionado " + item.getItemId());
		switch (item.getItemId()) {
		case R.id.menu_sync :
			new LoadNewIncidentsTask(this).execute();
			loadList();
			return true;
		case R.id.menu_search :
			return true;
		default :
			return super.onOptionsItemSelected(item);
		}
	}

	private void loadList() {
		IncidentDAO dao = new IncidentSqLiteDAO(this);
		incidents = dao.getIncidents();
		dao.close();
		
		IncidentListAdapter adapter = new IncidentListAdapter(this, incidents);
		incidentListView.setAdapter(adapter);
	}
}
