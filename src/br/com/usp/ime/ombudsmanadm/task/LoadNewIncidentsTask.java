package br.com.usp.ime.ombudsmanadm.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.bo.IncidentConverter;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.util.ConnectionException;
import br.com.usp.ime.ombudsmanadm.util.WebClient;

public class LoadNewIncidentsTask extends AsyncTask<Object, Object, String> {
	
	private Context context;
	private ProgressDialog progress;
	private static String URL = "http://uspservices.deusanyjunior.dj/incidente/%s.json";
	
	public LoadNewIncidentsTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		Resources rs = context.getResources();
		progress = ProgressDialog.show(context, rs.getString(R.string.wait), rs.getString(R.string.getData), true, true);
	}
	
	@Override
	protected void onPostExecute(String result) {
		progress.dismiss();
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected String doInBackground(Object... params) {
		IncidentDAO dao = new IncidentSqLiteDAO(context);
		long lastId = dao.getLastIncidentId();
		
		try {
			InputStream stream = new WebClient(String.format(URL, lastId + 1)).get();
			
			if (stream != null) {
				String json = getStringFromInputStream(stream);
				List<Incident> incidents = new IncidentConverter().toIncidentList(json);
				
				for (Incident incident : incidents) {
					dao.insert(incident);
				}
				dao.close();
				
				return (incidents.size() != 0) ? "Foram encontrados " + incidents.size() + " incidentes novos" : "Não há incidentes novos"; 
			}
			return null;
		} catch (ConnectionException | JSONException e) {
			e.printStackTrace();
			return "erro ao obter os ultimos incidentes, msg=" + e.getMessage();
		}
	}
	
	private static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
	}
}
