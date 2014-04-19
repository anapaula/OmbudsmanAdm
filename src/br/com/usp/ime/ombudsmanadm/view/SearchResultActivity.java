package br.com.usp.ime.ombudsmanadm.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
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
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.task.QueryExecutorTask;
import br.com.usp.ime.ombudsmanadm.task.QueryExecutorTask.QueryExecutorCallBack;
import br.com.usp.ime.ombudsmanadm.view.adapter.SearchListAdapter;

public class SearchResultActivity extends Activity implements  QueryExecutorCallBack {
	private static final int SECONDS = 1;
	
	private TextView resultSearch;
	private ListView incidentListView;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search_list);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		incidentListView = (ListView) findViewById(R.id.search_list);
		resultSearch = (TextView)findViewById(R.id.result_search);
		
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
			new QueryExecutorTask(this).execute(query);
		}
	}

	@Override
	public void onQueryExecutorReturn(List<Incident> incidents) {
		resultSearch.setText(incidents.size() + " incidentes encontrados");
		resultSearch.setBackgroundColor(this.getResources().getColor(R.color.search_result));
		
		SearchListAdapter adapter = new SearchListAdapter(this, incidents);
		incidentListView.setAdapter(adapter);
	}
}
