package br.com.usp.ime.ombudsmanadm.task;

import android.content.Context;
import android.os.AsyncTask;

public class LoadNewIncidentsTask extends AsyncTask<Object, Object, String> {
	
	private Context context;
	
	public LoadNewIncidentsTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected String doInBackground(Object... arg0) {
		return null;
	}

}
