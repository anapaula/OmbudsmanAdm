package br.com.usp.ime.ombudsmanadm.task;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.usp.ime.ombudsmanadm.model.bo.IncidentConverter;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.util.ConnectionException;
import br.com.usp.ime.ombudsmanadm.util.WebClient;

public class SendIncidentsTask extends AsyncTask<Object, Object, String> {

	private final Context context;
	private final String address = "http://uspservices.deusanyjunior.dj/incidente";
	private ProgressDialog progress;

	public SendIncidentsTask(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "@string/wait",
				"@string/sendData", true, true);
	}

	@Override
	protected String doInBackground(Object... params) {
		IncidentDAO dao = new IncidentSqLiteDAO(context);
		List<Incident> incidentList = dao.getIncidents();
		dao.close();
		String jsonList = new IncidentConverter().toJSON(incidentList);
		String responseJson = null;
		try {
			responseJson = new WebClient(address).post(jsonList);
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return responseJson;
	}

	@Override
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}

}
