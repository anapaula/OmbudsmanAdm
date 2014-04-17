package br.com.usp.ime.ombudsmanadm.model.bo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.util.Log;
import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class IncidentConverter {

	public String toJSON(List<Incident> incidents) {
		try {
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("incidentrecordlist").array().object().key("incidentrecord").array();

			for (Incident incident : incidents) {
				jsonStringer.object().key("id").value(incident.getId())
						.key("user").value(incident.getUser())
						.key("description").value(incident.getDescription())
						.key("localization").value(incident.getLocalization())
						.key("latitude").value(incident.getLatitude())
						.key("longitude").value(incident.getLongitude())
						.key("photo").value(incident.getPhoto())
						.key("createdAt").value(incident.getCreatedAt())
						.key("updatedAt").value(incident.getUpdatedAt()).endObject();
			}
			return jsonStringer.endArray().endObject().endArray().endObject()
					.toString();

		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Incident> toIncidentList(String json) throws JSONException {
		List<Incident> incidents = new ArrayList<>();
		
		try {
			JSONObject root = new JSONObject(json);
			JSONArray array = root.getJSONArray("incidentrecordlist");
			
			Log.d(IncidentConverter.class.getSimpleName(), " convertendo string json no objeto Incident");
			for (int i = 0 ; i < array.length() ; i++) {
				JSONObject objectRoot = array.getJSONObject(i);
				JSONObject object = objectRoot.getJSONObject("incidentrecord");
				
				Incident incident = new Incident();
				incident.setId(object.getLong("id"));
				incident.setUser(object.getLong("user"));
				incident.setDescription(object.getString("description"));
				incident.setLocalization(object.getString("localization"));
				incident.setLatitude(object.getString("latitude"));
				incident.setLongitude(object.getString("longitude"));
				incident.setPhoto(object.getString("photo").getBytes());
				incident.setCreatedAt(object.getString("created_at"));
				incident.setUpdatedAt(object.getString("updated_at"));
				Log.d(IncidentConverter.class.getSimpleName(), " object Incident convertido " + incident.toString());
				incidents.add(incident);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return incidents;
	}
}