package br.com.usp.ime.ombudsmanadm.util;

import java.io.IOException;

public class ConnectionException extends Exception {

	private static final long serialVersionUID = 261320209873827411L;

	public ConnectionException(String string, IOException e) {
		super(string, e);
	}

	public ConnectionException(String string) {
		super(string);
	}

}
