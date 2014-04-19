package br.com.usp.ime.ombudsmanadm.view.adapter;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.model.vo.Status;

public class SearchListAdapter extends BaseAdapter {
	
	private final List<Incident> incidents;
	private final Activity activity;
	
	public SearchListAdapter(Activity activity, List<Incident> incidents) {
		this.activity = activity;
		this.incidents = incidents;
	}
	
	@Override
	public int getCount() {
		return incidents.size();
	}

	@Override
	public Object getItem(int index) {
		return incidents.get(index);
	}

	@Override
	public long getItemId(int index) {
		if (incidents.size() == 0) {
			return 0;
		}
		return incidents.get(index).getId();
	}

	@Override
	public View getView(int index, View convertView, ViewGroup group) {
		View view = activity.getLayoutInflater().inflate(R.layout.activity_search_list_item, null);
		Incident incident = incidents.get(index);
		
		LinearLayout background = (LinearLayout)view.findViewById(R.id.search_list_item_background);
		
		if (index % 2 == 0) {
			background.setBackgroundColor(activity.getResources().getColor(R.color.even_line));
		} else {
			background.setBackgroundColor(activity.getResources().getColor(R.color.odd_line));
		}
		
		TextView text = (TextView)view.findViewById(R.id.search_list_item_description);
		text.setText(incident.getDescription());
		
		TextView status = (TextView)view.findViewById(R.id.search_list_item_status);
		
		switch (incident.getStatus().getStatusValue()) {
		case "Aberto":
			status.setText(Status.OPEN.getStatusValue());
			status.setBackgroundColor(activity.getResources().getColor(R.color.open));
			break;
		case "Em andamento":
			status.setText(Status.WORKING_ON.getStatusValue());
			status.setBackgroundColor(activity.getResources().getColor(R.color.working_on));
			break;
		case "Resolvido":
			status.setText(Status.SOLVED.getStatusValue());
			status.setBackgroundColor(activity.getResources().getColor(R.color.solved));
			break;
		case "Escondido":
			status.setText(Status.HIDEN.getStatusValue());
			status.setBackgroundColor(activity.getResources().getColor(R.color.hiden));
			break;
		default:
			Log.w(IncidentListAdapter.class.getSimpleName(), "Nao foi possivel encontrar o status com base no valor " + incident.getStatus().toString());
			status.setText(Status.OPEN.getStatusValue());
			status.setBackgroundColor(activity.getResources().getColor(R.color.open));
			break;
		}
		
		return view;
	}
}