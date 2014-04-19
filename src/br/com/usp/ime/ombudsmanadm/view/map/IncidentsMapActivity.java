package br.com.usp.ime.ombudsmanadm.view.map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import br.com.usp.ime.ombudsmanadm.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class IncidentsMapActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d(IncidentsMapActivity.class.getSimpleName(), "Iniciando a execucao");
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_incidents_map);

        // Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        LatLng sydney = new LatLng(-33.867, 151.206);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
        
        // Move the camera instantly to hamburg with a zoom of 15.
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
	
	
	
	
	
	
	
	

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.fragment_incidents_locator_map);
//		
////		setTitle("@string/incidentsMapActivityTitle");
//
////		if (savedInstanceState == null) {
////			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
////		}
////
////		SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
////		
////		GoogleMap googleMap = mapFragment.getMap();
////		
////		for (Incident incident : getIncidents()) {
////			MarkerOptions markerOptions = new MarkerOptions();
////			markerOptions.position(new LatLng(Double.parseDouble(incident.getLatitude()), 
////					Double.parseDouble(incident.getLongitude())));
////			markerOptions.title(incident.getDescription());
////			markerOptions.snippet(incident.getLocalization());
////			googleMap.addMarker(markerOptions);
////		}
////		
////		/** Centraliza o mapa no IME-USP. Bearing define a posi????o da b??ssola. Tilt define o ??ngulo de visualiza????o do mapa.
////		 * O metodo build constroi uma instancia de CameraPosition que ?? passada como par??metro para o m??todo newCameraPosition.*/
////		CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-23.558745, -46.731859)).zoom(17).bearing(90).tilt(60).build();
////		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//	}
}
