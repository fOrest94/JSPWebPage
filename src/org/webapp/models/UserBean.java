package org.webapp.models;

public class UserBean 
{
	private String imie;
	private String nazwisko;
	private String email;
	private String PESEL;
	private String login;
	private String haslo;
	private String telefon;
	private String specjalizacja;
	private String miasto;
	private String type;
		
	public String getImie() 
	{
		return imie;
	}
	
	public void setImie(String imie) 
	{
		this.imie = imie;
	}
	
	public String getNazwisko() 
	{
		return nazwisko;
	}
	
	public void setNazwisko(String nazwisko) 
	{
		this.nazwisko = nazwisko;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPESEL() 
	{
		return PESEL;
	}
	
	public void setPESEL(String PESEL) 
	{
		this.PESEL = PESEL;
	}
	
	public String getLogin() 
	{
		return login;
	}
	
	public void setLogin(String login) 
	{
		this.login = login;
	}
	
	public String getHaslo() 
	{
		return haslo;
	}
	
	public void setHaslo(String haslo) 
	{
		
		this.haslo = haslo;
	}
	
	public String getTelefon() 
	{
		return telefon;
	}
	
	public void setTelefon(String telefon) 
	{
		this.telefon = telefon;
	}
	
	public String getSpecjalizacja() 
	{
		return specjalizacja;
	}
	
	public void setSpecjalizacja(String specjalizacja) 
	{
		this.specjalizacja = specjalizacja;
	}
	
	public String getMiasto() 
	{
		return miasto;
	}
	
	public void setMiasto(String miasto) 
	{
		this.miasto = miasto;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
}
