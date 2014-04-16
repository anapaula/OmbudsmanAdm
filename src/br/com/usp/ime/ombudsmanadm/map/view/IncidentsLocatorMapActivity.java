package br.com.usp.ime.ombudsmanadm.map.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("NewApi")
public class IncidentsLocatorMapActivity extends ActionBarActivity {

	private final Context context;

	public IncidentsLocatorMapActivity(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incidents_locator_map);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}

		MapFragment mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
		GoogleMap googleMap = mapFragment.getMap();
		for (Incident incident : getIncidents()) {
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(new LatLng(Double.parseDouble(incident.getLatitude()), 
					Double.parseDouble(incident.getLongitude())));
			markerOptions.title(incident.getDescription());
			markerOptions.snippet(incident.getLocalization());
			googleMap.addMarker(markerOptions);
		}
		
		/** Centraliza o mapa para o estado de São Paulo. Bearing define a posição da bússola. Tilt define o ângulo de visualização do mapa.
		 * O método build constrói uma instância de CameraPosition que é passada como parâmetro para o método newCameraPosition.*/
		CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-22.411029, -49.002686)).zoom(17).
				bearing(90).tilt(60).build();
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

	}

	private List<Incident> getIncidents() {
		IncidentDAO incidentDao = new IncidentSqLiteDAO(context);
		List<Incident> incidents = incidentDao.getIncidents();
		incidentDao.close();
		return incidents;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.incidents_locator_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_incidents_locator_map, container, false);
			return rootView;
		}
	}

}
