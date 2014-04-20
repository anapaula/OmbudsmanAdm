package br.com.usp.ime.ombudsmanadm.view;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.usp.ime.ombudsmanadm.R;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentDAO;
import br.com.usp.ime.ombudsmanadm.model.dao.IncidentSqLiteDAO;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;
import br.com.usp.ime.ombudsmanadm.model.vo.Status;
import br.com.usp.ime.ombudsmanadm.util.ConnectionException;
import br.com.usp.ime.ombudsmanadm.util.WebClient;
import br.com.usp.ime.ombudsmanadm.view.adapter.IncidentListAdapter;

public class IncidentFormActivity extends Activity {
	
	final Context context = this;
	private Incident incident;
	private TextView id;
	private TextView user;
	private TextView localization;
	private TextView description;
	private TextView createdAt;
	private TextView updatedAt;
	private ImageView photoView;
	private ImageView localView;
	private Button status;
	final String[] choices = {"Aberto", "Em andamento", "Resolvido", "Escondido"};
	private Status newStatus;
	
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
		status = (Button) findViewById(R.id.status);
		
		status.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		 
					// set title
					alertDialogBuilder.setTitle("Atualizar status do incidente");
		 
					// set dialog message
					alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("Salvar",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								incident.setStatus(newStatus);
								IncidentDAO dao = new IncidentSqLiteDAO(context);
								dao.update(incident);
								
								incident = dao.getIncidentById(incident.getId());
								
								dao.close();
								
								updateStatus();
							}
						  })
						.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
							}
						});
					
					alertDialogBuilder.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							newStatus = Status.getFromString(choices[which]);
						}
						
					});
					
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
					
					// show it
					alertDialog.show();
		}});
		
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
		
		updateStatus();
		
		try {
			Date cre = parse(incident.getCreatedAt());
			Date upd = parse(incident.getUpdatedAt());
			
			createdAt.setText(toString(cre));
			updatedAt.setText(toString(upd));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateStatus() {
		switch (incident.getStatus().getStatusValue()) {
		case "Aberto":
			status.setText(Status.OPEN.getStatusValue());
			status.setBackgroundColor(getResources().getColor(R.color.open));
			break;
		case "Em andamento":
			status.setText(Status.WORKING_ON.getStatusValue());
			status.setBackgroundColor(getResources().getColor(R.color.working_on));
			break;
		case "Resolvido":
			status.setText(Status.SOLVED.getStatusValue());
			status.setBackgroundColor(getResources().getColor(R.color.solved));
			break;
		case "Escondido":
			status.setText(Status.HIDEN.getStatusValue());
			status.setBackgroundColor(getResources().getColor(R.color.hiden));
			break;
		default:
			Log.w(IncidentListAdapter.class.getSimpleName(), "Nao foi possivel encontrar o status com base no valor " + incident.getStatus().toString());
			status.setText(Status.OPEN.getStatusValue());
			status.setBackgroundColor(getResources().getColor(R.color.open));
			break;
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("incident", incident);
		super.onSaveInstanceState(outState);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static Date parse( String input ) throws java.text.ParseException {
	
	    SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
	    
	    if ( input.endsWith( "Z" ) ) {
	        input = input.substring( 0, input.length() - 1) + "GMT-00:00";
	    } else {
	        int inset = 6;
	    
	        String s0 = input.substring( 0, input.length() - inset );
	        String s1 = input.substring( input.length() - inset, input.length() );
	
	        input = s0 + "GMT" + s1;
	    }
	    
	    return df.parse( input );
	    
	}
	
    @SuppressLint("SimpleDateFormat")
	public static String toString( Date date ) {
        
        SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
        
        TimeZone tz = TimeZone.getTimeZone( "UTC" );
        
        df.setTimeZone( tz );

        String output = df.format( date );

        int inset0 = 9;
        int inset1 = 6;
        
        String s0 = output.substring( 0, output.length() - inset0 );
        String s1 = output.substring( output.length() - inset1, output.length() );

        String result = s0 + s1;

        result = result.replaceAll( "UTC", "+00:00" );
        
        return result;
        
    }
}