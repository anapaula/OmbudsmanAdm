package br.com.usp.ime.ombudsmanadm.util;

import java.io.IOException;

public class ConnectionException extends Exception {

	public ConnectionException(String string, IOException e) {
		super(string, e);
	}

	public ConnectionException(String string) {
		super(string);
	}

}
