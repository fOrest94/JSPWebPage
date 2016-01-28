package org.webapp.models;

import org.webapp.database.Database;

public class ContactBean 
{
	private String type;
	private String email;
	private String opis;
	
	public ContactBean() 
	{
		
	}
	
	public ContactBean(String type, String email, String opis) 
	{
		this.type = type;
		this.email = email;
		this.opis = opis;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getOpis() 
	{
		return opis;
	}
	
	public void setOpis(String opis) 
	{
		this.opis = opis;
	}
	
	public boolean sendQuestion(String type, String email, String opis)
	{
		Database baza = new Database();
		String sql = "insert into kontakt values ('"+type+"','"+email+"','"+opis+"')";
		
		return baza.dodajDoBazy(baza.statement, sql);
	}
	
}
