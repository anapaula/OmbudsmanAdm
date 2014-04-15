package br.com.usp.ime.ombudsmanadm.task;

import java.util.List;

import br.com.usp.ime.ombudsmanadm.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.Incident;
import android.content.Context;
import android.os.AsyncTask;

public class QueryExecutorTask extends AsyncTask<String, Void, String> {
	
	private Context context;

	public QueryExecutorTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... query) {
		IncidentDAO dao = new IncidentDAO(context);
		List<Incident> incidents = dao.getIncidentsByKeyValue(query[0]);
		dao.close();
		return null;
	}

}
