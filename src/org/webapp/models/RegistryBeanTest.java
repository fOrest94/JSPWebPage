package org.webapp.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegistryBeanTest {

	@Test
	public void testZarejestruj() 
	{
		RegistryBean bean = new RegistryBean();
		UserBean beanU = new UserBean();
		beanU.setType("ewe");
		assertEquals("Metoda testZarejestruj() pozwala na uzycie pustego obiektu UserBean", bean.zarejestruj(beanU), false );
	}

	@Test
	public void testWalidacja() 
	{
		RegistryBean bean = new RegistryBean();
		assertEquals("Metoda Walidacja() pozwala na wpisywanie zbyt krotkich danych loginu i hasla", bean.walidacja("reter", "retere"), false );
	}

	@Test
	public void testCzyIstnieje() 
	{
		RegistryBean bean = new RegistryBean();
		assertEquals("Metoda testWalidacja() pozwala na duplikacje uzytkownikow w bazie!", bean.czyIstnieje("gytheio", "superwpiernik"),true);
	}

}
