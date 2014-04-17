package br.com.usp.ime.ombudsmanadm.map.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("NewApi")
public class IncidentsLocatorMapActivity {
//extends ActionBarActivity {

	private final Context context;

	public IncidentsLocatorMapActivity(Context context) {
		super();
		this.context = context;
	}

//	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incidents_locator_map);
		
//		setTitle("@string/incidentsMapActivityTitle");

		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}

//		SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
//		GoogleMap googleMap = mapFragment.getMap();
		GoogleMap googleMap = null;
		for (Incident incident : getIncidents()) {
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(new LatLng(Double.parseDouble(incident.getLatitude()), 
					Double.parseDouble(incident.getLongitude())));
			markerOptions.title(incident.getDescription());
			markerOptions.snippet(incident.getLocalization());
			googleMap.addMarker(markerOptions);
		}
		
		/** Centraliza o mapa no IME-USP. Bearing define a posi��o da b�ssola. Tilt define o �ngulo de visualiza��o do mapa.
		 * O m�todo build constr�i uma inst�ncia de CameraPosition que � passada como par�metro para o m�todo newCameraPosition.*/
		CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-23.558745, -46.731859)).zoom(17).
				bearing(90).tilt(60).build();
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

	}

	private List<Incident> getIncidents() {
		IncidentDAO incidentDao = new IncidentSqLiteDAO(context);
		List<Incident> incidents = incidentDao.getIncidents();
		incidentDao.close();
		return incidents;
	}

//	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.incidents_locator_map, menu);
		return true;
	}

//	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
//		return super.onOptionsItemSelected(item);
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

//		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_incidents_locator_map, container, false);
			return rootView;
		}
	}
}
