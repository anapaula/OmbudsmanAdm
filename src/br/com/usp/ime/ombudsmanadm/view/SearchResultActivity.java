package br.com.usp.ime.ombudsmanadm.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.view.adapter.SearchListAdapter;

public class SearchResultActivity extends Activity {
	private static final int SECONDS = 1;
	
	private TextView textQuery;
	private List<Incident> incidents;
	private ListView incidentListView;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_result_list);
		
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
		
		incidentListView = (ListView) findViewById(R.id.search_result_list);
		
		textQuery = (TextView)findViewById(R.id.txtQuery);
		
		handleIntent(getIntent());
		
		incidentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "Item clicado " + position, SECONDS).show();
				
				Intent intent = new Intent(SearchResultActivity.this, IncidentFormActivity.class);
				intent.putExtra("incident", (Incident) incidentListView.getItemAtPosition(position));
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}
	
	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			
			IncidentDAO dao = new IncidentSqLiteDAO(this);
			incidents = dao.getIncidentsByKeyValue(query);
			dao.close();
			
			textQuery.setText("Encontrados " + incidents.size() + " resultados");
			
			SearchListAdapter adapter = new SearchListAdapter(this, incidents);
			incidentListView.setAdapter(adapter);
		}
	}
}
