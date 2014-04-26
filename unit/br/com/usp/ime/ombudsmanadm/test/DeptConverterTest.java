package br.com.usp.ime.ombudsmanadm.test;

import junit.framework.Assert;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import br.com.usp.ime.ombudsmanadm.util.DeptConveter;


public class DeptConverterTest extends ActivityInstrumentationTestCase2<Activity>{
	
	public DeptConverterTest(Class<Activity> activityClass) {
		super(activityClass);
	}
	
	public DeptConverterTest() {
		super(Activity.class);
	}

	public void testConvertToFullNameFromAbbr() {
		Assert.assertEquals("Instituto de Matemática e Estatística", DeptConveter.getDept("IME"));
	}
	
	public void testConvertToFullNameFromFullName() {
		Assert.assertEquals("Instituto de Matemática e Estatística", DeptConveter.getDept("Instituto de Matemática e Estatística"));
	}
}
