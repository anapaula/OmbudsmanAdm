package br.com.usp.ime.ombudsmanadm.task;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class QueryExecutorTask extends AsyncTask<String, Void, List<Incident>> {
	
	private Context context;
	private QueryExecutorCallBack callBack;
	private ProgressDialog progress;
	private List<Incident> incidents;
	
	public interface QueryExecutorCallBack {
		public void onQueryExecutorReturn(List<Incident> incidents);
	}

	public QueryExecutorTask(Context context) {
		this.context = context;
		this.callBack = (QueryExecutorCallBack)context;
	}
	
	@Override
	protected void onPreExecute() {
		Resources rs = context.getResources();
		progress = ProgressDialog.show(context, rs.getString(R.string.wait), "Buscando incidentes", true, true);
	}
	
	@Override
	protected void onPostExecute(List<Incident> result) {
		progress.dismiss();
		
		Toast.makeText(context, result.size() + " Incidentes recuperados", Toast.LENGTH_LONG).show();
		
		callBack.onQueryExecutorReturn(result);
		
	}
	
	@Override
	protected List<Incident> doInBackground(String... query) {
		IncidentDAO dao = new IncidentSqLiteDAO(context);
		incidents = dao.getIncidentsByKeyValue(query[0]);
		dao.close();
		
		return incidents;
	}

}
