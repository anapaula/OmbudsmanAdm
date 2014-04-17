package br.com.usp.ime.ombudsmanadm.view;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class IncidentFormActivity extends Activity {
	
	private Incident incident;
	private TextView id;
	private TextView user;
	private TextView localization;
	private TextView description;
	private TextView createdAt;
	private TextView updatedAt;
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.incident_form);
		setTitle("Incidente");
		
		incident = (Incident)getIntent().getSerializableExtra("incident");
		
		imageView = (ImageView)findViewById(R.id.photo);
		id = (TextView) findViewById(R.id.id);
		user = (TextView) findViewById(R.id.user);
		localization = (TextView) findViewById(R.id.localization);
		description = (TextView) findViewById(R.id.description);
		createdAt = (TextView) findViewById(R.id.createdAt);
		updatedAt = (TextView) findViewById(R.id.updatedAt);
		
		byte[] bytes = Base64.decode(incident.getPhoto(), Base64.DEFAULT);
		
		imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
		id.setText(""+incident.getId());
		user.setText(""+incident.getUser());
		localization.setText(incident.getLocalization());
		description.setText(incident.getDescription());
		createdAt.setText(incident.getCreatedAt());
		updatedAt.setText(incident.getUpdatedAt());
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("incident", incident);
		super.onSaveInstanceState(outState);
	}
}
