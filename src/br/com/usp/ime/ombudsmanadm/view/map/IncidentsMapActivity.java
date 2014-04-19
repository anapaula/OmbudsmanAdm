package br.com.usp.ime.ombudsmanadm.view.map;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
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

public class IncidentsMapActivity extends Activity {

	private static final int SECONDS = 30;

	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d(IncidentsMapActivity.class.getSimpleName(), "Iniciando a execucao");
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_incidents_map);

        // Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        
		// Centraliza o mapa no IME-USP.
		CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-23.558745, -46.731859)).zoom(17).bearing(90).tilt(60).build();
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        
		map.getUiSettings().setMyLocationButtonEnabled(true);
		
        addIncidentsOnMap(map);
    }
	
	@SuppressLint("ShowToast")
	private void addIncidentsOnMap(GoogleMap map) {
		IncidentDAO dao = new IncidentSqLiteDAO(this);
		List<Incident> incidents = dao.getIncidents();
		dao.close();
		
		int counter = 0;
		
		for (Incident incident : incidents) {
			if (incident.getLatitude() != null && incident.getLatitude().matches("(-)*[0-9][0-9].[0-9]*")
					&& incident.getLongitude() != null && incident.getLongitude().matches("(-)*[0-9][0-9].[0-9]*")) {

				LatLng lat = new LatLng(Double.parseDouble(incident.getLatitude()), Double.parseDouble(incident.getLongitude()));
				map.addMarker(new MarkerOptions()
						.title(incident.getDescription())
						.snippet(incident.getLocalization())
						.position(lat));
			} else {
				counter++;
				Log.d(IncidentsMapActivity.class.getSimpleName(), "incidente " + incident.getId() + " nao possui latitude ou longitude");
			}
		}
		
		if (counter > 0) {
			Toast.makeText(this, "Do Total de "+ incidents.size() +" incidentes." +
					" Somente " + (incidents.size() - counter) + " incidentes possuem informacoes de latitude ou longitude", SECONDS).show();
		}
	}
}