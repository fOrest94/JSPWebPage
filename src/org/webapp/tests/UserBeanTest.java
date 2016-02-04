package org.webapp.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.webapp.models.UserBean;

public class UserBeanTest 
{
	@Test
	public void testSprawdzHaslo() 
	{
		UserBean bean = new UserBean();
		assertEquals("Metoda zwraca blednie sprawdzone hasla",bean.sprawdzHaslo("malutki", "malutko"), false );
	}

	@Test
	public void testZmienHaslo() 
	{
		UserBean bean = new UserBean();
		assertEquals("Wewnetrzy problem w funkcji zmiany hasla",bean.zmienHaslo("malaka1", "kiszyna", "kiszyn", "pacjent"), false );
	}
}
