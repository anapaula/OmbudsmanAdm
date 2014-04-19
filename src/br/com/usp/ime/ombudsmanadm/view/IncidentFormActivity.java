package br.com.usp.ime.ombudsmanadm.view;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.util.ConnectionException;
import br.com.usp.ime.ombudsmanadm.util.WebClient;

public class IncidentFormActivity extends Activity {
	
	private Incident incident;
	private TextView id;
	private TextView user;
	private TextView localization;
	private TextView description;
	private TextView createdAt;
	private TextView updatedAt;
	private ImageView photoView;
	private ImageView localView;
	
	@SuppressLint({ "NewApi", "SimpleDateFormat" })
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incident_form);
		setTitle("Incidente");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		incident = (Incident)getIntent().getSerializableExtra("incident");
		
		photoView = (ImageView)findViewById(R.id.photo);
		localView = (ImageView)findViewById(R.id.local);
		id = (TextView) findViewById(R.id.id);
		user = (TextView) findViewById(R.id.user);
		localization = (TextView) findViewById(R.id.localization);
		description = (TextView) findViewById(R.id.description);
		createdAt = (TextView) findViewById(R.id.createdAt);
		updatedAt = (TextView) findViewById(R.id.updatedAt);
		
		byte[] bytes = Base64.decode(incident.getPhoto(), Base64.DEFAULT);
		
		
		String latlon = incident.getLatitude() + "," + incident.getLongitude();
		String mapStaticUrl ="http://maps.google.com/maps/api/staticmap?" +
				"center=" + URLEncoder.encode(latlon) +
				"&zoom=12" +
				"&size=1200x200" +
				"&markers=color:red" +
				URLEncoder.encode("|") +
				"label:i" +
				URLEncoder.encode("|") +
				URLEncoder.encode(latlon) +
				"&sensor=false";
		
		InputStream imageStream = null;
		try {
			imageStream = new WebClient(mapStaticUrl).get();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		
		photoView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
		localView.setImageBitmap(BitmapFactory.decodeStream(imageStream));
		id.setText(""+incident.getId());
		user.setText(""+incident.getUser());
		localization.setText(incident.getLocalization());
		description.setText(incident.getDescription());
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			createdAt.setText(sdf.format(incident.getCreatedAt()).toString());
			updatedAt.setText(sdf.format(incident.getUpdatedAt()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("incident", incident);
		super.onSaveInstanceState(outState);
	}
}
