package br.com.usp.ime.ombudsmanadm.view.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class SortedIncidentListAdapter extends BaseAdapter {
	
	List<String> depts = new ArrayList<>();
	Map<String, List<Incident>> map;
	Activity activity;
	
	public SortedIncidentListAdapter(Activity activity, Map<String, List<Incident>> map, List<String> depts) {
		this.activity = activity;
		this.map = map;
		this.depts = depts;
	}
	
	@Override
	public int getCount() {
		return depts.size();
	}

	@Override
	public Object getItem(int index) {
		return depts.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup group) {
		View view = activity.getLayoutInflater().inflate(R.layout.activity_dept_list_item, null);
		String dept = depts.get(index);
		
		LinearLayout background = (LinearLayout)view.findViewById(R.id.dept_list_item_background);
		
		if (index % 2 == 0) {
			background.setBackgroundColor(activity.getResources().getColor(R.color.even_line));
		} else {
			background.setBackgroundColor(activity.getResources().getColor(R.color.odd_line));
		}
		
		TextView text = (TextView)view.findViewById(R.id.dept_list_item);
		text.setText(dept);
		
		TextView quant = (TextView)view.findViewById(R.id.dept_list_item_quant);
		quant.setText("(" + map.get(dept).size() + " incidentes)");
		
		return view;
	}
}