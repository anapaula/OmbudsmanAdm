package br.com.usp.ime.ombudsmanadm.task;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class QueryExecutorTask extends AsyncTask<String, Void, String> {
	
	private Context context;

	public QueryExecutorTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... query) {
		IncidentDAO dao = new IncidentSqLiteDAO(context);
		List<Incident> incidents = dao.getIncidentsByKeyValue(query[0]);
		dao.close();
		return null;
	}

}
