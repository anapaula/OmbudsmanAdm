package br.com.usp.ime.ombudsmanadm.model.bo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class IncidentConverter {

	public String toJSON(List<Incident> incidents) {
		try {
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("incidentrecordlist").array().object().key("incidentrecord").array();

			for (Incident incident : incidents) {
				jsonStringer.object().key("id").value(incident.getId())
						.key("user").value(incident.getUspNumber())
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
		
		JSONObject root = new JSONObject(json);
		JSONArray array = root.getJSONArray("incidentrecordlist");
		
		for (int i = 0 ; i < array.length() ; i++) {
			JSONObject objectRoot = array.getJSONObject(i);
			JSONObject object = objectRoot.getJSONObject("incidentrecord");
			
			Incident incident = new Incident();
			incident.setId(object.getLong("id"));
			incident.setUspNumber(object.getLong("user"));
			incident.setDescription(object.getString("description"));
			incident.setLocalization(object.getString("localization"));
			incident.setLatitude(object.getDouble("latitude"));
			incident.setLongitude(object.getDouble("longitude"));
			incident.setPhoto(object.getString("photo").getBytes());
			incident.setCreatedAt(object.getString("createdAt"));
			incident.setUpdatedAt(object.getString("updatedAt"));
			
			incidents.add(incident);
		}
		
		return incidents;
	}
	
//	public static void main(String[] args) throws JSONException {
//		List<Incident> incidents = new ArrayList<>();
//		Incident incident = new Incident();
//		incident.setId(1L);
//		incident.setDescription("teste");
//		incident.setUspNumber(883772L);
//		incident.setLocalization("Itaqua");
//		incident.setLatitude(23232.23);
//		incident.setLongitude(12121.32);
//		incident.setPhoto("asdhkgsd7687".getBytes());
//		incident.setCreatedAt("23.12.23");
//		incident.setUpdatedAt("23.54.65");
//		
//		incidents.add(incident);
//		
//		String json = new IncidentConverter().toJSON(incidents);
//		
//		System.out.println(new IncidentConverter().toIncidentList(json));
//	}
}