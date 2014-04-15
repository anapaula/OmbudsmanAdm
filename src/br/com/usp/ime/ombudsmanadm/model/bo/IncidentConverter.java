package br.com.usp.ime.ombudsmanadm.model.bo;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import br.com.usp.ime.ombudsmanadm.model.vo.Incident;

public class IncidentConverter {

	public String toJSON(List<Incident> incidents) {
		try {
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("list").array().object().key("incident")
					.array();

			for (Incident incident : incidents) {
				jsonStringer.object().key("id").value(incident.getId())
						.key("uspNumber").value(incident.getUspNumber())
						.key("description").value(incident.getDescription())
						.key("localization").value(incident.getLocalization())
						.key("latitude").value(incident.getLatitude())
						.key("longitude").value(incident.getLongitude())
						.key("photo").value(incident.getPhoto()).endObject();
			}
			return jsonStringer.endArray().endObject().endArray().endObject()
					.toString();

		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

}
