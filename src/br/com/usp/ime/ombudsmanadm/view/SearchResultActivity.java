package br.com.usp.ime.ombudsmanadm.view;

import br.com.usp.ime.ombudsmanadm.R;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultActivity extends Activity {
	private TextView textQuery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("pegando valor de busca");
		setContentView(R.layout.search_result_list);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		textQuery = (TextView)findViewById(R.id.txtQuery);
		
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}
	
	private void handleIntent(Intent intent) {
		System.out.println("Pegando valor da busca ii");
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			
			textQuery.setText("Search query: " + query);
		}
	}
}
