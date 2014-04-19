package br.com.usp.ime.ombudsmanadm.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.util.DeptConveter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.Toast;

public class IncidentSorterTask extends AsyncTask<Object, Object, Map<String, List<Incident>>> {

	private final String DEFAULT_DEPTO = "Departamento desconhecido";
	
	private Context context;
	private IncidentSorterCallBack callBack;
	private ProgressDialog progress;
	
	public interface IncidentSorterCallBack {
		public void onIncidentSorterReturn(Map<String, List<Incident>> map, List<String> depts);
	}
	
	public IncidentSorterTask(Context context) {
		this.context = context;
		this.callBack = (IncidentSorterCallBack)context;
	}
	
	@Override
	protected void onPreExecute() {
		Resources rs = context.getResources();
		progress = ProgressDialog.show(context, rs.getString(R.string.wait), "Ordernando os incidentes por departamento", true, true);
	}
	
	@Override
	protected void onPostExecute(Map<String, List<Incident>> result) {
		progress.dismiss();
		Toast.makeText(context, "Incidentes ordenados com sucesso", Toast.LENGTH_LONG).show();
		
		List<String> depts = new ArrayList<>();
		for (String dept : result.keySet()) {
			depts.add(dept);
		}
		
		callBack.onIncidentSorterReturn(result, depts);
	}

	@Override
	protected Map<String, List<Incident>> doInBackground(Object... params) {
		IncidentDAO dao = new IncidentSqLiteDAO(context);
		List<Incident> incidents = dao.getIncidents();
		dao.close();
		
		Map<String, List<Incident>> map = new HashMap<>();
		for (Incident in : incidents) {
			String depto = DeptConveter.getDept(in.getLocalization());
			
			if (depto == null) {
				List<Incident> defaultList = (List<Incident>)map.get(DEFAULT_DEPTO);
				
				if (defaultList == null) {
					defaultList = new ArrayList<>();
				}
				
				defaultList.add(in);
				map.put(DEFAULT_DEPTO, defaultList);
				
			} else {
				List<Incident> sortedList = (List<Incident>)map.get(depto);
				
				if (sortedList == null) {
					sortedList = new ArrayList<>();
				}
				sortedList.add(in);
				map.put(depto, sortedList);
			}
		}
		
		return map;
	}

}
