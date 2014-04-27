package br.com.usp.ime.ombudsmanadm.test;

import br.com.usp.ime.ombudsmanadm.util.LoginParser;
import br.com.usp.ime.ombudsmanadm.util.LoginResponse;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

public class LoginParserTest extends ActivityInstrumentationTestCase2<Activity> {

	public LoginParserTest(Class<Activity> activityClass) {
		super(activityClass);
	}
	
	public LoginParserTest() {
		super(Activity.class);
	}
	
	public void testParseLoginSuccessfully() {
		String json = "{\"ok\":true,\"nusp\":\"6109460\",\"username\":\"ana\",\"email\":\"ana@ime.usp.br\"}";
		LoginResponse response = new LoginParser().toLoginResponse(json);
		assertNotNull(response);
		assertEquals("ana@ime.usp.br", response.getUser().getEmail());
		assertEquals("ana", response.getUser().getName());
		assertEquals("6109460", response.getUser().getUspNumber());
		assertNull(response.getError());
	}
	
	public void testParseErrorExecption() {
		
		String json = "{\"ok\":false,\"error\":\"O par usu\u00e1rio/senha est\u00e1 incorreto.\"}";
		LoginResponse response = new LoginParser().toLoginResponse(json);
		assertNotNull(response.getError());
	}

}
