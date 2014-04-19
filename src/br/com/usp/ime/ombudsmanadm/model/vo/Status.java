package br.com.usp.ime.ombudsmanadm.model.vo;

import android.util.Log;

public enum Status {
	OPEN("Aberto"),
	WORKING_ON("Em andamento"),
	SOLVED("Resolvido"),
	HIDEN("Escondido");
	
	private String status;
	
	Status(String status) {
		this.status = status;
	}
	
	public boolean equals(String status) {
		return this.status.equalsIgnoreCase(status);
	}
	
	public String getStatusValue() {
		return status;
	}
	
	public static Status getFromString(String statusString) {
		for (Status s : Status.values()) {
			if (s.getStatusValue().equals(statusString)) {
				return s;
			}
		}
		
		Log.w(Status.class.getSimpleName(), "Nao foi possivel encontrar o status com base no valor " + statusString);
		return Status.OPEN;
	}
}
