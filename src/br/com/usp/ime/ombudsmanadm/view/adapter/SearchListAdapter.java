package br.com.usp.ime.ombudsmanadm.view.adapter;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

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
		View view = activity.getLayoutInflater().inflate(R.layout.search_list_item, null);
		Incident incident = incidents.get(index);
		
		LinearLayout background = (LinearLayout)view.findViewById(R.id.search_list_item_background);
		
		if (index % 2 == 0) {
			background.setBackgroundColor(activity.getResources().getColor(R.color.even_line));
		} else {
			background.setBackgroundColor(activity.getResources().getColor(R.color.odd_line));
		}
		
		TextView text = (TextView)view.findViewById(R.id.search_list_item_description);
		text.setText(incident.getDescription());
		
		return view;
	}
}