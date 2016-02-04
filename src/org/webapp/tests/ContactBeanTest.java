package org.webapp.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.webapp.models.ContactBean;

public class ContactBeanTest extends ContactBean {

	@Test
	public void test() 
	{
		ContactBean bean = new ContactBean();
		assertEquals("Wewnetrzny problem w funkcji wysylania wiadomosci",bean.sendQuestion("dfgdfg", "rfgdfgd", "tht65uy"), true );
	}

}
