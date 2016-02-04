package org.webapp.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.webapp.models.ShowSpecBean;

public class ShowSpecBeanTest 
{
	@Test
	public void testGetPlaceInfo() 
	{
		ShowSpecBean bean = new ShowSpecBean();
		assertEquals("Metoda zwraca blad dla blednie wpisanego ID",bean.getPlaceInfo("0"), false );
	}

	@Test
	public void testZapiszDoBazy() 
	{
		ShowSpecBean bean = new ShowSpecBean();
		assertEquals("Metoda zapisz wizyte do bazy pozwala na wpisywanie b³êdnego formatu daty",
				bean.zapiszDoBazy("94040504156", 1, "prywatna", "kircholm", "09-08-2013", "tgfhhfdfhgth"), false );
	}
}
