package org.webapp.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginBeanTest 
{

	@Test
	public void testWalidacja() 
	{
		LoginBean bean = new LoginBean("malpa", "213has","Pacjent");
		
		assertEquals("Metoda Walidacja() pozwala na wpisywanie zbyt krotkich danych loginu i hasla", bean.walidacja(bean.getLogin(),bean.getHaslo()), false );
	}
	
	@Test
	public void testgetCookie() 
	{
		LoginBean bean = new LoginBean("moghom", "dBioQ139","");
		
		assertEquals("Metoda sprawdzania hasla w bazie nie dziala prawidlowo", bean.czyIstnieje(bean.getType()), false );
	}

	
}
