package br.com.usp.ime.ombudsmanadm.map.view;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBarActivity;

@SuppressLint("NewApi")
public class IncidentsLocatorMapActivity extends ActionBarActivity {

//	private final Context context;
//
//	public IncidentsLocatorMapActivity(Context context) {
//		super();
//		this.context = context;
//	}
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_incidents_locator_map);
//
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
//
//		MapFragment mapFragment = ((MapFragment) getFragmentManager()
//				.findFragmentById(R.id.map));
//		GoogleMap googleMap = mapFragment.getMap();
//		for (Incident incident : getIncidents()) {
//			MarkerOptions markerOptions = new MarkerOptions();
//			markerOptions.position(new LatLng(Double.parseDouble(incident.getLatitude()), 
//					Double.parseDouble(incident.getLongitude())));
//			markerOptions.title(incident.getDescription());
//			markerOptions.snippet(incident.getLocalization());
//			googleMap.addMarker(markerOptions);
//		}
//
//	}
//
//	private List<Incident> getIncidents() {
//		IncidentDAO incidentDao = new IncidentSqLiteDAO(context);
//		List<Incident> incidents = incidentDao.getIncidents();
//		incidentDao.close();
//		return incidents;
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.incidents_locator_map, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(
//					R.layout.fragment_incidents_locator_map, container, false);
//			return rootView;
//		}
//	}

}